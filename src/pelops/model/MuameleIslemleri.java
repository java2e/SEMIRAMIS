package pelops.model;

import java.util.Date;

public class MuameleIslemleri {

	private int id;
	boolean checkControl;
	private int status;
	private int icraDosyaId;
	private String icraDosyaNo;
	private int semiramisNo;
	private String borcluAdi;
	private Date muameleTarihi;
	private String talepIfadesi;
	private int masrafTipiId;
	private int masrafMiktari;
	private int malTipiId;
	private String malBilgisi;
	private int personelId;
	private int avukatId;
	private String avukatAdi;
	private String muhatapAdi;
	private int muameleStatusuId;
	private String muhatapAdresi;
	private int hacizSirasi;
	private Date tebligatTarihi;
	private Date hacizBaslangicTarihi;
	private String tebligatSonucu;
	private int hacizMiktari;
	private String maasMuvafakat;
	private String aciklama;
	private String alacakliBankasi;
	private String alacakFaizMasrafTutari;
	private String alacakliMail;
	private String hazirlayan;
	private String alacakliTel;
	private String bankaBilgileri;
	private String borcluTc;
	private String borcluAdresi;
	private String barcode;
	private int contentId;
	private int icraMudurluguId;
	private int hazirlayanId;
	private String baslik;
	private String pdf;
	private String paragraf1;
	private String paragraf2;
	private String paragraf3;
	private String muameleAdi;
	private int miktar;
	private String tarih;
	private String muzekkereAdi;
	private String talepAdi;
	private String muzekkereTalepAdi;
	private int muzekkereTalepMiktari;
	private String hazirlayanAdi;
	private String alacakFaizTutari;
	private String masrafTipiAdi;
	private String malTipiAdi;
	private String muameleStatuAdi;
	private String icraMudurluguAdi;
	private String avukatSoyadi;
	private String hazirlayanAdSoyad ;
	


	private boolean muzekkere1;
	private boolean muzekkere2;
	private boolean muzekkere3;
	private boolean muzekkere4;
	private boolean muzekkere5;
	private boolean muzekkere6;
	private boolean muzekkere7;
	private boolean muzekkere8;
	private boolean muzekkere9;

	private boolean muzekkere1Visible = false;
	private boolean muzekkere2Visible = false;
	private boolean muzekkere3Visible = false;
	private boolean muzekkere4Visible = false;
	private boolean muzekkere5Visible = false;
	private boolean muzekkere6Visible = false;
	private boolean muzekkere7Visible = false;
	private boolean muzekkere8Visible = false;
	private boolean muzekkere9Visible = false;

	private int muzekkere1Sayi = 1;
	private int muzekkere2Sayi = 1;
	private int muzekkere3Sayi = 1;
	private int muzekkere4Sayi = 1;
	private int muzekkere5Sayi = 1;
	private int muzekkere6Sayi = 1;
	private int muzekkere7Sayi = 1;
	private int muzekkere8Sayi = 1;
	private int muzekkere9Sayi = 1;

	private boolean talep1Visible = false;
	private boolean talep2Visible = false;
	private boolean talep3Visible = false;
	private boolean talep4Visible = false;
	private boolean talep5Visible = false;
	private boolean talep6Visible = false;
	private boolean talep7Visible = false;
	private boolean talep8Visible = false;
	private boolean talep9Visible = false;

	private boolean talep1;
	private boolean talep2;
	private boolean talep3;
	private boolean talep4;
	private boolean talep5;
	private boolean talep6;
	private boolean talep7;
	private boolean talep8;
	private boolean talep9;

	private int talep1Sayi = 1;
	private int talep2Sayi = 1;
	private int talep3Sayi = 1;
	private int talep4Sayi = 1;
	private int talep5Sayi = 1;
	private int talep6Sayi = 1;
	private int talep7Sayi = 1;
	private int talep8Sayi = 1;
	private int talep9Sayi = 1;


	
	
