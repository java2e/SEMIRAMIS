package semiramis.operasyon.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import pelops.controller.AktifBean;
import pelops.model.StaticDegerler;
import pelops.users.Util;
import semiramis.operasyon.dao.IzlemeBilgisiDAO;
import semiramis.operasyon.model.IzlemeBilgisi;

@ManagedBean(name = "izlemebilgisibean")
@ViewScoped
public class IzlemeBilgisiBean {

	ArrayList<IzlemeBilgisi> izlemeList = new ArrayList<IzlemeBilgisi>();
	IzlemeBilgisi izleme = new IzlemeBilgisi();
	IzlemeBilgisiDAO dao = new IzlemeBilgisiDAO();
	StaticDegerler staticDegerler = new StaticDegerler();
	
	private int status;
	boolean panelRender;
	boolean buttonDisabled;
	
	public IzlemeBilgisiBean() throws Exception {

		status = 0;
		PanelClose();
		ButtonOpen();
		izleme = new IzlemeBilgisi();
		izleme.setPersonelId(Util.getUser().getUsrId());
		izleme.setCagriAdet(dao.izlemeSayisi(AktifBean.icraDosyaID));
		izleme.setIzlemeTarihi(new Date());
		izleme.setPersonelId(Util.getUser().getUsrId());
		
		izlemeList = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);
		
	}
	
	
	

	public StaticDegerler getStaticDegerler() {
		staticDegerler.setBorcluAdi(AktifBean.borcluAdi);
		staticDegerler.setBorcluId(AktifBean.borcluId);
		staticDegerler.setIcraDosyaID(AktifBean.icraDosyaID);
		staticDegerler.setIcraDosyaNo(AktifBean.icraDosyaNo);
		staticDegerler.setMuvekkilAdi(AktifBean.muvekkilAdi);

		return staticDegerler;
	}
	
	
	
	

	public void setStaticDegerler(StaticDegerler staticDegerler) {
		this.staticDegerler = staticDegerler;
	}


	public ArrayList<IzlemeBilgisi> getIzlemeList()  {

		return this.izlemeList;

	}

	public void setIzlemeList(ArrayList<IzlemeBilgisi> izlemeList) {
		this.izlemeList = izlemeList;
	}

	public IzlemeBilgisi getIzleme() {
		return izleme;
	}

	public void setIzleme(IzlemeBilgisi izleme) {
		this.izleme = izleme;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	}

	public void YeniKayit() {

		izleme = new IzlemeBilgisi();
		status = 0;
		PanelOpen();

	}

	public void PanelOpen() {
		this.setPanelRender(true);
		
		izleme=new IzlemeBilgisi();
		izleme.setIzlemeTarihi(new Date());
		izleme.setCagriAdet(dao.izlemeSayisi(AktifBean.icraDosyaID));
		
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

					boolean result = dao.kaydet(izleme);

					if (result) {

						context.addMessage(null, new FacesMessage("Kaydedildi!"));
						
						izleme.setCagriAdet(izleme.getCagriAdet()+1);
						izlemeList = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);
						

					} else {

						context.addMessage(null, new FacesMessage("Kaydetme i�lemi ba�ar�s�z!"));

					}

					PanelClose();
					ButtonOpen();

				} else {

					izleme.setIcraDosyasiId(AktifBean.icraDosyaID);

					boolean duzenlendi = dao.guncelle(izleme);
					izlemeList = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);

					if (duzenlendi) {
						context.addMessage(null, new FacesMessage("G�ncellendi!"));
					} else {
						context.addMessage(null, new FacesMessage("G�ncelleme i�lemi ba�ar�s�z!"));
					}
					status = 0;

				}

				PanelClose();
				ButtonOpen();

			
		
		izleme=new IzlemeBilgisi();
		

	}

	public void Duzenle() throws Exception {

		status = 1;

		
		PanelOpen();
		
		ArrayList<IzlemeBilgisi> list = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);

		int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("buttonDuzenle").toString());

		for (IzlemeBilgisi hem : list) {
			if (hem.getId() == id) {
				izleme = hem;
			}
		}
		izlemeList = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);
		izleme.setCagriAdet(dao.izlemeSayisi(AktifBean.icraDosyaID));
		
		
		ButtonClose();

	}

	public void Sil() throws Exception {

		int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("buttonSil").toString());

		IzlemeBilgisiDAO dao = new IzlemeBilgisiDAO();

		boolean result = dao.sil(id);
		
		izlemeList = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);

		FacesContext context = FacesContext.getCurrentInstance();

		if (result) {

			context.addMessage(null, new FacesMessage("Silindi!"));

		} else {

			context.addMessage(null, new FacesMessage("Silme i�lemi ba�ar�s�z!"));

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
		RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
	}

}
