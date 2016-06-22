package pelops.dashboard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.db.DBConnection;
import pelops.kasa.model.TahsilatViewModel;

public class tahsilatDAO extends DBConnection {

	String SQL;
	ResultSet rs;
	Statement stmt;
	PreparedStatement pstmt;
	
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
	
}
