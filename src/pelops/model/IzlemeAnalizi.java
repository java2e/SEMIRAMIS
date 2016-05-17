package pelops.model;

import java.util.Date;

public class IzlemeAnalizi {

	private String icra_dosyasi_no;
	private String muvekkil_adi;
	private String ad_soyad;
	private String dosyatipi;
	private Date gelis_tarihi;
	private Date izleme_tarihi;
	private String izlemesonucu;
	private Date odeme_sozu_tarihi;
	private double odeme_sozu_miktari;
	private String odeme_sozu_miktari_tl;
	private double odenmeyuzdesi;
	
	public double getOdenmeyuzdesi() {
		return odenmeyuzdesi;
	}
	public void setOdenmeyuzdesi(double odenmeyuzdesi) {
		this.odenmeyuzdesi = odenmeyuzdesi;
	}
	public String getIcra_dosyasi_no() {
		return icra_dosyasi_no;
	}
	public void setIcra_dosyasi_no(String icra_dosyasi_no) {
		this.icra_dosyasi_no = icra_dosyasi_no;
	}
	public String getMuvekkil_adi() {
		return muvekkil_adi;
	}
	public void setMuvekkil_adi(String muvekkil_adi) {
		this.muvekkil_adi = muvekkil_adi;
	}
	public String getAd_soyad() {
		return ad_soyad;
	}
	public void setAd_soyad(String ad_soyad) {
		this.ad_soyad = ad_soyad;
	}
	public String getDosyatipi() {
		return dosyatipi;
	}
	public void setDosyatipi(String dosyatipi) {
		this.dosyatipi = dosyatipi;
	}
	public Date getGelis_tarihi() {
		return gelis_tarihi;
	}
	public void setGelis_tarihi(Date gelis_tarihi) {
		this.gelis_tarihi = gelis_tarihi;
	}
	public Date getIzleme_tarihi() {
		return izleme_tarihi;
	}
	public void setIzleme_tarihi(Date izleme_tarihi) {
		this.izleme_tarihi = izleme_tarihi;
	}
	public String getIzlemesonucu() {
		return izlemesonucu;
	}
	public void setIzlemesonucu(String izlemesonucu) {
		this.izlemesonucu = izlemesonucu;
	}
	public Date getOdeme_sozu_tarihi() {
		return odeme_sozu_tarihi;
	}
	public void setOdeme_sozu_tarihi(Date odeme_sozu_tarihi) {
		this.odeme_sozu_tarihi = odeme_sozu_tarihi;
	}
	public double getOdeme_sozu_miktari() {
		return odeme_sozu_miktari;
	}
	public void setOdeme_sozu_miktari(double odeme_sozu_miktari) {
		this.odeme_sozu_miktari = odeme_sozu_miktari;
	}
	public String getOdeme_sozu_miktari_tl() {
		return odeme_sozu_miktari_tl;
	}
	public void setOdeme_sozu_miktari_tl(String odeme_sozu_miktari_tl) {
		this.odeme_sozu_miktari_tl = odeme_sozu_miktari_tl;
	}
		
}
