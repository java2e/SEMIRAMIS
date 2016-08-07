package pelops.model;

import java.util.Date;

public class MuameleIslemleri {

	// ***********Genel Alanlar***********************************************************

	private int id;
	private Object barkod;
	private String barkodTxt;
	boolean checkControl;
	private int status;
	private int icraDosyaId;
	private String icraDosyaNo;
	private int semiramisNo;
	private String borcluAdi;
	private String talepIfadesi;
	private int masrafTipiId;
	private Double masrafMiktari;
	private int malTipiId;
	private String malBilgisi;
	private int personelId;
	private int avukatId;
	private String avukatAdi;
	private String muhatapAdi;
	private int muameleStatusuId;
	private String muhatapAdresi;
	private int hacizSirasi;
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
	
	private String tapuMudurlugu;
	private String tapuMudurluguIlIlce;
	private String tapuIl;
	private String tapuIlce;
	

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
	private Double alacakFaizTutari;
	private String masrafTipiAdi;
	private String malTipiAdi;
	private String muameleStatuAdi;
	private String icraMudurluguAdi;
	private String avukatSoyadi;
	private String hazirlayanAdSoyad;
	private int islemTuruId;
	private String adres;
	private String plaka;
	private String vekili;
	private String postaneAdi;
	private String il;
	private String ilce;
	private String tel;
	private String email;
	private Double borcluMiktari;
	
	private String borcluMiktariTxt;
	
	
	private String alacakliAdi;
	private String buroIbanNo;
	private String borcluTcKimlikNo;
	private String konu;
	private int miktari;
	private String riskYoneticisi;
	private String bankaAdi;
	private String eki;
	private Date dogumTarihi;
	private String sirketAdi;
	private String vergiNo;
	private String mudurYardimcisi;
	private Date muameleTarihi;
	private Date tebligatTarihi;
	private Date hacizBaslangicTarihi;
	private Date gondermeTarihi;
	private String tebligatTarihiText;
	private String hacizBaslangicTarihiText;
	private String gondermeTarihiText;
	private String muameleTarihiText;
	private String buroAdresi;
	private String vergiKimlikNo;
	private String kurumAdi;
	private int tebligatSonucuId;
	private int riskYoneticisiId;
	private String mernisAdresi;
	private String sgkAdresi;
	private String bankaMudurlukler;
	private String sgk_standart_text;
	private String tapu_standart_text;
	private String posta_standart_text;
	private String egm_standart_text;
	private String bankaAdlari;
	private String yurticiAdresi;
	private String kurumAdlari;
	private String tapuKayitlari;
	private String bankaAdresi;
	private String dogumTarihiText;
	private String pttIlText;
	private String pttIlceText;
	private String hazirlayanText;
	private String riskYoneticisiText;
	private String mevduatBankaAdi;
	private String mevduatBankaAdresi;
	private String postaneIl;
	private String postaneIlce;
	private String banka4;
	private String banka7;
	
	
	



	// ***********Müzekkereler , Visible , Sayi ***********************************************************

	private boolean Default;
	private boolean davetiyemuzekkeresi103;
	private boolean davetiyemuzekkeresi103arac;
	private boolean davetiyemuzekkeresi103sgk;
	private boolean davetiyemuzekkeresi103gayrimenkul;
	private boolean davetiyemuzekkeresi103menkul;

	private boolean mevduathaczimuzekkeresi;
	private boolean adresarastirmamuzekkeresikurumlaricin;
	private boolean hacizihbarnamesimuzekkeresi891;
	private boolean maashacizmuzekkeresigenel;
	private boolean maashacizmuzekkeresimuvafakat;
	private boolean ptthacizmuzekkeresi;
	private boolean tapuhacizmuzekkeresinokta;
	private boolean gsmmuzekkeresiturkcell;
	private boolean gsmmuzekkeresiturktelekom;
	private boolean gsmmuzekkeresivodafone;

	private boolean mevduathaczimuzekkeresiVisible = false;
	private boolean davetiyemuzekkeresi103Visible = false;
	private boolean davetiyemuzekkeresi103aracVisible = false;
	private boolean davetiyemuzekkeresi103sgkVisible = false;
	private boolean davetiyemuzekkeresi103gayrimenkulVisible = false;
	private boolean davetiyemuzekkeresi103menkulVisible = false;
	private boolean hacizihbarnamesimuzekkeresi891Visible = false;
	private boolean gsmmuzekkeresiturkcellVisible = false;
	private boolean gsmmuzekkeresiturktelekomVisible = false;
	private boolean gsmmuzekkeresivodafoneVisible = false;
	private boolean maashacizmuzekkeresigenelVisible = false;
	private boolean maashacizmuzekkeresimuvafakatVisible = false;
	private boolean ptthacizmuzekkeresiVisible = false;
	private boolean tapuhacizmuzekkeresinoktaVisible = false;

	private int mevduathaczimuzekkeresiSayi = 1;
	private int davetiyemuzekkeresi103Sayi = 1;
	private int davetiyemuzekkeresi103aracSayi = 1;
	private int davetiyemuzekkeresi103sgkSayi = 1;
	private int davetiyemuzekkeresi103gayrimenkulSayi = 1;
	private int davetiyemuzekkeresi103menkulSayi = 1;
	private int hacizihbarnamesimuzekkeresi891Sayi = 1;
	private int gsmmuzekkeresiturkcellSayi = 1;
	private int gsmmuzekkeresiturktelekomSayi = 1;
	private int gsmmuzekkeresivodafoneSayi = 1;
	private int maashacizmuzekkeresigenelSayi = 1;
	private int maashacizmuzekkeresimuvafakatSayi = 1;
	private int ptthacizmuzekkeresiSayi = 1;
	private int tapuhacizmuzekkeresinoktaSayi = 1;

	// ***********Talepler , Visible , Sayi ***********************************************************

	private boolean dosyaislemdenkaldirilmatalebiharcborcluda;
	private boolean dosyaislemdenkaldirilmatalebiharcburoda;
	private boolean aracserhitalebi;
	private boolean aracyakalamatalebi;
	private boolean tapuhaciztalebi;
	private boolean ailekayittablosutalebi;
	private boolean davetiyetalebi103;
	private boolean maashaciztalebigenel;
	private boolean maashaciztalebimuvafakat;
	private boolean adresarastimatalebi;
	private boolean arachaczitalebi;
	private boolean dosyaislemdenkaldirilmatalebi;
	private boolean feragattalebi;
	private boolean hacizihbarnamesitalebibankalaricin;
	private boolean kapanistalebiharcborcludatalebi;
	private boolean kapanistalebiharcburodatalebi;
	private boolean menkulhaciztalebi;
	private boolean mernisadresineodemeemritalebi;
	private boolean mevduathaczitalebi;
	private boolean sgkadresiodemeemritalebi;
	private boolean ticaretsiciladressormatalebi;
	private boolean tk21talebi;
	private boolean yenilemetalebi;
	private boolean yurticiadresiodemeemritalebi;
	private boolean ptthaciztalebi;
	private boolean coklu4lu5bankatalebi;
	private boolean coklu4lu7bankatalebi;

	private boolean dosyaislemdenkaldirilmatalebiharcborcludaVisible = false;
	private boolean dosyaislemdenkaldirilmatalebiharcburodaVisible = false;
	private boolean aracserhitalebiVisible = false;
	private boolean aracyakalamatalebiVisible = false;
	private boolean maashaciztalebimuvafakatVisible = false;
	private boolean tapuhaciztalebiVisible = false;
	private boolean ailekayittablosutalebiVisible = false;
	private boolean adresarastirmamuzekkeresikurumlaricinVisible = false;
	private boolean maashaciztalebigenelVisible = false;
	private boolean davetiyetalebi103Visible = false;
	private boolean adresarastimatalebiVisible = false;
	private boolean arachaczitalebiVisible = false;
	private boolean dosyaislemdenkaldirilmatalebiVisible = false;
	private boolean feragattalebiVisible = false;
	private boolean hacizihbarnamesitalebibankalaricinVisible = false;
	private boolean kapanistalebiharcborcludatalebiVisible = false;
	private boolean kapanistalebiharcburodatalebiVisible = false;
	private boolean menkulhaciztalebiVisible = false;
	private boolean mernisadresineodemeemritalebiVisible = false;
	private boolean mevduathaczitalebiVisible = false;
	private boolean sgkadresiodemeemritalebiVisible = false;
	private boolean ticaretsiciladressormatalebiVisible = false;
	private boolean tk21talebiVisible = false;
	private boolean yenilemetalebiVisible = false;
	private boolean yurticiadresiodemeemritalebiVisible = false;
	private boolean ptthaciztalebiVisible = false;
	private boolean coklu4lu5bankatalebiVisible=false;
	private boolean coklu4lu7bankatalebiVisible=false;

