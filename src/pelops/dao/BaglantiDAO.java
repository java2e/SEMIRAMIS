package pelops.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.db.DBConnection;
import pelops.model.Baglanti;

public class BaglantiDAO extends DBConnection {

	public Baglanti Listele(int icradosyaid) throws Exception {

		newConnectDB();
		String SQL = "SELECT id, icra_dosyasi_id, alacakli_id,borclu_id, hesap_id  FROM tbl_baglanti where icra_dosyasi_id="
				+ icradosyaid + ";";

		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		Baglanti baglanti = null;

		while (rs.next()) {
			baglanti = new Baglanti();
			baglanti.setAlacakliID(rs.getInt("alacakli_id"));
			baglanti.setBorcluID(rs.getInt("borclu_id"));
			baglanti.setHesaplamaID(rs.getInt("hesap_id"));
			baglanti.setIcradosyasiID(rs.getInt("icra_dosyasi_id"));

		}
		disconnectDB();
		return baglanti;

	}

	public ArrayList<Baglanti> BaglantiListele(int icradosyaid) throws Exception {

		DBConnection DB = new DBConnection();
		DB.newConnectDB();
		String SQL = "SELECT id, icra_dosyasi_id, alacakli_id,borclu_id, hesap_id  FROM tbl_baglanti where icra_dosyasi_id="
				+ icradosyaid + ";";
		Statement stmt;
		ResultSet rs;
		stmt = DB.conn.createStatement();
		rs = stmt.executeQuery(SQL);
		Baglanti baglanti = null;
		ArrayList<Baglanti> liste = new ArrayList<Baglanti>();

		while (rs.next()) {
			baglanti = new Baglanti();
			baglanti.setAlacakliID(rs.getInt("alacakli_id"));
			baglanti.setBorcluID(rs.getInt("borclu_id"));
			baglanti.setHesaplamaID(rs.getInt("hesap_id"));
			baglanti.setIcradosyasiID(rs.getInt("icra_dosyasi_id"));

			liste.add(baglanti);
		}
		DB.disconnectDB();
		return liste;

	}

	public ArrayList<Baglanti> BaglantiListele() throws Exception {

		DBConnection DB = new DBConnection();
		DB.newConnectDB();
		String SQL = "SELECT id, icra_dosyasi_id, alacakli_id,borclu_id, hesap_id  FROM tbl_baglanti ;";
		Statement stmt;
		ResultSet rs;
		stmt = DB.conn.createStatement();
		rs = stmt.executeQuery(SQL);
		Baglanti baglanti = null;
		ArrayList<Baglanti> liste = new ArrayList<Baglanti>();

		while (rs.next()) {
			baglanti = new Baglanti();
			baglanti.setAlacakliID(rs.getInt("alacakli_id"));
			baglanti.setBorcluID(rs.getInt("borclu_id"));
			baglanti.setHesaplamaID(rs.getInt("hesap_id"));
			baglanti.setIcradosyasiID(rs.getInt("icra_dosyasi_id"));

			liste.add(baglanti);
		}
		DB.disconnectDB();
		return liste;

	}

	public void Kaydet(Baglanti baglanti) throws Exception {
		String SQL = "INSERT INTO tbl_baglanti( icra_dosyasi_id, alacakli_id,borclu_id," + " hesap_id)"
				+ " VALUES ( ?, ?, ?, ?);";

		DBConnection DB = new DBConnection();
		DB.newConnectDB();

		PreparedStatement pstmt = DB.conn.prepareStatement(SQL.toString());

		pstmt.setInt(1, baglanti.getIcradosyasiID());
		pstmt.setInt(2, baglanti.getAlacakliID());
		pstmt.setInt(3, baglanti.getBorcluID());
		pstmt.setInt(4, baglanti.getHesaplamaID());
		pstmt.executeUpdate();
		DB.disconnectDB();

	}

	public void Duzenle(Baglanti baglanti) {

	}

	public void Sil(int id) {

	}

}
