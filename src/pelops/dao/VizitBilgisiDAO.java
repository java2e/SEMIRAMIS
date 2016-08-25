package pelops.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.controller.AktifBean;
import pelops.db.DBConnection;
import pelops.model.User;
import pelops.model.VizitBilgisi;
import pelops.model.VizitStatusu;
import semiramis.operasyon.controller.Utils;
import semiramis.operasyon.model.ChronologyIdentifier;

public class VizitBilgisiDAO extends DBConnection {

	String SQL;
	PreparedStatement prpst;
	Statement stmt;
	ResultSet rs;

	public boolean vizitBilgisiKaydet(VizitBilgisi vizitBilgisi) throws Exception {
		boolean kaydedildi = false;

		SQL = "INSERT INTO tbl_vizit_bilgisi( borclu_id, vizit_tarihi, vizit_statusu, odeme_sozu_tarihi, "
				+ "odeme_sozu_miktari, vizit_notu, personel_id, icra_dosyasi)" + "VALUES ( ?, ?, ?, ?,  ?, ?, ?, ?);";

		newConnectDB();

		prpst = conn.prepareStatement(SQL);
		prpst.setInt(1, AktifBean.getBorcluId());
		java.sql.Date date = convertFromJAVADateToSQLDate(vizitBilgisi.getVizitTarihi());
		prpst.setDate(2, date);
		prpst.setString(3, vizitBilgisi.getVizitStatusu());
		date = convertFromJAVADateToSQLDate(vizitBilgisi.getOdemeSozuTarihi());
		prpst.setDate(4, date);
		prpst.setDouble(5, vizitBilgisi.getOdemeMiktari());
		prpst.setString(6, vizitBilgisi.getVizitNotu());
		prpst.setInt(7, vizitBilgisi.getVizitPersoneliId());
		prpst.setInt(8, vizitBilgisi.getIcraDosyaID());

		int sonuc = prpst.executeUpdate();
		disconnectDB();
		if (sonuc == 1) {
			kaydedildi = true;
			Utils utils = new Utils();
			utils.saveChronology(AktifBean.getIcraDosyaID(), ChronologyIdentifier.ISLEM_VIZIT, utils.getUserName()
					+ " Personel " + utils.getBocluAdi() + " adlı borclunun vizit bilgisini kaydetmiştir.");
		}

		return kaydedildi;
	}

	public ArrayList<VizitBilgisi> getAllListFromIcraDosyaID(int id) throws Exception {

		ArrayList<VizitBilgisi> list = new ArrayList<VizitBilgisi>();

		SQL = "SELECT w.id, w.vizit_tarihi, w.vizit_statusu, w.odeme_sozu_tarihi,"
				+ "w.odeme_sozu_miktari, w.vizit_notu, u.\"adSoyad\", w.icra_dosyasi, w.personel_id "
				+ "FROM tbl_vizit_bilgisi w  inner join tbl_user u on u.id = w.personel_id " + "where w.icra_dosyasi="
				+ id;

		newConnectDB();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);

		if (rs != null) {

			while (rs.next()) {

				VizitBilgisi vizitBilgisi = new VizitBilgisi();

				vizitBilgisi.setId(rs.getInt("id"));
				vizitBilgisi.setOdemeMiktari(rs.getDouble("odeme_sozu_miktari"));
				vizitBilgisi.setOdemeSozuTarihi(rs.getDate("odeme_sozu_tarihi"));
				vizitBilgisi.setVizitTarihi(rs.getDate("vizit_tarihi"));
				vizitBilgisi.setUserName(rs.getString("adSoyad"));
				vizitBilgisi.setVizitStatusu(rs.getString("vizit_statusu"));
				vizitBilgisi.setVizitNotu(rs.getString("vizit_notu"));
				vizitBilgisi.setVizitPersoneliId(rs.getInt("personel_id"));

				list.add(vizitBilgisi);

			}
		}
		disconnectDB();

		return list;
	}

	public ArrayList<VizitStatusu> getAllVizitStatusu() throws Exception {

		ArrayList<VizitStatusu> vizitStatuleri = new ArrayList<VizitStatusu>();
		SQL = "SELECT id, adi FROM tbl_vizit_statusu;";

		newConnectDB();
		stmt = conn.createStatement();

		rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			VizitStatusu vizitStatusu = new VizitStatusu();

			vizitStatusu.setId(rs.getInt("id"));
			vizitStatusu.setAdi(rs.getString("adi"));

			vizitStatuleri.add(vizitStatusu);

		}
		disconnectDB();

		return vizitStatuleri;

	}

	public boolean guncelle(VizitBilgisi vizitBilgisi) throws Exception {
		boolean duzenlendi = false;
		SQL = "UPDATE tbl_vizit_bilgisi SET  " + "borclu_id=?, vizit_tarihi=?, vizit_statusu=?, odeme_sozu_tarihi=?,"
				+ " odeme_sozu_miktari=?, vizit_notu=?, personel_id=?, icra_dosyasi=?, guncelleme_tarihi=now() " + "WHERE id="
				+ vizitBilgisi.getId() + " ;";

		newConnectDB();

		prpst = conn.prepareStatement(SQL);

		prpst.setInt(1, AktifBean.getBorcluId());
		java.sql.Date date = convertFromJAVADateToSQLDate(vizitBilgisi.getVizitTarihi());
		prpst.setDate(2, date);
		prpst.setString(3, vizitBilgisi.getVizitStatusu());
		date = convertFromJAVADateToSQLDate(vizitBilgisi.getOdemeSozuTarihi());
		prpst.setDate(4, date);
		prpst.setDouble(5, vizitBilgisi.getOdemeMiktari());
		prpst.setString(6, vizitBilgisi.getVizitNotu());
		prpst.setInt(7, vizitBilgisi.getVizitPersoneliId());
		prpst.setInt(8, vizitBilgisi.getIcraDosyaID());

		int sonuc = prpst.executeUpdate();
		disconnectDB();
		if (sonuc == 1) {
			duzenlendi = true;
		}

		return duzenlendi;
	}

	public java.sql.Date convertFromJAVADateToSQLDate(java.util.Date javaDate) {
		java.sql.Date sqlDate = null;
		if (javaDate != null) {
			sqlDate = new Date(javaDate.getTime());
		}
		return sqlDate;
	}

	public ArrayList<User> getAllUsers() throws Exception {

		ArrayList<User> users = new ArrayList<User>();

		SQL = "SELECT id, ad_soyad FROM tbl_kullanici;";

		newConnectDB();
		stmt = conn.createStatement();

		rs = stmt.executeQuery(SQL);

		while (rs.next()) {

			User user = new User();

			user.setId(rs.getInt("id"));
			user.setAdSoyad(rs.getString("ad_soyad"));

			users.add(user);

		}
		disconnectDB();

		return users;
	}

	public boolean Sil(int id) throws Exception {
		SQL = "DELETE FROM tbl_vizit_bilgisi where id=" + id;
		newConnectDB();

		stmt = conn.createStatement();
		boolean silindi = stmt.execute(SQL);
		disconnectDB();

		return silindi;
	}

}
