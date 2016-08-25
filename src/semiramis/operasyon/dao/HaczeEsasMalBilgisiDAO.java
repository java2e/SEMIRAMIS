package semiramis.operasyon.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.a.a.a.b.h;

import pelops.controller.AktifBean;
import pelops.db.DBConnection;
import pelops.users.Util;
import semimis.utils.IDAO;
import semiramis.operasyon.controller.Utils;
import semiramis.operasyon.model.ChronologyIdentifier;
import semiramis.operasyon.model.HaczeEsasMalBilgisi;
import semiramis.operasyon.model.HaczeEsasMalBilgisiView;

public class HaczeEsasMalBilgisiDAO extends DBConnection implements IDAO<HaczeEsasMalBilgisi> {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public boolean kaydet(HaczeEsasMalBilgisi t) {

		java.sql.Timestamp guncellemeZamani = null;

		try {

			guncellemeZamani = new java.sql.Timestamp(new java.util.Date().getTime());

			String sql = "INSERT INTO tbl_hacze_esas_mal_bilgisi( "
					+ " borclu_id, menkul_bilgisi, tapu_mahalle_adi, tapu_parsel,  "
					+ " tapu_sayfa_no, tapu_cilt_no, arac_plaka_no, arac_aractipi, banka_hesap_no,  "
					+ " muhatap_adi, muhatap_adresi, diger_bilgiler, mal_tutari, icra_dosyasi_id,  "
					+ " mevduat_bilgisi, tapu_il_id,  "
					+ " tapu_ilce_id, mulk_tipi_id, arac_tipi_id, mal_tipi_id, guncelleyen_kullanici_id, ekleme_tarihi, guncelleme_tarihi,haciz_durum,tapu_ada,tapu_aciklama) "
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,  " + " ?, ?,now(),now(),1,?,?); ";

			newConnectDB();

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, AktifBean.getBorcluId());
			stmt.setString(2, t.getMenkulBilgisi());
			stmt.setString(3, t.getTapuMahalleAdi());
			stmt.setString(4, t.getTapuParsel());
			stmt.setString(5, t.getTapuSayfaNo());
			stmt.setString(6, t.getTapuCiltNo());
			stmt.setString(7, t.getAracPlakaNo());
			stmt.setString(8, t.getAracAracTipi());
			stmt.setString(9, t.getBankaHesapNo());
			stmt.setString(10, t.getMuhatapAdi());
			stmt.setString(11, t.getMuhatapAdresi());
			stmt.setString(12, t.getDigerBilgiler());
			stmt.setInt(13, t.getMalTutari());
			stmt.setInt(14, AktifBean.getIcraDosyaID());
			stmt.setString(15, t.getMevduatBilgisi());
			stmt.setInt(16, t.getTapuIlId());
			stmt.setInt(17, t.getTapuIlceId());
			stmt.setInt(18, t.getMulkTipiId());
			stmt.setInt(19, t.getAracTipiId());
			stmt.setInt(20, t.getMalTipiId());
			stmt.setInt(21, Util.getUser().getUsrId());
			stmt.setString(22, t.getTapuAda());
			stmt.setString(23, t.getTapuAciklama());

			int sonuc = stmt.executeUpdate();

			disconnectDB();

			if (sonuc == 1) {

				new Utils().saveChronology(AktifBean.getIcraDosyaID(), ChronologyIdentifier.ISLEM_HESAP,
						new Utils().getBocluAdi() + " Borçluya hacze esas mal bilgisi eklendi Mal Bilgisi: "
								+ t.getMenkulBilgisi());
			}

			if (sonuc == 1)
				
