package pelops.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

import pelops.db.DBConnection;
import pelops.model.IzlemeAnalizi;

public class IzlemeAnaliziDAO {

	public ArrayList<IzlemeAnalizi> Listele(String muvekkilAdi, String borcluAdi, String dosyaTipi, Date gelisTarihi1,Date gelisTarihi2, 
			Date odemeSozuTarihi1,Date odemeSozuTarihi2, double odenmeyuzdesi1,double odenmeyuzdesi2) throws Exception{
		
		 String SQL = "select * from vwizlemeanalizi where 1=1 ";
		
		 DBConnection DB = new DBConnection();
	     DB.newConnectDB();
	     
	     String oldDate="01/01/1900";
	     
	     @SuppressWarnings("deprecation")
		Date tarih = new Date(oldDate);
	     
	     String fullSQL="";
	      
	     if ((gelisTarihi1.equals(tarih)!=true) ) {fullSQL += " and gelis_tarihi between '"+gelisTarihi1+"' and '"+gelisTarihi2+"'";	}
	     if ((odemeSozuTarihi1.equals(tarih)!=true) ) {fullSQL += " and odeme_sozu_tarihi between '"+odemeSozuTarihi1+"' and '"+odemeSozuTarihi2+"'";	}
	     if (odenmeyuzdesi1>0) {fullSQL += " and odenmeyuzdesi between '"+odenmeyuzdesi1+"' and '"+odenmeyuzdesi2+"'";	}
	     
	     if(muvekkilAdi != ""){fullSQL += " and muvekkil_adi= '"+muvekkilAdi+"'";}
	     if(borcluAdi != ""){fullSQL += " and ad_soyad = '"+borcluAdi+"'";}
	     if(dosyaTipi != ""){fullSQL += " and dosyatipi = '"+dosyaTipi+"'";}
	     if (fullSQL=="") {fullSQL = SQL;} else {fullSQL = SQL+fullSQL;}

	        Statement stmt;
	        ResultSet rs;
	        stmt = DB.conn.createStatement();
	        rs = stmt.executeQuery(fullSQL);
	     IzlemeAnalizi izlemeanalizi;
	     NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
	     ArrayList<IzlemeAnalizi> analizListe = new ArrayList<IzlemeAnalizi>();
	     while(rs.next())
	     {
	    	 izlemeanalizi = new IzlemeAnalizi();
	    	 izlemeanalizi.setAd_soyad(rs.getString("ad_soyad"));
	    	 izlemeanalizi.setDosyatipi(rs.getString("dosyatipi"));
	    	 izlemeanalizi.setGelis_tarihi(rs.getDate("gelis_tarihi"));
	    	 izlemeanalizi.setIcra_dosyasi_no(rs.getString("icra_dosyasi_no"));
	    	 izlemeanalizi.setIzleme_tarihi(rs.getDate("izleme_tarihi"));
	    	 izlemeanalizi.setIzlemesonucu(rs.getString("izlemesonucu"));
	    	 izlemeanalizi.setMuvekkil_adi(rs.getString("muvekkil_adi"));
	    	 izlemeanalizi.setOdeme_sozu_miktari(rs.getDouble("odeme_sozu_miktari"));
	    	 izlemeanalizi.setOdeme_sozu_miktari_tl(defaultFormat.format(rs.getDouble("odeme_sozu_miktari")));
	    	 izlemeanalizi.setOdeme_sozu_tarihi(rs.getDate("odeme_sozu_tarihi"));
	    	 izlemeanalizi.setOdenmeyuzdesi(rs.getDouble("odenmeyuzdesi"));
	    	 analizListe.add(izlemeanalizi);
	    	 	    	 
	     }
	     DB.disconnectDB();
		
		 return analizListe;
	}
}
