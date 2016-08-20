package pelops.reports.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import pelops.controller.AktifBean;
import pelops.report.model.ConstructedData;
import pelops.report.model.DataToPrint;
import pelops.report.model.Model;
import pelops.report.model.ReportGenel;
import pelops.report.model.ReportUtils;
import pelops.report.model.SearchParams;

@ManagedBean(name = "printBean", eager = true)
@SessionScoped
public class PrintBean {

	public String yazdirTipi = "1";
	public String operasyonelYazdirma = "5";
	public String takipAcilisEvrakYazdirma = "0";

	public boolean dosyayaGirecekEvraklar;
	public boolean borcluyaGidecekEvraklar;
	public boolean bankayaGidecekEvraklar;
	public boolean tebligatListes;
	// Dosyaya girecek Evraklar
	private boolean talepKagidi;
	private boolean takipTalebi;
	private boolean odemeEmri;
	private boolean vekaletname;
	private boolean ihtarname;
	private boolean uyapVeri;

	private boolean checkboxPanelDisabled = true;

	// Borcluya gidecek evraklar

	private boolean tebligatZarfi = false;

	// Bankaya Gidecek Evraklar
	private boolean tebligatListesi = false;
	private boolean postanedenGelenEvrak = false;

	// adetler
	private int talepKagidiAdedi = 1;
	private int takipTalebiAdedi = 1;
	private int odemeEmriAdedi = 1;
	private int vekaletnameAdedi = 1;
	private int ihtarnameAdedi = 1;
	private int uyapVeriAdedi = 1;
	private int tebligatListesiAdedi = 1;
	private int postanedenGelenEvrakAdedi = 1;
	private int tebligatZarfiAdedi = 1;

	private ArrayList<Model> muvvekkilAdiModel = new ArrayList<Model>();
	private ArrayList<Model> borcluAdiModel = new ArrayList<Model>();
	private ArrayList<Model> icraDosyaNoModel = new ArrayList<Model>();
	private ArrayList<Model> icraMdModel = new ArrayList<Model>();

	private ArrayList<ReportGenel> rgList = new ArrayList<ReportGenel>();
	private ArrayList<ReportGenel> PrivateList = new ArrayList<ReportGenel>();
	private ArrayList<ReportGenel> filteredList = new ArrayList<>();
	GenelRaporDAO dao = new GenelRaporDAO();
	private Date tarih2 = new Date();
	String oldDate = "01/01/1900";
	private Date tarih1 = new Date(oldDate);

	private String semiNumbers;

	private SearchParams searchParams = new SearchParams();

	private ExternalContext eContext;

	public ArrayList<ReportGenel> getFilteredList() {
		return filteredList;
	}

	public void setFilteredList(ArrayList<ReportGenel> filteredList) {
		this.filteredList = filteredList;
	}

	public boolean getCheckboxPanelDisabled() {
		return checkboxPanelDisabled;
	}

	public void setCheckboxPanelDisabled(boolean checkboxPanelDisabled) {
		this.checkboxPanelDisabled = checkboxPanelDisabled;
	}

	public ArrayList<Model> getMuvvekkilAdiModel() throws Exception {
		return dao.getMuvekkilAdiComboboxModel();
	}

	public void setMuvvekkilAdiModel(ArrayList<Model> muvvekkilAdiModel) {
		this.muvvekkilAdiModel = muvvekkilAdiModel;
	}

	public ArrayList<Model> getBorcluAdiModel() throws Exception {
		return dao.getBorcluAdiComboboxModel();
	}

	public void setBorcluAdiModel(ArrayList<Model> borcluAdiModel) {
		this.borcluAdiModel = borcluAdiModel;
	}

	public ArrayList<Model> getIcraDosyaNoModel() throws Exception {
		return dao.getIcraDosyaNoComboboxModel();
	}

