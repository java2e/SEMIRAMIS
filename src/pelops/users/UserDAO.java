package pelops.users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pelops.db.DBConnection;

public class UserDAO extends DBConnection {
	/*
	 * veritabanina, kullaniciya ait veriler kaydedilirken kullanici sifresinin
	 * md5 e gore sifrelenmesi icin olusturulan obje
	 */
	private MD5Sifreleme md5;

	/**
	 * constructor
	 */
	public UserDAO() {
	}

	/**
	 * Kullanici eklemek icin cagrilan metod
	 * 
	 * @param user
	 */
	public void insert(User user) {
		java.sql.Timestamp guncellemeZamani = null;

		@SuppressWarnings("unused")
		int iptal;

		if (user.isUsrIptal() == true) {
			iptal = 1;
		} else {
			iptal = 0;
		}

		// User passwordun md5 sifreleme ile veritabanina yazilmasi icin stringe
		// atama yapildi
		md5 = new MD5Sifreleme();
		String pwdKeeper;

		StringBuffer buffer = new StringBuffer(
				"insert into tbl_kullanici (kullanici_adi, sifre, ad_soyad, email, telefon, aciklama, rol_id, iptal, guncelleyen_kullanici_id, guncelleme_zamani,image_path) values (");
		buffer.append("'" + user.getUsrName() + "'");
		// md5e gore sifrelendi
		pwdKeeper = md5.sifrele(user.getUsrPwd());
		// md5e gore sifrelendi ve veritabanina yazildi
		buffer.append(" ,'" + pwdKeeper + "'");
		buffer.append(" ,'" + user.getUsrAdSoyad() + "'");
		buffer.append(" ,'" + user.getUsrMail() + "'");
		buffer.append(" ,'" + user.getUsrTel() + "'");
		buffer.append(" ,'" + user.getUsrAck() + "'");
		buffer.append(" ," + user.getUsrKullaniciTipi());
		buffer.append(" ," + user.isUsrIptal());
		buffer.append(" ," + user.getGuncelleyenKullaniciId());
		if (user.getGuncellemeZamani() != null) {
			guncellemeZamani = new java.sql.Timestamp(user.getGuncellemeZamani().getTime());
			buffer.append(" ,'" + guncellemeZamani + "'");
		}
		buffer.append(" ,'" + user.getUsrPhotoUrl() + "'");
		buffer.append(")");

		// System.out.println(buffer.toString());

		try {
			super.newConnectDB();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Statement statement;
		try {
			statement = conn.createStatement();
			statement.execute(buffer.toString());
		} catch (SQLException e) {
			System.out.println("Hata USERDAO insert :" + e.getMessage());
			e.printStackTrace();
		}

		try {
			super.disconnectDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * kullanici silmek icin cagrilan metod
	 * 
	 * @param kullaniciId
	 */
	public void delete(int kullaniciId) {
		StringBuffer buffer = new StringBuffer("delete from tbl_kullanici" + " where id=" + kullaniciId);

		try {
			super.newConnectDB();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(buffer.toString());

		Statement statement;
		try {
			statement = conn.createStatement();
			statement.execute(buffer.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * guncelleme yapilirken cagrilan metod
	 * 
	 * @param user
	 */
	public void update(User user) {
		java.sql.Timestamp guncellemeZamani = null;

		StringBuffer buffer = new StringBuffer("update tbl_kullanici set kullanici_adi = '");
		buffer.append(user.getUsrName() + "'");
		buffer.append(", ad_soyad = '" + user.getUsrAdSoyad() + "'");
		buffer.append(", email = '" + user.getUsrMail() + "'");
		buffer.append(", image_path = '" + user.getUsrPhotoUrl() + "'");
		buffer.append(", telefon = '" + user.getUsrTel() + "'");
		if (user.getUsrAck() != null)
			buffer.append(", aciklama = '" + user.getUsrAck() + "'");
		buffer.append(", rol_id = " + user.getUsrKullaniciTipi());
		buffer.append(", iptal = " + user.isUsrIptal());
		buffer.append(", guncelleyen_kullanici_id = " + user.getGuncelleyenKullaniciId());
		if (user.getGuncellemeZamani() != null) {
			guncellemeZamani = new java.sql.Timestamp(user.getGuncellemeZamani().getTime());
			buffer.append(", guncelleme_zamani = '" + guncellemeZamani + "'");
		}
		buffer.append(" where id = " + user.getUsrId());
		try {
			super.newConnectDB();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//System.out.println(buffer.toString());

		Statement statement;
		try {
			statement = conn.createStatement();
			statement.execute(buffer.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			super.disconnectDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sifre guncellenirken cagrilan metod
	 * 
	 * @param userId
	 * @param pwd
	 */
	public void sifreGuncelle(int userId, String pwd) {
		// User passwordun md5 sifreleme ile veritabanina yazilmasi icin stringe
		// atama yapildi
		md5 = new MD5Sifreleme();
		String pwdKeeper = md5.sifrele(pwd);

		StringBuffer buffer = new StringBuffer("update tbl_kullanici set ");
		buffer.append(" sifre='" + pwdKeeper + "'");
		buffer.append(" WHERE id=" + userId);
		try {
			super.newConnectDB();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Statement statement;
		try {
			statement = conn.createStatement();
			statement.execute(buffer.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			super.disconnectDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Kullanici instancelarinin tamamini bir listeye doldurulur ve doner.
	 * 
	 * @param user
	 * @return
	 */
	public List<User> select() {
		List<User> userListesi = new ArrayList<User>();
		User userTemp;

		StringBuffer buffer = new StringBuffer(
				"select k1.id, k1.kullanici_adi, k1.sifre, k1.ad_soyad, k1.email, k1.telefon, k1.aciklama, k1.rol_id, k1.iptal, \n");
		buffer.append("t.tur_adi as rol_ack, k1.guncelleme_zamani \n");
		buffer.append("from tbl_kullanici k1 \n");
		buffer.append(" left outer join tbl_rol_turu t \n");
		buffer.append("on (k1.rol_id = t.id) where t.alan_id = 1 order by k1.id");

		try {
			super.newConnectDB();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet rset = statement.executeQuery(buffer.toString());

			while (rset.next()) {
				userTemp = new User();

				userTemp.setUsrId(rset.getInt("id"));
				userTemp.setUsrName(rset.getString("kullanici_adi"));
				userTemp.setUsrPwd(rset.getString("sifre"));
				userTemp.setUsrAdSoyad(rset.getString("ad_soyad"));
				userTemp.setUsrMail(rset.getString("email"));
				userTemp.setUsrTel(rset.getString("telefon"));
				userTemp.setUsrAck(rset.getString("aciklama"));
				userTemp.setUsrKullaniciTipi(rset.getInt("rol_id"));
				userTemp.setUsrRolAck(rset.getString("rol_ack"));
				userTemp.setUsrIptal(rset.getBoolean("iptal"));
				userTemp.setGuncellemeZamani(rset.getTimestamp("guncelleme_zamani"));

				userListesi.add(userTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			super.disconnectDB();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userListesi;
	}

	/**
	 * Sadece bir kullaniciya ait verileri doner
	 * 
	 * @param userId
	 * @return
	 */
	public User selectById(int userId) {
		User user = new User();

		StringBuffer buffer = new StringBuffer(
				"select id, kullanici_adi, ad_soyad, email, telefon, aciklama, rol_id, iptal,image_path from tbl_kullanici where id = ");
		buffer.append(userId);

		try {
			super.newConnectDB();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet rset = statement.executeQuery(buffer.toString());

			while (rset.next()) {
				user = new User();

				user.setUsrId(rset.getInt("id"));
				user.setUsrName(rset.getString("kullanici_adi"));
				user.setUsrAdSoyad(rset.getString("ad_soyad"));

				user.setUsrMail(rset.getString("email"));
				user.setUsrTel(rset.getString("telefon"));
				user.setUsrAck(rset.getString("aciklama"));
				user.setUsrKullaniciTipi(rset.getInt("rol_id"));
				user.setUsrIptal(rset.getBoolean("iptal"));
				user.setUsrPhotoUrl(rset.getString("image_path"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			super.disconnectDB();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	/**
	 * 
	 * @param usrName
	 * @return
	 */
	public User selectByUsrName(String usrName) {
		User user = null;
		StringBuffer buffer = new StringBuffer(
				"select id, kullanici_adi, sifre, ad_soyad, email, telefon, aciklama, rol_id, iptal,image_path from tbl_kullanici where kullanici_adi = ?");

		try {
			super.newConnectDB();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(buffer.toString());

			preparedStatement.setString(1, usrName);

			ResultSet rset = preparedStatement.executeQuery();

			while (rset.next()) {
				user = new User();
				user.setUsrId(rset.getInt("id"));
				user.setUsrName(rset.getString("kullanici_adi"));
				user.setUsrMail(rset.getString("email"));
				user.setUsrPwd(rset.getString("sifre"));
				user.setUsrAdSoyad(rset.getString("ad_soyad"));
				user.setUsrKullaniciTipi(rset.getInt("rol_id"));
				user.setUsrTel(rset.getString("telefon"));
				user.setUsrIptal(rset.getBoolean("iptal"));
				user.setUsrPhotoUrl(rset.getString("image_path"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			super.disconnectDB();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// kullanici iptal edilmisse null don
		if (user != null && user.isUsrIptal()) {
			user = null;
		}

		return user;
	}

	public List<ComboItem> getSelectRolAcklari(User user) {
		List<ComboItem> rolAcklari = new ArrayList<ComboItem>();
		StringBuffer buffer = new StringBuffer();

		// userId genel ve bolgesel admine ait olmadigi takdirde don
		if (user.getUsrKullaniciTipi() != 1)
			return rolAcklari;

		// Genel admine tum roller dondurulur
		if (user.getUsrKullaniciTipi() == 1)
		// if (true)
		{
			buffer = new StringBuffer("select id, tur_adi as ack from tbl_rol_turu where alan_id = 1");

			try {
				super.newConnectDB();
			} catch (Exception e) {
				e.printStackTrace();
			}

			Statement statement;
			try {
				statement = conn.createStatement();
				ResultSet rset = statement.executeQuery(buffer.toString());

				while (rset.next()) {
					ComboItem comboItem = new ComboItem();
					comboItem.setId(rset.getInt("id"));
					comboItem.setAck(rset.getString("ack"));
					rolAcklari.add(comboItem);
				}

				Collections.sort(rolAcklari);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				super.disconnectDB();
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		return rolAcklari;
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<Integer> getPages(int userId) {
		List<Integer> pages = new ArrayList<Integer>();
		StringBuffer buffer = new StringBuffer("SELECT sayfa_id from tbl_sayfa where yetki_id=" + userId);

		try {
			super.newConnectDB();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Statement statement;
		try {
			statement = (Statement) conn.createStatement();
			ResultSet rset = (ResultSet) statement.executeQuery(buffer.toString());

			while (rset.next()) {
				pages.add(rset.getInt("sayfa_id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			super.disconnectDB();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pages;
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<Integer> getWriteList(int userId) {
		List<Integer> writeList = new ArrayList<Integer>();
		StringBuffer buffer = new StringBuffer("SELECT tbl_kullanici.id, tur_id, kolon_id ");
		buffer.append("FROM tbl_kullanici INNER JOIN tbl_rol_kolon ");
		buffer.append("ON tbl_kullanici.rol_id = tbl_rol_kolon.rol_id ");
		buffer.append("WHERE 1 = tur_id AND tbl_kullanici.id = " + userId);

		try {
			super.newConnectDB();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Statement statement;
		try {
			statement = (Statement) conn.createStatement();
			ResultSet rset = (ResultSet) statement.executeQuery(buffer.toString());

			while (rset.next()) {
				writeList.add(rset.getInt("kolon_id"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			super.disconnectDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writeList;
	}

	/**
	 * Tur id 1 = Admin ve 0 = Gozlem oldugu durumda kolon okuma izni verilir
	 * 
	 * @param userId
	 * @return
	 */
	public List<Integer> getReadList(int userId) {
		List<Integer> readList = new ArrayList<Integer>();
		StringBuffer buffer = new StringBuffer("select tbl_kullanici.id, tur_id, kolon_id ");
		buffer.append("from tbl_kullanici inner join tbl_rol_kolon ");
		buffer.append("on tbl_kullanici.rol_id = tbl_rol_kolon.rol_id ");
		buffer.append("where (1 = tur_id OR 0 = tur_id) and tbl_kullanici.id = " + userId);

		try {
			super.newConnectDB();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Statement statement;
		try {
			statement = (Statement) conn.createStatement();
			ResultSet rset = (ResultSet) statement.executeQuery(buffer.toString());

			while (rset.next()) {
				readList.add(rset.getInt("kolon_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			super.disconnectDB();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return readList;
	}
}