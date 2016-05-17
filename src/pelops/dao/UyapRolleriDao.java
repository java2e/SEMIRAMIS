package pelops.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.db.DBConnection;
import pelops.model.UyapRolleri;

public class UyapRolleriDao extends DBConnection {

	public UyapRolleriDao() {

	}

	public ArrayList<UyapRolleri> getUyapRolleri() {

		ArrayList<UyapRolleri> list = new ArrayList<UyapRolleri>();

		String SQL = "SELECT id, rol_id, rol FROM public.tbl_uyap_rolleri";

		UyapRolleri uyapRolleri;

		try {

			newConnectDB();
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);

			while (rs.next()) {

				uyapRolleri = new UyapRolleri();

				uyapRolleri.setId(rs.getInt("id"));
				uyapRolleri.setRol(rs.getString("rol"));
				uyapRolleri.setRol_id(rs.getInt("rol_id"));

				list.add(uyapRolleri);

			}

			disconnectDB();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;

	}

}
