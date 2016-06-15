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

		formPath = "frm_rapor_dashboard.xhtml";

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

		if (AktifBean.icraDosyaID == 0 && id > 49 && id < 64) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_INFO,
					"ÜZGÜNÜZ !!!",
					"İCRA DOSYASINI SEÇMEDEN BU ALANDA İŞLEM YAPAMAZSINIZ...İCRA DOSYA TAKİP SAYFASINA YÖNLENDİRİLDİNİZ....");
			RequestContext.getCurrentInstance().showMessageInDialog(message);
			formPath = "frm_icra_dosya_takibi.xhtml";
		} else {
			switch (id) {

			case 222: formPath="/USER/frm_hesap_tanimlama.xhtml"; 
			break;
			
		case 223: formPath="/USER/frm_takim.xhtml"; 
		break;
		
		case 224: formPath="/USER/frm_kullanici_takim.xhtml"; 
		break;
		
			case 1:
				formPath = "/Tanimlar/frm_adres_statusu.xhtml";
				break;
			case 2:
				formPath = "/Tanimlar/frm_adres_tipi.xhtml";
				break;
			case 3:
				formPath = "/Tanimlar/frm_adres_turu.xhtml";
				break;
			case 4:
				formPath = "/Tanimlar/frm_alacak_belge_tipi.xhtml";
				break;
			case 5:
				formPath = "/Tanimlar/frm_alacak_tipi.xhtml";
				break;
			case 6:
				formPath = "/Tanimlar/frm_analiz_tipi.xhtml";
				break;
			case 7:
				formPath = "/Tanimlar/frm_avans_turu.xhtml";
				break;
			case 8:
				formPath = "/Tanimlar/frm_belge_tipi.xhtml";
				break;
			case 9:
				formPath = "/Tanimlar/frm_borc_tipi.xhtml";
				break;
			case 10:
				formPath = "/Tanimlar/frm_dava_masraflari.xhtml";
				break;
			case 11:
				formPath = "/Tanimlar/frm_dokuman_tipi.xhtml";
				break;
			case 12:
				formPath = "/Tanimlar/frm_dosya_statu.xhtml";
				break;
			case 13:
				formPath = "/Tanimlar/frm_dosya_turu.xhtml";
				break;
			case 14:
				formPath = "/Tanimlar/frm_faiz_tipi.xhtml";
				break;
			case 15:
				formPath = "/Tanimlar/frm_gonderi_tipi.xhtml";
				break;
			case 16:
				formPath = "/Tanimlar/frm_haciz_masrafi.xhtml";
				break;
			case 17:
				formPath = "/Tanimlar/frm_haciz_tipi.xhtml";
				break;
			case 18:
				formPath = "/Tanimlar/frm_harc_tipi.xhtml";
				break;
			case 19:
				formPath = "/Tanimlar/frm_icra_dosya_asamasi.xhtml";
				break;
			case 20:
				formPath = "/Tanimlar/frm_il.xhtml";
				break;
			case 21:
				formPath = "/Tanimlar/frm_ilce.xhtml";
				break;
			case 22:
				formPath = "/Tanimlar/frm_islem_statusu.xhtml";
				break;
			case 23:
				formPath = "/Tanimlar/frm_izleme_sonucu.xhtml";
				break;
			case 24:
				formPath = "/Tanimlar/frm_kasa_gelir_kalemi.xhtml";
				break;
			case 25:
				formPath = "/Tanimlar/frm_kasa_gider_kalemi.xhtml";
				break;
			case 26:
				formPath = "/Tanimlar/frm_kasa_tahsilat_statu.xhtml";
				break;
			case 27:
				formPath = "/Tanimlar/frm_kasa_tahsilat_tipi.xhtml";
				break;
			case 28:
				formPath = "/Tanimlar/frm_kurum_tipi.xhtml";
				break;
			case 29:
				formPath = "/Tanimlar/frm_mal_tipi.xhtml";
				break;
			case 30:
				formPath = "/Tanimlar/frm_masraf_tipi.xhtml";
				break;
			case 31:
				formPath = "/Tanimlar/frm_meslek.xhtml";
				break;
			case 32:
				formPath = "/Tanimlar/frm_muamele_statusu.xhtml";
				break;
			case 33:
				formPath = "/Tanimlar/frm_muamele_tipi.xhtml";
				break;
			case 34:
				formPath = "/Tanimlar/frm_muvekkil_tipi.xhtml";
				break;
			case 35:
				formPath = "/Tanimlar/frm_para_birimi.xhtml";
				break;
			case 36:
				formPath = "/Tanimlar/frm_rapor_tipi.xhtml";
				break;
			case 37:
				formPath = "/Tanimlar/frm_satis_masraflari.xhtml";
				break;
			case 38:
				formPath = "/Tanimlar/frm_tahsilat_statusu.xhtml";
				break;
			case 39:
				formPath = "/Tanimlar/frm_tahsilat_tipi.xhtml";
				break;
			case 40:
				formPath = "/Tanimlar/frm_takip_masraflari.xhtml";
				break;
			case 41:
				formPath = "/Tanimlar/frm_takip_sekli.xhtml";
				break;
			case 42:
				formPath = "/Tanimlar/frm_takip_tipi.xhtml";
				break;
			case 43:
				formPath = "/Tanimlar/frm_takip_yolu.xhtml";
				break;
			case 44:
				formPath = "/Tanimlar/frm_taraf_rolu.xhtml";
				break;
			case 45:
				formPath = "/Tanimlar/frm_taraf_tipi.xhtml";
				break;
			case 46:
				formPath = "/Tanimlar/frm_tebligat_tipi.xhtml";
				break;

			case 47:
				formPath = "/Tanimlar/frm_vizit_statusu.xhtml";
				break;
				
			case 1047:
				formPath = "/Tanimlar/frm_basvuru_harci.xhtml";
				break;
				
			case 1048:
				formPath = "/Tanimlar/frm_vekalet_harci.xhtml";
				break;
				
			case 1049:
				formPath = "/Tanimlar/frm_vekalet_sinirlari.xhtml";
				break;
				
				
			case 301:
				formPath = "/Tanimlar/frm_muvekkil_bilgisi_kaydi.xhtml";
				break;

			// �CRA DOSYA ��LEMLER� ALANI

			case 48:
				formPath = "frm_hizli_takip.xhtml";
				break;
			case 49:
				formPath = "frm_icra_dosya_takibi.xhtml";
				break;
			case 50:
				formPath = "frm_alacak_bilgisi_kaydi.xhtml";
				break;
			case 51:
				formPath = "frm_alacakli_bilgisi_kaydi.xhtml";
				break;
			case 52:
				formPath = "frm_borclu_bilgisi_kaydi.xhtml";
				break;
			case 53:
				formPath = "frm_haciz_bilgisi_kaydi.xhtml";
				break;
			case 54:
				formPath = "frm_hacze_esas_mal_bilgisi_kaydi.xhtml";
				break;
			case 55:
				formPath = "frm_harc_bilgisi_kaydi.xhtml";
				break;
			case 56:
				formPath = "frm_masraf_bilgisi_kaydi.xhtml";
				break;
			case 57:
				formPath = "frm_muamele_islemleri_kaydi.xhtml";
				break;
			case 58:
				formPath = "frm_odeme_emri_kaydi.xhtml";
				break;
			case 59:
				formPath = "frm_vizit_bilgisi_kaydi.xhtml";
				break;
			case 60:
				formPath = "frm_icra_borc_ses_kayit.xhtml";
				break;
			case 61:
				formPath = "frm_izleme_bilgisi_kaydi.xhtml";
				break;
			case 62:
				formPath = "frm_otomatik_dosya_yukleme.xhtml";
				break;

			// ANAL�Z ALANI

			case 63:
				formPath = "frm_muamele_analizi.xhtml";
				break;
			case 64:
				formPath = "frm_izleme_analizi.xhtml";
				break;
			case 65:
				formPath = "frm_tahsilat_analizi.xhtml";
				break;
			case 68:
				formPath = "frm_UyapXML.xhtml";
				break;

			case 70:
				formPath = "frm_excell_entegrasyonu.xhtml";
				break;
			case 71:
				formPath = "frm_XMLtoUyap.xhtml";
				break;

			case 100:
				formPath = "frm_odeme_plani.xhtml";
				break;

			case 101:
				formPath = "frm_muamele_islemleri.xhtml";
				break;

			case 102:
				formPath = "frm_tahsilat_listesi_kaydi.xhtml";
				break;

			case 103:
				formPath = "frm_reddiyat_listesi_kaydi.xhtml";
				break;
				
			case 104:
				formPath = "frm_kasa.xhtml";
				break;

			case 105:
				formPath = "frm_tahsilat_listesi.xhtml";
				break;
				
			case 169:
				formPath = "frm_uyap_sorgu_entegrasyonu.xhtml";
				break;

			case 1001:
				formPath = "dlg_icds_detayli_arama.xhtml";
				break;

			case 1002:
				formPath = "frm_rapor_dashboard.xhtml";
				break;

			// case 1003:
			// formPath = "newYazdir.xhtml";
			// break;

			case 1003:
				formPath = "frm_otomatik_dosya_yazdirma.xhtml";
				break;

			case 1005:
				formPath = "frm_print.xhtml";
				break;
			case 1004:
				formPath = "frm_otorobot.xhtml";
				break;
			case 2210:
				formPath = "/Tanimlar/frm_dosya_yukleme.xhtml";
				break;
			case 1010:
				formPath ="frm_muze_talep.xhtml";
				break;
			case 1011:
				formPath = "frm_dosya_chronology.xhtml";
				break;
			
			
				
			default:

				formPath = "frm_rapor_dashboard.xhtml"; // ilk sayfa
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
