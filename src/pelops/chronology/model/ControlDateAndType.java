package pelops.chronology.model;

import java.util.Date;

public class ControlDateAndType { 

	private boolean ihtarname = false;
	private boolean odemeEmri = false;
	private boolean vekaletname = false;
	private boolean takipTalebi = false;
	private boolean tebligatZarfi = false;
	private boolean tebligatListesi = false;

	private Date tarih;

	public boolean isIhtarname() {
		return ihtarname;
	}

	public void setIhtarname(boolean ihtarname) {
		this.ihtarname = ihtarname;
	}

	public boolean isOdemeEmri() {
		return odemeEmri;
	}

	public void setOdemeEmri(boolean odemeEmri) {
		this.odemeEmri = odemeEmri;
	}

	public boolean isVekaletname() {
		return vekaletname;
	}

	public void setVekaletname(boolean vekaletname) {
		this.vekaletname = vekaletname;
	}

	public boolean isTakipTalebi() {
		return takipTalebi;
	}

	public void setTakipTalebi(boolean takipTalebi) {
		this.takipTalebi = takipTalebi;
	}

	public boolean isTebligatZarfi() {
		return tebligatZarfi;
	}

	public void setTebligatZarfi(boolean tebligatZarfi) {
		this.tebligatZarfi = tebligatZarfi;
	}

	public boolean isTebligatListesi() {
		return tebligatListesi;
	}

	public void setTebligatListesi(boolean tebligatListesi) {
		this.tebligatListesi = tebligatListesi;
	}

	public Date getTarih() {
		return tarih;
	}

	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}

}
