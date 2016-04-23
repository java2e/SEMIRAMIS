package pelops.kasa.model;

import java.util.Date;

public class Hitam {
	private int id;
	private int tahsilatID;
	private int reddiyatID;
	private Date tarih;
	private int icraDosyaID;
	private int hitamDurum;
	private int kasaPersonelID;
	private int onaylayanID;

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

	public int getHitamDurum() {
		return hitamDurum;
	}

	public void setHitamDurum(int hitamDurum) {
		this.hitamDurum = hitamDurum;
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

	public int getReddiyatID() {
		return reddiyatID;
	}

	public void setReddiyatID(int reddiyatID) {
		this.reddiyatID = reddiyatID;
	}

	public Date getTarih() {
		return tarih;
	}

	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}

	public int getIcraDosyaID() {
		return icraDosyaID;
	}

	public void setIcraDosyaID(int icraDosyaID) {
		this.icraDosyaID = icraDosyaID;
	}

}
