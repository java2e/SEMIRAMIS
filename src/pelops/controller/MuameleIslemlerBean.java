package pelops.controller;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.StreamedContent;
import pelops.dao.MuameleIslemleriDAO;
import pelops.dao.PostaDAO;
import pelops.interfaces.ReportCRUDInterface;
import pelops.model.Avukat;
import pelops.model.DortluYapi;
import pelops.model.Ilce;
import pelops.model.MuameleIslemleri;
import pelops.model.Posta;
import pelops.model.StandartTalep;
import pelops.model.TebligatZarfi;
import pelops.muameleislemleri.util.BankaModel;
import pelops.muameleislemleri.util.GayrimenkulModel;
import pelops.muameleislemleri.util.KurumModel;
import pelops.muameleislemleri.util.MuameleIslemleriRequireCtrl;
import java.io.InputStream;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@ManagedBean(name = "muameleislemlerbean")
@SessionScoped
public class MuameleIslemlerBean implements ReportCRUDInterface {

	MuameleIslemleriDAO dao = new MuameleIslemleriDAO();
	private ArrayList<MuameleIslemleri> muameleList;
	MuameleIslemleri muamele = new MuameleIslemleri();
	TalepMuzekkereUtil util = new TalepMuzekkereUtil();
	private MuameleIslemleri muameleIslemleri;
	private JasperPrint jasperPrint;
	private JasperPrint jasperPrint2;
	private boolean panel1Visible = true;
	private boolean panel2Visible = false;
	private boolean onizleButtonVisible = true;
	private int whichContentType = 1;
	private int content_id = 0;
	String pdf;
	String path;
	int duzenleID;
	int onizleDuzenleID;
	private String contentAdi = "";
	private StreamedContent content;
	private TebligatZarfi tebligatZarfi = new TebligatZarfi();
	private String muzekkereTalep = "";
	private String pdfName = "";
	private static String pdfContent = "";
	String gelisAmaci;
	ArrayList<StandartTalep> standartTalepList;
	DortluYapi yapi = new DortluYapi();
	JasperPrint talepMuzekkere = new JasperPrint();
	JasperPrint tebligat = new JasperPrint();
	JasperPrint cokluBanka4 = new JasperPrint();
	JasperPrint cokluBanka7 = new JasperPrint();
	JasperPrint cokluBanka5 = new JasperPrint();
	ArrayList<JasperPrint> list = new ArrayList<JasperPrint>();
	TalepMuzekkereUtil tmUtil = new TalepMuzekkereUtil();

	public MuameleIslemlerBean() throws Exception {

		this.setIptalrender(false);
		this.setduzenlesilrender(false);
		muamele.setStatus(0);
		muamele.setBorcluAdi(AktifBean.getBorcluAdi());

		// Kullanıcı Adı otomatik gelecek

		// Hazırlayan Kişi Kullanıcı aynı kişi

		muamele.setIcraDosyaNo(AktifBean.icraDosyaNo);

		standartTalepList = new ArrayList<>();
		standartTalepList = dao.getStandartTalepTextList();

		muamele.setMuameleTarihi(util.getCurrentDate());

		// Listenin Getirilmesi sağlanır±r.
		muameleList = TümListeyiGetir();
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public String getPdf() {

		if (muameleList.isEmpty()) {

			muzekkereTalep = "default";
			pdf = "./pdfler/" + muzekkereTalep + ".pdf?pfdrid_c=true";

		} else {

			pdf = "./pdfler/" + muzekkereTalep + ".pdf?pfdrid_c=true";
			
		}
		return pdf;
	}

	// Modularize edilecek
	public void onMasrafTipiChange() {

		switch (muamele.getMasrafTipiId()) {

		case 1:
			muamele.setMasrafMiktari(10);
			break;

		case 2:

			muamele.setMasrafMiktari(20);
			break;

		case 3:

			muamele.setMasrafMiktari(30);
			break;

		case 4:

			muamele.setMasrafMiktari(40);
			break;
		case 5:

			muamele.setMasrafMiktari(50);
			break;

		}

	}

	public String barkodUret() throws Exception {

		String barkod = null;
		Posta posta = null;
		PostaDAO postaDAO = new PostaDAO();
		String uretilenBarkod = "";

		barkod = postaDAO.checkBarkod(muamele.getId());
		posta = postaDAO.BarkodVer();

		if (barkod == null || muamele.getBarcode() == "") {

			uretilenBarkod = posta.getBarkod();
			posta.setDurum(1);
			posta.setIcra_dosya_id(muamele.getId());
			postaDAO.Duzenle(posta);

		} else {
			uretilenBarkod = barkod;
		}

		return uretilenBarkod;

	}

	public void Yazdir() throws Exception {

//		String uretilenBarkod = barkodUret();
//		Duzenle();

	}


	public void PdfOnizle() throws Exception {

		Kaydet();
		Duzenle();
		muamele = dao.getMuameleFromList(onizleDuzenleID);
		// OnizleAndKaydet();

	}

	public String talepMuzekkereismiBul(String isim) {

		String deger = null;
		switch (isim) {
		case "103davetiyesitalebi":
			deger = "103 Davetiyesi Talebi";
			break;
		case "103davetiyesimuzekkeresi":
			deger = "103 Davetiyesi Müzekkeresi";
			break;
		case "adresarastirmatalebi":
			deger = "Adres Araştırması Talebi";
			break;
		case "dosyaislemdenkaldirilmatalebi":
			deger = "Dosya İşlemden Kaldırma Talebi";
			break;
		case "feragattalebi":
			deger = "Feragat Talebi";
			break;
		case "hacizihbarnamesimuzekkere":
			deger = "Haciz İhbarnamesi Müzekkeresi";
			break;
		case "hacizihbarnametalebi":
			deger = "Haciz İhbarname Talebi";
			break;
		case "menkulhaciztalebi":
			deger = "Menku Haciz Talebi";
			break;
		case "mernisadresineodemeemritalebi":
			deger = "Mernis Adresi Ödeme Emri Talebi";
		case "mevduathaczitalebi":
			deger = "Mevduat Haczi Talebi";
			break;
		case "odeme_emri_faizsiz":
			deger = "Ã–deme Emri Faizsiz";
			break;
		case "odeme_emri_masrafsiz":
			deger = "Ödeme Emri Masrafsız";
			break;
		case "odeme_emri_vergisiz":
			deger = "Ödeme Emri Vergisiz";
			break;
		case "odeme_emri":
			deger = "Ödeme Emri";
			break;
		case "takip_talebi_1":
			deger = "Takip Talebi";
			break;
		case "takip_talebi_faizsiz":
			deger = "Takip Talebi Faizsiz";
			break;
		case "takip_talebi_masrafsiz":
			deger = "Takip Talebi MasrafsÄ±z";
			break;
		case "takip_talebi_temerrutsuz":
			deger = "Takip Talebi Tememrrütsüz";
			break;
		case "takip_talebi_vergisiz":
			deger = "Takip Talebi Vergisiz";
			break;
		case "tapu_sicil":
			deger = "Tapu Sicil";
			break;
		case "tebligat_listesi":
			deger = "Tebligat Listesi";
			break;
		case "tebligat_zarfi":
			deger = "Tebligat Zarfı";
			break;
		case "tk21talebi":
			deger = "TK/21 Talebi";
			break;
		case "vekalet":
			deger = "Vekalet";
			break;
		}

		return deger;
	}

	public JasperPrint tebligatZarfiJasper(MuameleIslemleri muamele, String muzekkereTalep) throws Exception {

		ArrayList<TebligatZarfi> dataBeanListForTebligat = new ArrayList<TebligatZarfi>();
		TebligatZarfi zarf = new TebligatZarfi();
		zarf.setBorcluAdi(muamele.getBorcluAdi());
		zarf.setBorcluAdres(muamele.getBorcluAdresi());
		zarf.setIcraDosyaNo(muamele.getIcraDosyaNo());
		zarf.setIcraMudurluguAdi(muamele.getIcraMudurluguAdi());
		zarf.setAlacakliAdi(AktifBean.getMuvekkilAdi());
		zarf.setAvukatAdi(muamele.getAvukatAdi());
		zarf.setMuzekkereTalepAdi(muzekkereTalep);

		dataBeanListForTebligat.add(zarf);

		HashMap<String, Object> parameters = new HashMap<String, Object>();

		String pathName = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/reports/talep_muzekkereler/tebligat_zarfi.jrxml");
		InputStream inputStream = new FileInputStream(pathName);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanListForTebligat);
		JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		jasperPrint2 = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);