				return true;
			else
				return false;

		} catch (Exception e) {

			System.out.println("Hata HaczeEsasMalBilgisiDAO kaydet :" + e.getMessage());

			return false;

		}

	}

	@Override
	public boolean guncelleme(HaczeEsasMalBilgisi t) {

		try {

			String sql = "UPDATE tbl_hacze_esas_mal_bilgisi "
					+ " SET  borclu_id=?, menkul_bilgisi=?, tapu_mahalle_adi=?, tapu_parsel=?, "
					+ " tapu_sayfa_no=?, tapu_cilt_no=?, arac_plaka_no=?, arac_aractipi=?, "
					+ " banka_hesap_no=?, muhatap_adi=?, muhatap_adresi=?, diger_bilgiler=?, "
					+ " mal_tutari=?, icra_dosyasi_id=?, mevduat_bilgisi=?,  "
					+ " guncelleme_tarihi=?, tapu_il_id=?, tapu_ilce_id=?, mulk_tipi_id=?,  "
					+ " arac_tipi_id=?, mal_tipi_id=?, guncelleyen_kullanici_id=?,tapu_ada=?,tapu_aciklama=?" + " WHERE borclu_id="
					+ t.getBorcluId();

			newConnectDB();

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, AktifBean.getBorcluId());
			stmt.setString(2, t.getMenkulBilgisi());
			stmt.setString(3, t.getTapuMahalleAdi());
			stmt.setString(4, t.getTapuParsel());
			stmt.setString(5, t.getTapuSayfaNo());
			stmt.setString(6, t.getTapuCiltNo());
			stmt.setString(7, t.getAracPlakaNo());
			stmt.setString(8, t.getAracAracTipi());
			stmt.setString(9, t.getBankaHesapNo());
			stmt.setString(10, t.getMuhatapAdi());
			stmt.setString(11, t.getMuhatapAdresi());
			stmt.setString(12, t.getDigerBilgiler());
			stmt.setInt(13, t.getMalTutari());
			stmt.setInt(14, AktifBean.getIcraDosyaID());
			stmt.setString(15, t.getMevduatBilgisi());
			stmt.setDate(16, new java.sql.Date(new java.util.Date().getTime()));
			stmt.setInt(17, t.getTapuIlId());
			stmt.setInt(18, t.getTapuIlceId());
			stmt.setInt(19, t.getMulkTipiId());
			stmt.setInt(20, t.getAracTipiId());
			stmt.setInt(21, t.getMalTipiId());
			stmt.setInt(22, Util.getUser().getUsrId());
			stmt.setString(23, t.getTapuAda());
			stmt.setString(24, t.getTapuAciklama());

			int sonuc = stmt.executeUpdate();

			disconnectDB();

			if (sonuc == 1)
				return true;
			else
				return false;

		} catch (Exception e) {

			System.out.println("Hata hacze esas mal bilgisi guncelleme :" + e.getMessage());

			return false;

		}

	}

	@Override
	public boolean sil(int t) {

		try {

			String sql = "DELETE FROM tbl_hacze_esas_mal_bilgisi WHERE id=" + t;

			newConnectDB();

			Statement stmt = conn.createStatement();

			int sonuc = stmt.executeUpdate(sql);

			disconnectDB();

			if (sonuc == 1)
				return true;
			else
				return false;

		}

		catch (Exception e) {

			System.out.println("Hata hacze esas malbilgisi sil :" + e.getMessage());

			return false;

		}

	}

	@Override
	public HaczeEsasMalBilgisi getT(int id) {

		HaczeEsasMalBilgisi haczeEsasMalBilgisi = null;

		try {

			String sql = "select hcz.*,mt.adi as mulk_adi,il.il_adi as tapu_il,ilce.ilce_adi as tapu_ilce,"
					+ " arac.adi as arac_tipi,mal.adi as mal_tipi,kullanici.ad_soyad as guncelleyen_kullanici "
					+ " from tbl_hacze_esas_mal_bilgisi hcz "
					+ " inner join tbl_kullanici kullanici on hcz.guncelleyen_kullanici_id=kullanici.id "
					+ " left join tbl_mulk_tipi mt on hcz.mulk_tipi_id = mt.id "
					+ " left join tbl_il il on hcz.tapu_il_id=il.id "
					+ " left join tbl_ilce ilce on hcz.tapu_ilce_id=ilce.id "
					+ " left join tbl_arac_tipi arac on hcz.arac_tipi_id=arac.id "
					+ " left join tbl_mal_tipi mal on hcz.mal_tipi_id=mal.id where hcz.id=" + id;

			newConnectDB();

			Statement stmt = conn.createStatement();

			ResultSet set = stmt.executeQuery(sql);

			while (set.next()) {

				haczeEsasMalBilgisi = new HaczeEsasMalBilgisi();

				haczeEsasMalBilgisi.setId(set.getInt("id"));
				haczeEsasMalBilgisi.setBorcluId(set.getInt("borclu_id"));
				haczeEsasMalBilgisi.setMenkulBilgisi(set.getString("menkul_bilgisi"));
				haczeEsasMalBilgisi.setIlAdi(set.getString("tapu_il"));
				haczeEsasMalBilgisi.setIlceAdi(set.getString("tapu_ilce"));
				haczeEsasMalBilgisi.setTapuMahalleAdi(set.getString("tapu_mahalle_adi"));
				haczeEsasMalBilgisi.setTapuMulkTipi(set.getString("mulk_adi"));
				haczeEsasMalBilgisi.setTapuParsel(set.getString("tapu_parsel"));
				haczeEsasMalBilgisi.setTapuSayfaNo(set.getString("tapu_sayfa_no"));
				haczeEsasMalBilgisi.setTapuCiltNo(set.getString("tapu_cilt_no"));
				haczeEsasMalBilgisi.setAracPlakaNo(set.getString("arac_plaka_no"));
				haczeEsasMalBilgisi.setAracAracTipi(set.getString("arac_aractipi"));
				haczeEsasMalBilgisi.setBankaHesapNo(set.getString("banka_hesap_no"));
				haczeEsasMalBilgisi.setMuhatapAdi(set.getString("muhatap_adi"));
				haczeEsasMalBilgisi.setMuhatapAdresi(set.getString("muhatap_adresi"));
				haczeEsasMalBilgisi.setDigerBilgiler(set.getString("diger_bilgiler"));
				haczeEsasMalBilgisi.setMalTutari(set.getInt("mal_tutari"));
				haczeEsasMalBilgisi.setIcraDosyaId(set.getInt("icra_dosyasi_id"));
				haczeEsasMalBilgisi.setMevduatBilgisi(set.getString("mevduat_bilgisi"));
				haczeEsasMalBilgisi.setMalTipi(set.getString("mal_tipi"));
				haczeEsasMalBilgisi.setEklemeTarihi(set.getDate("ekleme_tarihi"));
				haczeEsasMalBilgisi.setTapuIlId(set.getInt("tapu_il_id"));
				haczeEsasMalBilgisi.setAracTipiId(set.getInt("arac_tipi_id"));
				haczeEsasMalBilgisi.setTapuIlceId(set.getInt("tapu_ilce_id"));
				haczeEsasMalBilgisi.setMalTipiId(set.getInt("mal_tipi_id"));
				haczeEsasMalBilgisi.setMulkTipiId(set.getInt("mulk_tipi_id"));
				haczeEsasMalBilgisi.setGuncellemeTarihi(set.getDate("guncelleme_tarihi"));
				haczeEsasMalBilgisi.setGuncelleyenKullaniciAdSoyad(set.getString("guncelleyen_kullanici"));

			}

			disconnectDB();

		} catch (Exception e) {

			System.out.println("Hata hacze esas mal bilgisi DAO getT :" + e.getMessage());

		}

		return haczeEsasMalBilgisi;
	}

	public List<HaczeEsasMalBilgisiView> listeView(int borcluId) {

		List<HaczeEsasMalBilgisiView> liste = null;

		try {

			String sql = "select hcz.*,mt.adi as mulk_adi,il.il_adi as tapu_il,ilce.ilce_adi as tapu_ilce,"
					+ "arac.adi as arac_tipi,mal.adi as mal_tipi,kullanici.ad_soyad as guncelleyen_kullanici,hesap.takip_alacagi"
					+ " from tbl_hacze_esas_mal_bilgisi hcz "
					+ " inner join tbl_kullanici kullanici on hcz.guncelleyen_kullanici_id=kullanici.id "
					+ " inner join tbl_baglanti bag on hcz.borclu_id=bag.borclu_id "
					+ " inner join tbl_hesap hesap on bag.hesap_id=hesap.id "
					+ " left join tbl_mulk_tipi mt on hcz.mulk_tipi_id = mt.id "
					+ " left join tbl_il il on hcz.tapu_il_id=il.id "
					+ " left join tbl_ilce ilce on hcz.tapu_ilce_id=ilce.id "
					+ " left join tbl_arac_tipi arac on hcz.arac_tipi_id=arac.id "
					+ " left join tbl_mal_tipi mal on hcz.mal_tipi_id=mal.id " + " where hcz.borclu_id=" + borcluId;

			newConnectDB();

			Statement stmt = conn.createStatement();

			ResultSet set = stmt.executeQuery(sql);

			liste = new ArrayList<HaczeEsasMalBilgisiView>();

			HaczeEsasMalBilgisiView haczeEsasMalBilgisi = null;

			while (set.next()) {

				haczeEsasMalBilgisi = new HaczeEsasMalBilgisiView();

				haczeEsasMalBilgisi.setId(set.getInt("id"));
				haczeEsasMalBilgisi.setBorcluId(set.getInt("borclu_id"));
				haczeEsasMalBilgisi.setIcraDosyaId(set.getInt("icra_dosyasi_id"));
				haczeEsasMalBilgisi.setMalTipi(set.getString("mal_tipi"));
				haczeEsasMalBilgisi.setMaltipiId(set.getInt("mal_tipi_id"));
				haczeEsasMalBilgisi.setBorcMiktari(set.getFloat("takip_alacagi"));

				liste.add(haczeEsasMalBilgisi);

			}

			disconnectDB();

		}

		catch (Exception e) {
			System.out.println("Hata hacze esas mal bilgisi DAO liste :" + e.getMessage());
		}

		return liste;
	}

	@Override
	public List<HaczeEsasMalBilgisi> liste(int borcluId, int malTipiId) {

		List<HaczeEsasMalBilgisi> liste = null;

		try {

			String sql = "select hcz.*,mt.adi as mulk_adi,il.il_adi as tapu_il,il.id as il_id,ilce.ilce_adi as tapu_ilce,ilce.id as ilce_id,"
					+ " arac.adi as arac_tipi,mal.adi as mal_tipi,kullanici.ad_soyad as guncelleyen_kullanici "
					+ " from tbl_hacze_esas_mal_bilgisi hcz "
					+ " inner join tbl_kullanici kullanici on hcz.guncelleyen_kullanici_id=kullanici.id "
					+ " left join tbl_mulk_tipi mt on hcz.mulk_tipi_id = mt.id "
					+ " left join tbl_il il on hcz.tapu_il_id=il.id "
					+ " left join tbl_ilce ilce on hcz.tapu_ilce_id=ilce.id "
					+ " left join tbl_arac_tipi arac on hcz.arac_tipi_id=arac.id "
					+ " left join tbl_mal_tipi mal on hcz.mal_tipi_id=mal.id ";

			if (borcluId != 0)
				sql = sql + " where hcz.borclu_id=" + borcluId + " and hcz.mal_tipi_id=" + malTipiId;

			newConnectDB();

			Statement stmt = conn.createStatement();

			ResultSet set = stmt.executeQuery(sql);

			liste = new ArrayList<HaczeEsasMalBilgisi>();

			HaczeEsasMalBilgisi haczeEsasMalBilgisi = null;

			while (set.next()) {

				haczeEsasMalBilgisi = new HaczeEsasMalBilgisi();

				haczeEsasMalBilgisi.setId(set.getInt("id"));
				haczeEsasMalBilgisi.setBorcluId(set.getInt("borclu_id"));
				haczeEsasMalBilgisi.setMenkulBilgisi(set.getString("menkul_bilgisi"));
				haczeEsasMalBilgisi.setTapuAciklama(set.getString("tapu_aciklama"));
				haczeEsasMalBilgisi.setTapuSicilMudurluk(set.getString("tapu_sicil_mudurluk"));
				haczeEsasMalBilgisi.setTapuIlId(set.getInt("il_id"));
				haczeEsasMalBilgisi.setTapuIlceId(set.getInt("ilce_id"));
				haczeEsasMalBilgisi.setIlAdi(set.getString("tapu_il"));
				haczeEsasMalBilgisi.setIlceAdi(set.getString("tapu_ilce"));
				haczeEsasMalBilgisi.setTapuMahalleAdi(set.getString("tapu_mahalle_adi"));
				haczeEsasMalBilgisi.setTapuMulkTipi(set.getString("mulk_adi"));
				haczeEsasMalBilgisi.setTapuParsel(set.getString("tapu_parsel"));
				haczeEsasMalBilgisi.setTapuSayfaNo(set.getString("tapu_sayfa_no"));
				haczeEsasMalBilgisi.setTapuCiltNo(set.getString("tapu_cilt_no"));
				haczeEsasMalBilgisi.setAracPlakaNo(set.getString("arac_plaka_no"));
				haczeEsasMalBilgisi.setAracAracTipi(set.getString("arac_aractipi"));
				haczeEsasMalBilgisi.setBankaHesapNo(set.getString("banka_hesap_no"));
				haczeEsasMalBilgisi.setMuhatapAdi(set.getString("muhatap_adi"));
				haczeEsasMalBilgisi.setMuhatapAdresi(set.getString("muhatap_adresi"));
				haczeEsasMalBilgisi.setDigerBilgiler(set.getString("diger_bilgiler"));
				haczeEsasMalBilgisi.setMalTutari(set.getInt("mal_tutari"));
				haczeEsasMalBilgisi.setIcraDosyaId(set.getInt("icra_dosyasi_id"));
				haczeEsasMalBilgisi.setMevduatBilgisi(set.getString("mevduat_bilgisi"));
				haczeEsasMalBilgisi.setMalTipi(set.getString("mal_tipi"));
				haczeEsasMalBilgisi.setEklemeTarihi(set.getDate("ekleme_tarihi"));
				haczeEsasMalBilgisi.setGuncellemeTarihi(set.getDate("guncelleme_tarihi"));
				haczeEsasMalBilgisi.setGuncelleyenKullaniciAdSoyad(set.getString("guncelleyen_kullanici"));
				haczeEsasMalBilgisi.setMalTipiId(set.getInt("mal_tipi_id"));
				haczeEsasMalBilgisi.setMulkTipiId(set.getInt("mulk_tipi_id"));

				liste.add(haczeEsasMalBilgisi);

			}

			disconnectDB();

		}

		catch (Exception e) {
			System.out.println("Hata hacze esas mal bilgisi DAO liste :" + e.getMessage());
		}

		return liste;
	}

	@Override
	public int getId(HaczeEsasMalBilgisi t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
