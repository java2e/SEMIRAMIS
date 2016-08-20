package pelops.dao;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import pelops.model.AlacakBilgisi;
import pelops.model.Baglanti;
import pelops.model.BorcluBilgisi;
import pelops.model.ExcellModel;
import pelops.model.FreeXML;
import pelops.model.Hesap;
import pelops.model.IcraDosyasi;
import pelops.model.XMLToUyap;
import semiramis.operasyon.controller.Utils;
import semiramis.operasyon.model.ChronologyIdentifier;

public class XMLToSistemAktarimDAO {

	int MuvekkilId = 0;
	int BorcluId = 0;
	int HesapId = 0;
	int IcraDosyaId = 0;
	private static DecimalFormat df = new DecimalFormat(".##");

	public Double doubleYap(String sttutar) {
		Double tutar = 0.0;
		if (sttutar == null || sttutar.equals("")) {
		} else {
			int nokta = sttutar.indexOf(",");
			if (nokta < 0)
				nokta = sttutar.indexOf(".");

			String tam = sttutar.substring(0, nokta);
			String kesir = sttutar.substring(nokta + 1, sttutar.length());
			String sayi = tam + "." + kesir;
			tutar = Double.parseDouble(sayi);
		}
		return tutar;
	}

	@SuppressWarnings("unused")
	public void Kaydet(XMLToUyap xmlVeri, ExcellModel xlsVeri) {

		// Gelen veride Müvekkil için sadece Müvekkilin adı alınarak ilgili
		// tablodan id si alınır. Diğer bilgier zaten sistemde mevcuttur.
		AlacakliDAO muvekkil = new AlacakliDAO();
		MuvekkilId = muvekkil.MuvekkilIdGetir(xmlVeri.get__kisiKurumBilgileri2_ad());

		// Borçlu bilgisinin tümü alınır. borçlu sistemde kayıtlı değil ise
		// bilgileri sisteme işlenir.
		BorcluBilgisiDAO Borclu = new BorcluBilgisiDAO(); // borçluyu kaydetmek
															// için gerekli DAO
															// ürettik

		BorcluBilgisi borcluBilgileri = new BorcluBilgisi(); // borçlu
																// bilgilerini
																// doldurmak
																// için yeni bir
																// Borçlu
																// Nesnesi
																// Ürettik
		borcluBilgileri.setAdSoyad(xmlVeri.get__kisiKurumBilgileri3_ad());

		AdresTuruDAO adresturu = new AdresTuruDAO(); // Adres türüne ait İd
														// bilgisini çekmek için
														// adresDAO ürettik
		borcluBilgileri.setAdresTuruId(adresturu.AdresIdGetir(xmlVeri.get___adres4_adresTuru()));

		borcluBilgileri.setAdres(xmlVeri.get___adres4_adres());
		borcluBilgileri.setMusteriNo(xlsVeri.getMusteriNo());

		//borcluBilgileri.setUrunNo(xlsVeri.getUrunAdi() + " - " + xlsVeri.getUrunNo());

		borcluBilgileri.setAd(xmlVeri.get___kisiTumbilgileri3_adi());
		borcluBilgileri.setSoyad(xmlVeri.get___kisiTumbilgileri3_soyadi());
		borcluBilgileri.setTcNo(xmlVeri.get___kisiTumbilgileri3_tcKimlikNo());
		borcluBilgileri.setIlAdi(xmlVeri.get___adres4_il());
		borcluBilgileri.setIlId(Integer.parseInt(xmlVeri.get___adres4_ilKodu()));
		borcluBilgileri.setUyap_rol(xmlVeri.get__rolTur3_Rol());
		borcluBilgileri.setUyap_rol_id(xmlVeri.get__rolTur3_rolID());

		try {
			BorcluId = Borclu.saveBorclu(borcluBilgileri); // burada borçlu
															// bilgilerini
															// sisteme kaydettik
															// ve id sini aldık.
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Banka Vekil Avukat Bilgileri zaten sistemde kayıtlı olduğu için işlen
		// yapılmıyor.
		// --------------------------------------------------------------------------------

		HesapDAO hesapListesi = new HesapDAO();

		Hesap hesapBilgi = new Hesap(); // Hesap Nesnesi oluşturularak gerekli
										// alanlar işleniyor.

		hesapBilgi.setTemerrut_faiz_orani(doubleYap(xmlVeri.get___alacakKalemi2_faiz2_faizOran()));

		hesapBilgi.setAlacak_kalemi2_alacak_kalem_adi(xmlVeri.get__alacakKalemi2_alacakKalemAdi());
		hesapBilgi.setAlacak_kalemi2_kalem_kod_aciklama(xmlVeri.get__alacakKalemi2_alacakKalemKodAciklama());
		hesapBilgi.setAlacak_kalemi2_kalem_kod_turu(xmlVeri.get__alacakKalemi2_alacakKalemKodTuru());

		hesapBilgi.setAlacak_kalemi3_alacak_kalem_adi(xmlVeri.get__alacakKalemi3_alacakKalemAdi());
		hesapBilgi.setAlacak_kalemi3_kalem_kod_aciklama(xmlVeri.get__alacakKalemi3_alacakKalemKodAciklama());
		hesapBilgi.setAlacak_kalemi3_kalem_kod_turu(xmlVeri.get__alacakKalemi3_alacakKalemKodTuru());
		hesapBilgi.setAlacak_kalemi3_kalem_kod(xmlVeri.get__alacakKalemi3_alacakKalemKod());

		hesapBilgi.setAlacak_kalemi4_alacak_kalem_adi(xmlVeri.get__alacakKalemi4_alacakKalemAdi());
		hesapBilgi.setAlacak_kalemi4_kalem_kod_aciklama(xmlVeri.get__alacakKalemi4_alacakKalemKodAciklama());
		hesapBilgi.setAlacak_kalemi4_kalem_kod_turu(xmlVeri.get__alacakKalemi4_alacakKalemKodTuru());
		hesapBilgi.setAlacak_kalemi4_kalem_kod(xmlVeri.get__alacakKalemi4_alacakKalemKod());

		hesapBilgi.setAlacak_kalemi5_alacak_kalem_adi(xmlVeri.get__alacakKalemi5_alacakKalemAdi());
		hesapBilgi.setAlacak_kalemi5_kalem_kod_aciklama(xmlVeri.get__alacakKalemi5_alacakKalemKodAciklama());
		hesapBilgi.setAlacak_kalemi5_kalem_kod_turu(xmlVeri.get__alacakKalemi5_alacakKalemKodTuru());
		hesapBilgi.setAlacak_kalemi5_kalem_kod(xmlVeri.get__alacakKalemi5_alacakKalemKod());
		hesapBilgi.setTakip_alacagi(doubleYap(xmlVeri.get_digerAlacak_tutar()));
		hesapBilgi.setAsil_alacak(doubleYap(xmlVeri.get__alacakKalemi2_alacakKalemTutar()));
		hesapBilgi.setFaiz_gider_vergisi(doubleYap(xmlVeri.get__alacakKalemi4_alacakKalemIlkTutar()));
		hesapBilgi.setMasraf_tutari(doubleYap(xmlVeri.get__alacakKalemi5_alacakKalemIlkTutar()));
		hesapBilgi.setUrunAdi(xlsVeri.getUrunAdi());
		hesapBilgi.setUrunNo(xlsVeri.getUrunNo());

		double toplamBorc = xlsVeri.getToplamBorc();
		double ihtarnameTutari = xlsVeri.getIhtarnameTutari();
		double BSMV = doubleYap(xmlVeri.get__alacakKalemi4_alacakKalemTutar());
		double temerrutFaizi = BSMV / 5 * 100;
		double toplamFaiz = doubleYap(xmlVeri.get__alacakKalemi3_alacakKalemTutar());
		double islenmisFaiz = toplamFaiz - temerrutFaizi;

		hesapBilgi.setAkdi_faiz_orani(xlsVeri.getAkdiFaizOrani());
		hesapBilgi.setAsil_alacak(doubleYap(xmlVeri.get__alacakKalemi2_alacakKalemTutar()));

		hesapBilgi.setGecikme_faizi(doubleYap(df.format(islenmisFaiz)));

		hesapBilgi.setTemerrut_faizi(doubleYap(df.format(temerrutFaizi)));

		try {
			HesapId = hesapListesi.Kaydet(hesapBilgi); // Hesap Bilgi
														// girişlerini hesap
														// tablosuna işledik.
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		
		
		
		
		
		
		IcraDosyasiDAO icradosya = new IcraDosyasiDAO();

		IcraDosyasi dosya = new IcraDosyasi();

		dosya.setUyap_dosya_tipi(xmlVeri.getDosyaTipi());
		dosya.setKKDF(xmlVeri.getKKDFUygulansin());
		dosya.setBSMV(xmlVeri.getBSMVUygulansin());
		dosya.setBK84(xmlVeri.getBK84MaddeUygulansin());
		dosya.setTalepEdilenHak(xmlVeri.getAlacaklininTalepEttigiHak());
		dosya.setTakipSekliId(Integer.parseInt(xmlVeri.getTakipSekli()));
		dosya.setTakipYoluId(Integer.parseInt(xmlVeri.getTakipYolu()));
		dosya.setTakipTipiId(Integer.parseInt(xmlVeri.getTakipTuru()));
		dosya.setDiger_alacak_aciklama(xmlVeri.get_digerAlacak_digerAlacakAciklama());
		dosya.setPara_birimi(xmlVeri.get_digerAlacak_tutarAdi());
		dosya.setDosyaStatusuId(6);
		dosya.setBorcTipiId(xlsVeri.getUrunKategorisi().equals("Kredi Kartı") ? 13 : null);

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = xmlVeri.get___alacakKalemi2_faiz2_baslangicTarihi();
		String gelisTarihiString = xlsVeri.getAvukataVerilisTarihi().toString();
		String ihtarnameTarihiString = xlsVeri.getIhtarnameTarihi().toString();
		java.util.Date date = null, gelisTarihi = null, ihtarnameTarihi = null, deneme = null;
		try {
			date = convertFromJAVADateToSQLDate(format.parse(dateString));
			gelisTarihi = convertFromJAVADateToSQLDate(format.parse(gelisTarihiString));
			ihtarnameTarihi = convertFromJAVADateToSQLDate(format.parse(ihtarnameTarihiString));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dosya.setTakipTarihi(date);
		dosya.setAvukat_sevis_no(xlsVeri.getAvukatServisNo());
		dosya.setBanka_servis_no(xlsVeri.getBankaServisNo());
		dosya.setGelisTarihi(gelisTarihi);
		dosya.setIhtarnameTarihi(ihtarnameTarihi);

		dosya.setUyap_para_turu(xmlVeri.get_digerAlacak_tutarTur());

		try {
			IcraDosyaId = icradosya.XMLKaydet(dosya);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		AlacakBilgisi alacak = new AlacakBilgisi();
		AlacakBilgisiDAO alacakdao = new AlacakBilgisiDAO();
		
		alacak.setIcra_dosyasi_id(IcraDosyaId);
		alacak.setBelge_miktari(hesapBilgi.getTakip_alacagi());
		alacak.setDoviz_tipi("TL");
		alacak.setBelge_tipi_id(7);
		alacak.setBelge_statusu("TAKİP ALACAĞI");
		alacak.setFaiz_tipi_id(14);
		alacak.setTanzim_tarihi(gelisTarihi);
		alacakdao.kaydet(alacak);
		
		
		
		Baglanti baglanti = new Baglanti();

		baglanti.setAlacakliID(MuvekkilId);
		baglanti.setBorcluID(BorcluId);
		baglanti.setHesaplamaID(HesapId);
		baglanti.setIcradosyasiID(IcraDosyaId);

		BaglantiDAO daobaglanti = new BaglantiDAO();

		try {
			daobaglanti.Kaydet(baglanti);
			//ChronologyUtil.getInstance().insertInstance(new Instance(IcraDosyaId, null, "Dosya Açıldı", null, 1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AlacakBilgisiDAO alacakBilgisiDAO = new AlacakBilgisiDAO();
		AlacakBilgisi alacakBilgisiBilgileri = new AlacakBilgisi();
		alacakBilgisiBilgileri.setIcra_dosyasi_id(IcraDosyaId);
		alacakBilgisiBilgileri.setIhtarname_tarihi(ihtarnameTarihi);
		alacakBilgisiBilgileri.setTanzim_tarihi(gelisTarihi);
		alacakBilgisiBilgileri.setBelge_miktari(doubleYap(xmlVeri.get_digerAlacak_tutar()));
		alacakBilgisiBilgileri.setDoviz_tipi(xmlVeri.get_digerAlacak_tutarAdi());
		alacakBilgisiDAO.kaydet(alacakBilgisiBilgileri);
	}

	@SuppressWarnings("unused")
	public void KaydetNoExcel(XMLToUyap xmlVeri) {

		// Gelen veride Müvekkil için sadece Müvekkilin adı alınarak ilgili
		// tablodan id si alınır. Diğer bilgier zaten sistemde mevcuttur.
		AlacakliDAO muvekkil = new AlacakliDAO();
		MuvekkilId = muvekkil.MuvekkilIdGetir(xmlVeri.get__kisiKurumBilgileri2_ad());

		// Borçlu bilgisinin tümü alınır. borçlu sistemde kayıtlı değil ise
		// bilgileri sisteme işlenir.
		BorcluBilgisiDAO Borclu = new BorcluBilgisiDAO(); // borçluyu kaydetmek
															// için gerekli DAO
															// ürettik

		BorcluBilgisi borcluBilgileri = new BorcluBilgisi(); // borçlu
																// bilgilerini
																// doldurmak
																// için yeni bir
																// Borçlu
																// Nesnesi
																// Ürettik
		borcluBilgileri.setAdSoyad(xmlVeri.get__kisiKurumBilgileri3_ad());

		AdresTuruDAO adresturu = new AdresTuruDAO(); // Adres türüne ait İd
														// bilgisini çekmek için
														// adresDAO ürettik
		borcluBilgileri.setAdresTuruId(adresturu.AdresIdGetir(xmlVeri.get___adres4_adresTuru()));

		borcluBilgileri.setAd(xmlVeri.get___kisiTumbilgileri3_adi());
		borcluBilgileri.setSoyad(xmlVeri.get___kisiTumbilgileri3_soyadi());
		borcluBilgileri.setTcNo(xmlVeri.get___kisiTumbilgileri3_tcKimlikNo());
		borcluBilgileri.setIlAdi(xmlVeri.get___adres4_il());
		borcluBilgileri.setIlId(Integer.parseInt(xmlVeri.get___adres4_ilKodu()));
		borcluBilgileri.setUyap_rol(xmlVeri.get__rolTur3_Rol());
		borcluBilgileri.setUyap_rol_id(xmlVeri.get__rolTur3_rolID());
		borcluBilgileri.setAdres(xmlVeri.get___adres4_adres());

		try {
			BorcluId = Borclu.saveBorclu(borcluBilgileri); // burada borçlu
															// bilgilerini
															// sisteme kaydettik
															// ve id sini aldık.
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Banka Vekil Avukat Bilgileri zaten sistemde kayıtlı olduğu için işlen
		// yapılmıyor.
		// --------------------------------------------------------------------------------

		HesapDAO hesapListesi = new HesapDAO();

		Hesap hesapBilgi = new Hesap(); // Hesap Nesnesi oluşturularak gerekli
										// alanlar işleniyor.

		hesapBilgi.setTemerrut_faiz_orani(doubleYap(xmlVeri.get___alacakKalemi2_faiz2_faizOran()));

		hesapBilgi.setAlacak_kalemi2_alacak_kalem_adi(xmlVeri.get__alacakKalemi2_alacakKalemAdi());
		hesapBilgi.setAlacak_kalemi2_kalem_kod_aciklama(xmlVeri.get__alacakKalemi2_alacakKalemKodAciklama());
		hesapBilgi.setAlacak_kalemi2_kalem_kod_turu(xmlVeri.get__alacakKalemi2_alacakKalemKodTuru());

		hesapBilgi.setAlacak_kalemi3_alacak_kalem_adi(xmlVeri.get__alacakKalemi3_alacakKalemAdi());
		hesapBilgi.setAlacak_kalemi3_kalem_kod_aciklama(xmlVeri.get__alacakKalemi3_alacakKalemKodAciklama());
		hesapBilgi.setAlacak_kalemi3_kalem_kod_turu(xmlVeri.get__alacakKalemi3_alacakKalemKodTuru());
		hesapBilgi.setAlacak_kalemi3_kalem_kod(xmlVeri.get__alacakKalemi3_alacakKalemKod());

		hesapBilgi.setAlacak_kalemi4_alacak_kalem_adi(xmlVeri.get__alacakKalemi4_alacakKalemAdi());
		hesapBilgi.setAlacak_kalemi4_kalem_kod_aciklama(xmlVeri.get__alacakKalemi4_alacakKalemKodAciklama());
		hesapBilgi.setAlacak_kalemi4_kalem_kod_turu(xmlVeri.get__alacakKalemi4_alacakKalemKodTuru());
		hesapBilgi.setAlacak_kalemi4_kalem_kod(xmlVeri.get__alacakKalemi4_alacakKalemKod());

		hesapBilgi.setAlacak_kalemi5_alacak_kalem_adi(xmlVeri.get__alacakKalemi5_alacakKalemAdi());
		hesapBilgi.setAlacak_kalemi5_kalem_kod_aciklama(xmlVeri.get__alacakKalemi5_alacakKalemKodAciklama());
		hesapBilgi.setAlacak_kalemi5_kalem_kod_turu(xmlVeri.get__alacakKalemi5_alacakKalemKodTuru());
		hesapBilgi.setAlacak_kalemi5_kalem_kod(xmlVeri.get__alacakKalemi5_alacakKalemKod());

		hesapBilgi.setTakip_alacagi(doubleYap(xmlVeri.get_digerAlacak_tutar()));
		hesapBilgi.setAsil_alacak(doubleYap(xmlVeri.get__alacakKalemi2_alacakKalemTutar()));
		hesapBilgi.setFaiz_gider_vergisi(doubleYap(xmlVeri.get__alacakKalemi4_alacakKalemIlkTutar()));
		hesapBilgi.setMasraf_tutari(doubleYap(xmlVeri.get__alacakKalemi5_alacakKalemIlkTutar()));

		double toplamBorc = doubleYap(xmlVeri.get_digerAlacak_tutar());

		double BSMV = doubleYap(xmlVeri.get__alacakKalemi4_alacakKalemTutar());
		double temerrutFaizi = BSMV / 5 * 100;
		double toplamFaiz = doubleYap(xmlVeri.get__alacakKalemi3_alacakKalemTutar());
		double islenmisFaiz = toplamFaiz - temerrutFaizi;

		hesapBilgi.setAsil_alacak(doubleYap(xmlVeri.get__alacakKalemi2_alacakKalemTutar()));

		hesapBilgi.setGecikme_faizi(doubleYap(df.format(islenmisFaiz)));

		hesapBilgi.setTemerrut_faizi(doubleYap(df.format(temerrutFaizi)));

		try {
			HesapId = hesapListesi.Kaydet(hesapBilgi); // Hesap Bilgi
														// girişlerini hesap
														// tablosuna işledik.
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		IcraDosyasiDAO icradosya = new IcraDosyasiDAO();

		IcraDosyasi dosya = new IcraDosyasi();

		dosya.setUyap_dosya_tipi(xmlVeri.getDosyaTipi());
		dosya.setKKDF(xmlVeri.getKKDFUygulansin());
		dosya.setBSMV(xmlVeri.getBSMVUygulansin());
		dosya.setBK84(xmlVeri.getBK84MaddeUygulansin());
		dosya.setTalepEdilenHak(xmlVeri.getAlacaklininTalepEttigiHak());
		dosya.setTakipSekliId(Integer.parseInt(xmlVeri.getTakipSekli()));
		dosya.setTakipYoluId(Integer.parseInt(xmlVeri.getTakipYolu()));
		dosya.setTakipTipiId(Integer.parseInt(xmlVeri.getTakipTuru()));
		dosya.setDiger_alacak_aciklama(xmlVeri.get_digerAlacak_digerAlacakAciklama());
		dosya.setPara_birimi(xmlVeri.get_digerAlacak_tutarAdi());
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = xmlVeri.get___alacakKalemi2_faiz2_baslangicTarihi();

		java.util.Date date = null, gelisTarihi = null, ihtarnameTarihi = null, deneme = null;
		try {
			date = convertFromJAVADateToSQLDate(format.parse(dateString));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dosya.setTakipTarihi(date);

		dosya.setUyap_para_turu(xmlVeri.get_digerAlacak_tutarTur());

		try {
			IcraDosyaId = icradosya.XMLKaydet(dosya);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Baglanti baglanti = new Baglanti();

		baglanti.setAlacakliID(MuvekkilId);
		baglanti.setBorcluID(BorcluId);
		baglanti.setHesaplamaID(HesapId);
		baglanti.setIcradosyasiID(IcraDosyaId);

		BaglantiDAO daobaglanti = new BaglantiDAO();

		try {
			daobaglanti.Kaydet(baglanti);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AlacakBilgisiDAO alacakBilgisiDAO = new AlacakBilgisiDAO();
		AlacakBilgisi alacakBilgisiBilgileri = new AlacakBilgisi();
		alacakBilgisiBilgileri.setIcra_dosyasi_id(IcraDosyaId);
		alacakBilgisiBilgileri.setIhtarname_tarihi(ihtarnameTarihi);
		alacakBilgisiBilgileri.setTanzim_tarihi(gelisTarihi);
		alacakBilgisiBilgileri.setBelge_miktari(doubleYap(xmlVeri.get_digerAlacak_tutar()));
		alacakBilgisiBilgileri.setDoviz_tipi(xmlVeri.get_digerAlacak_tutarAdi());
		alacakBilgisiDAO.kaydet(alacakBilgisiBilgileri);
		Utils utils = new Utils();
		utils.saveChronology(IcraDosyaId, ChronologyIdentifier.ISLEM_EXCELL, dosya.getIcraDosyaNo()+" Dosyası excell entegrasyonu ile açılmıştır.");
	}

	public void FreeXMLSave(FreeXML freeXmlVeri) {

		// Gelen veride Müvekkil için sadece Müvekkilin adı alınarak ilgili
		// tablodan id si alınır. Diğer bilgier zaten sistemde mevcuttur.
		AlacakliDAO muvekkil = new AlacakliDAO();
		MuvekkilId = muvekkil.MuvekkilIdGetir(freeXmlVeri.getMuvekkilAdi());

		// Borçlu bilgisinin tümü alınır. borçlu sistemde kayıtlı değil ise
		// bilgileri sisteme işlenir.
		BorcluBilgisiDAO Borclu = new BorcluBilgisiDAO(); // borçluyu kaydetmek
															// için gerekli DAO
															// ürettik

		BorcluBilgisi borcluBilgileri = new BorcluBilgisi(); // borçlu
																// bilgilerini
																// doldurmak
																// için yeni bir
																// Borçlu
																// Nesnesi
																// Ürettik
		borcluBilgileri.setAdSoyad(freeXmlVeri.getAdSoyad());

		AdresTuruDAO adresturu = new AdresTuruDAO(); // Adres türüne ait İd
														// bilgisini çekmek için
														// adresDAO ürettik
		borcluBilgileri.setAdresTuruId(adresturu.AdresIdGetir(freeXmlVeri.getAdresTuruId()));

		borcluBilgileri.setAd(freeXmlVeri.getAd());
		borcluBilgileri.setSoyad(freeXmlVeri.getSoyad());
		borcluBilgileri.setTcNo(freeXmlVeri.getTcNo());
		borcluBilgileri.setIlAdi(freeXmlVeri.getIlAdi());
		borcluBilgileri.setIlId(Integer.parseInt(freeXmlVeri.getIlId()));
		borcluBilgileri.setAdres(freeXmlVeri.getAdres());

		try {
			BorcluId = Borclu.saveBorclu(borcluBilgileri); // burada borçlu
															// bilgilerini
															// sisteme kaydettik
															// ve id sini aldık.
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Banka Vekil Avukat Bilgileri zaten sistemde kayıtlı olduğu için işlen
		// yapılmıyor.
		// --------------------------------------------------------------------------------

		IcraDosyasiDAO icradosya = new IcraDosyasiDAO();

		IcraDosyasi dosya = new IcraDosyasi();

		dosya.setKKDF(freeXmlVeri.getKKDFUygulansin());
		dosya.setBSMV(freeXmlVeri.getBSMVUygulansin());
		dosya.setBK84(freeXmlVeri.getBK84MaddeUygulansin());
		dosya.setTalepEdilenHak(freeXmlVeri.getAlacaklininTalepEttigiHak());
		dosya.setTakipSekliId(Integer.parseInt(freeXmlVeri.getTakipSekli()));
		dosya.setTakipYoluId(Integer.parseInt(freeXmlVeri.getTakipYolu()));
		dosya.setTakipTipiId(Integer.parseInt(freeXmlVeri.getTakipTuru()));
		dosya.setDiger_alacak_aciklama(freeXmlVeri.getDigerAlcakAciklama());
		dosya.setPara_birimi(freeXmlVeri.getParaBirimi());
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = freeXmlVeri.getTakipTarihi();

		java.util.Date date = null, gelisTarihi = null, ihtarnameTarihi = null;
		try {
			date = convertFromJAVADateToSQLDate(format.parse(dateString));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dosya.setTakipTarihi(date);

		dosya.setUyap_para_turu(freeXmlVeri.getUyapParaTuru());

		try {
			IcraDosyaId = icradosya.XMLKaydet(dosya);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		double freetoplamBorc = 0;
		for (int i = 0; i < freeXmlVeri.getHesapListesi().size(); i++) {

			HesapDAO hesapListesi = new HesapDAO();

			Hesap hesapBilgi = new Hesap(); // Hesap Nesnesi oluşturularak
											// gerekli alanlar işleniyor.

			hesapBilgi.setTemerrut_faiz_orani(doubleYap(freeXmlVeri.getHesapListesi().get(i).getTemerrut_faiz_orani()));

			hesapBilgi.setTakip_alacagi(doubleYap(freeXmlVeri.getHesapListesi().get(i).getTakip_alacagi()));
			hesapBilgi.setAsil_alacak(doubleYap(freeXmlVeri.getHesapListesi().get(i).getAsil_alacak()));
			hesapBilgi.setFaiz_gider_vergisi(doubleYap(freeXmlVeri.getHesapListesi().get(i).getFaizin_gider_vergisi()));
			hesapBilgi.setMasraf_tutari(doubleYap(freeXmlVeri.getHesapListesi().get(i).getNoter_masrafi()));

			double toplamBorc = doubleYap(freeXmlVeri.getHesapListesi().get(i).getTakip_alacagi());

			double BSMV = doubleYap(freeXmlVeri.getHesapListesi().get(i).getFaizin_gider_vergisi());
			double temerrutFaizi = BSMV / 5 * 100;
			double toplamFaiz = doubleYap(freeXmlVeri.getHesapListesi().get(i).getGecikme_faizi());
			double islenmisFaiz = toplamFaiz - temerrutFaizi;
			if (islenmisFaiz < 1) {
				temerrutFaizi = 0;
				islenmisFaiz = toplamFaiz;
			}

			hesapBilgi.setGecikme_faizi(doubleYap(df.format(islenmisFaiz)));

			hesapBilgi.setTemerrut_faizi(doubleYap(df.format(temerrutFaizi)));

			freetoplamBorc += toplamBorc;
			try {
				HesapId = hesapListesi.Kaydet(hesapBilgi); // Hesap Bilgi
															// girişlerini hesap
															// tablosuna
															// işledik.
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			
			AlacakBilgisi alacak = new AlacakBilgisi();
			AlacakBilgisiDAO alacakdao = new AlacakBilgisiDAO();
			
			alacak.setIcra_dosyasi_id(IcraDosyaId);
			alacak.setBelge_miktari(hesapBilgi.getTakip_alacagi());
			alacak.setDoviz_tipi("TL");
			alacak.setBelge_tipi_id(7);
			alacak.setBelge_statusu("TAKİP ALACAĞI");
			alacak.setFaiz_tipi_id(14);
			alacak.setTanzim_tarihi(gelisTarihi);
			alacakdao.kaydet(alacak);
			
			
			Baglanti baglanti = new Baglanti();

			baglanti.setAlacakliID(MuvekkilId);
			baglanti.setBorcluID(BorcluId);
			baglanti.setHesaplamaID(HesapId);
			baglanti.setIcradosyasiID(IcraDosyaId);
			try {
				//ChronologyUtil.getInstance().insertInstance(new Instance(IcraDosyaId, null, "Dosya Açıldı", null, 1));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


			BaglantiDAO daobaglanti = new BaglantiDAO();

			try {
				daobaglanti.Kaydet(baglanti);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		AlacakBilgisiDAO alacakBilgisiDAO = new AlacakBilgisiDAO();
		AlacakBilgisi alacakBilgisiBilgileri = new AlacakBilgisi();
		alacakBilgisiBilgileri.setIcra_dosyasi_id(IcraDosyaId);
		alacakBilgisiBilgileri.setIhtarname_tarihi(ihtarnameTarihi);
		alacakBilgisiBilgileri.setTanzim_tarihi(gelisTarihi);
		alacakBilgisiBilgileri.setBelge_miktari(freetoplamBorc);
		alacakBilgisiBilgileri.setDoviz_tipi(freeXmlVeri.getParaBirimi());
		alacakBilgisiDAO.kaydet(alacakBilgisiBilgileri);

	}

	public java.sql.Date convertFromJAVADateToSQLDate(java.util.Date javaDate) {
		java.sql.Date sqlDate = null;
		if (javaDate != null) {
			sqlDate = new Date(javaDate.getTime());
		}
		return sqlDate;
	}

}
