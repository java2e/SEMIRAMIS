package pelops.model;

import java.sql.Date;

public class Kasa {

	private int izlemeID;
	private int icraDosyaID;
	private int personelID;
	private int hesapID;
	private int borcluID;
	private String borcluAdi;
	private String icraDosyaNo;
	private Date izlemeTarihi;
	private String personelAdi;
	private double izlemeFiyat;
	
	
	public int getIzlemeID() {
		return izlemeID;
	}
	public void setIzlemeID(int izlemeID) {
		this.izlemeID = izlemeID;
	}
	public int getIcraDosyaID() {
		return icraDosyaID;
	}
	public void setIcraDosyaID(int icraDosyaID) {
		this.icraDosyaID = icraDosyaID;
	}
	public int getPersonelID() {
		return personelID;
	}
	public void setPersonelID(int personelID) {
		this.personelID = personelID;
	}
	public int getHesapID() {
		return hesapID;
	}
	public void setHesapID(int hesapID) {
		this.hesapID = hesapID;
	}
	public int getBorcluID() {
		return borcluID;
	}
	public void setBorcluID(int borcluID) {
		this.borcluID = borcluID;
	}
	public String getBorcluAdi() {
		return borcluAdi;
	}
	public void setBorcluAdi(String borcluAdi) {
		this.borcluAdi = borcluAdi;
	}
	public String getIcraDosyaNo() {
		return icraDosyaNo;
	}
	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
	}
	public Date getIzlemeTarihi() {
		return izlemeTarihi;
	}
	public void setIzlemeTarihi(Date izlemeTarihi) {
		this.izlemeTarihi = izlemeTarihi;
	}
	public String getPersonelAdi() {
		return personelAdi;
	}
	public void setPersonelAdi(String personelAdi) {
		this.personelAdi = personelAdi;
	}
	public double getIzlemeFiyat() {
		return izlemeFiyat;
	}
	public void setIzlemeFiyat(double izlemeFiyat) {
		this.izlemeFiyat = izlemeFiyat;
	}
	
	
	
}
