package pelops.model;

import java.sql.Timestamp;

public class ExcelEAktarma {

	
	private int icraMudurlugID;
	
	private int icraDosyaId;
	
	private double takipAlacagi;
	
	private String icraMudurlugu;
	
	private String icraDosyaNo;
	
	private String borcluAdi;
	
	private String muvekkilAdi;

	private Timestamp eklemeTarihi;
	
	private String tckimlik;
	
	
	
	
	
	

	public String getTckimlik() {
		return tckimlik;
	}

	public void setTckimlik(String tckimlik) {
		this.tckimlik = tckimlik;
	}

	public Timestamp getEklemeTarihi() {
		return eklemeTarihi;
	}

	public void setEklemeTarihi(Timestamp eklemeTarihi) {
		this.eklemeTarihi = eklemeTarihi;
	}

	public int getIcraMudurlugID() {
		return icraMudurlugID;
	}

	public void setIcraMudurlugID(int icraMudurlugID) {
		this.icraMudurlugID = icraMudurlugID;
	}

	public int getIcraDosyaId() {
		return icraDosyaId;
	}

	public void setIcraDosyaId(int icraDosyaId) {
		this.icraDosyaId = icraDosyaId;
	}

	public double getTakipAlacagi() {
		return takipAlacagi;
	}

	public void setTakipAlacagi(double takipAlacagi) {
		this.takipAlacagi = takipAlacagi;
	}

	public String getIcraMudurlugu() {
		return icraMudurlugu;
	}

	public void setIcraMudurlugu(String icraMudurlugu) {
		this.icraMudurlugu = icraMudurlugu;
	}

	public String getIcraDosyaNo() {
		return icraDosyaNo;
	}

	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
	}

	public String getBorcluAdi() {
		return borcluAdi;
	}

	public void setBorcluAdi(String borcluAdi) {
		this.borcluAdi = borcluAdi;
	}

	public String getMuvekkilAdi() {
		return muvekkilAdi;
	}

	public void setMuvekkilAdi(String muvekkilAdi) {
		this.muvekkilAdi = muvekkilAdi;
	}
	
	
}
