package pelops.dashboard;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.codehaus.groovy.transform.TupleConstructorASTTransformation;

import pelops.dao.HedefDAO;
import pelops.users.Takim;
import pelops.users.TakimDAO;
import pelops.users.TakimKullanici;
import pelops.users.UserDAO;

@ManagedBean(name = "dashBoardBean")
@SessionScoped
public class dashboardBean {

	private ArrayList<dashboardModels> DashBorad;
	private ArrayList<dashboardModels> DashBoradFaces ;

	private SimpleDateFormat yearsFormat = new SimpleDateFormat("yyyy");
	private SimpleDateFormat mountsFormat = new SimpleDateFormat("MM");
	private SimpleDateFormat fullDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	private SimpleDateFormat fullXDateFormat = new SimpleDateFormat("dd MMMM yyyy EEEE");
	
	private Date nowDate = new Date();
	

	private bankaTutarModel hedeflerGunluk = new bankaTutarModel();
	private bankaTutarModel hedeflerAylik = new bankaTutarModel();
	private bankaTutarModel hedeflerToplam = new bankaTutarModel();
	
	private bankaTutarModel gunluk = new bankaTutarModel();
	private bankaTutarModel aylik = new bankaTutarModel();
	private bankaTutarModel genelToplam = new bankaTutarModel();
	
