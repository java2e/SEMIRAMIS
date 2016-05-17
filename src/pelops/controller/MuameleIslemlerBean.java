package pelops.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.lowagie.text.pdf.codec.Base64.OutputStream;


import pelops.dao.MuameleIslemleriDAO;
import pelops.dao.PostaDAO;
import pelops.interfaces.ReportCRUDInterface;
import pelops.model.Avukat;
import pelops.model.MuameleIslemleri;
import pelops.model.Posta;
import pelops.model.TebligatZarfi;

import java.io.InputStream;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@ManagedBean(name = "muameleislemlerbean")
@SessionScoped
public class MuameleIslemlerBean implements ReportCRUDInterface {

	private ArrayList<MuameleIslemleri> muameleList;
	MuameleIslemleri muamele = new MuameleIslemleri();
	MuameleIslemleriDAO dao = new MuameleIslemleriDAO();
	private MuameleIslemleri muameleIslemleri;
	private JasperPrint jasperPrint;
	private JasperPrint jasperPrint2;
	private boolean panel1Visible = true;
	private boolean panel2Visible = false;
	private boolean onizleButtonVisible = true;
	private DefaultStreamedContent stream;
	private int whichContentType = 1;
	private int content_id = 0;
	private int sayac = 0;
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
	private String idFile;
	String gelisAmaci;

	public String getIdFile() {
		return java.util.UUID.randomUUID().toString();
	}

	public void setIdFile(String idFile) {
		this.idFile = idFile;
	}

	public MuameleIslemlerBean() throws Exception {

		this.setIptalrender(false);
		this.setduzenlesilrender(false);
		muamele.setStatus(0);
//		muamele.setBorcluAdi(AktifBean.getBorcluAdi());
//		muamele.setIcraDosyaNo(AktifBean.icraDosyaNo);
		
		muamele.setBorcluAdi("Deneme Statik");
		muamele.setIcraDosyaNo("2016_9999");

		// muamele.setPdf("./pdfler/default.pdf?pfdrid_c=true");

		// Listenin Getirilmesi sağlanır.
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
			System.out.println(pdf);
		}
		return pdf;
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

	public void yazdir() throws Exception {

		uretilenBarkod = barkodUret();
		Duzenle();
	
	}

	@SuppressWarnings("unused")

