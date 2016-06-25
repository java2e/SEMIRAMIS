package pelops.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pelops.db.DBConnection;
import pelops.model.Etkinlik;

public class EtkinlikDAO extends DBConnection{
	
	@SuppressWarnings("unused")
	public void insert(Etkinlik etkinlik) {
		StringBuffer buffer = new StringBuffer(
				"insert into tbl_etkinlik (aciklama, basTarih, bitTarih, userId) values (");
		buffer.append(" ,'" + etkinlik.getAciklama() + "'");
		buffer.append(" ,'" + etkinlik.getBasTarih() + "'");
		buffer.append(" ,'" + etkinlik.getBitTarih() + "'");
		buffer.append(" ," + etkinlik.getUserId());
		buffer.append(")");

		System.out.println(buffer.toString());

		try {
			super.newConnectDB();
			Statement statement;
			statement = conn.createStatement();
			statement.execute(buffer.toString());
			super.disconnectDB();
		} catch (SQLException e) {
			System.out.println("Hata etkinlikDAO insert :" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * kullanici silmek icin cagrilan metod
	 * 
	 * @param kullaniciId
	 */
	public void delete(int etkinlikId)
	{
		StringBuffer buffer = new StringBuffer("delete from tbl_etkinlik" + " where id=" + etkinlikId);

		try
		{
			
			super.newConnectDB();
			Statement statement;
			statement = conn.createStatement();
			statement.execute(buffer.toString());
			super.disconnectDB();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		System.out.println(buffer.toString());
			
	}

	/**
	 * guncelleme yapilirken cagrilan metod
	 * 
	 * @param user
	 */
	public void update(Etkinlik etkinlik)
	{
		
		StringBuffer buffer = new StringBuffer("update tbl_etkinlik set");
		buffer.append("aciklama = '" + etkinlik.getAciklama() + "'");
		buffer.append(", basTarih = '" + etkinlik.getBasTarih() + "'");
		buffer.append(", bitTarih = '" + etkinlik.getBitTarih() + "'");
		buffer.append(", userId = " + etkinlik.getUserId());
		buffer.append(" where id = " + etkinlik.getId());
		try
		{
			super.newConnectDB();
			Statement statement;
			statement = conn.createStatement();
			statement.execute(buffer.toString());
			super.disconnectDB();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		System.out.println(buffer.toString());
		
		
	}


	/**
	 * Kullanici instancelarinin tamamini bir listeye doldurulur ve doner.
	 * 
	 * @param user
	 * @return
	 */
	public List<Etkinlik> select(int userId)
	{
		List<Etkinlik> etkinlikListesi = new ArrayList<Etkinlik>();
		Etkinlik etkinlik;

		StringBuffer buffer = new StringBuffer(
				"select * from tbl_etkinlik where userId="+ userId + "");
		
		try
		{
			super.newConnectDB();
			Statement statement;
			statement = conn.createStatement();
			ResultSet rset = statement.executeQuery(buffer.toString());

			while (rset.next())
			{
				etkinlik = new Etkinlik();
				etkinlik.setId(rset.getInt("id"));
				etkinlik.setAciklama(rset.getString("aciklama"));
				etkinlik.setBasTarih(rset.getDate("basTarih"));
				etkinlik.setBitTarih(rset.getDate("bitTarih"));
				etkinlik.setUserId(rset.getInt("userId"));
				etkinlikListesi.add(etkinlik);							
			}
			super.disconnectDB();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return etkinlikListesi;
	}

	/**
	 * Sadece bir kullaniciya ait verileri doner
	 * 
	 * @param userId
	 * @return
	 */
	public Etkinlik selectById(int userId)
	{
		Etkinlik etkinlik = new Etkinlik();

		StringBuffer buffer = new StringBuffer(
				"select * from tbl_etkinlik where userId="+ userId + "");

		try
		{
			super.newConnectDB();
			Statement statement;
			statement = conn.createStatement();
			ResultSet rset = statement.executeQuery(buffer.toString());

			while (rset.next())
			{
				etkinlik = new Etkinlik();
				etkinlik.setId(rset.getInt("id"));
				etkinlik.setAciklama(rset.getString("aciklama"));
				etkinlik.setBasTarih(rset.getDate("basTarih"));
				etkinlik.setBitTarih(rset.getDate("bitTarih"));
				etkinlik.setUserId(rset.getInt("userId"));
			}
			super.disconnectDB();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return etkinlik;
	}
	
	

}
