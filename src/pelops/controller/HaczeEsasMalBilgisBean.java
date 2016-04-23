package pelops.controller;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pelops.dao.HaczeEsasMalBilgisiDAO;
import pelops.model.HaczeEsasMalBilgisi;
import pelops.model.Ilce;

@ManagedBean(name = "haczeEsasMalBilgisiBean")
@SessionScoped
public class HaczeEsasMalBilgisBean {

	@SuppressWarnings("unused")
	private String icraDosyaNO;
	@SuppressWarnings("unused")
	private String borcluAdi;
	@SuppressWarnings("unused")
	private String muvekkliAdi;

	private boolean panelRender;

	private boolean buttonDisabled;

	private int status = 0;

	ArrayList<HaczeEsasMalBilgisi> hemList = new ArrayList<HaczeEsasMalBilgisi>();

	private ArrayList<Ilce> ilceList = new ArrayList<Ilce>();

	HaczeEsasMalBilgisi haczeEsasMalBilgisi = new HaczeEsasMalBilgisi();

	HaczeEsasMalBilgisiDAO dao = new HaczeEsasMalBilgisiDAO();

	public HaczeEsasMalBilgisBean() {

		PanelClose();
		ButtonOpen();
	}

	public HaczeEsasMalBilgisi getHaczeEsasMalBilgisi() {
		return haczeEsasMalBilgisi;
	}

	public void setHaczeEsasMalBilgisi(HaczeEsasMalBilgisi haczeEsasMalBilgisi) {
		this.haczeEsasMalBilgisi = haczeEsasMalBilgisi;
	}

	public String getIcraDosyaNO() {

		return AktifBean.icraDosyaNo;
	}

	public void setIcraDosyaNO(String icraDosyaNO) {
		this.icraDosyaNO = icraDosyaNO;
	}

	public String getBorcluAdi() {
		return AktifBean.borcluAdi;
	}

	public void setBorcluAdi(String borcluAdi) {
		this.borcluAdi = borcluAdi;
	}

	public String getMuvekkliAdi() {
		return AktifBean.muvekkilAdi;
	}

	public ArrayList<Ilce> getIlceList() {
		return ilceList;
	}

	public void setIlceList(ArrayList<Ilce> ilceList) {
		this.ilceList = ilceList;
	}

	public boolean isPanelRender() {
		return panelRender;
	}

	public void setPanelRender(boolean panelRender) {
		this.panelRender = panelRender;
	}

	public boolean isButtonDisabled() {
		return buttonDisabled;
	}

	public void setButtonDisabled(boolean buttonDisabled) {
		this.buttonDisabled = buttonDisabled;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ArrayList<HaczeEsasMalBilgisi> getHemList() throws Exception {

		return dao.getAllListFromIcraDosyaID(AktifBean.borcluId);
	}

	public void setHemList(ArrayList<HaczeEsasMalBilgisi> hemList) {

		this.hemList = hemList;
	}

	public void ilceListeEkle() throws Exception {

		ilceList = new ArrayList<Ilce>();
		GenelSehirlerBean gs = new GenelSehirlerBean();
		int id = gs.getIlKoduFromName(haczeEsasMalBilgisi.getIlAdi());
		ilceList = gs.handleValueChange(id);

	}

	public void YeniKayit() {

		status = 0;
		haczeEsasMalBilgisi = new HaczeEsasMalBilgisi();
		PanelOpen();

	}

	public void PanelOpen() {

		this.setPanelRender(true);
		ButtonClose();

	}

	public void PanelClose() {

		this.setPanelRender(false);

	}

	public void ButtonOpen() {

		this.setButtonDisabled(false);

	}

	public void ButtonClose() {
		this.setButtonDisabled(true);

	}

	public void setMuvekkliAdi(String muvekkliAdi) {
		this.muvekkliAdi = muvekkliAdi;
	}

	public void Kaydet() throws Exception {

		FacesContext context = FacesContext.getCurrentInstance();

		if (haczeEsasMalBilgisi.getMalTipi() == "" && haczeEsasMalBilgisi.getMenkulBilgisi() == ""
				&& haczeEsasMalBilgisi.getMevduatBilgisi() == "" && haczeEsasMalBilgisi.getMuhatapAdi() == ""
				&& haczeEsasMalBilgisi.getMuhatapAdresi() == "" && haczeEsasMalBilgisi.getDigerBilgiler() == ""
				&& haczeEsasMalBilgisi.getIlAdi() == "" && haczeEsasMalBilgisi.getIlceAdi() == ""
				&& haczeEsasMalBilgisi.getTapuMahalleAdi() == "" && haczeEsasMalBilgisi.getTapuMulkTipi() == ""
				&& haczeEsasMalBilgisi.getTapuParsel() == "" && haczeEsasMalBilgisi.getTapuSayfaNo() == ""
				&& haczeEsasMalBilgisi.getTapuCiltNo() == "" && haczeEsasMalBilgisi.getAracPlakaNo() == ""
				&& haczeEsasMalBilgisi.getAracAracTipi() == "") {

			context.addMessage(null, new FacesMessage("Kaydetme işlemi için en az bir alan dolu olmalıdır!!!"));

		} else {

			if (status == 0) {

				boolean result = dao.kaydet(haczeEsasMalBilgisi);

				if (result) {
					// Pop up aıılmasını saılar
					context.addMessage(null, new FacesMessage("Kaydedildi!"));

				} else {

					context.addMessage(null, new FacesMessage("Kaydet işlemi Başarısız!"));

				}
			} else {
				
				boolean duzenlendi = dao.guncelle(haczeEsasMalBilgisi);

				if (duzenlendi) {
					context.addMessage(null, new FacesMessage("Düzenlendi!"));
				} else {
					context.addMessage(null, new FacesMessage("Gıncelleme işlemi baıarısız!"));
				}
				status = 0;
				hemList = dao.getAllListFromIcraDosyaID(AktifBean.borcluId);
			}

			PanelClose();
			ButtonOpen();

		}

	}

	public void Duzenle() throws Exception {

		status = 1;

		ArrayList<HaczeEsasMalBilgisi> list = dao.getAllListFromIcraDosyaID(AktifBean.borcluId);

		int id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("duzenle").toString());

		for (HaczeEsasMalBilgisi hem : list) {
			if (hem.getId() == id) {
				haczeEsasMalBilgisi = hem;
			}
		}
		hemList = dao.getAllListFromIcraDosyaID(AktifBean.borcluId);
		PanelOpen();
		ButtonClose();

	}

	public void Sil() throws Exception {

		FacesContext context = FacesContext.getCurrentInstance();

		int id = Integer.parseInt(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("sil").toString());

		int result = dao.Sil(id);

		if (result == 1) {

			context.addMessage(null, new FacesMessage("Silindi!"));
		} else {
			context.addMessage(null, new FacesMessage("Silme işlemi başarısız!"));
		}

	}

	public void Vazgec() {
		status = 0;
		PanelClose();
		ButtonOpen();

	}

}