	private int dosyaislemdenkaldirilmatalebiharcborcludaSayi = 1;
	private int dosyaislemdenkaldirilmatalebiharcburodaSayi = 1;
	private int aracserhitalebiSayi = 1;
	private int aracyakalamatalebiSayi = 1;
	private int maashaciztalebimuvafakatSayi = 1;
	private int tapuhaciztalebiSayi = 1;
	private int ailekayittablosutalebiSayi = 1;
	private int adresarastirmamuzekkeresikurumlaricinSayi = 1;
	private int maashaciztalebigenelSayi = 1;
	private int davetiyetalebi103Sayi = 1;
	private int adresarastimatalebiSayi = 1;
	private int arachaczitalebiSayi = 1;
	private int dosyaislemdenkaldirilmatalebiSayi = 1;
	private int feragattalebiSayi = 1;
	private int hacizihbarnamesitalebibankalaricinSayi = 1;
	private int kapanistalebiharcborcludatalebiSayi = 1;
	private int kapanistalebiharcburodatalebiSayi = 1;
	private int menkulhaciztalebiSayi = 1;
	private int mernisadresineodemeemritalebiSayi = 1;
	private int mevduathaczitalebiSayi = 1;
	private int sgkadresiodemeemritalebiSayi = 1;
	private int ticaretsiciladressormatalebiSayi = 1;
	private int tk21talebiSayi = 1;
	private int yenilemetalebiSayi = 1;
	private int yurticiadresiodemeemritalebiSayi = 1;
	private int ptthaciztalebiSayi = 1;
	private int coklu4lu5bankatalebiSayi =1;
	private int coklu4lu7bankatalebiSayi=1;

	// *********** Getter Setter ***********************************************************
	
	
	

	public int getId() {
		return id;
	}



	public Double getMasrafMiktari() {
		return masrafMiktari;
	}



	public void setMasrafMiktari(Double masrafMiktari) {
		this.masrafMiktari = masrafMiktari;
	}



	public Double getAlacakFaizTutari() {
		return alacakFaizTutari;
	}



	public void setAlacakFaizTutari(Double alacakFaizTutari) {
		this.alacakFaizTutari = alacakFaizTutari;
	}



	public boolean isDefault() {
		return Default;
	}



	public void setDefault(boolean default1) {
		Default = default1;
	}



	public String getBanka4() {
		return banka4;
	}


	public void setBanka4(String banka4) {
		this.banka4 = banka4;
	}





	public String getBanka7() {
		return banka7;
	}





	public void setBanka7(String banka7) {
		this.banka7 = banka7;
	}





	public boolean isCoklu4lu5bankatalebi() {
		return coklu4lu5bankatalebi;
	}





	public void setCoklu4lu5bankatalebi(boolean coklu4lu5bankatalebi) {
		this.coklu4lu5bankatalebi = coklu4lu5bankatalebi;
	}





	public boolean isCoklu4lu7bankatalebi() {
		return coklu4lu7bankatalebi;
	}





	public void setCoklu4lu7bankatalebi(boolean coklu4lu7bankatalebi) {
		this.coklu4lu7bankatalebi = coklu4lu7bankatalebi;
	}





	public boolean isCoklu4lu5bankatalebiVisible() {
		return coklu4lu5bankatalebiVisible;
	}





	public void setCoklu4lu5bankatalebiVisible(boolean coklu4lu5bankatalebiVisible) {
		this.coklu4lu5bankatalebiVisible = coklu4lu5bankatalebiVisible;
	}





	public boolean isCoklu4lu7bankatalebiVisible() {
		return coklu4lu7bankatalebiVisible;
	}





	public void setCoklu4lu7bankatalebiVisible(boolean coklu4lu7bankatalebiVisible) {
		this.coklu4lu7bankatalebiVisible = coklu4lu7bankatalebiVisible;
	}





	public int getCoklu4lu5bankatalebiSayi() {
		return coklu4lu5bankatalebiSayi;
	}





	public void setCoklu4lu5bankatalebiSayi(int coklu4lu5bankatalebiSayi) {
		this.coklu4lu5bankatalebiSayi = coklu4lu5bankatalebiSayi;
	}





	public int getCoklu4lu7bankatalebiSayi() {
		return coklu4lu7bankatalebiSayi;
	}





	public void setCoklu4lu7bankatalebiSayi(int coklu4lu7bankatalebiSayi) {
		this.coklu4lu7bankatalebiSayi = coklu4lu7bankatalebiSayi;
	}





	public String getMevduatBankaAdi() {
		return mevduatBankaAdi;
	}



	public void setMevduatBankaAdi(String mevduatBankaAdi) {
		this.mevduatBankaAdi = mevduatBankaAdi;
	}





	public String getMevduatBankaAdresi() {
		return mevduatBankaAdresi;
	}





	public void setMevduatBankaAdresi(String mevduatBankaAdresi) {
		this.mevduatBankaAdresi = mevduatBankaAdresi;
	}





	public String getPostaneIl() {
		return postaneIl;
	}


	public void setPostaneIl(String postaneIl) {
		this.postaneIl = postaneIl;
	}


	public String getPostaneIlce() {
		return postaneIlce;
	}


	public void setPostaneIlce(String postaneIlce) {
		this.postaneIlce = postaneIlce;
	}


	public String getHazirlayanText() {
		return hazirlayanText;
	}


	public void setHazirlayanText(String hazirlayanText) {
		this.hazirlayanText = hazirlayanText;
	}


	public String getRiskYoneticisiText() {
		return riskYoneticisiText;
	}


	public void setRiskYoneticisiText(String riskYoneticisiText) {
		this.riskYoneticisiText = riskYoneticisiText;
	}


	public String getPttIlText() {
		return pttIlText;
	}

	public void setPttIlText(String pttIlText) {
		this.pttIlText = pttIlText;
	}

	public String getPttIlceText() {
		return pttIlceText;
	}

	public void setPttIlceText(String pttIlceText) {
		this.pttIlceText = pttIlceText;
	}

	public String getDogumTarihiText() {
		return dogumTarihiText;
	}

	public void setDogumTarihiText(String dogumTarihiText) {
		this.dogumTarihiText = dogumTarihiText;
	}

	public String getBankaAdresi() {
		return bankaAdresi;
	}

	public void setBankaAdresi(String bankaAdresi) {
		this.bankaAdresi = bankaAdresi;
	}

	public String getBuroAdresi() {
		return buroAdresi;
	}

	public void setBuroAdresi(String buroAdresi) {
		this.buroAdresi = buroAdresi;
	}

	public String getTapuKayitlari() {
		return tapuKayitlari;
	}

	public void setTapuKayitlari(String tapuKayitlari) {
		this.tapuKayitlari = tapuKayitlari;
	}

	public String getKurumAdlari() {
		return kurumAdlari;
	}

	public void setKurumAdlari(String kurumAdlari) {
		this.kurumAdlari = kurumAdlari;
	}

	public String getYurticiAdresi() {
		return yurticiAdresi;
	}

	public void setYurticiAdresi(String yurticiAdresi) {
		this.yurticiAdresi = yurticiAdresi;
	}

	public boolean isDosyaislemdenkaldirilmatalebiharcburoda() {
		return dosyaislemdenkaldirilmatalebiharcburoda;
	}

	public void setDosyaislemdenkaldirilmatalebiharcburoda(boolean dosyaislemdenkaldirilmatalebiharcburoda) {
		this.dosyaislemdenkaldirilmatalebiharcburoda = dosyaislemdenkaldirilmatalebiharcburoda;
	}

