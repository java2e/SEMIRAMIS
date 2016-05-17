package pelops.model;

public class Posta {

	private int id;
	private int serial;
	private int icra_dosya_id;
	private String barkod;
	private int durum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public int getIcra_dosya_id() {
		return icra_dosya_id;
	}

	public void setIcra_dosya_id(int icra_dosya_id) {
		this.icra_dosya_id = icra_dosya_id;
	}

	public String getBarkod() {
		return barkod;
	}

	public void setBarkod(String barkod) {
		this.barkod = barkod;
	}

	public int getDurum() {
		return durum;
	}

	public void setDurum(int durum) {
		this.durum = durum;
	}

}
