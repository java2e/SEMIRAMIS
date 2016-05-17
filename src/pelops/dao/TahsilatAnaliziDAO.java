package pelops.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

import pelops.db.DBConnection;
import pelops.model.TahsilatAnalizi;

public class TahsilatAnaliziDAO extends DBConnection{

	ResultSet rs;
	Statement stmt;
	PreparedStatement pstmt;
	ArrayList<TahsilatAnalizi> TahsilatListesi ;
	
public ArrayList<TahsilatAnalizi> Listele(int id, int icraDosyasiId, String icraDosyaNo, String muvekkilAdi, String borcluAdi, Date gelisTarihi1, Date gelisTarihi2
									, String borcTipi, String dosyaTipi, String icraMudurlugu, Date tahsilatTarihi1, Date tahsilatTarihi2, String TahsilatTipi,
									double tahsilatMiktari, String tahsilatStatusu) throws Exception{
		
		String SQL = "SELECT id, icra_dosyasi_id, icra_dosya_no, muvekkil_adi, borclu_adi,"
				+ " gelis_tarihi, borc_tipi, dosya_tipi, icra_mudurlugu, tahsilat_tarihi,"
				+ " tahsilat_tipi, tahsilat_miktari, tahsilat_statusu  FROM tbl_tahsilat where 1=1";
		
	    String oldDate="01/01/1900";
	    
        @SuppressWarnings("deprecation")
		Date tarih = new Date(oldDate);
        String fullSQL="";
        if (gelisTarihi1.equals(tarih)!=true) {fullSQL += " and tahsilat_tarihi between '"+gelisTarihi1+"' and '"+gelisTarihi2+"'";	}
        if (tahsilatTarihi1.equals(tarih)!=true) {fullSQL += " and hitam_tarihi between '"+tahsilatTarihi1+"' and '"+tahsilatTarihi2+"'";	}
     
        if (id >0 ) {fullSQL += " and id= '"+id+"' ";	}
        if (icraDosyasiId >0 ) {fullSQL += " and icra_dosyasi_id= '"+icraDosyasiId+"' ";	}
        if (icraDosyaNo != "" ) {fullSQL += " and icra_dosya_no= '"+icraDosyaNo+"' ";	}
        if (muvekkilAdi != "" ) {fullSQL += " and muvekkil_adi= '"+muvekkilAdi+"' ";	}
        if (borcluAdi != "" ) {fullSQL += " and borclu_adi= '"+borcluAdi+"' ";	}
        if (borcTipi != "" ) {fullSQL += " and borc_tipi= '"+borcTipi+"' ";	}
        if (dosyaTipi != "" ) {fullSQL += " and dosya_tipi= '"+dosyaTipi+"' ";	}
        if (icraMudurlugu != "" ) {fullSQL += " and icra_mudurlugu= '"+icraMudurlugu+"' ";	}
        if (TahsilatTipi != "" ) {fullSQL += " and tahsilat_tipi= '"+TahsilatTipi+"' ";	}
        if (tahsilatMiktari >0 ) {fullSQL += " and tahsilat_miktari= '"+tahsilatMiktari+"' ";	}
        if (tahsilatStatusu != "" ) {fullSQL += " and tahsilat_statusu= '"+tahsilatStatusu+"' ";	}
        
        if (fullSQL=="") {fullSQL = SQL;} else {fullSQL = SQL+fullSQL;}
			
		newConnectDB();
		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(fullSQL);
		TahsilatAnalizi tahsilat;
		TahsilatListesi = new ArrayList<TahsilatAnalizi>();
		  NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		while(rs.next()){
			tahsilat = new TahsilatAnalizi();
			tahsilat.setBorc_tipi(rs.getString("borc_tipi"));
			tahsilat.setBorclu_adi(rs.getString("borclu_adi"));
			tahsilat.setDosya_tipi(rs.getString("dosya_tipi"));
			tahsilat.setGelis_tarihi(rs.getDate("gelis_tarihi"));
			tahsilat.setIcra_dosya_no(rs.getString("icra_dosya_no"));
			tahsilat.setIcra_dosyasi_id(rs.getInt("icra_dosyasi_id"));
			tahsilat.setIcra_mudurlugu(rs.getString("icra_mudurlugu"));
			tahsilat.setId(rs.getInt("id"));
			tahsilat.setMuvekkil_adi(rs.getString("muvekkil_adi"));
			tahsilat.setTahsilat_miktari(rs.getDouble("tahsilat_miktari"));
			tahsilat.setTahsilat_miktari_tl(defaultFormat.format(rs.getDouble("tahsilat_miktari")));
			
			tahsilat.setTahsilat_statusu(rs.getString("tahsilat_statusu"));
			tahsilat.setTahsilat_tarihi(rs.getDate("tahsilat_tarihi"));
			tahsilat.setTahsilat_tipi(rs.getString("tahsilat_tipi"));
			
			TahsilatListesi.add(tahsilat);	
			
		}
		
				
		disconnectDB();
		return TahsilatListesi;
	}
	
}
