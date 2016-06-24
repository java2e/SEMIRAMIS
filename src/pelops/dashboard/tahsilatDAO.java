package pelops.dashboard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import pelops.db.DBConnection;
import pelops.kasa.model.TahsilatViewModel;

public class tahsilatDAO extends DBConnection {

	String SQL;
	ResultSet rs;
	Statement stmt;
	PreparedStatement pstmt;
	private SimpleDateFormat yearsFormat = new SimpleDateFormat("yyyy");
	private SimpleDateFormat mountsFormat = new SimpleDateFormat("MM");
	private SimpleDateFormat fullDateFormat = new SimpleDateFormat("dd-MM-yyyy");
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
		 Calendar c = Calendar.getInstance();
		 int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		String aybasi= yearsFormat.format(nowDate)+"-"+mountsFormat.format(nowDate)+"-"+"1",aysonu=yearsFormat.format(nowDate)+"-"+mountsFormat.format(nowDate)+"-"+monthMaxDays;
		 
		 String SQL ="select * from tbl_tahsilat where tahsilat_tarihi BETWEEN '"+aybasi+"' and '"+aysonu+"'";
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
