package pelops.kasa.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.context.RequestContext;

import pelops.controller.AktifBean;
import pelops.controller.GenelTanimBean;
import pelops.dao.GelismisAramaDAO;
import pelops.kasa.model.Reddiyat;
import pelops.kasa.model.ReddiyatView;
import pelops.kasa.model.Tahsilat;
import pelops.kasa.model.TahsilatView;
import pelops.kasa.model.TahsilatViewModel;
import pelops.model.DetayliArama;
import pelops.model.GenelTanimSablon;
import pelops.model.Hesap;
import pelops.model.Kasa;
import pelops.util.Util;

@SessionScoped
@ManagedBean(name = "kasaBean")
public class KasaBean {

	private ArrayList<Kasa> kasaListesi = new ArrayList<Kasa>();
	private ArrayList<GenelTanimSablon> tahsilatStatuListesi = new ArrayList<GenelTanimSablon>();
	private ArrayList<GenelTanimSablon> odemeYeriListesi = new ArrayList<GenelTanimSablon>();
	private Kasa modelKasa = new Kasa();
	private ArrayList<DetayliArama> detayliAramaListesi = new ArrayList<DetayliArama>();
	private ArrayList<DetayliArama> filterDetayliAramaListesi;
	private Tahsilat bilgiTahsilat = new Tahsilat();
	private Date baslangicTarihi, bitisTarihi, olddatenew, enddatenew;
	private ArrayList<TahsilatViewModel> tahsilatYapilacakListe = new ArrayList<TahsilatViewModel>();
	private ArrayList<TahsilatViewModel> tahsilatiGecmisListe = new ArrayList<TahsilatViewModel>();
	
	private ArrayList<TahsilatView> tahsilatYapilmisListe = new ArrayList<TahsilatView>();
	
	private ArrayList<TahsilatView> tahsilatviewyapilacakListe = new ArrayList<TahsilatView>();
	private ArrayList<ReddiyatView> reddiyatListesi = new ArrayList<>();
	private ArrayList<ReddiyatView> reddiyatYapilmisListe = new ArrayList<>();
	
	private KasaCtrl controller = new KasaCtrl();
	private Reddiyat reddiyatBilgisi = new Reddiyat();
	
	
	
	



	public ArrayList<TahsilatViewModel> getTahsilatiGecmisListe() throws Exception {
		return controller.getListeFromViewsForTahsilatIslemi(olddatenew, enddatenew);
	}

	public void setTahsilatiGecmisListe(ArrayList<TahsilatViewModel> tahsilatiGecmisListe) {
		this.tahsilatiGecmisListe = tahsilatiGecmisListe;
	}

	public ArrayList<ReddiyatView> getReddiyatYapilmisListe() {
		return reddiyatYapilmisListe;
	}

	public void setReddiyatYapilmisListe(ArrayList<ReddiyatView> reddiyatYapilmisListe) {
		this.reddiyatYapilmisListe = reddiyatYapilmisListe;
	}

	public ArrayList<TahsilatView> getTahsilatYapilmisListe() {
		return tahsilatYapilmisListe;
	}

	public void setTahsilatYapilmisListe(ArrayList<TahsilatView> tahsilatYapilmisListe) {
		this.tahsilatYapilmisListe = tahsilatYapilmisListe;
	}

	public ArrayList<TahsilatView> getTahsilatviewyapilacakListe() {
		return tahsilatviewyapilacakListe;
	}

	public void setTahsilatviewyapilacakListe(ArrayList<TahsilatView> tahsilatviewyapilacakListe) {
		this.tahsilatviewyapilacakListe = tahsilatviewyapilacakListe;
	}

	public Reddiyat getReddiyatBilgisi() {
		return reddiyatBilgisi;
	}

	public void setReddiyatBilgisi(Reddiyat reddiyatBilgisi) {
		this.reddiyatBilgisi = reddiyatBilgisi;
	}

	public ArrayList<ReddiyatView> getReddiyatListesi() {
		return reddiyatListesi;
	}

