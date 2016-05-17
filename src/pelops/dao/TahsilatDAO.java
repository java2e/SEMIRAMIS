package pelops.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;

import pelops.controller.AktifBean;
import pelops.db.DBConnection;
import pelops.model.Tahsilat;

public class TahsilatDAO extends DBConnection{

	ResultSet rs;
	Statement stmt;
	PreparedStatement pstmt;
	ArrayList<Tahsilat> TahsilatListesi ;
	
	public void Kaydet(Tahsilat tahsilat) throws Exception{
		
		String SQL = "INSERT INTO tbl_tahsilat(icra_dosyasi_id, icra_dosya_no, muvekkil_adi, borclu_adi,"
				+ " gelis_tarihi, borc_tipi, dosya_tipi, icra_mudurlugu, tahsilat_tarihi,"
				+ " tahsilat_tipi, tahsilat_miktari, tahsilat_statusu)"
				+ "  VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		

		newConnectDB();

		PreparedStatement pstmt = conn.prepareStatement(SQL.toString());
		
		pstmt.setInt(1, tahsilat.getIcra_dosyasi_id());
		pstmt.setString(2, tahsilat.getIcra_dosya_no());
		pstmt.setString(3, tahsilat.getMuvekkil_adi());
		pstmt.setString(4, tahsilat.getBorclu_adi());
		pstmt.setDate(5,convertFromJAVADateToSQLDate(tahsilat.getGelis_tarihi()));
		pstmt.setString(6, tahsilat.getBorc_tipi());
		pstmt.setString(7, tahsilat.getDosya_tipi());
		pstmt.setString(8, tahsilat.getIcra_mudurlugu());
		pstmt.setDate(9, convertFromJAVADateToSQLDate(tahsilat.getTahsilat_tarihi()));
		pstmt.setString(10, tahsilat.getTahsilat_tipi());
		pstmt.setDouble(11, tahsilat.getTahsilat_miktari());
		pstmt.setString(12, tahsilat.getTahsilat_statusu());
		pstmt.executeUpdate();

		disconnectDB();
		
		HesapDAO dao = new HesapDAO();
		dao.GuncelleTahsilat(AktifBean.getHesapID(), tahsilat.getTahsilat_miktari());
		
	}
	
		
	public void Guncelle(Tahsilat tahsilat) throws Exception{
		
		String SQL="UPDATE tbl_tahsilat  SET id=?, icra_dosyasi_id=?, icra_dosya_no=?, muvekkil_adi=?, borclu_adi=?,"
				+ " gelis_tarihi=?, borc_tipi=?, dosya_tipi=?, icra_mudurlugu=?, tahsilat_tarihi=?, tahsilat_tipi=?, tahsilat_miktari=?, tahsilat_statusu=?"
				+ " WHERE id="+tahsilat.getId();
		newConnectDB();

		PreparedStatement pstmt = conn.prepareStatement(SQL.toString());
		
		pstmt.setInt(1, tahsilat.getIcra_dosyasi_id());
		pstmt.setString(2, tahsilat.getIcra_dosya_no());
		pstmt.setString(3, tahsilat.getMuvekkil_adi());
		pstmt.setString(4, tahsilat.getBorclu_adi());
		pstmt.setDate(5,convertFromJAVADateToSQLDate(tahsilat.getGelis_tarihi()));
		pstmt.setString(6, tahsilat.getBorc_tipi());
		pstmt.setString(7, tahsilat.getDosya_tipi());
		pstmt.setString(8, tahsilat.getIcra_mudurlugu());
		pstmt.setDate(9, convertFromJAVADateToSQLDate(tahsilat.getTahsilat_tarihi()));
		pstmt.setString(10, tahsilat.getTahsilat_tipi());
		pstmt.setDouble(11, tahsilat.getTahsilat_miktari());
		pstmt.setString(12, tahsilat.getTahsilat_statusu());
		pstmt.executeUpdate();

		disconnectDB();
		
	}
	
	public void Sil(int TahsilatID, double tahsilatTutari) throws Exception{
		
			String SQL = "DELETE FROM tbl_tahsilat WHERE id= ?;";

		
			newConnectDB();

			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, TahsilatID);

			pstmt.executeUpdate();

			disconnectDB();
			
