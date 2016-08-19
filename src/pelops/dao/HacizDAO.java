package pelops.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import pelops.controller.AktifBean;
import pelops.db.DBConnection;
import pelops.model.HacizBilgisi;
import pelops.model.Tipi;

public class HacizDAO extends DBConnection {

	String SQL;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;

	public boolean kaydet(HacizBilgisi haciz) {

		java.sql.Date dateTalimat = convertFromJAVADateToSQLDate(haciz
				.getTalimatTarihi());
		java.sql.Date dateHaciz = convertFromJAVADateToSQLDate(haciz
				.getHacizTarihi());

		boolean kaydedildi = false;

		String SQL = "INSERT INTO tbl_haciz_bilgisi(icra_dosyasi_id, haciz_tipi_id, talimat_icra_mudurlugu, talimat_dosya_no,"
				+ "talimat_tarihi, haciz_tarihi, haciz_bedeli, teslim_yeri_id, sehir,"
				+ "aciklama, haczedilen_tipi_id, personel_adi_id, borclu_id)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		try {

			newConnectDB();

			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, AktifBean.getIcraDosyaID());
			pstmt.setInt(2, haciz.getHacizTipiId());
			pstmt.setString(3, haciz.getTalimatIcraMd());
			pstmt.setString(4, haciz.getDosyaNo());
			pstmt.setDate(5, dateTalimat);
			pstmt.setDate(6, dateHaciz);
			pstmt.setDouble(7, haciz.getHacizBedeli());
			pstmt.setInt(8, haciz.getTeslimYeriId());
			pstmt.setString(9, haciz.getSehir());
			pstmt.setString(10, haciz.getAciklama());
			pstmt.setInt(11, haciz.getHacizTipiId());
			pstmt.setInt(12, haciz.getPersonelId());
			pstmt.setInt(13, AktifBean.getBorcluId());

			int result = pstmt.executeUpdate();
			if (result == 1) {

				return kaydedildi = true;
			}

