package pelops.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.db.DBConnection;

import pelops.model.UyapTakipTuru;

public class UyapTakipTuruDAO extends DBConnection {

	public UyapTakipTuruDAO() {

	}

	public ArrayList<UyapTakipTuru> getUyapTakipTuru() {

		ArrayList<UyapTakipTuru> list = new ArrayList<UyapTakipTuru>();

		String SQL = "SELECT id, kod, aciklama FROM public.tbl_uyap_takip_turu";

		UyapTakipTuru uyapTakipTuru ;

		try {

			newConnectDB();
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);

			while (rs.next()) {

				uyapTakipTuru = new UyapTakipTuru();

				uyapTakipTuru.setId(rs.getInt("id"));
				uyapTakipTuru.setKod(rs.getString("kod"));
				uyapTakipTuru.setAciklama(rs.getString("aciklama"));

				list.add(uyapTakipTuru);

			}

			disconnectDB();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;

	}

}
