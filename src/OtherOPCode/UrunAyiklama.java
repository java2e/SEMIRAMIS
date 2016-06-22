package OtherOPCode;

import java.util.ArrayList;

import pelops.dao.BaglantiDAO;
import pelops.dao.BorcluBilgisiDAO;
import pelops.dao.HesapDAO;
import pelops.db.DBConnection;
import pelops.model.Baglanti;

public class UrunAyiklama extends DBConnection{

	BaglantiDAO baglantidao = new BaglantiDAO();
	BorcluBilgisiDAO brcludao = new BorcluBilgisiDAO();
	HesapDAO hesapdao = new HesapDAO();
	
	public void ayristirma() throws Exception{
		String UrunAdi, UrunNo;
		for (Baglanti items : baglantidao.BaglantiListele()) {
			if(brcludao.Liste(items.getBorcluID()).getUrunNo()!=null){
			UrunAdi = brcludao.Liste(items.getBorcluID()).getUrunNo().split(" - ")[0];
			UrunNo =  brcludao.Liste(items.getBorcluID()).getUrunNo().split(" - ")[1];
			hesapdao.GuncelleUrun(items.getHesaplamaID(), UrunAdi, UrunNo);
			System.out.println(items.getHesaplamaID());
			}
		}
		
	}
	
	public static void main(String[] args) {
		UrunAyiklama run = new UrunAyiklama();
		try {
			run.ayristirma();
		} catch (Exception e) {
			System.out.println("Hata Oldu..:"+e.getMessage());
		}
	}
}
