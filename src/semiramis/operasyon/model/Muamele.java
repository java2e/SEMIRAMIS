package semiramis.operasyon.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Muamele {
	
	
	private int id;
	private Object barkod;
	private String barkodTxt;
	
	private int borcluId;
	private String borcluAdSoyad;
	private String borcluTcNo;
	private String borcluDogumTarihi;
	private String borcluAdres;
	private String borcluIsyeriAdi;
	private String borcluIsyeriAdres;
	
	
	private int icraDosyaID;
	private String icraDosyaNo;
	private int icraMudurluguID;
	private String icraMudurlugu;
	
	private int muvekkilId;
	private String muvekkilAdi;
	
	private String avukatAdi;
	private String avukatAdres;
	private String avukatIBAN;
	private int avukatId;
	
	
	private String pttAdi;
	
	private double asilAlacak;
	private double takipAlacak;
	private double toplamAlacak;
	private double tahsilatMiktari;
	private String urunAdi;
	private String urunNo;
	private float masrafTutari;
	
	private double borcMiktari;
	private String borcMiktariTxt;
	
	private int muzekkereId;
	private String muzekkereAdi;
	private String talepAdi;
	private String muzekkereTalepAdi;
	private int muzekkereTalepMiktari;
	private int muameleStatusu;
	private int tebligatSonucu;
	
	private Date mumaleTarihi;
	private String muameleTarihiTxt;
	
	private int personelId;
	private String personelAdSoyad;
	
	private HashMap<String, List<TapuBilgisi>> tapuKayitlar;
	
	private String haczeEsasMalId;
	
	private String tapuAciklama="";
	
	
	
	
	
	
	

	public String getTapuAciklama() {
		return tapuAciklama;
	}

	public void setTapuAciklama(String tapuAciklama) {
		this.tapuAciklama = tapuAciklama;
	}
	
	public String getHaczeEsasMalId() {
		return haczeEsasMalId;
	}

	public void setHaczeEsasMalId(String haczeEsasMalId) {
		this.haczeEsasMalId = haczeEsasMalId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Object getBarkod() {
		return barkod;
	}

	public void setBarkod(Object barkod) {
		this.barkod = barkod;
	}

	public String getBarkodTxt() {
		return barkodTxt;
	}

	public void setBarkodTxt(String barkodTxt) {
		this.barkodTxt = barkodTxt;
	}

	public int getBorcluId() {
		return borcluId;
	}

	public void setBorcluId(int borcluId) {
		this.borcluId = borcluId;
	}

	public String getBorcluAdSoyad() {
		return borcluAdSoyad;
	}

	public void setBorcluAdSoyad(String borcluAdSoyad) {
		this.borcluAdSoyad = borcluAdSoyad;
	}

	public String getBorcluTcNo() {
		return borcluTcNo;
	}

	public void setBorcluTcNo(String borcluTcNo) {
		this.borcluTcNo = borcluTcNo;
	}

	public String getBorcluDogumTarihi() {
		return borcluDogumTarihi;
	}

	public void setBorcluDogumTarihi(String borcluDogumTarihi) {
		this.borcluDogumTarihi = borcluDogumTarihi;
	}

	public String getBorcluAdres() {
		return borcluAdres;
	}

	public void setBorcluAdres(String borcluAdres) {
		this.borcluAdres = borcluAdres;
	}

	public String getBorcluIsyeriAdi() {
		return borcluIsyeriAdi;
	}

	public void setBorcluIsyeriAdi(String borcluIsyeriAdi) {
		this.borcluIsyeriAdi = borcluIsyeriAdi;
	}

	public String getBorcluIsyeriAdres() {
		return borcluIsyeriAdres;
	}

	public void setBorcluIsyeriAdres(String borcluIsyeriAdres) {
		this.borcluIsyeriAdres = borcluIsyeriAdres;
	}

	public int getIcraDosyaID() {
		return icraDosyaID;
	}

	public void setIcraDosyaID(int icraDosyaID) {
		this.icraDosyaID = icraDosyaID;
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

	public String getMuvekkilAdi() {
		return muvekkilAdi;
	}

	public void setMuvekkilAdi(String muvekkilAdi) {
		this.muvekkilAdi = muvekkilAdi;
	}

	public double getAsilAlacak() {
		return asilAlacak;
	}

	public void setAsilAlacak(double asilAlacak) {
		this.asilAlacak = asilAlacak;
	}

	public double getTakipAlacak() {
		return takipAlacak;
	}

	public void setTakipAlacak(double takipAlacak) {
		this.takipAlacak = takipAlacak;
	}

	public double getToplamAlacak() {
		return toplamAlacak;
	}

	public void setToplamAlacak(double toplamAlacak) {
		this.toplamAlacak = toplamAlacak;
	}

	public double getTahsilatMiktari() {
		return tahsilatMiktari;
	}

	public void setTahsilatMiktari(double tahsilatMiktari) {
		this.tahsilatMiktari = tahsilatMiktari;
	}

	public String getUrunAdi() {
		return urunAdi;
	}

	public void setUrunAdi(String urunAdi) {
		this.urunAdi = urunAdi;
	}

	public String getUrunNo() {
		return urunNo;
	}

	public void setUrunNo(String urunNo) {
		this.urunNo = urunNo;
	}

	public float getMasrafTutari() {
		return masrafTutari;
	}

	public void setMasrafTutari(float masrafTutari) {
		this.masrafTutari = masrafTutari;
	}

	public String getMuzekkereAdi() {
		return muzekkereAdi;
	}

	public void setMuzekkereAdi(String muzekkereAdi) {
		this.muzekkereAdi = muzekkereAdi;
	}

	public String getTalepAdi() {
		return talepAdi;
	}

	public void setTalepAdi(String talepAdi) {
		this.talepAdi = talepAdi;
	}

	public String getMuzekkereTalepAdi() {
		return muzekkereTalepAdi;
	}

	public void setMuzekkereTalepAdi(String muzekkereTalepAdi) {
		this.muzekkereTalepAdi = muzekkereTalepAdi;
	}

	public int getMuzekkereTalepMiktari() {
		return muzekkereTalepMiktari;
	}

	public void setMuzekkereTalepMiktari(int muzekkereTalepMiktari) {
		this.muzekkereTalepMiktari = muzekkereTalepMiktari;
	}

	public int getPersonelId() {
		return personelId;
	}

	public void setPersonelId(int personelId) {
		this.personelId = personelId;
	}

	public double getBorcMiktari() {
		return borcMiktari;
	}

	public void setBorcMiktari(double borcMiktari) {
		this.borcMiktari = borcMiktari;
	}

	public int getIcraMudurluguID() {
		return icraMudurluguID;
	}

	public void setIcraMudurluguID(int icraMudurluguID) {
		this.icraMudurluguID = icraMudurluguID;
	}

	public String getAvukatAdi() {
		return avukatAdi;
	}

	public void setAvukatAdi(String avukatAdi) {
		this.avukatAdi = avukatAdi;
	}

	public String getAvukatAdres() {
		return avukatAdres;
	}

	public void setAvukatAdres(String avukatAdres) {
		this.avukatAdres = avukatAdres;
	}

	public String getAvukatIBAN() {
		return avukatIBAN;
	}

	public void setAvukatIBAN(String avukatIBAN) {
		this.avukatIBAN = avukatIBAN;
	}

	public int getAvukatId() {
		return avukatId;
	}

	public void setAvukatId(int avukatId) {
		this.avukatId = avukatId;
	}

	public String getPttAdi() {
		return pttAdi;
	}

	public void setPttAdi(String pttAdi) {
		this.pttAdi = pttAdi;
	}

	public Date getMumaleTarihi() {
		return mumaleTarihi;
	}

	public void setMumaleTarihi(Date mumaleTarihi) {
		this.mumaleTarihi = mumaleTarihi;
	}

	public int getMuzekkereId() {
		return muzekkereId;
	}

	public void setMuzekkereId(int muzekkereId) {
		this.muzekkereId = muzekkereId;
	}

	public int getMuvekkilId() {
		return muvekkilId;
	}

	public void setMuvekkilId(int muvekkilId) {
		this.muvekkilId = muvekkilId;
	}

	public int getMuameleStatusu() {
		return muameleStatusu;
	}

	public void setMuameleStatusu(int muameleStatusu) {
		this.muameleStatusu = muameleStatusu;
	}

	public int getTebligatSonucu() {
		return tebligatSonucu;
	}

	public void setTebligatSonucu(int tebligatSonucu) {
		this.tebligatSonucu = tebligatSonucu;
	}

	public String getBorcMiktariTxt() {
		return borcMiktariTxt;
	}

	public void setBorcMiktariTxt(String borcMiktariTxt) {
		this.borcMiktariTxt = borcMiktariTxt;
	}

	public String getMuameleTarihiTxt() {
		return muameleTarihiTxt;
	}

	public void setMuameleTarihiTxt(String muameleTarihiTxt) {
		this.muameleTarihiTxt = muameleTarihiTxt;
	}

	public String getPersonelAdSoyad() {
		return personelAdSoyad;
	}

	public void setPersonelAdSoyad(String personelAdSoyad) {
		this.personelAdSoyad = personelAdSoyad;
	}

	public HashMap<String, List<TapuBilgisi>> getTapuKayitlar() {
		return tapuKayitlar;
	}

	public void setTapuKayitlar(HashMap<String, List<TapuBilgisi>> tapuKayitlar) {
		this.tapuKayitlar = tapuKayitlar;
	}


	


	
	
	
	
	
	

}
