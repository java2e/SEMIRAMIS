package pelops.chronology.model;

import java.util.Date;

public class Instance {

	public Instance(Integer icraDosyaID, String icradosyaNo, String olayAdi, String aciklama, int state) {

		if (!olayAdi.equals(null)) {
			this.olayAdi = olayAdi;
		}
		if (aciklama != null) {
			this.aciklama = aciklama;
		}
		if (icradosyaNo != null) {
			this.icraDosyaNo = icradosyaNo;
		}
		if (icraDosyaID != null) {
			this.icraDosyaID = icraDosyaID;
		}

		this.state = state;

	}

	public Instance(int id, String icradosyaNo, Integer icraDosyaID, Date tarih, String olayAdi, Integer userId,
			String aciklama, String durum) {
		if (!tarih.toString().equals(null)) {
			this.tarih = tarih;
		}
		if (!olayAdi.equals(null)) {
			this.olayAdi = olayAdi;
		}
		if (!aciklama.equals(null)) {
			this.aciklama = aciklama;
		}
		if (icradosyaNo != null) {
			this.icraDosyaNo = icradosyaNo;
		}
		if (icraDosyaID != null) {
			this.icraDosyaID = icraDosyaID;
		}
		if (userId != null) {
			this.userId = userId;
		}
		if (!durum.equals(null)) {
			this.durum = durum;
		}
		this.id = id;
	}

	private int id;
	private String icraDosyaNo;
	private Integer icraDosyaID;
	private Date tarih;
	private String olayAdi; // borclu kayıt gibi ana olaylar Instance util
							// üzerinden girilecek
	private Integer userId;
	private String aciklama; // detaylar istege baglı yazılabilir
	private String durum; // insert update delete gibi işlemleri kapsar
	private int state;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getDurum() {
		return durum;
	}

	public void setDurum(String durum) {
		this.durum = durum;
	}

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

	public Integer getIcraDosyaID() {
		return icraDosyaID;
	}

	public void setIcraDosyaID(Integer icraDosyaID) {
		this.icraDosyaID = icraDosyaID;
	}

	public Date getTarih() {
		return tarih;
	}

	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}

	public String getOlayAdi() {
		return olayAdi;
	}

	public void setOlayAdi(String olayAdi) {
		this.olayAdi = olayAdi;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

}
