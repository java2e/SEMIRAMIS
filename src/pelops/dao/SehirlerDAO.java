package pelops.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.faces.event.ValueChangeEvent;

import pelops.db.DBConnection;
import pelops.model.Il;
import pelops.model.Ilce;
import semiramis.operasyon.model.ComboItem;

public class SehirlerDAO extends DBConnection {

	public ArrayList<Il> GetListForIl() throws SQLException {

		newConnectDB();
		String SQL = "SELECT * FROM tbl_il ";
		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		Il il;
		ArrayList<Il> IllerListesi = new ArrayList<Il>();

		while (rs.next()) {

			il = new Il();
			il.setId(rs.getInt("id"));
			il.setIl_adi(rs.getString("il_adi"));
			il.setIl_kodu(rs.getInt("il_kodu"));

			IllerListesi.add(il);

		}
		return IllerListesi;
	}
	
	public ArrayList<ComboItem> getListIl() throws SQLException {

		newConnectDB();
		String SQL = "SELECT * FROM tbl_il ";
		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		ComboItem il;
		ArrayList<ComboItem> IllerListesi = new ArrayList<ComboItem>();

		while (rs.next()) {

			il = new ComboItem();
			il.setId(rs.getInt("id"));
			il.setAdi(rs.getString("il_adi"));

			IllerListesi.add(il);

		}
		return IllerListesi;
	}

	public ArrayList<Ilce> GetListForIlce(int il_kodu) throws SQLException {

		newConnectDB();
		String SQL = "SELECT * FROM tbl_ilce where il_id=" + il_kodu;
		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		Ilce ilce;
		ArrayList<Ilce> IlcelerListesi = new ArrayList<Ilce>();

		while (rs.next()) {

			ilce = new Ilce();
			ilce.setId(rs.getInt("id"));
			ilce.setIlce_adi(rs.getString("ilce_adi"));
			IlcelerListesi.add(ilce);

		}
		return IlcelerListesi;

	}
	
	public ArrayList<ComboItem> getListIlce(int il_kodu) throws SQLException {

		newConnectDB();
		String SQL = "SELECT * FROM tbl_ilce where il_id=" + il_kodu;
		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		ComboItem ilce;
		ArrayList<ComboItem> IlcelerListesi = new ArrayList<ComboItem>();

		while (rs.next()) {

			ilce = new ComboItem();
			ilce.setId(rs.getInt("id"));
			ilce.setAdi(rs.getString("ilce_adi"));
			IlcelerListesi.add(ilce);

		}
		return IlcelerListesi;

	}

	public int getIl_KoduFromName(String name) throws Exception {
		int il_k = 0;
		newConnectDB();
		String SQL ="Select * from tbl_il where il_adi= '"+name+"';";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(SQL);
		while(rs.next()){
			il_k = rs.getInt("il_kodu");
		}
		
		return il_k;
	}
	
	public String getIlName(int id)  {
		String il_k = "il";
		try {
			
		
		newConnectDB();
		String SQL ="Select * from tbl_il where id= "+id;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(SQL);
		while(rs.next()){
			il_k = rs.getString("il_adi");
		}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return il_k;
	}

	public ArrayList<Ilce> handleValueChange(ValueChangeEvent event) {

		String secilenIl = (String) event.getNewValue();
		int ilce_kodu = Integer.parseInt(secilenIl);
		ArrayList<Ilce> sonDurum = new ArrayList<Ilce>();

		try {

			sonDurum = GetListForIlce(ilce_kodu);

		} catch (SQLException e) {

			e.printStackTrace();

		}
		return sonDurum;

	}
}