	private String tarih;
	
	
	public dashboardBean() throws Exception {
		DashBorad = new ArrayList<dashboardModels>();
		DashBoradFaces = new ArrayList<dashboardModels>();
				
	takimtanimla();
	veriListele();
	aktar();
		
	tarih = fullXDateFormat.format(nowDate);
	
	for (int i = 0; i < DashBoradFaces.size(); i++) {
		System.out.println(DashBoradFaces.get(i).getTakimAdi());
		System.out.println("Personel Adı...:"+ DashBoradFaces.get(i).getPersonel().get(0).getPersonelAdi());
		System.out.println("gümlük tutar....:"+ DashBoradFaces.get(i).getPersonel().get(0).getBankamodel().getHSBC());
	
	}
	
	
	}

	
	// -------------- TABLO VERİLERİNİ ÇEKİYORUZ -------------------------
	//       KASA İŞLEMLERİ - HEDEFLER DEN BİLGİLERİ ÇEKİYORUZ
	// -------------------------------------------------------------------
	
	
	public void veriListele() throws Exception{
		UserDAO userdao = new UserDAO();
		int yil = Integer.parseInt(yearsFormat.format(nowDate));
		int ay = Integer.parseInt(mountsFormat.format(nowDate));
		HedefDAO hedefler = new HedefDAO();
		// Takım listesini alalım
		TakimDAO takimdao = new TakimDAO();
		tahsilatDAO tahsilatdao = new tahsilatDAO();
		
		for (Takim items : takimdao.getTakimList()) { // TAKIM LİSTESİNİ DÖNÜYORUZ
			dashboardModels models = new dashboardModels();
			models.setTakimAdi(items.getTakimAdi());
			models.setTakimResmi(items.getTakimUrlImg());
			models.setStarRate(5);
			ArrayList<dashboardPersonelModel> personelList = new ArrayList<>();
		
			// Personel Listesi
			bankaTutarModel tutartakim = new bankaTutarModel(); 
			for (TakimKullanici itemsTK : takimdao.selectByIdTKList(items.getId())) { // PERSONEL LİSTESİNİ DÖNÜYORUZ
				dashboardPersonelModel personelmodel = new dashboardPersonelModel();
			
				personelmodel.setPersonelAdi(userdao.selectById(itemsTK.getKullaniciId()).getUsrAdSoyad());
				
				bankaTutarModel tutarhedef = new bankaTutarModel(); 
				for (int i = 0; i < hedefler.Listele(yil, ay).size(); i++) {
				
					if(hedefler.Listele(yil, ay).get(i).getPersonel_id()==itemsTK.getKullaniciId()){
					
					if(hedefler.Listele(yil, ay).get(i).getMuvekkil_adi().contains("HSBC BANK A.Ş.")==true){
						tutarhedef.setHedefHSBC(hedefler.Listele(yil, ay).get(i).getGunluk_hedef());
						tutarhedef.setHedefHSBCaylik(hedefler.Listele(yil, ay).get(i).getAylik_hedef());
						tutartakim.setHedefHSBC(tutartakim.getHedefHSBC()+hedefler.Listele(yil, ay).get(i).getGunluk_hedef());
						tutartakim.setHedefHSBCaylik(tutartakim.getHedefHSBCaylik()+hedefler.Listele(yil, ay).get(i).getAylik_hedef());
						
						
				}
					else if(hedefler.Listele(yil, ay).get(i).getMuvekkil_adi().contains("İNG BANK A.Ş.")==true){
						tutarhedef.setHedefING(hedefler.Listele(yil, ay).get(i).getGunluk_hedef());
						tutarhedef.setHedefINGaylik(hedefler.Listele(yil, ay).get(i).getAylik_hedef());
						tutartakim.setHedefING(tutartakim.getHedefING()+hedefler.Listele(yil, ay).get(i).getGunluk_hedef());
						tutartakim.setHedefINGaylik(tutartakim.getHedefINGaylik()+hedefler.Listele(yil, ay).get(i).getAylik_hedef());
						
			   }
					else if(hedefler.Listele(yil, ay).get(i).getMuvekkil_adi().contains("AKBANK T.A.Ş.")==true){
						tutarhedef.setHedefAKBANK(hedefler.Listele(yil, ay).get(i).getGunluk_hedef());
						tutarhedef.setHedefAKBANKaylik(hedefler.Listele(yil, ay).get(i).getAylik_hedef());
						tutartakim.setHedefAKBANK(tutartakim.getHedefAKBANK()+hedefler.Listele(yil, ay).get(i).getGunluk_hedef());
						tutartakim.setHedefAKBANKaylik(tutartakim.getHedefAKBANKaylik()+hedefler.Listele(yil, ay).get(i).getAylik_hedef());
			  }
					else if(hedefler.Listele(yil, ay).get(i).getMuvekkil_adi().contains("T. GARANTİ BANKASI A.Ş.")==true){
						tutarhedef.setHedefGARANTI(hedefler.Listele(yil, ay).get(i).getGunluk_hedef());
						tutarhedef.setHedefGARANTIaylik(hedefler.Listele(yil, ay).get(i).getAylik_hedef());
						tutartakim.setHedefGARANTI(tutartakim.getHedefGARANTI()+hedefler.Listele(yil, ay).get(i).getGunluk_hedef());
						tutartakim.setHedefGARANTIaylik(tutartakim.getHedefGARANTIaylik()+hedefler.Listele(yil, ay).get(i).getAylik_hedef());
			}
								
			}
			}
			
				for (int i = 0; i < tahsilatdao.ListeleBugun().size(); i++) {
					if(tahsilatdao.Listele().get(i).getSoz_alan_personel_id()==itemsTK.getKullaniciId()){
					
						
						if(tahsilatdao.Listele().get(i).getMuvekkil_adi().contains("HSBC BANK A.Ş.")==true){
							tutarhedef.setHSBC(tutarhedef.getHSBC()+tahsilatdao.Listele().get(i).getOdemeMiktari());
							tutartakim.setHSBC(tutartakim.getHSBC()+tahsilatdao.Listele().get(i).getOdemeMiktari());
							
						}
						else if(tahsilatdao.Listele().get(i).getMuvekkil_adi().contains("İNG BANK A.Ş.")==true){
							tutarhedef.setING(tutarhedef.getING()+tahsilatdao.Listele().get(i).getOdemeMiktari());
							tutartakim.setING(tutartakim.getING()+tahsilatdao.Listele().get(i).getOdemeMiktari());
							
						}
						else if(tahsilatdao.Listele().get(i).getMuvekkil_adi().contains("AKBANK T.A.Ş.")==true){
							tutarhedef.setAKBANK(tutarhedef.getAKBANK()+tahsilatdao.Listele().get(i).getOdemeMiktari());
							tutartakim.setAKBANK(tutartakim.getAKBANK()+tahsilatdao.Listele().get(i).getOdemeMiktari());
							
						}
						else if(tahsilatdao.Listele().get(i).getMuvekkil_adi().contains("T. GARANTİ BANKASI A.Ş.")==true){
							tutarhedef.setGARANTI(tutarhedef.getGARANTI()+tahsilatdao.Listele().get(i).getOdemeMiktari());
							tutartakim.setGARANTI(tutartakim.getGARANTI()+tahsilatdao.Listele().get(i).getOdemeMiktari());
							
						}
						
						
						
					}
					
				}
			
				for (int i = 0; i < tahsilatdao.ListeleBuAy().size(); i++) {
					if(tahsilatdao.Listele().get(i).getSoz_alan_personel_id()==itemsTK.getKullaniciId()){
					
						
						if(tahsilatdao.Listele().get(i).getMuvekkil_adi().contains("HSBC BANK A.Ş.")==true){
							tutarhedef.setHSBCaylik(tutarhedef.getHSBCaylik()+tahsilatdao.Listele().get(i).getOdemeMiktari());
							tutartakim.setHSBCaylik(tutartakim.getHSBCaylik()+tahsilatdao.Listele().get(i).getOdemeMiktari());
							
						}
						else if(tahsilatdao.Listele().get(i).getMuvekkil_adi().contains("İNG BANK A.Ş.")==true){
							tutarhedef.setINGaylik(tutarhedef.getINGaylik()+tahsilatdao.Listele().get(i).getOdemeMiktari());
							tutartakim.setINGaylik(tutartakim.getINGaylik()+tahsilatdao.Listele().get(i).getOdemeMiktari());
							
						}
						else if(tahsilatdao.Listele().get(i).getMuvekkil_adi().contains("AKBANK T.A.Ş.")==true){
							tutarhedef.setAKBANKaylik(tutarhedef.getAKBANKaylik()+tahsilatdao.Listele().get(i).getOdemeMiktari());
							tutartakim.setAKBANKaylik(tutartakim.getAKBANKaylik()+tahsilatdao.Listele().get(i).getOdemeMiktari());
							
						}
						else if(tahsilatdao.Listele().get(i).getMuvekkil_adi().contains("T. GARANTİ BANKASI A.Ş.")==true){
							tutarhedef.setGARANTIaylik(tutarhedef.getGARANTIaylik()+tahsilatdao.Listele().get(i).getOdemeMiktari());
							tutartakim.setGARANTIaylik(tutartakim.getGARANTIaylik()+tahsilatdao.Listele().get(i).getOdemeMiktari());
							
						}
						
						
						
					}
					
				}
				
				personelmodel.setBankamodel(tutarhedef);
				personelList.add(personelmodel);
							
			}
			
			models.setBankaTutarModel(tutartakim);
			models.setPersonel(personelList);
			DashBorad.add(models);
			
			hedeflerAylik.setHedefHSBCaylik(hedeflerAylik.getHedefHSBCaylik()+tutartakim.getHedefHSBCaylik());
			hedeflerAylik.setHedefAKBANKaylik(hedeflerAylik.getHedefAKBANKaylik()+tutartakim.getHedefAKBANKaylik());
			hedeflerAylik.setHedefGARANTIaylik(hedeflerAylik.getHedefGARANTIaylik()+tutartakim.getHedefGARANTIaylik());
			hedeflerAylik.setHedefINGaylik(hedeflerAylik.getHedefINGaylik()+tutartakim.getHedefINGaylik());
			
			hedeflerGunluk.setHedefHSBC(hedeflerGunluk.getHedefHSBC()+tutartakim.getHedefHSBC());
			hedeflerGunluk.setHedefAKBANK(hedeflerGunluk.getHedefAKBANK()+tutartakim.getHedefAKBANK());
			hedeflerGunluk.setHedefGARANTI(hedeflerGunluk.getHedefGARANTI()+tutartakim.getHedefGARANTI());
			hedeflerGunluk.setHedefING(hedeflerGunluk.getHedefING()+tutartakim.getHedefING());
			
			gunluk.setHSBC(gunluk.getHSBC()+tutartakim.getHSBC());
			gunluk.setAKBANK(gunluk.getAKBANK()+tutartakim.getAKBANK());
			gunluk.setGARANTI(gunluk.getGARANTI()+tutartakim.getGARANTI());
			gunluk.setING(gunluk.getING()+tutartakim.getING());
			
			aylik.setHSBCaylik(aylik.getHSBCaylik()+tutartakim.getHSBCaylik());
			aylik.setAKBANKaylik(aylik.getAKBANKaylik()+tutartakim.getAKBANKaylik());
			aylik.setGARANTIaylik(aylik.getGARANTIaylik()+tutartakim.getGARANTIaylik());
			aylik.setINGaylik(aylik.getINGaylik()+tutartakim.getINGaylik());
			
		//	DashBoradFaces.get(0).getBankaTutarModel().getHSBC();
		//	DashBoradFaces.get(0).getBankaTutarModel().getYuzdeHSBC();
			
			//DashBoradFaces.get(0).getPersonel().get(0).getPersonelAdi()
			
		}
	
//	
//		System.out.println("---------------- "+DashBorad.get(0).getTakimAdi()+" ----------------");
//		System.out.println("---------------------------------------------------------------------");
//		System.out.println("");
//		System.out.println("Personel Adı........: "+ DashBorad.get(0).getPersonel().get(0).getPersonelAdi());
//		System.out.println("HSBC GÜNLÜK HEDEF....:"+ DashBorad.get(0).getPersonel().get(0).getBankamodel().getHedefHSBC());
//		System.out.println("HSBC AYLIK HEDEF....:"+ DashBorad.get(0).getPersonel().get(0).getBankamodel().getHedefHSBCaylik());
//		System.out.println("HSBC GÜNLÜK TOPLAM....:"+ DashBorad.get(0).getPersonel().get(0).getBankamodel().getHSBC());
//		System.out.println("");
//		System.out.println("Personel Adı........: "+ DashBorad.get(0).getPersonel().get(1).getPersonelAdi());
//		System.out.println("HSBC GÜNLÜK HEDEF....:"+ DashBorad.get(0).getPersonel().get(1).getBankamodel().getHedefHSBC());
//		System.out.println("HSBC AYLIK HEDEF....:"+ DashBorad.get(0).getPersonel().get(1).getBankamodel().getHedefHSBCaylik());
//		System.out.println("HSBC GÜNLÜK TOPLAM....:"+ DashBorad.get(0).getPersonel().get(1).getBankamodel().getHSBC());
//		System.out.println("");
//		System.out.println("Personel Adı........: "+ DashBorad.get(0).getPersonel().get(2).getPersonelAdi());
//		System.out.println("HSBC GÜNLÜK HEDEF....:"+ DashBorad.get(0).getPersonel().get(2).getBankamodel().getHedefHSBC());
//		System.out.println("HSBC AYLIK HEDEF....:"+ DashBorad.get(0).getPersonel().get(2).getBankamodel().getHedefHSBCaylik());
//		System.out.println("HSBC GÜNLÜK TOPLAM....:"+ DashBorad.get(0).getPersonel().get(2).getBankamodel().getHSBC());
//		
//		
		
		
	}
	
	
	
