package pelops.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import pelops.db.DBConnection;
import pelops.model.DetayliArama;

public class GelismisAramaDAO {

	@SuppressWarnings("deprecation")
	public ArrayList<DetayliArama> Listele(
			 String muvekkilAdi,String borcluAdi,String icraDosyaNo,String icraMudurlugu,String cepTel,String isTel,
			 int bankaServisNo,int avukatCikisSevisNo,int bankaMusteriNo,
			 Date gelisTarihi1,Date gelisTarihi2,Date hitamTarihi1,Date hitamTarihi2,Date takipTarihi1,Date takipTarihi2
			) throws Exception{
			  	 DBConnection DB = new DBConnection();
	        DB.newConnectDB();
	        
	        String SQL = "select * from vwarama where 1=1";
	        
	    
	        
	        String oldDate="01/01/1900";
	        Date tarih = new Date(oldDate);
	        String fullSQL="";
	        if (gelisTarihi1.equals(tarih)!=true) {fullSQL += " and gelis_tarihi between '"+gelisTarihi1+"' and '"+gelisTarihi2+"'";	}
	        if (hitamTarihi1.equals(tarih)!=true) {fullSQL += " and hitam_tarihi between '"+hitamTarihi1+"' and '"+hitamTarihi2+"'";	}
	        if (takipTarihi1.equals(tarih)!=true) {fullSQL += " and takip_tarihi between '"+takipTarihi1+"' and '"+takipTarihi2+"'";	}
		        
	        if (icraDosyaNo!="" ) {fullSQL += " and icra_dosyasi_no= '"+icraDosyaNo+"' ";	}
	        if (muvekkilAdi!="" ) {fullSQL += " and muvekkil_adi= '"+muvekkilAdi+"'";}
	        if (icraMudurlugu!="") {fullSQL += " and icra_mudurlugu= '"+icraMudurlugu+"'";}
	        if (borcluAdi!="") {fullSQL += " and borclu_adi= '"+borcluAdi+"'";}
	        if (bankaServisNo>0) {fullSQL += " and servis_no= '"+bankaServisNo+"'";}
	        if (avukatCikisSevisNo>0) {fullSQL += " and avukat_sevis_no= '"+avukatCikisSevisNo+"'";}
	        if (bankaMusteriNo>0) {fullSQL += " and banka_musteri_no= '"+bankaMusteriNo+"'";}
	        if (cepTel!="") {fullSQL += " and telefon_no= "+cepTel+"";}
		       
	        
	        if (fullSQL=="") {fullSQL = SQL;} else {fullSQL = SQL+fullSQL;}
	        
	       
	  	        Statement stmt;
	        ResultSet rs;
	        stmt = DB.conn.createStatement();
	        rs = stmt.executeQuery(fullSQL);
	        DetayliArama dtyArama ;
	        ArrayList<DetayliArama> dtyList = new ArrayList<DetayliArama>();
	        while (rs.next()) {
	       
	        	dtyArama = new  DetayliArama();
	        	dtyArama.setId(rs.getInt("id"));
	        	dtyArama.setBorcluAdi(rs.getString("borclu_adi"));
	        	dtyArama.setCepTel(rs.getString("telefon_no"));
	        	dtyArama.setSonucGelisTarihi(rs.getDate("gelis_tarihi"));
	        	dtyArama.setSonucHitamTarihi(rs.getDate("hitam_tarihi"));
	        	dtyArama.setSonucTakipTarihi(rs.getDate("takip_tarihi"));
	        	dtyArama.setIcraDosyaNo(rs.getString("icra_dosyasi_no"));
	        	dtyArama.setIcraMudurlugu(rs.getString("icra_mudurlugu"));
	        	dtyArama.setMuvekkilAdi(rs.getString("muvekkil_adi"));
	        	
	        	dtyArama.setAvukatCikisSevisNo(rs.getString("avukat_sevis_no") != null ? (int)Double.parseDouble(rs.getString("avukat_sevis_no")): 0);
	        	
	        	dtyArama.setBankaServisNo(rs.getString("banka_servis_no"));
	        	
	        	dtyList.add(dtyArama);
	        }
	        
	        DB.disconnectDB();
	       
		return dtyList;
	}
}
