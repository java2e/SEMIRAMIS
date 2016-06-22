package pelops.model;

public class Hesap {

	private int id, icra_dosyasi;

	private double asil_alacak, gecikme_faizi, temerrut_faizi, faiz_gider_vergisi, faiz_gider_vergisi2, noter_masrafi,
			takip_alacagi, vekalet_ucreti, takip_faizi, indirim_faiz_orani, takip_faiz_gider_vergi, diger_harclar,
			masraf_tutari, tahsil_harci, toplam_alacak, tahsilat_tutari, indirim_tutari, akdi_faiz_orani,
			temerrut_faiz_orani, kalan_alacak, harcoranTL, basvuruHarciTL, vekaletHarciTL, pesinHarcTL

	;

	String alacak_kalemi2_kalem_kod_turu, alacak_kalemi2_kalem_kod_aciklama, alacak_kalemi2_alacak_kalem_adi,
			alacak_kalemi3_kalem_kod_turu, alacak_kalemi3_kalem_kod, alacak_kalemi3_kalem_kod_aciklama,
			alacak_kalemi3_alacak_kalem_adi, alacak_kalemi4_kalem_kod_turu, alacak_kalemi4_kalem_kod_aciklama,
			alacak_kalemi4_alacak_kalem_adi, alacak_kalemi4_kalem_kod, alacak_kalemi5_kalem_kod_turu,
			alacak_kalemi5_kalem_kod_aciklama, alacak_kalemi5_alacak_kalem_adi, alacak_kalemi5_kalem_kod;

	String UrunAdi, UrunNo;
	
	
	
	
	public String getUrunAdi() {
		return UrunAdi;
	}

	public void setUrunAdi(String urunAdi) {
		UrunAdi = urunAdi;
	}

	public String getUrunNo() {
		return UrunNo;
	}

	public void setUrunNo(String urunNo) {
		UrunNo = urunNo;
	}

	public String getAlacak_kalemi2_kalem_kod_turu() {
		return alacak_kalemi2_kalem_kod_turu;
	}

	public void setAlacak_kalemi2_kalem_kod_turu(String alacak_kalemi2_kalem_kod_turu) {
		this.alacak_kalemi2_kalem_kod_turu = alacak_kalemi2_kalem_kod_turu;
	}

	public String getAlacak_kalemi2_kalem_kod_aciklama() {
		return alacak_kalemi2_kalem_kod_aciklama;
	}

	public void setAlacak_kalemi2_kalem_kod_aciklama(String alacak_kalemi2_kalem_kod_aciklama) {
		this.alacak_kalemi2_kalem_kod_aciklama = alacak_kalemi2_kalem_kod_aciklama;
	}

	public String getAlacak_kalemi2_alacak_kalem_adi() {
		return alacak_kalemi2_alacak_kalem_adi;
	}

	public void setAlacak_kalemi2_alacak_kalem_adi(String alacak_kalemi2_alacak_kalem_adi) {
		this.alacak_kalemi2_alacak_kalem_adi = alacak_kalemi2_alacak_kalem_adi;
	}

	public String getAlacak_kalemi3_kalem_kod_turu() {
		return alacak_kalemi3_kalem_kod_turu;
	}

	public void setAlacak_kalemi3_kalem_kod_turu(String alacak_kalemi3_kalem_kod_turu) {
		this.alacak_kalemi3_kalem_kod_turu = alacak_kalemi3_kalem_kod_turu;
	}

	public String getAlacak_kalemi3_kalem_kod() {
		return alacak_kalemi3_kalem_kod;
	}

	public void setAlacak_kalemi3_kalem_kod(String alacak_kalemi3_kalem_kod) {
		this.alacak_kalemi3_kalem_kod = alacak_kalemi3_kalem_kod;
	}

	public String getAlacak_kalemi3_kalem_kod_aciklama() {
		return alacak_kalemi3_kalem_kod_aciklama;
	}

	public void setAlacak_kalemi3_kalem_kod_aciklama(String alacak_kalemi3_kalem_kod_aciklama) {
		this.alacak_kalemi3_kalem_kod_aciklama = alacak_kalemi3_kalem_kod_aciklama;
	}

	public String getAlacak_kalemi3_alacak_kalem_adi() {
		return alacak_kalemi3_alacak_kalem_adi;
	}

	public void setAlacak_kalemi3_alacak_kalem_adi(String alacak_kalemi3_alacak_kalem_adi) {
		this.alacak_kalemi3_alacak_kalem_adi = alacak_kalemi3_alacak_kalem_adi;
	}

