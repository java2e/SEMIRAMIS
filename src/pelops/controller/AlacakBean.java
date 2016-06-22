package pelops.controller;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import pelops.dao.AlacakBilgisiDAO;
import pelops.dao.HesapDAO;
import pelops.db.DBConnection;
import pelops.model.*;

@ManagedBean(name = "alacakBean")
@SessionScoped
public class AlacakBean extends DBConnection {

	AlacakBilgisi alacakKayit = new AlacakBilgisi();
	private String icraDosyaNo;

	String isRequired = "false";

	public String getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(String isRequired) {
		this.isRequired = isRequired;
	}

	public void giveValueToRequired() {

		// System.out.println("metoda girdii");
		isRequired = "false";
	}

	/**
	 * :TODO Util class i yazıldığında AktifBean Kalıntıları düzeltlecek...
	 */
	String muvekkilAdi;
	String borcluAdi;
	private int status;
	AlacakBilgisiDAO dao = new AlacakBilgisiDAO();
	HesapDAO hesapDAO = new HesapDAO();
	boolean panelRender;

	// public void test(){
	//
	// RequestContext.getCurrentInstance().execute("PF('test').click()");
	//
	// }

	boolean buttonDisabled;
	ArrayList<AlacakBilgisi> alacakbilgisiarrayList = new ArrayList<AlacakBilgisi>();

	public ArrayList<AlacakBilgisi> getAlacakbilgisiarrayList() throws Exception {
		return dao.getAllListFromIcraDosyaID(AktifBean.getIcraDosyaID());
	}

	public void setAlacakbilgisiarrayList(ArrayList<AlacakBilgisi> alacakbilgisiarrayList) {
		this.alacakbilgisiarrayList = alacakbilgisiarrayList;
	}

	public AlacakBean() throws Exception {

		status = 0;
		PanelClose();
		ButtonOpen();

	}

	public String getIcraDosyaNo() {
		return AktifBean.icraDosyaNo;
	}

	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
	}

	public String getMuvekkilAdi() {
		return AktifBean.muvekkilAdi;
	}

	public void setMuvekkilAdi(String muvekkilAdi) {
		this.muvekkilAdi = muvekkilAdi;
	}

	public String getBorcluAdi() {
		return AktifBean.borcluAdi;
	}

	public void setBorcluAdi(String borcluAdi) {
		this.borcluAdi = borcluAdi;
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

	public AlacakBilgisi getAlacakKayit() {
		return alacakKayit;
	}

	public void setAlacakKayit(AlacakBilgisi alacakKayit) {
		this.alacakKayit = alacakKayit;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public AlacakBilgisiDAO getDao() {
		return dao;
	}

	public void setDao(AlacakBilgisiDAO dao) {
		this.dao = dao;
	}

	public void PanelOpen() {

		this.setPanelRender(true);
		ButtonClose();

	}

	public void PanelClose() {

		this.setPanelRender(false);
		ButtonOpen();

	}

	public void ButtonOpen() {

		this.setButtonDisabled(false);

	}

	public void ButtonClose() {

		this.setButtonDisabled(true);

	}

	public void YeniKayit() {

		status = 0;
		alacakKayit = new AlacakBilgisi();
		PanelOpen();

	}

	public void Vazgec() {

		System.out.println("vezgec girdi");

		status = 0;
		PanelClose();
		ButtonOpen();

	}

	public void Kaydet() throws Exception {

		FacesContext context = FacesContext.getCurrentInstance();

		if (alacakKayit.getBelge_miktari() == 0.0 && alacakKayit.getOdenen_miktar() == 0.0) {

			context.addMessage(null, new FacesMessage("Belge miktarı ve Ödenen miktar doldurulmalıdır!!!"));

		} else {

			if (status == 0) {

				AlacakBilgisiDAO dao = new AlacakBilgisiDAO();
				boolean result = dao.kaydet(alacakKayit);
				hesapDAO.guncelleAlacak(AktifBean.icraDosyaID, alacakKayit.getOdenen_miktar());

				if (result) {

					context.addMessage(null, new FacesMessage("Kaydedildi!"));

				} else {

					context.addMessage(null, new FacesMessage("Kaydet işlemi başarısız !!!"));

				}

				PanelClose();
				ButtonOpen();

			} else if (status == 1) {

				alacakKayit.setIcra_dosyasi_id(AktifBean.icraDosyaID);
				boolean duzenlendi = dao.guncelle(alacakKayit);

				if (duzenlendi) {

					context.addMessage(null, new FacesMessage("D�zenlendi!"));

				} else {

					context.addMessage(null, new FacesMessage("G�ncelleme ��lemi Ba�ar�s�z!"));
				}

				status = 0;

			}

			alacakbilgisiarrayList = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);
			PanelClose();
			ButtonOpen();

		}

	}

	public void Duzenle() throws Exception {

		int test = 0;
		status = 1;

		ArrayList<AlacakBilgisi> list = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);

		int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("buttonDuzenle").toString());

		for (AlacakBilgisi ab : list) {
			if (ab.getId() == id) {
				alacakKayit = ab;
				test = 100;
			}

		}

		PanelOpen();
		ButtonClose();
		alacakbilgisiarrayList = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);

	}

	public void Sil() throws Exception {

		boolean result;

		FacesContext context = FacesContext.getCurrentInstance();

		int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("buttonSil").toString());

		result = dao.sil(id);

		if (result) {
			context.addMessage(null, new FacesMessage("Kayıt Silindi"));

		} else {
			context.addMessage(null, new FacesMessage("Kayıt Silmesi Başarısız"));
		}

		alacakbilgisiarrayList = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);
		status = 0;

	}

	public void dlgKaydet() throws Exception {
		Kaydet();
		RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
	}

	public void dlgVazgec() {
		PanelClose();
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

	public void ConfirmMessage() {

	}

}
