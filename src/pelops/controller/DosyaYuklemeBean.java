package pelops.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.io.FileUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import pelops.dao.DosyaYuklemeDAO;
import pelops.model.DosyaYukleme;
import pelops.model.Tipi;

@ManagedBean(name = "DosyaYuklemeBean")
@SessionScoped
public class DosyaYuklemeBean {

	private DosyaYukleme dosyaYukleme = new DosyaYukleme();

	private DosyaYuklemeDAO yuklemeDAO = new DosyaYuklemeDAO();

	private boolean isTrue;

	ArrayList<DosyaYukleme> dosyaList = new ArrayList<DosyaYukleme>();

	ArrayList<Tipi> belgeTipiList = yuklemeDAO.getBelgeAdiComboboxModel();

	private UploadedFile uploadedFile;

	@SuppressWarnings("unused")
	private String icraDosyaNo;

	public ArrayList<Tipi> getBelgeTipiList() {
		return belgeTipiList;
	}

	public void setBelgeTipiList(ArrayList<Tipi> belgeTipiList) {
		this.belgeTipiList = belgeTipiList;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public String getIcraDosyaNo() {
		return AktifBean.icraDosyaNo;
	}

	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
	}

	public DosyaYukleme getDosyaYukleme() {
		return dosyaYukleme;
	}

	public void setDosyaYukleme(DosyaYukleme dosyaYukleme) {
		this.dosyaYukleme = dosyaYukleme;
	}

	public ArrayList<DosyaYukleme> getDosyaList() throws Exception {
		return yuklemeDAO.getAllListFromIcraDosyaID(AktifBean.icraDosyaID);
	}

	public void setDosyaList(ArrayList<DosyaYukleme> dosyaList) {
		this.dosyaList = dosyaList;
	}

	public void Kaydet() throws Exception {
		boolean a = false;
		FacesContext context = FacesContext.getCurrentInstance();
		if (dosyaYukleme.getDosyaYolu() != null) {
			a = yuklemeDAO.kaydet(dosyaYukleme);
		} else {
			context.addMessage(null, new FacesMessage(
					"Dosyayı sisteme yüklemediniz!"));
		}

		if (a) {
			context.addMessage(null, new FacesMessage("Kaydedildi!"));
		} else {
			context.addMessage(null, new FacesMessage(
					"Kaydetme işlemi başarısız!"));
		}
		dosyaYukleme = new DosyaYukleme();
	}

	public void Sil() throws Exception {
		int id = Integer.parseInt(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("sil")
				.toString());

		yuklemeDAO.Sil(id);
	}

	public void Vazgec() {
		dosyaYukleme = new DosyaYukleme();
	}

	// String destination = "/Users/Ozgen/Desktop/asd";
	String destination = "c:\\SASA\\";

	public void handleFileUpload(FileUploadEvent event) {
		// get uploaded file from the event
		UploadedFile uploadedFile = (UploadedFile) event.getFile();

		// create an InputStream from the uploaded file
		InputStream inputStr = null;
		try {
			inputStr = uploadedFile.getInputstream();
		} catch (IOException e) {
			// log error
		}

		// create destination File
		File destFile = new File(destination, event.getFile().getFileName());
		dosyaYukleme.setDosyaYolu(destination + "/"
				+ event.getFile().getFileName());

		// use org.apache.commons.io.FileUtils to copy the File
		try {
			FileUtils.copyInputStreamToFile(inputStr, destFile);
		} catch (IOException e) {
			// log error
		}
	}

	public void dlgKaydet() throws Exception {
		Kaydet();
		RequestContext.getCurrentInstance().execute("PF('dlgDosyaEk').show()");
	}

	public void dlgVazgec() {
		Vazgec();
		RequestContext.getCurrentInstance().execute("PF('dlgDosyaEk').show()");
	}

	public void dlgPanelOpen() {
		RequestContext.getCurrentInstance().execute("PF('dlgDosyaEk').show()");
	}

	public void dlgDuzenle() throws Exception {
		// Duzenle();
		RequestContext.getCurrentInstance().execute("PF('dlgDosyaEk').show()");
	}

	public void dlgSil() throws Exception {
		Sil();
		RequestContext.getCurrentInstance().execute("PF('dlgDosyaEk').show()");
	}

	public void dlgYeniKayit() {
		// YeniKayit();
		RequestContext.getCurrentInstance().execute("PF('dlgMasraf').show()");
	}

	public void subjectSelectionChanged(final AjaxBehaviorEvent event) {
		String value = (String) ((UIOutput) event.getSource()).getValue();
		System.out.println("burda  " + value + "   "
				+ dosyaYukleme.getBelgeAdi());
		if (value == "7") {
			checkComboboxValue(value);
		}

	}

	public boolean checkComboboxValue(String value) {
		if (value == "7") {
			isTrue = true;
		} else {
			isTrue = false;
		}
		return isTrue;
	}

}
