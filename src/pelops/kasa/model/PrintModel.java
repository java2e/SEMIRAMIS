package pelops.kasa.model;

public class PrintModel {

	public static final String TAHSILAT_MAKBUZU = "102_makbuz.jasper";
	public static final String TAHSILAT_MAKBUZUNEW = "Tahsilat-Makbuzu.jasper";

	public static String getTahsilatMakbuzunew() {
		return TAHSILAT_MAKBUZUNEW;
	}

	public static final String HITAM_MAKBUZU = "101_hitam_belgesi.jasper";

	private String tarih;

	private String siraNo;

	private String seri;

	private String makbuzNo;

	private String alacakli;

	private String borclu;

	private String dosyaNo;

	private String sebebi;

	private String alinanMiktar;

	private String gorevliKisi;

	private String miktari;

	private String adSoyad;

	private String icraMudurlugu;

	private String borcunSebebi;

	private String dosyaGorevlisi;
	
	private String dosyaAvukati;
	

	public String getDosyaAvukati() {
		return dosyaAvukati;
	}

	public void setDosyaAvukati(String dosyaAvukati) {
		this.dosyaAvukati = dosyaAvukati;
	}

	public String getDosyaGorevlisi() {
		return dosyaGorevlisi;
	}

	public void setDosyaGorevlisi(String dosyaGorevlisi) {
		this.dosyaGorevlisi = dosyaGorevlisi;
	}

	public String getBorcunSebebi() {
		return borcunSebebi;
	}

	public void setBorcunSebebi(String borcunSebebi) {
		this.borcunSebebi = borcunSebebi;
	}

	public String getIcraMudurlugu() {
		return icraMudurlugu;
	}

	public void setIcraMudurlugu(String icraMudurlugu) {
		this.icraMudurlugu = icraMudurlugu;
	}

	public String getAdSoyad() {
		return adSoyad;
	}

	public void setAdSoyad(String adSoyad) {
		this.adSoyad = adSoyad;
	}

	public String getMiktari() {
		return miktari;
	}

	public void setMiktari(String miktari) {
		this.miktari = miktari;
	}

	public String getGorevliKisi() {
		return gorevliKisi;
	}

	public void setGorevliKisi(String gorevliKisi) {
		this.gorevliKisi = gorevliKisi;
	}

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

	public String getSiraNo() {
		return siraNo;
	}

	public void setSiraNo(String siraNo) {
		this.siraNo = siraNo;
	}

	public String getSeri() {
		return seri;
	}

	public void setSeri(String seri) {
		this.seri = seri;
	}

	public String getMakbuzNo() {
		return makbuzNo;
	}

	public void setMakbuzNo(String makbuzNo) {
		this.makbuzNo = makbuzNo;
	}

	public String getAlacakli() {
		return alacakli;
	}

	public void setAlacakli(String alacakli) {
		this.alacakli = alacakli;
	}

	public String getBorclu() {
		return borclu;
	}

	public void setBorclu(String borclu) {
		this.borclu = borclu;
	}

	public String getDosyaNo() {
		return dosyaNo;
	}

	public void setDosyaNo(String dosyaNo) {
		this.dosyaNo = dosyaNo;
	}

	public String getSebebi() {
		return sebebi;
	}

	public void setSebebi(String sebebi) {
		this.sebebi = sebebi;
	}

	public String getAlinanMiktar() {
		return alinanMiktar;
	}

	public void setAlinanMiktar(String alinanMiktar) {
		this.alinanMiktar = alinanMiktar;
	}

}