	public void setIcraDosyaNoModel(ArrayList<Model> icraDosyaNoModel) {
		this.icraDosyaNoModel = icraDosyaNoModel;
	}

	public ArrayList<Model> getIcraMdModel() throws Exception {
		return dao.getIcraMudurluguComboboxModel();
	}

	public void setIcraMdModel(ArrayList<Model> icraMdModel) {
		this.icraMdModel = icraMdModel;
	}

	public SearchParams getSearchParams() {
		if (searchParams.getTarih1() == null) {
			searchParams.setTarih1(tarih1);
		}
		if (searchParams.getTarih2() == null) {
			searchParams.setTarih2(tarih2);
		}
		return searchParams;
	}

	public void setSearchParams(SearchParams searchParams) {
		this.searchParams = searchParams;
	}

	public String getSemiNumbers() {
		return semiNumbers;
	}

	public void setSemiNumbers(String semiNumbers) {
		this.semiNumbers = semiNumbers;
	}

	public Date getTarih2() {
		return tarih2;
	}

	public void setTarih2(Date tarih2) {
		this.tarih2 = tarih2;
	}

	public Date getTarih1() {
		return tarih1;
	}

	public void setTarih1(Date tarih1) {
		this.tarih1 = tarih1;
	}

	public ArrayList<ReportGenel> getRgList() throws Exception {
		return rgList;
	}

	public void setRgList(ArrayList<ReportGenel> rgList) {
		this.rgList = rgList;
	}

	public ArrayList<ReportGenel> getPrivateList() {
		return PrivateList;
	}

	public void setPrivateList(ArrayList<ReportGenel> privateList) {
		PrivateList = privateList;
	}

	public int getTalepKagidiAdedi() {
		return talepKagidiAdedi;
	}

	public void setTalepKagidiAdedi(int talepKagidiAdedi) {
		this.talepKagidiAdedi = talepKagidiAdedi;
	}

	public int getTakipTalebiAdedi() {
		return takipTalebiAdedi;
	}

	public void setTakipTalebiAdedi(int takipTalebiAdedi) {
		this.takipTalebiAdedi = takipTalebiAdedi;
	}

	public int getOdemeEmriAdedi() {
		return odemeEmriAdedi;
	}

	public void setOdemeEmriAdedi(int odemeEmriAdedi) {
		this.odemeEmriAdedi = odemeEmriAdedi;
	}

	public int getVekaletnameAdedi() {
		return vekaletnameAdedi;
	}

	public void setVekaletnameAdedi(int vekaletnameAdedi) {
		this.vekaletnameAdedi = vekaletnameAdedi;
	}

	public int getIhtarnameAdedi() {
		return ihtarnameAdedi;
	}

	public void setIhtarnameAdedi(int ihtarnameAdedi) {
		this.ihtarnameAdedi = ihtarnameAdedi;
	}

	public int getUyapVeriAdedi() {
		return uyapVeriAdedi;
	}

	public void setUyapVeriAdedi(int uyapVeriAdedi) {
		this.uyapVeriAdedi = uyapVeriAdedi;
	}

	public int getTebligatListesiAdedi() {
		return tebligatListesiAdedi;
	}

	public void setTebligatListesiAdedi(int tebligatListesiAdedi) {
		this.tebligatListesiAdedi = tebligatListesiAdedi;
	}

	public int getPostanedenGelenEvrakAdedi() {
		return postanedenGelenEvrakAdedi;
	}

	public void setPostanedenGelenEvrakAdedi(int postanedenGelenEvrakAdedi) {
		this.postanedenGelenEvrakAdedi = postanedenGelenEvrakAdedi;
	}

	public int getTebligatZarfiAdedi() {
		return tebligatZarfiAdedi;
	}

	public void setTebligatZarfiAdedi(int tebligatZarfiAdedi) {
		this.tebligatZarfiAdedi = tebligatZarfiAdedi;
	}

	public boolean isDosyayaGirecekEvraklar() {
		return dosyayaGirecekEvraklar;
	}

