package pelops.chronology.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pelops.chronology.model.Instance;
import pelops.db.DBConnection;
import pelops.util.Util;

public class InstanceUtil extends DBConnection {
	public static InstanceUtil util;
	private Statement stm;
	private PreparedStatement pstm;
	private ResultSet rs;
	private String SQL = "";

	private static InstanceUtil createInstance() {
		util = new InstanceUtil();
		return util;
	}

	public static InstanceUtil getInstance() {
		if (util == null) {
			util = createInstance();
		}
		return util;
	}

	/*
	 * states = 1: ilk kayıt 2: guncelle 3: sil
	 */
	public void insertInstance(String icradosyaNo, Integer icraDosyaID, String olayAdi, String aciklama, int state) {
		SQL = "INSERT INTO tbl_instance( icra_dosya_no, icra_dosya_id, tarih, olay_adi, user_id, aciklama,  durum) "
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ?);";
		System.out.println("ibstance");
		newConnectDB();

		try {
			switch (state) {
			case 1:
				pstm = conn.prepareStatement(SQL);
				if (!icradosyaNo.equals(null))
					pstm.setString(1, icradosyaNo);
				if (icraDosyaID != null)
					pstm.setInt(2, icraDosyaID);

				pstm.setDate(3, convertFromJAVADateToSQLDate(new Date()));
				if (!olayAdi.equals(null))
					pstm.setString(4, olayAdi);

				pstm.setInt(5, Integer.valueOf(Util.getSession().getAttribute("UserID").toString()));
				if (aciklama != null)
					pstm.setString(6, aciklama);
				else
					pstm.setString(6, "");

				pstm.setString(7, DURUM_KAYDET);
				break;
			case 2:
				pstm = conn.prepareStatement(SQL);
				if (!icradosyaNo.equals(null))
					pstm.setString(1, icradosyaNo);
				if (icraDosyaID != null)
					pstm.setInt(2, icraDosyaID);

				pstm.setDate(3, convertFromJAVADateToSQLDate(new Date()));
				if (!olayAdi.equals(null))
					pstm.setString(4, olayAdi);

				pstm.setInt(5, Integer.valueOf(Util.getSession().getAttribute("UserID").toString()));
				if (!aciklama.equals(null))
					pstm.setString(6, aciklama);

				pstm.setString(7, DURUM_GUNCELLE);

				break;
			case 3:
				pstm = conn.prepareStatement(SQL);
				if (!icradosyaNo.equals(null))
					pstm.setString(1, icradosyaNo);
				if (icraDosyaID != null)
					pstm.setInt(2, icraDosyaID);

				pstm.setDate(3, convertFromJAVADateToSQLDate(new Date()));
				if (!olayAdi.equals(null))
					pstm.setString(4, olayAdi);

				pstm.setInt(5, Integer.valueOf(Util.getSession().getAttribute("UserID").toString()));
				if (!aciklama.equals(null))
					pstm.setString(6, aciklama);

				pstm.setString(7, DURUM_SIL);

				break;

			default:
				break;
			}
			pstm.execute();
			disconnectDB();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * state = 1: ilk kayıt 2: guncelle 3: sil
	 */
	public void insertInstances(List instances, int state) {
		for (Object object : instances) {
			Instance instance = (Instance) object;
			if (object instanceof Instance) {
				insertInstance(instance.getIcraDosyaNo(), instance.getIcraDosyaID(), instance.getOlayAdi(),
						instance.getAciklama(), state);
			}
		}
	}

	public List getAllInstances(int id) throws Exception {
		List list = new ArrayList<>();
		SQL = "SELECT id,  icra_dosya_id, tarih, olay_adi, user_id, aciklama, durum FROM tbl_instance "
				+ "where icra_dosya_id =" + id + ";";
		newConnectDB();
		stm = conn.createStatement();
		rs = stm.executeQuery(SQL);
		while (rs.next()) {
			Instance instance = new Instance(rs.getInt("id"), null, rs.getInt("icra_dosya_id"), rs.getDate("tarih"),
					rs.getString("olay_adi"), rs.getInt("user_id"), rs.getString("aciklama"), rs.getString("durum"));
			list.add(instance);
		}
		return list;
	}

	/*
	 * Durum için ilgili static veriler
	 */
	public static final String DURUM_KAYDET = "İlk kayıt";

	public static final String DURUM_GUNCELLE = "Güncellendi";

	public static final String DURUM_SIL = "Silindi";

}
