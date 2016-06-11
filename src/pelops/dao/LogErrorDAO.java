package pelops.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.db.DBConnection;
import pelops.model.LogError;

public class LogErrorDAO extends DBConnection{

	
	public ArrayList<LogError> HataListesi(Integer personelID, Date tarih, String page){
		
		newConnectDB();
		String SQL = "SELECT id, user_id, page, hata_value, hata_detay, tarih, value_one,value_two, sayisal FROM tbl_log_error where 1=1 ";
		String fullSQL=SQL;
		
		if(personelID!=null){
			fullSQL += " user_id="+personelID;			
		}
		if(tarih!=null){
			fullSQL += " tarih="+tarih;
		}
		if(page!=null){
			fullSQL += " page="+page;
			
		}
		
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			stm = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = stm.executeQuery(fullSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LogError log;
		ArrayList<LogError> returnLog = new ArrayList<LogError>();
		
		try {
			while(rs.next()){
				log = new LogError();
				log.setHata_detay(rs.getString("hata_detay"));
				log.setHata_value(rs.getString("hata_value"));
				log.setId(rs.getInt("id"));
				log.setPage(rs.getString("page"));
				log.setSayisal(rs.getDouble("sayisal"));
				log.setTarih(rs.getDate("tarih"));
				log.setUser_id(rs.getInt("user_id"));
				log.setValue_one(rs.getString("value_one"));
				log.setValue_two(rs.getString("value_two"));
				returnLog.add(log);	
				
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
		
		return returnLog;
	}// HATALİSTESİ PROSEDÜR SONU 
	
	public void Kaydet(LogError log){
		
		newConnectDB();
		
		String SQL = "INSERT INTO tbl_log_error(user_id, page, hata_value, hata_detay, tarih, value_one,value_two, sayisal)"
					+" VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement pstmt = null;
		try {
			 pstmt = conn.prepareStatement(SQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			pstmt.setInt(1, log.getUser_id());
			pstmt.setString(2, log.getPage());
			pstmt.setString(3, log.getHata_value());
			pstmt.setString(4, log.getHata_detay());
			pstmt.setDate(5, log.getTarih());
			pstmt.setString(6, log.getValue_one());
			pstmt.setString(7, log.getValue_two());
			pstmt.setDouble(8, log.getSayisal());
			
			pstmt.executeUpdate();
			
			
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
		
	}// KAYDET PROSEDÜRÜNÜN SONU
	
	public void Duzenle(LogError log){
		
		String SQL = "UPDATE tbl_log_error  SET user_id=?, page=?, hata_value=?, hata_detay=?, tarih=?,value_one=?, value_two=?, sayisal=? WHERE id="+log.getId()+";";
		PreparedStatement pstmt = null;
		try {
			 pstmt = conn.prepareStatement(SQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			pstmt.setInt(1, log.getUser_id());
			pstmt.setString(2, log.getPage());
			pstmt.setString(3, log.getHata_value());
			pstmt.setString(4, log.getHata_detay());
			pstmt.setDate(5, log.getTarih());
			pstmt.setString(6, log.getValue_one());
			pstmt.setString(7, log.getValue_two());
			pstmt.setDouble(8, log.getSayisal());
			
			pstmt.executeUpdate();
			
			
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
		
	}
	
}
