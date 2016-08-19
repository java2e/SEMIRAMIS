package semiramis.operasyon.model;

import java.util.Date;

public class HaczeEsasMalBilgisi {

	private int id;
	private int borcluId;
	private String menkulBilgisi;
	private String ilAdi;
	private String ilceAdi;
	
	private int tapuIlId;
	private int tapuIlceId;
	private String tapuMahalleAdi;
	
	private int mulkTipiId;
	
	private String tapuMulkTipi;
	private String tapuParsel;
	private String tapuSayfaNo;
	private String tapuCiltNo;
	private String tapuAciklama;
	private String tapuSicilMudurluk;
	
	private String aracPlakaNo;
	
	private int aracTipiId;
	private String aracAracTipi;
	private String bankaHesapNo;
	private String muhatapAdi;
	private String muhatapAdresi;
	private String digerBilgiler;
	private int malTutari;
	private int icraDosyaId;
	
	private int malTipiId;
	private String malTipi;
	
	
	private String mevduatBilgisi;
	private Date eklemeTarihi;
	private Date guncellemeTarihi;
	private int guncelleyenKullanici;
	private String guncelleyenKullaniciAdSoyad;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBorcluId() {
		return borcluId;
	}

	public void setBorcluId(int borcluId) {
		this.borcluId = borcluId;
	}

	public String getMenkulBilgisi() {
		return menkulBilgisi;
	}

	public void setMenkulBilgisi(String menkulBilgisi) {
		this.menkulBilgisi = menkulBilgisi;
	}

	public String getIlAdi() {
		return ilAdi;
	}

	public void setIlAdi(String ilAdi) {
		this.ilAdi = ilAdi;
	}

	public String getIlceAdi() {
		return ilceAdi;
	}

	public void setIlceAdi(String ilceAdi) {
		this.ilceAdi = ilceAdi;
	}

	public String getTapuMahalleAdi() {
		return tapuMahalleAdi;
	}

	public void setTapuMahalleAdi(String tapuMahalleAdi) {
		this.tapuMahalleAdi = tapuMahalleAdi;
	}

	public String getTapuMulkTipi() {
		return tapuMulkTipi;
	}

	public void setTapuMulkTipi(String tapuMulkTipi) {
		this.tapuMulkTipi = tapuMulkTipi;
	}

	public String getTapuParsel() {
		return tapuParsel;
	}

	public void setTapuParsel(String tapuParsel) {
		this.tapuParsel = tapuParsel;
	}

	public String getTapuSayfaNo() {
		return tapuSayfaNo;
	}

	public void setTapuSayfaNo(String tapuSayfaNo) {
		this.tapuSayfaNo = tapuSayfaNo;
	}

	public String getTapuCiltNo() {
		return tapuCiltNo;
	}

	public void setTapuCiltNo(String tapuCiltNo) {
		this.tapuCiltNo = tapuCiltNo;
	}

	public String getAracPlakaNo() {
		return aracPlakaNo;
	}

	public void setAracPlakaNo(String aracPlakaNo) {
		this.aracPlakaNo = aracPlakaNo;
	}

	public String getAracAracTipi() {
		return aracAracTipi;
	}

	public void setAracAracTipi(String aracAracTipi) {
		this.aracAracTipi = aracAracTipi;
	}

	public String getBankaHesapNo() {
		return bankaHesapNo;
	}

	public void setBankaHesapNo(String bankaHesapNo) {
		this.bankaHesapNo = bankaHesapNo;
	}

	public String getMuhatapAdi() {
		return muhatapAdi;
	}

	public void setMuhatapAdi(String muhatapAdi) {
		this.muhatapAdi = muhatapAdi;
	}

	public String getMuhatapAdresi() {
		return muhatapAdresi;
	}

	public void setMuhatapAdresi(String muhatapAdresi) {
		this.muhatapAdresi = muhatapAdresi;
	}

	public String getDigerBilgiler() {
		return digerBilgiler;
	}

	public void setDigerBilgiler(String digerBilgiler) {
		this.digerBilgiler = digerBilgiler;
	}

	public int getMalTutari() {
		return malTutari;
	}

	public void setMalTutari(int malTutari) {
		this.malTutari = malTutari;
	}

	public int getIcraDosyaId() {
		return icraDosyaId;
	}

	public void setIcraDosyaId(int icraDosyaId) {
		this.icraDosyaId = icraDosyaId;
	}

	public String getMalTipi() {
		return malTipi;
	}

	public void setMalTipi(String malTipi) {
		this.malTipi = malTipi;
	}

	public String getMevduatBilgisi() {
		return mevduatBilgisi;
	}

	public void setMevduatBilgisi(String mevduatBilgisi) {
		this.mevduatBilgisi = mevduatBilgisi;
	}

	public Date getEklemeTarihi() {
		return eklemeTarihi;
	}

	public void setEklemeTarihi(Date eklemeTarihi) {
		this.eklemeTarihi = eklemeTarihi;
	}

	public Date getGuncellemeTarihi() {
		return guncellemeTarihi;
	}

	public void setGuncellemeTarihi(Date guncellemeTarihi) {
		this.guncellemeTarihi = guncellemeTarihi;
	}

	public int getGuncelleyenKullanici() {
		return guncelleyenKullanici;
	}

	public void setGuncelleyenKullanici(int guncelleyenKullanici) {
		this.guncelleyenKullanici = guncelleyenKullanici;
	}
	
	

	public String getGuncelleyenKullaniciAdSoyad() {
		return guncelleyenKullaniciAdSoyad;
	}

	public void setGuncelleyenKullaniciAdSoyad(String guncelleyenKullaniciAdSoyad) {
		this.guncelleyenKullaniciAdSoyad = guncelleyenKullaniciAdSoyad;
	}

	public int getTapuIlId() {
		return tapuIlId;
	}

	public void setTapuIlId(int tapuIlId) {
		this.tapuIlId = tapuIlId;
	}

	public int getTapuIlceId() {
		return tapuIlceId;
	}

	public void setTapuIlceId(int tapuIlceId) {
		this.tapuIlceId = tapuIlceId;
	}

	public int getMulkTipiId() {
		return mulkTipiId;
	}

	public void setMulkTipiId(int mulkTipiId) {
		this.mulkTipiId = mulkTipiId;
	}

	public int getAracTipiId() {
		return aracTipiId;
	}

	public void setAracTipiId(int aracTipiId) {
		this.aracTipiId = aracTipiId;
	}

	public int getMalTipiId() {
		return malTipiId;
	}

	public void setMalTipiId(int malTipiId) {
		this.malTipiId = malTipiId;
	}

	public String getTapuAciklama() {
		return tapuAciklama;
	}

	public void setTapuAciklama(String tapuAciklama) {
		this.tapuAciklama = tapuAciklama;
	}

	public String getTapuSicilMudurluk() {
		return tapuSicilMudurluk;
	}

	public void setTapuSicilMudurluk(String tapuSicilMudurluk) {
		this.tapuSicilMudurluk = tapuSicilMudurluk;
	}

	
	
	
	
	
	
	
	
	

}
