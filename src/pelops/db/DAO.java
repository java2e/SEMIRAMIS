package pelops.db;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pelops.model.GenelTanimSablon;
import pelops.model.Il;
import pelops.model.Ilce;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DAO extends DBConnection {

    public static Connection conn = null;
    public static PreparedStatement psmt = null;
    public static ResultSet rs = null;
  

//**********************************************************************************************************************************
//-------------------------------- TANIMLAMA VERï¿½ TABANI ï¿½ï¿½LEMLERï¿½  ---------------------------------------------------------------- 
//**********************************************************************************************************************************
//**************************************************************************************
//------------------   GENEL TANIM Lï¿½STE ï¿½EKME ï¿½ï¿½LEMï¿½  ---------------------------------
//**************************************************************************************
    public ArrayList<GenelTanimSablon> Liste(String DBAdi) throws SQLException {

       
        newConnectDB();
        String SQL = "SELECT * FROM " + DBAdi;
        Statement stmt;
        ResultSet rs;
        stmt = conn.createStatement();
        rs = stmt.executeQuery(SQL);
        GenelTanimSablon Sablon;
        ArrayList<GenelTanimSablon> SablonListesi = new ArrayList<GenelTanimSablon>();
        while (rs.next()) {
            Sablon = new GenelTanimSablon();
            Sablon.setAdi(rs.getString("adi"));
            Sablon.setId(rs.getInt("id"));
            SablonListesi.add(Sablon);
        }
        return SablonListesi;
    }

//*********************************************************************************
//---------------------------------- S O N ----------------------------------------
//*********************************************************************************
//*********************************************************************************
//------------------   GENEL TANIM KAYIT ï¿½ï¿½LEMï¿½  ----------------------------------
//*********************************************************************************
    public boolean Kayit(GenelTanimSablon Sablon, String DBAdi) throws SQLException {

        String SQL = "INSERT INTO " + DBAdi + "(\"adi\") VALUES ('" + Sablon.getAdi() + "')";
      
        newConnectDB();
        Statement stmt = conn.createStatement();
        boolean st = stmt.execute(SQL);
        return st;
    }

//*********************************************************************************
//---------------------------------- S O N ----------------------------------------
//*********************************************************************************
//*********************************************************************************
//------------------   GENEL TANIM Sï¿½LME ï¿½ï¿½LEMï¿½  ----------------------------------
//*********************************************************************************
    public boolean Sil(int id, String DBAdi) throws SQLException {
        String SQL = "DELETE FROM " + DBAdi + " WHERE \"id\"='" + id + "'";
        
        newConnectDB();
        Statement stmt = conn.createStatement();
        boolean st = stmt.execute(SQL);
        return st;
    }

//*********************************************************************************
//---------------------------------- S O N ----------------------------------------
//*********************************************************************************
//*********************************************************************************
//------------------   GENEL TANIM Dï¿½ZENLEME ï¿½ï¿½LEMï¿½  ----------------------------------
//*********************************************************************************
    
    
    public boolean Duzenle(GenelTanimSablon Sablon, String DBAdi) throws SQLException {
        String SQL = "UPDATE " + DBAdi + " SET \"adi\"='" + Sablon.getAdi() + "' WHERE \"id\"='" + Sablon.getId() + "'";
        
        newConnectDB();
        Statement stmt = conn.createStatement();
        boolean st = stmt.execute(SQL);
        return st;
    }

//*********************************************************************************
//---------------------------------- S O N ----------------------------------------
//*********************************************************************************
//------------------   ï¿½L Lï¿½STE ï¿½EKME ï¿½ï¿½LEMï¿½  ---------------------------------
//**************************************************************************************
    public ArrayList<Il> IlListe() throws SQLException {

       
        newConnectDB();
        String SQL = "SELECT * FROM tbl_il";
        Statement stmt;
        ResultSet rs;
        stmt = conn.createStatement();
        rs = stmt.executeQuery(SQL);
        Il il;
        ArrayList<Il> IlListesi = new ArrayList<Il>();
        while (rs.next()) {
            il = new Il();
            il.setId(rs.getInt("id"));
            il.setIl_adi(rs.getString("il_adi"));
            il.setIl_kodu(rs.getInt("il_kodu"));
            IlListesi.add(il);
        }
        return IlListesi;
    }
//*********************************************************************************
//---------------------------------- S O N ----------------------------------------
//*********************************************************************************
//------------------   ï¿½Lï¿½E Lï¿½STE ï¿½EKME ï¿½ï¿½LEMï¿½  ---------------------------------
//**************************************************************************************

    public ArrayList<Ilce> IlceListe() throws SQLException {

       
        newConnectDB();
        String SQL = "SELECT * FROM tbl_ilce";
        Statement stmt;
        ResultSet rs;
        stmt = conn.createStatement();
        rs = stmt.executeQuery(SQL);
        Ilce ilce;
        ArrayList<Ilce> IlceListesi = new ArrayList<Ilce>();
        while (rs.next()) {
            ilce = new Ilce();
            ilce.setId(rs.getInt("id"));
            ilce.setIlce_adi(rs.getString("ilce_adi"));
            ilce.setIlce_adi(rs.getString("ilce_id"));
            IlceListesi.add(ilce);
        }
        return IlceListesi;
    }
    


}