	public String getHazirlayanAdSoyad() {
		return hazirlayanAdSoyad;
	}

	public void setHazirlayanAdSoyad(String hazirlayanAdSoyad) {
		this.hazirlayanAdSoyad = hazirlayanAdSoyad;
	}

	public String getMuameleAdi() {
		return muameleAdi;
	}

	public void setMuameleAdi(String muameleAdi) {
		this.muameleAdi = muameleAdi;
	}

	public String getHazirlayanAdi() {
		return hazirlayanAdi;
	}

	public void setHazirlayanAdi(String hazirlayanAdi) {
		this.hazirlayanAdi = hazirlayanAdi;
	}

	public String getAlacakFaizTutari() {
		return alacakFaizTutari;
	}

	public void setAlacakFaizTutari(String alacakFaizTutari) {
		this.alacakFaizTutari = alacakFaizTutari;
	}

	public String getMasrafTipiAdi() {
		return masrafTipiAdi;
	}

	public void setMasrafTipiAdi(String masrafTipiAdi) {
		this.masrafTipiAdi = masrafTipiAdi;
	}

	public String getMalTipiAdi() {
		return malTipiAdi;
	}

	public void setMalTipiAdi(String malTipiAdi) {
		this.malTipiAdi = malTipiAdi;
	}

	public String getMuameleStatuAdi() {
		return muameleStatuAdi;
	}

	public void setMuameleStatuAdi(String muameleStatuAdi) {
		this.muameleStatuAdi = muameleStatuAdi;
	}

	public String getIcraMudurluguAdi() {
		return icraMudurluguAdi;
	}

	public void setIcraMudurluguAdi(String icraMudurluguAdi) {
		this.icraMudurluguAdi = icraMudurluguAdi;
	}

	public String getAvukatSoyadi() {
		return avukatSoyadi;
	}

	public void setAvukatSoyadi(String avukatSoyadi) {
		this.avukatSoyadi = avukatSoyadi;
	}

	public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public int getAvukatId() {
		return avukatId;
	}

	public void setAvukatId(int avukatId) {
		this.avukatId = avukatId;
	}

