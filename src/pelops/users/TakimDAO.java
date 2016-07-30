package pelops.users;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.common.util.concurrent.Service.State;

import pelops.db.DBConnection;

public class TakimDAO extends DBConnection {
	
	
	// Takım içerisine kullanıcı eklemek için kullanıcılacaktır
	public boolean kaydetTakimKullanici(TakimKullanici tk)
	{
		
		
		try {
			
			if(tk!=null)
			{
				
				String sql="insert into tbl_takim_kullanici( "
           +"  kullanici_id,takim_id) "
           +" values ("+tk.getKullaniciId()+","+tk.getTakimId()+"); ";
				
				super.newConnectDB();
				
				Statement stmt=conn.createStatement();
				
				boolean result=stmt.execute(sql);
				super.disconnectDB();
				return result;
				
				
			}
			else
			{
				System.out.println("Hata TAKIMDAO takimKullanci :takimKullanici null gelmektedir");
				return false;
			}
			
			
		} catch (Exception e) {
			
			System.out.println("Hata TakımDAO kaydetTakimKullanici :"+e.getMessage());
			return false;
			
			
			// TODO: handle exception
		}
		
		
		
	}
	
	
	public List<TakimKullanici> getListTakimKullanici()
	{
		
		ArrayList<TakimKullanici> liste=new ArrayList<>();
		try {
			
			String sql="Select tk.id,k.kullanici_adi,tk.takim_id , t.takim_adi from tbl_kullanici k "
					+" inner join tbl_takim_kullanici tk on tk.kullanici_id = k.id "
					+" inner join tbl_takim t on t.id=tk.takim_id";
					
					
					super.newConnectDB();
					
					Statement stmt=conn.createStatement();
					
					ResultSet set=stmt.executeQuery(sql);
					
					TakimKullanici tk;
					
					while(set.next())
					{
						
						tk=new TakimKullanici();
						
						tk.setKullaniciAck(set.getString("kullanici_adi"));
						tk.setTakimAck(set.getString("takim_adi"));
						tk.setId(set.getInt("id"));
						
						
						liste.add(tk);
						
					}
					
					super.disconnectDB();
					return liste;
					
			
			
		} catch (Exception e) {
			
			System.out.println("Hata TakimDAO getTakimKullaniciList :"+e.getMessage());
			return liste;
			// TODO: handle exception
		}
	}
	
	
	public TakimKullanici selectByIdTK(int id)
	{
		
		
		try {
			
			String sql="Select * from tbl_takim_kullanici where id="+id;
			
			super.newConnectDB();
			
			Statement stmt=conn.createStatement();
			
			ResultSet set=stmt.executeQuery(sql);
			
			TakimKullanici tk=null;
			
			while(set.next())
			{
				
				tk=new TakimKullanici();
				
				tk.setKullaniciId(set.getInt("kullanici_id"));
				tk.setId(set.getInt("id"));
				tk.setTakimId(set.getInt("takim_id"));
				
				
			}
			super.disconnectDB();
			return tk;
			
			
		} catch (Exception e) {
			
			System.out.println("Hata TakimDAO selectById :"+e.getMessage());
			return null;
			// TODO: handle exception
		}
		
	}
	
	

	public ArrayList<TakimKullanici> selectByIdTKList(int TKid)
	{
		
		ArrayList<TakimKullanici> liste = new ArrayList<TakimKullanici>();
		
		try {
			
			String sql="Select * from tbl_takim_kullanici where takim_id="+TKid;
			
			super.newConnectDB();
			
			Statement stmt=conn.createStatement();
			
			ResultSet set=stmt.executeQuery(sql);
			
			TakimKullanici tk=null;
			
			while(set.next())
			{
				
				tk=new TakimKullanici();
				
				tk.setKullaniciId(set.getInt("kullanici_id"));
				tk.setId(set.getInt("id"));
				tk.setTakimId(set.getInt("takim_id"));
				liste.add(tk);
			}
			super.disconnectDB();
			return liste;
			
			
		} catch (Exception e) {
			
			System.out.println("Hata TakimDAO selectById :"+e.getMessage());
			return null;
			// TODO: handle exception
		}
		
	}
	
	
	
	
	
	
	
	public boolean kaydet(Takim takim)
	{
		
		try {
			
			if(takim!=null)
			{
			String sql="INSERT INTO public.tbl_takim( "
            +"takim_adi, takim_img_url, takim_yonetici) "
            +"  VALUES ('"+takim.getTakimAdi()+"','"+takim.getTakimUrlImg()+"',"+takim.getTakimYonetici()+");";
			
			
			super.newConnectDB();
			
			Statement stmt=conn.createStatement();
			
			boolean result=stmt.execute(sql.toString());
			
			super.disconnectDB();
			return result;
			}
			else
				return false;
			 
			
			
		} catch (Exception e) {
			
			System.out.println("Hata Takim kaydet() :"+e.getMessage());
			
			return false;
			
			// TODO: handle exception
		}
	}
		
		public boolean guncelle(Takim takim)
		{
			
			
			try {
				
				if(takim!=null)
				{
					
					String sql="UPDATE tbl_takim "
							+" SET takim_adi='"+takim.getTakimAdi()+"', takim_img_url='"+takim.getTakimUrlImg()+"', "
							+ "takim_yonetici="+takim.getTakimYonetici()+" "
							+" WHERE id="+takim.getId();
					
					
					super.newConnectDB();
					
					Statement stmt=conn.createStatement();
					
					boolean result=stmt.execute(sql);
					
					super.disconnectDB();
					return result;
					
				}
				else
					return false;
				
			} catch (Exception e) {
				

				System.out.println("Hata TakimDAO guncelle() :"+e.getMessage());
				
				return false;
				
			}
			
			
			
			
			
		}
		
