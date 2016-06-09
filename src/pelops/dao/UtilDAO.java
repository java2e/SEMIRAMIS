package pelops.dao;

import java.sql.ResultSet;
import java.sql.Statement;

import pelops.db.DBConnection;

public class UtilDAO extends DBConnection {

	private Statement stm;
	private ResultSet rs;
	private String SQL;
	private static UtilDAO dao;

	private static void createInstance() {
		dao = new UtilDAO();
	}

	public static UtilDAO getInstance() {
		if (dao == null) {
			createInstance();
		}
		return dao;
	}

	public String getIcraMdwithID(int id) {
		String icraMd = null;

		SQL = "select * from tbl_icra_dosyasi where id =" + id + ";";
		newConnectDB();
		try {
			stm = conn.createStatement();
			rs = stm.executeQuery(SQL);
			int ids = 0;
			while (rs.next()) {
				ids = rs.getInt("icra_mudurlugu_id");
			}
			if (ids != 0) {
				SQL = "select adi from tbl_icra_mudurlugu where id = " + ids + ";";
				rs = stm.executeQuery(SQL);
				while (rs.next()) {
					icraMd = rs.getString("adi");
				}

			} else {
				icraMd = "İcra Md.lüğü girilmemiş.";
			}
			disconnectDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return icraMd;
	}

}
