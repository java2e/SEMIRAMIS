package pelops.kasa.controller;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import pelops.controller.AktifBean;
import pelops.dao.HesapDAO;
import pelops.db.DBConnection;
import pelops.kasa.model.Hitam;
import pelops.kasa.model.HitamView;
import pelops.kasa.model.KasaSearchParams;
import pelops.kasa.model.ReddiyatView;
import pelops.kasa.model.Tahsilat;
import pelops.kasa.model.TahsilatViewModel;
import pelops.users.User;
import pelops.report.model.ReportGenel;
import pelops.util.Util;

public class TahsilatDAO extends DBConnection implements IDAO {

	ResultSet rs;
	Statement stmt;
	PreparedStatement pstmt;
	ArrayList<Tahsilat> TahsilatListesi;

	public void Kaydet(Tahsilat tahsilat) throws Exception {

		String SQL = "INSERT INTO tbl_tahsilat( icra_dosyasi_id, muvekkil_adi, borclu_adi, gelis_tarihi, "
				+ " borc_tipi, tahsilat_tarihi, tahsilat_tipi, tahsilat_miktari,"
				+ " tahsilat_statusu, durum, gelis_yeri, onaylayan_id, kasa_personel_id,"
				+ " dosya_tipi, icra_dosya_no, icra_mudurlugu,izleme_id , vizit_id , odemeplani_id,soz_alan_personel_id ) "
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?,   ?, ?, ?, ?, ?,  ?, ?, ?,? ,? ,?,?);";

		newConnectDB();

		PreparedStatement pstmt = conn.prepareStatement(SQL.toString());

		pstmt.setInt(1, tahsilat.getIcra_dosyasi_id());
		pstmt.setString(2, tahsilat.getMuvekkil_adi());
		pstmt.setString(3, tahsilat.getBorclu_adi());
		pstmt.setDate(4, convertFromJAVADateToSQLDate(tahsilat.getGelis_tarihi()));
		pstmt.setString(5, tahsilat.getBorc_tipi());
		pstmt.setDate(6, convertFromJAVADateToSQLDate(tahsilat.getTahsilat_tarihi()));
		pstmt.setString(7, tahsilat.getTahsilat_tipi());
		pstmt.setDouble(8, tahsilat.getTahsilat_miktari());
		pstmt.setString(9, tahsilat.getTahsilat_statusu());
		pstmt.setInt(10, tahsilat.getDurum());
		pstmt.setString(11, tahsilat.getGelisYeri());
		pstmt.setInt(12, tahsilat.getOnaylayanID());
		HttpSession session = Util.getSession();
		pstmt.setInt(13, Integer.valueOf(((User) session.getAttribute("user")).getUsrId()));
		pstmt.setString(14, tahsilat.getDosya_tipi());
		pstmt.setString(15, tahsilat.getIcra_dosya_no());
		pstmt.setString(16, tahsilat.getIcra_mudurlugu());
		pstmt.setInt(17, tahsilat.getIzleme_id());
		pstmt.setInt(18, tahsilat.getVizit_id());
		pstmt.setInt(19, tahsilat.getOdemeplani_id());
		pstmt.setInt(20, tahsilat.getSoz_alan_personel_id());
		
		
		pstmt.executeUpdate();

		disconnectDB();

