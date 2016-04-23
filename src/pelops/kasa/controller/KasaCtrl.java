package pelops.kasa.controller;

import java.text.NumberFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;

import pelops.dao.BaglantiDAO;
import pelops.dao.BasvuruHarciDAO;
import pelops.dao.HesapDAO;
import pelops.dao.VekaletHarciDAO;
import pelops.dao.VekaletSinirlariDAO;
import pelops.kasa.model.Reddiyat;
import pelops.kasa.model.Tahsilat;
import pelops.kasa.model.TahsilatView;
import pelops.kasa.model.TahsilatViewModel;
import pelops.model.Hesap;
import pelops.model.IcraDosyasi;
import pelops.model.User;
import pelops.util.Util;

public class KasaCtrl {
	ViewDAO viewDAO = new ViewDAO();
	HitamDAO hitamDAO = new HitamDAO();
	ReddiyatDAO reddiyatDAO = new ReddiyatDAO();
	TahsilatDAO tahsilatDAO = new TahsilatDAO();

	/*
	 * sonuc 0: hitama dusmemis borcun tamamı karsilanmamış sonuc 1: hitama
	 * dusecek uyarı ver...
	 */
	public int checkAndSave() {
		return 0;
	}

	// Tahsilat ve izleme bilgisinin tamamı gelir
	public ArrayList<TahsilatView> getListeFromViewsForTahsilatIslemi() throws Exception {

		return viewDAO.getAllTahsilatFromView();
	}

	public Tahsilat getSelectedModel(String id) throws Exception {

		TahsilatViewModel model = viewDAO.getSelectedValueFromID(id);
		Tahsilat tahsilat = new Tahsilat();
		tahsilat.setBorclu_adi(model.getBorcluAdi());
		// tahsilat.setDurum(0);
		tahsilat.setGelis_tarihi(model.getTarih());
		tahsilat.setIcra_dosyasi_id(model.getIcraDosyaID());
		tahsilat.setTahsilat_miktari(model.getOdemeMiktari());
		return tahsilat;
	}

	public void kaydetTahsilat(Tahsilat tahsilat, boolean hitam, Reddiyat reddiyat) throws Exception {
		int tahsilatID = tahsilatDAO.insertObjToDB(tahsilat);
		if (hitam) {
				reddiyat.setTahsilatID(tahsilatID);
				reddiyatDAO.insertObjToDB(reddiyat);
		} else {
			Reddiyat reddiyat2 = convertTahsilatToReddiyat(tahsilat);
			reddiyat2.setTahsilatID(tahsilatID);
			reddiyatDAO.insertObjToDB(reddiyat2);
		}


	}

