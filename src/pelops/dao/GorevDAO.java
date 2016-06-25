package pelops.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pelops.db.DBConnection;
import pelops.model.Gorev;

public class GorevDAO extends DBConnection{


	public GorevDAO()
	{
	}

	@SuppressWarnings("unused")
	public void insert(Gorev gorev) {
		StringBuffer buffer = new StringBuffer(
				"insert into tbl_gorev (konu, aciklama, bastarih, bittarih, userid) values (");
		buffer.append("'" + gorev.getKonu() + "'");

		buffer.append(" ,'" + gorev.getAciklama() + "'");
		buffer.append(" ,'" + convertFromJAVADateToSQLDate(gorev.getBasTarih()) + "'");
		buffer.append(" ,'" + convertFromJAVADateToSQLDate(gorev.getBitTarih()) + "'");
		buffer.append(" ," + gorev.getUserId());
		buffer.append(")");

		System.out.println(buffer.toString());

		try {
			super.newConnectDB();
			Statement statement;
			statement = conn.createStatement();
			statement.execute(buffer.toString());
			super.disconnectDB();
		} catch (SQLException e) {
			System.out.println("Hata gorevDAO insert :" + e.getMessage());
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
	public void delete(int gorevId)
	{
		StringBuffer buffer = new StringBuffer("delete from tbl_gorev" + " where id=" + gorevId);

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
	public void update(Gorev gorev)
	{
		
		StringBuffer buffer = new StringBuffer("update tbl_gorev set konu = '");
		buffer.append(gorev.getKonu() + "'");
		buffer.append(", aciklama = '" + gorev.getAciklama() + "'");
		buffer.append(", bastarih = '" + gorev.getBasTarih() + "'");
		buffer.append(", bittarih = '" + gorev.getBitTarih() + "'");
		buffer.append(", userid = " + gorev.getUserId());
		buffer.append(" where id = " + gorev.getId());
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
	public List<Gorev> select(int userId)
	{
		List<Gorev> gorevListesi = new ArrayList<Gorev>();
		Gorev gorevTemp;

		StringBuffer buffer = new StringBuffer(
				"select * from tbl_gorev where userid="+ userId + "");
		
		try
		{
			super.newConnectDB();
			Statement statement;
			statement = conn.createStatement();
			ResultSet rset = statement.executeQuery(buffer.toString());

			while (rset.next())
			{
				gorevTemp = new Gorev();
				gorevTemp.setId(rset.getInt("id"));
				gorevTemp.setKonu(rset.getString("konu"));
				gorevTemp.setAciklama(rset.getString("aciklama"));
				gorevTemp.setBasTarih(rset.getDate("bastarih"));
				gorevTemp.setBitTarih(rset.getDate("bittarih"));
				gorevTemp.setUserId(rset.getInt("userid"));
				gorevListesi.add(gorevTemp);							
			}
			super.disconnectDB();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return gorevListesi;
	}

	/**
	 * Sadece bir kullaniciya ait verileri doner
	 * 
	 * @param userId
	 * @return
	 */
	public Gorev selectById(int gorevId)
	{
		Gorev gorev = new Gorev();

		StringBuffer buffer = new StringBuffer(
				"select * from tbl_gorev where id="+ gorevId + "");

		try
		{
			super.newConnectDB();
			Statement statement;
			statement = conn.createStatement();
			ResultSet rset = statement.executeQuery(buffer.toString());

			while (rset.next())
			{
				gorev = new Gorev();
				gorev.setId(rset.getInt("id"));
				gorev.setKonu(rset.getString("konu"));
				gorev.setAciklama(rset.getString("aciklama"));
				gorev.setBasTarih(rset.getDate("bastarih"));
				gorev.setBitTarih(rset.getDate("bittarih"));
				gorev.setUserId(rset.getInt("userid"));
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

		return gorev;
	}
	
	public java.sql.Date convertFromJAVADateToSQLDate(java.util.Date javaDate) {

		java.sql.Date sqlDate = null;

		if (javaDate != null) {

			sqlDate = new Date(javaDate.getTime());
		}

		return sqlDate;
	}
	
	
	
}