	public boolean isDosyaislemdenkaldirilmatalebiharcburodaVisible() {
		return dosyaislemdenkaldirilmatalebiharcburodaVisible;
	}

	public void setDosyaislemdenkaldirilmatalebiharcburodaVisible(
			boolean dosyaislemdenkaldirilmatalebiharcburodaVisible) {
		this.dosyaislemdenkaldirilmatalebiharcburodaVisible = dosyaislemdenkaldirilmatalebiharcburodaVisible;
	}

	public int getDosyaislemdenkaldirilmatalebiharcburodaSayi() {
		return dosyaislemdenkaldirilmatalebiharcburodaSayi;
	}

	public void setDosyaislemdenkaldirilmatalebiharcburodaSayi(int dosyaislemdenkaldirilmatalebiharcburodaSayi) {
		this.dosyaislemdenkaldirilmatalebiharcburodaSayi = dosyaislemdenkaldirilmatalebiharcburodaSayi;
	}

	public boolean isDosyaislemdenkaldirilmatalebiharcborcluda() {
		return dosyaislemdenkaldirilmatalebiharcborcluda;
	}

	public void setDosyaislemdenkaldirilmatalebiharcborcluda(boolean dosyaislemdenkaldirilmatalebiharcborcluda) {
		this.dosyaislemdenkaldirilmatalebiharcborcluda = dosyaislemdenkaldirilmatalebiharcborcluda;
	}

	public boolean isDosyaislemdenkaldirilmatalebiharcborcludaVisible() {
		return dosyaislemdenkaldirilmatalebiharcborcludaVisible;
	}

	public void setDosyaislemdenkaldirilmatalebiharcborcludaVisible(
			boolean dosyaislemdenkaldirilmatalebiharcborcludaVisible) {
		this.dosyaislemdenkaldirilmatalebiharcborcludaVisible = dosyaislemdenkaldirilmatalebiharcborcludaVisible;
	}

	public int getDosyaislemdenkaldirilmatalebiharcborcludaSayi() {
		return dosyaislemdenkaldirilmatalebiharcborcludaSayi;
	}

	public void setDosyaislemdenkaldirilmatalebiharcborcludaSayi(int dosyaislemdenkaldirilmatalebiharcborcludaSayi) {
		this.dosyaislemdenkaldirilmatalebiharcborcludaSayi = dosyaislemdenkaldirilmatalebiharcborcludaSayi;
	}

	public boolean isAracserhitalebi() {
		return aracserhitalebi;
	}

	public void setAracserhitalebi(boolean aracserhitalebi) {
		this.aracserhitalebi = aracserhitalebi;
	}

	public boolean isAracserhitalebiVisible() {
		return aracserhitalebiVisible;
	}

	public void setAracserhitalebiVisible(boolean aracserhitalebiVisible) {
		this.aracserhitalebiVisible = aracserhitalebiVisible;
	}

	public int getAracserhitalebiSayi() {
		return aracserhitalebiSayi;
	}

	public void setAracserhitalebiSayi(int aracserhitalebiSayi) {
		this.aracserhitalebiSayi = aracserhitalebiSayi;
	}

	public boolean isAracyakalamatalebi() {
		return aracyakalamatalebi;
	}

	public void setAracyakalamatalebi(boolean aracyakalamatalebi) {
		this.aracyakalamatalebi = aracyakalamatalebi;
	}

	public boolean isAracyakalamatalebiVisible() {
		return aracyakalamatalebiVisible;
	}

	public void setAracyakalamatalebiVisible(boolean aracyakalamatalebiVisible) {
		this.aracyakalamatalebiVisible = aracyakalamatalebiVisible;
	}

	public int getAracyakalamatalebiSayi() {
		return aracyakalamatalebiSayi;
	}

	public void setAracyakalamatalebiSayi(int aracyakalamatalebiSayi) {
		this.aracyakalamatalebiSayi = aracyakalamatalebiSayi;
	}

	public boolean isMaashaciztalebimuvafakat() {
		return maashaciztalebimuvafakat;
	}

	public void setMaashaciztalebimuvafakat(boolean maashaciztalebimuvafakat) {
		this.maashaciztalebimuvafakat = maashaciztalebimuvafakat;
	}

	public boolean isMaashaciztalebimuvafakatVisible() {
		return maashaciztalebimuvafakatVisible;
	}

	public void setMaashaciztalebimuvafakatVisible(boolean maashaciztalebimuvafakatVisible) {
		this.maashaciztalebimuvafakatVisible = maashaciztalebimuvafakatVisible;
	}

	public int getMaashaciztalebimuvafakatSayi() {
		return maashaciztalebimuvafakatSayi;
	}

	public void setMaashaciztalebimuvafakatSayi(int maashaciztalebimuvafakatSayi) {
		this.maashaciztalebimuvafakatSayi = maashaciztalebimuvafakatSayi;
	}

	public boolean isTapuhaciztalebi() {
		return tapuhaciztalebi;
	}

	public void setTapuhaciztalebi(boolean tapuhaciztalebi) {
		this.tapuhaciztalebi = tapuhaciztalebi;
	}

	public boolean isTapuhaciztalebiVisible() {
		return tapuhaciztalebiVisible;
	}

	public void setTapuhaciztalebiVisible(boolean tapuhaciztalebiVisible) {
		this.tapuhaciztalebiVisible = tapuhaciztalebiVisible;
	}

	public int getTapuhaciztalebiSayi() {
		return tapuhaciztalebiSayi;
	}

	public void setTapuhaciztalebiSayi(int tapuhaciztalebiSayi) {
		this.tapuhaciztalebiSayi = tapuhaciztalebiSayi;
	}

	public boolean isAilekayittablosutalebi() {
		return ailekayittablosutalebi;
	}

	public void setAilekayittablosutalebi(boolean ailekayittablosutalebi) {
		this.ailekayittablosutalebi = ailekayittablosutalebi;
	}

	public boolean isAilekayittablosutalebiVisible() {
		return ailekayittablosutalebiVisible;
	}

	public void setAilekayittablosutalebiVisible(boolean ailekayittablosutalebiVisible) {
		this.ailekayittablosutalebiVisible = ailekayittablosutalebiVisible;
	}

	public int getAilekayittablosutalebiSayi() {
		return ailekayittablosutalebiSayi;
	}

	public void setAilekayittablosutalebiSayi(int ailekayittablosutalebiSayi) {
		this.ailekayittablosutalebiSayi = ailekayittablosutalebiSayi;
	}

	public boolean isMevduathaczimuzekkeresi() {
		return mevduathaczimuzekkeresi;
	}

	public void setMevduathaczimuzekkeresi(boolean mevduathaczimuzekkeresi) {
		this.mevduathaczimuzekkeresi = mevduathaczimuzekkeresi;
	}

	public boolean isMevduathaczimuzekkeresiVisible() {
		return mevduathaczimuzekkeresiVisible;
	}

	public void setMevduathaczimuzekkeresiVisible(boolean mevduathaczimuzekkeresiVisible) {
		this.mevduathaczimuzekkeresiVisible = mevduathaczimuzekkeresiVisible;
	}

	public int getMevduathaczimuzekkeresiSayi() {
		return mevduathaczimuzekkeresiSayi;
	}

	public void setMevduathaczimuzekkeresiSayi(int mevduathaczimuzekkeresiSayi) {
		this.mevduathaczimuzekkeresiSayi = mevduathaczimuzekkeresiSayi;
	}

	public boolean isAdresarastirmamuzekkeresikurumlaricin() {
		return adresarastirmamuzekkeresikurumlaricin;
	}

	public void setAdresarastirmamuzekkeresikurumlaricin(boolean adresarastirmamuzekkeresikurumlaricin) {
		this.adresarastirmamuzekkeresikurumlaricin = adresarastirmamuzekkeresikurumlaricin;
	}

	public boolean isAdresarastirmamuzekkeresikurumlaricinVisible() {
		return adresarastirmamuzekkeresikurumlaricinVisible;
	}

