package pelops.model;

import java.sql.Date;

public class Hedef {

	private int id;
	private  int personel_id;
	private  int muvekkil_id;
	private  double gunluk_hedef;
	private  double aylik_hedef;
	private  int ilgili_ay;
	private  int ilgili_yil;
	private  int user_id;
	private  Date ekleme_tarihi; 
	private  Date guncelleme_tarihi;
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPersonel_id() {
		return personel_id;
	}
	public void setPersonel_id(int personel_id) {
		this.personel_id = personel_id;
	}
	public int getMuvekkil_id() {
		return muvekkil_id;
	}
	public void setMuvekkil_id(int muvekkil_id) {
		this.muvekkil_id = muvekkil_id;
	}
	public double getGunluk_hedef() {
		return gunluk_hedef;
	}
	public void setGunluk_hedef(double gunluk_hedef) {
		this.gunluk_hedef = gunluk_hedef;
	}
	public double getAylik_hedef() {
		return aylik_hedef;
	}
	public void setAylik_hedef(double aylik_hedef) {
		this.aylik_hedef = aylik_hedef;
	}
	public int getIlgili_ay() {
		return ilgili_ay;
	}
	public void setIlgili_ay(int ilgili_ay) {
		this.ilgili_ay = ilgili_ay;
	}
	public int getIlgili_yil() {
		return ilgili_yil;
	}
	public void setIlgili_yil(int ilgili_yil) {
		this.ilgili_yil = ilgili_yil;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Date getEkleme_tarihi() {
		return ekleme_tarihi;
	}
	public void setEkleme_tarihi(Date ekleme_tarihi) {
		this.ekleme_tarihi = ekleme_tarihi;
	}
	public Date getGuncelleme_tarihi() {
		return guncelleme_tarihi;
	}
	public void setGuncelleme_tarihi(Date guncelleme_tarihi) {
		this.guncelleme_tarihi = guncelleme_tarihi;
	} 
	  
	  
	
	
}
