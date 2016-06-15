package pelops.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import javax.rmi.CORBA.UtilDelegate;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;


import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.ss.formula.functions.Now;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import pelops.dao.ExportXMLExcelDAO;
import pelops.dao.LogErrorDAO;
import pelops.model.LogError;
import pelops.model.UyapXML;
import pelops.util.Util;


@ManagedBean(name="exportBean")
@SessionScoped
public class ExportBean {
	LogErrorDAO log = new LogErrorDAO();
	Date nowDate = new Date();
	LogError newlog;
	
	String oldDate="01/01/1900";
	public static String DOSYA_KLASORU = System.getProperty("catalina.base") + File.separator + "temp" + File.separator + "uyaptest.xml" + File.separator;
			
	private Date Takip_Tarihi;
	
	 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	private String MuvekkilAdi;
	
	private StreamedContent downloadFile;
	
	
	public StreamedContent getDownloadFile() {
		return downloadFile;
	}

	public void setDownloadFile(StreamedContent downloadFile) {
		this.downloadFile = downloadFile;
	}

	public ExportBean(){
		
		 InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/UyapXMLFiles/uyaptest.xml");
		 downloadFile = new DefaultStreamedContent(stream, "text/xml", "UYAP.xml");
		 System.out.println(stream);
		 System.out.println(DOSYA_KLASORU);
		 
	}
	 
	public String getMuvekkilAdi() {
		return MuvekkilAdi;
	}


	public void setMuvekkilAdi(String muvekkilAdi) {
		MuvekkilAdi = muvekkilAdi;
	}


	public Date getGelisTarihi1() {
		return gelisTarihi1;
	}


	public Date getTakip_Tarihi() {
		return Takip_Tarihi;
	}


	public void setTakip_Tarihi(Date takip_Tarihi) {
		Takip_Tarihi =  takip_Tarihi;
	}


	public void setGelisTarihi1(Date gelisTarihi1) {
		this.gelisTarihi1 = gelisTarihi1;
	}


	public Date getGelisTarihi2() {
		return gelisTarihi2;
	}


	public void setGelisTarihi2(Date gelisTarihi2) {
		this.gelisTarihi2 = gelisTarihi2;
	}

	private Date gelisTarihi1 = new Date(), gelisTarihi2=new Date();
	
	
	
	ArrayList<UyapXML> uyapModel;
	
	public ArrayList<UyapXML> getUyapModel() {
		return uyapModel;
	}


	public void setUyapModel(ArrayList<UyapXML> uyapModel) {
		this.uyapModel = uyapModel;
	}


	public void Listele() {
		try {
		ExportXMLExcelDAO dao=new ExportXMLExcelDAO();
		
			uyapModel = dao.exportUYAPXML();
		} catch (SQLException e) {
			String Hata="";
			for (int i = 0; i < e.getStackTrace().length; i++) {
				Hata += e.getStackTrace()[i]+" : ";	
			}
			newlog = new LogError();
			newlog.setHata_detay(Hata);
			newlog.setHata_value("ExportBean - Listele Prosedürü (SQL ERROR)");
			newlog.setPage("frm_UyapXML");
			newlog.setUser_id(99);
			
			
			try {
				log.Kaydet(newlog);	
			} catch (Exception e2) {
				
			}
			
			
		}catch(Exception ex){
			String Hata="";
			for (int i = 0; i < ex.getStackTrace().length; i++) {
				Hata += ex.getStackTrace()[i]+" : ";	
			}
			newlog = new LogError();
			newlog.setHata_detay(Hata);
			newlog.setHata_value("ExportBean - Listele Prosedürü (STANDART ERROR)");
			newlog.setPage("frm_UyapXML");
			newlog.setUser_id(99);
			
			
			try {
				log.Kaydet(newlog);	
			} catch (Exception e2) {
				
			}
		}finally {
			
		}
		
	
		
		
	}
	
	public void AralikListele() throws SQLException{
		try{
		ExportXMLExcelDAO dao = new ExportXMLExcelDAO();
		uyapModel = dao.exportAramaUYAPXML(gelisTarihi1, gelisTarihi2);
		} catch (SQLException e) {
			String Hata="";
			for (int i = 0; i < e.getStackTrace().length; i++) {
				Hata += e.getStackTrace()[i]+" : ";	
			}
			newlog = new LogError();
			newlog.setHata_detay(Hata);
			newlog.setHata_value("ExportBean - AralikListele Prosedürü (SQL ERROR)");
			newlog.setPage("frm_UyapXML");
			newlog.setUser_id(99);
			
			
			try {
				log.Kaydet(newlog);	
			} catch (Exception e2) {
				
			}
			
			
		}catch(Exception ex){
			String Hata="";
			for (int i = 0; i < ex.getStackTrace().length; i++) {
				Hata += ex.getStackTrace()[i]+" : ";	
			}
			newlog = new LogError();
			newlog.setHata_detay(Hata);
			newlog.setHata_value("ExportBean - AralikListele Prosedürü (STANDART ERROR)");
			newlog.setPage("frm_UyapXML");
			newlog.setUser_id(99);
			
			
			try {
				log.Kaydet(newlog);	
			} catch (Exception e2) {
				
			}
		}finally {
			
		}
		
		
	}
	
	
	public String virgulYap(String sttutar){
		
		String SonucSayi="";
		if(sttutar==null || sttutar.equals("")){}
		else
		{
			int nokta = sttutar.indexOf(",");
			if(nokta<0) nokta = sttutar.indexOf(".");
			
		
		String tam = sttutar.substring(0, nokta);
		String kesir = sttutar.substring(nokta+1, sttutar.length());
		String sayi = tam + "," + kesir;
		SonucSayi = sayi;
		}
		return SonucSayi;
	}
	
