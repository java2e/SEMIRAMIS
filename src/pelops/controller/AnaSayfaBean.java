package pelops.controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;

@ManagedBean(name = "anaSayfaBean")
@SessionScoped
public class AnaSayfaBean {

	private String formPath;
	private Tanimlar tanimlar;
	FacesContext context = FacesContext.getCurrentInstance();
	public AnaSayfaBean() {

		formPath = "frm_anasayfa_default.xhtml";

		tanimlar=new Tanimlar();
		
		// formPath = "frm_rapor_charts.xhtml";

	}

	public String getFormPath() {
		return formPath;
	}

	public void setFormPath(String formPath) {
		this.formPath = formPath;
	}

	public void gotoPage(int id) {

		if (AktifBean.icraDosyaID == 0 && id > 49 && id < 64 || (id==1010 && AktifBean.icraDosyaID == 0 )) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"ÜZGÜNÜZ !!!",
					"İCRA DOSYASINI SEÇMEDEN BU ALANDA İŞLEM YAPAMAZSINIZ...İCRA DOSYA TAKİP SAYFASINA YÖNLENDİRİLDİNİZ....");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			formPath = "XHTML/icra_dosyasi/frm_icra_dosya_takibi.xhtml";
		} else {
			switch (id) {

		
			case 1:
				formPath = "/XHTML/tanimlar/frm_adres_statusu.xhtml";
				break;
			case 2:
				formPath = "/XHTML/XHTML/tanimlar/frm_adres_tipi.xhtml";
				break;
			case 3:
				formPath = "/XHTML/XHTML/tanimlar/frm_adres_turu.xhtml";
				break;
			case 4:
				formPath = "/XHTML/tanimlar/frm_alacak_belge_tipi.xhtml";
				break;
			case 5:
				formPath = "/XHTML/tanimlar/frm_alacak_tipi.xhtml";
				break;
			case 6:
				formPath = "/XHTML/tanimlar/frm_analiz_tipi.xhtml";
				break;
			case 7:
				formPath = "/XHTML/tanimlar/frm_avans_turu.xhtml";
				break;
			case 8:
				formPath = "/XHTML/tanimlar/frm_belge_tipi.xhtml";
				break;
			case 9:
				formPath = "/XHTML/tanimlar/frm_borc_tipi.xhtml";
				break;
			case 10:
				formPath = "/XHTML/tanimlar/frm_dava_masraflari.xhtml";
				break;
			case 11:
				formPath = "/XHTML/tanimlar/frm_dokuman_tipi.xhtml";
				break;
			case 12:
				formPath = "/XHTML/tanimlar/frm_dosya_statu.xhtml";
				break;
			case 13:
				formPath = "/XHTML/tanimlar/frm_dosya_turu.xhtml";
				break;
			case 14:
				formPath = "/XHTML/tanimlar/frm_faiz_tipi.xhtml";
				break;
			case 15:
				formPath = "/XHTML/tanimlar/frm_gonderi_tipi.xhtml";
				break;
			case 16:
				formPath = "/XHTML/tanimlar/frm_haciz_masrafi.xhtml";
				break;
			case 17:
				formPath = "/XHTML/tanimlar/frm_haciz_tipi.xhtml";
				break;
			case 18:
				formPath = "/XHTML/tanimlar/frm_harc_tipi.xhtml";
				break;
			case 19:
				formPath = "/XHTML/tanimlar/frm_icra_dosya_asamasi.xhtml";
				break;
			case 20:
				formPath = "/XHTML/tanimlar/frm_il.xhtml";
				break;
			case 21:
				formPath = "/XHTML/tanimlar/frm_ilce.xhtml";
				break;
			case 22:
				formPath = "/XHTML/tanimlar/frm_islem_statusu.xhtml";
				break;
			case 23:
				formPath = "/XHTML/tanimlar/frm_izleme_sonucu.xhtml";
				break;
			case 24:
				formPath = "/XHTML/tanimlar/frm_kasa_gelir_kalemi.xhtml";
				break;
			case 25:
				formPath = "/XHTML/tanimlar/frm_kasa_gider_kalemi.xhtml";
				break;
			case 26:
				formPath = "/XHTML/tanimlar/frm_kasa_tahsilat_statu.xhtml";
				break;
			case 27:
				formPath = "/XHTML/tanimlar/frm_kasa_tahsilat_tipi.xhtml";
				break;
			case 28:
				formPath = "/XHTML/tanimlar/frm_kurum_tipi.xhtml";
				break;
			case 29:
				formPath = "/XHTML/tanimlar/frm_mal_tipi.xhtml";
				break;
			case 30:
				formPath = "/XHTML/tanimlar/frm_masraf_tipi.xhtml";
				break;
			case 31:
				formPath = "/XHTML/tanimlar/frm_meslek.xhtml";
				break;
			case 32:
				formPath = "/XHTML/tanimlar/frm_muamele_statusu.xhtml";
				break;
			case 33:
				formPath = "/XHTML/tanimlar/frm_muamele_tipi.xhtml";
				break;
			case 34:
				formPath = "/XHTML/tanimlar/frm_muvekkil_tipi.xhtml";
				break;
			case 35:
				formPath = "/XHTML/tanimlar/frm_para_birimi.xhtml";
				break;
			case 36:
				formPath = "/XHTML/tanimlar/frm_rapor_tipi.xhtml";
				break;
			case 37:
				formPath = "/XHTML/tanimlar/frm_satis_masraflari.xhtml";
				break;
			case 38:
				formPath = "/XHTML/tanimlar/frm_tahsilat_statusu.xhtml";
				break;
			case 39:
				formPath = "/XHTML/tanimlar/frm_tahsilat_tipi.xhtml";
				break;
			case 40:
				formPath = "/XHTML/tanimlar/frm_takip_masraflari.xhtml";
				break;
			case 41:
				formPath = "/XHTML/tanimlar/frm_takip_sekli.xhtml";
				break;
			case 42:
				formPath = "/XHTML/tanimlar/frm_takip_tipi.xhtml";
				break;
			case 43:
				formPath = "/XHTML/tanimlar/frm_takip_yolu.xhtml";
				break;
			case 44:
				formPath = "/XHTML/tanimlar/frm_taraf_rolu.xhtml";
				break;
			case 45:
				formPath = "/XHTML/tanimlar/frm_taraf_tipi.xhtml";
				break;
			case 46:
				formPath = "/XHTML/tanimlar/frm_tebligat_tipi.xhtml";
				break;

			case 47:
				formPath = "/XHTML/tanimlar/frm_vizit_statusu.xhtml";
				break;
				
			case 48:
				formPath = "/XHTML/tanimlar/frm_basvuru_harci.xhtml";
				break;
				
			case 49:
				formPath = "/XHTML/tanimlar/frm_vekalet_harci.xhtml";
				break;
				
			case 50:
				formPath = "/XHTML/tanimlar/frm_vekalet_sinirlari.xhtml";
				break;
				
				
			case 51:
				formPath = "/XHTML/tanimlar/frm_muvekkil_bilgisi_kaydi.xhtml";
				break;

			

			case 101:
				formPath = "/XHTML/icra_dosyasi/frm_hizli_takip.xhtml";
				break;
			case 102:
				formPath = "/XHTML/icra_dosyasi/frm_icra_dosya_takibi.xhtml";
				break;
			case 103:
				formPath = "/XHTML/icra_dosyasi/frm_alacak_bilgisi_kaydi.xhtml";
				break;
			case 104:
				formPath = "/XHTML/icra_dosyasi/frm_alacakli_bilgisi_kaydi.xhtml";
				break;
			case 105:
				formPath = "/XHTML/icra_dosyasi/frm_borclu_bilgisi_kaydi.xhtml";
				break;
				
				
				
			case 201:
				formPath = "/XHTML/operasyonel/frm_haciz_bilgisi_kaydi.xhtml";
				break;
			case 202:
				formPath = "/XHTML/operasyonel/frm_hacze_esas_mal_bilgisi.xhtml";
				break;
			case 203:
				formPath = "/XHTML/operasyonel/frm_harc_bilgisi_kaydi.xhtml";
				break;
			case 204:
				formPath = "/XHTML/operasyonel/frm_masraf_bilgisi_kaydi.xhtml";
				break;
			case 205:
				formPath = "/XHTML/operasyonel/frm_odeme_emri_kaydi.xhtml";
				break;
			case 206:
				formPath = "/XHTML/operasyonel/frm_vizit_bilgisi_kaydi.xhtml";
				break;
			case 207:
				formPath = "/XHTML/operasyonel/frm_ses_kayit.xhtml";
				break;
			case 208:
				formPath = "/XHTML/operasyonel/frm_izleme_bilgisi_kaydi.xhtml";
				break;
			case 209:
				formPath = "/XHTML/operasyonel/frm_otomatik_dosya_yukleme.xhtml";
				break;
			case 210:
				formPath = "/XHTML/operasyonel/frm_odeme_plani.xhtml";
				break;
			case 211:
				formPath = "/XHTML/operasyonel/frm_tahsilat_listesi.xhtml";
				break;
			case 212:
				formPath = "/XHTML/operasyonel/frm_muamele_islemleri_kaydi.xhtml";
				break;
			case 213:
				formPath = "/XHTML/operasyonel/frm_print.xhtml";
				break;
			case 214:
				formPath = "/XHTML/operasyonel/frm_dosya_chronology.xhtml";
				break;
			case 215:
				formPath = "/XHTML/operasyonel/frm_hedef.xhtml";
				break;
			case 216:
				formPath = "/XHTML/operasyonel/frm_vizit_talep.xhtml";
				break;
			
			
			
			

			case 301:
				formPath = "/XHTML/analiz/frm_muamele_analizi.xhtml";
				break;
			case 302:
				formPath = "/XHTML/analiz/frm_izleme_analizi.xhtml";
				break;
			case 303:
				formPath = "/XHTML/analiz/frm_tahsilat_analizi.xhtml";
				break;
			case 304:
				formPath = "/XHTML/analiz/frm_haciz_analizi.xhtml";
				break;
			case 305:
				formPath = "/XHTML/analiz/frm_kasa_analizi.xhtml";
				break;
			case 306:
				formPath = "/XHTML/analiz/frm_vizit_analizi.xhtml";
				break;
				
				
				
				
			case 401:
				formPath = "/XHTML/uyap/frm_uyap_XML.xhtml";
				break;
			case 403:
				formPath = "/XHTML/uyap/frm_excel_entegrasyonu.xhtml";
				break;
			case 404:
				formPath = "/XHTML/uyap/frm_uyap_sorgu_entegrasyonu.xhtml";
				break;	
			case 405:
				formPath = "/XHTML/uyap/frm_XML_to_uyap.xhtml";
				break;	
				
				
			case 501:
				formPath = "/XHTML/kasa/frm_kasa2.xhtml";
				break;
				
				
			case 901: 
				formPath="/XHTML/kullanici/frm_hesap_tanimlama.xhtml"; 
			break;	
			case 902: 
				formPath="/XHTML/kullanici/frm_takim.xhtml"; 
			break;
			case 903: 
				formPath="/XHTML/kullanici/frm_kullanici_takim.xhtml"; 
			break;
			
			
			case 1001:
				formPath = "/XHTML/yonetici/frm_gorev_tanimlama.xhtml";
				break;
			case 1002:
				formPath = "/XHTML/yonetici/frm_duyuru_tanimlama.xhtml";
				break;
			case 1003:
				formPath = "/XHTML/yonetici/frm_gorev_tanimlama.xhtml";
				break;

		

		/*	case 101:
				formPath = "frm_muamele_islemleri.xhtml";
				break;

			case 102:
				formPath = "frm_tahsilat_listesi_kaydi.xhtml";
				break;

			case 103:
				formPath = "frm_reddiyat_listesi_kaydi.xhtml";
				break;
		
			
				
			case 169:
				formPath = "frm_uyap_sorgu_entegrasyonu.xhtml";
				break;

			case 1001:
				formPath = "dlg_icds_detayli_arama.xhtml";
				break;

			case 1002:
				formPath = "frm_anasayfa_default.xhtml";
				break;

			// case 1003:
			// formPath = "newYazdir.xhtml";
			// break;F_

			case 1003:
				formPath = "frm_otomatik_dosya_yazdirma.xhtml";
				break;
*/
			
			case 1004:
				formPath = "frm_otorobot.xhtml";
				break;
			case 2210:
				formPath = "/XHTML/tanimlar/frm_dosya_yukleme.xhtml";
				break;
			case 1010:
				formPath ="/XHTML/muzekkere/frm_muze_talep2.xhtml";
				break;
			case 1111:
				formPath ="/muzekkere/frm_muze_talep.xhtml";
				break;
			
		
			

				
			default:

				formPath = "frm_anasayfa_default.xhtml"; // ilk sayfa
			}
		}
	}
	public Tanimlar getTanimlar() {
		return tanimlar;
	}

	public void setTanimlar(Tanimlar tanimlar) {
		this.tanimlar = tanimlar;
	}

}
