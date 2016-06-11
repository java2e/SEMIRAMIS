package pelops.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.db.DBConnection;
import pelops.model.VekaletHarci;
import pelops.model.VekaletSinirlari;

public class VekaletSinirlariDAO  extends DBConnection{

		Statement stmt;
		ResultSet rs;
	public VekaletSinirlari Liste(int yil) throws Exception{
		
		
		String SQL="SELECT id, yil, yuzde_1, sinir_1, yuzde_2, sinir_2, yuzde_3, sinir_3, yuzde_4, sinir_4, yuzde_5, sinir_5, yuzde_6, sinir_6, yuzde_7,"
				+ " sinir_7, yuzde_8, sinir_8, vekalet_ucret, vekalet_ucret_sinir FROM tbl_vekalet_ucret_limitleri WHERE yil="+yil;
		
		newConnectDB();

		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		VekaletSinirlari liste = new VekaletSinirlari();
		while (rs.next()) {
			liste.setId(rs.getInt("id"));
			liste.setSinir_1(rs.getDouble("sinir_1"));
			liste.setSinir_2(rs.getDouble("sinir_2"));
			liste.setSinir_3(rs.getDouble("sinir_3"));
			liste.setSinir_4(rs.getDouble("sinir_4"));
			liste.setSinir_5(rs.getDouble("sinir_5"));
			liste.setSinir_6(rs.getDouble("sinir_6"));
			liste.setSinir_7(rs.getDouble("sinir_7"));
			liste.setSinir_8(rs.getDouble("sinir_8"));
			liste.setYil(rs.getInt("yil"));
			liste.setYuzde_1(rs.getInt("yuzde_1"));
			liste.setYuzde_2(rs.getInt("yuzde_2"));
			liste.setYuzde_3(rs.getInt("yuzde_3"));
			liste.setYuzde_4(rs.getInt("yuzde_4"));
			liste.setYuzde_5(rs.getInt("yuzde_5"));
			liste.setYuzde_6(rs.getInt("yuzde_6"));
			liste.setYuzde_7(rs.getInt("yuzde_7"));
			liste.setYuzde_8(rs.getInt("yuzde_8"));
					
		}
		
		disconnectDB();
				
		
		return liste;
	}
	
	
public ArrayList<VekaletSinirlari> Liste() throws Exception{
	
		String SQL="SELECT  * FROM tbl_vekalet_ucret_limitleri;";
			
			newConnectDB();

			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			VekaletSinirlari liste;
			ArrayList<VekaletSinirlari> basvuruHarciListe = new ArrayList<>();
			while (rs.next()) {
				liste = new VekaletSinirlari();
				liste.setId(rs.getInt("id"));
				liste.setSinir_1(rs.getDouble("sinir_1"));
				liste.setSinir_2(rs.getDouble("sinir_2"));
				liste.setSinir_3(rs.getDouble("sinir_3"));
				liste.setSinir_4(rs.getDouble("sinir_4"));
				liste.setSinir_5(rs.getDouble("sinir_5"));
				liste.setSinir_6(rs.getDouble("sinir_6"));
				liste.setSinir_7(rs.getDouble("sinir_7"));
				liste.setSinir_8(rs.getDouble("sinir_8"));
				liste.setYil(rs.getInt("yil"));
				liste.setYuzde_1(rs.getInt("yuzde_1"));
				liste.setYuzde_2(rs.getInt("yuzde_2"));
				liste.setYuzde_3(rs.getInt("yuzde_3"));
				liste.setYuzde_4(rs.getInt("yuzde_4"));
				liste.setYuzde_5(rs.getInt("yuzde_5"));
				liste.setYuzde_6(rs.getInt("yuzde_6"));
				liste.setYuzde_7(rs.getInt("yuzde_7"));
				liste.setYuzde_8(rs.getInt("yuzde_8"));
				
				basvuruHarciListe.add(liste);
			}
			
			disconnectDB();
			return basvuruHarciListe;
		}
	