	public String getAlacak_kalemi4_kalem_kod_turu() {
		return alacak_kalemi4_kalem_kod_turu;
	}

	public void setAlacak_kalemi4_kalem_kod_turu(String alacak_kalemi4_kalem_kod_turu) {
		this.alacak_kalemi4_kalem_kod_turu = alacak_kalemi4_kalem_kod_turu;
	}

	public String getAlacak_kalemi4_kalem_kod_aciklama() {
		return alacak_kalemi4_kalem_kod_aciklama;
	}

	public void setAlacak_kalemi4_kalem_kod_aciklama(String alacak_kalemi4_kalem_kod_aciklama) {
		this.alacak_kalemi4_kalem_kod_aciklama = alacak_kalemi4_kalem_kod_aciklama;
	}

	public String getAlacak_kalemi4_alacak_kalem_adi() {
		return alacak_kalemi4_alacak_kalem_adi;
	}

	public void setAlacak_kalemi4_alacak_kalem_adi(String alacak_kalemi4_alacak_kalem_adi) {
		this.alacak_kalemi4_alacak_kalem_adi = alacak_kalemi4_alacak_kalem_adi;
	}

	public String getAlacak_kalemi4_kalem_kod() {
		return alacak_kalemi4_kalem_kod;
	}

	public void setAlacak_kalemi4_kalem_kod(String alacak_kalemi4_kalem_kod) {
		this.alacak_kalemi4_kalem_kod = alacak_kalemi4_kalem_kod;
	}

	public String getAlacak_kalemi5_kalem_kod_turu() {
		return alacak_kalemi5_kalem_kod_turu;
	}

	public void setAlacak_kalemi5_kalem_kod_turu(String alacak_kalemi5_kalem_kod_turu) {
		this.alacak_kalemi5_kalem_kod_turu = alacak_kalemi5_kalem_kod_turu;
	}

	public String getAlacak_kalemi5_kalem_kod_aciklama() {
		return alacak_kalemi5_kalem_kod_aciklama;
	}

	public void setAlacak_kalemi5_kalem_kod_aciklama(String alacak_kalemi5_kalem_kod_aciklama) {
		this.alacak_kalemi5_kalem_kod_aciklama = alacak_kalemi5_kalem_kod_aciklama;
	}

	public String getAlacak_kalemi5_alacak_kalem_adi() {
		return alacak_kalemi5_alacak_kalem_adi;
	}

	public void setAlacak_kalemi5_alacak_kalem_adi(String alacak_kalemi5_alacak_kalem_adi) {
		this.alacak_kalemi5_alacak_kalem_adi = alacak_kalemi5_alacak_kalem_adi;
	}

	public String getAlacak_kalemi5_kalem_kod() {
		return alacak_kalemi5_kalem_kod;
	}

	public void setAlacak_kalemi5_kalem_kod(String alacak_kalemi5_kalem_kod) {
		this.alacak_kalemi5_kalem_kod = alacak_kalemi5_kalem_kod;
	}

	public double getAkdi_faiz_orani() {
		return akdi_faiz_orani;
	}

	public void setAkdi_faiz_orani(double akdi_faiz_orani) {
		this.akdi_faiz_orani = akdi_faiz_orani;
	}

	public double getTemerrut_faiz_orani() {
		return temerrut_faiz_orani;
	}

	public void setTemerrut_faiz_orani(double temerrut_faiz_orani) {
		this.temerrut_faiz_orani = temerrut_faiz_orani;
	}

	private String asil_alacak_tl, gecikme_faizi_tl, temerrut_faizi_tl, faiz_gider_vergisi_tl, faiz_gider_vergisi2_tl,
			noter_masrafi_tl, takip_alacagi_tl, vekalet_ucreti_tl, takip_faizi_tl, indirim_faiz_orani_tl,
			takip_faiz_gider_vergi_tl, diger_harclar_tl, masraf_tutari_tl, tahsil_harci_tl, toplam_alacak_tl,
			tahsilat_tutari_tl, indirim_tutari_tl, kalan_alacak_tl;

	public String getAsil_alacak_tl() {
		return asil_alacak_tl;
	}

	public void setAsil_alacak_tl(String asil_alacak_tl) {
		this.asil_alacak_tl = asil_alacak_tl;
	}

	public String getGecikme_faizi_tl() {
		return gecikme_faizi_tl;
	}

