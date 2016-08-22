package pelops.kasa.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.a.a.a.a.a.c;

import pelops.db.DBConnection;
import pelops.kasa.model.HitamView;
import pelops.kasa.model.KasaSearchParams;
import pelops.kasa.model.ReddiyatView;
import pelops.kasa.model.TahsilatView;
import pelops.kasa.model.TahsilatViewModel;
import pelops.kasa.model.ViewTahsilatListesi;

public class ViewDAO extends DBConnection {

	/*
	 * Tahsilat Reddiyat hitam viewlerinin veritabanından cekilme islemi
	 */

	public ArrayList<TahsilatViewModel> getKasaIzlemeView(Date tarih1, Date tarih2) throws Exception {

		ArrayList<TahsilatViewModel> list = new ArrayList<TahsilatViewModel>();

		String fullSQL = "";

		String SQL = "SELECT * FROM vwkasa_izleme where 1=1";
		java.sql.Date date1 = convertFromJAVADateToSQLDate(tarih1);
		java.sql.Date date2 = convertFromJAVADateToSQLDate(tarih2);
		if (tarih1 != null && tarih2 != null) {
			fullSQL += " and odeme_sozu_tarihi between '" + date1 + "' and '" + date2 + "'";
		}

		if (fullSQL == "") {
			fullSQL = SQL + ";";
		} else {
			fullSQL = SQL + fullSQL + ";";
		}

		newConnectDB();
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(fullSQL);
		while (rs.next()) {
			TahsilatViewModel model = new TahsilatViewModel();
			model.setId(rs.getInt("id"));
			model.setFromID("ki-" + rs.getInt("id"));
			model.setDurum(rs.getString("durum"));
			model.setBorcluAdi(rs.getString("ad_soyad"));
			model.setTarih(rs.getDate("odeme_sozu_tarihi"));
			model.setOdemeMiktari(rs.getDouble("odeme_sozu_miktari"));
			model.setOdemeMiktariTL(convertDoubleToTL(model.getOdemeMiktari()));
			model.setHangiView("Izleme Bilgisi");
			model.setIcraDosyaID(rs.getInt("icra_dosyasi_id"));
			model.setPersonelAdi(rs.getString("user"));
			model.setIcraDosyaNo(rs.getString("icra_dosya_no"));
			model.setIzleme_id(rs.getInt("id"));
			model.setSoz_alan_personel_id(rs.getInt("personel_id"));

			list.add(model);
		}
		disconnectDB();

		return list;
	}

