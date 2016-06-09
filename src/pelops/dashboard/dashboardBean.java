package pelops.dashboard;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="dashBoard")
@SessionScoped
public class dashboardBean {

	private ArrayList<dashboardModels> panelListesi;
	
	
	public dashboardBean(){
		

	}


	public ArrayList<dashboardModels> getPanelListesi() {
		return panelListesi;
	}


	public void setPanelListesi(ArrayList<dashboardModels> panelListesi) {
		this.panelListesi = panelListesi;
	}


	
	
	
}
