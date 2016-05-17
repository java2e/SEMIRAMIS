package pelops.model;

public class UyapTakipSekli {

	private int id;
	private int Kod;
	private String Aciklama;
	private String TakipYolu;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKod() {
		return Kod;
	}

	public void setKod(int kod) {
		Kod = kod;
	}

	public String getAciklama() {
		return Aciklama;
	}

	public void setAciklama(String aciklama) {
		Aciklama = aciklama;
	}

	public String getTakipYolu() {
		return TakipYolu;
	}

	public void setTakipYolu(String takipYolu) {
		TakipYolu = takipYolu;
	}

}
