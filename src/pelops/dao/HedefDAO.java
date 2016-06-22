package pelops.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;

import pelops.db.DBConnection;
import pelops.model.Hedef;

public class HedefDAO extends DBConnection{

	public void Kaydet(Hedef hedef){
		newConnectDB();
		
		String SQL = "INSERT INTO tbl_hedef(personel_id, muvekkil_id, gunluk_hedef, aylik_hedef, ilgili_ay,ilgili_yil, user_id, personel_adi, muvekkil_adi)"
					+" VALUES (?, ?, ?, ?, ?,?, ?,?,?);";
		PreparedStatement pstmt = null;
	 
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, hedef.getPersonel_id());
			pstmt.setInt(2, hedef.getMuvekkil_id());
			pstmt.setDouble(3, hedef.getGunluk_hedef());
			pstmt.setDouble(4, hedef.getAylik_hedef());
			pstmt.setInt(5, hedef.getIlgili_ay());
			pstmt.setInt(6, hedef.getIlgili_yil());
			pstmt.setInt(7, hedef.getUser_id());
			pstmt.setString(8, hedef.getPersonel_adi());
			pstmt.setString(9, hedef.getMuvekkil_adi());
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("Beklenmeyen Hata oluştu....:" + e.getMessage());
		}
		
		try {
			disconnectDB();
		} catch (Exception e) {
			System.out.println("DB Bağlantısı kapatılamadı, ya da hiç açılmamıştı....: "+ e.getMessage());
		}
		
		
	}
	
	public void Duzenle(Hedef hedef){
		
		newConnectDB();
		
		String SQL = "UPDATE tbl_hedef  SET personel_id=?, muvekkil_id=?, gunluk_hedef=?, aylik_hedef=?,"
				+" ilgili_ay=?, ilgili_yil=?, user_id=?, personel_adi=?, muvekkil_adi=? WHERE id="+hedef.getId();
		
		PreparedStatement pstmt = null;
	 
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, hedef.getPersonel_id());
			pstmt.setInt(2, hedef.getMuvekkil_id());
			pstmt.setDouble(3, hedef.getGunluk_hedef());
			pstmt.setDouble(4, hedef.getAylik_hedef());
			pstmt.setInt(5, hedef.getIlgili_ay());
			pstmt.setInt(6, hedef.getIlgili_yil());
			pstmt.setInt(7, hedef.getUser_id());
			pstmt.setString(8, hedef.getPersonel_adi());
			pstmt.setString(9, hedef.getMuvekkil_adi());
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("Beklenmeyen Hata oluştu....:" + e.getMessage());
		}
		
		try {
			disconnectDB();
		} catch (Exception e) {
			System.out.println("DB Bağlantısı kapatılamadı, ya da hiç açılmaımıştı....: "+ e.getMessage());
		}
		
		
	}
	
	public void Sil(int id){
		
		newConnectDB();
		
		String SQL = "DELETE FROM tbl_hedef WHERE id="+id;
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("Beklenmeyen Hata oluştu....:" + e.getMessage());
		}
		
		try {
			disconnectDB();
		} catch (Exception e) {
			System.out.println("DB Bağlantısı kapatılamadı, ya da hiç açılmaımıştı....: "+ e.getMessage());
		}
		
		
		
				
		
	}
	
	public ArrayList<Hedef> Listele(){
		
		newConnectDB();
		
		String SQL = "SELECT id, personel_id, muvekkil_id, gunluk_hedef, aylik_hedef,"
				+" ilgili_ay,ilgili_yil, user_id, ekleme_tarihi, guncelleme_tarihi,personel_adi, muvekkil_adi  FROM tbl_hedef;";
		
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			stm = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = stm.executeQuery(SQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Hedef hedef ;
		ArrayList<Hedef> listHedef = new ArrayList<Hedef>();
		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		try {
			while(rs.next()){
				hedef = new Hedef();
				hedef.setId(rs.getInt("id"));
				hedef.setAylik_hedef(rs.getDouble("aylik_hedef"));
				hedef.setEkleme_tarihi(rs.getDate("ekleme_tarihi"));
				hedef.setGuncelleme_tarihi(rs.getDate("guncelleme_tarihi"));
				hedef.setGunluk_hedef(rs.getDouble("gunluk_hedef"));
				hedef.setIlgili_ay(rs.getInt("ilgili_ay"));
				hedef.setIlgili_yil(rs.getInt("ilgili_yil"));
				hedef.setMuvekkil_id(rs.getInt("muvekkil_id"));
				hedef.setPersonel_id(rs.getInt("personel_id"));
				hedef.setUser_id(rs.getInt("user_id"));
				hedef.setPersonel_adi(rs.getString("personel_adi"));
				hedef.setMuvekkil_adi(rs.getString("muvekkil_adi"));
				hedef.setAylikHedefTL(defaultFormat.format(hedef.getAylik_hedef()));
				hedef.setGunlukHedefTL(defaultFormat.format(hedef.getGunluk_hedef()));
				
				listHedef.add(hedef);				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			disconnectDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listHedef;
	}
	
	
public ArrayList<Hedef> Listele(int yil, int ay){
		
		newConnectDB();
		
		String SQL = "SELECT id, personel_id, muvekkil_id, gunluk_hedef, aylik_hedef,"
				+" ilgili_ay,ilgili_yil, user_id, ekleme_tarihi, guncelleme_tarihi,personel_adi, muvekkil_adi  FROM tbl_hedef"+
				" where ilgili_yil="+yil +" and ilgili_ay="+ay;
		
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			stm = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = stm.executeQuery(SQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Hedef hedef ;
		ArrayList<Hedef> listHedef = new ArrayList<Hedef>();
		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		try {
			while(rs.next()){
				hedef = new Hedef();
				hedef.setId(rs.getInt("id"));
				hedef.setAylik_hedef(rs.getDouble("aylik_hedef"));
				hedef.setEkleme_tarihi(rs.getDate("ekleme_tarihi"));
				hedef.setGuncelleme_tarihi(rs.getDate("guncelleme_tarihi"));
				hedef.setGunluk_hedef(rs.getDouble("gunluk_hedef"));
				hedef.setIlgili_ay(rs.getInt("ilgili_ay"));
				hedef.setIlgili_yil(rs.getInt("ilgili_yil"));
				hedef.setMuvekkil_id(rs.getInt("muvekkil_id"));
				hedef.setPersonel_id(rs.getInt("personel_id"));
				hedef.setUser_id(rs.getInt("user_id"));
				hedef.setPersonel_adi(rs.getString("personel_adi"));
				hedef.setMuvekkil_adi(rs.getString("muvekkil_adi"));
				hedef.setAylikHedefTL(defaultFormat.format(hedef.getAylik_hedef()));
				hedef.setGunlukHedefTL(defaultFormat.format(hedef.getGunluk_hedef()));
				
				listHedef.add(hedef);				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			disconnectDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listHedef;
	}
	
}