	public void setReddiyatListesi(ArrayList<ReddiyatView> reddiyatListesi) {
		this.reddiyatListesi = reddiyatListesi;
	}

	public ArrayList<TahsilatViewModel> getTahsilatYapilacakListe() throws Exception {
	
		
		return controller.getListeFromViewsForTahsilatIslemi(baslangicTarihi, bitisTarihi);
	}

	public void setTahsilatYapilacakListe(ArrayList<TahsilatViewModel> tahsilatYapilacakListe) {
		this.tahsilatYapilacakListe = tahsilatYapilacakListe;
	}

	public KasaCtrl getController() {
		return controller;
	}

	public void setController(KasaCtrl controller) {
		this.controller = controller;
	}

	public Date getBaslangicTarihi() {
		return baslangicTarihi;
	}

	public void setBaslangicTarihi(Date baslangicTarihi) {
		this.baslangicTarihi = baslangicTarihi;
	}

	public Date getBitisTarihi() {
		return bitisTarihi;
	}

	public void setBitisTarihi(Date bitisTarihi) {
		this.bitisTarihi = bitisTarihi;
	}

	public Tahsilat getBilgiTahsilat() {
		return bilgiTahsilat;
	}

	public void setBilgiTahsilat(Tahsilat bilgiTahsilat) {
		this.bilgiTahsilat = bilgiTahsilat;
	}

	public ArrayList<DetayliArama> getFilterDetayliAramaListesi() {
		return filterDetayliAramaListesi;
	}

	public void setFilterDetayliAramaListesi(ArrayList<DetayliArama> filterDetayliAramaListesi) {
		this.filterDetayliAramaListesi = filterDetayliAramaListesi;
	}

	public ArrayList<DetayliArama> getDetayliAramaListesi() {
		return detayliAramaListesi;
	}

	public void setDetayliAramaListesi(ArrayList<DetayliArama> detayliAramaListesi) {
		this.filterDetayliAramaListesi = detayliAramaListesi;
		this.detayliAramaListesi = detayliAramaListesi;
	}

	public ArrayList<GenelTanimSablon> getOdemeYeriListesi() throws Exception {
		GenelTanimBean gtlist = new GenelTanimBean();
		return gtlist.ListeGetir("tbl_tahsilat_tipi");
	}

	public void setOdemeYeriListesi(ArrayList<GenelTanimSablon> odemeYeriListesi) {
		this.odemeYeriListesi = odemeYeriListesi;
	}

	public ArrayList<GenelTanimSablon> getTahsilatStatuListesi() throws Exception {
		GenelTanimBean liste = new GenelTanimBean();
		return liste.ListeGetir("tbl_tahsilat_statusu");
	}

	public void setTahsilatStatuListesi(ArrayList<GenelTanimSablon> tahsilatStatuListesi) {
		this.tahsilatStatuListesi = tahsilatStatuListesi;
	}

	public ArrayList<Kasa> getKasaListesi() {
		return kasaListesi;
	}

	public void setKasaListesi(ArrayList<Kasa> kasaListesi) {
		this.kasaListesi = kasaListesi;
	}

	public Kasa getModelKasa() {
		return modelKasa;
	}

	public void setModelKasa(Kasa modelKasa) {
		this.modelKasa = modelKasa;
	}

