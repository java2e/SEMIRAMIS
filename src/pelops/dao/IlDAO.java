package pelops.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.controller.AktifBean;
import pelops.db.DBConnection;
import pelops.model.HarcBilgisi;
import pelops.model.Il;


public class IlDAO extends DBConnection{

	private String SQL;
	private PreparedStatement pstmt;
	private Statement stm;
	private ResultSet rs;
	  
	    public ArrayList<Il> Liste() throws Exception {

	        
	        newConnectDB();
	        String SQL = "SELECT * FROM tbl_il";
	        Statement stmt;
	        ResultSet rs;
	        stmt = conn.createStatement();
	        rs = stmt.executeQuery(SQL);
	        Il Sablon;
	        ArrayList<Il> SablonListesi = new ArrayList<Il>();
	        while (rs.next()) {
	            Sablon = new Il();
	            Sablon.setIl_adi(rs.getString("il_adi"));
	            Sablon.setId(rs.getInt("id"));
	            Sablon.setIl_kodu(rs.getInt("il_kodu"));
	           
	            SablonListesi.add(Sablon);
	        }
	       disconnectDB();
	        return SablonListesi;
	      
	        
	    }


	    public Il Liste(int id) throws Exception {

	       
	        newConnectDB();
	        String SQL = "SELECT * FROM tbl_il where id="+id;
	        Statement stmt;
	        ResultSet rs;
	        stmt = conn.createStatement();
	        rs = stmt.executeQuery(SQL);
	        Il Sablon = null;
	        
	        while (rs.next()) {
	            Sablon = new Il();
	            Sablon.setIl_adi(rs.getString("il_adi"));
	            Sablon.setId(rs.getInt("id"));
	            Sablon.setIl_kodu(rs.getInt("il_kodu"));
	            
	        }
	      disconnectDB();
	        return Sablon;
	           
	    }

	    
	    public boolean Kayit(Il Sablon) throws Exception {
	    	boolean kaydedildi = false;
	        String SQL = "INSERT INTO tbl_il(il_adi, il_kodu)  VALUES (?, ?)";
	        
	        
	        newConnectDB();
	        PreparedStatement pstmt = conn.prepareStatement(SQL.toString());
        
	        pstmt.setString(1, Sablon.getIl_adi());
	        pstmt.setInt(2, Sablon.getIl_kodu());
			if(pstmt.executeUpdate()==1)
			{
				kaydedildi=true;
			}
	      
	       disconnectDB();
	        return kaydedildi;
	    }


	    public boolean Sil(int id) throws Exception {
	        String SQL = "DELETE FROM tbl_il WHERE \"id\"='" + id + "'";
	       
	        newConnectDB();
	        Statement stmt = conn.createStatement();
	        boolean st = stmt.execute(SQL);
	        disconnectDB();
	        return st;
	    }


	    
	    public boolean Duzenle(Il Sablon) throws Exception {
	    	boolean st=false;
	        String SQL = "UPDATE tbl_il   SET  il_adi=?, il_kodu=? WHERE id=?";
	        
	       
	        newConnectDB();
	        PreparedStatement pstmt = conn.prepareStatement(SQL);
	        pstmt.setString(1, Sablon.getIl_adi());
	        pstmt.setInt(2, Sablon.getIl_kodu());
	        pstmt.setInt(3, Sablon.getId());
	        
	        if(   pstmt.executeUpdate()==1){
	        	st=true;
	        	
	        }
	        
	       disconnectDB();
	        return st;
	    }


		public boolean Guncelle(Il secimIl) throws Exception {

				boolean duzenlendi = false;

				String SQL = "UPDATE tbl_il SET il_kodu=?, il_adi=? WHERE id=" + secimIl.getId()+ ";";

				newConnectDB();

				pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, secimIl.getIl_kodu());
				pstmt.setString(2,secimIl.getIl_adi());

				int sonuc = pstmt.executeUpdate();
				disconnectDB();
				if (sonuc == 1) {
					duzenlendi = true;
				}

				return duzenlendi;
			}
		
	    
}