	public void setGecikme_faizi_tl(String gecikme_faizi_tl) {
		this.gecikme_faizi_tl = gecikme_faizi_tl;
	}

	public String getTemerrut_faizi_tl() {
		return temerrut_faizi_tl;
	}

	public void setTemerrut_faizi_tl(String temerrut_faizi_tl) {
		this.temerrut_faizi_tl = temerrut_faizi_tl;
	}

	public String getFaiz_gider_vergisi_tl() {
		return faiz_gider_vergisi_tl;
	}

	public void setFaiz_gider_vergisi_tl(String faiz_gider_vergisi_tl) {
		this.faiz_gider_vergisi_tl = faiz_gider_vergisi_tl;
	}

	public String getFaiz_gider_vergisi2_tl() {
		return faiz_gider_vergisi2_tl;
	}

	public void setFaiz_gider_vergisi2_tl(String faiz_gider_vergisi2_tl) {
		this.faiz_gider_vergisi2_tl = faiz_gider_vergisi2_tl;
	}

	public String getNoter_masrafi_tl() {
		return noter_masrafi_tl;
	}

	public void setNoter_masrafi_tl(String noter_masrafi_tl) {
		this.noter_masrafi_tl = noter_masrafi_tl;
	}

	public String getTakip_alacagi_tl() {
		return takip_alacagi_tl;
	}

	public void setTakip_alacagi_tl(String takip_alacagi_tl) {
		this.takip_alacagi_tl = takip_alacagi_tl;
	}

	public String getVekalet_ucreti_tl() {
		return vekalet_ucreti_tl;
	}

	public void setVekalet_ucreti_tl(String vekalet_ucreti_tl) {
		this.vekalet_ucreti_tl = vekalet_ucreti_tl;
	}

	public String getTakip_faizi_tl() {
		return takip_faizi_tl;
	}

	public void setTakip_faizi_tl(String takip_faizi_tl) {
		this.takip_faizi_tl = takip_faizi_tl;
	}

	public String getIndirim_faiz_orani_tl() {
		return indirim_faiz_orani_tl;
	}

	public void setIndirim_faiz_orani_tl(String indirim_faiz_orani_tl) {
		this.indirim_faiz_orani_tl = indirim_faiz_orani_tl;
	}

	public String getTakip_faiz_gider_vergi_tl() {
		return takip_faiz_gider_vergi_tl;
	}

	public void setTakip_faiz_gider_vergi_tl(String takip_faiz_gider_vergi_tl) {
		this.takip_faiz_gider_vergi_tl = takip_faiz_gider_vergi_tl;
	}

	public String getDiger_harclar_tl() {
		return diger_harclar_tl;
	}

	public void setDiger_harclar_tl(String diger_harclar_tl) {
		this.diger_harclar_tl = diger_harclar_tl;
	}

	public String getMasraf_tutari_tl() {
		return masraf_tutari_tl;
	}

	public void setMasraf_tutari_tl(String masraf_tutari_tl) {
		this.masraf_tutari_tl = masraf_tutari_tl;
	}

	public String getTahsil_harci_tl() {
		return tahsil_harci_tl;
	}

	public void setTahsil_harci_tl(String tahsil_harci_tl) {
		this.tahsil_harci_tl = tahsil_harci_tl;
	}

	public String getToplam_alacak_tl() {
		return toplam_alacak_tl;
	}

	public void setToplam_alacak_tl(String toplam_alacak_tl) {
		this.toplam_alacak_tl = toplam_alacak_tl;
	}

	public String getTahsilat_tutari_tl() {
		return tahsilat_tutari_tl;
	}

	public void setTahsilat_tutari_tl(String tahsilat_tutari_tl) {
		this.tahsilat_tutari_tl = tahsilat_tutari_tl;
	}

	public String getIndirim_tutari_tl() {
		return indirim_tutari_tl;
	}

	public void setIndirim_tutari_tl(String indirim_tutari_tl) {
		this.indirim_tutari_tl = indirim_tutari_tl;
	}

	public String getKalan_alacak_tl() {
		return kalan_alacak_tl;
	}

	public void setKalan_alacak_tl(String kalan_alacak_tl) {
		this.kalan_alacak_tl = kalan_alacak_tl;
	}

	public double getFaiz_gider_vergisi2() {
		return faiz_gider_vergisi2;
	}

