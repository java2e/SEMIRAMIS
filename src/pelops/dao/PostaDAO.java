package pelops.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import pelops.db.DBConnection;
import pelops.model.Posta;

public class PostaDAO extends DBConnection {

	Statement stmt;
	ResultSet rs;

	public void Kaydet(Posta posta) throws Exception {

		String SQL = "INSERT INTO tbl_posta_barkod(icra_dosya_id, barkod, durum) VALUES ( ?, ?, ?);";
		newConnectDB();
		PreparedStatement ps = conn.prepareStatement(SQL);

		ps.setInt(1, posta.getIcra_dosya_id());
		ps.setString(2, posta.getBarkod());
		ps.setInt(3, 0);

		ps.executeUpdate();
		disconnectDB();
	}

	public void Duzenle(Posta posta) throws Exception {

		String SQL = "UPDATE tbl_posta_barkod SET icra_dosya_id=?, barkod=?, durum=? WHERE id="
				+ posta.getId();
		
		PreparedStatement ps = conn.prepareStatement(SQL);

		ps.setInt(1, posta.getIcra_dosya_id());
		ps.setString(2, posta.getBarkod());
		ps.setInt(3, posta.getDurum());

		ps.executeUpdate();
	

	}

	public void Sil(int id) throws Exception {

		String SQL = "DELETE FROM tbl_posta_barkod WHERE id=" + id;
		newConnectDB();
		PreparedStatement ps = conn.prepareStatement(SQL);
		ps.executeUpdate();
		disconnectDB();
	}

	public ArrayList<Posta> postaListesi() throws Exception {

		String SQL = "SELECT id, icra_dosya_id, barkod, durum FROM tbl_posta_barkod;";

		newConnectDB();

		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		Posta posta = new Posta();
		ArrayList<Posta> postaList = new ArrayList<Posta>();
		while (rs.next()) {
			posta.setId(rs.getInt("id"));
			posta.setBarkod(rs.getString("barkod"));
			posta.setDurum(rs.getInt("durum"));
			posta.setIcra_dosya_id(rs.getInt("icra_dosya_id"));
			postaList.add(posta);

		}
		disconnectDB();
		return postaList;

	}

	public Posta BarkodVer() throws Exception {

		String SQL = "SELECT id, icra_dosya_id, barkod, durum FROM tbl_posta_barkod where durum=0 ORDER BY barkod DESC  limit 1";

		newConnectDB();

		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		Posta posta = new Posta();

		if (rs.next()) {
			posta.setId(rs.getInt("id"));
			posta.setBarkod(rs.getString("barkod"));
			posta.setDurum(0);
			posta.setIcra_dosya_id(rs.getInt("icra_dosya_id"));
		}
		Duzenle(posta);

		disconnectDB();
		//System.out.println("postanin Dao: " + posta.getBarkod());
		return posta;

	}

	public String checkBarkod(int id) throws Exception {
		String SQL = "select * from tbl_posta_barkod where icra_dosya_id= "
				+ id;

		newConnectDB();

		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);

		String barkod = null;
		while (rs.next()) {
			barkod = rs.getString("barkod");
		}
		disconnectDB();
		return barkod;
	}

	public boolean checkBarkodStatus() throws Exception {
		boolean yok = false;

		String SQL = "SELECT count( barkod) as num FROM tbl_posta_barkod where durum = 0 ORDER BY barkod DESC limit 1";
		newConnectDB();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		int count = 0;
		while (rs.next()) {

			count = rs.getInt("num");
		}
		disconnectDB();

		if (count == 0)
			yok = true;
		else
			yok = false;

		return yok;
	}

	// public static void main(String[] args) throws Exception{
	//
	// PostaDAO posta = new PostaDAO();
	// Posta ps = new Posta();
	//
	// try {
	// ps = posta.BarkodVer();
	// System.out.println(ps.getBarkod());
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// ps.setDurum(1);
	//
	// posta.Duzenle(ps);
	//
	// }

}
