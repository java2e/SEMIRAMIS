package pelops.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.faces.context.FacesContext;
import org.apache.commons.lang3.time.DateFormatUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import pelops.db.DBConnection;
import pelops.model.Avukat;
import pelops.model.CokluBankaFour;
import pelops.model.DortluYapi;
import pelops.model.MuameleIslemleri;
import pelops.model.StandartTalep;
import pelops.reports.controller.GenelYazdirBean;

public class MuameleIslemleriDAO extends DBConnection {

	public void MuzekkereTalepEkle(MuameleIslemleri muamele, ArrayList<MuameleIslemleri> muameleList) throws Exception {

		newConnectDB();
		Statement stmt = conn.createStatement();
		
		GenelYazdirBean genelYazdirBean=new GenelYazdirBean();

		for (int i = 0; i < muameleList.size(); i++) {

			String kurumAdi = null;
			String alacakliBankasi = null;
			String tapuKayitlari = null;

			if (muameleList.get(i).getKurumAdi() == null) {

				kurumAdi = "...";

			} else {

				kurumAdi = muameleList.get(i).getKurumAdi();
			}

			if (muameleList.get(i).getAlacakliBankasi() == null) {

				alacakliBankasi = "...";

			} else {

				alacakliBankasi = muameleList.get(i).getAlacakliBankasi();
			}

			if (muameleList.get(i).getTapuKayitlari() == null) {

				tapuKayitlari = "...";

			} else {

				tapuKayitlari = muameleList.get(i).getTapuKayitlari();
			}

			String SQL = "SELECT COUNT(*) AS total FROM tbl_muamele_islemi where icra_dosya_no='"
					+ muameleList.get(i).getIcraDosyaNo() + "' and muzekkere_talep_adi='"
					+ muameleList.get(i).getMuzekkereTalepAdi() + "' and kurum_adi='" + kurumAdi
					+ "' and alacakli_bankasi='" + alacakliBankasi + "' and tapu_kayitlari='" + tapuKayitlari + "';";

			ResultSet rs = stmt.executeQuery(SQL);
			int deger = 0;

			if (rs.next()) {

				deger = rs.getInt("total");

			}

			if (deger == 0) {

				SQL = "INSERT INTO tbl_muamele_islemi(borclu_adi, muamele_tarihi, talep_ifadesi, masraf_tipi_id,"
						+ "masraf_miktari, mal_tipi_id, mal_bilgisi, personel_id, avukat_id,"
						+ "muhatap_adi, muamele_statusu_id, muhatap_adresi, haciz_sirasi,"
						+ "tebligat_tarihi, haciz_baslangic_tarihi, tebligat_sonucu, haciz_miktari,"
						+ "maas_muvafakat, aciklama, icra_mudurlugu_id, barcode, content_id,icra_dosya_id,"
						+ "alacakli_mail,hazirlayan_id,alacakli_tel,banka_bilgileri,borclu_tc,borclu_adresi,"
						+ "semiramis_no,alacakli_bankasi,icra_dosya_no,muzekkere_talep_adi,muzekkere_talep_miktari,alacak_faiz_tutari,baslik,paragraf_1,paragraf_2,kurum_adi,"
						+ "tapu_kayitlari,vergi_kimlik_no,buro_adresi,plaka,risk_yoneticisi_text,hazirlayan_text,borclu_miktari,postane_adi,ptt_il_text,ptt_ilce_text, alacakli_adi,"
						+ "mevduat_banka_adi,mevduat_banka_adresi,buro_iban_no,konu,mernis_adresi,sgk_adresi,yurtici_adresi,eki,dogum_tarihi,tapu_mudurluk,tapu_il,tapu_ilce)"
						+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

				PreparedStatement pstmt = conn.prepareStatement(SQL.toString());

				pstmt.setString(1, muamele.getBorcluAdi());
				pstmt.setDate(2, convertFromJAVADateToSQLDate(muamele.getMuameleTarihi()));
				pstmt.setString(3, muamele.getTalepIfadesi());
				pstmt.setInt(4, muamele.getMasrafTipiId());
				pstmt.setDouble(5, muamele.getMasrafMiktari());
				pstmt.setInt(6, muamele.getMalTipiId());
				pstmt.setString(7, muamele.getMalBilgisi());
				pstmt.setInt(8, muamele.getPersonelId());
				pstmt.setInt(9, muamele.getAvukatId());
				pstmt.setString(10, muamele.getMuhatapAdi().toString());
				pstmt.setInt(11, muamele.getMuameleStatusuId());
				pstmt.setString(12, muamele.getMuhatapAdresi().toString());
				pstmt.setInt(13, muamele.getHacizSirasi());
				pstmt.setDate(14, convertFromJAVADateToSQLDate(muamele.getTebligatTarihi()));
				pstmt.setDate(15, convertFromJAVADateToSQLDate(muamele.getHacizBaslangicTarihi()));
				pstmt.setString(16, muamele.getTebligatSonucu());
				pstmt.setInt(17, muamele.getHacizMiktari());
				pstmt.setString(18, muamele.getMaasMuvafakat());
				pstmt.setString(19, muamele.getAciklama());
				pstmt.setInt(20, muamele.getIcraMudurluguId());
				pstmt.setString(21, muamele.getBarcode());
				pstmt.setInt(22, muamele.getContentId());
				pstmt.setInt(23, muamele.getIcraDosyaId());
				pstmt.setString(24, muamele.getAlacakliMail());
				pstmt.setInt(25, muamele.getHazirlayanId());
				pstmt.setString(26, muamele.getAlacakliTel());
				pstmt.setString(27, muamele.getBankaBilgileri());
				pstmt.setString(28, muamele.getBorcluTc());
				pstmt.setString(29, muamele.getBorcluAdresi());
				pstmt.setInt(30, muamele.getSemiramisNo());
				pstmt.setString(31, alacakliBankasi);
				pstmt.setString(32, muamele.getIcraDosyaNo());
				pstmt.setString(33, muameleList.get(i).getMuzekkereTalepAdi());
				pstmt.setInt(34, muameleList.get(i).getMiktar());
				pstmt.setDouble(35, muamele.getAlacakFaizTutari());
				pstmt.setString(36, muamele.getBaslik());
				pstmt.setString(37, muamele.getParagraf1());
				pstmt.setString(38, muamele.getParagraf2());
				pstmt.setString(39, kurumAdi);
				pstmt.setString(40, tapuKayitlari);
				pstmt.setString(41, muamele.getVergiKimlikNo());
				pstmt.setString(42, muamele.getBuroAdresi());
				pstmt.setString(43, muamele.getPlaka());
				pstmt.setString(44, muamele.getRiskYoneticisiText());
				pstmt.setString(45, muamele.getHazirlayanText());
				pstmt.setDouble(46, muamele.getBorcluMiktari());
				pstmt.setString(47, muamele.getPostaneAdi());
				pstmt.setString(48, muamele.getPttIlText());
				pstmt.setString(49, muamele.getPttIlceText());
				pstmt.setString(50, muamele.getAlacakliAdi());
				pstmt.setString(51, muamele.getMevduatBankaAdi());
				pstmt.setString(52, muamele.getMevduatBankaAdresi());
				pstmt.setString(53, muamele.getBuroIbanNo());
				pstmt.setString(54, muamele.getKonu());
				pstmt.setString(55, muamele.getMernisAdresi());
				pstmt.setString(56, muamele.getSgkAdresi());
				pstmt.setString(57, muamele.getYurticiAdresi());
				pstmt.setString(58, muamele.getEki());
				pstmt.setDate(59, convertFromJAVADateToSQLDate(muamele.getDogumTarihi()));
				if(muameleList.get(i).getTapuMudurlugu()!=null)
				pstmt.setString(60, muameleList.get(i).getTapuMudurlugu().toString());
				else
				pstmt.setString(60, muameleList.get(i).getTapuMudurlugu());

				pstmt.setString(61, muameleList.get(i).getTapuIl());
				pstmt.setString(62, muameleList.get(i).getTapuIlce());

				pstmt.executeUpdate();

			}

			else if (deger == 1) {

				SQL = "UPDATE tbl_muamele_islemi "
						+ "SET borclu_adi = ?,  muamele_tarihi= ? , talep_ifadesi = ?, masraf_tipi_id = ?, masraf_miktari = ?, "
						+ " mal_tipi_id = ?, mal_bilgisi = ?, personel_id = ?, avukat_id = ?, muhatap_adi = ?, "
						+ " muamele_statusu_id = ?, muhatap_adresi = ?, haciz_sirasi = ?,  tebligat_tarihi= ? , haciz_baslangic_tarihi= ?, "
						+ " tebligat_sonucu = ?, "
						+ " haciz_miktari = ?, maas_muvafakat = ?, aciklama = ?, icra_mudurlugu_id= ? , "
						+ " barcode= ? , content_id= ? , icra_dosya_id= ? , alacakli_mail= ? , "
						+ " hazirlayan_id= ? , alacakli_tel= ? , banka_bilgileri= ? , "
						+ " borclu_tc= ? , borclu_adresi= ? ,  alacak_faiz_tutari= ? , "
						+ " semiramis_no= ? , alacakli_bankasi= ? , icra_dosya_no= ? , muzekkere_talep_adi= ? , "
						+ " muzekkere_talep_miktari = ? ,baslik = ? , paragraf_1 = ? , paragraf_2 = ? , kurum_adi=? , tapu_kayitlari=? , vergi_kimlik_no=? , buro_adresi=? , plaka=? , risk_yoneticisi_text=? , hazirlayan_text=? , borclu_miktari=? , postane_adi = ? , ptt_il_text =?, ptt_ilce_text =? ,"
						+ " alacakli_adi=? , mevduat_banka_adi= ? , mevduat_banka_adresi = ? , buro_iban_no=? , konu=?  , mernis_adresi =? , sgk_adresi=? , yurtici_adresi=?  ,eki =? , dogum_tarihi =?,tapu_mudurluk=?,tapu_il=?,tapu_ilce=?  WHERE icra_dosya_no = '"
						+ muameleList.get(i).getIcraDosyaNo() + "'  and muzekkere_talep_adi = '"
						+ muameleList.get(i).getMuzekkereTalepAdi() + "' and kurum_adi = '" + kurumAdi
						+ "' and alacakli_bankasi='" + alacakliBankasi + "' and tapu_kayitlari='" + tapuKayitlari
						+ "';";

				PreparedStatement pstmt = conn.prepareStatement(SQL.toString());

				pstmt.setString(1, muamele.getBorcluAdi());
				pstmt.setDate(2, convertFromJAVADateToSQLDate(muamele.getMuameleTarihi()));
				pstmt.setString(3, muamele.getTalepIfadesi());
				pstmt.setInt(4, muamele.getMasrafTipiId());
				pstmt.setDouble(5, muamele.getMasrafMiktari());
				pstmt.setInt(6, muamele.getMalTipiId());
				pstmt.setString(7, muamele.getMalBilgisi());
				pstmt.setInt(8, muamele.getPersonelId());
				pstmt.setInt(9, muamele.getAvukatId());
				pstmt.setString(10, muamele.getMuhatapAdi());
				pstmt.setInt(11, muamele.getMuameleStatusuId());
				pstmt.setString(12, muamele.getMuhatapAdresi());
				pstmt.setInt(13, muamele.getHacizSirasi());
				pstmt.setDate(14, convertFromJAVADateToSQLDate(muamele.getTebligatTarihi()));
				pstmt.setDate(15, convertFromJAVADateToSQLDate(muamele.getHacizBaslangicTarihi()));
				pstmt.setString(16, muamele.getTebligatSonucu());
				pstmt.setInt(17, muamele.getHacizMiktari());
				pstmt.setString(18, muamele.getMaasMuvafakat());
				pstmt.setString(19, muamele.getAciklama());
				pstmt.setInt(20, muamele.getIcraMudurluguId());
				pstmt.setString(21, muamele.getBarcode());
				pstmt.setInt(22, muamele.getContentId());
				pstmt.setInt(23, muamele.getIcraDosyaId());
				pstmt.setString(24, muamele.getAlacakliMail());
				pstmt.setInt(25, muamele.getHazirlayanId());
				pstmt.setString(26, muamele.getAlacakliTel());
				pstmt.setString(27, muamele.getBankaBilgileri());
				pstmt.setString(28, muamele.getBorcluTc());
				pstmt.setString(29, muamele.getBorcluAdresi());
				pstmt.setDouble(30, muamele.getAlacakFaizTutari());
				pstmt.setInt(31, muamele.getSemiramisNo());
				pstmt.setString(32, alacakliBankasi);
				pstmt.setString(33, muamele.getIcraDosyaNo());
				pstmt.setString(34, muameleList.get(i).getMuzekkereTalepAdi());
				pstmt.setInt(35, muameleList.get(i).getMiktar());
				pstmt.setString(36, muamele.getBaslik());
				pstmt.setString(37, muamele.getParagraf1());
				pstmt.setString(38, muamele.getParagraf2());
				pstmt.setString(39, kurumAdi);
				pstmt.setString(40, tapuKayitlari);
				pstmt.setString(41, muamele.getVergiKimlikNo());
				pstmt.setString(42, muamele.getBuroAdresi());
				pstmt.setString(43, muamele.getPlaka());
				pstmt.setString(44, muamele.getRiskYoneticisiText());
				pstmt.setString(45, muamele.getHazirlayanText());
				pstmt.setDouble(46, muamele.getBorcluMiktari());
				pstmt.setString(47, muamele.getPostaneAdi());
				pstmt.setString(48, muamele.getPttIlText());
				pstmt.setString(49, muamele.getPttIlceText());
				pstmt.setString(50, muamele.getAlacakliAdi());
				pstmt.setString(51, muamele.getMevduatBankaAdi());
				pstmt.setString(52, muamele.getMevduatBankaAdresi());
				pstmt.setString(53, muamele.getBuroIbanNo());
				pstmt.setString(54, muamele.getKonu());
				pstmt.setString(55, muamele.getMernisAdresi());
				pstmt.setString(56, muamele.getSgkAdresi());
				pstmt.setString(57, muamele.getYurticiAdresi());
				pstmt.setString(58, muamele.getEki());
				pstmt.setDate(59, convertFromJAVADateToSQLDate(muamele.getDogumTarihi()));
				pstmt.setString(60, muameleList.get(i).getTapuMudurlugu());
				pstmt.setString(61, muameleList.get(i).getTapuIl());
				pstmt.setString(62, muameleList.get(i).getTapuIlce());

				

				pstmt.executeUpdate();

			}
		}

		disconnectDB();
	}

