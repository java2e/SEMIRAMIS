package pelops.dashboard;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pelops.users.Takim;
import pelops.users.TakimDAO;
import pelops.users.TakimKullanici;
import pelops.users.UserDAO;

@ManagedBean(name = "dashBoardBean")
@SessionScoped
public class dashboardBean {

	private ArrayList<dashboardModels> panelListesi = new ArrayList<dashboardModels>();

	private bankaTutarModel Hedef = new bankaTutarModel();
	private bankaTutarModel Gunluk = new bankaTutarModel();
	private bankaTutarModel Aylık = new bankaTutarModel();
	private bankaTutarModel GenleToplam = new bankaTutarModel();

	public dashboardBean() {

		dashboardModels models = new dashboardModels();
		models.setTakimAdi("KAPLANLAR TAKIMI");
		models.setStarRate(2);
		models.setTakimResmi("Deneme Takım Resmi");
		bankaTutarModel takimTutarModel = new bankaTutarModel();

		NumberFormat defaultFormat2 = NumberFormat.getCurrencyInstance();
		takimTutarModel.setAKBANK(defaultFormat2.format(3000));
		takimTutarModel.setGARANTI(defaultFormat2.format(4000));
		takimTutarModel.setHSBC(defaultFormat2.format(4766));
		takimTutarModel.setING(defaultFormat2.format(4000));

		models.setBankaTutarModel(takimTutarModel);

		ArrayList<dashboardPersonelModel> list = new ArrayList<>();

		dashboardPersonelModel personel = new dashboardPersonelModel();

		personel.setPersonelAdi("Husnu TAPAN");
		personel.setAKBANK(3000);
		personel.setGARANTI(3000);
		personel.setHSBC(3000);
		personel.setING(3000);

		dashboardPersonelModel personel2 = new dashboardPersonelModel();

		personel2.setPersonelAdi("Muhammet Ali KAYA");
		personel2.setAKBANK(4000);
		personel2.setGARANTI(4000);
		personel2.setHSBC(4000);
		personel2.setING(4000);

		list.add(personel);
		list.add(personel2);

		models.setPersonel(list);

		panelListesi.add(models);

		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();

		Hedef.setAKBANK(defaultFormat.format(12500));
		Hedef.setGARANTI(defaultFormat.format(25000));
		Hedef.setHSBC(defaultFormat.format(62500));
		Hedef.setING(defaultFormat.format(2500));

		// hedef bankaların koymus oldugu degerler

		Hedef.setHedefAKBANK(defaultFormat.format(20000));
		Hedef.setHedefGARANTI(defaultFormat.format(25000));
		Hedef.setHedefHSBC(defaultFormat.format(30000));
		Hedef.setHedefING(defaultFormat.format(20000));

		Hedef.setYuzdeAKBANK(yuzdeHesapla(Hedef.getAKBANK(), Hedef.getHedefAKBANK()));
		Hedef.setYuzdeGARANTI(yuzdeHesapla(Hedef.getGARANTI(), Hedef.getHedefGARANTI()));
		Hedef.setYuzdeHSBC(yuzdeHesapla(Hedef.getHSBC(), Hedef.getHedefHSBC()));
		Hedef.setYuzdeING(yuzdeHesapla(Hedef.getING(), Hedef.getHedefING()));
		// System.out.println("takım adı.....:" +
		// panelListesi.get(0).getTakimAdi());

		Gunluk.setAKBANK(defaultFormat.format(12500));
		Gunluk.setGARANTI(defaultFormat.format(20000));
		Gunluk.setHSBC(defaultFormat.format(62200));
		Gunluk.setING(defaultFormat.format(4500));

		Gunluk.setHedefAKBANK(defaultFormat.format(40000));
		Gunluk.setHedefGARANTI(defaultFormat.format(40000));
		Gunluk.setHedefHSBC(defaultFormat.format(40000));
		Gunluk.setHedefING(defaultFormat.format(40000));

		Gunluk.setYuzdeAKBANK(yuzdeHesapla(Gunluk.getAKBANK(), Gunluk.getHedefAKBANK()));
		Gunluk.setYuzdeGARANTI(yuzdeHesapla(Gunluk.getGARANTI(), Gunluk.getHedefGARANTI()));
		Gunluk.setYuzdeHSBC(yuzdeHesapla(Gunluk.getHSBC(), Gunluk.getHedefHSBC()));
		Gunluk.setYuzdeING(yuzdeHesapla(Gunluk.getING(), Gunluk.getHedefING()));

		Aylık.setAKBANK(defaultFormat.format(3000));
		Aylık.setGARANTI(defaultFormat.format(2300));
		Aylık.setHSBC(defaultFormat.format(4000));
		Aylık.setING(defaultFormat.format(4000));

		Aylık.setHedefAKBANK(defaultFormat.format(40000));
		Aylık.setHedefGARANTI(defaultFormat.format(40000));
		Aylık.setHedefHSBC(defaultFormat.format(40000));
		Aylık.setHedefING(defaultFormat.format(40000));

		Aylık.setYuzdeAKBANK(yuzdeHesapla(Aylık.getAKBANK(), Aylık.getHedefAKBANK()));
		Aylık.setYuzdeGARANTI(yuzdeHesapla(Aylık.getGARANTI(), Aylık.getHedefGARANTI()));
		Aylık.setYuzdeHSBC(yuzdeHesapla(Aylık.getHSBC(), Aylık.getHedefHSBC()));
		Aylık.setYuzdeING(yuzdeHesapla(Aylık.getING(), Aylık.getHedefING()));

		GenleToplam.setAKBANK(defaultFormat.format(3000));
		GenleToplam.setGARANTI(defaultFormat.format(2300));
		GenleToplam.setHSBC(defaultFormat.format(4000));
		GenleToplam.setING(defaultFormat.format(4000));

		GenleToplam.setHedefAKBANK(defaultFormat.format(40000));
		GenleToplam.setHedefGARANTI(defaultFormat.format(40000));
		GenleToplam.setHedefHSBC(defaultFormat.format(40000));
		GenleToplam.setHedefING(defaultFormat.format(40000));

		GenleToplam.setYuzdeAKBANK(yuzdeHesapla(GenleToplam.getAKBANK(), GenleToplam.getHedefAKBANK()));
		GenleToplam.setYuzdeGARANTI(yuzdeHesapla(GenleToplam.getGARANTI(), GenleToplam.getHedefGARANTI()));
		GenleToplam.setYuzdeHSBC(yuzdeHesapla(GenleToplam.getHSBC(), GenleToplam.getHedefHSBC()));
		GenleToplam.setYuzdeING(yuzdeHesapla(GenleToplam.getING(), GenleToplam.getHedefING()));

		veriListele();
		
	}

