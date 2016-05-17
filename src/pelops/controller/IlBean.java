package pelops.controller;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pelops.dao.IlDAO;
import pelops.model.Il;

@ManagedBean(name="ilBean")
@SessionScoped
public class IlBean {

	private ArrayList<Il> ilListesiArrayList= new ArrayList<Il>();
	private Il secimIl= new Il();


	public Il getSecimIl() {
		return secimIl;
	}

	public void setSecimIl(Il secimIl) {
		this.secimIl = secimIl;
	}

	public IlBean(){
		
		secimIl= new Il();
	}
	
	public ArrayList<Il> Liste() throws Exception{
		ilListesiArrayList= new ArrayList<Il>();
		IlDAO ildao = new IlDAO();
		ilListesiArrayList = ildao.Liste();
		return ilListesiArrayList;		
	}
	
	public void Kayit() throws Exception{
		IlDAO ildao = new IlDAO();
		ildao.Kayit(secimIl);
		ilListesiArrayList= new ArrayList<Il>();
		ilListesiArrayList = ildao.Liste();
	}
	
	public void Sil() throws Exception{
		IlDAO ildao = new IlDAO();
		int id=Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sil").toString());
		ildao.Sil(id);
		ilListesiArrayList= new ArrayList<Il>();
		ilListesiArrayList = ildao.Liste();
	}
	
	public void Duzenle() throws Exception{
		IlDAO ildao = new IlDAO();
		ildao.Duzenle(secimIl);
		ilListesiArrayList= new ArrayList<Il>();
		ilListesiArrayList = ildao.Liste();
		
	}
	

}
