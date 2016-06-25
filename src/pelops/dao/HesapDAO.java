package pelops.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pelops.db.DBConnection;
import pelops.kasa.controller.KasaCtrl;
import pelops.model.Hesap;

public class HesapDAO extends DBConnection {

	public Hesap Liste(int id) throws Exception {

		DBConnection DB = new DBConnection();
		DB.newConnectDB();
		String SQL = "SELECT * FROM tbl_hesap where id=" + id;

		Statement stmt;
		ResultSet rs;
		stmt = DB.conn.createStatement();
		rs = stmt.executeQuery(SQL);
		Hesap hesap = null;

		while (rs.next()) {
			hesap = new Hesap();
			hesap.setIcra_dosyasi(rs.getInt("icra_dosyasi"));
			hesap.setAsil_alacak(rs.getDouble("asil_alacak"));
			hesap.setGecikme_faizi(rs.getDouble("gecikme_faizi"));
			hesap.setTemerrut_faizi(rs.getDouble("temerrut_faizi"));
			hesap.setFaiz_gider_vergisi(rs.getDouble("faiz_gider_vergisi"));
			hesap.setNoter_masrafi(rs.getDouble("noter_masrafi"));
			hesap.setTakip_alacagi(rs.getDouble("takip_alacagi"));
			hesap.setVekalet_ucreti(rs.getDouble("vekalet_ucreti"));
			hesap.setTakip_faizi(rs.getDouble("takip_faizi"));
			hesap.setIndirim_faiz_orani(rs.getDouble("indirim_faiz_orani"));
			hesap.setTakip_faiz_gider_vergi(rs.getDouble("takip_faiz_gider_vergi"));
			hesap.setDiger_harclar(rs.getDouble("diger_harclar"));
			hesap.setMasraf_tutari(rs.getDouble("masraf_tutari"));
			hesap.setTahsil_harci(rs.getDouble("tahsil_harci"));
			hesap.setToplam_alacak(rs.getDouble("toplam_alacak"));
			hesap.setTahsilat_tutari(rs.getDouble("tahsilat_tutari"));
			hesap.setIndirim_tutari(rs.getDouble("indirim_tutari"));
			hesap.setKalan_alacak(rs.getDouble("kalan_alacak"));
			hesap.setTemerrut_faiz_orani(rs.getDouble("temerrut_faiz_orani"));
			hesap.setAkdi_faiz_orani(rs.getDouble("akdi_faiz_orani"));
			hesap.setUrunAdi(rs.getString("urun_adi"));
			hesap.setUrunNo(rs.getString("urun_no"));

		}
		DB.disconnectDB();
		return hesap;

	}

	public int HesapIdGetir(int IcraDosyaNo) throws Exception {
		newConnectDB();
		String SQL = "SELECT * FROM tbl_hesap where icra_dosyasi=" + IcraDosyaNo;
		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		int id = 0;
		while (rs.next()) {
			id = rs.getInt("id");
		}
		disconnectDB();
		return id;
	}

	public int Kaydet(Hesap hesap) throws Exception {

		String SQL = "INSERT INTO tbl_hesap(icra_dosyasi, asil_alacak, gecikme_faizi, temerrut_faizi,"
				+ " faiz_gider_vergisi, noter_masrafi, takip_alacagi, vekalet_ucreti,"
				+ " takip_faizi, indirim_faiz_orani, takip_faiz_gider_vergi, diger_harclar,"
				+ " masraf_tutari, tahsil_harci, toplam_alacak, tahsilat_tutari,"
				+ " indirim_tutari, kalan_alacak, akdi_faiz_orani, temerrut_faiz_orani,"
				+ " alacak_kalemi2_kalem_kod_turu, alacak_kalemi2_kalem_kod_aciklama, alacak_kalemi2_alacak_kalem_adi,"
				+ " alacak_kalemi3_kalem_kod_turu, alacak_kalemi3_kalem_kod, alacak_kalemi3_kalem_kod_aciklama,"
				+ " alacak_kalemi3_alacak_kalem_adi, alacak_kalemi4_kalem_kod_turu, alacak_kalemi4_kalem_kod_aciklama,"
				+ " alacak_kalemi4_alacak_kalem_adi, alacak_kalemi4_kalem_kod, alacak_kalemi5_kalem_kod_turu,"
				+ " alacak_kalemi5_kalem_kod_aciklama, alacak_kalemi5_alacak_kalem_adi, alacak_kalemi5_kalem_kod, urun_adi,urun_no"
				+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); ";

		newConnectDB();

		PreparedStatement pstmt = conn.prepareStatement(SQL.toString());

