package pelops.controller;


import java.text.ParseException;

import java.util.ArrayList;
import java.util.Date;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pelops.dao.GelismisAramaDAO;
import pelops.model.DetayliArama;
import pelops.model.GenelTanimSablon;
import pelops.model.Kasa;

@SessionScoped
@ManagedBean(name="kasaBean")
public class KasaBean {

	private ArrayList<Kasa> kasaListesi = new ArrayList<Kasa>();
	private ArrayList<GenelTanimSablon> tahsilatStatuListesi = new ArrayList<GenelTanimSablon>();
	private ArrayList<GenelTanimSablon> odemeYeriListesi = new ArrayList<GenelTanimSablon>();
	private Kasa modelKasa = new Kasa();
	private ArrayList<DetayliArama> detayliAramaListesi = new  ArrayList<DetayliArama>();
	private ArrayList<DetayliArama> filterDetayliAramaListesi;
	
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
		
	}
	
	
	 private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
		 
		         java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		
		         return sDate;
		
		     }

	
}
