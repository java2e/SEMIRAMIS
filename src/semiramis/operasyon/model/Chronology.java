package semiramis.operasyon.model;

import java.util.Date;

public class Chronology {


	public Chronology(int id, String departman, String borcluAdi, String islem, String aciklama, int userid,
			Date date) {
		this.icraDosyaID = id;
		this.departman = departman;
		this.borcluAdi = borcluAdi;
		this.islem = islem;
		this.aciklama = aciklama;
		this.userid = userid;
		this.tarih = date;
	}

	private int id;
	private int icraDosyaID;
	private String departman;
	private String borcluAdi;
	private String islem;
	private String aciklama;
	private Date tarih;
	private int userid;
	private String personelAdi;

	public String getPersonelAdi() {
		return personelAdi;
	}

	public void setPersonelAdi(String personelAdi) {
		this.personelAdi = personelAdi;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Date getTarih() {
		return tarih;
	}

	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIcraDosyaID() {
		return icraDosyaID;
	}

	public void setIcraDosyaID(int icraDosyaID) {
		this.icraDosyaID = icraDosyaID;
	}

	public String getDepartman() {
		return departman;
	}

	public void setDepartman(String departman) {
		this.departman = departman;
	}

	public String getBorcluAdi() {
		return borcluAdi;
	}

	public void setBorcluAdi(String borcluAdi) {
		this.borcluAdi = borcluAdi;
	}

	public String getIslem() {
		return islem;
	}

	public void setIslem(String islem) {
		this.islem = islem;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

}
