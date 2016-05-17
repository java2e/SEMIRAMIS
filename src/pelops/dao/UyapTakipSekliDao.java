package pelops.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.db.DBConnection;

import pelops.model.UyapTakipSekli;

public class UyapTakipSekliDao extends DBConnection {

	public UyapTakipSekliDao() {

	}

	public ArrayList<UyapTakipSekli> getUyapTakipSekli() {

		ArrayList<UyapTakipSekli> list = new ArrayList<UyapTakipSekli>();

		String SQL = "SELECT id, kod, aciklama, takip_yolu FROM public.tbl_uyap_takip_sekli";

		UyapTakipSekli uyapTakipSekli;

		try {

			newConnectDB();
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);

			while (rs.next()) {

				uyapTakipSekli = new UyapTakipSekli();

				uyapTakipSekli.setId(rs.getInt("id"));
				uyapTakipSekli.setKod(rs.getInt("kod"));
				uyapTakipSekli.setAciklama(rs.getString("aciklama"));

				list.add(uyapTakipSekli);

			}

			disconnectDB();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;

	}

}
