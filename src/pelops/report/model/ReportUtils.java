package pelops.report.model;

import java.util.ArrayList;

public class ReportUtils {

	/*
	 * jasper file isimleri hsbc için
	 */
	public static final String JASPERFILE_NAME_TEBLIGAT = "tebligat_zarfi.jasper";
	public static final String JASPERFILE_NAME_ODEME_EMRI = "odeme_emri.jasper";
	public static final String JASPERFILE_NAME_TAKIP_TALEBI = "takip_talebi.jasper";
	public static final String JASPERFILE_NAME_TEBLIGAT_LISTESI = "tebligat_listesi1.jasper";
	public static final String JASPERFILE_NAME_VEKALET_HSBC ="vekalet.jasper";
	public static final String JASPERFILE_NAME_TAKIP_TALEBI_AKBANK = "takip_talebi_akbank.jasper";

	/*
	 * raporların isimleri
	 */
	public static final String REPORT_TAKIP_TALEBI = "takipTalebi";
	public static final String REPORT_ODEME_EMRI = "odemeEmri";
	public static final String REPORT_TEBLIGAT_ZARFI = "tebligatZarfi";
	public static final String REPORT_TALEP_KAGIDI = "talepKagidi";
	public static final String REPORT_IHTARNAME = "ihtarname";
	public static final String REPORT_VEKALETNAME = "vekaletname";
	public static final String REPORT_POSTANEDEN_GELEN_EVRAK = "postanedenGelenEvrak";
	public static final String REPORT_UYAPSORGU = "uyapSorgu";
	public static final String REPORT_TEBLIGAT_LISTESI = "tebligatListesi";
	public static final String SOZLESME = "sozlesme";
	public static final String DIGER = "diger";
	public static final String HESAP_OZETI = "hesapOzeti";
	
	
	//bankalar...
	public static final String HSBC = "HSBC BANK A.Ş.";
	public static final String AKBANK = "AKBANK T.A.Ş.";
	public static final String GARANTI = "T. GARANTİ BANKASI A.Ş.";
	public static final String ING = "İNG BANK A.Ş.";



	
	//model Dosya Yükleme Modeli Belge adi combobox

	public static final ArrayList<Model> getCbModels() {
		ArrayList<Model> list = new ArrayList<Model>();
		Model model = new Model();
		model.setName("Hesap Özeti");
		model.setValue(HESAP_OZETI);
		list.add(model);
		model = new Model();
		model.setName("Talep Kağıdı");
		model.setValue(REPORT_TALEP_KAGIDI);
		list.add(model);
		model = new Model();
		model.setName("İhtarname");
		model.setValue(REPORT_IHTARNAME);
		list.add(model);
		model = new Model();
		model.setName("Vekaletname");
		model.setValue(REPORT_VEKALETNAME);
		list.add(model);
		model = new Model();
		model.setName("Postaneden Gelen Evrak");
		model.setValue(REPORT_POSTANEDEN_GELEN_EVRAK);
		list.add(model);
		model = new Model();
		model.setName("Sözleşme");
		model.setValue(SOZLESME);
		list.add(model);
		model = new Model();
		model.setName("Diğer");
		model.setValue(DIGER);
		list.add(model);

		return list;
	}

}
