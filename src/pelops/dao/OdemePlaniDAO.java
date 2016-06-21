package pelops.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import pelops.controller.AktifBean;
import pelops.controller.IcraDosyaIslemleriBean;
import pelops.db.DBConnection;
import pelops.model.OdemePlani;

public class OdemePlaniDAO extends DBConnection {

	String SQL;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	OdemePlani kayit;
	ArrayList<OdemePlani> odemeSablon = new ArrayList<OdemePlani>();

	public java.sql.Date convertFromJAVADateToSQLDate(java.util.Date javaDate) {
		java.sql.Date sqlDate = null;
		if (javaDate != null) {
			sqlDate = new Date(javaDate.getTime());
		}
		return sqlDate;
	}

	@SuppressWarnings({ "unused", "deprecation" })
	public ArrayList<OdemePlani> odemeolustur(OdemePlani plan) throws Exception {

		double kalanAlacakMiktari = plan.getKalanAlacakMiktari();
		double pesinat = plan.getPesinatMiktari();
		int taksitSayisi = plan.getTaksitAdedi();
		java.sql.Date odemeTarihleri = convertFromJAVADateToSQLDate(plan.getIlkOdemeTarihi());
		plan.setDurum("�denecek");

		String SQL = "DELETE FROM tbl_odeme_plani_temp;";

		try {

			newConnectDB();
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// execute delete SQL stetement
			int result = pstmt.executeUpdate();
			disconnectDB();

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		newConnectDB();

		double aylikTaksitMiktari = (kalanAlacakMiktari - pesinat) / taksitSayisi;

		for (int i = 0; i < taksitSayisi; i++) {

			SQL = "INSERT INTO tbl_odeme_plani_temp (\"icraDosyID\", odeme_tarihleri, durum, taksit_aylik_miktar)  VALUES (?, ?, ?, ?);";

			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, plan.getIcraDosyID());
			pstmt.setDate(2, odemeTarihleri);
			pstmt.setString(3, plan.getDurum());
			pstmt.setDouble(4, aylikTaksitMiktari);

			odemeTarihleri.setMonth(odemeTarihleri.getMonth() + 1);

			int sonuc = pstmt.executeUpdate();

		}

		disconnectDB();



		// �deme Plan� G�sterme
		SQL = "SELECT id, \"icraDosyID\", odeme_tarihleri, durum, taksit_aylik_miktar FROM tbl_odeme_plani_temp;";

		newConnectDB();

		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		while (rs.next()) {

			kayit = new OdemePlani();
			kayit.setId(rs.getInt("id"));
			kayit.setTaksitTarihleri(rs.getDate("odeme_tarihleri"));
			kayit.setDurum(rs.getString("durum"));
			kayit.setTaksitAylikMiktar(rs.getDouble("taksit_aylik_miktar"));

			odemeSablon.add(kayit);
		}

		disconnectDB();

		return odemeSablon;

	}

		public int kaydet(ArrayList<OdemePlani> nihaiKayit, OdemePlani plan) throws Exception {

		int sonuc = 0;
		newConnectDB();

		for (OdemePlani odemePlani : nihaiKayit) {

			String SQL = "INSERT INTO tbl_odeme_plani(borclu_id, odeme_tarihleri, odeme_aylik_miktar, icra_dosyasi_id, pesinat_miktari, durum, taksit_adedi)"
					+ "VALUES (?, ?, ?, ?,?, ?, ?);";

			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, AktifBean.getBorcluId());
			pstmt.setDate(2, convertFromJAVADateToSQLDate(odemePlani.getTaksitTarihleri()));
			pstmt.setDouble(3, odemePlani.getTaksitAylikMiktar());
			pstmt.setInt(4, AktifBean.getIcraDosyaID());
			pstmt.setDouble(5, odemePlani.getPesinatMiktari());
			pstmt.setString(6, odemePlani.getDurum());
			pstmt.setInt(7, odemePlani.getTaksitAdedi());

			sonuc = pstmt.executeUpdate();

		}

	

		disconnectDB();

		return sonuc;

	}

	public ArrayList<OdemePlani> getAllListFromIcraDosyaID(int id) throws Exception {

		ArrayList<OdemePlani> planList = new ArrayList<OdemePlani>();

		SQL = "SELECT id, borclu_id, odeme_tarihleri, odeme_aylik_miktar, icra_dosyasi_id, pesinat_miktari, durum, taksit_adedi FROM tbl_odeme_plani where icra_dosyasi_id="
				+ id;

		newConnectDB();

		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);

		while (rs.next()) {

			OdemePlani plan = new OdemePlani();
			plan.setId(rs.getInt("id"));
			plan.setTaksitTarihleri(rs.getDate("odeme_tarihleri"));
			plan.setTaksitAylikMiktar(rs.getDouble("odeme_aylik_miktar"));
			plan.setDurum(rs.getString("durum"));
			planList.add(plan);

		}

		disconnectDB();

		return planList;

	}

		public int sil(int id) {

		int result = 0;

		String SQL = "DELETE FROM tbl_odeme_plani WHERE id = ?;";

		try {

			newConnectDB();
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);

			// execute delete SQL stetement
			result = pstmt.executeUpdate();

			disconnectDB();

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	

		return result;

	}

		public int guncelle(OdemePlani plan) throws Exception {

		// boolean duzenlendi = false;
		SQL = "UPDATE tbl_odeme_plani"
				+ " SET borclu_id=?, odeme_tarihleri=?, odeme_aylik_miktar=?, icra_dosyasi_id=?, pesinat_miktari=?, durum=?, taksit_adedi=? WHERE id="
				+ plan.getId() + ";";

		newConnectDB();

		pstmt = conn.prepareStatement(SQL);

		pstmt.setInt(1, AktifBean.getBorcluId());
		pstmt.setDate(2, convertFromJAVADateToSQLDate(plan.getTaksitTarihleri()));
		pstmt.setDouble(3, plan.getTaksitAylikMiktar());
		pstmt.setInt(4, plan.getIcraDosyID());
		pstmt.setDouble(5, plan.getPesinatMiktari());
		pstmt.setString(6, plan.getDurum());
		pstmt.setInt(7, plan.getTaksitAdedi());

		int sonuc = pstmt.executeUpdate();
		disconnectDB();

	

		return sonuc;

	}

	public double getKalanAlacak(int id) throws Exception {

//		double kalanAlacak = 0;
//
//		SQL = "SELECT kalan_alacak FROM tbl_hesap where id=" + id+ ";";
//
//		newConnectDB();
//
//		stmt = conn.createStatement();
//		rs = stmt.executeQuery(SQL);
//
//		while (rs.next()) {
//
//			kalanAlacak = rs.getDouble("kalan_alacak");
//
//		}
//
//		disconnectDB();

		IcraDosyaIslemleriBean icra = new IcraDosyaIslemleriBean();
		icra.GelismisListe(id);
			
		return icra.getHesaplistesi().getKalan_alacak();

	}

}
