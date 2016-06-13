package pelops.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import pelops.util.*;
import pelops.dao.UserDAO;
import pelops.model.User;

@ManagedBean(name = "loginbean")
@SessionScoped
public class LoginBean {

	String kullaniciAdi, sifre, adSoyad;

	public String getAdSoyad() {
		return adSoyad;
	}

	public void setAdSoyad(String adSoyad) {
		this.adSoyad = adSoyad;
	}

	User user;
	UserDAO userDAO;
	private int girisHatasiSayisi = 0;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getGirisHatasiSayisi() {
		return girisHatasiSayisi;
	}

	public void setGirisHatasiSayisi(int girisHatasiSayisi) {
		this.girisHatasiSayisi = girisHatasiSayisi;
	}

	public LoginBean() {

		userDAO = new UserDAO();

	}

	public String getKullaniciAdi() {
		return kullaniciAdi;
	}

	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
	}

	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public boolean login() throws Exception {

		user = userDAO.getUserinkullaniciAdi(kullaniciAdi.trim());

		if (user == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Hatal� Giri�", "Kullan�c� ad� veya �ifre hatal�d�r."));

		} else {

			if (user.getSifre().equals(sifre.trim())) {
				girisHatasiSayisi = 0;
				return true;
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Hatal� Giri�", "Kullan�c� ad� veya �ifre hatal�d�r."));
				girisHatasiSayisi++;
				return false;
			}
		}
		girisHatasiSayisi++;
		return false;

	}

	public String loginProject() throws Exception {

		boolean result = login();
		if (result) {

			HttpSession session = Util.getSession();
			session.setAttribute("username", kullaniciAdi);
			this.setAdSoyad(user.getAdSoyad());
			session.setAttribute("user", user);

			return "ANASAYFA.jsf";
		} else {

			return "login.jsf";
		}
	}

	public String logout() {
		HttpSession session = Util.getSession();
		session.invalidate();

		return "login.jsf";
	}

}
