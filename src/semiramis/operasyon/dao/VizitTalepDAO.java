package semiramis.operasyon.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import pelops.db.DBConnection;
import semiramis.operasyon.model.VizitTalep;

public class VizitTalepDAO extends DBConnection{

	public List<VizitTalep> getList(int vizitDurum, String tarih) {
		
		List<VizitTalep> liste=null;

		try {

			String sql = "select iz.adi as izleme_statusu,hesap.toplam_alacak, borclu.ad_soyad,borclu.tc_no,ib.izleme_tarihi,kul.ad_soyad as personel_adsoyad,  "
					+"  borclu.adres,icra.icra_dosyasi_no,imud.adi,ib.aciklama,alacakli.muvekkil_adi as alacakli_adi from tbl_izleme_bilgisi ib  " 
					+"   inner join tbl_kullanici kul on ib.personel_id=kul.id   "
					+"  inner join tbl_icra_dosyasi icra on ib.icra_dosyasi_id=icra.id   "
					+"  inner join tbl_icra_mudurlugu imud on icra.icra_mudurlugu_id=imud.id   "
					+"   inner join tbl_baglanti bag on bag.icra_dosyasi_id=icra.id  " 
					+"   inner join tbl_alacakli_bilgisi alacakli on alacakli.id=bag.alacakli_id " 
					+"   inner join tbl_izleme_sonucu iz on iz.id=ib.izleme_sonucu_id  "
					+"   inner join tbl_hesap hesap on bag.hesap_id=hesap.id " 
					+"   inner join tbl_borclu borclu on bag.borclu_id = borclu.id  where ib.vizit_durumu="
					+ vizitDurum + " and ib.izleme_tarihi>'" + tarih + "' ";
			
			
					
			
			
			newConnectDB();
			
			Statement stmt=conn.createStatement();
			
			ResultSet set=stmt.executeQuery(sql);
			
			liste=new ArrayList<VizitTalep>();
			
			VizitTalep vizit=null;
			
			while(set.next())
			{
				vizit=new VizitTalep();
				vizit.setAciklama(set.getString("aciklama"));
				vizit.setBorcluAdi(set.getString("ad_soyad"));
				vizit.setBorcluTCNo(set.getString("tc_no"));
				vizit.setIcraMudurlugu(set.getString("adi"));
				vizit.setIcraDosyaNo(set.getString("icra_dosyasi_no"));
				vizit.setIl(set.getString("adres").split(" ")[set.getString("adres").split(" ").length-2]);
				vizit.setIlce(set.getString("adres").split(" ")[set.getString("adres").split(" ").length-1]);
				vizit.setPersonelAdSoyad(set.getString("personel_adsoyad"));
				vizit.setIzlemeStatusu(set.getString("izleme_statusu"));
				vizit.setBorcMiktari(Double.valueOf(new DecimalFormat("0.00").format(set.getDouble("toplam_alacak")).replace(",", ".")));
				vizit.setAlacakliAdi(set.getString("alacakli_adi"));
				vizit.setBorcluAdres(set.getString("adres"));
				vizit.setAvukatAdi("Av. MEHMET ORUÇ SASA");
				
				liste.add(vizit);
				
			}
			
			
			disconnectDB();
			

		} catch (Exception e) {
			
			System.out.println("Hata vizit talebiDAO getList :"+e.getMessage());
			// TODO: handle exception
		}
		
		return liste;

	}

}
