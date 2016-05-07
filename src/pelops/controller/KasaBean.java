package pelops.controller;

import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.context.RequestContext;

import pelops.dao.GelismisAramaDAO;
import pelops.kasa.model.Tahsilat;
import pelops.model.DetayliArama;
import pelops.model.GenelTanimSablon;
import pelops.model.Kasa;
import pelops.util.Util;

@SessionScoped
@ManagedBean(name="kasaBean")
public class KasaBean {

	private ArrayList<Kasa> kasaListesi = new ArrayList<Kasa>();
	private ArrayList<GenelTanimSablon> tahsilatStatuListesi = new ArrayList<GenelTanimSablon>();
	private ArrayList<GenelTanimSablon> odemeYeriListesi = new ArrayList<GenelTanimSablon>();
	private Kasa modelKasa = new Kasa();
	private ArrayList<DetayliArama> detayliAramaListesi = new  ArrayList<DetayliArama>();
	private ArrayList<DetayliArama> filterDetayliAramaListesi;
	private Tahsilat bilgiTahsilat = new Tahsilat();
	private Date baslangicTarihi, bitisTarihi;
	
	
	
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
	public void setFilterDetayliAramaListesi(
			ArrayList<DetayliArama> filterDetayliAramaListesi) {
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
	
	public KasaBean() throws Exception{
		
		for (int i = 0; i < 16; i++) {
		modelKasa = new Kasa();
		modelKasa.setBorcluAdi("Muhammet Ali KAYA");
		modelKasa.setIcraDosyaNo("2015/10");
		String string = "15/03/2016";
		
		@SuppressWarnings("deprecation")
		Date date = new Date(string);
		modelKasa.setIzlemeTarihi(convertUtilToSql(date));
		modelKasa.setPersonelAdi("Mehmet Can TOPUZ");
		modelKasa.setIzlemeFiyat(1254.45);
		kasaListesi.add(modelKasa);
		
		}
		 String oldDate="01/01/1900";
	       Date tarih = new Date(oldDate);
		GelismisAramaDAO dao = new GelismisAramaDAO();
		detayliAramaListesi = dao.Listele("", "", "", "", "", "", 0, 0 , 0, tarih, tarih, tarih,tarih,tarih, tarih);
		bilgiTahsilat = new Tahsilat();
		HttpSession session = Util.getSession();
		
		bilgiTahsilat.setKasa_islemini_yapan(session.getAttribute("user").toString());
		baslangicTarihi = new Date();
	    Date tson = DateUtils.addMonths(new Date(), 1);
		bitisTarihi = tson;
	}
	
	public void icraDosyaSec(int id){
		
			
		RequestContext.getCurrentInstance().execute("PF('dlgdetayliarama').hide()");
		
		bilgiTahsilat.setBorclu_adi(AktifBean.borcluAdi);
		bilgiTahsilat.setIcra_dosya_no(AktifBean.icraDosyaNo);
		bilgiTahsilat.setIcra_dosyasi_id(AktifBean.getIcraDosyaID());
		bilgiTahsilat.setMuvekkil_adi(AktifBean.muvekkilAdi);
		HttpSession session = Util.getSession();
		
		bilgiTahsilat.setTasilati_yapan(session.getAttribute("user").toString());
	}
	
	public void dosyaal(int id){
	
		
		
	}
	
	 private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
		 
		         java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		
		         return sDate;
		
		     }

	
}
