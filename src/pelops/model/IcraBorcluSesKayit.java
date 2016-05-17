package pelops.model;

import java.util.Date;

import org.primefaces.model.UploadedFile;

public class IcraBorcluSesKayit {

	private int id;
	private int borcluId;
	private Date tarihsaat;
	private String konu;
	private String aciklama;
	private UploadedFile seskayit;
	private String path;
	private int icraDosyaID;

	public int getBorcluId() {
		return borcluId;
	}

	public void setBorcluId(int borcluId) {
		this.borcluId = borcluId;
	}

	public Date getTarihsaat() {
		return tarihsaat;
	}

	public void setTarihsaat(Date tarihsaat) {
		this.tarihsaat = tarihsaat;
	}

	public String getKonu() {
		return konu;
	}

	public void setKonu(String konu) {
		this.konu = konu;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public UploadedFile getSeskayit() {
		return seskayit;
	}

	public void setSeskayit(UploadedFile seskayit) {
		this.seskayit = seskayit;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIcraDosyaID() {
		return icraDosyaID;
	}

	public void setIcraDosyaID(int icraDosyaID) {
		this.icraDosyaID = icraDosyaID;
	}
	
	

}
