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

import pelops.model.XMLToUyap;

public class XMLDeneme {
	
	ArrayList<XMLToUyap> XmlList = new ArrayList<XMLToUyap>();
	
	
	
	XMLToUyap XmlModel;

	String ChildOzellik;

		public void XMLOku() throws ParserConfigurationException, SAXException, IOException {
			
		
			
			File dosya = new File("C:\\Users\\Java_Hckr\\Desktop\\ÖRNEK EKİM.xml");
		
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(dosya);
		
			NodeList nList_Dosya = doc.getElementsByTagName("dosya");
			
			for (int i = 0; i < nList_Dosya.getLength(); i++) { // ANA FOR DÖNGÜSÜ START (XML İçinceki Dosyasayısı kadar Döner)

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
													XmlModel.set_digerAlacak_tutar(Taraf_Vekilkisi_Digeralacak_Icerik.getAttributes().item(j2).getNodeValue());
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

																							} else if (j3==1) {

																								XmlModel.set__alacakKalemi3_alacakKalemTutar(Bilgiler);

																							} else if (j3==2) {

																								XmlModel.set__alacakKalemi4_alacakKalemTutar(Bilgiler);

																							} else if (j3==3) {

																								XmlModel.set__alacakKalemi5_alacakKalemTutar(Bilgiler);

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
	
		
		public static void main(String[] args)  {
			
			XMLDeneme XML = new XMLDeneme();
			try {
				XML.XMLOku();
			} catch (ParserConfigurationException | SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			System.out.println("------------------------ Taraf_2 -----------------------------");
			System.out.println(XML.XmlList.get(0).getDosyaTipi());
			System.out.println(XML.XmlList.get(0).getDosyaBelirleyicisi());
			System.out.println(XML.XmlList.get(0).getKKDFUygulansin());
			System.out.println(XML.XmlList.get(0).getBSMVUygulansin());
			System.out.println(XML.XmlList.get(0).getBK84MaddeUygulansin());
			System.out.println(XML.XmlList.get(0).getAciklama48e9());
			System.out.println(XML.XmlList.get(0).getAlacaklininTalepEttigiHak());
			System.out.println(XML.XmlList.get(0).getTakipSekli());
			System.out.println(XML.XmlList.get(0).getTakipYolu());
			System.out.println(XML.XmlList.get(0).getTakipTuru());
			System.out.println(XML.XmlList.get(0).getDosyaTuru());
			
		
			
			
			
			System.out.println("--------------------------------------------------------------------------------------------------");
			System.out.println("--------------------------------------------------------------------------------------------------");
			System.out.println("--------------------------------------------------------------------------------------------------");
			System.out.println("--------------------------------------------------------------------------------------------------");
			
			
			
			System.out.println("------------------------ Taraf_2 -----------------------------");
			System.out.println(XML.XmlList.get(0).get__kisiKurumBilgileri2_id());
			System.out.println(XML.XmlList.get(0).get__kisiKurumBilgileri2_ad());
			System.out.println(XML.XmlList.get(0).get__rolTur2_Rol());
			System.out.println(XML.XmlList.get(0).get__rolTur2_rolID());
			System.out.println(XML.XmlList.get(0).get__ref2_id());
			System.out.println(XML.XmlList.get(0).get__ref2_to());
			System.out.println("--------------------------------------------------------------------------------------------------");
			System.out.println("--------------------------------------------------------------------------------------------------");
			System.out.println("------------------------ Taraf_3 -----------------------------");
			System.out.println(XML.XmlList.get(0).get__kisiKurumBilgileri3_id());
			System.out.println(XML.XmlList.get(0).get__kisiKurumBilgileri3_ad());
			System.out.println(XML.XmlList.get(0).get__rolTur3_Rol());
			System.out.println(XML.XmlList.get(0).get__rolTur3_rolID());
			System.out.println("------------------------ Vekil_2  -----------------------------");
			System.out.println("---------------------------------------- Vekil Bilgileri  -----------------------------");
			System.out.println(XML.XmlList.get(0).get__vekil2_id());
			System.out.println(XML.XmlList.get(0).get__vekil2_verigNo());
			System.out.println(XML.XmlList.get(0).get__vekil2_kurumAvukatiMi());
			System.out.println("---------------------------------------- Vekil Kişi Tüm Bilgileri  -----------------------------");
			System.out.println(XML.XmlList.get(0).get__kisiTumBilgileri2_id());
			System.out.println(XML.XmlList.get(0).get__kisiTumbilgileri2_adi());
			System.out.println(XML.XmlList.get(0).get__kisiTumbilgileri2_soyadi());
			System.out.println("---------------------------------------- Vekil Kişi Adres Bilgileri  -----------------------------");
			System.out.println(XML.XmlList.get(0).get__adres3_id());
			System.out.println(XML.XmlList.get(0).get__adres3_adresTuruAciklama());
			System.out.println(XML.XmlList.get(0).get__adres3_adres());
			System.out.println(XML.XmlList.get(0).get__adres3_adresTuru());
			System.out.println(XML.XmlList.get(0).get__adres3_cepTelefon());
			System.out.println(XML.XmlList.get(0).get__adres3_telefon());
			System.out.println(XML.XmlList.get(0).get__adres3_ilceKodu());
			System.out.println(XML.XmlList.get(0).get__adres3_ilce());
			System.out.println(XML.XmlList.get(0).get__adres3_il());
			System.out.println(XML.XmlList.get(0).get__adres3_ilKodu());
			System.out.println("--------------------------------------------------------------------------------------------------");
			System.out.println("--------------------------------------------------------------------------------------------------");
			
			
			
			System.out.println("---------------------------------------- DİĞER ALACAK BİGLİLERİ  ----------------------------------");
		
			System.out.println("---------------------------------------- DİĞER ALACAK 2  ----------------------------------");
			System.out.println(XML.XmlList.get(0).get__alacakKalemi2_id());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi2_tutarAdi());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi2_tutarTur());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi2_alacakKalemKodTuru());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi2_alacakKalemKodAciklama());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi2_alacakKalemIlkTutar());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi2_alacakKalemTutar());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi2_alacakKalemAdi());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi2_alacakKalemKod());
			
			
			
			
			
			
			
			System.out.println("---------------------------------------- DİĞER ALACAK 3  ----------------------------------");
			System.out.println(XML.XmlList.get(0).get__alacakKalemi3_id());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi3_tutarAdi());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi3_tutarTur());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi3_alacakKalemKodTuru());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi3_alacakKalemKodAciklama());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi3_alacakKalemIlkTutar());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi3_alacakKalemTutar());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi3_alacakKalemAdi());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi3_alacakKalemKod());
			
			System.out.println("---------------------------------------- DİĞER ALACAK 4  ----------------------------------");
			System.out.println(XML.XmlList.get(0).get__alacakKalemi4_id());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi4_tutarAdi());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi4_tutarTur());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi4_alacakKalemKodTuru());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi4_alacakKalemKodAciklama());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi4_alacakKalemIlkTutar());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi4_alacakKalemTutar());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi4_alacakKalemAdi());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi4_alacakKalemKod());
			
			System.out.println("---------------------------------------- DİĞER ALACAK 5  ----------------------------------");
			System.out.println(XML.XmlList.get(0).get__alacakKalemi5_id());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi5_tutarAdi());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi5_tutarTur());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi5_alacakKalemKodTuru());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi5_alacakKalemKodAciklama());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi5_alacakKalemIlkTutar());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi5_alacakKalemTutar());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi5_alacakKalemAdi());
			System.out.println(XML.XmlList.get(0).get__alacakKalemi5_alacakKalemKod());
			System.out.println("--------------------------------------------------------------------------------------------------");
			System.out.println("--------------------------------------------------------------------------------------------------");
			
						
			
			
			System.out.println("---------------------------------------- TARAF 2 NİN ADRES 2  BİLGiLERİ  ----------------------------------");
			System.out.println(XML.XmlList.get(0).get___adres2_id());
			System.out.println(XML.XmlList.get(0).get___adres2_adresTuruAciklama());
			System.out.println(XML.XmlList.get(0).get___adres2_adres());
			System.out.println(XML.XmlList.get(0).get___adres2_adresTuru());
			System.out.println(XML.XmlList.get(0).get___adres2_ilceKodu());
			System.out.println(XML.XmlList.get(0).get___adres2_ilce());
			System.out.println(XML.XmlList.get(0).get___adres2_il());
			System.out.println(XML.XmlList.get(0).get___adres2_ilKodu());
			System.out.println("---------------------------------------- TARAF 2 NİN KURUM 2  BİLGiLERİ  ----------------------------------");
			System.out.println(XML.XmlList.get(0).get___kurum2_id());
			System.out.println(XML.XmlList.get(0).get___kurum2_vergiNo());
			System.out.println(XML.XmlList.get(0).get___kurum2_vergiDairesi());
			System.out.println(XML.XmlList.get(0).get___kurum2_kamuOzel());
			System.out.println(XML.XmlList.get(0).get___kurum2_kurumAdi());
			System.out.println(XML.XmlList.get(0).get___kurum2_harcDurumu());
			System.out.println("---------------------------------------- TARAF 3 NİN ADRES 4  BİLGiLERİ  ----------------------------------");
			System.out.println(XML.XmlList.get(0).get___adres4_id());
			System.out.println(XML.XmlList.get(0).get___adres4_adresTuruAciklama());
			System.out.println(XML.XmlList.get(0).get___adres4_adres());
			System.out.println(XML.XmlList.get(0).get___adres4_adresTuru());
			System.out.println(XML.XmlList.get(0).get___adres4_ilceKodu());
			System.out.println(XML.XmlList.get(0).get___adres4_ilce());
			System.out.println(XML.XmlList.get(0).get___adres4_il());
			System.out.println(XML.XmlList.get(0).get___adres4_ilKodu());
			System.out.println("---------------------------------------- TARAF 3 NİN KİŞİ TÜM BİLGiLERİ  ----------------------------------");
			System.out.println(XML.XmlList.get(0).get___kisiTumbilgileri3_id());
			System.out.println(XML.XmlList.get(0).get___kisiTumbilgileri3_tcKimlikNo());
			System.out.println(XML.XmlList.get(0).get___kisiTumbilgileri3_adi());
			System.out.println(XML.XmlList.get(0).get___kisiTumbilgileri3_soyadi());
			System.out.println("---------------------------------------- DİĞER ALACAK 2 FAİZ BİLGİLERİ BİLGiLERİ  ----------------------------------");
			System.out.println(XML.XmlList.get(0).get___alacakKalemi2_faiz2_id());
			System.out.println(XML.XmlList.get(0).get___alacakKalemi2_faiz_faizSureTip());
			System.out.println(XML.XmlList.get(0).get___alacakKalemi2_faiz2_faizTipKodAciklama());
			System.out.println(XML.XmlList.get(0).get___alacakKalemi2_faiz2_faizTipKod());
			System.out.println(XML.XmlList.get(0).get___alacakKalemi2_faiz2_faizOran());
			System.out.println(XML.XmlList.get(0).get___alacakKalemi2_faiz2_baslangicTarihi());
			
			
			System.out.println("----------------------------------------------------------------");
			System.out.println(XML.XmlList.get(0).get_digerAlacak_id());
			System.out.println(XML.XmlList.get(0).get_digerAlacak_tutar());
			System.out.println(XML.XmlList.get(0).get_digerAlacak_digerAlacakAciklama());
			System.out.println(XML.XmlList.get(0).get_digerAlacak_tutarAdi());
			System.out.println(XML.XmlList.get(0).get_digerAlacak_tutarTur());
			
			
		
			
		}
}