	public int yuzdeHesapla(String deger, String hedeflenenDeger) {
		String[] dizi = Hedef.getAKBANK().replace(",", "").replace(".", "").split(" ");
		String[] dizi2 = Hedef.getHedefAKBANK().replace(",", "").replace(".", "").split(" ");
		int yuzdeDeger = Integer.parseInt(dizi[0]);
		int yuzdeHedefDeger = Integer.parseInt(dizi2[0]);
		//System.out.println(yuzdeDeger);
		//System.out.println(yuzdeHedefDeger);
		int yuzde = yuzdeDeger * 100 / yuzdeHedefDeger;
		//System.out.println(yuzde);
		return yuzde;
	}
	

	public void veriListele(){
		UserDAO userdao = new UserDAO();
		// Takım listesini alalım
		TakimDAO takimdao = new TakimDAO();
		for (Takim items : takimdao.getTakimList()) {
			dashboardModels models = new dashboardModels();
			models.setTakimAdi(items.getTakimAdi());
			System.out.println(items.getTakimAdi());
			System.out.println("--------------------------------------");
			for (TakimKullanici itemsTK : takimdao.selectByIdTKList(items.getId())) {
				dashboardPersonelModel personelmodel = new dashboardPersonelModel();
				//System.out.println(itemsTK.getKullaniciId()); 
				personelmodel.setPersonelAdi(userdao.selectById(itemsTK.getKullaniciId()).getUsrAdSoyad());
				
				System.out.println(personelmodel.getPersonelAdi()); 
				
			}
			
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public bankaTutarModel getHedef() {
		return Hedef;
	}

	public void setHedef(bankaTutarModel hedef) {
		Hedef = hedef;
	}

	public bankaTutarModel getGunluk() {
		return Gunluk;
	}

	public void setGunluk(bankaTutarModel gunluk) {
		Gunluk = gunluk;
	}

	public bankaTutarModel getAylık() {
		return Aylık;
	}

	public void setAylık(bankaTutarModel aylık) {
		Aylık = aylık;
	}

	public bankaTutarModel getGenleToplam() {
		return GenleToplam;
	}

	public void setGenleToplam(bankaTutarModel genleToplam) {
		GenleToplam = genleToplam;
	}

	public ArrayList<dashboardModels> getPanelListesi() {
		return panelListesi;
	}

	public void setPanelListesi(ArrayList<dashboardModels> panelListesi) {
		this.panelListesi = panelListesi;
	}

}
