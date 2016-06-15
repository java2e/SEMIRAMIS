package pelops.model;

import java.sql.Date;

public class CokluBankaFour {

	private String icraDosyaNo;
	private String icraMudurluguAdi;
	private String talepMuzekkereAdi;
	private Date tebligatTarihi;
	private String sgk_standart_text;
	private String egm_standart_text;
	private String tapu_standart_text;
	private String posta_standart_text;
	private String banka4;
	private String banka7;
	private int id;
	private String adi;
	private String bankaIsmi;

	public String getBanka7() {
		return banka7;
	}

	public void setBanka7(String banka7) {
		this.banka7 = banka7;
	}

	public String getIcraDosyaNo() {
		return icraDosyaNo;
	}

	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
	}

	public String getIcraMudurluguAdi() {
		return icraMudurluguAdi;
	}

	public void setIcraMudurluguAdi(String icraMudurluguAdi) {
		this.icraMudurluguAdi = icraMudurluguAdi;
	}

	public String getTalepMuzekkereAdi() {
		return talepMuzekkereAdi;
	}

	public void setTalepMuzekkereAdi(String talepMuzekkereAdi) {
		this.talepMuzekkereAdi = talepMuzekkereAdi;
	}


	public Date getTebligatTarihi() {
		return tebligatTarihi;
	}

	public void setTebligatTarihi(Date tebligatTarihi) {
		this.tebligatTarihi = tebligatTarihi;
	}

	public String getSgk_standart_text() {
		return sgk_standart_text;
	}

	public void setSgk_standart_text(String sgk_standart_text) {
		this.sgk_standart_text = sgk_standart_text;
	}

	public String getEgm_standart_text() {
		return egm_standart_text;
	}

	public void setEgm_standart_text(String egm_standart_text) {
		this.egm_standart_text = egm_standart_text;
	}

	public String getTapu_standart_text() {
		return tapu_standart_text;
	}

	public void setTapu_standart_text(String tapu_standart_text) {
		this.tapu_standart_text = tapu_standart_text;
	}

	public String getPosta_standart_text() {
		return posta_standart_text;
	}

	public void setPosta_standart_text(String posta_standart_text) {
		this.posta_standart_text = posta_standart_text;
	}

	public String getBanka4() {
		return banka4;
	}

	public void setBanka4(String banka4) {
		this.banka4 = banka4;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public String getBankaIsmi() {
		return bankaIsmi;
	}

	public void setBankaIsmi(String bankaIsmi) {
		this.bankaIsmi = bankaIsmi;
	}

}
