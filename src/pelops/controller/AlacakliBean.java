package pelops.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import pelops.dao.AlacakliDAO;
import pelops.db.DBConnection;
import pelops.model.AlacakliBilgiler;
import pelops.model.Ilce;
import pelops.model.StaticDegerler;

@ManagedBean(name = "alacakliBean")
@SessionScoped
public class AlacakliBean extends DBConnection {

	AlacakliBilgiler alacakliBilgisi = new AlacakliBilgiler();
	StaticDegerler degerler = new StaticDegerler();
	ArrayList<AlacakliBilgiler> tamListe = new ArrayList<AlacakliBilgiler>();
	AlacakliDAO dao = new AlacakliDAO();
	boolean panelRender;

	boolean buttonDisabled;
	@SuppressWarnings("unused")
	private String muvekkilAdi;
	
	

	public String getMuvekkilAdi() {

		return AktifBean.getMuvekkilAdi();
	}

	public ArrayList<AlacakliBilgiler> getTamListe() throws Exception {
		
		return dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);
	}

	public void setTamListe(ArrayList<AlacakliBilgiler> tamListe) {
		this.tamListe = tamListe;
	}

	public void setMuvekkilAdi(String muvekkilAdi) {
		this.muvekkilAdi = muvekkilAdi;
	}

	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getIcraDosyaNo() {
		return AktifBean.icraDosyaNo;
	}

	public String getBorcluAdi() {
		
		return AktifBean.borcluAdi;
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

	public AlacakliDAO getDao() {
		return dao;
	}

	public void setDao(AlacakliDAO dao) {
		this.dao = dao;
	}

	public void setButtonDisabled(boolean buttonDisabled) {
		this.buttonDisabled = buttonDisabled;
	}

	public StaticDegerler getDegerler() {

		return degerler;
	}

	public void setDegerler(StaticDegerler degerler) {

		this.degerler = degerler;
	}



	public void PanelOpen() {

		this.setPanelRender(true);
		ButtonClose();

	}

	public void PanelClose() {

		this.setPanelRender(true); // true ya çevrildi

	}

	public void ButtonOpen() {

		this.setButtonDisabled(false);

	}

	public void ButtonClose() {

		this.setButtonDisabled(true);

	}

	ArrayList<Ilce> ilceList;

	public AlacakliBean() {
	
	
		status = 0;
		PanelClose();
		ButtonOpen();

	}

	public AlacakliBilgiler getAlacakliBilgisi() {
		
		alacakliBilgisi.setIcraDosyasiNo(AktifBean.getIcraDosyaNo());
		alacakliBilgisi.setMuvekkilAdi(AktifBean.getMuvekkilAdi());
		return alacakliBilgisi;
	}

	public void setAlacakliBilgisi(AlacakliBilgiler alacakliBilgisi) {
		this.alacakliBilgisi = alacakliBilgisi;

	}

	public void ilceListeEkle() throws SQLException {

		ilceList = new ArrayList<Ilce>();
		GenelSehirlerBean gs = new GenelSehirlerBean();
		ilceList = gs.handleValueChange(alacakliBilgisi.getIlId());

	}

	public ArrayList<Ilce> getIlceList() {
		return ilceList;
	}

	public void setIlceList(ArrayList<Ilce> ilceList) {
		this.ilceList = ilceList;
	}

	public void Guncelle() throws Exception {

		FacesContext context = FacesContext.getCurrentInstance();

//		if (status == 0) {
//
//			AlacakliDAO dao = new AlacakliDAO();
//			boolean result = dao.Kaydet(alacakliBilgisi);
//			
//
//			if (result) {
//
//				context.addMessage(null, new FacesMessage("Kaydedildi!"));
//
//			} else {
//
//				context.addMessage(null, new FacesMessage("Kaydet i�lemi ba�ar�s�z!!!"));
//
//			}
//
//			PanelClose();
//			ButtonOpen();
//
//		} else {
             
			
			alacakliBilgisi.setIcraDosyaId(AktifBean.icraDosyaID);
			
			boolean duzenlendi = dao.guncelle(alacakliBilgisi);
			

			if (duzenlendi) {

				context.addMessage(null, new FacesMessage("G�ncellendi!"));

			} else {

				context.addMessage(null, new FacesMessage("G�ncelleme ��lemi Ba�ar�s�z!"));
			}

			status = 0;
			tamListe = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);
//		}

//		PanelClose();
//		ButtonOpen();

	}

	public ArrayList<AlacakliBilgiler> Liste() {

		ArrayList<AlacakliBilgiler> AlBilListesi = new ArrayList<AlacakliBilgiler>();
		AlacakliDAO dao = new AlacakliDAO();
		AlBilListesi = dao.ListeGetir();
		return AlBilListesi;

	}

	public ArrayList<AlacakliBilgiler> Liste(int id) {

		ArrayList<AlacakliBilgiler> AlBilListesi = new ArrayList<AlacakliBilgiler>();
		AlacakliDAO dao = new AlacakliDAO();
		AlBilListesi = dao.ListeGetir();
		return AlBilListesi;

	}

	public void Vazgec() {

		status = 0;
//		PanelClose();
//		ButtonOpen();

	}

	public void YeniKayit() {

	
		status = 0;
		alacakliBilgisi = new AlacakliBilgiler();
//		PanelOpen();

	}

	public void Duzenle() throws Exception {
		
	
		status = 1;

		ArrayList<AlacakliBilgiler> list = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);

		int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("buttonDuzenle").toString());

		
		
		for (AlacakliBilgiler hem : list) {
			if (hem.getId() == id) {
				alacakliBilgisi = hem;
			}
		}
		tamListe = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);
//		PanelOpen();
//		ButtonClose();


	}

	public void Sil() throws Exception {

		int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("buttonSil").toString());
		
		FacesContext context = FacesContext.getCurrentInstance();

		boolean result =dao.sil(id);
		
		if (result) {

			context.addMessage(null, new FacesMessage("Silindi!"));

		} else {

			context.addMessage(null, new FacesMessage("Silme ��lemi Ba�ar�s�z!"));
		}

		status = 0;
		tamListe = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);
		
		
		

	}
	
	
	public void dlgKaydet() throws Exception {
		Guncelle();
		RequestContext.getCurrentInstance().execute("PF('dlgAlacak').show()");
	}

	public void dlgVazgec() {
		Vazgec();
		RequestContext.getCurrentInstance().execute("PF('dlgAlacak').show()");
	}

	public void dlgPanelOpen() {
		PanelOpen();
		RequestContext.getCurrentInstance().execute("PF('dlgAlacak').show()");
	}

	public void dlgDuzenle() throws Exception {
		Duzenle();
		RequestContext.getCurrentInstance().execute("PF('dlgAlacak').show()");
	}

	public void dlgSil() throws Exception {
		Sil();
		RequestContext.getCurrentInstance().execute("PF('dlgAlacak').show()");
	}
	public void dlgYeniKayit() {
		YeniKayit();
		RequestContext.getCurrentInstance().execute("PF('dlgAlacak').show()");
	}


}
