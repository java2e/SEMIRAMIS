package semiramis.operasyon.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import pelops.db.DBConnection;
import pelops.users.User;
import pelops.util.Util;
import semiramis.operasyon.model.Chronology;

public class UtilsDAO extends DBConnection {
	private String SQL = "";
	private Statement stm;
	private ResultSet rs;
	private PreparedStatement pstm;

	public void insertChronology(Chronology chronology) {
		SQL = "INSERT INTO tbl_kronoloji(icra_dosya_id, departman, borclu, islem, aciklama, tarih, userid) "
				+ "VALUES ( ?, ?, ?, ?, ?, ?, ?);";
		newConnectDB();
		try {
			pstm = conn.prepareStatement(SQL);
			pstm.setInt(1, chronology.getIcraDosyaID());
			pstm.setString(2, chronology.getDepartman());
			pstm.setString(3, chronology.getBorcluAdi());
			pstm.setString(4, chronology.getIslem());
			pstm.setString(5, chronology.getAciklama());
			pstm.setDate(6, convertFromJAVADateToSQLDate(new Date()));
			pstm.setInt(7, chronology.getUserid());
			pstm.execute();
			disconnectDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				disconnectDB();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public List getListFromIcraDosyaID(int id) {
		List list = new ArrayList<Chronology>();
		SQL = "SELECT  k.icra_dosya_id, k.departman, k.borclu, k.islem, k.aciklama, k.tarih,"
				+ " u.ad_soyad as ad_soyad, k.userid  FROM tbl_kronoloji k inner join tbl_kullanici u on k.userid= u.id "
				+ "where k.icra_dosya_id = " + id + " order by k.tarih desc;";

		newConnectDB();
		try {
			stm = conn.createStatement();

			rs = stm.executeQuery(SQL);
			while (rs.next()) {
				Chronology ch = new Chronology(rs.getInt("icra_dosya_id"), rs.getString("departman"),
						rs.getString("borclu"), rs.getString("islem"), rs.getString("aciklama"), rs.getInt("userid"),
						rs.getDate("tarih"));
				ch.setPersonelAdi(rs.getString("ad_soyad"));
				list.add(ch);
			}
			disconnectDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				disconnectDB();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}

	public String getBorcluAdi(int icraDosyaID) {

		SQL = "SELECT borclu_adi FROM vwreport where id = " + icraDosyaID + ";";
		String name = "";

		try {
			newConnectDB();
			stm = conn.createStatement();
			rs = stm.executeQuery(SQL);
			while (rs.next()) {
				name = rs.getString("borclu_adi");
			}
			disconnectDB();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				disconnectDB();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return name;
	}

	public String getDepartman() {

		HttpSession session = Util.getSession();

		User user = (User) session.getAttribute("user");
		newConnectDB();
		String departman = "";
		departman = user.getUsrRolAck();
		return departman;
	}

	public int getUserID() {
		HttpSession session = Util.getSession();
		User user = (User) session.getAttribute("user");
		return user.getUsrId();
	}
	
	public String getPersoneAdi(){
		HttpSession session = Util.getSession();
		User user = (User) session.getAttribute("user");
		return user.getUsrAdSoyad();
	}

}
