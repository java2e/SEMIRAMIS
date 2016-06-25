package pelops.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import pelops.dao.XMLToSistemAktarimDAO;
import pelops.model.ExcellModel;
import pelops.model.FreeXML;
import pelops.model.FreeXMLHesap;
import pelops.model.XMLToUyap;

@ManagedBean(name = "xmltosistem")
@SessionScoped
public class XmlToSistemAktarim {

	
	ArrayList<FreeXML> FreeXmlList = new ArrayList<FreeXML>();
	
	FreeXML FreeXmlModel;
	
	ArrayList<FreeXMLHesap> tmplistHesap;
	
	
	
	
	public ArrayList<FreeXML> getFreeXmlList() {
		return FreeXmlList;
	}

	public void setFreeXmlList(ArrayList<FreeXML> freeXmlList) {
		FreeXmlList = freeXmlList;
	}

	public FreeXML getFreeXmlModel() {
		return FreeXmlModel;
	}

	public void setFreeXmlModel(FreeXML freeXmlModel) {
		FreeXmlModel = freeXmlModel;
	}

	public ArrayList<FreeXMLHesap> getTmplistHesap() {
		return tmplistHesap;
	}

	public void setTmplistHesap(ArrayList<FreeXMLHesap> tmplistHesap) {
		this.tmplistHesap = tmplistHesap;
	}

	ArrayList<XMLToUyap> XmlList ;

	ArrayList<ExcellModel> arrayListExcel ;	
	
	private String muvekkilinAdi;
	
	
	public String getMuvekkilinAdi() {
		return muvekkilinAdi;
	}

	public void setMuvekkilinAdi(String muvekkilinAdi) {
		this.muvekkilinAdi = muvekkilinAdi;
	}

	private int yuklemedurum = 0;
	
	private boolean btnXml, btnExcel, btnTblIsle, btnSistAktar;
	
	public XmlToSistemAktarim(){
		
		btnExcel = false;
		btnSistAktar =false;
		btnTblIsle = false;
		btnXml = false;
				
		
	}
	
	public boolean isBtnXml() {
		return btnXml;
	}

	public void setBtnXml(boolean btnXml) {
		this.btnXml = btnXml;
	}

	public boolean isBtnExcel() {
		return btnExcel;
	}

	public void setBtnExcel(boolean btnExcel) {
		this.btnExcel = btnExcel;
	}

	public boolean isBtnTblIsle() {
		return btnTblIsle;
	}

	public void setBtnTblIsle(boolean btnTblIsle) {
		this.btnTblIsle = btnTblIsle;
	}

	public boolean isBtnSistAktar() {
		return btnSistAktar;
	}

	public void setBtnSistAktar(boolean btnSistAktar) {
		this.btnSistAktar = btnSistAktar;
	}

	private boolean Yukleme= false;
	
	public boolean isYukleme() {
		return Yukleme;
	}

	public void setYukleme(boolean yukleme) {
		Yukleme = yukleme;
	}

	public int getYuklemedurum() {
		return yuklemedurum;
	}

	public void setYuklemedurum(int yuklemedurum) {
		this.yuklemedurum = yuklemedurum;
	}

	XMLToUyap XmlModel;

	String ChildOzellik;
	
	 private Timestamp islemTarihi;

	public ArrayList<XMLToUyap> getXmlList() {
		return XmlList;
	}

	public void setXmlList(ArrayList<XMLToUyap> xmlList) {
		XmlList = xmlList;
	}

	

	public Timestamp getIslemTarihi() {
		return islemTarihi;
	}

	public void setIslemTarihi(Timestamp islemTarihi) {
		this.islemTarihi = islemTarihi;
	}

	public void goster(){
		
		btnXml = true;
	}
	
	public void XMLDosyaAl(FileUploadEvent event) throws Exception {
	
		UploadedFile uploadedFile = (UploadedFile) event.getFile();
		
		if(muvekkilinAdi.equals("HSBC BANK A.Ş.")){
			XMLOku(uploadedFile);
			
			btnExcel = true;
		
		}
		else{
			FreeXMLOku(uploadedFile, this.getMuvekkilinAdi());
		btnSistAktar = true;
		}
		

		String dosyaAdi = uploadedFile.getFileName();
		String[] Uzanti = dosyaAdi.split("[.]");
		String DosyaIsmi="";
		
		File Klasor = new File("C:\\SEMIRAMIS_ICRA_KLASORU\\AKTARMA-XML");
		
		if(!Klasor.exists())Klasor.mkdir();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		Date tarih = new Date();
		if(Uzanti[1].equals("xml"))
		DosyaIsmi = dosyaAdi+"_"+format.format(tarih)+".xml";
		
		File dosya= new File("C:\\SEMIRAMIS_ICRA_KLASORU\\AKTARMA-XML"+"\\"+DosyaIsmi);
		
		FileUtils.copyInputStreamToFile(uploadedFile.getInputstream(), dosya);	
	
		
		
	}
	
