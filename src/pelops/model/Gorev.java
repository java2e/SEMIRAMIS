package pelops.model;

import java.util.Date;

public class Gorev {

	private int id; 
	private String konu;
	private String aciklama;
	private Date basTarih;
	private Date bitTarih;
	private int userId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKonu() {
		return konu;
	}
	public void setKonu(String konu) {
		this.konu = konu;
	}
	public String getAciklama() {
		return aciklama;
	}
	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
	public Date getBasTarih() {
		return basTarih;
	}
	public void setBasTarih(Date basTarih) {
		this.basTarih = basTarih;
	}
	public Date getBitTarih() {
		return bitTarih;
	}
	public void setBitTarih(Date bitTarih) {
		this.bitTarih = bitTarih;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	

	
	
	
	
}

