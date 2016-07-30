package pelops.users;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "hesapTanimlamaBean", eager = true)
@SessionScoped
public class HesapTanimlamaBean {
	private UserDAO userDAO;
	private List<User> users;
	private User updatedUser;
	private boolean updatedVisible;
	// ekle paneli gorunurlulugunu kontrol eder
	private boolean updatedVisibleEkle;

	private UploadedFile userPhotoFile;

	private int islem;

	// filter islemi uygulandiginda primefaces tarafindan set edilen degisken
	private List<User> filteredUsers;

	private int sifreIslemi = 0;
	// sifre degistirilmek istendiginde 2 defa girilen sifrenin
	// ayni olmasinin kontrolu bu degisken uzerinden yapilir
	private String geciciSifre;

	private int selectedIdForSil = -1;

	public HesapTanimlamaBean() {
		updatedVisible = false;
		updatedVisibleEkle = false;
		updatedUser = new User();
		userDAO = new UserDAO();
		users = userDAO.select();
	}

	public void handleFileUpload(FileUploadEvent event) throws IOException {

		// path adresi değiştirilmelidir.
		InputStream input = event.getFile().getInputstream();

		File file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\USER\\USER_IMG\\"),
				event.getFile().getFileName());

		OutputStream output = new FileOutputStream(file);
		updatedUser.setUsrPhotoUrl(event.getFile().getFileName());

