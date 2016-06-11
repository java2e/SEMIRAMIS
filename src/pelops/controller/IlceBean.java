package pelops.controller;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pelops.dao.IlDAO;
import pelops.dao.IlceDAO;
import pelops.model.Il;
import pelops.model.Ilce;

@ManagedBean(name = "ilceBean")
@SessionScoped
public class IlceBean {

	private ArrayList<Ilce> ilceListesiArrayList = new ArrayList<Ilce>();
	private Ilce secimIlce;
	boolean panelRender;
	boolean buttonDisabled;
	private Ilce ilce;
	boolean cmbRender;
	boolean lblRender;
	boolean kaydetButtonRender;

	int status = 0;

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

	public IlceBean() {

		secimIlce = new Ilce();
	}

	public ArrayList<Ilce> Liste() throws Exception {
		ilceListesiArrayList = new ArrayList<Ilce>();
		IlceDAO ilcedao = new IlceDAO();
		ilceListesiArrayList = ilcedao.Liste();
		return ilceListesiArrayList;
	}

	public void YeniKayit() throws Exception {
		status = 0;
		ilce = new Ilce();
		secimIlce = new Ilce();
		PanelOpen();
	}

	public void Kayit() throws Exception {

		IlceDAO ilcedao = new IlceDAO();

		FacesContext context = FacesContext.getCurrentInstance();

		if (status == 0) {

			boolean result = ilcedao.Kayit(secimIlce);

			if (result) {
				context.addMessage(null, new FacesMessage("Kaydedildi!"));

			} else {

				context.addMessage(null, new FacesMessage("Kaydet başarısız!"));

			}
		} else {
			boolean duzenlendi = ilcedao.Guncelle(secimIlce);

			if (duzenlendi) {
				context.addMessage(null, new FacesMessage("Düzenlendi!"));
			} else {
				context.addMessage(null, new FacesMessage("Güncelleme işlemi başarısız!"));
			}
			status = 0;
		}

		ilceListesiArrayList = ilcedao.Liste();

		PanelClose();
		ButtonOpen();

		ilceListesiArrayList = new ArrayList<Ilce>();
		ilceListesiArrayList = ilcedao.Liste();

		this.panelRender = false;
		ButtonOpen();
	}

	public void ButtonClose() {

		this.setButtonDisabled(true);

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

	public void Sil() throws Exception {
		IlceDAO ilcedao = new IlceDAO();
		int id = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sil").toString());
		ilcedao.Sil(id);
		ilceListesiArrayList = new ArrayList<Ilce>();
		ilceListesiArrayList = ilcedao.Liste();
	}

	public void Duzenle() throws Exception {
		IlceDAO ilcedao = new IlceDAO();
		ilcedao.Duzenle(secimIlce);
		ilceListesiArrayList = new ArrayList<Ilce>();
		ilceListesiArrayList = ilcedao.Liste();

	}
	
	public void Vazgec() {
		status = 0;
		PanelClose();
		ButtonOpen();
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

	public Ilce getIlce() {
		return ilce;
	}

	public void setIlce(Ilce ilce) {
		this.ilce = ilce;
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

}
