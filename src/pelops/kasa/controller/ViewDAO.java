package pelops.kasa.controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import pelops.db.DBConnection;
import pelops.kasa.model.HitamView;
import pelops.kasa.model.KasaSearchParams;
import pelops.kasa.model.ReddiyatView;
import pelops.kasa.model.TahsilatView;
import pelops.kasa.model.TahsilatViewModel;

public class ViewDAO extends DBConnection {

	/*
	 * Tahsilat Reddiyat hitam viewlerinin veritabanından cekilme islemi
	 */

	public ArrayList<TahsilatViewModel> getKasaIzlemeView(Date tarih1, Date tarih2) throws Exception {

		ArrayList<TahsilatViewModel> list = new ArrayList<TahsilatViewModel>();

		String fullSQL = "";

		String SQL = "SELECT id, odeme_sozu_tarihi, odeme_sozu_miktari, personel_id, \"user\", "
				+ "icra_dosyasi_id, ad_soyad, durum FROM vwkasa_izleme where 1=1";
		java.sql.Date date1 = convertFromJAVADateToSQLDate(tarih1);
		java.sql.Date date2 = convertFromJAVADateToSQLDate(tarih2);
		if (tarih1.equals(new Date())) {
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
			model.setHangiView("Izleme Bilgisi");
			model.setIcraDosyaID(rs.getInt("icra_dosyasi_id"));
			list.add(model);
		}
		disconnectDB();

		return list;
	}

