package pelops.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import pelops.db.DBConnection;
import pelops.model.BasvuruHarci;

public class BasvuruHarciDAO extends DBConnection{

	Statement stmt;
	ResultSet rs;
	
	public BasvuruHarci liste(int yil) throws Exception{
		
	String SQL="SELECT id, yil, tutar, konusu FROM tbl_basvuru_harci;";
		
		newConnectDB();

		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		BasvuruHarci liste = new BasvuruHarci();
		while (rs.next()) {
			liste.setId(rs.getInt("id"));
			liste.setTutar(rs.getDouble("tutar"));
			liste.setYil(rs.getInt("yil"));
			
		}
		
		disconnectDB();
		return liste;
	}
	
	public void Kaydet(BasvuruHarci liste) throws Exception{
		String SQL="INSERT INTO tbl_basvuru_harci(yil, tutar)  VALUES ( ?, ?);";
		newConnectDB();
		PreparedStatement ps = conn.prepareStatement(SQL);
		
		ps.setInt(1, liste.getYil());
		ps.setDouble(2, liste.getTutar());
		ps.executeUpdate();
		disconnectDB();
		
	}
	
	public void Duzenle(BasvuruHarci liste) throws Exception{
		String SQL = "UPDATE tbl_basvuru_harci SET  yil=?, tutar=? WHERE id="+liste.getId();
		newConnectDB();
		PreparedStatement ps = conn.prepareStatement(SQL);
		
		ps.setInt(1, liste.getYil());
		ps.setDouble(2, liste.getTutar());
		ps.executeUpdate();
		disconnectDB();
		
	}
	
	public void Sil(int id) throws Exception
	{
		String SQL ="DELETE FROM tbl_basvuru_harci WHERE id="+id;
		newConnectDB();
		PreparedStatement ps = conn.prepareStatement(SQL);
		ps.executeUpdate();
		disconnectDB();
	}
}
