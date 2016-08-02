package pelops.model;

import java.sql.Date;
import java.sql.Time;

public class BorcluBilgisi {

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int borcluId;

	private String tcNo;

	private String urunNo;

	private String adSoyad;

	private int borcluTipiId;

	private int ticariSicilNo;

	private int vergiNo;

	private int vergiDairesiId;

	private int sskIsyeriNo;

	private int servisNo;

	private String musteriNo;

	private int talimatIcraMudId;

	private String email;

	private String webAdres;

	private int ticaretSicilNo;

	private int adresTuruId;

	private String note;

	private int telefonNo;

	private String semtAdi;

	private String adres;

	private String istihbariNotlar;

	private int ilId;

	private int ilceId;

	private String telefon_no;

	private String telefon_no1;

	private String isYeriAdi;
	
	private String isYeriAdres;

	private String departman;

	private String isUnvani;

	private String cinsiyet;

	private String babAdi;

	private String anaAdi;

	private String dogumYeri;

	private Date dogumTarihi;

	private String medeniHali;

	private String mahalle;

	private String koy;

	private String verilisYeri;

	private Date verilisTarihi;

	private String vefat;

	private String resim;

	private String ilAdi;

	private String ilceAdi;

	private int ciltNo;

	private int siraNo;

	private String verilisNedeni;

	private String sskSicilNo;

	private String telefon_no2, telefon_no3;

	private String ad, soyad;

	public String getUyap_rol() {
		return uyap_rol;
	}

	public void setUyap_rol(String uyap_rol) {
		this.uyap_rol = uyap_rol;
	}

	public String getUyap_rol_id() {
		return uyap_rol_id;
	}

	public void setUyap_rol_id(String uyap_rol_id) {
		this.uyap_rol_id = uyap_rol_id;
	}

	private Time ekleme, guncelleme;

	private String uyap_rol, uyap_rol_id;

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public Time getEkleme() {
		return ekleme;
	}

	public void setEkleme(Time ekleme) {
		this.ekleme = ekleme;
	}

	public Time getGuncelleme() {
		return guncelleme;
	}

	public void setGuncelleme(Time guncelleme) {
		this.guncelleme = guncelleme;
	}

	public String getTelefon_no2() {
		return telefon_no2;
	}

	public void setTelefon_no2(String telefon_no2) {
		this.telefon_no2 = telefon_no2;
	}

	public String getTelefon_no3() {
		return telefon_no3;
	}

	public void setTelefon_no3(String telefon_no3) {
		this.telefon_no3 = telefon_no3;
	}

	public String getSskSicilNo() {
		return sskSicilNo;
	}

	public void setSskSicilNo(String sskSicilNo) {
		this.sskSicilNo = sskSicilNo;
	}

	public String getVerilisNedeni() {
		return verilisNedeni;
	}

	public void setVerilisNedeni(String verilisNedeni) {
		this.verilisNedeni = verilisNedeni;
	}

	public int getSiraNo() {
		return siraNo;
	}

	public void setSiraNo(int siraNo) {
		this.siraNo = siraNo;
	}

	public int getCiltNo() {
		return ciltNo;
	}