	public Hesap hesapla(Hesap hesap) throws Exception {
		VekaletSinirlariDAO vekaletsinirlar = new VekaletSinirlariDAO();
		VekaletHarciDAO vekaletharcidao = new VekaletHarciDAO();
		BasvuruHarciDAO basvuruharcidao = new BasvuruHarciDAO();
		int yil = Year.now().getValue();
		BaglantiDAO baglantidao = new BaglantiDAO();
		Date bugun = new Date();
		// hesap.setId(
		// baglantidao.Listele(pelops.controller.AktifBean.getIcraDosyaID()).getHesaplamaID());

		double asilalacak = hesap.getAsil_alacak();
		double gecikme = hesap.getGecikme_faizi();
		double temerrut = hesap.getTemerrut_faizi();
		double faizgider = hesap.getFaiz_gider_vergisi();
		double noter = hesap.getMasraf_tutari();

		double takipalacagi = asilalacak + gecikme + temerrut + faizgider + noter;

		hesap.setTakip_alacagi(takipalacagi);

		if (hesap.getTakip_alacagi() <= vekaletsinirlar.Liste(yil).getSinir_1()) {
			hesap.setVekalet_ucreti(hesap.getTakip_alacagi() * vekaletsinirlar.Liste(yil).getYuzde_1() / 100);

		} else {
			if (hesap.getTakip_alacagi() <= 70000) {
				hesap.setVekalet_ucreti(3600 + (hesap.getTakip_alacagi() - 30000) * 11 / 100);
			} else {
				if (hesap.getTakip_alacagi() <= 150000) {
					hesap.setVekalet_ucreti(8000 + (hesap.getTakip_alacagi() - 70000) * 8 / 100);
				} else {
					if (hesap.getTakip_alacagi() <= 400000) {
						hesap.setVekalet_ucreti(14400 + (hesap.getTakip_alacagi() - 150000) * 6 / 100);

					} else {
						if (hesap.getTakip_alacagi() <= 1000000) {
							hesap.setVekalet_ucreti(29400 + (hesap.getTakip_alacagi() - 400000) * 4 / 100);
						} else {
							if (hesap.getTakip_alacagi() <= 1750000) {
								hesap.setVekalet_ucreti(53400 + (hesap.getTakip_alacagi() - 1000000) * 3 / 100);
							} else {

								if (hesap.getTakip_alacagi() <= 3000000) {
									hesap.setVekalet_ucreti(75900 + (hesap.getTakip_alacagi() - 1750000) * 1.5 / 100);
								} else {
									hesap.setVekalet_ucreti(94650 + (hesap.getTakip_alacagi() - 3000000) * 1 / 100);

								}

							}

						}

					}

				}

			}

		}

		if (takipalacagi < vekaletsinirlar.Liste(yil).getVekalet_ucret_sinir()) {
			if (asilalacak < vekaletsinirlar.Liste(yil).getVekalet_ucret()) {
				hesap.setVekalet_ucreti(asilalacak);
			} else {
				hesap.setVekalet_ucreti(vekaletsinirlar.Liste(yil).getVekalet_ucret());

			}

		}
		Date hesapTarihi = new Date();
		IcraDosyasi icradosyasilistesi = new IcraDosyasi();
		long Fark;
		Date ilkTarih = icradosyasilistesi.getTakipTarihi();
		Date sonTarih = new Date();

		if (hesapTarihi.getDate() != bugun.getDate()) {
			sonTarih = hesapTarihi;
		}

		if (sonTarih == null || ilkTarih == null)
			Fark = 0;
		else
			Fark = (sonTarih.getTime() - ilkTarih.getTime());

		int gun = (int) (Fark / (1000 * 60 * 60 * 24));

		if (gun <= 7) {

			hesap.setVekalet_ucreti(hesap.getVekalet_ucreti() * 3 / 4);
		}

		double faiz = hesap.getTakip_alacagi() * gun / 360 * hesap.getTemerrut_faiz_orani() / 100;

		hesap.setTakip_faizi(faiz);

		hesap.setFaiz_gider_vergisi2(faiz * 5 / 100);

		double basvuruHarci = basvuruharcidao.liste(yil).getTutar();

		double vekaletHarci = vekaletharcidao.liste(yil).getTutar();

		double pesinHarc = hesap.getTakip_alacagi() * 0.5 / 100;

		double digerHarc = basvuruHarci + vekaletHarci + pesinHarc;

		double harcoran = 2.27;

		hesap.setPesinHarcTL(pesinHarc);
		hesap.setBasvuruHarciTL(basvuruHarci);
		hesap.setVekaletHarciTL(vekaletHarci);

		double tahsil_harci = (hesap.getTakip_alacagi() * harcoran / 100) - pesinHarc;

		hesap.setTahsil_harci(tahsil_harci);

		hesap.setDiger_harclar(digerHarc);

		hesap.setToplam_alacak(hesap.getTakip_alacagi() + hesap.getVekalet_ucreti() + hesap.getTakip_faizi()
				+ hesap.getFaiz_gider_vergisi2() + hesap.getDiger_harclar() + hesap.getMasraf_tutari()
				+ hesap.getTahsil_harci());

		hesap.setKalan_alacak(hesap.getToplam_alacak() - hesap.getTahsilat_tutari() - hesap.getIndirim_tutari());

		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();

		hesap.setAsil_alacak_tl(defaultFormat.format(hesap.getAsil_alacak()));
		hesap.setDiger_harclar_tl(defaultFormat.format(hesap.getDiger_harclar()));
		hesap.setFaiz_gider_vergisi_tl(defaultFormat.format(hesap.getFaiz_gider_vergisi()));
		hesap.setFaiz_gider_vergisi2_tl(defaultFormat.format(hesap.getFaiz_gider_vergisi2()));
		hesap.setGecikme_faizi_tl(defaultFormat.format(hesap.getGecikme_faizi()));
		hesap.setIndirim_faiz_orani_tl(defaultFormat.format(hesap.getIndirim_faiz_orani()));
		hesap.setIndirim_tutari_tl(defaultFormat.format(hesap.getIndirim_tutari()));
		hesap.setKalan_alacak_tl(defaultFormat.format(hesap.getKalan_alacak()));
		hesap.setMasraf_tutari_tl(defaultFormat.format(hesap.getMasraf_tutari()));
		hesap.setNoter_masrafi_tl(defaultFormat.format(hesap.getNoter_masrafi()));
		hesap.setTahsil_harci_tl(defaultFormat.format(hesap.getTahsil_harci()));
		hesap.setTahsilat_tutari_tl(defaultFormat.format(hesap.getTahsilat_tutari()));
		hesap.setTakip_alacagi_tl(defaultFormat.format(hesap.getTakip_alacagi()));

		hesap.setTakip_faiz_gider_vergi_tl(defaultFormat.format(hesap.getTakip_faiz_gider_vergi()));
		hesap.setTakip_faizi_tl(defaultFormat.format(hesap.getTakip_faizi()));
		hesap.setTemerrut_faizi_tl(defaultFormat.format(hesap.getTemerrut_faizi()));
		hesap.setToplam_alacak_tl(defaultFormat.format(hesap.getToplam_alacak()));
		hesap.setVekalet_ucreti_tl(defaultFormat.format(hesap.getVekalet_ucreti()));

		return hesap;

	}

	public Reddiyat convertTahsilatToReddiyat(Tahsilat tahsilat){
		Reddiyat reddiyat = new Reddiyat();
		
		reddiyat.setMuvekkilDurum(0);
		reddiyat.setMuvekkilReddiyatTutari(tahsilat.getTahsilat_miktari());
		reddiyat.setKasaPersonelID(getPersonelID());
		
		return reddiyat;
	}

	public int getPersonelID() {

		User user = (User) Util.getSession().getAttribute("user");
		return user.getId();
	}

}
