package pelops.login;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import pelops.users.User;
import pelops.users.UserDAO;
import pelops.util.Util;


@ManagedBean(name="genericBean",eager=true)
@SessionScoped
public class GenericBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private static String kullanici="";
	private static String sifre="";
	public static int i=0;
	private String kullaniciAdi="";
	
	
	
	public String getKullaniciAdi() {
		return kullaniciAdi;
	}

	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
	}

	public String getKullanici()
	{
		return kullanici;
	}

	@SuppressWarnings("static-access")
	public void setKullanici(String kullanici)
	{
		this.kullanici = kullanici;
	}

	public String getSifre()
	{
		return sifre;
	}

	@SuppressWarnings("static-access")
	public void setSifre(String sifre)
	{
		this.sifre = sifre;
	}

	public String login(ActionEvent actionEvent) throws IOException
	{
		
		FacesContext context = FacesContext.getCurrentInstance();
		
//		HttpSession session = Util.getSession();
//		session.setAttribute("username", "muhammet");
//		
//		session.setAttribute("user", "kaya");
//		
//		context.getExternalContext().redirect("/SEMIRAMIS/index.jsf");
//		context.responseComplete();

		if (kullanici.equals(null) || sifre == null || kullanici.trim().length() == 0 || sifre.trim().length() == 0 )
		{
			
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "",
					"Kullanıcı adınız veya şifrenizi BOŞ bırakamazsınız."));
			
		}
		else
		{
			
			User user = new User();
			UserDAO dao = new UserDAO();
			
			try {
				user = dao.selectByUsrName(kullanici);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			if (user.getUsrPwd().equals(sifre.trim()))
			{
				if(user.getUsrName().equals("SasaKasa"))
				{
					HttpSession session = Util.getSession();
					session.setAttribute("username", user.getUsrName());
					kullaniciAdi = user.getUsrName();
					session.setAttribute("user", user.getUsrAdSoyad());
					session.setAttribute("UserID", user.getUsrId());
					
					context.getExternalContext().redirect("/SEMIRAMIS/frm_kasa.jsf");
					context.responseComplete();
				}else{
					HttpSession session = Util.getSession();
					session.setAttribute("username", user.getUsrName());
					kullaniciAdi = user.getUsrName();
					session.setAttribute("user", user.getUsrAdSoyad());
					session.setAttribute("UserID", user.getUsrId());
					
					
					context.getExternalContext().redirect("/SEMIRAMIS/index.jsf");
					context.responseComplete();
					
				}
		
			
			
			
			}else{
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "",
						"Kullanıcı adınız veya şifreniz yanlış. Lütfen tekrar deneyiniz."));
				
			}
		}
		
		return "";
	}
	
}