	public TahsilatViewModel getSelectedValueFromID(String id) throws Exception {
		String[] values = id.split("-");
		String sql = "";
		TahsilatViewModel model = new TahsilatViewModel();
		if (values.length > 0) {
			String parameter = values[0];
			int ids = Integer.parseInt(values[1]);
			if (parameter == "ki") {
				sql = "select * from vwkasa_izleme where id = " + ids + ";";

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
					model.setHangiView("Izleme Bilgisi");
					model.setIcraDosyaID(rs.getInt("icra_dosyasi_id"));
				}
				disconnectDB();

			} else if (parameter == "op") {
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
					model.setHangiView("Odeme Plani Bilgisi");
					model.setIcraDosyaID(rs.getInt("icra_dosyasi_id"));
				}
				disconnectDB();
			}

		}
		return model;
	}

	public ArrayList<TahsilatViewModel> getKasaOdemePlaniView(Date tarih1, Date tarih2) throws Exception {

		ArrayList<TahsilatViewModel> list = new ArrayList<TahsilatViewModel>();

		String fullSQL = "";

		String SQL = "SELECT id, ad_soyad, odeme_tarihleri, odeme_aylik_miktar, icra_dosyasi_id FROM vwkasa_odeme_plani where 1=1";
		java.sql.Date date1 = convertFromJAVADateToSQLDate(tarih1);
		java.sql.Date date2 = convertFromJAVADateToSQLDate(tarih2);
		if (tarih1.equals(new Date())) {
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
			model.setHangiView("Odeme Plani Bilgisi");
			model.setIcraDosyaID(rs.getInt("icra_dosyasi_id"));
			list.add(model);
		}
		disconnectDB();
		return list;
	}

	public ArrayList<TahsilatViewModel> getAllViewList(Date tarih1, Date tarih2) throws Exception {
		ArrayList<TahsilatViewModel> returnList = getKasaIzlemeView(tarih1, tarih2);
		returnList.addAll(getKasaOdemePlaniView(tarih1, tarih2));
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
		}
		disconnectDB();

		return hitamView;

	}

	// kimeGore: 1:sasa, 2: muvekkil, 3: devlet
	public ArrayList<ReddiyatView> getAllReddiyatFromView(int status, Integer kimeGore) throws Exception {
		ArrayList<ReddiyatView> list = new ArrayList<ReddiyatView>();
		String sql = "SELECT id, tahsilat_id, kasa_personel_id, onaylayan_id, sasa_reddiyat_tutar, "
				+ "devlet_reddiyat_tutar, muvekkil_reddiyat_tutar, sasa_durum, devlet_durum, "
				+ " muvekkil_adi, borclu_adi, icra_dosyasi_id, icra_dosya_no, ad_soyad, "
				+ " muvekkil_durum, toplam_tutar " + " FROM vwreddiyat where sasa_durum=" + status + ";";

		if (kimeGore != null) {
			switch (kimeGore) {
			case 1:
				// default sasa_durum
				break;
			case 2:
				sql = "SELECT id, tahsilat_id, kasa_personel_id, onaylayan_id, sasa_reddiyat_tutar, "
						+ "devlet_reddiyat_tutar, muvekkil_reddiyat_tutar, sasa_durum, devlet_durum, "
						+ " muvekkil_adi, borclu_adi, icra_dosyasi_id, icra_dosya_no, ad_soyad, "
						+ " muvekkil_durum, toplam_tutar " + " FROM vwreddiyat where muvekkil_durum=" + status + ";";
				break;
			case 3:
				sql = "SELECT id, tahsilat_id, kasa_personel_id, onaylayan_id, sasa_reddiyat_tutar, "
						+ "devlet_reddiyat_tutar, muvekkil_reddiyat_tutar, sasa_durum, devlet_durum, "
						+ " muvekkil_adi, borclu_adi, icra_dosyasi_id, icra_dosya_no, ad_soyad, "
						+ " muvekkil_durum, toplam_tutar " + " FROM vwreddiyat where devlet_durum=" + status + ";";
				break;

			default:
				break;
			}
		}

		newConnectDB();
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
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
			view.setIcraDosyaId(rs.getInt("icra_dosya_id"));
			view.setIcraDosyaNo(rs.getString("icra_dosya_no"));
			view.setAdSoyad(rs.getString("ad_soyad"));
			view.setToplamTutar(rs.getDouble("toplam_tutar"));
			list.add(view);
		}
		disconnectDB();
		return list;
	}

	public ReddiyatView getReddiyatFromViewByID(int id) throws Exception {
		String sql = "SELECT id, tahsilat_id, kasa_personel_id, onaylayan_id, sasa_reddiyat_tutar, "
				+ "devlet_reddiyat_tutar, muvekkil_reddiyat_tutar, sasa_durum, devlet_durum, "
				+ " muvekkil_adi, borclu_adi, icra_dosyasi_id, icra_dosya_no, ad_soyad, "
				+ " muvekkil_durum, toplam_tutar " + " FROM vwreddiyat where id = " + id + ";";
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
		}
		disconnectDB();
		return view;
	}

	public ArrayList<TahsilatView> getAllTahsilatFromView(int status) throws Exception {
		ArrayList<TahsilatView> list = new ArrayList<TahsilatView>();

		String sql = "SELECT id, icra_dosyasi_id, muvekkil_adi, borclu_adi, gelis_tarihi, "
				+ "borc_tipi, tahsilat_tarihi, tahsilat_tipi, tahsilat_miktari, "
				+ "tahsilat_statusu, durum, gelis_yeri, onaylayan_id, kasa_personel_id, "
				+ "ad_soyad, dosya_tipi, icra_dosya_no, icra_mudurlugu" + " FROM vwtahsilat where durum =" + status
				+ ";";
		newConnectDB();
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		while (rs.next()) {
			TahsilatView view = new TahsilatView();
			view.setId(rs.getInt("id"));
			view.setIcraDosyaId(rs.getInt("icra_dosya_id"));
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
			list.add(view);
		}
		disconnectDB();
		return list;
	}

	public TahsilatView getTahsilatFromViewByID(int id) throws Exception {

		String sql = "SELECT id, icra_dosyasi_id, muvekkil_adi, borclu_adi, gelis_tarihi, "
				+ "borc_tipi, tahsilat_tarihi, tahsilat_tipi, tahsilat_miktari, "
				+ "tahsilat_statusu, durum, gelis_yeri, onaylayan_id, kasa_personel_id, "
				+ "ad_soyad, dosya_tipi, icra_dosya_no, icra_mudurlugu FROM vwtahsilat " + " where id = " + id + ";";
		newConnectDB();
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		TahsilatView view = new TahsilatView();
		while (rs.next()) {
			view.setId(rs.getInt("id"));
			view.setIcraDosyaId(rs.getInt("icra_dosya_id"));
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
		}
		disconnectDB();
		return view;
	}

}
