package pelops.model;


import java.sql.Time;
import java.util.Date;

public class IcraDosyasi {
	
	private int id;
		
	String uyap_dosya_tipi,
	  KKDF,
	  BSMV,
	  BK84,
	  uyap_rol_turu,
	  uyap_rol_id ,
	  diger_alacak_aciklama ,
	  para_birimi ,
	  uyap_para_turu ;
	
	Time   ekleme_tarihi,
	  guncelleme_tarihi ;
	
	
	
	
	public String getUyap_dosya_tipi() {
		return uyap_dosya_tipi;
	}

	public void setUyap_dosya_tipi(String uyap_dosya_tipi) {
		this.uyap_dosya_tipi = uyap_dosya_tipi;
	}

	public String getKKDF() {
		return KKDF;
	}

	public void setKKDF(String kKDF) {
		KKDF = kKDF;
	}

	public String getBSMV() {
		return BSMV;
	}

	public void setBSMV(String bSMV) {
		BSMV = bSMV;
	}

	public String getBK84() {
		return BK84;
	}

	public void setBK84(String bK84) {
		BK84 = bK84;
	}

	public String getUyap_rol_turu() {
		return uyap_rol_turu;
	}

	public void setUyap_rol_turu(String uyap_rol_turu) {
		this.uyap_rol_turu = uyap_rol_turu;
	}

	public String getUyap_rol_id() {
		return uyap_rol_id;
	}

	public void setUyap_rol_id(String uyap_rol_id) {
		this.uyap_rol_id = uyap_rol_id;
	}

	public String getDiger_alacak_aciklama() {
		return diger_alacak_aciklama;
	}

	public void setDiger_alacak_aciklama(String diger_alacak_aciklama) {
		this.diger_alacak_aciklama = diger_alacak_aciklama;
	}

	public String getPara_birimi() {
		return para_birimi;
	}

	public void setPara_birimi(String para_birimi) {
		this.para_birimi = para_birimi;
	}

	public String getUyap_para_turu() {
		return uyap_para_turu;
	}

	public void setUyap_para_turu(String uyap_para_turu) {
		this.uyap_para_turu = uyap_para_turu;
	}

	public Time getEkleme_tarihi() {
		return ekleme_tarihi;
	}

	public void setEkleme_tarihi(Time ekleme_tarihi) {
		this.ekleme_tarihi = ekleme_tarihi;
	}

	public Time getGuncelleme_tarihi() {
		return guncelleme_tarihi;
	}

	public void setGuncelleme_tarihi(Time guncelleme_tarihi) {
		this.guncelleme_tarihi = guncelleme_tarihi;
	}



	
	private String banka_servis_no;
	
	private String avukat_sevis_no;
	
	public String getBanka_servis_no() {
		return banka_servis_no;
	}

	public void setBanka_servis_no(String banka_servis_no) {
		this.banka_servis_no = banka_servis_no;
	}

	public String getAvukat_sevis_no() {
		return avukat_sevis_no;
	}

	public void setAvukat_sevis_no(String avukat_sevis_no) {
		this.avukat_sevis_no = avukat_sevis_no;
	}


	private String semiramisNo;
	
	private String icraDosyaNo;
	
	private Date vadeTarihi;
	
	private Date ihtarnameTarihi;
	
	private Date gelisTarihi;
	
	private Date takipTarihi;
	
	private Date tebligTarihi;
	
	private Date bilaTarihi;
	
	private Date kesinlesmeTarihi;
	
	private Date hitamTarihi;
	
	private Date icraKapanisTarihi;
	
	private Date bankaKapanisTarihi;
	
	public Date getBankaKapanisTarihi() {
		return bankaKapanisTarihi;
	}

	public void setBankaKapanisTarihi(Date bankaKapanisTarihi) {
		this.bankaKapanisTarihi = bankaKapanisTarihi;
	}


	private Date gidisTarihi;
	
	private String itirazDurumu;
	
	private Date itirazTarihi;
	
	private String hesapTipi;
	
	private String itirazTipi;
	
	
	public String getItirazTipi() {
		return itirazTipi;
	}

	public void setItirazTipi(String itirazTipi) {
		this.itirazTipi = itirazTipi;
	}

