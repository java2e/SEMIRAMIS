package pelops.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import pelops.dao.EtkinlikDAO;
import pelops.model.Etkinlik;
import pelops.users.User;
import pelops.users.UserDAO;

@ManagedBean(name = "etkinlikTanimlamaBean")
@ViewScoped
public class EtkinlikTanimlamaBean {

	private Etkinlik etkinlik;

	private String comboBoxState;
	private List<User> userList;
	private EtkinlikDAO etkinlikDAO;

	private int[] selectedUsers;

	private UserDAO userDAO;

	@PostConstruct
	public void init() {
		etkinlikDAO = new EtkinlikDAO();
		etkinlik = new Etkinlik();
		comboBoxState = new String();
		userList = new ArrayList<>();
		userDAO = new UserDAO();
		userList = userDAO.select();
	}

	public EtkinlikTanimlamaBean() {
	}

	public void kaydet() {
		FacesContext context = FacesContext.getCurrentInstance();
		if(selectedUsers==null || selectedUsers.length<=0){
			context.addMessage(null, new FacesMessage("Error", "Kişi  seçimi zorunlu."));
			return;
		}

		for (int i = 0; i < selectedUsers.length; i++) {
			Etkinlik etkinlikTemp = new Etkinlik();
			etkinlikTemp.setAciklama(comboBoxState);
			etkinlikTemp.setBasTarih(etkinlik.getBasTarih());
			etkinlikTemp.setBitTarih(etkinlik.getBitTarih());
			etkinlikTemp.setEventId(UUID.randomUUID().toString());
			etkinlikTemp.setUserId(selectedUsers[i]);
			etkinlikDAO.insert(etkinlikTemp);
			
		}
		
		

		context.addMessage(null, new FacesMessage("Successful", "Kayıt başarılı eklendi."));
		selectedUsers = null;
		etkinlik.setBasTarih(null);
		etkinlik.setBitTarih(null);
		

	}

	public Etkinlik getEtkinlik() {
		return etkinlik;
	}

	public void setEtkinlik(Etkinlik etkinlik) {
		this.etkinlik = etkinlik;
	}

	public String getComboBoxState() {
		return comboBoxState;
	}

	public void setComboBoxState(String comboBoxState) {
		this.comboBoxState = comboBoxState;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public int[] getSelectedUsers() {
		return selectedUsers;
	}

	public void setSelectedUsers(int[] selectedUsers) {
		this.selectedUsers = selectedUsers;
	}

	public EtkinlikDAO getEtkinlikDAO() {
		return etkinlikDAO;
	}

	public void setEtkinlikDAO(EtkinlikDAO etkinlikDAO) {
		this.etkinlikDAO = etkinlikDAO;
	}

}
