package pelops.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import pelops.model.FreeXML;
import pelops.model.FreeXMLHesap;



public class FreeXMLDeneme {

	ArrayList<FreeXML> FreeXmlList = new ArrayList<FreeXML>();
	
	FreeXML FreeXmlModel;
	
	ArrayList<FreeXMLHesap> tmplistHesap;
	

	public Double doubleYap(String sttutar){
		sttutar = sttutar ==null ? "0,0" : sttutar;
		int nokta = sttutar.indexOf(",");
		String tam = sttutar.substring(0, nokta);
		String kesir = sttutar.substring(nokta+1, sttutar.length());
		String sayi = tam + "." + kesir;
		Double tutar = Double.parseDouble(sayi); 
		return tutar;
	}
	
	
	public void XMLOku(String Banka) throws ParserConfigurationException, SAXException, IOException {
		
	
		
		File dosya = new File("C:\\Users\\JAVA\\Desktop\\GARANTİ.xml");
		//File dosya = new File("C:\\Users\\JAVA\\Desktop\\HSBC.xml");
		//File dosya = new File("C:\\Users\\JAVA\\Desktop\\AKBANK.xml");
		//File dosya = new File("C:\\Users\\JAVA\\Desktop\\uyapGAR02.11 17TKP.xml");
		
	
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
									
									NodeList alacakKalemi = DosyaBilgileriIcerik.getChildNodes();
									FreeXMLHesap tmpHesap = new FreeXMLHesap();
									FreeXMLHesap tmpHesaplar = new FreeXMLHesap();
									
									for (int k1 = 0; k1 < alacakKalemi.getLength(); k1++) {
										
										Node alacakKalemiIcerik = alacakKalemi.item(k1);
										int kalemkod=0;
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
												
											}
											
												if(kalemkod==3){
												tmpHesaplar.setAsil_alacak(tmpHesap.getAlacakKalemTutar());
												}else if(kalemkod==7170){
												tmpHesaplar.setNoter_masrafi(tmpHesap.getAlacakKalemTutar());
												}else if(kalemkod==7171){
												tmpHesaplar.setGecikme_faizi(tmpHesap.getAlacakKalemTutar());
												}else if(kalemkod==6){
												tmpHesaplar.setFaizin_gider_vergisi(tmpHesap.getAlacakKalemTutar());
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
								
								
							}
			
			FreeXmlList.add(FreeXmlModel);
						
		}		
					
		
		
	}// PROSEDÜR SONU


	public static void main(String[] args)  {
	
		FreeXMLDeneme XML = new FreeXMLDeneme();
		try {
			XML.XMLOku("T.GARANTİ BANKASI A.Ş.");
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int j = 0; j < XML.FreeXmlList.size(); j++) {
			
		
		
		System.out.println("------------------------"+j+ ".. DOSYA BAŞLIK BİLGİLERİ -----------------------------");
		System.out.println("--------------------------------------------------------------------------------------------------");
		
		System.out.println(XML.FreeXmlList.get(j).getDosyaTipi());
		System.out.println(XML.FreeXmlList.get(j).getDosyaBelirleyicisi());
		System.out.println(XML.FreeXmlList.get(j).getKKDFUygulansin());
		System.out.println(XML.FreeXmlList.get(j).getBSMVUygulansin());
		System.out.println(XML.FreeXmlList.get(j).getBK84MaddeUygulansin());
		System.out.println(XML.FreeXmlList.get(j).getAciklama48e9());
		System.out.println(XML.FreeXmlList.get(j).getAlacaklininTalepEttigiHak());
		System.out.println(XML.FreeXmlList.get(j).getTakipSekli());
		System.out.println(XML.FreeXmlList.get(j).getTakipYolu());
		System.out.println(XML.FreeXmlList.get(j).getTakipTuru());
		System.out.println(XML.FreeXmlList.get(j).getDosyaTuru());
		

		System.out.println("------------------------KİŞİ BİLGİLERİ -------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------------------------------");
		
		System.out.println(XML.FreeXmlList.get(j).getAdSoyad());
		System.out.println(XML.FreeXmlList.get(j).getAdresTuruId());
		System.out.println(XML.FreeXmlList.get(j).getAdres());
		System.out.println(XML.FreeXmlList.get(j).getAd());
		System.out.println(XML.FreeXmlList.get(j).getSoyad());
		System.out.println(XML.FreeXmlList.get(j).getTcNo());
		System.out.println(XML.FreeXmlList.get(j).getIlAdi());
		System.out.println(XML.FreeXmlList.get(j).getIlId());
		
		System.out.println("--------------------------HESAP BİLGİLERİ---------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------------------------------");
		
		for (int i = 0; i < XML.FreeXmlList.get(j).getHesapListesi().size(); i++) {
			System.out.println("---------------------"+(i+1)+" . Hesap -----------------------------------------------------------------------------");
			System.out.println(XML.FreeXmlList.get(j).getHesapListesi().get(i).getTakip_alacagi());
			System.out.println(XML.FreeXmlList.get(j).getHesapListesi().get(i).getAsil_alacak());
			System.out.println(XML.FreeXmlList.get(j).getHesapListesi().get(i).getGecikme_faizi());
			System.out.println(XML.FreeXmlList.get(j).getHesapListesi().get(i).getFaizin_gider_vergisi());
			System.out.println(XML.FreeXmlList.get(j).getHesapListesi().get(i).getNoter_masrafi());
			System.out.println(XML.FreeXmlList.get(j).getHesapListesi().get(i).getTemerrut_faiz_orani());
			System.out.println("--------------------------------------------------------------------------------------------------");
		}
			
	
		
		
		System.out.println("--------------------------------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------------------------------");
		
		
		}
	
	}
	
}



