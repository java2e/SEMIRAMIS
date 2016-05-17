package pelops.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.db.DBConnection;
import pelops.model.AdresTuru;
import pelops.model.AlacakliBilgiler;

public class AdresTuruDAO extends DBConnection{

	ArrayList<AlacakliBilgiler> alacakliListesi = new ArrayList<AlacakliBilgiler>();
	String SQL;
	ResultSet rs;
	Statement stmt;
	PreparedStatement pstmt;
	
	public void Kaydet(AdresTuru adresTuru){
		
		String SQL = "INSERT INTO tbl_adres_turu(adi, uyap_kodu) VALUES(?,?)";
		
	 try {
		
		pstmt = conn.prepareStatement(SQL.toString());	
		pstmt.setString(1, adresTuru.getAdi());
		pstmt.setString(2, adresTuru.getUyap_kodu());
		pstmt.executeUpdate();
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
	
	public int AdresIdGetir(String Uyap_Kodu){
		
		String SQL = "SELECT id, uyap_kodu from tbl_adres_turu where uyap_kodu='"+Uyap_Kodu+"'";
		
		int BorcluId=0;
		try {
			newConnectDB();
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			
			while (rs.next()) {
				
				BorcluId = rs.getInt("id");
				
			}

			disconnectDB();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return BorcluId;
		
		
	}
	
	
public ArrayList<AdresTuru> AdresListesi(){
		
		String SQL = "SELECT id, uyap_kodu,adi from tbl_adres_turu ";
		ArrayList<AdresTuru> adrlist = new ArrayList<AdresTuru>();
		
		try {
			newConnectDB();
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			AdresTuru ad ;
			
			while (rs.next()) {
				ad = new AdresTuru();
				ad.setId(rs.getInt("id"));
				ad.setAdi(rs.getString("adi"));
				ad.setUyap_kodu(rs.getString("uyap_kodu"));
				adrlist.add(ad);
			}

			disconnectDB();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return adrlist;
		
		
	}
	
//	public static void main(String[] args) {
//		AdresTuruDAO adres = new AdresTuruDAO();
//		System.out.println(adres.AdresListesi());
//	}
//	
	
}
