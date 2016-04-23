package pelops.model;

public class Adres {

	private int adresId;
	private int ilId ;
	private int ilceId ;
	private String adres;
	private int adresStatusu;
	private String semtAdi;
	private int borcluId ;
	private int alacakliId;
	
	public int getIlId() {
		return ilId;
	}
	public void setIlId(int ilId) {
		this.ilId = ilId;
	}
	public int getIlceId() {
		return ilceId;
	}
	public void setIlceId(int ilceId) {
		this.ilceId = ilceId;
	}
	public int getBorcluId() {
		return borcluId;
	}
	public void setBorcluId(int borcluId) {
		this.borcluId = borcluId;
	}
	public int getAdresId() {
		return adresId;
	}
	public void setAdresId(int adresId) {
		this.adresId = adresId;
	}
	
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public int getAdresStatusu() {
		return adresStatusu;
	}
	public void setAdresStatusu(int adresStatusu) {
		this.adresStatusu = adresStatusu;
	}
	
	public String getSemtAdi() {
		return semtAdi;
	}
	public void setSemtAdi(String semtAdi) {
		this.semtAdi = semtAdi;
	}
	public int getAlacakliId() {
		return alacakliId;
	}
	public void setAlacakliId(int alacakliId) {
		this.alacakliId = alacakliId;
	}
	
	
	
	
}
