package pelops.controller;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import pelops.dao.IlceDAO;
import pelops.model.Ilce;

@ManagedBean(name="ilceBean")
@SessionScoped
public class IlceBean {

	private ArrayList<Ilce> ilceListesiArrayList= new ArrayList<Ilce>();
	private Ilce secimIlce;

	public ArrayList<Ilce> getIlceListesiArrayList() {
		return ilceListesiArrayList;
	}

	public void setIlceListesiArrayList(ArrayList<Ilce> ilceListesiArrayList) {
		this.ilceListesiArrayList = ilceListesiArrayList;
	}

	public Ilce getSecimIlce() {
		return secimIlce;
	}

	public void setSecimIlce(Ilce secimIlce) {
		this.secimIlce = secimIlce;
	}

	public IlceBean(){
		
		secimIlce= new Ilce();
	}
	
	public ArrayList<Ilce> Liste() throws Exception{
		ilceListesiArrayList= new ArrayList<Ilce>();
		IlceDAO ilcedao = new IlceDAO();
		ilceListesiArrayList = ilcedao.Liste();
		return ilceListesiArrayList;		
	}
	
	public void Kayit() throws Exception{
		IlceDAO ilcedao = new IlceDAO();
		ilcedao.Kayit(secimIlce);
		ilceListesiArrayList= new ArrayList<Ilce>();
		ilceListesiArrayList = ilcedao.Liste();
	}
	
	public void Sil() throws Exception{
		IlceDAO ilcedao = new IlceDAO();
		int id=Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sil").toString());
		ilcedao.Sil(id);
		ilceListesiArrayList= new ArrayList<Ilce>();
		ilceListesiArrayList = ilcedao.Liste();
	}
	
	public void Duzenle() throws Exception{
		IlceDAO ilcedao = new IlceDAO();
		ilcedao.Duzenle(secimIlce);
		ilceListesiArrayList= new ArrayList<Ilce>();
		ilceListesiArrayList = ilcedao.Liste();
		
	}
	
}
