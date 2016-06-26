package pelops.kasa.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.dao.IcraDosyasiDAO;
import pelops.db.DBConnection;
import pelops.kasa.model.Hitam;

public class HitamDAO extends DBConnection implements IDAO {

	private Statement stm;
	private ResultSet rs;
	private String sql;
	private PreparedStatement psmt;

	@Override
	public ArrayList<Object> getAllObjFromDB() throws Exception {
		ArrayList<Object> list = new ArrayList<Object>();
		sql = "SELECT id, tahsilat_id, reddiyat_id, tarih, icra_dosya_id, hitam_durum, "
				+ "kasa_personel_id, onaylayan_id FROM tbl_hitam;";
		newConnectDB();
		stm = conn.createStatement();
		rs = stm.executeQuery(sql);

		while (rs.next()) {
			Hitam hitam = new Hitam();
			hitam.setId(rs.getInt("id"));
			hitam.setTahsilatID(rs.getInt("tahsilat_id"));
			hitam.setReddiyatID(rs.getInt("reddiyat_id"));
			hitam.setTarih(rs.getDate("tarih"));
			hitam.setIcraDosyaID(rs.getInt("icra_dosya_id"));
			hitam.setHitamDurum(rs.getInt("hitam_durum"));
			hitam.setKasaPersonelID(rs.getInt("kasa_personel_id"));
			hitam.setOnaylayanID(rs.getInt("onaylayan_id"));

			list.add(hitam);

		}
		disconnectDB();

		return list;
	}

	@Override
	public int insertObjToDB(Object obj) throws Exception {
		sql = "INSERT INTO tbl_hitam(  tahsilat_id, reddiyat_id, tarih, icra_dosya_id, hitam_durum, "
				+ "     kasa_personel_id, onaylayan_id)  VALUES ( ?, ?, ?, ?, ?,     ?, ?);";
		int id = 0;
		if (obj instanceof Hitam) {
			newConnectDB();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, ((Hitam) obj).getTahsilatID());
			psmt.setInt(2, ((Hitam) obj).getReddiyatID());
			psmt.setDate(3, convertFromJAVADateToSQLDate(((Hitam) obj).getTarih()));
			psmt.setInt(4, ((Hitam) obj).getIcraDosyaID());
			psmt.setInt(5, ((Hitam) obj).getHitamDurum());
			psmt.setInt(6, ((Hitam) obj).getKasaPersonelID());
			psmt.setInt(7, ((Hitam) obj).getOnaylayanID());

			psmt.execute();
			disconnectDB();
			
			IcraDosyasiDAO icradao = new IcraDosyasiDAO();
			icradao.hitamTarihiEkle(convertFromJAVADateToSQLDate(((Hitam) obj).getTarih()), ((Hitam) obj).getIcraDosyaID());
			
			id = getID(obj);
		}
		return id;
	}

	@Override
	public int updateObjFromDB(Object obj) throws Exception {

		sql = "UPDATE tbl_hitam  SET  tahsilat_id=?, reddiyat_id=?, tarih=?, icra_dosya_id=?, "
				+ "   hitam_durum=?, kasa_personel_id=?, onaylayan_id=? WHERE id =" + ((Hitam) obj).getId() + ";";
		int id = 0;
		if (obj instanceof Hitam) {
			newConnectDB();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, ((Hitam) obj).getTahsilatID());
			psmt.setInt(2, ((Hitam) obj).getReddiyatID());
			psmt.setDate(3, convertFromJAVADateToSQLDate(((Hitam) obj).getTarih()));
			psmt.setInt(4, ((Hitam) obj).getIcraDosyaID());
			psmt.setInt(5, ((Hitam) obj).getHitamDurum());
			psmt.setInt(6, ((Hitam) obj).getKasaPersonelID());
			psmt.setInt(7, ((Hitam) obj).getOnaylayanID());

			psmt.execute();
			disconnectDB();
			id = getID(obj);
		}
		return id;
	}

	@Override
	public boolean deleteObjFromDB(int id) throws Exception {
		boolean isTrue = false;
		sql = "DELETE FROM tbl_hitam  WHERE id=" + id + ";";
		newConnectDB();
		stm = conn.createStatement();
		isTrue = stm.execute(sql);
		disconnectDB();
		return isTrue;
	}

	@Override
	public Object getObjFromDB(int id) throws Exception {
		sql = "select * from tbl_hitam where id=" + id + ";";
		newConnectDB();
		stm = conn.createStatement();
		rs = stm.executeQuery(sql);
		Hitam hitam = new Hitam();

		while (rs.next()) {
			hitam.setId(rs.getInt("id"));
			hitam.setTahsilatID(rs.getInt("tahsilat_id"));
			hitam.setReddiyatID(rs.getInt("reddiyat_id"));
			hitam.setTarih(rs.getDate("tarih"));
			hitam.setIcraDosyaID(rs.getInt("icra_dosya_id"));
			hitam.setHitamDurum(rs.getInt("hitam_durum"));
			hitam.setKasaPersonelID(rs.getInt("kasa_personel_id"));
			hitam.setOnaylayanID(rs.getInt("onaylayan_id"));

		}
		disconnectDB();
		return hitam;
	}

	@Override
	public int getID(Object object) throws Exception {
		int id = 0;
		if (object instanceof Hitam) {
			String sql = "select id from tbl_hitam where icra_dosya_id=" + ((Hitam) object).getIcraDosyaID() + ";";
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
		sql = "SELECT id, tahsilat_id, reddiyat_id, tarih, icra_dosya_id, hitam_durum, "
				+ "kasa_personel_id, onaylayan_id FROM tbl_hitam where hitam_durum="+status+";";
		newConnectDB();
		stm = conn.createStatement();
		rs = stm.executeQuery(sql);

		while (rs.next()) {
			Hitam hitam = new Hitam();
			hitam.setId(rs.getInt("id"));
			hitam.setTahsilatID(rs.getInt("tahsilat_id"));
			hitam.setReddiyatID(rs.getInt("reddiyat_id"));
			hitam.setTarih(rs.getDate("tarih"));
			hitam.setIcraDosyaID(rs.getInt("icra_dosya_id"));
			hitam.setHitamDurum(rs.getInt("hitam_durum"));
			hitam.setKasaPersonelID(rs.getInt("kasa_personel_id"));
			hitam.setOnaylayanID(rs.getInt("onaylayan_id"));

			list.add(hitam);

		}
		disconnectDB();

		return list;
	}

}
