package pelops.dao;


import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

import pelops.db.DBConnection;
import pelops.model.MuameleAnalizi;

public class MuameleAnaliziDAO {

	public  ArrayList<MuameleAnalizi> Listele(String icraDosyaNo,String muvekkilAdi,String icraMudurlugu,String dosyaTipi,
			String borcTipi,String takipTipi, String riskYoneticisi, Date gelisTarihi1, Date gelisTarihi2,
			Date kesinlesmeTarihi1, Date kesinlesmeTarihi2) throws Exception{
    	
   	 DBConnection DB = new DBConnection();
        DB.newConnectDB();
        
       // muamele.getIcra_dosyasi_no()!=null ? "muamele.getIcra_dosyasi_no()" : "*"
     
        String SQL = "select * from vw_muamele_detayli where "
        		+ " gelis_tarihi between '"+gelisTarihi1+"' and '"+gelisTarihi2+"'";
        		//+ " and kesinlesme_tarihi between '"+kesinlesmeTarihi1+"' and '"+kesinlesmeTarihi2+"'";
     
        String fullSQL="";
        if (icraDosyaNo!="") {fullSQL += " and icra_dosyasi_no= '"+icraDosyaNo+"' ";	}
        if (muvekkilAdi!="") {fullSQL += " and muvekkil_adi= '"+muvekkilAdi+"'";}
        if (icraMudurlugu!="") {fullSQL += " and icra_mudurlugu= '"+icraMudurlugu+"'";}
        if (dosyaTipi!="") {fullSQL += " and dosya_tipi= '"+dosyaTipi+"'";}
        if (borcTipi!="") {fullSQL += " and borc_tipi= '"+borcTipi+"'";}
        if (takipTipi!="") {fullSQL += " and takip_tipi= '"+takipTipi+"'";}
        if (riskYoneticisi!="") {fullSQL += " and risk_yoneticisi= '"+riskYoneticisi+"'";}
       
        if (fullSQL=="") {fullSQL = SQL;} else {fullSQL = SQL+fullSQL;}
            Statement stmt;
        ResultSet rs;
        stmt = DB.conn.createStatement();
        rs = stmt.executeQuery(fullSQL);
        MuameleAnalizi muameleanalizi;
        ArrayList<MuameleAnalizi> muamelelist = new ArrayList<MuameleAnalizi>();
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
        while (rs.next()) {
        		
        	muameleanalizi = new MuameleAnalizi();
        	muameleanalizi.setId(rs.getInt("id"));
            muameleanalizi.setAraba(rs.getString("araba"));
            muameleanalizi.setBorc_tipi(rs.getString("borc_tipi"));
            muameleanalizi.setBorclu_adi(rs.getString("borclu_adi"));
            muameleanalizi.setDosya_tipi(rs.getString("dosya_tipi"));
            muameleanalizi.setEv(rs.getString("ev"));
            muameleanalizi.setIcra_dosyasi_no(rs.getString("icra_dosyasi_no"));
            muameleanalizi.setIcra_mudurlugu(rs.getString("icra_mudurlugu"));
            muameleanalizi.setKalantl(defaultFormat.format(rs.getDouble("kalantutar")));
            muameleanalizi.setKalan(rs.getDouble("kalantutar"));
            muameleanalizi.setMuvekkil_adi(rs.getString("muvekkil_adi"));
            muameleanalizi.setRisk_yoneticisi(rs.getString("risk_yoneticisi"));
            muameleanalizi.setSgk(rs.getString("sgk"));
            muameleanalizi.setTakip_tipi(rs.getString("takip_tipi"));
            muameleanalizi.setGelis_tarihi(rs.getDate("gelis_tarihi"));
            muameleanalizi.setKesinlesme_tarihi(rs.getDate("kesinlesme_tarihi"));
                      
            muamelelist.add(muameleanalizi);
        }
       DB.disconnectDB();
      
       return muamelelist;
      	    	
   }
	
	  
	
}
