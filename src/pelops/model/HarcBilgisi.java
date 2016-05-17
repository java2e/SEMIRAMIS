package pelops.model;

import java.util.Date;

public class HarcBilgisi {

	private int id;
	private Date harc_tarihi;

	private String harc_tipi;
	private double harc_orani;
	private double harc_miktari;
	private String uygulama_asamasi;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getHarc_tarihi() {
		return harc_tarihi;
	}

	public void setHarc_tarihi(Date harc_tarihi) {
		this.harc_tarihi = harc_tarihi;
	}

	public double getHarc_orani() {
		return harc_orani;
	}

	public void setHarc_orani(double harc_orani) {
		this.harc_orani = harc_orani;
	}

	public double getHarc_miktari() {
		return harc_miktari;
	}

	public void setHarc_miktari(double harc_miktari) {
		this.harc_miktari = harc_miktari;
	}

	public String getUygulama_asamasi() {
		return uygulama_asamasi;
	}

	public void setUygulama_asamasi(String uygulama_asamasi) {
		this.uygulama_asamasi = uygulama_asamasi;
	}

	public String getHarc_tipi() {
		return harc_tipi;
	}

	public void setHarc_tipi(String harc_tipi) {
		this.harc_tipi = harc_tipi;
	}

}