	public ArrayList<Avukat> AvukatListesiGetir() throws Exception {

		ArrayList<Avukat> list = new ArrayList<Avukat>();

		Avukat avukat = null;

		newConnectDB();
		String SQL = "select * from tbl_vekil_bilgisi";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {

			avukat = new Avukat();
			avukat.setId(rs.getInt("id"));
			avukat.setAdi(rs.getString("adi"));
			avukat.setSoyadi(rs.getString("soyadi"));
			avukat.setKurumAvukatiMi(rs.getString("kurumavukatimi"));

			list.add(avukat);
		}
		disconnectDB();

		return list;

	}

	public String getIcraMudurluguText(int icraMudurluguId) throws Exception {

		newConnectDB();
		String icraMudurluguText = null;
		String SQL = "select * from tbl_icra_mudurlugu where id=" + icraMudurluguId;
		System.out.println(SQL);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {

			icraMudurluguText = rs.getString("adi");

		}

		disconnectDB();

		return icraMudurluguText;

	}

	public String getBuroAdresiText(int icraDosyaId) throws Exception {

		newConnectDB();
		String buroAdresiText = null;
		String SQL = "select * from tbl_vekil_bilgisi where id=1";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {

			buroAdresiText = rs.getString("adres");

		}

		disconnectDB();

		return buroAdresiText;

	}

