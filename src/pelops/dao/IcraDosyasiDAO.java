package pelops.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.controller.AktifBean;
import pelops.db.DBConnection;
import pelops.model.BorcluBilgisi;
import pelops.model.IcraDosyasi;
import semiramis.operasyon.model.PairLevha;

public class IcraDosyasiDAO extends DBConnection {
	public static PreparedStatement psmt = null;
	public static ResultSet rs = null;

	public void ExcelGuncelle(int id, String icraDosyaNo, String icraMudurlugu) throws Exception {

		try {

			if (icraDosyaNo != null && icraMudurlugu != null) {
				String SQL = "UPDATE tbl_icra_dosyasi SET icra_dosyasi_no=?, icra_mudurlugu_id=? WHERE id=" + id;
				newConnectDB();
				PreparedStatement pstmt = conn.prepareStatement(SQL.toString());

				pstmt.setString(1, icraDosyaNo);
				pstmt.setInt(2, IcraId(icraMudurlugu));

				pstmt.executeUpdate();

			}

		} catch (Exception e) {

			System.out.println("Hata :" + e.getMessage());
			// TODO: handle exception
		} finally {
			disconnectDB();
		}

	}

	public int IcraId(String icraMudurlugu) throws Exception {

		int id = 0;

		if (icraMudurlugu != null || icraMudurlugu != "") {

			try {

				String SQL = "SELECT * FROM tbl_icra_mudurlugu where adi='" + icraMudurlugu + "'";
				Statement stmt;
				ResultSet rs;
				stmt = conn.createStatement();
				rs = stmt.executeQuery(SQL);

				while (rs.next()) {

					id = rs.getInt("id");

				}

			} catch (Exception e) {

				id = 0;

			} finally {

			}

		}

		return id;

	}

	public ArrayList<IcraDosyasi> Listele() throws Exception {

		DBConnection DB = new DBConnection();
		DB.newConnectDB();
		String SQL = "SELECT * FROM tbl_icra_dosyasi";
		Statement stmt;
		ResultSet rs;
		stmt = DB.conn.createStatement();
		rs = stmt.executeQuery(SQL);
		IcraDosyasi icradosyasi;
		ArrayList<IcraDosyasi> IcraDosyasiListesi = new ArrayList<IcraDosyasi>();

		while (rs.next()) {
			icradosyasi = new IcraDosyasi();
			icradosyasi.setId(rs.getInt("id"));
			icradosyasi.setIcraDosyaNo(rs.getString("icra_dosyasi_no"));
			icradosyasi.setVadeTarihi(rs.getDate("vade_tarihi"));
			icradosyasi.setIhtarnameTarihi(rs.getDate("ihtarname_tarihi"));
			icradosyasi.setGelisTarihi(rs.getDate("gelis_tarihi"));
			icradosyasi.setTakipTarihi(rs.getDate("takip_tarihi"));
			icradosyasi.setTebligTarihi(rs.getDate("teblig_tarihi"));
			icradosyasi.setBilaTarihi(rs.getDate("bila_tarihi"));
			icradosyasi.setKesinlesmeTarihi(rs.getDate("kesinlesme_tarihi"));
			icradosyasi.setHitamTarihi(rs.getDate("hitam_tarihi"));
			icradosyasi.setIcraKapanisTarihi(rs.getDate("icra_kapanis_tarihi"));
			icradosyasi.setGidisTarihi(rs.getDate("gidis_tarihi"));
			icradosyasi.setItirazDurumu(rs.getString("itiraz_durumu"));
			icradosyasi.setItirazTarihi(rs.getDate("itiraz_tarihi"));
			icradosyasi.setBankaKapanisTarihi(rs.getDate("banka_kapanis_tarihi"));
			icradosyasi.setItirazTipi(rs.getString("itiraz_tipi"));
			icradosyasi.setIcraMudurluguId(rs.getInt("icra_mudurlugu_id"));
			icradosyasi.setDosyaStatusuId(rs.getInt("dosya_statusu_id"));
			icradosyasi.setDosyaTipiId(rs.getInt("dosya_tipi_id"));
			icradosyasi.setTakipTipiId(rs.getInt("takip_tipi_id"));
			icradosyasi.setTakipYoluId(rs.getInt("takip_yolu_id"));
			icradosyasi.setTakipSekliId(rs.getInt("takip_sekli_id"));
			icradosyasi.setBorcTipiId(rs.getInt("borc_tipi_id"));
			icradosyasi.setBorcaEsasEvrak(rs.getString("borca_esas_evrak"));
			icradosyasi.setTalepEdilenHak(rs.getString("talep_edilen_hak"));
			icradosyasi.setVekaletUcretTipi(rs.getString("vekalet_ucret_tipi"));
			icradosyasi.setRiskYoneticisiId(rs.getInt("risk_yoneticisi_id"));
			icradosyasi.setAvukat_sevis_no(rs.getString("avukat_sevis_no"));
			icradosyasi.setBanka_servis_no(rs.getString("banka_servis_no"));
			icradosyasi.setDiger_alacak_aciklama(rs.getString("diger_alacak_aciklama"));
			icradosyasi.setKKDF(rs.getString("\"KKDF\""));
			icradosyasi.setBSMV(rs.getString("\"BSMV\""));
			icradosyasi.setBK84(rs.getString("\"BK84\""));

			IcraDosyasiListesi.add(icradosyasi);
		}
		DB.disconnectDB();
		return IcraDosyasiListesi;

	}

