package pelops.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import pelops.controller.AktifBean;
import pelops.db.DBConnection;
import pelops.model.OdemeEmri;

public class OdemeEmriDAO extends DBConnection {

	private String SQL;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	double oldTahsilatTutari;
	double newTahsilatTutari;

	public boolean odemeEmriKaydet(OdemeEmri odemeEmri) throws Exception {

		boolean kaydedildi = false;

		SQL = "INSERT INTO tbl_odeme_emri( borclu_id," + " odeme_tarihi, odeme_miktari,"
				+ " odeme_sonucu, aciklama, icra_dosyasi_id)VALUES ( ?, ?, ?, ?, ?, ?);";

		newConnectDB();

		pstmt = conn.prepareStatement(SQL.toString());

		pstmt.setInt(1, odemeEmri.getBorcluId());
		pstmt.setDate(2, (Date) odemeEmri.getOdemeTarihi());
		pstmt.setDouble(3, odemeEmri.getOdemeMiktari());
		pstmt.setString(4, odemeEmri.getOdemeSonucu());
		pstmt.setString(5, odemeEmri.getAciklama());
		pstmt.setInt(6, odemeEmri.getIcraDosyadiID());

		int sonuc = pstmt.executeUpdate();
		disconnectDB();
		if (sonuc == 1) {
			kaydedildi = true;

			// hesaplaraEkle(AktifBean.getIcraDosyaID(), odemeEmri);

		}

		return kaydedildi;
	}

	public void hesaplaraEkle(int icraDosyaID, OdemeEmri odeme) throws Exception {

		SQL = "SELECT tahsilat_tutari FROM tbl_hesap where id='" + AktifBean.getIcraDosyaID() + "';";

		newConnectDB();

		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);

		while (rs.next()) {

			oldTahsilatTutari = rs.getDouble("tahsilat_tutari");

		}

		newTahsilatTutari = oldTahsilatTutari + odeme.getOdemeMiktari();

		SQL = "UPDATE tbl_hesap SET tahsilat_tutari=? WHERE id=" + AktifBean.getIcraDosyaID() + ";";

		pstmt = conn.prepareStatement(SQL);

		pstmt.setDouble(1, newTahsilatTutari);

		pstmt.executeUpdate();

		disconnectDB();

	}

	public void hesaplaraEkleForDelete(int icraDosyaID, OdemeEmri odeme) throws Exception {

		SQL = "SELECT tahsilat_tutari FROM tbl_hesap where id='" + AktifBean.getIcraDosyaID() + "';";

		newConnectDB();

		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);

		while (rs.next()) {

			oldTahsilatTutari = rs.getDouble("tahsilat_tutari");

		}

		newTahsilatTutari = oldTahsilatTutari - odeme.getOdemeMiktari();

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

	public boolean guncelle(OdemeEmri odemeEmri) throws Exception {
		boolean duzenlendi = false;
		SQL = "UPDATE tbl_odeme_emri " + "SET odeme_tarihi=?, odeme_miktari=?, odeme_sonucu=?, "
				+ "aciklama=?, icra_dosyasi_id=?" + "WHERE id =" + odemeEmri.getId() + ";";

		newConnectDB();

		pstmt = conn.prepareStatement(SQL);

		java.sql.Date date = convertFromJAVADateToSQLDate(odemeEmri.getOdemeTarihi());
		pstmt.setDate(1, date);
		pstmt.setDouble(2, odemeEmri.getOdemeMiktari());
		pstmt.setString(3, odemeEmri.getOdemeSonucu());
		pstmt.setString(4, odemeEmri.getAciklama());

		pstmt.setInt(5, odemeEmri.getIcraDosyadiID());

		int sonuc = pstmt.executeUpdate();
		disconnectDB();
		if (sonuc == 1) {
			duzenlendi = true;
		}

		return duzenlendi;
	}

	public ArrayList<OdemeEmri> getAllListFromIcraDosyaID(int id) throws Exception {

		ArrayList<OdemeEmri> list = new ArrayList<OdemeEmri>();

		SQL = "SELECT  odeme_tarihi, odeme_miktari, odeme_sonucu, aciklama, " + "id FROM tbl_odeme_emri "
				+ "where icra_dosyasi_id=" + id + ";";

		newConnectDB();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);

		while (rs.next()) {

			OdemeEmri emri = new OdemeEmri();
			emri.setId(rs.getInt("id"));
			emri.setOdemeMiktari(rs.getInt("odeme_miktari"));
			emri.setAciklama(rs.getString("aciklama"));
			emri.setOdemeSonucu(rs.getString("odeme_sonucu"));
			emri.setOdemeTarihi(rs.getDate("odeme_tarihi"));

			list.add(emri);

		}
		disconnectDB();

		return list;

	}

	public int Sil(int id,OdemeEmri odemeEmri) throws Exception {
		
		int silindi=0;
		SQL = "DELETE FROM tbl_odeme_emri where id=" + id;
		newConnectDB();

		stmt = conn.createStatement();
		silindi = stmt.executeUpdate(SQL);
	
		disconnectDB();

		return silindi;
	}

}