	public void setDosyayaGirecekEvraklar(boolean dosyayaGirecekEvraklar) {
		this.dosyayaGirecekEvraklar = dosyayaGirecekEvraklar;
	}

	public boolean isBorcluyaGidecekEvraklar() {
		return borcluyaGidecekEvraklar;
	}

	public void setBorcluyaGidecekEvraklar(boolean borcluyaGidecekEvraklar) {
		this.borcluyaGidecekEvraklar = borcluyaGidecekEvraklar;
	}

	public boolean isBankayaGidecekEvraklar() {
		return bankayaGidecekEvraklar;
	}

	public void setBankayaGidecekEvraklar(boolean bankayaGidecekEvraklar) {
		this.bankayaGidecekEvraklar = bankayaGidecekEvraklar;
	}

	public boolean isTebligatListes() {
		return tebligatListes;
	}

	public void setTebligatListes(boolean tebligatListes) {
		this.tebligatListes = tebligatListes;
	}

	public String getYazdirTipi() {
		return yazdirTipi;
	}

	public void setYazdirTipi(String yazdirTipi) {
		this.yazdirTipi = yazdirTipi;
	}

	public String getOperasyonelYazdirma() {
		return operasyonelYazdirma;
	}

	public void setOperasyonelYazdirma(String operasyonelYazdirma) {
		this.operasyonelYazdirma = operasyonelYazdirma;
	}

	public String getTakipAcilisEvrakYazdirma() {
		return takipAcilisEvrakYazdirma;
	}

	public void setTakipAcilisEvrakYazdirma(String takipAcilisEvrakYazdirma) {
		this.takipAcilisEvrakYazdirma = takipAcilisEvrakYazdirma;
	}

	public boolean isTalepKagidi() {
		return talepKagidi;
	}

	public void setTalepKagidi(boolean talepKagidi) {
		this.talepKagidi = talepKagidi;
	}

	public boolean isTakipTalebi() {
		return takipTalebi;
	}

	public void setTakipTalebi(boolean takipTalebi) {
		this.takipTalebi = takipTalebi;
	}

	public boolean isOdemeEmri() {
		return odemeEmri;
	}

	public void setOdemeEmri(boolean odemeEmri) {
		this.odemeEmri = odemeEmri;
	}

	public boolean isVekaletname() {
		return vekaletname;
	}

	public void setVekaletname(boolean vekaletname) {
		this.vekaletname = vekaletname;
	}

	public boolean isIhtarname() {
		return ihtarname;
	}

	public void setIhtarname(boolean ihtarname) {
		this.ihtarname = ihtarname;
	}

	public boolean isUyapVeri() {
		return uyapVeri;
	}

	public void setUyapVeri(boolean uyapVeri) {
		this.uyapVeri = uyapVeri;
	}

	public boolean isTebligatZarfi() {
		return tebligatZarfi;
	}

	public void setTebligatZarfi(boolean tebligatZarfi) {
		this.tebligatZarfi = tebligatZarfi;
	}

	public boolean isTebligatListesi() {
		return tebligatListesi;
	}

	public void setTebligatListesi(boolean tebligatListesi) {
		this.tebligatListesi = tebligatListesi;
	}

	public boolean isPostanedenGelenEvrak() {
		return postanedenGelenEvrak;
	}

	public void setPostanedenGelenEvrak(boolean postanedenGelenEvrak) {
		this.postanedenGelenEvrak = postanedenGelenEvrak;
	}