	public void setFaiz_gider_vergisi2(double faiz_gider_vergisi2) {
		this.faiz_gider_vergisi2 = faiz_gider_vergisi2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIcra_dosyasi() {
		return icra_dosyasi;
	}

	public void setIcra_dosyasi(int icra_dosyasi) {
		this.icra_dosyasi = icra_dosyasi;
	}

	public double getAsil_alacak() {
		return asil_alacak;
	}

	public void setAsil_alacak(double asil_alacak) {
		this.asil_alacak = asil_alacak;
	}

	public double getGecikme_faizi() {
		return gecikme_faizi;
	}

	public void setGecikme_faizi(double gecikme_faizi) {
		this.gecikme_faizi = gecikme_faizi;
	}

	public double getTemerrut_faizi() {
		return temerrut_faizi;
	}

	public void setTemerrut_faizi(double temerrut_faizi) {
		this.temerrut_faizi = temerrut_faizi;
	}

	public double getFaiz_gider_vergisi() {
		return faiz_gider_vergisi;
	}

	public void setFaiz_gider_vergisi(double faiz_gider_vergisi) {
		this.faiz_gider_vergisi = faiz_gider_vergisi;
	}

	public double getNoter_masrafi() {
		return noter_masrafi;
	}

	public void setNoter_masrafi(double noter_masrafi) {
		this.noter_masrafi = noter_masrafi;
	}

	public double getTakip_alacagi() {
		return takip_alacagi;
	}

	public void setTakip_alacagi(double takip_alacagi) {
		this.takip_alacagi = takip_alacagi;
	}

	public double getVekalet_ucreti() {
		return vekalet_ucreti;
	}

	public void setVekalet_ucreti(double vekalet_ucreti) {
		this.vekalet_ucreti = vekalet_ucreti;
	}

	public double getTakip_faizi() {
		return takip_faizi;
	}

	public void setTakip_faizi(double takip_faizi) {
		this.takip_faizi = takip_faizi;
	}

	public double getIndirim_faiz_orani() {
		return indirim_faiz_orani;
	}

	public void setIndirim_faiz_orani(double indirim_faiz_orani) {
		this.indirim_faiz_orani = indirim_faiz_orani;
	}

	public double getTakip_faiz_gider_vergi() {
		return takip_faiz_gider_vergi;
	}

	public void setTakip_faiz_gider_vergi(double takip_faiz_gider_vergi) {
		this.takip_faiz_gider_vergi = takip_faiz_gider_vergi;
	}

	public double getDiger_harclar() {
		return diger_harclar;
	}

	public void setDiger_harclar(double diger_harclar) {
		this.diger_harclar = diger_harclar;
	}

	public double getMasraf_tutari() {
		return masraf_tutari;
	}

	public void setMasraf_tutari(double masraf_tutari) {
		this.masraf_tutari = masraf_tutari;
	}

	public double getTahsil_harci() {
		return tahsil_harci;
	}

	public void setTahsil_harci(double tahsil_harci) {
		this.tahsil_harci = tahsil_harci;
	}

	public double getToplam_alacak() {
		return toplam_alacak;
	}

	public void setToplam_alacak(double toplam_alacak) {
		this.toplam_alacak = toplam_alacak;
	}

	public double getTahsilat_tutari() {
		return tahsilat_tutari;
	}

	public void setTahsilat_tutari(double tahsilat_tutari) {
		this.tahsilat_tutari = tahsilat_tutari;
	}

	public double getIndirim_tutari() {
		return indirim_tutari;
	}

	public void setIndirim_tutari(double indirim_tutari) {
		this.indirim_tutari = indirim_tutari;
	}

	public double getKalan_alacak() {
		return kalan_alacak;
	}

	public void setKalan_alacak(double kalan_alacak) {
		this.kalan_alacak = kalan_alacak;
	}

	public double getHarcoranTL() {
		return harcoranTL;
	}

	public void setHarcoranTL(double harcoranTL) {
		this.harcoranTL = harcoranTL;
	}

	public double getBasvuruHarciTL() {
		return basvuruHarciTL;
	}

	public void setBasvuruHarciTL(double basvuruHarciTL) {
		this.basvuruHarciTL = basvuruHarciTL;
	}

	public double getVekaletHarciTL() {
		return vekaletHarciTL;
	}

	public void setVekaletHarciTL(double vekaletHarciTL) {
		this.vekaletHarciTL = vekaletHarciTL;
	}

	public double getPesinHarcTL() {
		return pesinHarcTL;
	}

	public void setPesinHarcTL(double pesinHarcTL) {
		this.pesinHarcTL = pesinHarcTL;
	}

}