	public void setAdresarastirmamuzekkeresikurumlaricinVisible(boolean adresarastirmamuzekkeresikurumlaricinVisible) {
		this.adresarastirmamuzekkeresikurumlaricinVisible = adresarastirmamuzekkeresikurumlaricinVisible;
	}

	public int getAdresarastirmamuzekkeresikurumlaricinSayi() {
		return adresarastirmamuzekkeresikurumlaricinSayi;
	}

	public void setAdresarastirmamuzekkeresikurumlaricinSayi(int adresarastirmamuzekkeresikurumlaricinSayi) {
		this.adresarastirmamuzekkeresikurumlaricinSayi = adresarastirmamuzekkeresikurumlaricinSayi;
	}

	public String getBankaAdlari() {
		return bankaAdlari;
	}

	public void setBankaAdlari(String bankaAdlari) {
		this.bankaAdlari = bankaAdlari;
	}

	public boolean isMaashaciztalebigenel() {
		return maashaciztalebigenel;
	}

	public void setMaashaciztalebigenel(boolean maashaciztalebigenel) {
		this.maashaciztalebigenel = maashaciztalebigenel;
	}

	public boolean isMaashaciztalebigenelVisible() {
		return maashaciztalebigenelVisible;
	}

	public void setMaashaciztalebigenelVisible(boolean maashaciztalebigenelVisible) {
		this.maashaciztalebigenelVisible = maashaciztalebigenelVisible;
	}

	public int getMaashaciztalebigenelSayi() {
		return maashaciztalebigenelSayi;
	}

	public void setMaashaciztalebigenelSayi(int maashaciztalebigenelSayi) {
		this.maashaciztalebigenelSayi = maashaciztalebigenelSayi;
	}

	public String getSgk_standart_text() {
		return sgk_standart_text;
	}

	public void setSgk_standart_text(String sgk_standart_text) {
		this.sgk_standart_text = sgk_standart_text;
	}

	public String getTapu_standart_text() {
		return tapu_standart_text;
	}

	public void setTapu_standart_text(String tapu_standart_text) {
		this.tapu_standart_text = tapu_standart_text;
	}

	public String getPosta_standart_text() {
		return posta_standart_text;
	}

	public void setPosta_standart_text(String posta_standart_text) {
		this.posta_standart_text = posta_standart_text;
	}

	public String getEgm_standart_text() {
		return egm_standart_text;
	}

	public void setEgm_standart_text(String egm_standart_text) {
		this.egm_standart_text = egm_standart_text;
	}

	public boolean isPtthaciztalebi() {
		return ptthaciztalebi;
	}

	public void setPtthaciztalebi(boolean ptthaciztalebi) {
		this.ptthaciztalebi = ptthaciztalebi;
	}

	public boolean isPtthaciztalebiVisible() {
		return ptthaciztalebiVisible;
	}

	public void setPtthaciztalebiVisible(boolean ptthaciztalebiVisible) {
		this.ptthaciztalebiVisible = ptthaciztalebiVisible;
	}

	public int getPtthaciztalebiSayi() {
		return ptthaciztalebiSayi;
	}

	public void setPtthaciztalebiSayi(int ptthaciztalebiSayi) {
		this.ptthaciztalebiSayi = ptthaciztalebiSayi;
	}

	public String getBankaMudurlukler() {
		return bankaMudurlukler;
	}

	public void setBankaMudurlukler(String bankaMudurlukler) {
		this.bankaMudurlukler = bankaMudurlukler;
	}

	public boolean isHacizihbarnamesitalebibankalaricin() {
		return hacizihbarnamesitalebibankalaricin;
	}

	public void setHacizihbarnamesitalebibankalaricin(boolean hacizihbarnamesitalebibankalaricin) {
		this.hacizihbarnamesitalebibankalaricin = hacizihbarnamesitalebibankalaricin;
	}

	public boolean isHacizihbarnamesitalebibankalaricinVisible() {
		return hacizihbarnamesitalebibankalaricinVisible;
	}

	public void setHacizihbarnamesitalebibankalaricinVisible(boolean hacizihbarnamesitalebibankalaricinVisible) {
		this.hacizihbarnamesitalebibankalaricinVisible = hacizihbarnamesitalebibankalaricinVisible;
	}

	public int getHacizihbarnamesitalebibankalaricinSayi() {
		return hacizihbarnamesitalebibankalaricinSayi;
	}

	public void setHacizihbarnamesitalebibankalaricinSayi(int hacizihbarnamesitalebibankalaricinSayi) {
		this.hacizihbarnamesitalebibankalaricinSayi = hacizihbarnamesitalebibankalaricinSayi;
	}

	public boolean isHacizihbarnamesimuzekkeresi891() {
		return hacizihbarnamesimuzekkeresi891;
	}

	public void setHacizihbarnamesimuzekkeresi891(boolean hacizihbarnamesimuzekkeresi891) {
		this.hacizihbarnamesimuzekkeresi891 = hacizihbarnamesimuzekkeresi891;
	}

	public boolean isHacizihbarnamesimuzekkeresi891Visible() {
		return hacizihbarnamesimuzekkeresi891Visible;
	}

	public void setHacizihbarnamesimuzekkeresi891Visible(boolean hacizihbarnamesimuzekkeresi891Visible) {
		this.hacizihbarnamesimuzekkeresi891Visible = hacizihbarnamesimuzekkeresi891Visible;
	}

	public int getHacizihbarnamesimuzekkeresi891Sayi() {
		return hacizihbarnamesimuzekkeresi891Sayi;
	}

	public void setHacizihbarnamesimuzekkeresi891Sayi(int hacizihbarnamesimuzekkeresi891Sayi) {
		this.hacizihbarnamesimuzekkeresi891Sayi = hacizihbarnamesimuzekkeresi891Sayi;
	}

	public boolean isDavetiyemuzekkeresi103menkul() {
		return davetiyemuzekkeresi103menkul;
	}

	public void setDavetiyemuzekkeresi103menkul(boolean davetiyemuzekkeresi103menkul) {
		this.davetiyemuzekkeresi103menkul = davetiyemuzekkeresi103menkul;
	}

	public boolean isDavetiyemuzekkeresi103menkulVisible() {
		return davetiyemuzekkeresi103menkulVisible;
	}

	public void setDavetiyemuzekkeresi103menkulVisible(boolean davetiyemuzekkeresi103menkulVisible) {
		this.davetiyemuzekkeresi103menkulVisible = davetiyemuzekkeresi103menkulVisible;
	}

	public int getDavetiyemuzekkeresi103menkulSayi() {
		return davetiyemuzekkeresi103menkulSayi;
	}

	public void setDavetiyemuzekkeresi103menkulSayi(int davetiyemuzekkeresi103menkulSayi) {
		this.davetiyemuzekkeresi103menkulSayi = davetiyemuzekkeresi103menkulSayi;
	}

	public boolean isDavetiyemuzekkeresi103gayrimenkul() {
		return davetiyemuzekkeresi103gayrimenkul;
	}

	public void setDavetiyemuzekkeresi103gayrimenkul(boolean davetiyemuzekkeresi103gayrimenkul) {
		this.davetiyemuzekkeresi103gayrimenkul = davetiyemuzekkeresi103gayrimenkul;
	}

	public boolean isDavetiyemuzekkeresi103gayrimenkulVisible() {
		return davetiyemuzekkeresi103gayrimenkulVisible;
	}

	public void setDavetiyemuzekkeresi103gayrimenkulVisible(boolean davetiyemuzekkeresi103gayrimenkulVisible) {
		this.davetiyemuzekkeresi103gayrimenkulVisible = davetiyemuzekkeresi103gayrimenkulVisible;
	}

	public int getDavetiyemuzekkeresi103gayrimenkulSayi() {
		return davetiyemuzekkeresi103gayrimenkulSayi;
	}

	public void setDavetiyemuzekkeresi103gayrimenkulSayi(int davetiyemuzekkeresi103gayrimenkulSayi) {
		this.davetiyemuzekkeresi103gayrimenkulSayi = davetiyemuzekkeresi103gayrimenkulSayi;
	}

