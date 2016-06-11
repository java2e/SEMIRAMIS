package pelops.kasa.model;

import java.util.Date;

public class TahsilatView {

	// SELECT id, icra_dosyasi_id, muvekkil_adi, borclu_adi, gelis_tarihi,
	// borc_tipi, tahsilat_tarihi, tahsilat_tipi, tahsilat_miktari,
	// tahsilat_statusu, durum, gelis_yeri, onaylayan_id, kasa_personel_id,
	// ad_soyad, dosya_tipi, icra_dosya_no, icra_mudurlugu
	// FROM vwtahsilat;

	private int id;
	private int icraDosyaId;
	private String muvekkilAdi;
	private String borcluAdi;
	private String borcTipi;
	private Date tahsilatTarihi;
	private String tahsilatTipi;
	private Double tahsilatMiktari;
	private String tahsilatStatusu;
	private int durum;
	private String gelisYeri;
	private Date gelisTarihi;
	private int onaylayanId;
	private int kasaPersonelId;
	private String adSoyad;
	private String dosyaTipi;
	private String icraDosyaNo;
	private String icraMudurlugu;
	private String tahsilatMiktariTL;

	public String getTahsilatMiktariTL() {
		return tahsilatMiktariTL;
	}

	public void setTahsilatMiktariTL(String tahsilatMiktariTL) {
		this.tahsilatMiktariTL = tahsilatMiktariTL;
	}

	public Date getGelisTarihi() {
		return gelisTarihi;
	}

	public void setGelisTarihi(Date gelisTarihi) {
		this.gelisTarihi = gelisTarihi;
	}

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

	public String getMuvekkilAdi() {
		return muvekkilAdi;
	}

	public void setMuvekkilAdi(String muvekkilAdi) {
		this.muvekkilAdi = muvekkilAdi;
	}

	public String getBorcluAdi() {
		return borcluAdi;
	}

	public void setBorcluAdi(String borcluAdi) {
		this.borcluAdi = borcluAdi;
	}

	public String getBorcTipi() {
		return borcTipi;
	}

	public void setBorcTipi(String borcTipi) {
		this.borcTipi = borcTipi;
	}

	public Date getTahsilatTarihi() {
		return tahsilatTarihi;
	}

	public void setTahsilatTarihi(Date tahsilatTarihi) {
		this.tahsilatTarihi = tahsilatTarihi;
	}

	public String getTahsilatTipi() {
		return tahsilatTipi;
	}

	public void setTahsilatTipi(String tahsilatTipi) {
		this.tahsilatTipi = tahsilatTipi;
	}

	public Double getTahsilatMiktari() {
		return tahsilatMiktari;
	}

	public void setTahsilatMiktari(Double tahsilatMiktari) {
		this.tahsilatMiktari = tahsilatMiktari;
	}

	public String getTahsilatStatusu() {
		return tahsilatStatusu;
	}

	public void setTahsilatStatusu(String tahsilatStatusu) {
		this.tahsilatStatusu = tahsilatStatusu;
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

	public int getOnaylayanId() {
		return onaylayanId;
	}

	public void setOnaylayanId(int onaylayanId) {
		this.onaylayanId = onaylayanId;
	}

	public int getKasaPersonelId() {
		return kasaPersonelId;
	}

	public void setKasaPersonelId(int kasaPersonelId) {
		this.kasaPersonelId = kasaPersonelId;
	}

	public String getAdSoyad() {
		return adSoyad;
	}

	public void setAdSoyad(String adSoyad) {
		this.adSoyad = adSoyad;
	}

	public String getDosyaTipi() {
		return dosyaTipi;
	}

	public void setDosyaTipi(String dosyaTipi) {
		this.dosyaTipi = dosyaTipi;
	}

	public String getIcraDosyaNo() {
		return icraDosyaNo;
	}

	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
	}

	public String getIcraMudurlugu() {
		return icraMudurlugu;
	}

	public void setIcraMudurlugu(String icraMudurlugu) {
		this.icraMudurlugu = icraMudurlugu;
	}

}
