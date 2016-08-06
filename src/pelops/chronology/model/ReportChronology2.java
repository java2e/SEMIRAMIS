package pelops.chronology.model;

import java.io.Serializable;
import java.util.Date;

public class ReportChronology2 implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReportChronology2(String belgeAdi, Integer icraDosyaID, String dosyaYonu, Date tarih) {
		this.belgeAdi = belgeAdi;
		this.icraDosyaID = icraDosyaID;
		this.dosyaYonu = dosyaYonu;
		this.tarih = tarih;
	}


	private int id;
	private String belgeAdi;
	private Integer icraDosyaID;
	private Date tarih;
	private String dosyaYonu;

	public String getDosyaYonu() {
		return dosyaYonu;
	}

	public void setDosyaYonu(String dosyaYonu) {
		this.dosyaYonu = dosyaYonu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBelgeAdi() {
		return belgeAdi;
	}

	public Integer getIcraDosyaID() {
		return icraDosyaID;
	}

	public void setIcraDosyaID(Integer icraDosyaID) {
		this.icraDosyaID = icraDosyaID;
	}

	public void setBelgeAdi(String belgeAdi) {
		this.belgeAdi = belgeAdi;
	}

	public void setIcraDosyaID(int icraDosyaID) {
		this.icraDosyaID = icraDosyaID;
	}

	public Date getTarih() {
		return tarih;
	}

	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}

}