	public boolean isDavetiyemuzekkeresi103sgk() {
		return davetiyemuzekkeresi103sgk;
	}

	public void setDavetiyemuzekkeresi103sgk(boolean davetiyemuzekkeresi103sgk) {
		this.davetiyemuzekkeresi103sgk = davetiyemuzekkeresi103sgk;
	}

	public boolean isDavetiyemuzekkeresi103sgkVisible() {
		return davetiyemuzekkeresi103sgkVisible;
	}

	public void setDavetiyemuzekkeresi103sgkVisible(boolean davetiyemuzekkeresi103sgkVisible) {
		this.davetiyemuzekkeresi103sgkVisible = davetiyemuzekkeresi103sgkVisible;
	}

	public int getDavetiyemuzekkeresi103sgkSayi() {
		return davetiyemuzekkeresi103sgkSayi;
	}

	public void setDavetiyemuzekkeresi103sgkSayi(int davetiyemuzekkeresi103sgkSayi) {
		this.davetiyemuzekkeresi103sgkSayi = davetiyemuzekkeresi103sgkSayi;
	}

	public boolean isDavetiyemuzekkeresi103arac() {
		return davetiyemuzekkeresi103arac;
	}

	public void setDavetiyemuzekkeresi103arac(boolean davetiyemuzekkeresi103arac) {
		this.davetiyemuzekkeresi103arac = davetiyemuzekkeresi103arac;
	}

	public boolean isDavetiyemuzekkeresi103aracVisible() {
		return davetiyemuzekkeresi103aracVisible;
	}

	public void setDavetiyemuzekkeresi103aracVisible(boolean davetiyemuzekkeresi103aracVisible) {
		this.davetiyemuzekkeresi103aracVisible = davetiyemuzekkeresi103aracVisible;
	}

	public int getDavetiyemuzekkeresi103aracSayi() {
		return davetiyemuzekkeresi103aracSayi;
	}

	public void setDavetiyemuzekkeresi103aracSayi(int davetiyemuzekkeresi103aracSayi) {
		this.davetiyemuzekkeresi103aracSayi = davetiyemuzekkeresi103aracSayi;
	}

	public String getMernisAdresi() {
		return mernisAdresi;
	}

	public void setMernisAdresi(String mernisAdresi) {
		this.mernisAdresi = mernisAdresi;
	}

	public String getSgkAdresi() {
		return sgkAdresi;
	}

	public void setSgkAdresi(String sgkAdresi) {
		this.sgkAdresi = sgkAdresi;
	}

	public int getRiskYoneticisiId() {
		return riskYoneticisiId;
	}

	public void setRiskYoneticisiId(int riskYoneticisiId) {
		this.riskYoneticisiId = riskYoneticisiId;
	}

	public int getTebligatSonucuId() {
		return tebligatSonucuId;
	}

	public void setTebligatSonucuId(int tebligatSonucuId) {
		this.tebligatSonucuId = tebligatSonucuId;
	}

	public String getKurumAdi() {
		return kurumAdi;
	}

	public void setKurumAdi(String kurumAdi) {
		this.kurumAdi = kurumAdi;
	}

	public String getVergiKimlikNo() {
		return vergiKimlikNo;
	}

	public void setVergiKimlikNo(String vergiKimlikNo) {
		this.vergiKimlikNo = vergiKimlikNo;
	}



	public String getMuameleTarihiText() {
		return muameleTarihiText;
	}

	public void setMuameleTarihiText(String muameleTarihiText) {
		this.muameleTarihiText = muameleTarihiText;
	}

	public String getTebligatTarihiText() {
		return tebligatTarihiText;
	}

	public void setTebligatTarihiText(String tebligatTarihiText) {
		this.tebligatTarihiText = tebligatTarihiText;
	}

	public String getHacizBaslangicTarihiText() {
		return hacizBaslangicTarihiText;
	}

	public void setHacizBaslangicTarihiText(String hacizBaslangicTarihiText) {
		this.hacizBaslangicTarihiText = hacizBaslangicTarihiText;
	}

	public String getGondermeTarihiText() {
		return gondermeTarihiText;
	}

	public void setGondermeTarihiText(String gondermeTarihiText) {
		this.gondermeTarihiText = gondermeTarihiText;
	}

	public Date getGondermeTarihi() {
		return gondermeTarihi;
	}

	public void setGondermeTarihi(Date gondermeTarihi) {
		this.gondermeTarihi = gondermeTarihi;
	}

	public String getMudurYardimcisi() {
		return mudurYardimcisi;
	}

	public void setMudurYardimcisi(String mudurYardimcisi) {
		this.mudurYardimcisi = mudurYardimcisi;
	}

	public String getVergiNo() {
		return vergiNo;
	}

	public void setVergiNo(String vergiNo) {
		this.vergiNo = vergiNo;
	}

	public String getSirketAdi() {
		return sirketAdi;
	}

	public void setSirketAdi(String sirketAdi) {
		this.sirketAdi = sirketAdi;
	}

	public Date getDogumTarihi() {
		return dogumTarihi;
	}

	public void setDogumTarihi(Date dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}

	public String getEki() {
		return eki;
	}

	public void setEki(String eki) {
		this.eki = eki;
	}

	public String getBankaAdi() {
		return bankaAdi;
	}

	public void setBankaAdi(String bankaAdi) {
		this.bankaAdi = bankaAdi;
	}

	public String getRiskYoneticisi() {
		return riskYoneticisi;
	}

	public void setRiskYoneticisi(String riskYoneticisi) {
		this.riskYoneticisi = riskYoneticisi;
	}

	public int getMiktari() {
		return miktari;
	}

	public void setMiktari(int miktari) {
		this.miktari = miktari;
	}

	public String getKonu() {
		return konu;
	}

	public void setKonu(String konu) {
		this.konu = konu;
	}

	public String getBorcluTcKimlikNo() {
		return borcluTcKimlikNo;
	}

	public void setBorcluTcKimlikNo(String borcluTcKimlikNo) {
		this.borcluTcKimlikNo = borcluTcKimlikNo;
	}

	public String getBuroIbanNo() {
		return buroIbanNo;
	}

	public void setBuroIbanNo(String buroIbanNo) {
		this.buroIbanNo = buroIbanNo;
	}

	public String getAlacakliAdi() {
		return alacakliAdi;
	}

	public void setAlacakliAdi(String alacakliAdi) {
		this.alacakliAdi = alacakliAdi;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}





	public Double getBorcluMiktari() {
		return borcluMiktari;
	}



	public void setBorcluMiktari(Double borcluMiktari) {
		this.borcluMiktari = borcluMiktari;
	}



	public String getIl() {
		return il;
	}

	public void setIl(String il) {
		this.il = il;
	}

	public String getIlce() {
		return ilce;
	}

	public void setIlce(String ilce) {
		this.ilce = ilce;
	}

	public String getPostaneAdi() {
		return postaneAdi;
	}

