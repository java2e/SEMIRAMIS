package pelops.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.db.DBConnection;
import pelops.model.DosyaTipi;
import pelops.model.GenelTanimSablon;

public class DosyaTipiDAO {

	 public static Connection conn = null;
	    public static PreparedStatement psmt = null;
	    public static ResultSet rs = null;
	  
	    public ArrayList<DosyaTipi> Liste() throws Exception {

	        DBConnection DB = new DBConnection();
	        DB.newConnectDB();
	        String SQL = "SELECT * FROM tbl_dosya_tipi" ;
	        Statement stmt;
	        ResultSet rs;
	        stmt = DB.conn.createStatement();
	        rs = stmt.executeQuery(SQL);
	        DosyaTipi dosyatipi;
	        ArrayList<DosyaTipi> DosyaTipiListesi = new ArrayList<DosyaTipi>();
	        while (rs.next()) {
	            dosyatipi = new DosyaTipi();
	            dosyatipi.setAdi(rs.getString("adi"));
	            dosyatipi.setId(rs.getInt("id"));
	            dosyatipi.setBorc_tipi_id(rs.getInt("borc_tipi_id"));
	            dosyatipi.setBorca_esas_evrak_id(rs.getInt("borca_esas_evrak_id"));
	            dosyatipi.setTakip_tipi_id(rs.getInt("takip_tipi_id"));
	            dosyatipi.setTakip_yolu_id(rs.getInt("takip_yolu_id"));
	            dosyatipi.setTalep_edilen_hakkinda_id(rs.getInt("talep_edilen_hakkinda_id"));
	            
	            DosyaTipiListesi.add(dosyatipi);
	        }
	       DB.disconnectDB();
	        return DosyaTipiListesi;
	         
	    }

	    public ArrayList<DosyaTipi> Liste(int id) throws Exception {

	        DBConnection DB = new DBConnection();
	        DB.newConnectDB();
	        String SQL = "SELECT * FROM tbl_dosya_tipi where id="+id ;
	        Statement stmt;
	        ResultSet rs;
	        stmt = DB.conn.createStatement();
	        rs = stmt.executeQuery(SQL);
	        DosyaTipi dosyatipi;
	        ArrayList<DosyaTipi> DosyaTipiListesi = new ArrayList<DosyaTipi>();
	        while (rs.next()) {
	            dosyatipi = new DosyaTipi();
	            dosyatipi.setAdi(rs.getString("adi"));
	            dosyatipi.setId(rs.getInt("id"));
	            dosyatipi.setBorc_tipi_id(rs.getInt("borc_tipi_id"));
	            dosyatipi.setBorca_esas_evrak_id(rs.getInt("borca_esas_evrak_id"));
	            dosyatipi.setTakip_tipi_id(rs.getInt("takip_tipi_id"));
	            dosyatipi.setTakip_yolu_id(rs.getInt("takip_yolu_id"));
	            dosyatipi.setTalep_edilen_hakkinda_id(rs.getInt("talep_edilen_hakkinda_id"));
	            
	            DosyaTipiListesi.add(dosyatipi);
	        }
	       DB.disconnectDB();
	        return DosyaTipiListesi;
	         
	    }
	    
	    public boolean Kayit(DosyaTipi Sablon) throws Exception {

	        String SQL = "INSERT INTO  tbl_dosya_tipi (\"adi\") VALUES ('" + Sablon.getAdi() + "')";
	        DBConnection DB = new DBConnection();
	        DB.newConnectDB();
	        Statement stmt = DB.conn.createStatement();
	        boolean st = stmt.execute(SQL);
	        DB.disconnectDB();
	        return st;
	    }


	    public boolean Sil(int id) throws Exception {
	        String SQL = "DELETE FROM tbl_dosya_adi WHERE \"id\"='" + id + "'";
	        DBConnection DB = new DBConnection();
	        DB.newConnectDB();
	        Statement stmt = DB.conn.createStatement();
	        boolean st = stmt.execute(SQL);
	        DB.disconnectDB();
	        return st;
	    }


	    
	    public boolean Duzenle(GenelTanimSablon Sablon, String DBAdi) throws Exception {
	        String SQL = "UPDATE " + DBAdi + " SET \"adi\"='" + Sablon.getAdi() + "' WHERE \"id\"='" + Sablon.getId() + "'";
	        DBConnection DB = new DBConnection();
	        DB.newConnectDB();
	        Statement stmt = DB.conn.createStatement();
	        boolean st = stmt.execute(SQL);
	        DB.disconnectDB();
	        return st;
	    }

	
}
