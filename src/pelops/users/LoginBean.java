package pelops.users;

import java.util.Formatter;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {

	private String password;
	private String message, uname, eposta;
	private User user;
	private UserDAO  userDAO;
	private MD5Sifreleme MD5Sifreleme;
	
	private int girisHatasiSayisi = 0;

	public LoginBean() {

		userDAO = new UserDAO();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getEposta() {
		return eposta;
	}

	public void setEposta(String eposta) {
		this.eposta = eposta;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public boolean login() {

		Formatter formatterU = new Formatter();
		Formatter formatterP = new Formatter();
		formatterU.format(new Locale("tr"),uname);
		formatterP.format(new Locale("tr"),password);
		
		MD5Sifreleme = new MD5Sifreleme();
		user = userDAO.selectByUsrName(formatterU.toString().trim());

		if (user == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Hatalı Giriş", "Kullanıcı adı veya şifre hatalıdır........"));

		} else {
			
			if (user.getUsrPwd().equals(MD5Sifreleme.sifrele(formatterP.toString().trim()))) {
			//if (true) {	
			girisHatasiSayisi = 0;
				return true;
			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Hatalı Giriş", "Kullanıcı adı veya şifre hatalıdır."));
				girisHatasiSayisi++;
				return false;
			}
		}
		girisHatasiSayisi++;
		return false;
	}

	public String loginProject() {
		boolean result = login();
		if (result) {

			HttpSession session = Util.getSession();
			session.setAttribute("username", uname);
			
			user.setPage(userDAO.getPages(user.getUsrId()));
			//user.setWriteList(userDAO.getWriteList(user.getUsrId()));
			//user.setReadList(userDAO.getReadList(user.getUsrId()));
			
			session.setAttribute("user", user);

			return "index.xhtml";
		} else {

			return "login.jsf";
		}
	}

	public String logout() {
		HttpSession session = Util.getSession();
		session.invalidate();

		return "login.jsf";
	}

	public int getGirisHatasiSayisi()
	{
		return girisHatasiSayisi;
	}

	public void setGirisHatasiSayisi(int girisHatasiSayisi)
	{
		this.girisHatasiSayisi = girisHatasiSayisi;
	}
}