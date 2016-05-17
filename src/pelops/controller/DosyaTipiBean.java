package pelops.controller;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pelops.dao.DosyaTipiDAO;

import pelops.model.DosyaTipi;


@ManagedBean(name="dosyaTipiBean")
@SessionScoped
public class DosyaTipiBean {

	ArrayList<DosyaTipi> Liste = new ArrayList<DosyaTipi>();
	
	public ArrayList<DosyaTipi> ListeGetir() throws Exception{
		Liste = new ArrayList<DosyaTipi>();
		DosyaTipiDAO dao = new DosyaTipiDAO();
		Liste = dao.Liste();
		return Liste;
		}
	
	
}
