package pelops.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import pelops.dao.AlacakliDAO;
import pelops.dao.HedefDAO;
import pelops.model.Hedef;
import pelops.users.UserDAO;

@ManagedBean(name="hedefbean")
@SessionScoped
public class HedefBean {

	private ArrayList<Hedef> hedefList = new ArrayList<>();
	private ArrayList<Hedef> filterhedefList = new ArrayList<>();
	HedefDAO dao = new HedefDAO();
	private Hedef hedef = new Hedef();
	private SelectItem[] YilListesi = new SelectItem[20] ; 
	private SelectItem[] AyListesi = new SelectItem[]{new SelectItem("1", "OCAK"),new SelectItem("2", "ŞUBAT"),new SelectItem("3", "MART"),
													new SelectItem("4", "NİSAN"),new SelectItem("5", "MAYIS"),new SelectItem("6", "HAZİRAN"),
													new SelectItem("7", "TEMMUZ"),new SelectItem("8", "AĞUSTOS"),new SelectItem("9", "EYLÜL"),
													new SelectItem("10", "EKİM"),new SelectItem("11", "KASIM"),new SelectItem("12", "ARALIK")	};
	
	private SimpleDateFormat yearsFormat = new SimpleDateFormat("yyyy");
	private SimpleDateFormat fullDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	private Date nowDate = new Date();
	
	
	public HedefBean(){
		int yil =Integer.parseInt(yearsFormat.format(nowDate));
		
		for (int i = 0; i < 20; i++) {
			SelectItem TMP = new SelectItem((yil+i)+"",(yil+i)+""); 
			YilListesi[i]=TMP;
		}
		hedef.setIlgili_yil(Integer.parseInt(yearsFormat.format(nowDate)));
		Listele();
	}
	
	public void Listele(){
		this.setHedefList(dao.Listele(hedef.getIlgili_yil(),hedef.getIlgili_ay()));
		this.setFilterhedefList(dao.Listele(hedef.getIlgili_yil(),hedef.getIlgili_ay()));
	}
	
	public void kaydet(){
		UserDAO usedao = new UserDAO();
		AlacakliDAO alacakli = new AlacakliDAO();
		
		hedef.setPersonel_adi(usedao.selectById(hedef.getPersonel_id()).getUsrAdSoyad());
		hedef.setMuvekkil_adi(alacakli.ListeGetir(hedef.getMuvekkil_id()).getMuvekkilAdi());		
		
		dao.Kaydet(hedef);
		Listele();
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("KAYIT İŞLEMİ !!!", new FacesMessage("Kayıt İşlemi Başarı ile Gerçekleşti."));
		
	}
	
	public void tarihListele(){
		this.setHedefList(dao.Listele(hedef.getIlgili_yil(),hedef.getIlgili_ay()));
		this.setFilterhedefList(dao.Listele(hedef.getIlgili_yil(),hedef.getIlgili_ay()));
		
	}
	
	public void duzenle(){
		dao.Duzenle(hedef);
		Listele();
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Düzenleme İşlemi Başarı ile Gerçekleşti."));
		
	}
	
	public void sil(int id){
		dao.Sil(id);
		Listele();
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Silme İşlemi Başarı ile Gerçekleşti."));
	}
	
	public ArrayList<Hedef> getFilterhedefList() {
		return filterhedefList;
	}



	public void setFilterhedefList(ArrayList<Hedef> filterhedefList) {
		this.filterhedefList = filterhedefList;
	}



	
	public SelectItem[] getYilListesi() {
		return YilListesi;
	}


	public void setYilListesi(SelectItem[] yilListesi) {
		YilListesi = yilListesi;
	}


	public SelectItem[] getAyListesi() {
		return AyListesi;
	}


	public void setAyListesi(SelectItem[] ayListesi) {
		AyListesi = ayListesi;
	}


	public ArrayList<Hedef> getHedefList() {
		return hedefList;
	}
	public void setHedefList(ArrayList<Hedef> hedefList) {
		this.hedefList = hedefList;
		this.filterhedefList = hedefList;
	}
	public Hedef getHedef() {
		return hedef;
	}
	public void setHedef(Hedef hedef) {
		this.hedef = hedef;
	}
	
	
	
	
}