	public void exportUyapXML()
	{
	
		try{
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("exchangeData");
		doc.appendChild(rootElement);
		
		Element staff = doc.createElement("exchangeHeader");
		rootElement.appendChild(staff);
		
		staff.setAttribute("version", "1.1");
		
		Element dosyalar = doc.createElement("dosyalar");
		rootElement.appendChild(dosyalar);
		
		
		
		for (int i = 0; i < uyapModel.size(); i++) {
			
			Element dosya = doc.createElement("dosya");
			dosyalar.appendChild(dosya);
			
			dosya.setAttribute("dosyaTuru", uyapModel.get(i).getDosyaTuru());
			dosya.setAttribute("dosyaBelirleyicisi", uyapModel.get(i).getDosyaBelirleyicisi());
			dosya.setAttribute("KKDFUygulansin", uyapModel.get(i).getKkdfUygulansin());
			dosya.setAttribute("BSMVUygulansin", uyapModel.get(i).getBsmvUygulansin());
			dosya.setAttribute("BK84MaddeUygulansin", uyapModel.get(i).getBk84MaddeUygulansin());
			dosya.setAttribute("aciklama48e9", uyapModel.get(i).getAciklama48e9()== null || uyapModel.get(i).getAciklama48e9()== "" ? "HACİZ" : uyapModel.get(i).getAciklama48e9());
			dosya.setAttribute("alacaklininTalepEttigiHak", uyapModel.get(i).getAlacaklininTalepEttigiHak());
			dosya.setAttribute("takipSekli", uyapModel.get(i).getTakip_sekli_id());
			dosya.setAttribute("takipYolu", uyapModel.get(i).getTakipYolu());
			dosya.setAttribute("takipTuru", uyapModel.get(i).getTakipTuru()== null || uyapModel.get(i).getTakipTuru()== "" ? "1" : uyapModel.get(i).getTakipTuru() );
			dosya.setAttribute("dosyaTipi", uyapModel.get(i).getDosyaTipi()== null || uyapModel.get(i).getDosyaTipi()== "" ? "Genel İcra Dairesi" : uyapModel.get(i).getDosyaTipi());
			
				
		
		Element taraf2=doc.createElement("taraf");
		dosya.appendChild(taraf2);
		
		taraf2.setAttribute("id", "taraf_2");
		
		Element kisiKurumBilgileri=doc.createElement("kisiKurumBilgileri");
		taraf2.appendChild(kisiKurumBilgileri);
		
		kisiKurumBilgileri.setAttribute("id", "kisiKurumBilgileri_2");
		kisiKurumBilgileri.setAttribute("ad", uyapModel.get(i).getKisiKurumBilgileriAd());
		
		Element adres=doc.createElement("adres");
		kisiKurumBilgileri.appendChild(adres);
		
		adres.setAttribute("id", "adres_2");
		adres.setAttribute("adresTuruAciklama", uyapModel.get(i).getMuvekkilAdresTuruAciklama()== null || 
												uyapModel.get(i).getMuvekkilAdresTuruAciklama()== "" ?
														"Yurt İçi İkametgah Adresi" : uyapModel.get(i).getMuvekkilAdresTuruAciklama());
		adres.setAttribute("postaKodu", "");
		adres.setAttribute("adres", uyapModel.get(i).getMuvekkilAdres());
		adres.setAttribute("adresTuru", uyapModel.get(i).getMuvekkilAdresTuru()==null ||
										uyapModel.get(i).getMuvekkilAdresTuru()=="" ?
												"ADRTR00001" : uyapModel.get(i).getMuvekkilAdresTuru());
		adres.setAttribute("elektronikPostaAdresi", "");
		adres.setAttribute("cepTelefon", "");
		adres.setAttribute("telefon", "");
		adres.setAttribute("ilceKodu", uyapModel.get(i).getMuvekkilIlceKodu());
		adres.setAttribute("ilce", uyapModel.get(i).getMuvekkilIlce());
		adres.setAttribute("il", uyapModel.get(i).getMuvekkilIl());
		adres.setAttribute("ilKodu", uyapModel.get(i).getMuvekkilIlKodu());
		
		Element kurum2=doc.createElement("kurum");
		kisiKurumBilgileri.appendChild(kurum2);
		
		kurum2.setAttribute("id", "kurum_2");
		kurum2.setAttribute("ticaretSicilNoVerildigiYer", "");
		kurum2.setAttribute("harcDurumu", "0");
		kurum2.setAttribute("ticaretSicilNo", "");
		kurum2.setAttribute("kurumAdi", uyapModel.get(i).getMuvekkilKurumAdi());
		kurum2.setAttribute("kamuOzel", uyapModel.get(i).getMuvekkilKamuOzel());
		kurum2.setAttribute("vergiDairesi", "Büyük Mükellefler");
		kurum2.setAttribute("sskIsyeriSicilNo", "");
		kurum2.setAttribute("vergiNo", uyapModel.get(i).getMuvekkilVergiNo());
		
		Element rolTur=doc.createElement("rolTur");
		taraf2.appendChild(rolTur);
	
		
		rolTur.setAttribute("rolID", "21");
		rolTur.setAttribute("Rol", "ALACAKLI");
		
		
		Element ref=doc.createElement("ref");
		taraf2.appendChild(ref);
		
		ref.setAttribute("id", "VekilKisi_2");
		ref.setAttribute("to", "VekilKisi");

	Element taraf3=doc.createElement("taraf");
	dosya.appendChild(taraf3);
	
	Element kisiKurumBilgileri3=doc.createElement("kisiKurumBilgileri");
	taraf3.appendChild(kisiKurumBilgileri3);
	
	taraf3.setAttribute("id", "taraf_3");
	
	kisiKurumBilgileri3.setAttribute("id", "kisiKurumBilgileri_3");
	kisiKurumBilgileri3.setAttribute("ad", uyapModel.get(i).getBorcluAdSoyad());
	
	Element adres4=doc.createElement("adres");
	kisiKurumBilgileri3.appendChild(adres4);
	
	
	adres4.setAttribute("id","adres_4");
	adres4.setAttribute("ilKodu", uyapModel.get(i).getBorcluIlKodu());
	adres4.setAttribute("il",  uyapModel.get(i).getBorcluIl());
	adres4.setAttribute("ilce",  uyapModel.get(i).getBorcluIlce());
	adres4.setAttribute("ilceKodu",  uyapModel.get(i).getBorcluIlceKodu());
	adres4.setAttribute("telefon",  "");
	adres4.setAttribute("cepTelefon", "");
	adres4.setAttribute("elektronikPostaAdresi", "");
	adres4.setAttribute("adresTuru",  uyapModel.get(i).getBorcluAdresTuru()==null || uyapModel.get(i).getBorcluAdresTuru()=="" ? "ADRTR00001" : uyapModel.get(i).getBorcluAdresTuru());
	adres4.setAttribute("adres",  uyapModel.get(i).getBorcluAdres());
	adres4.setAttribute("postaKodu", "");
	adres4.setAttribute("adresTuruAciklama", uyapModel.get(i).getBorcluAdresTuruAciklama()==null ||
			uyapModel.get(i).getBorcluAdresTuruAciklama()=="" ? "Yurt İçi İkametgah Adresi" : uyapModel.get(i).getBorcluAdresTuruAciklama());
	
	

			
			
	Element kisiTumBilgileri=doc.createElement("kisiTumBilgileri");
	kisiKurumBilgileri3.appendChild(kisiTumBilgileri);
	
	kisiTumBilgileri.setAttribute("id", "kisiTumBilgileri_2");
	kisiTumBilgileri.setAttribute("kayitNo", "");
	kisiTumBilgileri.setAttribute("soyadi",uyapModel.get(i).getSoyad());
	kisiTumBilgileri.setAttribute("ybnNfsKayitliOldgYer", "");
	kisiTumBilgileri.setAttribute("aileSiraNo", "");
	kisiTumBilgileri.setAttribute("cuzdanNo", "");
	kisiTumBilgileri.setAttribute("babaAdi", "");
	kisiTumBilgileri.setAttribute("anaAdi", "");
	kisiTumBilgileri.setAttribute("adi", uyapModel.get(i).getAd());
	kisiTumBilgileri.setAttribute("tcKimlikNo", uyapModel.get(i).getBorcluTCKimlik());
	kisiTumBilgileri.setAttribute("oncekiSoyadi", "");
	kisiTumBilgileri.setAttribute("ciltNo", "");
	kisiTumBilgileri.setAttribute("cuzdanSeriNo", "");
	kisiTumBilgileri.setAttribute("siraNo", "");
	kisiTumBilgileri.setAttribute("mahKoy", "");
	kisiTumBilgileri.setAttribute("dogumYeri", "");	
	
	Element rolTur22=doc.createElement("rolTur");
	taraf3.appendChild(rolTur22);
	
	rolTur22.setAttribute("rolID", "22");
	rolTur22.setAttribute("Rol", "BORÇLU/MÜFLİS");
	

	
	

	Element vekilKisi=doc.createElement("VekilKisi");
	dosya.appendChild(vekilKisi);
	
	vekilKisi.setAttribute("id", "VekilKisi_2");
	
	Element vekil=doc.createElement("vekil");
	vekilKisi.appendChild(vekil);
	
	vekil.setAttribute("id", "VekilKisi_2");
	vekil.setAttribute("kurumAvukatiMi", uyapModel.get(i).getKurumAvukatimi());
	vekil.setAttribute("avukatlikBuroAdi", uyapModel.get(i).getAvukatlikBaroAdi());
	vekil.setAttribute("baroNo", uyapModel.get(i).getBaroNo());
	vekil.setAttribute("vergiNo", uyapModel.get(i).getVergiNo());
	
	Element vekilKisiTumBilgileri=doc.createElement("kisiTumBilgileri");
	vekilKisi.appendChild(vekilKisiTumBilgileri);
	
	vekilKisiTumBilgileri.setAttribute("id", "kisiTumBilgileri_3");
	vekilKisiTumBilgileri.setAttribute("kayitNo", "");
	vekilKisiTumBilgileri.setAttribute("soyadi",uyapModel.get(i).getSoyadi());
	vekilKisiTumBilgileri.setAttribute("ybnNfsKayitliOldgYer", "");
	vekilKisiTumBilgileri.setAttribute("aileSiraNo", "");
	vekilKisiTumBilgileri.setAttribute("cuzdanNo", "");
	vekilKisiTumBilgileri.setAttribute("babaAdi", "");
	vekilKisiTumBilgileri.setAttribute("anaAdi", "");
	vekilKisiTumBilgileri.setAttribute("adi", uyapModel.get(i).getAdi()	);
	vekilKisiTumBilgileri.setAttribute("tcKimlikNo", "");
	vekilKisiTumBilgileri.setAttribute("oncekiSoyadi", "");
	vekilKisiTumBilgileri.setAttribute("ciltNo", "");
	vekilKisiTumBilgileri.setAttribute("cuzdanSeriNo", "");
	vekilKisiTumBilgileri.setAttribute("siraNo", "");
	vekilKisiTumBilgileri.setAttribute("mahKoy", "");
	vekilKisiTumBilgileri.setAttribute("dogumYeri", "");	
	
	
	Element adres3=doc.createElement("adres");
	vekilKisi.appendChild(adres3);
	
	
	adres3.setAttribute("id","adres_3");
	adres3.setAttribute("ilKodu", uyapModel.get(i).getVekilIlKodu());
	adres3.setAttribute("il",  uyapModel.get(i).getVekilIl());
	adres3.setAttribute("ilce",  uyapModel.get(i).getVekilIlce());
	adres3.setAttribute("ilceKodu",  uyapModel.get(i).getVekilIlceKodu());
	adres3.setAttribute("telefon",  uyapModel.get(i).getVekilTelefon());
	adres3.setAttribute("cepTelefon",uyapModel.get(i).getVekilCepTelefon());
	adres3.setAttribute("elektronikPostaAdresi","");
	adres3.setAttribute("adresTuru",  uyapModel.get(i).getVekilAdresTuru());
	adres3.setAttribute("adres",  uyapModel.get(i).getVekilAdres());
	adres3.setAttribute("postaKodu", "");
	adres3.setAttribute("adresTuruAciklama", uyapModel.get(i).getVekilAdresTuruAciklama());

	
		Element digerAlacak=doc.createElement("digerAlacak");
		dosya.appendChild(digerAlacak);
		
		digerAlacak.setAttribute("id", "digerAlacak_2");
		digerAlacak.setAttribute("tutarTur",uyapModel.get(i).getTutarTur()==null || uyapModel.get(i).getTutarTur()=="" ? "PRBRMTL":uyapModel.get(i).getTutarTur() );
		digerAlacak.setAttribute("tutar", ""+virgulYap(uyapModel.get(i).getTutar()+""));
		digerAlacak.setAttribute("alacakNo", "");
		digerAlacak.setAttribute("digerAlacakAciklama", uyapModel.get(i).getDigerAlacakAciklama()==null || uyapModel.get(i).getDigerAlacakAciklama()=="" ? "ihtarname, kredi sözleşmesi ve hesap ekstresi" :uyapModel.get(i).getDigerAlacakAciklama() );
		digerAlacak.setAttribute("tutarAdi", uyapModel.get(i).getPara_birimi()==null || uyapModel.get(i).getPara_birimi()=="" ? "TL - Türk Lirası" : uyapModel.get(i).getPara_birimi());
		digerAlacak.setAttribute("tarih", "");
		
		
		Element alacakKalemi2=doc.createElement("alacakKalemi");
		digerAlacak.appendChild(alacakKalemi2);
		
		
		alacakKalemi2.setAttribute("id","alacakKalemi_2");
		alacakKalemi2.setAttribute("alacakKalemKod", "3");
		alacakKalemi2.setAttribute("alacakKalemAdi", "Diğer Asıl Alacağı" );
		alacakKalemi2.setAttribute("alacakKalemTutar",""+virgulYap(uyapModel.get(i).getAlacakKalemTutar_alacakKalemi_2()+"") );
		alacakKalemi2.setAttribute("alacakKalemIlkTutar",""+virgulYap(uyapModel.get(i).getAlacakKalemTutar_alacakKalemi_2()+"") );
		alacakKalemi2.setAttribute("tutarTur",uyapModel.get(i).getTutarTur()==null || uyapModel.get(i).getTutarTur()=="" ? "PRBRMTL" : uyapModel.get(i).getTutarTur());
		alacakKalemi2.setAttribute("tutarAdi", uyapModel.get(i).getPara_birimi()==null || uyapModel.get(i).getPara_birimi()=="" ? "TL - Türk Lirası" :uyapModel.get(i).getPara_birimi());
		alacakKalemi2.setAttribute("sabitTaksitTarihi","" );
		alacakKalemi2.setAttribute("dovizKurCevrimi","" );
		alacakKalemi2.setAttribute("akdiFaiz","" );
		alacakKalemi2.setAttribute("alacakKalemKodAciklama","Asıl Alacak" );
		alacakKalemi2.setAttribute("aciklama","");
		alacakKalemi2.setAttribute("alacakKalemKodTuru", "1");
		
		
		Element alacakKalemi2Ref=doc.createElement("ref");
		alacakKalemi2.appendChild(alacakKalemi2Ref);
		
		alacakKalemi2Ref.setAttribute("id", "taraf_3");
		alacakKalemi2Ref.setAttribute("to", "taraf");
		
		Element alacakKalemi2Ref2=doc.createElement("ref");
		alacakKalemi2.appendChild(alacakKalemi2Ref2);
		
		alacakKalemi2Ref2.setAttribute("id", "taraf_2");
		alacakKalemi2Ref2.setAttribute("to", "taraf");
		
		Element alacakKalemi2Faiz=doc.createElement("faiz");
		alacakKalemi2.appendChild(alacakKalemi2Faiz);
		
		
				
		String Tarih = uyapModel.get(i).getBaslangicTarihi_alacakKalemi_2().toString();
		
		String newTarih = Tarih.substring(8, 10) + "/" +Tarih.substring(5,7) + "/" + Tarih.substring(0, 4);
		
		alacakKalemi2Faiz.setAttribute("id","faiz_2");
		alacakKalemi2Faiz.setAttribute("baslangicTarihi",newTarih);
		alacakKalemi2Faiz.setAttribute("bitisTarihi","");
		alacakKalemi2Faiz.setAttribute("faizOran",virgulYap(uyapModel.get(i).getTemerrut_faiz_orani()+""));
		alacakKalemi2Faiz.setAttribute("faizTipKod","FAIZT00003");
		alacakKalemi2Faiz.setAttribute("faizTipKodAciklama","Diğer");
		alacakKalemi2Faiz.setAttribute("faizTutar","");
		alacakKalemi2Faiz.setAttribute("faizTutarTur","");
		alacakKalemi2Faiz.setAttribute("faizTutarTurAdi","");
		alacakKalemi2Faiz.setAttribute("faizSureTip","2");
		
		
		
		Element alacakKalemi3=doc.createElement("alacakKalemi");
		digerAlacak.appendChild(alacakKalemi3);
		
		
		alacakKalemi3.setAttribute("id","alacakKalemi_3");
		alacakKalemi3.setAttribute("alacakKalemKod", "7171");
		alacakKalemi3.setAttribute("alacakKalemAdi","Diğer Faiz");
		alacakKalemi3.setAttribute("alacakKalemTutar",""+virgulYap(uyapModel.get(i).getAlacakKalemTutar_alacakKalemi_3()+"") );
		alacakKalemi3.setAttribute("alacakKalemIlkTutar",""+virgulYap(uyapModel.get(i).getAlacakKalemTutar_alacakKalemi_3()+"") );
		alacakKalemi3.setAttribute("alacakKalemTip","" );
		alacakKalemi3.setAttribute("tutarTur",uyapModel.get(i).getTutarTur()==null || uyapModel.get(i).getTutarTur()=="" ? "PRBRMTL" : uyapModel.get(i).getTutarTur());
		alacakKalemi3.setAttribute("tutarAdi",uyapModel.get(i).getPara_birimi()==null || uyapModel.get(i).getPara_birimi()=="" ? "TL - Türk Lirası" : uyapModel.get(i).getPara_birimi() );
		alacakKalemi3.setAttribute("sabitTaksitTarihi","" );
		alacakKalemi3.setAttribute("dovizKurCevrimi","" );
		alacakKalemi3.setAttribute("akdiFaiz","" );
		alacakKalemi3.setAttribute("alacakKalemKodAciklama","İşlemiş Faiz");
		alacakKalemi3.setAttribute("aciklama","");
		alacakKalemi3.setAttribute("alacakKalemKodTuru","0");
		
		
		Element alacakKalemi3Ref=doc.createElement("ref");
		alacakKalemi3.appendChild(alacakKalemi3Ref);
		
		alacakKalemi3Ref.setAttribute("id", "taraf_3");
		alacakKalemi3Ref.setAttribute("to", "taraf");
		
		Element alacakKalemi3Ref2=doc.createElement("ref");
		alacakKalemi3.appendChild(alacakKalemi3Ref2);
		
		alacakKalemi3Ref2.setAttribute("id", "taraf_2");
		alacakKalemi3Ref2.setAttribute("to", "taraf");
		
		
		Element alacakKalemi4=doc.createElement("alacakKalemi");
		digerAlacak.appendChild(alacakKalemi4);
		
		
		alacakKalemi4.setAttribute("id","alacakKalemi_4");
		alacakKalemi4.setAttribute("alacakKalemKod","6");
		alacakKalemi4.setAttribute("alacakKalemAdi", "Diğer Faiz Alacağı");
		alacakKalemi4.setAttribute("alacakKalemTutar",""+virgulYap(uyapModel.get(i).getAlacakKalemTutar_alacakKalemi_4()+"") );
		alacakKalemi4.setAttribute("alacakKalemIlkTutar",""+virgulYap(uyapModel.get(i).getAlacakKalemTutar_alacakKalemi_4()+"") );
		alacakKalemi4.setAttribute("alacakKalemTip","" );
		alacakKalemi4.setAttribute("tutarTur",uyapModel.get(i).getTutarTur()==null || uyapModel.get(i).getTutarTur()=="" ? "PRBRMTL" : uyapModel.get(i).getTutarTur());
		alacakKalemi4.setAttribute("tutarAdi",uyapModel.get(i).getPara_birimi()==null || uyapModel.get(i).getPara_birimi()=="" ? "TL - Türk Lirası" : uyapModel.get(i).getPara_birimi() );
		alacakKalemi4.setAttribute("sabitTaksitTarihi","" );
		alacakKalemi4.setAttribute("dovizKurCevrimi","" );
		alacakKalemi4.setAttribute("akdiFaiz","" );
		alacakKalemi4.setAttribute("alacakKalemKodAciklama","%5 BSMV" );
		alacakKalemi4.setAttribute("aciklama","");
		alacakKalemi4.setAttribute("alacakKalemKodTuru","2");
		
		
		Element alacakKalemi4Ref=doc.createElement("ref");
		alacakKalemi4.appendChild(alacakKalemi4Ref);
		
		alacakKalemi4Ref.setAttribute("id", "taraf_3");
		alacakKalemi4Ref.setAttribute("to", "taraf");
		
		Element alacakKalemi4Ref2=doc.createElement("ref");
		alacakKalemi4.appendChild(alacakKalemi4Ref2);
		
		alacakKalemi4Ref2.setAttribute("id", "taraf_2");
		alacakKalemi4Ref2.setAttribute("to", "taraf");
		
		if(uyapModel.get(i).getAlacakKalemTutar_alacakKalemi_5()>0){
		
		Element alacakKalemi5=doc.createElement("alacakKalemi");
		digerAlacak.appendChild(alacakKalemi5);
		
		
		alacakKalemi5.setAttribute("id","alacakKalemi_5");
		alacakKalemi5.setAttribute("alacakKalemKod","7170");
		alacakKalemi5.setAttribute("alacakKalemAdi","Masraf");
		alacakKalemi5.setAttribute("alacakKalemTutar",""+virgulYap(uyapModel.get(i).getAlacakKalemTutar_alacakKalemi_5()+"") );
		alacakKalemi5.setAttribute("alacakKalemIlkTutar",""+virgulYap(uyapModel.get(i).getAlacakKalemTutar_alacakKalemi_5()+"") );
		alacakKalemi5.setAttribute("alacakKalemTip","" );
		alacakKalemi5.setAttribute("tutarTur",uyapModel.get(i).getTutarTur()==null || uyapModel.get(i).getTutarTur()=="" ? "PRBRMTL" : uyapModel.get(i).getTutarTur());
		alacakKalemi5.setAttribute("tutarAdi",uyapModel.get(i).getPara_birimi()==null || uyapModel.get(i).getPara_birimi()=="" ? "TL - Türk Lirası" : uyapModel.get(i).getPara_birimi() );
		alacakKalemi5.setAttribute("sabitTaksitTarihi","" );
		alacakKalemi5.setAttribute("dovizKurCevrimi","" );
		alacakKalemi5.setAttribute("akdiFaiz","" );
		alacakKalemi5.setAttribute("alacakKalemKodAciklama","Diğer Masraf");
		alacakKalemi5.setAttribute("aciklama","");
		alacakKalemi5.setAttribute("alacakKalemKodTuru","0");
		
		
		Element alacakKalemi5Ref=doc.createElement("ref");
		alacakKalemi5.appendChild(alacakKalemi5Ref);
		
		alacakKalemi5Ref.setAttribute("id", "taraf_3");
		alacakKalemi5Ref.setAttribute("to", "taraf");
		
		Element alacakKalemi5Ref2=doc.createElement("ref");
		alacakKalemi5.appendChild(alacakKalemi5Ref2);
		
		alacakKalemi5Ref2.setAttribute("id", "taraf_2");
		alacakKalemi5Ref2.setAttribute("to", "taraf");
		
		}
		
			}
		
			
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		//StreamResult result = new StreamResult(new File("C:\\apache-tomcat-8.0.30\\webapps\\SEMIRAMIS\\UyapXMLFiles\\uyaptest.xml"));
		StreamResult result = new StreamResult(new File(DOSYA_KLASORU));
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);
		
		
		
		
		 File file = new File(DOSYA_KLASORU);
		// File file = new File("C:\\apache-tomcat-8.0.30\\webapps\\SEMIRAMIS\\UyapXMLFiles\\uyaptest.xml");
		    
		 InputStream fis = new FileInputStream(file);
	      byte[] buf = new byte[(int)file.length()];
	      int offset = 0;
	      int numRead = 0;
	      while ((offset < buf.length) && ((numRead = fis.read(buf, offset, buf.length -offset)) >= 0)) 
	      {
	        offset += numRead;
	      }
	      fis.close();
	      HttpServletResponse response =
	         (HttpServletResponse) FacesContext.getCurrentInstance()
	        .getExternalContext().getResponse();

	     response.setContentType("text/xml");
	     response.setHeader("Content-Disposition", "attachment;filename=TakipAcilis.xml");
	     response.getOutputStream().write(buf);
	     response.getOutputStream().flush();
	     response.getOutputStream().close();
	     FacesContext.getCurrentInstance().responseComplete();
	
	    
		
				} catch (IOException e) {
			String Hata="";
			for (int i = 0; i < e.getStackTrace().length; i++) {
				Hata += e.getStackTrace()[i]+" : ";	
			}
			newlog = new LogError();
			newlog.setHata_detay(Hata);
			newlog.setHata_value("ExportBean - exportUyapXML Prosedürü (IOException)");
			newlog.setPage("frm_UyapXML");
			newlog.setUser_id(99);
			
			
			try {
				log.Kaydet(newlog);	
			} catch (Exception e2) {
				
			}
			
				
		} catch (TransformerException e) {
			String Hata="";
			for (int i = 0; i < e.getStackTrace().length; i++) {
				Hata += e.getStackTrace()[i]+" : ";	
			}
			newlog = new LogError();
			newlog.setHata_detay(Hata);
			newlog.setHata_value("ExportBean - exportUyapXML Prosedürü (TransformerException)");
			newlog.setPage("frm_UyapXML");
			newlog.setUser_id(99);
			
			
			try {
				log.Kaydet(newlog);	
			} catch (Exception e2) {
				
			}
			
			
		} catch (ParserConfigurationException e) {
			String Hata="";
			for (int i = 0; i < e.getStackTrace().length; i++) {
				Hata += e.getStackTrace()[i]+" : ";	
			}
			newlog = new LogError();
			newlog.setHata_detay(Hata);
			newlog.setHata_value("ExportBean - exportUyapXML Prosedürü (ParserConfigurationException)");
			newlog.setPage("frm_UyapXML");
			newlog.setUser_id(99);
			
			
			try {
				log.Kaydet(newlog);	
			} catch (Exception e2) {
				
			}
			
			
		}catch(Exception ex){
			String Hata="";
			for (int i = 0; i < ex.getStackTrace().length; i++) {
				Hata += ex.getStackTrace()[i]+" : ";	
			}
			newlog = new LogError();
			newlog.setHata_detay(Hata);
			newlog.setHata_value("ExportBean - exportUyapXML Prosedürü (STANDART ERROR)");
			newlog.setPage("frm_UyapXML");
			newlog.setUser_id(99);
			
			
			try {
				log.Kaydet(newlog);	
			} catch (Exception e2) {
				
			}
		}finally {
			
		}
		
		
	

