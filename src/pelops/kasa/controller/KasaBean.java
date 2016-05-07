package pelops.kasa.controller;

import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.context.RequestContext;

import pelops.controller.AktifBean;
import pelops.controller.GenelTanimBean;
import pelops.dao.GelismisAramaDAO;
import pelops.kasa.model.Reddiyat;
import pelops.kasa.model.ReddiyatView;
import pelops.kasa.model.Tahsilat;
import pelops.kasa.model.TahsilatViewModel;
import pelops.model.DetayliArama;
import pelops.model.GenelTanimSablon;
import pelops.model.Hesap;
import pelops.model.Kasa;
import pelops.util.Util;

@SessionScoped
@ManagedBean(name = "kasaBean")
public class KasaBean {

	private ArrayList<Kasa> kasaListesi = new ArrayList<Kasa>();
	private ArrayList<GenelTanimSablon> tahsilatStatuListesi = new ArrayList<GenelTanimSablon>();
	private ArrayList<GenelTanimSablon> odemeYeriListesi = new ArrayList<GenelTanimSablon>();
	private Kasa modelKasa = new Kasa();
	private ArrayList<DetayliArama> detayliAramaListesi = new ArrayList<DetayliArama>();
	private ArrayList<DetayliArama> filterDetayliAramaListesi;
	private Tahsilat bilgiTahsilat = new Tahsilat();
	private Date baslangicTarihi, bitisTarihi;
	private ArrayList<TahsilatViewModel> tahsilatYapilacakListe = new ArrayList<TahsilatViewModel>();
	private ArrayList<ReddiyatView> reddiyatListesi = new ArrayList<>();
	private KasaCtrl controller = new KasaCtrl();

	public ArrayList<ReddiyatView> getReddiyatListesi() {
		return reddiyatListesi;
	}

	public void setReddiyatListesi(ArrayList<ReddiyatView> reddiyatListesi) {
		this.reddiyatListesi = reddiyatListesi;
	}

	public ArrayList<TahsilatViewModel> getTahsilatYapilacakListe() throws Exception {
		return controller.getListeFromViewsForTahsilatIslemi(baslangicTarihi, bitisTarihi);
	}

	public void setTahsilatYapilacakListe(ArrayList<TahsilatViewModel> tahsilatYapilacakListe) {
		this.tahsilatYapilacakListe = tahsilatYapilacakListe;
	}

	public KasaCtrl getController() {
		return controller;
	}

	public void setController(KasaCtrl controller) {
		this.controller = controller;
	}

	public Date getBaslangicTarihi() {
		return baslangicTarihi;
	}

	public void setBaslangicTarihi(Date baslangicTarihi) {
		this.baslangicTarihi = baslangicTarihi;
	}

	public Date getBitisTarihi() {
		return bitisTarihi;
	}

	public void setBitisTarihi(Date bitisTarihi) {
		this.bitisTarihi = bitisTarihi;
	}

	public Tahsilat getBilgiTahsilat() {
		return bilgiTahsilat;
	}

	public void setBilgiTahsilat(Tahsilat bilgiTahsilat) {
		this.bilgiTahsilat = bilgiTahsilat;
	}

	public ArrayList<DetayliArama> getFilterDetayliAramaListesi() {
		return filterDetayliAramaListesi;
	}

	public void setFilterDetayliAramaListesi(ArrayList<DetayliArama> filterDetayliAramaListesi) {
		this.filterDetayliAramaListesi = filterDetayliAramaListesi;
	}

	public ArrayList<DetayliArama> getDetayliAramaListesi() {
		return detayliAramaListesi;
	}

	public void setDetayliAramaListesi(ArrayList<DetayliArama> detayliAramaListesi) {
		this.filterDetayliAramaListesi = detayliAramaListesi;
		this.detayliAramaListesi = detayliAramaListesi;
	}

	public ArrayList<GenelTanimSablon> getOdemeYeriListesi() throws Exception {
		GenelTanimBean gtlist = new GenelTanimBean();
		return gtlist.ListeGetir("tbl_tahsilat_tipi");
	}

