package pelops.kasa.model;

import java.util.Date;

public class KasaSearchParams {

	private String borcluAdi;
	private Date tarih1;
	private Date tarih2;
	private int icraDosyaId;
	private String icraDosyaNo;

	public String getBorcluAdi() {
		return borcluAdi;
	}

	public void setBorcluAdi(String borcluAdi) {
		this.borcluAdi = borcluAdi;
	}

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

}
