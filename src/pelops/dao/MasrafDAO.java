package pelops.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import pelops.controller.AktifBean;
import pelops.db.DBConnection;
import pelops.model.MasrafBilgisi;

public class MasrafDAO extends DBConnection {

	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	String SQL;
	double oldTahsilatTutari = 0;
	double newTahsilatTutari = 0;

	ArrayList<MasrafBilgisi> masrafListesi = new ArrayList<MasrafBilgisi>();

	public boolean kaydet(MasrafBilgisi masraf) throws Exception {

		java.sql.Date dateMasraf = convertFromJAVADateToSQLDate(masraf.getMasrafTarihi());

		boolean kaydedildi = false;

		SQL = "INSERT INTO tbl_masraf_bilgisi("
				+ " miktar, aciklama, tarih, personel_id, masraf_tipi_id, icra_dosyasi_id, "
				+ " borclu_id, uygulama_asamasi_id) " + "VALUES ( ?, ?, ?, ?, ?, ?,  ?, ?);";

		newConnectDB();

		pstmt = conn.prepareStatement(SQL);

		pstmt.setDouble(1, masraf.getMasrafMiktari());
		pstmt.setString(2, masraf.getMasrafAciklama());
		pstmt.setDate(3, dateMasraf);
		pstmt.setInt(4, masraf.getMasrafPersonel_adi_id());
		pstmt.setInt(5, masraf.getMasrafTipiId());
		pstmt.setInt(6, AktifBean.getIcraDosyaID());
		pstmt.setInt(7, masraf.getBorcluId());
		pstmt.setInt(8, masraf.getMasrafUygulamaAsamasiId());

		int sonuc = pstmt.executeUpdate();
		disconnectDB();
		if (sonuc == 1) {

			kaydedildi = true;

			// hesaplaraEkle(AktifBean.getIcraDosyaID(), masraf);
		}

		return kaydedildi;

	}

	public void hesaplaraEkle(int icraDosyaID, MasrafBilgisi masraf) throws Exception {

		SQL = "SELECT tahsilat_tutari FROM tbl_hesap where id='" + AktifBean.getIcraDosyaID() + "';";

		newConnectDB();

		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);

		while (rs.next()) {

			oldTahsilatTutari = rs.getDouble("tahsilat_tutari");

		}

		newTahsilatTutari = oldTahsilatTutari + masraf.getMasrafMiktari();

		SQL = "UPDATE tbl_hesap SET tahsilat_tutari=? WHERE id=" + AktifBean.getIcraDosyaID() + ";";

		pstmt = conn.prepareStatement(SQL);

		pstmt.setDouble(1, newTahsilatTutari);

		pstmt.executeUpdate();

		disconnectDB();

	}

	public void hesaplaraEkleForDelete(int icraDosyaID, MasrafBilgisi masraf) throws Exception {

		SQL = "SELECT tahsilat_tutari FROM tbl_hesap where id='" + AktifBean.getIcraDosyaID() + "';";

		newConnectDB();

		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);

		while (rs.next()) {

			oldTahsilatTutari = rs.getDouble("tahsilat_tutari");

		}

		newTahsilatTutari = oldTahsilatTutari - masraf.getMasrafMiktari();

		SQL = "UPDATE tbl_hesap SET tahsilat_tutari=? WHERE id=" + AktifBean.getIcraDosyaID() + ";";

		pstmt = conn.prepareStatement(SQL);

		pstmt.setDouble(1, newTahsilatTutari);

		pstmt.executeUpdate();

		disconnectDB();

	}

	public java.sql.Date convertFromJAVADateToSQLDate(java.util.Date javaDate) {
		java.sql.Date sqlDate = null;
		if (javaDate != null) {
			sqlDate = new Date(javaDate.getTime());
		}
		return sqlDate;
	}

	public ArrayList<MasrafBilgisi> getAllListFromIcraDosyaID(int id) throws Exception {

		ArrayList<MasrafBilgisi> list = new ArrayList<MasrafBilgisi>();

		SQL = "SELECT m.id, m.miktar, m.aciklama, m.tarih, u.\"adSoyad\", "
				+ "mt.adi as mtadi, m.uygulama_asamasi_id , m.icra_dosyasi_id, uy.adi as uyadi"
				+ ", m.personel_id, m.masraf_tipi_id, m.uygulama_asamasi_id" + " FROM tbl_masraf_bilgisi m "
				+ "inner join tbl_user u on m.personel_id=u.id "
				+ "inner join tbl_masraf_tipi mt on m.masraf_tipi_id = mt.id "
				+ "inner join tbl_uygulama_asamasi uy on m.uygulama_asamasi_id=uy.id where m.icra_dosyasi_id = "+ id + ";";

		newConnectDB();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);

		while (rs.next()) {

			MasrafBilgisi masraf = new MasrafBilgisi();
			masraf.setId(rs.getInt("id"));
			masraf.setMasrafAciklama(rs.getString("aciklama"));
			masraf.setMasrafMiktari(rs.getDouble("miktar"));
			masraf.setMasrafTarihi(rs.getDate("tarih"));
			masraf.setPersonelName(rs.getString("adSoyad"));
			masraf.setMasrafUygulamaAsamasiName(rs.getString("uyadi"));
			masraf.setMasrafTipiName(rs.getString("mtadi"));
			masraf.setMasrafTipiId(rs.getInt("masraf_tipi_id"));
			masraf.setMasrafPersonel_adi_id(rs.getInt("personel_id"));
			masraf.setMasrafUygulamaAsamasiId(rs.getInt("uygulama_asamasi_id"));

			list.add(masraf);

		}
		disconnectDB();

		return list;

	}

	public boolean guncelle(MasrafBilgisi masraf) throws Exception {
		boolean duzenlendi = false;
		SQL = "UPDATE tbl_masraf_bilgisi "
				+ "SET id=?, miktar=?, aciklama=?, tarih=?, personel_id=?, masraf_tipi_id=?, "
				+ " uygulama_asamasi_id=? " + "WHERE id =" + masraf.getId() + ";";

		newConnectDB();

		pstmt = conn.prepareStatement(SQL);

		pstmt.setInt(1, masraf.getId());
		pstmt.setDouble(2, masraf.getMasrafMiktari());
		pstmt.setString(3, masraf.getMasrafAciklama());
		java.sql.Date date = convertFromJAVADateToSQLDate(masraf.getMasrafTarihi());
		pstmt.setDate(4, date);
		pstmt.setInt(5, masraf.getMasrafPersonel_adi_id());
		pstmt.setInt(6, masraf.getMasrafTipiId());
		pstmt.setInt(7, masraf.getMasrafUygulamaAsamasiId());

		int sonuc = pstmt.executeUpdate();
		disconnectDB();
		if (sonuc == 1) {
			duzenlendi = true;
		}

		return duzenlendi;
	}

	public int Sil(int id, MasrafBilgisi masraf) throws Exception {
		
		int silindi=0;
		SQL = "DELETE FROM tbl_masraf_bilgisi where id=" + id;
		newConnectDB();

		stmt = conn.createStatement();
		
		 silindi = stmt.executeUpdate(SQL);

	
		disconnectDB();

		return silindi;
	}

}
