package pelops.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import pelops.dao.IzlemeBilgisiDAO;
import pelops.model.IzlemeBilgisiKaydi;
import pelops.model.StaticDegerler;

@ManagedBean(name = "izlemebilgisibean")
@SessionScoped
public class IzlemeBilgisiBean {

	ArrayList<IzlemeBilgisiKaydi> izlemeList = new ArrayList<IzlemeBilgisiKaydi>();
	IzlemeBilgisiKaydi izleme = new IzlemeBilgisiKaydi();
	IzlemeBilgisiDAO dao = new IzlemeBilgisiDAO();
	StaticDegerler staticDegerler = new StaticDegerler();

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

	private int status;
	boolean panelRender;
	boolean buttonDisabled;

	public ArrayList<IzlemeBilgisiKaydi> getIzlemeList() throws Exception {

		izlemeList = dao.getAllListFromIcraDosyaID(AktifBean.getIcraDosyaID());

		return izlemeList;

	}

	public void setIzlemeList(ArrayList<IzlemeBilgisiKaydi> izlemeList) {
		this.izlemeList = izlemeList;
	}

	public IzlemeBilgisiKaydi getIzleme() {
		return izleme;
	}

	public void setIzleme(IzlemeBilgisiKaydi izleme) {
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

	public IzlemeBilgisiBean() {

		status = 0;
		PanelClose();
		ButtonOpen();
		izleme = new IzlemeBilgisiKaydi();
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	}

	public void YeniKayit() {

		izleme = new IzlemeBilgisiKaydi();
		status = 0;
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

		if (izleme.getIzlemeSonucuTarihi() == null && izleme.getIzlemeSonucu() == null
				&& izleme.getOdemeSozuTarihi() == null && izleme.getOdemeSozuMiktari() == null
				&& izleme.getAciklama() == "" && izleme.getPersonelId() == 0 && izleme.getIzlemeTarihi() == null
				&& izleme.getIzlemeSonucuId() == 0) {

			context.addMessage(null, new FacesMessage("En az bir alan dolu olmalıdır!"));

		} else {

			if (izleme.getOdemeSozuMiktari() == null || izleme.getOdemeSozuTarihi() == null) {

				context.addMessage(null,
						new FacesMessage("Ödeme Sözü miktarı veya ödeme sözü tarihi ikisi de dolu olmalıdır!"));

			} else {

				if (status == 0) {

					boolean result = dao.kaydet(izleme);

					if (result) {

						context.addMessage(null, new FacesMessage("Kaydedildi!"));

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

			}
		}

	}

	public void Duzenle() throws Exception {

		status = 1;

		ArrayList<IzlemeBilgisiKaydi> list = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);

		int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("buttonDuzenle").toString());

		for (IzlemeBilgisiKaydi hem : list) {
			if (hem.getId() == id) {
				izleme = hem;
			}
		}
		izlemeList = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);
		PanelOpen();
		ButtonClose();

	}

	public void Sil() {

		int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("buttonSil").toString());

		IzlemeBilgisiDAO dao = new IzlemeBilgisiDAO();

		boolean result = dao.sil(id);

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
