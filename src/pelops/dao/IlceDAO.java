package pelops.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import pelops.db.DBConnection;
import pelops.model.Il;
import pelops.model.Ilce;

public class IlceDAO extends DBConnection {
	
	private String SQL;
	private PreparedStatement pstmt;
	private Statement stm;
	private ResultSet rs;
	public ArrayList<Ilce> Liste() throws Exception{
		 newConnectDB();
	        String SQL = "SELECT * FROM tbl_ilce";
	        Statement stmt;
	        ResultSet rs;
	        stmt = conn.createStatement();
	        rs = stmt.executeQuery(SQL);
	        Ilce Sablon;
		ArrayList<Ilce> sablonListe = new ArrayList<Ilce>();
		while(rs.next()){
			Sablon = new Ilce();
			Sablon.setIl_id(rs.getInt("il_id"));
			Sablon.setId(rs.getInt("id"));
			Sablon.setIlce_adi(rs.getString("ilce_adi"));
			
			sablonListe.add(Sablon);
			}
		disconnectDB();
		return sablonListe;
		
	}
	
	public ArrayList<Ilce> Liste(int id) throws Exception{
		 newConnectDB();
	        String SQL = "SELECT * FROM tbl_ilce where id="+id;
	        Statement stmt;
	        ResultSet rs;
	        stmt = conn.createStatement();
	        rs = stmt.executeQuery(SQL);
	        Ilce Sablon;
		ArrayList<Ilce> sablonListe = new ArrayList<Ilce>();
		while(rs.next()){
			Sablon = new Ilce();
			Sablon.setIl_id(rs.getInt("il_id"));
			Sablon.setId(rs.getInt("id"));
			Sablon.setIlce_adi(rs.getString("ilce_adi"));
			sablonListe.add(Sablon);
			}
		disconnectDB();
		return sablonListe;
		
	}
	
	   public boolean Kayit(Ilce Sablon) throws Exception {
	    	boolean kaydedildi = false;
	        String SQL = "INSERT INTO tbl_ilce(ilce_adi, il_id)  VALUES (?, ?)";
	        
	        
	        newConnectDB();
	        PreparedStatement pstmt = conn.prepareStatement(SQL.toString());
       
	        pstmt.setString(1, Sablon.getIlce_adi());
	        pstmt.setInt(2, Sablon.getIl_id());
			if(pstmt.executeUpdate()==1)
			{
				kaydedildi=true;
			}
	      
	       disconnectDB();
	        return kaydedildi;
	    }


	    public boolean Sil(int id) throws Exception {
	        String SQL = "DELETE FROM tbl_ilce WHERE \"id\"='" + id + "'";
	       
	        newConnectDB();
	        Statement stmt = conn.createStatement();
	        boolean st = stmt.execute(SQL);
	        disconnectDB();
	        return st;
	    }


	    
	    public boolean Duzenle(Ilce Sablon) throws Exception {
	    	boolean st=false;
	        String SQL = "UPDATE tbl_ilce   SET  ilce_adi=?, il_id=? WHERE id=?";
	        
	       
	        newConnectDB();
	        PreparedStatement pstmt = conn.prepareStatement(SQL);
	        pstmt.setString(1, Sablon.getIlce_adi());
	        pstmt.setInt(2, Sablon.getIl_id());
	        pstmt.setInt(3, Sablon.getId());
	        
	        if(   pstmt.executeUpdate()==1){
	        	st=true;
	        	
	        }
	        
	       disconnectDB();
	        return st;
	    }

		public boolean Guncelle(Ilce secimIlce) throws Exception {

			boolean duzenlendi = false;

			String SQL = "UPDATE tbl_ilce  SET ilce_kodu=?, ilce_adi=? WHERE id=" + secimIlce.getId()+ ";";

			newConnectDB();

			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, secimIlce.getIl_id());
			pstmt.setString(2 ,secimIlce.getIlce_adi());

			int sonuc = pstmt.executeUpdate();
			disconnectDB();
			if (sonuc == 1) {
				duzenlendi = true;
			}

			return duzenlendi;
		}
	
	
}
