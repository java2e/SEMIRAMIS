package pelops.kasa.controller;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pelops.controller.AktifBean;
import pelops.dao.IcraDosyasiDAO;
import pelops.kasa.model.Tahsilat;

@ManagedBean(name="tahsilatBean")
@SessionScoped
public class TahsilatBean {

	private ArrayList<Tahsilat> tahsilatListesi = new ArrayList<Tahsilat>() ;
	private Tahsilat tahsilat ;
	private boolean panelRender;
	private boolean buttonDisabled;
	private int status = 0;
	
	public ArrayList<Tahsilat> getTahsilatListesi() {
		return tahsilatListesi;
	}

	public void setTahsilatListesi(ArrayList<Tahsilat> tahsilatListesi) {
		this.tahsilatListesi = tahsilatListesi;
	}

	public Tahsilat getTahsilat() {
		return tahsilat;
	}

	public void setTahsilat(Tahsilat tahsilat) {
		this.tahsilat = tahsilat;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public TahsilatBean() throws Exception{
		
	
		TahsilatDAO dao = new TahsilatDAO();
	
		tahsilatListesi = dao.ListeleID(AktifBean.getIcraDosyaID()); 
		tahsilat = new Tahsilat();
		tahsilat.setBorclu_adi(AktifBean.getBorcluAdi());
		tahsilat.setIcra_dosya_no(AktifBean.getIcraDosyaNo());
		tahsilat.setIcra_dosyasi_id(AktifBean.getIcraDosyaID());
		tahsilat.setMuvekkil_adi(AktifBean.getMuvekkilAdi());
		
	
	}
	
	public void Kaydet() throws Exception{
		
		IcraDosyasiDAO icradao = new IcraDosyasiDAO();
			
		tahsilat.setGelis_tarihi(icradao.Listele(tahsilat.getIcra_dosyasi_id()).getGelisTarihi());

		TahsilatDAO dao = new TahsilatDAO();
		dao.Kaydet(tahsilat);
		Listele();
		PanelClose();
		ButtonOpen();	
	}
	
	public void Duzenle() throws Exception{
		TahsilatDAO dao = new TahsilatDAO();
		dao.Guncelle(tahsilat);
		Listele();
		PanelClose();
		ButtonOpen();
	}
	
	public void Sil() throws Exception{
		TahsilatDAO dao = new TahsilatDAO();
		int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("sil").toString());
		dao.Sil(id,dao.ListeleTahsilat(id).getTahsilat_miktari());
	Listele();	
	}
	
	public void ListeleTumu() throws Exception{
		TahsilatDAO dao = new TahsilatDAO();
		tahsilatListesi = dao.ListeleTum();
		
	}
	
	public void Listele() throws Exception{
		TahsilatDAO dao = new TahsilatDAO();
		tahsilatListesi = dao.ListeleID(AktifBean.getIcraDosyaID());
		
	}
	
	
	public void YeniKayit() {

		status = 0;
		tahsilat = new Tahsilat();
		tahsilat.setBorclu_adi(AktifBean.getBorcluAdi());
		tahsilat.setIcra_dosya_no(AktifBean.getIcraDosyaNo());
		tahsilat.setIcra_dosyasi_id(AktifBean.getIcraDosyaID());
		tahsilat.setMuvekkil_adi(AktifBean.getMuvekkilAdi());
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
	
	public void Vazgec() {
		status = 0;
		PanelClose();
		ButtonOpen();

	}
	
	
}
