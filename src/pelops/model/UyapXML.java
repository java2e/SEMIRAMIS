package pelops.model;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.Date;

public class UyapXML {
	
	private String dosyaTuru;
	private String takipTuru;
	private String takipYolu;
	private String alacaklininTalepEttigiHak;
	private String aciklama48e9;
	private String bk84MaddeUygulansin;
	private String bsmvUygulansin;
	private String kkdfUygulansin;
	private String dosyaBelirleyicisi;
	private String dosyaTipi;
	private String kisiKurumBilgileriID;
	private String kisiKurumBilgileriAd;
	private String adresID;
	private String muvekkilIlKodu;
	private String muvekkilIl; 
	private String muvekkilIlce;
	private String muvekkilIlceKodu;
	private String muvekkilAdresTuru;
	private String muvekkilAdres;
	private String muvekkilAdresTuruAciklama;
	private String muvekkilKurumAdi;
	private String muvekkilKamuOzel;
	private String muvekkilVergiNo;
	private String borcluIlKodu;
	private String borcluIl;
	private String borcluIlceKodu;
	private String borcluIlce;
	private String borcluAdresTuru;
	private String borcluAdres;
    private String borcluAdresTuruAciklama;
    private String borcluAdSoyad;
    private String borcluTCKimlik;
    private String kurumAvukatimi;
    private String avukatlikBaroAdi;
    private String baroNo;
    private String vergiNo;
    private String soyadi;
    private String adi;
    private String vekilIlKodu; 
    private String vekilIl;
    private String vekilIlceKodu;
    private String vekilIlce;
    private String vekilTelefon; 
    private String vekilCepTelefon; 
    private String vekilAdresTuru;
    private String vekilAdres;
    private String vekilAdresTuruAciklama;
    private String tutarTur;
    private double tutar;
    private String digerAlacakAciklama;
    private String tutarAdi;
    private double alacakKalemTutar_alacakKalemi_2;
       
    private double alacakKalemIlkTutar_alacakKalemi_2;
    private Date   baslangicTarihi_alacakKalemi_2;
    private String faizOran_alacakKalemi_2;
    private double alacakKalemTutar_alacakKalemi_3;
    private double alacakKalemIlkTutar_alacakKalemi_3;
    private double alacakKalemTutar_alacakKalemi_4;
    private double alacakKalemIlkTutar_alacakKalemi_4;
    private double alacakKalemTutar_alacakKalemi_5;
    private double alacakKalemIlkTutar_alacakKalemi_5;
    private int icra_dosyasi_id;
    
    NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
    private String asil_alacak, toplam_faiz, faiz_vergi, masraf, takip_tutari;
    
    private String masraf_tutari;
   private String ad;
   private String soyad;	
		    private String temerrut_faiz_orani;
		    private String alacak_kalemi2_kalem_kod_turu;
		    private String alacak_kalemi2_kalem_kod_aciklama;
		    private String alacak_kalemi2_alacak_kalem_adi;
		    private String alacak_kalemi3_kalem_kod_turu;
		    private String alacak_kalemi3_kalem_kod;
		    private String alacak_kalemi3_kalem_kod_aciklama;
		    private String alacak_kalemi3_alacak_kalem_adi;
		    private String alacak_kalemi4_kalem_kod_turu;
		    private String alacak_kalemi4_kalem_kod_aciklama;
		    private String alacak_kalemi4_alacak_kalem_adi;
		    private String alacak_kalemi4_kalem_kod;
		    private String alacak_kalemi5_kalem_kod_turu;
		    private String alacak_kalemi5_kalem_kod_aciklama;
		    private String alacak_kalemi5_alacak_kalem_adi;
		    private String alacak_kalemi5_kalem_kod;
		    private String para_birimi;
    private String takip_sekli_id ;
    
    private String vergi_dairesi;
    private Timestamp ekleme_tarihi;
    
    
    
    
     
