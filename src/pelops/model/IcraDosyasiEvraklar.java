package pelops.model;

import java.sql.Timestamp;
import java.util.Date;



public class IcraDosyasiEvraklar {
private int			id;
private int	 		icra_dosyasi_no; 
private Date		tarih ;
private int	  		borclu_id;
private Timestamp	ekleme_tarihi;
private Timestamp	guncelleme_tarihi;
private String	  	dosya_yolu;
private String	  	dosya_turu;
private int	  		dosya_tur_id ;
private int	  		personel_id;
private String 		muvekkil;



public String getMuvekkil() {
	return muvekkil;
}
public void setMuvekkil(String muvekkil) {
	this.muvekkil = muvekkil;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getIcra_dosyasi_no() {
	return icra_dosyasi_no;
}
public void setIcra_dosyasi_no(int icra_dosyasi_no) {
	this.icra_dosyasi_no = icra_dosyasi_no;
}
public Date getTarih() {
	return tarih;
}
public void setTarih(Date tarih) {
	this.tarih = tarih;
}
public int getBorclu_id() {
	return borclu_id;
}
public void setBorclu_id(int borclu_id) {
	this.borclu_id = borclu_id;
}

public Timestamp getGuncelleme_tarihi() {
	return guncelleme_tarihi;
}
public void setGuncelleme_tarihi(Timestamp guncelleme_tarihi) {
	this.guncelleme_tarihi = guncelleme_tarihi;
}
public String getDosya_yolu() {
	return dosya_yolu;
}
public void setDosya_yolu(String dosya_yolu) {
	this.dosya_yolu = dosya_yolu;
}
public String getDosya_turu() {
	return dosya_turu;
}
public void setDosya_turu(String dosya_turu) {
	this.dosya_turu = dosya_turu;
}
public int getDosya_tur_id() {
	return dosya_tur_id;
}
public void setDosya_tur_id(int dosya_tur_id) {
	this.dosya_tur_id = dosya_tur_id;
}
public int getPersonel_id() {
	return personel_id;
}
public void setPersonel_id(int personel_id) {
	this.personel_id = personel_id;
}
public Timestamp getEkleme_tarihi() {
	return ekleme_tarihi;
}
public void setEkleme_tarihi(Timestamp ekleme_tarihi) {
	this.ekleme_tarihi = ekleme_tarihi;
}



}
