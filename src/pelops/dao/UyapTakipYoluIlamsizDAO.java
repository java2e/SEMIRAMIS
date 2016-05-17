package pelops.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.db.DBConnection;
import pelops.model.UyapTakipYoluIlamsiz;

public class UyapTakipYoluIlamsizDAO extends DBConnection {

	public UyapTakipYoluIlamsizDAO() {

	}

	public ArrayList<UyapTakipYoluIlamsiz> getUyapTakipYoluIlamsiz() {

		ArrayList<UyapTakipYoluIlamsiz> list = new ArrayList<UyapTakipYoluIlamsiz>();

		String SQL = "SELECT id, kod, aicklama FROM public.tbl_uyap_takip_yolu_ilamsiz";

		UyapTakipYoluIlamsiz uyapTakipYoluIlamsiz;

		try {

			newConnectDB();
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);

			while (rs.next()) {

				uyapTakipYoluIlamsiz = new UyapTakipYoluIlamsiz();

				uyapTakipYoluIlamsiz.setId(rs.getInt("id"));
				uyapTakipYoluIlamsiz.setKod(rs.getString("kod"));
				uyapTakipYoluIlamsiz.setAciklama(rs.getString("aciklama"));

				list.add(uyapTakipYoluIlamsiz);

			}

			disconnectDB();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;

	}

}
