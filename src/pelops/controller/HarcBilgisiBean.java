package pelops.controller;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import pelops.dao.HarcBilgisiDAO;
import pelops.model.HarcBilgisi;

@ManagedBean(name = "harcBean")
@SessionScoped
public class HarcBilgisiBean {

	public HarcBilgisiBean() {

		PanelClose();
		ButtonOpen();
	}

	private ArrayList<HarcBilgisi> harcBilgisiListesi = new ArrayList<HarcBilgisi>();
	private HarcBilgisi harcBilgi = new HarcBilgisi();
	private HarcBilgisiDAO dao = new HarcBilgisiDAO();

	@SuppressWarnings("unused")
	private String icraDosyaNo;
	@SuppressWarnings("unused")
	private String borcluAdi;

	@SuppressWarnings("unused")
	private String muvekkilAdi;

	private boolean panelRender;

	private boolean buttonDisabled;

	private int status = 0;

	@SuppressWarnings("unused")
	private ArrayList<HarcBilgisi> harcList = new ArrayList<HarcBilgisi>();

	public ArrayList<HarcBilgisi> getHarcBilgisiListesi() {
		return harcBilgisiListesi;
	}

	public void setHarcBilgisiListesi(ArrayList<HarcBilgisi> harcBilgisiListesi) {
		this.harcBilgisiListesi = harcBilgisiListesi;
	}

	public HarcBilgisi getHarcBilgi() {
		return harcBilgi;
	}

	public void setHarcBilgi(HarcBilgisi harcBilgi) {
		this.harcBilgi = harcBilgi;
	}

	public String getIcraDosyaNo() {
		return AktifBean.icraDosyaNo;
	}

	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
	}

	public String getBorcluAdi() {
		return AktifBean.borcluAdi;
	}

	public void setBorcluAdi(String borcluAdi) {
		this.borcluAdi = borcluAdi;
	}

	public String getMuvekkilAdi() {
		return AktifBean.muvekkilAdi;
	}

	public void setMuvekkilAdi(String muvekkilAdi) {
		this.muvekkilAdi = muvekkilAdi;
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

	public ArrayList<HarcBilgisi> getHarcList() throws Exception {
		return dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);
	}

	public void setHarcList(ArrayList<HarcBilgisi> harcList) {
		this.harcList = harcList;
	}

	public void YeniKayit() {
		status = 0;
		harcBilgi = new HarcBilgisi();
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

	public void Kaydet() throws Exception {
		
		FacesContext context = FacesContext.getCurrentInstance();

		if (status == 0) {

			boolean result = dao.kaydet(harcBilgi);
		

			if (result) {
				// Pop up a��lmas�n� sa�lar
				context.addMessage(null, new FacesMessage("Kaydedildi!"));

			} else {

				context.addMessage(null, new FacesMessage("Kaydet i�lemi ba�ar�s�z!"));

			}
		} else {
			boolean duzenlendi = dao.guncelle(harcBilgi);
		

			if (duzenlendi) {
				context.addMessage(null, new FacesMessage("Düzenlendi!"));
			} else {
				context.addMessage(null, new FacesMessage("Güncelleme işlemi başarısız!"));
			}
			status = 0;
		}

		harcList = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);

		PanelClose();
		ButtonOpen();

	}

	public void Duzenle() throws Exception {

		status = 1;

		ArrayList<HarcBilgisi> list = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);

		int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("duzenle").toString());

		for (HarcBilgisi hem : list) {
			if (hem.getId() == id) {
				harcBilgi = hem;
			}
		}
		harcList = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);
		PanelOpen();
		ButtonClose();

	}

	public void Sil() throws Exception {

		FacesContext context = FacesContext.getCurrentInstance();

		int id = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sil").toString());

		int result = dao.Sil(id);

		if (result==1) {

			context.addMessage(null, new FacesMessage("Silindi!"));

		} else {
			context.addMessage(null, new FacesMessage("Silme Başarısız!"));
		}

	}

	public void Vazgec() {
		status = 0;
		PanelClose();
		ButtonOpen();

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
		System.out.println("yeni kayıtta");
		RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
	}

}