	public String getAlacakliTelText(int icraDosyaId) throws Exception {

		newConnectDB();
		String alacakliTelText = null;
		String SQL = "select * from tbl_alacakli_bilgisi where id=" + icraDosyaId;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {

			alacakliTelText = rs.getString("telefon_no");

		}

		disconnectDB();

		return alacakliTelText;

	}

	public String getRiskYoneticisiText(int riskYoneticisiId) throws Exception {

		newConnectDB();
		String riskYoneticisiText = null;
		String SQL = "select * from tbl_user where id=" + riskYoneticisiId;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {

			riskYoneticisiText = rs.getString("adSoyad");

		}

		disconnectDB();

		return riskYoneticisiText;

	}

	public String getAlacakliEpostaText(int alacakliId) throws Exception {

		newConnectDB();
		String alacakliEpostaText = null;
		String SQL = "select * from tbl_alacakli_bilgisi where id=" + alacakliId;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {

			alacakliEpostaText = rs.getString("muvekkil_adi");

		}

		disconnectDB();

		return alacakliEpostaText;

	}

	public String getIcraDosyaNoText(int icraDosyaNoId) throws Exception {

		newConnectDB();
		String icraDosyaNoText = null;
		String SQL = "select * from tbl_icra_dosyasi where id=" + icraDosyaNoId;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {

			icraDosyaNoText = rs.getString("icra_dosyasi_no");

		}

		disconnectDB();

		return icraDosyaNoText;

	}

