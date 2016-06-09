package pelops.dashboard;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="dashBoard")
@SessionScoped
public class dashboardBean {

	private ArrayList<dashboardModels> panelListesi;
	
	
	public dashboardBean(){
		
		dashboardModels models = new dashboardModels();
		
		models.setTakimAdi("KAPLANLAR TAKIMI");
		
		panelListesi.add(models);
		
		System.out.println(panelListesi.get(0).getTakimAdi());

	}


	public ArrayList<dashboardModels> getPanelListesi() {
		return panelListesi;
	}


	public void setPanelListesi(ArrayList<dashboardModels> panelListesi) {
		this.panelListesi = panelListesi;
	}


	
	
	
}
