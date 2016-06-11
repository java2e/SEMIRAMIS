package pelops.kasa.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

import pelops.db.DBConnection;
import pelops.kasa.model.Reddiyat;

public class ReddiyatDAO extends DBConnection implements IDAO {

	private Statement stm;
	private ResultSet rs;
	private String sql;
	private PreparedStatement psmt;

	@Override
	public ArrayList<Object> getAllObjFromDB() throws Exception {
		ArrayList<Object> list = new ArrayList<Object>();
		sql = "SELECT id, tahsilat_id, kasa_personel_id, onaylayan_id, sasa_reddiyat_tutar,"
				+ " devlet_reddiyat_tutar, muvekkil_reddiyat_tutar, sasa_durum, devlet_durum, "
				+ "  muvekkil_durum, toplam_tutar, muvekkil_adi,  borclu_adi , tarih FROM tbl_reddiyat;";
		newConnectDB();
		stm = conn.createStatement();
		rs = stm.executeQuery(sql);
		while (rs.next()) {
			Reddiyat reddiyat = new Reddiyat();
			reddiyat.setId(rs.getInt("id"));
			reddiyat.setTahsilatID(rs.getInt("tahsilat_id"));
			reddiyat.setKasaPersonelID(rs.getInt("kasa_personel_id"));
			reddiyat.setOnaylayanID(rs.getInt("onaylayan_id"));
			reddiyat.setSasaReddiyatTutari(rs.getDouble("sasa_reddiyat_tutar"));
			reddiyat.setDevletReddiyatTutari(rs.getDouble("devlet_reddiyat_tutar"));
			reddiyat.setMuvekkilReddiyatTutari(rs.getDouble("muvekkil_reddiyat_tutar"));
			reddiyat.setSasaDurum(rs.getInt("sasa_durum"));
			reddiyat.setDevletDurum(rs.getInt("devlet_durum"));
			reddiyat.setMuvekkilDurum(rs.getInt("muvekkil_durum"));
			reddiyat.setToplamReddiyatTutari(rs.getDouble("toplam_tutar"));
			reddiyat.setBorcluAdi(rs.getString("borclu_adi"));
			reddiyat.setMuvekkilAdi(rs.getString("muvekkil_adi"));
			reddiyat.setTarih(rs.getDate("tarih"));

			list.add(reddiyat);
		}
		disconnectDB();

		return list;
	}

	@Override
	public int insertObjToDB(Object obj) throws Exception {
		int id = 0;
		sql = "INSERT INTO tbl_reddiyat( tahsilat_id, kasa_personel_id, onaylayan_id, sasa_reddiyat_tutar, "
				+ "   devlet_reddiyat_tutar, muvekkil_reddiyat_tutar, sasa_durum, devlet_durum, "
				+ "    muvekkil_durum, toplam_tutar, icra_dosya_id, muvekkil_adi,  borclu_adi, tarih) "
				+ "  VALUES (?, ?, ?, ?, ?,   ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
		if (obj instanceof Reddiyat) {
			newConnectDB();
			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, ((Reddiyat) obj).getTahsilatID());
			psmt.setInt(2, ((Reddiyat) obj).getKasaPersonelID());
			psmt.setInt(3, ((Reddiyat) obj).getOnaylayanID());
			psmt.setDouble(4, ((Reddiyat) obj).getSasaReddiyatTutari());
			psmt.setDouble(5, ((Reddiyat) obj).getDevletReddiyatTutari());
			psmt.setDouble(6, ((Reddiyat) obj).getMuvekkilReddiyatTutari());
			psmt.setInt(7, ((Reddiyat) obj).getSasaDurum());
			psmt.setInt(8, ((Reddiyat) obj).getDevletDurum());
			psmt.setInt(9, ((Reddiyat) obj).getMuvekkilDurum());
			psmt.setDouble(10, ((Reddiyat) obj).getToplamReddiyatTutari());
			psmt.setInt(11, ((Reddiyat) obj).getIcraDosyaID());
			psmt.setString(12, ((Reddiyat) obj).getMuvekkilAdi());
			psmt.setString(13, ((Reddiyat) obj).getBorcluAdi());
			psmt.setDate(14, convertFromJAVADateToSQLDate(new Date()));

			psmt.execute();

			disconnectDB();

			id = getID(obj);

		}