		return jasperPrint2;

	}

	public java.sql.Date convertFromJAVADateToSQLDate(java.util.Date javaDate) {
		java.sql.Date sqlDate = null;
		if (javaDate != null) {
			sqlDate = new Date(javaDate.getTime());
		}
		return sqlDate;
	}

	public void display() {
		panel2Visible = false;
		panel1Visible = true;
	}

	public void display2() {
		panel1Visible = false;
		panel2Visible = true;
	}

	public boolean isPanel1Visible() {
		return panel1Visible;
	}

	public void setPanel1Visible(boolean panel1Visible) {
		this.panel1Visible = panel1Visible;
	}

	public boolean isPanel2Visible() {
		return panel2Visible;
	}

	public void setPanel2Visible(boolean panel2Visible) {
		this.panel2Visible = panel2Visible;
	}

	public MuameleIslemleri getMuameleIslemleri() throws Exception {

		muameleList = TümListeyiGetir();
		return muameleIslemleri;
	}

	public void setMuameleIslemleri(MuameleIslemleri muameleIslemleri) {
		this.muameleIslemleri = muameleIslemleri;
	}

	public int getWhichContentType() {
		return whichContentType;
	}

	public void setWhichContentType(int whichContentType) {
		this.whichContentType = whichContentType;
	}

	boolean iptalrender, duzenlesilrender;

	public boolean isDuzenlesilrender() {
		return duzenlesilrender;
	}

	public void setDuzenlesilrender(boolean duzenlesilrender) {
		this.duzenlesilrender = duzenlesilrender;
	}

	public boolean isIptalrender() {
		return iptalrender;
	}

	public void setIptalrender(boolean iptalrender) {
		this.iptalrender = iptalrender;
	}

	public boolean isduzenlesilrender() {
		return duzenlesilrender;
	}

	public void setduzenlesilrender(boolean duzenlesilrender) {
		this.duzenlesilrender = duzenlesilrender;
	}

	public boolean isOnizleButtonVisible() {
		return onizleButtonVisible;
	}

	public void setOnizleButtonVisible(boolean onizleButtonVisible) {
		this.onizleButtonVisible = onizleButtonVisible;
	}

	public ArrayList<MuameleIslemleri> getMuameleList() throws Exception {

		return muameleList;
	}

	public void setMuameleList(ArrayList<MuameleIslemleri> muameleList) {
		this.muameleList = muameleList;
	}

	public MuameleIslemleri getMuamele() {
		return muamele;
	}

	public void setMuamele(MuameleIslemleri muamele) {
		this.muamele = muamele;
	}

	public MuameleIslemleriDAO getDao() {
		return dao;
	}

	public void setDao(MuameleIslemleriDAO dao) {
		this.dao = dao;
	}

	public int getContent_id() {
		return content_id;
	}

	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}

	public String getContentAdi() {
		return contentAdi;
	}

	public void setContentAdi(String contentAdi) {
		this.contentAdi = contentAdi;
	}

	public TebligatZarfi getTebligatZarfi() {
		return tebligatZarfi;
	}

	public void setTebligatZarfi(TebligatZarfi tebligatZarfi) {
		this.tebligatZarfi = tebligatZarfi;
	}

	public StreamedContent getContent() {
		return content;
	}

	public void setContent(StreamedContent content) {
		this.content = content;
	}

	public static String getPdfContent() {
		return pdfContent;
	}

	public static void setPdfContent(String pdfContent) {
		MuameleIslemlerBean.pdfContent = pdfContent;
	}

	public String getPdfName() {
		return pdfName;
	}

	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}

	@Override
	public void Kaydet() throws Exception {

		muameleList = new ArrayList<>();
		TalepMuzekkereUtil talepMuzekkereUtil = new TalepMuzekkereUtil();

		// Seçilen Talep ve Müzekkerelerin nesneye aktarılması
		muameleList = talepMuzekkereUtil.TalepMuzekkereListesiOlustur(muamele, kurumList, bankaList, gayrimenkulList);

		// Bilgilerin DB'ye aktarılması sağlanır.
		dao.MuzekkereTalepEkle(muamele, muameleList);

		// Listenin Yinelenmesi sağlanır
		muameleList = TümListeyiGetir();
	}

	public ArrayList<Avukat> AvukatGetir() throws Exception {

		ArrayList<Avukat> avukatList = new ArrayList<Avukat>();
		avukatList = dao.AvukatListesiGetir();
		return avukatList;

	}

	public JasperPrint OnizleAndKaydet() throws FileNotFoundException, JRException, InterruptedException, SQLException {

		// Müzekkereler

		muamele.setMevduathaczimuzekkeresi(false);
		muamele.setAdresarastirmamuzekkeresikurumlaricin(false);
		muamele.setDavetiyemuzekkeresi103menkul(false);
		muamele.setDavetiyemuzekkeresi103gayrimenkul(false);
		muamele.setDavetiyemuzekkeresi103arac(false);
		muamele.setDavetiyemuzekkeresi103sgk(false);
		muamele.setHacizihbarnamesimuzekkeresi891(false);
		muamele.setMaashacizmuzekkeresigenel(false);
		muamele.setMaashacizmuzekkeresimuvafakat(false);
		muamele.setPtthacizmuzekkeresi(false);
		muamele.setTapuhacizmuzekkeresinokta(false);
		muamele.setGsmmuzekkeresiturkcell(false);
		muamele.setGsmmuzekkeresiturktelekom(false);
		muamele.setGsmmuzekkeresivodafone(false);

		// Talepler
		muamele.setDosyaislemdenkaldirilmatalebiharcburoda(false);
		muamele.setDosyaislemdenkaldirilmatalebiharcborcluda(false);
		muamele.setAracserhitalebi(false);
		muamele.setAracyakalamatalebi(false);
		muamele.setMaashaciztalebimuvafakat(false);
		muamele.setTapuhaciztalebi(false);
		muamele.setAilekayittablosutalebi(false);
		muamele.setMaashaciztalebigenel(false);
		muamele.setPtthaciztalebi(false);
		muamele.setDavetiyetalebi103(false);
		muamele.setAdresarastimatalebi(false);
		muamele.setArachaczitalebi(false);
		muamele.setDosyaislemdenkaldirilmatalebi(false);
		muamele.setFeragattalebi(false);
		muamele.setHacizihbarnamesitalebibankalaricin(false);
		muamele.setKapanistalebiharcborcludatalebi(false);
		muamele.setKapanistalebiharcburodatalebi(false);
		muamele.setMenkulhaciztalebi(false);
		muamele.setMernisadresineodemeemritalebi(false);
		muamele.setMevduathaczitalebi(false);
		muamele.setSgkadresiodemeemritalebi(false);
		muamele.setTicaretsiciladressormatalebi(false);
		muamele.setTk21talebi(false);
		muamele.setYenilemetalebi(false);
		muamele.setYurticiadresiodemeemritalebi(false);

		// Müzekkere Visible
		muamele.setAilekayittablosutalebiVisible(true);
		muamele.setMevduathaczimuzekkeresiVisible(true);
		muamele.setAdresarastirmamuzekkeresikurumlaricinVisible(true);
		muamele.setDavetiyemuzekkeresi103menkulVisible(true);
		muamele.setDavetiyemuzekkeresi103gayrimenkulVisible(true);
		muamele.setDavetiyemuzekkeresi103aracVisible(true);
		muamele.setDavetiyemuzekkeresi103sgkVisible(true);
		muamele.setHacizihbarnamesimuzekkeresi891Visible(true);
		muamele.setMaashacizmuzekkeresigenelVisible(true);
		muamele.setMaashacizmuzekkeresimuvafakatVisible(true);
		muamele.setPtthacizmuzekkeresiVisible(true);
		muamele.setTapuhacizmuzekkeresinoktaVisible(true);
		muamele.setGsmmuzekkeresiturkcellVisible(true);
		muamele.setGsmmuzekkeresiturktelekomVisible(true);
		muamele.setGsmmuzekkeresivodafoneVisible(true);

		// Talep Visible
		muamele.setDosyaislemdenkaldirilmatalebiharcburodaVisible(true);
		muamele.setDosyaislemdenkaldirilmatalebiharcborcludaVisible(true);
		muamele.setAracserhitalebiVisible(true);
		muamele.setAracyakalamatalebiVisible(true);
		muamele.setMaashaciztalebimuvafakatVisible(true);
		muamele.setTapuhaciztalebiVisible(true);
		muamele.setMaashaciztalebigenelVisible(true);
		muamele.setPtthaciztalebiVisible(true);
		muamele.setDavetiyetalebi103Visible(true);
		muamele.setAdresarastimatalebiVisible(true);
		muamele.setArachaczitalebiVisible(true);
		muamele.setDosyaislemdenkaldirilmatalebiVisible(true);
		muamele.setFeragattalebiVisible(true);
		muamele.setHacizihbarnamesitalebibankalaricinVisible(true);
		muamele.setKapanistalebiharcborcludatalebiVisible(true);
		muamele.setKapanistalebiharcburodatalebiVisible(true);
		muamele.setMenkulhaciztalebiVisible(true);
		muamele.setMernisadresineodemeemritalebiVisible(true);
		muamele.setMevduathaczitalebiVisible(true);
		muamele.setSgkadresiodemeemritalebiVisible(true);
		muamele.setTicaretsiciladressormatalebiVisible(true);
		muamele.setTk21talebiVisible(true);
		muamele.setYenilemetalebiVisible(true);
		muamele.setYurticiadresiodemeemritalebiVisible(true);

		onizleButtonVisible = false;

		switch (muzekkereTalep) {

		// ******************************************************************************

		case "103 Davetiyesi Müzekkeresi(Araç)":

			muzekkereTalep = "103davetiyesimuzekkeresiarac";
			muamele.setDavetiyemuzekkeresi103arac(true);
			muamele.setDavetiyemuzekkeresi103aracVisible(false);
			break;

		case "103 Davetiyesi Müzekkeresi(SGK)":

			muzekkereTalep = "103davetiyesimuzekkeresisgk";
			muamele.setDavetiyemuzekkeresi103sgk(true);
			muamele.setDavetiyemuzekkeresi103sgkVisible(false);
			break;

		case "103 Davetiyesi Müzekkeresi(Gayrimenkul)":

			muzekkereTalep = "103davetiyesimuzekkeresigayrimenkul";
			muamele.setDavetiyemuzekkeresi103gayrimenkul(true);
			muamele.setDavetiyemuzekkeresi103gayrimenkulVisible(false);
			break;

		case "103 Davetiyesi Müzekkeresi(Menkul)":

			muzekkereTalep = "103davetiyesimuzekkeresimenkul";
			muamele.setDavetiyemuzekkeresi103menkul(true);
			muamele.setDavetiyemuzekkeresi103menkulVisible(false);
			break;

		case "Adres Araştırma Müzekkeresi(Kurumlar İçin)":

			muzekkereTalep = "adresarastirmamuzekkeresikurumlaricin";
			muamele.setAdresarastirmamuzekkeresikurumlaricin(true);
			muamele.setAdresarastirmamuzekkeresikurumlaricinVisible(false);
			break;

		case "Haciz İhbarnamesi Müzekkeresi(89/1)":

			muzekkereTalep = "hacizihbarnamesimuzekkeresi891";
			muamele.setHacizihbarnamesimuzekkeresi891(true);
			muamele.setHacizihbarnamesimuzekkeresi891Visible(false);

			break;

		case "Maaş Haciz Müzekkeresi(Genel)":

			muzekkereTalep = "maashacizmuzekkeresigenel";
			muamele.setMaashacizmuzekkeresigenel(true);
			muamele.setMaashacizmuzekkeresigenelVisible(false);

			break;

		case "Maaş Haciz Müzekkeresi(Muvafakat)":

			muzekkereTalep = "maashacizmuzekkeresimuvafakat";
			muamele.setMaashacizmuzekkeresimuvafakat(true);
			muamele.setMaashacizmuzekkeresimuvafakatVisible(false);
			break;

		case "PTT Haciz Müzekkeresi":

			muzekkereTalep = "ptthacizmuzekkeresi";
			muamele.setPtthacizmuzekkeresi(true);
			muamele.setPtthacizmuzekkeresiVisible(false);

			break;

		case "Tapu Haciz Müzekkeresi(Nokta)":

			muzekkereTalep = "tapuhacizmuzekkeresinokta";
			muamele.setTapuhacizmuzekkeresinokta(true);
			muamele.setTapuhacizmuzekkeresinoktaVisible(false);
			break;

		case "Mevduat Haczi Müzekkeresi":

			muzekkereTalep = "mevduathaczimuzekkeresi";
			muamele.setMevduathaczimuzekkeresi(true);
			muamele.setMevduathaczimuzekkeresiVisible(false);
			break;

		// ******************************************************************************

		case "103 Davetiyesi Talebi":

			muzekkereTalep = "103davetiyesitalebi";
			muamele.setDavetiyemuzekkeresi103(true);
			muamele.setDavetiyemuzekkeresi103Visible(false);
			break;

		case "Maaş Haciz Talebi(Genel)":

			muzekkereTalep = "maashaciztalebigenel";
			muamele.setMaashaciztalebigenel(true);
			muamele.setMaashaciztalebigenelVisible(false);

			// SGK Standart text'in alınması sağlanır
			yapi = dao.dortluYapiGetir();
			muamele.setSgk_standart_text(yapi.getSgk_standart_text());

			break;

		case "Maaş Haciz Talebi(Muvafakat)":

			muzekkereTalep = "maashaciztalebimuvafakat";
			muamele.setMaashaciztalebimuvafakat(true);
			muamele.setMaashaciztalebimuvafakatVisible(false);
			break;

		case "Haciz İhbarnamesi Talebi(Bankalar İçin)":

			muzekkereTalep = "hacizihbarnamesitalebibankalaricin";
			muamele.setHacizihbarnamesitalebibankalaricin(true);
			muamele.setHacizihbarnamesitalebibankalaricinVisible(false);
			break;

		case "PTT Haciz Talebi":

			muzekkereTalep = "ptthaciztalebi";
			muamele.setPtthaciztalebi(true);
			muamele.setPtthaciztalebiVisible(false);

			// Standart Text'lerin getirilmesi sağlanır.
			yapi = dao.dortluYapiGetir();
			muamele.setEgm_standart_text(yapi.getEgm_standart_text());
			muamele.setSgk_standart_text(yapi.getSgk_standart_text());
			muamele.setTapu_standart_text(yapi.getTapu_standart_text());
			muamele.setPosta_standart_text(yapi.getPosta_standart_text());

			break;

		case "Aile Kayıt Tablosu Talebi":

			muzekkereTalep = "ailekayittablosutalebi";
			muamele.setAilekayittablosutalebi(true);
			muamele.setAilekayittablosutalebiVisible(false);

			break;

		case "Tapu Haciz Talebi(Nokta)":

			muzekkereTalep = "tapuhaciztalebinokta";
			muamele.setTapuhaciztalebi(true);
			muamele.setTapuhaciztalebiVisible(false);

			// Standart Text'lerin getirilmesi sağlanır.
			yapi = dao.dortluYapiGetir();
			muamele.setEgm_standart_text(yapi.getEgm_standart_text());
			muamele.setSgk_standart_text(yapi.getSgk_standart_text());
			muamele.setTapu_standart_text(yapi.getTapu_standart_text());
			muamele.setPosta_standart_text(yapi.getPosta_standart_text());

			break;

		case "Adres Araştırma Talebi":

			muzekkereTalep = "adresarastirmatalebi";
			muamele.setAdresarastimatalebi(true);
			muamele.setAdresarastimatalebiVisible(false);
			break;

		case "Araç Haczi Talebi":

			muzekkereTalep = "arachaczitalebi";
			muamele.setArachaczitalebi(true);
			muamele.setArachaczitalebiVisible(false);
			break;

		case "Araç Yakalama Talebi":
			muzekkereTalep = "aracyakalamatalebi";
			muamele.setAracyakalamatalebi(true);
			muamele.setAracyakalamatalebiVisible(false);
			break;

		case "Araç Şerhi Talebi":

			muzekkereTalep = "aracserhitalebi";
			muamele.setAracserhitalebi(true);
			muamele.setAracserhitalebiVisible(false);
			break;

		case "Dosya İşlemden Kaldırılma Talebi(Harç Borçluda)":

			muzekkereTalep = "dosyaislemdenkaldirilmatalebiferagatnedeniyleharcborcluda";
			muamele.setDosyaislemdenkaldirilmatalebiharcborcluda(true);
			muamele.setDosyaislemdenkaldirilmatalebiharcborcludaVisible(false);
			break;

		case "Dosya İşlemden Kaldırılma Talebi(Harç Büroda)":

			muzekkereTalep = "dosyaislemdenkaldirilmatalebiferagatnedeniyleharcburoda";
			muamele.setDosyaislemdenkaldirilmatalebiharcburoda(true);
			muamele.setDosyaislemdenkaldirilmatalebiharcburodaVisible(false);
			break;

		case "Feragat Talebi":

			muzekkereTalep = "feragattalebi";
			muamele.setFeragattalebi(true);
			muamele.setFeragattalebiVisible(false);
			break;

		case "Kapanış Harç Borçluda Talebi":

			muzekkereTalep = "kapanistalebiharcborcludatalebi";
			muamele.setKapanistalebiharcborcludatalebi(true);
			muamele.setKapanistalebiharcborcludatalebiVisible(false);
			break;

		case "Kapanış Harç Büroda Talebi":

			muzekkereTalep = "kapanistalebiharcburodatalebi";
			muamele.setKapanistalebiharcburodatalebi(true);
			muamele.setKapanistalebiharcburodatalebiVisible(false);
			break;

		case "Menkul Haciz Talebi":

			muzekkereTalep = "menkulhaciztalebi";
			muamele.setMenkulhaciztalebi(true);
			muamele.setMenkulhaciztalebiVisible(false);
			break;

		case "Mernis Adresi Ödeme Emri Talebi":

			muzekkereTalep = "mernisadresineodemeemritalebi";
			muamele.setMernisadresineodemeemritalebi(true);
			muamele.setMernisadresineodemeemritalebiVisible(false);
			break;

		case "Mevduat Haczi Talebi":

			muzekkereTalep = "mevduathaczitalebi";
			muamele.setMevduathaczitalebi(true);
			muamele.setMevduathaczitalebiVisible(false);
			break;

		case "SGK Adresi Ödeme Emri Talebi":

			muzekkereTalep = "sgkadresiodemeemritalebi";
			muamele.setSgkadresiodemeemritalebi(true);
			muamele.setSgkadresiodemeemritalebiVisible(false);
			break;

		case "Ticaret Sicil Adresi Sorma Talebi":

			muzekkereTalep = "ticaretsiciladressormatalebi";
			muamele.setTicaretsiciladressormatalebi(true);
			muamele.setTicaretsiciladressormatalebiVisible(false);
			break;

		case "TK/21 Talebi":

			muzekkereTalep = "tk21talebi";
			muamele.setTk21talebi(true);
			muamele.setTk21talebiVisible(false);
			break;

		case "Yenileme Talebi":

			muzekkereTalep = "yenilemetalebi";
			muamele.setYenilemetalebi(true);
			muamele.setYenilemetalebiVisible(false);
			break;

		case "Yurtiçi Adresi Ödeme Emri Talebi":

			muzekkereTalep = "yurticiadresiodemeemritalebi";
			muamele.setYurticiadresiodemeemritalebi(true);
			muamele.setYurticiadresiodemeemritalebiVisible(false);
			break;

		default:
			break;
		}

		// ******************************************************************************

		ArrayList<MuameleIslemleri> dataBeanList = new ArrayList<MuameleIslemleri>();

		dataBeanList.add(muamele);

		HashMap<String, Object> parameters = new HashMap<String, Object>();

		String pathName = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/reports/talep_muzekkereler/" + muzekkereTalep + ".jrxml");

		InputStream inputStream = new FileInputStream(pathName);

		path = "C:/apache-tomcat-8.0.30/webapps/SEMIRAMIS/pdfler/"
				+ muzekkereTalep + ".pdf";

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
		JasperDesign jasperDesign = new JasperDesign();
		jasperDesign = JRXmlLoader.load(inputStream);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
		// JasperExportManager.exportReportToPdfFile(jasperPrint, path);

		return jasperPrint;

	}

	@Override
	public void Duzenle() throws Exception {

		ArrayList<JasperPrint> list = new ArrayList<JasperPrint>();

		gelisAmaci = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("gelis_amaci")
				.toString());

		if (gelisAmaci.equals("duzenle")) {

			duzenleID = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
					.get("buttonDuzenle").toString());

			muzekkereTalep = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
					.get("muzekkeretalep").toString());

		}

		// Önizleme ve Düzenleme Metotlarından Ulaşım için Yapıldı
		onizleDuzenleID = duzenleID;

		// Önizlemede Kullanılacak
		muamele = dao.getMuameleFromList(onizleDuzenleID);

		// Önizleme yapılırken text'lerin yeniden gelmesi için sağlanır
		if (muzekkereTalep.equals("ptthaciztalebi") || muzekkereTalep.equals("maashaciztalebigenel")
				|| muzekkereTalep.equals("tapuhaciztalebinokta")) {

			yapi = dao.dortluYapiGetir();
			muamele.setSgk_standart_text(yapi.getSgk_standart_text());
			muamele.setTapu_standart_text(yapi.getTapu_standart_text());
			muamele.setEgm_standart_text(yapi.getEgm_standart_text());
			muamele.setPosta_standart_text(yapi.getPosta_standart_text());

		}

		if (muzekkereTalep.equals("4lu5bankatalebi")) {

			cokluBanka4 = dao.cokluTalepGetirFor(muamele, muzekkereTalep);
			list.add(cokluBanka4);

		} else if (muzekkereTalep.equals("4lu7bankatalebi")) {

			cokluBanka7 = dao.cokluTalepGetirFor(muamele, muzekkereTalep);
			list.add(cokluBanka7);

		} else {

			// Tebligat zarfı oluşturmak için müzekkere kontrolu mu yapılır
			boolean result = tmUtil.isMuzekkere(muzekkereTalep);
			// Talep Müzekkere ve Tebligatın Hazırlanması
			talepMuzekkere = OnizleAndKaydet();
			list.add(talepMuzekkere);

			if (result) {
				tebligat = tebligatZarfiJasper(muamele, muzekkereTalep);
				list.add(tebligat);
			}

		}

		String path = null;
		if (gelisAmaci.equals("duzenle")) {

			path = "C:/apache-tomcat-8.0.30/webapps/SEMIRAMIS/pdfler/"
					+ muzekkereTalep + ".pdf";

		} else if (gelisAmaci.equals("onizleme")) {

			// gelis_amaci onizleme

			path = "C:/apache-tomcat-8.0.30/webapps/SEMIRAMIS/pdfler/"
					+ muzekkereTalep + ".pdf";

		} else if (gelisAmaci.equals("yazdir")) {

			path = "C:/muzekkere_talep_ciktilari/" + muzekkereTalep + "_" + muamele.getIcraDosyaNo() + ".pdf";
		}

		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(SimpleExporterInput.getInstance(list));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
		SimplePdfExporterConfiguration config = new SimplePdfExporterConfiguration();
		config.setCreatingBatchModeBookmarks(true);
		exporter.exportReport();

		// Oluşturulan PDF'lerin Gösterimi Sağlanır
		pdf = path;
		Thread.sleep(2000);
	}

	@Override
	public void Sil() throws Exception {

		String muzekkereTalep = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("muzekkereTalepAdi"));
		String icraDosyaNo = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("icraDosyaNumarasi"));

		String kurumAdi = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("kurumAdi"));

		String alacakliBankasi = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("alacakliBankasi"));

		String tapuKayitlari = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("tapuKayitlari"));

		MuameleIslemleriDAO dao = new MuameleIslemleriDAO();
		dao.deleteRowIndex(muzekkereTalep, icraDosyaNo, kurumAdi, alacakliBankasi, tapuKayitlari);

		// Kayıtların Yinelenmesi Sağlanır
		muameleList = TümListeyiGetir();

		if (muameleList.size() == 0) {

			onizleButtonVisible = true;

		}

	}

	@Override
	public ArrayList<MuameleIslemleri> TümListeyiGetir() throws Exception {
		return dao.getMuzekkereTalepList(muamele.getIcraDosyaNo());
	}

	@Override
	public void SecilenKaydiGetir() throws Exception {

	}

	public void YeniKayitAc() throws InterruptedException {

		muamele = new MuameleIslemleri();
		muamele.setBorcluAdi(AktifBean.getBorcluAdi());
		muamele.setIcraDosyaNo(AktifBean.icraDosyaNo);
		onizleButtonVisible = true;
		muzekkereTalep = "default";
		Thread.sleep(1000);

	}

	@Override
	public void Iptal() {

	}

	private ArrayList<BankaModel> bankaList = new ArrayList<>();
	private ArrayList<GayrimenkulModel> gayrimenkulList = new ArrayList<>();
	private ArrayList<KurumModel> kurumList = new ArrayList<>();

	private String bankaAdi;
	private String bankaBilgisi;
	private String il;
	private String ilce;
	private String ada;
	private String parsel;
	private String aciklama;
	private String adi;
	private String adres;
	private String kurumAdi;

	private ArrayList<Ilce> ilceList = new ArrayList<Ilce>();

	private MuameleIslemleriRequireCtrl ctrl = new MuameleIslemleriRequireCtrl();

	public MuameleIslemleriRequireCtrl getCtrl() {
		return ctrl;
	}

	public void setCtrl(MuameleIslemleriRequireCtrl ctrl) {
		this.ctrl = ctrl;
	}

	public ArrayList<Ilce> getIlceList() {
		return ilceList;
	}

	public void setIlceList(ArrayList<Ilce> ilceList) {
		this.ilceList = ilceList;
	}

	public ArrayList<BankaModel> getBankaList() {
		return bankaList;
	}

	public void setBankaList(ArrayList<BankaModel> bankaList) {
		this.bankaList = bankaList;
	}

	public ArrayList<GayrimenkulModel> getGayrimenkulList() {
		return gayrimenkulList;
	}

	public void setGayrimenkulList(ArrayList<GayrimenkulModel> gayrimenkulList) {
		this.gayrimenkulList = gayrimenkulList;
	}

	public ArrayList<KurumModel> getKurumList() {
		return kurumList;
	}

	public void setKurumList(ArrayList<KurumModel> kurumList) {
		this.kurumList = kurumList;
	}

	public String getBankaAdi() {
		return bankaAdi;
	}

	public void setBankaAdi(String bankaAdi) {
		this.bankaAdi = bankaAdi;
	}

	public String getBankaBilgisi() {
		return bankaBilgisi;
	}

	public void setBankaBilgisi(String bankaBilgisi) {
		this.bankaBilgisi = bankaBilgisi;
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

	public String getKurumAdi() {
		return kurumAdi;
	}

	public void setKurumAdi(String kurumAdi) {
		this.kurumAdi = kurumAdi;
	}

	public void setIlce(String ilce) {
		this.ilce = ilce;
	}

	public String getAda() {
		return ada;
	}

	public void setAda(String ada) {
		this.ada = ada;
	}

	public String getParsel() {
		return parsel;
	}

	public void setParsel(String parsel) {
		this.parsel = parsel;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public String getAdi() {
		return adi;
	}

	public void setAdi(String adi) {
		this.adi = adi;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public void clearPopupFields() {

		bankaAdi = "";
		bankaBilgisi = "";
		il = "";
		ilce = "";
		ada = "";
		parsel = "";
		aciklama = "";
		adi = "";
		adres = "";
		kurumAdi = "";
	}

	public void addBankList() {
		int id = 1;
		if (bankaList.size() > 0) {
			id = bankaList.get(bankaList.size() - 1).getId() + 1;
		}
		bankaList.add(new BankaModel(id, adi, bankaBilgisi));
		clearPopupFields();
	}

	public void addKurumList() {

		int id = 1;
		if (kurumList.size() > 0) {
			id = kurumList.get(kurumList.size() - 1).getId() + 1;
		}
		kurumList.add(new KurumModel(id, kurumAdi, adres));

		clearPopupFields();
	}

	public void removeBankList(int id) {

		for (int i = 0; i < bankaList.size(); i++) {
			if (bankaList.get(i).getId() == id) {
				bankaList.remove(i);
			}
		}
	}

	public void addGayrimenkulList() {
		int id = 1;
		if (gayrimenkulList.size() > 0) {
			id = gayrimenkulList.get(gayrimenkulList.size() - 1).getId() + 1;
		}
		gayrimenkulList.add(new GayrimenkulModel(id, il, ilce, ada, parsel, aciklama));
		clearPopupFields();
	}

	public void removeGayrimenkulList(int id) {
		for (int i = 0; i < gayrimenkulList.size(); i++) {
			if (gayrimenkulList.get(i).getId() == id) {
				gayrimenkulList.remove(i);
			}
		}
	}

	public void removeKurumList(int id) {
		for (int i = 0; i < kurumList.size(); i++) {
			if (kurumList.get(i).getId() == id) {
				kurumList.remove(i);
			}
		}
	}

	public void clearList() {
		kurumList = new ArrayList<>();
		gayrimenkulList = new ArrayList<>();
		bankaList = new ArrayList<>();
		clearPopupFields();
	}

	public void ilceListeEkle() throws Exception {

		ilceList = new ArrayList<Ilce>();
		GenelSehirlerBean gs = new GenelSehirlerBean();
		ilceList = gs.getlceFromIlAd(muamele.getPttIlText());

	}

	public void ilceListeEkleForGayriMenkul() throws Exception {

		ilceList = new ArrayList<Ilce>();
		GenelSehirlerBean gs = new GenelSehirlerBean();
		ilceList = gs.getlceFromIlAd(il);

	}

	public void checkCheckBoxes() {

		if (muamele.isDavetiyemuzekkeresi103arac()) {
			ctrl.setAlacakliTel(true);
			ctrl.setAlacakliMail(true);
			ctrl.setBankaBilgileri(true);
			ctrl.setBorcluAdi(true);
			ctrl.setBorcluTc(true);
			ctrl.setBorcluAdresi(true);
			ctrl.setHazirlayan(true);
			ctrl.setIcraMudurluguAdi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setSemiramisNo(true);

		}

		if (muamele.isDavetiyemuzekkeresi103gayrimenkul()) {
			ctrl.setAlacakliTel(true);
			ctrl.setAlacakliMail(true);
			ctrl.setBankaBilgileri(true);
			ctrl.setBorcluAdi(true);
			ctrl.setBorcluTc(true);
			ctrl.setBorcluAdresi(true);
			ctrl.setHazirlayan(true);
			ctrl.setIcraMudurluguAdi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setSemiramisNo(true);
			ctrl.setRiskYoneticisi(true);
			ctrl.setMuameleTarihiText(true);
			ctrl.setAvukatAdi(true);
			ctrl.setVergiKimlikNo(true);
			ctrl.setBuroAdres(true);
			ctrl.setHacizBaslangicTarihi(true);
			ctrl.setPlaka(true);

		}

		if (muamele.isDavetiyemuzekkeresi103menkul()) {
			ctrl.setAlacakliTel(true);
			ctrl.setAlacakliMail(true);
			ctrl.setBankaBilgileri(true);
			ctrl.setBorcluAdi(true);
			ctrl.setBorcluTc(true);
			ctrl.setBorcluAdresi(true);
			ctrl.setHazirlayan(true);
			ctrl.setIcraMudurluguAdi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setSemiramisNo(true);
			ctrl.setRiskYoneticisi(true);
			ctrl.setMuameleTarihiText(true);
			ctrl.setAvukatAdi(true);
			ctrl.setVergiKimlikNo(true);
			ctrl.setBuroAdres(true);
			ctrl.setHacizBaslangicTarihi(true);
			ctrl.setPlaka(true);

		}

		if (muamele.isDavetiyemuzekkeresi103sgk()) {
			ctrl.setAlacakliTel(true);
			ctrl.setAlacakliMail(true);
			ctrl.setBankaBilgileri(true);
			ctrl.setBorcluAdi(true);
			ctrl.setBorcluTc(true);
			ctrl.setBorcluAdresi(true);
			ctrl.setHazirlayan(true);
			ctrl.setIcraMudurluguAdi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setSemiramisNo(true);
			ctrl.setRiskYoneticisi(true);
			ctrl.setMuameleTarihiText(true);
			ctrl.setAvukatAdi(true);
			ctrl.setVergiKimlikNo(true);
			ctrl.setBuroAdres(true);
			ctrl.setHacizBaslangicTarihi(true);
			ctrl.setPlaka(true);

		}

		if (muamele.isDavetiyetalebi103()) {
			ctrl.setIcraMudurluguAdi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAvukatAdi(true);
			ctrl.setBarcode(true);
			ctrl.setMuameleTarihiText(true);

		}

		if (muamele.isAdresarastirmamuzekkeresikurumlaricin()) {
			ctrl.setIcraMudurluguAdi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAlacakliAdi(true);
			ctrl.setBorcluAdi(true);
			ctrl.setBorcluTcKimlikNo(true);
			ctrl.setMuameleTarihiText(true);
			ctrl.setAvukatAdi(true);
			ctrl.setKurumAdi(true);

		}

		if (muamele.isAdresarastimatalebi()) {
			ctrl.setIcraMudurluguAdi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAlacakliAdi(true);
			ctrl.setAvukatAdi(true);
			ctrl.setBarcode(true);
			ctrl.setKurumAdi(true);
			ctrl.setMuameleTarihiText(true);

		}

		if (muamele.isAilekayittablosutalebi()) {

			ctrl.setIcraMudurluguAdi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAvukatAdi(true);
			ctrl.setBarcode(true);
			ctrl.setMuameleTarihiText(true);

		}
		if (muamele.isAracserhitalebi()) {
			ctrl.setIcraMudurluguAdi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAvukatAdi(true);
			ctrl.setPlaka(true);
			ctrl.setMuameleTarihiText(true);

		}
		if (muamele.isAracyakalamatalebi()) {
			ctrl.setIcraMudurluguAdi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAvukatAdi(true);
			ctrl.setPlaka(true);
			ctrl.setMuameleTarihiText(true);

		}
		if (muamele.isDosyaislemdenkaldirilmatalebi()) {
			ctrl.setIcraMudurluguAdi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAvukatAdi(true);
			ctrl.setPlaka(true);
			ctrl.setMuameleTarihiText(true);

		}
		if (muamele.isDosyaislemdenkaldirilmatalebiharcburoda()) {
			ctrl.setIcraMudurluguAdi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAvukatAdi(true);
			ctrl.setPlaka(true);
			ctrl.setMuameleTarihiText(true);

		}

		if (muamele.isDosyaislemdenkaldirilmatalebiharcborcluda()) {
			ctrl.setIcraMudurluguAdi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAvukatAdi(true);
			ctrl.setPlaka(true);
			ctrl.setMuameleTarihiText(true);

		}

		if (muamele.isHacizihbarnamesimuzekkeresi891()) {
			ctrl.setIcraMudurluguAdi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAvukatAdi(true);
			ctrl.setBarcode(true);
			ctrl.setAlacakliAdi(true);
			ctrl.setAlacakliMail(true);
			ctrl.setAlacakliTel(true);
			ctrl.setBankaBilgileri(true);
			ctrl.setBorcluAdi(true);
			ctrl.setBorcluTc(true);
			ctrl.setBorcluAdresi(true);
			ctrl.setSemiramisNo(true);
			ctrl.setHazirlayan(true);
			ctrl.setAlacakliBankasi(true);
			ctrl.setAlacakFaizTutari(true);
			ctrl.setMuameleTarihiText(true);
			ctrl.setMuhatapAdi(true);
			ctrl.setVergiKimlikNo(true);
		}

		if (muamele.isHacizihbarnamesitalebibankalaricin()) {
			ctrl.setIcraMudurluguAdi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAvukatAdi(true);
			ctrl.setBarcode(true);
			ctrl.setAlacakliBankasi(true);
			ctrl.setMuameleTarihiText(true);
		}

		if (muamele.isMaashacizmuzekkeresigenel()) {

			ctrl.setIcraMudurluguAdi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAlacakliBankasi(true);
			ctrl.setAvukatAdi(true);
			ctrl.setBorcluAdi(true);
			ctrl.setBorcluTcKimlikNo(true);
			ctrl.setKonu(true);
			ctrl.setSemiramisNo(true);
			ctrl.setHazirlayan(true);
			ctrl.setRiskYoneticisi(true);
			ctrl.setMuameleTarihiText(true);
			ctrl.setAlacakliMail(true);
			ctrl.setAlacakliTel(true);
			ctrl.setBorcluMiktari(true);
			ctrl.setBuroIbanNo(true);
			ctrl.setBuroAdres(true);

		}
		if (muamele.isMaashacizmuzekkeresimuvafakat()) {
			ctrl.setIcraMudurluguAdi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAlacakliBankasi(true);
			ctrl.setAvukatAdi(true);
			ctrl.setBorcluAdi(true);
			ctrl.setBorcluTcKimlikNo(true);
			ctrl.setKonu(true);
			ctrl.setSemiramisNo(true);
			ctrl.setHazirlayan(true);
			ctrl.setRiskYoneticisi(true);
			ctrl.setMuameleTarihiText(true);
			ctrl.setAlacakliMail(true);
			ctrl.setAlacakliTel(true);
			ctrl.setBorcluMiktari(true);
			ctrl.setBuroIbanNo(true);
			ctrl.setBuroAdres(true);
			ctrl.setMaasMuvafakat(true);

		}

		if (muamele.isMaashaciztalebigenel()) {

			ctrl.setIcraMudurluguAdi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setSgk_standart_text(true);
			ctrl.setEgm_standart_text(true);
			ctrl.setTapu_standart_text(true);
			ctrl.setPosta_standart_text(true);
		}

		if (muamele.isMaashaciztalebimuvafakat()) {

			ctrl.setIcraMudurluguAdi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setMuameleTarihiText(true);
		}

		if (muamele.isMenkulhaciztalebi()) {
			ctrl.setIcraMudurluguAdi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAvukatAdi(true);
			ctrl.setBarcode(true);
		}

	}
}
