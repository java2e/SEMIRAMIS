package pelops.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import pelops.db.DBConnection;
import pelops.model.User;

public class UserDAO {

	
	    public ArrayList<User> Liste(String DBAdi) throws Exception {

	        DBConnection DB = new DBConnection();
	        DB.newConnectDB();
	        String SQL = "SELECT * FROM " + DBAdi;
	        Statement stmt;
	        ResultSet rs;
	        stmt = DB.conn.createStatement();
	        rs = stmt.executeQuery(SQL);
	        User Sablon;
	        ArrayList<User> SablonListesi = new ArrayList<User>();
	        while (rs.next()) {
	            Sablon = new User();
	            Sablon.setId(rs.getInt("id"));
	            Sablon.setAciklama(rs.getString("aciklama"));
	            Sablon.setAdres(rs.getString("adres"));
	            Sablon.setAdSoyad(rs.getString("adSoyad"));
	            Sablon.setKullaniciAdi(rs.getString("kullaniciAdi"));
	            Sablon.setMail(rs.getString("mail"));
	            Sablon.setRol(rs.getString("rol"));
	            Sablon.setRolId(rs.getInt("rolId"));
	            Sablon.setSifre(rs.getString("sifre"));
	            Sablon.setTelfon(rs.getString("telefon"));
	            
	            SablonListesi.add(Sablon);
	        }
	       
	        DB.disconnectDB();
	        return SablonListesi;
	      
	        
	    }

	    public boolean Kayit(User Sablon, String DBAdi) throws Exception {

	        String SQL = "INSERT INTO " + DBAdi + 
	        		 "(\"rolId\","+
	        		 " \"kullaniciAdi\","+
	        		 " \"adSoyad\","+
	        		 " sifre,"+
	        		 " mail,"+
	        		 " telefon,"+
	        		 " adres,"+
	        		 " rol,"+
	        		 " aciklama)"+
	                 " VALUES ("+Sablon.getRolId()+","+Sablon.getKullaniciAdi()+","+Sablon.getAdSoyad()+","+
	        		 Sablon.getSifre()+","+Sablon.getMail()+","+Sablon.getTelfon()+","+Sablon.getAdres()+","+
	                 Sablon.getRol()+","+Sablon.getAciklama()+")";
	        
	        DBConnection DB = new DBConnection();
	        DB.newConnectDB();
	        Statement stmt = DB.conn.createStatement();
	        boolean st = stmt.execute(SQL);
	        DB.disconnectDB();
	        return st;
	    }


	    public boolean Sil(int id, String DBAdi) throws Exception {
	        String SQL = "DELETE FROM " + DBAdi + " WHERE \"id\"='" + id + "'";
	        DBConnection DB = new DBConnection();
	        DB.newConnectDB();
	        Statement stmt = DB.conn.createStatement();
	        boolean st = stmt.execute(SQL);
	        DB.disconnectDB();
	        return st;
	    }

	
	    
	    public boolean Duzenle(User Sablon, String DBAdi) throws Exception {
	        String SQL = "UPDATE " + DBAdi + " SET"+
	        		"(\"rolId\"="+Sablon.getRolId()+
	        		 " \"kullaniciAdi\"="+Sablon.getKullaniciAdi()+
	        		 " \"adSoyad\"="+Sablon.getAdSoyad()+
	        		 " sifre="+Sablon.getSifre()+
	        		 " mail="+Sablon.getMail()+
	        		 " telefon="+Sablon.getTelfon()+
	        		 " adres="+Sablon.getAdres()+
	        		 " rol="+Sablon.getRol()+
	        		 " aciklama="+Sablon.getAciklama()+
	        		 "' WHERE \"id\"='" + Sablon.getId() + "'";
	        
	        DBConnection DB = new DBConnection();
	        DB.newConnectDB();
	        Statement stmt = DB.conn.createStatement();
	        boolean st = stmt.execute(SQL);
	        DB.disconnectDB();
	        return st;
	    }
	
	    public User getUserinkullaniciAdi(String kullaniciAdi) throws Exception {
	    	
	    		User user=null;
	 	        DBConnection DB = new DBConnection();
	 	        DB.newConnectDB();
	 	        String SQL = "SELECT id, \"rolId\", \"kullaniciAdi\", \"adSoyad\", sifre, mail, telefon, adres, rol, aciklama  FROM tbl_user where upper(\"kullaniciAdi\")='"+kullaniciAdi.toUpperCase()+"'";
	 	        Statement stmt;
	 	        ResultSet rs;
	 	        stmt = DB.conn.createStatement();
	 	        rs = stmt.executeQuery(SQL);
	     
	 	        while (rs.next()) {
	 	        	user = new User();
	 	        	user.setId(rs.getInt("id"));
	 	        	user.setAciklama(rs.getString("aciklama"));
	 	        	user.setAdres(rs.getString("adres"));
	 	        	user.setAdSoyad(rs.getString("adSoyad"));
	 	        	user.setKullaniciAdi(rs.getString("kullaniciAdi"));
	 	        	user.setMail(rs.getString("mail"));
	 	        	user.setRol(rs.getString("rol"));
	 	        	user.setRolId(rs.getInt("rolId"));
	 	        	user.setSifre(rs.getString("sifre"));
	 	        	user.setTelfon(rs.getString("telefon"));
	 	         }
	 	       DB.disconnectDB();
	 	        return user;
	 	         
	 	    }
	    	
	    
}
