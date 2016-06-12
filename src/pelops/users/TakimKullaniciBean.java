package pelops.users;

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





@ManagedBean(name = "takimKullaniciBean", eager = true)
@SessionScoped
public class TakimKullaniciBean
{
	private TakimDAO takimDAO;
	private List<TakimKullanici> takims;
	private TakimKullanici updatedTakim;
	private boolean updatedVisible;
	// ekle paneli gorunurlulugunu kontrol eder
	private boolean updatedVisibleEkle;
	
	private UserDAO userDAO;

	private int islem;

	// filter islemi uygulandiginda primefaces tarafindan set edilen degisken
	private List<TakimKullanici> filteredTakims;

	private int sifreIslemi = 0;
	// sifre degistirilmek istendiginde 2 defa girilen sifrenin
	// ayni olmasinin kontrolu bu degisken uzerinden yapilir
	private String geciciSifre;

	private int selectedIdForSil = -1;


	

	public TakimKullaniciBean()
	{
		updatedVisible = false;
		updatedVisibleEkle = false;
		updatedTakim = new TakimKullanici();
		takimDAO = new TakimDAO();
	    takims = takimDAO.getListTakimKullanici();
	    userDAO=new UserDAO();
	}
	
	public List<ComboItem> getTakimComboList()
	{
		return  takimDAO.getListCombo();
	}

	


	

	

	public boolean isUpdatedVisible()
	{
		return updatedVisible;
	}

	public void setUpdatedVisible(boolean updatedVisible)
	{
		this.updatedVisible = updatedVisible;
	}

	public boolean isUpdatedVisibleEkle()
	{
		return updatedVisibleEkle;
	}

	public void setUpdatedVisibleEkle(boolean updatedVisibleEkle)
	{
		this.updatedVisibleEkle = updatedVisibleEkle;
	}

	

	public String getGeciciSifre()
	{
		return geciciSifre;
	}

	public void setGeciciSifre(String geciciSifre)
	{
		this.geciciSifre = geciciSifre;
	}

	// sayfanin her acilisinda tum panellerin kapatilmasini saglayan metod
	public void panelKapat()
	{
		// filteredBaraGruplari degiskeni bara gruplari degiskenine set edilerek
		// sayfanin her acilisinda tum listenin data table a dolmasi saglandi
		filteredTakims = takims;
		updatedVisible = false;
		updatedVisibleEkle = false;
		sifreIslemi = 0;
	}

	public void guncellePanelAc(int takimId)
	{
		islem = 1;
		updatedVisible = true;
		updatedTakim = takimDAO.selectByIdTK(takimId);

		// guncelleme islemi yapilirken ekle paneli kapat
		updatedVisibleEkle = false;
	}

