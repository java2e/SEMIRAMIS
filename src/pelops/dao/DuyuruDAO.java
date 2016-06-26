package pelops.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pelops.db.DBConnection;
import pelops.model.Duyuru;
import pelops.model.Gorev;

public class DuyuruDAO  extends DBConnection{
	
	@SuppressWarnings("unused")
	public void insert(Duyuru duyuru) {
		java.sql.Timestamp guncellemeZamani = new java.sql.Timestamp(new Date().getTime());
		StringBuffer buffer = new StringBuffer(
				"insert into tbl_duyuru (aciklama, gunTarih) values (");
		buffer.append("'" + duyuru.getAciklama() + "'");
		buffer.append(" ,'" + convertFromJAVADateToSQLDate(guncellemeZamani) + "')");

		System.out.println(buffer.toString());

		try {
			super.newConnectDB();
			Statement statement;
			statement = conn.createStatement();
			statement.execute(buffer.toString());
			super.disconnectDB();
		} catch (SQLException e) {
			System.out.println("Hata DuyuruDAO insert :" + e.getMessage());
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
	public void delete(int duyuruId)
	{
		StringBuffer buffer = new StringBuffer("delete from tbl_duyuru" + " where id=" + duyuruId);

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
	public void update(Duyuru duyuru)
	{
		java.sql.Timestamp guncellemeZamani = new java.sql.Timestamp(new Date().getTime());
				
		StringBuffer buffer = new StringBuffer("update tbl_duyuru set aciklama = '");
		buffer.append(duyuru.getAciklama() + "'");
		buffer.append(", gunTarih = '" + guncellemeZamani + "'");
		buffer.append(" where id = " + duyuru.getId());
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
	public List<Duyuru> select()
	{
		List<Duyuru> duyuruListesi = new ArrayList<Duyuru>();
		Duyuru duyuru;

		StringBuffer buffer = new StringBuffer(
				"select * from tbl_duyuru ");
		
		try
		{
			super.newConnectDB();
			Statement statement;
			statement = conn.createStatement();
			ResultSet rset = statement.executeQuery(buffer.toString());

			while (rset.next())
			{
				duyuru = new Duyuru();
				duyuru.setId(rset.getInt("id"));
				duyuru.setAciklama(rset.getString("aciklama"));
				duyuru.setGunTarih(rset.getDate("gunTarih"));
				duyuruListesi.add(duyuru);							
			}
			super.disconnectDB();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return duyuruListesi;
	}

	
	public Duyuru selectById(int duyuruId)
	{
		Duyuru duyuru = new Duyuru();

		StringBuffer buffer = new StringBuffer(
				"select * from tbl_duyuru where id="+ duyuruId + "");

		try
		{
			super.newConnectDB();
			Statement statement;
			statement = conn.createStatement();
			ResultSet rset = statement.executeQuery(buffer.toString());

			while (rset.next())
			{
				duyuru = new Duyuru();
				duyuru.setId(rset.getInt("id"));
				duyuru.setAciklama(rset.getString("aciklama"));
				duyuru.setGunTarih(rset.getDate("guntarih"));
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

		return duyuru;
	}
	


}