	public void setCiltNo(int ciltNo) {
		this.ciltNo = ciltNo;
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

	public String getIsYeriAdi() {
		return isYeriAdi;
	}

	public void setIsYeriAdi(String isYeriAdi) {
		this.isYeriAdi = isYeriAdi;
	}

	public String getDepartman() {
		return departman;
	}

	public void setDepartman(String departman) {
		this.departman = departman;
	}

	public String getIsUnvani() {
		return isUnvani;
	}

	public void setIsUnvani(String isUnvani) {
		this.isUnvani = isUnvani;
	}

	public String getCinsiyet() {
		return cinsiyet;
	}

	public void setCinsiyet(String cinsiyet) {
		this.cinsiyet = cinsiyet;
	}

	public String getBabAdi() {
		return babAdi;
	}

	public void setBabAdi(String babAdi) {
		this.babAdi = babAdi;
	}

	public String getAnaAdi() {
		return anaAdi;
	}

	public void setAnaAdi(String anaAdi) {
		this.anaAdi = anaAdi;
	}

	public String getDogumYeri() {
		return dogumYeri;
	}

	public void setDogumYeri(String dogumYeri) {
		this.dogumYeri = dogumYeri;
	}

	public Date getDogumTarihi() {
		return dogumTarihi;
	}

	public void setDogumTarihi(Date dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}

	public String getMedeniHali() {
		return medeniHali;
	}

	public void setMedeniHali(String medeniHali) {
		this.medeniHali = medeniHali;
	}

	public String getMahalle() {
		return mahalle;
	}

	public void setMahalle(String mahalle) {
		this.mahalle = mahalle;
	}

	public String getKoy() {
		return koy;
	}

	public void setKoy(String koy) {
		this.koy = koy;
	}

	public String getVerilisYeri() {
		return verilisYeri;
	}

	public void setVerilisYeri(String verilisYeri) {
		this.verilisYeri = verilisYeri;
	}

	public Date getVerilisTarihi() {
		return verilisTarihi;
	}

	public void setVerilisTarihi(Date verilisTarihi) {
		this.verilisTarihi = verilisTarihi;
	}

	public String getVefat() {
		return vefat;
	}

	public void setVefat(String vefat) {
		this.vefat = vefat;
	}

	public String getResim() {
		return resim;
	}

	public void setResim(String resim) {
		this.resim = resim;
	}

	public int getVergiDairesiId() {
		return vergiDairesiId;
	}

	public void setVergiDairesiId(int vergiDairesiId) {
		this.vergiDairesiId = vergiDairesiId;
	}

	public String getIstihbariNotlar() {
		return istihbariNotlar;
	}

	public void setIstihbariNotlar(String istihbariNotlar) {
		this.istihbariNotlar = istihbariNotlar;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getSemtAdi() {
		return semtAdi;
	}

	public void setSemtAdi(String semtAdi) {
		this.semtAdi = semtAdi;
	}

	public int getBorcluId() {
		return borcluId;
	}

	public void setBorcluId(int borcluId) {
		this.borcluId = borcluId;
	}

	public int getBorcluTipiId() {
		return borcluTipiId;
	}

	public void setBorcluTipiId(int borcluTipiId) {
		this.borcluTipiId = borcluTipiId;
	}

	public String getWebAdres() {
		return webAdres;
	}

	public void setWebAdres(String webAdres) {
		this.webAdres = webAdres;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getUrunNo() {
		return urunNo;
	}

	public void setUrunNo(String urunNo) {
		this.urunNo = urunNo;
	}

	public String getAdSoyad() {
		return adSoyad;
	}

	public void setAdSoyad(String adSoyad) {
		this.adSoyad = adSoyad;
	}

	public int getTicariSicilNo() {
		return ticariSicilNo;
	}

	public void setTicariSicilNo(int ticariSicilNo) {
		this.ticariSicilNo = ticariSicilNo;
	}

	public int getVergiNo() {
		return vergiNo;
	}

	public void setVergiNo(int vergiNo) {
		this.vergiNo = vergiNo;
	}

	public int getSskIsyeriNo() {
		return sskIsyeriNo;
	}

	public void setSskIsyeriNo(int sskIsyeriNo) {
		this.sskIsyeriNo = sskIsyeriNo;
	}

	public int getServisNo() {
		return servisNo;
	}

	public void setServisNo(int servisNo) {
		this.servisNo = servisNo;
	}

	public String getMusteriNo() {
		return musteriNo;
	}

	public void setMusteriNo(String musteriNo) {
		this.musteriNo = musteriNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTicaretSicilNo() {
		return ticaretSicilNo;
	}

	public void setTicaretSicilNo(int ticaretSicilNo) {
		this.ticaretSicilNo = ticaretSicilNo;
	}

	public int getTalimatIcraMudId() {
		return talimatIcraMudId;
	}

	public void setTalimatIcraMudId(int talimatIcraMudId) {
		this.talimatIcraMudId = talimatIcraMudId;
	}

	public int getAdresTuruId() {
		return adresTuruId;
	}

	public void setAdresTuruId(int adreTuruId) {
		this.adresTuruId = adreTuruId;
	}

	public int getTelefonNo() {
		return telefonNo;
	}

	public void setTelefonNo(int telefonNo) {
		this.telefonNo = telefonNo;
	}

	public int getIlId() {
		return ilId;
	}

	public void setIlId(int ilId) {
		this.ilId = ilId;
	}

	public int getIlceId() {
		return ilceId;
	}

	public void setIlceId(int ilceId) {
		this.ilceId = ilceId;
	}

	public String getTelefon_no() {
		return telefon_no;
	}

	public void setTelefon_no(String telefon_no) {
		this.telefon_no = telefon_no;
	}

	public String getTelefon_no1() {
		return telefon_no1;
	}

	public void setTelefon_no1(String telefon_no1) {
		this.telefon_no1 = telefon_no1;
	}

	public String getTcNo() {
		return tcNo;
	}

	public void setTcNo(String tcNo) {
		this.tcNo = tcNo;
	}

	public String getIsYeriAdres() {
		return isYeriAdres;
	}

	public void setIsYeriAdres(String isYeriAdres) {
		this.isYeriAdres = isYeriAdres;
	}
	
	

}
