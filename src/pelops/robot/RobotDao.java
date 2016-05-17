package pelops.robot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pelops.db.DBConnection;

public class RobotDao extends DBConnection {

	public static WebDriver driver;
	public static JavascriptExecutor js;
	public Robot bot;
	Boolean mernisResult = false;
	ArrayList<SearchEsas> icraDosyalari = new ArrayList<SearchEsas>();
	ArrayList<MernisInfo> mernisListe = new ArrayList<MernisInfo>();
	ArrayList<SgkCalisaniInfo> sgkCalisaniListe = new ArrayList<SgkCalisaniInfo>();
	private ArrayList<SgkCalisaniInfo> mSgkCalisaniListe;

	public RobotDao() {

	}

	public Boolean OpenConnectAndAccess() throws Exception {

		// connect();
		DesiredCapabilities capabilities = DesiredCapabilities
				.internetExplorer();
		capabilities
				.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
		//String file = "C:\\Users\\JAVACI\\Desktop\\SEMIRAMIS\\WebContent\\UyapXMLFiles\\IEDriverServer.exe";
		String file = "C:\\apache-tomcat-8.0.30\\webapps\\ROOT\\IEDriverServer_Win32_2.46.0\\IEDriverServer.exe";
		
		System.setProperty("webdriver.ie.driver", file);
		driver = new InternetExplorerDriver(capabilities);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.get("https://www.turkiye.gov.tr/uyap-portali-avukat-girisi");
		clickElement("/html/body/section/section/div/div[2]/a");
		Thread.sleep(1000);
		clickElement("/html/body/div[1]/section/nav/ul/li[3]/a");
		Thread.sleep(1000);

		return false;

	}

	public Boolean AccessTheSystem() throws InterruptedException {

		Thread.sleep(5000);
		Robot bot = null;
		try {
			bot = new Robot();
		} catch (Exception failed) {
			System.err.println("Failed instantiating Robot: " + failed);
		}
		int mask = InputEvent.BUTTON1_DOWN_MASK;
		bot.mouseMove(808, 401);
		bot.mousePress(mask);
		bot.mouseRelease(mask);
		Thread.sleep(1000);
		bot.mouseMove(700, 493);
		bot.mousePress(mask);
		bot.mouseRelease(mask);
		Thread.sleep(1000);
		bot.mouseMove(758, 516);
		bot.mousePress(mask);
		bot.mouseRelease(mask);
		Thread.sleep(3000);
		bot.mouseMove(868, 581);
		bot.mousePress(mask);
		bot.mouseRelease(mask);
		Thread.sleep(3000);
		bot.mouseMove(830, 394);
		bot.mousePress(mask);
		bot.mouseRelease(mask);
		bot.keyPress(KeyEvent.VK_4);
		bot.keyRelease(KeyEvent.VK_4);
		bot.keyPress(KeyEvent.VK_2);
		bot.keyRelease(KeyEvent.VK_2);
		bot.keyPress(KeyEvent.VK_5);
		bot.keyRelease(KeyEvent.VK_5);
		bot.keyPress(KeyEvent.VK_3);
		bot.keyRelease(KeyEvent.VK_3);
		Thread.sleep(1000);
		bot.mouseMove(865, 583);
		bot.mousePress(mask);
		bot.mouseRelease(mask);

		Thread.sleep(2000); // Giri� Yap
		bot.mouseMove(892, 579);
		bot.mousePress(mask);
		bot.mouseRelease(mask);

		Thread.sleep(3000);

		driver.findElement(By.className("exitButton")).click();
		driver.findElement(By.className("ssoLink")).click(); // Uygulamaya Git
		Thread.sleep(5000);

		HandleWindowAndMaximizeBrowser();

		return false;
	}

	public void clickIcraTakibi() throws Exception {
		executeAScript("menuAc('Online Takip', '/jsp/icra_takip/Icra_Takip_v_1_5Live.jsp', 72, 0);");
		Thread.sleep(5000);

	}

	public static void executeAScript(String script) {

		((JavascriptExecutor) driver).executeScript(script);
	}

	public Boolean TakipAcOnlineTakip() throws Exception {

		clickIcraTakibi();
		IcraTakipFormDoldur();

		return false;
	}

