package pelops.model;

import java.util.Date;

public class AlacakBilgisi {

	int id;
	int icra_dosyasi_id;
	int belge_tipi_id;
	String belge_tipi;
	String belge_statusu;
	String doviz_tipi;
	String doviz_kuru;
	Date tanzim_tarihi;
	Date vade_tarihi;
	Date ihtarname_tarihi;
	double belge_miktari;
	double odenen_miktar;
	int faiz_tipi_id;
	String aciklama;
	String belgeMiktariTL;
	

	public String getBelgeMiktariTL() {
		return belgeMiktariTL;
	}

	public void setBelgeMiktariTL(String belgeMiktariTL) {
		this.belgeMiktariTL = belgeMiktariTL;
	}

	public int getId() {
		return id;
	}

	public String getBelge_tipi() {
		return belge_tipi;
	}

	public void setBelge_tipi(String belge_tipi) {
		this.belge_tipi = belge_tipi;
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

	public int getBelge_tipi_id() {
		return belge_tipi_id;
	}

	public void setBelge_tipi_id(int belge_tipi_id) {
		this.belge_tipi_id = belge_tipi_id;
	}

	public String getBelge_statusu() {
		return belge_statusu;
	}

	public void setBelge_statusu(String belge_statusu) {
		this.belge_statusu = belge_statusu;
	}

	public String getDoviz_tipi() {
		return doviz_tipi;
	}

	public void setDoviz_tipi(String doviz_tipi) {
		this.doviz_tipi = doviz_tipi;
	}

	public String getDoviz_kuru() {
		return doviz_kuru;
	}

	public void setDoviz_kuru(String doviz_kuru) {
		this.doviz_kuru = doviz_kuru;
	}

	public Date getTanzim_tarihi() {
		return tanzim_tarihi;
	}

	public void setTanzim_tarihi(Date tanzim_tarihi) {
		this.tanzim_tarihi = tanzim_tarihi;
	}

	public Date getVade_tarihi() {
		return vade_tarihi;
	}

	public void setVade_tarihi(Date vade_tarihi) {
		this.vade_tarihi = vade_tarihi;
	}

	public Date getIhtarname_tarihi() {
		return ihtarname_tarihi;
	}

	public void setIhtarname_tarihi(Date ihtarname_tarihi) {
		this.ihtarname_tarihi = ihtarname_tarihi;
	}

	public double getBelge_miktari() {
		return belge_miktari;
	}

	public void setBelge_miktari(double belge_miktari) {
		this.belge_miktari = belge_miktari;
	}

	public void setBelge_miktari(int belge_miktari) {
		this.belge_miktari = belge_miktari;
	}

	public double getOdenen_miktar() {
		return odenen_miktar;
	}

	public void setOdenen_miktar(double odenen_miktar) {
		this.odenen_miktar = odenen_miktar;
	}

	public int getFaiz_tipi_id() {
		return faiz_tipi_id;
	}

	public void setFaiz_tipi_id(int faiz_tipi_id) {
		this.faiz_tipi_id = faiz_tipi_id;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

}
