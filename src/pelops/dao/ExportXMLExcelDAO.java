package pelops.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import pelops.db.DBConnection;
import pelops.model.LogError;
import pelops.model.UyapXML;

public class ExportXMLExcelDAO extends DBConnection {
	
	LogErrorDAO log = new LogErrorDAO();
	Date nowDate = new Date();
	LogError newlog;
	public ArrayList<UyapXML> exportUYAPXML() throws SQLException 
	{
		
		
	  String sql="SELECT dosyaturu, takipturu, takipyolu, alacaklinintalepettigihak, aciklama48e9, "
      +" bk84maddeuygulansin, bsmvuygulansin, kkdfuygulansin, dosyabelirleyicisi,  "
      +"  dosyatipi, kisikurumbilgileri_ad,        "
      +"  muvekkil_ilkodu, muvekkil_il, muvekkil_ilce, muvekkil_ilcekodu,           "
      +"  muvekkil_adresturu, muvekkil_adres, muvekkil_adresturuaciklama,           "
      +"  muvekkil_kurumadi, muvekkil_kamuozel, muvekkil_vergino, borclu_ilkodu,    "
      +"  borclu_il, borclu_ilce, borclu_ilcekodu, borclu_adresturu, borclu_adres,  "
      +"  borclu_adresturuaciklama, borclu_adsoyad, borclu_tckimlik, kurumavukatimi,"
      +"  avukatlikbaroadi, barono, vergino, soyadi, adi, vekil_ilkodu,             "
      +"  vekil_il, vekil_ilcekodu, vekil_ilce, vekil_telefon, vekil_ceptelefon,    "
      +"  vekil_adresturu, vekil_adres, vekil_adresturuaciklama,                    "
      +"  icra_dosyasi_id, digeralacak_tutartur, digeralacak_tutar,					"
      +"  alacakkalemi_alacakkalemtutar, alacakkalemi_alacakkalemilktutar, 			"
      +"  alacakkalemi_baslangic_tarihi, alacakkalemi_diger_faiz, 					"
      +"  alacakkalemi_diger_faiz_alacagi, masraf_tutari, ad, soyad,					"
      +"  temerrut_faiz_orani, alacak_kalemi2_kalem_kod_turu,  alacak_kalemi2_kalem_kod_aciklama,"
      + " alacak_kalemi2_alacak_kalem_adi,   alacak_kalemi3_kalem_kod_turu,"
      + " alacak_kalemi3_kalem_kod,   alacak_kalemi3_kalem_kod_aciklama,  alacak_kalemi3_alacak_kalem_adi,"
      + " alacak_kalemi4_kalem_kod_turu,	   alacak_kalemi4_kalem_kod_aciklama,	   alacak_kalemi4_alacak_kalem_adi,"
      + " alacak_kalemi4_kalem_kod,	   alacak_kalemi5_kalem_kod_turu,	   alacak_kalemi5_kalem_kod_aciklama,"
      + " alacak_kalemi5_alacak_kalem_adi,	   alacak_kalemi5_kalem_kod,	   para_birimi, takip_sekli_id, vergi_dairesi, ekleme_tarihi"
      + " FROM vwxmlhazirlama;";
	  
	  
	 newConnectDB();
	  
	  
	  ArrayList<UyapXML> liste = null;
	  Statement stmt;
	
		stmt = conn.createStatement();
	
	  
	  ResultSet set=stmt.executeQuery(sql);
	   liste=new ArrayList<UyapXML>();
	  
	  UyapXML uyap;
	  
	  while(set.next())
	  {
		  uyap=new UyapXML();
		  
		  uyap.setAciklama48e9(set.getString("aciklama48e9"));
		  uyap.setAdi(set.getString("adi"));
		  uyap.setAdresID("adres_2");
		  uyap.setAlacaklininTalepEttigiHak(set.getString("alacaklinintalepettigihak"));
		  uyap.setAvukatlikBaroAdi(set.getString("avukatlikbaroadi"));
		  uyap.setBaroNo(set.getString("barono"));
		  uyap.setBk84MaddeUygulansin(set.getString("bk84maddeuygulansin"));
		  uyap.setBorcluAdres(set.getString("borclu_adres"));
		  uyap.setBorcluAdresTuru(set.getString("borclu_adresturu"));
		  uyap.setBorcluAdresTuruAciklama(set.getString("borclu_adresturuaciklama"));
		  uyap.setBorcluAdSoyad(set.getString("borclu_adsoyad"));
		  uyap.setBorcluIl(set.getString("borclu_il"));
		  uyap.setBorcluIlce(set.getString("borclu_ilce"));
		  uyap.setBorcluIlceKodu(set.getString("borclu_ilcekodu"));
		  uyap.setBorcluIlKodu(set.getString("borclu_ilkodu"));
		  uyap.setBorcluTCKimlik(set.getString("borclu_tckimlik"));
		  uyap.setBsmvUygulansin(set.getString("bsmvuygulansin"));
		  uyap.setDosyaBelirleyicisi(set.getString("dosyabelirleyicisi"));
		  uyap.setDosyaTipi(set.getString("dosyatipi"));
		  uyap.setDosyaTuru(set.getString("dosyaturu"));
		  uyap.setKisiKurumBilgileriAd(set.getString("kisikurumbilgileri_ad"));
		  uyap.setKisiKurumBilgileriID("kisiKurumBilgileri_2");
		  uyap.setKkdfUygulansin(set.getString("kkdfuygulansin"));
		  uyap.setKurumAvukatimi(set.getString("kurumavukatimi"));
		  uyap.setMuvekkilAdres(set.getString("muvekkil_adres"));
		  uyap.setMuvekkilAdresTuru(set.getString("muvekkil_adresturu"));
		  uyap.setMuvekkilAdresTuruAciklama(set.getString("muvekkil_adresturuaciklama"));
		  uyap.setMuvekkilIl(set.getString("muvekkil_il"));
		  uyap.setMuvekkilIlce(set.getString("muvekkil_ilce"));
		  uyap.setMuvekkilIlceKodu(set.getString("muvekkil_ilcekodu"));
		  uyap.setMuvekkilIlKodu(set.getString("muvekkil_ilkodu"));
		  uyap.setMuvekkilKamuOzel(set.getString("muvekkil_kamuozel"));
		  uyap.setMuvekkilKurumAdi(set.getString("muvekkil_kurumadi"));
		  uyap.setMuvekkilVergiNo(set.getString("muvekkil_vergino"));
		  uyap.setSoyadi(set.getString("soyadi"));
		  uyap.setTakipTuru(set.getString("takipturu"));
		  uyap.setTakipYolu(set.getString("takipyolu"));
		  uyap.setVekilAdres(set.getString("vekil_adres"));
		  uyap.setVekilAdresTuru(set.getString("vekil_adresturu"));
		  uyap.setVekilAdresTuruAciklama(set.getString("vekil_adresturuaciklama"));
		  uyap.setVekilCepTelefon(set.getString("vekil_ceptelefon"));
		  uyap.setVekilIl(set.getString("vekil_il"));
		  uyap.setVekilIlce(set.getString("vekil_ilce"));
		  uyap.setVekilIlceKodu(set.getString("vekil_ilcekodu"));
		  uyap.setVekilIlKodu(set.getString("vekil_ilkodu"));
		  uyap.setVekilTelefon(set.getString("vekil_telefon"));
		  uyap.setVergiNo(set.getString("vergino"));
	    
		   uyap.setIcra_dosyasi_id(set.getInt("icra_dosyasi_id"));
		   uyap.setTutarTur(set.getString("digeralacak_tutartur"));
		   uyap.setTutar(set.getDouble("digeralacak_tutar"));
		   uyap.setAlacakKalemTutar_alacakKalemi_2(set.getDouble("alacakkalemi_alacakkalemtutar"));
		   uyap.setAlacakKalemIlkTutar_alacakKalemi_2(set.getDouble("alacakkalemi_alacakkalemilktutar"));
		   uyap.setBaslangicTarihi_alacakKalemi_2(set.getDate("alacakkalemi_baslangic_tarihi"));
		   uyap.setAlacakKalemTutar_alacakKalemi_3(set.getDouble("alacakkalemi_diger_faiz"));
	       uyap.setAlacakKalemTutar_alacakKalemi_4(set.getDouble("alacakkalemi_diger_faiz_alacagi"));
	       uyap.setAlacakKalemTutar_alacakKalemi_5(set.getDouble("masraf_tutari"));
	
	
	       uyap.setMasraf_tutari(set.getString("masraf_tutari"));
	       uyap.setAd(set.getString("ad"));
	       uyap.setSoyad(set.getString("soyad"));
	       Double temerrut = set.getDouble("temerrut_faiz_orani");
	       uyap.setTemerrut_faiz_orani(temerrut.toString());
	       uyap.setAlacak_kalemi2_kalem_kod_turu(set.getString("alacak_kalemi2_kalem_kod_turu"));
	       uyap.setAlacak_kalemi2_kalem_kod_aciklama(set.getString("alacak_kalemi2_kalem_kod_aciklama"));
	       uyap.setAlacak_kalemi2_alacak_kalem_adi(set.getString("alacak_kalemi2_alacak_kalem_adi"));
	       
	       uyap.setAlacak_kalemi3_kalem_kod_turu(set.getString("alacak_kalemi3_kalem_kod_turu"));
	       uyap.setAlacak_kalemi3_kalem_kod_aciklama(set.getString("alacak_kalemi3_kalem_kod_aciklama"));
	       uyap.setAlacak_kalemi3_alacak_kalem_adi(set.getString("alacak_kalemi3_alacak_kalem_adi"));
	       uyap.setAlacak_kalemi3_kalem_kod(set.getString("alacak_kalemi3_kalem_kod"));
	       
	       uyap.setAlacak_kalemi4_kalem_kod_turu(set.getString("alacak_kalemi4_kalem_kod_turu"));
	       uyap.setAlacak_kalemi4_kalem_kod_aciklama(set.getString("alacak_kalemi4_kalem_kod_aciklama"));
	       uyap.setAlacak_kalemi4_alacak_kalem_adi(set.getString("alacak_kalemi4_alacak_kalem_adi"));
	       uyap.setAlacak_kalemi4_kalem_kod(set.getString("alacak_kalemi4_kalem_kod"));
	       
	       
	       uyap.setAlacak_kalemi5_kalem_kod_turu(set.getString("alacak_kalemi5_kalem_kod_turu"));
	       uyap.setAlacak_kalemi5_kalem_kod_aciklama(set.getString("alacak_kalemi5_kalem_kod_aciklama"));
	       uyap.setAlacak_kalemi5_alacak_kalem_adi(set.getString("alacak_kalemi5_alacak_kalem_adi"));
	       uyap.setAlacak_kalemi5_kalem_kod(set.getString("alacak_kalemi5_kalem_kod"));
	       uyap.setTakip_sekli_id(set.getString("takip_sekli_id"));
	       uyap.setPara_birimi(set.getString("para_birimi"));
	       uyap.setVergi_dairesi(set.getString("vergi_dairesi"));
	       uyap.setEkleme_tarihi(set.getTimestamp("ekleme_tarihi"));
	       
	       	       
		  liste.add(uyap);
	  
		  
	  }
	
	
		return liste;
	}

	
	public ArrayList<UyapXML> exportAramaUYAPXML(Date ekleme_tarihi1,Date ekleme_tarihi2) throws SQLException
	{
		
		
	  String sql="SELECT dosyaturu, takipturu, takipyolu, alacaklinintalepettigihak, aciklama48e9, "
      +" bk84maddeuygulansin, bsmvuygulansin, kkdfuygulansin, dosyabelirleyicisi,  "
      +"  dosyatipi, kisikurumbilgileri_ad,        "
      +"  muvekkil_ilkodu, muvekkil_il, muvekkil_ilce, muvekkil_ilcekodu,           "
      +"  muvekkil_adresturu, muvekkil_adres, muvekkil_adresturuaciklama,           "
      +"  muvekkil_kurumadi, muvekkil_kamuozel, muvekkil_vergino, borclu_ilkodu,    "
      +"  borclu_il, borclu_ilce, borclu_ilcekodu, borclu_adresturu, borclu_adres,  "
      +"  borclu_adresturuaciklama, borclu_adsoyad, borclu_tckimlik, kurumavukatimi,"
      +"  avukatlikbaroadi, barono, vergino, soyadi, adi, vekil_ilkodu,             "
      +"  vekil_il, vekil_ilcekodu, vekil_ilce, vekil_telefon, vekil_ceptelefon,    "
      +"  vekil_adresturu, vekil_adres, vekil_adresturuaciklama,                    "
      +"  icra_dosyasi_id, digeralacak_tutartur, digeralacak_tutar,					"
      +"  alacakkalemi_alacakkalemtutar, alacakkalemi_alacakkalemilktutar, 			"
      +"  alacakkalemi_baslangic_tarihi, alacakkalemi_diger_faiz, 					"
      +"  alacakkalemi_diger_faiz_alacagi, masraf_tutari, ad, soyad,					"
      +"  temerrut_faiz_orani, alacak_kalemi2_kalem_kod_turu,  alacak_kalemi2_kalem_kod_aciklama,"
      + " alacak_kalemi2_alacak_kalem_adi,   alacak_kalemi3_kalem_kod_turu,"
      + " alacak_kalemi3_kalem_kod,   alacak_kalemi3_kalem_kod_aciklama,  alacak_kalemi3_alacak_kalem_adi,"
      + " alacak_kalemi4_kalem_kod_turu,	   alacak_kalemi4_kalem_kod_aciklama,	   alacak_kalemi4_alacak_kalem_adi,"
      + " alacak_kalemi4_kalem_kod,	   alacak_kalemi5_kalem_kod_turu,	   alacak_kalemi5_kalem_kod_aciklama,"
      + " alacak_kalemi5_alacak_kalem_adi,	   alacak_kalemi5_kalem_kod,	   para_birimi, takip_sekli_id, vergi_dairesi, ekleme_tarihi"
      + " FROM vwxmlhazirlama where ekleme_tarihi between '"+ekleme_tarihi1+"' and '"+ekleme_tarihi2+"'";
	  
	  
	  newConnectDB();
	  
	  Statement stmt=conn.createStatement();
	  
	  ResultSet set=stmt.executeQuery(sql);
	  
	  ArrayList<UyapXML> liste=new ArrayList<UyapXML>();
	  
	  UyapXML uyap;
	  
	  while(set.next())
	  {
		  uyap=new UyapXML();
		  
		  uyap.setAciklama48e9(set.getString("aciklama48e9"));
		  uyap.setAdi(set.getString("adi"));
		  uyap.setAdresID("adres_2");
		  uyap.setAlacaklininTalepEttigiHak(set.getString("alacaklinintalepettigihak"));
		  uyap.setAvukatlikBaroAdi(set.getString("avukatlikbaroadi"));
		  uyap.setBaroNo(set.getString("barono"));
		  uyap.setBk84MaddeUygulansin(set.getString("bk84maddeuygulansin"));
		  uyap.setBorcluAdres(set.getString("borclu_adres"));
		  uyap.setBorcluAdresTuru(set.getString("borclu_adresturu"));
		  uyap.setBorcluAdresTuruAciklama(set.getString("borclu_adresturuaciklama"));
		  uyap.setBorcluAdSoyad(set.getString("borclu_adsoyad"));
		  uyap.setBorcluIl(set.getString("borclu_il"));
		  uyap.setBorcluIlce(set.getString("borclu_ilce"));
		  uyap.setBorcluIlceKodu(set.getString("borclu_ilcekodu"));
		  uyap.setBorcluIlKodu(set.getString("borclu_ilkodu"));
		  uyap.setBorcluTCKimlik(set.getString("borclu_tckimlik"));
		  uyap.setBsmvUygulansin(set.getString("bsmvuygulansin"));
		  uyap.setDosyaBelirleyicisi(set.getString("dosyabelirleyicisi"));
		  uyap.setDosyaTipi(set.getString("dosyatipi"));
		  uyap.setDosyaTuru(set.getString("dosyaturu"));
		  uyap.setKisiKurumBilgileriAd(set.getString("kisikurumbilgileri_ad"));
		  uyap.setKisiKurumBilgileriID("kisiKurumBilgileri_2");
		  uyap.setKkdfUygulansin(set.getString("kkdfuygulansin"));
		  uyap.setKurumAvukatimi(set.getString("kurumavukatimi"));
		  uyap.setMuvekkilAdres(set.getString("muvekkil_adres"));
		  uyap.setMuvekkilAdresTuru(set.getString("muvekkil_adresturu"));
		  uyap.setMuvekkilAdresTuruAciklama(set.getString("muvekkil_adresturuaciklama"));
		  uyap.setMuvekkilIl(set.getString("muvekkil_il"));
		  uyap.setMuvekkilIlce(set.getString("muvekkil_ilce"));
		  uyap.setMuvekkilIlceKodu(set.getString("muvekkil_ilcekodu"));
		  uyap.setMuvekkilIlKodu(set.getString("muvekkil_ilkodu"));
		  uyap.setMuvekkilKamuOzel(set.getString("muvekkil_kamuozel"));
		  uyap.setMuvekkilKurumAdi(set.getString("muvekkil_kurumadi"));
		  uyap.setMuvekkilVergiNo(set.getString("muvekkil_vergino"));
		  uyap.setSoyadi(set.getString("soyadi"));
		  uyap.setTakipTuru(set.getString("takipturu"));
		  uyap.setTakipYolu(set.getString("takipyolu"));
		  uyap.setVekilAdres(set.getString("vekil_adres"));
		  uyap.setVekilAdresTuru(set.getString("vekil_adresturu"));
		  uyap.setVekilAdresTuruAciklama(set.getString("vekil_adresturuaciklama"));
		  uyap.setVekilCepTelefon(set.getString("vekil_ceptelefon"));
		  uyap.setVekilIl(set.getString("vekil_il"));
		  uyap.setVekilIlce(set.getString("vekil_ilce"));
		  uyap.setVekilIlceKodu(set.getString("vekil_ilcekodu"));
		  uyap.setVekilIlKodu(set.getString("vekil_ilkodu"));
		  uyap.setVekilTelefon(set.getString("vekil_telefon"));
		  uyap.setVergiNo(set.getString("vergino"));
	    
		   uyap.setIcra_dosyasi_id(set.getInt("icra_dosyasi_id"));
		   uyap.setTutarTur(set.getString("digeralacak_tutartur"));
		   uyap.setTutar(set.getDouble("digeralacak_tutar"));
		   uyap.setAlacakKalemTutar_alacakKalemi_2(set.getDouble("alacakkalemi_alacakkalemtutar"));
		   uyap.setAlacakKalemIlkTutar_alacakKalemi_2(set.getDouble("alacakkalemi_alacakkalemilktutar"));
		   uyap.setBaslangicTarihi_alacakKalemi_2(set.getDate("alacakkalemi_baslangic_tarihi"));
		   uyap.setAlacakKalemTutar_alacakKalemi_3(set.getDouble("alacakkalemi_diger_faiz"));
	       uyap.setAlacakKalemTutar_alacakKalemi_4(set.getDouble("alacakkalemi_diger_faiz_alacagi"));
	       uyap.setAlacakKalemTutar_alacakKalemi_5(set.getDouble("masraf_tutari"));
	
	
	       uyap.setMasraf_tutari(set.getString("masraf_tutari"));
	       uyap.setAd(set.getString("ad"));
	       uyap.setSoyad(set.getString("soyad"));
	       Double temerrut = set.getDouble("temerrut_faiz_orani");
	       uyap.setTemerrut_faiz_orani(temerrut.toString());
	       uyap.setAlacak_kalemi2_kalem_kod_turu(set.getString("alacak_kalemi2_kalem_kod_turu"));
	       uyap.setAlacak_kalemi2_kalem_kod_aciklama(set.getString("alacak_kalemi2_kalem_kod_aciklama"));
	       uyap.setAlacak_kalemi2_alacak_kalem_adi(set.getString("alacak_kalemi2_alacak_kalem_adi"));
	       
	       uyap.setAlacak_kalemi3_kalem_kod_turu(set.getString("alacak_kalemi3_kalem_kod_turu"));
	       uyap.setAlacak_kalemi3_kalem_kod_aciklama(set.getString("alacak_kalemi3_kalem_kod_aciklama"));
	       uyap.setAlacak_kalemi3_alacak_kalem_adi(set.getString("alacak_kalemi3_alacak_kalem_adi"));
	       uyap.setAlacak_kalemi3_kalem_kod(set.getString("alacak_kalemi3_kalem_kod"));
	       
	       uyap.setAlacak_kalemi4_kalem_kod_turu(set.getString("alacak_kalemi4_kalem_kod_turu"));
	       uyap.setAlacak_kalemi4_kalem_kod_aciklama(set.getString("alacak_kalemi4_kalem_kod_aciklama"));
	       uyap.setAlacak_kalemi4_alacak_kalem_adi(set.getString("alacak_kalemi4_alacak_kalem_adi"));
	       uyap.setAlacak_kalemi4_kalem_kod(set.getString("alacak_kalemi4_kalem_kod"));
	       
	       
	       uyap.setAlacak_kalemi5_kalem_kod_turu(set.getString("alacak_kalemi5_kalem_kod_turu"));
	       uyap.setAlacak_kalemi5_kalem_kod_aciklama(set.getString("alacak_kalemi5_kalem_kod_aciklama"));
	       uyap.setAlacak_kalemi5_alacak_kalem_adi(set.getString("alacak_kalemi5_alacak_kalem_adi"));
	       uyap.setAlacak_kalemi5_kalem_kod(set.getString("alacak_kalemi5_kalem_kod"));
	       uyap.setTakip_sekli_id(set.getString("takip_sekli_id"));
	       uyap.setPara_birimi(set.getString("para_birimi"));
	       uyap.setVergi_dairesi(set.getString("vergi_dairesi"));
	       uyap.setEkleme_tarihi(set.getTimestamp("ekleme_tarihi"));
	       
	       	       
		  liste.add(uyap);
	  
		  
	  }
		
		return liste;
	}
}