	public IcraDosyasi Listele(int id) throws Exception {

		DBConnection DB = new DBConnection();
		DB.newConnectDB();
		String SQL = "SELECT * FROM tbl_icra_dosyasi where id=" + id;
		Statement stmt;
		ResultSet rs;
		stmt = DB.conn.createStatement();
		rs = stmt.executeQuery(SQL);
		IcraDosyasi icradosyasi = null;

		while (rs.next()) {
			icradosyasi = new IcraDosyasi();
			icradosyasi.setId(rs.getInt("id"));
			icradosyasi.setIcraDosyaNo(rs.getString("icra_dosyasi_no"));
			icradosyasi.setVadeTarihi(rs.getDate("vade_tarihi"));
			icradosyasi.setIhtarnameTarihi(rs.getDate("ihtarname_tarihi"));
			icradosyasi.setGelisTarihi(rs.getDate("gelis_tarihi"));
			icradosyasi.setTakipTarihi(rs.getDate("takip_tarihi"));
			icradosyasi.setTebligTarihi(rs.getDate("teblig_tarihi"));
			icradosyasi.setBilaTarihi(rs.getDate("bila_tarihi"));
			icradosyasi.setKesinlesmeTarihi(rs.getDate("kesinlesme_tarihi"));
			icradosyasi.setHitamTarihi(rs.getDate("hitam_tarihi"));
			icradosyasi.setIcraKapanisTarihi(rs.getDate("icra_kapanis_tarihi"));
			icradosyasi.setGidisTarihi(rs.getDate("gidis_tarihi"));
			icradosyasi.setItirazDurumu(rs.getString("itiraz_durumu"));
			icradosyasi.setItirazTarihi(rs.getDate("itiraz_tarihi"));
			icradosyasi.setBankaKapanisTarihi(rs.getDate("banka_kapanis_tarihi"));
			icradosyasi.setItirazTipi(rs.getString("itiraz_tipi"));
			icradosyasi.setIcraMudurluguId(rs.getInt("icra_mudurlugu_id"));
			icradosyasi.setDosyaStatusuId(rs.getInt("dosya_statusu_id"));
			icradosyasi.setDosyaTipiId(rs.getInt("dosya_tipi_id"));
			icradosyasi.setTakipTipiId(rs.getInt("takip_tipi_id"));
			icradosyasi.setTakipYoluId(rs.getInt("takip_yolu_id"));
			icradosyasi.setTakipSekliId(rs.getInt("takip_sekli_id"));
			icradosyasi.setBorcTipiId(rs.getInt("borc_tipi_id"));
			icradosyasi.setBorcaEsasEvrak(rs.getString("borca_esas_evrak"));
			icradosyasi.setTalepEdilenHak(rs.getString("talep_edilen_hak"));
			icradosyasi.setVekaletUcretTipi(rs.getString("vekalet_ucret_tipi"));
			icradosyasi.setRiskYoneticisiId(rs.getInt("risk_yoneticisi_id"));
			icradosyasi.setAvukat_sevis_no(rs.getString("avukat_sevis_no"));
			icradosyasi.setBanka_servis_no(rs.getString("banka_servis_no"));
			icradosyasi.setDiger_alacak_aciklama(rs.getString("diger_alacak_aciklama"));
			icradosyasi.setKKDF(rs.getString("KKDF"));
			icradosyasi.setBSMV(rs.getString("BSMV"));
			icradosyasi.setBK84(rs.getString("BK84"));

		}
		DB.disconnectDB();
		return icradosyasi;

	}

