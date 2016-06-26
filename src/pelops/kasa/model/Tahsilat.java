package pelops.kasa.model;

import java.util.Date;

public class Tahsilat {

	private int id;
	private int icra_dosyasi_id;
	private String icra_dosya_no;
	private String muvekkil_adi;
	private String borclu_adi;
	private Date gelis_tarihi;
	private String borc_tipi;
	private String dosya_tipi;
	private String icra_mudurlugu;
	private Date tahsilat_tarihi;
	private String tahsilat_tipi;
	private double tahsilat_miktari;
	private String tahsilat_statusu;
	private String tahsilat_miktari_tl;
	private int kasaPersonelID;
	private int onaylayanID;
	private int tahsilatDurum;
	private int durum;
	private String gelisYeri;
	private String tasilati_yapan;
	private String kasa_islemini_yapan;
	private String tahsilatMiktariTL;
	private String baknaServisNo;
	private String musteriNo;
	private int izleme_id;
	private int vizit_id;
	private int odemeplani_id;
	private int soz_alan_personel_id;
	private int hitam_durum;
	
	
	
	
	
	

	public int getHitam_durum() {
		return hitam_durum;
	}

	public void setHitam_durum(int hitam_durum) {
		this.hitam_durum = hitam_durum;
	}

	public int getSoz_alan_personel_id() {
		return soz_alan_personel_id;
	}

	public void setSoz_alan_personel_id(int soz_alan_personel_id) {
		this.soz_alan_personel_id = soz_alan_personel_id;
	}

	public int getIzleme_id() {
		return izleme_id;
	}

	public void setIzleme_id(int izleme_id) {
		this.izleme_id = izleme_id;
	}

	public int getVizit_id() {
		return vizit_id;
	}

	public void setVizit_id(int vizit_id) {
		this.vizit_id = vizit_id;
	}

	public int getOdemeplani_id() {
		return odemeplani_id;
	}

	public void setOdemeplani_id(int odemeplani_id) {
		this.odemeplani_id = odemeplani_id;
	}

	public String getBaknaServisNo() {
		return baknaServisNo;
	}

	public void setBaknaServisNo(String baknaServisNo) {
		this.baknaServisNo = baknaServisNo;
	}

	public String getMusteriNo() {
		return musteriNo;
	}

	public void setMusteriNo(String musteriNo) {
		this.musteriNo = musteriNo;
	}

	public String getTahsilatMiktariTL() {
		return tahsilatMiktariTL;
	}

	public void setTahsilatMiktariTL(String tahsilatMiktariTL) {
		this.tahsilatMiktariTL = tahsilatMiktariTL;
	}

	public String getTasilati_yapan() {
		return tasilati_yapan;
	}

	public void setTasilati_yapan(String tasilati_yapan) {
		this.tasilati_yapan = tasilati_yapan;
	}

	public String getKasa_islemini_yapan() {
		return kasa_islemini_yapan;
	}

	public void setKasa_islemini_yapan(String kasa_islemini_yapan) {
		this.kasa_islemini_yapan = kasa_islemini_yapan;
	}

	public int getDurum() {
		return durum;
	}

	public void setDurum(int durum) {
		this.durum = durum;
	}

	public String getGelisYeri() {
		return gelisYeri;
	}

	public void setGelisYeri(String gelisYeri) {
		this.gelisYeri = gelisYeri;
	}

	public int getTahsilatDurum() {
		return tahsilatDurum;
	}

	public void setTahsilatDurum(int tahsilatDurum) {
		this.tahsilatDurum = tahsilatDurum;
	}

	public int getKasaPersonelID() {
		return kasaPersonelID;
	}

	public void setKasaPersonelID(int kasaPersonelID) {
		this.kasaPersonelID = kasaPersonelID;
	}

	public int getOnaylayanID() {
		return onaylayanID;
	}

	public void setOnaylayanID(int onaylayanID) {
		this.onaylayanID = onaylayanID;
	}

	public String getTahsilat_miktari_tl() {
		return tahsilat_miktari_tl;
	}

	public void setTahsilat_miktari_tl(String tahsilat_miktari_tl) {
		this.tahsilat_miktari_tl = tahsilat_miktari_tl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIcra_dosyasi_id() {
		return icra_dosyasi_id;
	}

	public void setIcra_dosyasi_id(int icra_dosyasi_id) {
		this.icra_dosyasi_id = icra_dosyasi_id;
	}

	public String getIcra_dosya_no() {
		return icra_dosya_no;
	}

	public void setIcra_dosya_no(String icra_dosya_no) {
		this.icra_dosya_no = icra_dosya_no;
	}

	public String getMuvekkil_adi() {
		return muvekkil_adi;
	}

	public void setMuvekkil_adi(String muvekkil_adi) {
		this.muvekkil_adi = muvekkil_adi;
	}

	public String getBorclu_adi() {
		return borclu_adi;
	}

	public void setBorclu_adi(String borclu_adi) {
		this.borclu_adi = borclu_adi;
	}

	public Date getGelis_tarihi() {
		return gelis_tarihi;
	}

	public void setGelis_tarihi(Date gelis_tarihi) {
		this.gelis_tarihi = gelis_tarihi;
	}

	public String getBorc_tipi() {
		return borc_tipi;
	}

	public void setBorc_tipi(String borc_tipi) {
		this.borc_tipi = borc_tipi;
	}

	public String getDosya_tipi() {
		return dosya_tipi;
	}

	public void setDosya_tipi(String dosya_tipi) {
		this.dosya_tipi = dosya_tipi;
	}

	public String getIcra_mudurlugu() {
		return icra_mudurlugu;
	}

	public void setIcra_mudurlugu(String icra_mudurlugu) {
		this.icra_mudurlugu = icra_mudurlugu;
	}

	public Date getTahsilat_tarihi() {
		return tahsilat_tarihi;
	}

	public void setTahsilat_tarihi(Date tahsilat_tarihi) {
		this.tahsilat_tarihi = tahsilat_tarihi;
	}

	public String getTahsilat_tipi() {
		return tahsilat_tipi;
	}

	public void setTahsilat_tipi(String tahsilat_tipi) {
		this.tahsilat_tipi = tahsilat_tipi;
	}

	public double getTahsilat_miktari() {
		return tahsilat_miktari;
	}

	public void setTahsilat_miktari(double tahsilat_miktari) {
		this.tahsilat_miktari = tahsilat_miktari;
	}

	public String getTahsilat_statusu() {
		return tahsilat_statusu;
	}

	public void setTahsilat_statusu(String tahsilat_statusu) {
		this.tahsilat_statusu = tahsilat_statusu;
	}

}