	@SuppressWarnings("deprecation")
	public KasaBean() throws Exception {

		String oldDate = "01/01/1900";
		Date tarih = new Date(oldDate);
		GelismisAramaDAO dao = new GelismisAramaDAO();
		detayliAramaListesi = dao.Listele("", "", "", "", "", "", 0, 0, 0, tarih, tarih, tarih, tarih, tarih, tarih);
		bilgiTahsilat = new Tahsilat();
		HttpSession session = Util.getSession();

		bilgiTahsilat.setKasa_islemini_yapan(session.getAttribute("user").toString());
		baslangicTarihi = new Date();
		Date tson = DateUtils.addMonths(new Date(), 1);
		bitisTarihi = tson;

		reddiyatListesi =  (ArrayList<ReddiyatView>) controller.getListefromView(0, 3, 1);
		reddiyatListesi.addAll(controller.getListefromView(0, 3, 2));
		reddiyatListesi.addAll(controller.getListefromView(0, 3, 3));

		reddiyatListesi= returnReddiyatview(reddiyatListesi);
		
		ViewDAO viewdao = new ViewDAO();
		tahsilatYapilmisListe = viewdao.getAllTahsilatFromView(0);
		
		reddiyatYapilmisListe =  (ArrayList<ReddiyatView>) controller.getListefromView(1, 3, 1);
		reddiyatYapilmisListe.addAll(controller.getListefromView(1, 3, 2));
		reddiyatYapilmisListe.addAll(controller.getListefromView(1, 3, 3));
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		String dateInString = "01-01-1900";
		Date date = sdf.parse(dateInString);
		
		olddatenew = date;
		enddatenew = baslangicTarihi;
		enddatenew.setHours(-24);
		
	}
	
	
	public void TahsilatAktar(int id) throws Exception{
	
	
	bilgiTahsilat.setBorclu_adi(AktifBean.getBorcluAdi());
	bilgiTahsilat.setIcra_dosya_no(AktifBean.getIcraDosyaNo());
	bilgiTahsilat.setIcra_dosyasi_id(AktifBean.getIcraDosyaID());
	bilgiTahsilat.setMuvekkil_adi(AktifBean.getMuvekkilAdi());
	bilgiTahsilat.setTahsilat_miktari(this.getTahsilatYapilacakListe().get(returnID(id)).getOdemeMiktari());
	HttpSession session = Util.getSession();

	bilgiTahsilat.setTasilati_yapan(session.getAttribute("user").toString());
	
	RequestContext.getCurrentInstance().execute("PF('frmtahsilatyap').show();");
	
	
	}
	
	public int returnID(int id) throws Exception{
		int rID=0;
		
		for (int i = 0; i < this.getTahsilatYapilacakListe().size(); i++) {
			if(this.getTahsilatYapilacakListe().get(i).getId()==id)
			{
				rID=i;
			}
			
		}
	
		return rID;
		
	}
	
	public int returnReddiyatID(int id){
		int rtID=0;
		for (int i = 0; i < reddiyatListesi.size(); i++) {
			if(reddiyatListesi.get(i).getId()==id){
				rtID=i;
			}
		}
		return rtID;
	}
	
	public void icraDosyaSec(int id) {

		RequestContext.getCurrentInstance().execute("PF('dlgdetayliarama').hide()");

		bilgiTahsilat.setBorclu_adi(AktifBean.getBorcluAdi());
		bilgiTahsilat.setIcra_dosya_no(AktifBean.getIcraDosyaNo());
		bilgiTahsilat.setIcra_dosyasi_id(AktifBean.getIcraDosyaID());
		bilgiTahsilat.setMuvekkil_adi(AktifBean.getMuvekkilAdi());
		HttpSession session = Util.getSession();

		bilgiTahsilat.setTasilati_yapan(session.getAttribute("user").toString());
	}

