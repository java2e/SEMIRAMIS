package pelops.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.controller.AktifBean;
import pelops.db.DBConnection;
import pelops.model.AlacakliBilgiler;

public class AlacakliDAO extends DBConnection {

	ArrayList<AlacakliBilgiler> alacakliListesi = new ArrayList<AlacakliBilgiler>();
	String SQL;
	ResultSet rs;
	Statement stmt;
	PreparedStatement pstmt;

	public boolean Kaydet(AlacakliBilgiler alacakli) {

		boolean kaydedildi = false;

		String SQL = "INSERT INTO tbl_alacakli_bilgisi(muvekkil_adi, muvekkil_tipi_id, muvekkil_sube_adi, ticaret_sicil_no, "
				+ "vergi_no, eposta, web_adresi, notlar, sube_tipi_id, "
				+ " musteri_no, vergi_dairesi, adres_turu_id, telefon_tipi_id, il_id, "
				+ " telefon_no, ilce_id, telefon_statusu, semt_adi, adres_statusu_id, adres, icra_dosyasi_id)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		try {

			newConnectDB();

			PreparedStatement pstmt = conn.prepareStatement(SQL.toString());
		
			pstmt.setString(1, alacakli.getMuvekkilAdi());
			pstmt.setInt(2, alacakli.getMuvekkilTipiId());
			pstmt.setString(3, alacakli.getMuvekkilSubeAdi());
			pstmt.setString(4, alacakli.getTicaretSicilNo());
			pstmt.setString(5, alacakli.getVergiNo());
			pstmt.setString(6, alacakli.getePosta());
			pstmt.setString(7, alacakli.getWebAdres());
			pstmt.setString(8, alacakli.getNotlar());
			pstmt.setInt(9, alacakli.getSubeTipiId());
			pstmt.setString(10, alacakli.getMusteriNo());
			pstmt.setString(11, alacakli.getVergiDairesi());
			pstmt.setInt(12, alacakli.getAdresTuruId());
			pstmt.setInt(13, alacakli.getTelefonTipiId());
			pstmt.setInt(14, alacakli.getId());
			pstmt.setString(15, alacakli.getTelefonNo());
			pstmt.setInt(16, alacakli.getIlceId());
			pstmt.setString(17, alacakli.getTelefonStatusu());
			pstmt.setString(18, alacakli.getSemtAdi());
			pstmt.setInt(19, alacakli.getAdresStatusuId());
			pstmt.setString(20, alacakli.getAdres());

			pstmt.setInt(21, AktifBean.getIcraDosyaID());

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

	public int MuvekkilIdGetir(String MuvekkilAdi) {

		String SQL = "SELECT id, muvekkil_adi  FROM tbl_alacakli_bilgisi where muvekkil_adi='" + MuvekkilAdi + "';";
		int MuvekkilId = 0;
		try {
			newConnectDB();
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);

			while (rs.next()) {

				MuvekkilId = rs.getInt("id");

			}

			disconnectDB();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return MuvekkilId;
	}

	public ArrayList<AlacakliBilgiler> ListeGetir() {

		String SQL = "SELECT id, muvekkil_adi, muvekkil_tipi_id, muvekkil_sube_adi, ticaret_sicil_no, "
				+ " vergi_no, eposta, web_adresi, notlar, sube_tipi_id, musteri_no, "
				+ " vergi_dairesi, adres_turu_id, telefon_tipi_id, il_id, telefon_no, "
				+ " ilce_id, semt_adi, adres_statusu_id, adres, telefon_statusu FROM tbl_alacakli_bilgisi;";
	
		try {

			newConnectDB();

			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			AlacakliBilgiler alacakli;

			while (rs.next()) {
				alacakli = new AlacakliBilgiler();
				alacakli.setId(rs.getInt("id"));
				alacakli.setMuvekkilAdi(rs.getString("muvekkil_adi"));
				alacakli.setMuvekkilTipiId(rs.getInt("muvekkil_tipi_id"));
				alacakli.setMuvekkilSubeAdi(rs.getString("muvekkil_sube_adi"));
				alacakli.setTicaretSicilNo(rs.getString("ticaret_sicil_no"));
				alacakli.setVergiNo(rs.getString("vergi_no"));
				alacakli.setePosta(rs.getString("eposta"));
				alacakli.setWebAdres(rs.getString("web_adresi"));
				alacakli.setNotlar(rs.getString("notlar"));
				alacakli.setSubeTipiId(rs.getInt("sube_tipi_id"));
				alacakli.setMusteriNo(rs.getString("musteri_no"));
				alacakli.setVergiDairesi(rs.getString("vergi_dairesi"));
				alacakli.setAdresTuruId(rs.getInt("adres_turu_id"));
				alacakli.setTelefonTipiId(rs.getInt("telefon_tipi_id"));
				alacakli.setTelefonNo(rs.getString("telefon_no"));
				alacakli.setIlceId(rs.getInt("ilce_id"));
				alacakli.setSemtAdi(rs.getString("semt_adi"));
				alacakli.setAdresStatusuId(rs.getInt("adres_statusu_id"));
				alacakli.setAdres(rs.getString("adres"));
				alacakli.setTelefonStatusu(rs.getString("telefon_statusu"));

				alacakliListesi.add(alacakli);
			}

			disconnectDB();

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		return alacakliListesi;

	}

	public ArrayList<AlacakliBilgiler> ListeyiYenile() {
		
		alacakliListesi = new ArrayList<AlacakliBilgiler>();
		  


		String SQL = "SELECT id, muvekkil_adi, muvekkil_tipi_id, muvekkil_sube_adi, ticaret_sicil_no, "
				+ " vergi_no, eposta, web_adresi, notlar, sube_tipi_id, musteri_no, "
				+ " vergi_dairesi, adres_turu_id, telefon_tipi_id, il_id, telefon_no, "
				+ " ilce_id, semt_adi, adres_statusu_id, adres, telefon_statusu FROM tbl_alacakli_bilgisi;";

		try {

			newConnectDB();

			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			AlacakliBilgiler alacakli;

			while (rs.next()) {
				alacakli = new AlacakliBilgiler();
				alacakli.setId(rs.getInt("id"));
				alacakli.setMuvekkilAdi(rs.getString("muvekkil_adi"));
				alacakli.setMuvekkilTipiId(rs.getInt("muvekkil_tipi_id"));
				alacakli.setMuvekkilSubeAdi(rs.getString("muvekkil_sube_adi"));
				alacakli.setTicaretSicilNo(rs.getString("ticaret_sicil_no"));
				alacakli.setVergiNo(rs.getString("vergi_no"));
				alacakli.setePosta(rs.getString("eposta"));
				alacakli.setWebAdres(rs.getString("web_adresi"));
				alacakli.setNotlar(rs.getString("notlar"));
				alacakli.setSubeTipiId(rs.getInt("sube_tipi_id"));
				alacakli.setMusteriNo(rs.getString("musteri_no"));
				alacakli.setVergiDairesi(rs.getString("vergi_dairesi"));
				alacakli.setAdresTuruId(rs.getInt("adres_turu_id"));
				alacakli.setTelefonTipiId(rs.getInt("telefon_tipi_id"));
				alacakli.setTelefonNo(rs.getString("telefon_no"));
				alacakli.setIlceId(rs.getInt("ilce_id"));
				alacakli.setSemtAdi(rs.getString("semt_adi"));
				alacakli.setAdresStatusuId(rs.getInt("adres_statusu_id"));
				alacakli.setAdres(rs.getString("adres"));
				alacakli.setTelefonStatusu(rs.getString("telefon_statusu"));

				alacakliListesi.add(alacakli);
			}

			disconnectDB();

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		return alacakliListesi;

	}

	public AlacakliBilgiler ListeGetir(int id) {

		String SQL = "SELECT id, muvekkil_adi, muvekkil_tipi_id, muvekkil_sube_adi, ticaret_sicil_no, "
				+ " vergi_no, eposta, web_adresi, notlar, sube_tipi_id, musteri_no, "
				+ " vergi_dairesi, adres_turu_id, telefon_tipi_id, il_id, telefon_no, "
				+ " ilce_id, semt_adi, adres_statusu_id, adres, telefon_statusu FROM tbl_alacakli_bilgisi where id="
				+ id + ";";

		AlacakliBilgiler alacakli = null;
		try {

			newConnectDB();

			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);

			while (rs.next()) {
				alacakli = new AlacakliBilgiler();
				alacakli.setId(rs.getInt("id"));
				alacakli.setMuvekkilAdi(rs.getString("muvekkil_adi"));
				alacakli.setMuvekkilTipiId(rs.getInt("muvekkil_tipi_id"));
				alacakli.setMuvekkilSubeAdi(rs.getString("muvekkil_sube_adi"));
				alacakli.setTicaretSicilNo(rs.getString("ticaret_sicil_no"));
				alacakli.setVergiNo(rs.getString("vergi_no"));
				alacakli.setePosta(rs.getString("eposta"));
				alacakli.setWebAdres(rs.getString("web_adresi"));
				alacakli.setNotlar(rs.getString("notlar"));
				alacakli.setSubeTipiId(rs.getInt("sube_tipi_id"));
				alacakli.setMusteriNo(rs.getString("musteri_no"));
				alacakli.setVergiDairesi(rs.getString("vergi_dairesi"));
				alacakli.setAdresTuruId(rs.getInt("adres_turu_id"));
				alacakli.setTelefonTipiId(rs.getInt("telefon_tipi_id"));
				alacakli.setTelefonNo(rs.getString("telefon_no"));
				alacakli.setIlceId(rs.getInt("ilce_id"));
				alacakli.setSemtAdi(rs.getString("semt_adi"));
				alacakli.setAdresStatusuId(rs.getInt("adres_statusu_id"));
				alacakli.setAdres(rs.getString("adres"));
				alacakli.setTelefonStatusu(rs.getString("telefon_statusu"));
			}
			disconnectDB();

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		return alacakli;

	}

	public boolean sil(int id) {

		SQL = "DELETE FROM tbl_alacakli_bilgisi WHERE id= ?;";

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

	public ArrayList<AlacakliBilgiler> getAllListFromIcraDosyaID(int icraDosyaID) throws Exception {

		ArrayList<AlacakliBilgiler> list = new ArrayList<AlacakliBilgiler>();

		SQL = "SELECT id, muvekkil_adi, muvekkil_tipi_id, muvekkil_sube_adi, ticaret_sicil_no, "
				+ "vergi_no, eposta, web_adresi, notlar, icra_dosyasi_no, sube_tipi_id, "
				+ " musteri_no, vergi_dairesi, adres_turu_id, telefon_tipi_id, il_id, "
				+ " telefon_no, ilce_id, semt_adi, adres_statusu_id, adres, telefon_statusu FROM tbl_alacakli_bilgisi"
				+ " where icra_dosyasi_id=" + icraDosyaID + ";";

		newConnectDB();

		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		while (rs.next()) {

			AlacakliBilgiler alacakliBilgisi = new AlacakliBilgiler();

			alacakliBilgisi.setId(rs.getInt("id"));
			alacakliBilgisi.setMuvekkilAdi(rs.getString("muvekkil_adi"));
			alacakliBilgisi.setMuvekkilTipiId(rs.getInt("muvekkil_tipi_id"));
			alacakliBilgisi.setMuvekkilSubeAdi(rs.getString("muvekkil_sube_adi"));
			alacakliBilgisi.setTicaretSicilNo(rs.getString("ticaret_sicil_no"));
			alacakliBilgisi.setVergiNo(rs.getString("vergi_no"));
			alacakliBilgisi.setePosta(rs.getString("vergi_no"));
			alacakliBilgisi.setWebAdres(rs.getString("web_adresi"));
			alacakliBilgisi.setNotlar(rs.getString("notlar"));
			alacakliBilgisi.setIcraDosyasiNo(rs.getString("icra_dosyasi_no"));
			alacakliBilgisi.setSubeTipiId(rs.getInt("sube_tipi_id"));
			alacakliBilgisi.setMusteriNo(rs.getString("musteri_no"));
			alacakliBilgisi.setVergiDairesi(rs.getString("vergi_dairesi"));
			alacakliBilgisi.setVergiDairesi(rs.getString("vergi_dairesi"));
			alacakliBilgisi.setAdresTuruId(rs.getInt("adres_turu_id"));
			alacakliBilgisi.setTelefonTipiId(rs.getInt("telefon_tipi_id"));
			alacakliBilgisi.setIlId(rs.getInt("il_id"));
			alacakliBilgisi.setTelefonNo(rs.getString("telefon_no"));
			alacakliBilgisi.setIlceId(rs.getInt("ilce_id"));
			alacakliBilgisi.setSemtAdi(rs.getString("semt_adi"));
			alacakliBilgisi.setAdresStatusuId(rs.getInt("adres_statusu_id"));
			alacakliBilgisi.setAdres(rs.getString("adres"));
			alacakliBilgisi.setTelefonStatusu(rs.getString("telefon_statusu"));

			//
			list.add(alacakliBilgisi);
		}

		disconnectDB();

		return list;

	}

	public boolean guncelle(AlacakliBilgiler alacakliBilgisi) throws Exception {

		boolean duzenlendi = false;

		SQL = "UPDATE tbl_alacakli_bilgisi SET muvekkil_adi=?, muvekkil_tipi_id=?, muvekkil_sube_adi=?, "
				+ "ticaret_sicil_no=?, vergi_no=?, eposta=?, web_adresi=?, notlar=?, "
				+ " icra_dosyasi_no=?, sube_tipi_id=?, musteri_no=?, vergi_dairesi=?, "
				+ "adres_turu_id=?, telefon_tipi_id=?, il_id=?, telefon_no=?, ilce_id=?, "
				+ " semt_adi=?, adres_statusu_id=?, adres=?, telefon_statusu=?, icra_dosyasi_id=? WHERE  muvekkil_adi= '"
				+ alacakliBilgisi.getMuvekkilAdi() + "'";

		newConnectDB();
		try {

			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, alacakliBilgisi.getMuvekkilAdi());
			pstmt.setInt(2, alacakliBilgisi.getMuvekkilTipiId());
			pstmt.setString(3, alacakliBilgisi.getMuvekkilSubeAdi());
			pstmt.setString(4, alacakliBilgisi.getTicaretSicilNo());
			pstmt.setString(5, alacakliBilgisi.getVergiNo());
			pstmt.setString(6, alacakliBilgisi.getePosta());
			pstmt.setString(7, alacakliBilgisi.getWebAdres());
			pstmt.setString(8, alacakliBilgisi.getNotlar());
			pstmt.setString(9, alacakliBilgisi.getIcraDosyasiNo());
			pstmt.setInt(10, alacakliBilgisi.getSubeTipiId());
			pstmt.setString(11, alacakliBilgisi.getMusteriNo());
			pstmt.setString(12, alacakliBilgisi.getVergiDairesi());
			pstmt.setInt(13, alacakliBilgisi.getAdresTuruId());
			pstmt.setInt(14, alacakliBilgisi.getTelefonTipiId());
			pstmt.setInt(15, alacakliBilgisi.getIlId());
			pstmt.setString(16, alacakliBilgisi.getTelefonNo());
			pstmt.setInt(17, alacakliBilgisi.getIlceId());
			pstmt.setString(18, alacakliBilgisi.getSemtAdi());
			pstmt.setInt(19, alacakliBilgisi.getAdresStatusuId());
			pstmt.setString(20, alacakliBilgisi.getAdres());
			pstmt.setString(21, alacakliBilgisi.getTelefonStatusu());
			pstmt.setInt(22, AktifBean.getIcraDosyaID());

			int sonuc = pstmt.executeUpdate();

			disconnectDB();
			if (sonuc == 1) {

				duzenlendi = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return duzenlendi;

	}

	// public static void main(String[] args) {
	// AlacakliDAO dao = new AlacakliDAO();
	// dao.ListeGetir();
	// }
}
