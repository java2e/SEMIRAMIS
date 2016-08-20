package semiramis.operasyon.model;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String imagePath;
	private boolean period;
	private int id;
	private Integer icraDosyaID;
	private Date tarih;

	public Task(String title, String imagePath, boolean period) {
		this.title = title;
		this.imagePath = imagePath;
		this.period = period;
	}

	public Task(String title, String imagePath, boolean period, Integer icraDosyaID, Date date) {
		this.title = title;
		this.imagePath = imagePath;
		this.period = period;
		this.icraDosyaID = icraDosyaID;
		this.tarih = date;
	}

	public Date getTarih() {
		return tarih;
	}

	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}

	public String getTitle() {
		return title;
	}

	public String getImagePath() {
		return imagePath;
	}

	public boolean isPeriod() {
		return period;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getIcraDosyaID() {
		return icraDosyaID;
	}

	public void setIcraDosyaID(Integer icraDosyaID) {
		this.icraDosyaID = icraDosyaID;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public void setPeriod(boolean period) {
		this.period = period;
	}

}
