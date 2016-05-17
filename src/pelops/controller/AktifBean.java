package pelops.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pelops.model.Hesap;

@ManagedBean(name="aktifbean")
@SessionScoped
public class AktifBean {

	static int  icraDosyaID=0, borcluId=0, hesapID=0;
	static String icraDosyaNo="";
	static String muvekkilAdi ="";
    static  String  borcluAdi ="";
    public static Hesap hesaplistesi = new Hesap();
    
    
	public static Hesap getHesaplistesi() {
		return hesaplistesi;
	}
	public static void setHesaplistesi(Hesap hesaplistesi) {
		AktifBean.hesaplistesi = hesaplistesi;
	}
	public static int getHesapID() {
		return hesapID;
	}
	public static void setHesapID(int hesapID) {
		AktifBean.hesapID = hesapID;
	}
	public static int getIcraDosyaID() {
		return icraDosyaID;
	}
	public static void setIcraDosyaID(int icraDosyaID) {
		AktifBean.icraDosyaID = icraDosyaID;
	}
	public static int getBorcluId() {
		return borcluId;
	}
	public static void setBorcluId(int borcluId) {
		AktifBean.borcluId = borcluId;
	}
	public static String getIcraDosyaNo() {
		return icraDosyaNo;
	}
	public static void setIcraDosyaNo(String icraDosyaNo) {
		AktifBean.icraDosyaNo = icraDosyaNo;
	}
	public static String getMuvekkilAdi() {
		return muvekkilAdi;
	}
	public static void setMuvekkilAdi(String muvekkilAdi) {
		AktifBean.muvekkilAdi = muvekkilAdi;
	}
	public static String getBorcluAdi() {
		return borcluAdi;
	}
	public static void setBorcluAdi(String borcluAdi) {
		AktifBean.borcluAdi = borcluAdi;
	}
	
	
	




	
	
}
