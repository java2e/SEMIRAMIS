
package pelops.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.controller.AktifBean;
import pelops.db.DBConnection;
import pelops.model.HaczeEsasMalBilgisi;
import semiramis.operasyon.controller.Utils;
import semiramis.operasyon.model.ChronologyIdentifier;

public class HaczeEsasMalBilgisiDAO extends DBConnection {

	PreparedStatement pstm;
	String SQL = "";
	Statement stmt;
	ResultSet rs;
	Utils utils = new Utils();

	public boolean kaydet(HaczeEsasMalBilgisi haczeEsasMalBilgisi) throws Exception {

		boolean kaydedildi = false;

		SQL = "INSERT INTO tbl_hacze_esas_mal_bilgisi("
				+ " borclu_id, menkul_bilgisi, tapu_il_adi, tapu_ilce_adi, tapu_mahalle_adi, "
				+ "tapu_mulk_tipi, tapu_parsel, tapu_sayfa_no, tapu_cilt_no, "
				+ "arac_plaka_no, arac_aractipi, banka_hesap_no, muhatap_adi, muhatap_adresi, diger_bilgiler,"
				+ " mal_tutari, icra_dosyasi_id, mal_tipi, mevduat_bilgisi)" + " VALUES ( ?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, " + " ?, ?, ?, ?, ?,  ?, ?, ?, ?);";

		newConnectDB();

		pstm = conn.prepareStatement(SQL);

		pstm.setInt(1, AktifBean.getBorcluId());
		pstm.setString(2, haczeEsasMalBilgisi.getMenkulBilgisi());
		pstm.setString(3, haczeEsasMalBilgisi.getIlAdi());
		pstm.setString(4, haczeEsasMalBilgisi.getIlceAdi());
		pstm.setString(5, haczeEsasMalBilgisi.getTapuMahalleAdi());
		pstm.setString(6, haczeEsasMalBilgisi.getTapuMulkTipi());
		pstm.setString(7, haczeEsasMalBilgisi.getTapuParsel());
		pstm.setString(8, haczeEsasMalBilgisi.getTapuSayfaNo());
		pstm.setString(9, haczeEsasMalBilgisi.getTapuCiltNo());
		pstm.setString(10, haczeEsasMalBilgisi.getAracPlakaNo());
		pstm.setString(11, haczeEsasMalBilgisi.getAracAracTipi());
		pstm.setString(12, haczeEsasMalBilgisi.getBankaHesapNo());
		pstm.setString(13, haczeEsasMalBilgisi.getMuhatapAdi());
		pstm.setString(14, haczeEsasMalBilgisi.getMuhatapAdresi());
		pstm.setString(15, haczeEsasMalBilgisi.getDigerBilgiler());
		pstm.setInt(16, haczeEsasMalBilgisi.getMalTutari());
		pstm.setInt(17, AktifBean.getIcraDosyaID());
		pstm.setString(18, haczeEsasMalBilgisi.getMalTipi());
		pstm.setString(19, haczeEsasMalBilgisi.getMevduatBilgisi());

		int sonuc = pstm.executeUpdate();
		disconnectDB();
		if (sonuc == 1) {
			kaydedildi = true;
			utils.saveChronology(AktifBean.getIcraDosyaID(), ChronologyIdentifier.ISLEM_HESAP,
					utils.getBocluAdi() + " Borçluya hacze esas mal bilgisi eklendi Mal Bilgisi: "
							+ haczeEsasMalBilgisi.getMenkulBilgisi());
		}
		return kaydedildi;
	}

	public boolean egmkaydet(HaczeEsasMalBilgisi haczeEsasMalBilgisi) throws Exception {

		boolean kaydedildi = false;

		SQL = "INSERT INTO tbl_hacze_esas_mal_bilgisi("
				+ " borclu_id, menkul_bilgisi, tapu_il_adi, tapu_ilce_adi, tapu_mahalle_adi, "
				+ "tapu_mulk_tipi, tapu_parsel, tapu_sayfa_no, tapu_cilt_no, "
				+ "arac_plaka_no, arac_aractipi, banka_hesap_no, muhatap_adi, muhatap_adresi, diger_bilgiler,"
				+ " mal_tutari, icra_dosyasi_id, mal_tipi, mevduat_bilgisi)" + " VALUES ( ?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, " + " ?, ?, ?, ?, ?,  ?, ?, ?, ?);";

		newConnectDB();

		pstm = conn.prepareStatement(SQL);

		pstm.setInt(1, haczeEsasMalBilgisi.getBorcluId());
		pstm.setString(2, haczeEsasMalBilgisi.getMenkulBilgisi());
		pstm.setString(3, haczeEsasMalBilgisi.getIlAdi());
		pstm.setString(4, haczeEsasMalBilgisi.getIlceAdi());
		pstm.setString(5, haczeEsasMalBilgisi.getTapuMahalleAdi());
		pstm.setString(6, haczeEsasMalBilgisi.getTapuMulkTipi());
		pstm.setString(7, haczeEsasMalBilgisi.getTapuParsel());
		pstm.setString(8, haczeEsasMalBilgisi.getTapuSayfaNo());
		pstm.setString(9, haczeEsasMalBilgisi.getTapuCiltNo());
		pstm.setString(10, haczeEsasMalBilgisi.getAracPlakaNo());
		pstm.setString(11, haczeEsasMalBilgisi.getAracAracTipi());
		pstm.setString(12, haczeEsasMalBilgisi.getBankaHesapNo());
		pstm.setString(13, haczeEsasMalBilgisi.getMuhatapAdi());
		pstm.setString(14, haczeEsasMalBilgisi.getMuhatapAdresi());
		pstm.setString(15, haczeEsasMalBilgisi.getDigerBilgiler());
		pstm.setInt(16, haczeEsasMalBilgisi.getMalTutari());
		pstm.setInt(17, haczeEsasMalBilgisi.getIcraDosyaId());
		pstm.setString(18, haczeEsasMalBilgisi.getMalTipi());
		pstm.setString(19, haczeEsasMalBilgisi.getMevduatBilgisi());

		int sonuc = pstm.executeUpdate();
		disconnectDB();
		if (sonuc == 1) {
			kaydedildi = true;
		}
		
		return kaydedildi;
	}

