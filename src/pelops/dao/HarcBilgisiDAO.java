package pelops.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.controller.AktifBean;
import pelops.db.DBConnection;
import pelops.model.HarcBilgisi;

public class HarcBilgisiDAO extends DBConnection {

	ArrayList<HarcBilgisi> harcListesi = new ArrayList<HarcBilgisi>();
	private String SQL;
	private PreparedStatement pstmt;
	private Statement stm;
	private ResultSet rs;
	HesapDAO dao = new HesapDAO();

	@SuppressWarnings("unused")
	public boolean kaydet(HarcBilgisi harcbilgisi) {

		boolean kaydedildi = false;

		boolean hesapResult;

		SQL = "INSERT INTO tbl_harc_bilgisi(harc_tarihi, harc_tipi," + " harc_orani, harc_miktari, uygulama_asamasi, "
				+ "icra_dosyasi_id) VALUES ( ?, ?, ?, ?, ?,  ?);";

		try {

			newConnectDB();

			pstmt = conn.prepareStatement(SQL);
			java.sql.Date dateHarc = convertFromJAVADateToSQLDate(harcbilgisi.getHarc_tarihi());
			pstmt.setDate(1, dateHarc);
			pstmt.setString(2, harcbilgisi.getHarc_tipi());
			pstmt.setDouble(3, harcbilgisi.getHarc_orani());
			pstmt.setDouble(4, harcbilgisi.getHarc_miktari());
			pstmt.setString(5, harcbilgisi.getUygulama_asamasi());
			pstmt.setInt(6, AktifBean.getIcraDosyaID());

			int result = pstmt.executeUpdate();

			disconnectDB();

			if (result == 1) {

				kaydedildi = true;

			}

			

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		return kaydedildi;

	}

//	 public void hesaplaraEkle(int icraDosyaID, double harcMiktari) throws
//	 Exception {
//	
//		 HesapDAO hesap = new HesapDAO();
//		 int hesapID = hesap.HesapIdGetir(icraDosyaID);
//		 double digerHarclar = hesap.Liste(hesapID).getDiger_harclar(), 
//				toplamAlacak = hesap.Liste(hesapID).getToplam_alacak(), 
//				kalanAlacak = hesap.Liste(hesapID).getKalan_alacak();
//	
//		 
//		 
//	 SQL = "UPDATE tbl_hesap  SET diger_harclar=?, toplam_alacak=?, kalan_alacak=?  WHERE id=" + hesapID + ";";
//	 System.out.println(SQL);
//	 newConnectDB();
//	 PreparedStatement pstmt1 = conn.prepareStatement(SQL.toString());
//	
//	 pstmt1.setDouble(1, (harcMiktari+digerHarclar));
//	 pstmt1.setDouble(2, (harcMiktari+toplamAlacak));
//	 pstmt1.setDouble(3, (harcMiktari+kalanAlacak));
//	
//	 
//	 pstmt1.executeUpdate();
//	 disconnectDB();
//	
//	 }

	public ArrayList<HarcBilgisi> getAllListFromIcraDosyaID(int id) throws Exception {

		ArrayList<HarcBilgisi> list = new ArrayList<HarcBilgisi>();
		SQL = "SELECT id, harc_tarihi, harc_tipi, harc_orani, harc_miktari,"
				+ " uygulama_asamasi, icra_dosyasi_id FROM tbl_harc_bilgisi where icra_dosyasi_id="+id;
		newConnectDB();
		stm = conn.createStatement();
		rs = stm.executeQuery(SQL);
		while (rs.next()) {
			HarcBilgisi bilgisi = new HarcBilgisi();
			bilgisi.setId(rs.getInt("id"));
			bilgisi.setHarc_miktari(rs.getDouble("harc_miktari"));
			bilgisi.setHarc_orani(rs.getDouble("harc_orani"));
			bilgisi.setHarc_tarihi(rs.getDate("harc_tarihi"));
			bilgisi.setHarc_tipi(rs.getString("harc_tipi"));
			bilgisi.setUygulama_asamasi(rs.getString("uygulama_asamasi"));

			list.add(bilgisi);

		}

		disconnectDB();

		return list;
	}

	
	
	public HarcBilgisi getHarcBilgisi(int id) {
		SQL = "SELECT id, harc_tarihi, harc_tipi, harc_orani, harc_miktari,"
				+ " uygulama_asamasi, icra_dosyasi_id FROM tbl_harc_bilgisi where id =" + id + ";";
		newConnectDB();
		HarcBilgisi harc = new HarcBilgisi();

		try {
			stm = conn.createStatement();
			rs = stm.executeQuery(SQL);
			while (rs.next()) {

				harc.setId(rs.getInt("id"));
				harc.setHarc_miktari(rs.getDouble("harc_miktari"));
				harc.setHarc_orani(rs.getDouble("harc_orani"));
				harc.setHarc_tarihi(rs.getDate("harc_tarihi"));
				harc.setHarc_tipi(rs.getString("harc_tipi"));
				harc.setUygulama_asamasi(rs.getString("uygulama_asamasi"));

			}

			disconnectDB();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return harc;
	}

	public boolean guncelle(HarcBilgisi harc) throws Exception {

		boolean duzenlendi = false;

		SQL = "UPDATE tbl_harc_bilgisi SET  harc_tarihi=?, harc_tipi=?, harc_orani=?, harc_miktari=?, "
				+ "uygulama_asamasi=?, icra_dosyasi_id=? WHERE id=" + harc.getId() + ";";
		HarcBilgisi oldHarc = getHarcBilgisi(harc.getId());
		newConnectDB();
		System.out.println(oldHarc.getHarc_miktari());
		pstmt = conn.prepareStatement(SQL);
		java.sql.Date date = convertFromJAVADateToSQLDate(harc.getHarc_tarihi());
		pstmt.setDate(1, date);

		pstmt.setString(2, harc.getHarc_tipi());
		pstmt.setDouble(3, harc.getHarc_orani());
		pstmt.setDouble(4, harc.getHarc_miktari());
		pstmt.setString(5, harc.getUygulama_asamasi());
		pstmt.setInt(6, AktifBean.getIcraDosyaID());

		int sonuc = pstmt.executeUpdate();
		disconnectDB();
		if (sonuc == 1) {
			duzenlendi = true;

			dao.guncelleHarc(AktifBean.getIcraDosyaID(), (harc.getHarc_miktari() - oldHarc.getHarc_miktari()));
		}

		return duzenlendi;
	}

	public boolean Sil(int id) throws Exception {
		SQL = "DELETE FROM tbl_harc_bilgisi where id=" + id;

		
		newConnectDB();
		stm = conn.createStatement();
		 boolean silindi = stm.execute(SQL);
		//boolean silindi = stm.executeQuery(SQL) != null;

		disconnectDB();

		return silindi;
	}

	public java.sql.Date convertFromJAVADateToSQLDate(java.util.Date javaDate) {
		java.sql.Date sqlDate = null;
		if (javaDate != null) {
			sqlDate = new Date(javaDate.getTime());
		}
		return sqlDate;
	}

}