	public int IcraDosyaIdGetir() throws Exception {
		DBConnection DB = new DBConnection();
		DB.newConnectDB();
		String SQL = "SELECT * FROM tbl_icra_dosyasi ORDER BY ekleme_tarihi DESC";

		Statement stmt;
		ResultSet rs;
		stmt = DB.conn.createStatement();
		rs = stmt.executeQuery(SQL);
		int id = 0;
		rs.next();
		id = rs.getInt("id");

		DB.disconnectDB();
		return id;
	}

	public int IcraDosyaIdGetir(String IcraDosyaNo, int IcramudurluguId) throws Exception {
		DBConnection DB = new DBConnection();
		DB.newConnectDB();
		String SQL = "SELECT * FROM tbl_icra_dosyasi where icra_dosyasi_no='" + IcraDosyaNo
				+ "' and icra_mudurlugu_id =" + IcramudurluguId;
		Statement stmt;
		ResultSet rs;
		stmt = DB.conn.createStatement();
		rs = stmt.executeQuery(SQL);
		int id = 0;
		while (rs.next()) {
			id = rs.getInt("id");
		}
		DB.disconnectDB();
		return id;
	}

	public int Kaydet(IcraDosyasi icradosyasi) throws Exception {

		String SQL = " INSERT INTO tbl_icra_dosyasi("
				+ " icra_dosyasi_no, vade_tarihi, ihtarname_tarihi, gelis_tarihi, "
				+ " takip_tarihi, teblig_tarihi, bila_tarihi, kesinlesme_tarihi, "
				+ " hitam_tarihi, icra_kapanis_tarihi, gidis_tarihi, itiraz_durumu,"
				+ " itiraz_tarihi, banka_kapanis_tarihi, itiraz_tipi, icra_mudurlugu_id,"
				+ " dosya_statusu_id, dosya_tipi_id, takip_tipi_id, takip_yolu_id,"
				+ " takip_sekli_id, borc_tipi_id, borca_esas_evrak, talep_edilen_hak,"
				+ " vekalet_ucret_tipi, risk_yoneticisi_id,avukat_sevis_no,banka_servis_no,"
				+ " uyap_dosya_tipi, \"KKDF\", \"BSMV\", \"BK84\", uyap_rol_turu, uyap_rol_id , diger_alacak_aciklama , para_birimi , uyap_para_turu "
				+ "  )"
				+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?);";
		DBConnection DB = new DBConnection();
		DB.newConnectDB();

		PreparedStatement pstmt = DB.conn.prepareStatement(SQL.toString());