	public void XLSDosyaAl(FileUploadEvent event) throws Exception {

		arrayListExcel = new ArrayList<ExcellModel>();
		UploadedFile uploadedFile = (UploadedFile) event.getFile();
		
		arrayListExcel = XLSOku(uploadedFile, XmlList.size()+1);
		btnTblIsle = true;
		btnSistAktar = true;
		
		
		String dosyaAdi = uploadedFile.getFileName();
		String[] Uzanti = dosyaAdi.split("[.]");
		String DosyaIsmi="";
		
		File Klasor = new File("C:\\SEMIRAMIS_ICRA_KLASORU\\AKTARMA-XLS");
		
		if(!Klasor.exists())Klasor.mkdir();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		Date tarih = new Date();
		if(Uzanti[1].equals("xlsx"))
		DosyaIsmi = dosyaAdi+"_"+format.format(tarih)+".xlsx";
		
		File dosya= new File("C:\\SEMIRAMIS_ICRA_KLASORU\\AKTARMA-XLS"+"\\"+DosyaIsmi);
		
		FileUtils.copyInputStreamToFile(uploadedFile.getInputstream(), dosya);	
	
		
		
	}

	
	public void SistemeAktar(){
		
		XMLToSistemAktarimDAO XMLDAO = new XMLToSistemAktarimDAO();

		if(muvekkilinAdi.equals("HSBC BANK A.Ş.")){
			
				for (int i = 0; i < XmlList.size(); i++) {
					XMLDAO.Kaydet(XmlList.get(i), arrayListExcel.get(i));
				
					}
		}else{
			
				for (int i = 0; i < FreeXmlList.size(); i++) {
					XMLDAO.FreeXMLSave(FreeXmlList.get(i));
				
					}
			
		}
				
				
		
		 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "XML AKTARIM UYARISI !!!", "LİSTEDEKİ BİLGİLER BARAŞI İLE SİSTEME AKTARILMIŞTIR.....");
		 RequestContext.getCurrentInstance().showMessageInDialog(message);
	
