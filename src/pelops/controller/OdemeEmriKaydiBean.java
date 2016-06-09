package pelops.controller;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import pelops.dao.OdemeEmriDAO;
import pelops.dao.UtilDAO;
import pelops.model.OdemeEmri;

@ManagedBean(name = "odemeEmriKaydiBean")
@SessionScoped
public class OdemeEmriKaydiBean {

	public OdemeEmriKaydiBean() {

		PanelClose();
		ButtonOpen();
	}

	private int status = 0;

	private OdemeEmri odemeEmri = new OdemeEmri();

	private OdemeEmriDAO dao = new OdemeEmriDAO();

	@SuppressWarnings("unused")
	private ArrayList<OdemeEmri> listOdeme = new ArrayList<OdemeEmri>();

	String icradosyaNo;

	String borcluAdi;

	private String icraMd;

	boolean panelRender;

	boolean buttonDisabled;

	public OdemeEmri getOdemeEmri() {

		return odemeEmri;
	}

	public void setOdemeEmri(OdemeEmri odemeEmri) {
		this.odemeEmri = odemeEmri;
	}

	public void Kaydet() throws Exception {

		FacesContext context = FacesContext.getCurrentInstance();

		if (odemeEmri.getOdemeMiktari() == 0.0 && odemeEmri.getOdemeSonucu() == "") {

			context.addMessage(null, new FacesMessage("Ödeme miktarı ve Ödeme sonucu doldurunuz!!!"));

		} else {

			if (status == 0) {

				java.sql.Date date = dao.convertFromJAVADateToSQLDate(odemeEmri.getOdemeTarihi());
				odemeEmri.setOdemeTarihi(date);

				odemeEmri.setIcraDosyadiID(AktifBean.getIcraDosyaID());

				boolean kaydedildi = dao.odemeEmriKaydet(odemeEmri);

				if (kaydedildi) {

					context.addMessage(null, new FacesMessage("Kaydedildi!"));

				} else {
					context.addMessage(null, new FacesMessage("Kaydet işlemi başarısız!"));

				}
				odemeEmri = new OdemeEmri();
			} else {
				odemeEmri.setIcraDosyadiID(AktifBean.getIcraDosyaID());
				boolean duzenlendi = dao.guncelle(odemeEmri);

				if (duzenlendi) {
					context.addMessage(null, new FacesMessage("Düzenlendi!"));
				} else {
					context.addMessage(null, new FacesMessage("Güncelleme işlemi başarısız!"));
				}
				status = 0;
				listOdeme = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);
			}

			PanelClose();
			ButtonOpen();

		}
	}

	public boolean isPanelRender() {
		return panelRender;
	}

	public void setPanelRender(boolean panelRender) {
		this.panelRender = panelRender;
	}

	public void Vazgec() {
		status = 0;
		PanelClose();
		ButtonOpen();

	}

	public void Duzenle() throws Exception {

		status = 1;

		ArrayList<OdemeEmri> list = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);

		int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("duzenle").toString());

		for (OdemeEmri oe : list) {
			if (oe.getId() == id) {
				odemeEmri = oe;
			}
		}
		listOdeme = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);
		PanelOpen();
		ButtonClose();

	}

	public void YeniKayit() {
		odemeEmri = new OdemeEmri();
		PanelOpen();

	}

	public void PanelOpen() {
		this.setPanelRender(true);
		ButtonClose();

	}

	public void PanelClose() {

		this.setPanelRender(false);

	}

	public void ButtonOpen() {

		this.setButtonDisabled(false);

	}

	public void ButtonClose() {
		this.setButtonDisabled(true);

	}

	public boolean isButtonDisabled() {
		return buttonDisabled;
	}

	public void setButtonDisabled(boolean buttonDisabled) {
		this.buttonDisabled = buttonDisabled;
	}

	public void Sil() throws Exception {

		FacesContext context = FacesContext.getCurrentInstance();

		int id = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sil").toString());

		int result = dao.Sil(id, odemeEmri);

		if (result == 1) {
			context.addMessage(null, new FacesMessage("Silindi!"));
		} else {
			context.addMessage(null, new FacesMessage("Silme Başarısız!"));
		}

	}

	public ArrayList<OdemeEmri> getListOdeme() throws Exception {
		return dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);
	}

	public void setListOdeme(ArrayList<OdemeEmri> listOdeme) {
		this.listOdeme = listOdeme;
	}

	public String getIcradosyaNo() {
		return AktifBean.icraDosyaNo;
	}

	public void setIcradosyaNo(String icradosyaNo) {
		this.icradosyaNo = icradosyaNo;
	}

	public String getBorcluAdi() {
		return AktifBean.borcluAdi;
	}

	public void setBorcluAdi(String borcluAdi) {
		this.borcluAdi = borcluAdi;
	}

	public String getIcraMd() {
		return UtilDAO.getInstance().getIcraMdwithID(AktifBean.icraDosyaID);
	}

	public void setIcraMd(String icraMd) {
		this.icraMd = icraMd;
	}

	public void dlgKaydet() throws Exception {
		Kaydet();
		RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
	}

	public void dlgVazgec() {
		Vazgec();
		RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
	}

	public void dlgPanelOpen() {
		PanelOpen();
		RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
	}

	public void dlgDuzenle() throws Exception {
		Duzenle();
		RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
	}

	public void dlgSil() throws Exception {
		Sil();
		RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
	}

	public void dlgYeniKayit() {
		YeniKayit();
		RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
	}
}