	public Boolean IcraTakipFormDoldur() throws AWTException,
			InterruptedException {
		Robot bot = new Robot();
		int mask = InputEvent.BUTTON1_DOWN_MASK;
		int x = 0, y = 0, x2 = 0, y2 = 0, sleepTime = 0;
		String ScrollEvent = "noScroll";
		int GecisSayisi = 0;

		// �l (ComboBox)
		x = 661;
		y = 222;
		sleepTime = 2000;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		// �l (ComboBox) -Ankara
		x = 542;
		y = 299;
		sleepTime = 5000;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		// Adliye-2 (ComboBox)
		x = 660;
		y = 251;
		sleepTime = 500;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		// Adliye-2 (ComboBox) - Ankara Adliye
		x = 538;
		y = 296;
		sleepTime = 500;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		// Takip Mahiyeti (radioButton) -Belgesiz
		x = 363;
		y = 436;
		sleepTime = 500;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		// 48/4 A��klama (textArea)
		x = 684;
		y = 569;
		sleepTime = 500;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		String text = "TEST";
		sleepTime = 500;
		GiveTextForKeyboard(text, bot, sleepTime); // Doldurulacak
													// D�zenlenecek

		// BSMV (checkBox)
		x = 489;
		y = 678;
		sleepTime = 500;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		// Scroll Drag
		x = 1114;
		y = 299;
		x2 = 1113;
		y2 = 393;
		sleepTime = 500;
		ScrollEvent = "dragScroll";
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);
		ScrollEvent = "noScroll";
		x2 = 0;
		y2 = 0;

		// Takibe Esas Miktar (textBox)
		x = 503;
		y = 397;
		sleepTime = 500;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		text = "1250";
		GiveTextForKeyboard(text, bot, sleepTime); // Doldurulacak

		// Taraf ��lemleri (Button)
		x = 420;
		y = 456;
		sleepTime = 1000;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		// Kurum (radioButton)
		x = 578;
		y = 248;
		sleepTime = 1000;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		// VergiNo (textBox)
		x = 571;
		y = 351;
		sleepTime = 1000;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		text = "8790017566";
		GiveTextForKeyboard(text, bot, sleepTime); // Doldurulacak

