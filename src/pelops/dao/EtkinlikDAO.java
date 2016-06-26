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
				"insert into tbl_etkinlik (aciklama, bastarih, bittarih,eventid, userid) values (");
		buffer.append("'" + etkinlik.getAciklama() + "'");
		buffer.append(" ,'" + etkinlik.getBasTarih() + "'");
		buffer.append(" ,'" + etkinlik.getBitTarih() + "'");
		buffer.append(" ,'" + etkinlik.getEventId() + "'");
		buffer.append(" ," + etkinlik.getUserId());
		buffer.append(")");

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

			
	}
	
	
	public void deleteByEventId(String eventId)
	{
		StringBuffer buffer = new StringBuffer("delete from tbl_etkinlik" + " where eventid='" + eventId+"'");

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

			
	}
	

	public void updateByEventId(Etkinlik etkinlik)
	{
		
		StringBuffer buffer = new StringBuffer("update tbl_etkinlik set");
		buffer.append(" aciklama = '" + etkinlik.getAciklama() + "'");
		buffer.append(", bastarih = '" + etkinlik.getBasTarih() + "'");
		buffer.append(", bittarih = '" + etkinlik.getBitTarih() + "'");
		buffer.append(", userid = " + etkinlik.getUserId());
		buffer.append(" where eventid = '" + etkinlik.getEventId()+"'");
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

		
		
	}


	public List<Etkinlik> select(int userId)
	{
		List<Etkinlik> etkinlikListesi = new ArrayList<Etkinlik>();
		Etkinlik etkinlik;

		StringBuffer buffer = new StringBuffer(
				"select * from tbl_etkinlik where userid="+ userId + "");
		
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
				etkinlik.setBasTarih(rset.getDate("bastarih"));
				etkinlik.setBitTarih(rset.getDate("bittarih"));
				etkinlik.setEventId(rset.getString("eventid"));
				etkinlik.setUserId(rset.getInt("userid"));
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


	public Etkinlik selectById(int userId)
	{
		Etkinlik etkinlik = new Etkinlik();

		StringBuffer buffer = new StringBuffer(
				"select * from tbl_etkinlik where userid="+ userId + "");

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
				etkinlik.setBasTarih(rset.getDate("bastarih"));
				etkinlik.setBitTarih(rset.getDate("bittarih"));
				etkinlik.setEventId(rset.getString("eventid"));
				etkinlik.setUserId(rset.getInt("userid"));
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
