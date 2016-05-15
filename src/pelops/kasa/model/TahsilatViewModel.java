package pelops.kasa.model;

import java.util.Date;

public class TahsilatViewModel {
	private int id;
	private String fromID;
	private int icraDosyaID;
	private Date tarih;
	private String durum; // Tahislat yapılmış olan viewlerin durumu 0: onayda
							// , 1: onaylanmıs, 2: red/iptal, 3:makbuz
							// yazdırılmış, 4: işlem görmemiş
	private String borcluAdi;
	private Double odemeMiktari;
	private String hangiView;

	private String icraDosyaNo;

	public String getIcraDosyaNo() {
		return icraDosyaNo;
	}

	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
	}

	private String personelAdi;

	public String getPersonelAdi() {
		return personelAdi;
	}

	public void setPersonelAdi(String personelAdi) {
		this.personelAdi = personelAdi;
	}

	public String getFromID() {
		return fromID;
	}

	public void setFromID(String fromID) {
		this.fromID = fromID;
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

	public String getHangiView() {
		return hangiView;
	}

	public void setHangiView(String hangiView) {
		this.hangiView = hangiView;
	}

	public Double getOdemeMiktari() {
		return odemeMiktari;
	}

	public void setOdemeMiktari(Double odemeMiktari) {
		this.odemeMiktari = odemeMiktari;
	}

	public Date getTarih() {
		return tarih;
	}

	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}

	public String getDurum() {
		return durum;
	}

	public void setDurum(String durum) {
		this.durum = durum;
	}

	public String getBorcluAdi() {
		return borcluAdi;
	}

	public void setBorcluAdi(String borcluAdi) {
		this.borcluAdi = borcluAdi;
	}

}