		pstmt.setString(1, icradosyasi.getIcraDosyaNo());
		pstmt.setDate(2, convertFromJAVADateToSQLDate(icradosyasi.getVadeTarihi()));
		pstmt.setDate(3, convertFromJAVADateToSQLDate(icradosyasi.getIhtarnameTarihi()));
		pstmt.setDate(4, convertFromJAVADateToSQLDate(icradosyasi.getGelisTarihi()));
		pstmt.setDate(5, convertFromJAVADateToSQLDate(icradosyasi.getTakipTarihi()));
		pstmt.setDate(6, convertFromJAVADateToSQLDate(icradosyasi.getTebligTarihi()));
		pstmt.setDate(7, convertFromJAVADateToSQLDate(icradosyasi.getBilaTarihi()));
		pstmt.setDate(8, convertFromJAVADateToSQLDate(icradosyasi.getKesinlesmeTarihi()));
		pstmt.setDate(9, convertFromJAVADateToSQLDate(icradosyasi.getHitamTarihi()));
		pstmt.setDate(10, convertFromJAVADateToSQLDate(icradosyasi.getIcraKapanisTarihi()));
		pstmt.setDate(11, convertFromJAVADateToSQLDate(icradosyasi.getGidisTarihi()));
		pstmt.setString(12, icradosyasi.getItirazDurumu());
		pstmt.setDate(13, convertFromJAVADateToSQLDate(icradosyasi.getItirazTarihi()));
		pstmt.setDate(14, convertFromJAVADateToSQLDate(icradosyasi.getBankaKapanisTarihi()));
		pstmt.setString(15, icradosyasi.getItirazTipi());
		pstmt.setInt(16, icradosyasi.getIcraMudurluguId());
		pstmt.setInt(17, icradosyasi.getDosyaStatusuId());
		pstmt.setInt(18, icradosyasi.getDosyaTipiId());
		pstmt.setInt(19, icradosyasi.getTakipTipiId());
		pstmt.setInt(20, icradosyasi.getTakipYoluId());
		pstmt.setInt(21, icradosyasi.getTakipSekliId());
		pstmt.setInt(22, icradosyasi.getBorcTipiId());
		pstmt.setString(23, icradosyasi.getBorcaEsasEvrak());
		pstmt.setString(24, icradosyasi.getTalepEdilenHak());
		pstmt.setString(25, icradosyasi.getVekaletUcretTipi());
		pstmt.setInt(26, icradosyasi.getRiskYoneticisiId());
		pstmt.setString(27, icradosyasi.getAvukat_sevis_no());
		pstmt.setString(28, icradosyasi.getBanka_servis_no());

		pstmt.setString(29, icradosyasi.getUyap_dosya_tipi());
		pstmt.setString(30, icradosyasi.getKKDF());
		pstmt.setString(31, icradosyasi.getBSMV());
		pstmt.setString(32, icradosyasi.getBK84());
		pstmt.setString(33, icradosyasi.getUyap_rol_turu());
		pstmt.setString(34, icradosyasi.getUyap_rol_id());
		pstmt.setString(35, icradosyasi.getDiger_alacak_aciklama());
		pstmt.setString(36, icradosyasi.getPara_birimi());
		pstmt.setString(37, icradosyasi.getUyap_para_turu());

		pstmt.executeUpdate();

		DB.disconnectDB();

