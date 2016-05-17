package pelops.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.db.DBConnection;
import pelops.model.VekaletTipi;

public class VekaletTipiDAO {


	 public static Connection conn = null;
	    public static PreparedStatement psmt = null;
	    public static ResultSet rs = null;
	  
	    public ArrayList<VekaletTipi> Liste() throws Exception {

	        DBConnection DB = new DBConnection();
	        DB.newConnectDB();
	        String SQL = "SELECT * FROM tbl_vekalet_ucret_tipi";
	        Statement stmt;
	        ResultSet rs;
	        stmt = DB.conn.createStatement();
	        rs = stmt.executeQuery(SQL);
	        VekaletTipi Sablon;
	        ArrayList<VekaletTipi> SablonListesi = new ArrayList<VekaletTipi>();
	        while (rs.next()) {
	            Sablon = new VekaletTipi();
	            Sablon.setAdi(rs.getString("adi"));
	            Sablon.setId(rs.getInt("id"));
	            SablonListesi.add(Sablon);
	        }
	       DB.disconnectDB();
	        return SablonListesi;
	      
	        
	    }


	    public ArrayList<VekaletTipi> Liste(int id) throws Exception {

	        DBConnection DB = new DBConnection();
	        DB.newConnectDB();
	        String SQL = "SELECT * FROM tbl_vekalet_ucret_tipi where id="+id;
	        Statement stmt;
	        ResultSet rs;
	        stmt = DB.conn.createStatement();
	        rs = stmt.executeQuery(SQL);
	        VekaletTipi Sablon;
	        ArrayList<VekaletTipi> SablonListesi = new ArrayList<VekaletTipi>();
	        while (rs.next()) {
	            Sablon = new VekaletTipi();
	            Sablon.setAdi(rs.getString("adi"));
	            Sablon.setId(rs.getInt("id"));
	            SablonListesi.add(Sablon);
	        }
	       DB.disconnectDB();
	        return SablonListesi;
	      
	        
	    }

	    
	    public boolean Kayit(VekaletTipi Sablon) throws Exception {

	        String SQL = "INSERT INTO tbl_vekalet_ucret_tipi (\"adi\") VALUES ('" + Sablon.getAdi() + "')";
	        DBConnection DB = new DBConnection();
	        DB.newConnectDB();
	        Statement stmt = DB.conn.createStatement();
	        boolean st = stmt.execute(SQL);
	        DB.disconnectDB();
	        return st;
	    }


	    public boolean Sil(int id) throws Exception {
	        String SQL = "DELETE FROM tbl_vekalet_ucret_tipi WHERE \"id\"='" + id + "'";
	        DBConnection DB = new DBConnection();
	        DB.newConnectDB();
	        Statement stmt = DB.conn.createStatement();
	        boolean st = stmt.execute(SQL);
	        DB.disconnectDB();
	        return st;
	    }


	    
	    public boolean Duzenle(VekaletTipi Sablon) throws Exception {
	        String SQL = "UPDATE tbl_vekalet_ucret_tipi SET \"adi\"='" + Sablon.getAdi() + "' WHERE \"id\"='" + Sablon.getId() + "'";
	        DBConnection DB = new DBConnection();
	        DB.newConnectDB();
	        Statement stmt = DB.conn.createStatement();
	        boolean st = stmt.execute(SQL);
	        DB.disconnectDB();
	        return st;
	    }

}