	public String getIcraDosyaNo() {
		return icraDosyaNo;
	}

	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
	}

	public String getBaslik() {
		return baslik;
	}

	public String getAlacakliBankasi() {
		return alacakliBankasi;
	}

	public void setAlacakliBankasi(String alacakliBankasi) {
		this.alacakliBankasi = alacakliBankasi;
	}

	public int getHazirlayanId() {
		return hazirlayanId;
	}

	public void setHazirlayanId(int hazirlayanId) {
		this.hazirlayanId = hazirlayanId;
	}

	public void setBaslik(String baslik) {
		this.baslik = baslik;
	}

	public String getParagraf1() {
		return paragraf1;
	}

	public void setParagraf1(String paragraf1) {
		this.paragraf1 = paragraf1;
	}

	public String getParagraf2() {
		return paragraf2;
	}

	public void setParagraf2(String paragraf2) {
		this.paragraf2 = paragraf2;
	}

	public String getParagraf3() {
		return paragraf3;
	}

	public void setParagraf3(String paragraf3) {
		this.paragraf3 = paragraf3;
	}

	public int getIcraDosyaId() {
		return icraDosyaId;
	}

	public void setIcraDosyaId(int icraDosyaId) {
		this.icraDosyaId = icraDosyaId;
	}

	public int getSemiramisNo() {
		return semiramisNo;
	}

	public void setSemiramisNo(int semiramisNo) {
		this.semiramisNo = semiramisNo;
	}

	public String getBorcluAdi() {
		return borcluAdi;
	}

	public void setBorcluAdi(String borcluAdi) {
		this.borcluAdi = borcluAdi;
	}

	public Date getMuameleTarihi() {
		return muameleTarihi;
	}

	public void setMuameleTarihi(Date muameleTarihi) {
		this.muameleTarihi = muameleTarihi;
	}

	public String getTalepIfadesi() {
		return talepIfadesi;
	}

	public void setTalepIfadesi(String talepIfadesi) {
		this.talepIfadesi = talepIfadesi;
	}

	public int getMasrafTipiId() {
		return masrafTipiId;
	}

	public void setMasrafTipiId(int masrafTipiId) {
		this.masrafTipiId = masrafTipiId;
	}

	public int getMasrafMiktari() {
		return masrafMiktari;
	}

	public void setMasrafMiktari(int masrafMiktari) {
		this.masrafMiktari = masrafMiktari;
	}

	public int getMalTipiId() {
		return malTipiId;
	}

	public void setMalTipiId(int malTipiId) {
		this.malTipiId = malTipiId;
	}

	public String getMalBilgisi() {
		return malBilgisi;
	}

	public void setMalBilgisi(String malBilgisi) {
		this.malBilgisi = malBilgisi;
	}

	public int getMuameleStatusuId() {
		return muameleStatusuId;
	}

	public void setMuameleStatusuId(int muameleStatusuId) {
		this.muameleStatusuId = muameleStatusuId;
	}

	public int getPersonelId() {
		return personelId;
	}

	public void setPersonelId(int personelId) {
		this.personelId = personelId;
	}

	public String getAvukatAdi() {
		return avukatAdi;
	}

	public void setAvukatAdi(String avukatAdi) {
		this.avukatAdi = avukatAdi;
	}

	public String getMuhatapAdi() {
		return muhatapAdi;
	}

	public void setMuhatapAdi(String muhatapAdi) {
		this.muhatapAdi = muhatapAdi;
	}

	public String getMuhatapAdresi() {
		return muhatapAdresi;
	}

	public void setMuhatapAdresi(String muhatapAdresi) {
		this.muhatapAdresi = muhatapAdresi;
	}

	public int getHacizSirasi() {
		return hacizSirasi;
	}

	public void setHacizSirasi(int hacizSirasi) {
		this.hacizSirasi = hacizSirasi;
	}

	public Date getTebligatTarihi() {
		return tebligatTarihi;
	}

	public void setTebligatTarihi(Date tebligatTarihi) {
		this.tebligatTarihi = tebligatTarihi;
	}

	public Date getHacizBaslangicTarihi() {
		return hacizBaslangicTarihi;
	}

	public void setHacizBaslangicTarihi(Date hacizBaslangicTarihi) {
		this.hacizBaslangicTarihi = hacizBaslangicTarihi;
	}

	public String getTebligatSonucu() {
		return tebligatSonucu;
	}

	public void setTebligatSonucu(String tebligatSonucu) {
		this.tebligatSonucu = tebligatSonucu;
	}

	public int getHacizMiktari() {
		return hacizMiktari;
	}

	public void setHacizMiktari(int hacizMiktari) {
		this.hacizMiktari = hacizMiktari;
	}

	public String getMaasMuvafakat() {
		return maasMuvafakat;
	}

	public void setMaasMuvafakat(String maasMuvafakat) {
		this.maasMuvafakat = maasMuvafakat;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public int getMiktar() {
		return miktar;
	}

	public void setMiktar(int miktar) {
		this.miktar = miktar;
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

	public boolean isMuzekkere1() {
		return muzekkere1;
	}

	public void setMuzekkere1(boolean muzekkere1) {
		this.muzekkere1 = muzekkere1;
	}

	public boolean isMuzekkere2() {
		return muzekkere2;
	}

	public void setMuzekkere2(boolean muzekkere2) {
		this.muzekkere2 = muzekkere2;
	}

	public int getIcraMudurluguId() {
		return icraMudurluguId;
	}

	public void setIcraMudurluguId(int icraMudurluguId) {
		this.icraMudurluguId = icraMudurluguId;
	}

	public boolean isMuzekkere3() {
		return muzekkere3;
	}

	public void setMuzekkere3(boolean muzekkere3) {
		this.muzekkere3 = muzekkere3;
	}

	public boolean isMuzekkere4() {
		return muzekkere4;
	}

	public void setMuzekkere4(boolean muzekkere4) {
		this.muzekkere4 = muzekkere4;
	}

	public boolean isMuzekkere5() {
		return muzekkere5;
	}

	public void setMuzekkere5(boolean muzekkere5) {
		this.muzekkere5 = muzekkere5;
	}

	public boolean isMuzekkere6() {
		return muzekkere6;
	}

	public void setMuzekkere6(boolean muzekkere6) {
		this.muzekkere6 = muzekkere6;
	}

	public boolean isMuzekkere7() {
		return muzekkere7;
	}

	public void setMuzekkere7(boolean muzekkere7) {
		this.muzekkere7 = muzekkere7;
	}

	public boolean isMuzekkere8() {
		return muzekkere8;
	}

	public void setMuzekkere8(boolean muzekkere8) {
		this.muzekkere8 = muzekkere8;
	}

	public boolean isMuzekkere9() {
		return muzekkere9;
	}

	public void setMuzekkere9(boolean muzekkere9) {
		this.muzekkere9 = muzekkere9;
	}

	public int getMuzekkere1Sayi() {
		return muzekkere1Sayi;
	}

	public void setMuzekkere1Sayi(int muzekkere1Sayi) {
		this.muzekkere1Sayi = muzekkere1Sayi;
	}

	public int getMuzekkere2Sayi() {
		return muzekkere2Sayi;
	}

	public void setMuzekkere2Sayi(int muzekkere2Sayi) {
		this.muzekkere2Sayi = muzekkere2Sayi;
	}

	public int getMuzekkere3Sayi() {
		return muzekkere3Sayi;
	}

	public void setMuzekkere3Sayi(int muzekkere3Sayi) {
		this.muzekkere3Sayi = muzekkere3Sayi;
	}

	public int getMuzekkere4Sayi() {
		return muzekkere4Sayi;
	}

	public void setMuzekkere4Sayi(int muzekkere4Sayi) {
		this.muzekkere4Sayi = muzekkere4Sayi;
	}

	public int getMuzekkere5Sayi() {
		return muzekkere5Sayi;
	}

	public void setMuzekkere5Sayi(int muzekkere5Sayi) {
		this.muzekkere5Sayi = muzekkere5Sayi;
	}

	public int getMuzekkere6Sayi() {
		return muzekkere6Sayi;
	}

	public void setMuzekkere6Sayi(int muzekkere6Sayi) {
		this.muzekkere6Sayi = muzekkere6Sayi;
	}

	public int getMuzekkere7Sayi() {
		return muzekkere7Sayi;
	}

	public void setMuzekkere7Sayi(int muzekkere7Sayi) {
		this.muzekkere7Sayi = muzekkere7Sayi;
	}

	public int getMuzekkere8Sayi() {
		return muzekkere8Sayi;
	}

	public void setMuzekkere8Sayi(int muzekkere8Sayi) {
		this.muzekkere8Sayi = muzekkere8Sayi;
	}

	public int getMuzekkere9Sayi() {
		return muzekkere9Sayi;
	}

	public void setMuzekkere9Sayi(int muzekkere9Sayi) {
		this.muzekkere9Sayi = muzekkere9Sayi;
	}

	public boolean isTalep1() {
		return talep1;
	}

	public void setTalep1(boolean talep1) {
		this.talep1 = talep1;
	}

	public boolean isTalep2() {
		return talep2;
	}

	public void setTalep2(boolean talep2) {
		this.talep2 = talep2;
	}

	public boolean isTalep3() {
		return talep3;
	}

	public void setTalep3(boolean talep3) {
		this.talep3 = talep3;
	}

	public boolean isTalep4() {
		return talep4;
	}

	public void setTalep4(boolean talep4) {
		this.talep4 = talep4;
	}

	public boolean isTalep5() {
		return talep5;
	}

	public void setTalep5(boolean talep5) {
		this.talep5 = talep5;
	}

	public boolean isTalep6() {
		return talep6;
	}

	public void setTalep6(boolean talep6) {
		this.talep6 = talep6;
	}

	public boolean isTalep7() {
		return talep7;
	}

	public void setTalep7(boolean talep7) {
		this.talep7 = talep7;
	}

	public boolean isTalep8() {
		return talep8;
	}

	public void setTalep8(boolean talep8) {
		this.talep8 = talep8;
	}

	public boolean isTalep9() {
		return talep9;
	}

	public void setTalep9(boolean talep9) {
		this.talep9 = talep9;
	}

	public int getTalep1Sayi() {
		return talep1Sayi;
	}

	public void setTalep1Sayi(int talep1Sayi) {
		this.talep1Sayi = talep1Sayi;
	}

	public int getTalep2Sayi() {
		return talep2Sayi;
	}

	public void setTalep2Sayi(int talep2Sayi) {
		this.talep2Sayi = talep2Sayi;
	}

	public int getTalep3Sayi() {
		return talep3Sayi;
	}

	public void setTalep3Sayi(int talep3Sayi) {
		this.talep3Sayi = talep3Sayi;
	}

	public int getTalep4Sayi() {
		return talep4Sayi;
	}

	public void setTalep4Sayi(int talep4Sayi) {
		this.talep4Sayi = talep4Sayi;
	}

	public int getTalep5Sayi() {
		return talep5Sayi;
	}

	public void setTalep5Sayi(int talep5Sayi) {
		this.talep5Sayi = talep5Sayi;
	}

	public int getTalep6Sayi() {
		return talep6Sayi;
	}

	public void setTalep6Sayi(int talep6Sayi) {
		this.talep6Sayi = talep6Sayi;
	}

	public int getTalep7Sayi() {
		return talep7Sayi;
	}

	public void setTalep7Sayi(int talep7Sayi) {
		this.talep7Sayi = talep7Sayi;
	}

	public int getTalep8Sayi() {
		return talep8Sayi;
	}

	public void setTalep8Sayi(int talep8Sayi) {
		this.talep8Sayi = talep8Sayi;
	}

	public int getTalep9Sayi() {
		return talep9Sayi;
	}

	public void setTalep9Sayi(int talep9Sayi) {
		this.talep9Sayi = talep9Sayi;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isCheckControl() {
		return checkControl;
	}

	public void setCheckControl(boolean checkControl) {
		this.checkControl = checkControl;
	}

	public String getTarih() {
		return tarih;
	}

	public boolean isMuzekkere1Visible() {
		return muzekkere1Visible;
	}

	public void setMuzekkere1Visible(boolean muzekkere1Visible) {
		this.muzekkere1Visible = muzekkere1Visible;
	}

	public boolean isMuzekkere2Visible() {
		return muzekkere2Visible;
	}

	public void setMuzekkere2Visible(boolean muzekkere2Visible) {
		this.muzekkere2Visible = muzekkere2Visible;
	}

	public boolean isMuzekkere3Visible() {
		return muzekkere3Visible;
	}

	public void setMuzekkere3Visible(boolean muzekkere3Visible) {
		this.muzekkere3Visible = muzekkere3Visible;
	}

	public boolean isMuzekkere4Visible() {
		return muzekkere4Visible;
	}

	public void setMuzekkere4Visible(boolean muzekkere4Visible) {
		this.muzekkere4Visible = muzekkere4Visible;
	}

	public boolean isMuzekkere5Visible() {
		return muzekkere5Visible;
	}

	public void setMuzekkere5Visible(boolean muzekkere5Visible) {
		this.muzekkere5Visible = muzekkere5Visible;
	}

	public boolean isMuzekkere6Visible() {
		return muzekkere6Visible;
	}

	public void setMuzekkere6Visible(boolean muzekkere6Visible) {
		this.muzekkere6Visible = muzekkere6Visible;
	}

	public boolean isMuzekkere7Visible() {
		return muzekkere7Visible;
	}

	public void setMuzekkere7Visible(boolean muzekkere7Visible) {
		this.muzekkere7Visible = muzekkere7Visible;
	}

	public boolean isMuzekkere8Visible() {
		return muzekkere8Visible;
	}

	public void setMuzekkere8Visible(boolean muzekkere8Visible) {
		this.muzekkere8Visible = muzekkere8Visible;
	}

	public boolean isMuzekkere9Visible() {
		return muzekkere9Visible;
	}

	public void setMuzekkere9Visible(boolean muzekkere9Visible) {
		this.muzekkere9Visible = muzekkere9Visible;
	}

	public boolean isTalep1Visible() {
		return talep1Visible;
	}

	public void setTalep1Visible(boolean talep1Visible) {
		this.talep1Visible = talep1Visible;
	}

	public boolean isTalep2Visible() {
		return talep2Visible;
	}

	public void setTalep2Visible(boolean talep2Visible) {
		this.talep2Visible = talep2Visible;
	}

	public boolean isTalep3Visible() {
		return talep3Visible;
	}

	public void setTalep3Visible(boolean talep3Visible) {
		this.talep3Visible = talep3Visible;
	}

	public boolean isTalep4Visible() {
		return talep4Visible;
	}

	public void setTalep4Visible(boolean talep4Visible) {
		this.talep4Visible = talep4Visible;
	}

	public boolean isTalep5Visible() {
		return talep5Visible;
	}

	public void setTalep5Visible(boolean talep5Visible) {
		this.talep5Visible = talep5Visible;
	}

	public boolean isTalep6Visible() {
		return talep6Visible;
	}

	public void setTalep6Visible(boolean talep6Visible) {
		this.talep6Visible = talep6Visible;
	}

	public boolean isTalep7Visible() {
		return talep7Visible;
	}

	public void setTalep7Visible(boolean talep7Visible) {
		this.talep7Visible = talep7Visible;
	}

	public String getAlacakliMail() {
		return alacakliMail;
	}

	public void setAlacakliMail(String alacakliMail) {
		this.alacakliMail = alacakliMail;
	}

	public String getHazirlayan() {
		return hazirlayan;
	}

	public void setHazirlayan(String hazirlayan) {
		this.hazirlayan = hazirlayan;
	}

	public String getAlacakliTel() {
		return alacakliTel;
	}

	public void setAlacakliTel(String alacakliTel) {
		this.alacakliTel = alacakliTel;
	}

	public String getBankaBilgileri() {
		return bankaBilgileri;
	}

	public void setBankaBilgileri(String bankaBilgileri) {
		this.bankaBilgileri = bankaBilgileri;
	}

	public String getBorcluTc() {
		return borcluTc;
	}

	public void setBorcluTc(String borcluTc) {
		this.borcluTc = borcluTc;
	}

	public String getBorcluAdresi() {
		return borcluAdresi;
	}

	public void setBorcluAdresi(String borcluAdresi) {
		this.borcluAdresi = borcluAdresi;
	}

	public boolean isTalep8Visible() {
		return talep8Visible;
	}

	public void setTalep8Visible(boolean talep8Visible) {
		this.talep8Visible = talep8Visible;
	}

	public boolean isTalep9Visible() {
		return talep9Visible;
	}

	public void setTalep9Visible(boolean talep9Visible) {
		this.talep9Visible = talep9Visible;
	}

	public String getAlacakFaizMasrafTutari() {
		return alacakFaizMasrafTutari;
	}

	public void setAlacakFaizMasrafTutari(String alacakFaizMasrafTutari) {
		this.alacakFaizMasrafTutari = alacakFaizMasrafTutari;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

}