	public String getBorcluAdresiText(int borcluId) throws Exception {

		newConnectDB();
		String borcluAdresiText = null;
		String SQL = "select * from tbl_borclu where id=" + borcluId;
		System.out.println(SQL);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {

			borcluAdresiText = rs.getString("adres");

		}

		disconnectDB();

		return borcluAdresiText;

	}

	public String getBorcluTcText(int borclubilgisiID) throws Exception {

		newConnectDB();
		String borcluText = null;
		String SQL = "select * from tbl_borclu where id=" + borclubilgisiID;
		System.out.println("borclu tc " + SQL);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {

			borcluText = rs.getString("tc_no");

		}

		disconnectDB();

		return borcluText;

	}

	public String DateToText(java.sql.Date sqlDate) {

		String date = null;

		if (sqlDate != null) {

			date = DateFormatUtils.format(sqlDate, "dd/MM/yyyy");

		}

		return date;

	}

	public java.sql.Date convertFromJAVADateToSQLDate(java.util.Date javaDate) {

		java.sql.Date sqlDate = null;

		if (javaDate != null) {

			sqlDate = new Date(javaDate.getTime());
		}

		return sqlDate;
	}

	public MuameleIslemleri contentGetir(int content_id) throws Exception {

		newConnectDB();
		String SQL = "select * from tbl_muamele_islemleri_content where id=" + content_id;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		MuameleIslemleri muameleIslm = new MuameleIslemleri();
		while (rs.next()) {
			muameleIslm.setParagraf1(rs.getString("paragraf"));
			muameleIslm.setParagraf2(rs.getString("paragraf_2"));
		}
		disconnectDB();
		return muameleIslm;
	}

