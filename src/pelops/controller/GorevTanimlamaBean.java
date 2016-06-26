package pelops.controller;

import pelops.users.User;
import pelops.users.UserDAO;
import pelops.dao.GorevDAO;
import pelops.model.Gorev;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name = "gorevTanimlamaBean")
@ViewScoped
public class GorevTanimlamaBean {

	private UserDAO userDAO;
	
	private GorevDAO gorevDAO;
	
	private Gorev gorev;
	
	private boolean islem;
	
	private List<User> userList;
	private int selectedUserId;
	
	private List<Gorev> gorevList;

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public int getSelectedUserId() {
		return selectedUserId;
	}

	public void setSelectedUserId(int selectedUserId) {
		this.selectedUserId = selectedUserId;
	}

	public List<Gorev> getGorevList() {
		return gorevList;
	}

	public void setGorevList(List<Gorev> gorevList) {
		this.gorevList = gorevList;
	}
	
    public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	
	

	public GorevDAO getGorevDAO() {
		return gorevDAO;
	}

	public void setGorevDAO(GorevDAO gorevDAO) {
		this.gorevDAO = gorevDAO;
	}

	public Gorev getGorev() {
		return gorev;
	}

	public void setGorev(Gorev gorev) {
		this.gorev = gorev;
	}

	@PostConstruct
    public void init() {
		userDAO=new UserDAO();
		
    	userList = userDAO.select();
    	selectedUserId=userList.get(0).getUsrId();
    	
    	
    	gorev = new Gorev();
    	
    	gorevDAO = new GorevDAO();
    	gorevList=gorevDAO.select(1);
    	
    	islem = true;
         
    }   
	
	public void onUserSelected(){
		int id=selectedUserId;
		
		gorevList = gorevDAO.select(id);
		
		System.out.println(id);
	}
	
	
	public void kaydet(){
		gorev.setUserId(selectedUserId);
		gorevDAO.insert(gorev);
		
		gorevList = gorevDAO.select(selectedUserId);		
		FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Kaydedildi!"));
		
	}
	
	
	public void onGorevSelected(int id){
		System.out.println(id);
		gorev = gorevDAO.selectById(id);
		
		islem = false;
		
	}
	
	public void guncelle(){
		gorevDAO.update(gorev);
		gorevList = gorevDAO.select(selectedUserId);
	}
	public void sil(){
		gorevDAO.delete(gorev.getId());
		gorevList = gorevDAO.select(selectedUserId);
	}
	
	
	public void onKaydetClicked(){
		islem = true;
		gorev.setAciklama("");
		gorev.setKonu("");
		gorev.setBitTarih(null);
		
		gorev.setBasTarih(null);
		gorev.setId(-1);
	}

	public boolean isIslem() {
		return islem;
	}

	public void setIslem(boolean islem) {
		this.islem = islem;
	}
	
	
	
	
	
	
	
}
