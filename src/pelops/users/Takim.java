package pelops.users;

import java.util.Date;

public class Takim {
	
	private int id;
	
	private String takimAdi;
	
	private String takimUrlImg;
	
	private int takimYonetici;
	
	private int guncelleyenKullaniciId;
	
	private String guncelleyenKullaniciAck;
	
	private Date guncellemeZamani;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTakimAdi() {
		return takimAdi;
	}

	public void setTakimAdi(String takimAdi) {
		this.takimAdi = takimAdi;
	}

	public String getTakimUrlImg() {
		return takimUrlImg;
	}

	public void setTakimUrlImg(String takimUrlImg) {
		this.takimUrlImg = takimUrlImg;
	}

	public int getTakimYonetici() {
		return takimYonetici;
	}

	public void setTakimYonetici(int takimYonetici) {
		this.takimYonetici = takimYonetici;
	}

	public int getGuncelleyenKullaniciId() {
		return guncelleyenKullaniciId;
	}

	public void setGuncelleyenKullaniciId(int guncelleyenKullaniciKullaniciId) {
		this.guncelleyenKullaniciId = guncelleyenKullaniciKullaniciId;
	}

	public Date getGuncellemeZamani() {
		return guncellemeZamani;
	}

	public void setGuncellemeZamani(Date guncellemeZamani) {
		this.guncellemeZamani = guncellemeZamani;
	}

	public String getGuncelleyenKullaniciAck() {
		return guncelleyenKullaniciAck;
	}

	public void setGuncelleyenKullaniciAck(String guncelleyenKullaniciAck) {
		this.guncelleyenKullaniciAck = guncelleyenKullaniciAck;
	}
	
	
	
	

}
