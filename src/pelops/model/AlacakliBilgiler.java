package pelops.model;

public class AlacakliBilgiler {
	
	private int id;
	private int icraDosyaId;
	private String IcraDosyasiNo;
	
	
	public String getIcraDosyasiNo() {
		return IcraDosyasiNo;
	}

	public void setIcraDosyasiNo(String icraDosyasiNo) {
		IcraDosyasiNo = icraDosyasiNo;
	}

	private int subeTipiId;

	private int muvekkilTipiId;

	private String muvekkilAdi;

	private String muvekkilSubeAdi;

	private String ticaretSicilNo;

	private String musteriNo;

	private String vergiNo;

	private String vergiDairesi;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIcraDosyaId() {
		return icraDosyaId;
	}

	public void setIcraDosyaId(int icraDosyaId) {
		this.icraDosyaId = icraDosyaId;
	}

	public int getSubeTipiId() {
		return subeTipiId;
	}

	public void setSubeTipiId(int subeTipiId) {
		this.subeTipiId = subeTipiId;
	}

	public int getMuvekkilTipiId() {
		return muvekkilTipiId;
	}

	public void setMuvekkilTipiId(int muvekkilTipiId) {
		this.muvekkilTipiId = muvekkilTipiId;
	}

	public String getMuvekkilAdi() {
		return muvekkilAdi;
	}

	public void setMuvekkilAdi(String muvekkilAdi) {
		this.muvekkilAdi = muvekkilAdi;
	}

	public String getMuvekkilSubeAdi() {
		return muvekkilSubeAdi;
	}

	public void setMuvekkilSubeAdi(String muvekkilSubeAdi) {
		this.muvekkilSubeAdi = muvekkilSubeAdi;
	}

	public String getTicaretSicilNo() {
		return ticaretSicilNo;
	}

	public void setTicaretSicilNo(String ticaretSicilNo) {
		this.ticaretSicilNo = ticaretSicilNo;
	}

	public String getMusteriNo() {
		return musteriNo;
	}

	public void setMusteriNo(String musteriNo) {
		this.musteriNo = musteriNo;
	}

	public String getVergiNo() {
		return vergiNo;
	}

	public void setVergiNo(String vergiNo) {
		this.vergiNo = vergiNo;
	}

	public String getVergiDairesi() {
		return vergiDairesi;
	}

	public void setVergiDairesi(String vergiDairesi) {
		this.vergiDairesi = vergiDairesi;
	}

	public String getePosta() {
		return ePosta;
	}

	public void setePosta(String ePosta) {
		this.ePosta = ePosta;
	}

	public String getWebAdres() {
		return webAdres;
	}

	public void setWebAdres(String webAdres) {
		this.webAdres = webAdres;
	}

	public int getAdresTuruId() {
		return adresTuruId;
	}

	public void setAdresTuruId(int adresTuruId) {
		this.adresTuruId = adresTuruId;
	}

	public int getTelefonTipiId() {
		return telefonTipiId;
	}

	public void setTelefonTipiId(int telefonTipiId) {
		this.telefonTipiId = telefonTipiId;
	}

	public int getIlId() {
		return ilId;
	}

	public void setIlId(int ilId) {
		this.ilId = ilId;
	}

	public String getTelefonNo() {
		return telefonNo;
	}

	public void setTelefonNo(String telefonNo) {
		this.telefonNo = telefonNo;
	}

	public int getIlceId() {
		return ilceId;
	}

	public void setIlceId(int ilceId) {
		this.ilceId = ilceId;
	}

	public String getTelefonStatusu() {
		return telefonStatusu;
	}

	public void setTelefonStatusu(String telefonStatusu) {
		this.telefonStatusu = telefonStatusu;
	}

	public String getSemtAdi() {
		return semtAdi;
	}

	public void setSemtAdi(String semtAdi) {
		this.semtAdi = semtAdi;
	}



	public int getAdresStatusuId() {
		return AdresStatusuId;
	}

	public void setAdresStatusuId(int adresStatusuId) {
		AdresStatusuId = adresStatusuId;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getNotlar() {
		return notlar;
	}

	public void setNotlar(String notlar) {
		this.notlar = notlar;
	}

	private String ePosta;

	private String webAdres;

	private int adresTuruId;

	private int telefonTipiId;

	private int ilId;

	private String telefonNo;

	private int ilceId;

	private String telefonStatusu;

	private String semtAdi;

	private int AdresStatusuId;
	
	@SuppressWarnings("unused")
	private String AdresStatusu;



	private String adres;

	private String notlar;

}
