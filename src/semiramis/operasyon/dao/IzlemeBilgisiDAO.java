package semiramis.operasyon.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.controller.AktifBean;
import pelops.db.DBConnection;
import semiramis.operasyon.model.IzlemeBilgisi;

public class IzlemeBilgisiDAO extends DBConnection {

	String SQL;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	java.sql.Date izlemeTarihi;
	java.sql.Date sonOdemeSozuTarihi;

	public boolean kaydet(IzlemeBilgisi izlemeKayit) {

		boolean kaydedildi = false;

	

			izlemeTarihi = convertFromJAVADateToSQLDate(izlemeKayit.getIzlemeTarihi());
			sonOdemeSozuTarihi = convertFromJAVADateToSQLDate(izlemeKayit.getOdemeSozuTarihi());

			SQL = "INSERT INTO tbl_izleme_bilgisi("
					+ " izleme_tarihi, izleme_sonucu_id, odeme_sozu_tarihi, odeme_sozu_miktari, "
					+ "  aciklama, personel_id, icra_dosyasi_id,vizit_durumu,guncelleme_tarihi)"
					+ "  VALUES (?, ?, ?, ?, ?, ?, ?,?,now());";

			try {
				newConnectDB();

				pstmt = conn.prepareStatement(SQL);
				pstmt.setDate(1, izlemeTarihi);
				pstmt.setInt(2, izlemeKayit.getIzlemeSonucuId());
				pstmt.setDate(3, sonOdemeSozuTarihi);
				pstmt.setDouble(4, izlemeKayit.getOdemeSozuMiktari()==null ? 0 : izlemeKayit.getOdemeSozuMiktari());
				pstmt.setString(5, izlemeKayit.getAciklama());
				pstmt.setInt(6, izlemeKayit.getPersonelId());
				pstmt.setInt(7, AktifBean.getIcraDosyaID());
				pstmt.setInt(8, izlemeKayit.isVizitDurumu() == true ? 1 : 0);

				int result = pstmt.executeUpdate();

				if (result == 1) {

					kaydedildi = true;
				}

				disconnectDB();

			} catch (Exception ex) {

				ex.printStackTrace();
			}
		
		return kaydedildi;

	}

	public boolean sil(int id) {

		String SQL = "DELETE FROM tbl_izleme_bilgisi WHERE id = ?;";

		boolean silindi = true;

		try {
			newConnectDB();
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);

			// execute delete SQL statement
			int result = pstmt.executeUpdate();

			disconnectDB();

			if (result == 1) {

				silindi = false;
			}

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		return silindi;

	}

	public boolean guncelle(IzlemeBilgisi izlemeKayit) throws Exception {

		boolean duzenlendi = false;

		izlemeTarihi = convertFromJAVADateToSQLDate(izlemeKayit.getIzlemeTarihi());
		sonOdemeSozuTarihi = convertFromJAVADateToSQLDate(izlemeKayit.getOdemeSozuTarihi());

		SQL = "UPDATE tbl_izleme_bilgisi SET izleme_tarihi=?, odeme_sozu_tarihi=?, odeme_sozu_miktari=?, "
				+ "aciklama=?, personel_id=?, icra_dosyasi_id=?, izleme_sonucu_id=?,vizit_durumu=? , guncelleme_tarihi=now() "
				+ "WHERE id=" + izlemeKayit.getId() + ";";

		newConnectDB();

		pstmt = conn.prepareStatement(SQL);
		pstmt.setDate(1, izlemeTarihi);
		pstmt.setDate(2, sonOdemeSozuTarihi);
		pstmt.setDouble(3, izlemeKayit.getOdemeSozuMiktari());
		pstmt.setString(4, izlemeKayit.getAciklama());
		pstmt.setInt(5, izlemeKayit.getPersonelId());
		pstmt.setInt(6, izlemeKayit.getIcraDosyasiId());
		pstmt.setInt(7, izlemeKayit.getIzlemeSonucuId());
		pstmt.setInt(8, izlemeKayit.isVizitDurumu() == true ? 1 : 0);

		duzenlendi = pstmt.execute();

		disconnectDB();

		return duzenlendi;

	}

	public int izlemeSayisi(int icraDosyaId) {

		int sayi = 0;

		try {

			String sql = "SELECT count(icra_dosyasi_id) as cagri_adet "
					+ " FROM tbl_izleme_bilgisi where icra_dosyasi_id=" + icraDosyaId;

			newConnectDB();

			Statement stmt = conn.createStatement();

			ResultSet set = stmt.executeQuery(sql);

			while (set.next()) {
				sayi = set.getInt("cagri_adet");
			}

			disconnectDB();

		} catch (Exception e) {

			System.out.println("Hata izlemebilgiDAO izlemeSayisi :" + e.getMessage());
			// TODO: handle exception
		}

		return sayi;

	}

	public ArrayList<IzlemeBilgisi> getAllListFromIcraDosyaID(int id) throws Exception {

		ArrayList<IzlemeBilgisi> list = new ArrayList<IzlemeBilgisi>();

		SQL = "SELECT ib.id,ib.vizit_durumu, ib.izleme_tarihi, ib.odeme_sozu_tarihi, ib.odeme_sozu_miktari,"
				+ " ib.aciklama, ib.personel_id, ib.icra_dosyasi_id, iz.adi as izleme_sonucu,iz.id as izleme_sonucu_id"
				+ " FROM tbl_izleme_bilgisi ib inner join tbl_izleme_sonucu iz on ib.izleme_sonucu_id=iz.id where ib.icra_dosyasi_id="
				+ id + ";";

		newConnectDB();

		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		while (rs.next()) {

			IzlemeBilgisi kayit = new IzlemeBilgisi();

			kayit.setId(rs.getInt("id"));
			kayit.setIzlemeTarihi(rs.getDate("izleme_tarihi"));
			kayit.setOdemeSozuTarihi(rs.getDate("odeme_sozu_tarihi"));
			kayit.setOdemeSozuMiktari(rs.getDouble("odeme_sozu_miktari"));
			kayit.setAciklama(rs.getString("aciklama"));
			kayit.setPersonelId(rs.getInt("personel_id"));
			kayit.setIcraDosyasiId(rs.getInt("icra_dosyasi_id"));
			kayit.setIzlemeSonucu(rs.getString("izleme_sonucu"));
			kayit.setIzlemeSonucuId(rs.getInt("izleme_sonucu_id"));
			kayit.setVizitDurumu(rs.getInt("vizit_durumu") == 1 ? true : false);

			list.add(kayit);
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

}