	public boolean Kaydet(VekaletSinirlari liste) throws Exception{
		
		
		String SQL = "INSERT INTO tbl_vekalet_ucret_limitleri("
				+ "  yil, yuzde_1, sinir_1, yuzde_2, sinir_2, yuzde_3, sinir_3,"
				+ " yuzde_4, sinir_4, yuzde_5, sinir_5, yuzde_6, sinir_6, yuzde_7,"
				+ " sinir_7, yuzde_8, sinir_8, vekalet_ucret, vekalet_ucret_sinir)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		newConnectDB();
		PreparedStatement ps = conn.prepareStatement(SQL);
		
		ps.setInt(1, liste.getYil());
		ps.setInt(2, liste.getYuzde_1());
		ps.setDouble(3, liste.getSinir_1());
		ps.setInt(4, liste.getYuzde_2());
		ps.setDouble(5, liste.getSinir_2());
		ps.setInt(6, liste.getYuzde_3());
		ps.setDouble(7, liste.getSinir_3());
		ps.setInt(8, liste.getYuzde_4());
		ps.setDouble(9, liste.getSinir_4());
		ps.setInt(10, liste.getYuzde_5());
		ps.setDouble(11, liste.getSinir_5());
		ps.setInt(12, liste.getYuzde_6());
		ps.setDouble(13, liste.getSinir_6());
		ps.setInt(14, liste.getYuzde_7());
		ps.setDouble(15, liste.getSinir_7());
		ps.setInt(16, liste.getYuzde_8());
		ps.setDouble(17, liste.getSinir_8());
		ps.setDouble(18, liste.getVekalet_ucret());
		ps.setDouble(19, liste.getVekalet_ucret_sinir());
		
		ps.executeUpdate();
		
		disconnectDB();
		
		return true;
		
		
	}
	
	
	public boolean  Duzenle(VekaletSinirlari liste) throws Exception{
		
		String SQL ="UPDATE tbl_vekalet_ucret_limitleri"
				+ " SET yil=?, yuzde_1=?, sinir_1=?, yuzde_2=?, sinir_2=?, yuzde_3=?,"
				+ " sinir_3=?, yuzde_4=?, sinir_4=?, yuzde_5=?, sinir_5=?, yuzde_6=?,"
				+ " sinir_6=?, yuzde_7=?, sinir_7=?, yuzde_8=?, sinir_8=?, vekalet_ucret=?, vekalet_ucret_sinir=? WHERE id="+liste.getId();
		
		newConnectDB();
		PreparedStatement ps = conn.prepareStatement(SQL);
		
		ps.setInt(1, liste.getYil());
		ps.setInt(2, liste.getYuzde_1());
		ps.setDouble(3, liste.getSinir_1());
		ps.setInt(4, liste.getYuzde_2());
		ps.setDouble(5, liste.getSinir_2());
		ps.setInt(6, liste.getYuzde_3());
		ps.setDouble(7, liste.getSinir_3());
		ps.setInt(8, liste.getYuzde_4());
		ps.setDouble(9, liste.getSinir_4());
		ps.setInt(10, liste.getYuzde_5());
		ps.setDouble(11, liste.getSinir_5());
		ps.setInt(12, liste.getYuzde_6());
		ps.setDouble(13, liste.getSinir_6());
		ps.setInt(14, liste.getYuzde_7());
		ps.setDouble(15, liste.getSinir_7());
		ps.setInt(16, liste.getYuzde_8());
		ps.setDouble(17, liste.getSinir_8());
		ps.setDouble(18, liste.getVekalet_ucret());
		ps.setDouble(19, liste.getVekalet_ucret_sinir());
		
		ps.executeUpdate();
		
		disconnectDB();
		return true;
		
	}
	
	public void Sil(int id) throws Exception{
		String SQL ="DELETE FROM tbl_vekalet_ucret_limitleri WHERE id="+id;
		newConnectDB();
		PreparedStatement ps = conn.prepareStatement(SQL);
		ps.executeUpdate();
		disconnectDB();
		
	}
	
}
