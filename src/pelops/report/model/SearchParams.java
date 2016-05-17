package pelops.report.model;

import java.util.Date;

public class SearchParams {
	private Date tarih1 = new Date("01/01/1900");
	private Date tarih2 = new Date();
	private String muvekkilAdi;
	private String borcluAdi;
	private String icraDosyaNo;
	private String icraMudurlugu;

	public Date getTarih1() {
		return tarih1;
	}

	public void setTarih1(Date tarih1) {
		this.tarih1 = tarih1;
	}

	public Date getTarih2() {
		return tarih2;
	}

	public void setTarih2(Date tarih2) {
		this.tarih2 = tarih2;
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

	public String getIcraDosyaNo() {
		return icraDosyaNo;
	}

	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
	}

	public String getIcraMudurlugu() {
		return icraMudurlugu;
	}

	public void setIcraMudurlugu(String icraMudurlugu) {
		this.icraMudurlugu = icraMudurlugu;
	}

}