	public void setOdemeYeriListesi(ArrayList<GenelTanimSablon> odemeYeriListesi) {
		this.odemeYeriListesi = odemeYeriListesi;
	}

	public ArrayList<GenelTanimSablon> getTahsilatStatuListesi() throws Exception {
		GenelTanimBean liste = new GenelTanimBean();
		return liste.ListeGetir("tbl_tahsilat_statusu");
	}

	public void setTahsilatStatuListesi(ArrayList<GenelTanimSablon> tahsilatStatuListesi) {
		this.tahsilatStatuListesi = tahsilatStatuListesi;
	}

	public ArrayList<Kasa> getKasaListesi() {
		return kasaListesi;
	}

	public void setKasaListesi(ArrayList<Kasa> kasaListesi) {
		this.kasaListesi = kasaListesi;
	}

	public Kasa getModelKasa() {
		return modelKasa;
	}

	public void setModelKasa(Kasa modelKasa) {
		this.modelKasa = modelKasa;
	}

	public KasaBean() throws Exception {

		//
		//
		// for (int i = 0; i < 16; i++) {
		// modelKasa = new Kasa();
		// modelKasa.setBorcluAdi("Muhammet Ali KAYA");
		// modelKasa.setIcraDosyaNo("2015/10");
		// String string = "15/03/2016";
		//
		// @SuppressWarnings("deprecation")
		// Date date = new Date(string);
		// modelKasa.setIzlemeTarihi(convertUtilToSql(date));
		// modelKasa.setPersonelAdi("Mehmet Can TOPUZ");
		// modelKasa.setIzlemeFiyat(1254.45);
		// kasaListesi.add(modelKasa);
		//
		// }

		String oldDate = "01/01/1900";
		Date tarih = new Date(oldDate);
		GelismisAramaDAO dao = new GelismisAramaDAO();
		detayliAramaListesi = dao.Listele("", "", "", "", "", "", 0, 0, 0, tarih, tarih, tarih, tarih, tarih, tarih);
		bilgiTahsilat = new Tahsilat();
		HttpSession session = Util.getSession();

		bilgiTahsilat.setKasa_islemini_yapan(session.getAttribute("user").toString());
		baslangicTarihi = new Date();
		Date tson = DateUtils.addMonths(new Date(), 1);
		bitisTarihi = tson;

		reddiyatListesi =  (ArrayList<ReddiyatView>) controller.getListefromView(0, 3, 1);
		reddiyatListesi.addAll(controller.getListefromView(0, 3, 2));
		reddiyatListesi.addAll(controller.getListefromView(0, 3, 3));

	}

	public void icraDosyaSec(int id) {

		RequestContext.getCurrentInstance().execute("PF('dlgdetayliarama').hide()");

		bilgiTahsilat.setBorclu_adi(AktifBean.getBorcluAdi());
		bilgiTahsilat.setIcra_dosya_no(AktifBean.getIcraDosyaNo());
		bilgiTahsilat.setIcra_dosyasi_id(AktifBean.getIcraDosyaID());
		bilgiTahsilat.setMuvekkil_adi(AktifBean.getMuvekkilAdi());
		HttpSession session = Util.getSession();

		bilgiTahsilat.setTasilati_yapan(session.getAttribute("user").toString());
	}

	public void dosyaal(int id) {

	}

	public void tahsilatYap() throws Exception {
		Hesap hesap = AktifBean.hesaplistesi;
		ArrayList<Reddiyat> redList = new ArrayList<>();
		boolean hitam = false;
		if ((hesap.getKalan_alacak() - bilgiTahsilat.getTahsilat_miktari() <= 1)) {
			hitam = true;
			for (int i = 1; i < 4; i++) {
				Reddiyat reddiyat = controller.createReddiyatForStatus(i, hesap, bilgiTahsilat);
				redList.add(reddiyat);
			}
		} else {
			hitam = false;
		}
		controller.kaydet(bilgiTahsilat, hitam, redList);

	}

	private static java.sql.Date convertUtilToSql(java.util.Date uDate) {

		java.sql.Date sDate = new java.sql.Date(uDate.getTime());

		return sDate;

	}

}