		pstmt.setInt(1, hesap.getIcra_dosyasi());
		pstmt.setDouble(2, hesap.getAsil_alacak());
		pstmt.setDouble(3, hesap.getGecikme_faizi());
		pstmt.setDouble(4, hesap.getTemerrut_faizi());
		pstmt.setDouble(5, hesap.getFaiz_gider_vergisi());
		pstmt.setDouble(6, hesap.getNoter_masrafi());
		pstmt.setDouble(7, hesap.getTakip_alacagi());
		pstmt.setDouble(8, hesap.getVekalet_ucreti());
		pstmt.setDouble(9, hesap.getTakip_faizi());
		pstmt.setDouble(10, hesap.getIndirim_faiz_orani());
		pstmt.setDouble(11, hesap.getTakip_faiz_gider_vergi());
		pstmt.setDouble(12, hesap.getDiger_harclar());
		pstmt.setDouble(13, hesap.getMasraf_tutari());
		pstmt.setDouble(14, hesap.getTahsil_harci());
		pstmt.setDouble(15, hesap.getToplam_alacak());
		pstmt.setDouble(16, hesap.getTahsilat_tutari());
		pstmt.setDouble(17, hesap.getIndirim_tutari());
		pstmt.setDouble(18, hesap.getKalan_alacak());
		pstmt.setDouble(19, hesap.getAkdi_faiz_orani());
		pstmt.setDouble(20, hesap.getTemerrut_faiz_orani());
		pstmt.setString(21, hesap.getAlacak_kalemi2_kalem_kod_turu());
		pstmt.setString(22, hesap.getAlacak_kalemi2_kalem_kod_aciklama());
		pstmt.setString(23, hesap.getAlacak_kalemi2_alacak_kalem_adi());
		pstmt.setString(24, hesap.getAlacak_kalemi3_kalem_kod_turu());
		pstmt.setString(25, hesap.getAlacak_kalemi3_kalem_kod());
		pstmt.setString(26, hesap.getAlacak_kalemi3_kalem_kod_aciklama());
		pstmt.setString(27, hesap.getAlacak_kalemi3_alacak_kalem_adi());
		pstmt.setString(28, hesap.getAlacak_kalemi4_kalem_kod_turu());
		pstmt.setString(29, hesap.getAlacak_kalemi4_kalem_kod_aciklama());
		pstmt.setString(30, hesap.getAlacak_kalemi4_alacak_kalem_adi());
		pstmt.setString(31, hesap.getAlacak_kalemi4_kalem_kod());
		pstmt.setString(32, hesap.getAlacak_kalemi5_kalem_kod_turu());
		pstmt.setString(33, hesap.getAlacak_kalemi5_kalem_kod_aciklama());
		pstmt.setString(34, hesap.getAlacak_kalemi5_alacak_kalem_adi());
		pstmt.setString(35, hesap.getAlacak_kalemi5_kalem_kod());
		pstmt.setString(36, hesap.getUrunAdi());
		pstmt.setString(37, hesap.getUrunNo());
		

		pstmt.executeUpdate();
		disconnectDB();

