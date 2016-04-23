package pelops.dao;

import java.io.File;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import pelops.controller.AktifBean;
import pelops.db.DBConnection;
import pelops.model.DosyaYukleme;

public class DosyaEklemeDAO extends DBConnection {

	String SQL;
	PreparedStatement pstm;
	Statement stm;
	ResultSet rs;

	public boolean kaydet(DosyaYukleme yukleme) throws Exception {

		boolean kaydedildi = false;

		SQL = "INSERT INTO tbl_dosya_belgeleri( adi, icra_dosya_id,"
				+ " dosya_yolu, belge_yonu, belge_tarihi)"
				+ " VALUES ( ?, ?, ?, ?, ?);";

		newConnectDB();
		pstm = conn.prepareStatement(SQL);
		pstm.setString(1, yukleme.getBelgeAdi());
		pstm.setInt(2, AktifBean.getIcraDosyaID());
		pstm.setString(3, yukleme.getDosyaYolu());
		pstm.setString(4, yukleme.getBelgeYonu());
		java.sql.Date date = convertFromJAVADateToSQLDate(yukleme
				.getBelgeTarihi());
		pstm.setDate(5, date);

		int result = pstm.executeUpdate();
		disconnectDB();
		if (result == 1) {
			kaydedildi = true;
		}
		System.out.println(kaydedildi);
		return kaydedildi;

	}

	public ArrayList<DosyaYukleme> getAllListFromIcraDosyaID(int id)
			throws Exception {
		ArrayList<DosyaYukleme> dList = new ArrayList<DosyaYukleme>();
		SQL = "select * from tbl_dosya_belgeleri where icra_dosya_id=" + id
				+ ";";
		newConnectDB();
		stm = conn.createStatement();
		rs = stm.executeQuery(SQL);

		while (rs.next()) {

			DosyaYukleme yukleme = new DosyaYukleme();
			yukleme.setId(rs.getInt("id"));
			yukleme.setBelgeAdi(rs.getString("adi"));
			yukleme.setBelgeTarihi(rs.getDate("belge_tarihi"));
			yukleme.setBelgeYonu(rs.getString("belge_yonu"));

			dList.add(yukleme);
		}
		disconnectDB();

		return dList;
	}

	public java.sql.Date convertFromJAVADateToSQLDate(java.util.Date javaDate) {

		java.sql.Date sqlDate = null;

		if (javaDate != null) {

			sqlDate = new Date(javaDate.getTime());
		}
		return sqlDate;
	}

	String destination = "c:\\SASA\\";
	String destinationForServer = "c:\\SASA\\";

	@SuppressWarnings("unused")
	public boolean saveGeneratedFileToDB(String fileName) throws Exception {
		String path = destination + fileName;
		File file = new File(path);
		boolean a = false;
		if (file != null) {
			DosyaYukleme yukleme = new DosyaYukleme();
			yukleme.setBelgeAdi(fileName);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date date = new java.util.Date();
			yukleme.setBelgeTarihi(date);
			yukleme.setBelgeYonu("Giden");
			yukleme.setDosyaYolu(destinationForServer + "/" + fileName);
			yukleme.setIcraDosyaID(AktifBean.getIcraDosyaID());
			a = kaydet(yukleme);

//			InputStream is;
//
//			is = new FileInputStream(file);
//			File destFile = new File(destinationForServer, fileName);
//			FileUtils.copyInputStreamToFile(is, destFile);

		}
		return a;
	}

	public boolean Sil(int id) throws Exception {
		SQL = "DELETE FROM tbl_dosya_belgeleri where id=" + id;
		newConnectDB();

		stm = conn.createStatement();
		boolean silindi = stm.execute(SQL);
		disconnectDB();

		return silindi;
	}
}
