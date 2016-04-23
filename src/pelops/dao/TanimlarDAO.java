package pelops.dao;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.db.DBConnection;
import pelops.model.GenelTanimSablon;
import pelops.model.Il;
import pelops.model.Ilce;








import java.sql.Connection;
import java.sql.PreparedStatement;

public class TanimlarDAO extends DBConnection {

    public static Connection conn = null;
    public static PreparedStatement psmt = null;
    public static ResultSet rs = null;
  
    public ArrayList<GenelTanimSablon> Liste(String DBAdi) throws Exception {

        DBConnection DB = new DBConnection();
        DB.newConnectDB();
        String SQL = "SELECT * FROM " + DBAdi;
        Statement stmt;
        ResultSet rs;
        stmt = DB.conn.createStatement();
        rs = stmt.executeQuery(SQL);
        GenelTanimSablon Sablon;
        ArrayList<GenelTanimSablon> SablonListesi = new ArrayList<GenelTanimSablon>();
        while (rs.next()) {
            Sablon = new GenelTanimSablon();
            Sablon.setAdi(rs.getString("adi"));
            Sablon.setId(rs.getString("id"));
            SablonListesi.add(Sablon);
        }
       DB.disconnectDB();
        return SablonListesi;
      
        
    }

    public boolean Kayit(GenelTanimSablon Sablon, String DBAdi) throws Exception {

        String SQL = "INSERT INTO " + DBAdi + "(\"adi\") VALUES ('" + Sablon.getAdi() + "')";
        DBConnection DB = new DBConnection();
        DB.newConnectDB();
        Statement stmt = DB.conn.createStatement();
        boolean st = stmt.execute(SQL);
        DB.disconnectDB();
        return st;
    }


    public boolean Sil(int id, String DBAdi) throws Exception {
        String SQL = "DELETE FROM " + DBAdi + " WHERE \"id\"='" + id + "'";
        DBConnection DB = new DBConnection();
        DB.newConnectDB();
        Statement stmt = DB.conn.createStatement();
        boolean st = stmt.execute(SQL);
        DB.disconnectDB();
        return st;
    }


    
    public boolean Duzenle(GenelTanimSablon Sablon, String DBAdi) throws Exception {
        String SQL = "UPDATE " + DBAdi + " SET \"adi\"='" + Sablon.getAdi() + "' WHERE \"id\"='" + Sablon.getId() + "'";
        DBConnection DB = new DBConnection();
        DB.newConnectDB();
        Statement stmt = DB.conn.createStatement();
        boolean st = stmt.execute(SQL);
        DB.disconnectDB();
        return st;
    }

    public ArrayList<Il> IlListe() throws Exception {

        DBConnection DB = new DBConnection();
        DB.newConnectDB();
        String SQL = "SELECT * FROM tbl_il";
        Statement stmt;
        ResultSet rs;
        stmt = DB.conn.createStatement();
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

        DB.disconnectDB();
        return IlListesi;
    }

    public ArrayList<Ilce> IlceListe() throws Exception {

        DBConnection DB = new DBConnection();
        DB.newConnectDB();
        String SQL = "SELECT * FROM tbl_ilce";
        Statement stmt;
        ResultSet rs;
        stmt = DB.conn.createStatement();
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

        DB.disconnectDB();
        return IlceListesi;
    }

/*
    public void MasrafBilgisiKaydet(MasrafBilgisi masrafData) throws SQLException {

          // tbl_masraf_bilgisi tablosu oluÅŸturuldu.
          // Tatih bilgisinden dolayÄ± insert edilemedi. (Tarih =tipi String yapÄ±lÄ±nca insert baÅŸarÄ±lÄ±)
        
         String SQL = "INSERT INTO tbl_masraf_bilgisi(\"masraf_tipi\",\"masraf_tarihi\",masraf_miktari,\"masraf_aciklama\",\"masraf_personel_adi\",\"masraf_uygulama_asamasi\") "
                + "VALUES ('" + masrafData.getMasrafTipi() + "','" + masrafData.getMasrafTarihi()+ "'," + masrafData.getMasrafMiktari() + ",'" + masrafData.getMasrafAciklama() + "','" + masrafData.getMasrafPersonelAdi() + "','"
                + masrafData.getMasrafUygulamaAsamasi() + "')";
        DBConnection DB = new DBConnection();
        DB.newConnectDB();
        Statement stmt = DB.conn.createStatement();
        stmt.execute(SQL);

    }


    public void icraBorcKaydet(IcraBorcBean icraData) throws SQLException {

        // Database'de tbl_icra_borc tablosu oluÅŸturuldu.
        // Ses kayÄ±ttan dolayÄ± insert edilemedi.
        
           String SQL = "INSERT INTO tbl_icra_borc(\"adi_soyadi\",\"tarih_saat\",\"konu\",\"aciklama\",\"ses_kaydi\") "
                + "VALUES ('" + icraData.getAdisoyadi() + "','" + icraData.getTarihsaat()+ "'," + icraData.getKonu() + ",'" +icraData.getAciklama() + "','" + icraData.getSeskayit()+ "')";
        DBConnection DB = new DBConnection();
        DB.newConnectDB();
        Statement stmt = DB.conn.createStatement();
      stmt.execute(SQL);
        
        
        
        
    }

 
    public ArrayList<MasrafTipi> masrafTipiGetir(String DBAdi) throws SQLException {

        DBConnection DB = new DBConnection();
        DB.newConnectDB();
        String SQL = "SELECT * FROM " + DBAdi;
        Statement stmt;
        ResultSet rs;
        stmt = DB.conn.createStatement();
        rs = stmt.executeQuery(SQL);

        ArrayList<MasrafTipi> list = new ArrayList<MasrafTipi>();
        while (rs.next()) {
            MasrafTipi tip = new MasrafTipi();

            tip.setId(rs.getInt("id"));
            tip.setAdi(rs.getString("adi"));
            list.add(tip);
        }

        return list;

    }
*/
   

}