	public String getIcraDosyaNo() {
		return icraDosyaNo;
	}

	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
	}

	public String getHesapTipi() {
		return hesapTipi;
	}

	public void setHesapTipi(String hesapTipi) {
		this.hesapTipi = hesapTipi;
	}


	public int icraMudurluguId;
	
	public int dosyaStatusuId;
	
	public int dosyaTipiId;
	
	public int takipTipiId;
	
	public int takipYoluId;
	
	public int takipSekliId;
	
	public int borcTipiId;
	
	public int riskYoneticisiId;
	
	public String borcaEsasEvrak;
	
	public String talepEdilenHak;
	
	public String vekaletUcretTipi;
	

	public String getVekaletUcretTipi() {
		return vekaletUcretTipi;
	}

	public void setVekaletUcretTipi(String vekaletUcretTipi) {
		this.vekaletUcretTipi = vekaletUcretTipi;
	}

	public String getTalepEdilenHak() {
		return talepEdilenHak;
	}

	public void setTalepEdilenHak(String talepEdilenHak) {
		this.talepEdilenHak = talepEdilenHak;
	}

	public String getBorcaEsasEvrak() {
		return borcaEsasEvrak;
	}

	public void setBorcaEsasEvrak(String borcaEsasEvrak) {
		this.borcaEsasEvrak = borcaEsasEvrak;
	}

	public int getDosyaStatusuId() {
		return dosyaStatusuId;
	}

	public void setDosyaStatusuId(int dosyaStatusuId) {
		this.dosyaStatusuId = dosyaStatusuId;
	}

	public int getDosyaTipiId() {
		return dosyaTipiId;
	}

	public void setDosyaTipiId(int dosyaTipiId) {
		this.dosyaTipiId = dosyaTipiId;
	}

	public int getTakipTipiId() {
		return takipTipiId;
	}

	public void setTakipTipiId(int takipTipiId) {
		this.takipTipiId = takipTipiId;
	}

	public int getTakipYoluId() {
		return takipYoluId;
	}

	public void setTakipYoluId(int takipYoluId) {
		this.takipYoluId = takipYoluId;
	}

	public int getTakipSekliId() {
		return takipSekliId;
	}

	public void setTakipSekliId(int takipSekliId) {
		this.takipSekliId = takipSekliId;
	}

	public int getBorcTipiId() {
		return borcTipiId;
	}

	public void setBorcTipiId(int borcTipiId) {
		this.borcTipiId = borcTipiId;
	}

	public int getRiskYoneticisiId() {
		return riskYoneticisiId;
	}

	public void setRiskYoneticisiId(int riskYoneticisiId) {
		this.riskYoneticisiId = riskYoneticisiId;
	}

	public int getIcraMudurluguId() {
		return icraMudurluguId;
	}

	public void setIcraMudurluguId(int icraMudurluguId) {
		this.icraMudurluguId = icraMudurluguId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSemiramisNo() {
		return semiramisNo;
	}

	public void setSemiramisNo(String semiramisNo) {
		this.semiramisNo = semiramisNo;
	}

	public Date getVadeTarihi() {
		return vadeTarihi;
	}

	public void setVadeTarihi(Date vadeTarihi) {
		this.vadeTarihi = vadeTarihi;
	}

	public Date getIhtarnameTarihi() {
		return ihtarnameTarihi;
	}

	public void setIhtarnameTarihi(Date ihtarnameTarihi) {
		this.ihtarnameTarihi = ihtarnameTarihi;
	}

	public Date getGelisTarihi() {
		return gelisTarihi;
	}

	public void setGelisTarihi(Date gelisTarihi) {
		this.gelisTarihi = gelisTarihi;
	}

	public Date getTakipTarihi() {
		return takipTarihi;
	}

	public void setTakipTarihi(Date takipTarihi) {
		this.takipTarihi = takipTarihi;
	}

	public Date getTebligTarihi() {
		return tebligTarihi;
	}

	public void setTebligTarihi(Date tebligTarihi) {
		this.tebligTarihi = tebligTarihi;
	}

	public Date getBilaTarihi() {
		return bilaTarihi;
	}

	public void setBilaTarihi(Date bilaTarihi) {
		this.bilaTarihi = bilaTarihi;
	}

	public Date getKesinlesmeTarihi() {
		return kesinlesmeTarihi;
	}

	public void setKesinlesmeTarihi(Date kesinlesmeTarihi) {
		this.kesinlesmeTarihi = kesinlesmeTarihi;
	}

	public Date getHitamTarihi() {
		return hitamTarihi;
	}

	public void setHitamTarihi(Date hitamTarihi) {
		this.hitamTarihi = hitamTarihi;
	}

	public Date getIcraKapanisTarihi() {
		return icraKapanisTarihi;
	}

	public void setIcraKapanisTarihi(Date icraKapanisTarihi) {
		this.icraKapanisTarihi = icraKapanisTarihi;
	}

	public Date getGidisTarihi() {
		return gidisTarihi;
	}

	public void setGidisTarihi(Date gidisTarihi) {
		this.gidisTarihi = gidisTarihi;
	}

	public String getItirazDurumu() {
		return itirazDurumu;
	}

	public void setItirazDurumu(String itirazDurumu) {
		this.itirazDurumu = itirazDurumu;
	}

	public Date getItirazTarihi() {
		return itirazTarihi;
	}

	public void setItirazTarihi(Date itirazTarihi) {
		this.itirazTarihi = itirazTarihi;
	}

	
	
	
	
	

}
