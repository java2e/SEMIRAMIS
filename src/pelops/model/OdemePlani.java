package pelops.model;

import java.util.Date;

public class OdemePlani {
	
	private int icraDosyID;
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private double kalanAlacakMiktari;
	private double pesinatMiktari;
	private double taksitAylikMiktar;
	private double odenecekMiktar;
	
	
	public double getOdenecekMiktar() {
		return odenecekMiktar;
	}
	public void setOdenecekMiktar(double odenecekMiktar) {
		this.odenecekMiktar = odenecekMiktar;
	}
	public double getTaksitAylikMiktar() {
		return taksitAylikMiktar;
	}
	public void setTaksitAylikMiktar(double taksitAylikMiktar) {
		this.taksitAylikMiktar = taksitAylikMiktar;
	}
	private int taksitAdedi;
	private Date IlkOdemeTarihi;
	private Date taksitTarihleri;
	private String durum;
	private String taksitAylar;
	public int getIcraDosyID() {
		return icraDosyID;
	}
	public void setIcraDosyID(int icraDosyID) {
		this.icraDosyID = icraDosyID;
	}
	public double getKalanAlacakMiktari() {
		return kalanAlacakMiktari;
	}
	public void setKalanAlacakMiktari(double kalanAlacakMiktari) {
		this.kalanAlacakMiktari = kalanAlacakMiktari;
	}
	public double getPesinatMiktari() {
		return pesinatMiktari;
	}
	public void setPesinatMiktari(double pesinatMiktari) {
		this.pesinatMiktari = pesinatMiktari;
	}
	public int getTaksitAdedi() {
		return taksitAdedi;
	}
	public void setTaksitAdedi(int taksitAdedi) {
		this.taksitAdedi = taksitAdedi;
	}
	public Date getIlkOdemeTarihi() {
		return IlkOdemeTarihi;
	}
	public void setIlkOdemeTarihi(Date ilkOdemeTarihi) {
		IlkOdemeTarihi = ilkOdemeTarihi;
	}
	
	public Date getTaksitTarihleri() {
		return taksitTarihleri;
	}
	public void setTaksitTarihleri(Date taksitRTarihleri) {
		this.taksitTarihleri = taksitRTarihleri;
	}
	public String getDurum() {
		return durum;
	}
	public void setDurum(String durum) {
		this.durum = durum;
	}
	public String getTaksitAylar() {
		return taksitAylar;
	}
	public void setTaksitAylar(String taksitAylar) {
		this.taksitAylar = taksitAylar;
	}
	
	
	

	
	
	
	
	
	
	
	
	

}
