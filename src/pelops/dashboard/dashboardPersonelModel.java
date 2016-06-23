package pelops.dashboard;

public class dashboardPersonelModel {

	private String personelAdi;
	private bankaTutarModel bankamodel = new bankaTutarModel();
	private boolean durum=false;
	
	
	
	public boolean isDurum() {
		return durum;
	}
	public void setDurum(boolean durum) {
		this.durum = durum;
	}
	public String getPersonelAdi() {
		return personelAdi;
	}
	public void setPersonelAdi(String personelAdi) {
		this.personelAdi = personelAdi;
	}
	public bankaTutarModel getBankamodel() {
		return bankamodel;
	}
	public void setBankamodel(bankaTutarModel bankamodel) {
		this.bankamodel = bankamodel;
	}
	
	
	
	
	
}