		return id;
	}

	@Override
	public int updateObjFromDB(Object obj) throws Exception {
		int id = 0;
		if (obj instanceof Reddiyat) {

			sql = "UPDATE tbl_reddiyat SET  tahsilat_id=?, kasa_personel_id=?, onaylayan_id=?, sasa_reddiyat_tutar=?, "
					+ "   devlet_reddiyat_tutar=?, muvekkil_reddiyat_tutar=?, sasa_durum=?, "
					+ "    devlet_durum=?, muvekkil_durum=?, toplam_tutar=? ,  muvekkil_adi=?,  "
					+ "borclu_adi=?, tarih=? WHERE id=" + ((Reddiyat) obj).getId() + " ;";
			newConnectDB();
			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, ((Reddiyat) obj).getTahsilatID());
			psmt.setInt(2, ((Reddiyat) obj).getKasaPersonelID());
			psmt.setInt(3, ((Reddiyat) obj).getOnaylayanID());
			psmt.setDouble(4, ((Reddiyat) obj).getSasaReddiyatTutari());
			psmt.setDouble(5, ((Reddiyat) obj).getDevletReddiyatTutari());
			psmt.setDouble(6, ((Reddiyat) obj).getMuvekkilReddiyatTutari());
			psmt.setInt(7, ((Reddiyat) obj).getSasaDurum());
			psmt.setInt(8, ((Reddiyat) obj).getDevletDurum());
			psmt.setInt(9, ((Reddiyat) obj).getMuvekkilDurum());
			psmt.setDouble(10, ((Reddiyat) obj).getToplamReddiyatTutari());
			psmt.setString(11, ((Reddiyat) obj).getMuvekkilAdi());
			psmt.setString(12, ((Reddiyat) obj).getBorcluAdi());
			psmt.setDate(13, convertFromJAVADateToSQLDate(new Date()));
			psmt.execute();

			disconnectDB();

			id = getID(obj);

		}

		return id;
	}

	@Override
	public boolean deleteObjFromDB(int id) throws Exception {
		boolean isTrue = false;
		sql = "DELETE FROM tbl_reddiyat  WHERE id=" + id + ";";
		newConnectDB();
		stm = conn.createStatement();
		isTrue = stm.execute(sql);
		disconnectDB();
		return isTrue;
	}

	@Override
	public Object getObjFromDB(int id) throws Exception {
		sql = "select * from tbl_reddiyat where id=" + id + ";";
		newConnectDB();
		stm = conn.createStatement();
		rs = stm.executeQuery(sql);
		Reddiyat reddiyat = new Reddiyat();
		while (rs.next()) {
			reddiyat.setId(rs.getInt("id"));
			reddiyat.setTahsilatID(rs.getInt("tahsilat_id"));
			reddiyat.setKasaPersonelID(rs.getInt("kasa_personel_id"));
			reddiyat.setOnaylayanID(rs.getInt("onaylayan_id"));
			reddiyat.setSasaReddiyatTutari(rs.getDouble("sasa_reddiyat_tutar"));
			reddiyat.setDevletReddiyatTutari(rs.getDouble("devlet_reddiyat_tutar"));
			reddiyat.setMuvekkilReddiyatTutari(rs.getDouble("muvekkil_reddiyat_tutar"));
			reddiyat.setSasaDurum(rs.getInt("sasa_durum"));
			reddiyat.setDevletDurum(rs.getInt("devlet_durum"));
			reddiyat.setMuvekkilDurum(rs.getInt("muvekkil_durum"));
			reddiyat.setToplamReddiyatTutari(rs.getDouble("toplam_tutar"));
			reddiyat.setIcraDosyaID(rs.getInt("icra_dosya_id"));
			reddiyat.setBorcluAdi(rs.getString("borclu_adi"));
			reddiyat.setMuvekkilAdi(rs.getString("muvekkil_adi"));
			reddiyat.setDevletReddiyatTutariTL(convertDoubleToTL(reddiyat.getDevletReddiyatTutari()));
			reddiyat.setMuvekkilReddiyatTutariTL(convertDoubleToTL(reddiyat.getMuvekkilReddiyatTutari()));
			reddiyat.setSasaReddiyatTutariTL(convertDoubleToTL(reddiyat.getSasaReddiyatTutari()));
			reddiyat.setAktifTutarTL(convertDoubleToTL(reddiyat.getAktifTutar()));
			reddiyat.setTarih(rs.getDate("tarih"));
		}
		disconnectDB();
		return reddiyat;
	}

	@Override
	public int getID(Object object) throws Exception {
		int id = 0;
		if (object instanceof Reddiyat) {
			String sql = "select id from tbl_reddiyat where icra_dosya_id =" + ((Reddiyat) object).getIcraDosyaID()
					+ ";";
			newConnectDB();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				id = rs.getInt("id");

			}
			disconnectDB();

		}
		return id;
	}

	@Override
	public ArrayList<Object> getAllObjFromStatus(int status) throws Exception {
		ArrayList<Object> list = new ArrayList<Object>();
		sql = "SELECT * FROM tbl_reddiyat " + "where sasa_durum =" + status + ";";
		newConnectDB();
		stm = conn.createStatement();
		rs = stm.executeQuery(sql);
		while (rs.next()) {
			Reddiyat reddiyat = new Reddiyat();
			reddiyat.setId(rs.getInt("id"));
			reddiyat.setTahsilatID(rs.getInt("tahsilat_id"));
			reddiyat.setKasaPersonelID(rs.getInt("kasa_personel_id"));
			reddiyat.setOnaylayanID(rs.getInt("onaylayan_id"));
			reddiyat.setSasaReddiyatTutari(rs.getDouble("sasa_reddiyat_tutar"));
			reddiyat.setDevletReddiyatTutari(rs.getDouble("devlet_reddiyat_tutar"));
			reddiyat.setMuvekkilReddiyatTutari(rs.getDouble("muvekkil_reddiyat_tutar"));
			reddiyat.setSasaDurum(rs.getInt("sasa_durum"));
			reddiyat.setDevletDurum(rs.getInt("devlet_durum"));
			reddiyat.setMuvekkilDurum(rs.getInt("muvekkil_durum"));
			reddiyat.setToplamReddiyatTutari(rs.getDouble("toplam_tutar"));
			reddiyat.setBorcluAdi(rs.getString("borclu_adi"));
			reddiyat.setMuvekkilAdi(rs.getString("muvekkil_adi"));
			reddiyat.setDevletReddiyatTutariTL(convertDoubleToTL(reddiyat.getDevletReddiyatTutari()));
			reddiyat.setMuvekkilReddiyatTutariTL(convertDoubleToTL(reddiyat.getMuvekkilReddiyatTutari()));
			reddiyat.setSasaReddiyatTutariTL(convertDoubleToTL(reddiyat.getSasaReddiyatTutari()));
			reddiyat.setAktifTutarTL(convertDoubleToTL(reddiyat.getAktifTutar()));
			reddiyat.setTarih(rs.getDate("tarih"));
			list.add(reddiyat);
		}
		disconnectDB();

		return list;
	}

	// Kimegore 1: sasa, 2: muvekkil, 3: devlet
	public ArrayList<Object> getAllObjFromStatus(int status, Integer kimegore) throws Exception {
		ArrayList<Object> list = new ArrayList<Object>();
		sql = "SELECT * FROM tbl_reddiyat " + "where sasa_durum =" + status + ";";
		if (kimegore != null) {
			switch (kimegore) {
			case 1:
				// bisi yapmaya gerek yokyukarda set ettik
				break;
			case 2:
				sql = "SELECT * FROM tbl_reddiyat " + "where muvekkil_durum =" + status + ";";
				break;
			case 3:
				sql = "SELECT * FROM tbl_reddiyat " + "where devlet_durum =" + status + ";";
				break;

			default:
				break;
			}
		}

		newConnectDB();
		stm = conn.createStatement();
		rs = stm.executeQuery(sql);
		while (rs.next()) {
			Reddiyat reddiyat = new Reddiyat();
			reddiyat.setId(rs.getInt("id"));
			reddiyat.setTahsilatID(rs.getInt("tahsilat_id"));
			reddiyat.setKasaPersonelID(rs.getInt("kasa_personel_id"));
			reddiyat.setOnaylayanID(rs.getInt("onaylayan_id"));
			reddiyat.setSasaReddiyatTutari(rs.getDouble("sasa_reddiyat_tutar"));
			reddiyat.setDevletReddiyatTutari(rs.getDouble("devlet_reddiyat_tutar"));
			reddiyat.setMuvekkilReddiyatTutari(rs.getDouble("muvekkil_reddiyat_tutar"));
			reddiyat.setSasaDurum(rs.getInt("sasa_durum"));
			reddiyat.setDevletDurum(rs.getInt("devlet_durum"));
			reddiyat.setMuvekkilDurum(rs.getInt("muvekkil_durum"));
			reddiyat.setToplamReddiyatTutari(rs.getDouble("toplam_tutar"));
			reddiyat.setBorcluAdi(rs.getString("borclu_adi"));
			reddiyat.setMuvekkilAdi(rs.getString("muvekkil_adi"));
			reddiyat.setDevletReddiyatTutariTL(convertDoubleToTL(reddiyat.getDevletReddiyatTutari()));
			reddiyat.setMuvekkilReddiyatTutariTL(convertDoubleToTL(reddiyat.getMuvekkilReddiyatTutari()));
			reddiyat.setSasaReddiyatTutariTL(convertDoubleToTL(reddiyat.getSasaReddiyatTutari()));
			reddiyat.setAktifTutarTL(convertDoubleToTL(reddiyat.getAktifTutar()));
			reddiyat.setTarih(rs.getDate("tarih"));
			list.add(reddiyat);
		}
		disconnectDB();

		return list;
	}

	public String convertDoubleToTL(double tutar) {

		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		return defaultFormat.format(tutar);
	}

}