			HesapDAO dao = new HesapDAO();
			dao.SilTahsilat(AktifBean.getHesapID(), tahsilatTutari);
		
	}
	
	public ArrayList<Tahsilat> ListeleTum() throws Exception{
		
		String SQL = "SELECT id, icra_dosyasi_id, icra_dosya_no, muvekkil_adi, borclu_adi,"
				+ " gelis_tarihi, borc_tipi, dosya_tipi, icra_mudurlugu, tahsilat_tarihi,"
				+ " tahsilat_tipi, tahsilat_miktari, tahsilat_statusu  FROM tbl_tahsilat;";
		
		newConnectDB();

		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		Tahsilat tahsilat;
		TahsilatListesi = new ArrayList<Tahsilat>();
		  NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		while(rs.next()){
			tahsilat = new Tahsilat();
			tahsilat.setBorc_tipi(rs.getString("borc_tipi"));
			tahsilat.setBorclu_adi(rs.getString("borclu_adi"));
			tahsilat.setDosya_tipi(rs.getString("dosya_tipi"));
			tahsilat.setGelis_tarihi(rs.getDate("gelis_tarihi"));
			tahsilat.setIcra_dosya_no(rs.getString("icra_dosya_no"));
			tahsilat.setIcra_dosyasi_id(rs.getInt("icra_dosyasi_id"));
			tahsilat.setIcra_mudurlugu(rs.getString("icra_mudurlugu"));
			tahsilat.setId(rs.getInt("id"));
			tahsilat.setMuvekkil_adi(rs.getString("muvekkil_adi"));
			tahsilat.setTahsilat_miktari(rs.getDouble("tahsilat_miktari"));
			tahsilat.setTahsilat_miktari_tl(defaultFormat.format(rs.getDouble("tahsilat_miktari")));
			tahsilat.setTahsilat_statusu(rs.getString("tahsilat_statusu"));
			tahsilat.setTahsilat_tarihi(rs.getDate("tahsilat_tarihi"));
			tahsilat.setTahsilat_tipi(rs.getString("tahsilat_tipi"));
			
			TahsilatListesi.add(tahsilat);	
			
		}
		
	
		
		disconnectDB();
		return TahsilatListesi;
	}

	public ArrayList<Tahsilat> ListeleID(int IcraDosyasiID) throws Exception{
		
		String SQL = "SELECT id, icra_dosyasi_id, icra_dosya_no, muvekkil_adi, borclu_adi,"
				+ " gelis_tarihi, borc_tipi, dosya_tipi, icra_mudurlugu, tahsilat_tarihi,"
				+ " tahsilat_tipi, tahsilat_miktari, tahsilat_statusu  FROM tbl_tahsilat where icra_dosyasi_id='"+IcraDosyasiID+"'";
		
		newConnectDB();

		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		Tahsilat tahsilat;
		TahsilatListesi = new ArrayList<Tahsilat>();
		  NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		while(rs.next()){
			tahsilat = new Tahsilat();
			tahsilat.setBorc_tipi(rs.getString("borc_tipi"));
			tahsilat.setBorclu_adi(rs.getString("borclu_adi"));
			tahsilat.setDosya_tipi(rs.getString("dosya_tipi"));
			tahsilat.setGelis_tarihi(rs.getDate("gelis_tarihi"));
			tahsilat.setIcra_dosya_no(rs.getString("icra_dosya_no"));
			tahsilat.setIcra_dosyasi_id(rs.getInt("icra_dosyasi_id"));
			tahsilat.setIcra_mudurlugu(rs.getString("icra_mudurlugu"));
			tahsilat.setId(rs.getInt("id"));
			tahsilat.setMuvekkil_adi(rs.getString("muvekkil_adi"));
			tahsilat.setTahsilat_miktari(rs.getDouble("tahsilat_miktari"));
			tahsilat.setTahsilat_miktari_tl(defaultFormat.format(rs.getDouble("tahsilat_miktari")));
			
			tahsilat.setTahsilat_statusu(rs.getString("tahsilat_statusu"));
			tahsilat.setTahsilat_tarihi(rs.getDate("tahsilat_tarihi"));
			tahsilat.setTahsilat_tipi(rs.getString("tahsilat_tipi"));
			
			TahsilatListesi.add(tahsilat);	
			
		}
		
				
		disconnectDB();
		return TahsilatListesi;
	}
	
public Tahsilat ListeleTahsilat(int id) throws Exception{
		
		String SQL = "SELECT id, icra_dosyasi_id, icra_dosya_no, muvekkil_adi, borclu_adi,"
				+ " gelis_tarihi, borc_tipi, dosya_tipi, icra_mudurlugu, tahsilat_tarihi,"
				+ " tahsilat_tipi, tahsilat_miktari, tahsilat_statusu  FROM tbl_tahsilat where id='"+id+"'";
		
		newConnectDB();

		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		Tahsilat tahsilat= new Tahsilat();;
				while(rs.next()){
			
			tahsilat.setBorc_tipi(rs.getString("borc_tipi"));
			tahsilat.setBorclu_adi(rs.getString("borclu_adi"));
			tahsilat.setDosya_tipi(rs.getString("dosya_tipi"));
			tahsilat.setGelis_tarihi(rs.getDate("gelis_tarihi"));
			tahsilat.setIcra_dosya_no(rs.getString("icra_dosya_no"));
			tahsilat.setIcra_dosyasi_id(rs.getInt("icra_dosyasi_id"));
			tahsilat.setIcra_mudurlugu(rs.getString("icra_mudurlugu"));
			tahsilat.setId(rs.getInt("id"));
			tahsilat.setMuvekkil_adi(rs.getString("muvekkil_adi"));
			tahsilat.setTahsilat_miktari(rs.getDouble("tahsilat_miktari"));
			tahsilat.setTahsilat_statusu(rs.getString("tahsilat_statusu"));
			tahsilat.setTahsilat_tarihi(rs.getDate("tahsilat_tarihi"));
			tahsilat.setTahsilat_tipi(rs.getString("tahsilat_tipi"));
		
			
		}
		
		
		
		disconnectDB();
		return tahsilat;
	}
	
    public java.sql.Date convertFromJAVADateToSQLDate(java.util.Date javaDate) {
			java.sql.Date sqlDate = null;
			if (javaDate != null) {
				sqlDate = new Date(javaDate.getTime());
			}
			return sqlDate;
		}
}
