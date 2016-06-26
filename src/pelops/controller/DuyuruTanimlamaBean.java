package pelops.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import pelops.dao.DuyuruDAO;
import pelops.model.Duyuru;

@ManagedBean(name = "duyuruTanimlamaBean")
@ViewScoped
public class DuyuruTanimlamaBean {
	
	private DuyuruDAO duyuruDAO;
	
	private Duyuru duyuru;
	
	private boolean islem;
	
	private List<Duyuru> duyuruList;

	public DuyuruDAO getDuyuruDAO() {
		return duyuruDAO;
	}

	public void setDuyuruDAO(DuyuruDAO duyuruDAO) {
		this.duyuruDAO = duyuruDAO;
	}

	public Duyuru getDuyuru() {
		return duyuru;
	}

	public void setDuyuru(Duyuru duyuru) {
		this.duyuru = duyuru;
	}

	public boolean isIslem() {
		return islem;
	}

	public void setIslem(boolean islem) {
		this.islem = islem;
	}

	public List<Duyuru> getDuyuruList() {
		return duyuruList;
	}

	public void setDuyuruList(List<Duyuru> duyuruList) {
		this.duyuruList = duyuruList;
	}
	
	@PostConstruct
    public void init() {
		duyuru = new Duyuru();
    	
    	duyuruDAO = new DuyuruDAO();
    	duyuruList=duyuruDAO.select();    
    	
    	islem = true;
    }   
	
	public void onDuyuruSelected(int id){
		System.out.println(id);
		duyuru = duyuruDAO.selectById(id);
		
		islem = false;		
	}
	
	public void kaydet(){
		duyuruDAO.insert(duyuru);
		
		duyuruList = duyuruDAO.select();		
		FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Kaydedildi!"));		
	}

	public void guncelle(){
		duyuruDAO.update(duyuru);
		duyuruList = duyuruDAO.select();
	}
	
	public void sil(){
		duyuruDAO.delete(duyuru.getId());
		duyuruList = duyuruDAO.select();
	}
		
	public void onKaydetClicked(){
		islem = true;
		duyuru.setAciklama("");
		duyuru.setGunTarih(null);
		duyuru.setId(-1);
	}


	



}
