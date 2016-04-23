package pelops.kasa.model;

import java.util.Date;

public class Reddiyat {

	private int id;
	private int tahsilatID;
	private int kasaPersonelID;
	private int onaylayanID;
	private double sasaReddiyatTutari;
	private int sasaDurum;//
	private double devletReddiyatTutari;
	private int devletDurum;
	private double muvekkilReddiyatTutari;
	private int muvekkilDurum;// 0: kaydedildi fakat müvekkile aktarılmadı, 1:
								// müvekkile ödendi,
	private double toplamReddiyatTutari;
	private int icraDosyaID;

	public int getIcraDosyaID() {
		return icraDosyaID;
	}

	public void setIcraDosyaID(int icraDosyaID) {
		this.icraDosyaID = icraDosyaID;
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
