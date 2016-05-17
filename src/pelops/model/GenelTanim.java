package pelops.model;

import java.util.ArrayList;

public class GenelTanim {
	public ArrayList<GenelTanimSablon> Liste = new ArrayList<GenelTanimSablon>();
	GenelTanimSablon Sablon;
	
	
	public ArrayList<GenelTanimSablon> getListe() {
		return Liste;
	}
	public void setListe(ArrayList<GenelTanimSablon> liste) {
		Liste = liste;
	}
	public GenelTanimSablon getSablon() {
		return Sablon;
	}
	public void setSablon(GenelTanimSablon sablon) {
		Sablon = sablon;
	}
	
	
	
	

}
