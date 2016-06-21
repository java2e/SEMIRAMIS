package pelops.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import pelops.dao.OdemePlaniDAO;
import pelops.model.OdemePlani;
import pelops.model.StaticDegerler;

@ManagedBean(name = "odemePlaniBean")
@SessionScoped

public class OdemePlaniBean {

	private int status;
	boolean panelRender;
	boolean buttonDisabled;
	private int odemeTemp;

	OdemePlani plan = new OdemePlani();

	ArrayList<OdemePlani> odemePlaniList = new ArrayList<OdemePlani>();

	StaticDegerler staticDegerler = new StaticDegerler();

	OdemePlaniDAO dao = new OdemePlaniDAO();

	public OdemePlaniBean() throws Exception {

		getKalanAlacakMiktari();

		status = 0;
		odemeTemp = 0;

		PanelClose();
		ButtonOpen();
		// getSonList();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getOdemeTemp() {
		return odemeTemp;
	}

	public void setOdemeTemp(int odemeTemp) {
		this.odemeTemp = odemeTemp;
	}

	public OdemePlaniDAO getDao() {
		return dao;
	}

	public void setDao(OdemePlaniDAO dao) {
		this.dao = dao;
	}

	public void getSonList() throws Exception {

		odemePlaniList = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);

	}

	public ArrayList<OdemePlani> getOdemePlaniList() throws Exception {

		return odemePlaniList;

	}

	public void setOdemePlaniList(ArrayList<OdemePlani> odemePlaniList) {

		this.odemePlaniList = odemePlaniList;
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

	public OdemePlani getPlan() {
		return plan;
	}

	public void setPlan(OdemePlani plan) {
		this.plan = plan;
	}

	public Double doubleYap(String sttutar){
		Double tutar = 0.0;
		if(sttutar==null || sttutar.equals("")){}
		else
		{
			int nokta = sttutar.indexOf(",");
			if(nokta<0) nokta = sttutar.indexOf(".");
			
		
		String tam = sttutar.substring(0, nokta);
		String kesir = sttutar.substring(nokta+1, sttutar.length());
		String sayi = tam + "." + kesir;
		tutar = Double.parseDouble(sayi); 
		}
		return tutar;
	}
	
	public void getKalanAlacakMiktari() throws Exception {

		FacesContext context = FacesContext.getCurrentInstance();
		DecimalFormat format = new DecimalFormat("0.00");
			
		plan.setKalanAlacakMiktari(doubleYap(format.format(dao.getKalanAlacak(AktifBean.getIcraDosyaID()))));

		if (plan.getKalanAlacakMiktari() == 0.0) {

			context.addMessage(null, new FacesMessage("Kalan alacak miktarı otomatik olarak gelmedi"));

		}

	}

	public void OdemeOlustur() throws Exception {

		FacesContext context = FacesContext.getCurrentInstance();

		if (plan.getKalanAlacakMiktari() == 0.0 && plan.getTaksitAdedi() == 0 && plan.getIlkOdemeTarihi() == null) {

			context.addMessage(null,
					new FacesMessage("Kalan Alacak Miktarı, taksit adedi ve ilk ödeme tarihi dolu olmalıdır!!!"));

		} else {

			status = 0;
			odemeTemp = 1;

			OdemePlaniDAO dao = new OdemePlaniDAO();
			ButtonClose();
			odemePlaniList = dao.odemeolustur(plan);

		}

	}

	public void YeniKayit() throws Exception {

		status = 0;
		plan = new OdemePlani();
		getKalanAlacakMiktari();
		PanelOpen();

	}

	public void Kaydet() throws Exception {

		FacesContext context = FacesContext.getCurrentInstance();

		if (plan.getKalanAlacakMiktari() == 0.0 && plan.getTaksitAdedi() == 0 && plan.getIlkOdemeTarihi() == null) {

			context.addMessage(null, new FacesMessage("Nihai kayıt için ödeme planı oluşturunuz"));

		} else {

			if (status == 0 && odemeTemp == 1) {

				dao = new OdemePlaniDAO();
				int sonuc = dao.kaydet(odemePlaniList, plan);

				if (sonuc == 1) {
					context.addMessage(null, new FacesMessage("Kaydedildi"));
				} else {

					context.addMessage(null, new FacesMessage("Kayıt başarısız"));

				}

				PanelClose();
				ButtonOpen();
				// getSonList();

			} else if (status == 1) {

				plan.setIcraDosyID(AktifBean.icraDosyaID);
				int sonuc = dao.guncelle(plan);

				if (sonuc == 1) {

					context.addMessage(null, new FacesMessage("Güncelleme yapıldı."));

					status = 0;
					getSonList();

				} else {

					context.addMessage(null, new FacesMessage("Güncelleme başarısız."));

				}

			} else {

				getSonList();

			}

			PanelClose();

			ButtonOpen();

		}

	}

	public void Duzenle() throws Exception {


		status = 1;

		ArrayList<OdemePlani> list = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);

		int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("buttonDuzenle").toString());

		for (OdemePlani op : list) {

			if (op.getId() == id) {

				plan = op;

			}
		}

		odemePlaniList = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);
		PanelOpen();
		ButtonClose();

	}

	public void Sil() throws Exception {

		FacesContext context = FacesContext.getCurrentInstance();

		int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("buttonSil").toString());
		int result = dao.sil(id);

		if (result == 1) {

			context.addMessage(null, new FacesMessage("Silindi"));

		} else {

			context.addMessage(null, new FacesMessage("Silme başarısız"));

		}

		getSonList();

	}

	public void Vazgec() {

		status = 0;
		PanelClose();
		ButtonOpen();

	}

}
