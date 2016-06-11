package pelops.model;

import java.sql.Date;

public class LogError {

	int id;
	int user_id;
	String page;
	String hata_value;
	String hata_detay;
	Date tarih;
	String value_one;
	String value_two;
	double sayisal;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getHata_value() {
		return hata_value;
	}
	public void setHata_value(String hata_value) {
		this.hata_value = hata_value;
	}
	public String getHata_detay() {
		return hata_detay;
	}
	public void setHata_detay(String hata_detay) {
		this.hata_detay = hata_detay;
	}
	public Date getTarih() {
		return tarih;
	}
	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}
	public String getValue_one() {
		return value_one;
	}
	public void setValue_one(String value_one) {
		this.value_one = value_one;
	}
	public String getValue_two() {
		return value_two;
	}
	public void setValue_two(String value_two) {
		this.value_two = value_two;
	}
	public double getSayisal() {
		return sayisal;
	}
	public void setSayisal(double sayisal) {
		this.sayisal = sayisal;
	}
	
	
	
	
	
}
