package semiramis.operasyon.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import pelops.controller.AktifBean;
import semiramis.operasyon.dao.HaczeEsasMalBilgisiDAO;
import semiramis.operasyon.model.HaczeEsasMalBilgisi;
import semiramis.operasyon.model.HaczeEsasMalBilgisiView;

@ManagedBean(name = "haczeEsasMalBilgisiBean2", eager = true)
@SessionScoped
public class HaczeEsasMalBilgisiBean {

	public final static int MAL_EV = 1;
	public final static int MAL_ARAC = 3;
	public final static int MAL_SGK = 2;

	private String icraDosyaNO;
	private String borcluAdi;
	private String muvekkliAdi;

	private boolean panelArac = false;
	private boolean panelTapu = false;
	private boolean panelMaas = false;

	HaczeEsasMalBilgisiDAO hczEsasMalDAO;

	private boolean updatedVisible;

	private HaczeEsasMalBilgisi updatedHczEsasMal;

	private List<HaczeEsasMalBilgisiView> filteredHczEsasMallar;

	private List<HaczeEsasMalBilgisiView> hczEsasMallar;

	private int islem;

	public HaczeEsasMalBilgisiBean() {

		updatedVisible = false;

		updatedHczEsasMal = new HaczeEsasMalBilgisi();

		hczEsasMalDAO = new HaczeEsasMalBilgisiDAO();

		hczEsasMallar = hczEsasMalDAO.listeView(AktifBean.getBorcluId());

	}

	public void changePanel() {

		if (updatedHczEsasMal.getMalTipiId() == MAL_EV) {
			panelArac = false;
			panelMaas = false;
			panelTapu = true;
			updatedVisible = true;
		} else if (updatedHczEsasMal.getMalTipiId() == MAL_ARAC) {
			panelArac = true;
			panelMaas = false;
			panelTapu = false;

			updatedVisible = true;
		} else {
			panelArac = false;
			panelMaas = true;
			panelTapu = false;

			updatedVisible = true;
		}

	}

	public String getIslem() {

		String butonText = "";

		if (islem == 1) {

			butonText = "Güncelle";
		} else {

			butonText = "Ekle";
		}
		return butonText;
	}

	public void iptal() {

		updatedVisible = false;
	}

	public void guncellePanelAc(int id) { // Tablodaki guncelle
		// butonuna tiklandiginda
		// giris panelini acar ve
		// componentleri doldurur.

		islem = 1;
		updatedVisible = true;
		updatedHczEsasMal = hczEsasMalDAO.getT(id);
		
		changePanel();

	}

	/*
	 * Ekle butonuna tiklandiginda girisPaneli acar ve componentler bos gelir
	 */
	public void eklePanelAc() {

		islem = 0;
		updatedHczEsasMal = new HaczeEsasMalBilgisi();
		updatedVisible = true;
	}

	// sayfanin her acilisinda tum panellerin kapatilmasini saglayan metod
	public void panelKapat() {
		filteredHczEsasMallar = hczEsasMallar;
		updatedVisible = false;
	}

	public void kaydet() {

		if (islem != 1) {

			hczEsasMalDAO.kaydet(updatedHczEsasMal);

		} else {

			hczEsasMalDAO.guncelleme(updatedHczEsasMal);

		}

		hczEsasMallar = hczEsasMalDAO.listeView(AktifBean.getBorcluId());

		updatedVisible = false;

	}

	public void sil(int id) {

		hczEsasMalDAO.sil(id);
		hczEsasMallar = hczEsasMalDAO.listeView(AktifBean.getBorcluId());

	}

	public String getIcraDosyaNO() {

		return AktifBean.getIcraDosyaNo();
	}

	public void setIcraDosyaNO(String icraDosyaNO) {
		this.icraDosyaNO = icraDosyaNO;
	}

	public String getBorcluAdi() {
		return AktifBean.getBorcluAdi();
	}

	public void setBorcluAdi(String borcluAdi) {
		this.borcluAdi = borcluAdi;
	}

	public String getMuvekkliAdi() {
		return AktifBean.getMuvekkilAdi();
	}

	public boolean isUpdatedVisible() {
		return updatedVisible;
	}

	public void setUpdatedVisible(boolean updatedVisible) {
		this.updatedVisible = updatedVisible;
	}

	public HaczeEsasMalBilgisi getUpdatedHczEsasMal() {
		return updatedHczEsasMal;
	}

	public void setUpdatedHczEsasMal(HaczeEsasMalBilgisi updatedHczEsasMal) {
		this.updatedHczEsasMal = updatedHczEsasMal;
	}

	public List<HaczeEsasMalBilgisiView> getFilteredHczEsasMallar() {
		return filteredHczEsasMallar;
	}

	public void setFilteredHczEsasMallar(List<HaczeEsasMalBilgisiView> filteredHczEsasMallar) {
		this.filteredHczEsasMallar = filteredHczEsasMallar;
	}

	public List<HaczeEsasMalBilgisiView> getHczEsasMallar() {
		return hczEsasMallar;
	}

	public void setHczEsasMallar(List<HaczeEsasMalBilgisiView> hczEsasMallar) {
		this.hczEsasMallar = hczEsasMallar;
	}

	public boolean isPanelArac() {
		return panelArac;
	}

	public void setPanelArac(boolean panelArac) {
		this.panelArac = panelArac;
	}

	public boolean isPanelTapu() {
		return panelTapu;
	}

	public void setPanelTapu(boolean panelTapu) {
		this.panelTapu = panelTapu;
	}

	public boolean isPanelMaas() {
		return panelMaas;
	}

	public void setPanelMaas(boolean panelMaas) {
		this.panelMaas = panelMaas;
	}

}