	public void ReddiyatAktar(int id){
		reddiyatBilgisi  = new Reddiyat();
		reddiyatListesi= returnReddiyatview(reddiyatListesi);
		reddiyatBilgisi = controller.createReddiyatFromReddiyatView(reddiyatListesi.get(returnReddiyatID(id)));
	
		RequestContext.getCurrentInstance().execute("PF('frmreddiyatyap').show();");
	}
	
	
	public ArrayList<ReddiyatView> returnReddiyatview(ArrayList<ReddiyatView> rw){
		ArrayList<ReddiyatView> returnRW = new ArrayList<>();
		returnRW = rw;
		
		for (int i = 0; i < rw.size(); i++) {
			if( rw.get(i).getDevletDurum()==0){
				returnRW.get(i).setAktifTutar(rw.get(i).getDevletReddiyatTuttar());
				returnRW.get(i).setReddiyatTuru("Devlete Reddiyat");
			}else if(rw.get(i).getMuvekkilDurum()==0){
				returnRW.get(i).setAktifTutar(rw.get(i).getMuvekkilReddiyatTutar());
				returnRW.get(i).setReddiyatTuru("Bankaya Reddiyat");
			}else if(rw.get(i).getSasaDurum()==0){
				returnRW.get(i).setAktifTutar(rw.get(i).getSasaReddiyatTutar());
				returnRW.get(i).setReddiyatTuru("Sasaya Reddiyat");
			}
			
			
		}
		return returnRW;
	}
	
	
	
	
	public void reddiyatYap() throws Exception{
		
		if(reddiyatBilgisi.getDevletDurum()==0) reddiyatBilgisi.setDevletDurum(1) ;
		if(reddiyatBilgisi.getSasaDurum()==0) reddiyatBilgisi.setSasaDurum(1);
		if(reddiyatBilgisi.getMuvekkilDurum()==0) reddiyatBilgisi.setMuvekkilDurum(1);
		controller.guncelle(reddiyatBilgisi);
		sayfaGuncelle();
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Reddiyat İşlemi Başarı İle Gerçekleştirildi..."));
	}
	
	
	
	

	public void tahsilatYap() throws Exception {
		Hesap hesap = AktifBean.hesaplistesi;
		ArrayList<Reddiyat> redList = new ArrayList<>();
		boolean hitam = false;
		if ((hesap.getKalan_alacak() - bilgiTahsilat.getTahsilat_miktari() <= 1)) {
			hitam = true;
			for (int i = 1; i < 4; i++) {
				Reddiyat reddiyat = controller.createReddiyatForStatus(i, hesap, bilgiTahsilat);
				redList.add(reddiyat);
			}
		} else {
			hitam = false;
		}
		controller.kaydet(bilgiTahsilat, hitam, redList);
		sayfaGuncelle();
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Tahsilat İşlemi Başarı İle Gerçekleştirildi..."));
	}

	@SuppressWarnings("unused")
	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {

		java.sql.Date sDate = new java.sql.Date(uDate.getTime());

		return sDate;

	}
	
	public void sayfaGuncelle() throws Exception{
		String oldDate = "01/01/1900";
		Date tarih = new Date(oldDate);
		GelismisAramaDAO dao = new GelismisAramaDAO();
		detayliAramaListesi = dao.Listele("", "", "", "", "", "", 0, 0, 0, tarih, tarih, tarih, tarih, tarih, tarih);
		bilgiTahsilat = new Tahsilat();
		HttpSession session = Util.getSession();

		bilgiTahsilat.setKasa_islemini_yapan(session.getAttribute("user").toString());
		baslangicTarihi = new Date();
		Date tson = DateUtils.addMonths(new Date(), 1);
		bitisTarihi = tson;

		reddiyatListesi =  (ArrayList<ReddiyatView>) controller.getListefromView(0, 3, 1);
		reddiyatListesi.addAll(controller.getListefromView(0, 3, 2));
		reddiyatListesi.addAll(controller.getListefromView(0, 3, 3));

		reddiyatListesi= returnReddiyatview(reddiyatListesi);
		
		ViewDAO viewdao = new ViewDAO();
		tahsilatYapilmisListe = viewdao.getAllTahsilatFromView(0);
		
		reddiyatYapilmisListe =  (ArrayList<ReddiyatView>) controller.getListefromView(1, 3, 1);
		reddiyatYapilmisListe.addAll(controller.getListefromView(1, 3, 2));
		reddiyatYapilmisListe.addAll(controller.getListefromView(1, 3, 3));
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		String dateInString = "01-01-1900";
		Date date = sdf.parse(dateInString);
		
		olddatenew = date;
		enddatenew = baslangicTarihi;
		enddatenew.setHours(-24);
		
		
	}
	
	

}