		return IcraDosyaIdGetir();
	}

	public int XMLKaydet(IcraDosyasi icradosyasi) throws Exception {

		String SQL = " INSERT INTO tbl_icra_dosyasi("
				+ " icra_dosyasi_no, vade_tarihi, ihtarname_tarihi, gelis_tarihi, "
				+ " takip_tarihi, teblig_tarihi, bila_tarihi, kesinlesme_tarihi, "
				+ " hitam_tarihi, icra_kapanis_tarihi, gidis_tarihi, itiraz_durumu,"
				+ " itiraz_tarihi, banka_kapanis_tarihi, itiraz_tipi, icra_mudurlugu_id,"
				+ " dosya_statusu_id, dosya_tipi_id, takip_tipi_id, takip_yolu_id,"
				+ " takip_sekli_id, borc_tipi_id, borca_esas_evrak, talep_edilen_hak,"
				+ " vekalet_ucret_tipi, risk_yoneticisi_id,avukat_sevis_no,banka_servis_no,"
				+ " uyap_dosya_tipi, \"KKDF\", \"BSMV\", \"BK84\", uyap_rol_turu, uyap_rol_id , diger_alacak_aciklama , para_birimi , uyap_para_turu "
				+ "  )"
				+ " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?);";
		DBConnection DB = new DBConnection();
		DB.newConnectDB();

		PreparedStatement pstmt = DB.conn.prepareStatement(SQL.toString());

		pstmt.setString(1, icradosyasi.getIcraDosyaNo());
		pstmt.setDate(2, convertFromJAVADateToSQLDate(icradosyasi.getVadeTarihi()));
		pstmt.setDate(3, convertFromJAVADateToSQLDate(icradosyasi.getIhtarnameTarihi()));
		pstmt.setDate(4, convertFromJAVADateToSQLDate(icradosyasi.getGelisTarihi()));
		pstmt.setDate(5, convertFromJAVADateToSQLDate(icradosyasi.getTakipTarihi()));
		pstmt.setDate(6, convertFromJAVADateToSQLDate(icradosyasi.getTebligTarihi()));
		pstmt.setDate(7, convertFromJAVADateToSQLDate(icradosyasi.getBilaTarihi()));
		pstmt.setDate(8, convertFromJAVADateToSQLDate(icradosyasi.getKesinlesmeTarihi()));
		pstmt.setDate(9, convertFromJAVADateToSQLDate(icradosyasi.getHitamTarihi()));
		pstmt.setDate(10, convertFromJAVADateToSQLDate(icradosyasi.getIcraKapanisTarihi()));
		pstmt.setDate(11, convertFromJAVADateToSQLDate(icradosyasi.getGidisTarihi()));
		pstmt.setString(12, icradosyasi.getItirazDurumu());
		pstmt.setDate(13, convertFromJAVADateToSQLDate(icradosyasi.getItirazTarihi()));
		pstmt.setDate(14, convertFromJAVADateToSQLDate(icradosyasi.getBankaKapanisTarihi()));
		pstmt.setString(15, icradosyasi.getItirazTipi());
		pstmt.setInt(16, icradosyasi.getIcraMudurluguId());
		pstmt.setInt(17, icradosyasi.getDosyaStatusuId());
		pstmt.setInt(18, icradosyasi.getDosyaTipiId());
		pstmt.setInt(19, icradosyasi.getTakipTipiId());
		pstmt.setInt(20, icradosyasi.getTakipYoluId());
		pstmt.setInt(21, icradosyasi.getTakipSekliId());
		pstmt.setInt(22, icradosyasi.getBorcTipiId());
		pstmt.setString(23, icradosyasi.getBorcaEsasEvrak());
		pstmt.setString(24, icradosyasi.getTalepEdilenHak());
		pstmt.setString(25, icradosyasi.getVekaletUcretTipi());
		pstmt.setInt(26, icradosyasi.getRiskYoneticisiId());
		pstmt.setString(27, icradosyasi.getAvukat_sevis_no());
		pstmt.setString(28, icradosyasi.getBanka_servis_no());

		pstmt.setString(29, icradosyasi.getUyap_dosya_tipi());
		pstmt.setString(30, icradosyasi.getKKDF());
		pstmt.setString(31, icradosyasi.getBSMV());
		pstmt.setString(32, icradosyasi.getBK84());
		pstmt.setString(33, icradosyasi.getUyap_rol_turu());
		pstmt.setString(34, icradosyasi.getUyap_rol_id());
		pstmt.setString(35, icradosyasi.getDiger_alacak_aciklama());
		pstmt.setString(36, icradosyasi.getPara_birimi());
		pstmt.setString(37, icradosyasi.getUyap_para_turu());

		pstmt.executeUpdate();

		DB.disconnectDB();

		int id = 0;
		DB.newConnectDB();
		String SQLXML = "SELECT * FROM tbl_icra_dosyasi ";
		Statement stmt;
		ResultSet rs;
		stmt = DB.conn.createStatement();
		rs = stmt.executeQuery(SQLXML);
		while (rs.next()) {
			id = rs.getInt("id");
		}
		DB.disconnectDB();

		return id;
	}

	public String getDosyaStatusu(int id) {

		String dosyaStatu = "";

		try {

			String sql = "SELECT id, adi FROM tbl_dosya_statu where id=" + id;

			newConnectDB();

			Statement stmt = conn.createStatement();

			ResultSet set = stmt.executeQuery(sql);

			while (set.next()) {

				dosyaStatu = set.getString("adi").toUpperCase();

			}

		} catch (Exception e) {

			System.out.println("Hata icra dosyası DAO getDosyaStatusu :" + e.getMessage());
			// TODO: handle exception
		}

		return dosyaStatu;

	}

	public PairLevha MalSayisi(int malId, int id) throws Exception {
		DBConnection DB = new DBConnection();
		DB.newConnectDB();
		String SQL = "SELECT COUNT(*) as Adet,haciz_durum FROM tbl_hacze_esas_mal_bilgisi where borclu_id = " + id
				+ " and mal_tipi_id= " + malId + " group by haciz_durum;";
		Statement stmt;
		ResultSet rs;
		stmt = DB.conn.createStatement();

		PairLevha pair = new PairLevha();

		rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			pair.setAdet(rs.getInt("Adet"));
			pair.setDurum(rs.getInt("haciz_durum"));
		}
		DB.disconnectDB();
		return pair;
	}

	public PairLevha vizitDurum(int icraDosyaId) {

		PairLevha pair = new PairLevha();

		
		try {
			
		
		newConnectDB();
		String SQL = "SELECT vizit_statusu " + " FROM tbl_vizit_bilgisi where icra_dosyasi=" + icraDosyaId+"   order by vizit_tarihi desc";
		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();

		
		rs = stmt.executeQuery(SQL);
		
		int adet=0;
		
		while (rs.next()) {
			
			adet++;

			pair.setDurumTxt(rs.getString("vizit_statusu"));
		}

		pair.setAdet(adet);
		
		disconnectDB();
		
		} catch (Exception e) {
			

			System.out.println("Hata icradosyasi DAO vizitdurum :"+e.getMessage());
			
		}
		return pair;
	}

	public String ItirazDurumu(int id) throws Exception {
		DBConnection DB = new DBConnection();
		DB.newConnectDB();
		String SQL = "SELECT  itiraz_durumu FROM tbl_icra_dosyasi where id = " + id + " ";
		Statement stmt;
		ResultSet rs;
		stmt = DB.conn.createStatement();
		rs = stmt.executeQuery(SQL);
		String itiraz = "";
		while (rs.next()) {
			itiraz = rs.getString("itiraz_durumu");
		}
		DB.disconnectDB();
		return itiraz;
	}

	public String BilaDurumu(int id) throws Exception {
		DBConnection DB = new DBConnection();
		DB.newConnectDB();
		String SQL = "SELECT  bila_tarihi FROM tbl_icra_dosyasi where id = " + id + " ";
		Statement stmt;
		ResultSet rs;
		stmt = DB.conn.createStatement();
		rs = stmt.executeQuery(SQL);
		String bila = "";
		while (rs.next()) {
			bila = rs.getString("bila_tarihi") == null ? "H" : "E";
		}
		DB.disconnectDB();

		return bila;
	}

	public String HitamDurumu(int id) throws Exception {
		DBConnection DB = new DBConnection();
		DB.newConnectDB();
		String SQL = "SELECT  hitam_tarihi FROM tbl_icra_dosyasi where id = " + id + " ";
		Statement stmt;
		ResultSet rs;
		stmt = DB.conn.createStatement();
		rs = stmt.executeQuery(SQL);
		String bila = "";
		while (rs.next()) {
			bila = rs.getString("hitam_tarihi") == null ? "H" : "E";
		}
		DB.disconnectDB();

		return bila;
	}

	public int MaasDurumu(int id) throws Exception {
		DBConnection DB = new DBConnection();
		DB.newConnectDB();
		String SQL = "SELECT COUNT(*) as Adet FROM tbl_hacze_esas_mal_bilgisi where icra_dosyasi_id = " + id
				+ " and ( mal_tipi_id= 2 or mal_tipi_id=9 or mal_tipi_id=7);";

		Statement stmt;
		ResultSet rs;
		stmt = DB.conn.createStatement();
		rs = stmt.executeQuery(SQL);
		rs.next();
		int maas = rs.getInt("Adet");
		DB.disconnectDB();
		return maas;
	}

	public int HacizDurumu(int id) throws Exception {
		DBConnection DB = new DBConnection();
		DB.newConnectDB();
		String SQL = "SELECT COUNT(*) as Adet FROM tbl_haciz_bilgisi where icra_dosyasi_id = " + id + ";";
		Statement stmt;
		ResultSet rs;
		stmt = DB.conn.createStatement();
		rs = stmt.executeQuery(SQL);
		rs.next();
		int haciz = rs.getInt("Adet");
		DB.disconnectDB();
		return haciz;
	}

	public int HacizDurumuMaas(int id) throws Exception {
		DBConnection DB = new DBConnection();
		DB.newConnectDB();
		String SQL = "SELECT COUNT(*) as Adet FROM vwHacizLevha where icra_dosyasi_id = " + id
				+ " and ( adi= 'Sgk' or adi='Maa�' or adi='Emekli Maa��');";
		Statement stmt;
		ResultSet rs;
		stmt = DB.conn.createStatement();
		rs = stmt.executeQuery(SQL);
		rs.next();
		int haciz = rs.getInt("Adet");
		DB.disconnectDB();
		return haciz;
	}

	public int HacizDurumuEv(int id) throws Exception {
		DBConnection DB = new DBConnection();
		DB.newConnectDB();
		String SQL = "SELECT COUNT(*) as Adet FROM vwHacizLevha where icra_dosyasi_id = " + id + " and  adi='Ev';";
		Statement stmt;
		ResultSet rs;
		stmt = DB.conn.createStatement();
		rs = stmt.executeQuery(SQL);
		rs.next();
		int haciz = rs.getInt("Adet");
		DB.disconnectDB();
		return haciz;
	}

	public int HacizDurumuAraba(int id) throws Exception {
		DBConnection DB = new DBConnection();
		DB.newConnectDB();
		String SQL = "SELECT COUNT(*) as Adet FROM vwHacizLevha where icra_dosyasi_id = " + id + " and  adi= 'Ara�' ;";
		Statement stmt;
		ResultSet rs;
		stmt = DB.conn.createStatement();
		rs = stmt.executeQuery(SQL);
		rs.next();
		int haciz = rs.getInt("Adet");
		DB.disconnectDB();
		return haciz;
	}

	public void GenelHacizBilgisiGuncelle(IcraDosyasi icradosyasi) throws Exception {
		String SQL = "UPDATE tbl_icra_dosyasi" + "   SET 	icra_dosyasi_no=?," + "	icra_mudurlugu_id=?,"
				+ "	dosya_statusu_id=?," + "	dosya_tipi_id=?," + "	takip_tipi_id=?," + "	takip_yolu_id=?,"
				+ " takip_sekli_id=?," + "	borc_tipi_id=?," + "	talep_edilen_hak=?,"
				+ "	diger_alacak_aciklama=?, \"KKDF\"=?, \"BSMV\"=?, \"BK84\"=? " + "WHERE id=" + icradosyasi.getId();

		DBConnection DB = new DBConnection();
		DB.newConnectDB();
		PreparedStatement pstmt = DB.conn.prepareStatement(SQL.toString());

		pstmt.setString(1, icradosyasi.getIcraDosyaNo());
		pstmt.setInt(2, icradosyasi.getIcraMudurluguId());
		pstmt.setInt(3, icradosyasi.getDosyaStatusuId());
		pstmt.setInt(4, icradosyasi.getDosyaTipiId());
		pstmt.setInt(5, icradosyasi.getTakipTipiId());
		pstmt.setInt(6, icradosyasi.getTakipYoluId());
		pstmt.setInt(7, icradosyasi.getTakipSekliId());
		pstmt.setInt(8, icradosyasi.getBorcTipiId());
		pstmt.setString(9, icradosyasi.getTalepEdilenHak());
		pstmt.setString(10, icradosyasi.getDiger_alacak_aciklama());
		pstmt.setString(11, icradosyasi.getKKDF());
		pstmt.setString(12, icradosyasi.getBSMV());
		pstmt.setString(13, icradosyasi.getBK84());

		pstmt.executeUpdate();

		DB.disconnectDB();
	}

	public void TarihBilgisiGuncelle(IcraDosyasi icradosyasi) throws Exception {

		String SQL = "UPDATE tbl_icra_dosyasi" + "   SET 	vade_tarihi=?," + " ihtarname_tarihi=?,"
				+ "	gelis_tarihi=?," + "	takip_tarihi=?," + "	teblig_tarihi=?," + "	bila_tarihi=?,"
				+ "	kesinlesme_tarihi=?," + "	hitam_tarihi=?," + "	icra_kapanis_tarihi=?," + "	gidis_tarihi=?,"
				+ "	banka_kapanis_tarihi=?," + "	vekalet_ucret_tipi=?," + "	risk_yoneticisi_id=?" + " WHERE id="
				+ icradosyasi.getId();

		DBConnection DB = new DBConnection();
		DB.newConnectDB();
		PreparedStatement pstmt = DB.conn.prepareStatement(SQL.toString());

		pstmt.setDate(1, convertFromJAVADateToSQLDate(icradosyasi.getVadeTarihi()));
		pstmt.setDate(2, convertFromJAVADateToSQLDate(icradosyasi.getIhtarnameTarihi()));
		pstmt.setDate(3, convertFromJAVADateToSQLDate(icradosyasi.getGelisTarihi()));
		pstmt.setDate(4, convertFromJAVADateToSQLDate(icradosyasi.getTakipTarihi()));
		pstmt.setDate(5, convertFromJAVADateToSQLDate(icradosyasi.getTebligTarihi()));
		pstmt.setDate(6, convertFromJAVADateToSQLDate(icradosyasi.getBilaTarihi()));
		pstmt.setDate(7, convertFromJAVADateToSQLDate(icradosyasi.getKesinlesmeTarihi()));
		pstmt.setDate(8, convertFromJAVADateToSQLDate(icradosyasi.getHitamTarihi()));
		pstmt.setDate(9, convertFromJAVADateToSQLDate(icradosyasi.getIcraKapanisTarihi()));
		pstmt.setDate(10, convertFromJAVADateToSQLDate(icradosyasi.getGidisTarihi()));
		pstmt.setDate(11, convertFromJAVADateToSQLDate(icradosyasi.getBankaKapanisTarihi()));
		pstmt.setString(12, icradosyasi.getVekaletUcretTipi());
		pstmt.setInt(13, icradosyasi.getRiskYoneticisiId());

		pstmt.executeUpdate();

		DB.disconnectDB();
	}

	public void hitamTarihiEkle(Date date, int icradosyaid) throws Exception {
		String SQL = "UPDATE tbl_icra_dosyasi SET " + "	hitam_tarihi=? " + " WHERE id=" + icradosyaid;

		System.out.println(SQL);
		DBConnection DB = new DBConnection();
		DB.newConnectDB();
		PreparedStatement pstmt = DB.conn.prepareStatement(SQL.toString());

		pstmt.setDate(1, convertFromJAVADateToSQLDate(date));

		pstmt.executeUpdate();

		DB.disconnectDB();
	}

	public void ItirazBilgisiGuncelle(IcraDosyasi icradosyasi) throws Exception {

		String SQL = "UPDATE tbl_icra_dosyasi" + " SET itiraz_durumu=?," + " itiraz_tarihi=?," + " itiraz_tipi=?"
				+ " WHERE id=" + icradosyasi.getId();

		DBConnection DB = new DBConnection();
		DB.newConnectDB();
		PreparedStatement pstmt = DB.conn.prepareStatement(SQL.toString());

		pstmt.setString(1, icradosyasi.getItirazDurumu());
		pstmt.setDate(2, convertFromJAVADateToSQLDate(icradosyasi.getItirazTarihi()));
		pstmt.setString(3, icradosyasi.getItirazTipi());

		pstmt.executeUpdate();

		DB.disconnectDB();
	}

	public void BorcluBilgisiGuncelle(BorcluBilgisi borcluBilgisi) throws Exception {

		String SQL = "UPDATE tbl_borclu SET  ad_soyad= ?," + " musteri_no= ?, " + " adres= ?, "
				+ " telefon_no2= ?, tc_no= ? " + " WHERE id = ?";

		DBConnection DB1 = new DBConnection();
		DB1.newConnectDB();

		PreparedStatement pstmt1 = DB1.conn.prepareStatement(SQL.toString());

		pstmt1.setString(1, borcluBilgisi.getAdSoyad());
		pstmt1.setString(2, borcluBilgisi.getMusteriNo());
		pstmt1.setString(3, borcluBilgisi.getAdres());
		pstmt1.setString(4, borcluBilgisi.getTelefon_no2());
		pstmt1.setString(5, borcluBilgisi.getTcNo());
		pstmt1.setInt(6, AktifBean.getBorcluId());

		pstmt1.executeUpdate();
		DB1.disconnectDB();

	}

	public void Sil(int id) {

	}

	public java.sql.Date convertFromJAVADateToSQLDate(java.util.Date javaDate) {
		java.sql.Date sqlDate = null;
		if (javaDate != null) {
			sqlDate = new Date(javaDate.getTime());
		}
		return sqlDate;
	}

	// public static void main(String[] args) throws SQLException {
	// IcraDosyasiDAO icd = new IcraDosyasiDAO();
	// System.out.println( icd.MalSayisi("Ara�",1));
	//
	//
	// }
	//
}
