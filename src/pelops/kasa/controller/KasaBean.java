package pelops.kasa.controller;

import java.text.NumberFormat;
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
import pelops.users.User;
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
	
	Date tarih_rapor = new Date();
	
	private double hsbc_aylik, akbank_aylik, garanti_aylik, ing_aylik;
	private double hsbc_gunluk, akbank_gunluk, garanti_gunluk, ing_gunluk;
	
	private String hsbc_aylik1, akbank_aylik1, garanti_aylik1, ing_aylik1;
	private String hsbc_gunluk1, akbank_gunluk1, garanti_gunluk1, ing_gunluk1;
	
	
	private String Ay, gun;
	
	



	public String getHsbc_aylik1() {
		return hsbc_aylik1;
	}

	public void setHsbc_aylik1(String hsbc_aylik1) {
		this.hsbc_aylik1 = hsbc_aylik1;
	}

	public String getAkbank_aylik1() {
		return akbank_aylik1;
	}

	public void setAkbank_aylik1(String akbank_aylik1) {
		this.akbank_aylik1 = akbank_aylik1;
	}

	public String getGaranti_aylik1() {
		return garanti_aylik1;
	}

	public void setGaranti_aylik1(String garanti_aylik1) {
		this.garanti_aylik1 = garanti_aylik1;
	}

	public String getIng_aylik1() {
		return ing_aylik1;
	}

	public void setIng_aylik1(String ing_aylik1) {
		this.ing_aylik1 = ing_aylik1;
	}

	public String getHsbc_gunluk1() {
		return hsbc_gunluk1;
	}

	public void setHsbc_gunluk1(String hsbc_gunluk1) {
		this.hsbc_gunluk1 = hsbc_gunluk1;
	}

	public String getAkbank_gunluk1() {
		return akbank_gunluk1;
	}

	public void setAkbank_gunluk1(String akbank_gunluk1) {
		this.akbank_gunluk1 = akbank_gunluk1;
	}

	public String getGaranti_gunluk1() {
		return garanti_gunluk1;
	}

	public void setGaranti_gunluk1(String garanti_gunluk1) {
		this.garanti_gunluk1 = garanti_gunluk1;
	}

	public String getIng_gunluk1() {
		return ing_gunluk1;
	}

	public void setIng_gunluk1(String ing_gunluk1) {
		this.ing_gunluk1 = ing_gunluk1;
	}

	public String getAy() {
		return Ay;
	}

	public void setAy(String ay) {
		Ay = ay;
	}

	public String getGun() {
		return gun;
	}

	public void setGun(String gun) {
		this.gun = gun;
	}

	public Date getOlddatenew() {
		return olddatenew;
	}

	public void setOlddatenew(Date olddatenew) {
		this.olddatenew = olddatenew;
	}

	public Date getEnddatenew() {
		return enddatenew;
	}

	public void setEnddatenew(Date enddatenew) {
		this.enddatenew = enddatenew;
	}

	public Date getTarih() {
		return tarih_rapor;
	}

	public void setTarih(Date tarih) {
		this.tarih_rapor = tarih;
	}

	

	public Date getTarih_rapor() {
		return tarih_rapor;
	}

	public void setTarih_rapor(Date tarih_rapor) {
		this.tarih_rapor = tarih_rapor;
	}

	public double getHsbc_aylik() {
		return hsbc_aylik;
	}

	public void setHsbc_aylik(double hsbc_aylik) {
		this.hsbc_aylik = hsbc_aylik;
	}

	public double getAkbank_aylik() {
		return akbank_aylik;
	}

	public void setAkbank_aylik(double akbank_aylik) {
		this.akbank_aylik = akbank_aylik;
	}

	public double getGaranti_aylik() {
		return garanti_aylik;
	}

	public void setGaranti_aylik(double garanti_aylik) {
		this.garanti_aylik = garanti_aylik;
	}

	public double getIng_aylik() {
		return ing_aylik;
	}

	public void setIng_aylik(double ing_aylik) {
		this.ing_aylik = ing_aylik;
	}

	public double getHsbc_gunluk() {
		return hsbc_gunluk;
	}

	public void setHsbc_gunluk(double hsbc_gunluk) {
		this.hsbc_gunluk = hsbc_gunluk;
	}

	public double getAkbank_gunluk() {
		return akbank_gunluk;
	}

	public void setAkbank_gunluk(double akbank_gunluk) {
		this.akbank_gunluk = akbank_gunluk;
	}

	public double getGaranti_gunluk() {
		return garanti_gunluk;
	}

	public void setGaranti_gunluk(double garanti_gunluk) {
		this.garanti_gunluk = garanti_gunluk;
	}

	public double getIng_gunluk() {
		return ing_gunluk;
	}

	public void setIng_gunluk(double ing_gunluk) {
		this.ing_gunluk = ing_gunluk;
	}

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

		bilgiTahsilat.setKasa_islemini_yapan(((User)session.getAttribute("user")).getUsrAdSoyad());
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
		sayfaGuncelle();
	}
	
	
	public void TahsilatAktar(int id) throws Exception{
	
	
	bilgiTahsilat.setBorclu_adi(AktifBean.getBorcluAdi());
	bilgiTahsilat.setIcra_dosya_no(AktifBean.getIcraDosyaNo());
	bilgiTahsilat.setIcra_dosyasi_id(AktifBean.getIcraDosyaID());
	bilgiTahsilat.setMuvekkil_adi(AktifBean.getMuvekkilAdi());
	bilgiTahsilat.setTahsilat_miktari(this.getTahsilatYapilacakListe().get(returnID(id)).getOdemeMiktari());
System.out.println(id);
	HttpSession session = Util.getSession();

	bilgiTahsilat.setTasilati_yapan(((User) session.getAttribute("user")).getUsrAdSoyad());
	
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
				returnRW.get(i).setAktifTutarTL(rw.get(i).getDevletReddiyatTuttarTL());
				returnRW.get(i).setReddiyatTuru("Devlete Reddiyat");
			}else if(rw.get(i).getMuvekkilDurum()==0){
				returnRW.get(i).setAktifTutarTL(rw.get(i).getMuvekkilReddiyatTutarTL());
				returnRW.get(i).setReddiyatTuru("Bankaya Reddiyat");
			}else if(rw.get(i).getSasaDurum()==0){
				returnRW.get(i).setAktifTutarTL(rw.get(i).getSasaReddiyatTutarTL());
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

		bilgiTahsilat.setKasa_islemini_yapan(((User) session.getAttribute("user")).getUsrAdSoyad());
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
		
		Ay ="2016 - HAZİRAN";
		gun = tarih_rapor.toString();
		
		
		for (int i = 0; i < tahsilatYapilmisListe.size(); i++) {
			
			if(tahsilatYapilmisListe.get(i).getMuvekkilAdi().equals("HSBC BANK A.Ş.")==true)
				hsbc_aylik += tahsilatYapilmisListe.get(i).getTahsilatMiktari();
			if(tahsilatYapilmisListe.get(i).getMuvekkilAdi().equals("AKBANK T.A.Ş.")==true)
				akbank_aylik += tahsilatYapilmisListe.get(i).getTahsilatMiktari();
			if(tahsilatYapilmisListe.get(i).getMuvekkilAdi().equals("T. GARANTİ BANKASI A.Ş.")==true)
				garanti_aylik += tahsilatYapilmisListe.get(i).getTahsilatMiktari();
			if(tahsilatYapilmisListe.get(i).getMuvekkilAdi().equals("İNG BANK A.Ş.")==true)
				ing_aylik += tahsilatYapilmisListe.get(i).getTahsilatMiktari();
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			
			if(tahsilatYapilmisListe.get(i).getTahsilatTarihi()==tarih_rapor){
				if(tahsilatYapilmisListe.get(i).getMuvekkilAdi().equals("HSBC BANK A.Ş.")==true)
					hsbc_gunluk += tahsilatYapilmisListe.get(i).getTahsilatMiktari();
				if(tahsilatYapilmisListe.get(i).getMuvekkilAdi().equals("AKBANK T.A.Ş.")==true)
					akbank_gunluk += tahsilatYapilmisListe.get(i).getTahsilatMiktari();
				if(tahsilatYapilmisListe.get(i).getMuvekkilAdi().equals("T. GARANTİ BANKASI A.Ş.")==true)
					garanti_gunluk += tahsilatYapilmisListe.get(i).getTahsilatMiktari();
				if(tahsilatYapilmisListe.get(i).getMuvekkilAdi().equals("İNG BANK A.Ş.")==true)
					ing_gunluk += tahsilatYapilmisListe.get(i).getTahsilatMiktari();
					
			}
		}
		
		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		
		hsbc_aylik1 = defaultFormat.format(hsbc_aylik);
		akbank_aylik1 = defaultFormat.format(akbank_aylik);
		garanti_aylik1 = defaultFormat.format(garanti_aylik);
		ing_aylik1 = defaultFormat.format(ing_aylik);
		
		hsbc_gunluk1 = defaultFormat.format(hsbc_aylik);
		akbank_gunluk1 = defaultFormat.format(akbank_gunluk);
		garanti_gunluk1= defaultFormat.format(garanti_gunluk);
		ing_gunluk1 = defaultFormat.format(ing_gunluk);
		
				
		
		
		
	}
	
	public void print() throws Exception{
		KasaCtrl islem = new KasaCtrl();
		islem.printTahsilatMakbuzu(10);
		System.out.println("burda");
		
	}
	
	
	

}
