package pelops.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import pelops.controller.AktifBean;
import pelops.db.DBConnection;
import pelops.model.IcraBorcluSesKayit;

public class SesKayitDAO extends DBConnection {

	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	String SQL;

	public boolean kaydet(IcraBorcluSesKayit seskayit) {

		java.sql.Date dateSon = convertFromJAVADateToSQLDate(seskayit
				.getTarihsaat());

		boolean kaydedildi = false;

		SQL = "INSERT INTO tbl_icra_borc_ses_kayit( "
				+ "icra_dosyasi_no, tarihsaat, konu, aciklama, seskayit, borclu_id) "
				+ "VALUES ( ?, ?, ?, ?, ?, ?);";

		try {

			newConnectDB();
			pstmt = conn.prepareStatement(SQL.toString());

			pstmt.setInt(1, AktifBean.getIcraDosyaID());
			pstmt.setDate(2, dateSon);
			pstmt.setString(3, seskayit.getKonu());
			pstmt.setString(4, seskayit.getAciklama());
			pstmt.setString(5, seskayit.getPath());
			pstmt.setInt(6, AktifBean.getBorcluId());

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

	public ArrayList<IcraBorcluSesKayit> getAllListFromIcraDosyaID(int id)
			throws Exception {
		ArrayList<IcraBorcluSesKayit> list = new ArrayList<IcraBorcluSesKayit>();
		SQL = "SELECT id, icra_dosyasi_no, tarihsaat, konu, aciklama,"
				+ " seskayit, borclu_id"
				+ " FROM tbl_icra_borc_ses_kayit where icra_dosyasi_no=" + id
				+ ";";

		newConnectDB();

		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			IcraBorcluSesKayit kayit = new IcraBorcluSesKayit();

			kayit.setId(rs.getInt("id"));
			kayit.setIcraDosyaID(rs.getInt("icra_dosyasi_no"));
			kayit.setTarihsaat(rs.getDate("tarihsaat"));
			kayit.setKonu(rs.getString("konu"));
			kayit.setAciklama(rs.getString("aciklama"));
			kayit.setPath(rs.getString("seskayit"));
			kayit.setBorcluId(rs.getInt("borclu_id"));

			list.add(kayit);

		}

		disconnectDB();
		return list;
	}

	public boolean guncelle(IcraBorcluSesKayit kayit) throws Exception {
		boolean duzenlendi = false;
		SQL = "UPDATE tbl_icra_borc_ses_kayit SET  "
				+ "icra_dosyasi_no=?, tarihsaat=?, "
				+ "konu=?, aciklama=?, seskayit=?, " + "borclu_id=? WHERE id="
				+ kayit.getId() + ";";

		newConnectDB();

		pstmt = conn.prepareStatement(SQL);

		pstmt.setInt(1, AktifBean.getIcraDosyaID());

		java.sql.Date date = convertFromJAVADateToSQLDate(kayit.getTarihsaat());
		pstmt.setDate(2, date);

		pstmt.setString(3, kayit.getKonu());
		pstmt.setString(4, kayit.getAciklama());
		pstmt.setString(5, kayit.getPath());
		pstmt.setInt(6, AktifBean.getBorcluId());

		int sonuc = pstmt.executeUpdate();
		disconnectDB();
		if (sonuc == 1) {
			duzenlendi = true;
		}

		return duzenlendi;
	}

	public boolean Sil(int id) {

		String SQL = "DELETE FROM tbl_icra_borc_ses_kayit WHERE id = ?;";

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

	public java.sql.Date convertFromJAVADateToSQLDate(java.util.Date javaDate) {

		java.sql.Date sqlDate = null;

		if (javaDate != null) {

			sqlDate = new Date(javaDate.getTime());
		}
		return sqlDate;
	}

}
