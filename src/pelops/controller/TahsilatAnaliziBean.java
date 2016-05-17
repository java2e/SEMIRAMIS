package pelops.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pelops.dao.TahsilatAnaliziDAO;
import pelops.model.TahsilatAnalizi;

@ManagedBean(name="tahsilatAnaliziBean")
@SessionScoped
public class TahsilatAnaliziBean {

	private int id; 
	private int icraDosyasiId; 
	private String icraDosyaNo; 
	private String muvekkilAdi; 
	private String borcluAdi; 
	private Date gelisTarihi1; 
	private Date gelisTarihi2; 
	private String borcTipi; 
	private String dosyaTipi; 
	private String icraMudurlugu; 
	private Date tahsilatTarihi1; 
	private Date tahsilatTarihi2; 
	private String TahsilatTipi;
	private double tahsilatMiktari; 
	private String tahsilatStatusu;
	private ArrayList<TahsilatAnalizi> tahsilatListesi = new ArrayList<TahsilatAnalizi>();
	private ArrayList<TahsilatAnalizi> filtertahsilatListesi = new ArrayList<TahsilatAnalizi>();
	
	
	public ArrayList<TahsilatAnalizi> getFiltertahsilatListesi() {
		return filtertahsilatListesi;
	}

	public void setFiltertahsilatListesi(
			ArrayList<TahsilatAnalizi> filtertahsilatListesi) {
		this.filtertahsilatListesi = filtertahsilatListesi;
	}

	public TahsilatAnaliziBean(){
		  String oldDate="01/01/1900";
		    
	        @SuppressWarnings("deprecation")
			Date tarih = new Date(oldDate);
	        
	        gelisTarihi1 = tarih;
	        gelisTarihi2 = new Date();
	        tahsilatTarihi1 = tarih;
	        tahsilatTarihi2 = new Date();
	        
		
	}
	
	public ArrayList<TahsilatAnalizi> getTahsilatListesi() {
		return tahsilatListesi;
	}
	public void setTahsilatListesi(ArrayList<TahsilatAnalizi> tahsilatListesi) {
		this.tahsilatListesi = tahsilatListesi;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIcraDosyasiId() {
		return icraDosyasiId;
	}
	public void setIcraDosyasiId(int icraDosyasiId) {
		this.icraDosyasiId = icraDosyasiId;
	}
	public String getIcraDosyaNo() {
		return icraDosyaNo;
	}
	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
	}
	public String getMuvekkilAdi() {
		return muvekkilAdi;
	}
	public void setMuvekkilAdi(String muvekkilAdi) {
		this.muvekkilAdi = muvekkilAdi;
	}
	public String getBorcluAdi() {
		return borcluAdi;
	}
	public void setBorcluAdi(String borcluAdi) {
		this.borcluAdi = borcluAdi;
	}
	public Date getGelisTarihi1() {
		return gelisTarihi1;
	}
	public void setGelisTarihi1(Date gelisTarihi1) {
		this.gelisTarihi1 = gelisTarihi1;
	}
	public Date getGelisTarihi2() {
		return gelisTarihi2;
	}
	public void setGelisTarihi2(Date gelisTarihi2) {
		this.gelisTarihi2 = gelisTarihi2;
	}
	public String getBorcTipi() {
		return borcTipi;
	}
	public void setBorcTipi(String borcTipi) {
		this.borcTipi = borcTipi;
	}
	public String getDosyaTipi() {
		return dosyaTipi;
	}
	public void setDosyaTipi(String dosyaTipi) {
		this.dosyaTipi = dosyaTipi;
	}
	public String getIcraMudurlugu() {
		return icraMudurlugu;
	}
	public void setIcraMudurlugu(String icraMudurlugu) {
		this.icraMudurlugu = icraMudurlugu;
	}
	public Date getTahsilatTarihi1() {
		return tahsilatTarihi1;
	}
	public void setTahsilatTarihi1(Date tahsilatTarihi1) {
		this.tahsilatTarihi1 = tahsilatTarihi1;
	}
	public Date getTahsilatTarihi2() {
		return tahsilatTarihi2;
	}
	public void setTahsilatTarihi2(Date tahsilatTarihi2) {
		this.tahsilatTarihi2 = tahsilatTarihi2;
	}
	public String getTahsilatTipi() {
		return TahsilatTipi;
	}
	public void setTahsilatTipi(String tahsilatTipi) {
		TahsilatTipi = tahsilatTipi;
	}
	public double getTahsilatMiktari() {
		return tahsilatMiktari;
	}
	public void setTahsilatMiktari(double tahsilatMiktari) {
		this.tahsilatMiktari = tahsilatMiktari;
	}
	public String getTahsilatStatusu() {
		return tahsilatStatusu;
	}
	public void setTahsilatStatusu(String tahsilatStatusu) {
		this.tahsilatStatusu = tahsilatStatusu;
	}
	
	public void TahsilatListele() throws Exception{
		
		TahsilatAnaliziDAO dao = new TahsilatAnaliziDAO();
		tahsilatListesi = dao.Listele(id, icraDosyasiId, icraDosyaNo, muvekkilAdi, borcluAdi, gelisTarihi1, gelisTarihi2, borcTipi, 
				dosyaTipi, icraMudurlugu, tahsilatTarihi1, tahsilatTarihi2, TahsilatTipi, tahsilatMiktari, tahsilatStatusu);
		filtertahsilatListesi = tahsilatListesi;
	}
	
	
}
