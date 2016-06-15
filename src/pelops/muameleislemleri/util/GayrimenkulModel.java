package pelops.muameleislemleri.util;

public class GayrimenkulModel {

	public GayrimenkulModel(int id, String il, String ilce, String ada, String parsel, String aciklama) {
		this.id = id;
		this.il = il;
		this.ilce = ilce;
		this.ada = ada;
		this.parsel = parsel;
		this.aciklama = aciklama;
	}

	private int id;
	private String il;
	private String ilce;
	private String ada;
	private String parsel;
	private String aciklama;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIl() {
		return il;
	}

	public void setIl(String il) {
		this.il = il;
	}

	public String getIlce() {
		return ilce;
	}

	public void setIlce(String ilce) {
		this.ilce = ilce;
	}

	public String getAda() {
		return ada;
	}

	public void setAda(String ada) {
		this.ada = ada;
	}

	public String getParsel() {
		return parsel;
	}

	public void setParsel(String parsel) {
		this.parsel = parsel;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

}
