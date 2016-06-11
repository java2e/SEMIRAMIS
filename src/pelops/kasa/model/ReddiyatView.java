package pelops.kasa.model;

import java.util.Date;

public class ReddiyatView {

	// SELECT id, tahsilat_id, kasa_personel_id, onaylayan_id,
	// sasa_reddiyat_tutar,
	// devlet_reddiyat_tutar, muvekkil_reddiyat_tutar, sasa_durum, devlet_durum,
	// muvekkil_adi, borclu_adi, icra_dosyasi_id, icra_dosya_no, ad_soyad,
	// muvekkil_durum, toplam_tutar
	// FROM vwreddiyat;

	private int id;
	private int tahsilatId;
	private int kasaPersonelId;
	private int onaylayanId;
	private Double sasaReddiyatTutar;
	private Double devletReddiyatTuttar;
	private Double muvekkilReddiyatTutar;
	private int sasaDurum;
	private int devletDurum;
	private int muvekkilDurum;
	private String muvekkilAdi;
	private String borcluAdi;
	private int icraDosyaId;
	private String icraDosyaNo;
	private String adSoyad;
	private Double toplamTutar;
	private Date tarih;

	private double aktifTutar;

	private String aktifTutarTL;

	private String toplamTutarTL;
	private String sasaReddiyatTutarTL;
	private String devletReddiyatTuttarTL;
	private String muvekkilReddiyatTutarTL;

	public Date getTarih() {
		return tarih;
	}

	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}

	private String reddiyatTuru;

	public String getAktifTutarTL() {
		return aktifTutarTL;
	}

	public void setAktifTutarTL(String aktifTutarTL) {
		this.aktifTutarTL = aktifTutarTL;
	}

	public String getToplamTutarTL() {
		return toplamTutarTL;
	}

	public void setToplamTutarTL(String toplamTutarTL) {
		this.toplamTutarTL = toplamTutarTL;
	}

	public String getSasaReddiyatTutarTL() {
		return sasaReddiyatTutarTL;
	}

	public void setSasaReddiyatTutarTL(String sasaReddiyatTutarTL) {
		this.sasaReddiyatTutarTL = sasaReddiyatTutarTL;
	}

	public String getDevletReddiyatTuttarTL() {
		return devletReddiyatTuttarTL;
	}

	public void setDevletReddiyatTuttarTL(String devletReddiyatTuttarTL) {
		this.devletReddiyatTuttarTL = devletReddiyatTuttarTL;
	}

	public String getMuvekkilReddiyatTutarTL() {
		return muvekkilReddiyatTutarTL;
	}

	public void setMuvekkilReddiyatTutarTL(String muvekkilReddiyatTutarTL) {
		this.muvekkilReddiyatTutarTL = muvekkilReddiyatTutarTL;
	}

	public String getReddiyatTuru() {
		return reddiyatTuru;
	}

	public void setReddiyatTuru(String reddiyatTuru) {
		this.reddiyatTuru = reddiyatTuru;
	}

	public double getAktifTutar() {
		return aktifTutar;
	}

	public void setAktifTutar(double aktifTutar) {
		this.aktifTutar = aktifTutar;
	}

	public Double getToplamTutar() {
		return toplamTutar;
	}

	public void setToplamTutar(Double toplamTutar) {
		this.toplamTutar = toplamTutar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTahsilatId() {
		return tahsilatId;
	}

	public void setTahsilatId(int tahsilatId) {
		this.tahsilatId = tahsilatId;
	}

	public int getKasaPersonelId() {
		return kasaPersonelId;
	}

	public void setKasaPersonelId(int kasaPersonelId) {
		this.kasaPersonelId = kasaPersonelId;
	}

	public int getOnaylayanId() {
		return onaylayanId;
	}

	public void setOnaylayanId(int onaylayanId) {
		this.onaylayanId = onaylayanId;
	}

	public Double getSasaReddiyatTutar() {
		return sasaReddiyatTutar;
	}

	public void setSasaReddiyatTutar(Double sasaReddiyatTutar) {
		this.sasaReddiyatTutar = sasaReddiyatTutar;
	}

	public Double getDevletReddiyatTuttar() {
		return devletReddiyatTuttar;
	}

	public void setDevletReddiyatTuttar(Double devletReddiyatTuttar) {
		this.devletReddiyatTuttar = devletReddiyatTuttar;
	}

	public Double getMuvekkilReddiyatTutar() {
		return muvekkilReddiyatTutar;
	}

	public void setMuvekkilReddiyatTutar(Double muvekkilReddiyatTutar) {
		this.muvekkilReddiyatTutar = muvekkilReddiyatTutar;
	}

	public int getSasaDurum() {
		return sasaDurum;
	}

	public void setSasaDurum(int sasaDurum) {
		this.sasaDurum = sasaDurum;
	}

	public int getDevletDurum() {
		return devletDurum;
	}

	public void setDevletDurum(int devletDurum) {
		this.devletDurum = devletDurum;
	}

	public int getMuvekkilDurum() {
		return muvekkilDurum;
	}

	public void setMuvekkilDurum(int muvekkilDurum) {
		this.muvekkilDurum = muvekkilDurum;
	}

	public String getMuvekkilAdi() {
		return muvekkilAdi;
	}

	public void setMuvekkilAdi(String muvekkilAdi) {
		this.muvekkilAdi = muvekkilAdi;
	}

	public String getBorcluAdi() {
		return borcluAdi;
	}

	public void setBorcluAdi(String borcluAdi) {
		this.borcluAdi = borcluAdi;
	}

	public int getIcraDosyaId() {
		return icraDosyaId;
	}

	public void setIcraDosyaId(int icraDosyaId) {
		this.icraDosyaId = icraDosyaId;
	}

	public String getIcraDosyaNo() {
		return icraDosyaNo;
	}

	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
	}

	public String getAdSoyad() {
		return adSoyad;
	}

	public void setAdSoyad(String adSoyad) {
		this.adSoyad = adSoyad;
	}

}