	public boolean checkComboboxValue(Integer status, Integer cbxValue) {
		/*
		 * Ekran Statuslerini ayarlamak için yazılan methoddur. 1: yazdırma
		 * tipi, 2: operasyonel yazdırma , 3: takip açılış evrak yazdırma
		 */
		if (status == null) {
			return false;
		} else {
			boolean isSelected = false;

			switch (status) {
			case 1:
				if (yazdirTipi != null) {
					switch (cbxValue) {
					case 0:
						isSelected = false;
						operasyonelYazdirma = "0";
						takipAcilisEvrakYazdirma = "0";

						break;
					case 1:
						isSelected = true;
						break;

					default:
						break;
					}
				}
				break;
			case 2:
				if (operasyonelYazdirma != null) {
					switch (cbxValue) {
					case 0:
						isSelected = false;
						takipAcilisEvrakYazdirma = "0";
						break;
					case 5:
						isSelected = true;
						break;

					default:
						break;
					}

				}

				break;
			case 3:
				if (takipAcilisEvrakYazdirma != null) {

					switch (cbxValue) {
					case 0:
						isSelected = true;
						clearAllCheckbox();
						break;
					case 8:
						isSelected = true;
						clearAllCheckbox();
						setDosyayaGirecekEvrakCheckbox();
						break;
					case 9:
						isSelected = true;
						clearAllCheckbox();
						setBorcluyaGidecekEvrakCheckbox();
						break;
					case 10:
						isSelected = true;
						clearAllCheckbox();
						tebligatListesi = true;
						break;
					case 11:
						isSelected = true;
						clearAllCheckbox();
						setBankayaGidecekEvrakCombobox();
						break;
					case 12:
						isSelected = true;
						setDigerCheckbox();
					default:
						break;
					}

				}
				break;

			default:
				break;

			}

			return isSelected;
		}
	}

	public void clearAllCheckbox() {

		talepKagidi = false;
		takipTalebi = false;
		odemeEmri = false;
		vekaletname = false;
		ihtarname = false;
		uyapVeri = false;
		tebligatListesi = false;
		postanedenGelenEvrak = false;
		tebligatZarfi = false;

		checkboxPanelDisabled = true;
	}

	public void setDosyayaGirecekEvrakCheckbox() {
		talepKagidi = true;
		takipTalebi = true;
		odemeEmri = true;
		vekaletname = true;
		ihtarname = true;
		uyapVeri = true;

		checkboxPanelDisabled = true;
	}

	public void setBorcluyaGidecekEvrakCheckbox() {
		odemeEmri = true;
		ihtarname = true;
		tebligatZarfi = true;

		checkboxPanelDisabled = true;
	}

	public void setBankayaGidecekEvrakCombobox() {
		tebligatListesi = true;
		postanedenGelenEvrak = true;

		checkboxPanelDisabled = true;
	}

	public void setDigerCheckbox() {
		clearAllCheckbox();
		checkboxPanelDisabled = false;
	}

	// Ajaxın dinlediği method ve dönen veriye göre Ekranın statusunu
	// değiştiriyor....
	public void subjectSelectionChanged(final AjaxBehaviorEvent event) {
		String value = (String) ((UIOutput) event.getSource()).getValue();
		if (event.getSource() != null) {
			if (value != null) {
				if (yazdirTipi != null) {
					if (Integer.valueOf(yazdirTipi) != 0) {
						checkComboboxValue(1, Integer.parseInt(value));
					}
				} else if (operasyonelYazdirma != null) {
					if (Integer.valueOf(operasyonelYazdirma) != 0) {
						checkComboboxValue(2, Integer.parseInt(value));
					}
				} else if (takipAcilisEvrakYazdirma != null) {
					if (Integer.valueOf(takipAcilisEvrakYazdirma) != 0) {
						checkComboboxValue(3, Integer.parseInt(value));
						checkTakipAcilisCombobox();
					} else if (Integer.valueOf(takipAcilisEvrakYazdirma) == 0) {
						checkTakipAcilisCombobox();
					}
				}

			}
		}
	}