		return HesapIdGetir(hesap.getIcra_dosyasi());
	}

	public void Gucelle(Hesap hesap) throws Exception {
		if (hesap.getId() == 99) {
		} else {
			String SQL = "UPDATE tbl_hesap" + "  SET icra_dosyasi=?, asil_alacak=?, gecikme_faizi=?, temerrut_faizi=?,"
					+ "  faiz_gider_vergisi=?, noter_masrafi=?, takip_alacagi=?, vekalet_ucreti=?,"
					+ "  takip_faizi=?, indirim_faiz_orani=?, takip_faiz_gider_vergi=?,"
					+ "  diger_harclar=?, masraf_tutari=?, tahsil_harci=?, toplam_alacak=?,"
					+ "  tahsilat_tutari=?, indirim_tutari=?, kalan_alacak=? WHERE id=" + hesap.getId() + ";";

			newConnectDB();

			PreparedStatement pstmt = conn.prepareStatement(SQL.toString());

			pstmt.setInt(1, hesap.getIcra_dosyasi());
			pstmt.setDouble(2, hesap.getAsil_alacak());
			pstmt.setDouble(3, hesap.getGecikme_faizi());
			pstmt.setDouble(4, hesap.getTemerrut_faizi());
			pstmt.setDouble(5, hesap.getFaiz_gider_vergisi());
			pstmt.setDouble(6, hesap.getNoter_masrafi());
			pstmt.setDouble(7, hesap.getTakip_alacagi());
			pstmt.setDouble(8, hesap.getVekalet_ucreti());
			pstmt.setDouble(9, hesap.getTakip_faizi());
			pstmt.setDouble(10, hesap.getIndirim_faiz_orani());
			pstmt.setDouble(11, hesap.getTakip_faiz_gider_vergi());
			pstmt.setDouble(12, hesap.getDiger_harclar());
			pstmt.setDouble(13, hesap.getMasraf_tutari());
			pstmt.setDouble(14, hesap.getTahsil_harci());
			pstmt.setDouble(15, hesap.getToplam_alacak());
			pstmt.setDouble(16, hesap.getTahsilat_tutari());
			pstmt.setDouble(17, hesap.getIndirim_tutari());
			pstmt.setDouble(18, hesap.getKalan_alacak());

			pstmt.executeUpdate();
			disconnectDB();
		}

	}

	public void GuncelleTahsilat(int id, double tahsilatTutari) throws Exception {
		String SQL = "UPDATE tbl_hesap SET" + " tahsilat_tutari=?, kalan_alacak=? WHERE id=" + id + ";";

		newConnectDB();

		PreparedStatement pstmt = conn.prepareStatement(SQL.toString());
		double tahsilkalan = Liste(id).getTahsilat_tutari() + tahsilatTutari;
		pstmt.setDouble(1, tahsilkalan);
		double kalan = Liste(id).getKalan_alacak() - tahsilatTutari;
		pstmt.setDouble(2, kalan);

		pstmt.executeUpdate();
		disconnectDB();

	}

	public void GuncelleUrun(int id, String UrunAdi, String UrunNo) throws Exception {
		String SQL = "UPDATE tbl_hesap SET" + " urun_adi=?, urun_no=? WHERE id=" + id + ";";

		newConnectDB();

		PreparedStatement pstmt = conn.prepareStatement(SQL.toString());
		pstmt.setString(1, UrunAdi);
		pstmt.setString(2, UrunNo);

		pstmt.executeUpdate();
		disconnectDB();

	}
	
	public void guncelleAlacak(int id, double alacaktutari) {
		String SQL = "UPDATE tbl_hesap SET" + "  kalan_alacak=?, toplam_alacak=? WHERE id=" + id + ";";
		BaglantiDAO dao = new BaglantiDAO();
		try {
			Hesap hesap = Liste(dao.Listele(id).getHesaplamaID());
			newConnectDB();
			PreparedStatement pstm = conn.prepareStatement(SQL);
			double kalan = hesap.getKalan_alacak() + alacaktutari;
			pstm.setDouble(1, kalan);
			double toplam = hesap.getToplam_alacak() + alacaktutari;
			pstm.setDouble(2, toplam);

			pstm.executeUpdate();
			disconnectDB();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void guncelleHarc(int id, double harcTutari) {
		String SQL = "UPDATE tbl_hesap SET" + " diger_harclar=?, kalan_alacak=?, toplam_alacak=? WHERE id=" + id + ";";
		BaglantiDAO dao = new BaglantiDAO();
		try {
			Hesap hesap = Liste(dao.Listele(id).getHesaplamaID());
			newConnectDB();
			PreparedStatement pstm = conn.prepareStatement(SQL);
			double newHarc = hesap.getDiger_harclar() + harcTutari;
			pstm.setDouble(1, newHarc);
			double kalan = hesap.getKalan_alacak() + harcTutari;
			pstm.setDouble(2, kalan);
			double toplam = hesap.getToplam_alacak() + harcTutari;
			pstm.setDouble(3, toplam);

			pstm.executeUpdate();
			disconnectDB();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void guncelleMasraf(int id, double masrafTutari) {
		String SQLupdate = "UPDATE tbl_hesap SET" + " masraf_tutari=?, kalan_alacak=?, toplam_alacak=? WHERE id=" + id
				+ ";";
		try {

			Hesap hesap = Liste(id);
			newConnectDB();

			PreparedStatement pstmtMasraf = conn.prepareStatement(SQLupdate);
			System.out.println(hesap.getKalan_alacak());
			double masraf = hesap.getMasraf_tutari() + masrafTutari;
			pstmtMasraf.setDouble(1, masraf);
			double kalan = hesap.getKalan_alacak() + masrafTutari;
			pstmtMasraf.setDouble(2, kalan);
			double toplam = hesap.getToplam_alacak() + masrafTutari;
			pstmtMasraf.setDouble(3, toplam);

			pstmtMasraf.executeUpdate();
			disconnectDB();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void SilTahsilat(int id, double tahsilatTutari) throws Exception {
		String SQL = "UPDATE tbl_hesap SET" + " tahsilat_tutari=?, kalan_alacak=? WHERE id=" + id + ";";

		newConnectDB();

		PreparedStatement pstmt = conn.prepareStatement(SQL.toString());
		double tahsilkalan = Liste(id).getTahsilat_tutari() - tahsilatTutari;
		pstmt.setDouble(1, tahsilkalan);
		double kalan = Liste(id).getKalan_alacak() + tahsilatTutari;
		pstmt.setDouble(2, kalan);

		pstmt.executeUpdate();
		disconnectDB();

	}

	public static void main(String[] args) {
		HesapDAO dao = new HesapDAO();
		try {
			// System.out.println(dao.Liste(13044).getToplam_alacak());
			KasaCtrl ctrl = new KasaCtrl();
			ctrl.printTahsilatMakbuzu(10);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