	public String getAsil_alacak() {
		return asil_alacak;
	}
	public void setAsil_alacak(String asil_alacak) {
		this.asil_alacak = asil_alacak;
	}
	public String getToplam_faiz() {
		return toplam_faiz;
	}
	public void setToplam_faiz(String toplam_faiz) {
		this.toplam_faiz = toplam_faiz;
	}
	public String getFaiz_vergi() {
		return faiz_vergi;
	}
	public void setFaiz_vergi(String faiz_vergi) {
		this.faiz_vergi = faiz_vergi;
	}
	public String getMasraf() {
		return masraf;
	}
	public void setMasraf(String masraf) {
		this.masraf = masraf;
	}
	public String getTakip_tutari() {
		return takip_tutari;
	}
	public void setTakip_tutari(String takip_tutari) {
		this.takip_tutari = takip_tutari;
	}
	public String getVergi_dairesi() {
		return vergi_dairesi;
	}
	public void setVergi_dairesi(String vergi_dairesi) {
		this.vergi_dairesi = vergi_dairesi;
	}
	public Timestamp getEkleme_tarihi() {
		return ekleme_tarihi;
	}
	public void setEkleme_tarihi(Timestamp ekleme_tarihi) {
		this.ekleme_tarihi = ekleme_tarihi;
	}
	public String getTakip_sekli_id() {
		return takip_sekli_id;
	}
	public void setTakip_sekli_id(String takip_sekli_id) {
		this.takip_sekli_id = takip_sekli_id;
	}
	public String getMasraf_tutari() {
				return masraf_tutari;
			}
			public void setMasraf_tutari(String masraf_tutari) {
				this.masraf_tutari = masraf_tutari;
			}
			public String getAd() {
				return ad;
			}
			public void setAd(String ad) {
				this.ad = ad;
			}
			public String getSoyad() {
				return soyad;
			}
			public void setSoyad(String soyad) {
				this.soyad = soyad;
			}
			public String getTemerrut_faiz_orani() {
				return temerrut_faiz_orani;
			}
			public void setTemerrut_faiz_orani(String temerrut_faiz_orani) {
				this.temerrut_faiz_orani = temerrut_faiz_orani;
			}
			public String getAlacak_kalemi2_kalem_kod_turu() {
				return alacak_kalemi2_kalem_kod_turu;
			}
			public void setAlacak_kalemi2_kalem_kod_turu(
					String alacak_kalemi2_kalem_kod_turu) {
				this.alacak_kalemi2_kalem_kod_turu = alacak_kalemi2_kalem_kod_turu;
			}
			public String getAlacak_kalemi2_kalem_kod_aciklama() {
				return alacak_kalemi2_kalem_kod_aciklama;
			}
			public void setAlacak_kalemi2_kalem_kod_aciklama(
					String alacak_kalemi2_kalem_kod_aciklama) {
				this.alacak_kalemi2_kalem_kod_aciklama = alacak_kalemi2_kalem_kod_aciklama;
			}
			public String getAlacak_kalemi2_alacak_kalem_adi() {
				return alacak_kalemi2_alacak_kalem_adi;
			}
			public void setAlacak_kalemi2_alacak_kalem_adi(
					String alacak_kalemi2_alacak_kalem_adi) {
				this.alacak_kalemi2_alacak_kalem_adi = alacak_kalemi2_alacak_kalem_adi;
			}
			public String getAlacak_kalemi3_kalem_kod_turu() {
				return alacak_kalemi3_kalem_kod_turu;
			}
			public void setAlacak_kalemi3_kalem_kod_turu(
					String alacak_kalemi3_kalem_kod_turu) {
				this.alacak_kalemi3_kalem_kod_turu = alacak_kalemi3_kalem_kod_turu;
			}
			public String getAlacak_kalemi3_kalem_kod() {
				return alacak_kalemi3_kalem_kod;
			}
			public void setAlacak_kalemi3_kalem_kod(String alacak_kalemi3_kalem_kod) {
				this.alacak_kalemi3_kalem_kod = alacak_kalemi3_kalem_kod;
			}
			public String getAlacak_kalemi3_kalem_kod_aciklama() {
				return alacak_kalemi3_kalem_kod_aciklama;
			}
			public void setAlacak_kalemi3_kalem_kod_aciklama(
					String alacak_kalemi3_kalem_kod_aciklama) {
				this.alacak_kalemi3_kalem_kod_aciklama = alacak_kalemi3_kalem_kod_aciklama;
			}
			public String getAlacak_kalemi3_alacak_kalem_adi() {
				return alacak_kalemi3_alacak_kalem_adi;
			}
			public void setAlacak_kalemi3_alacak_kalem_adi(
					String alacak_kalemi3_alacak_kalem_adi) {
				this.alacak_kalemi3_alacak_kalem_adi = alacak_kalemi3_alacak_kalem_adi;
			}
			public String getAlacak_kalemi4_kalem_kod_turu() {
				return alacak_kalemi4_kalem_kod_turu;
			}
			public void setAlacak_kalemi4_kalem_kod_turu(
					String alacak_kalemi4_kalem_kod_turu) {
				this.alacak_kalemi4_kalem_kod_turu = alacak_kalemi4_kalem_kod_turu;
			}
			public String getAlacak_kalemi4_kalem_kod_aciklama() {
				return alacak_kalemi4_kalem_kod_aciklama;
			}
			public void setAlacak_kalemi4_kalem_kod_aciklama(
					String alacak_kalemi4_kalem_kod_aciklama) {
				this.alacak_kalemi4_kalem_kod_aciklama = alacak_kalemi4_kalem_kod_aciklama;
			}
			public String getAlacak_kalemi4_alacak_kalem_adi() {
				return alacak_kalemi4_alacak_kalem_adi;
			}
			public void setAlacak_kalemi4_alacak_kalem_adi(
					String alacak_kalemi4_alacak_kalem_adi) {
				this.alacak_kalemi4_alacak_kalem_adi = alacak_kalemi4_alacak_kalem_adi;
			}
			public String getAlacak_kalemi4_kalem_kod() {
				return alacak_kalemi4_kalem_kod;
			}
			public void setAlacak_kalemi4_kalem_kod(String alacak_kalemi4_kalem_kod) {
				this.alacak_kalemi4_kalem_kod = alacak_kalemi4_kalem_kod;
			}
			public String getAlacak_kalemi5_kalem_kod_turu() {
				return alacak_kalemi5_kalem_kod_turu;
			}
			public void setAlacak_kalemi5_kalem_kod_turu(
					String alacak_kalemi5_kalem_kod_turu) {
				this.alacak_kalemi5_kalem_kod_turu = alacak_kalemi5_kalem_kod_turu;
			}
			public String getAlacak_kalemi5_kalem_kod_aciklama() {
				return alacak_kalemi5_kalem_kod_aciklama;
			}
			public void setAlacak_kalemi5_kalem_kod_aciklama(
					String alacak_kalemi5_kalem_kod_aciklama) {
				this.alacak_kalemi5_kalem_kod_aciklama = alacak_kalemi5_kalem_kod_aciklama;
			}
			public String getAlacak_kalemi5_alacak_kalem_adi() {
				return alacak_kalemi5_alacak_kalem_adi;
			}
			public void setAlacak_kalemi5_alacak_kalem_adi(
					String alacak_kalemi5_alacak_kalem_adi) {
				this.alacak_kalemi5_alacak_kalem_adi = alacak_kalemi5_alacak_kalem_adi;
			}
			public String getAlacak_kalemi5_kalem_kod() {
				return alacak_kalemi5_kalem_kod;
			}
			public void setAlacak_kalemi5_kalem_kod(String alacak_kalemi5_kalem_kod) {
				this.alacak_kalemi5_kalem_kod = alacak_kalemi5_kalem_kod;
			}
			public String getPara_birimi() {
				return para_birimi;
			}
			public void setPara_birimi(String para_birimi) {
				this.para_birimi = para_birimi;
			}
	public int getIcra_dosyasi_id() {
		return icra_dosyasi_id;
	}
	public void setIcra_dosyasi_id(int icra_dosyasi_id) {
		this.icra_dosyasi_id = icra_dosyasi_id;
	}
	public String getTutarTur() {
		return tutarTur;
	}
	public void setTutarTur(String tutarTur) {
		this.tutarTur = tutarTur;
	}
	public double getTutar() {
		return tutar;
	}
	public void setTutar(double tutar) {
		this.tutar = tutar;
		this.takip_tutari = defaultFormat.format(tutar);
	}
	public String getDigerAlacakAciklama() {
		return digerAlacakAciklama;
	}
	public void setDigerAlacakAciklama(String digerAlacakAciklama) {
		this.digerAlacakAciklama = digerAlacakAciklama;
	}
	public String getTutarAdi() {
		return tutarAdi;
	}
	public void setTutarAdi(String tutarAdi) {
		this.tutarAdi = tutarAdi;
	}
	public double getAlacakKalemTutar_alacakKalemi_2() {
		return alacakKalemTutar_alacakKalemi_2;
	}
	public void setAlacakKalemTutar_alacakKalemi_2(
			double alacakKalemTutar_alacakKalemi_2) {
		this.alacakKalemTutar_alacakKalemi_2 = alacakKalemTutar_alacakKalemi_2;
		this.asil_alacak = defaultFormat.format(alacakKalemTutar_alacakKalemi_2);
	}
	public double getAlacakKalemIlkTutar_alacakKalemi_2() {
		return alacakKalemIlkTutar_alacakKalemi_2;
	}
	public void setAlacakKalemIlkTutar_alacakKalemi_2(
			double alacakKalemIlkTutar_alacakKalemi_2) {
		this.alacakKalemIlkTutar_alacakKalemi_2 = alacakKalemIlkTutar_alacakKalemi_2;
	}
	public Date getBaslangicTarihi_alacakKalemi_2() {
		return baslangicTarihi_alacakKalemi_2;
	}
	public void setBaslangicTarihi_alacakKalemi_2(
			Date baslangicTarihi_alacakKalemi_2) {
		this.baslangicTarihi_alacakKalemi_2 = baslangicTarihi_alacakKalemi_2;
	}
	public String getFaizOran_alacakKalemi_2() {
		return faizOran_alacakKalemi_2;
	}
	public void setFaizOran_alacakKalemi_2(String faizOran_alacakKalemi_2) {
		this.faizOran_alacakKalemi_2 = faizOran_alacakKalemi_2;
	}
	public double getAlacakKalemTutar_alacakKalemi_3() {
		return alacakKalemTutar_alacakKalemi_3;
	}
	public void setAlacakKalemTutar_alacakKalemi_3(
			double alacakKalemTutar_alacakKalemi_3) {
		this.alacakKalemTutar_alacakKalemi_3 = alacakKalemTutar_alacakKalemi_3;
		this.toplam_faiz = defaultFormat.format(alacakKalemTutar_alacakKalemi_3);
	}
	public double getAlacakKalemIlkTutar_alacakKalemi_3() {
		return alacakKalemIlkTutar_alacakKalemi_3;
	}
	public void setAlacakKalemIlkTutar_alacakKalemi_3(
			double alacakKalemIlkTutar_alacakKalemi_3) {
		this.alacakKalemIlkTutar_alacakKalemi_3 = alacakKalemIlkTutar_alacakKalemi_3;
	}
	public double getAlacakKalemTutar_alacakKalemi_4() {
		return alacakKalemTutar_alacakKalemi_4;
	}
	public void setAlacakKalemTutar_alacakKalemi_4(
			double alacakKalemTutar_alacakKalemi_4) {
		this.alacakKalemTutar_alacakKalemi_4 = alacakKalemTutar_alacakKalemi_4;
		this.faiz_vergi = defaultFormat.format(alacakKalemTutar_alacakKalemi_4);
	}
	public double getAlacakKalemIlkTutar_alacakKalemi_4() {
		return alacakKalemIlkTutar_alacakKalemi_4;
	}
	public void setAlacakKalemIlkTutar_alacakKalemi_4(
			double alacakKalemIlkTutar_alacakKalemi_4) {
		this.alacakKalemIlkTutar_alacakKalemi_4 = alacakKalemIlkTutar_alacakKalemi_4;
	}
	public double getAlacakKalemTutar_alacakKalemi_5() {
		return alacakKalemTutar_alacakKalemi_5;
	}
	public void setAlacakKalemTutar_alacakKalemi_5(
			double alacakKalemTutar_alacakKalemi_5) {
		this.alacakKalemTutar_alacakKalemi_5 = alacakKalemTutar_alacakKalemi_5;
		this.masraf = defaultFormat.format(alacakKalemTutar_alacakKalemi_5);
	}
	public double getAlacakKalemIlkTutar_alacakKalemi_5() {
		return alacakKalemIlkTutar_alacakKalemi_5;
	}
	public void setAlacakKalemIlkTutar_alacakKalemi_5(
			double alacakKalemIlkTutar_alacakKalemi_5) {
		this.alacakKalemIlkTutar_alacakKalemi_5 = alacakKalemIlkTutar_alacakKalemi_5;
	}
	public String getDosyaTuru() {
		return dosyaTuru;
	}
	public void setDosyaTuru(String dosyaTuru) {
		this.dosyaTuru = dosyaTuru;
	}
	public String getTakipTuru() {
		return takipTuru;
	}
	public void setTakipTuru(String takipTuru) {
		this.takipTuru = takipTuru;
	}
	public String getTakipYolu() {
		return takipYolu;
	}
	public void setTakipYolu(String takipYolu) {
		this.takipYolu = takipYolu;
	}
	public String getAlacaklininTalepEttigiHak() {
		return alacaklininTalepEttigiHak;
	}
	public void setAlacaklininTalepEttigiHak(String alacaklininTalepEttigiHak) {
		this.alacaklininTalepEttigiHak = alacaklininTalepEttigiHak;
	}
	public String getAciklama48e9() {
		return aciklama48e9;
	}
	public void setAciklama48e9(String aciklama48e9) {
		this.aciklama48e9 = aciklama48e9;
	}
	public String getBk84MaddeUygulansin() {
		return bk84MaddeUygulansin;
	}
	public void setBk84MaddeUygulansin(String bk84MaddeUygulansin) {
		this.bk84MaddeUygulansin = bk84MaddeUygulansin;
	}
	public String getBsmvUygulansin() {
		return bsmvUygulansin;
	}
	public void setBsmvUygulansin(String bsmvUygulansin) {
		this.bsmvUygulansin = bsmvUygulansin;
	}
	public String getKkdfUygulansin() {
		return kkdfUygulansin;
	}
	public void setKkdfUygulansin(String kkdfUygulansin) {
		this.kkdfUygulansin = kkdfUygulansin;
	}
	public String getDosyaBelirleyicisi() {
		return dosyaBelirleyicisi;
	}
	public void setDosyaBelirleyicisi(String dosyaBelirleyicisi) {
		this.dosyaBelirleyicisi = dosyaBelirleyicisi;
	}
	public String getDosyaTipi() {
		return dosyaTipi;
	}
	public void setDosyaTipi(String dosyaTipi) {
		this.dosyaTipi = dosyaTipi;
	}
	public String getKisiKurumBilgileriID() {
		return kisiKurumBilgileriID;
	}
	public void setKisiKurumBilgileriID(String kisiKurumBilgileriID) {
		this.kisiKurumBilgileriID = kisiKurumBilgileriID;
	}
	public String getKisiKurumBilgileriAd() {
		return kisiKurumBilgileriAd;
	}
	public void setKisiKurumBilgileriAd(String kisiKurumBilgileriAd) {
		this.kisiKurumBilgileriAd = kisiKurumBilgileriAd;
	}
	public String getAdresID() {
		return adresID;
	}
	public void setAdresID(String adresID) {
		this.adresID = adresID;
	}
	public String getMuvekkilIlKodu() {
		return muvekkilIlKodu;
	}
	public void setMuvekkilIlKodu(String muvekkilIlKodu) {
		this.muvekkilIlKodu = muvekkilIlKodu;
	}
	public String getMuvekkilIl() {
		return muvekkilIl;
	}
	public void setMuvekkilIl(String muvekkilIl) {
		this.muvekkilIl = muvekkilIl;
	}
	public String getMuvekkilIlce() {
		return muvekkilIlce;
	}
	public void setMuvekkilIlce(String muvekkilIlce) {
		this.muvekkilIlce = muvekkilIlce;
	}
	public String getMuvekkilIlceKodu() {
		return muvekkilIlceKodu;
	}
	public void setMuvekkilIlceKodu(String muvekkilIlceKodu) {
		this.muvekkilIlceKodu = muvekkilIlceKodu;
	}
	public String getMuvekkilAdresTuru() {
		return muvekkilAdresTuru;
	}
	public void setMuvekkilAdresTuru(String muvekkilAdresTuru) {
		this.muvekkilAdresTuru = muvekkilAdresTuru;
	}
	public String getMuvekkilAdres() {
		return muvekkilAdres;
	}
	public void setMuvekkilAdres(String muvekkilAdres) {
		this.muvekkilAdres = muvekkilAdres;
	}
	public String getMuvekkilAdresTuruAciklama() {
		return muvekkilAdresTuruAciklama;
	}
	public void setMuvekkilAdresTuruAciklama(String muvekkilAdresTuruAciklama) {
		this.muvekkilAdresTuruAciklama = muvekkilAdresTuruAciklama;
	}
	public String getMuvekkilKurumAdi() {
		return muvekkilKurumAdi;
	}
	public void setMuvekkilKurumAdi(String muvekkilKurumAdi) {
		this.muvekkilKurumAdi = muvekkilKurumAdi;
	}
	public String getMuvekkilKamuOzel() {
		return muvekkilKamuOzel;
	}
	public void setMuvekkilKamuOzel(String muvekkilKamuOzel) {
		this.muvekkilKamuOzel = muvekkilKamuOzel;
	}
	public String getMuvekkilVergiNo() {
		return muvekkilVergiNo;
	}
	public void setMuvekkilVergiNo(String muvekkilVergiNo) {
		this.muvekkilVergiNo = muvekkilVergiNo;
	}
	public String getBorcluIlKodu() {
		return borcluIlKodu;
	}
	public void setBorcluIlKodu(String borcluIlKodu) {
		this.borcluIlKodu = borcluIlKodu;
	}
	public String getBorcluIl() {
		return borcluIl;
	}
	public void setBorcluIl(String borcluIl) {
		this.borcluIl = borcluIl;
	}
	public String getBorcluIlceKodu() {
		return borcluIlceKodu;
	}
	public void setBorcluIlceKodu(String borcluIlceKodu) {
		this.borcluIlceKodu = borcluIlceKodu;
	}
	public String getBorcluIlce() {
		return borcluIlce;
	}
	public void setBorcluIlce(String borcluIlce) {
		this.borcluIlce = borcluIlce;
	}
	public String getBorcluAdresTuru() {
		return borcluAdresTuru;
	}
	public void setBorcluAdresTuru(String borcluAdresTuru) {
		this.borcluAdresTuru = borcluAdresTuru;
	}
	public String getBorcluAdres() {
		return borcluAdres;
	}
	public void setBorcluAdres(String borcluAdres) {
		this.borcluAdres = borcluAdres;
	}
	public String getBorcluAdresTuruAciklama() {
		return borcluAdresTuruAciklama;
	}
	public void setBorcluAdresTuruAciklama(String borcluAdresTuruAciklama) {
		this.borcluAdresTuruAciklama = borcluAdresTuruAciklama;
	}
	public String getBorcluAdSoyad() {
		return borcluAdSoyad;
	}
	public void setBorcluAdSoyad(String borcluAdSoyad) {
		this.borcluAdSoyad = borcluAdSoyad;
	}
	public String getBorcluTCKimlik() {
		return borcluTCKimlik;
	}
	public void setBorcluTCKimlik(String borcluTCKimlik) {
		this.borcluTCKimlik = borcluTCKimlik;
	}
	public String getKurumAvukatimi() {
		return kurumAvukatimi;
	}
	public void setKurumAvukatimi(String kurumAvukatimi) {
		this.kurumAvukatimi = kurumAvukatimi;
	}
	public String getAvukatlikBaroAdi() {
		return avukatlikBaroAdi;
	}
	public void setAvukatlikBaroAdi(String avukatlikBaroAdi) {
		this.avukatlikBaroAdi = avukatlikBaroAdi;
	}
	public String getBaroNo() {
		return baroNo;
	}
	public void setBaroNo(String baroNo) {
		this.baroNo = baroNo;
	}
	public String getVergiNo() {
		return vergiNo;
	}
	public void setVergiNo(String vergiNo) {
		this.vergiNo = vergiNo;
	}
	public String getSoyadi() {
		return soyadi;
	}
	public void setSoyadi(String soyadi) {
		this.soyadi = soyadi;
	}
	public String getAdi() {
		return adi;
	}
	public void setAdi(String adi) {
		this.adi = adi;
	}
	public String getVekilIlKodu() {
		return vekilIlKodu;
	}
	public void setVekilIlKodu(String vekilIlKodu) {
		this.vekilIlKodu = vekilIlKodu;
	}
	public String getVekilIl() {
		return vekilIl;
	}
	public void setVekilIl(String vekilIl) {
		this.vekilIl = vekilIl;
	}
	public String getVekilIlceKodu() {
		return vekilIlceKodu;
	}
	public void setVekilIlceKodu(String vekilIlceKodu) {
		this.vekilIlceKodu = vekilIlceKodu;
	}
	public String getVekilIlce() {
		return vekilIlce;
	}
	public void setVekilIlce(String vekilIlce) {
		this.vekilIlce = vekilIlce;
	}
	public String getVekilTelefon() {
		return vekilTelefon;
	}
	public void setVekilTelefon(String vekilTelefon) {
		this.vekilTelefon = vekilTelefon;
	}
	public String getVekilCepTelefon() {
		return vekilCepTelefon;
	}
	public void setVekilCepTelefon(String vekilCepTelefon) {
		this.vekilCepTelefon = vekilCepTelefon;
	}
	public String getVekilAdresTuru() {
		return vekilAdresTuru;
	}
	public void setVekilAdresTuru(String vekilAdresTuru) {
		this.vekilAdresTuru = vekilAdresTuru;
	}
	public String getVekilAdres() {
		return vekilAdres;
	}
	public void setVekilAdres(String vekilAdres) {
		this.vekilAdres = vekilAdres;
	}
	public String getVekilAdresTuruAciklama() {
		return vekilAdresTuruAciklama;
	}
	public void setVekilAdresTuruAciklama(String vekilAdresTuruAciklama) {
		this.vekilAdresTuruAciklama = vekilAdresTuruAciklama;
	}
    
    
	
	


}
