package pelops.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.db.DBConnection;
import pelops.model.IcraMudurlugu;

public class IcraMudurluguDAO {

	
	 public static Connection conn = null;
	    public static PreparedStatement psmt = null;
	    public static ResultSet rs = null;
	  
	    public ArrayList<IcraMudurlugu> Liste() throws Exception {

	        DBConnection DB = new DBConnection();
	        DB.newConnectDB();
	        String SQL = "SELECT * FROM tbl_icra_mudurlugu";
	        Statement stmt;
	        ResultSet rs;
	        stmt = DB.conn.createStatement();
	        rs = stmt.executeQuery(SQL);
	        IcraMudurlugu Sablon;
	        ArrayList<IcraMudurlugu> SablonListesi = new ArrayList<IcraMudurlugu>();
	        while (rs.next()) {
	            Sablon = new IcraMudurlugu();
	            Sablon.setAdi(rs.getString("adi"));
	            Sablon.setId(rs.getInt("id"));
	            SablonListesi.add(Sablon);
	        }
	       DB.disconnectDB();
	        return SablonListesi;
	      
	        
	    }


	    public ArrayList<IcraMudurlugu> Liste(int id) throws Exception {

	        DBConnection DB = new DBConnection();
	        DB.newConnectDB();
	        String SQL = "SELECT * FROM tbl_icra_mudurlugu where id="+id;
	        Statement stmt;
	        ResultSet rs;
	        stmt = DB.conn.createStatement();
	        rs = stmt.executeQuery(SQL);
	        IcraMudurlugu Sablon;
	        ArrayList<IcraMudurlugu> SablonListesi = new ArrayList<IcraMudurlugu>();
	        while (rs.next()) {
	            Sablon = new IcraMudurlugu();
	            Sablon.setAdi(rs.getString("adi"));
	            Sablon.setId(rs.getInt("id"));
	            SablonListesi.add(Sablon);
	        }
	       DB.disconnectDB();
	        return SablonListesi;
	      
	        
	    }

	    
	    public boolean Kayit(IcraMudurlugu Sablon) throws Exception {

	        String SQL = "INSERT INTO tbl_icra_mudurlugu (\"adi\") VALUES ('" + Sablon.getAdi() + "')";
	        DBConnection DB = new DBConnection();
	        DB.newConnectDB();
	        Statement stmt = DB.conn.createStatement();
	        boolean st = stmt.execute(SQL);
	        DB.disconnectDB();
	        return st;
	    }


	    public boolean Sil(int id) throws Exception {
	        String SQL = "DELETE FROM tbl_icra_mudurlugu WHERE \"id\"='" + id + "'";
	        DBConnection DB = new DBConnection();
	        DB.newConnectDB();
	        Statement stmt = DB.conn.createStatement();
	        boolean st = stmt.execute(SQL);
	        DB.disconnectDB();
	        return st;
	    }


	    
	    public boolean Duzenle(IcraMudurlugu Sablon) throws Exception {
	        String SQL = "UPDATE tbl_icra_mudurlugu SET \"adi\"='" + Sablon.getAdi() + "' WHERE \"id\"='" + Sablon.getId() + "'";
	        DBConnection DB = new DBConnection();
	        DB.newConnectDB();
	        Statement stmt = DB.conn.createStatement();
	        boolean st = stmt.execute(SQL);
	        DB.disconnectDB();
	        return st;
	    }

}