	// -------------- TAKIMLARI TANIMLIYORUZ ---------------------
	//    TOPLAM 6 TAKIM VE HER TAKIMDA 6 KİŞİ OLACAK ŞEKİLDE
	//------------------------------------------------------------
	
	public void takimtanimla(){
		
		for (int i = 0; i < 6; i++) {
			dashboardModels takim = new dashboardModels();
			ArrayList<dashboardPersonelModel> personelListesi = new ArrayList<>();
			for (int j = 0; j < 8 ; j++) {
				dashboardPersonelModel personel = new dashboardPersonelModel();
				bankaTutarModel bankaPersonelBilgi = new bankaTutarModel();
				personel.setBankamodel(bankaPersonelBilgi);
				personelListesi.add(personel);				
			}
			bankaTutarModel bankaTakimBilgi = new bankaTutarModel();
			takim.setBankaTutarModel(bankaTakimBilgi);
			takim.setPersonel(personelListesi);
			DashBoradFaces.add(takim);
		}
	
	}
	
	
	
	// ------------ VERİLERİ AKTARIYORUZ  -------------------------------
	//     ÇEKİLEN VERİLERİ ARAYÜZ TANIMLARINA AKTARIYORUZ
	// ------------------------------------------------------------------
	
	public void aktar(){
		
		for (int i = 0; i < DashBorad.size(); i++) {
			DashBoradFaces.get(i).setBankaTutarModel(DashBorad.get(i).getBankaTutarModel());
			DashBoradFaces.get(i).setDurum(true);
			DashBoradFaces.get(i).setTakimAdi(DashBorad.get(i).getTakimAdi());
			DashBoradFaces.get(i).setTakimResmi(DashBorad.get(i).getTakimResmi());
			
			for (int j = 0; j < DashBorad.get(i).getPersonel().size(); j++) {
				DashBoradFaces.get(i).getPersonel().get(j).setBankamodel(DashBorad.get(i).getPersonel().get(j).getBankamodel());
				
				DashBoradFaces.get(i).getPersonel().get(j).setDurum(true);
				DashBoradFaces.get(i).getPersonel().get(j).setPersonelAdi(DashBorad.get(i).getPersonel().get(j).getPersonelAdi());
				
			}
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	public bankaTutarModel getHedeflerGunluk() {
		return hedeflerGunluk;
	}


	public void setHedeflerGunluk(bankaTutarModel hedeflerGunluk) {
		this.hedeflerGunluk = hedeflerGunluk;
	}


	public bankaTutarModel getHedeflerAylik() {
		return hedeflerAylik;
	}


	public void setHedeflerAylik(bankaTutarModel hedeflerAylik) {
		this.hedeflerAylik = hedeflerAylik;
	}


	public bankaTutarModel getHedeflerToplam() {
		return hedeflerToplam;
	}


	public void setHedeflerToplam(bankaTutarModel hedeflerToplam) {
		this.hedeflerToplam = hedeflerToplam;
	}


	public ArrayList<dashboardModels> getDashBoradFaces() {
		return DashBoradFaces;
	}


	public void setDashBoradFaces(ArrayList<dashboardModels> dashBoradFaces) {
		DashBoradFaces = dashBoradFaces;
	}


	public ArrayList<dashboardModels> getDashBorad() {
		return DashBorad;
	}



	public void setDashBorad(ArrayList<dashboardModels> dashBorad) {
		DashBorad = dashBorad;
	}


	

	public bankaTutarModel getGenelToplam() {
		return genelToplam;
	}


	public void setGenelToplam(bankaTutarModel genelToplam) {
		this.genelToplam = genelToplam;
	}


	public bankaTutarModel getGunluk() {
		return gunluk;
	}


	public void setGunluk(bankaTutarModel gunluk) {
		this.gunluk = gunluk;
	}


	public bankaTutarModel getAylik() {
		return aylik;
	}


	public void setAylik(bankaTutarModel aylik) {
		this.aylik = aylik;
	}


	public String getTarih() {
		return tarih;
	}


	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

	

	
//	dashboardModels models = new dashboardModels();
//	models.setTakimAdi("KAPLANLAR TAKIMI");
//	models.setStarRate(2);
//	models.setTakimResmi("Deneme Takım Resmi");
//	bankaTutarModel takimTutarModel = new bankaTutarModel();
//
//	NumberFormat defaultFormat2 = NumberFormat.getCurrencyInstance();
//	takimTutarModel.setAKBANK(defaultFormat2.format(3000));
//	takimTutarModel.setGARANTI(defaultFormat2.format(4000));
//	takimTutarModel.setHSBC(defaultFormat2.format(4766));
//	takimTutarModel.setING(defaultFormat2.format(4000));
//
//	models.setBankaTutarModel(takimTutarModel);
//
//	ArrayList<dashboardPersonelModel> list = new ArrayList<>();
//
//	dashboardPersonelModel personel = new dashboardPersonelModel();
//
//	personel.setPersonelAdi("Husnu TAPAN");
//	personel.setAKBANK(3000);
//	personel.setGARANTI(3000);
//	personel.setHSBC(3000);
//	personel.setING(3000);
//
//	dashboardPersonelModel personel2 = new dashboardPersonelModel();
//
//	personel2.setPersonelAdi("Muhammet Ali KAYA");
//	personel2.setAKBANK(4000);
//	personel2.setGARANTI(4000);
//	personel2.setHSBC(4000);
//	personel2.setING(4000);
//
//	list.add(personel);
//	list.add(personel2);
//
//	models.setPersonel(list);
//
//	panelListesi.add(models);
//
//	NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
//
//	Hedef.setAKBANK(defaultFormat.format(12500));
//	Hedef.setGARANTI(defaultFormat.format(25000));
//	Hedef.setHSBC(defaultFormat.format(62500));
//	Hedef.setING(defaultFormat.format(2500));
//
//	// hedef bankaların koymus oldugu degerler
//
//	Hedef.setHedefAKBANK(defaultFormat.format(20000));
//	Hedef.setHedefGARANTI(defaultFormat.format(25000));
//	Hedef.setHedefHSBC(defaultFormat.format(30000));
//	Hedef.setHedefING(defaultFormat.format(20000));
//
//	Hedef.setYuzdeAKBANK(yuzdeHesapla(Hedef.getAKBANK(), Hedef.getHedefAKBANK()));
//	Hedef.setYuzdeGARANTI(yuzdeHesapla(Hedef.getGARANTI(), Hedef.getHedefGARANTI()));
//	Hedef.setYuzdeHSBC(yuzdeHesapla(Hedef.getHSBC(), Hedef.getHedefHSBC()));
//	Hedef.setYuzdeING(yuzdeHesapla(Hedef.getING(), Hedef.getHedefING()));
//	// System.out.println("takım adı.....:" +
//	// panelListesi.get(0).getTakimAdi());
//
//	Gunluk.setAKBANK(defaultFormat.format(12500));
//	Gunluk.setGARANTI(defaultFormat.format(20000));
//	Gunluk.setHSBC(defaultFormat.format(62200));
//	Gunluk.setING(defaultFormat.format(4500));
//
//	Gunluk.setHedefAKBANK(defaultFormat.format(40000));
//	Gunluk.setHedefGARANTI(defaultFormat.format(40000));
//	Gunluk.setHedefHSBC(defaultFormat.format(40000));
//	Gunluk.setHedefING(defaultFormat.format(40000));
//
//	Gunluk.setYuzdeAKBANK(yuzdeHesapla(Gunluk.getAKBANK(), Gunluk.getHedefAKBANK()));
//	Gunluk.setYuzdeGARANTI(yuzdeHesapla(Gunluk.getGARANTI(), Gunluk.getHedefGARANTI()));
//	Gunluk.setYuzdeHSBC(yuzdeHesapla(Gunluk.getHSBC(), Gunluk.getHedefHSBC()));
//	Gunluk.setYuzdeING(yuzdeHesapla(Gunluk.getING(), Gunluk.getHedefING()));
//
//	Aylık.setAKBANK(defaultFormat.format(3000));
//	Aylık.setGARANTI(defaultFormat.format(2300));
//	Aylık.setHSBC(defaultFormat.format(4000));
//	Aylık.setING(defaultFormat.format(4000));
//
//	Aylık.setHedefAKBANK(defaultFormat.format(40000));
//	Aylık.setHedefGARANTI(defaultFormat.format(40000));
//	Aylık.setHedefHSBC(defaultFormat.format(40000));
//	Aylık.setHedefING(defaultFormat.format(40000));
//
//	Aylık.setYuzdeAKBANK(yuzdeHesapla(Aylık.getAKBANK(), Aylık.getHedefAKBANK()));
//	Aylık.setYuzdeGARANTI(yuzdeHesapla(Aylık.getGARANTI(), Aylık.getHedefGARANTI()));
//	Aylık.setYuzdeHSBC(yuzdeHesapla(Aylık.getHSBC(), Aylık.getHedefHSBC()));
//	Aylık.setYuzdeING(yuzdeHesapla(Aylık.getING(), Aylık.getHedefING()));
//
//	GenleToplam.setAKBANK(defaultFormat.format(3000));
//	GenleToplam.setGARANTI(defaultFormat.format(2300));
//	GenleToplam.setHSBC(defaultFormat.format(4000));
//	GenleToplam.setING(defaultFormat.format(4000));
//
//	GenleToplam.setHedefAKBANK(defaultFormat.format(40000));
//	GenleToplam.setHedefGARANTI(defaultFormat.format(40000));
//	GenleToplam.setHedefHSBC(defaultFormat.format(40000));
//	GenleToplam.setHedefING(defaultFormat.format(40000));
//
//	GenleToplam.setYuzdeAKBANK(yuzdeHesapla(GenleToplam.getAKBANK(), GenleToplam.getHedefAKBANK()));
//	GenleToplam.setYuzdeGARANTI(yuzdeHesapla(GenleToplam.getGARANTI(), GenleToplam.getHedefGARANTI()));
//	GenleToplam.setYuzdeHSBC(yuzdeHesapla(GenleToplam.getHSBC(), GenleToplam.getHedefHSBC()));
//	GenleToplam.setYuzdeING(yuzdeHesapla(GenleToplam.getING(), GenleToplam.getHedefING()));
	
	
}
