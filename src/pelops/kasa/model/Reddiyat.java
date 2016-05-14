package pelops.kasa.model;

import java.util.Date;

public class Reddiyat {

	private int id;
	private int tahsilatID;
	private int kasaPersonelID;
	private int onaylayanID;
	private double sasaReddiyatTutari;
	private Integer sasaDurum;//
	private double devletReddiyatTutari;
	private Integer devletDurum;
	private double muvekkilReddiyatTutari;
	private Integer muvekkilDurum;// 0: kaydedildi fakat müvekkile aktarılmadı,
									// 1:
									// müvekkile ödendi,
	private String muvekkilAdi;

	private String borcluAdi;

	private String icraDosyaNo;

	private double aktifTutar;
	
	private String reddiyatTuru;
	
	
	
	
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

	public String getIcraDosyaNo() {
		return icraDosyaNo;
	}

	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
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

	private double toplamReddiyatTutari;
	private int icraDosyaID;

	public int getIcraDosyaID() {
		return icraDosyaID;
	}

	public void setIcraDosyaID(int icraDosyaID) {
		this.icraDosyaID = icraDosyaID;
	}

	public Integer getSasaDurum() {
		return sasaDurum;
	}

	public void setSasaDurum(Integer sasaDurum) {
		this.sasaDurum = sasaDurum;
	}

	public Integer getDevletDurum() {
		return devletDurum;
	}

	public void setDevletDurum(Integer devletDurum) {
		this.devletDurum = devletDurum;
	}

	public Integer getMuvekkilDurum() {
		return muvekkilDurum;
	}

	public void setMuvekkilDurum(Integer muvekkilDurum) {
		this.muvekkilDurum = muvekkilDurum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTahsilatID() {
		return tahsilatID;
	}

	public void setTahsilatID(int tahsilatID) {
		this.tahsilatID = tahsilatID;
	}

	public int getKasaPersonelID() {
		return kasaPersonelID;
	}

	public void setKasaPersonelID(int kasaPersonelID) {
		this.kasaPersonelID = kasaPersonelID;
	}

	public int getOnaylayanID() {
		return onaylayanID;
	}

	public void setOnaylayanID(int onaylayanID) {
		this.onaylayanID = onaylayanID;
	}

	public double getSasaReddiyatTutari() {
		return sasaReddiyatTutari;
	}

	public void setSasaReddiyatTutari(double sasaReddiyatTutari) {
		this.sasaReddiyatTutari = sasaReddiyatTutari;
	}

	public double getDevletReddiyatTutari() {
		return devletReddiyatTutari;
	}

	public void setDevletReddiyatTutari(double devletReddiyatTutari) {
		this.devletReddiyatTutari = devletReddiyatTutari;
	}

	public double getMuvekkilReddiyatTutari() {
		return muvekkilReddiyatTutari;
	}

	public void setMuvekkilReddiyatTutari(double muvekkilReddiyatTutari) {
		this.muvekkilReddiyatTutari = muvekkilReddiyatTutari;
	}

	public double getToplamReddiyatTutari() {
		return toplamReddiyatTutari;
	}

	public void setToplamReddiyatTutari(double toplamReddiyatTutari) {
		this.toplamReddiyatTutari = toplamReddiyatTutari;
	}

}
