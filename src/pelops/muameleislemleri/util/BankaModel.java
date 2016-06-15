package pelops.muameleislemleri.util;

public class BankaModel {

	public BankaModel(int id, String adi, String bankaBilgisi) {
		this.id = id;
		this.adi = adi;
		this.bankaBilgisi = bankaBilgisi;
	}

	private int id;
	private String adi;
	private String bankaBilgisi;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public String getBankaBilgisi() {
		return bankaBilgisi;
	}

	public void setBankaBilgisi(String bankaBilgisi) {
		this.bankaBilgisi = bankaBilgisi;
	}

}
