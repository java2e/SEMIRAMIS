package pelops.controller;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pelops.dao.TanimlarDAO;
import pelops.model.GenelTanimSablon;

@ManagedBean(name = "geneltanimbean")
@SessionScoped
public class GenelTanimBean {

	private ArrayList<GenelTanimSablon> sablonListesiArrayList = new ArrayList<GenelTanimSablon>();
	private GenelTanimSablon secimSablon = new GenelTanimSablon();
	boolean panelRender;
	boolean buttonDisabled;
	private GenelTanimSablon genelTanimSablon;

	boolean cmbRender;
	boolean lblRender;
	boolean kaydetButtonRender;

	int status = 0;

	public GenelTanimBean() {
		secimSablon = new GenelTanimSablon();
	}

	public ArrayList<GenelTanimSablon> Liste(String dbAdi) throws Exception {
		sablonListesiArrayList = new ArrayList<>();
		TanimlarDAO tanimlarDAO = new TanimlarDAO();
		sablonListesiArrayList = tanimlarDAO.Liste(dbAdi);
		return sablonListesiArrayList;
	}
	
	
	public ArrayList<GenelTanimSablon> ListeGetir(String dbAdi) throws Exception {
		sablonListesiArrayList = new ArrayList<>();
		TanimlarDAO tanimlarDAO = new TanimlarDAO();
		sablonListesiArrayList = tanimlarDAO.Liste(dbAdi);
		return sablonListesiArrayList;
	}

	public void Kayit(String dbAdi) throws Exception {

		TanimlarDAO tanimlarDAO = new TanimlarDAO();

		FacesContext context = FacesContext.getCurrentInstance();

		if (status == 0) {

			boolean result = tanimlarDAO.Kayit(secimSablon, dbAdi);

			if (result) {
				context.addMessage(null, new FacesMessage("Kaydedildi!"));

			} else {

				context.addMessage(null, new FacesMessage("Kaydet başarısız!"));

			}
		} else {
			boolean duzenlendi = tanimlarDAO.Duzenle(secimSablon, dbAdi);

			if (duzenlendi) {
				context.addMessage(null, new FacesMessage("Düzenlendi!"));
			} else {
				context.addMessage(null, new FacesMessage("Güncelleme işlemi başarısız!"));
			}
			status = 0;
		}

		sablonListesiArrayList = tanimlarDAO.Liste(dbAdi);

		PanelClose();
		ButtonOpen();

		sablonListesiArrayList = new ArrayList<GenelTanimSablon>();
		sablonListesiArrayList = tanimlarDAO.Liste(dbAdi);

		this.panelRender = false;
		ButtonOpen();
	}

	public void Sil(String dbAdi) throws Exception {
		TanimlarDAO tanimlarDAO = new TanimlarDAO();
		int id = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sil").toString());
		tanimlarDAO.Sil(id, dbAdi);
		sablonListesiArrayList = new ArrayList<>();
		sablonListesiArrayList = tanimlarDAO.Liste(dbAdi);
	}

	public void Duzenle(String dbAdi) throws Exception {
		status = 1;

		TanimlarDAO tanimlarDAO = new TanimlarDAO();
		sablonListesiArrayList = new ArrayList<GenelTanimSablon>();
		ArrayList<GenelTanimSablon> list = tanimlarDAO.Liste(dbAdi);

		int id = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id").toString());

		for (GenelTanimSablon hem : list) {
			if (hem.getId() == id) {
				genelTanimSablon = hem;
			}
		}
		secimSablon = genelTanimSablon;
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
		genelTanimSablon = new GenelTanimSablon();
		secimSablon = new GenelTanimSablon();
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

	public GenelTanimSablon getSecimSablon() {
		return secimSablon;
	}

	public void setSecimSablon(GenelTanimSablon secimSablon) {
		this.secimSablon = secimSablon;
	}

	public GenelTanimSablon getGenelTanimSablon() {
		return genelTanimSablon;
	}

	public void setGenelTanimSablon(GenelTanimSablon genelTanimSablon) {
		this.genelTanimSablon = genelTanimSablon;
	}
	
	

}
