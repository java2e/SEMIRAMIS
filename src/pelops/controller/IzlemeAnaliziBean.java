package pelops.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pelops.dao.IzlemeAnaliziDAO;
import pelops.model.IzlemeAnalizi;
@ManagedBean(name="izlemeAnaliziBean")
@SessionScoped
public class IzlemeAnaliziBean {

	private String muvekkilAdi; 
	private String borcluAdi; 
	private String dosyaTipi;
	private Date gelisTarihi1;
	private Date gelisTarihi2; 
	private Date odemeSozuTarihi1;
	private Date odemeSozuTarihi2;
	private double odenmeyuzdesi1;
	private double odenmeyuzdesi2;
	private double dosyaBuyuklugu1;
	private double dosyaBuyuklugu2;
	private double dosyaYasi1;
	private double dosyaYasi2;
	private String borcTipi;
	private String takipTipi;
	private String dosyaStatusu; 
	
	
	
	public String getBorcTipi() {
		return borcTipi;
	}

	public void setBorcTipi(String borcTipi) {
		this.borcTipi = borcTipi;
	}

	public String getTakipTipi() {
		return takipTipi;
	}

	public void setTakipTipi(String takipTipi) {
		this.takipTipi = takipTipi;
	}

	public String getDosyaStatusu() {
		return dosyaStatusu;
	}

	public void setDosyaStatusu(String dosyaStatusu) {
		this.dosyaStatusu = dosyaStatusu;
	}

	public double getDosyaBuyuklugu1() {
		return dosyaBuyuklugu1;
	}

	public void setDosyaBuyuklugu1(double dosyaBuyuklugu1) {
		this.dosyaBuyuklugu1 = dosyaBuyuklugu1;
	}

	public double getDosyaBuyuklugu2() {
		return dosyaBuyuklugu2;
	}

	public void setDosyaBuyuklugu2(double dosyaBuyuklugu2) {
		this.dosyaBuyuklugu2 = dosyaBuyuklugu2;
	}

	public double getDosyaYasi1() {
		return dosyaYasi1;
	}

	public void setDosyaYasi1(double dosyaYasi1) {
		this.dosyaYasi1 = dosyaYasi1;
	}

	public double getDosyaYasi2() {
		return dosyaYasi2;
	}

	public void setDosyaYasi2(double dosyaYasi2) {
		this.dosyaYasi2 = dosyaYasi2;
	}

	private ArrayList<IzlemeAnalizi> analizListesi;

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

	public String getDosyaTipi() {
		return dosyaTipi;
	}

	public void setDosyaTipi(String dosyaTipi) {
		this.dosyaTipi = dosyaTipi;
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

	public Date getOdemeSozuTarihi1() {
		return odemeSozuTarihi1;
	}

	public void setOdemeSozuTarihi1(Date odemeSozuTarihi1) {
		this.odemeSozuTarihi1 = odemeSozuTarihi1;
	}

	public Date getOdemeSozuTarihi2() {
		return odemeSozuTarihi2;
	}

	public void setOdemeSozuTarihi2(Date odemeSozuTarihi2) {
		this.odemeSozuTarihi2 = odemeSozuTarihi2;
	}

	public double getOdenmeyuzdesi1() {
		return odenmeyuzdesi1;
	}

	public void setOdenmeyuzdesi1(double odenmeyuzdesi1) {
		this.odenmeyuzdesi1 = odenmeyuzdesi1;
	}

	public double getOdenmeyuzdesi2() {
		return odenmeyuzdesi2;
	}

	public void setOdenmeyuzdesi2(double odenmeyuzdesi2) {
		this.odenmeyuzdesi2 = odenmeyuzdesi2;
	}

	public ArrayList<IzlemeAnalizi> getAnalizListesi() {
		return analizListesi;
	}

	public void setAnalizListesi(ArrayList<IzlemeAnalizi> analizListesi) {
		this.analizListesi = analizListesi;
	}
	
	  String oldDate="01/01/1900";
	     
	     @SuppressWarnings("deprecation")
		Date tarih = new Date(oldDate);
	
	public IzlemeAnaliziBean(){
		this.odemeSozuTarihi1 = tarih;
		this.gelisTarihi1 = tarih;
		this.odemeSozuTarihi2 = new Date();
		this.gelisTarihi2 =  new Date();
		this.analizListesi = new ArrayList<IzlemeAnalizi>();
	}
	
	public void Listele() throws Exception{
		IzlemeAnaliziDAO dao = new IzlemeAnaliziDAO();
		this.analizListesi = dao.Listele(muvekkilAdi, borcluAdi, dosyaTipi, gelisTarihi1, gelisTarihi2, odemeSozuTarihi1, odemeSozuTarihi2, odenmeyuzdesi1, odenmeyuzdesi2);
		
	}
	
	
}
