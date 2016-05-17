package pelops.model;

public class MasrafBilgisi {

	private String icra_dosyasi_no;
	private int icra_dosyasi_id;
	private String muvekkilAdi;
	


	public String getMuvekkilAdi() {
		return muvekkilAdi;
	}

	public void setMuvekkilAdi(String muvekkilAdi) {
		this.muvekkilAdi = muvekkilAdi;
	}

	private int borcluId;
	private java.util.Date masrafTarihi;
	private Double masrafMiktari;
	private String masrafAciklama;
	private int masrafPersonel_adi_id;
	private int masrafUygulamaAsamasiId;
	private int masrafTipiId;
	private int id;
	private String masrafTipiName;
	private String masrafUygulamaAsamasiName;
	private String personelName;
	
	
	public int getIcra_dosyasi_id() {
		return icra_dosyasi_id;
	}

	public void setIcra_dosyasi_id(int icra_dosyasi_id) {
		this.icra_dosyasi_id = icra_dosyasi_id;
	}

	public String getMasrafTipiName() {
		return masrafTipiName;
	}

	public void setMasrafTipiName(String masrafTipiName) {
		this.masrafTipiName = masrafTipiName;
	}

	public String getMasrafUygulamaAsamasiName() {
		return masrafUygulamaAsamasiName;
	}

	public void setMasrafUygulamaAsamasiName(String masrafUygulamaAsamasiName) {
		this.masrafUygulamaAsamasiName = masrafUygulamaAsamasiName;
	}

	public String getPersonelName() {
		return personelName;
	}

	public void setPersonelName(String personelName) {
		this.personelName = personelName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getIcra_dosyasi_no() {
		return icra_dosyasi_no;
	}

	public void setIcra_dosyasi_no(String icra_dosyasi_no) {
		this.icra_dosyasi_no = icra_dosyasi_no;
	}

	public int getBorcluId() {
		return borcluId;
	}

	public void setBorcluId(int borcluId) {
		this.borcluId = borcluId;
	}

	public java.util.Date getMasrafTarihi() {
		return masrafTarihi;
	}

	public void setMasrafTarihi(java.util.Date masrafTarihi) {
		this.masrafTarihi = masrafTarihi;
	}

	public Double getMasrafMiktari() {
		return masrafMiktari;
	}

	public void setMasrafMiktari(Double masrafMiktari) {
		this.masrafMiktari = masrafMiktari;
	}

	public String getMasrafAciklama() {
		return masrafAciklama;
	}

	public void setMasrafAciklama(String masrafAciklama) {
		this.masrafAciklama = masrafAciklama;
	}

	public int getMasrafPersonel_adi_id() {
		return masrafPersonel_adi_id;
	}

	public void setMasrafPersonel_adi_id(int masrafPersonel_adi_id) {
		this.masrafPersonel_adi_id = masrafPersonel_adi_id;
	}

	public int getMasrafUygulamaAsamasiId() {
		return masrafUygulamaAsamasiId;
	}

	public void setMasrafUygulamaAsamasiId(int masrafUygulamaAsamasiId) {
		this.masrafUygulamaAsamasiId = masrafUygulamaAsamasiId;
	}

	public int getMasrafTipiId() {
		return masrafTipiId;
	}

	public void setMasrafTipiId(int masrafTipiId) {
		this.masrafTipiId = masrafTipiId;
	}

}
