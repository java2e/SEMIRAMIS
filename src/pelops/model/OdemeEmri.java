package pelops.model;

import java.util.Date;

public class OdemeEmri {

	private int borcluId; // combobox
	private Date odemeTarihi;
	private double odemeMiktari;
	private String odemeSonucu;
	private String aciklama;
	private int icraDosyadiID;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIcraDosyadiID() {
		return icraDosyadiID;
	}

	public void setIcraDosyadiID(int icraDosyadiID) {
		this.icraDosyadiID = icraDosyadiID;
	}

	public int getBorcluId() {
		return borcluId;
	}

	public void setBorcluId(int borcluId) {
		this.borcluId = borcluId;
	}

	public Date getOdemeTarihi() {
		return odemeTarihi;
	}

	public void setOdemeTarihi(Date odemeTarihi) {
		this.odemeTarihi = odemeTarihi;
	}

	public double getOdemeMiktari() {
		return odemeMiktari;
	}

	public void setOdemeMiktari(double odemeMiktari) {
		this.odemeMiktari = odemeMiktari;
	}

	public String getOdemeSonucu() {
		return odemeSonucu;
	}

	public void setOdemeSonucu(String odemeSonucu) {
		this.odemeSonucu = odemeSonucu;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

}
