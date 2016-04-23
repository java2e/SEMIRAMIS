package pelops.report.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;

public class ReportGenel {

	private int id;
	private String icraDosyaNo;
	private String alacakliAdi;
	private String vergiNo;
	private String vergiDairesi;
	private String borclu;
	private String borcluAdres;
	private String talepYazisi;
	private String vekil;
	private ArrayList<String> asilAlacak = new ArrayList<String>();
	private ArrayList<String> temmerutFaizi = new ArrayList<String>();
	private ArrayList<String> noterMasrafi = new ArrayList<String>();
	private ArrayList<String> giderVergisi = new ArrayList<String>();
	private ArrayList<String> toplam1 = new ArrayList<String>();
	private String asilAlacak1;
	private String temmerutFaizi1;
	private String noterMasrafi1;
	private String giderVergisi1;
	private String toplam;
	private String gecikmeFaizi1;
	private String faizGider1;
	private String ek;
	private String tarih;
	private String takipYolu;
	private String icraMdHesapNo;
	private String icraBilgi;
	private String tapuBilgi;
	private String tapuSicilMd;
	private String borcluTC;
	private String borcluDogum;
	private String borcMik;
	private String user;
	private ArrayList<String> gecikmeFaizi = new ArrayList<String>();
	private ArrayList<String> faizGider = new ArrayList<String>();
	private String barkot;
	private Date gelisTarihi;
	private String alacakli;
	private BufferedImage image;
	private ArrayList<String> hesap1 = new ArrayList<String>();
	private String hesap;
	private String borcluAdi;
	private String il;
	private String konu;
	private Object brcd;
	private String muvekkilAdi;
	private int borclu_id;
	private ArrayList<String> borc_bilgi = new ArrayList<String>();
	private ArrayList<String> ekler = new ArrayList<String>();
	private String talepYaziEk;
	private ArrayList<AkbankHesap> hesapAkbank = new ArrayList<AkbankHesap>();

	public ArrayList<String> getBorc_bilgi() {
		return borc_bilgi;
	}

	public void setBorc_bilgi(ArrayList<String> borc_bilgi) {
		this.borc_bilgi = borc_bilgi;
	}

	public ArrayList<AkbankHesap> getHesapAkbank() {
		return hesapAkbank;
	}

	public void setHesapAkbank(ArrayList<AkbankHesap> hesapAkbank) {
		this.hesapAkbank = hesapAkbank;
	}

	public String getTalepYaziEk() {
		return talepYaziEk;
	}

	public void setTalepYaziEk(String talepYaziEk) {
		this.talepYaziEk = talepYaziEk;
	}

	public ArrayList<String> getEkler() {
		return ekler;
	}

	public void setEkler(ArrayList<String> ekler) {
		this.ekler = ekler;
	}

	public ArrayList<String> getBorclu_bilgi() {
		return borc_bilgi;
	}

	public void setBorclu_bilgi(ArrayList<String> borclu_bilgi) {
		this.borc_bilgi = borclu_bilgi;
	}

	public int getBorclu_id() {
		return borclu_id;
	}

	public void setBorclu_id(int borclu_id) {
		this.borclu_id = borclu_id;
	}

	public String getMuvekkilAdi() {
		return muvekkilAdi;
	}

	public void setMuvekkilAdi(String muvekkilAdi) {
		this.muvekkilAdi = muvekkilAdi;
	}

	public Object getBrcd() {
		return brcd;
	}

	public void setBrcd(Object brcd) {
		this.brcd = brcd;
	}

	public String getBorcluAdi() {
		return borcluAdi;
	}

	public void setBorcluAdi(String borcluAdi) {
		this.borcluAdi = borcluAdi;
	}

	public String getIl() {
		return il;
	}

	public void setIl(String il) {
		this.il = il;
	}

	public String getKonu() {
		return konu;
	}

	public void setKonu(String konu) {
		this.konu = konu;
	}

	private ArrayList<String> diger = new ArrayList<String>();
	private String diger1;

	public ArrayList<String> getDiger() {
		return diger;
	}

	public void setDiger(ArrayList<String> diger) {
		this.diger = diger;
	}

	public String getDiger1() {
		return diger1;
	}

	public void setDiger1(String diger1) {
		this.diger1 = diger1;
	}

	public String getHesap() {
		return hesap;
	}

	public void setHesap(String hesap) {
		this.hesap = hesap;
	}

	public ArrayList<String> getHesap1() {
		return hesap1;
	}

