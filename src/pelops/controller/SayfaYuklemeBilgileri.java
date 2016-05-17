package pelops.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

@SessionScoped
@ManagedBean(name="sayfaYuklemeBilgileri")
public class SayfaYuklemeBilgileri {

	private String dlgSayfa="";

	public String getDlgSayfa() {
		return dlgSayfa;
	}

	public void setDlgSayfa(String dlgSayfa) {
		this.dlgSayfa = dlgSayfa;
	}
	
	public void getir(int id){
		
		switch (id) {
		case 1: dlgSayfa="dlg_haciz_bilgisi_kaydi.xhtml"; break;
		case 2: dlgSayfa="dlg_harc_bilgisi_kaydi.xhtml"; break;
		case 3: dlgSayfa="dlg_masraf_bilgisi_kaydi.xhtml"; break;
		case 4: dlgSayfa="dlg_muamele_islemleri_kaydi.xhtml"; break;
		case 5: dlgSayfa="dlg_odeme_emri_kaydi.xhtml"; break;
		case 6: dlgSayfa="dlg_vizit_bilgisi_kaydi.xhtml"; break;
		case 7: dlgSayfa="dlg_icra_borc_ses_kayit.xhtml"; break;
		case 8: dlgSayfa="dlg_izleme_bilgisi_kaydi.xhtml"; break;
		case 9: dlgSayfa="dlg_alacak_bilgisi_kaydi.xhtml"; break;
		case 10: dlgSayfa="dlg_muamele_detay.xhtml"; break;
		case 11: dlgSayfa="dlg_izleme_detay.xhtml"; break;
		case 12: dlgSayfa="dlg_gonderi_takibi.xhtml"; break;
		case 13: dlgSayfa="dlg_dosya_ekleme.xhtml"; break;

		
		default:		break;
		}
		
		 RequestContext.getCurrentInstance().execute("PF('dlgHarcBilgisi').show()");
System.out.println("geldi");
	}
}