	// :TODO Vizit bilgisiinin eklenmesi ile bu method güncellenmeli
	public TahsilatViewModel getSelectedValueFromID(String id) throws Exception {
		String[] values = id.split("-");
		String sql = "";
		TahsilatViewModel model = new TahsilatViewModel();
		if (values.length > 0) {
			String parameter = values[0];
			int ids = Integer.parseInt(values[1]);
			if (parameter.equals("ki")) {
				sql = "select * from vwkasa_izleme2 where id = " + ids + ";";

				newConnectDB();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql);
				while (rs.next()) {
					model.setId(rs.getInt("id"));
					model.setFromID("ki-" + rs.getInt("id"));
					model.setDurum(rs.getString("durum"));
					model.setBorcluAdi(rs.getString("ad_soyad"));
					model.setTarih(rs.getDate("odeme_sozu_tarihi"));
					model.setOdemeMiktari(rs.getDouble("odeme_sozu_miktari"));
					model.setOdemeMiktariTL(convertDoubleToTL(model.getOdemeMiktari()));
					model.setHangiView("Izleme Bilgisi");
					model.setIcraDosyaID(rs.getInt("icra_dosyasi_id"));
					model.setIzleme_id(rs.getInt("id"));
					model.setPersonelAdi(rs.getString("kullanici_ad_soyad"));
					model.setSoz_alan_personel_id(rs.getInt("personel_id"));
					model.setIcraMudurluk(rs.getString("mudurluk_adi"));
				}
				disconnectDB();

			} else if (parameter.equals("op")) {
				sql = "select * from vwkasa_odeme_plani where id = " + ids + ";";
				newConnectDB();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql);
				while (rs.next()) {
					model.setId(rs.getInt("id"));
					model.setFromID("op-" + rs.getInt("id"));
					model.setBorcluAdi(rs.getString("ad_soyad"));
					model.setTarih(rs.getDate("odeme_tarihleri"));
					model.setOdemeMiktari(rs.getDouble("odeme_aylik_miktar"));
					model.setOdemeMiktariTL(convertDoubleToTL(model.getOdemeMiktari()));
					model.setHangiView("Odeme Plani Bilgisi");
					model.setIcraDosyaID(rs.getInt("icra_dosyasi_id"));
					model.setOdemeplani_id(rs.getInt("id"));
				}
				disconnectDB();
			} else if (parameter.equals("vz")) {
				sql = "select * from vwkasa_vizit where id = " + ids + ";";

				newConnectDB();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql);
				while (rs.next()) {
					model.setId(rs.getInt("id"));
					model.setFromID("vz-" + rs.getInt("id"));
					model.setBorcluAdi(rs.getString("ad_soyad"));
					model.setTarih(rs.getDate("odeme_sozu_tarihi"));
					model.setOdemeMiktari(rs.getDouble("odeme_sozu_miktari"));
					model.setOdemeMiktariTL(convertDoubleToTL(model.getOdemeMiktari()));
					model.setHangiView("Izleme Bilgisi");
					model.setIcraDosyaID(rs.getInt("icra_dosyasi"));
					model.setVizit_id(rs.getInt("id"));
					model.setPersonelAdi(rs.getString("user"));
					model.setSoz_alan_personel_id(rs.getInt("personel_id"));
				}
				disconnectDB();
			}

		}
		return model;
	}

	private ArrayList<TahsilatViewModel> getKasaOdemePlaniView(Date tarih1, Date tarih2) throws Exception {

		ArrayList<TahsilatViewModel> list = new ArrayList<TahsilatViewModel>();

		String fullSQL = "";

		String SQL = "SELECT * FROM vwkasa_odeme_plani where 1=1";
		java.sql.Date date1 = convertFromJAVADateToSQLDate(tarih1);
		java.sql.Date date2 = convertFromJAVADateToSQLDate(tarih2);
		if (tarih1 != null && tarih2 != null) {
			fullSQL += " and odeme_tarihleri between '" + date1 + "' and '" + date2 + "'";
		}

		if (fullSQL == "") {
			fullSQL = SQL + ";";
		} else {
			fullSQL = SQL + fullSQL + ";";
		}

		newConnectDB();
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(fullSQL);
		while (rs.next()) {
			TahsilatViewModel model = new TahsilatViewModel();
			model.setId(rs.getInt("id"));
			model.setFromID("op-" + rs.getInt("id"));
			model.setBorcluAdi(rs.getString("ad_soyad"));
			model.setTarih(rs.getDate("odeme_tarihleri"));
			model.setOdemeMiktari(rs.getDouble("odeme_aylik_miktar"));
			model.setOdemeMiktariTL(convertDoubleToTL(model.getOdemeMiktari()));
			model.setHangiView("Odeme Plani Bilgisi");
			model.setIcraDosyaID(rs.getInt("icra_dosyasi_id"));
			model.setIcraDosyaNo(rs.getString("icra_dosya_no"));
			model.setOdemeplani_id(rs.getInt("id"));
			list.add(model);
		}
		disconnectDB();
		return list;
	}

	private ArrayList<TahsilatViewModel> getVizitView(Date tarih1, Date tarih2) throws Exception {
		ArrayList<TahsilatViewModel> list = new ArrayList<>();
		String fullSQL = "";

		String SQL = "SELECT * FROM vwkasa_vizit where 1=1";
		java.sql.Date date1 = convertFromJAVADateToSQLDate(tarih1);
		java.sql.Date date2 = convertFromJAVADateToSQLDate(tarih2);
		if (tarih1 != null && tarih2 != null) {
			fullSQL += " and odeme_sozu_tarihi between '" + date1 + "' and '" + date2 + "'";
		}

		if (fullSQL == "") {
			fullSQL = SQL + ";";
		} else {
			fullSQL = SQL + fullSQL + ";";
		}

		newConnectDB();
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(fullSQL);
		while (rs.next()) {
			TahsilatViewModel model = new TahsilatViewModel();
			model.setId(rs.getInt("id"));
			model.setFromID("vz-" + rs.getInt("id"));
			model.setBorcluAdi(rs.getString("ad_soyad"));
			model.setTarih(rs.getDate("odeme_sozu_tarihi"));
			model.setOdemeMiktari(rs.getDouble("odeme_sozu_miktari"));
			model.setOdemeMiktariTL(convertDoubleToTL(model.getOdemeMiktari()));
			model.setHangiView("Vizit Bilgisi");
			model.setIcraDosyaID(rs.getInt("icra_dosyasi"));
			model.setIcraDosyaNo(rs.getString("icra_dosya_no"));
			model.setVizit_id(rs.getInt("id"));
			model.setSoz_alan_personel_id(rs.getInt("personel_id"));
			if (model.getIcraDosyaID() != 0) {
				list.add(model);
			}
		}
		disconnectDB();

		return list;
	}

	public ArrayList<TahsilatViewModel> getAllViewList(Date tarih1, Date tarih2) throws Exception {
		ArrayList<TahsilatViewModel> returnList = getKasaIzlemeView(tarih1, tarih2);
		returnList.addAll(getKasaOdemePlaniView(tarih1, tarih2));
		returnList.addAll(getVizitView(tarih1, tarih2));
		return returnList;

	}

	public ArrayList<HitamView> getAllHitamFromView(int status) throws Exception {
		ArrayList<HitamView> hitams = new ArrayList<HitamView>();
		String sql = "SELECT id, tahsilat_id, reddiyat_id, tarih, icra_dosya_id, hitam_durum, "
				+ "kasa_personel_id, onaylayan_id, muvekkil_adi, borclu_adi, gelis_tarihi, "
				+ " borc_tipi, tahsilat_tarihi, tahsilat_tipi, tahsilat_miktari, "
				+ "tahsilat_statusu, gelis_yeri, dosya_tipi, icra_dosya_no, icra_mudurlugu, "
				+ " sasa_reddiyat_tutar, devlet_reddiyat_tutar, muvekkil_reddiyat_tutar, "
				+ "  sasa_durum, devlet_durum, muvekkil_durum, toplam_tutar, ad_soyad"
				+ " FROM vwhitam where hitam_durum =" + status + "; ";
		newConnectDB();
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		while (rs.next()) {
			HitamView hitamView = new HitamView();
			hitamView.setId(rs.getInt("id"));
			hitamView.setTahsilatId(rs.getInt("tahsilat_id"));
			hitamView.setReddiyatId(rs.getInt("reddiyat_id"));
			hitamView.setTarih(rs.getDate("tarih"));
			hitamView.setIcraDosyaId(rs.getInt("icra_dosya_id"));
			hitamView.setHitamDurum(rs.getInt("hitam_durum"));
			hitamView.setKasaPersonelId(rs.getInt("kasa_personel_id"));
			hitamView.setOnaylayanId(rs.getInt("onaylayan_id"));
			hitamView.setMuvekkilAdi(rs.getString("muvekkil_adi"));
			hitamView.setBorcluAdi(rs.getString("borclu_adi"));
			hitamView.setGelisTarihi(rs.getDate("gelis_tarihi"));
			hitamView.setBorcTipi(rs.getString("borc_tipi"));
			hitamView.setTahsilatTarihi(rs.getDate("tahsilat_tarihi"));
			hitamView.setTahsilatTipi(rs.getString("tahsilat_tipi"));
			hitamView.setTahsilatMiktari(rs.getDouble("tahsilat_miktari"));
			hitamView.setTahsilatStatusu(rs.getString("tahsilat_statusu"));
			hitamView.setGelisYeri(rs.getString("gelis_yeri"));
			hitamView.setDosyaTipi(rs.getString("dosya_tipi"));
			hitamView.setIcraDosyaNo(rs.getString("icra_dosya_no"));
			hitamView.setIcraMudurlugu(rs.getString("icra_mudurlugu"));
			hitamView.setSasaReddiyatTutar(rs.getDouble("sasa_reddiyat_tutar"));
			hitamView.setDevletReddiyatTutar(rs.getDouble("devlet_reddiyat_tutar"));
			hitamView.setMuvvekkilReddiyatTutar(rs.getDouble("muvekkil_reddiyat_tutar"));
			hitamView.setSasaDurum(rs.getInt("sasa_durum"));
			hitamView.setDevletDurum(rs.getInt("devlet_durum"));
			hitamView.setMuvvekkilDurum(rs.getInt("muvekkil_durum"));
			hitamView.setToplamTutar(rs.getDouble("toplam_tutar"));
			hitamView.setAdSoyad("ad_soyad");
			hitamView.setTahsilatMiktariTL(convertDoubleToTL(hitamView.getTahsilatMiktari()));
			hitamView.setDevletReddiyatTutarTL(convertDoubleToTL(hitamView.getDevletReddiyatTutar()));
			hitamView.setSasaReddiyatTutarTL(convertDoubleToTL(hitamView.getSasaReddiyatTutar()));
			hitamView.setMuvekkilReddiyatTutarTL(convertDoubleToTL(hitamView.getMuvvekkilReddiyatTutar()));
			hitamView.setToplamTutarTL(convertDoubleToTL(hitamView.getToplamTutar()));

			hitams.add(hitamView);
		}
		disconnectDB();

		return hitams;

	}

	public HitamView getHitamFromViewByID(int id) throws Exception {
		String sql = "SELECT id, tahsilat_id, reddiyat_id, tarih, icra_dosya_id, hitam_durum, "
				+ "kasa_personel_id, onaylayan_id, muvekkil_adi, borclu_adi, gelis_tarihi, "
				+ " borc_tipi, tahsilat_tarihi, tahsilat_tipi, tahsilat_miktari, "
				+ "tahsilat_statusu, gelis_yeri, dosya_tipi, icra_dosya_no, icra_mudurlugu, "
				+ " sasa_reddiyat_tutar, devlet_reddiyat_tutar, muvekkil_reddiyat_tutar, "
				+ "  sasa_durum, devlet_durum, muvekkil_durum, toplam_tutar, ad_soyad" + " FROM vwhitam   where id ="
				+ id + "; ";
		newConnectDB();
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		HitamView hitamView = new HitamView();
		while (rs.next()) {

			hitamView.setId(rs.getInt("id"));
			hitamView.setTahsilatId(rs.getInt("tahsilat_id"));
			hitamView.setReddiyatId(rs.getInt("reddiyat_id"));
			hitamView.setTarih(rs.getDate("tarih"));
			hitamView.setIcraDosyaId(rs.getInt("icra_dosya_id"));
			hitamView.setHitamDurum(rs.getInt("hitam_durum"));
			hitamView.setKasaPersonelId(rs.getInt("kasa_personel_id"));
			hitamView.setOnaylayanId(rs.getInt("onaylayan_id"));
			hitamView.setMuvekkilAdi(rs.getString("muvekkil_adi"));
			hitamView.setBorcluAdi(rs.getString("borclu_adi"));
			hitamView.setGelisTarihi(rs.getDate("gelis_tarihi"));
			hitamView.setBorcTipi(rs.getString("borc_tipi"));
			hitamView.setTahsilatTarihi(rs.getDate("tahsilat_tarihi"));
			hitamView.setTahsilatTipi(rs.getString("tahsilat_tipi"));
			hitamView.setTahsilatMiktari(rs.getDouble("tahsilat_miktari"));
			hitamView.setTahsilatStatusu(rs.getString("tahsilat_statusu"));
			hitamView.setGelisYeri(rs.getString("gelis_yeri"));
			hitamView.setDosyaTipi(rs.getString("dosya_tipi"));
			hitamView.setIcraDosyaNo(rs.getString("icra_dosya_no"));
			hitamView.setIcraMudurlugu(rs.getString("icra_mudurlugu"));
			hitamView.setSasaReddiyatTutar(rs.getDouble("sasa_reddiyat_tutar"));
			hitamView.setDevletReddiyatTutar(rs.getDouble("devlet_reddiyat_tutar"));
			hitamView.setMuvvekkilReddiyatTutar(rs.getDouble("muvekkil_reddiyat_tutar"));
			hitamView.setSasaDurum(rs.getInt("sasa_durum"));
			hitamView.setDevletDurum(rs.getInt("devlet_durum"));
			hitamView.setMuvvekkilDurum(rs.getInt("muvekkil_durum"));
			hitamView.setToplamTutar(rs.getDouble("toplam_tutar"));
			hitamView.setAdSoyad("ad_soyad");

			hitamView.setTahsilatMiktariTL(convertDoubleToTL(hitamView.getTahsilatMiktari()));
			hitamView.setDevletReddiyatTutarTL(convertDoubleToTL(hitamView.getDevletReddiyatTutar()));
			hitamView.setSasaReddiyatTutarTL(convertDoubleToTL(hitamView.getSasaReddiyatTutar()));
			hitamView.setMuvekkilReddiyatTutarTL(convertDoubleToTL(hitamView.getMuvvekkilReddiyatTutar()));
			hitamView.setToplamTutarTL(convertDoubleToTL(hitamView.getToplamTutar()));
		}
		disconnectDB();

		return hitamView;

	}

	public HitamView getHitamFromViewByTahsilatID(int idTahsilat) throws Exception {
		String sql = "SELECT id, tahsilat_id, reddiyat_id, tarih, icra_dosya_id, hitam_durum, "
				+ "kasa_personel_id, onaylayan_id, muvekkil_adi, borclu_adi, gelis_tarihi, "
				+ " borc_tipi, tahsilat_tarihi, tahsilat_tipi, tahsilat_miktari, "
				+ "tahsilat_statusu, gelis_yeri, dosya_tipi, icra_dosya_no, icra_mudurlugu, "
				+ " sasa_reddiyat_tutar, devlet_reddiyat_tutar, muvekkil_reddiyat_tutar, "
				+ "  sasa_durum, devlet_durum, muvekkil_durum, toplam_tutar, ad_soyad" + " FROM vwhitam   where id ="
				+ idTahsilat + "; ";
		newConnectDB();
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		HitamView hitamView = new HitamView();
		while (rs.next()) {

			hitamView.setId(rs.getInt("id"));
			hitamView.setTahsilatId(rs.getInt("tahsilat_id"));
			hitamView.setReddiyatId(rs.getInt("reddiyat_id"));
			hitamView.setTarih(rs.getDate("tarih"));
			hitamView.setIcraDosyaId(rs.getInt("icra_dosya_id"));
			hitamView.setHitamDurum(rs.getInt("hitam_durum"));
			hitamView.setKasaPersonelId(rs.getInt("kasa_personel_id"));
			hitamView.setOnaylayanId(rs.getInt("onaylayan_id"));
			hitamView.setMuvekkilAdi(rs.getString("muvekkil_adi"));
			hitamView.setBorcluAdi(rs.getString("borclu_adi"));
			hitamView.setGelisTarihi(rs.getDate("gelis_tarihi"));
			hitamView.setBorcTipi(rs.getString("borc_tipi"));
			hitamView.setTahsilatTarihi(rs.getDate("tahsilat_tarihi"));
			hitamView.setTahsilatTipi(rs.getString("tahsilat_tipi"));
			hitamView.setTahsilatMiktari(rs.getDouble("tahsilat_miktari"));
			hitamView.setTahsilatStatusu(rs.getString("tahsilat_statusu"));
			hitamView.setGelisYeri(rs.getString("gelis_yeri"));
			hitamView.setDosyaTipi(rs.getString("dosya_tipi"));
			hitamView.setIcraDosyaNo(rs.getString("icra_dosya_no"));
			hitamView.setIcraMudurlugu(rs.getString("icra_mudurlugu"));
			if (hitamView.getSasaReddiyatTutar() == 0) {
				hitamView.setSasaReddiyatTutar(rs.getDouble("sasa_reddiyat_tutar"));
			}
			if (hitamView.getDevletReddiyatTutar() == 0) {
				hitamView.setDevletReddiyatTutar(rs.getDouble("devlet_reddiyat_tutar"));
			}
			if (hitamView.getMuvvekkilReddiyatTutar() == 0) {
				hitamView.setMuvvekkilReddiyatTutar(rs.getDouble("muvekkil_reddiyat_tutar"));
			}
			hitamView.setSasaDurum(rs.getInt("sasa_durum"));
			hitamView.setDevletDurum(rs.getInt("devlet_durum"));
			hitamView.setMuvvekkilDurum(rs.getInt("muvekkil_durum"));
			hitamView.setToplamTutar(rs.getDouble("toplam_tutar"));
			hitamView.setAdSoyad("ad_soyad");

			hitamView.setTahsilatMiktariTL(convertDoubleToTL(hitamView.getTahsilatMiktari()));
			hitamView.setDevletReddiyatTutarTL(convertDoubleToTL(hitamView.getDevletReddiyatTutar()));
			hitamView.setSasaReddiyatTutarTL(convertDoubleToTL(hitamView.getSasaReddiyatTutar()));
			hitamView.setMuvekkilReddiyatTutarTL(convertDoubleToTL(hitamView.getMuvvekkilReddiyatTutar()));
			hitamView.setToplamTutarTL(convertDoubleToTL(hitamView.getToplamTutar()));
		}
		disconnectDB();

		return hitamView;

	}

	// kimeGore: 1:sasa, 2: muvekkil, 3: devlet
	public ArrayList<ReddiyatView> getAllReddiyatFromView(int status, Integer kimeGore, Date date1, Date date2)
			throws Exception {
		ArrayList<ReddiyatView> list = new ArrayList<ReddiyatView>();
		String sql = "SELECT id, tahsilat_id, kasa_personel_id, onaylayan_id, sasa_reddiyat_tutar, "
				+ "devlet_reddiyat_tutar, muvekkil_reddiyat_tutar, sasa_durum, devlet_durum, "
				+ " muvekkil_adi, borclu_adi, icra_dosyasi_id, icra_dosya_no, ad_soyad, "
				+ " muvekkil_durum, toplam_tutar, tarih " + " FROM vwreddiyat where sasa_durum=" + status;

		if (kimeGore != null) {
			switch (kimeGore) {
			case 1:
				// default sasa_durum
				break;
			case 2:
				sql = "SELECT id, tahsilat_id, kasa_personel_id, onaylayan_id, sasa_reddiyat_tutar, "
						+ "devlet_reddiyat_tutar, muvekkil_reddiyat_tutar, sasa_durum, devlet_durum, "
						+ " muvekkil_adi, borclu_adi, icra_dosyasi_id, icra_dosya_no, ad_soyad, "
						+ " muvekkil_durum, toplam_tutar, tarih " + " FROM vwreddiyat where muvekkil_durum=" + status;
				break;
			case 3:
				sql = "SELECT id, tahsilat_id, kasa_personel_id, onaylayan_id, sasa_reddiyat_tutar, "
						+ "devlet_reddiyat_tutar, muvekkil_reddiyat_tutar, sasa_durum, devlet_durum, "
						+ " muvekkil_adi, borclu_adi, icra_dosyasi_id, icra_dosya_no, ad_soyad, "
						+ " muvekkil_durum, toplam_tutar,tarih " + " FROM vwreddiyat where devlet_durum=" + status;
				break;

			default:
				break;
			}
		}
		String fullSQL = sql;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (date1 != null && date2 != null) {
			fullSQL = sql + " and tarih between '" + format.format(date1) + "' and '" + format.format(date2) + "'";
		}

		newConnectDB();
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(fullSQL);
		while (rs.next()) {
			ReddiyatView view = new ReddiyatView();
			view.setId(rs.getInt("id"));
			view.setTahsilatId(rs.getInt("tahsilat_id"));
			view.setKasaPersonelId(rs.getInt("kasa_personel_id"));
			view.setOnaylayanId(rs.getInt("onaylayan_id"));
			view.setSasaReddiyatTutar(rs.getDouble("sasa_reddiyat_tutar"));
			view.setDevletReddiyatTuttar(rs.getDouble("devlet_reddiyat_tutar"));
			view.setMuvekkilReddiyatTutar(rs.getDouble("muvekkil_reddiyat_tutar"));
			view.setSasaDurum(rs.getInt("sasa_durum"));
			view.setDevletDurum(rs.getInt("devlet_durum"));
			view.setMuvekkilDurum(rs.getInt("muvekkil_durum"));
			view.setMuvekkilAdi(rs.getString("muvekkil_adi"));
			view.setBorcluAdi(rs.getString("borclu_adi"));
			view.setIcraDosyaId(rs.getInt("icra_dosyasi_id"));
			view.setIcraDosyaNo(rs.getString("icra_dosya_no"));
			view.setAdSoyad(rs.getString("ad_soyad"));
			view.setToplamTutar(rs.getDouble("toplam_tutar"));
			view.setToplamTutarTL(convertDoubleToTL(view.getToplamTutar()));
			view.setSasaReddiyatTutarTL(convertDoubleToTL(view.getSasaReddiyatTutar()));
			view.setDevletReddiyatTuttarTL(convertDoubleToTL(view.getDevletReddiyatTuttar()));
			view.setMuvekkilReddiyatTutarTL(convertDoubleToTL(view.getMuvekkilReddiyatTutar()));
			view.setTarih(rs.getDate("tarih") != null ? rs.getDate("tarih") : null);
			list.add(view);
		}
		disconnectDB();
		return list;
	}

	public ReddiyatView getReddiyatFromViewByID(int id) throws Exception {
		String sql = "SELECT id, tahsilat_id, kasa_personel_id, onaylayan_id, sasa_reddiyat_tutar, "
				+ "devlet_reddiyat_tutar, muvekkil_reddiyat_tutar, sasa_durum, devlet_durum, "
				+ " muvekkil_adi, borclu_adi, icra_dosyasi_id, icra_dosya_no, ad_soyad, "
				+ " muvekkil_durum, toplam_tutar, tarih " + " FROM vwreddiyat where id = " + id + ";";
		newConnectDB();
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		ReddiyatView view = new ReddiyatView();

		while (rs.next()) {
			view.setId(rs.getInt("id"));
			view.setTahsilatId(rs.getInt("tahsilat_id"));
			view.setKasaPersonelId(rs.getInt("kasa_personel_id"));
			view.setOnaylayanId(rs.getInt("onaylayan_id"));
			view.setSasaReddiyatTutar(rs.getDouble("sasa_reddiyat_tutar"));
			view.setDevletReddiyatTuttar(rs.getDouble("devlet_reddiyat_tutar"));
			view.setMuvekkilReddiyatTutar(rs.getDouble("muvekkil_reddiyat_tutar"));
			view.setSasaDurum(rs.getInt("sasa_durum"));
			view.setDevletDurum(rs.getInt("devlet_durum"));
			view.setMuvekkilDurum(rs.getInt("muvekkil_durum"));
			view.setMuvekkilAdi(rs.getString("muvekkil_adi"));
			view.setBorcluAdi(rs.getString("borclu_adi"));
			view.setIcraDosyaId(rs.getInt("icra_dosya_id"));
			view.setIcraDosyaNo(rs.getString("icra_dosya_no"));
			view.setAdSoyad(rs.getString("ad_soyad"));
			view.setToplamTutar(rs.getDouble("toplam_tutar"));
			view.setToplamTutarTL(convertDoubleToTL(view.getToplamTutar()));
			view.setSasaReddiyatTutarTL(convertDoubleToTL(view.getSasaReddiyatTutar()));
			view.setDevletReddiyatTuttarTL(convertDoubleToTL(view.getDevletReddiyatTuttar()));
			view.setMuvekkilReddiyatTutarTL(convertDoubleToTL(view.getMuvekkilReddiyatTutar()));
			view.setTarih(rs.getDate("tarih"));
		}
		disconnectDB();
		return view;
	}

	public ArrayList<TahsilatView> getAllTahsilatFromView(int status, Date date1, Date date2) throws Exception {
		ArrayList<TahsilatView> list = new ArrayList<TahsilatView>();

		String sql = "SELECT id, icra_dosyasi_id, muvekkil_adi, borclu_adi, gelis_tarihi, "
				+ "borc_tipi, tahsilat_tarihi, tahsilat_tipi, tahsilat_miktari, "
				+ "tahsilat_statusu, durum, gelis_yeri, onaylayan_id, kasa_personel_id, "
				+ "ad_soyad, dosya_tipi, icra_dosya_no, icra_mudurlugu,izleme_id , vizit_id , odemeplani_id, hitam_durum "
				+ " FROM vwtahsilat where durum =" + status;

		String fullSQL = sql;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		if (date1 != null || date2 != null) {

			fullSQL = sql + " and tahsilat_tarihi BETWEEN '" + format.format(date1) + "' AND '" + format.format(date2)
					+ "' ";

		}

		newConnectDB();

		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(fullSQL);
		while (rs.next()) {
			TahsilatView view = new TahsilatView();
			view.setId(rs.getInt("id"));
			view.setIcraDosyaId(rs.getInt("icra_dosyasi_id"));
			view.setMuvekkilAdi(rs.getString("muvekkil_adi"));
			view.setBorcluAdi(rs.getString("borclu_adi"));
			view.setGelisTarihi(rs.getDate("gelis_tarihi"));
			view.setBorcTipi(rs.getString("borc_tipi"));
			view.setTahsilatTarihi(rs.getDate("tahsilat_tarihi"));
			view.setTahsilatTipi(rs.getString("tahsilat_tipi"));
			view.setTahsilatMiktari(rs.getDouble("tahsilat_miktari"));
			view.setTahsilatStatusu(rs.getString("tahsilat_statusu"));
			view.setDurum(rs.getInt("durum"));
			view.setGelisYeri(rs.getString("gelis_yeri"));
			view.setOnaylayanId(rs.getInt("onaylayan_id"));
			view.setKasaPersonelId(rs.getInt("kasa_personel_id"));
			view.setAdSoyad(rs.getString("ad_soyad"));
			view.setDosyaTipi(rs.getString("dosya_tipi"));
			view.setIcraDosyaNo(rs.getString("icra_dosya_no"));
			view.setIcraMudurlugu(rs.getString("icra_mudurlugu"));
			view.setIzleme_id(rs.getInt("izleme_id"));
			view.setOdemeplani_id(rs.getInt("odemeplani_id"));
			view.setVizit_id(rs.getInt("vizit_id"));
			view.setTahsilatMiktariTL(convertDoubleToTL(view.getTahsilatMiktari()));
			view.setHitam_durum(rs.getInt("hitam_durum"));
			list.add(view);
		}
		disconnectDB();
		return list;
	}

	public TahsilatView getTahsilatFromViewByID(int id) throws Exception {

		String sql = "SELECT id, icra_dosyasi_id, muvekkil_adi, borclu_adi, gelis_tarihi, "
				+ "borc_tipi, tahsilat_tarihi, tahsilat_tipi, tahsilat_miktari, "
				+ "tahsilat_statusu, durum, gelis_yeri, onaylayan_id, kasa_personel_id, "
				+ "ad_soyad, dosya_tipi, icra_dosya_no, icra_mudurlugu,izleme_id , vizit_id , odemeplani_id, hitam_durum  FROM vwtahsilat "
				+ " where id = " + id + ";";
		newConnectDB();
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		TahsilatView view = new TahsilatView();
		while (rs.next()) {
			view.setId(rs.getInt("id"));
			view.setIcraDosyaId(rs.getInt("icra_dosyasi_id"));
			view.setMuvekkilAdi(rs.getString("muvekkil_adi"));
			view.setBorcluAdi(rs.getString("borclu_adi"));
			view.setGelisTarihi(rs.getDate("gelis_tarihi"));
			view.setBorcTipi(rs.getString("borc_tipi"));
			view.setTahsilatTarihi(rs.getDate("tahsilat_tarihi"));
			view.setTahsilatTipi(rs.getString("tahsilat_tipi"));
			view.setTahsilatMiktari(rs.getDouble("tahsilat_miktari"));
			view.setTahsilatStatusu(rs.getString("tahsilat_statusu"));
			view.setDurum(rs.getInt("durum"));
			view.setGelisYeri(rs.getString("gelis_yeri"));
			view.setOnaylayanId(rs.getInt("onaylayan_id"));
			view.setKasaPersonelId(rs.getInt("kasa_personel_id"));
			view.setAdSoyad(rs.getString("ad_soyad"));
			view.setDosyaTipi(rs.getString("dosya_tipi"));
			view.setIcraDosyaNo(rs.getString("icra_dosya_no"));
			view.setIcraMudurlugu(rs.getString("icra_mudurlugu"));
			view.setIzleme_id(rs.getInt("izleme_id"));
			view.setOdemeplani_id(rs.getInt("odemeplani_id"));
			view.setVizit_id(rs.getInt("vizit_id"));
			view.setTahsilatMiktariTL(convertDoubleToTL(view.getTahsilatMiktari()));
			view.setHitam_durum(rs.getInt("hitam_durum"));
		}
		disconnectDB();
		return view;
	}

	public ArrayList<ViewTahsilatListesi> getTahsilatListesiView(Date tarih1, Date tarih2, String MuvekkilAdi)
			throws Exception {

		ArrayList<ViewTahsilatListesi> list = new ArrayList<ViewTahsilatListesi>();

		String fullSQL = "";

		String SQL = "SELECT * FROM vw_tahsilat_listesi where 1=1";
		java.sql.Date date1 = convertFromJAVADateToSQLDate(tarih1);
		java.sql.Date date2 = convertFromJAVADateToSQLDate(tarih2);
		if (tarih1 != null && tarih2 != null) {
			fullSQL += " and tahsilat_tarihi between '" + date1 + "' and '" + date2 + "'";
		}
		if (MuvekkilAdi != null) {
			fullSQL += " and muvekkil_adi=" + MuvekkilAdi;
		}

		if (fullSQL == "") {
			fullSQL = SQL + ";";
		} else {
			fullSQL = SQL + fullSQL + ";";
		}

		newConnectDB();
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(fullSQL);
		int sira_no = 1;
		while (rs.next()) {
			ViewTahsilatListesi model = new ViewTahsilatListesi();
			model.setSira_no(sira_no);
			model.setBanka_servis_no(rs.getString("banka_servis_no"));
			model.setBorclu_adi(rs.getString("borclu_adi"));
			model.setDosya_statusu(rs.getString("dosya_statusu"));
			model.setIcra_dosya_no(rs.getString("icra_dosya_no"));
			model.setIcra_mudurlugu(rs.getString("icra_mudurlugu"));
			model.setKullanici_adi(rs.getString("kullanici_adi"));
			model.setMusteri_no(rs.getString("musteri_no"));
			model.setMuvekkil_adi(rs.getString("muvekkil_adi"));
			model.setTahsilat_miktari(rs.getDouble("tahsilat_miktari"));
			model.setTahsilat_tarihi(rs.getDate("tahsilat_tarihi"));
			model.setTahsilat_tipi(rs.getString("tahsilat_tipi"));
			model.setUrun_adi(rs.getString("urun_adi"));
			model.setUrun_no(rs.getString("urun_no"));
			sira_no++;
			list.add(model);
		}
		disconnectDB();

		return list;
	}

	public String convertDoubleToTL(double tutar) {

		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		return defaultFormat.format(tutar);
	}

}
