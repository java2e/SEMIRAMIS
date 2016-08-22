package semiramis.kasa.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pelops.db.DBConnection;
import semiramis.kasa.model.KasaReddiyatView;

public class KasaReddiyatDAO extends DBConnection {

	public List<KasaReddiyatView> getListTahsilat(int icraDosyaId) {

		List<KasaReddiyatView> liste = null;

		try {

			String sql = "SELECT tah.id, tah.icra_dosyasi_id,  tah.tahsilat_miktari,kul.ad_soyad,tah.tahsilat_tarihi "
					+ " FROM tbl_tahsilat tah  " + " inner join tbl_kullanici kul on kul.id=tah.kasa_personel_id "
					+ " where icra_dosyasi_id= " + icraDosyaId + " order by tah.tahsilat_tarihi desc ";

			newConnectDB();

			Statement stmt = conn.createStatement();

			ResultSet set = stmt.executeQuery(sql);

			KasaReddiyatView tahsilat = null;

			liste = new ArrayList<KasaReddiyatView>();

			while (set.next()) {
				tahsilat = new KasaReddiyatView();

				tahsilat.setMiktar(set.getFloat("tahsilat_miktari"));
				tahsilat.setPersonelAdSoyad(set.getString("ad_soyad"));
				tahsilat.setTarih(set.getString("tahsilat_tarihi"));

				liste.add(tahsilat);
			}

			disconnectDB();

		} catch (Exception e) {

			System.out.println("Hata kasareddiyatDao getListeTahsilat :" + e.getMessage());
			// TODO: handle exception
		}

		return liste;

	}

	public List<KasaReddiyatView> getListReddiyat(int icraDosyaId) {

		List<KasaReddiyatView> liste = null;

		try {

			String sql = "  SELECT red.id, red.sasa_reddiyat_tutar,red.icra_dosya_id, red.tarih,kul.ad_soyad "
					+ " FROM tbl_reddiyat red  " + " inner join tbl_kullanici kul on red.kasa_personel_id=kul.id "
					+ " where icra_dosya_id=" + icraDosyaId + " and sasa_reddiyat_tutar > 0  "
					+ " order by red.tarih desc ";

			newConnectDB();

			Statement stmt = conn.createStatement();

			ResultSet set = stmt.executeQuery(sql);

			KasaReddiyatView reddiyat = null;

			liste = new ArrayList<KasaReddiyatView>();

			while (set.next()) {
				reddiyat = new KasaReddiyatView();

				reddiyat.setMiktar(set.getFloat("sasa_reddiyat_tutar"));
				reddiyat.setPersonelAdSoyad(set.getString("ad_soyad"));
				reddiyat.setTarih(set.getString("tarih"));

				liste.add(reddiyat);
			}

			disconnectDB();

		} catch (Exception e) {

			System.out.println("Hata kasareddiyatDao getListeReddiyat :" + e.getMessage());
			// TODO: handle exception
		}

		return liste;

	}

}
