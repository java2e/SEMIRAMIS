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
	public static final String JASPERFILE_NAME_VEKALET_HSBC = "vekalet.jasper";

	/*
	 * Jasper file isimleri Akbank için
	 */
	public static final String JASPERFILE_NAME_TAKIP_TALEBI_AKBANK = "takip_talebi_akbank1.jasper";
	public static final String JASPERFILE_NAME_ODEME_EMRI_AKBANK = "odeme_emri_akbank.jasper";

	/*
	 * Jasper File isimleri Garanti için
	 */
	public static final String JASPERFILE_NAME_TAKIP_TALEBI_GARANTI = "takip_talebi_garanti.jasper";
	public static final String JASPERFILE_NAME_ODEME_EMRI_GARANTI = "takip_talebi_garanti.jasper";

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

	// bankalar...
	public static final String HSBC = "HSBC BANK A.Ş.";
	public static final String AKBANK = "AKBANK T.A.Ş.";
	public static final String GARANTI = "T. GARANTİ BANKASI A.Ş.";
	public static final String ING = "İNG BANK A.Ş.";

	// Dosya Krononolojisi için static veriler

	public static final String DOSYA_YONU_GIDEN = "Giden";
	public static final String DOSYA_YONU_GELEN = "Gelen";

	// model Dosya Yükleme Modeli Belge adi combobox

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

	public static String extractNumber(String str) {
		str = str.replaceAll("\\s+", "");
		if (str == null || str.isEmpty())
			return "";

		StringBuilder sb = new StringBuilder();
		boolean found = false;
		for (char c : str.toCharArray()) {
			if (Character.isDigit(c)) {
				sb.append(c);
				found = true;
			} else if (found) {
				// If we already found a digit before and this char is not a
				// digit, stop looping
				break;
			}
		}

		return sb.toString();
	}

	public static String getAkbankHesapTitle(String str) {
		String a = extractNumber(str);
		if (!a.equals("")) {
			a += " nolu  kredi kartı için";
		}
		return a;
	}

	public static String convertReportName(String name) {
		String returnVal = "";
		if (name.equals(REPORT_IHTARNAME)) {
			returnVal = "İhtarname";
		} else if (name.equals(REPORT_ODEME_EMRI)) {
			returnVal = "Ödeme Emri";
		} else if (name.equals(REPORT_POSTANEDEN_GELEN_EVRAK)) {
			returnVal = "Postane Evrakı";
		} else if (name.equals(REPORT_ODEME_EMRI)) {
			returnVal = "İhtarname";
		} else if (name.equals(REPORT_TAKIP_TALEBI)) {
			returnVal = "Takip Talebi";
		} else if (name.equals(REPORT_TALEP_KAGIDI)) {
			returnVal = "Talep Kağıdı";
		} else if (name.equals(REPORT_TEBLIGAT_LISTESI)) {
			returnVal = "Tebligat Listesi";
		} else if (name.equals(REPORT_TEBLIGAT_ZARFI)) {
			returnVal = "Tebligat Zarfı";
		} else if (name.equals(REPORT_VEKALETNAME)) {
			returnVal = "Vekaletname";
		}
		return returnVal;
	}

	public static void main(String[] a) {
		System.out.println(extractNumber(
				"326,44 TL Takip öncesi tahsilat, borçtan düşülmüştür.) ( * ): Alacaklarımızın tamamının, asıl alacak tutarlarına takip tarihinden tamamen tahsiline kadar yukarıda belirtilen krediler için, yukarıdaki hesap tablosu/tablolarında belirtilen oranlardan işleyecek temerrüt faizleri, faizlerin gider vergisi, avukatlık ücreti, icra harç ve masraflarıyla birlikte fazlaya ilişkin talep ve dava haklarımız saklı kalmak, tahsilde tekerrür olmamak kaydıyla, sözleşme(ler)deki teselsül hükümleri gereğince TAHSİLİ talebidir. TBK. 100 Md. uyarınca kısmi ödemeler öncelikle faiz ve masraflara mahsup edilecektir."));
	}

}
