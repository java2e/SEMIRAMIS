package pelops.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import pelops.controller.AktifBean;
import pelops.db.DBConnection;
import pelops.model.AlacakBilgisi;

public class AlacakBilgisiDAO extends DBConnection {

	ArrayList<AlacakBilgisi> alacakBilgisiListesi = new ArrayList<AlacakBilgisi>();

	public boolean kaydet(AlacakBilgisi alacak) {

		boolean kaydedildi = false;

		String SQL = "INSERT INTO tbl_alacak_bilgisi(belge_tipi_id, belge_statusu, doviz_tipi, doviz_kuru, tanzim_tarihi,"
				+ " vade_tarihi, ihtarname_tarihi, belge_miktari, odenen_miktar,"
				+ " faiz_tipi_id, aciklama, icra_dosyasi_id)" + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		try {

			newConnectDB();

			PreparedStatement pstmt = conn.prepareStatement(SQL.toString());

			pstmt.setInt(1, alacak.getBelge_tipi_id());
			pstmt.setString(2, alacak.getBelge_statusu());
			pstmt.setString(3, alacak.getDoviz_tipi());
			pstmt.setString(4, alacak.getDoviz_kuru());
			pstmt.setDate(5, convertFromJAVADateToSQLDate(alacak.getTanzim_tarihi()));
			pstmt.setDate(6, convertFromJAVADateToSQLDate(alacak.getVade_tarihi()));
			pstmt.setDate(7, convertFromJAVADateToSQLDate(alacak.getIhtarname_tarihi()));
			pstmt.setDouble(8, alacak.getBelge_miktari());
			pstmt.setDouble(9, alacak.getOdenen_miktar());
			pstmt.setInt(10, alacak.getFaiz_tipi_id());
			pstmt.setString(11, alacak.getAciklama());
			pstmt.setInt(12, AktifBean.getIcraDosyaID());

			int result = pstmt.executeUpdate();

			disconnectDB();
			if (result == 1) {
				kaydedildi = true;
			}

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		return kaydedildi;
	}

	public ArrayList<AlacakBilgisi> vwListe() throws Exception {
		newConnectDB();
		String SQL = "select * from vwalacakbilgisi ";
		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);

		AlacakBilgisi alacak;
		ArrayList<AlacakBilgisi> alacakListesi = new ArrayList<AlacakBilgisi>();
		while (rs.next()) {
			alacak = new AlacakBilgisi();

			alacak.setId(rs.getInt("id"));
			alacak.setBelge_miktari(rs.getInt("belge_miktari"));
			alacak.setBelge_statusu(rs.getString("belge_statusu"));
			alacak.setBelge_tipi(rs.getString("belge_tipi"));
			alacak.setDoviz_kuru(rs.getString("doviz_kuru"));
			alacak.setDoviz_tipi(rs.getString("doviz_tipi"));
			alacak.setIhtarname_tarihi(rs.getDate("ihtarname_tarihi"));
			alacak.setOdenen_miktar(rs.getDouble("odenen_miktar"));
			alacak.setTanzim_tarihi(rs.getDate("tanzim_tarihi"));
			alacak.setVade_tarihi(rs.getDate("vade_tarihi"));
			alacakListesi.add(alacak);

		}
		disconnectDB();
		return alacakListesi;

	}

	public boolean sil(int id) throws Exception {

		String SQL = "DELETE FROM tbl_alacak_bilgisi WHERE id = ?;";

		boolean silindi = false;

		try {
			newConnectDB();
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);

			// execute delete SQL stetement
			int result = pstmt.executeUpdate();

			disconnectDB();

			if (result == 1) {

				silindi = true;

			}

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		return silindi;

	}

	public java.sql.Date convertFromJAVADateToSQLDate(java.util.Date javaDate) {
		java.sql.Date sqlDate = null;
		if (javaDate != null) {
			sqlDate = new Date(javaDate.getTime());
		}
		return sqlDate;
	}

	public ArrayList<AlacakBilgisi> getAllListFromIcraDosyaID(int icraDosyaID) throws Exception {

		ArrayList<AlacakBilgisi> list = new ArrayList<AlacakBilgisi>();
		newConnectDB();

		String SQL = "SELECT * FROM tbl_alacak_bilgisi where icra_dosyasi_id=" + icraDosyaID;

		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);

		AlacakBilgisi alacak = null;

		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();

		while (rs.next()) {

			alacak = new AlacakBilgisi();
			alacak.setId(rs.getInt("id"));
			alacak.setAciklama(rs.getString("aciklama"));
			alacak.setBelgeMiktariTL(defaultFormat.format(rs.getDouble("belge_miktari")));
			alacak.setBelge_statusu(rs.getString("belge_statusu"));
			alacak.setBelge_tipi_id(rs.getInt("belge_tipi_id"));
			alacak.setDoviz_kuru(rs.getString("doviz_kuru"));
			alacak.setDoviz_tipi(rs.getString("doviz_tipi"));
			alacak.setFaiz_tipi_id(rs.getInt("faiz_tipi_id"));
			alacak.setIcra_dosyasi_id(rs.getInt("icra_dosyasi_id"));
			alacak.setIhtarname_tarihi(rs.getDate("ihtarname_tarihi"));
			alacak.setOdenen_miktar(rs.getDouble("odenen_miktar"));
			alacak.setTanzim_tarihi(rs.getDate("tanzim_tarihi"));
			alacak.setVade_tarihi(rs.getDate("vade_tarihi"));

			list.add(alacak);

		}
		disconnectDB();

		return list;
	}

	public boolean guncelle(AlacakBilgisi alacak) {

		boolean guncellendi = false;

		String SQL = "UPDATE tbl_alacak_bilgisi SET belge_tipi_id=?, belge_statusu=?, doviz_tipi=?, doviz_kuru=?,"
				+ " tanzim_tarihi=?, vade_tarihi=?, ihtarname_tarihi=?, belge_miktari=?,"
				+ " odenen_miktar=?, faiz_tipi_id=?, aciklama=?, icra_dosyasi_id=?" + " WHERE id=?;";
		try {

			newConnectDB();

			PreparedStatement pstmt = conn.prepareStatement(SQL.toString());

			pstmt.setInt(1, alacak.getBelge_tipi_id());
			pstmt.setString(2, alacak.getBelge_statusu());
			pstmt.setString(3, alacak.getDoviz_tipi());
			pstmt.setString(4, alacak.getDoviz_kuru());
			pstmt.setDate(5, convertFromJAVADateToSQLDate(alacak.getTanzim_tarihi()));
			pstmt.setDate(6, convertFromJAVADateToSQLDate(alacak.getVade_tarihi()));
			pstmt.setDate(7, convertFromJAVADateToSQLDate(alacak.getIhtarname_tarihi()));
			pstmt.setDouble(8, alacak.getBelge_miktari());
			pstmt.setDouble(9, alacak.getOdenen_miktar());
			pstmt.setInt(10, alacak.getFaiz_tipi_id());
			pstmt.setString(11, alacak.getAciklama());
			pstmt.setInt(12, AktifBean.getIcraDosyaID());
			pstmt.setInt(13, alacak.getId());

			int result = pstmt.executeUpdate();

			disconnectDB();
			if (result == 1) {
				guncellendi = true;
			}

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		return guncellendi;
	}

}
