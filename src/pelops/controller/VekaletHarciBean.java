package pelops.controller;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pelops.dao.BasvuruHarciDAO;
import pelops.dao.VekaletHarciDAO;
import pelops.model.BasvuruHarci;
import pelops.model.VekaletHarci;

@ManagedBean(name="vekaletHarci")
@SessionScoped
public class VekaletHarciBean {
	
	private ArrayList<VekaletHarci> vekaletHarciArrayList= new ArrayList<VekaletHarci>();
	private VekaletHarci secimVekaletHarci= new VekaletHarci();
	boolean panelRender;
	boolean buttonDisabled;
	private VekaletHarci vekaletHarci;
	boolean cmbRender;
	boolean lblRender;
	boolean kaydetButtonRender;
	
	int status=0;

	

	public VekaletHarci getVekaletHarci() {
		return vekaletHarci;
	}

	public void setVekaletHarci(VekaletHarci vekaletHarci) {
		this.vekaletHarci = vekaletHarci;
	}

	public VekaletHarciBean() {
		
		secimVekaletHarci= new VekaletHarci();
	}
	
	public ArrayList<VekaletHarci> Liste() throws Exception{
		vekaletHarciArrayList= new ArrayList<VekaletHarci>();
		VekaletHarciDAO vekaletHarciDAO = new VekaletHarciDAO();
		vekaletHarciArrayList = vekaletHarciDAO.Liste();
		return vekaletHarciArrayList;		
	}
	
	public void Kayit() throws Exception{
		
		VekaletHarciDAO vekaletHarciDAO = new VekaletHarciDAO();

		FacesContext context = FacesContext.getCurrentInstance();
		
		if (status == 0) {

			boolean result = 	vekaletHarciDAO.Kaydet(secimVekaletHarci);
		

			if (result) {
				context.addMessage(null, new FacesMessage("Kaydedildi!"));

			} else {

				context.addMessage(null, new FacesMessage("Kaydet başarısız!"));

			}
		} else {
			boolean duzenlendi = vekaletHarciDAO.Duzenle(secimVekaletHarci);
		

			if (duzenlendi) {
				context.addMessage(null, new FacesMessage("Düzenlendi!"));
			} else {
				context.addMessage(null, new FacesMessage("Güncelleme işlemi başarısız!"));
			}
			status = 0;
		}

		vekaletHarciArrayList = vekaletHarciDAO.Liste();

		PanelClose();
		ButtonOpen();
		
		
		
	
	
		vekaletHarciArrayList= new ArrayList<VekaletHarci>();
		vekaletHarciArrayList = vekaletHarciDAO.Liste();
		
		this.panelRender = false;
		ButtonOpen();
	}
	
	public void Sil() throws Exception{
		VekaletHarciDAO vekaletHarciDAO = new VekaletHarciDAO();
		int id=Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sil").toString());
		vekaletHarciDAO.Sil(id);
		vekaletHarciArrayList= new ArrayList<VekaletHarci>();
		vekaletHarciArrayList = vekaletHarciDAO.Liste();
	}
	
	public void Duzenle() throws Exception{
			status = 1;

			VekaletHarciDAO vekaletHarciDAO = new VekaletHarciDAO();
			vekaletHarciArrayList= new ArrayList<VekaletHarci>();
			ArrayList<VekaletHarci> list = vekaletHarciDAO.Liste();

			int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
					.get("id").toString());
			

			for (VekaletHarci hem : list) {
				if (hem.getId() == id) {
					vekaletHarci = hem;
				}
			}
			secimVekaletHarci=vekaletHarci;
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
		vekaletHarci = new VekaletHarci();
		secimVekaletHarci = new VekaletHarci();
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


	public VekaletHarci getSecimVekaletHarci() {
		return secimVekaletHarci;
	}

	public void setSecimVekaletHarci(VekaletHarci secimVekaletHarci) {
		this.secimVekaletHarci = secimVekaletHarci;
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