	public ArrayList<HaczeEsasMalBilgisi> getAllListFromIcraDosyaID(int id) throws Exception {

		ArrayList<HaczeEsasMalBilgisi> list = new ArrayList<HaczeEsasMalBilgisi>();

		SQL = "SELECT id, borclu_id, menkul_bilgisi, tapu_il_adi," + " tapu_ilce_adi, tapu_mahalle_adi, "
				+ "tapu_mulk_tipi, tapu_parsel, tapu_sayfa_no," + " tapu_cilt_no, arac_plaka_no,"
				+ " arac_aractipi, banka_hesap_no, muhatap_adi, muhatap_adresi, " + "diger_bilgiler,"
				+ " mal_tutari, icra_dosyasi_id, mal_tipi, mevduat_bilgisi"
				+ " FROM tbl_hacze_esas_mal_bilgisi where borclu_id =" + id + " ;";

		newConnectDB();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			HaczeEsasMalBilgisi h = new HaczeEsasMalBilgisi();
			h.setId(rs.getInt("id"));
			h.setMenkulBilgisi(rs.getString("menkul_bilgisi"));
			h.setIlAdi(rs.getString("tapu_il_adi"));
			h.setIlceAdi(rs.getString("tapu_ilce_adi"));
			h.setTapuMahalleAdi(rs.getString("tapu_mahalle_adi"));
			h.setTapuMulkTipi(rs.getString("tapu_mulk_tipi"));
			h.setTapuParsel(rs.getString("tapu_parsel"));
			h.setTapuSayfaNo(rs.getString("tapu_sayfa_no"));
			h.setTapuCiltNo(rs.getString("tapu_cilt_no"));
			h.setAracPlakaNo(rs.getString("arac_plaka_no"));
			h.setAracAracTipi(rs.getString("arac_aractipi"));
			h.setBankaHesapNo(rs.getString("banka_hesap_no"));
			h.setMuhatapAdi(rs.getString("muhatap_adi"));
			h.setDigerBilgiler(rs.getString("diger_bilgiler"));
			h.setMalTutari(rs.getInt("mal_tutari"));
			h.setMalTipi(rs.getString("mal_tipi"));
			h.setMevduatBilgisi(rs.getString("mevduat_bilgisi"));

			list.add(h);
		}

		disconnectDB();

		return list;

	}

	public boolean guncelle(HaczeEsasMalBilgisi hb) throws Exception {

		boolean duzenlendi = false;

		SQL = "UPDATE tbl_hacze_esas_mal_bilgisi SET "
				+ " borclu_id=?, menkul_bilgisi=?, tapu_il_adi=?, tapu_ilce_adi=?, "
				+ "tapu_mahalle_adi=?, tapu_mulk_tipi=?, tapu_parsel=?, tapu_sayfa_no=?, "
				+ " tapu_cilt_no=?, arac_plaka_no=?, arac_aractipi=?, banka_hesap_no=?, "
				+ "muhatap_adi=?, muhatap_adresi=?, diger_bilgiler=?, mal_tutari=?, "
				+ " icra_dosyasi_id=?, mal_tipi=?, mevduat_bilgisi=?" + " WHERE id = " + hb.getId() + ";";

		newConnectDB();

		pstm = conn.prepareStatement(SQL);

		pstm.setInt(1, AktifBean.getBorcluId());
		pstm.setString(2, hb.getMenkulBilgisi());
		pstm.setString(3, hb.getIlAdi());
		pstm.setString(4, hb.getIlceAdi());
		pstm.setString(5, hb.getTapuMahalleAdi());
		pstm.setString(6, hb.getTapuMulkTipi());
		pstm.setString(7, hb.getTapuParsel());
		pstm.setString(8, hb.getTapuSayfaNo());
		pstm.setString(9, hb.getTapuCiltNo());
		pstm.setString(10, hb.getAracPlakaNo());
		pstm.setString(11, hb.getAracAracTipi());
		pstm.setString(12, hb.getBankaHesapNo());
		pstm.setString(13, hb.getMuhatapAdi());
		pstm.setString(14, hb.getMuhatapAdresi());
		pstm.setString(15, hb.getDigerBilgiler());
		pstm.setInt(16, hb.getMalTutari());
		pstm.setInt(17, AktifBean.getIcraDosyaID());
		pstm.setString(18, hb.getMalTipi());
		pstm.setString(19, hb.getMevduatBilgisi());

		int sonuc = pstm.executeUpdate();
		disconnectDB();

		if (sonuc == 1) {

			duzenlendi = true;
		}

		return duzenlendi;
	}

	public int Sil(int id) throws Exception {

		SQL = "DELETE FROM tbl_hacze_esas_mal_bilgisi where id=" + id;
		newConnectDB();

		stmt = conn.createStatement();
		int silindi = stmt.executeUpdate(SQL);
		disconnectDB();

		return silindi;
	}

}
