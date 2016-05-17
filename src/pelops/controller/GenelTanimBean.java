package pelops.controller;


import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pelops.dao.AdresTuruDAO;
import pelops.dao.TanimlarDAO;
import pelops.model.AdresTuru;
import pelops.model.GenelTanimSablon;


@ManagedBean(name="geneltanimbean")
@SessionScoped
public class GenelTanimBean {

	private ArrayList<GenelTanimSablon> Liste = new ArrayList<GenelTanimSablon>();
	GenelTanimSablon sablon;
	boolean iptalrender, duzenlesilrender;
	

	public boolean isIptalrender() {
		return iptalrender;
	}
	public void setIptalrender(boolean iptalrender) {
		this.iptalrender = iptalrender;
	}
	public boolean isduzenlesilrender() {
		return duzenlesilrender;
	}
	public void setduzenlesilrender(boolean duzenlesilrender) {
		this.duzenlesilrender = duzenlesilrender;
	}

	
	public GenelTanimBean(){
		sablon = new GenelTanimSablon();
		this.setIptalrender(false);
		this.setduzenlesilrender(true);
	}
	
	int idGelen;
	String butonValue="Kaydet";
	
	public int getIdGelen() {
		return idGelen;
	}
	public void setIdGelen(int idGelen) {
		this.idGelen = idGelen;
	}
	public String getButonValue() {
		return butonValue;
	}
	public void setButonValue(String butonValue) {
		this.butonValue = butonValue;
	}
	public ArrayList<GenelTanimSablon> getListe() {
		return Liste;
	}
	public void setListe(ArrayList<GenelTanimSablon> liste) {
		Liste = liste;
	}
	
	
	public GenelTanimSablon getSablon() {
		return sablon;
	}
	public void setSablon(GenelTanimSablon sablon) {
		this.sablon = sablon;
	}
	public ArrayList<GenelTanimSablon> ListeGetir(String DBAdi) throws Exception{
		Liste = new ArrayList<GenelTanimSablon>();
		TanimlarDAO dao = new TanimlarDAO();
		Liste = dao.Liste(DBAdi);
		return Liste;
		}
	
	public void Kayit(String DBAdi) throws Exception{
		TanimlarDAO dao = new TanimlarDAO();
		dao.Kayit(sablon, DBAdi);
		Liste = new ArrayList<GenelTanimSablon>();
		Liste = dao.Liste(DBAdi);
		this.setIptalrender(false);
		this.setduzenlesilrender(true);
		
	}
	
	public void Sil( String DBAdi) throws Exception{
		TanimlarDAO dao = new TanimlarDAO();
		int id=Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sil").toString());
		dao.Sil(id, DBAdi);
		Liste = new ArrayList<GenelTanimSablon>();
		Liste = dao.Liste(DBAdi);
		
	}
	
	public void Duzenle(String id, String adi, String DBAdi) throws Exception{
		TanimlarDAO dao = new TanimlarDAO();
		GenelTanimSablon Sablon = new GenelTanimSablon();
		Sablon.setAdi(adi);
		Sablon.setId(id);
		dao.Duzenle(Sablon, DBAdi);
		Liste = new ArrayList<GenelTanimSablon>();
		Liste = dao.Liste(DBAdi);	
		this.butonValue="Kaydet";
		this.setIptalrender(false);
		this.setduzenlesilrender(true);
	}
	
	public void TabloButonDuzenle(){
		this.butonValue="Düzenleme Yap";
		GenelTanimSablon Sablon = new GenelTanimSablon();
		Sablon.setAdi(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("adi").toString());
		Sablon.setId(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id").toString());
		
		this.setSablon(Sablon);
		this.setIdGelen(1);
		this.setIptalrender(true);
		this.setduzenlesilrender(false);
	}
	
	public void IslemButonDuzenleKaydet(String DBAdi) throws Exception{
		if (this.getIdGelen()==1) {
			Duzenle(sablon.getId(), sablon.getAdi(), DBAdi);
			sablon.setAdi("");
		}
		else
		{
			Kayit(DBAdi);
			sablon.setAdi("");
		}
	}
	
	public void Iptal(){
		this.setIptalrender(false);
		this.setduzenlesilrender(true);
		sablon.setAdi("");
		this.butonValue="Kaydet";
		
	}
	
	public ArrayList<AdresTuru> DosyaTuruListesi(){
		AdresTuruDAO dao = new AdresTuruDAO();
		
		return dao.AdresListesi();
	}
	
}
