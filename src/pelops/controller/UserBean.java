package pelops.controller;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import javax.faces.context.FacesContext;

import pelops.dao.UserDAO;
import pelops.model.User;

@ManagedBean(name="userbean")
@SessionScoped
public class UserBean {

	private ArrayList<User> Liste = new ArrayList<User>();
	User sablon;
	public ArrayList<User> getListe() {
		return Liste;
	}
	public void setListe(ArrayList<User> liste) {
		Liste = liste;
	}
	public User getSablon() {
		return sablon;
	}
	public void setSablon(User sablon) {
		this.sablon = sablon;
	}
	public UserBean(){
		sablon = new User();
		
	}
	
	public ArrayList<User> ListeGetir() throws Exception{
		Liste = new ArrayList<User>();
		UserDAO dao = new UserDAO();
		Liste = dao.Liste("tbl_user");
		return Liste;
		}
	
	public void Kayit() throws Exception{
		UserDAO dao = new UserDAO();
		dao.Kayit(sablon, "tbl_user");
		Liste = new ArrayList<User>();
		Liste = dao.Liste("tbl_user");
			
	}
	
	public void Sil() throws Exception{
		UserDAO dao = new UserDAO();
		int id=Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sil").toString());
		dao.Sil(id, "tbl_user");
		Liste = new ArrayList<User>();
		Liste = dao.Liste("tbl_user");
		
	}
	
	public void Duzenle(String id) throws Exception{
		UserDAO dao = new UserDAO();
		dao.Duzenle(sablon, "tbl_user");
		Liste = new ArrayList<User>();
		Liste = dao.Liste("tbl_user");	
		
	}
}