	public boolean checkTakipAcilisCombobox() {
		if (Integer.valueOf(takipAcilisEvrakYazdirma) == 0) {
			return true;
		} else if (Integer.valueOf(takipAcilisEvrakYazdirma) == 8) {
			dosyayaGirecekEvraklar = true;
			borcluyaGidecekEvraklar = false;
			tebligatListes = false;
			bankayaGidecekEvraklar = false;
			clearAllCheckbox();
			setDosyayaGirecekEvrakCheckbox();
			return dosyayaGirecekEvraklar;
		} else if (Integer.valueOf(takipAcilisEvrakYazdirma) == 9) {
			dosyayaGirecekEvraklar = false;
			borcluyaGidecekEvraklar = true;
			tebligatListes = false;
			bankayaGidecekEvraklar = false;
			clearAllCheckbox();
			setBorcluyaGidecekEvrakCheckbox();
			return bankayaGidecekEvraklar;
		} else if (Integer.valueOf(takipAcilisEvrakYazdirma) == 10) {
			dosyayaGirecekEvraklar = false;
			borcluyaGidecekEvraklar = false;
			tebligatListes = true;
			clearAllCheckbox();
			tebligatListesi = true;
			bankayaGidecekEvraklar = false;

			return tebligatListes;
		} else if (Integer.valueOf(takipAcilisEvrakYazdirma) == 11) {
			dosyayaGirecekEvraklar = false;
			borcluyaGidecekEvraklar = false;
			tebligatListes = false;
			bankayaGidecekEvraklar = true;
			clearAllCheckbox();
			setBankayaGidecekEvrakCombobox();
			return false;
		} else {
			return false;
		}
	}

	public ArrayList<DataToPrint> getCheckBoxvalueCreateDP() {
		ArrayList<DataToPrint> dataToPrints = new ArrayList<DataToPrint>();

		if (talepKagidi) {
			DataToPrint dataToPrint = new DataToPrint();
			dataToPrint.setAdet(talepKagidiAdedi);
			dataToPrint.setBelgeAdi(ReportUtils.REPORT_TALEP_KAGIDI);
			dataToPrint.setJasperFileName("");
			dataToPrints.add(dataToPrint);
		}
		if (takipTalebi) {
			DataToPrint dataToPrint = new DataToPrint();
			dataToPrint.setAdet(takipTalebiAdedi);
			dataToPrint.setBelgeAdi(ReportUtils.REPORT_TAKIP_TALEBI);
			dataToPrints.add(dataToPrint);
		}
		if (odemeEmri) {
			DataToPrint dataToPrint = new DataToPrint();
			dataToPrint.setAdet(odemeEmriAdedi);
			dataToPrint.setBelgeAdi(ReportUtils.REPORT_ODEME_EMRI);
			dataToPrints.add(dataToPrint);
		}
		if (vekaletname) {
			DataToPrint dataToPrint = new DataToPrint();
			dataToPrint.setAdet(vekaletnameAdedi);
			dataToPrint.setBelgeAdi(ReportUtils.REPORT_VEKALETNAME);
			dataToPrint.setJasperFileName("");
			dataToPrints.add(dataToPrint);
		}
		if (ihtarname) {
			DataToPrint dataToPrint = new DataToPrint();
			dataToPrint.setAdet(ihtarnameAdedi);
			dataToPrint.setBelgeAdi(ReportUtils.REPORT_IHTARNAME);
			dataToPrint.setJasperFileName("");
			dataToPrints.add(dataToPrint);
		}
		if (uyapVeri) {
			DataToPrint dataToPrint = new DataToPrint();
			dataToPrint.setAdet(uyapVeriAdedi);
			dataToPrint.setBelgeAdi(ReportUtils.REPORT_UYAPSORGU);
			dataToPrint.setJasperFileName("");
			dataToPrints.add(dataToPrint);
		}

		if (postanedenGelenEvrak) {
			DataToPrint dataToPrint = new DataToPrint();
			dataToPrint.setAdet(postanedenGelenEvrakAdedi);
			dataToPrint.setBelgeAdi(ReportUtils.REPORT_POSTANEDEN_GELEN_EVRAK);
			dataToPrint.setJasperFileName("");
			dataToPrints.add(dataToPrint);
		}
		if (tebligatZarfi) {
			DataToPrint dataToPrint = new DataToPrint();
			dataToPrint.setAdet(tebligatZarfiAdedi);
			dataToPrint.setBelgeAdi(ReportUtils.REPORT_TEBLIGAT_ZARFI);
			dataToPrints.add(dataToPrint);
		}

		return dataToPrints;
	}

