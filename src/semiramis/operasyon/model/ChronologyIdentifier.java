package semiramis.operasyon.model;

public class ChronologyIdentifier {

	// Timeline için kullanılacak Static Tanımlar

	public static final String ICRADOSYASI_GIRECEK_EVRAK = "İcra Dosyasına Girecek Evraklar";

	public static final String BORCLU_GIDECEK_EVRAK = "Borçluya Gidecek Evraklar";

	public static final String TEBLIGAT_LISTE_EVRAK = "Tebligat Listesi";

	public static final String BANKA_GONDERILECEK_EVRAK = "Bankaya Gönderilecek Evraklar";

	public static final String DIGER = "Diğer";

	public static final String IMAGE_I_D_G_E = "timeline/report.png";
	public static final String IMAGE_FILE_O = "timeline/file_open.png";
	public static final String IMAGE_FILE_U = "timeline/file_update.png";
	public static final String IMAGE_FILE_D = "timeline/file_delete.png";
	public static final String IMAGE_VIZIT = "timeline/saat.png";
	public static final String IMAGE_VIZIT_2 = "timeline/telefonpara.png";
	public static final String IMAGE_KASA_1 = "timeline/para.png";
	public static final String IMAGE_KASA_2 = "timeline/para2.png";
	public static final String IMAGE_YAZDIRMA = "timeline/yazdirma.png";
	public static final String IMAGE_XML = "timeline/xml.png";
	public static final String IMAGE_XLS = "timeline/xls.png";
	public static final String IMAGE_BINA = "timeline/bina.png";
	public static final String IMAGE_GERIDON = "timeline/callback.png";
	public static final String IMAGE_DOSYA_ACMA = "timeline/dosyaacma.png";
	public static final String IMAGE_TELEFON = "timeline/telefon.png";
	public static final String IMAGE_HESAPMAKINESI = "timeline/hesapmakinesi.png";
	public static final String IMAGE_FILE_5 = "timeline/file5.png";

	public static final String ISLEM_YAZDIRMA = "Yazdırma İşlemi";
	public static final String ISLEM_MUAMELE = "Muamele";

	public static final String ISLEM_TAKIP_ACILISI = "Takip Açılışı";

	public static final String ISLEM_GIDEN_EVRAK = "Giden Evrak";

	public static final String ISLEM_GELEN_EVRAK = "Gelen Evrak";

	public static final String ISLEM_MUZEKKERE = "Müzekkere";

	public static final String ISLEM_VIZIT = "Vizit İşlemi";

	public static final String ISLEM_HIZLI_TAKİP = "Hızlı Takip Açılışı";

	public static final String ISLEM_HESAP = "Kasa İşlemi";

	public static final String ISLEM_TAHSILAT = "Tahsilat İşlemi";

	public static final String ISLEM_REDDIYAT = "Reddiyat İşlemi";

	public static final String ISLEM_HITAM = "Hitam İşlemi";

	public static final String ISLEM_UYAP = "Uyap Entegrasyonu";

	public static final String ISLEM_EXCELL = "Excell Entegrasyonu";

	public static final String ISLEM_XML = "XML Entegrasyonu";

	public static String getImagePath(Chronology chronology) {
		String path = null;
		String islem = chronology.getIslem();
		if (islem.equals(ISLEM_MUAMELE)) {
			path = IMAGE_FILE_5;
		} else if (islem.equals(ISLEM_TAKIP_ACILISI)) {
			path = IMAGE_I_D_G_E;
		} else if (islem.equals(ISLEM_GIDEN_EVRAK)) {
			path = IMAGE_FILE_U;
		} else if (islem.equals(ISLEM_GELEN_EVRAK)) {
			path = IMAGE_FILE_D;
		} else if (islem.equals(ISLEM_MUZEKKERE)) {
			path = IMAGE_FILE_O;
		} else if (islem.equals(ISLEM_VIZIT)) {
			path = IMAGE_VIZIT;
		} else if (islem.equals(ISLEM_HIZLI_TAKİP)) {
			path = IMAGE_DOSYA_ACMA;
		} else if (islem.equals(ISLEM_HESAP)) {
			path = IMAGE_HESAPMAKINESI;
		} else if (islem.equals(ISLEM_TAHSILAT)) {
			path = IMAGE_VIZIT_2;
		} else if (islem.equals(ISLEM_REDDIYAT)) {
			path = IMAGE_KASA_2;
		} else if (islem.equals(ISLEM_HITAM)) {
			path = IMAGE_KASA_1;
		} else if (islem.equals(ISLEM_UYAP)) {
			path = IMAGE_I_D_G_E;
		} else if (islem.equals(ISLEM_EXCELL)) {
			path = IMAGE_XLS;
		} else if (islem.equals(ISLEM_XML)) {
			path = IMAGE_XML;
		} else if (islem.equals(ISLEM_YAZDIRMA)) {
			path = IMAGE_YAZDIRMA;
		}
		return path;
	}

}
