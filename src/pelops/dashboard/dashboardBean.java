package pelops.dashboard;

import java.text.NumberFormat;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="dashBoardBean")
@SessionScoped
public class dashboardBean {

	private ArrayList<dashboardModels> panelListesi = new ArrayList<dashboardModels>();
	
	private bankaTutarModel Hedef = new bankaTutarModel();
	private bankaTutarModel Gunluk = new bankaTutarModel();
	private bankaTutarModel Aylık = new bankaTutarModel();
	private bankaTutarModel GenleToplam = new bankaTutarModel();
	
	
	
	public dashboardBean(){
		
		
		dashboardModels models = new dashboardModels();
		
		models.setTakimAdi("KAPLANLAR TAKIMI");
		
		panelListesi.add(models);
		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		
		Hedef.setAKBANK(defaultFormat.format(12500));
		Hedef.setGARANTI(defaultFormat.format(25000));
		Hedef.setHSBC(defaultFormat.format(62500));
		Hedef.setING(defaultFormat.format(2500));
		System.out.println("takım adı.....:" + panelListesi.get(0).getTakimAdi());

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
