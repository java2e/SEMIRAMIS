package pelops.model;

import java.util.Date;

public class MuameleIslemleri {

	int id;
	int borclu_id;
	String borclu_adi;
	Date muamele_tarihi;
	String muzekkere_adi;
	String talep_ifadesi;
	int masraf_tipi_id;
	String masraf_tipi_adi;
	int masraf_miktari;
	int mal_tipi_id;
	String mal_tipi_adi;
	String muhatap_adi;
	String muhatap_adresi;
	Date tabligat_tarihi;
	String tebligat_sonucu;
	String muamele_statusu;
	int haciz_sirasi;
	Date haciz_baslangic_tarihi;
	int haciz_miktari;
	String maas_muvafakat;
	String aciklama;
	int personel_id;
	String kullanici_adi;
	public String getBorclu_adi() {
		return borclu_adi;
	}
	public void setBorclu_adi(String borclu_adi) {
		this.borclu_adi = borclu_adi;
	}
	public String getMasraf_tipi_adi() {
		return masraf_tipi_adi;
	}
	public void setMasraf_tipi_adi(String masraf_tipi_adi) {
		this.masraf_tipi_adi = masraf_tipi_adi;
	}
	public String getMal_tipi_adi() {
		return mal_tipi_adi;
	}
	public void setMal_tipi_adi(String mal_tipi_adi) {
		this.mal_tipi_adi = mal_tipi_adi;
	}
	public String getKullanici_adi() {
		return kullanici_adi;
	}
	public void setKullanici_adi(String kullanici_adi) {
		this.kullanici_adi = kullanici_adi;
	}
	int icra_dosyasi_id;
	String mal_bilgisi;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBorclu_id() {
		return borclu_id;
	}
	public void setBorclu_id(int borclu_id) {
		this.borclu_id = borclu_id;
	}
	public Date getMuamele_tarihi() {
		return muamele_tarihi;
	}
	public void setMuamele_tarihi(Date muamele_tarihi) {
		this.muamele_tarihi = muamele_tarihi;
	}
	public String getMuzekkere_adi() {
		return muzekkere_adi;
	}
	public void setMuzekkere_adi(String muzekkere_adi) {
		this.muzekkere_adi = muzekkere_adi;
	}
	public String getTalep_ifadesi() {
		return talep_ifadesi;
	}
	public void setTalep_ifadesi(String talep_ifadesi) {
		this.talep_ifadesi = talep_ifadesi;
	}
	public int getMasraf_tipi_id() {
		return masraf_tipi_id;
	}
	public void setMasraf_tipi_id(int masraf_tipi_id) {
		this.masraf_tipi_id = masraf_tipi_id;
	}
	public int getMasraf_miktari() {
		return masraf_miktari;
	}
	public void setMasraf_miktari(int masraf_miktari) {
		this.masraf_miktari = masraf_miktari;
	}
	public int getMal_tipi_id() {
		return mal_tipi_id;
	}
	public void setMal_tipi_id(int mal_tipi_id) {
		this.mal_tipi_id = mal_tipi_id;
	}
	public String getMuhatap_adi() {
		return muhatap_adi;
	}
	public void setMuhatap_adi(String muhatap_adi) {
		this.muhatap_adi = muhatap_adi;
	}
	public String getMuhatap_adresi() {
		return muhatap_adresi;
	}
	public void setMuhatap_adresi(String muhatap_adresi) {
		this.muhatap_adresi = muhatap_adresi;
	}
	public Date getTabligat_tarihi() {
		return tabligat_tarihi;
	}
	public void setTabligat_tarihi(Date tabligat_tarihi) {
		this.tabligat_tarihi = tabligat_tarihi;
	}
	public String getTebligat_sonucu() {
		return tebligat_sonucu;
	}
	public void setTebligat_sonucu(String tebligat_sonucu) {
		this.tebligat_sonucu = tebligat_sonucu;
	}
	public String getMuamele_statusu() {
		return muamele_statusu;
	}
	public void setMuamele_statusu(String muamele_statusu) {
		this.muamele_statusu = muamele_statusu;
	}
	
	public int getHaciz_sirasi() {
		return haciz_sirasi;
	}
	public void setHaciz_sirasi(int haciz_sirasi) {
		this.haciz_sirasi = haciz_sirasi;
	}
	public Date getHaciz_baslangic_tarihi() {
		return haciz_baslangic_tarihi;
	}
	public void setHaciz_baslangic_tarihi(Date haciz_baslangic_tarihi) {
		this.haciz_baslangic_tarihi = haciz_baslangic_tarihi;
	}
	public int getHaciz_miktari() {
		return haciz_miktari;
	}
	public void setHaciz_miktari(int haciz_miktari) {
		this.haciz_miktari = haciz_miktari;
	}
	public String getMaas_muvafakat() {
		return maas_muvafakat;
	}
	public void setMaas_muvafakat(String maas_muvafakat) {
		this.maas_muvafakat = maas_muvafakat;
	}
	public String getAciklama() {
		return aciklama;
	}
	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
	public int getPersonel_id() {
		return personel_id;
	}
	public void setPersonel_id(int personel_id) {
		this.personel_id = personel_id;
	}
	public int getIcra_dosyasi_id() {
		return icra_dosyasi_id;
	}
	public void setIcra_dosyasi_id(int icra_dosyasi_id) {
		this.icra_dosyasi_id = icra_dosyasi_id;
	}
	public String getMal_bilgisi() {
		return mal_bilgisi;
	}
	public void setMal_bilgisi(String mal_bilgisi) {
		this.mal_bilgisi = mal_bilgisi;
	}
	
	  
	  
}
