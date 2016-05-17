package pelops.chronology.model;

import java.io.Serializable;

public class Task implements Serializable {

	private String title;
	private String imagePath;
	private boolean period;
	private int id;
	private Integer icraDosyaID;

	public Task(String title, String imagePath, boolean period) {
		this.title = title;
		this.imagePath = imagePath;
		this.period = period;
	}

	public Task(String title, String imagePath, boolean period, int ChronologyID, Integer icraDosyaID) {
		this.title = title;
		this.imagePath = imagePath;
		this.period = period;
		this.id = ChronologyID;
		this.icraDosyaID = icraDosyaID;
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
