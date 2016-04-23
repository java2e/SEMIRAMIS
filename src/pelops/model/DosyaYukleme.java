package pelops.model;

import java.util.Date;

public class DosyaYukleme {

	int id;
	Date belgeTarihi;
	String belgeYonu;
	String belgeAdi;
	int icraDosyaID;
	String dosyaYolu;
	String aciklama;

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBelgeTarihi() {
		return belgeTarihi;
	}

	public void setBelgeTarihi(Date belgeTarihi) {
		this.belgeTarihi = belgeTarihi;
	}

	public String getBelgeYonu() {
		return belgeYonu;
	}

	public void setBelgeYonu(String belgeYonu) {
		this.belgeYonu = belgeYonu;
	}

	public String getBelgeAdi() {
		return belgeAdi;
	}

	public void setBelgeAdi(String belgeAdi) {
		this.belgeAdi = belgeAdi;
	}

	public int getIcraDosyaID() {
		return icraDosyaID;
	}

	public void setIcraDosyaID(int icraDosyaID) {
		this.icraDosyaID = icraDosyaID;
	}

	public String getDosyaYolu() {
		return dosyaYolu;
	}

	public void setDosyaYolu(String dosyaYolu) {
		this.dosyaYolu = dosyaYolu;
	}

}