	public void setPostaneAdi(String postaneAdi) {
		this.postaneAdi = postaneAdi;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isCheckControl() {
		return checkControl;
	}

	public void setCheckControl(boolean checkControl) {
		this.checkControl = checkControl;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIcraDosyaId() {
		return icraDosyaId;
	}

	public void setIcraDosyaId(int icraDosyaId) {
		this.icraDosyaId = icraDosyaId;
	}

	public String getIcraDosyaNo() {
		return icraDosyaNo;
	}

	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
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

	public int getPersonelId() {
		return personelId;
	}

	public void setPersonelId(int personelId) {
		this.personelId = personelId;
	}

	public int getAvukatId() {
		return avukatId;
	}

	public void setAvukatId(int avukatId) {
		this.avukatId = avukatId;
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

	public int getMuameleStatusuId() {
		return muameleStatusuId;
	}

	public void setMuameleStatusuId(int muameleStatusuId) {
		this.muameleStatusuId = muameleStatusuId;
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

	public String getAlacakliBankasi() {
		return alacakliBankasi;
	}

	public void setAlacakliBankasi(String alacakliBankasi) {
		this.alacakliBankasi = alacakliBankasi;
	}

	public String getAlacakFaizMasrafTutari() {
		return alacakFaizMasrafTutari;
	}

	public void setAlacakFaizMasrafTutari(String alacakFaizMasrafTutari) {
		this.alacakFaizMasrafTutari = alacakFaizMasrafTutari;
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

	public int getIcraMudurluguId() {
		return icraMudurluguId;
	}

	public void setIcraMudurluguId(int icraMudurluguId) {
		this.icraMudurluguId = icraMudurluguId;
	}

	public int getHazirlayanId() {
		return hazirlayanId;
	}

	public void setHazirlayanId(int hazirlayanId) {
		this.hazirlayanId = hazirlayanId;
	}

	public String getBaslik() {
		return baslik;
	}

	public void setBaslik(String baslik) {
		this.baslik = baslik;
	}

	public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
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

	public String getMuameleAdi() {
		return muameleAdi;
	}

	public void setMuameleAdi(String muameleAdi) {
		this.muameleAdi = muameleAdi;
	}

	public int getMiktar() {
		return miktar;
	}

	public void setMiktar(int miktar) {
		this.miktar = miktar;
	}

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
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

	public String getHazirlayanAdi() {
		return hazirlayanAdi;
	}

	public void setHazirlayanAdi(String hazirlayanAdi) {
		this.hazirlayanAdi = hazirlayanAdi;
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

	public String getHazirlayanAdSoyad() {
		return hazirlayanAdSoyad;
	}

	public void setHazirlayanAdSoyad(String hazirlayanAdSoyad) {
		this.hazirlayanAdSoyad = hazirlayanAdSoyad;
	}

	public int getIslemTuruId() {
		return islemTuruId;
	}

	public void setIslemTuruId(int islemTuruId) {
		this.islemTuruId = islemTuruId;
	}

	public boolean isDavetiyemuzekkeresi103() {
		return davetiyemuzekkeresi103;
	}

	public void setDavetiyemuzekkeresi103(boolean davetiyemuzekkeresi103) {
		this.davetiyemuzekkeresi103 = davetiyemuzekkeresi103;
	}

	public boolean isGsmmuzekkeresiturkcell() {
		return gsmmuzekkeresiturkcell;
	}

	public void setGsmmuzekkeresiturkcell(boolean gsmmuzekkeresiturkcell) {
		this.gsmmuzekkeresiturkcell = gsmmuzekkeresiturkcell;
	}

	public boolean isGsmmuzekkeresiturktelekom() {
		return gsmmuzekkeresiturktelekom;
	}

	public void setGsmmuzekkeresiturktelekom(boolean gsmmuzekkeresiturktelekom) {
		this.gsmmuzekkeresiturktelekom = gsmmuzekkeresiturktelekom;
	}

	public boolean isGsmmuzekkeresivodafone() {
		return gsmmuzekkeresivodafone;
	}

	public void setGsmmuzekkeresivodafone(boolean gsmmuzekkeresivodafone) {
		this.gsmmuzekkeresivodafone = gsmmuzekkeresivodafone;
	}

	public boolean isMaashacizmuzekkeresigenel() {
		return maashacizmuzekkeresigenel;
	}

	public void setMaashacizmuzekkeresigenel(boolean maashacizmuzekkeresigenel) {
		this.maashacizmuzekkeresigenel = maashacizmuzekkeresigenel;
	}

	public boolean isMaashacizmuzekkeresimuvafakat() {
		return maashacizmuzekkeresimuvafakat;
	}

	public void setMaashacizmuzekkeresimuvafakat(boolean maashacizmuzekkeresimuvafakat) {
		this.maashacizmuzekkeresimuvafakat = maashacizmuzekkeresimuvafakat;
	}

	public boolean isPtthacizmuzekkeresi() {
		return ptthacizmuzekkeresi;
	}

	public void setPtthacizmuzekkeresi(boolean ptthacizmuzekkeresi) {
		this.ptthacizmuzekkeresi = ptthacizmuzekkeresi;
	}

	public boolean isTapuhacizmuzekkeresinokta() {
		return tapuhacizmuzekkeresinokta;
	}

	public void setTapuhacizmuzekkeresinokta(boolean tapuhacizmuzekkeresinokta) {
		this.tapuhacizmuzekkeresinokta = tapuhacizmuzekkeresinokta;
	}

	public boolean isDavetiyemuzekkeresi103Visible() {
		return davetiyemuzekkeresi103Visible;
	}

	public void setDavetiyemuzekkeresi103Visible(boolean davetiyemuzekkeresi103Visible) {
		this.davetiyemuzekkeresi103Visible = davetiyemuzekkeresi103Visible;
	}

	public boolean isGsmmuzekkeresiturkcellVisible() {
		return gsmmuzekkeresiturkcellVisible;
	}

	public void setGsmmuzekkeresiturkcellVisible(boolean gsmmuzekkeresiturkcellVisible) {
		this.gsmmuzekkeresiturkcellVisible = gsmmuzekkeresiturkcellVisible;
	}

	public boolean isGsmmuzekkeresiturktelekomVisible() {
		return gsmmuzekkeresiturktelekomVisible;
	}

	public void setGsmmuzekkeresiturktelekomVisible(boolean gsmmuzekkeresiturktelekomVisible) {
		this.gsmmuzekkeresiturktelekomVisible = gsmmuzekkeresiturktelekomVisible;
	}

	public boolean isGsmmuzekkeresivodafoneVisible() {
		return gsmmuzekkeresivodafoneVisible;
	}

	public void setGsmmuzekkeresivodafoneVisible(boolean gsmmuzekkeresivodafoneVisible) {
		this.gsmmuzekkeresivodafoneVisible = gsmmuzekkeresivodafoneVisible;
	}

	public boolean isMaashacizmuzekkeresigenelVisible() {
		return maashacizmuzekkeresigenelVisible;
	}

	public void setMaashacizmuzekkeresigenelVisible(boolean maashacizmuzekkeresigenelVisible) {
		this.maashacizmuzekkeresigenelVisible = maashacizmuzekkeresigenelVisible;
	}

	public boolean isMaashacizmuzekkeresimuvafakatVisible() {
		return maashacizmuzekkeresimuvafakatVisible;
	}

	public void setMaashacizmuzekkeresimuvafakatVisible(boolean maashacizmuzekkeresimuvafakatVisible) {
		this.maashacizmuzekkeresimuvafakatVisible = maashacizmuzekkeresimuvafakatVisible;
	}

	public boolean isPtthacizmuzekkeresiVisible() {
		return ptthacizmuzekkeresiVisible;
	}

	public void setPtthacizmuzekkeresiVisible(boolean ptthacizmuzekkeresiVisible) {
		this.ptthacizmuzekkeresiVisible = ptthacizmuzekkeresiVisible;
	}

	public boolean isTapuhacizmuzekkeresinoktaVisible() {
		return tapuhacizmuzekkeresinoktaVisible;
	}

	public void setTapuhacizmuzekkeresinoktaVisible(boolean tapuhacizmuzekkeresinoktaVisible) {
		this.tapuhacizmuzekkeresinoktaVisible = tapuhacizmuzekkeresinoktaVisible;
	}

	public int getDavetiyemuzekkeresi103Sayi() {
		return davetiyemuzekkeresi103Sayi;
	}

	public void setDavetiyemuzekkeresi103Sayi(int davetiyemuzekkeresi103Sayi) {
		this.davetiyemuzekkeresi103Sayi = davetiyemuzekkeresi103Sayi;
	}

	public int getGsmmuzekkeresiturkcellSayi() {
		return gsmmuzekkeresiturkcellSayi;
	}

	public void setGsmmuzekkeresiturkcellSayi(int gsmmuzekkeresiturkcellSayi) {
		this.gsmmuzekkeresiturkcellSayi = gsmmuzekkeresiturkcellSayi;
	}

	public int getGsmmuzekkeresiturktelekomSayi() {
		return gsmmuzekkeresiturktelekomSayi;
	}

	public void setGsmmuzekkeresiturktelekomSayi(int gsmmuzekkeresiturktelekomSayi) {
		this.gsmmuzekkeresiturktelekomSayi = gsmmuzekkeresiturktelekomSayi;
	}

	public int getGsmmuzekkeresivodafoneSayi() {
		return gsmmuzekkeresivodafoneSayi;
	}

	public void setGsmmuzekkeresivodafoneSayi(int gsmmuzekkeresivodafoneSayi) {
		this.gsmmuzekkeresivodafoneSayi = gsmmuzekkeresivodafoneSayi;
	}

	public int getMaashacizmuzekkeresigenelSayi() {
		return maashacizmuzekkeresigenelSayi;
	}

	public void setMaashacizmuzekkeresigenelSayi(int maashacizmuzekkeresigenelSayi) {
		this.maashacizmuzekkeresigenelSayi = maashacizmuzekkeresigenelSayi;
	}

	public int getMaashacizmuzekkeresimuvafakatSayi() {
		return maashacizmuzekkeresimuvafakatSayi;
	}

	public void setMaashacizmuzekkeresimuvafakatSayi(int maashacizmuzekkeresimuvafakatSayi) {
		this.maashacizmuzekkeresimuvafakatSayi = maashacizmuzekkeresimuvafakatSayi;
	}

	public int getPtthacizmuzekkeresiSayi() {
		return ptthacizmuzekkeresiSayi;
	}

	public void setPtthacizmuzekkeresiSayi(int ptthacizmuzekkeresiSayi) {
		this.ptthacizmuzekkeresiSayi = ptthacizmuzekkeresiSayi;
	}

	public int getTapuhacizmuzekkeresinoktaSayi() {
		return tapuhacizmuzekkeresinoktaSayi;
	}

	public void setTapuhacizmuzekkeresinoktaSayi(int tapuhacizmuzekkeresinoktaSayi) {
		this.tapuhacizmuzekkeresinoktaSayi = tapuhacizmuzekkeresinoktaSayi;
	}

	public boolean isDavetiyetalebi103() {
		return davetiyetalebi103;
	}

	public void setDavetiyetalebi103(boolean davetiyetalebi103) {
		this.davetiyetalebi103 = davetiyetalebi103;
	}

	public boolean isAdresarastimatalebi() {
		return adresarastimatalebi;
	}

	public void setAdresarastimatalebi(boolean adresarastimatalebi) {
		this.adresarastimatalebi = adresarastimatalebi;
	}

	public boolean isArachaczitalebi() {
		return arachaczitalebi;
	}

	public void setArachaczitalebi(boolean arachaczitalebi) {
		this.arachaczitalebi = arachaczitalebi;
	}

	public boolean isDosyaislemdenkaldirilmatalebi() {
		return dosyaislemdenkaldirilmatalebi;
	}

	public void setDosyaislemdenkaldirilmatalebi(boolean dosyaislemdenkaldirilmatalebi) {
		this.dosyaislemdenkaldirilmatalebi = dosyaislemdenkaldirilmatalebi;
	}

	public boolean isFeragattalebi() {
		return feragattalebi;
	}

	public void setFeragattalebi(boolean feragattalebi) {
		this.feragattalebi = feragattalebi;
	}

	public boolean isKapanistalebiharcborcludatalebi() {
		return kapanistalebiharcborcludatalebi;
	}

	public void setKapanistalebiharcborcludatalebi(boolean kapanistalebiharcborcludatalebi) {
		this.kapanistalebiharcborcludatalebi = kapanistalebiharcborcludatalebi;
	}

	public boolean isKapanistalebiharcburodatalebi() {
		return kapanistalebiharcburodatalebi;
	}

	public void setKapanistalebiharcburodatalebi(boolean kapanistalebiharcburodatalebi) {
		this.kapanistalebiharcburodatalebi = kapanistalebiharcburodatalebi;
	}

	public boolean isMenkulhaciztalebi() {
		return menkulhaciztalebi;
	}

	public void setMenkulhaciztalebi(boolean menkulhaciztalebi) {
		this.menkulhaciztalebi = menkulhaciztalebi;
	}

	public boolean isMernisadresineodemeemritalebi() {
		return mernisadresineodemeemritalebi;
	}

	public void setMernisadresineodemeemritalebi(boolean mernisadresineodemeemritalebi) {
		this.mernisadresineodemeemritalebi = mernisadresineodemeemritalebi;
	}

	public boolean isMevduathaczitalebi() {
		return mevduathaczitalebi;
	}

	public void setMevduathaczitalebi(boolean mevduathaczitalebi) {
		this.mevduathaczitalebi = mevduathaczitalebi;
	}

	public boolean isSgkadresiodemeemritalebi() {
		return sgkadresiodemeemritalebi;
	}

	public void setSgkadresiodemeemritalebi(boolean sgkadresiodemeemritalebi) {
		this.sgkadresiodemeemritalebi = sgkadresiodemeemritalebi;
	}

	public boolean isTicaretsiciladressormatalebi() {
		return ticaretsiciladressormatalebi;
	}

	public void setTicaretsiciladressormatalebi(boolean ticaretsiciladressormatalebi) {
		this.ticaretsiciladressormatalebi = ticaretsiciladressormatalebi;
	}

	public boolean isTk21talebi() {
		return tk21talebi;
	}

	public void setTk21talebi(boolean tk21talebi) {
		this.tk21talebi = tk21talebi;
	}

	public boolean isYenilemetalebi() {
		return yenilemetalebi;
	}

	public void setYenilemetalebi(boolean yenilemetalebi) {
		this.yenilemetalebi = yenilemetalebi;
	}

	public boolean isYurticiadresiodemeemritalebi() {
		return yurticiadresiodemeemritalebi;
	}

	public void setYurticiadresiodemeemritalebi(boolean yurticiadresiodemeemritalebi) {
		this.yurticiadresiodemeemritalebi = yurticiadresiodemeemritalebi;
	}

	public boolean isDavetiyetalebi103Visible() {
		return davetiyetalebi103Visible;
	}

	public void setDavetiyetalebi103Visible(boolean davetiyetalebi103Visible) {
		this.davetiyetalebi103Visible = davetiyetalebi103Visible;
	}

	public boolean isAdresarastimatalebiVisible() {
		return adresarastimatalebiVisible;
	}

	public void setAdresarastimatalebiVisible(boolean adresarastimatalebiVisible) {
		this.adresarastimatalebiVisible = adresarastimatalebiVisible;
	}

	public boolean isArachaczitalebiVisible() {
		return arachaczitalebiVisible;
	}

	public void setArachaczitalebiVisible(boolean arachaczitalebiVisible) {
		this.arachaczitalebiVisible = arachaczitalebiVisible;
	}

	public boolean isDosyaislemdenkaldirilmatalebiVisible() {
		return dosyaislemdenkaldirilmatalebiVisible;
	}

	public void setDosyaislemdenkaldirilmatalebiVisible(boolean dosyaislemdenkaldirilmatalebiVisible) {
		this.dosyaislemdenkaldirilmatalebiVisible = dosyaislemdenkaldirilmatalebiVisible;
	}

	public boolean isFeragattalebiVisible() {
		return feragattalebiVisible;
	}

	public void setFeragattalebiVisible(boolean feragattalebiVisible) {
		this.feragattalebiVisible = feragattalebiVisible;
	}

	public boolean isKapanistalebiharcborcludatalebiVisible() {
		return kapanistalebiharcborcludatalebiVisible;
	}

	public void setKapanistalebiharcborcludatalebiVisible(boolean kapanistalebiharcborcludatalebiVisible) {
		this.kapanistalebiharcborcludatalebiVisible = kapanistalebiharcborcludatalebiVisible;
	}

	public boolean isKapanistalebiharcburodatalebiVisible() {
		return kapanistalebiharcburodatalebiVisible;
	}

	public void setKapanistalebiharcburodatalebiVisible(boolean kapanistalebiharcburodatalebiVisible) {
		this.kapanistalebiharcburodatalebiVisible = kapanistalebiharcburodatalebiVisible;
	}

	public boolean isMenkulhaciztalebiVisible() {
		return menkulhaciztalebiVisible;
	}

	public void setMenkulhaciztalebiVisible(boolean menkulhaciztalebiVisible) {
		this.menkulhaciztalebiVisible = menkulhaciztalebiVisible;
	}

	public boolean isMernisadresineodemeemritalebiVisible() {
		return mernisadresineodemeemritalebiVisible;
	}

	public void setMernisadresineodemeemritalebiVisible(boolean mernisadresineodemeemritalebiVisible) {
		this.mernisadresineodemeemritalebiVisible = mernisadresineodemeemritalebiVisible;
	}

	public boolean isMevduathaczitalebiVisible() {
		return mevduathaczitalebiVisible;
	}

	public void setMevduathaczitalebiVisible(boolean mevduathaczitalebiVisible) {
		this.mevduathaczitalebiVisible = mevduathaczitalebiVisible;
	}

	public boolean isSgkadresiodemeemritalebiVisible() {
		return sgkadresiodemeemritalebiVisible;
	}

	public void setSgkadresiodemeemritalebiVisible(boolean sgkadresiodemeemritalebiVisible) {
		this.sgkadresiodemeemritalebiVisible = sgkadresiodemeemritalebiVisible;
	}

	public boolean isTicaretsiciladressormatalebiVisible() {
		return ticaretsiciladressormatalebiVisible;
	}

	public void setTicaretsiciladressormatalebiVisible(boolean ticaretsiciladressormatalebiVisible) {
		this.ticaretsiciladressormatalebiVisible = ticaretsiciladressormatalebiVisible;
	}

	public boolean isTk21talebiVisible() {
		return tk21talebiVisible;
	}

	public void setTk21talebiVisible(boolean tk21talebiVisible) {
		this.tk21talebiVisible = tk21talebiVisible;
	}

	public boolean isYenilemetalebiVisible() {
		return yenilemetalebiVisible;
	}

	public void setYenilemetalebiVisible(boolean yenilemetalebiVisible) {
		this.yenilemetalebiVisible = yenilemetalebiVisible;
	}

	public boolean isYurticiadresiodemeemritalebiVisible() {
		return yurticiadresiodemeemritalebiVisible;
	}

	public void setYurticiadresiodemeemritalebiVisible(boolean yurticiadresiodemeemritalebiVisible) {
		this.yurticiadresiodemeemritalebiVisible = yurticiadresiodemeemritalebiVisible;
	}

	public int getDavetiyetalebi103Sayi() {
		return davetiyetalebi103Sayi;
	}

	public void setDavetiyetalebi103Sayi(int davetiyetalebi103Sayi) {
		this.davetiyetalebi103Sayi = davetiyetalebi103Sayi;
	}

	public int getAdresarastimatalebiSayi() {
		return adresarastimatalebiSayi;
	}

	public void setAdresarastimatalebiSayi(int adresarastimatalebiSayi) {
		this.adresarastimatalebiSayi = adresarastimatalebiSayi;
	}

	public int getArachaczitalebiSayi() {
		return arachaczitalebiSayi;
	}

	public void setArachaczitalebiSayi(int arachaczitalebiSayi) {
		this.arachaczitalebiSayi = arachaczitalebiSayi;
	}

	public int getDosyaislemdenkaldirilmatalebiSayi() {
		return dosyaislemdenkaldirilmatalebiSayi;
	}

	public void setDosyaislemdenkaldirilmatalebiSayi(int dosyaislemdenkaldirilmatalebiSayi) {
		this.dosyaislemdenkaldirilmatalebiSayi = dosyaislemdenkaldirilmatalebiSayi;
	}

	public int getFeragattalebiSayi() {
		return feragattalebiSayi;
	}

	public void setFeragattalebiSayi(int feragattalebiSayi) {
		this.feragattalebiSayi = feragattalebiSayi;
	}

	public int getKapanistalebiharcborcludatalebiSayi() {
		return kapanistalebiharcborcludatalebiSayi;
	}

	public void setKapanistalebiharcborcludatalebiSayi(int kapanistalebiharcborcludatalebiSayi) {
		this.kapanistalebiharcborcludatalebiSayi = kapanistalebiharcborcludatalebiSayi;
	}

	public int getKapanistalebiharcburodatalebiSayi() {
		return kapanistalebiharcburodatalebiSayi;
	}

	public void setKapanistalebiharcburodatalebiSayi(int kapanistalebiharcburodatalebiSayi) {
		this.kapanistalebiharcburodatalebiSayi = kapanistalebiharcburodatalebiSayi;
	}

	public int getMenkulhaciztalebiSayi() {
		return menkulhaciztalebiSayi;
	}

	public void setMenkulhaciztalebiSayi(int menkulhaciztalebiSayi) {
		this.menkulhaciztalebiSayi = menkulhaciztalebiSayi;
	}

	public int getMernisadresineodemeemritalebiSayi() {
		return mernisadresineodemeemritalebiSayi;
	}

	public void setMernisadresineodemeemritalebiSayi(int mernisadresineodemeemritalebiSayi) {
		this.mernisadresineodemeemritalebiSayi = mernisadresineodemeemritalebiSayi;
	}

	public int getMevduathaczitalebiSayi() {
		return mevduathaczitalebiSayi;
	}

	public void setMevduathaczitalebiSayi(int mevduathaczitalebiSayi) {
		this.mevduathaczitalebiSayi = mevduathaczitalebiSayi;
	}

	public int getSgkadresiodemeemritalebiSayi() {
		return sgkadresiodemeemritalebiSayi;
	}

	public void setSgkadresiodemeemritalebiSayi(int sgkadresiodemeemritalebiSayi) {
		this.sgkadresiodemeemritalebiSayi = sgkadresiodemeemritalebiSayi;
	}

	public int getTicaretsiciladressormatalebiSayi() {
		return ticaretsiciladressormatalebiSayi;
	}

	public void setTicaretsiciladressormatalebiSayi(int ticaretsiciladressormatalebiSayi) {
		this.ticaretsiciladressormatalebiSayi = ticaretsiciladressormatalebiSayi;
	}

	public int getTk21talebiSayi() {
		return tk21talebiSayi;
	}

	public void setTk21talebiSayi(int tk21talebiSayi) {
		this.tk21talebiSayi = tk21talebiSayi;
	}

	public int getYenilemetalebiSayi() {
		return yenilemetalebiSayi;
	}

	public void setYenilemetalebiSayi(int yenilemetalebiSayi) {
		this.yenilemetalebiSayi = yenilemetalebiSayi;
	}

	public int getYurticiadresiodemeemritalebiSayi() {
		return yurticiadresiodemeemritalebiSayi;
	}

	public void setYurticiadresiodemeemritalebiSayi(int yurticiadresiodemeemritalebiSayi) {
		this.yurticiadresiodemeemritalebiSayi = yurticiadresiodemeemritalebiSayi;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getPlaka() {
		return plaka;
	}

	public void setPlaka(String plaka) {
		this.plaka = plaka;
	}

	public String getVekili() {
		return vekili;
	}

	public void setVekili(String vekili) {
		this.vekili = vekili;
	}



	public Object getBarkod() {
		return barkod;
	}



	public void setBarkod(Object barkod) {
		this.barkod = barkod;
	}



	public String getTapuMudurlugu() {
		return tapuMudurlugu;
	}



	public void setTapuMudurlugu(String tapuMudurlugu) {
		this.tapuMudurlugu = tapuMudurlugu;
	}



	public String getTapuMudurluguIlIlce() {
		return tapuMudurluguIlIlce;
	}



	public void setTapuMudurluguIlIlce(String tapuMudurluguIlIlce) {
		this.tapuMudurluguIlIlce = tapuMudurluguIlIlce;
	}



	public String getTapuIl() {
		return tapuIl;
	}



	public void setTapuIl(String tapuIl) {
		this.tapuIl = tapuIl;
	}



	public String getTapuIlce() {
		return tapuIlce;
	}



	public void setTapuIlce(String tapuIlce) {
		this.tapuIlce = tapuIlce;
	}



	public String getBorcluMiktariTxt() {
		return borcluMiktariTxt;
	}



	public void setBorcluMiktariTxt(String borcluMiktariTxt) {
		this.borcluMiktariTxt = borcluMiktariTxt;
	}






	

}