	public DataToPrint createTebligatListesiDP() {
		if (tebligatListesi) {
			DataToPrint dataToPrint = new DataToPrint();
			dataToPrint.setAdet(tebligatListesiAdedi);
			dataToPrint.setBelgeAdi(ReportUtils.REPORT_TEBLIGAT_LISTESI);
			dataToPrint.setJasperFileName(ReportUtils.JASPERFILE_NAME_TEBLIGAT_LISTESI);
			return dataToPrint;
		} else {
			return null;
		}

	}

	public void insertAll() {
		if (filteredList.size() < rgList.size()) {
			PrivateList = filteredList;
		} else {
			PrivateList = rgList;
		}
	}

	public void print() throws Exception {
		ArrayList<DataToPrint> prints = getCheckBoxvalueCreateDP();
		String msg = null;
		if (prints.size() > 0 || tebligatListesi) {
			ConstructDataCtrl ctrl = new ConstructDataCtrl();
			if (PrivateList.size() < 1) {

			}

			for (ReportGenel r : PrivateList) {
				// :TODO Yeni banka eklendiğinde buralar güncellenecek
				if (!(r.getMuvekkilAdi().equals(ReportUtils.HSBC) || r.getMuvekkilAdi().equals(ReportUtils.AKBANK))) {
					if (!(r.getMuvekkilAdi().equals(ReportUtils.GARANTI))) {
						msg = r.getMuvekkilAdi() + "  raporu şuan çalışma aşamasındadır! ";

					}
				}
			}
			if (msg == null && prints.size() > 0) {
				ArrayList<ConstructedData> listC = ctrl.createConstructedData(prints, PrivateList);
				if (listC.size() > 0) {
					printConstructedData(listC);
				}
			} else if (tebligatListesi) {
				printOnlyTebligatListesi(PrivateList);
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(msg));
			}
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Lütfen yazdırmak istediğiniz rapor türünü veya türlerini şeçiniz!"));
		}
	}

	public void privateListEkle() throws Exception {
		if (eContext == null) {
			eContext = getContext();
		}
		int id = Integer.parseInt(eContext.getRequestParameterMap().get("ekle").toString());

		if (id != 0)
			PrivateList.add(dao.getPrintableList(null, id).get(0));

	}

	public void clearParams() {
		searchParams = new SearchParams();
	}

	public void privateListeCikar() {
		if (eContext == null) {
			eContext = getContext();
		}
		int id = Integer.parseInt(eContext.getRequestParameterMap().get("cikar").toString());
		for (int i = 0; i < PrivateList.size(); i++) {
			if (id == PrivateList.get(i).getId()) {
				PrivateList.remove(i);
			}
		}
	}

	public void SemiNoYazdir() throws NumberFormatException, Exception {

		String[] SemiNums = this.semiNumbers.split(",");
		if (SemiNums.length == 1)
			SemiNums = this.semiNumbers.split("-");
		if (SemiNums.length == 1)
			SemiNums = this.semiNumbers.split(";");
		if (SemiNums.length == 1)
			SemiNums = this.semiNumbers.split(":");

		ArrayList<ReportGenel> RaporListesi = new ArrayList<ReportGenel>();
		GenelRaporDAO dao = new GenelRaporDAO();
		for (int i = 0; i < SemiNums.length; i++) {
			RaporListesi.add(dao.getPrintableList(null, Integer.parseInt(SemiNums[i])).get(0));

		}
		ConstructDataCtrl ctrl = new ConstructDataCtrl();
		if (RaporListesi.size() > 0) {
			ArrayList<DataToPrint> list = getCheckBoxvalueCreateDP();
			if (list.size() > 0) {
				ArrayList<ConstructedData> constructedDatas = ctrl.createConstructedData(list, RaporListesi);
				printConstructedData(constructedDatas);
			} else {

				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage("Lütfen yazdırmak istediğiniz rapor türünü veya türlerini şeçiniz!"));
			}
		}

	}

	@SuppressWarnings("deprecation")
	public void printConstructedData(ArrayList<ConstructedData> list) throws Exception {
		ArrayList<JasperPrint> printList = new ArrayList<JasperPrint>();

		// Dosya Chronolojisine Kayit

		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).getDataToPrints().size(); j++) {
				for (int j2 = 0; j2 < list.get(i).getDataToPrints().get(j).getJasperPrint().size(); j2++) {
					JasperPrint jasperPrint = list.get(i).getDataToPrints().get(j).getJasperPrint().get(j2);
					if (jasperPrint != null) {
						printList.add(jasperPrint);
					}

				}
			}

		}
		// Eger Tebligat Listesi Tikli ise buraya girer...

		if (tebligatListesi) {

			ArrayList<ReportGenel> genels = new ArrayList<ReportGenel>();
			for (ConstructedData data : list) {
				genels.add(data.getGenel());
			}
			ConstructDataCtrl ctrl = new ConstructDataCtrl();
			ArrayList<JasperPrint> liste = ctrl.getTebligatListesiJasperPrint(createTebligatListesiDP(), genels);
			// icra müdürlüğü ekli olmayan nesne varsa uyarı verir...
			boolean İcraMd = false;
			ReportGenel report = null;
			for (ReportGenel reportGenel : genels) {
				if (reportGenel.getIcraBilgi() == "" || reportGenel.getIcraBilgi() == null) {
					İcraMd = true;
				}
				report = reportGenel;
			}
			if (!İcraMd) {
				// ReportChronologyUtil.getInstance()
				// .insertObjToDB(ReportChronologyUtil.convertObjToRC(createTebligatListesiDP(),
				// null));
				for (JasperPrint print : liste) {
					printList.add(print);
				}
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("İcra Müdürlüğü belli olmayan rapor veya raporlarlar var!"));
				return;
			}
		}
		if (eContext == null) {
			eContext = getContext();
		}
		HttpServletResponse httpServletResponse = (HttpServletResponse) eContext.getResponse();
		httpServletResponse.addHeader("Content-disposition",
				"attachment; filename=" + "-" + "raporlar" + "_" + AktifBean.getIcraDosyaID() + ".pdf");
		ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();

		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, printList);

		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
		exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);

		exporter.exportReport();
		servletOutputStream.flush();
		servletOutputStream.close();
		FacesContext.getCurrentInstance().responseComplete();

	}

	public void listele() throws Exception {
		ArrayList<ReportGenel> tmp = new ArrayList<ReportGenel>();
		GenelRaporDAO daom = new GenelRaporDAO();

		tmp = daom.getPrintableList(searchParams, null);

		rgList.clear();
		// for (int i = 0; i < tmp.size(); i++) {
		// ReportGenel rpt = dao.getPrintableList(null,
		// tmp.get(i).getId()).get(0);
		// rgList.add(rpt);
		// filteredList.add(rpt);
		//
		// }
		rgList.addAll(tmp);
		filteredList.addAll(tmp);

	}

	public void printAll() throws Exception {

		ArrayList<DataToPrint> list = getCheckBoxvalueCreateDP();
		String msg = null;
		if (list.size() > 0 || tebligatListesi) {
			ConstructDataCtrl ctrl = new ConstructDataCtrl();
			for (ReportGenel r : rgList) {

				// :TODO Yeni banka eklendiğinde buralar güncellenecek

				if (!(r.getMuvekkilAdi().equals(ReportUtils.HSBC) || r.getMuvekkilAdi().equals(ReportUtils.AKBANK))) {
					if (!(r.getMuvekkilAdi().equals(ReportUtils.GARANTI))) {
						msg = r.getMuvekkilAdi() + "  raporu şuan çalışma aşamasındadır! ";

					}
				}
			}

			if (msg == null) {
				ArrayList<ConstructedData> listeC = ctrl.createConstructedData(list, filteredList);
				if (listeC.size() > 1) {
					printConstructedData(listeC);
				}
			} else if (tebligatListesi) {
				printOnlyTebligatListesi(filteredList);
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(msg));
			}
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null,
					new FacesMessage("Lütfen yazdırmak istediğiniz rapor türünü veya türlerini şeçiniz!"));
		}
	}

	public void clearLists() {
		PrivateList = new ArrayList<ReportGenel>();
		rgList = new ArrayList<ReportGenel>();
		filteredList = new ArrayList<>();
	}

	public void printOne() throws Exception {
		int id = 0;
		id = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("pdf").toString());
		String msg = null;

		ArrayList<DataToPrint> list = getCheckBoxvalueCreateDP();
		if (id != 0) {

			ArrayList<ReportGenel> rList = dao.getPrintableList(null, id);
			for (ReportGenel r : rList) {

				// :TODO Yeni banka eklendiğinde buralar güncellenecek

				if (!(r.getMuvekkilAdi().equals(ReportUtils.HSBC) || r.getMuvekkilAdi().equals(ReportUtils.AKBANK))) {
					if (!(r.getMuvekkilAdi().equals(ReportUtils.GARANTI))) {
						msg = r.getMuvekkilAdi() + "  raporu şuan çalışma aşamasındadır! ";

					}
				}
			}
			if (list.size() > 0 || tebligatListesi) {
				if (msg == null && list.size() > 0) {
					ConstructDataCtrl ctrl = new ConstructDataCtrl();
					ArrayList<ConstructedData> listC = ctrl.createConstructedData(list, rList);
					if (listC.size() > 0)
						printConstructedData(listC);
				} else if (tebligatListesi) {

					printOnlyTebligatListesi(rList);
				} else {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage(msg));
				}
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null,
						new FacesMessage("Lütfen yazdırmak istediğiniz rapor türünü veya türlerini şeçiniz!"));
			}
		}
		rgList = dao.getPrintableList(searchParams, null);
	}

	public ExternalContext getContext() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		return context;
	}

	public void printOnlyTebligatListesi(ArrayList<ReportGenel> genels) throws Exception {

		ConstructDataCtrl ctrl = new ConstructDataCtrl();

		ArrayList<JasperPrint> liste = ctrl.getTebligatListesiJasperPrint(createTebligatListesiDP(), genels);

		
		// icra müdürlüğü ekli olmayan nesne varsa uyarı verir...
		boolean İcraMd = false;
		for (ReportGenel reportGenel : genels) {
			if (reportGenel.getIcraBilgi() == "" || reportGenel.getIcraBilgi() == null) {
				İcraMd = true;
			}
		}
		if (İcraMd) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("İcra Müdürlüğü belli olmayan rapor veya raporlarlar var!"));
			return;
		}

		if (eContext == null) {
			eContext = getContext();
		}
		HttpServletResponse httpServletResponse = (HttpServletResponse) eContext.getResponse();
		httpServletResponse.addHeader("Content-disposition",
				"attachment; filename=" + "-" + "raporlar" + "_" + AktifBean.getIcraDosyaID() + ".pdf");

		ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();

		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, liste);

		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
		exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);

		exporter.exportReport();
		servletOutputStream.flush();
		servletOutputStream.close();
		FacesContext.getCurrentInstance().responseComplete();
	}

}
