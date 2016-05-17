package pelops.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sun.jna.platform.win32.Sspi.PSecHandle;

import pelops.db.DBConnection;
import pelops.model.Avukat;
import pelops.model.MuameleIslemleri;


public class MuameleIslemleriDAO extends DBConnection {

	@SuppressWarnings("resource")
	public void MuzekkereTalepEkle(MuameleIslemleri muamele, ArrayList<MuameleIslemleri> muameleList) throws Exception {

		newConnectDB();

		for (int i = 0; i < muameleList.size(); i++) {

			Statement stmt = conn.createStatement();
			ResultSet result = null;

			System.out.println(muameleList.get(i).getIcraDosyaNo());
			System.out.println(muameleList.get(i).getMuzekkereTalepAdi());

			result = stmt.executeQuery(
					"SELECT * FROM tbl_muamele_islemi where icra_dosya_no='" + muameleList.get(i).getIcraDosyaNo()
							+ "' and muzekkere_talep_adi='" + muameleList.get(i).getMuzekkereTalepAdi() + "';");

			if (!(result.isBeforeFirst())) {

				String SQL = "INSERT INTO tbl_muamele_islemi(borclu_adi, muamele_tarihi, talep_ifadesi, masraf_tipi_id,"
						+ "masraf_miktari, mal_tipi_id, mal_bilgisi, personel_id, avukat_id,"
						+ "muhatap_adi, muamele_statusu_id, muhatap_adresi, haciz_sirasi,"
						+ "tebligat_tarihi, haciz_baslangic_tarihi, tebligat_sonucu, haciz_miktari,"
						+ "maas_muvafakat, aciklama, icra_mudurlugu_id, barcode, content_id,icra_dosya_id,"
						+ "alacakli_mail,hazirlayan_id,alacakli_tel,banka_bilgileri,borclu_tc,borclu_adresi,"
						+ "semiramis_no,alacakli_bankasi,icra_dosya_no,muzekkere_talep_adi,muzekkere_talep_miktari,alacak_faiz_tutari,baslik,paragraf_1,paragraf_2)"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?, ?, ?, ?,?,?,?);";

				PreparedStatement pstmt = conn.prepareStatement(SQL.toString());

				pstmt.setString(1, muamele.getBorcluAdi());
				pstmt.setDate(2, convertFromJAVADateToSQLDate(muamele.getMuameleTarihi()));
				pstmt.setString(3, muamele.getTalepIfadesi());
				pstmt.setInt(4, muamele.getMasrafTipiId());
				pstmt.setInt(5, muamele.getMasrafMiktari());
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
				pstmt.setInt(30, muamele.getSemiramisNo());
				pstmt.setString(31, muamele.getAlacakliBankasi());
				pstmt.setString(32, muamele.getIcraDosyaNo());
				pstmt.setString(33, muameleList.get(i).getMuzekkereTalepAdi());
				pstmt.setInt(34, muameleList.get(i).getMiktar());
				pstmt.setString(35, muamele.getAlacakFaizTutari());
				pstmt.setString(36, muamele.getBaslik());
				pstmt.setString(37, muamele.getParagraf1());
				pstmt.setString(38, muamele.getParagraf2());

				pstmt.executeUpdate();

				System.out.println("insert");
			}

			else {

				String sql = "UPDATE tbl_muamele_islemi "
						+ "SET borclu_adi = ?,  muamele_tarihi= ? , talep_ifadesi = ?, masraf_tipi_id = ?, masraf_miktari = ?, "
						+ " mal_tipi_id = ?, mal_bilgisi = ?, personel_id = ?, avukat_id = ?, muhatap_adi = ?, "
						+ " muamele_statusu_id = ?, muhatap_adresi = ?, haciz_sirasi = ?,  tebligat_tarihi= ? , haciz_baslangic_tarihi= ?, "
						+ " tebligat_sonucu = ?, "
						+ " haciz_miktari = ?, maas_muvafakat = ?, aciklama = ?, icra_mudurlugu_id= ? , "
						+ " barcode= ? , content_id= ? , icra_dosya_id= ? , alacakli_mail= ? , "
						+ " hazirlayan_id= ? , alacakli_tel= ? , banka_bilgileri= ? , "
						+ " borclu_tc= ? , borclu_adresi= ? ,  alacak_faiz_tutari= ? , "
						+ " semiramis_no= ? , alacakli_bankasi= ? , icra_dosya_no= ? , muzekkere_talep_adi= ? , "
						+ " muzekkere_talep_miktari = ? ,baslik = ? , paragraf_1 = ? , paragraf_2 = ?  WHERE icra_dosya_no = '"
						+ muameleList.get(i).getIcraDosyaNo() + "'  and muzekkere_talep_adi = '"
						+ muameleList.get(i).getMuzekkereTalepAdi() + "'";

				PreparedStatement pstmt = conn.prepareStatement(sql.toString());

				pstmt.setString(1, muamele.getBorcluAdi());
				pstmt.setDate(2, convertFromJAVADateToSQLDate(muamele.getMuameleTarihi()));
				pstmt.setString(3, muamele.getTalepIfadesi());
				pstmt.setInt(4, muamele.getMasrafTipiId());
				pstmt.setInt(5, muamele.getMasrafMiktari());
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
				pstmt.setString(30, muamele.getAlacakFaizMasrafTutari());
				pstmt.setInt(31, muamele.getSemiramisNo());
				pstmt.setString(32, muamele.getAlacakliBankasi());
				pstmt.setString(33, muamele.getIcraDosyaNo());
				pstmt.setString(34, muameleList.get(i).getMuzekkereTalepAdi());
				pstmt.setInt(35, muameleList.get(i).getMiktar());
				pstmt.setString(36, muamele.getBaslik());
				pstmt.setString(37, muamele.getParagraf1());
				pstmt.setString(38, muamele.getParagraf2());
				pstmt.executeUpdate();
				System.out.println("update");
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

	@SuppressWarnings("resource")
	public ArrayList<MuameleIslemleri> getMuzekkereTalepList(String icraDosyaNo) throws Exception {

		ArrayList<MuameleIslemleri> List = new ArrayList<MuameleIslemleri>();
		MuameleIslemleri muamele;
		newConnectDB();
		ResultSet result = null;
		String SQL = "select * from vw_muamele_islemi where icra_dosya_no='" + icraDosyaNo + "'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			muamele = new MuameleIslemleri();
			muamele.setId(rs.getInt("muameleid"));
			muamele.setBorcluAdi(rs.getString("borcluadi"));
			muamele.setTalepIfadesi(rs.getString("talepifadesi"));
			muamele.setMasrafMiktari(rs.getInt("masrafmiktari"));
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
			muamele.setAlacakFaizMasrafTutari(rs.getString("alacak_faiz_tutari"));
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

			// result = stmt.executeQuery("SELECT * FROM tbl_vekil_bilgisi where
			// id ='"+ rs.getInt("avukatid")+"';");
			// result.next();
			// muamele.setAvukatAdi(result.getString("adi"));

			// rs = stmt.executeQuery("SELECT * FROM tbl_vekil_bilgisi where id
			// ='"+ muamele.getAvukatId()+"';");
			// rs.next();
			// muamele.setAvukatAdi(rs.getString("adi"));
			//

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
		ResultSet result = null;
		MuameleIslemleri islm = null;

		while (rs.next()) {
			islm = new MuameleIslemleri();
			islm.setId(rs.getInt("muameleid"));
			islm.setBorcluAdi(rs.getString("borcluadi"));
			islm.setTalepIfadesi(rs.getString("talepifadesi"));
			islm.setMasrafMiktari(rs.getInt("masrafmiktari"));
			islm.setMalBilgisi(rs.getString("malbilgisi"));
			islm.setMuhatapAdi(rs.getString("muhatapadi"));
			islm.setMuhatapAdresi(rs.getString("muhatapadresi"));
			islm.setHacizSirasi(rs.getInt("hacizsirasi"));
			islm.setTebligatSonucu(rs.getString("tebligat_sonucu"));
			islm.setHacizMiktari(rs.getInt("haciz_miktari"));
			islm.setMaasMuvafakat(rs.getString("maas_muvafakat"));
			islm.setAciklama(rs.getString("aciklama"));
			islm.setBarcode(rs.getString("barcode"));
			islm.setContentId(rs.getInt("contentid"));
			islm.setIcraDosyaId(rs.getInt("icradosyaid"));
			islm.setMuameleTarihi(rs.getDate("muamele_tarihi"));
			islm.setTebligatTarihi(rs.getDate("tebligat_tarihi"));
			islm.setHacizBaslangicTarihi(rs.getDate("haciz_baslangic_tarihi"));
			islm.setAlacakliTel(rs.getString("alacakli_tel"));
			islm.setBankaBilgileri(rs.getString("banka_bilgileri"));
			islm.setBorcluTc(rs.getString("borclu_tc"));
			islm.setBorcluAdresi(rs.getString("borclu_adresi"));
			islm.setAlacakliMail(rs.getString("alacakli_mail"));
			islm.setAlacakFaizMasrafTutari(rs.getString("alacak_faiz_tutari"));
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
			rs = stmt.executeQuery("SELECT * FROM tbl_vekil_bilgisi where id ='" + rs.getInt("avukatid") + "';");

			if (rs.isBeforeFirst()) {
				rs.next();
				islm.setAvukatAdi(rs.getString("adi"));
			}

		}

		disconnectDB();

		return islm;

	}

	public void deleteRowIndex(String muzekkereTalep, String icraDosyaNo) throws Exception {
		String SQL = "DELETE FROM tbl_muamele_islemi WHERE muzekkere_talep_adi='" + muzekkereTalep
				+ "' and icra_dosya_no='" + icraDosyaNo + "'";
		newConnectDB();
		PreparedStatement ps = conn.prepareStatement(SQL);
		ps.executeUpdate();
		disconnectDB();
	}

	public String getPdfAdi(String muzekkereTalep) throws Exception {

		System.out.println("geldiiii");
		newConnectDB();
		String SQL = "select  *  from tbl_muamele_islemleri_pdf where talep_muzekkere_adi = '" + muzekkereTalep + "'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		MuameleIslemleri islm = null;
		String pdfAdi = null;
		while (rs.next()) {
			pdfAdi = rs.getString("pdf_adi");
			System.out.println("pdf_adi" + pdfAdi);
			return pdfAdi;
		}

		disconnectDB();

		return null;

	}

}
