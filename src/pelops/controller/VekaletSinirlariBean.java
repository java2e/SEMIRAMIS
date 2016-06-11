package pelops.controller;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pelops.dao.VekaletSinirlariDAO;
import pelops.model.VekaletSinirlari;

@ManagedBean(name="vekaletSinirlariBean")
@SessionScoped
public class VekaletSinirlariBean {
	
	private ArrayList<VekaletSinirlari> vekaletSinirlariArrayList= new ArrayList<VekaletSinirlari>();
	private VekaletSinirlari secimVekaletSinirlari= new VekaletSinirlari();
	boolean panelRender;
	boolean buttonDisabled;
	private VekaletSinirlari vekaletSinirlari;
	boolean cmbRender;
	boolean lblRender;
	boolean kaydetButtonRender;
	
	int status=0;

	

	public VekaletSinirlari getVekaletSinirlari() {
		return vekaletSinirlari;
	}

	public void setVekaletSinirlari(VekaletSinirlari vekaletSinirlari) {
		this.vekaletSinirlari = vekaletSinirlari;
	}

	public VekaletSinirlariBean() {
		
		secimVekaletSinirlari= new VekaletSinirlari();
	}
	
	public ArrayList<VekaletSinirlari> Liste() throws Exception{
		vekaletSinirlariArrayList= new ArrayList<VekaletSinirlari>();
		VekaletSinirlariDAO vekaletSinirlariDAO= new VekaletSinirlariDAO();
		vekaletSinirlariArrayList = vekaletSinirlariDAO.Liste();
		return vekaletSinirlariArrayList;		
	}
	
	public void Kayit() throws Exception{
		
		VekaletSinirlariDAO vekaletSinirlariDAO= new VekaletSinirlariDAO();

		FacesContext context = FacesContext.getCurrentInstance();
		
		if (status == 0) {

			boolean result = 	vekaletSinirlariDAO.Kaydet(secimVekaletSinirlari);
		

			if (result) {
				context.addMessage(null, new FacesMessage("Kaydedildi!"));

			} else {

				context.addMessage(null, new FacesMessage("Kaydet başarısız!"));

			}
		} else {
			boolean duzenlendi = vekaletSinirlariDAO.Duzenle(secimVekaletSinirlari);
		

			if (duzenlendi) {
				context.addMessage(null, new FacesMessage("Düzenlendi!"));
			} else {
				context.addMessage(null, new FacesMessage("Güncelleme işlemi başarısız!"));
			}
			status = 0;
		}

		vekaletSinirlariArrayList = vekaletSinirlariDAO.Liste();

		PanelClose();
		ButtonOpen();
		
		
		
	
	
		vekaletSinirlariArrayList= new ArrayList<VekaletSinirlari>();
		vekaletSinirlariArrayList = vekaletSinirlariDAO.Liste();
		
		this.panelRender = false;
		ButtonOpen();
	}
	
	public void Sil() throws Exception{
		VekaletSinirlariDAO vekaletHarciDAO = new VekaletSinirlariDAO();
		int id=Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sil").toString());
		vekaletHarciDAO.Sil(id);
		vekaletSinirlariArrayList= new ArrayList<VekaletSinirlari>();
		vekaletSinirlariArrayList = vekaletHarciDAO.Liste();
	}
	
	public void Duzenle() throws Exception{
			status = 1;

			VekaletSinirlariDAO vekaletHarciDAO = new VekaletSinirlariDAO();
			vekaletSinirlariArrayList= new ArrayList<VekaletSinirlari>();
			ArrayList<VekaletSinirlari> list = vekaletHarciDAO.Liste();

			int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
					.get("id").toString());
			

			for (VekaletSinirlari hem : list) {
				if (hem.getId() == id) {
					vekaletSinirlari = hem;
				}
			}
			secimVekaletSinirlari=vekaletSinirlari;
			PanelOpen();
			ButtonClose();
		
	}
	
	public void ButtonClose() {

		this.setButtonDisabled(true);

	}

	
	public void Vazgec() {
		status = 0;
		PanelClose();
		ButtonOpen();
	}
	
	public void YeniKayit() throws Exception {

		status = 0;
		vekaletSinirlari = new VekaletSinirlari();
		secimVekaletSinirlari = new VekaletSinirlari();
		PanelOpen();

	}
	
	public void PanelOpen() throws Exception {
		this.setPanelRender(true);
	}
	
	public void LblPanelOpen() {
		this.setCmbRender(false);
		this.setLblRender(true);
		this.setKaydetButtonRender(false);
	}
	
	public void PanelClose() {

		this.setPanelRender(false);

	}
	public void ButtonOpen() {

		this.setButtonDisabled(false);

	}

	public boolean isPanelRender() {
		return panelRender;
	}

	public void setPanelRender(boolean panelRender) {
		this.panelRender = panelRender;
	}

	public boolean isButtonDisabled() {
		return buttonDisabled;
	}

	public void setButtonDisabled(boolean buttonDisabled) {
		this.buttonDisabled = buttonDisabled;
	}


	

	public VekaletSinirlari getSecimVekaletSinirlari() {
		return secimVekaletSinirlari;
	}

	public void setSecimVekaletSinirlari(VekaletSinirlari secimVekaletSinirlari) {
		this.secimVekaletSinirlari = secimVekaletSinirlari;
	}

	public boolean isCmbRender() {
		return cmbRender;
	}

	public void setCmbRender(boolean cmbRender) {
		this.cmbRender = cmbRender;
	}

	public boolean isLblRender() {
		return lblRender;
	}

	public void setLblRender(boolean lblRender) {
		this.lblRender = lblRender;
	}

	public boolean isKaydetButtonRender() {
		return kaydetButtonRender;
	}

	public void setKaydetButtonRender(boolean kaydetButtonRender) {
		this.kaydetButtonRender = kaydetButtonRender;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
