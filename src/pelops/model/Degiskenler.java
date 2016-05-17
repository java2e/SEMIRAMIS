package pelops.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="Degiskenler")
@SessionScoped
public class Degiskenler {

	String Sayfa;
	public String getSayfa() {
		return Sayfa;
	}
	public void setSayfa(String sayfa) {
		Sayfa = sayfa;
	}
	
		
	public void frmSayfaSec(int i){
		
		switch (i) {
		case 1: Sayfa= "/Tanimlar/frmAdresTuru.xhtml";	break;
		case 2: Sayfa= "/Tanimlar/frmAvansTuru.xhtml";break;
		case 3: Sayfa= "/Tanimlar/frmBelgeTipi.xhtml";break;
		case 4: Sayfa= "/Tanimlar/frmBorcTipi.xhtml";break;
		case 5: Sayfa= "/Tanimlar/frmDavaMasraflari.xhtml";break;
		case 6: Sayfa= "/Tanimlar/frmDosyaStatu.xhtml";break;
		case 7: Sayfa= "/Tanimlar/frmDosyaTuru.xhtml";break;
		case 8: Sayfa= "/Tanimlar/frmParaBirimi.xhtml";break;
		
		default: Sayfa= "AnaSayfa.xhtml";break;
		}
		System.out.println(Sayfa);
		}
}
