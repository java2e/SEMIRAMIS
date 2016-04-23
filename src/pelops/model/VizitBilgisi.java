package pelops.model;

import java.util.Date;

public class VizitBilgisi {

	private int id;
	private int borcluId;
	private Date vizitTarihi;
	private String vizitStatusu;
	private Date odemeSozuTarihi;
	private double odemeMiktari;
	private String vizitNotu;
	private int vizitPersoneliId;
	private int icraDosyaID;
	private String userName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getIcraDosyaID() {
		return icraDosyaID;
	}

	public void setIcraDosyaID(int icraDosyaID) {
		this.icraDosyaID = icraDosyaID;
	}

	public int getBorcluId() {
		return borcluId;
	}

	public void setBorcluId(int borcluId) {
		this.borcluId = borcluId;
	}

	public Date getVizitTarihi() {
		return vizitTarihi;
	}

	public void setVizitTarihi(Date vizitTarihi) {
		this.vizitTarihi = vizitTarihi;
	}

	public String getVizitStatusu() {
		return vizitStatusu;
	}

	public void setVizitStatusu(String vizitStatusu) {
		this.vizitStatusu = vizitStatusu;
	}

	public Date getOdemeSozuTarihi() {
		return odemeSozuTarihi;
	}

	public void setOdemeSozuTarihi(Date odemeSozuTarihi) {
		this.odemeSozuTarihi = odemeSozuTarihi;
	}

	public double getOdemeMiktari() {
		return odemeMiktari;
	}

	public void setOdemeMiktari(double odemeMiktari) {
		this.odemeMiktari = odemeMiktari;
	}

	public String getVizitNotu() {
		return vizitNotu;
	}

	public void setVizitNotu(String vizitNotu) {
		this.vizitNotu = vizitNotu;
	}

	public int getVizitPersoneliId() {
		return vizitPersoneliId;
	}

	public void setVizitPersoneliId(int vizitPersoneliId) {
		this.vizitPersoneliId = vizitPersoneliId;
	}

}