	public void setHesap1(ArrayList<String> hesap1) {
		this.hesap1 = hesap1;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public String getAlacakli() {
		return alacakli;
	}

	public void setAlacakli(String alacakli) {
		this.alacakli = alacakli;
	}

	public Date getGelisTarihi() {
		return gelisTarihi;
	}

	public void setGelisTarihi(Date gelisTarihi) {
		this.gelisTarihi = gelisTarihi;
	}

	public String getBarkot() {
		return barkot;
	}

	public void setBarkot(String barkot) {
		this.barkot = barkot;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIcraDosyaNo() {
		return icraDosyaNo;
	}

	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
	}

	public String getAlacakliAdi() {
		return alacakliAdi;
	}

	public void setAlacakliAdi(String alacakliAdi) {
		this.alacakliAdi = alacakliAdi;
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

	public String getBorclu() {
		return borclu;
	}

	public void setBorclu(String borclu) {
		this.borclu = borclu;
	}

	public String getBorcluAdres() {
		return borcluAdres;
	}

	public void setBorcluAdres(String borcluAdres) {
		this.borcluAdres = borcluAdres;
	}

	public String getTalepYazisi() {
		return talepYazisi;
	}

	public void setTalepYazisi(String talepYazisi) {
		this.talepYazisi = talepYazisi;
	}

	public String getVekil() {
		return vekil;
	}

	public void setVekil(String vekil) {
		this.vekil = vekil;
	}

	public ArrayList<String> getAsilAlacak() {
		return asilAlacak;
	}

	public void setAsilAlacak(ArrayList<String> asilAlacak) {
		this.asilAlacak = asilAlacak;
	}

	public ArrayList<String> getTemmerutFaizi() {
		return temmerutFaizi;
	}

	public void setTemmerutFaizi(ArrayList<String> temmerutFaizi) {
		this.temmerutFaizi = temmerutFaizi;
	}

	public ArrayList<String> getNoterMasrafi() {
		return noterMasrafi;
	}

	public void setNoterMasrafi(ArrayList<String> noterMasrafi) {
		this.noterMasrafi = noterMasrafi;
	}

	public ArrayList<String> getGiderVergisi() {
		return giderVergisi;
	}

	public void setGiderVergisi(ArrayList<String> giderVergisi) {
		this.giderVergisi = giderVergisi;
	}

	public void setGecikmeFaizi(ArrayList<String> gecikmeFaizi) {
		this.gecikmeFaizi = gecikmeFaizi;
	}

	public void setFaizGider(ArrayList<String> faizGider) {
		this.faizGider = faizGider;
	}

	public String getEk() {
		return ek;
	}

	public void setEk(String ek) {
		this.ek = ek;
	}

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

	public String getTakipYolu() {
		return takipYolu;
	}

	public void setTakipYolu(String takipYolu) {
		this.takipYolu = takipYolu;
	}

	public String getIcraMdHesapNo() {
		return icraMdHesapNo;
	}

	public void setIcraMdHesapNo(String icraMdHesapNo) {
		this.icraMdHesapNo = icraMdHesapNo;
	}

	public String getIcraBilgi() {
		return icraBilgi;
	}

	public void setIcraBilgi(String icraBilgi) {
		this.icraBilgi = icraBilgi;
	}

	public String getTapuBilgi() {
		return tapuBilgi;
	}

	public void setTapuBilgi(String tapuBilgi) {
		this.tapuBilgi = tapuBilgi;
	}

	public String getBorcluTC() {
		return borcluTC;
	}

	public void setBorcluTC(String borcluTC) {
		this.borcluTC = borcluTC;
	}

	public String getBorcluDogum() {
		return borcluDogum;
	}

	public void setBorcluDogum(String borcluDogum) {
		this.borcluDogum = borcluDogum;
	}

	public String getTapuSicilMd() {
		return tapuSicilMd;
	}

	public void setTapuSicilMd(String tapuSicilMd) {
		this.tapuSicilMd = tapuSicilMd;
	}

	public String getBorcMik() {
		return borcMik;
	}

	public void setBorcMik(String borcMik) {
		this.borcMik = borcMik;
	}

	public String getAsilAlacak1() {
		return asilAlacak1;
	}

	public void setAsilAlacak1(String asilAlacak1) {
		this.asilAlacak1 = asilAlacak1;
	}

	public String getTemmerutFaizi1() {
		return temmerutFaizi1;
	}

	public void setTemmerutFaizi1(String temmerutFaizi1) {
		this.temmerutFaizi1 = temmerutFaizi1;
	}

	public String getNoterMasrafi1() {
		return noterMasrafi1;
	}

	public void setNoterMasrafi1(String noterMasrafi1) {
		this.noterMasrafi1 = noterMasrafi1;
	}

	public String getGiderVergisi1() {
		return giderVergisi1;
	}

	public void setGiderVergisi1(String giderVergisi1) {
		this.giderVergisi1 = giderVergisi1;
	}

	public ArrayList<String> getToplam1() {
		return toplam1;
	}

	public void setToplam1(ArrayList<String> toplam1) {
		this.toplam1 = toplam1;
	}

	public String getToplam() {
		return toplam;
	}

	public void setToplam(String toplam) {
		this.toplam = toplam;
	}

	public String getGecikmeFaizi1() {
		return gecikmeFaizi1;
	}

	public void setGecikmeFaizi1(String gecikmeFaizi1) {
		this.gecikmeFaizi1 = gecikmeFaizi1;
	}

	public String getFaizGider1() {
		return faizGider1;
	}

	public void setFaizGider1(String faizGider1) {
		this.faizGider1 = faizGider1;
	}

	public ArrayList<String> getGecikmeFaizi() {
		return gecikmeFaizi;
	}

	public ArrayList<String> getFaizGider() {
		return faizGider;
	}

}
