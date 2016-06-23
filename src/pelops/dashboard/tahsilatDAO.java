package pelops.dashboard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pelops.db.DBConnection;
import pelops.kasa.model.TahsilatViewModel;

public class tahsilatDAO extends DBConnection {

	String SQL;
	ResultSet rs;
	Statement stmt;
	PreparedStatement pstmt;
	SimpleDateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date nowDate = new Date();
	Date gelecekay = new Date();
	
	
	 public ArrayList<TahsilatViewModel> Listele() throws Exception{
		 
		 String SQL ="select * from tbl_tahsilat";
		 newConnectDB();

		ArrayList<TahsilatViewModel> list = new ArrayList<>();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		TahsilatViewModel models ;
		while(rs.next()){
			models = new TahsilatViewModel();
			models.setOdemeMiktari(rs.getDouble("tahsilat_miktari"));
			models.setSoz_alan_personel_id(rs.getInt("soz_alan_personel_id"));
			models.setMuvekkil_adi(rs.getString("muvekkil_adi"));
			list.add(models);
		}
		disconnectDB();
		
		return list;
	 }
	 
	 public ArrayList<TahsilatViewModel> ListeleBugun() throws Exception{
		
			
		 String SQL ="select * from tbl_tahsilat where tahsilat_tarihi='"+fullDateFormat.format(nowDate)+"'";
		 newConnectDB();

		ArrayList<TahsilatViewModel> list = new ArrayList<>();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		TahsilatViewModel models ;
		while(rs.next()){
			models = new TahsilatViewModel();
			models.setOdemeMiktari(rs.getDouble("tahsilat_miktari"));
			models.setSoz_alan_personel_id(rs.getInt("soz_alan_personel_id"));
			models.setMuvekkil_adi(rs.getString("muvekkil_adi"));
			list.add(models);
		}
		disconnectDB();
		
		return list;
	 }
	 
	 	
	 @SuppressWarnings("deprecation")
	public ArrayList<TahsilatViewModel> ListeleBuAy() throws Exception{
		 gelecekay.setMonth(1);
		 
		 String SQL ="select * from tbl_tahsilat where tahsilat_tarihi BETWEEN '"+fullDateFormat.format(nowDate)+"' and '"+fullDateFormat.format(gelecekay)+"'";
		 newConnectDB();

		ArrayList<TahsilatViewModel> list = new ArrayList<>();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		TahsilatViewModel models ;
		while(rs.next()){
			models = new TahsilatViewModel();
			models.setOdemeMiktari(rs.getDouble("tahsilat_miktari"));
			models.setSoz_alan_personel_id(rs.getInt("soz_alan_personel_id"));
			models.setMuvekkil_adi(rs.getString("muvekkil_adi"));
			list.add(models);
		}
		disconnectDB();
		
		return list;
	 }
	 
	 
	 
	
}
