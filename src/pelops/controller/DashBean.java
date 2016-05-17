package pelops.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="dashBean")
@SessionScoped
public class DashBean {
	
	
	private String testVeri;
	
	
	public DashBean() {
		// TODO Auto-generated constructor stub
	
	testVeri="{ "
     +"     Muvekkil: \"HSBC\", "
     +"     DosyaSayisi: 58             "
     +" }, {                           "
     +"     Muvekkil: \"AKBANK\",        "
     +"     DosyaSayisi: 42             "
     +" } ";
	
	}

	public String getTestVeri() {
		return testVeri;
	}

	public void setTestVeri(String testVeri) {
		this.testVeri = testVeri;
	}
	
	
	
	

}
