package pelops.report.model;

public class TebligatZarfi {

	private int id;

	private String icraBilgi;

	private String icraDosyaNo;

	private String tarih;

	private String borclu;

	private String borcluAdres;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIcraDosyaNo() {
		return icraDosyaNo;
	}

	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
	}

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

	public String getBorclu() {
		return borclu;
	}

	public void setBorclu(String borclu) {
		this.borclu = borclu;
	}

	public String getIcraBilgi() {
		return icraBilgi;
	}

	public void setIcraBilgi(String icraBilgi) {
		this.icraBilgi = icraBilgi;
	}

	public String getBorcluAdres() {
		return borcluAdres;
	}

	public void setBorcluAdres(String borcluAdres) {
		this.borcluAdres = borcluAdres;
	}

}