	public void pdfOnizleKayit() throws Exception {

		Kaydet();
		Duzenle();
		// muamele = dao.getMuameleFromList(onizleDuzenleID);
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
			deger = "Dosya İşlemden Kaldırılma Talebi";
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
			deger = "Ödeme Emri Faizsiz";
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
			deger = "Takip Talebi Masrafsız";
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
		zarf.setTalepMuzekkereAdi(talepMuzekkereismiBul(muzekkereTalep));

		dataBeanListForTebligat.add(zarf);

		HashMap<String, Object> parameters = new HashMap<String, Object>();
		String pathName = "D:/testworkspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SEMIRAMIS/reports/tebligat_zarfi.jrxml";
		InputStream inputStream = new FileInputStream(pathName);
		System.out.println("path name :" + pathName);
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

	public void setStream(DefaultStreamedContent stream) {
		this.stream = stream;
	}

	boolean iptalrender, duzenlesilrender;
	private String str;
	private String uretilenBarkod;

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

		muameleList = null;
		muameleList = new ArrayList<>();

		// 103 Davetiye Müzekkere
		if (muamele.isMuzekkere1()) {
			MuameleIslemleri muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getMuzekkere1Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("103 Davetiyesi Muzekkeresi");

			muameleList.add(muameleIslemleri);
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getMuzekkere1Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("103 Davetiyesi Talebi");
			muameleList.add(muameleIslemleri);

		}

		// Haciz İhbarnamesi Müzekkeresi
		if (muamele.isMuzekkere2()) {
			MuameleIslemleri muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getMuzekkere2Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("89/1 Haciz Ihbarnamesi Muzekkeresi");
			muameleList.add(muameleIslemleri);
			muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getMuzekkere2Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("89/1 Haciz Ihbarnamesi Talebi");
			muameleList.add(muameleIslemleri);

		}

		// Revize Edilecek
		if (muamele.isMuzekkere3()) {
			MuameleIslemleri muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri.setIcraDosyaId(muamele.getIcraDosyaId());
			muameleIslemleri.setBorcluAdi(muamele.getBorcluAdi());
			muameleIslemleri.setMuzekkereAdi("Muzekkere 3");
			muameleIslemleri.setTalepAdi("");
			muameleIslemleri.setMiktar(muamele.getMuzekkere3Sayi());
			muameleIslemleri.setIcraDosyaNo(muamele.getIcraDosyaNo());
			muameleIslemleri.setTarih(muamele.getTarih());
			muameleIslemleri.setMuzekkereTalepAdi("Muzekkere 3");
			muameleList.add(muameleIslemleri);
		}

		// Revize Edilecek
		if (muamele.isMuzekkere4()) {
			MuameleIslemleri muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri.setIcraDosyaId(muamele.getIcraDosyaId());
			muameleIslemleri.setBorcluAdi(muamele.getBorcluAdi());
			muameleIslemleri.setIcraDosyaNo(muamele.getIcraDosyaNo());
			muameleIslemleri.setMuzekkereAdi("Muzekkere 4");
			muameleIslemleri.setTalepAdi("");
			muameleIslemleri.setMiktar(muamele.getMuzekkere4Sayi());
			muameleIslemleri.setTarih(muamele.getTarih());
			muameleIslemleri.setMuzekkereTalepAdi("Muzekkere 4");
			muameleList.add(muameleIslemleri);
		}

		// Revize Edilecek
		if (muamele.isMuzekkere5()) {
			MuameleIslemleri muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri.setIcraDosyaId(muamele.getIcraDosyaId());
			muameleIslemleri.setIcraDosyaNo(muamele.getIcraDosyaNo());
			muameleIslemleri.setBorcluAdi(muamele.getBorcluAdi());
			muameleIslemleri.setMuzekkereAdi("Muzekkere 5");
			muameleIslemleri.setTalepAdi("");
			muameleIslemleri.setMiktar(muamele.getMuzekkere5Sayi());
			muameleIslemleri.setTarih(muamele.getTarih());
			muameleIslemleri.setMuzekkereTalepAdi("Muzekkere 5");
			muameleList.add(muameleIslemleri);
		}

		// Revize Edilecek
		if (muamele.isMuzekkere6()) {
			MuameleIslemleri muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri.setIcraDosyaId(muamele.getIcraDosyaId());
			muameleIslemleri.setIcraDosyaNo(muamele.getIcraDosyaNo());
			muameleIslemleri.setBorcluAdi(muamele.getBorcluAdi());
			muameleIslemleri.setMuzekkereAdi("Muzekkere 6");
			muameleIslemleri.setTalepAdi("");
			muameleIslemleri.setMiktar(muamele.getMuzekkere6Sayi());
			muameleIslemleri.setTarih(muamele.getTarih());
			muameleIslemleri.setMuzekkereTalepAdi("Muzekkere 6");
			muameleList.add(muameleIslemleri);
		}

		// Revize Edilecek
		if (muamele.isMuzekkere7()) {
			MuameleIslemleri muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri.setIcraDosyaId(muamele.getIcraDosyaId());
			muameleIslemleri.setIcraDosyaNo(muamele.getIcraDosyaNo());
			muameleIslemleri.setBorcluAdi(muamele.getBorcluAdi());
			muameleIslemleri.setMuzekkereAdi("Muzekkere 7");
			muameleIslemleri.setTalepAdi("");
			muameleIslemleri.setMiktar(muamele.getMuzekkere7Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("Muzekkere 7");
			muameleIslemleri.setTarih(muamele.getTarih());
			muameleList.add(muameleIslemleri);
		}

		// Revize Edilecek
		if (muamele.isMuzekkere8()) {
			MuameleIslemleri muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri.setIcraDosyaId(muamele.getIcraDosyaId());
			muameleIslemleri.setIcraDosyaNo(muamele.getIcraDosyaNo());
			muameleIslemleri.setBorcluAdi(muamele.getBorcluAdi());
			muameleIslemleri.setMuzekkereAdi("Muzekkere 8");
			muameleIslemleri.setTalepAdi("");
			muameleIslemleri.setMiktar(muamele.getMuzekkere8Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("Muzekkere 8");
			muameleIslemleri.setTarih(muamele.getTarih());
			muameleList.add(muameleIslemleri);
		}

		// Revize Edilecek
		if (muamele.isMuzekkere9()) {
			MuameleIslemleri muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri.setIcraDosyaId(muamele.getIcraDosyaId());
			muameleIslemleri.setIcraDosyaNo(muamele.getIcraDosyaNo());
			muameleIslemleri.setBorcluAdi(muamele.getBorcluAdi());
			muameleIslemleri.setMuzekkereAdi("Muzekkere 9");
			muameleIslemleri.setTalepAdi("");
			muameleIslemleri.setMiktar(muamele.getMuzekkere9Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("Muzekkere 9");
			muameleIslemleri.setTarih(muamele.getTarih());
			muameleList.add(muameleIslemleri);
		}

		// 103 Davetiye Talebi
		if (muamele.isTalep1()) {
			MuameleIslemleri muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getTalep1Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("103 Davetiyesi Talebi");
			muameleList.add(muameleIslemleri);

		}

		// 89/1 Haciz İhbarnamesi Talebi
		if (muamele.isTalep2()) {

			MuameleIslemleri muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getTalep2Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("89/1 Haciz Ihbarnamesi Talebi");
			muameleList.add(muameleIslemleri);

		}

		// TK/21 Talebi
		if (muamele.isTalep3()) {
			MuameleIslemleri muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getTalep3Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("TK/21 Talebi");
			muameleList.add(muameleIslemleri);

		}

		// Menkul Haciz Talebi
		if (muamele.isTalep4()) {
			MuameleIslemleri muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getTalep4Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("Menkul Haciz Talebi");
			muameleList.add(muameleIslemleri);

		}

		// Dosyanın İşlemden Kaldırılması Talebi
		if (muamele.isTalep5()) {
			MuameleIslemleri muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getTalep5Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("Dosyanin Islenmden Kaldirilmasi Telebi");
			muameleList.add(muameleIslemleri);

		}
		// Adresi Arastırması Talebi
		if (muamele.isTalep6()) {
			MuameleIslemleri muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getTalep6Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("Adresi Arastirmasi Talebi");
			muameleList.add(muameleIslemleri);

		}
		// Mernis Adresine Ödeme Emri Gönderilmesi Talebi
		if (muamele.isTalep7()) {
			MuameleIslemleri muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getTalep7Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("Mernis Adresine Odeme Emri Gonderilmesi Talebi");
			muameleList.add(muameleIslemleri);

		}

		// Feragat Talebi
		if (muamele.isTalep8()) {
			MuameleIslemleri muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getTalep8Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("Feragat Talebi");
			muameleList.add(muameleIslemleri);

		}

		// Mevduat Haczi Talebi
		if (muamele.isTalep9()) {
			MuameleIslemleri muameleIslemleri = new MuameleIslemleri();
			muameleIslemleri = nesneDoldur(muamele);
			muameleIslemleri.setMiktar(muamele.getTalep9Sayi());
			muameleIslemleri.setMuzekkereTalepAdi("Mevduat Haczi Talebi");
			muameleList.add(muameleIslemleri);
		}

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

	public MuameleIslemleri nesneDoldur(MuameleIslemleri muamele) {

		muameleIslemleri = new MuameleIslemleri();
		muameleIslemleri.setBaslik(muamele.getBaslik());
		muameleIslemleri.setParagraf1(muamele.getParagraf1());
		muameleIslemleri.setParagraf2(muamele.getParagraf2());
		muameleIslemleri.setBorcluAdi(muamele.getBorcluAdi());
		muameleIslemleri.setTarih(muamele.getTarih());
		muameleIslemleri.setIcraDosyaNo(muamele.getIcraDosyaNo());
		muameleIslemleri.setAciklama(muamele.getAciklama());
		muameleIslemleri.setAlacakFaizTutari(muamele.getAlacakFaizTutari());
		muameleIslemleri.setAlacakliBankasi(muamele.getAlacakliBankasi());
		muameleIslemleri.setAlacakliMail(muamele.getAlacakliMail());
		muameleIslemleri.setAlacakliTel(muamele.getAlacakliTel());
		muameleIslemleri.setAvukatId(muamele.getAvukatId());
		muameleIslemleri.setBankaBilgileri(muamele.getBankaBilgileri());
		muameleIslemleri.setBarcode("123456789012");
		muameleIslemleri.setBorcluAdresi(muamele.getBorcluAdresi());
		muameleIslemleri.setBorcluTc(muamele.getBorcluTc());
		muameleIslemleri.setHacizBaslangicTarihi(muamele.getHacizBaslangicTarihi());
		muameleIslemleri.setHacizMiktari(muamele.getHacizMiktari());
		muameleIslemleri.setHacizSirasi(muamele.getHacizSirasi());
		muameleIslemleri.setHazirlayanId(muamele.getHazirlayanId());
		muameleIslemleri.setIcraDosyaId(muamele.getIcraDosyaId());
		muameleIslemleri.setIcraMudurluguId(muamele.getIcraMudurluguId());
		muameleIslemleri.setMaasMuvafakat(muamele.getMaasMuvafakat());
		muameleIslemleri.setMalBilgisi(muamele.getMalBilgisi());
		muameleIslemleri.setMalTipiId(muamele.getMalTipiId());
		muameleIslemleri.setMasrafMiktari(muamele.getMasrafMiktari());
		muameleIslemleri.setMasrafTipiId(muamele.getMasrafTipiId());
		muameleIslemleri.setMuameleStatusuId(muamele.getMuameleStatusuId());
		muameleIslemleri.setMuameleTarihi(muamele.getMuameleTarihi());
		muameleIslemleri.setMuhatapAdi(muamele.getMuhatapAdi());
		muameleIslemleri.setMuhatapAdresi(muamele.getMuhatapAdresi());
		muameleIslemleri.setPersonelId(muamele.getPersonelId());
		muameleIslemleri.setSemiramisNo(muamele.getSemiramisNo());
		muameleIslemleri.setTalepIfadesi(muamele.getTalepIfadesi());
		muameleIslemleri.setTebligatTarihi(muamele.getTebligatTarihi());
		muameleIslemleri.setTebligatSonucu(muamele.getTebligatSonucu());
		muameleIslemleri.setIcraMudurluguAdi(muamele.getIcraMudurluguAdi());
		muameleIslemleri.setHazirlayanAdi(muamele.getHazirlayanAdi());
		muameleIslemleri.setAvukatAdi(muamele.getAvukatAdi());

		return muameleIslemleri;
	}

	public JasperPrint OnizleAndKaydet() throws FileNotFoundException, JRException, InterruptedException {

		// İlgili Talep ve Müzekkerelerin Checkbock kontrolü
		muamele.setMuzekkere1(false);
		muamele.setMuzekkere2(false);
		muamele.setMuzekkere3(false);
		muamele.setMuzekkere4(false);
		muamele.setMuzekkere5(false);
		muamele.setMuzekkere6(false);
		muamele.setMuzekkere7(false);
		muamele.setMuzekkere8(false);
		muamele.setMuzekkere9(false);
		muamele.setTalep1(false);
		muamele.setTalep2(false);
		muamele.setTalep3(false);
		muamele.setTalep4(false);
		muamele.setTalep5(false);
		muamele.setTalep6(false);
		muamele.setTalep7(false);
		muamele.setTalep8(false);
		muamele.setTalep9(false);

		// İlgili Talep ve Müzekkerelerin Visible Değerleri
		muamele.setMuzekkere1Visible(true);
		muamele.setMuzekkere2Visible(true);
		muamele.setMuzekkere3Visible(true);
		muamele.setMuzekkere4Visible(true);
		muamele.setMuzekkere5Visible(true);
		muamele.setMuzekkere6Visible(true);
		muamele.setMuzekkere7Visible(true);
		muamele.setMuzekkere8Visible(true);
		muamele.setMuzekkere9Visible(true);
		muamele.setTalep1Visible(true);
		muamele.setTalep2Visible(true);
		muamele.setTalep3Visible(true);
		muamele.setTalep4Visible(true);
		muamele.setTalep5Visible(true);
		muamele.setTalep6Visible(true);
		muamele.setTalep7Visible(true);
		muamele.setTalep8Visible(true);
		muamele.setTalep9Visible(true);

		onizleButtonVisible = false;

		switch (muzekkereTalep) {

		case "103 Davetiyesi Muzekkeresi":

			muzekkereTalep = "103davetiyesimuzekkeresi";
			muamele.setMuzekkere1(true);
			muamele.setMuzekkere1Visible(false);

			break;

		case "89/1 Haciz Ihbarnamesi Muzekkeresi":

			muzekkereTalep = "hacizihbarnamesimuzekkere";
			muamele.setMuzekkere2(true);
			muamele.setMuzekkere2Visible(false);

			break;

		case "Muzekkere 3":

			muzekkereTalep = "muzekkere3";
			muamele.setMuzekkere3(true);
			muamele.setMuzekkere3Visible(false);

			break;

		case "Muzekkere 4":

			muzekkereTalep = "muzekkere4";
			muamele.setMuzekkere4(true);
			muamele.setMuzekkere4Visible(false);
			break;

		case "Muzekkere 5":

			muzekkereTalep = "muzekkere5";
			muamele.setMuzekkere5(true);
			muamele.setMuzekkere5Visible(false);
			break;

		case "muzekkere6":

			muzekkereTalep = "muzekkere6";
			muamele.setMuzekkere6(true);
			muamele.setMuzekkere6Visible(false);
			break;

		case "muzekkere7":

			muzekkereTalep = "muzekkere7";
			muamele.setMuzekkere7(true);
			muamele.setMuzekkere7Visible(false);
			break;

		case "muzekkere8":

			muzekkereTalep = "muzekkere8";
			muamele.setMuzekkere8(true);
			muamele.setMuzekkere8Visible(false);
			break;

		case "muzekkere9":

			muzekkereTalep = "muzekkere9";
			muamele.setMuzekkere9(true);
			muamele.setMuzekkere9Visible(false);
			break;

		case "103 Davetiyesi Talebi":

			muzekkereTalep = "103davetiyesitalebi";
			muamele.setTalep1(true);
			muamele.setTalep1Visible(false);
			break;

		case "89/1 Haciz Ihbarnamesi Talebi":

			muzekkereTalep = "hacizihbarnametalebi";
			muamele.setTalep2(true);
			muamele.setTalep2Visible(false);
			break;

		case "TK/21 Talebi":
			muzekkereTalep = "tk21talebi";
			muamele.setTalep3(true);
			muamele.setTalep3Visible(false);
			break;

		case "Menkul Haciz Talebi":

			muzekkereTalep = "menkulhaciztalebi";
			muamele.setTalep4(true);
			muamele.setTalep4Visible(false);
			break;

		case "Dosyanın islemden kaldırılması talebi":

			muzekkereTalep = "dosyaislemdenkaldirilmatalebi";
			muamele.setTalep5(true);
			muamele.setTalep5Visible(false);
			break;

		case "Adresi Arastirmasi Talebi":

			muzekkereTalep = "adresarastirmatalebi";
			muamele.setTalep6(true);
			muamele.setTalep6Visible(false);
			break;

		case "Mernis Adresine Odeme Emri Gonderilmesi Talebi":

			muzekkereTalep = "mernisadresineodemeemritalebi";
			muamele.setTalep7(true);
			muamele.setTalep7Visible(false);
			break;

		case "Feragat Talebi":

			muzekkereTalep = "feragattalebi";
			muamele.setTalep8(true);
			muamele.setTalep8Visible(false);
			break;

		case "Mevduat Haczi Talebi":

			muzekkereTalep = "mevduathaczitalebi";
			muamele.setTalep9(true);
			muamele.setTalep9Visible(false);
			break;

		default:
			break;
		}

		// muzekkere1 103 Davetiye Müzekkere
		if (muzekkereTalep.equals("103davetiyesimuzekkeresi")) {
			muamele.setMuzekkere1(true);
			muamele.setMuzekkere1Visible(false);
			whichContentType = 2;
			contentAdi = "103davetiyesiMuzekkeresi";
			content_id = 10;
		}

		// muzekkere2 haciz İhbarname Müzekkere
		if (muzekkereTalep.equals("hacizihbarnamesimuzekkere")) {
			muamele.setMuzekkere2(true);
			muamele.setMuzekkere2Visible(false);
			whichContentType = 3;
			contentAdi = "HacizIhbarnamesiMuzekkere";
			content_id = 11;
		}
		if (muzekkereTalep.equals("muzekkere3")) {
			muamele.setMuzekkere3(true);
			muamele.setMuzekkere3Visible(false);
			whichContentType = 4;
			contentAdi = "muzekkere3";
		}
		if (muzekkereTalep.equals("muzekkere4")) {
			muamele.setMuzekkere4(true);
			muamele.setMuzekkere4Visible(false);
			whichContentType = 5;
			contentAdi = "muzekkere4";
		}
		if (muzekkereTalep.equals("muzekkere5")) {
			muamele.setMuzekkere5(true);
			muamele.setMuzekkere5Visible(false);
			whichContentType = 6;
			contentAdi = "muzekkere5";
		}
		if (muzekkereTalep.equals("muzekkere6")) {
			muamele.setMuzekkere6(true);
			muamele.setMuzekkere6Visible(false);
			whichContentType = 7;
			contentAdi = "muzekkere6";
		}
		if (muzekkereTalep.equals("muzekkere7")) {
			muamele.setMuzekkere7(true);
			muamele.setMuzekkere7Visible(false);
			whichContentType = 8;
			contentAdi = "muzekkere7";
		}
		if (muzekkereTalep.equals("muzekkere8")) {
			muamele.setMuzekkere8(true);
			muamele.setMuzekkere8Visible(false);
			whichContentType = 9;
			contentAdi = "muzekkere8";
		}
		if (muzekkereTalep.equals("muzekkere9")) {
			muamele.setMuzekkere9(true);
			muamele.setMuzekkere9Visible(false);
			whichContentType = 10;
			contentAdi = "muzekkere9";
		}

		// 103 Davetiye Talebi
		if (muzekkereTalep.equals("103davetiyesitalebi")) {
			muamele.setTalep1(true);
			muamele.setTalep1Visible(false);
			whichContentType = 11;
			content_id = 1;
			contentAdi = "davetiye103talep";
		}

		// Haciz İhbarname Talebi
		if (muzekkereTalep.equals("hacizihbarnametalebi")) {
			muamele.setTalep2(true);
			muamele.setTalep2Visible(false);
			whichContentType = 12;
			content_id = 4;
			contentAdi = "hacizihbarnametalebi";
		}

		// TK/21 Talebi
		if (muzekkereTalep.equals("tk21talebi")) {
			muamele.setTalep3(true);
			muamele.setTalep3Visible(false);
			whichContentType = 13;
			content_id = 2;
			contentAdi = "tk21talebi";

		}

		// Menkul Haciz Talebi
		if (muzekkereTalep.equals("menkulhaciztalebi")) {
			muamele.setTalep4(true);
			muamele.setTalep4Visible(false);
			whichContentType = 14;
			content_id = 3;
			contentAdi = "menkulhaciztaebi";
		}

		// Dosya İşlemden Kaldırılma Talebi
		if (muzekkereTalep.equals("dosyaislemdenkaldirilmatalebi")) {
			muamele.setTalep5(true);
			muamele.setTalep5Visible(false);
			whichContentType = 15;
			contentAdi = "dosyaislemdenkaldirilmatalebi";
			content_id = 8;

		}

		// Adres Araştırma Talebi
		if (muzekkereTalep.equals("adresarastirmatalebi")) {
			muamele.setTalep6(true);
			muamele.setTalep6Visible(false);
			whichContentType = 16;
			contentAdi = "adresarastirmatalebi";
			content_id = 7;
		}

		// Mernis Adresine Odeme Emri Talebi
		if (muzekkereTalep.equals("mernisadresineodemeemritalebi")) {
			muamele.setTalep7(true);
			muamele.setTalep7Visible(false);
			whichContentType = 17;
			contentAdi = "mernisAdresineOdemeEmriTalebi";
			content_id = 6;

		}

		// Feragat Talebi
		if (muzekkereTalep.equals("feragattalebi")) {
			muamele.setTalep8(true);
			muamele.setTalep8Visible(false);
			whichContentType = 18;
			content_id = 9;
			contentAdi = "feragattalebi";
		}

		// Mevduat Haczi Talebi
		if (muzekkereTalep.equals("mevduathaczitalebi")) {
			muamele.setTalep9(true);
			muamele.setTalep9Visible(false);
			whichContentType = 19;
			contentAdi = "mevduatHacziTalebi";
			content_id = 5;
		}

		// muamele listesi yeniden viewe gore cekilecek

		ArrayList<MuameleIslemleri> dataBeanList = new ArrayList<MuameleIslemleri>();
		dataBeanList.add(muamele);
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		String pathName = "D:/testworkspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SEMIRAMIS/reports/"
				+ muzekkereTalep + ".jrxml";

		InputStream inputStream = new FileInputStream(pathName);

		path = "D:/testworkspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SEMIRAMIS/pdfler/"
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

		gelisAmaci = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("gelis_amaci")
				.toString());

		if (gelisAmaci.equals("duzenle")) {

			duzenleID = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
					.get("buttonDuzenle").toString());

			muzekkereTalep = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
					.get("muzekkeretalep").toString());

		}

		// Önizleme ve Düzenle Metodlarından ulaşım için yapıldı.
		onizleDuzenleID = duzenleID;
		// önizlemede kullanılacak
		muamele = dao.getMuameleFromList(onizleDuzenleID);

		JasperPrint talepMuzekkere = new JasperPrint();
		JasperPrint tebligat = new JasperPrint();
		ArrayList<JasperPrint> list = new ArrayList<JasperPrint>();

		// Talep Müzekkere ve Tebligatın Hazırlanması
		talepMuzekkere = OnizleAndKaydet();
		tebligat = tebligatZarfiJasper(muamele, muzekkereTalep);

		// İstenilen Miktar Kadar Çıktının Alınmasını Sağlar
		for (int i = 0; i < muamele.getMiktar(); i++) {

			list.add(talepMuzekkere);
			list.add(tebligat);
		}
		
		String path;
		if (gelisAmaci.equals("duzenle")) {

			path = "D:/testworkspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SEMIRAMIS/pdfler/"
					+ muzekkereTalep + ".pdf";

		} else if (gelisAmaci.equals("onizleme")) {
			
			// gelis_amaci önizleme
			
			path = "D:/testworkspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SEMIRAMIS/pdfler/"
					+ muzekkereTalep + ".pdf";

			

		}else {
			
			// geliş amacı yazdir
			path = "D:/muzekkere_talep_ciktilari/" + muzekkereTalep + "_" + muamele.getIcraDosyaNo() + ".pdf";
		}

		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(SimpleExporterInput.getInstance(list));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
		SimplePdfExporterConfiguration config = new SimplePdfExporterConfiguration();
		config.setCreatingBatchModeBookmarks(true);
		exporter.exportReport();

		// Oluşturulan Pdf'in Gösterilmesi sağlanır.
		pdf = path;
		Thread.sleep(2000);
	}

	@Override
	public void Sil() throws Exception {

		String muzekkereTalep = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("muzekkereTalepAdi"));
		String icraDosyaNo = (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("icraDosyaNumarasi"));

		MuameleIslemleriDAO dao = new MuameleIslemleriDAO();
		dao.deleteRowIndex(muzekkereTalep, icraDosyaNo);

		// Kayıtların Yinelenmesi sağlanır
		muameleList = TümListeyiGetir();

	}

	@Override
	public ArrayList<MuameleIslemleri> TümListeyiGetir() throws Exception {
		return dao.getMuzekkereTalepList(muamele.getIcraDosyaNo());
	}

	@Override
	public void SecilenKaydiGetir() throws Exception {

	}

	public void yeniKayit() throws InterruptedException {

		muamele = new MuameleIslemleri();
		muamele.setBorcluAdi(AktifBean.getBorcluAdi());
		muamele.setIcraDosyaNo(AktifBean.icraDosyaNo);
		onizleButtonVisible = true;
		muzekkereTalep = "default";
		Thread.sleep(1000);

	}

	@Override
	public void Iptal() {
		// TODO Auto-generated method stub

	}
}
