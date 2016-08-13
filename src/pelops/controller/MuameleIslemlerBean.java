package pelops.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import com.a.a.a.b.i;

import pelops.dao.AlacakliDAO;
import pelops.dao.BaglantiDAO;
import pelops.dao.BorcluBilgisiDAO;
import pelops.dao.HesapDAO;
import pelops.dao.IcraDosyasiDAO;
import pelops.dao.MuameleIslemleriDAO;
import pelops.dao.PostaDAO;
import pelops.interfaces.ReportCRUDInterface;
import pelops.model.Avukat;
import pelops.model.BorcluBilgisi;
import pelops.model.DortluYapi;
import pelops.model.Ilce;
import pelops.model.MuameleAutoFields;
import pelops.model.MuameleIslemleri;
import pelops.model.Posta;
import pelops.model.StandartTalep;
import pelops.model.TebligatListesi;
import pelops.model.TebligatZarfi;
import pelops.muameleislemleri.util.BankaModel;
import pelops.muameleislemleri.util.GayrimenkulModel;
import pelops.muameleislemleri.util.KurumModel;
import pelops.muameleislemleri.util.MuameleIslemleriRequireCtrl;
import pelops.reports.controller.GenelYazdirBean;

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
@ViewScoped
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
	
	//Tebligat zarfının tipinin ne oldugu tanımlar
	public String zarfTipi= " ";
	
	
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
	
	//public String Yol = Yol;
	//public String Yol = "";
	//public String Yol = "C:/Users/JAVA/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SEMIRAMIS/pdfler/";
	private String Yol = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\pdfler\\")+"\\";    //"C:/apache-tomcat-8.0.30/webapps/SEMIRAMIS/pdfler/";
			
	
	FacesContext context = FacesContext.getCurrentInstance();

	public void getSelectedId(int selectedId) throws Exception {

		AktifBean.icraDosyaID = selectedId;
		
		System.out.println("secilen id :"+ selectedId);
		
		getFieldFromIcraDosyaTakibi();

	}
	
	
	public void hidePopup(){
		
		
		RequestContext.getCurrentInstance().execute("PF('arama').hide()");
		
	}

	public void getFieldFromIcraDosyaTakibi() throws Exception {

		
		System.out.println("AktifBean iD "+ AktifBean.icraDosyaID);
		
	
		
		this.setIptalrender(false);
		this.setduzenlesilrender(false);

		
		//Gayrimenkul listesi sıfırlanır
		
		gayrimenkulList.clear();
		
		// Müvekkil ile Alacaklı aynı kişi

		int icraDosyaId = AktifBean.icraDosyaID;
	
		if (icraDosyaId != 0) {

			MuameleAutoFields autoFields = new MuameleAutoFields();
			BaglantiDAO baglantidao = new BaglantiDAO();
			HesapDAO hesapDao = new HesapDAO();

			int hesapId = baglantidao.Listele(icraDosyaId).getHesaplamaID();

			
			
			BorcluBilgisiDAO daoborclu = new BorcluBilgisiDAO();
			AlacakliDAO daoalacakli = new AlacakliDAO();
			IcraDosyasiDAO icradosyasidao = new IcraDosyasiDAO();
			TalepMuzekkereUtil talepMuzekkereUtil = new TalepMuzekkereUtil();
			int borclubilgisiID = baglantidao.Listele(icraDosyaId).getBorcluID();

			BorcluBilgisi borclu=daoborclu.Liste(borclubilgisiID);
			
			AktifBean.borcluAdi=borclu.getAdSoyad();
			AktifBean.borcluId=borclu.getBorcluId();
			
			
			System.out.println("Borclu Bilgisi ID "+borclubilgisiID);
			
			int alacakliID = baglantidao.Listele(icraDosyaId).getAlacakliID();

			autoFields.setBorcMiktari(
					(hesapDao.Liste(hesapId).getToplam_alacak()) - (hesapDao.Liste(hesapId).getTahsilat_tutari()));

			System.out.println(borclu.getAdSoyad());
			
			
			
			autoFields.setBorcluAdi(borclu.getAdSoyad());
			
			if(borclu.getDogumTarihi()!=null)
			autoFields.setBorcluDogumTarihi(new SimpleDateFormat("MM/dd/yyyy").format(borclu.getDogumTarihi()));
			autoFields.setIcraDosyaNo(icradosyasidao.Listele(icraDosyaId).getIcraDosyaNo());
			autoFields.setIcraMudurluguId(icradosyasidao.Listele(icraDosyaId).getIcraMudurluguId());
			autoFields.setRiskYoneticisiId(icradosyasidao.Listele(icraDosyaId).getRiskYoneticisiId());
			autoFields.setIcraMudurluguText(talepMuzekkereUtil.getIcraMudurluguText(autoFields.getIcraMudurluguId()));
			autoFields
					.setRiskYoneticisiText(talepMuzekkereUtil.getRiskYoneticisiText(autoFields.getRiskYoneticisiId()));
			autoFields.setAlacakliEpostaText(talepMuzekkereUtil.getAlacakliEpostaText(alacakliID));
			autoFields.setIcraMudurluguText(talepMuzekkereUtil.getIcraMudurluguText(alacakliID));
			autoFields.setAlacakliEpostaText(talepMuzekkereUtil.getAlacakliEpostaText(alacakliID));
			autoFields.setIcraDosyaNoText(talepMuzekkereUtil.getIcraDosyaNoText(icraDosyaId));
			autoFields.setBorcluAdresiText(talepMuzekkereUtil.getBorcluAdresiText(borclubilgisiID));
			autoFields.setBuroAdresiText(talepMuzekkereUtil.getBuroAdresiText(icraDosyaId));
			autoFields.setAlacakliTelText(talepMuzekkereUtil.getAlacakliTelText(alacakliID));
			autoFields.setBorcluTcText(talepMuzekkereUtil.getBorcluTcText(borclubilgisiID));
			autoFields.setMuvekkilAdi(daoalacakli.ListeGetir(alacakliID).getMuvekkilAdi());
			

			// Manuel Giriş Yapılmıştır -- Bad Code :(
			muamele.setPostaneAdi("PTT İSKİTLER MERKEZ MÜDÜRLÜĞÜ");
			muamele.setBuroIbanNo("TR3000 1230 0067 1038 9292 8100");
			muamele.setVergiKimlikNo("Kimlik No ?");

			muamele.setBorcluAdi(autoFields.getBorcluAdi());
			muamele.setStatus(0);
			muamele.setIcraDosyaNo(autoFields.getIcraDosyaNo());
			muamele.setIcraMudurluguAdi(autoFields.getIcraMudurluguText());

			muamele.setRiskYoneticisi(autoFields.getRiskYoneticisiText());
			muamele.setSemiramisNo(icraDosyaId);
			muamele.setAlacakliAdi(autoFields.getAlacakliEpostaText());
			muamele.setBankaAdi(autoFields.getMuvekkilAdi());
			muamele.setBorcluTcKimlikNo(autoFields.getBorcluTcText());
			muamele.setBorcluAdresi(autoFields.getBorcluAdresiText());
			muamele.setDogumTarihiText(autoFields.getBorcluDogumTarihi());
		
			muamele.setMuhatapAdi(borclu.getIsYeriAdi());
			muamele.setMuhatapAdresi(borclu.getIsYeriAdres());
			
			
			setBankaAdi(muamele.getAlacakliAdi());
			
			// ALACAKLI OLARAK BANKANIN EKLENMESİ
			
			BankaModel bankaModel=new BankaModel(1, muamele.getAlacakliAdi(), muamele.getAlacakliAdi());
			
			bankaList.add(bankaModel);
			
			
			
			
			
			muamele.setAlacakliMail(autoFields.getAlacakliMail());
			muamele.setBuroAdresi(autoFields.getBuroAdresiText());
			muamele.setAlacakliTel(autoFields.getAlacakliTelText());
			muamele.setBorcluTc(autoFields.getBorcluTcText());
			muamele.setIcraMudurluguId(autoFields.getIcraMudurluguId());
			muamele.setRiskYoneticisiId(autoFields.getRiskYoneticisiId());
			muamele.setBorcluAdresi(autoFields.getBorcluAdresiText());
			
			
			
			System.out.println("Muamele Borclu Adı "+muamele.getBorcluAdi() );
		
			
			
			

			if (autoFields.getBorcMiktari() == null) {

				muamele.setBorcluMiktari(0.0);

			} else {
				
				System.out.println(new DecimalFormat("0.00").format(autoFields.getBorcMiktari()));

				muamele.setBorcluMiktari(Double.valueOf(new DecimalFormat("0.00").format(autoFields.getBorcMiktari()).replace(",", ".")));
				
				
			
				
			}

			if (muamele.getAlacakFaizTutari() == null) {

				muamele.setAlacakFaizTutari(0.0);

			}
			if (muamele.getMasrafMiktari() == null) {

				muamele.setMasrafMiktari(0.0);

			}

			System.out.println(autoFields.getBorcMiktari());

			muameleList = TümListeyiGetir();
			
		
		}

	}
	
	
	public MuameleIslemlerBean() throws Exception {
		// TODO Auto-generated constructor stub
		
		// İcra Dosya Takibinden bilgilerin alınıp set edilmesi sağlanır
				getFieldFromIcraDosyaTakibi();

				gayrimenkulList=new ArrayList<GayrimenkulModel>();
				standartTalepList = new ArrayList<>();
				standartTalepList = dao.getStandartTalepTextList();
				muamele.setMuameleTarihi(util.getCurrentDate());
				
				

				// Listenin Getirilmesi sağlanır±r.
				muameleList = TümListeyiGetir();
	}

	
	public void init() throws Exception {

		// İcra Dosya Takibinden bilgilerin alınıp set edilmesi sağlanır
		getFieldFromIcraDosyaTakibi();

		gayrimenkulList=new ArrayList<GayrimenkulModel>();
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
			muamele.setMasrafMiktari(10.0);
			break;

		case 2:

			muamele.setMasrafMiktari(20.0);
			break;

		case 3:

			muamele.setMasrafMiktari(30.0);
			break;

		case 4:

			muamele.setMasrafMiktari(40.0);
			break;
		case 5:

			muamele.setMasrafMiktari(50.0);
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

		// String uretilenBarkod = barkodUret();
		// Duzenle();

	}

	public void PdfOnizle() throws Exception {

		Kaydet();
		Duzenle();
		// muamele = dao.getMuameleFromList(onizleDuzenleID);
		// Thread.sleep(2000);

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
		
		// Maaş Müzekkeresi Genel Durumunda 
		
		if(muamele.isMaashacizmuzekkeresigenel())
		{
		
		zarf.setBorcluAdi(muamele.getMuhatapAdi().toUpperCase());
		zarf.setBorcluAdres(muamele.getMuhatapAdresi().toUpperCase());
		zarf.setMuzekkereTalepAdi("Maaş Haciz Müzekkeresi (Genel)");
		zarfTipi="Maaş Haciz Müzekkeresi";

		
		}
		else if(muamele.isTapuhacizmuzekkeresinokta())
		{
			zarf.setBorcluAdi(muamele.getTapuMudurlugu().toUpperCase());
			zarf.setBorcluAdres(muamele.getTapuIlce().toUpperCase()+" "+muamele.getTapuIl().toUpperCase());
			zarf.setMuzekkereTalepAdi("Tapu Haciz Müzekkeresi (Nokta)");
			zarfTipi="Tapu Haciz Müzekkeresi";
		}
		
		zarf.setIcraDosyaNo(muamele.getIcraDosyaNo());
		zarf.setIcraMudurluguAdi(muamele.getIcraMudurluguAdi());
		zarf.setAlacakliAdi(AktifBean.getMuvekkilAdi());
		zarf.setAvukatAdi(muamele.getAvukatAdi());
		zarf.setBarkod(muamele.getBarkod());
		

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
	
	
	public JasperPrint tebligatListesiJasper(MuameleIslemleri muamele, String muzekkereTalep) throws Exception {

		ArrayList<TebligatListesi> dataBeanListForTebligat = new ArrayList<TebligatListesi>();
		TebligatListesi liste = new TebligatListesi();
		
		// Maaş Müzekkeresi Genel Durumunda 
		
		if(muamele.isMaashacizmuzekkeresigenel())
		{
		
		liste.setBorcluAdi(muamele.getMuhatapAdi().toUpperCase());
		
		
		}
		else
		{
			liste.setBorcluAdi(muamele.getBorcluAdi());
				
		}
		
		liste.setIcraDosyaNo(muamele.getIcraDosyaNo());
		liste.setIcraBilgi(muamele.getIcraMudurluguAdi());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		liste.setTarih(sdf.format(new java.util.Date()));
		liste.setIl(muamele.getIcraMudurluguAdi().split(" ")[0]);
		liste.setBrcd(muamele.getBarkod());
		liste.setKonu(zarfTipi);

		dataBeanListForTebligat.add(liste);

		HashMap<String, Object> parameters = new HashMap<String, Object>();

		String pathName = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/reports/tebligat_listesi1.jrxml");
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

		muamele.setBarcode(barkodUret());
		
		muamele.setBarkod(new GenelYazdirBean().createBarcode(muamele.getBarcode()));
		
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

	public JasperPrint OnizleAndKaydet() throws Exception {

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
		muamele.setMaashacizmuzekkeresimuvafakat(false);
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
		muamele.setCoklu4lu5bankatalebi(false);
		muamele.setCoklu4lu7bankatalebi(false);

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
		muamele.setMaashacizmuzekkeresimuvafakatVisible(true);
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
		muamele.setCoklu4lu5bankatalebiVisible(true);
		muamele.setCoklu4lu7bankatalebiVisible(true);

		onizleButtonVisible = false;

		switch (muzekkereTalep) {

		// ******************************************************************************

		case "103 Davetiyesi Müzekkeresi(Araç)":

			muzekkereTalep = "103davetiyesimuzekkeresiarac";
			muamele.setDavetiyemuzekkeresi103arac(true);
			muamele.setDavetiyemuzekkeresi103aracVisible(false);
			break;

		case "103davetiyesimuzekkeresiarac":

			muzekkereTalep = "103davetiyesimuzekkeresiarac";
			muamele.setDavetiyemuzekkeresi103arac(true);
			muamele.setDavetiyemuzekkeresi103aracVisible(false);
			break;

		case "103 Davetiyesi Müzekkeresi(SGK)":

			muzekkereTalep = "103davetiyesimuzekkeresisgk";
			muamele.setDavetiyemuzekkeresi103sgk(true);
			muamele.setDavetiyemuzekkeresi103sgkVisible(false);
			break;

		case "103davetiyesimuzekkeresisgk":

			muzekkereTalep = "103davetiyesimuzekkeresisgk";
			muamele.setDavetiyemuzekkeresi103sgk(true);
			muamele.setDavetiyemuzekkeresi103sgkVisible(false);
			break;

		case "103 Davetiyesi Müzekkeresi(Gayrimenkul)":

			muzekkereTalep = "103davetiyesimuzekkeresigayrimenkul";
			muamele.setDavetiyemuzekkeresi103gayrimenkul(true);
			muamele.setDavetiyemuzekkeresi103gayrimenkulVisible(false);
			break;

		case "103davetiyesimuzekkeresigayrimenkul":

			muzekkereTalep = "103davetiyesimuzekkeresigayrimenkul";
			muamele.setDavetiyemuzekkeresi103gayrimenkul(true);
			muamele.setDavetiyemuzekkeresi103gayrimenkulVisible(false);
			break;

		case "103 Davetiyesi Müzekkeresi(Menkul)":

			muzekkereTalep = "103davetiyesimuzekkeresimenkul";
			muamele.setDavetiyemuzekkeresi103menkul(true);
			muamele.setDavetiyemuzekkeresi103menkulVisible(false);
			break;

		case "103davetiyesimuzekkeresimenkul":

			muzekkereTalep = "103davetiyesimuzekkeresimenkul";
			muamele.setDavetiyemuzekkeresi103menkul(true);
			muamele.setDavetiyemuzekkeresi103menkulVisible(false);
			break;

		case "Adres Araştırma Müzekkeresi(Kurumlar İçin)":

			muzekkereTalep = "adresarastirmamuzekkeresikurumlaricin";
			muamele.setAdresarastirmamuzekkeresikurumlaricin(true);
			muamele.setAdresarastirmamuzekkeresikurumlaricinVisible(false);
			break;

		case "adresarastirmamuzekkeresikurumlaricin":

			muzekkereTalep = "adresarastirmamuzekkeresikurumlaricin";
			muamele.setAdresarastirmamuzekkeresikurumlaricin(true);
			muamele.setAdresarastirmamuzekkeresikurumlaricinVisible(false);
			break;

		case "Haciz İhbarnamesi Müzekkeresi(89/1)":

			muzekkereTalep = "hacizihbarnamesimuzekkeresi891";
			muamele.setHacizihbarnamesimuzekkeresi891(true);
			muamele.setHacizihbarnamesimuzekkeresi891Visible(false);

			break;

		case "hacizihbarnamesimuzekkeresi891":

			muzekkereTalep = "hacizihbarnamesimuzekkeresi891";
			muamele.setHacizihbarnamesimuzekkeresi891(true);
			muamele.setHacizihbarnamesimuzekkeresi891Visible(false);

			break;

		case "Maaş Haciz Müzekkeresi(Genel)":

			muzekkereTalep = "maashacizmuzekkeresigenel";
			zarfTipi="Maaş Haciz Müzekkeresi";
			muamele.setMaashacizmuzekkeresigenel(true);
			muamele.setMaashacizmuzekkeresigenelVisible(false);

			break;

		case "maashacizmuzekkeresigenel":

			muzekkereTalep = "maashacizmuzekkeresigenel";
			zarfTipi="Maaş Haciz Müzekkeresi";
			muamele.setMaashacizmuzekkeresigenel(true);
			muamele.setMaashacizmuzekkeresigenelVisible(false);

			break;

		case "Maaş Haciz Müzekkeresi(Muvafakat)":

			muzekkereTalep = "maashacizmuzekkeresimuvafakat";
			muamele.setMaashacizmuzekkeresimuvafakat(true);
			muamele.setMaashacizmuzekkeresimuvafakatVisible(false);
			break;

		case "maashacizmuzekkeresimuvafakat":

			muzekkereTalep = "maashacizmuzekkeresimuvafakat";
			muamele.setMaashacizmuzekkeresimuvafakat(true);
			muamele.setMaashacizmuzekkeresimuvafakatVisible(false);
			break;

		case "PTT Haciz Müzekkeresi":

			muzekkereTalep = "ptthacizmuzekkeresi";
			muamele.setPtthacizmuzekkeresi(true);
			muamele.setPtthacizmuzekkeresiVisible(false);

			break;

		case "ptthacizmuzekkeresi":

			muzekkereTalep = "ptthacizmuzekkeresi";
			muamele.setPtthacizmuzekkeresi(true);
			muamele.setPtthacizmuzekkeresiVisible(false);

			break;

		case "Tapu Haciz Müzekkeresi(Nokta)":

			muzekkereTalep = "tapuhacizmuzekkeresinokta";
			muamele.setTapuhacizmuzekkeresinokta(true);
			muamele.setTapuhacizmuzekkeresinoktaVisible(false);
			break;

		case "tapuhacizmuzekkeresinokta":

			muzekkereTalep = "tapuhacizmuzekkeresinokta";
			zarfTipi="Tapu Haciz Müzekkeresi";
			muamele.setTapuhacizmuzekkeresinokta(true);
			muamele.setTapuhacizmuzekkeresinoktaVisible(false);
			break;

		case "Mevduat Haczi Müzekkeresi":

			muzekkereTalep = "mevduathaczimuzekkeresi";
			muamele.setMevduathaczimuzekkeresi(true);
			muamele.setMevduathaczimuzekkeresiVisible(false);
			break;

		case "mevduathaczimuzekkeresi":

			muzekkereTalep = "mevduathaczimuzekkeresi";
			muamele.setMevduathaczimuzekkeresi(true);
			muamele.setMevduathaczimuzekkeresiVisible(false);
			break;

		// ******************************************************************************

		case "103 Davetiyesi Talebi":

			muzekkereTalep = "103davetiyesitalebi";
			muamele.setDavetiyetalebi103(true);
			muamele.setDavetiyetalebi103Visible(false);
			break;

		case "103davetiyesitalebi":

			muzekkereTalep = "103davetiyesitalebi";
			muamele.setDavetiyetalebi103(true);
			muamele.setDavetiyetalebi103Visible(false);
			break;

		case "Maaş Haciz Talebi(Genel)":

			muzekkereTalep = "maashaciztalebigenel";
			muamele.setMaashaciztalebigenel(true);
			muamele.setMaashaciztalebigenelVisible(false);

			// SGK Standart text'in alınması sağlanır
			yapi = dao.dortluYapiGetir();
			muamele.setSgk_standart_text(yapi.getSgk_standart_text());

			break;

		case "maashaciztalebigenel":

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

		case "maashaciztalebimuvafakat":

			muzekkereTalep = "maashaciztalebimuvafakat";
			muamele.setMaashaciztalebimuvafakat(true);
			muamele.setMaashaciztalebimuvafakatVisible(false);
			break;

		case "Haciz İhbarnamesi Talebi(Bankalar İçin)":

			muzekkereTalep = "hacizihbarnamesitalebibankalaricin";
			muamele.setHacizihbarnamesitalebibankalaricin(true);
			muamele.setHacizihbarnamesitalebibankalaricinVisible(false);
			break;

		case "hacizihbarnamesitalebibankalaricin":

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

		case "ptthaciztalebi":

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

		case "ailekayittablosutalebi":

			muzekkereTalep = "ailekayittablosutalebi";
			muamele.setAilekayittablosutalebi(true);
			muamele.setAilekayittablosutalebiVisible(false);

			break;

		case "Tapu Haciz Talebi(Nokta)":

			muzekkereTalep = "tapuhaciztalebinokta";
			muamele.setTapuhaciztalebi(true);
			muamele.setTapuhaciztalebiVisible(false);
			zarfTipi="Tapu Haciz Müzekkeresi";

			// Standart Text'lerin getirilmesi sağlanır.
			yapi = dao.dortluYapiGetir();
			muamele.setEgm_standart_text(yapi.getEgm_standart_text());
			muamele.setSgk_standart_text(yapi.getSgk_standart_text());
			muamele.setTapu_standart_text(yapi.getTapu_standart_text());
			muamele.setPosta_standart_text(yapi.getPosta_standart_text());

			break;

		case "tapuhaciztalebinokta":

			muzekkereTalep = "tapuhaciztalebinokta";
			muamele.setTapuhaciztalebi(true);
			muamele.setTapuhaciztalebiVisible(false);
			zarfTipi="Tapu Haciz Müzekkeresi";

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

		case "adresarastirmatalebi":

			muzekkereTalep = "adresarastirmatalebi";
			muamele.setAdresarastimatalebi(true);
			muamele.setAdresarastimatalebiVisible(false);
			break;

		case "Araç Haczi Talebi":

			muzekkereTalep = "arachaczitalebi";
			muamele.setArachaczitalebi(true);
			muamele.setArachaczitalebiVisible(false);
			break;

		case "arachaczitalebi":

			muzekkereTalep = "arachaczitalebi";
			muamele.setArachaczitalebi(true);
			muamele.setArachaczitalebiVisible(false);
			break;

		case "Araç Yakalama Talebi":
			muzekkereTalep = "aracyakalamatalebi";
			muamele.setAracyakalamatalebi(true);
			muamele.setAracyakalamatalebiVisible(false);
			break;

		case "aracyakalamatalebi":
			muzekkereTalep = "aracyakalamatalebi";
			muamele.setAracyakalamatalebi(true);
			muamele.setAracyakalamatalebiVisible(false);
			break;

		case "Araç Şerhi Talebi":

			muzekkereTalep = "aracserhitalebi";
			muamele.setAracserhitalebi(true);
			muamele.setAracserhitalebiVisible(false);
			break;

		case "aracserhitalebi":

			muzekkereTalep = "aracserhitalebi";
			muamele.setAracserhitalebi(true);
			muamele.setAracserhitalebiVisible(false);
			break;

		case "Dosya İşlemden Kaldırılma Talebi(Harç Borçluda)":

			muzekkereTalep = "dosyaislemdenkaldirilmatalebiferagatnedeniyleharcborcluda";
			muamele.setDosyaislemdenkaldirilmatalebiharcborcluda(true);
			muamele.setDosyaislemdenkaldirilmatalebiharcborcludaVisible(false);
			break;

		case "dosyaislemdenkaldirilmatalebiferagatnedeniyleharcborcluda":

			muzekkereTalep = "dosyaislemdenkaldirilmatalebiferagatnedeniyleharcborcluda";
			muamele.setDosyaislemdenkaldirilmatalebiharcborcluda(true);
			muamele.setDosyaislemdenkaldirilmatalebiharcborcludaVisible(false);
			break;

		case "Dosya İşlemden Kaldırılma Talebi(Harç Büroda)":

			muzekkereTalep = "dosyaislemdenkaldirilmatalebiferagatnedeniyleharcburoda";
			muamele.setDosyaislemdenkaldirilmatalebiharcburoda(true);
			muamele.setDosyaislemdenkaldirilmatalebiharcburodaVisible(false);
			break;

		case "dosyaislemdenkaldirilmatalebiferagatnedeniyleharcburoda":

			muzekkereTalep = "dosyaislemdenkaldirilmatalebiferagatnedeniyleharcburoda";
			muamele.setDosyaislemdenkaldirilmatalebiharcburoda(true);
			muamele.setDosyaislemdenkaldirilmatalebiharcburodaVisible(false);
			break;

		case "Feragat Talebi":

			muzekkereTalep = "feragattalebi";
			muamele.setFeragattalebi(true);
			muamele.setFeragattalebiVisible(false);
			break;

		case "feragattalebi":

			muzekkereTalep = "feragattalebi";
			muamele.setFeragattalebi(true);
			muamele.setFeragattalebiVisible(false);
			break;

		case "Kapanış Harç Borçluda Talebi":

			muzekkereTalep = "kapanistalebiharcborcludatalebi";
			muamele.setKapanistalebiharcborcludatalebi(true);
			muamele.setKapanistalebiharcborcludatalebiVisible(false);
			break;

		case "kapanistalebiharcborcludatalebi":

			muzekkereTalep = "kapanistalebiharcborcludatalebi";
			muamele.setKapanistalebiharcborcludatalebi(true);
			muamele.setKapanistalebiharcborcludatalebiVisible(false);
			break;

		case "Kapanış Harç Büroda Talebi":

			muzekkereTalep = "kapanistalebiharcburodatalebi";
			muamele.setKapanistalebiharcburodatalebi(true);
			muamele.setKapanistalebiharcburodatalebiVisible(false);
			break;

		case "kapanistalebiharcburodatalebi":

			muzekkereTalep = "kapanistalebiharcburodatalebi";
			muamele.setKapanistalebiharcburodatalebi(true);
			muamele.setKapanistalebiharcburodatalebiVisible(false);
			break;

		case "Menkul Haciz Talebi":

			muzekkereTalep = "menkulhaciztalebi";
			muamele.setMenkulhaciztalebi(true);
			muamele.setMenkulhaciztalebiVisible(false);
			break;

		case "menkulhaciztalebi":

			muzekkereTalep = "menkulhaciztalebi";
			muamele.setMenkulhaciztalebi(true);
			muamele.setMenkulhaciztalebiVisible(false);
			break;

		case "Mernis Adresi Ödeme Emri Talebi":

			muzekkereTalep = "mernisadresineodemeemritalebi";
			muamele.setMernisadresineodemeemritalebi(true);
			muamele.setMernisadresineodemeemritalebiVisible(false);
			break;

		case "mernisadresineodemeemritalebi":

			muzekkereTalep = "mernisadresineodemeemritalebi";
			muamele.setMernisadresineodemeemritalebi(true);
			muamele.setMernisadresineodemeemritalebiVisible(false);
			break;

		case "Mevduat Haczi Talebi":

			muzekkereTalep = "mevduathaczitalebi";
			muamele.setMevduathaczitalebi(true);
			muamele.setMevduathaczitalebiVisible(false);
			break;

		case "mevduathaczitalebi":

			muzekkereTalep = "mevduathaczitalebi";
			muamele.setMevduathaczitalebi(true);
			muamele.setMevduathaczitalebiVisible(false);
			break;

		case "SGK Adresi Ödeme Emri Talebi":

			muzekkereTalep = "sgkadresiodemeemritalebi";
			muamele.setSgkadresiodemeemritalebi(true);
			muamele.setSgkadresiodemeemritalebiVisible(false);
			break;

		case "sgkadresiodemeemritalebi":

			muzekkereTalep = "sgkadresiodemeemritalebi";
			muamele.setSgkadresiodemeemritalebi(true);
			muamele.setSgkadresiodemeemritalebiVisible(false);
			break;

		case "Ticaret Sicil Adresi Sorma Talebi":

			muzekkereTalep = "ticaretsiciladressormatalebi";
			muamele.setTicaretsiciladressormatalebi(true);
			muamele.setTicaretsiciladressormatalebiVisible(false);
			break;

		case "ticaretsiciladressormatalebi":

			muzekkereTalep = "ticaretsiciladressormatalebi";
			muamele.setTicaretsiciladressormatalebi(true);
			muamele.setTicaretsiciladressormatalebiVisible(false);
			break;

		case "TK/21 Talebi":

			muzekkereTalep = "tk21talebi";
			muamele.setTk21talebi(true);
			muamele.setTk21talebiVisible(false);
			break;

		case "tk21talebi":

			muzekkereTalep = "tk21talebi";
			muamele.setTk21talebi(true);
			muamele.setTk21talebiVisible(false);
			break;

		case "Yenileme Talebi":

			muzekkereTalep = "yenilemetalebi";
			muamele.setYenilemetalebi(true);
			muamele.setYenilemetalebiVisible(false);
			break;

		case "yenilemetalebi":

			muzekkereTalep = "yenilemetalebi";
			muamele.setYenilemetalebi(true);
			muamele.setYenilemetalebiVisible(false);
			break;

		case "Yurtiçi Adresi Ödeme Emri Talebi":

			muzekkereTalep = "yurticiadresiodemeemritalebi";
			muamele.setYurticiadresiodemeemritalebi(true);
			muamele.setYurticiadresiodemeemritalebiVisible(false);
			break;

		case "yurticiadresiodemeemritalebi":

			muzekkereTalep = "yurticiadresiodemeemritalebi";
			muamele.setYurticiadresiodemeemritalebi(true);
			muamele.setYurticiadresiodemeemritalebiVisible(false);
			break;

		case "4lu( 5 Banka ) Talebi":
			muzekkereTalep = "coklu4lu5bankatalebi";
			muamele.setCoklu4lu5bankatalebi(true);
			muamele.setCoklu4lu5bankatalebiVisible(false);

			break;

		case "coklu4lu5bankatalebi":
			muzekkereTalep = "coklu4lu5bankatalebi";
			muamele.setCoklu4lu5bankatalebi(true);
			muamele.setCoklu4lu5bankatalebiVisible(false);

			break;

		case "4lu( 7 Banka ) Talebi":
			muzekkereTalep = "coklu4lu7bankatalebi";
			muamele.setCoklu4lu7bankatalebi(true);
			muamele.setCoklu4lu7bankatalebiVisible(false);
			break;

		case "coklu4lu7bankatalebi":
			muzekkereTalep = "coklu4lu7bankatalebi";
			muamele.setCoklu4lu7bankatalebi(true);
			muamele.setCoklu4lu7bankatalebiVisible(false);
			break;

		case "default":

			muzekkereTalep = "default";
			muamele.setDefault(true);
			break;

		default:

			break;
		}

		// ******************************************************************************

		ArrayList<MuameleIslemleri> dataBeanList = new ArrayList<MuameleIslemleri>();
		
		muamele.setBankaAdi(muamele.getAlacakliBankasi());
		
		muamele.setBarkod(new GenelYazdirBean().createBarcode(muamele.getBarcode()));
		
	
		dataBeanList.add(muamele);

		HashMap<String, Object> parameters = new HashMap<String, Object>();

		String pathName = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/reports/talep_muzekkereler/" + muzekkereTalep + ".jrxml");

		InputStream inputStream = new FileInputStream(pathName);

		File file = new File(
				Yol
						+ muzekkereTalep + ".pdf");
		file.delete();

		path = Yol
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
		
		
		muamele.setBorcluMiktariTxt(new DecimalFormat("###,###.##").format(muamele.getBorcluMiktari()));
		

		// Önizleme yapılırken text'lerin yeniden gelmesi için sağlanır

		if (muzekkereTalep.equals("ptthaciztalebi") || muzekkereTalep.equals("maashaciztalebigenel")
				|| muzekkereTalep.equals("coklu4lu5bankatalebi") || muzekkereTalep.equals("coklu4lu7bankatalebi")
				|| muzekkereTalep.equals("tapuhaciztalebinokta") || muzekkereTalep.equals("4lu( 5 Banka ) Talebi")
				|| muzekkereTalep.equals("4lu( 7 Banka ) Talebi")) {

			yapi = dao.dortluYapiGetir();
			muamele.setSgk_standart_text(yapi.getSgk_standart_text());
			muamele.setTapu_standart_text(yapi.getTapu_standart_text());
			muamele.setEgm_standart_text(yapi.getEgm_standart_text());
			muamele.setPosta_standart_text(yapi.getPosta_standart_text());

		}

		// Tebligat zarfı oluşturmak için müzekkere kontrolu mu yapılır
		boolean result = tmUtil.isMuzekkere(muzekkereTalep);

		// Talep Müzekkere ve Tebligatın Hazırlanması
		talepMuzekkere = OnizleAndKaydet();

		list.add(talepMuzekkere);

		if (result) {
			tebligat = tebligatZarfiJasper(muamele, muzekkereTalep);
			list.add(tebligat);
			
			JasperPrint tebligatListesi=tebligatListesiJasper(muamele, muzekkereTalep);
			
			list.add(tebligatListesi);
			
		}

		String path = null;
		if (gelisAmaci.equals("duzenle")) {

			// File file = new
			// File(Yol+
			// muzekkereTalep + ".pdf");
			// file.delete();

			path = Yol
					+ muzekkereTalep + ".pdf";

			System.out.println(path);

		} else if (gelisAmaci.equals("onizleme")) {

			// gelis_amaci onizleme

			path = Yol
					+ muzekkereTalep + ".pdf";

		} else if (gelisAmaci.equals("yazdir")) {

			path = Yol
					+ muzekkereTalep + "_" + muamele.getIcraDosyaNo() + ".pdf";
		}

		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(SimpleExporterInput.getInstance(list));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
		SimplePdfExporterConfiguration config = new SimplePdfExporterConfiguration();
		config.setCreatingBatchModeBookmarks(true);
		exporter.exportReport();

		// Oluşturulan PDF'lerin Gösterimi Sağlanır

		pdf = path;

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
			YeniKayitAc();

		}

	}

	@Override
	public ArrayList<MuameleIslemleri> TümListeyiGetir() throws Exception {
		return dao.getMuzekkereTalepList(muamele.getIcraDosyaNo());
	}

	@Override
	public void SecilenKaydiGetir() throws Exception {

	}

	public void YeniKayitAc() throws Exception {

		// Talep ve Müzekkere Görünür Hale Getirilmesi

		muamele.setDosyaislemdenkaldirilmatalebiharcborcludaVisible(false);
		muamele.setDosyaislemdenkaldirilmatalebiharcburodaVisible(false);
		muamele.setAracserhitalebiVisible(false);
		muamele.setAracyakalamatalebiVisible(false);
		muamele.setTapuhaciztalebiVisible(false);
		muamele.setAilekayittablosutalebiVisible(false);
		muamele.setDavetiyetalebi103Visible(false);
		muamele.setMaashaciztalebigenelVisible(false);
		muamele.setMaashacizmuzekkeresimuvafakatVisible(false);
		muamele.setAdresarastimatalebiVisible(false);
		muamele.setArachaczitalebiVisible(false);
		muamele.setDosyaislemdenkaldirilmatalebiVisible(false);
		muamele.setFeragattalebiVisible(false);
		muamele.setHacizihbarnamesitalebibankalaricinVisible(false);
		muamele.setKapanistalebiharcborcludatalebiVisible(false);
		muamele.setKapanistalebiharcburodatalebiVisible(false);
		muamele.setMenkulhaciztalebiVisible(false);
		muamele.setMernisadresineodemeemritalebiVisible(false);
		muamele.setMevduathaczitalebiVisible(false);
		muamele.setSgkadresiodemeemritalebiVisible(false);
		muamele.setTicaretsiciladressormatalebiVisible(false);
		muamele.setTk21talebiVisible(false);
		muamele.setYenilemetalebiVisible(false);
		muamele.setYurticiadresiodemeemritalebiVisible(false);
		muamele.setPtthaciztalebiVisible(false);
		muamele.setCoklu4lu5bankatalebiVisible(false);
		muamele.setCoklu4lu7bankatalebiVisible(false);

		muamele.setMevduathaczimuzekkeresiVisible(false);
		muamele.setDavetiyemuzekkeresi103aracVisible(false);
		muamele.setDavetiyemuzekkeresi103gayrimenkulVisible(false);
		muamele.setDavetiyemuzekkeresi103menkulVisible(false);
		muamele.setDavetiyemuzekkeresi103sgkVisible(false);
		muamele.setHacizihbarnamesitalebibankalaricinVisible(false);
		muamele.setMaashacizmuzekkeresigenelVisible(false);
		muamele.setMaashacizmuzekkeresimuvafakatVisible(false);
		muamele.setPtthacizmuzekkeresiVisible(false);
		muamele.setTapuhacizmuzekkeresinoktaVisible(false);
		muamele.setAdresarastimatalebiVisible(false);
		muamele.setHacizihbarnamesimuzekkeresi891Visible(false);
		muamele.setAdresarastirmamuzekkeresikurumlaricinVisible(false);

		getFieldFromIcraDosyaTakibi();
		onizleButtonVisible = true;

		muzekkereTalep = "default";
		path = Yol
				+ muzekkereTalep + ".pdf";

		pdf = path;

		Thread.sleep(2000);

		System.out.println(pdf);

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
		
		
		ctrl.setAciklama(false);
		ctrl.setAdres(false);
		ctrl.setAlacakFaizMasrafTutari(false);
		ctrl.setAlacakFaizTutari(false);
		ctrl.setAlacakliAdi(false);
		ctrl.setAlacakliBankasi(false);
		ctrl.setAlacakliMail(false);
		ctrl.setAlacakliTel(false);
		ctrl.setAvukatAdi(false);
		ctrl.setAvukatId(false);
		ctrl.setAvukatSoyadi(false);
		ctrl.setBankaAdi(false);
		ctrl.setBankaAdlari(false);
		ctrl.setBankaBilgileri(false);
		ctrl.setBankaMudurlukler(false);
		ctrl.setBarcode(false);
		ctrl.setBaslik(false);
		ctrl.setBorcluAdi(false);
		ctrl.setBorcluAdresi(false);
		ctrl.setBorcluMiktari(false);
		ctrl.setBorcluTc(false);
		ctrl.setBorcluTcKimlikNo(false);
		ctrl.setBuroAdresi(false);
		ctrl.setBuroIbanNo(false);
		ctrl.setDogumTarihi(false);
		ctrl.setEki(false);
		ctrl.setEmail(false);
		ctrl.setGondermeTarihi(false);
		ctrl.setGondermeTarihiText(false);
		ctrl.setHacizBaslangicTarihi(false);
		ctrl.setHacizBaslangicTarihiText(false);
		ctrl.setHacizMiktari(false);
		ctrl.setHacizSirasi(false);
		ctrl.setHazirlayan(false);
		ctrl.setHazirlayanAdSoyad(false);
		ctrl.setHazirlayanId(false);
		ctrl.setHazirlayanText(false);
		ctrl.setIcraDosyaNo(false);
		ctrl.setIcraMudurluguAdi(false);
		ctrl.setIcraMudurluguId(false);
		ctrl.setKonu(false);
		ctrl.setKurumAdi(false);
		ctrl.setKurumAdlari(false);
		ctrl.setMaasMuvafakat(false);
		ctrl.setMalBilgisi(false);
		ctrl.setMalTipiAdi(false);
		ctrl.setMalTipiId(false);
		ctrl.setMasrafMiktari(false);
		ctrl.setMasrafTipiAdi(false);
		ctrl.setMasrafTipiId(false);
		ctrl.setMernisAdresi(false);
		ctrl.setMuameleAdi(false);
		ctrl.setMuameleStatuAdi(false);
		ctrl.setMuameleStatusuId(false);
		ctrl.setMuameleTarihi(false);
		ctrl.setMuameleTarihiText(false);
		ctrl.setMuhatapAdi(false);
		ctrl.setMuhatapAdresi(false);
		ctrl.setPersonelId(false);
		ctrl.setPlaka(false);
		ctrl.setPostaneAdi(false);
		ctrl.setYurticiAdresi(false);
		ctrl.setVergiNo(false);
		ctrl.setVergiKimlikNo(false);
		ctrl.setVekili(false);
		ctrl.setTel(false);
		ctrl.setTebligatTarihiText(false);
		ctrl.setTebligatTarihi(false);
		ctrl.setTebligatSonucuId(false);
		ctrl.setTebligatSonucu(false);
		ctrl.setTapuKayitlari(false);
		ctrl.setTalepIfadesi(false);
		ctrl.setSirketAdi(false);
		ctrl.setSgkAdresi(false);
		ctrl.setSemiramisNo(false);
		ctrl.setRiskYoneticisiText(false);
		ctrl.setRiskYoneticisiId(false);
		ctrl.setPttIlText(false);
		ctrl.setPttIlceText(false);
		
	

		if (muamele.isDavetiyemuzekkeresi103arac()) {
		
			ctrl.setAlacakliTel(true);
			ctrl.setAlacakliMail(true);
			ctrl.setBankaBilgileri(true);
			ctrl.setBorcluAdi(true);
			ctrl.setBorcluTc(true);
			ctrl.setBorcluAdresi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setSemiramisNo(true);
			ctrl.setHacizBaslangicTarihi(true);
			ctrl.setPlaka(true);
			ctrl.setMuameleTarihi(true);
			ctrl.setAvukatId(true);
			ctrl.setHazirlayanText(true);
			ctrl.setRiskYoneticisiText(true);

		}

		if (muamele.isDavetiyemuzekkeresi103gayrimenkul()) {

			ctrl.setAlacakliTel(true);
			ctrl.setAlacakliMail(true);
			ctrl.setBankaBilgileri(true);
			ctrl.setBorcluAdi(true);
			ctrl.setBorcluTc(true);
			ctrl.setBorcluAdresi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setSemiramisNo(true);
			ctrl.setHacizBaslangicTarihi(true);
			ctrl.setPlaka(true);
			ctrl.setMuameleTarihi(true);
			ctrl.setAvukatId(true);
			ctrl.setHazirlayanText(true);
			ctrl.setRiskYoneticisiText(true);

		}

		if (muamele.isDavetiyemuzekkeresi103menkul()) {
			ctrl.setAlacakliTel(true);
			ctrl.setAlacakliMail(true);
			ctrl.setBankaBilgileri(true);
			ctrl.setBorcluAdi(true);
			ctrl.setBorcluTc(true);
			ctrl.setBorcluAdresi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setSemiramisNo(true);
			ctrl.setHacizBaslangicTarihi(true);
			ctrl.setPlaka(true);
			ctrl.setMuameleTarihi(true);
			ctrl.setAvukatId(true);
			ctrl.setHazirlayanText(true);
			ctrl.setRiskYoneticisiText(true);

		}

		if (muamele.isDavetiyemuzekkeresi103sgk()) {
			ctrl.setAlacakliTel(true);
			ctrl.setAlacakliMail(true);
			ctrl.setBankaBilgileri(true);
			ctrl.setBorcluAdi(true);
			ctrl.setBorcluTc(true);
			ctrl.setBorcluAdresi(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setSemiramisNo(true);
			ctrl.setHacizBaslangicTarihi(true);
			ctrl.setPlaka(true);
			ctrl.setMuameleTarihi(true);
			ctrl.setAvukatId(true);
			ctrl.setHazirlayanText(true);
			ctrl.setRiskYoneticisiText(true);

		}

		if (muamele.isDavetiyetalebi103()) {
			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAvukatId(true);
			ctrl.setMuameleTarihi(true);

		}

		if (muamele.isAdresarastirmamuzekkeresikurumlaricin()) {
			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAlacakliAdi(true);
			ctrl.setBorcluTc(true);
			ctrl.setMuameleTarihi(true);
			ctrl.setAvukatId(true);
			ctrl.setKurumAdi(true);

		}

		if (muamele.isAdresarastimatalebi()) {
			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAvukatId(true);
			ctrl.setKurumAdi(true);
			ctrl.setAlacakliAdi(true);
			ctrl.setAvukatAdi(true);
			ctrl.setKurumAdi(true);
			ctrl.setMuameleTarihi(true);

		}

		if (muamele.isAilekayittablosutalebi()) {

			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAvukatId(true);
			ctrl.setMuameleTarihi(true);

		}
		if (muamele.isAracserhitalebi()) {
			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAvukatId(true);
			ctrl.setPlaka(true);
			ctrl.setMuameleTarihi(true);

		}

		if (muamele.isAracyakalamatalebi()) {
			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setPlaka(true);
			ctrl.setMuameleTarihi(true);

		}

		if (muamele.isDosyaislemdenkaldirilmatalebiharcburoda()) {
			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setPlaka(true);
			ctrl.setPlaka(true);
			ctrl.setMuameleTarihi(true);

		}

		if (muamele.isDosyaislemdenkaldirilmatalebiharcborcluda()) {
			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setPlaka(true);
			ctrl.setPlaka(true);
			ctrl.setMuameleTarihi(true);

		}

		if (muamele.isHacizihbarnamesimuzekkeresi891()) {
			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAlacakliBankasi(true);
			ctrl.setAvukatId(true);
			ctrl.setBorcluTcKimlikNo(true);
			ctrl.setKonu(true);
			ctrl.setSemiramisNo(true);
			ctrl.setHazirlayanId(true);
			ctrl.setRiskYoneticisiId(true);
			ctrl.setMuameleTarihi(true);
			ctrl.setMuameleTarihi(true);
			ctrl.setAlacakliTel(true);
			ctrl.setAlacakliMail(true);
			ctrl.setBorcluMiktari(true);
			ctrl.setBuroIbanNo(true);
			ctrl.setBuroAdresi(true);

		}

		if (muamele.isHacizihbarnamesitalebibankalaricin()) {
			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAvukatId(true);
			ctrl.setAlacakliBankasi(true);
			ctrl.setMuameleTarihi(true);
		}

		if (muamele.isMaashacizmuzekkeresigenel()) {

			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAlacakliBankasi(true);
			ctrl.setAvukatId(true);
			ctrl.setBorcluTcKimlikNo(true);
			ctrl.setKonu(true);
			ctrl.setSemiramisNo(true);
			ctrl.setHazirlayanId(true);
			ctrl.setRiskYoneticisiId(true);
			ctrl.setMuameleTarihi(true);
			ctrl.setMuameleTarihi(true);
			ctrl.setAlacakliTel(true);
			ctrl.setAlacakliMail(true);
			ctrl.setBorcluMiktari(true);
			ctrl.setBuroIbanNo(true);
			ctrl.setBuroAdresi(true);
			ctrl.setKonu(true);
			ctrl.setRiskYoneticisiId(true);

		}

		if (muamele.isMaashacizmuzekkeresimuvafakat()) {
			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAlacakliBankasi(true);
			ctrl.setAvukatId(true);
			ctrl.setBorcluTcKimlikNo(true);
			ctrl.setKonu(true);
			ctrl.setSemiramisNo(true);
			ctrl.setHazirlayanId(true);
			ctrl.setRiskYoneticisiId(true);
			ctrl.setMuameleTarihi(true);
			ctrl.setMuameleTarihi(true);
			ctrl.setAlacakliTel(true);
			ctrl.setAlacakliMail(true);
			ctrl.setBorcluMiktari(true);
			ctrl.setBuroIbanNo(true);
			ctrl.setBuroAdresi(true);
			ctrl.setRiskYoneticisiId(true);
			ctrl.setMaasMuvafakat(true);

		}

		if (muamele.isMaashaciztalebigenel()) {

			ctrl.setIcraMudurluguId(true);
		}

		if (muamele.isMaashaciztalebimuvafakat()) {

			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setMuameleTarihi(true);
		}

		if (muamele.isMenkulhaciztalebi()) {
			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAvukatId(true);
			ctrl.setMuameleTarihi(true);

		}

		if (muamele.isMernisadresineodemeemritalebi()) {

			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAvukatId(true);
			ctrl.setMuameleTarihi(true);
			ctrl.setMernisAdresi(true);

		}

		if (muamele.isMevduathaczimuzekkeresi()) {

			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAvukatId(true);
			ctrl.setAlacakliAdi(true);
			ctrl.setMuameleTarihi(true);
			ctrl.setGondermeTarihi(true);
			ctrl.setBuroAdresi(true);
			ctrl.setAlacakliTel(true);
			ctrl.setBorcluAdresi(true);
			ctrl.setBuroIbanNo(true);
			ctrl.setBorcluMiktari(true);

		}

		if (muamele.isMevduathaczitalebi()) {

			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setMuameleTarihi(true);

		}

		if (muamele.isPtthacizmuzekkeresi()) {

			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setMuameleTarihi(true);
			ctrl.setPostaneAdi(true);
			ctrl.setAvukatId(true);
			ctrl.setPttIlceText(true);
			ctrl.setPttIlText(true);
			ctrl.setAlacakliTel(true);
			ctrl.setAlacakliMail(true);
			ctrl.setBorcluMiktari(true);
			ctrl.setAlacakliAdi(true);
			ctrl.setMuameleTarihi(true);
			ctrl.setGondermeTarihi(true);
			ctrl.setBuroAdresi(true);
		}

		if (muamele.isPtthaciztalebi()) {

			ctrl.setIcraMudurluguId(true);
			ctrl.setMuameleTarihi(true);

		}

		if (muamele.isSgkadresiodemeemritalebi()) {
			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAvukatId(true);
			ctrl.setMuameleTarihi(true);
			ctrl.setSgkAdresi(true);

		}

		if (muamele.isTapuhacizmuzekkeresinokta()) {

			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAvukatId(true);
			ctrl.setBorcluTc(true);
			ctrl.setSemiramisNo(true);
			ctrl.setHazirlayanId(true);
			ctrl.setRiskYoneticisiId(true);
			ctrl.setMuameleTarihi(true);
			ctrl.setBorcluMiktari(true);
			ctrl.setDogumTarihi(true);
			ctrl.setKonu(true);
			
			RequestContext.getCurrentInstance().execute("PF('dlgGayrimenkul').show();");

		}

		if (muamele.isTapuhaciztalebi()) {

			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setMuameleTarihi(true);

		}

		if (muamele.isTicaretsiciladressormatalebi()) {

			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setAvukatId(true);
			ctrl.setMuameleTarihi(true);

		}

		if (muamele.isTk21talebi()) {
			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setMuameleTarihi(true);
			ctrl.setMernisAdresi(true);

		}

		if (muamele.isYenilemetalebi()) {

			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setMuameleTarihi(true);

		}

		if (muamele.isYurticiadresiodemeemritalebi()) {

			ctrl.setIcraMudurluguId(true);
			ctrl.setIcraDosyaNo(true);
			ctrl.setMuameleTarihi(true);
			ctrl.setAvukatId(true);
			ctrl.setYurticiAdresi(true);

		}

	}
}