		public boolean sil(int id)
		{
			
			try {
				
				
				String sql="Delete from tbl_takim where id="+id;
				
				super.newConnectDB();
				
				Statement stmt=conn.createStatement();
				
				boolean result=stmt.execute(sql);
				super.disconnectDB();
				return result;

				
				
			} catch (Exception e) {
				
				System.out.println("Hata takim sil :"+e.getMessage());
				
				return false;
				// TODO: handle exception
			}
		}
		
		public List<ComboItem> getListCombo()
		{
			
			ArrayList<ComboItem> liste=new ArrayList<ComboItem>();
			
			try {
				
				String sql="Select * from tbl_takim";
				
				super.newConnectDB();
				
				Statement stmt=conn.createStatement();
				
				ResultSet set=stmt.executeQuery(sql);
				
				
				
				ComboItem item;
				
				while(set.next())
				{
					item=new ComboItem();
					
					item.setId(set.getInt("id"));
					item.setAck(set.getString("takim_adi"));
					
					liste.add(item);
					
					
				}
				super.disconnectDB();
				return liste;
				
				
			} catch (Exception e) {
				// TODO: handle exception
				
				System.out.println("Hata TakimDAO getListe :"+e.getMessage());
				
				return liste;
			}
			
		}
		
		public List<Takim> getTakimList()
		{
			ArrayList<Takim> liste=new ArrayList<>();
			
			try {
				
				String sql="Select * from tbl_takim";
				
				super.newConnectDB();
				
				Statement stmt=conn.createStatement();
				
				ResultSet set=stmt.executeQuery(sql);
				
				
				
				Takim takim;
				
				while(set.next())
				{
					takim=new Takim();
					
					takim.setId(set.getInt("id"));
					takim.setTakimAdi(set.getString("takim_adi"));
					takim.setTakimUrlImg(set.getString("takim_img_url"));
					takim.setTakimYonetici(set.getInt("takim_yonetici"));
					
					
					liste.add(takim);
					
					
				}
				
				super.disconnectDB();
				return liste;
				
				
			} catch (Exception e) {
				// TODO: handle exception
				
				System.out.println("Hata TakimDAO getListe :"+e.getMessage());
				
				return liste;
			}
			
			
		}


		public Takim selectById(int takimId) {

			
			try {
				
				String sql="Select * from tbl_takim where id="+takimId;
				
				super.newConnectDB();
				
				Statement stmt=conn.createStatement();
				
				ResultSet set=stmt.executeQuery(sql);
				
				Takim takim = null;
				
				while(set.next())
				{
					takim=new Takim();
					
					takim.setId(set.getInt("id"));
					takim.setTakimAdi(set.getString("takim_adi"));
					takim.setTakimUrlImg(set.getString("takim_img_url"));
					takim.setTakimYonetici(set.getInt("takim_yonetici"));
					
					
				}
				super.disconnectDB();
				return takim;
				
				
			} catch (Exception e) {
				
				
				
				
				System.out.println("Hata takimDAO selectById :"+e.getMessage());
				
				return null;
				
				// TODO: handle exception
			}
			
			
		}


		public void guncelleTK(TakimKullanici updatedTakim) {
			
			try {
				
				String sql="UPDATE tbl_takim_kullanici "
						+"  SET  takim_id="+updatedTakim.getTakimId()+", kullanici_id="+updatedTakim.getKullaniciId()
						+"  WHERE id="+updatedTakim.getId();
				
				
				super.newConnectDB();
				
				Statement stmt=conn.createStatement();
				
				stmt.executeQuery(sql);
				super.disconnectDB();
				
			} catch (Exception e) {
				
				System.out.println("Hata TakimDAO guncelleTK :"+e.getMessage());
				
				// TODO: handle exception
			}

			
		}


		public void kaydetTK(TakimKullanici takim) {
			
			try {
				
				if(takim!=null)
				{
				String sql="INSERT INTO tbl_takim_kullanici( "
	            +"takim_id, kullanici_id) "
	            +"  VALUES ("+takim.getTakimId()+","+takim.getKullaniciId()+");";
				
				
				super.newConnectDB();
				
				Statement stmt=conn.createStatement();
				
				boolean result=stmt.execute(sql.toString());
				
				}
				else
					System.out.println("Hata TakimDAO kaydetTK : takim nesnesi bos gelmektedir");
				 
				super.disconnectDB();
				
			} catch (Exception e) {
				
				System.out.println("Hata Takim kaydet() :"+e.getMessage());
				
				
				
				// TODO: handle exception
			}
			
			
		}


		public void silTK(int id) {
			

			

			try {
				
				
				String sql="Delete from tbl_takim_kullanici where id="+id;
				
				super.newConnectDB();
				
				Statement stmt=conn.createStatement();
				
				stmt.execute(sql);
				
				super.disconnectDB();
				
				
			} catch (Exception e) {
				
				System.out.println("Hata takim kullanici silTK() sil :"+e.getMessage());
				
				// TODO: handle exception
			}
			
			
			
		}
		
		
		
	

}
