package semiramis.operasyon.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import pelops.controller.AktifBean;
import pelops.db.DBConnection;
import pelops.users.Util;
import semiramis.operasyon.controller.MuameleBean;
import semiramis.operasyon.controller.Utils;
import semiramis.operasyon.model.ChronologyIdentifier;
import semiramis.operasyon.model.ComboItem;
import semiramis.operasyon.model.Muamele;

public class MuameleDAO extends DBConnection {

	public void kaydet(Muamele muamele) {
		try {

			int adet = 0;

			String sql1 = "SELECT count(*) FROM tbl_muamele_bilgisi where icra_dosyasi_id=" + muamele.getIcraDosyaID()
					+ " and muzekkere_talep_id=" + muamele.getMuzekkereId() + " and hacze_esas_id='"
					+ muamele.getHaczeEsasMalId() + "'";
			newConnectDB();

			Statement stmt2 = conn.createStatement();

			ResultSet set = stmt2.executeQuery(sql1);

			set.next();

			adet = set.getInt(1);

			if (adet == 0) {
				String sql = "INSERT INTO tbl_muamele_bilgisi( "
						+ " borclu_id, alacak_id, icra_dosyasi_id, barkod, personel_id,   "
						+ " muzekkere_talep_miktari, muamele_statusu_id, tebligat_sonucu,  "
						+ " muzekkere_talep_id, guncelleme_tarihi, muamele_tarihi,tapu_aciklama,hacze_esas_id) "
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, now(),now(),?,?) ";

				PreparedStatement stmt = conn.prepareStatement(sql);

				stmt.setInt(1, muamele.getBorcluId());
				stmt.setInt(2, muamele.getMuvekkilId());
				stmt.setInt(3, muamele.getIcraDosyaID());
				stmt.setString(4, muamele.getBarkodTxt());
				stmt.setInt(5, Util.getUser().getUsrId());
				stmt.setInt(6, muamele.getMuzekkereTalepMiktari());
				stmt.setInt(7, muamele.getMuameleStatusu());
				stmt.setInt(8, muamele.getTebligatSonucu());
				stmt.setInt(9, muamele.getMuzekkereId());
				stmt.setString(10, muamele.getTapuAciklama());
				stmt.setString(11, muamele.getHaczeEsasMalId());

				stmt.execute();
				
				
				
				
				

			} else {

				String sql2 = " UPDATE tbl_muamele_bilgisi "
						+ " SET borclu_id=?, alacak_id=?, icra_dosyasi_id=?, barkod=?, personel_id=?,"
						+ " muamele_tarihi=now(), muzekkere_talep_miktari=?, muamele_statusu_id=?,  "
						+ " tebligat_sonucu=?, muzekkere_talep_id=?, guncelleme_tarihi=now(), tapu_aciklama=? ,hacze_esas_id=?"
						+ " WHERE icra_dosyasi_id=" + muamele.getIcraDosyaID() + " and muzekkere_talep_id="
						+ muamele.getMuzekkereId() + " and hacze_esas_id='" + muamele.getHaczeEsasMalId() + "'";

				PreparedStatement stmt3 = conn.prepareStatement(sql2);

				stmt3.setInt(1, muamele.getBorcluId());
				stmt3.setInt(2, muamele.getMuvekkilId());
				stmt3.setInt(3, muamele.getIcraDosyaID());
				stmt3.setString(4, muamele.getBarkodTxt());
				stmt3.setInt(5, Util.getUser().getUsrId());
				stmt3.setInt(6, muamele.getMuzekkereTalepMiktari());
				stmt3.setInt(7, muamele.getMuameleStatusu());
				stmt3.setInt(8, muamele.getTebligatSonucu());
				stmt3.setInt(9, muamele.getMuzekkereId());
				stmt3.setString(10, muamele.getTapuAciklama());
				stmt3.setString(11, muamele.getHaczeEsasMalId());

				stmt3.execute();

			}

			disconnectDB();
			
			if(adet==0)
			{
				
				if(MuameleBean.MUZEKKERE_MAAS==muamele.getMuzekkereId() || MuameleBean.MUZEKKERE_MAAS_TALEP==muamele.getMuzekkereId())
				{
				new Utils().saveChronology(AktifBean.getIcraDosyaID(), ChronologyIdentifier.ISLEM_MUAMELE,
						new Utils().getBocluAdi() + " Maaş Müzekkeresi yazıldı. ");
				new Utils().saveChronology(AktifBean.getIcraDosyaID(), ChronologyIdentifier.ISLEM_MUAMELE,
						new Utils().getBocluAdi() + " Maaş Talebi yazıldı. ");
				}
				else if(MuameleBean.MUZEKKERE_TAPU==muamele.getMuzekkereId())
				{
					
					new Utils().saveChronology(AktifBean.getIcraDosyaID(), ChronologyIdentifier.ISLEM_MUAMELE,
							new Utils().getBocluAdi() + " Tapu Müzekkeresi yazıldı. ");
					
				}
				
				
			}

		} catch (Exception e) {

			System.out.println("Hata muameleDAO kaydet :" + e.getMessage());

			// TODO: handle exception
		}
	}

	public Muamele getMuameleDuzenle(int id) {

		Muamele muamele = null;

		try {

			String sql = "select mb.id,mb.barkod,mb.muamele_tarihi,mb.tapu_aciklama,mb.hacze_esas_id, "
					+ "  borclu.ad_soyad,borclu.adres as borclu_adres,borclu.is_yeri_adi,borclu.isyeri_adres,borclu.urun_no,borclu.tc_no,borclu.musteri_no, "
					+ "  alacakli.muvekkil_adi, "
					+ "  icra.icra_dosyasi_no,icra.id as icra_dosyasi_id,imud.adi as icra_mudurluk,imud.id as icra_mudurluk_id,  "
					+ "  mtip.adi as muzekkere_adi,mtip.sira as muzekkere_id, "
					+ "  hesap.toplam_alacak,hesap.takip_alacagi,hesap.asil_alacak " + "  from tbl_muamele_bilgisi mb "
					+ "  inner join tbl_borclu borclu on mb.borclu_id=borclu.id "
					+ "  inner join tbl_alacakli_bilgisi alacakli on mb.alacak_id=alacakli.id "
					+ "  inner join tbl_icra_dosyasi icra on mb.icra_dosyasi_id=icra.id "
					+ "  inner join tbl_icra_mudurlugu imud on icra.icra_mudurlugu_id=imud.id "
					+ "  inner join tbl_muzekkere_tipi mtip on mb.muzekkere_talep_id=mtip.sira "
					+ "  inner join tbl_baglanti bag on bag.borclu_id=mb.borclu_id "
					+ "  inner join tbl_hesap hesap on bag.hesap_id=hesap.id where mb.id=" + id;

			newConnectDB();

			Statement stmt = conn.createStatement();

			ResultSet set = stmt.executeQuery(sql);

			while (set.next()) {
				muamele = new Muamele();

				muamele.setId(set.getInt("id"));
				muamele.setBarkodTxt(set.getString("barkod"));
				muamele.setMumaleTarihi(set.getDate("muamele_tarihi"));
				muamele.setBorcluAdSoyad(set.getString("ad_soyad"));
				muamele.setBorcluAdres(set.getString("borclu_adres"));
				muamele.setBorcluIsyeriAdi(set.getString("is_yeri_adi"));
				muamele.setBorcluIsyeriAdres(set.getString("isyeri_adres"));
				muamele.setUrunNo(set.getString("urun_no"));
				muamele.setBorcluTcNo(set.getString("tc_no"));
				muamele.setMuvekkilAdi(set.getString("muvekkil_adi"));
				muamele.setIcraDosyaNo(set.getString("icra_dosyasi_no"));
				muamele.setIcraDosyaID(set.getInt("icra_dosyasi_id"));
				muamele.setIcraMudurluguID(set.getInt("icra_mudurluk_id"));
				muamele.setIcraMudurlugu(set.getString("icra_mudurluk"));
				muamele.setMuzekkereAdi(set.getString("muzekkere_adi"));
				muamele.setMuzekkereId(set.getInt("muzekkere_id"));
				muamele.setToplamAlacak(set.getDouble("toplam_alacak"));
				muamele.setTakipAlacak(set.getDouble("takip_alacagi"));
				muamele.setAsilAlacak(set.getDouble("asil_alacak"));
				muamele.setTapuAciklama(set.getString("tapu_aciklama"));
				muamele.setHaczeEsasMalId(set.getString("hacze_esas_id"));

			}

			muamele.setMuameleTarihiTxt(new SimpleDateFormat("MM/dd/yyyy").format(muamele.getMumaleTarihi()));
			muamele.setBorcMiktari(muamele.getToplamAlacak() - muamele.getTahsilatMiktari());
			muamele.setBorcMiktari(
					Double.valueOf(new DecimalFormat("0.00").format(muamele.getBorcMiktari()).replace(",", ".")));
			muamele.setBorcMiktariTxt(new DecimalFormat("###,###.##").format(muamele.getBorcMiktari()));
			muamele.setAvukatIBAN("TR3000 1230 0067 1038 9292 8100");
			muamele.setAvukatAdi("M.Oruç SASA");

			disconnectDB();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return muamele;

	}

	public Muamele getMuamele(int icraDosyaID) {

		Muamele muamele = null;

		try {

			String sql = "select borclu.id as borclu_id,borclu.ad_soyad,borclu.tc_no,borclu.dogum_tarihi,borclu.adres,borclu.is_yeri_adi,borclu.isyeri_adres,  "
					+ " icra.id as icra_id,icra.icra_dosyasi_no,imud.adi as icra_mudurlugu,imud.id as icra_mudurluk_id, "
					+ " alacak.muvekkil_adi, alacak.id as muvekkil_id,"
					+ " hesap.asil_alacak,hesap.takip_alacagi,hesap.toplam_alacak,hesap.tahsilat_tutari,hesap.urun_adi,hesap.urun_no,hesap.masraf_tutari "
					+ " from tbl_baglanti bag " + " inner join tbl_borclu borclu on bag.borclu_id=borclu.id "
					+ " inner join tbl_icra_dosyasi icra on bag.icra_dosyasi_id=icra.id "
					+ " inner join tbl_icra_mudurlugu imud on icra.icra_mudurlugu_id=imud.id "
					+ " inner join tbl_alacakli_bilgisi alacak on bag.alacakli_id=alacak.id "
					+ " inner join tbl_hesap hesap on bag.hesap_id=hesap.id " + " where icra.id=" + icraDosyaID;

			System.out.println(sql);

			newConnectDB();

			Statement stmt = conn.createStatement();

			ResultSet set = stmt.executeQuery(sql);

			while (set.next()) {
				muamele = new Muamele();

				muamele.setBorcluId(set.getInt("borclu_id"));
				muamele.setBorcluAdSoyad(set.getString("ad_soyad"));
				muamele.setBorcluTcNo(set.getString("tc_no"));
				muamele.setBorcluDogumTarihi(set.getString("dogum_tarihi"));
				muamele.setBorcluAdres(set.getString("adres"));
				muamele.setBorcluIsyeriAdres(set.getString("isyeri_adres"));
				muamele.setBorcluIsyeriAdi(set.getString("is_yeri_adi"));
				muamele.setIcraDosyaID(set.getInt("icra_id"));
				muamele.setIcraDosyaNo(set.getString("icra_dosyasi_no"));
				muamele.setIcraMudurlugu(set.getString("icra_mudurlugu"));
				muamele.setIcraMudurluguID(set.getInt("icra_mudurluk_id"));
				muamele.setMuvekkilAdi(set.getString("muvekkil_adi"));
				muamele.setMuvekkilId(set.getInt("muvekkil_id"));
				muamele.setAsilAlacak(set.getDouble("asil_alacak"));
				muamele.setTakipAlacak(set.getDouble("takip_alacagi"));
				muamele.setToplamAlacak(set.getDouble("toplam_alacak"));
				muamele.setTahsilatMiktari(set.getDouble("tahsilat_tutari"));
				muamele.setUrunAdi(set.getString("urun_adi"));
				muamele.setUrunNo(set.getString("urun_no"));
				muamele.setMasrafTutari(set.getFloat("masraf_tutari"));

			}

			disconnectDB();

		} catch (Exception e) {

			System.out.println("Hata muamele DAO getMuamele :" + e.getMessage());

		}

		return muamele;

	}

	public List<Muamele> getMuameleList(String icraDosyaNo) {
		List<Muamele> liste = null;

		try {

			String sql = "SELECT muamele.tapu_aciklama, muamele.id,mtip.adi as muzekkere_adi,borclu.ad_soyad,icra.icra_dosyasi_no,muamele.muzekkere_talep_miktari,alacakli.muvekkil_adi,muamele.muamele_tarihi "
					+ " FROM tbl_muamele_bilgisi muamele "
					+ " inner join tbl_icra_dosyasi icra on icra.id=muamele.icra_dosyasi_id "
					+ " inner join tbl_muzekkere_tipi mtip on mtip.sira=muamele.muzekkere_talep_id "
					+ " inner join tbl_borclu borclu on borclu.id=muamele.borclu_id "
					+ " inner join tbl_alacakli_bilgisi alacakli on alacakli.id=muamele.alacak_id "
					+ " where icra.icra_dosyasi_no='" + icraDosyaNo + "'";

			newConnectDB();

			Statement stmt = conn.createStatement();

			ResultSet set = stmt.executeQuery(sql);

			Muamele muamele = null;

			liste = new ArrayList<Muamele>();

			while (set.next()) {
				muamele = new Muamele();

				muamele.setIcraDosyaNo(set.getString("icra_dosyasi_no"));
				muamele.setId(set.getInt("id"));
				muamele.setMuzekkereAdi(set.getString("muzekkere_adi"));
				muamele.setBorcluAdSoyad(set.getString("ad_soyad"));
				muamele.setMuzekkereTalepMiktari(set.getInt("muzekkere_talep_miktari"));
				muamele.setMuvekkilAdi(set.getString("muvekkil_adi"));
				muamele.setMumaleTarihi(set.getDate("muamele_tarihi"));
				muamele.setTapuAciklama(set.getString("tapu_aciklama"));

				liste.add(muamele);

			}

			disconnectDB();

		} catch (Exception e) {

			System.out.println("Hata muamele DAO getmuameleList :" + e.getMessage());

		}
		return liste;
	}

	public List<ComboItem> getMuzekkereTip() {

		List<ComboItem> liste = null;

		try {

			String sql = "SELECT id, adi,sira FROM tbl_muzekkere_tipi";

			newConnectDB();

			Statement stmt = conn.createStatement();

			ResultSet set = stmt.executeQuery(sql);

			ComboItem item = null;

			liste = new ArrayList<ComboItem>();

			while (set.next()) {
				item = new ComboItem();

				item.setId(set.getInt("sira"));
				item.setAdi(set.getString("adi"));

				liste.add(item);
			}

			disconnectDB();

		} catch (Exception e) {

			System.out.println("HATA muamele DAO getMuzekkere List :" + e.getMessage());

			// TODO: handle exception
		}

		return liste;

	}
	
	
	public void sil(int id)
	{
		
		
		try {
			
			String sql="DELETE FROM tbl_muamele_bilgisi WHERE id="+id;
			
			
			newConnectDB();
			
			Statement stmt=conn.createStatement();
			
			stmt.execute(sql);
			
			
		} catch (Exception e) {
			
			System.out.println("Hata muameleDAO SİL :"+e.getMessage());
			// TODO: handle exception
		}
		
	}

}
