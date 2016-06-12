package pelops.users;

import java.util.Date;

public class TakimKullanici {
	
	private int id;
	
	private int takimId;
	
	private String takimAck;
	
	private int kullaniciId;
	
	private String kullaniciAck;
	
	private int guncelleyenKullaniciId;
	
	private String guncelleyenKullaniciAck;
	
	private Date guncellemeZamani;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTakimId() {
		return takimId;
	}

	public void setTakimId(int takimId) {
		this.takimId = takimId;
	}

	public int getKullaniciId() {
		return kullaniciId;
	}

	public void setKullaniciId(int kullaniciId) {
		this.kullaniciId = kullaniciId;
	}

	public String getTakimAck() {
		return takimAck;
	}

	public void setTakimAck(String takimAck) {
		this.takimAck = takimAck;
	}

	public String getKullaniciAck() {
		return kullaniciAck;
	}

	public void setKullaniciAck(String kullaniciAck) {
		this.kullaniciAck = kullaniciAck;
	}

	public int getGuncelleyenKullaniciId() {
		return guncelleyenKullaniciId;
	}

	public void setGuncelleyenKullaniciId(int guncelleyenKullaniciId) {
		this.guncelleyenKullaniciId = guncelleyenKullaniciId;
	}

	public String getGuncelleyenKullaniciAck() {
		return guncelleyenKullaniciAck;
	}

	public void setGuncelleyenKullaniciAck(String guncelleyenKullaniciAck) {
		this.guncelleyenKullaniciAck = guncelleyenKullaniciAck;
	}

	public Date getGuncellemeZamani() {
		return guncellemeZamani;
	}

	public void setGuncellemeZamani(Date guncellemeZamani) {
		this.guncellemeZamani = guncellemeZamani;
	}
	
	

}
