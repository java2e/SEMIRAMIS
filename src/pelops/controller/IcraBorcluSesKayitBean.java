package pelops.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import pelops.dao.SesKayitDAO;
import pelops.db.DBConnection;
import pelops.model.IcraBorcluSesKayit;

@ManagedBean(name = "icraBorcluSesKayitBean")
@SessionScoped
public class IcraBorcluSesKayitBean extends DBConnection {

	IcraBorcluSesKayit seskaydi = new IcraBorcluSesKayit();

	ArrayList<IcraBorcluSesKayit> icraBorcluSesKayitListesi = new ArrayList<IcraBorcluSesKayit>();

	SesKayitDAO dao = new SesKayitDAO();

	@SuppressWarnings("unused")
	private String icraDosyaNo;
	@SuppressWarnings("unused")
	private String borcluAdi;
	@SuppressWarnings("unused")
	private String muvekkilAdi;
	private String OynatPath;
		
	
	
	public void Ses(){
		
	OynatPath =	FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("oynat").toString();
	
	RequestContext.getCurrentInstance().execute("PF('dlgOynat').show()");
	
	
	
	}
	
	public String getOynatPath() {
		return OynatPath;
	}

	public void setOynatPath(String oynatPath) {
		OynatPath = oynatPath;
	}

	boolean panelRender;

	boolean buttonDisabled;
	int status = 0;

	public IcraBorcluSesKayitBean() {
		status = 0;

		OynatPath="";
		PanelClose();
		ButtonOpen();
	}

	public IcraBorcluSesKayit getSeskaydi() {
		return seskaydi;
	}

	public void setSeskaydi(IcraBorcluSesKayit seskaydi) {
		this.seskaydi = seskaydi;
	}

	public ArrayList<IcraBorcluSesKayit> getIcraBorcluSesKayitListesi()
			throws Exception {
		return dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);
	}

	public void setIcraBorcluSesKayitListesi(
			ArrayList<IcraBorcluSesKayit> icraBorcluSesKayitListesi) {
		this.icraBorcluSesKayitListesi = icraBorcluSesKayitListesi;
	}

	public String getIcraDosyaNo() {
		return AktifBean.icraDosyaNo;
	}

	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
	}

	public String getBorcluAdi() {
		return AktifBean.borcluAdi;
	}

	public void setBorcluAdi(String borcluAdi) {
		this.borcluAdi = borcluAdi;
	}

	public String getMuvekkilAdi() {
		return AktifBean.muvekkilAdi;
	}

	public void setMuvekkilAdi(String muvekkilAdi) {
		this.muvekkilAdi = muvekkilAdi;
	}

	public boolean isPanelRender() {
		return panelRender;
	}

	public void setPanelRender(boolean panelRender) {
		this.panelRender = panelRender;
	}

	public boolean isButtonDisabled() {
		return buttonDisabled;
	}

	public void setButtonDisabled(boolean buttonDisabled) {
		this.buttonDisabled = buttonDisabled;
	}

	
	
	
	public void SesAl(FileUploadEvent event) throws Exception {
		
		UploadedFile uploadedFile = (UploadedFile) event.getFile();
		
		String dosyaAdi = uploadedFile.getFileName();
		String[] Uzanti = dosyaAdi.split("[.]");
		String DosyaIsmi="";
		//File Klasor = new File("E:\\SEMIRAMIS_ICRA_KLASORU\\SES\\"+AktifBean.getIcraDosyaID());
		File Klasor = new File("C:\\SEMIRAMIS_ICRA_KLASORU\\SES\\"+AktifBean.getIcraDosyaID());
		
		if(!Klasor.exists())Klasor.mkdir();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		Date tarih = new Date();
		if(Uzanti[1].equals("wma"))
		DosyaIsmi = AktifBean.getIcraDosyaID()+"_"+format.format(tarih)+".wma";
		//File dosya= new File("E:\\SEMIRAMIS_ICRA_KLASORU\\SES\\"+AktifBean.getIcraDosyaID()+"\\"+DosyaIsmi);
		File dosya= new File("C:\\SEMIRAMIS_ICRA_KLASORU\\SES\\"+AktifBean.getIcraDosyaID()+"\\"+DosyaIsmi);
		
		FileUtils.copyInputStreamToFile(uploadedFile.getInputstream(), dosya);	
		//seskaydi.setPath("E:"+"\\"+"\\SEMIRAMIS_ICRA_KLASORU\\"+"\\"+"SES\\"+"\\"+AktifBean.getIcraDosyaID()+"\\"+"\\"+DosyaIsmi);
		seskaydi.setPath("C:"+"\\"+"\\SEMIRAMIS_ICRA_KLASORU\\"+"\\"+"SES\\"+"\\"+AktifBean.getIcraDosyaID()+"\\"+"\\"+DosyaIsmi);
			
	}
	
	
	public void Kaydet() throws Exception {

		if (status == 0) {

			SesKayitDAO dao = new SesKayitDAO();
			boolean result = dao.kaydet(seskaydi);
			FacesContext context = FacesContext.getCurrentInstance();

			if (result) {
				
				context.addMessage(null, new FacesMessage("Kaydedildi!"));

			} else {

				context.addMessage(null, new FacesMessage(
						"Kaydet işlemi başarısız!"));

			}
		}else {
			seskaydi.setIcraDosyaID(AktifBean.icraDosyaID);
			boolean duzenlendi = dao.guncelle(seskaydi);
			FacesContext context = FacesContext.getCurrentInstance();

			if (duzenlendi) {
				context.addMessage(null, new FacesMessage("Düzenlendi!"));
			} else {
				context.addMessage(null, new FacesMessage(
						"Güncelleme işlemi başarısız!"));
			}
			status = 0;
			icraBorcluSesKayitListesi = dao.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);
		}

		PanelClose();
		ButtonOpen();


	}
	
	
	public void YeniKayit() {
		status = 0;
		seskaydi = new IcraBorcluSesKayit();
		PanelOpen();

	}

	public void PanelOpen() {
		this.setPanelRender(true);
		ButtonClose();

	}

	public void PanelClose() {

		this.setPanelRender(false);

	}

	public void ButtonOpen() {

		this.setButtonDisabled(false);

	}

	public void ButtonClose() {
		this.setButtonDisabled(true);

	}

	public void Duzenle() throws Exception {

		status = 1;

		ArrayList<IcraBorcluSesKayit> list = dao
				.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);

		int id = Integer.parseInt(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("duzenle")
				.toString());

		for (IcraBorcluSesKayit hem : list) {
			if (hem.getId() == id) {
				seskaydi = hem;
			}
		}
		icraBorcluSesKayitListesi = dao
				.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);
		PanelOpen();
		ButtonClose();

	}

	public void Sil() throws Exception {
		int id = Integer.parseInt(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("sil")
				.toString());

		dao.Sil(id);

	}

	public void Vazgec() {
		status = 0;
		PanelClose();
		ButtonOpen();

	}

	public void dlgKaydet() throws Exception{
		Kaydet();
		RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
	}
	
	public void dlgVazgec(){
		Vazgec();
		RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
	}
	
	public void dlgPanelOpen(){
		PanelOpen();
		RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
	}
	
	public void dlgDuzenle() throws Exception{
		Duzenle();
		RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
	}
	
	public void dlgSil() throws Exception{
		Sil();
		RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
	}

	public void dlgYeniKayit() {
		YeniKayit();
		RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
	}
	
	
	
}