		//System.out.println("File saved!");
		
	//	FileDownloadView();
	 
	     
		
	}
	
	

	public void exportTakipTarihiUyapXML() 
	{

	try{
DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
DocumentBuilder docBuilder = docFactory.newDocumentBuilder();


Document doc = docBuilder.newDocument();
Element rootElement = doc.createElement("exchangeData");
doc.appendChild(rootElement);

Element staff = doc.createElement("exchangeHeader");
rootElement.appendChild(staff);

staff.setAttribute("version", "1.1");

Element dosyalar = doc.createElement("dosyalar");
rootElement.appendChild(dosyalar);



for (int i = 0; i < uyapModel.size(); i++) {
	
	Element dosya = doc.createElement("dosya");
	dosyalar.appendChild(dosya);
	
	dosya.setAttribute("dosyaTuru", uyapModel.get(i).getDosyaTuru());
	dosya.setAttribute("dosyaBelirleyicisi", uyapModel.get(i).getDosyaBelirleyicisi());
	dosya.setAttribute("KKDFUygulansin", uyapModel.get(i).getKkdfUygulansin());
	dosya.setAttribute("BSMVUygulansin", uyapModel.get(i).getBsmvUygulansin());
	dosya.setAttribute("BK84MaddeUygulansin", uyapModel.get(i).getBk84MaddeUygulansin());
	dosya.setAttribute("aciklama48e9", uyapModel.get(i).getAciklama48e9()== null || uyapModel.get(i).getAciklama48e9()== "" ? "HACİZ" : uyapModel.get(i).getAciklama48e9());
	dosya.setAttribute("alacaklininTalepEttigiHak", uyapModel.get(i).getAlacaklininTalepEttigiHak());
	dosya.setAttribute("takipSekli", uyapModel.get(i).getTakip_sekli_id());
	dosya.setAttribute("takipYolu", uyapModel.get(i).getTakipYolu());
	dosya.setAttribute("takipTuru", uyapModel.get(i).getTakipTuru()== null || uyapModel.get(i).getTakipTuru()== "" ? "1" : uyapModel.get(i).getTakipTuru() );
	dosya.setAttribute("dosyaTipi", uyapModel.get(i).getDosyaTipi()== null || uyapModel.get(i).getDosyaTipi()== "" ? "Genel İcra Dairesi" : uyapModel.get(i).getDosyaTipi());
	
		

Element taraf2=doc.createElement("taraf");
dosya.appendChild(taraf2);

taraf2.setAttribute("id", "taraf_2");

Element kisiKurumBilgileri=doc.createElement("kisiKurumBilgileri");
taraf2.appendChild(kisiKurumBilgileri);

kisiKurumBilgileri.setAttribute("id", "kisiKurumBilgileri_2");
kisiKurumBilgileri.setAttribute("ad", uyapModel.get(i).getKisiKurumBilgileriAd());

Element adres=doc.createElement("adres");
kisiKurumBilgileri.appendChild(adres);

adres.setAttribute("id", "adres_2");
adres.setAttribute("adresTuruAciklama", uyapModel.get(i).getMuvekkilAdresTuruAciklama()== null || 
										uyapModel.get(i).getMuvekkilAdresTuruAciklama()== "" ?
												"Yurt İçi İkametgah Adresi" : uyapModel.get(i).getMuvekkilAdresTuruAciklama());
adres.setAttribute("postaKodu", "");
adres.setAttribute("adres", uyapModel.get(i).getMuvekkilAdres());
adres.setAttribute("adresTuru", uyapModel.get(i).getMuvekkilAdresTuru()==null ||
								uyapModel.get(i).getMuvekkilAdresTuru()=="" ?
										"ADRTR00001" : uyapModel.get(i).getMuvekkilAdresTuru());
adres.setAttribute("elektronikPostaAdresi", "");
adres.setAttribute("cepTelefon", "");
adres.setAttribute("telefon", "");
adres.setAttribute("ilceKodu", uyapModel.get(i).getMuvekkilIlceKodu());
adres.setAttribute("ilce", uyapModel.get(i).getMuvekkilIlce());
adres.setAttribute("il", uyapModel.get(i).getMuvekkilIl());
adres.setAttribute("ilKodu", uyapModel.get(i).getMuvekkilIlKodu());

Element kurum2=doc.createElement("kurum");
kisiKurumBilgileri.appendChild(kurum2);

kurum2.setAttribute("id", "kurum_2");
kurum2.setAttribute("ticaretSicilNoVerildigiYer", "");
kurum2.setAttribute("harcDurumu", "0");
kurum2.setAttribute("ticaretSicilNo", "");
kurum2.setAttribute("kurumAdi", uyapModel.get(i).getMuvekkilKurumAdi());
kurum2.setAttribute("kamuOzel", uyapModel.get(i).getMuvekkilKamuOzel());
kurum2.setAttribute("vergiDairesi", "Büyük Mükellefler");
kurum2.setAttribute("sskIsyeriSicilNo", "");
kurum2.setAttribute("vergiNo", uyapModel.get(i).getMuvekkilVergiNo());

Element rolTur=doc.createElement("rolTur");
taraf2.appendChild(rolTur);


rolTur.setAttribute("rolID", "21");
rolTur.setAttribute("Rol", "ALACAKLI");


Element ref=doc.createElement("ref");
taraf2.appendChild(ref);

ref.setAttribute("id", "VekilKisi_2");
ref.setAttribute("to", "VekilKisi");

Element taraf3=doc.createElement("taraf");
dosya.appendChild(taraf3);

Element kisiKurumBilgileri3=doc.createElement("kisiKurumBilgileri");
taraf3.appendChild(kisiKurumBilgileri3);

taraf3.setAttribute("id", "taraf_3");

kisiKurumBilgileri3.setAttribute("id", "kisiKurumBilgileri_3");
kisiKurumBilgileri3.setAttribute("ad", uyapModel.get(i).getBorcluAdSoyad());

Element adres4=doc.createElement("adres");
kisiKurumBilgileri3.appendChild(adres4);


adres4.setAttribute("id","adres_4");
adres4.setAttribute("ilKodu", uyapModel.get(i).getBorcluIlKodu());
adres4.setAttribute("il",  uyapModel.get(i).getBorcluIl());
adres4.setAttribute("ilce",  uyapModel.get(i).getBorcluIlce());
adres4.setAttribute("ilceKodu",  uyapModel.get(i).getBorcluIlceKodu());
adres4.setAttribute("telefon",  "");
adres4.setAttribute("cepTelefon", "");
adres4.setAttribute("elektronikPostaAdresi", "");
adres4.setAttribute("adresTuru",  uyapModel.get(i).getBorcluAdresTuru()==null || uyapModel.get(i).getBorcluAdresTuru()=="" ? "ADRTR00001" : uyapModel.get(i).getBorcluAdresTuru());
adres4.setAttribute("adres",  uyapModel.get(i).getBorcluAdres());
adres4.setAttribute("postaKodu", "");
adres4.setAttribute("adresTuruAciklama", uyapModel.get(i).getBorcluAdresTuruAciklama()==null ||
	uyapModel.get(i).getBorcluAdresTuruAciklama()=="" ? "Yurt İçi İkametgah Adresi" : uyapModel.get(i).getBorcluAdresTuruAciklama());



	
	
Element kisiTumBilgileri=doc.createElement("kisiTumBilgileri");
kisiKurumBilgileri3.appendChild(kisiTumBilgileri);

kisiTumBilgileri.setAttribute("id", "kisiTumBilgileri_2");
kisiTumBilgileri.setAttribute("kayitNo", "");
kisiTumBilgileri.setAttribute("soyadi",uyapModel.get(i).getSoyad());
kisiTumBilgileri.setAttribute("ybnNfsKayitliOldgYer", "");
kisiTumBilgileri.setAttribute("aileSiraNo", "");
kisiTumBilgileri.setAttribute("cuzdanNo", "");
kisiTumBilgileri.setAttribute("babaAdi", "");
kisiTumBilgileri.setAttribute("anaAdi", "");
kisiTumBilgileri.setAttribute("adi", uyapModel.get(i).getAd());
kisiTumBilgileri.setAttribute("tcKimlikNo", uyapModel.get(i).getBorcluTCKimlik());
kisiTumBilgileri.setAttribute("oncekiSoyadi", "");
kisiTumBilgileri.setAttribute("ciltNo", "");
kisiTumBilgileri.setAttribute("cuzdanSeriNo", "");
kisiTumBilgileri.setAttribute("siraNo", "");
kisiTumBilgileri.setAttribute("mahKoy", "");
kisiTumBilgileri.setAttribute("dogumYeri", "");	

Element rolTur22=doc.createElement("rolTur");
taraf3.appendChild(rolTur22);

rolTur22.setAttribute("rolID", "22");
rolTur22.setAttribute("Rol", "BORÇLU/MÜFLİS");





Element vekilKisi=doc.createElement("VekilKisi");
dosya.appendChild(vekilKisi);

vekilKisi.setAttribute("id", "VekilKisi_2");

Element vekil=doc.createElement("vekil");
vekilKisi.appendChild(vekil);

vekil.setAttribute("id", "VekilKisi_2");
vekil.setAttribute("kurumAvukatiMi", uyapModel.get(i).getKurumAvukatimi());
vekil.setAttribute("avukatlikBuroAdi", uyapModel.get(i).getAvukatlikBaroAdi());
vekil.setAttribute("baroNo", uyapModel.get(i).getBaroNo());
vekil.setAttribute("vergiNo", uyapModel.get(i).getVergiNo());

Element vekilKisiTumBilgileri=doc.createElement("kisiTumBilgileri");
vekilKisi.appendChild(vekilKisiTumBilgileri);

vekilKisiTumBilgileri.setAttribute("id", "kisiTumBilgileri_3");
vekilKisiTumBilgileri.setAttribute("kayitNo", "");
vekilKisiTumBilgileri.setAttribute("soyadi",uyapModel.get(i).getSoyadi());
vekilKisiTumBilgileri.setAttribute("ybnNfsKayitliOldgYer", "");
vekilKisiTumBilgileri.setAttribute("aileSiraNo", "");
vekilKisiTumBilgileri.setAttribute("cuzdanNo", "");
vekilKisiTumBilgileri.setAttribute("babaAdi", "");
vekilKisiTumBilgileri.setAttribute("anaAdi", "");
vekilKisiTumBilgileri.setAttribute("adi", uyapModel.get(i).getAdi()	);
vekilKisiTumBilgileri.setAttribute("tcKimlikNo", "");
vekilKisiTumBilgileri.setAttribute("oncekiSoyadi", "");
vekilKisiTumBilgileri.setAttribute("ciltNo", "");
vekilKisiTumBilgileri.setAttribute("cuzdanSeriNo", "");
vekilKisiTumBilgileri.setAttribute("siraNo", "");
vekilKisiTumBilgileri.setAttribute("mahKoy", "");
vekilKisiTumBilgileri.setAttribute("dogumYeri", "");	


Element adres3=doc.createElement("adres");
vekilKisi.appendChild(adres3);


adres3.setAttribute("id","adres_3");
adres3.setAttribute("ilKodu", uyapModel.get(i).getVekilIlKodu());
adres3.setAttribute("il",  uyapModel.get(i).getVekilIl());
adres3.setAttribute("ilce",  uyapModel.get(i).getVekilIlce());
adres3.setAttribute("ilceKodu",  uyapModel.get(i).getVekilIlceKodu());
adres3.setAttribute("telefon",  uyapModel.get(i).getVekilTelefon());
adres3.setAttribute("cepTelefon",uyapModel.get(i).getVekilCepTelefon());
adres3.setAttribute("elektronikPostaAdresi","");
adres3.setAttribute("adresTuru",  uyapModel.get(i).getVekilAdresTuru());
adres3.setAttribute("adres",  uyapModel.get(i).getVekilAdres());
adres3.setAttribute("postaKodu", "");
adres3.setAttribute("adresTuruAciklama", uyapModel.get(i).getVekilAdresTuruAciklama());


Element digerAlacak=doc.createElement("digerAlacak");
dosya.appendChild(digerAlacak);

digerAlacak.setAttribute("id", "digerAlacak_2");
digerAlacak.setAttribute("tutarTur",uyapModel.get(i).getTutarTur()==null || uyapModel.get(i).getTutarTur()=="" ? "PRBRMTL":uyapModel.get(i).getTutarTur() );
digerAlacak.setAttribute("tutar", ""+virgulYap(uyapModel.get(i).getTutar()+""));
digerAlacak.setAttribute("alacakNo", "");
digerAlacak.setAttribute("digerAlacakAciklama", uyapModel.get(i).getDigerAlacakAciklama()==null || uyapModel.get(i).getDigerAlacakAciklama()=="" ? "ihtarname, kredi sözleşmesi ve hesap ekstresi" :uyapModel.get(i).getDigerAlacakAciklama() );
digerAlacak.setAttribute("tutarAdi", uyapModel.get(i).getPara_birimi()==null || uyapModel.get(i).getPara_birimi()=="" ? "TL - Türk Lirası" : uyapModel.get(i).getPara_birimi());
digerAlacak.setAttribute("tarih", "");


Element alacakKalemi2=doc.createElement("alacakKalemi");
digerAlacak.appendChild(alacakKalemi2);


alacakKalemi2.setAttribute("id","alacakKalemi_2");
alacakKalemi2.setAttribute("alacakKalemKod", "3");
alacakKalemi2.setAttribute("alacakKalemAdi", "Diğer Asıl Alacağı" );
alacakKalemi2.setAttribute("alacakKalemTutar",""+virgulYap(uyapModel.get(i).getAlacakKalemTutar_alacakKalemi_2()+"") );
alacakKalemi2.setAttribute("alacakKalemIlkTutar",""+virgulYap(uyapModel.get(i).getAlacakKalemTutar_alacakKalemi_2()+"") );
alacakKalemi2.setAttribute("tutarTur",uyapModel.get(i).getTutarTur()==null || uyapModel.get(i).getTutarTur()=="" ? "PRBRMTL" : uyapModel.get(i).getTutarTur());
alacakKalemi2.setAttribute("tutarAdi", uyapModel.get(i).getPara_birimi()==null || uyapModel.get(i).getPara_birimi()=="" ? "TL - Türk Lirası" :uyapModel.get(i).getPara_birimi());
alacakKalemi2.setAttribute("sabitTaksitTarihi","" );
alacakKalemi2.setAttribute("dovizKurCevrimi","" );
alacakKalemi2.setAttribute("akdiFaiz","" );
alacakKalemi2.setAttribute("alacakKalemKodAciklama","Asıl Alacak" );
alacakKalemi2.setAttribute("aciklama","");
alacakKalemi2.setAttribute("alacakKalemKodTuru", "1");


Element alacakKalemi2Ref=doc.createElement("ref");
alacakKalemi2.appendChild(alacakKalemi2Ref);

alacakKalemi2Ref.setAttribute("id", "taraf_3");
alacakKalemi2Ref.setAttribute("to", "taraf");

Element alacakKalemi2Ref2=doc.createElement("ref");
alacakKalemi2.appendChild(alacakKalemi2Ref2);

alacakKalemi2Ref2.setAttribute("id", "taraf_2");
alacakKalemi2Ref2.setAttribute("to", "taraf");

Element alacakKalemi2Faiz=doc.createElement("faiz");
alacakKalemi2.appendChild(alacakKalemi2Faiz);



alacakKalemi2Faiz.setAttribute("id","faiz_2");
alacakKalemi2Faiz.setAttribute("baslangicTarihi",format.format(Takip_Tarihi));
alacakKalemi2Faiz.setAttribute("bitisTarihi","");
alacakKalemi2Faiz.setAttribute("faizOran",virgulYap(uyapModel.get(i).getTemerrut_faiz_orani()+""));
alacakKalemi2Faiz.setAttribute("faizTipKod","FAIZT00003");
alacakKalemi2Faiz.setAttribute("faizTipKodAciklama","Diğer");
alacakKalemi2Faiz.setAttribute("faizTutar","");
alacakKalemi2Faiz.setAttribute("faizTutarTur","");
alacakKalemi2Faiz.setAttribute("faizTutarTurAdi","");
alacakKalemi2Faiz.setAttribute("faizSureTip","2");



Element alacakKalemi3=doc.createElement("alacakKalemi");
digerAlacak.appendChild(alacakKalemi3);


alacakKalemi3.setAttribute("id","alacakKalemi_3");
alacakKalemi3.setAttribute("alacakKalemKod", "7171");
alacakKalemi3.setAttribute("alacakKalemAdi","Diğer Faiz");
alacakKalemi3.setAttribute("alacakKalemTutar",""+virgulYap(uyapModel.get(i).getAlacakKalemTutar_alacakKalemi_3()+"") );
alacakKalemi3.setAttribute("alacakKalemIlkTutar",""+virgulYap(uyapModel.get(i).getAlacakKalemTutar_alacakKalemi_3()+"") );
alacakKalemi3.setAttribute("alacakKalemTip","" );
alacakKalemi3.setAttribute("tutarTur",uyapModel.get(i).getTutarTur()==null || uyapModel.get(i).getTutarTur()=="" ? "PRBRMTL" : uyapModel.get(i).getTutarTur());
alacakKalemi3.setAttribute("tutarAdi",uyapModel.get(i).getPara_birimi()==null || uyapModel.get(i).getPara_birimi()=="" ? "TL - Türk Lirası" : uyapModel.get(i).getPara_birimi() );
alacakKalemi3.setAttribute("sabitTaksitTarihi","" );
alacakKalemi3.setAttribute("dovizKurCevrimi","" );
alacakKalemi3.setAttribute("akdiFaiz","" );
alacakKalemi3.setAttribute("alacakKalemKodAciklama","İşlemiş Faiz");
alacakKalemi3.setAttribute("aciklama","");
alacakKalemi3.setAttribute("alacakKalemKodTuru","0");


Element alacakKalemi3Ref=doc.createElement("ref");
alacakKalemi3.appendChild(alacakKalemi3Ref);

alacakKalemi3Ref.setAttribute("id", "taraf_3");
alacakKalemi3Ref.setAttribute("to", "taraf");

Element alacakKalemi3Ref2=doc.createElement("ref");
alacakKalemi3.appendChild(alacakKalemi3Ref2);

alacakKalemi3Ref2.setAttribute("id", "taraf_2");
alacakKalemi3Ref2.setAttribute("to", "taraf");


Element alacakKalemi4=doc.createElement("alacakKalemi");
digerAlacak.appendChild(alacakKalemi4);


alacakKalemi4.setAttribute("id","alacakKalemi_4");
alacakKalemi4.setAttribute("alacakKalemKod","6");
alacakKalemi4.setAttribute("alacakKalemAdi", "Diğer Faiz Alacağı");
alacakKalemi4.setAttribute("alacakKalemTutar",""+virgulYap(uyapModel.get(i).getAlacakKalemTutar_alacakKalemi_4()+"") );
alacakKalemi4.setAttribute("alacakKalemIlkTutar",""+virgulYap(uyapModel.get(i).getAlacakKalemTutar_alacakKalemi_4()+"") );
alacakKalemi4.setAttribute("alacakKalemTip","" );
alacakKalemi4.setAttribute("tutarTur",uyapModel.get(i).getTutarTur()==null || uyapModel.get(i).getTutarTur()=="" ? "PRBRMTL" : uyapModel.get(i).getTutarTur());
alacakKalemi4.setAttribute("tutarAdi",uyapModel.get(i).getPara_birimi()==null || uyapModel.get(i).getPara_birimi()=="" ? "TL - Türk Lirası" : uyapModel.get(i).getPara_birimi() );
alacakKalemi4.setAttribute("sabitTaksitTarihi","" );
alacakKalemi4.setAttribute("dovizKurCevrimi","" );
alacakKalemi4.setAttribute("akdiFaiz","" );
alacakKalemi4.setAttribute("alacakKalemKodAciklama","%5 BSMV" );
alacakKalemi4.setAttribute("aciklama","");
alacakKalemi4.setAttribute("alacakKalemKodTuru","2");


Element alacakKalemi4Ref=doc.createElement("ref");
alacakKalemi4.appendChild(alacakKalemi4Ref);

alacakKalemi4Ref.setAttribute("id", "taraf_3");
alacakKalemi4Ref.setAttribute("to", "taraf");

Element alacakKalemi4Ref2=doc.createElement("ref");
alacakKalemi4.appendChild(alacakKalemi4Ref2);

alacakKalemi4Ref2.setAttribute("id", "taraf_2");
alacakKalemi4Ref2.setAttribute("to", "taraf");

if(uyapModel.get(i).getAlacakKalemTutar_alacakKalemi_5()>0){

Element alacakKalemi5=doc.createElement("alacakKalemi");
digerAlacak.appendChild(alacakKalemi5);


alacakKalemi5.setAttribute("id","alacakKalemi_5");
alacakKalemi5.setAttribute("alacakKalemKod","7170");
alacakKalemi5.setAttribute("alacakKalemAdi","Masraf");
alacakKalemi5.setAttribute("alacakKalemTutar",""+virgulYap(uyapModel.get(i).getAlacakKalemTutar_alacakKalemi_5()+"") );
alacakKalemi5.setAttribute("alacakKalemIlkTutar",""+virgulYap(uyapModel.get(i).getAlacakKalemTutar_alacakKalemi_5()+"") );
alacakKalemi5.setAttribute("alacakKalemTip","" );
alacakKalemi5.setAttribute("tutarTur",uyapModel.get(i).getTutarTur()==null || uyapModel.get(i).getTutarTur()=="" ? "PRBRMTL" : uyapModel.get(i).getTutarTur());
alacakKalemi5.setAttribute("tutarAdi",uyapModel.get(i).getPara_birimi()==null || uyapModel.get(i).getPara_birimi()=="" ? "TL - Türk Lirası" : uyapModel.get(i).getPara_birimi() );
alacakKalemi5.setAttribute("sabitTaksitTarihi","" );
alacakKalemi5.setAttribute("dovizKurCevrimi","" );
alacakKalemi5.setAttribute("akdiFaiz","" );
alacakKalemi5.setAttribute("alacakKalemKodAciklama","Diğer Masraf");
alacakKalemi5.setAttribute("aciklama","");
alacakKalemi5.setAttribute("alacakKalemKodTuru","0");


Element alacakKalemi5Ref=doc.createElement("ref");
alacakKalemi5.appendChild(alacakKalemi5Ref);

alacakKalemi5Ref.setAttribute("id", "taraf_3");
alacakKalemi5Ref.setAttribute("to", "taraf");

Element alacakKalemi5Ref2=doc.createElement("ref");
alacakKalemi5.appendChild(alacakKalemi5Ref2);

alacakKalemi5Ref2.setAttribute("id", "taraf_2");
alacakKalemi5Ref2.setAttribute("to", "taraf");

}

	}

	

TransformerFactory transformerFactory = TransformerFactory.newInstance();
Transformer transformer = transformerFactory.newTransformer();
DOMSource source = new DOMSource(doc);
StreamResult result = new StreamResult(new File(DOSYA_KLASORU));
//StreamResult result = new StreamResult(new File("C:/Users/JAVACI/Desktop/SEMIRAMIS/WebContent/UyapXMLFiles/uyaptest.xml"));
// Output to console for testing
// StreamResult result = new StreamResult(System.out);

transformer.transform(source, result);




//File file = new File("C:\\Users\\JAVACI\\Desktop\\SEMIRAMIS\\WebContent\\UyapXMLFiles\\uyaptest.xml");
File file = new File(DOSYA_KLASORU);

 InputStream fis = new FileInputStream(file);
  byte[] buf = new byte[(int)file.length()];
  int offset = 0;
  int numRead = 0;
  while ((offset < buf.length) && ((numRead = fis.read(buf, offset, buf.length -offset)) >= 0)) 
  {
    offset += numRead;
  }
  fis.close();
  HttpServletResponse response =
     (HttpServletResponse) FacesContext.getCurrentInstance()
    .getExternalContext().getResponse();

 response.setContentType("text/xml");
 response.setHeader("Content-Disposition", "attachment;filename=TakipAcilis.xml");
 response.getOutputStream().write(buf);
 response.getOutputStream().flush();
 response.getOutputStream().close();
 FacesContext.getCurrentInstance().responseComplete();
 

	} catch (IOException e) {
String Hata="";
for (int i = 0; i < e.getStackTrace().length; i++) {
	Hata += e.getStackTrace()[i]+" : ";	
}
newlog = new LogError();
newlog.setHata_detay(Hata);
newlog.setHata_value("ExportBean - exportTakipTarihiUyapXML Prosedürü (IOException)");
newlog.setPage("frm_UyapXML");
newlog.setUser_id(99);


try {
	log.Kaydet(newlog);	
} catch (Exception e2) {
	
}

	
} catch (TransformerException e) {
String Hata="";
for (int i = 0; i < e.getStackTrace().length; i++) {
	Hata += e.getStackTrace()[i]+" : ";	
}
newlog = new LogError();
newlog.setHata_detay(Hata);
newlog.setHata_value("ExportBean - exportTakipTarihiUyapXML Prosedürü (TransformerException)");
newlog.setPage("frm_UyapXML");
newlog.setUser_id(99);


try {
	log.Kaydet(newlog);	
} catch (Exception e2) {
	
}


} catch (ParserConfigurationException e) {
String Hata="";
for (int i = 0; i < e.getStackTrace().length; i++) {
	Hata += e.getStackTrace()[i]+" : ";	
}
newlog = new LogError();
newlog.setHata_detay(Hata);
newlog.setHata_value("ExportBean - exportTakipTarihiUyapXML Prosedürü (ParserConfigurationException)");
newlog.setPage("frm_UyapXML");
newlog.setUser_id(99);


try {
	log.Kaydet(newlog);	
} catch (Exception e2) {
	
}


}catch(Exception ex){
String Hata="";
for (int i = 0; i < ex.getStackTrace().length; i++) {
	Hata += ex.getStackTrace()[i]+" : ";	
}
newlog = new LogError();
newlog.setHata_detay(Hata);
newlog.setHata_value("ExportBean - exportTakipTarihiUyapXML Prosedürü (STANDART ERROR)");
newlog.setPage("frm_UyapXML");
newlog.setUser_id(99);


try {
	log.Kaydet(newlog);	
} catch (Exception e2) {
	
}
}finally {

}

		
	}
	


}
