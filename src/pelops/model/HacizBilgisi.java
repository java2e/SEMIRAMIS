package pelops.model;

import java.util.Date;

public class HacizBilgisi {

	private int id;
	private int borcluId;
	private int personelId;
	private String personeName;
	private int hacizTipiId; // combo
	private String talimatIcraMd;
	private String dosyaNo;
	private Date talimatTarihi;
	private Date hacizTarihi;
	private int haczedilenTipiId; // combo
	private double hacizBedeli;
	private int teslimYeriId; // combo
	private String teslimYeri;
	private String sehir;
	private String aciklama;
	private int icra_dosyasi_id;
	private String hacizTipiAdi;
	private String haczedilenTipiAdi;

	public String getPersoneName() {
		return personeName;
	}

	public void setPersoneName(String personeName) {
		this.personeName = personeName;
	}

	public String getTeslimYeri() {
		return teslimYeri;
	}

	public void setTeslimYeri(String teslimYeri) {
		this.teslimYeri = teslimYeri;
	}

	public String getHacizTipiAdi() {
		return hacizTipiAdi;
	}

	public void setHacizTipiAdi(String hacizTipiAdi) {
		this.hacizTipiAdi = hacizTipiAdi;
	}

	public String getHaczedilenTipiAdi() {
		return haczedilenTipiAdi;
	}

	public void setHaczedilenTipiAdi(String haczedilenTipiAdi) {
		this.haczedilenTipiAdi = haczedilenTipiAdi;
	}

	public int getIcra_dosyasi_id() {
		return icra_dosyasi_id;
	}

	public void setIcra_dosyasi_id(int icra_dosyasi_id) {
		this.icra_dosyasi_id = icra_dosyasi_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPersonelId() {
		return personelId;
	}

	public void setPersonelId(int personelId) {
		this.personelId = personelId;
	}

	public int getBorcluId() {
		return borcluId;
	}

	public void setBorcluId(int borcluId) {
		this.borcluId = borcluId;
	}

	public String getTalimatIcraMd() {
		return talimatIcraMd;
	}

	public void setTalimatIcraMd(String talimatIcraMd) {
		this.talimatIcraMd = talimatIcraMd;
	}

	public String getDosyaNo() {
		return dosyaNo;
	}

	public void setDosyaNo(String dosyaNo) {
		this.dosyaNo = dosyaNo;
	}

	public Date getTalimatTarihi() {
		return talimatTarihi;
	}

	public void setTalimatTarihi(Date talimatTarihi) {
		this.talimatTarihi = talimatTarihi;
	}

	public Date getHacizTarihi() {
		return hacizTarihi;
	}

	public void setHacizTarihi(Date hacizTarihi) {
		this.hacizTarihi = hacizTarihi;
	}

	public double getHacizBedeli() {
		return hacizBedeli;
	}

	public void setHacizBedeli(double hacizBedeli) {
		this.hacizBedeli = hacizBedeli;
	}

	public String getSehir() {
		return sehir;
	}

	public void setSehir(String sehir) {
		this.sehir = sehir;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public int getHacizTipiId() {
		return hacizTipiId;
	}

	public void setHacizTipiId(int hacizTipiId) {
		this.hacizTipiId = hacizTipiId;
	}

	public int getHaczedilenTipiId() {
		return haczedilenTipiId;
	}

	public void setHaczedilenTipiId(int haczedilenTipiId) {
		this.haczedilenTipiId = haczedilenTipiId;
	}

	public int getTeslimYeriId() {
		return teslimYeriId;
	}

	public void setTeslimYeriId(int teslimYeriId) {
		this.teslimYeriId = teslimYeriId;
	}

}
