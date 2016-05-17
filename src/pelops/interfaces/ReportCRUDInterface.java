package pelops.interfaces;

import java.util.ArrayList;

import pelops.model.MuameleIslemleri;

public interface ReportCRUDInterface {
	public void Kaydet() throws Exception;
	public void Iptal();
	public void Duzenle() throws Exception;
	public void Sil() throws Exception;
 	public void SecilenKaydiGetir() throws Exception;
	public ArrayList<MuameleIslemleri> TümListeyiGetir() throws Exception;

}