		try {
			IOUtils.copy(input, output);
			FacesMessage message = new FacesMessage("",
					event.getFile().getFileName() + " başarılı bir şekilde yüklendi.");
			FacesContext.getCurrentInstance().addMessage(null, message);

		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(output);
		}
	}

	public List<ComboItem> getUserRolAcklari() {
		return userDAO.getSelectRolAcklari(Util.getUser());
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<User> getFilteredUsers() {
		return filteredUsers;
	}

	public void setFilteredUsers(List<User> filteredUsers) {
		this.filteredUsers = filteredUsers;
	}

	public User getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}

	public boolean isUpdatedVisible() {
		return updatedVisible;
	}

	public void setUpdatedVisible(boolean updatedVisible) {
		this.updatedVisible = updatedVisible;
	}

	public boolean isUpdatedVisibleEkle() {
		return updatedVisibleEkle;
	}

	public void setUpdatedVisibleEkle(boolean updatedVisibleEkle) {
		this.updatedVisibleEkle = updatedVisibleEkle;
	}

	public String getGeciciSifre() {
		return geciciSifre;
	}

	public void setGeciciSifre(String geciciSifre) {
		this.geciciSifre = geciciSifre;
	}

	// sayfanin her acilisinda tum panellerin kapatilmasini saglayan metod
	public void panelKapat() {
		// filteredBaraGruplari degiskeni bara gruplari degiskenine set edilerek
		// sayfanin her acilisinda tum listenin data table a dolmasi saglandi
		filteredUsers = users;
		updatedVisible = false;
		updatedVisibleEkle = false;
		sifreIslemi = 0;
	}

	public void guncellePanelAc(int userId) {
		islem = 1;
		updatedVisible = true;
		updatedUser = userDAO.selectById(userId);

		// guncelleme islemi yapilirken ekle paneli kapat
		updatedVisibleEkle = false;
	}

	public void eklePanelAc() {
		islem = 0;
		updatedUser = new User();
		updatedVisible = false;
		updatedVisibleEkle = true;
		sifreIslemi = 0;
	}

	public void guncelleUser() {
		Date date = new Date();
		updatedUser.setGuncelleyenKullaniciId(Util.getUser().getUsrId());
		updatedUser.setGuncellemeZamani(date);
		
		
		
		userDAO.update(updatedUser);
		FacesMessage msg = new FacesMessage("Kullanıcıya ait bilgiler güncellendi");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void ekleUser() {
		Date date = new Date();
		updatedUser.setGuncelleyenKullaniciId(Util.getUser().getUsrId());
		updatedUser.setGuncelleyenKullaniciId(1);
		updatedUser.setGuncellemeZamani(date);
		updatedUser.setUsrPwd(geciciSifre);

		userDAO.insert(updatedUser);

		FacesMessage msg = new FacesMessage("Kullanıcı eklendi");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * islem degiskenine gore kayit uzerinde ekleme ya da guncelleme yapilmasina
	 * karar verilir
	 * 
	 * @throws IOException
	 */
	public void ekleGuncelle() throws IOException {
		if (islem == 1) {
			guncelleUser();
		} else {
			ekleUser();
		}
		// reset table state
		// UIComponent table =
		// FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2:dtTableUser");
		// table.setValueExpression("sortBy", null);

		users = userDAO.select();
		updatedVisible = false;
		updatedVisibleEkle = false;
	}

	/* kullanici kaydi uzerinde guncelleme yapilirken cagrilan metod */
	public void guncelle() {
		guncelleUser();

		users = userDAO.select();
		updatedVisible = false;
		sifreIslemi = 0;

	}

	/* kullanici kaydi uzerinde ekleme yapilirken cagrilan metod */
	public void ekle() throws IOException {
		ekleUser();

		users = userDAO.select();
		updatedVisibleEkle = false;
	}

	// silme islemini gerceklestiren metod
	public void userSil() {
		System.out.println("Silinecek kullanicinin id'si: " + getSelectedIdForSil());
		userDAO.delete(getSelectedIdForSil());
		users = userDAO.select();

		FacesMessage msg = new FacesMessage("Kullanıcı silindi.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	// Ekleme ya da guncelleme islemi iptal edildiginde cagrilan metod
	public void iptal() {

		updatedVisible = false;
		sifreIslemi = 0;
		updatedVisibleEkle = false;
	}

	// Ekleme islemi iptal edildiginde cagrilan metod
	public void iptalEkle() {

		updatedVisibleEkle = false;

		FacesMessage msg = new FacesMessage("İşleminiz iptal edilmiştir.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	// Guncelleme ya da ekleme islemine karar vermek icin cagrilan metod
	public String getIslem() {

		String butonText = "";

		if (islem == 1) {

			butonText = "Güncelle";
		} else {

			butonText = "Ekle";
		}

		return butonText;
	}

	/**
	 * @throws Exception
	 * 
	 */
	public void retrieveFreshData() throws Exception {
		users = userDAO.select();
		filteredUsers = new ArrayList<User>();

		for (Iterator iterator = users.iterator(); iterator.hasNext();) {
			User user = (User) iterator.next();
			filteredUsers.add(user.clone());
		}
	}

	// Sistemdeki user sayisini doner
	public String userSayisi() {
		StringBuilder mesaj = new StringBuilder();
		mesaj.append("Sistemde " + users.size() + " kullanıcı bulunmaktadır.");
		return mesaj.toString();
	}

	/*
	 * Sifre degistir butonuna basildiginda cagrilan metod. sifreIslemi
	 * degiskenini 1 e set eder bu da formda sifre panelinin gorunur olmasını
	 * saglar
	 */
	public void sifreDegistir() {
		sifreIslemi = 1;
	}

	/*
	 * Sifre degistir butonuna basildiginda cagrilan metod. sifreIslemi
	 * degiskenini 1 e set eder bu da formda sifre panelinin gorunur olmasını
	 * saglar
	 */
	public void sifreDegistirUygula() {
		userDAO.sifreGuncelle(updatedUser.getUsrId(), geciciSifre);
		geciciSifre = "";
	}

	/*
	 * sifre degistirilmek istediginde sifre panelinin gorunurlulugunu bu metod
	 * belirler
	 */
	public boolean isSifreDegistir() {
		// sifreIslemi degiskenine gore panelin gorunurlulugu ayarlanır
		if (sifreIslemi == 0) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * Degistir butonuna basildiginda actionListener tarafindan cagrilan metod
	 */
	public void sifreMesaji() {
		sifreIslemi = 0;
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Mesaj:", "Şifreniz başarıyla değiştirilmiştir"));
	}

	// arama ozelligini case insensitive yapan metod
	public boolean filterByName(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		if (value == null) {
			return false;
		}

		String name = value.toString().toUpperCase(new Locale("tr"));
		filterText = filterText.toUpperCase(new Locale("tr"));

		if (name.contains(filterText)) {
			return true;
		} else {
			return false;
		}
	}

	// sortu turkce karakterlere gorede duzenleyen metod
	public int sortByName(String value1, String value2) {

		String val1 = value1;
		String val2 = value2;

		Collator trCollator = Collator.getInstance(new Locale("tr")); // Your
																		// locale
																		// here
		trCollator.setStrength(Collator.PRIMARY);

		return trCollator.compare(val1, val2);

	}

	public int getSelectedIdForSil() {
		return selectedIdForSil;
	}

	public void setSelectedIdForSil(int selectedIdForSil) {
		this.selectedIdForSil = selectedIdForSil;
	}

	public UploadedFile getUserPhotoFile() {
		return userPhotoFile;
	}

	public void setUserPhotoFile(UploadedFile userPhotoFile) {
		this.userPhotoFile = userPhotoFile;
	}

}