	public void eklePanelAc()
	{
		islem = 0;
		updatedTakim = new TakimKullanici();
		updatedVisible = false;
		updatedVisibleEkle = true;
		sifreIslemi = 0;
	}

	
	public void guncelleUser()
	{
		Date date = new Date();
		//updatedTakim.setGuncelleyenKullaniciId(Util.getUser().getUsrId());
		//updatedTakim.setGuncellemeZamani(date);

		takimDAO.guncelleTK(updatedTakim);
		FacesMessage msg = new FacesMessage("Takıma ait bilgiler güncellendi");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void ekleUser()
	{
		Date date = new Date();
		//updatedTakim.setGuncelleyenKullaniciId(Util.getUser().getUsrId());
		//updatedTakim.setGuncellemeZamani(date);
		
		takimDAO.kaydetTK(updatedTakim);

		FacesMessage msg = new FacesMessage("Takım eklendi");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * islem degiskenine gore kayit uzerinde ekleme ya da guncelleme yapilmasina karar verilir
	 */
	public void ekleGuncelle()
	{
		if (islem == 1)
		{
			guncelleUser();
		}
		else
		{
			ekleUser();
		}
		// reset table state
//		UIComponent table = FacesContext.getCurrentInstance().getViewRoot().findComponent(":form2:dtTableUser");
//		table.setValueExpression("sortBy", null);

		takims = takimDAO.getListTakimKullanici();
		updatedVisible = false;
		updatedVisibleEkle = false;
	}

	/* kullanici kaydi uzerinde guncelleme yapilirken cagrilan metod */
	public void guncelle()
	{
		guncelleUser();

		takims = takimDAO.getListTakimKullanici();
		updatedVisible = false;
		sifreIslemi = 0;

	}

	/* kullanici kaydi uzerinde ekleme yapilirken cagrilan metod */
	public void ekle()
	{
		ekleUser();

		takims = takimDAO.getListTakimKullanici();
		updatedVisibleEkle = false;
	}

	// silme islemini gerceklestiren metod
	public void userSil()
	{
		System.out.println("Silinecek kullanicinin id'si: " + getSelectedIdForSil());
		takimDAO.silTK(getSelectedIdForSil());
		takims = takimDAO.getListTakimKullanici();

		FacesMessage msg = new FacesMessage("Takım silindi.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	// Ekleme ya da guncelleme islemi iptal edildiginde cagrilan metod
	public void iptal()
	{

		updatedVisible = false;
		sifreIslemi = 0;
		updatedVisibleEkle = false;
	}

	// Ekleme islemi iptal edildiginde cagrilan metod
	public void iptalEkle()
	{

		updatedVisibleEkle = false;

		FacesMessage msg = new FacesMessage("İşleminiz iptal edilmiştir.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	// Guncelleme ya da ekleme islemine karar vermek icin cagrilan metod
	public String getIslem()
	{

		String butonText = "";

		if (islem == 1)
		{

			butonText = "Güncelle";
		}
		else
		{

			butonText = "Ekle";
		}

		return butonText;
	}

	/**
	 * @throws Exception
	 * 
	 */
	public void retrieveFreshData() throws Exception
	{
		takims = takimDAO.getListTakimKullanici();
		filteredTakims = new ArrayList<TakimKullanici>();

		for (Iterator iterator = takims.iterator(); iterator.hasNext();)
		{
			TakimKullanici takim = (TakimKullanici) iterator.next();
			filteredTakims.add(takim);
		}
	}
	

	// Sistemdeki takim sayisini doner
	public String takimSayisi()
	{
		StringBuilder mesaj = new StringBuilder();
		mesaj.append("Sistemde " + takims.size() + " takım bulunmaktadır.");
		return mesaj.toString();
	}

	
	

	

	/*
	 * Sifre degistir butonuna basildiginda cagrilan metod. sifreIslemi degiskenini 1 e set eder bu
	 * da formda sifre panelinin gorunur olmasını saglar
	 */
	public void sifreDegistir()
	{
		sifreIslemi = 1;
	}

	
	public boolean isSifreDegistir()
	{
		// sifreIslemi degiskenine gore panelin gorunurlulugu ayarlanır
		if (sifreIslemi == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}


	/*
	 * Degistir butonuna basildiginda actionListener tarafindan cagrilan metod
	 */
	public void sifreMesaji()
	{
		sifreIslemi = 0;
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Mesaj:", "Şifreniz başarıyla değiştirilmiştir"));
	}

	// arama ozelligini case insensitive yapan metod
	public boolean filterByName(Object value, Object filter, Locale locale)
	{
		String filterText = (filter == null) ? null : filter.toString().trim();
		if (filterText == null || filterText.equals(""))
		{
			return true;
		}

		if (value == null)
		{
			return false;
		}

		String name = value.toString().toUpperCase(new Locale("tr"));
		filterText = filterText.toUpperCase(new Locale("tr"));

		if (name.contains(filterText))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	// sortu turkce karakterlere gorede duzenleyen metod
	public int sortByName(String value1, String value2)
	{

		String val1 = value1;
		String val2 = value2;

		Collator trCollator = Collator.getInstance(new Locale("tr")); // Your locale here
		trCollator.setStrength(Collator.PRIMARY);

		return trCollator.compare(val1, val2);

	}

	public int getSelectedIdForSil()
	{
		return selectedIdForSil;
	}

	public void setSelectedIdForSil(int selectedIdForSil)
	{
		this.selectedIdForSil = selectedIdForSil;
	}

	public List<TakimKullanici> getTakims() {
		return takims;
	}

	public void setTakims(List<TakimKullanici> takims) {
		this.takims = takims;
	}

	public TakimKullanici getUpdatedTakim() {
		return updatedTakim;
	}

	public void setUpdatedTakim(TakimKullanici updatedTakim) {
		this.updatedTakim = updatedTakim;
	}

	public List<TakimKullanici> getFilteredTakims() {
		return filteredTakims;
	}

	public void setFilteredTakims(List<TakimKullanici> filteredTakims) {
		this.filteredTakims = filteredTakims;
	}

	public int getSifreIslemi() {
		return sifreIslemi;
	}

	public void setSifreIslemi(int sifreIslemi) {
		this.sifreIslemi = sifreIslemi;
	}

	public void setIslem(int islem) {
		this.islem = islem;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public TakimDAO getTakimDAO() {
		return takimDAO;
	}

	public void setTakimDAO(TakimDAO takimDAO) {
		this.takimDAO = takimDAO;
	}
	
	
	
}
