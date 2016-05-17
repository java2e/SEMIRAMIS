package pelops.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import pelops.dao.IcraDosyasiEvraklariDAO;
import pelops.model.IcraDosyasiEvraklar;

@SessionScoped
@ManagedBean(name="evrakEkle")
public class IcraDosyasiEvraklariBean {

	IcraDosyasiEvraklar evrak;
	private ArrayList<IcraDosyasiEvraklar> evrakList;
	private String muvekkilAdi, borcluAdi;
	
	
	public String getMuvekkilAdi() {
		return muvekkilAdi;
	}

	public void setMuvekkilAdi(String muvekkilAdi) {
		this.muvekkilAdi = muvekkilAdi;
	}

	public String getBorcluAdi() {
		return borcluAdi;
	}

	public void setBorcluAdi(String borcluAdi) {
		this.borcluAdi = borcluAdi;
	}

	public IcraDosyasiEvraklar getEvrak() {
		return evrak;
	}

	public void setEvrak(IcraDosyasiEvraklar evrak) {
		this.evrak = evrak;
	}

	public ArrayList<IcraDosyasiEvraklar> getEvrakList() {
		return evrakList;
	}

	public void setEvrakList(ArrayList<IcraDosyasiEvraklar> evrakList) {
		this.evrakList = evrakList;
	}

	public IcraDosyasiEvraklariBean(){
		evrakList = new ArrayList<IcraDosyasiEvraklar>();
		evrak = new IcraDosyasiEvraklar();
		muvekkilAdi = AktifBean.getMuvekkilAdi();
		borcluAdi = AktifBean.getBorcluAdi();
		evrak.setIcra_dosyasi_no(AktifBean.getIcraDosyaID());
	}

public void dosyaAktar(FileUploadEvent event) throws Exception {
	
	UploadedFile uploadedFile = (UploadedFile) event.getFile();
	
	String dosyaAdi = uploadedFile.getFileName();
	String[] Uzanti = dosyaAdi.split("[.]");
	String DosyaIsmi="";

	File Klasor = new File("C:\\SEMIRAMIS_ICRA_KLASORU\\EVRAK\\"+AktifBean.getIcraDosyaID());
	
	if(!Klasor.exists())Klasor.mkdir();
	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
	Date tarih = new Date();
	if(Uzanti[1].equals("pdf"))
	DosyaIsmi = AktifBean.getIcraDosyaID()+"_"+format.format(tarih)+".pdf";

	File dosya= new File("C:\\SEMIRAMIS_ICRA_KLASORU\\EVRAK\\"+AktifBean.getIcraDosyaID()+"\\"+DosyaIsmi);
	
	FileUtils.copyInputStreamToFile(uploadedFile.getInputstream(), dosya);	
	
	evrak.setDosya_yolu("C:"+"\\"+"\\SEMIRAMIS_ICRA_KLASORU\\"+"\\"+"EVRAK\\"+"\\"+AktifBean.getIcraDosyaID()+"\\"+"\\"+DosyaIsmi);
		
}

public ArrayList<IcraDosyasiEvraklar> listEvrak() throws Exception{
	
	IcraDosyasiEvraklariDAO  dao = new IcraDosyasiEvraklariDAO();
	return dao.Liste(AktifBean.getIcraDosyaID());
	
}

public void Kaydet(){
	IcraDosyasiEvraklariDAO  dao = new IcraDosyasiEvraklariDAO();
	evrak.setBorclu_id(AktifBean.getBorcluId());
	evrak.setIcra_dosyasi_no(AktifBean.getIcraDosyaID());
	evrak.setDosya_turu(evrakturu(evrak.getDosya_tur_id()));
	dao.Kaydet(evrak);
	
}




public String evrakturu(int idsi){
	String evraktur="";
	switch (idsi) {
	case 1: evraktur="Vekaletname"; break;
	case 2: evraktur="Müzekkere"; break;
	case 4: evraktur="Dilekçe"; break;
	case 5: evraktur="Yetki Belgesi"; break;
	case 6: evraktur="Azilname"; break;
	case 7: evraktur="Diğer"; break;
	case 8: evraktur="Sözleşme"; break;
	case 9: evraktur="Hitam Belgesi"; break;

	default:  break;
	
	}

	return evraktur;
}


//---------------------------------------------------------
UploadedFile uploadedFile1;
public void dosyaAktarGenel(FileUploadEvent event) throws Exception {
	
	uploadedFile1 = (UploadedFile) event.getFile();
	
		
}

public ArrayList<IcraDosyasiEvraklar> listGenelEvrak() throws Exception{
	
	IcraDosyasiEvraklariDAO  dao = new IcraDosyasiEvraklariDAO();
	return dao.Liste(99999999);
	
}

public void GenelEvrakKayıt() throws IOException{
	
	IcraDosyasiEvraklariDAO  dao = new IcraDosyasiEvraklariDAO();
	evrak.setIcra_dosyasi_no(99999999);
	evrak.setDosya_turu("Vekaletname");
	evrak.setMuvekkil(muvekkilAdi);
	dosyayikaydet();
	
	dao.Kaydet(evrak);
	
}

public void dosyayikaydet() throws IOException{
	String DosyaIsmi="";
	String reportPath = FacesContext.getCurrentInstance()
			.getExternalContext()
			.getRealPath("/reports/");
	
	DosyaIsmi = "Vekaletname"+muvekkilAdi+".png";

	File dosya= new File(reportPath+"\\"+DosyaIsmi);
	
	FileUtils.copyInputStreamToFile(uploadedFile1.getInputstream(), dosya);	
	
	evrak.setDosya_yolu(dosya.getPath());
	
}



}
