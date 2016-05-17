package pelops.model;

import java.sql.Date;

public class MuameleAnalizi {

	int id;
	String icra_dosyasi_no;
	String muvekkil_adi;
	String icra_mudurlugu;
	String dosya_tipi;
	String takip_tipi;
	String borc_tipi;
	String risk_yoneticisi;
	String borclu_adi;
	double kalan;
	String kalantl;
	public String getKalantl() {
		return kalantl;
	}
	public void setKalantl(String kalantl) {
		this.kalantl = kalantl;
	}
	String ev;
	String araba;
	String sgk;
	Date gelis1, gelis2, kesin1,kesin2;
	public Date getGelis1() {
		return gelis1;
	}
	public void setGelis1(Date gelis1) {
		this.gelis1 = gelis1;
	}
	public Date getGelis2() {
		return gelis2;
	}
	public void setGelis2(Date gelis2) {
		this.gelis2 = gelis2;
	}
	public Date getKesin1() {
		return kesin1;
	}
	public void setKesin1(Date kesin1) {
		this.kesin1 = kesin1;
	}
	public Date getKesin2() {
		return kesin2;
	}
	public void setKesin2(Date kesin2) {
		this.kesin2 = kesin2;
	}
	Date gelis_tarihi;
	Date kesinlesme_tarihi;
	
	public Date getGelis_tarihi() {
		return gelis_tarihi;
	}
	public void setGelis_tarihi(Date gelis_tarihi) {
		this.gelis_tarihi = gelis_tarihi;
	}
	public Date getKesinlesme_tarihi() {
		return kesinlesme_tarihi;
	}
	public void setKesinlesme_tarihi(Date kesinlesme_tarihi) {
		this.kesinlesme_tarihi = kesinlesme_tarihi;
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
	public String getMuvekkil_adi() {
		return muvekkil_adi;
	}
	public void setMuvekkil_adi(String muvekkil_adi) {
		this.muvekkil_adi = muvekkil_adi;
	}
	public String getIcra_mudurlugu() {
		return icra_mudurlugu;
	}
	public void setIcra_mudurlugu(String icra_mudurlugu) {
		this.icra_mudurlugu = icra_mudurlugu;
	}
	public String getDosya_tipi() {
		return dosya_tipi;
	}
	public void setDosya_tipi(String dosya_tipi) {
		this.dosya_tipi = dosya_tipi;
	}
	public String getTakip_tipi() {
		return takip_tipi;
	}
	public void setTakip_tipi(String takip_tipi) {
		this.takip_tipi = takip_tipi;
	}
	public String getBorc_tipi() {
		return borc_tipi;
	}
	public void setBorc_tipi(String borc_tipi) {
		this.borc_tipi = borc_tipi;
	}
	public String getRisk_yoneticisi() {
		return risk_yoneticisi;
	}
	public void setRisk_yoneticisi(String risk_yoneticisi) {
		this.risk_yoneticisi = risk_yoneticisi;
	}
	public String getBorclu_adi() {
		return borclu_adi;
	}
	public void setBorclu_adi(String borclu_adi) {
		this.borclu_adi = borclu_adi;
	}
	public double getKalan() {
		return kalan;
	}
	public void setKalan(double kalan) {
		this.kalan = kalan;
	}
	public String getEv() {
		return ev;
	}
	public void setEv(String ev) {
		this.ev = ev;
	}
	public String getAraba() {
		return araba;
	}
	public void setAraba(String araba) {
		this.araba = araba;
	}
	public String getSgk() {
		return sgk;
	}
	public void setSgk(String sgk) {
		this.sgk = sgk;
	}
	
}