	public ArrayList<MuameleIslemleri> getMuzekkereTalepList(String icraDosyaNo) throws Exception {

		ArrayList<MuameleIslemleri> List = new ArrayList<MuameleIslemleri>();
		MuameleIslemleri muamele;
		newConnectDB();
		String SQL = "select * from vw_muamele_islemi where icra_dosya_no='" + icraDosyaNo + "'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			muamele = new MuameleIslemleri();

			muamele.setId(rs.getInt("muameleid"));
			muamele.setBorcluAdi(rs.getString("borcluadi"));
			muamele.setTalepIfadesi(rs.getString("talepifadesi"));
			muamele.setMasrafMiktari(rs.getDouble("masrafmiktari"));
			muamele.setMalBilgisi(rs.getString("malbilgisi"));
			muamele.setMuhatapAdi(rs.getString("muhatapadi"));
			muamele.setMuhatapAdresi(rs.getString("muhatapadresi"));
			muamele.setHacizSirasi(rs.getInt("hacizsirasi"));
			muamele.setTebligatSonucu(rs.getString("tebligat_sonucu"));
			muamele.setHacizMiktari(rs.getInt("haciz_miktari"));
			muamele.setMaasMuvafakat(rs.getString("maas_muvafakat"));
			muamele.setAciklama(rs.getString("aciklama"));
			muamele.setBarcode(rs.getString("barcode"));
			muamele.setContentId(rs.getInt("contentid"));
			muamele.setIcraDosyaId(rs.getInt("icradosyaid"));
			muamele.setMuameleTarihi(rs.getDate("muamele_tarihi"));
			muamele.setTebligatTarihi(rs.getDate("tebligat_tarihi"));
			muamele.setHacizBaslangicTarihi(rs.getDate("haciz_baslangic_tarihi"));
			muamele.setAlacakliTel(rs.getString("alacakli_tel"));
			muamele.setBankaBilgileri(rs.getString("banka_bilgileri"));
			muamele.setBorcluTc(rs.getString("borclu_tc"));
			muamele.setBorcluAdresi(rs.getString("borclu_adresi"));
			muamele.setAlacakliMail(rs.getString("alacakli_mail"));
			muamele.setSemiramisNo(rs.getInt("semiramis_no"));
			muamele.setIcraDosyaNo(rs.getString("icra_dosya_no"));
			muamele.setMuzekkereTalepAdi(rs.getString("muzekkere_talep_adi"));
			muamele.setMuzekkereTalepMiktari(rs.getInt("muzekkere_talep_miktari"));
			muamele.setMasrafTipiId(rs.getInt("masraftipiid"));
			muamele.setMalTipiId(rs.getInt("maltipiid"));
			muamele.setPersonelId(rs.getInt("personel_id"));
			muamele.setHazirlayanId(rs.getInt("hazirlayan_id"));
			muamele.setMuameleStatusuId(rs.getInt("muamele_statusu_id"));
			muamele.setAlacakliBankasi(rs.getString("alacakli_bankasi"));
			muamele.setMasrafTipiAdi(rs.getString("masraftipiadi"));
			muamele.setMalTipiAdi(rs.getString("maltipiadi"));
			muamele.setMuameleStatuAdi(rs.getString("muamelestatuadi"));
			muamele.setIcraMudurluguAdi(rs.getString("icramudurluguadi"));
			muamele.setBaslik(rs.getString("baslik"));
			muamele.setParagraf1(rs.getString("p1"));
			muamele.setParagraf2(rs.getString("p2"));
			muamele.setIcraMudurluguId(rs.getInt("icramudurluguid"));
			muamele.setHazirlayanAdSoyad(rs.getString("isimsoyisim"));
			muamele.setAvukatId(rs.getInt("avukatid"));
			muamele.setKurumAdi(rs.getString("kurum_adi"));
			muamele.setAlacakliBankasi(rs.getString("alacakli_bankasi"));
			muamele.setTapuKayitlari(rs.getString("tapu_kayitlari"));
			muamele.setVergiKimlikNo(rs.getString("vergi_kimlik_no"));
			muamele.setBankaBilgileri(rs.getString("banka_bilgileri"));
			muamele.setBuroAdresi(rs.getString("buro_adresi"));
			muamele.setPlaka(rs.getString("plaka"));
			muamele.setRiskYoneticisiText(rs.getString("risk_yoneticisi_text"));
			muamele.setHazirlayanText(rs.getString("hazirlayan_text"));
			muamele.setBorcluMiktari(rs.getDouble("borclu_miktari"));
			muamele.setPostaneAdi(rs.getString("postane_adi"));
			muamele.setPttIlText(rs.getString("ptt_il_text"));
			muamele.setPttIlceText(rs.getString("ptt_ilce_text"));
			muamele.setAlacakliAdi(rs.getString("alacakli_adi"));
			muamele.setMevduatBankaAdi(rs.getString("mevduat_banka_adi"));
			muamele.setMevduatBankaAdresi(rs.getString("mevduat_banka_adresi"));
			muamele.setBuroIbanNo(rs.getString("buro_iban_no"));
			muamele.setKonu(rs.getString("konu"));
			muamele.setMernisAdresi(rs.getString("mernis_adresi"));
			muamele.setSgkAdresi(rs.getString("sgk_adresi"));
			muamele.setYurticiAdresi(rs.getString("yurtici_adresi"));
			muamele.setAlacakFaizTutari(rs.getDouble("alacak_faiz_tutari"));
			muamele.setEki(rs.getString("eki"));
			muamele.setDogumTarihi(rs.getDate("dogum_tarihi"));

			List.add(muamele);
		}