		HesapDAO dao = new HesapDAO();
		dao.GuncelleTahsilat(AktifBean.getHesapID(), tahsilat.getTahsilat_miktari());

	}

	public void Guncelle(Tahsilat tahsilat) throws Exception {

		String SQL = "UPDATE tbl_tahsilat SET  icra_dosyasi_id=?, muvekkil_adi=?, borclu_adi=?, gelis_tarihi=?, "
				+ "borc_tipi=?, tahsilat_tarihi=?, tahsilat_tipi=?, tahsilat_miktari=?, "
				+ " tahsilat_statusu=?, durum=?, gelis_yeri=?, onaylayan_id=?, kasa_personel_id=?, "
				+ " dosya_tipi=?, icra_dosya_no=?, icra_mudurlugu=?" + " WHERE id=" + tahsilat.getId();
		newConnectDB();

		PreparedStatement pstmt = conn.prepareStatement(SQL.toString());

		pstmt.setInt(1, tahsilat.getIcra_dosyasi_id());
		pstmt.setString(2, tahsilat.getMuvekkil_adi());
		pstmt.setString(3, tahsilat.getBorclu_adi());
		pstmt.setDate(4, convertFromJAVADateToSQLDate(tahsilat.getGelis_tarihi()));
		pstmt.setString(5, tahsilat.getBorc_tipi());
		pstmt.setDate(6, convertFromJAVADateToSQLDate(tahsilat.getTahsilat_tarihi()));
		pstmt.setString(7, tahsilat.getTahsilat_tipi());
		pstmt.setDouble(8, tahsilat.getTahsilat_miktari());
		pstmt.setString(9, tahsilat.getTahsilat_statusu());
		pstmt.setInt(10, tahsilat.getDurum());
		pstmt.setString(11, tahsilat.getGelisYeri());
		pstmt.setInt(12, tahsilat.getOnaylayanID());
		pstmt.setInt(13, tahsilat.getKasaPersonelID());
		pstmt.setString(14, tahsilat.getDosya_tipi());
		pstmt.setString(15, tahsilat.getIcra_dosya_no());
		pstmt.setString(16, tahsilat.getIcra_mudurlugu());
		pstmt.executeUpdate();

		disconnectDB();

	}

	public void Sil(int TahsilatID, double tahsilatTutari) throws Exception {

		String SQL = "DELETE FROM tbl_tahsilat WHERE id= ?;";

		newConnectDB();

		PreparedStatement pstmt = conn.prepareStatement(SQL);
		pstmt.setInt(1, TahsilatID);

		pstmt.executeUpdate();

		disconnectDB();

		HesapDAO dao = new HesapDAO();
		dao.SilTahsilat(AktifBean.getHesapID(), tahsilatTutari);

	}

	public ArrayList<Tahsilat> ListeleTum() throws Exception {

		String SQL = "SELECT id, icra_dosyasi_id, muvekkil_adi, borclu_adi, gelis_tarihi, "
				+ "  borc_tipi, tahsilat_tarihi, tahsilat_tipi, tahsilat_miktari, "
				+ "  tahsilat_statusu, durum, gelis_yeri, onaylayan_id, kasa_personel_id, "
				+ "  dosya_tipi, icra_dosya_no, icra_mudurlugu, izleme_id , vizit_id , odemeplani_id " + " FROM tbl_tahsilat;";

		newConnectDB();

		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		Tahsilat tahsilat;
		TahsilatListesi = new ArrayList<Tahsilat>();
		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		while (rs.next()) {
			tahsilat = new Tahsilat();
			tahsilat.setId(rs.getInt("id"));
			tahsilat.setBorc_tipi(rs.getString("borc_tipi"));
			tahsilat.setBorclu_adi(rs.getString("borclu_adi"));
			tahsilat.setDosya_tipi(rs.getString("dosya_tipi"));
			tahsilat.setGelis_tarihi(rs.getDate("gelis_tarihi"));
			tahsilat.setIcra_dosya_no(rs.getString("icra_dosya_no"));
			tahsilat.setIcra_dosyasi_id(rs.getInt("icra_dosyasi_id"));
			tahsilat.setIcra_mudurlugu(rs.getString("icra_mudurlugu"));
			tahsilat.setMuvekkil_adi(rs.getString("muvekkil_adi"));
			tahsilat.setTahsilat_miktari(rs.getDouble("tahsilat_miktari"));
			tahsilat.setTahsilat_miktari_tl(defaultFormat.format(rs.getDouble("tahsilat_miktari")));
			tahsilat.setTahsilat_statusu(rs.getString("tahsilat_statusu"));
			tahsilat.setTahsilat_tarihi(rs.getDate("tahsilat_tarihi"));
			tahsilat.setTahsilat_tipi(rs.getString("tahsilat_tipi"));
			tahsilat.setOnaylayanID(rs.getInt("onaylayan_id"));
			tahsilat.setKasaPersonelID(rs.getInt("kasa_personel_id"));
			tahsilat.setGelisYeri(rs.getString("gelis_yeri"));
			tahsilat.setDurum(rs.getInt("durum"));
			tahsilat.setIzleme_id(rs.getInt("izleme_id"));
			tahsilat.setVizit_id(rs.getInt("vizit_id"));
			tahsilat.setOdemeplani_id(rs.getInt("odemeplani_id"));
			TahsilatListesi.add(tahsilat);

		}

		disconnectDB();
		return TahsilatListesi;
	}

	public ArrayList<Tahsilat> ListeleID(int IcraDosyasiID) throws Exception {

		String SQL = "SELECT id, icra_dosyasi_id, icra_dosya_no, muvekkil_adi, borclu_adi,"
				+ " gelis_tarihi, borc_tipi, dosya_tipi, icra_mudurlugu, tahsilat_tarihi,"
				+ " tahsilat_tipi, tahsilat_miktari, tahsilat_statusu, izleme_id , vizit_id , odemeplani_id   FROM tbl_tahsilat where icra_dosyasi_id='"
				+ IcraDosyasiID + "'";

		newConnectDB();

		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		Tahsilat tahsilat;
		TahsilatListesi = new ArrayList<Tahsilat>();
		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		while (rs.next()) {
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
			tahsilat.setIzleme_id(rs.getInt("izleme_id"));
			tahsilat.setVizit_id(rs.getInt("vizit_id"));
			tahsilat.setOdemeplani_id(rs.getInt("odemeplani_id"));
			TahsilatListesi.add(tahsilat);

		}

		disconnectDB();
		return TahsilatListesi;
	}

	public Tahsilat ListeleTahsilat(int id) throws Exception {

		String SQL = "SELECT id, icra_dosyasi_id, icra_dosya_no, muvekkil_adi, borclu_adi,"
				+ " gelis_tarihi, borc_tipi, dosya_tipi, icra_mudurlugu, tahsilat_tarihi,"
				+ " tahsilat_tipi, tahsilat_miktari, tahsilat_statusu, izleme_id , vizit_id , odemeplani_id   FROM tbl_tahsilat where id='" + id + "'";

		newConnectDB();

		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		Tahsilat tahsilat = new Tahsilat();
		
		while (rs.next()) {

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
			tahsilat.setIzleme_id(rs.getInt("izleme_id"));
			tahsilat.setVizit_id(rs.getInt("vizit_id"));
			tahsilat.setOdemeplani_id(rs.getInt("odemeplani_id"));
		}

		disconnectDB();
		return tahsilat;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pelops.kasa.controller.IDAO#getAllObjFromDB() Yeni yazılan
	 * methodlar...
	 */

	@Override
	public ArrayList<Object> getAllObjFromDB() throws Exception {
		String SQL = "SELECT id, icra_dosyasi_id, muvekkil_adi, borclu_adi, gelis_tarihi, "
				+ "  borc_tipi, tahsilat_tarihi, tahsilat_tipi, tahsilat_miktari, "
				+ "  tahsilat_statusu, durum, gelis_yeri, onaylayan_id, kasa_personel_id, "
				+ "  dosya_tipi, icra_dosya_no, icra_mudurlugu,  izleme_id , vizit_id , odemeplani_id " + " FROM tbl_tahsilat;";

		newConnectDB();

		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		Tahsilat tahsilat;
		ArrayList<Object> list = new ArrayList<Object>();
		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		while (rs.next()) {
			tahsilat = new Tahsilat();
			tahsilat.setId(rs.getInt("id"));
			tahsilat.setBorc_tipi(rs.getString("borc_tipi"));
			tahsilat.setBorclu_adi(rs.getString("borclu_adi"));
			tahsilat.setDosya_tipi(rs.getString("dosya_tipi"));
			tahsilat.setGelis_tarihi(rs.getDate("gelis_tarihi"));
			tahsilat.setIcra_dosya_no(rs.getString("icra_dosya_no"));
			tahsilat.setIcra_dosyasi_id(rs.getInt("icra_dosyasi_id"));
			tahsilat.setIcra_mudurlugu(rs.getString("icra_mudurlugu"));
			tahsilat.setMuvekkil_adi(rs.getString("muvekkil_adi"));
			tahsilat.setTahsilat_miktari(rs.getDouble("tahsilat_miktari"));
			tahsilat.setTahsilat_miktari_tl(defaultFormat.format(rs.getDouble("tahsilat_miktari")));
			tahsilat.setTahsilat_statusu(rs.getString("tahsilat_statusu"));
			tahsilat.setTahsilat_tarihi(rs.getDate("tahsilat_tarihi"));
			tahsilat.setTahsilat_tipi(rs.getString("tahsilat_tipi"));
			tahsilat.setOnaylayanID(rs.getInt("onaylayan_id"));
			tahsilat.setKasaPersonelID(rs.getInt("kasa_personel_id"));
			tahsilat.setGelisYeri(rs.getString("gelis_yeri"));
			tahsilat.setDurum(rs.getInt("durum"));
			tahsilat.setIzleme_id(rs.getInt("izleme_id"));
			tahsilat.setVizit_id(rs.getInt("vizit_id"));
			tahsilat.setOdemeplani_id(rs.getInt("odemeplani_id"));
			list.add(tahsilat);

		}

		disconnectDB();
		return list;
	}

	@Override
	public int insertObjToDB(Object obj) throws Exception {
		int id = 0;
		String SQL = "INSERT INTO tbl_tahsilat( icra_dosyasi_id, muvekkil_adi, borclu_adi, gelis_tarihi, "
				+ " borc_tipi, tahsilat_tarihi, tahsilat_tipi, tahsilat_miktari,"
				+ " tahsilat_statusu, durum, gelis_yeri, onaylayan_id, kasa_personel_id,"
				+ " dosya_tipi, icra_dosya_no, icra_mudurlugu,izleme_id , vizit_id , odemeplani_id, soz_alan_personel_id, hitam_durum) "
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?,   ?, ?, ?, ?, ?,  ?, ?, ?,?,?,?,?,?);";

		newConnectDB();
		if (obj instanceof Tahsilat) {
			Tahsilat tahsilat = (Tahsilat) obj;
			PreparedStatement pstmt = conn.prepareStatement(SQL.toString());

			pstmt.setInt(1, tahsilat.getIcra_dosyasi_id());
			pstmt.setString(2, tahsilat.getMuvekkil_adi());
			pstmt.setString(3, tahsilat.getBorclu_adi());
			pstmt.setDate(4, convertFromJAVADateToSQLDate(tahsilat.getGelis_tarihi()));
			pstmt.setString(5, tahsilat.getBorc_tipi());
			pstmt.setDate(6, convertFromJAVADateToSQLDate(tahsilat.getTahsilat_tarihi()));
			pstmt.setString(7, tahsilat.getTahsilat_tipi());
			pstmt.setDouble(8, tahsilat.getTahsilat_miktari());
			pstmt.setString(9, tahsilat.getTahsilat_statusu());
			pstmt.setInt(10, tahsilat.getDurum());
			pstmt.setString(11, tahsilat.getGelisYeri());
			pstmt.setInt(12, tahsilat.getOnaylayanID());
			HttpSession session = Util.getSession();

			pstmt.setInt(13, Integer.valueOf(((User) session.getAttribute("user")).getUsrId()));
			pstmt.setString(14, tahsilat.getDosya_tipi());
			pstmt.setString(15, tahsilat.getIcra_dosya_no());
			pstmt.setString(16, tahsilat.getIcra_mudurlugu());
			pstmt.setInt(17, tahsilat.getIzleme_id());
			pstmt.setInt(18, tahsilat.getVizit_id());
			pstmt.setInt(19, tahsilat.getOdemeplani_id());
			pstmt.setInt(20, tahsilat.getSoz_alan_personel_id());
			pstmt.setInt(21, tahsilat.getHitam_durum());
			
			pstmt.execute();

			disconnectDB();

			id = getID(tahsilat);

			HesapDAO dao = new HesapDAO();
			dao.GuncelleTahsilat(AktifBean.getHesapID(), tahsilat.getTahsilat_miktari());

		}
		return id;
	}

	@Override
	public int updateObjFromDB(Object obj) throws Exception {
		// TODO Auto-generated method stub
		int id = 0;
		if (obj instanceof Tahsilat) {
			Tahsilat tahsilat = (Tahsilat) obj;
			String SQL = "UPDATE tbl_tahsilat SET  icra_dosyasi_id=?, muvekkil_adi=?, borclu_adi=?, gelis_tarihi=?, "
					+ "borc_tipi=?, tahsilat_tarihi=?, tahsilat_tipi=?, tahsilat_miktari=?, "
					+ " tahsilat_statusu=?, durum=?, gelis_yeri=?, onaylayan_id=?, kasa_personel_id=?, "
					+ " dosya_tipi=?, icra_dosya_no=?, icra_mudurlugu=?" + " WHERE id=" + tahsilat.getId();
			newConnectDB();

			PreparedStatement pstmt = conn.prepareStatement(SQL.toString());

			pstmt.setInt(1, tahsilat.getIcra_dosyasi_id());
			pstmt.setString(2, tahsilat.getMuvekkil_adi());
			pstmt.setString(3, tahsilat.getBorclu_adi());
			pstmt.setDate(4, convertFromJAVADateToSQLDate(tahsilat.getGelis_tarihi()));
			pstmt.setString(5, tahsilat.getBorc_tipi());
			pstmt.setDate(6, convertFromJAVADateToSQLDate(tahsilat.getTahsilat_tarihi()));
			pstmt.setString(7, tahsilat.getTahsilat_tipi());
			pstmt.setDouble(8, tahsilat.getTahsilat_miktari());
			pstmt.setString(9, tahsilat.getTahsilat_statusu());
			pstmt.setInt(10, tahsilat.getDurum());
			pstmt.setString(11, tahsilat.getGelisYeri());
			pstmt.setInt(12, tahsilat.getOnaylayanID());
			pstmt.setInt(13, tahsilat.getKasaPersonelID());
			pstmt.setString(14, tahsilat.getDosya_tipi());
			pstmt.setString(15, tahsilat.getIcra_dosya_no());
			pstmt.setString(16, tahsilat.getIcra_mudurlugu());
			pstmt.execute();

			disconnectDB();

			id = getID(tahsilat);
		}
		return id;
	}

	@Override
	public boolean deleteObjFromDB(int id) throws Exception {
		String SQL = "DELETE FROM tbl_tahsilat WHERE id= ?;";

		newConnectDB();

		PreparedStatement pstmt = conn.prepareStatement(SQL);
		pstmt.setInt(1, id);

		boolean isTrue = pstmt.execute();

		disconnectDB();

		return isTrue;
	}

	@Override
	public Object getObjFromDB(int id) throws Exception {
		String SQL = "SELECT id, icra_dosyasi_id, icra_dosya_no, muvekkil_adi, borclu_adi,"
				+ " gelis_tarihi, borc_tipi, dosya_tipi, icra_mudurlugu, tahsilat_tarihi,"
				+ " tahsilat_tipi, tahsilat_miktari, tahsilat_statusu, izleme_id , vizit_id , odemeplani_id  FROM tbl_tahsilat where id='" + id + "'";

		newConnectDB();

		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		Tahsilat tahsilat = new Tahsilat();

		while (rs.next()) {

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
			tahsilat.setIzleme_id(rs.getInt("izleme_id"));
			tahsilat.setVizit_id(rs.getInt("vizit_id"));
			tahsilat.setOdemeplani_id(rs.getInt("odemeplani_id"));
		}

		disconnectDB();
		return tahsilat;
	}

	@Override
	public int getID(Object object) throws Exception {
		// TODO Auto-generated method stub
		int id = 0;
		if (object instanceof Tahsilat) {
			String sql = "select id from  tbl_tahsilat where icra_dosyasi_id="
					+ ((Tahsilat) object).getIcra_dosyasi_id() + ";";

			newConnectDB();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				id = rs.getInt("id");
			}

			disconnectDB();

		}

		return id;
	}

	@Override
	public ArrayList<Object> getAllObjFromStatus(int status) throws Exception {
		String SQL = "SELECT id, icra_dosyasi_id, muvekkil_adi, borclu_adi, gelis_tarihi, "
				+ "  borc_tipi, tahsilat_tarihi, tahsilat_tipi, tahsilat_miktari, "
				+ "  tahsilat_statusu, durum, gelis_yeri, onaylayan_id, kasa_personel_id, "
				+ "  dosya_tipi, icra_dosya_no, icra_mudurlugu,izleme_id , vizit_id , odemeplani_id" + " FROM tbl_tahsilat where durum =" + status + ";";

		newConnectDB();

		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		Tahsilat tahsilat;
		ArrayList<Object> list = new ArrayList<Object>();
		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		while (rs.next()) {
			tahsilat = new Tahsilat();
			tahsilat.setId(rs.getInt("id"));
			tahsilat.setBorc_tipi(rs.getString("borc_tipi"));
			tahsilat.setBorclu_adi(rs.getString("borclu_adi"));
			tahsilat.setDosya_tipi(rs.getString("dosya_tipi"));
			tahsilat.setGelis_tarihi(rs.getDate("gelis_tarihi"));
			tahsilat.setIcra_dosya_no(rs.getString("icra_dosya_no"));
			tahsilat.setIcra_dosyasi_id(rs.getInt("icra_dosyasi_id"));
			tahsilat.setIcra_mudurlugu(rs.getString("icra_mudurlugu"));
			tahsilat.setMuvekkil_adi(rs.getString("muvekkil_adi"));
			tahsilat.setTahsilat_miktari(rs.getDouble("tahsilat_miktari"));
			tahsilat.setTahsilat_miktari_tl(defaultFormat.format(rs.getDouble("tahsilat_miktari")));
			tahsilat.setTahsilat_statusu(rs.getString("tahsilat_statusu"));
			tahsilat.setTahsilat_tarihi(rs.getDate("tahsilat_tarihi"));
			tahsilat.setTahsilat_tipi(rs.getString("tahsilat_tipi"));
			tahsilat.setOnaylayanID(rs.getInt("onaylayan_id"));
			tahsilat.setKasaPersonelID(rs.getInt("kasa_personel_id"));
			tahsilat.setGelisYeri(rs.getString("gelis_yeri"));
			tahsilat.setDurum(rs.getInt("durum"));
			tahsilat.setIzleme_id(rs.getInt("izleme_id"));
			tahsilat.setVizit_id(rs.getInt("vizit_id"));
			tahsilat.setOdemeplani_id(rs.getInt("odemeplani_id"));
			list.add(tahsilat);

		}

		disconnectDB();
		return list;
	}

}
