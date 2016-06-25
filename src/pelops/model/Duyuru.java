package pelops.model;

import java.util.Date;

public class Duyuru {

	private int id; 
	private String aciklama;
	private Date gunTarih;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getGunTarih() {
		return gunTarih;
	}
	public void setGunTarih(Date gunTarih) {
		this.gunTarih = gunTarih;
	}
	public String getAciklama() {
		return aciklama;
	}
	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
	
	
	
}
