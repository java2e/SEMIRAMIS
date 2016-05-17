package pelops.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.db.DBConnection;
import pelops.model.UyapSureBirimleri;

public class UyapSureBirimleriDao extends DBConnection {

	public UyapSureBirimleriDao() {

	}

	public ArrayList<UyapSureBirimleri> getUyapSureBirimleri() {

		ArrayList<UyapSureBirimleri> list = new ArrayList<UyapSureBirimleri>();

		String SQL = "SELECT id, kod, aciklama FROM public.tbl_uyap_sure_birimleri;";

		UyapSureBirimleri uyapSureBirimleri;

		try {

			newConnectDB();
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);

			while (rs.next()) {

				uyapSureBirimleri = new UyapSureBirimleri();

				uyapSureBirimleri.setId(rs.getInt("id"));
				uyapSureBirimleri.setKod(rs.getString("kod"));
				uyapSureBirimleri.setAciklama(rs.getString("aciklama"));

				list.add(uyapSureBirimleri);

			}

			disconnectDB();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;

	}
}