		 btnSistAktar = false;
		 btnExcel = false;
		 
	
	}
	
	
	public Double doubleYap(String sttutar){
		Double tutar = 0.0;
		if(sttutar==null || sttutar.equals("")){}
		else
		{
			int nokta = sttutar.indexOf(",");
			if(nokta<0) nokta = sttutar.indexOf(".");
			
		
		String tam = sttutar.substring(0, nokta);
		String kesir = sttutar.substring(nokta+1, sttutar.length());
		String sayi = tam + "." + kesir;
		tutar = Double.parseDouble(sayi); 
		}
		return tutar;
	}

	private void XMLOku(UploadedFile Dosya) throws Exception {


		 NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		XmlList = new ArrayList<XMLToUyap>();
		
		File dosya= new File("tmp.xml");
		FileUtils.copyInputStreamToFile(Dosya.getInputstream(), dosya);
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(dosya);
	
		NodeList nList_Dosya = doc.getElementsByTagName("dosya");
		
		for (int i = 0; i < nList_Dosya.getLength(); i++) { // ANA FOR DÖNGÜSÜ START (XML İçinceki Dosyasayısı kadar Döner)

		//	for (int i = 0; i < 19; i++) { // ANA FOR DÖNGÜSÜ START (XML İçinceki Dosyasayısı kadar Döner)

		
			Node DosyaIcerik = nList_Dosya.item(i);

			XmlModel = new XMLToUyap();
			
			for (int j = 0; j < DosyaIcerik.getAttributes().getLength(); j++) { // DOSYA' ya ait özellikleri listeler
												
					String Ozellik = DosyaIcerik.getAttributes().item(j).getNodeName();
					String bilgi = DosyaIcerik.getAttributes().item(j).getNodeValue();
					
					switch (Ozellik) {

					case "dosyaTipi":
						XmlModel.setDosyaTipi(bilgi);
						break;
					case "dosyaBelirleyicisi":
						XmlModel.setDosyaBelirleyicisi(bilgi);
						break;

					case "KKDFUygulansin":
						XmlModel.setKKDFUygulansin(bilgi);
						break;

					case "BSMVUygulansin":
						XmlModel.setBSMVUygulansin(bilgi);
						break;

					case "BK84MaddeUygulansin":
						XmlModel.setBK84MaddeUygulansin(bilgi);
						break;
					case "aciklama48e9":
						XmlModel.setAciklama48e9(bilgi);
						break;
					case "alacaklininTalepEttigiHak":
						XmlModel.setAlacaklininTalepEttigiHak(bilgi);
						break;
					case "takipSekli":
						XmlModel.setTakipSekli(bilgi);
						break;
					case "takipYolu":
						XmlModel.setTakipYolu(bilgi);
						break;
					case "takipTuru":
						XmlModel.setTakipTuru(bilgi);
						break;
					case "dosyaTuru":
						XmlModel.setDosyaTuru(bilgi);
					default:
						break;

					}
					
			}
						
			
						NodeList nList_Taraf_Vekilkisi_digerAlacak = DosyaIcerik.getChildNodes();
					
						for (int j1 = 0; j1 < nList_Taraf_Vekilkisi_digerAlacak.getLength(); j1++) { // TARAF VEKİLKİŞİ ve DİĞER ALACAK BAŞLANGIÇ
							
							
									Node  Taraf_Vekilkisi_Digeralacak_Icerik = nList_Taraf_Vekilkisi_digerAlacak.item(j1);
									for (int j2 = 0; j2 < Taraf_Vekilkisi_Digeralacak_Icerik.getAttributes().getLength(); j2++) { // 4 Alana ait ÖZellikleri listeler
											if (Taraf_Vekilkisi_Digeralacak_Icerik.getAttributes().item(j2).getNodeName() == "id"){
												ChildOzellik = Taraf_Vekilkisi_Digeralacak_Icerik.getAttributes().item(j2).getNodeValue();
												XmlModel.set_digerAlacak_id(ChildOzellik);
											}
											else if(Taraf_Vekilkisi_Digeralacak_Icerik.getAttributes().item(j2).getNodeName() == "tutarAdi")
												XmlModel.set_digerAlacak_tutarAdi(Taraf_Vekilkisi_Digeralacak_Icerik.getAttributes().item(j2).getNodeValue());
											else if(Taraf_Vekilkisi_Digeralacak_Icerik.getAttributes().item(j2).getNodeName() == "digerAlacakAciklama")
												XmlModel.set_digerAlacak_digerAlacakAciklama(Taraf_Vekilkisi_Digeralacak_Icerik.getAttributes().item(j2).getNodeValue());
											else if(Taraf_Vekilkisi_Digeralacak_Icerik.getAttributes().item(j2).getNodeName() == "tutar")
											{
												XmlModel.set_digerAlacak_tutar(Taraf_Vekilkisi_Digeralacak_Icerik.getAttributes().item(j2).getNodeValue());
												XmlModel.set_digerAlacak_tutar_tl(defaultFormat.format(doubleYap(Taraf_Vekilkisi_Digeralacak_Icerik.getAttributes().item(j2).getNodeValue())));
																								
											}
											else if(Taraf_Vekilkisi_Digeralacak_Icerik.getAttributes().item(j2).getNodeName() == "tutarTur")
												XmlModel.set_digerAlacak_tutarTur(Taraf_Vekilkisi_Digeralacak_Icerik.getAttributes().item(j2).getNodeValue());
																				
									}// 4 ALANA AİT ÖZELLİKLERİN DÖGÜ SONU
							
														NodeList nListTaraf_Vekilkisi_Digeralacak_Cocuklar = Taraf_Vekilkisi_Digeralacak_Icerik.getChildNodes();
													
														for (int j3 = 0; j3 < nListTaraf_Vekilkisi_Digeralacak_Cocuklar.getLength(); j3++) { // KişiKurumBilgileri - RolTur - Vekil - KişiTümBilgieri - AlacakKalemleri Ana Başlıklar
															
																			Node Taraf_Vekilkisi_Digeralacak_Cocuk_Ozellikler = nListTaraf_Vekilkisi_Digeralacak_Cocuklar.item(j3);
																			String AttrName = Taraf_Vekilkisi_Digeralacak_Cocuk_Ozellikler.getNodeName();
																			for (int j4 = 0; j4 < Taraf_Vekilkisi_Digeralacak_Cocuk_Ozellikler.getAttributes().getLength(); j4++) { //KişiKurumBilgileri - RolTur - Vekil - KişiTümBilgieri - AlacakKalemleri iç ÖZellikleri
																				
																				String Ozellikler = Taraf_Vekilkisi_Digeralacak_Cocuk_Ozellikler.getAttributes().item(j4).getNodeName();
																				String Bilgiler = Taraf_Vekilkisi_Digeralacak_Cocuk_Ozellikler.getAttributes().item(j4).getNodeValue();
																				
																				if(ChildOzellik.equals("taraf_2")){
																					
																					switch (Ozellikler) {

																					case "id":
																						if (AttrName.equals("kisiKurumBilgileri")) {

																							XmlModel.set__kisiKurumBilgileri2_id(Bilgiler);

																						} else if (AttrName.equals("ref")) {

																							XmlModel.set__ref2_id(Bilgiler);

																						}
																						break;

																					case "ad":
																						XmlModel.set__kisiKurumBilgileri2_ad(Bilgiler);
																						break;
																					case "Rol":
																						XmlModel.set__rolTur2_Rol(Bilgiler);
																						break;
																					case "rolID":
																						XmlModel.set__rolTur2_rolID(Bilgiler);
																						break;
																					case "to":
																						XmlModel.set__ref2_to(Bilgiler);
																						break;

																					}
																					
																				}
																				
																				else if (ChildOzellik.equals("taraf_3")){
																				
																					switch (Ozellikler) {
																				case "id":
																					XmlModel.set__kisiKurumBilgileri3_id(Bilgiler);
																					break;

																				case "ad":
																					XmlModel.set__kisiKurumBilgileri3_ad(Bilgiler);
																					break;

																				case "Rol":
																					XmlModel.set__rolTur3_Rol(Bilgiler);
																					break;
																				case "rolID":
																					XmlModel.set__rolTur3_rolID(Bilgiler);
																					break;

																				default:
																					break;

																				}
																					
																				}
																				
																				else if(ChildOzellik.equals("VekilKisi_2")){
																					
																					switch (Ozellikler) {

																					case "id":
																						if (AttrName.equals("vekil")) {

																							XmlModel.set__vekil2_id(Bilgiler);

																						} else if (AttrName.equals("kisiTumBilgileri")) {

																							XmlModel.set__kisiTumBilgileri2_id(Bilgiler);

																						} else if (AttrName.equals("adres")) {

																							XmlModel.set__adres3_id(Bilgiler);

																						}
																						break;

																					case "vergiNo":
																						XmlModel.set__vekil2_verigNo(Bilgiler);
																						break;
																					case "kurumAvukatiMi":
																						XmlModel.set__vekil2_kurumAvukatiMi(Bilgiler);
																						break;
																					case "adi":
																						XmlModel.set__kisiTumbilgileri2_adi(Bilgiler);
																						break;
																					case "soyadi":
																						XmlModel.set__kisiTumbilgileri2_soyadi(Bilgiler);
																						break;
																					case "adresTuruAciklama":
																						XmlModel.set__adres3_adresTuruAciklama(Bilgiler);
																						break;
																					case "adres":
																						XmlModel.set__adres3_adres(Bilgiler);
																						break;
																					case "adresTuru":
																						XmlModel.set__adres3_adresTuru(Bilgiler);
																						break;
																					case "cepTelefon":
																						XmlModel.set__adres3_cepTelefon(Bilgiler);
																						break;
																					case "telefon":
																						XmlModel.set__adres3_telefon(Bilgiler);
																						break;
																					case "ilceKodu":
																						XmlModel.set__adres3_ilceKodu(Bilgiler);
																						break;
																					case "ilce":
																						XmlModel.set__adres3_ilce(Bilgiler);
																						break;
																					case "il":
																						XmlModel.set__adres3_il(Bilgiler);
																						break;
																					case "ilKodu":
																						XmlModel.set__adres3_ilKodu(Bilgiler);
																						break;

																					}
																					
																					
																					
																				}
																				
																				else if (ChildOzellik.equals("digerAlacak_2")){
																					
																					
																					
																					switch (Ozellikler) {

																					case "id":

																						if (Bilgiler.equals("alacakKalemi_2")) {
																							
																						    XmlModel.set__alacakKalemi2_id(Bilgiler);

																						} else if (Bilgiler.equals("alacakKalemi_3")) {
																							
																							XmlModel.set__alacakKalemi3_id(Bilgiler);

																						} else if (Bilgiler.equals("alacakKalemi_4")) {
																							
																							XmlModel.set__alacakKalemi4_id(Bilgiler);

																						} else if (Bilgiler.equals("alacakKalemi_5")) {
																						
																							XmlModel.set__alacakKalemi5_id(Bilgiler);

																						}
																						break;

																					case "tutarAdi":
																						if (j3==0) {

																							XmlModel.set__alacakKalemi2_tutarAdi(Bilgiler);

																						} else if (j3==1) {

																							XmlModel.set__alacakKalemi3_tutarAdi(Bilgiler);

																						} else if (j3==2) {

																							XmlModel.set__alacakKalemi4_tutarAdi(Bilgiler);

																						} else if (j3==3) {

																							XmlModel.set__alacakKalemi5_tutarAdi(Bilgiler);

																						}
																						break;

																					case "tutarTur":
																						if (j3==0) {

																							XmlModel.set__alacakKalemi2_tutarTur(Bilgiler);

																						} else if (j3==1) {

																							XmlModel.set__alacakKalemi3_tutarTur(Bilgiler);

																						} else if (j3==2) {

																							XmlModel.set__alacakKalemi4_tutarTur(Bilgiler);

																						} else if (j3==3) {

																							XmlModel.set__alacakKalemi5_tutarTur(Bilgiler);

																						}
																						break;

																					case "alacakKalemKodTuru":
																						if (j3==0) {

																							XmlModel.set__alacakKalemi2_alacakKalemKodTuru(Bilgiler);

																						} else if (j3==1) {

																							XmlModel.set__alacakKalemi3_alacakKalemKodTuru(Bilgiler);

																						} else if (j3==2) {

																							XmlModel.set__alacakKalemi4_alacakKalemKodTuru(Bilgiler);

																						} else if (j3==3) {

																							XmlModel.set__alacakKalemi5_alacakKalemKodTuru(Bilgiler);

																						}
																						break;

																					case "alacakKalemKodAciklama":
																						if (j3==0) {

																							XmlModel.set__alacakKalemi2_alacakKalemKodAciklama(Bilgiler);

																						} else if (j3==1) {

																							XmlModel.set__alacakKalemi3_alacakKalemKodAciklama(Bilgiler);

																						} else if (j3==2) {

																							XmlModel.set__alacakKalemi4_alacakKalemKodAciklama(Bilgiler);

																						} else if (j3==3) {

																							XmlModel.set__alacakKalemi5_alacakKalemKodAciklama(Bilgiler);

																						}
																						break;

																					case "alacakKalemIlkTutar":
																						if (j3==0) {
																							
																							XmlModel.set__alacakKalemi2_alacakKalemIlkTutar(Bilgiler);

																						} else if (j3==1) {

																							XmlModel.set__alacakKalemi3_alacakKalemIlkTutar(Bilgiler);

																						} else if (j3==2) {

																							XmlModel.set__alacakKalemi4_alacakKalemIlkTutar(Bilgiler);

																						} else if (j3==3) {

																							XmlModel.set__alacakKalemi5_alacakKalemIlkTutar(Bilgiler);

																						}
																						break;

																					case "alacakKalemTutar":
																						if (j3==0) {

																							XmlModel.set__alacakKalemi2_alacakKalemTutar(Bilgiler);
																							XmlModel.set__alacakKalemi2_alacakKalemTutar_Tl(defaultFormat.format(doubleYap(Bilgiler)));
																							

																						} else if (j3==1) {

																							XmlModel.set__alacakKalemi3_alacakKalemTutar(Bilgiler);
																							XmlModel.set__alacakKalemi3_alacakKalemTutar_Tl(defaultFormat.format(doubleYap(Bilgiler)));
																						} else if (j3==2) {

																							XmlModel.set__alacakKalemi4_alacakKalemTutar(Bilgiler);
																							XmlModel.set__alacakKalemi4_alacakKalemTutar_Tl(defaultFormat.format(doubleYap(Bilgiler)));
																						} else if (j3==3) {

																							XmlModel.set__alacakKalemi5_alacakKalemTutar(Bilgiler);
																							XmlModel.set__alacakKalemi5_alacakKalemTutar_Tl(defaultFormat.format(doubleYap(Bilgiler)));
																						}
																						break;

																					case "alacakKalemAdi":
																						if (j3==0) {

																							XmlModel.set__alacakKalemi2_alacakKalemAdi(Bilgiler);

																						} else if (j3==1) {

																							XmlModel.set__alacakKalemi3_alacakKalemAdi(Bilgiler);

																						} else if (j3==2) {

																							XmlModel.set__alacakKalemi4_alacakKalemAdi(Bilgiler);

																						} else if (j3==3) {

																							XmlModel.set__alacakKalemi5_alacakKalemAdi(Bilgiler);

																						}
																						break;

																					case "alacakKalemKod":
																						if (j3==0) {

																							XmlModel.set__alacakKalemi2_alacakKalemKod(Bilgiler);

																						} else if (j3==1) {

																							XmlModel.set__alacakKalemi3_alacakKalemKod(Bilgiler);

																						} else if (j3==2) {

																							XmlModel.set__alacakKalemi4_alacakKalemKod(Bilgiler);

																						} else if (j3==3) {

																							XmlModel.set__alacakKalemi5_alacakKalemKod(Bilgiler);

																						}
																						break;

																					} // switch end
																					
																					
																				}
																				
																				
																			}
															
																			
																										NodeList nList_adres_faiz = Taraf_Vekilkisi_Digeralacak_Cocuk_Ozellikler.getChildNodes();
																										for (int j5 = 0; j5 < nList_adres_faiz.getLength(); j5++) { // ADRES2 VE ADRES3 VE FAİZ BİLGİLERİ LİSTESİ ANA BAŞLIKLAR
																											
																																	Node adres_faiz_icerikler = nList_adres_faiz.item(j5);
																																	String FaizOzellik = adres_faiz_icerikler.getNodeName();
																																	for (int j6 = 0; j6 < adres_faiz_icerikler.getAttributes().getLength(); j6++) {// DRES2 VE ADRES3 VE FAİZ BİLGİLERİ İÇERİK KALEMLERİ
																																		 
																																		String adresfaizOzellik = adres_faiz_icerikler.getAttributes().item(j6).getNodeName();
																																		String adresfaizBilgi = adres_faiz_icerikler.getAttributes().item(j6).getNodeValue();
																																		if(ChildOzellik.equals("taraf_2")){
																																			
																																			switch (adresfaizOzellik) {
																																			
																																			case "id":
																																				if (FaizOzellik.equals("kurum")) {
																																					XmlModel.set___kurum2_id(adresfaizBilgi);
																																					break;
																																				} else if (FaizOzellik.equals("adres")) {
																																					XmlModel.set___adres2_id(adresfaizBilgi);
																																				}
																																																																																																							
																																				break;

																																			case "adresTuruAciklama":
																																				XmlModel.set___adres2_adresTuruAciklama(adresfaizBilgi);
																																				break;
																																			case "adres":
																																				XmlModel.set___adres2_adres(adresfaizBilgi);
																																				break;
																																			case "adresTuru":
																																				XmlModel.set___adres2_adresTuru(adresfaizBilgi);
																																				break;
																																			case "il":
																																				XmlModel.set___adres2_il(adresfaizBilgi);
																																				break;
																																			case "ilceKodu":
																																				XmlModel.set___adres2_ilceKodu(adresfaizBilgi);
																																				break;
																																			case "ilce":
																																				XmlModel.set___adres2_ilce(adresfaizBilgi);
																																				break;

																																			case "ilKodu":
																																				XmlModel.set___adres2_ilKodu(adresfaizBilgi);
																																				break;
																																
																																			case "vergiNo":
																																				XmlModel.set___kurum2_vergiNo(adresfaizBilgi);
																																				break;
																																			case "vergiDairesi":
																																				XmlModel.set___kurum2_vergiDairesi(adresfaizBilgi);
																																				break;
																																			case "kamuOzel":
																																				XmlModel.set___kurum2_kamuOzel(adresfaizBilgi);
																																				break;
																																			case "kurumAdi":
																																				XmlModel.set___kurum2_kurumAdi(adresfaizBilgi);
																																				break;
																																			case "harcDurumu":
																																				XmlModel.set___kurum2_harcDurumu(adresfaizBilgi);
																																				break;
																																			
																																			
																																			}

																																			
																																		}
																																		else if (ChildOzellik.equals("taraf_3"))
																																		{
																																			switch (adresfaizOzellik) {
																																			
																																			case "id":
																																				if (FaizOzellik.equals("kisiTumBilgileri")) {
																																					XmlModel.set___kisiTumbilgileri3_id(adresfaizBilgi);
																																					break;
																																				} else if (FaizOzellik.equals("adres")) {
																																					XmlModel.set___adres4_id(adresfaizBilgi);
																																				}
																																																																																																							
																																				break;

																																			case "adresTuruAciklama":
																																				XmlModel.set___adres4_adresTuruAciklama(adresfaizBilgi);
																																				break;
																																			case "adres":
																																				XmlModel.set___adres4_adres(adresfaizBilgi);
																																				break;
																																			case "adresTuru":
																																				XmlModel.set___adres4_adresTuru(adresfaizBilgi);
																																				break;
																																			case "il":
																																				XmlModel.set___adres4_il(adresfaizBilgi);
																																				break;
																																			case "ilceKodu":
																																				XmlModel.set___adres4_ilceKodu(adresfaizBilgi);
																																				break;
																																			case "ilce":
																																				XmlModel.set___adres4_ilce(adresfaizBilgi);
																																				break;

																																			case "ilKodu":
																																				XmlModel.set___adres4_ilKodu(adresfaizBilgi);
																																				break;
																																			
																																			
																																		case "tcKimlikNo":
																																			XmlModel.set___kisiTumbilgileri3_tcKimlikNo(adresfaizBilgi);
																																			break;
																																		case "adi":
																																			XmlModel.set___kisiTumbilgileri3_adi(adresfaizBilgi);
																																			break;
																																		case "soyadi":
																																			XmlModel.set___kisiTumbilgileri3_soyadi(adresfaizBilgi);
																																			break;
																																					
																																			
																																			}
																																			
																																		}
																																		else if(ChildOzellik.equals("digerAlacak_2")){
																																			switch (adresfaizOzellik) {
																																			
																																			case "id":
																																				if (FaizOzellik.equals("faiz")) {
																																					XmlModel.set___alacakKalemi2_faiz2_id(adresfaizBilgi);
																																					
																																				}
																																				break;
																																		
																																			case "faizSureTip":
																																				XmlModel.set___alacakKalemi2_faiz_faizSureTip(adresfaizBilgi);
																																				break;
																																				
																																			case "faizTipKodAciklama":
																																				XmlModel.set___alacakKalemi2_faiz2_faizTipKodAciklama(adresfaizBilgi);
																																				break;
																																			
																																			case "faizTipKod":
																																				XmlModel.set___alacakKalemi2_faiz2_faizTipKod(adresfaizBilgi);
																																				break;
																																			
																																			case "faizOran":
																																				XmlModel.set___alacakKalemi2_faiz2_faizOran(adresfaizBilgi);
																																				break;
																																			
																																			case "baslangicTarihi":
																																				XmlModel.set___alacakKalemi2_faiz2_baslangicTarihi(adresfaizBilgi);
																																				break;
																																			
																																				
																																			}
																																				
																																				
																																		}
																																	}
																											
																											
																										} //ADRES2 VE ADRES3 VE FAİZ BİLGİLERİ  DÖNGÜ SONU
																			
																			
															
															
														}// KİŞİKURUMBİLGİLERİ - ROLTUR - VEKİL - KİŞİTÜMBİLGİLERİ - ALACAKKALEMLERİ DÖNGÜSÜ SON
							
									
									
							
							
							
						}// TARAF2 - TARAF3 - VEKİLKİŞİ VE DİĞER ALACAK ARASI DÖNGÜ SONU
				
							
		XmlList.add(XmlModel);
		}// DOSYALAR ARASI DÖNGÜ SONU	
		
		
	}// PROSEDÜR SONU

	@SuppressWarnings({ "resource", "unused" })
	private  ArrayList<ExcellModel> XLSOku(UploadedFile Dosya, int sayi) throws IOException, InvalidFormatException{
		
		File dosya= new File("tmp.xlsx");
		FileUtils.copyInputStreamToFile(Dosya.getInputstream(), dosya);
	
		
		XSSFWorkbook workbook = new XSSFWorkbook(dosya);
		
		XSSFSheet sheet = workbook.getSheetAt(0);

		int rowNum = sheet.getLastRowNum() + 1;
	    int colNum = sheet.getRow(0).getLastCellNum() + 1;

		ArrayList<ExcellModel> excellModelList = new ArrayList<ExcellModel>();

		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		for (int i = 1; i < sayi; i++) {

			Row row = sheet.getRow(i);

			ExcellModel excellModel = new ExcellModel();

			excellModel.setAvukatServisNo(String.valueOf(row.getCell(0)
					.getNumericCellValue()));
			excellModel.setBankaServisNo(row.getCell(1).getStringCellValue());
			excellModel.setMusteriNo(String.valueOf(row.getCell(2)
					.getNumericCellValue()).substring(0, String.valueOf(row.getCell(2)
							.getNumericCellValue()).length()-2 ));
			
			excellModel.setMusteri(row.getCell(3).getStringCellValue());
			excellModel.setTcNo((long) (row.getCell(4).getNumericCellValue()));
			excellModel.setUrunKategorisi(row.getCell(5).getStringCellValue());
			excellModel.setUrunAdi(row.getCell(6).getStringCellValue());
			BigDecimal bd = new BigDecimal(row.getCell(7)
					.getNumericCellValue());
			long lonVal = bd.longValue();
			excellModel.setUrunNo(Long.toString(lonVal));
			excellModel.setAvukat(row.getCell(8).getStringCellValue());
			excellModel.setYasalTakipTarihi(sdf.format(row.getCell(9).getDateCellValue()));
		    excellModel.setAvukataVerilisTarihi(sdf.format(row.getCell(10)
						.getDateCellValue()));
			
						
			excellModel.setSubeAdi(row.getCell(11).getStringCellValue());
			excellModel.setYTilkBakiye((double) row.getCell(12)
					.getNumericCellValue());
			excellModel.setToplamBorc((double) row.getCell(13)
					.getNumericCellValue());
			excellModel.setIhtarnameTarihi(sdf.format(row.getCell(14).getDateCellValue()));
			excellModel.setIhtarnameTutari((double) row.getCell(15)
					.getNumericCellValue());
			excellModel.setAkdiFaizOrani((double) row.getCell(16)
					.getNumericCellValue());
			excellModel.setTemmerrutFaizi((double) row.getCell(17)
					.getNumericCellValue());

			excellModelList.add(excellModel);

		}

		return excellModelList;
		
	}

	

	private void FreeXMLOku(UploadedFile Dosya, String Banka) throws IOException, ParserConfigurationException, SAXException{
		 NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		
			XmlList = new ArrayList<XMLToUyap>();
		File dosya= new File("tmp.xml");
		FileUtils.copyInputStreamToFile(Dosya.getInputstream(), dosya);
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(dosya);
	
		NodeList nList_Dosya = doc.getElementsByTagName("dosya");
		
		for (int i = 0; i < nList_Dosya.getLength(); i++) { // ANA FOR DÖNGÜSÜ START (XML İçinceki Dosyasayısı kadar Döner)

			Node DosyaIcerik = nList_Dosya.item(i);

			FreeXmlModel = new FreeXML();
			tmplistHesap = new ArrayList<FreeXMLHesap>();
		
			for (int j = 0; j < DosyaIcerik.getAttributes().getLength(); j++) { // DOSYA' ya ait özellikleri listeler
												
					String Ozellik = DosyaIcerik.getAttributes().item(j).getNodeName();
					String bilgi = DosyaIcerik.getAttributes().item(j).getNodeValue();
					
					switch (Ozellik) {

					case "dosyaTipi":
						FreeXmlModel.setDosyaTipi(bilgi);
						break;
					case "dosyaBelirleyicisi":
						FreeXmlModel.setDosyaBelirleyicisi(bilgi);
						break;

					case "KKDFUygulansin":
						FreeXmlModel.setKKDFUygulansin(bilgi);
						break;

					case "BSMVUygulansin":
						FreeXmlModel.setBSMVUygulansin(bilgi);
						break;

					case "BK84MaddeUygulansin":
						FreeXmlModel.setBK84MaddeUygulansin(bilgi);
						break;
					case "aciklama48e9":
						FreeXmlModel.setAciklama48e9(bilgi);
						break;
					case "alacaklininTalepEttigiHak":
						FreeXmlModel.setAlacaklininTalepEttigiHak(bilgi);
						break;
					case "takipSekli":
						FreeXmlModel.setTakipSekli(bilgi);
						break;
					case "takipYolu":
						FreeXmlModel.setTakipYolu(bilgi);
						break;
					case "takipTuru":
						FreeXmlModel.setTakipTuru(bilgi);
						break;
					case "dosyaTuru":
						FreeXmlModel.setDosyaTuru(bilgi);
					default:
						break;

					}
					
			}
			
							NodeList DosyaBilgileri = DosyaIcerik.getChildNodes();
							
							for (int k = 0; k < DosyaBilgileri.getLength(); k++) {
								
								Node DosyaBilgileriIcerik = DosyaBilgileri.item(k);
								
								if (DosyaBilgileriIcerik.getNodeName()=="taraf") {
									
										NodeList kisiKurumBilgileri = DosyaBilgileriIcerik.getChildNodes();
										for (int j1 = 0; j1 < kisiKurumBilgileri.getLength(); j1++) {
											
											Node kisiKurumicerik = kisiKurumBilgileri.item(j1);
										
											if(kisiKurumicerik.getNodeName()=="kisiKurumBilgileri"){
											
												for (int j2 = 0; j2 < kisiKurumicerik.getAttributes().getLength(); j2++) {
													
													
													if(kisiKurumicerik.getAttributes().item(j2).getNodeName()=="ad"){
														if(kisiKurumicerik.getAttributes().item(j2).getNodeValue().equals(Banka)){}else{
													
														NodeList borcluBilgileri = kisiKurumicerik.getChildNodes();
														for (int j3 = 0; j3 < borcluBilgileri.getLength(); j3++) {
															Node borcluIcBilgileri = borcluBilgileri.item(j3);
															
														
															if(borcluIcBilgileri.getNodeName()=="kisiTumBilgileri" || borcluIcBilgileri.getNodeName()=="adres"){
															for (int j4 = 0; j4 < borcluIcBilgileri.getAttributes().getLength(); j4++) {
																String bilgi = borcluIcBilgileri.getAttributes().item(j4).getNodeName();
																String icerik = borcluIcBilgileri.getAttributes().item(j4).getNodeValue();
															
																switch (bilgi) {

																case "adresTuru":
																	FreeXmlModel.setAdresTuruId(icerik);
																	break;
																case "adres":
																	FreeXmlModel.setAdres(icerik);
																	break;
																case "adi":
																	FreeXmlModel.setAd(icerik);
																	break;
																case "soyadi":
																	FreeXmlModel.setSoyad(icerik);
																	break;
																case "tcKimlikNo":
																	FreeXmlModel.setTcNo(icerik);
																	break;
																case "il":
																	FreeXmlModel.setIlAdi(icerik);
																	break;
																case "ilKodu":
																	FreeXmlModel.setIlId(icerik);
																	break;
																	
																default:
																	break;

																}
																
																
															}
														
															}
														}
														
													
														}
														}
													
												}
												
												
											}
											
											
										}
									
									
									
								}
								
								
								
								if (DosyaBilgileriIcerik.getNodeName()=="digerAlacak") {
									
									
									for (int ds = 0; ds < DosyaBilgileriIcerik.getAttributes().getLength(); ds++) {
										String bilgi = DosyaBilgileriIcerik.getAttributes().item(ds).getNodeName();
										String icerik = DosyaBilgileriIcerik.getAttributes().item(ds).getNodeValue();
										
										if(bilgi.equals("tarih")){
											FreeXmlModel.setTakipTarihi(icerik);
										}else if(bilgi.equals("digerAlacakAciklama")){
											FreeXmlModel.setDigerAlcakAciklama(icerik);
										}else if(bilgi.equals("tutarAdi")){
											FreeXmlModel.setParaBirimi(icerik);
										}
										
										
									}
									
									
									NodeList alacakKalemi = DosyaBilgileriIcerik.getChildNodes();
									FreeXMLHesap tmpHesap = new FreeXMLHesap();
									FreeXMLHesap tmpHesaplar = new FreeXMLHesap();
									
									for (int k1 = 0; k1 < alacakKalemi.getLength(); k1++) {
										
										Node alacakKalemiIcerik = alacakKalemi.item(k1);
										int kalemkod=0, isIslemis=0;
										if(alacakKalemiIcerik.getNodeName().equals("alacakKalemi")){
											for (int t1 = 0; t1 < alacakKalemiIcerik.getAttributes().getLength(); t1++) {
												String bilgi = alacakKalemiIcerik.getAttributes().item(t1).getNodeName();
												String icerik = alacakKalemiIcerik.getAttributes().item(t1).getNodeValue();
												
												if(bilgi.equals("alacakKalemKod"))
												{
													tmpHesap.setAlacakKalemKod(icerik);
													if(icerik.equals("3")){
													kalemkod=3;	
													}else if(icerik.equals("7170")){
														kalemkod=7170;
													}else if(icerik.equals("7171")){
														kalemkod=7171;
													}
													else if(icerik.equals("6")){
														kalemkod=6;
													}
												}
												
												if(bilgi.equals("alacakKalemTutar")){
													tmpHesap.setAlacakKalemTutar(icerik==null ? "0": icerik);
													
												}
												
												if(bilgi.equals("alacakKalemKodAciklama")){
													String str = icerik==null ? "0": icerik;
													if(str.indexOf("İşlemiş")==-1){
														isIslemis=0;
													}else{
														isIslemis=1;
													}
														
													
												}
												
												
												
											}
											
												if(kalemkod==3){
												tmpHesaplar.setAsil_alacak(tmpHesap.getAlacakKalemTutar());
												}else if(kalemkod==7170){
												tmpHesaplar.setNoter_masrafi(tmpHesap.getAlacakKalemTutar());
												}else if(kalemkod==7171){
												tmpHesaplar.setGecikme_faizi(tmpHesap.getAlacakKalemTutar());
												}else if(kalemkod==6){
													if(isIslemis==1){
														tmpHesaplar.setGecikme_faizi(tmpHesap.getAlacakKalemTutar());
													}else{
												tmpHesaplar.setFaizin_gider_vergisi(tmpHesap.getAlacakKalemTutar());
													}
												}
										
												NodeList faizlist = alacakKalemiIcerik.getChildNodes();
															for (int f = 0; f < faizlist.getLength(); f++) {
																
																Node faiz = faizlist.item(f);
																if(faiz.getNodeName().equals("faiz")){
																	
																	for (int f1 = 0; f1 < faiz.getAttributes().getLength(); f1++) {
																		String bilgi = faiz.getAttributes().item(f1).getNodeName();
																		String icerik = faiz.getAttributes().item(f1).getNodeValue();
																		
																		if(bilgi.equals("faizOran")){
																			tmpHesaplar.setTemerrut_faiz_orani(icerik);
																		}
																		
																		if(bilgi.equals("baslangicTarihi")){
																			FreeXmlModel.setTakipTarihi(icerik);
																		}
																		
																		
																		
																	}
																	
																}
																
															}
												
												
												
										}
												
									}
									
									double takipalacagi = doubleYap(tmpHesaplar.getAsil_alacak())+
											doubleYap(tmpHesaplar.getGecikme_faizi())+	
											doubleYap(tmpHesaplar.getNoter_masrafi())+
											doubleYap(tmpHesaplar.getFaizin_gider_vergisi());
									
									tmpHesaplar.setTakip_alacagi(takipalacagi+"");
									tmplistHesap.add(tmpHesaplar);
									
								}	
								
								FreeXmlModel.setAdSoyad(FreeXmlModel.getAd() +" " + FreeXmlModel.getSoyad());
								FreeXmlModel.setHesapListesi(tmplistHesap);
								FreeXmlModel.setMuvekkilAdi(this.getMuvekkilinAdi());
								
							}
							
							
							XMLToUyap tmpxmluyap = new XMLToUyap();
							
							tmpxmluyap.set___alacakKalemi2_faiz2_baslangicTarihi(FreeXmlModel.getTakipTarihi());
							tmpxmluyap.setDosyaBelirleyicisi(FreeXmlModel.getAdSoyad());
							tmpxmluyap.set__alacakKalemi2_alacakKalemTutar_Tl(defaultFormat.format(doubleYap(FreeXmlModel.getHesapListesi().get(0).getAsil_alacak())));
							tmpxmluyap.set__alacakKalemi3_alacakKalemTutar_Tl(defaultFormat.format(doubleYap(FreeXmlModel.getHesapListesi().get(0).getGecikme_faizi())));
							tmpxmluyap.set__alacakKalemi4_alacakKalemTutar_Tl(defaultFormat.format(doubleYap(FreeXmlModel.getHesapListesi().get(0).getFaizin_gider_vergisi())));
							tmpxmluyap.set__alacakKalemi5_alacakKalemTutar_Tl(defaultFormat.format(doubleYap(FreeXmlModel.getHesapListesi().get(0).getNoter_masrafi())));
							tmpxmluyap.set_digerAlacak_tutar_tl(defaultFormat.format(doubleYap(FreeXmlModel.getHesapListesi().get(0).getTakip_alacagi())));
						
							
							XmlList.add(tmpxmluyap);
							
			
			FreeXmlList.add(FreeXmlModel);
						
		}		
		
		
		
	}

	
	
	
	
}