			disconnectDB();

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		return kaydedildi;

	}

	public ArrayList<HacizBilgisi> getAllListFromIcraDosyaID(int id)
			throws Exception {

		ArrayList<HacizBilgisi> list = new ArrayList<HacizBilgisi>();

		SQL = "SELECT h.id, h.borclu_id, h.haciz_tipi_id, ht.adi as htadi,"
				+ " h.talimat_icra_mudurlugu, h.talimat_dosya_no, h.talimat_tarihi, "
				+ "h.haciz_tarihi, h.haciz_bedeli, h.sehir, h.aciklama, "
				+ " h.icra_dosyasi_id, h.haczedilen_tipi_id, h.personel_adi_id,"
				+ " u.\"adSoyad\" as uadi, h.teslim_yeri_id, ty.adi as tyadi "
				+ "FROM tbl_haciz_bilgisi h inner join tbl_haciz_tipi ht on h.haciz_tipi_id=ht.id "
				+ "inner join tbl_user u on h.personel_adi_id=u.id "
				+ " inner join tbl_teslim_yeri ty on h.teslim_yeri_id=ty.id where h.icra_dosyasi_id="
				+ id + ";";

		newConnectDB();

		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		while (rs.next()) {

			HacizBilgisi hacizBilgisi = new HacizBilgisi();

			hacizBilgisi.setId(rs.getInt("id"));
			hacizBilgisi.setBorcluId(rs.getInt("borclu_id"));
			hacizBilgisi.setHacizTipiId(rs.getInt("haciz_tipi_id"));
			hacizBilgisi.setHacizTipiAdi(rs.getString("htadi"));
			hacizBilgisi.setTalimatIcraMd(rs
					.getString("talimat_icra_mudurlugu"));
			hacizBilgisi.setDosyaNo(rs.getString("talimat_dosya_no"));
			hacizBilgisi.setTalimatTarihi(rs.getDate("talimat_tarihi"));
			hacizBilgisi.setHacizTarihi(rs.getDate("haciz_tarihi"));
			hacizBilgisi.setHacizBedeli(rs.getDouble("haciz_bedeli"));
			hacizBilgisi.setSehir(rs.getString("sehir"));
			hacizBilgisi.setAciklama(rs.getString("aciklama"));
			hacizBilgisi.setIcra_dosyasi_id(rs.getInt("icra_dosyasi_id"));
			hacizBilgisi.setHaczedilenTipiId(rs.getInt("haczedilen_tipi_id"));
			hacizBilgisi.setPersonelId(rs.getInt("personel_adi_id"));
			hacizBilgisi.setPersoneName(rs.getString("uadi"));
			hacizBilgisi.setTeslimYeriId(rs.getInt("teslim_yeri_id"));
			hacizBilgisi.setTeslimYeri(rs.getString("tyadi"));
			hacizBilgisi.setIcra_dosyasi_id(rs.getInt("icra_dosyasi_id"));

			list.add(hacizBilgisi);
		}

		disconnectDB();

		for (int i = 0; i < list.size(); i++) {
			String name = null;
			name = getHaczedilenTipi(list.get(i).getHaczedilenTipiId());
			list.get(i).setHaczedilenTipiAdi(name);
		}

		return list;

	}

	public boolean guncelle(HacizBilgisi haciz) throws Exception {
		boolean duzenlendi = false;
		SQL = "UPDATE tbl_haciz_bilgisi SET "
				+ " borclu_id=?, haciz_tipi_id=?, talimat_icra_mudurlugu=?, "
				+ "talimat_dosya_no=?, talimat_tarihi=?, haciz_tarihi=?, haciz_bedeli=?, "
				+ " sehir=?, aciklama=?, icra_dosyasi_id=?, haczedilen_tipi_id=?, "
				+ " personel_adi_id=?, teslim_yeri_id=?" + " WHERE id="
				+ haciz.getId() + ";";

		newConnectDB();

		pstmt = conn.prepareStatement(SQL);

		pstmt.setInt(1, AktifBean.getBorcluId());
		pstmt.setInt(2, haciz.getHacizTipiId());
		pstmt.setString(3, haciz.getTalimatIcraMd());
		pstmt.setString(4, haciz.getDosyaNo());
		java.sql.Date date = convertFromJAVADateToSQLDate(haciz
				.getTalimatTarihi());
		pstmt.setDate(5, date);
		date = convertFromJAVADateToSQLDate(haciz.getHacizTarihi());
		pstmt.setDate(6, date);
		pstmt.setDouble(7, haciz.getHacizBedeli());
		pstmt.setString(8, haciz.getSehir());
		pstmt.setString(9, haciz.getAciklama());
		pstmt.setInt(10, AktifBean.getIcraDosyaID());
		pstmt.setInt(11, haciz.getHaczedilenTipiId());
		pstmt.setInt(12, haciz.getPersonelId());
		pstmt.setInt(13, haciz.getTeslimYeriId());

		int sonuc = pstmt.executeUpdate();
		disconnectDB();
		if (sonuc == 1) {
			duzenlendi = true;
		}

		return duzenlendi;
	}

	public String getHaczedilenTipi(int id) throws Exception {
		String sql = "select * from tbl_haczedilen_tipi where id = " + id + ";";
		newConnectDB();
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		String hacz = null;
		while (rs.next()) {
			hacz = rs.getString("adi");
		}
		disconnectDB();
		return hacz;
	}

	public boolean Sil(int id) {

		String SQL = "DELETE FROM tbl_haciz_bilgisi WHERE id = ?;";

		boolean silindi = false;

		try {
			newConnectDB();
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);

			// execute delete SQL stetement
			int result = pstmt.executeUpdate();

			disconnectDB();

			if (result == 1) {

				silindi = true;
			}

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		return silindi;
	}

	public int getHesapID(int id) throws Exception {
		SQL = "SELECT id, \"icradosyasiID\", \"alacakliID\", \"borcluID\", "
				+ "\"hesaplamaID\" FROM tbl_baglanti where \"icradosyasiID\" = "
				+ id + ";";
		newConnectDB();
		int hesapid = 0;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			hesapid = rs.getInt("hesaplamaID");
		}
		disconnectDB();
		return hesapid;

	}

	public void hesapDuzenle(int hesapID, int ekleCikar, double tutar)
			throws Exception {
		SQL = "select * from tbl_hesap where id= " + hesapID + ";";
		newConnectDB();
		stmt = conn.createStatement();
		double tahsilatTutari = 0;
		rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			tahsilatTutari = rs.getDouble("tahsilat_tutari");
		}
		disconnectDB();
		switch (ekleCikar) {
		case 1:
			tahsilatTutari = tahsilatTutari + tutar;
			break;
		case 2:
			tahsilatTutari = tutar;
			break;
		case 3:
			tahsilatTutari = tahsilatTutari - tutar;
			break;

		default:
			break;
		}

		String update = "UPDATE tbl_hesap SET  tahsilat_tutari=?  WHERE id = "
				+ hesapID + ";";
		newConnectDB();
		pstmt = conn.prepareStatement(update);
		pstmt.setDouble(1, tahsilatTutari);
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

	public ArrayList<String> getHaczeEsasMalBilgisiFromBorcluID(int id)
			throws Exception {
		ArrayList<String> malList = new ArrayList<String>();
		SQL = "SELECT mal.adi as mal_tipi FROM tbl_hacze_esas_mal_bilgisi hcz "
				+ " INNER JOIN tbl_mal_tipi mal on hcz.mal_tipi_id=mal.id"
				+ " where hcz.borclu_id ="
				+ id + ";";
		
		newConnectDB();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			String mal = null;
			mal = rs.getString("mal_tipi");
			malList.add(mal);
		}
		disconnectDB();
		return malList;
	}

	public ArrayList<Tipi> getHacizTipiList(ArrayList<String> malTipiList)
			throws Exception {

		ArrayList<Tipi> hacizTipiList = new ArrayList<Tipi>();
		SQL = "SELECT id, adi FROM tbl_haciz_tipi;";
		newConnectDB();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			Tipi tipi = new Tipi();
			tipi.setId(rs.getInt("id"));
			tipi.setAdi(rs.getString("adi"));
			hacizTipiList.add(tipi);
		}
		disconnectDB();
		ArrayList<Tipi> borcluMalTipiList = new ArrayList<Tipi>();
		
		if (malTipiList.size() > 0) {
			for (int j = 0; j < malTipiList.size(); j++) {
				for (int i = 0; i < hacizTipiList.size(); i++) {

					if (malTipiList.get(j)
							.equals(hacizTipiList.get(i).getAdi())) {

						borcluMalTipiList.add(hacizTipiList.get(i));
						
					}
				}
			}

		}
		return borcluMalTipiList;
	}

}