		disconnectDB();

		return List;

	}

	@SuppressWarnings("resource")
	public MuameleIslemleri getMuameleFromList(int id) throws Exception {

		newConnectDB();
		String SQL = "select * from vw_muamele_islemi where muameleid=" + id;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		MuameleIslemleri islm = null;

		while (rs.next()) {
			islm = new MuameleIslemleri();
			islm.setId(rs.getInt("muameleid"));
			islm.setBorcluAdi(rs.getString("borcluadi"));
			islm.setTalepIfadesi(rs.getString("talepifadesi"));
			islm.setMasrafMiktari(rs.getDouble("masrafmiktari"));
			islm.setMalBilgisi(rs.getString("malbilgisi"));
			islm.setMuhatapAdi(rs.getString("muhatapadi").toString());
			islm.setMuhatapAdresi(rs.getString("muhatapadresi").toString());
			islm.setHacizSirasi(rs.getInt("hacizsirasi"));
			islm.setTebligatSonucu(rs.getString("tebligat_sonucu"));
			islm.setHacizMiktari(rs.getInt("haciz_miktari"));
			islm.setMaasMuvafakat(rs.getString("maas_muvafakat"));
			islm.setAciklama(rs.getString("aciklama"));
			islm.setBarcode(rs.getString("barcode"));
			islm.setContentId(rs.getInt("contentid"));
			islm.setIcraDosyaId(rs.getInt("icradosyaid"));
			islm.setAlacakliTel(rs.getString("alacakli_tel"));
			islm.setBankaBilgileri(rs.getString("banka_bilgileri"));
			islm.setBorcluTc(rs.getString("borclu_tc"));
			islm.setBorcluAdresi(rs.getString("borclu_adresi"));
			islm.setAlacakliMail(rs.getString("alacakli_mail"));
			islm.setSemiramisNo(rs.getInt("semiramis_no"));
			islm.setIcraDosyaNo(rs.getString("icra_dosya_no"));
			islm.setMuzekkereTalepAdi(rs.getString("muzekkere_talep_adi"));
			islm.setMuzekkereTalepMiktari(rs.getInt("muzekkere_talep_miktari"));
			islm.setMasrafTipiId(rs.getInt("masraftipiid"));
			islm.setMalTipiId(rs.getInt("maltipiid"));
			islm.setPersonelId(rs.getInt("personel_id"));
			islm.setHazirlayanId(rs.getInt("hazirlayan_id"));
			islm.setMuameleStatusuId(rs.getInt("muamele_statusu_id"));
			islm.setAlacakliBankasi(rs.getString("alacakli_bankasi"));
			islm.setMasrafTipiAdi(rs.getString("masraftipiadi"));
			islm.setMalTipiAdi(rs.getString("maltipiadi"));
			islm.setMuameleStatuAdi(rs.getString("muamelestatuadi"));
			islm.setIcraMudurluguAdi(rs.getString("icramudurluguadi"));
			islm.setBaslik(rs.getString("baslik"));
			islm.setParagraf1(rs.getString("p1"));
			islm.setParagraf2(rs.getString("p2"));
			islm.setIcraMudurluguId(rs.getInt("icramudurluguid"));
			islm.setHazirlayanAdSoyad(rs.getString("isimsoyisim"));
			islm.setAvukatId(rs.getInt("avukatid"));
			islm.setMiktar(rs.getInt("mtmiktar"));
			islm.setKurumAdi(rs.getString("kurum_adi"));
			islm.setAlacakliBankasi(rs.getString("alacakli_bankasi"));
			islm.setTapuKayitlari(rs.getString("tapu_kayitlari"));
			islm.setVergiKimlikNo(rs.getString("vergi_kimlik_no"));
			islm.setBankaBilgileri(rs.getString("banka_bilgileri"));
			islm.setBuroAdresi(rs.getString("buro_adresi"));
			islm.setPlaka(rs.getString("plaka"));
			islm.setMuzekkereTalepAdi(rs.getString("muzekkere_talep_adi"));
			islm.setRiskYoneticisiText(rs.getString("risk_yoneticisi_text"));
			islm.setHazirlayanText(rs.getString("hazirlayan_text"));
			islm.setBorcluMiktari(rs.getDouble("borclu_miktari"));
			islm.setPostaneAdi(rs.getString("postane_adi"));
			islm.setPttIlText(rs.getString("ptt_il_text"));
			islm.setPttIlceText(rs.getString("ptt_ilce_text"));
			islm.setAlacakliAdi(rs.getString("alacakli_adi"));
			islm.setMevduatBankaAdi(rs.getString("mevduat_banka_adi"));
			islm.setMevduatBankaAdresi(rs.getString("mevduat_banka_adresi"));
			islm.setBuroIbanNo(rs.getString("buro_iban_no"));
			islm.setKonu(rs.getString("konu"));
			islm.setMernisAdresi(rs.getString("mernis_adresi"));
			islm.setSgkAdresi(rs.getString("sgk_adresi"));
			islm.setYurticiAdresi(rs.getString("yurtici_adresi"));
			islm.setAlacakFaizTutari(rs.getDouble("alacak_faiz_tutari"));
			islm.setMuameleTarihi(rs.getDate("muamele_tarihi"));
			islm.setMuameleTarihiText(DateToText(rs.getDate("muamele_tarihi")));
			islm.setTebligatTarihi(rs.getDate("tebligat_tarihi"));
			islm.setTebligatTarihiText(DateToText(rs.getDate("tebligat_tarihi")));
			islm.setHacizBaslangicTarihi(rs.getDate("haciz_baslangic_tarihi"));
			islm.setHacizBaslangicTarihiText(DateToText(rs.getDate("haciz_baslangic_tarihi")));
			islm.setEki(rs.getString("eki"));
			islm.setDogumTarihiText(DateToText(rs.getDate("dogum_tarihi")));
			islm.setTapuMudurlugu(rs.getString("tapu_mudurluk"));
			islm.setTapuIl(rs.getString("tapu_il"));
			islm.setTapuIlce(rs.getString("tapu_ilce"));
			islm.setTapuMudurluguIlIlce(rs.getString("tapu_ilce")+" "+rs.getString("tapu_il"));

			rs = stmt.executeQuery("SELECT * FROM tbl_vekil_bilgisi where id ='" + rs.getInt("avukatid") + "';");

			if (rs.isBeforeFirst()) {
				rs.next();
				islm.setAvukatAdi(rs.getString("adi")+" "+rs.getString("soyadi"));
			}

		}

		disconnectDB();

		return islm;

	}

	public void deleteRowIndex(String muzekkereTalep, String icraDosyaNo, String kurumAdi, String alacakliBankasi,
			String tapuKayitlari) throws Exception {

		String SQL = "DELETE FROM tbl_muamele_islemi WHERE muzekkere_talep_adi='" + muzekkereTalep
				+ "' and icra_dosya_no='" + icraDosyaNo + "' and kurum_adi='" + kurumAdi + "' and alacakli_bankasi='"
				+ alacakliBankasi + "' and tapu_kayitlari='" + tapuKayitlari + "'";

		newConnectDB();
		PreparedStatement ps = conn.prepareStatement(SQL);
		ps.executeUpdate();
		disconnectDB();
	}

	public String getPdfAdi(String muzekkereTalep) throws Exception {

		newConnectDB();
		String SQL = "select  *  from tbl_muamele_islemleri_pdf where talep_muzekkere_adi = '" + muzekkereTalep + "'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		String pdfAdi = null;
		while (rs.next()) {

			pdfAdi = rs.getString("pdf_adi");

			return pdfAdi;
		}

		disconnectDB();

		return null;

	}

	public ArrayList<StandartTalep> getStandartTalepTextList() throws SQLException {

		newConnectDB();

		String SQL = "select * from tbl_talep_standart_text";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		StandartTalep standartTalep = null;
		ArrayList<StandartTalep> standartTalepList = new ArrayList<StandartTalep>();

		while (rs.next()) {

			standartTalep = new StandartTalep();
			standartTalep.setAdi(rs.getString("adi"));
			standartTalep.setBankaIsmi(rs.getString("banka_ismi"));
			standartTalep.setStandartText(rs.getString("standart_text"));
			standartTalep.setId(rs.getInt("id"));

			standartTalepList.add(standartTalep);

		}

		return standartTalepList;

	}

	public DortluYapi dortluYapiGetir() throws SQLException {

		newConnectDB();
		String SQL = "select * from tbl_talep_standart_text";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		DortluYapi yapi = new DortluYapi();

		while (rs.next()) {

			switch (rs.getString("adi")) {

			case "EGM Sorgu Talebi":
				yapi.setEgm_standart_text(rs.getString("standart_text"));
				break;

			case "Maaş Haciz Talebi (Genel)":
				yapi.setSgk_standart_text(rs.getString("standart_text"));
				break;

			case "PTT Haciz Talebi":
				yapi.setPosta_standart_text(rs.getString("standart_text"));
				break;

			case "Tapu Haciz Talebi":
				yapi.setTapu_standart_text(rs.getString("standart_text"));
				break;

			}
		}

		return yapi;
	}

	public JasperPrint cokluTalepGetirFor(MuameleIslemleri muamele, String muzekkereTalep)
			throws FileNotFoundException, SQLException, JRException {

		JasperPrint cokluFourPrint = new JasperPrint();
		ArrayList<CokluBankaFour> dataBeanListForCokluBankaFour = new ArrayList<CokluBankaFour>();
		ArrayList<StandartTalep> standartList = new ArrayList<StandartTalep>();
		CokluBankaFour cokluBankaFour = new CokluBankaFour();

		standartList = getStandartTalepTextList();
		cokluBankaFour.setIcraDosyaNo(muamele.getIcraDosyaNo());
		cokluBankaFour.setIcraMudurluguAdi(muamele.getIcraMudurluguAdi());
		cokluBankaFour.setTalepMuzekkereAdi(muzekkereTalep);

		for (StandartTalep standartTalep : standartList) {

			if (standartTalep.getAdi().equals("Maaş Haciz Talebi (Genel)")) {

				cokluBankaFour.setSgk_standart_text(standartTalep.getStandartText());

			} else if (standartTalep.getAdi().equals("Tapu Haciz Talebi")) {

				cokluBankaFour.setTapu_standart_text(standartTalep.getStandartText());

			} else if (standartTalep.getAdi().equals("PTT Haciz Talebi")) {

				cokluBankaFour.setPosta_standart_text(standartTalep.getStandartText());

			} else if (standartTalep.getAdi().equals("EGM Sorgu Talebi")) {

				cokluBankaFour.setEgm_standart_text(standartTalep.getStandartText());

			} else if (standartTalep.getAdi().equals("Banka4")) {

				cokluBankaFour.setBanka4(standartTalep.getStandartText());

			} else if (standartTalep.getAdi().equals("Banka7")) {

				cokluBankaFour.setBanka7(standartTalep.getStandartText());
			}

		}

		dataBeanListForCokluBankaFour.add(cokluBankaFour);

		HashMap<String, Object> parameters = new HashMap<String, Object>();

		String pathName = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/reports/4lu5banka.jrxml");

		InputStream inputStream = new FileInputStream(pathName);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanListForCokluBankaFour);
		JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		cokluFourPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);

		return cokluFourPrint;

	}

}
