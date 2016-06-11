package pelops.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.db.DBConnection;
import pelops.model.Adres;
import pelops.model.AdresStatusu;
import pelops.model.Il;

public class AdresStatuDAO extends DBConnection {
	private String SQL;
	private PreparedStatement pstmt;
	private Statement stm;
	private ResultSet rs;
	
	public ArrayList<AdresStatusu> Liste() throws Exception{
		newConnectDB();
        String SQL = "SELECT * FROM tbl_adres_statusu";
        Statement stmt;
        ResultSet rs;
        stmt = conn.createStatement();
        rs = stmt.executeQuery(SQL);
        AdresStatusu Sablon;
        ArrayList<AdresStatusu> SablonListesi = new ArrayList<AdresStatusu>();
        while (rs.next()) {
            Sablon = new AdresStatusu();
            Sablon.setId(rs.getInt("id"));
            Sablon.setStatu(rs.getString("adi"));
            SablonListesi.add(Sablon);
        }
       disconnectDB();
        return SablonListesi;
		
	}
	
    public AdresStatusu Liste(int id) throws Exception {

	       
        newConnectDB();
        String SQL = "SELECT * FROM tbl_adres_statusu where id="+id;
        Statement stmt;
        ResultSet rs;
        stmt = conn.createStatement();
        rs = stmt.executeQuery(SQL);
        AdresStatusu Sablon = null;
        
        while (rs.next()) {
            Sablon = new AdresStatusu();
            Sablon.setId(rs.getInt("id"));
            Sablon.setStatu(rs.getString("adi"));
        }
      disconnectDB();
        return Sablon;
           
    }
    
    public boolean Kayit(AdresStatusu Sablon) throws Exception {
    	boolean kaydedildi = false;
        String SQL = "INSERT INTO tbl_adres_statusu(adi)  VALUES ( ?)";
        
        
        newConnectDB();
        PreparedStatement pstmt = conn.prepareStatement(SQL.toString());
    
        pstmt.setString(1, Sablon.getStatu());
		if(pstmt.executeUpdate()==1)
		{
			kaydedildi=true;
		}
      
       disconnectDB();
        return kaydedildi;
    }
    
    
    
    public boolean Sil(int id) throws Exception {
        String SQL = "DELETE FROM tbl_adres_statusu WHERE \"id\"='" + id + "'";
       
        newConnectDB();
        Statement stmt = conn.createStatement();
        boolean st = stmt.execute(SQL);
        disconnectDB();
        return st;
    }


    
    public boolean Duzenle(AdresStatusu Sablon) throws Exception {
    	boolean st=false;
        String SQL = "UPDATE tbl_adres_statusu   SET  adi=? WHERE id=?";
        
       
        newConnectDB();
        PreparedStatement pstmt = conn.prepareStatement(SQL);
        pstmt.setString(1, Sablon.getStatu());
        pstmt.setInt(2, Sablon.getId());
        
        if(   pstmt.executeUpdate()==1){
        	st=true;
        	
        }
        
       disconnectDB();
        return st;
    }


	public boolean Guncelle(AdresStatusu secimAdresStatu) throws Exception {

			boolean duzenlendi = false;

			String SQL = "UPDATE tbl_adres_statusu SET adi=? WHERE id=" +secimAdresStatu.getId()+ ";";

			newConnectDB();

			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,secimAdresStatu.getStatu());

			int sonuc = pstmt.executeUpdate();
			disconnectDB();
			if (sonuc == 1) {
				duzenlendi = true;
			}

			return duzenlendi;
		}
    
    
}
