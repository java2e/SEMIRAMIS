package pelops.muameleislemleri.util;

public class KurumModel {
	
	
	private int id;
	private String kurumAdi;
	private String adres;
	
	
	public KurumModel(int id, String kurumAdi, String adres) {
		this.id = id;
		this.kurumAdi = kurumAdi;
		this.adres = adres;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getKurumAdi() {
		return kurumAdi;
	}

	public void setKurumAdi(String kurumAdi) {
		this.kurumAdi = kurumAdi;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	
}
