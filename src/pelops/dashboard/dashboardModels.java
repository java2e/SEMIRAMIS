package pelops.dashboard;

import java.util.ArrayList;

public class dashboardModels {

	private String takimAdi;
	private String takimResmi;
	private int starRate;
	private ArrayList<dashboardPersonelModel> personel;
	private bankaTutarModel bankaTutarModel;
	
	private boolean durum=false;
	
	
	
	public boolean isDurum() {
		return durum;
	}
	public void setDurum(boolean durum) {
		this.durum = durum;
	}
	
	public String getTakimAdi() {
		return takimAdi;
	}
	public void setTakimAdi(String takimAdi) {
		this.takimAdi = takimAdi;
	}
	public String getTakimResmi() {
		return takimResmi;
	}
	public void setTakimResmi(String takimResmi) {
		this.takimResmi = takimResmi;
	}
	public int getStarRate() {
		return starRate;
	}
	public void setStarRate(int starRate) {
		this.starRate = starRate;
	}
	public ArrayList<dashboardPersonelModel> getPersonel() {
		return personel;
	}
	public void setPersonel(ArrayList<dashboardPersonelModel> personel) {
		this.personel = personel;
	}
	public bankaTutarModel getBankaTutarModel() {
		return bankaTutarModel;
	}
	public void setBankaTutarModel(bankaTutarModel bankaTutarModel) {
		this.bankaTutarModel = bankaTutarModel;
	}
	
	
	
	
	
	
	
}