		// Sorgula (Button)
		x = 488;
		y = 378;
		sleepTime = 15000;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		// Adres �l (ComboBox)
		x = 703;
		y = 490;
		sleepTime = 500;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		// Adres �l (ComboBox) -�stanbul
		x = 583;
		y = 537;
		sleepTime = 500;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		// Adres �lce (ComboBox)
		x = 630;
		y = 517;
		sleepTime = 500;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		// Scroll Click
		x = 636;
		y = 641;
		sleepTime = 500;
		GecisSayisi = 7;
		ScrollEvent = "clickScroll";
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);
		ScrollEvent = "noScroll";
		GecisSayisi = 0;

		// Adres �lce -Be�ikta� (ComboBox)
		x = 574;
		y = 535;
		sleepTime = 500;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		// Adres Genel (textArea)
		x = 838;
		y = 532;
		sleepTime = 500;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		text = "TEST";
		GiveTextForKeyboard(text, bot, sleepTime); // Doldurulacak

		// Scroll Drag
		x = 1022;
		y = 400;
		x2 = 1022;
		y2 = 485;
		sleepTime = 500;
		GecisSayisi = 0;
		ScrollEvent = "dragScroll";
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);
		ScrollEvent = "noScroll";
		x2 = 0;
		y2 = 0;

		// Ekle (Button)
		x = 725;
		y = 665;
		sleepTime = 500;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		// Scroll Drag
		x = 1018;
		y = 481;
		x2 = 1022;
		y2 = 402;
		sleepTime = 500;
		GecisSayisi = 0;
		ScrollEvent = "dragScroll";
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);
		ScrollEvent = "noScroll";
		x2 = 0;
		y2 = 0;

		// S�fat� (Combo)
		x = 987;
		y = 248;
		sleepTime = 500;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		// S�fat� Borclu ve M�flis (Combo)
		x = 815;
		y = 294;
		sleepTime = 1000;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		// Ki�i Kurum (RadioButton)
		x = 527;
		y = 237;
		sleepTime = 500;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		// T.C. Kimlik No. (textBox)
		x = 595;
		y = 292;
		sleepTime = 500;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		text = "10900098120";
		GiveTextForKeyboard(text, bot, sleepTime); // Doldurulacak

		// Sorgula (Button)
		x = 769;
		y = 349;
		sleepTime = 1000;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		// Mernis Adresini Kullan (checkBox)
		x = 608;
		y = 325;
		sleepTime = 1000;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		// Alert( Mernis adresi sistemde mevcut)
		x = 734;
		y = 494;
		sleepTime = 1000;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		// Scroll Drag
		x = 1022;
		y = 401;
		x2 = 1022;
		y2 = 462;
		sleepTime = 500;
		GecisSayisi = 0;
		ScrollEvent = "dragScroll";
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);
		ScrollEvent = "noScroll";
		x2 = 0;
		y2 = 0;

		// Ekle(Button)
		x = 722;
		y = 458;
		sleepTime = 500;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		// Tamam (Button)
		x = 963;
		y = 608;
		sleepTime = 500;
		GiveCoordinatForMouse(x, y, x2, y2, sleepTime, bot, mask, ScrollEvent,
				GecisSayisi);

		return false;
	}

	public MernisInfo getMernisINFO() throws Exception {

		// if (a) {
		// driver.navigate().refresh();
		// }
		Thread.sleep(500);

		clickElement("/html/body/div[3]/div/fieldset/table/tbody/tr/td[5]/a");
		Thread.sleep(1000);
		clickElement("/html/body/div[3]/div[2]/div/ul/li[7]/a");
		Thread.sleep(1000);
		clickElement("/html/body/div[3]/div[2]/div/div[8]/table/tbody/tr/td[7]/a");

		Thread.sleep(1000);
		clickElement("/html/body/div[4]/div[2]/div/div[1]/a");

		Thread.sleep(1000);

		MernisInfo mernisInfo = new MernisInfo();

		// **********************************SORGULAMAYA AİT GENEL
		// BİLGİLER**********************************************

		// İslemi gerceklestirenin adi soyadi
		String IslemiGerceklestirenKisi = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[1]/table/tbody/tr[1]/td[2]");

		mernisInfo.setIslemiYapanKisiAdiSoyadi(IslemiGerceklestirenKisi);

		// Birim Adi

		String BirimAdi = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[1]/table/tbody/tr[2]/td[2]");

		mernisInfo.setBirimAdiDosyaNo(BirimAdi);

		// Sorgulanan Kisi Bilgileri

		String SorgulalanKisiBilgileri = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[1]/table/tbody/tr[3]/td[2]");

		mernisInfo.setSorgulananKisiBilgileri(SorgulalanKisiBilgileri);

		// **********************************MERNİSE KAYITLI KİMLİK
		// BİLGİLERİ**********************************************

		// TC Kimlik No

		String TcKimlikno = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[2]/table/tbody/tr[1]/td[2]");

		mernisInfo.setTcKimlikNo(TcKimlikno);

		// ADİ

		String adi = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[2]/table/tbody/tr[2]/td[2]");

		mernisInfo.setAdi(adi);

		// Baba Adı

		String baba_adi = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[2]/table/tbody/tr[3]/td[2]");
		mernisInfo.setBabaAdi(baba_adi);

		// Doğum Yeri

		String dogum_yeri = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[2]/table/tbody/tr[4]/td[2]");

		mernisInfo.setDogumYeri(dogum_yeri);

		// Doğum Yeri

		String nufkayil = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[2]/table/tbody/tr[5]/td[2]");

		mernisInfo.setNufusKayitliIl(nufkayil);

		// Mahalle Koy

		String mahalle_koy = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[2]/table/tbody/tr[6]/td[2]");

		mernisInfo.setMahalleKoy(mahalle_koy);

		// Verilis Tarihi

		String verilisTarihi = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[2]/table/tbody/tr[7]/td[2]");

		mernisInfo.setVerilisTarihi(verilisTarihi);

		// Verilen İlce

		String verilen_ilce = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[2]/table/tbody/tr[8]/td[2]");

		mernisInfo.setVerilenIlce(verilen_ilce);

		// Cuzdan Seri No

		String cuzdan_seri_no = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[2]/table/tbody/tr[9]/td[2]");
		mernisInfo.setCuzdanSeriNo(cuzdan_seri_no);

		// Cilt No

		String cilt_no = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[2]/table/tbody/tr[10]/td[2]");

		mernisInfo.setCiltNo(cilt_no);

		// Sıra No

		String sira_no = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[2]/table/tbody/tr[11]/td[2]");

		mernisInfo.setSiraNo(sira_no);

		// Onceki Soyadı

		String onceki_soyadi = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[2]/table/tbody/tr[12]/td[2]");

		mernisInfo.setOncekiSoyadi(onceki_soyadi);

		// Cinsiyeti

		String cinsiyeti = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[2]/table/tbody/tr[1]/td[4]");

		mernisInfo.setCinsiyeti(cinsiyeti);

		// Soyadi

		String soyadi = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[2]/table/tbody/tr[2]/td[4]");

		mernisInfo.setSoyadi(soyadi);

		// Ana Adi

		String ana_adi = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[2]/table/tbody/tr[3]/td[4]");

		mernisInfo.setAnaAdi(ana_adi);

		// Dogum Tarihi

		String dogum_tarihi = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[2]/table/tbody/tr[4]/td[4]");
		mernisInfo.setDogumTarihi(dogum_tarihi);

		// Nufus Kayıtlı İlce
		String nufusa_kayitli_ilce = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[2]/table/tbody/tr[5]/td[4]");
		mernisInfo.setNufusKayitliIlce(nufusa_kayitli_ilce);

		// Veriliş Nedeni

		String verilis_nedeni = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[2]/table/tbody/tr[6]/td[4]");

		mernisInfo.setVerilisNedeni(verilis_nedeni);

		// Verilen İl

		String verilen_id = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[2]/table/tbody/tr[7]/td[4]");

		mernisInfo.setVerilenIl(verilen_id);

		// Cuzdan No

		String cuzdan_no = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[2]/table/tbody/tr[9]/td[4]");

		mernisInfo.setCuzdanNo(cuzdan_no);

		// Aile Sıra No

		String aile_sira_no = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[2]/table/tbody/tr[10]/td[4]");

		mernisInfo.setAileSiraNo(aile_sira_no);

		// Dini

		String dini = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[2]/table/tbody/tr[11]/td[4]");

		mernisInfo.setDini(dini);

		// Olum Tarihi

		String olum_tarihi = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[2]/table/tbody/tr[12]/td[4]");

		mernisInfo.setOlumTarihi(olum_tarihi);

		// **********************************MERNİSE KAYITLI ADRES
		// BİLGİLERİ**********************************************

		// Adres Tipi

		String adres_tipi = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[3]/table/tbody/tr[1]/td[2]");

		mernisInfo.setAdresTipi(adres_tipi);

		// Taşınma Tarihi

		String tasinma_tarihi = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[3]/table/tbody/tr[2]/td[2]");

		mernisInfo.setTasinmaTarihi(tasinma_tarihi);

		// Mahalle

		String mahalle = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[3]/table/tbody/tr[3]/td[2]");

		mernisInfo.setMahalle(mahalle);

		// Dış Kapı No

		String dis_kapi_no = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[3]/table/tbody/tr[4]/td[2]");

		mernisInfo.setDisKapiNo(dis_kapi_no);

		// İl

		String il = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[3]/table/tbody/tr[5]/td[2]");

		mernisInfo.setIl(il);

		// Beyan Tarihi

		String beyan_tarihi = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[3]/table/tbody/tr[1]/td[4]");

		mernisInfo.setBeyanTarihi(beyan_tarihi);

		// Tescil Tarihi

		String tescil_tarihi = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[3]/table/tbody/tr[2]/td[4]");

		mernisInfo.setTescilTarihi(tescil_tarihi);

		// Cadde Sokak

		String cadde_sokak = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[3]/table/tbody/tr[3]/td[4]");

		mernisInfo.setCaddeSokak(cadde_sokak);

		// İç Kapı No

		String ic_kapi_no = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[3]/table/tbody/tr[4]/td[4]");

		mernisInfo.setIcKapiNo(ic_kapi_no);

		// İlçe

		String ilce = getWebElementValue("/html/body/div[4]/div[2]/div/div[1]/fieldset/fieldset[3]/table/tbody/tr[5]/td[4]");
		mernisInfo.setIcKapiNo(ilce);

		driver.switchTo().activeElement();
		clickElement("/html/body/div[4]/div[1]/button");

		Thread.sleep(1000);
		clickElement("/html/body/div[2]/div[1]/button");

		return mernisInfo;

	}

	public String getWebElementValue(String xPath) {
		String text = "";

		WebElement el = driver.findElement(By.xpath(xPath));

		text = el.getText();

		return text;
	}

	@SuppressWarnings("unused")
	public Boolean GiveTextForKeyboard(String text, Robot bot, int sleepTime)
			throws InterruptedException {

		int textLength = text.length();

		if (text.equals("TEST")) {

			bot.keyPress(KeyEvent.VK_T);
			bot.keyRelease(KeyEvent.VK_T);

			bot.keyPress(KeyEvent.VK_E);
			bot.keyRelease(KeyEvent.VK_E);

			bot.keyPress(KeyEvent.VK_S);
			bot.keyRelease(KeyEvent.VK_S);

			bot.keyPress(KeyEvent.VK_T);
			bot.keyRelease(KeyEvent.VK_T);
		}

		else if (text.equals("1250")) {

			bot.keyPress(KeyEvent.VK_1);
			bot.keyRelease(KeyEvent.VK_1);

			bot.keyPress(KeyEvent.VK_2);
			bot.keyRelease(KeyEvent.VK_2);

			bot.keyPress(KeyEvent.VK_5);
			bot.keyRelease(KeyEvent.VK_5);

			bot.keyPress(KeyEvent.VK_0);
			bot.keyRelease(KeyEvent.VK_0);

		}

		else if (text.equals("8790017566")) {

			bot.keyPress(KeyEvent.VK_8);
			bot.keyRelease(KeyEvent.VK_8);

			bot.keyPress(KeyEvent.VK_7);
			bot.keyRelease(KeyEvent.VK_7);

			bot.keyPress(KeyEvent.VK_9);
			bot.keyRelease(KeyEvent.VK_9);

			bot.keyPress(KeyEvent.VK_0);
			bot.keyRelease(KeyEvent.VK_0);

			bot.keyPress(KeyEvent.VK_0);
			bot.keyRelease(KeyEvent.VK_0);

			bot.keyPress(KeyEvent.VK_1);
			bot.keyRelease(KeyEvent.VK_1);

			bot.keyPress(KeyEvent.VK_7);
			bot.keyRelease(KeyEvent.VK_7);

			bot.keyPress(KeyEvent.VK_5);
			bot.keyRelease(KeyEvent.VK_5);

			bot.keyPress(KeyEvent.VK_6);
			bot.keyRelease(KeyEvent.VK_6);

			bot.keyPress(KeyEvent.VK_6);
			bot.keyRelease(KeyEvent.VK_6);

		}

		else if (text.equals("10900098120")) {

			bot.keyPress(KeyEvent.VK_1);
			bot.keyRelease(KeyEvent.VK_1);
			bot.keyPress(KeyEvent.VK_0);
			bot.keyRelease(KeyEvent.VK_0);

			bot.keyPress(KeyEvent.VK_9);
			bot.keyRelease(KeyEvent.VK_9);

			bot.keyPress(KeyEvent.VK_0);
			bot.keyRelease(KeyEvent.VK_0);

			bot.keyPress(KeyEvent.VK_0);
			bot.keyRelease(KeyEvent.VK_0);

			bot.keyPress(KeyEvent.VK_0);
			bot.keyRelease(KeyEvent.VK_0);

			bot.keyPress(KeyEvent.VK_9);
			bot.keyRelease(KeyEvent.VK_9);

			bot.keyPress(KeyEvent.VK_8);
			bot.keyRelease(KeyEvent.VK_8);

			bot.keyPress(KeyEvent.VK_1);
			bot.keyRelease(KeyEvent.VK_1);

			bot.keyPress(KeyEvent.VK_2);
			bot.keyRelease(KeyEvent.VK_2);
			bot.keyPress(KeyEvent.VK_0);
			bot.keyRelease(KeyEvent.VK_0);

		}

		Thread.sleep(sleepTime);

		return false;

	}

	public Boolean GiveCoordinatForMouse(int x, int y, int x2, int y2,
			int sleepTime, Robot bot, int mask, String ScrollEvent,
			int GecisSayisi) throws InterruptedException {

		if (ScrollEvent.equals("noScroll")) {
			bot.mouseMove(x, y);
			bot.mousePress(mask);
			bot.mouseRelease(mask);
			Thread.sleep(sleepTime);

		}

		if (ScrollEvent.equals("clickScroll")) {

			bot.mouseMove(x, y);

			for (int i = 0; i < GecisSayisi; i++) {

				bot.mousePress(mask);
				bot.mouseRelease(mask);
				Thread.sleep(100);
			}

		}

		if (ScrollEvent.equals("dragScroll")) {

			bot.mouseMove(x, y);
			bot.mousePress(mask);
			bot.mouseMove(x2, y2);
			bot.mouseRelease(mask);
			Thread.sleep(100);

		}

		return false;
	}

	public void setElementValueByXPath(String xPath, String value) {
		try {
			// if(driver.findElement(By.id(id)).isEnabled() &&
			// driver.findElement(By.id(id)).isDisplayed())
			driver.findElement(By.xpath(xPath)).sendKeys(value);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public Boolean HandleWindowAndMaximizeBrowser() {

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to
													// the next found window
													// handle (that's your newly
													// opened window)
			driver.manage().window().maximize();

		}

		return false;
	}

	public void clearValueByXpath(String xpath) {

		WebElement e = driver.findElement(By.xpath(xpath));
		e.clear();
	}

	@SuppressWarnings("unused")
	public MernisInfo searchEsas(String dosyaTuru, String dosyaYil,
			String dosyaNo) throws Exception {

		driver.switchTo().activeElement();

		Select dropdown = new Select(driver.findElement(By.id("dt")));
		dropdown.selectByVisibleText(dosyaTuru);
		String val = getWebElementValue("/html/body/div/form/fieldset/div[2]/input[1]");
		if (val != null) {
			clearValueByXpath("/html/body/div/form/fieldset/div[2]/input[1]");
		}
		setElementValueByXPath("/html/body/div/form/fieldset/div[2]/input[1]",
				dosyaYil);

		Thread.sleep(1000);
		String val2 = getWebElementValue("/html/body/div/form/fieldset/div[2]/input[2]");
		if (val2 != null) {
			clearValueByXpath("/html/body/div/form/fieldset/div[2]/input[2]");
		}

		setElementValueByXPath("/html/body/div/form/fieldset/div[2]/input[2]",
				dosyaNo);

		Thread.sleep(1000);
		clickElement("/html/body/div/form/fieldset/div[3]/input");

		Thread.sleep(3000);
		MernisInfo info = getMernisINFO();
		boolean a = true;

		return info;
	}

	@SuppressWarnings("unused")
	public void clickElement(String xPath) throws Exception {

		WebElement quitButton = null;
		boolean status = false;

		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
		quitButton = createWebElementByXpath(xPath);

		Actions actions = new Actions(driver);
		actions.moveToElement(quitButton).click().build().perform();
		System.out.println("Button is triggered");

	}

	public Boolean HandleWindowAndMaximize() throws InterruptedException {

		driver.findElement(By.className("ssoLink")).click(); // Uygulamaya Git
		Thread.sleep(5000);

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle); // switch focus of WebDriver to
													// the next found window
													// handle (that's your newly
													// opened window)
			driver.manage().window().maximize();

		}

		return false;
	}

	public WebElement createWebElementByXpath(String xPath) throws Exception {

		WebElement e = null;

		e = driver.findElement(By.xpath(xPath));
		while (e == null) {

			Thread.sleep(500);
			e = driver.findElement(By.xpath(xPath));
			if (e != null) {
				break;
			}
		}

		return e;
	}

	public void enterTheSearch() throws Exception {
		Thread.sleep(2000);
		executeAScript("menuAc('Esas', '/jsp/dosya_gor_jsp/esas_no_ile_sorgula.jsp?v=185', 30, 0);");

		Thread.sleep(4000);

		WebElement row = createWebElementByXpath("/html/body/div[1]/div[2]/div[2]/iframe[2]");
		driver.switchTo().frame(row);

	}

	public ArrayList<SearchEsas> getIcraDosyasiList() throws Exception {

		ArrayList<SearchEsas> listeForIcraDosyalari = new ArrayList<SearchEsas>();
		SearchEsas se = null;

		newConnectDB();
		String SQL = "SELECT icra_dosyasi_no FROM vwarama ;";
		Statement stm;
		ResultSet rs;
		stm = conn.createStatement();
		rs = stm.executeQuery(SQL);

		while (rs.next()) {

			String icraDosyasiNo = rs.getString("icra_dosyasi_no");

			if (icraDosyasiNo != "" || icraDosyasiNo != null) {

				se = new SearchEsas();

				int charForSlash = icraDosyasiNo.indexOf("/");
				String year = icraDosyasiNo.substring(0, charForSlash);
				String dosyaNo = icraDosyasiNo.substring(charForSlash + 1);
				se.setYear(year);
				se.setDosyaNo(dosyaNo);
				se.setSorguCmbbx("�cra Dosyas�");
				listeForIcraDosyalari.add(se);

			}

		}
		disconnectDB();

		return listeForIcraDosyalari;

	}

	public void runRobotForMernisArama(ArrayList<SearchEsas> icraDosyalari)
			throws Exception {

		ArrayList<MernisInfo> mList = new ArrayList<MernisInfo>();

		for (int i = 0; i < icraDosyalari.size(); i++) {

			Thread.sleep(4000);
			enterTheSearch();
			MernisInfo mernisInfo = searchEsas(icraDosyalari.get(i)
					.getSorguCmbbx(), icraDosyalari.get(i).getYear(),
					icraDosyalari.get(i).getDosyaNo());

			mList.add(mernisInfo);
			driver.switchTo().defaultContent();
			driver.navigate().refresh();

		}

		mernisListe = mList;

	}

	public void prepareForGetData(String dosyaTuru, String dosyaYil,
			String dosyaNo) throws Exception {

		driver.switchTo().activeElement();

		Select dropdown = new Select(driver.findElement(By.id("dt")));
		dropdown.selectByVisibleText(dosyaTuru);
		String val = getWebElementValue("/html/body/div/form/fieldset/div[2]/input[1]");
		if (val != null) {
			clearValueByXpath("/html/body/div/form/fieldset/div[2]/input[1]");
		}
		setElementValueByXPath("/html/body/div/form/fieldset/div[2]/input[1]",
				dosyaYil);

		Thread.sleep(1000);
		String val2 = getWebElementValue("/html/body/div/form/fieldset/div[2]/input[2]");
		if (val2 != null) {
			clearValueByXpath("/html/body/div/form/fieldset/div[2]/input[2]");
		}

		setElementValueByXPath("/html/body/div/form/fieldset/div[2]/input[2]",
				dosyaNo);

		Thread.sleep(1000);
		try {
			clickElement("/html/body/div/form/fieldset/div[3]/input");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread.sleep(3000);

	}

	public void openSgkCalisaniMenu() throws Exception {
		// if (a) {
		// driver.navigate().refresh();
		// }
		Thread.sleep(500);

		clickElement("/html/body/div[3]/div/fieldset/table/tbody/tr/td[5]/a");
		Thread.sleep(1000);
		clickElement("/html/body/div[3]/div[2]/div/ul/li[7]/a");
		Thread.sleep(1000);
		clickElement("/html/body/div[3]/div[2]/div/div[8]/table/tbody/tr/td[7]/a");

		Thread.sleep(1000);
		clickElement("/html/body/div[4]/div[2]/div/ul/li[2]/a");

		Thread.sleep(1000);
		clickElement("/html/body/div[4]/div[2]/div/div[3]/div[1]");
		Thread.sleep(1000);
	}

	public SgkCalisaniInfo getSgkCalisaniInfo() throws InterruptedException {

		SgkCalisaniInfo info = new SgkCalisaniInfo();

		// T.C. Kimlik No.
		String tcKimlikNo = getWebElementValue("/html/body/div[4]/div[2]/div/div[3]/div[4]/div[2]/table/tbody/tr[2]/td[2]");

		info.setTcKimlikNo(tcKimlikNo);
		Thread.sleep(200);

		// Adi
		String adi = getWebElementValue("/html/body/div[4]/div[2]/div/div[3]/div[4]/div[2]/table/tbody/tr[2]/td[4]");

		info.setAdi(adi);
		Thread.sleep(200);

		// Soyadi
		String soyadi = getWebElementValue("/html/body/div[4]/div[2]/div/div[3]/div[4]/div[2]/table/tbody/tr[3]/td[2]");

		info.setSoyadi(soyadi);
		Thread.sleep(200);

		// �lk Soyadi
		String ilkSoyadi = getWebElementValue("/html/body/div[4]/div[2]/div/div[3]/div[4]/div[2]/table/tbody/tr[3]/td[4]");

		info.setIlkSoyadi(ilkSoyadi);
		Thread.sleep(200);

		// Anne Adi
		String anneAdi = getWebElementValue("/html/body/div[4]/div[2]/div/div[3]/div[4]/div[2]/table/tbody/tr[4]/td[2]");

		info.setAnneAdi(anneAdi);
		Thread.sleep(200);

		// Baba Adi
		String babaAdi = getWebElementValue("/html/body/div[4]/div[2]/div/div[3]/div[4]/div[2]/table/tbody/tr[4]/td[4]");
		info.setBabaAdi(babaAdi);
		Thread.sleep(200);

		// Do�um Yeri
		String dogumYeri = getWebElementValue("/html/body/div[4]/div[2]/div/div[3]/div[4]/div[2]/table/tbody/tr[5]/td[2]");
		info.setDogumYeri(dogumYeri);
		Thread.sleep(200);

		// Do�um Tarihi
		String dogumTarihi = getWebElementValue("/html/body/div[4]/div[2]/div/div[3]/div[4]/div[2]/table/tbody/tr[5]/td[4]");
		info.setDogumTarihi(dogumTarihi);
		Thread.sleep(200);

		// �� Yeri Unvan�
		String isYeriUnvani = getWebElementValue("/html/body/div[4]/div[2]/div/div[3]/div[4]/div[2]/table/tbody/tr[6]/td[2]");
		info.setDogumTarihi(isYeriUnvani);
		Thread.sleep(200);

		// �� Yeri Adresi
		String isYeriAdresi = getWebElementValue("/html/body/div[4]/div[2]/div/div[3]/div[4]/div[2]/table/tbody/tr[6]/td[4]");
		info.setIsYeriAdresi(isYeriAdresi);
		Thread.sleep(200);

		// �� Yeri Il
		String isYeriIl = getWebElementValue("/html/body/div[4]/div[2]/div/div[3]/div[4]/div[2]/table/tbody/tr[7]/td[2]");
		info.setIsYeriIl(isYeriIl);
		Thread.sleep(200);

		// �� Yeri Sicil
		String isYeriSicil = getWebElementValue("/html/body/div[4]/div[2]/div/div[3]/div[4]/div[2]/table/tbody/tr[7]/td[4]");
		info.setIsYeriSicil(isYeriSicil);
		Thread.sleep(200);

		// D�nem Y�l Ay
		String donemYilAy = getWebElementValue("/html/body/div[4]/div[2]/div/div[3]/div[4]/div[2]/table/tbody/tr[8]/td[2]");
		info.setDonemYilAy(donemYilAy);
		Thread.sleep(200);

		// Durum
		String durum = getWebElementValue("/html/body/div[4]/div[2]/div/div[3]/div[4]/div[2]/table/tbody/tr[8]/td[4]");
		info.setDurum(durum);
		Thread.sleep(200);

		// Sicil No
		String sicilNo = getWebElementValue("/html/body/div[4]/div[2]/div/div[3]/div[4]/div[2]/table/tbody/tr[9]/td[2]");
		info.setSicilNo(sicilNo);
		Thread.sleep(200);

		// �lk ��e Giri� Tarihi
		String ilkIseGirisTarihi = getWebElementValue("/html/body/div[4]/div[2]/div/div[3]/div[4]/div[2]/table/tbody/tr[9]/td[4]");
		info.setIlkIseGirisTarihi(ilkIseGirisTarihi);
		Thread.sleep(200);

		// Son ��e Giri� Tarihi
		String sonIseGirisTarihi = getWebElementValue("/html/body/div[4]/div[2]/div/div[3]/div[4]/div[2]/table/tbody/tr[10]/td[2]");
		info.setSonIseGirisTarihi(sonIseGirisTarihi);
		Thread.sleep(200);

		// Son ��ten ��k�� T
		String sonIstenCikisTarihi = getWebElementValue("/html/body/div[4]/div[2]/div/div[3]/div[4]/div[2]/table/tbody/tr[10]/td[4]");
		info.setSonIstenCikisTarihi(sonIstenCikisTarihi);
		Thread.sleep(200);

		return info;
	}

	public SgkCalisaniInfo searchSgkCalisani(String dosyaTuru, String dosyaYil,
			String dosyaNo) throws Exception {

		SgkCalisaniInfo sgkCalisaniInfo = new SgkCalisaniInfo();

		prepareForGetData(dosyaTuru, dosyaYil, dosyaNo);
		openSgkCalisaniMenu();
		sgkCalisaniInfo = getSgkCalisaniInfo();

		return sgkCalisaniInfo;

	}

	public Boolean getSgkCalisani(ArrayList<SearchEsas> icraDosyalari)
			throws Exception {

		mSgkCalisaniListe = new ArrayList<SgkCalisaniInfo>();

		for (int i = 0; i < icraDosyalari.size(); i++) {

			Thread.sleep(4000);
			enterTheSearch();

			SgkCalisaniInfo sgkCalisaniInfo = searchSgkCalisani(icraDosyalari
					.get(i).getSorguCmbbx(), icraDosyalari.get(i).getYear(),
					icraDosyalari.get(i).getDosyaNo());

			mSgkCalisaniListe.add(sgkCalisaniInfo);
			driver.switchTo().defaultContent();
			driver.navigate().refresh();

		}

		return false;
	}

	@SuppressWarnings("unused")
	public Boolean runRobotForSgkArama(ArrayList<SearchEsas> icraDosyalari)
			throws Exception {

		Boolean sgkCalisaniResult = getSgkCalisani(icraDosyalari);

		// SgkEmeklisi
		// BagkurCalisani
		// BagkurEmeklisi i�in ayr� methodlar uretilecek

		return false;
	}

	public Boolean startEsasArama() throws Exception {

		// �cra Dosyalar�n�n Veritaban�ndan Al�nmas�
		icraDosyalari = getIcraDosyasiList();

		// Veritaban�ndan al�nan icra dosyalar�na gore robot
		// cal�st�t�larak
		// Arraylist'e push edilmesi
		runRobotForMernisArama(icraDosyalari);
		runRobotForSgkArama(icraDosyalari);

		return false;
	}

}
