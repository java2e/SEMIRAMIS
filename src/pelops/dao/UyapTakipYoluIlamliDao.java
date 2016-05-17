package pelops.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.db.DBConnection;
import pelops.model.UyapTakipYoluIlamli;

public class UyapTakipYoluIlamliDao extends DBConnection {

	public UyapTakipYoluIlamliDao() {

	}

	public ArrayList<UyapTakipYoluIlamli> getUyapTakipYoluIlamli() {

		ArrayList<UyapTakipYoluIlamli> list = new ArrayList<UyapTakipYoluIlamli>();

		String SQL = "SELECT id, kod, aciklama FROM public.tbl_uyap_takip_yolu_ilamli;";

		UyapTakipYoluIlamli uyapTakipYoluIlamli;

		try {

			newConnectDB();
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);

			while (rs.next()) {

				uyapTakipYoluIlamli = new UyapTakipYoluIlamli();

				uyapTakipYoluIlamli.setId(rs.getInt("id"));
				uyapTakipYoluIlamli.setKod(rs.getString("kod"));
				uyapTakipYoluIlamli.setAciklama(rs.getString("aciklama"));

				list.add(uyapTakipYoluIlamli);

			}

			disconnectDB();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;

	}

}
