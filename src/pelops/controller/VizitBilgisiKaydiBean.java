package pelops.controller;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import pelops.dao.VizitBilgisiDAO;
import pelops.model.User;
import pelops.model.VizitBilgisi;
import pelops.model.VizitStatusu;

@ManagedBean(name = "vizitBilgisiKaydiBean")
@SessionScoped
public class VizitBilgisiKaydiBean {

	public VizitBilgisiKaydiBean() {
		// TODO Auto-generated constructor stub
		PanelClose();
		ButtonOpen();
	}

	private int status = 0;

	ArrayList<VizitBilgisi> listVizit = new ArrayList<VizitBilgisi>();

	VizitBilgisiDAO dao = new VizitBilgisiDAO();

	ArrayList<VizitStatusu> vizitStatuleri = new ArrayList<VizitStatusu>();

	VizitBilgisi vizitBilgisi = new VizitBilgisi();

	ArrayList<User> users = new ArrayList<User>();

	String icradosyaNo;

	String borcluAdi;

	boolean panelRender;

	boolean buttonDisabled;

	public String getIcradosyaNo() {
		return AktifBean.icraDosyaNo;
	}

	public void setIcradosyaNo(String icradosyaNo) {
		this.icradosyaNo = icradosyaNo;
	}

	public ArrayList<VizitStatusu> getVizitStatuleri() throws Exception {
		return dao.getAllVizitStatusu();
	}

	public void setVizitStatuleri(ArrayList<VizitStatusu> vizitStatuleri) {
		this.vizitStatuleri = vizitStatuleri;
	}

	public VizitBilgisi getVizitBilgisi() {
		return vizitBilgisi;
	}

	public void setVizitBilgisi(VizitBilgisi vizitBilgisi) {
		this.vizitBilgisi = vizitBilgisi;
	}

	public ArrayList<User> getUsers() throws Exception {
		return dao.getAllUsers();
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public String getBorcluAdi() {
		return AktifBean.borcluAdi;
	}

	public void setBorcluAdi(String borcluAdi) {
		this.borcluAdi = borcluAdi;
	}

	public ArrayList<VizitBilgisi> getListVizit() throws Exception {
		return dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);
	}

	public void setListVizit(ArrayList<VizitBilgisi> listVizit) {
		this.listVizit = listVizit;
	}

	public void Kaydet() throws Exception {
		// burada aktifBeanden icra dosyası idyi set edilecek...

		if (status == 0) {
			FacesContext context = FacesContext.getCurrentInstance();

			vizitBilgisi.setIcraDosyaID(AktifBean.getIcraDosyaID());
			if (vizitBilgisi.getOdemeMiktari() != Double.valueOf(0)
					&& !vizitBilgisi.getVizitStatusu().equals("")
					&& vizitBilgisi.getVizitPersoneliId() != 0
					&& !vizitBilgisi.getVizitNotu().equals("")) {
				
				boolean kaydedildi = dao.vizitBilgisiKaydet(vizitBilgisi);
				if (kaydedildi) {
					// Pop Up Çıkacak... Kaydedildiğine dair.
					context.addMessage(null, new FacesMessage("Kaydedildi!"));

				} else {
					context.addMessage(null, new FacesMessage(
							"Kaydet işlemi başarısız!"));

				}
				PanelClose();
				ButtonOpen();
			} else {
				context.addMessage(null, new FacesMessage(
						"Eksik bilgi doldurdunuz!"));
			}

			vizitBilgisi = new VizitBilgisi();

		} else {
			vizitBilgisi.setIcraDosyaID(AktifBean.getIcraDosyaID());
			boolean duzenlendi = dao.guncelle(vizitBilgisi);
			System.out.println(duzenlendi);
			FacesContext context = FacesContext.getCurrentInstance();

			if (duzenlendi) {
				context.addMessage(null, new FacesMessage("Düzenlendi!"));
				PanelClose();
				ButtonOpen();
			} else {
				context.addMessage(null, new FacesMessage(
						"Güncelleme işlemi başarısız!"));
			}
			status = 0;
			listVizit = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);
			
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

		ArrayList<VizitBilgisi> list = dao
				.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);

		int id = Integer.parseInt(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("duzenle")
				.toString());

		for (VizitBilgisi vb : list) {
			if (vb.getId() == id) {
				vizitBilgisi = vb;
			}
		}
		listVizit = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);
		PanelOpen();
		ButtonClose();

	}

	public void YeniKayit() {
		vizitBilgisi = new VizitBilgisi();
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
		int id = Integer.parseInt(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("sil")
				.toString());

		dao.Sil(id);

	}

	public void dlgKaydet() throws Exception {
		Kaydet();
		RequestContext.getCurrentInstance().execute("PF('dlgMasraf').show()");
	}

	public void dlgVazgec() {
		Vazgec();
		RequestContext.getCurrentInstance().execute("PF('dlgVizit').show()");
	}

	public void dlgPanelOpen() {
		PanelOpen();
		RequestContext.getCurrentInstance().execute("PF('dlgVizit').show()");
	}

	public void dlgDuzenle() throws Exception {
		Duzenle();
		RequestContext.getCurrentInstance().execute("PF('dlgVizit').show()");
	}

	public void dlgSil() throws Exception {
		Sil();
		RequestContext.getCurrentInstance().execute("PF('dlgVizit').show()");
	}

	public void dlgYeniKayit() {
		YeniKayit();
		RequestContext.getCurrentInstance().execute("PF('dlgVizit').show()");
	}
}
