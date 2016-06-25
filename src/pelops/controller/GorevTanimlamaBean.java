package pelops.controller;

import pelops.users.User;
import pelops.users.UserDAO;
import pelops.model.Gorev;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "gorevTanimlamaBean")
@ViewScoped
public class GorevTanimlamaBean {

	
	private UserDAO userDAO;
	
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

	@PostConstruct
    public void init() {
		userDAO=new UserDAO();
		
    	userList = userDAO.select();
    	selectedUserId=userList.get(0).getUsrId();
    	
    	gorevList=new ArrayList<Gorev>();
    	
         
    }   
	
	public void onUserSelected(){
		int id=selectedUserId;
		System.out.println(id);
	}
	
	
	
	
	
	
	
}
