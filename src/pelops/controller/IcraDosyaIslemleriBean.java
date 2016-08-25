package pelops.controller;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;


import pelops.dao.AlacakliDAO;
import pelops.dao.BaglantiDAO;
import pelops.dao.BasvuruHarciDAO;
import pelops.dao.BorcluBilgisiDAO;
import pelops.dao.HarcBilgisiDAO;
import pelops.dao.HesapDAO;
import pelops.dao.IcraDosyasiDAO;
import pelops.dao.IcraMudurluguDAO;
import pelops.dao.LogErrorDAO;
import pelops.dao.VekaletHarciDAO;
import pelops.dao.VekaletSinirlariDAO;
import pelops.model.AlacakliBilgiler;
import pelops.model.Baglanti;
import pelops.model.BorcluBilgisi;
import pelops.model.HarcBilgisi;
import pelops.model.Hesap;
import pelops.model.HesaplarList;
import pelops.model.IcraDosyasi;
import pelops.model.Ilce;
import pelops.model.LogError;

@ManagedBean(name = "icradosyaislemleribean")
@SessionScoped
public class IcraDosyaIslemleriBean {

	
	private String dosyaStatusu="Dosya Seçilmedi!";
	private String dosyaStatutDurum="";
	
	private boolean showPic=false;
	
	
	LogErrorDAO log = new LogErrorDAO();
	Date nowDate = new Date();
	LogError newlog;
	FacesContext context = FacesContext.getCurrentInstance();

	private ArrayList<HesaplarList> hesaplarlistesi;

	private boolean isdialogsVisible = true;

	private int HesapTumId;

	private String dialogUrl = "dlg_common";

	public boolean getIsdialogsVisible() {
		return isdialogsVisible;
	}

	public void setIsdialogsVisible(boolean isdialogsVisible) {
		this.isdialogsVisible = isdialogsVisible;
	}

	public void setDialogPage(String arg) {
		setDialogUrl(arg);
	}

	public String getDialogUrl() {
		return dialogUrl;
	}

	public void setDialogUrl(String dialogUrl) {
		this.dialogUrl = dialogUrl;
	}

	public int getHesapTumId() {
		return HesapTumId;
	}

	public void setHesapTumId(int hesapTumId) {
		HesapTumId = hesapTumId;
	}

	public ArrayList<HesaplarList> getHesaplarlistesi() {
		return hesaplarlistesi;
	}

	public void setHesaplarlistesi(ArrayList<HesaplarList> hesaplarlistesi) {
		this.hesaplarlistesi = hesaplarlistesi;
	}

	private ArrayList<Ilce> ilceList;

	public ArrayList<Ilce> getIlceList() {
		return ilceList;
	}

	public void setIlceList(ArrayList<Ilce> ilceList) {
		this.ilceList = ilceList;
	}

	public void ilceListeEkle() throws SQLException {

		ilceList = new ArrayList<Ilce>();
		GenelSehirlerBean gs = new GenelSehirlerBean();
		ilceList = gs.handleValueChange(borclubilgisi.getIlId());

	}

	private int AramaIcraDosyasiID;

	public int getAramaIcraDosyasiID() {
		return AramaIcraDosyasiID;
	}

	public void setAramaIcraDosyasiID(int aramaIcraDosyasiID) {
		AramaIcraDosyasiID = aramaIcraDosyasiID;
	}

	private IcraDosyasi icradosyasi;
	private BorcluBilgisi borclubilgisi;
	private int AracSayisi = 0;
	private int EvSayisi = 0;
	private Date hesapTarihi = new Date();

	private double basvuruHarciTL;

	private double vekaletHarciTL;

	private double pesinHarcTL;

	private double harcoranTL = 2.27;

	public double getHarcoranTL() {
		return harcoranTL;
	}

	public void setHarcoranTL(double harcoranTL) {
		this.harcoranTL = harcoranTL;
	}

	public double getBasvuruHarciTL() {
		return basvuruHarciTL;
	}

	public void setBasvuruHarciTL(double basvuruHarciTL) {
		this.basvuruHarciTL = basvuruHarciTL;
	}

	public double getVekaletHarciTL() {
		return vekaletHarciTL;
	}

	public void setVekaletHarciTL(double vekaletHarciTL) {
		this.vekaletHarciTL = vekaletHarciTL;
	}

	public double getPesinHarcTL() {
		return pesinHarcTL;
	}

	public void setPesinHarcTL(double pesinHarcTL) {
		this.pesinHarcTL = pesinHarcTL;
	}

	public Date getHesapTarihi() {
		return hesapTarihi;
	}

	public void setHesapTarihi(Date hesapTarihi) {
		this.hesapTarihi = hesapTarihi;
	}

	private String Ev = "img/acikgri.png", Arac = "img/acikgri.png", Para = "img/acikgri.png",
			Haciz = "img/acikgri.png", Itiraz = "img/acikgri.png", Istahbarat = "img/acikgri.png",
			Bila = "img/acikgri.png", Hitam = "img/acikgri.png", Temlik = "img/acikgri.png", Vefat = "img/acikgri.png";

	private String Arac_1 = "img/acikgri.png", Arac_2 = "img/acikgri.png", Arac_3 = "img/acikgri.png",
			Arac_4 = "img/acikgri.png";
	private String Ev_1 = "img/acikgri.png", Ev_2 = "img/acikgri.png", Ev_3 = "img/acikgri.png",
			Ev_4 = "img/acikgri.png";

	private String kart_1 = "img/hsbc_kart.jpg", kart2 = "img/HSBC-GOLD.jpg", kart_3 = "img/HSBC-PLATİNİUM.jpg",
			kart_4 = "HSBC-PREMİER.jpg";

	public String getKart_1() {
		return kart_1;
	}

	public void setKart_1(String kart_1) {
		this.kart_1 = kart_1;
	}

	public String getKart2() {
		return kart2;
	}

	public void setKart2(String kart2) {
		this.kart2 = kart2;
	}

	public String getKart_3() {
		return kart_3;
	}

	public void setKart_3(String kart_3) {
		this.kart_3 = kart_3;
	}

	public String getKart_4() {
		return kart_4;
	}

	public void setKart_4(String kart_4) {
		this.kart_4 = kart_4;
	}

	public String getArac_1() {
		return Arac_1;
	}

	public void setArac_1(String arac_1) {
		Arac_1 = arac_1;
	}

	public String getArac_2() {
		return Arac_2;
	}

	public void setArac_2(String arac_2) {
		Arac_2 = arac_2;
	}

	public String getArac_3() {
		return Arac_3;
	}

	public void setArac_3(String arac_3) {
		Arac_3 = arac_3;
	}

	public String getArac_4() {
		return Arac_4;
	}

	public void setArac_4(String arac_4) {
		Arac_4 = arac_4;
	}

	public String getEv_1() {
		return Ev_1;
	}

	public void setEv_1(String ev_1) {
		Ev_1 = ev_1;
	}

	public String getEv_2() {
		return Ev_2;
	}

	public void setEv_2(String ev_2) {
		Ev_2 = ev_2;
	}

	public String getEv_3() {
		return Ev_3;
	}

	public void setEv_3(String ev_3) {
		Ev_3 = ev_3;
	}

	public String getEv_4() {
		return Ev_4;
	}

	public void setEv_4(String ev_4) {
		Ev_4 = ev_4;
	}

	public String getEv() {
		return Ev;
	}

	public void setEv(String ev) {
		Ev = ev;
	}

	public String getArac() {
		return Arac;
	}

	public String getPara() {
		return Para;
	}

	public void setPara(String para) {
		Para = para;
	}

	public String getHaciz() {
		return Haciz;
	}

	public void setHaciz(String haciz) {
		Haciz = haciz;
	}

	public String getItiraz() {
		return Itiraz;
	}

	public void setItiraz(String itiraz) {
		Itiraz = itiraz;
	}

	public String getIstahbarat() {
		return Istahbarat;
	}

	public void setIstahbarat(String istahbarat) {
		Istahbarat = istahbarat;
	}

	public String getBila() {
		return Bila;
	}

	public void setBila(String bila) {
		Bila = bila;
	}

	public String getHitam() {
		return Hitam;
	}

	public void setHitam(String hitam) {
		Hitam = hitam;
	}

	public String getTemlik() {
		return Temlik;
	}

	public void setTemlik(String temlik) {
		Temlik = temlik;
	}

	public String getVefat() {
		return Vefat;
	}

	public void setVefat(String vefat) {
		Vefat = vefat;
	}

	public void setArac(String arac) {
		Arac = arac;
	}

	public int getAracSayisi() {
		return AracSayisi;
	}

	public void setAracSayisi(int aracSayisi) {
		AracSayisi = aracSayisi;
	}

	public int getEvSayisi() {
		return EvSayisi;
	}

	public void setEvSayisi(int evSayisi) {
		EvSayisi = evSayisi;
	}

	private Baglanti baglanti;
	private Hesap hesap;
	private int alacakliID;
	private String icraDosyaNo;
	private int icraMudurluguID;

	public int getIcraMudurluguID() {
		return icraMudurluguID;
	}

	public void setIcraMudurluguID(int icraMudurluguID) {
		this.icraMudurluguID = icraMudurluguID;
	}

	public String getIcraDosyaNo() {
		return icraDosyaNo;
	}

	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
	}

	public int getAlacakliID() {
		return alacakliID;
	}

	public void setAlacakliID(int alacakliID) {
		this.alacakliID = alacakliID;
	}

	public BorcluBilgisi getBorclubilgisi() {
		return borclubilgisi;
	}

	public void setBorclubilgisi(BorcluBilgisi borclubilgisi) {
		this.borclubilgisi = borclubilgisi;
	}

	public Baglanti getBaglanti() {
		return baglanti;
	}

	public void setBaglanti(Baglanti baglanti) {
		this.baglanti = baglanti;
	}

	public Hesap getHesap() {
		return hesap;
	}

	public void setHesap(Hesap hesap) {
		this.hesap = hesap;
	}

	public IcraDosyasi getIcradosyasi() {
		return icradosyasi;
	}

	public void setIcradosyasi(IcraDosyasi icradosyasi) {
		this.icradosyasi = icradosyasi;
	}

	private Date Tarih, Tarih1, Tarih2;

	public Date getTarih1() {
		return Tarih1;
	}

	public void setTarih1(Date tarih1) {
		Tarih1 = tarih1;
	}

	public Date getTarih2() {
		return Tarih2;
	}

	public void setTarih2(Date tarih2) {
		Tarih2 = tarih2;
	}

	public IcraDosyaIslemleriBean() throws Exception {

		icradosyasi = new IcraDosyasi();
		hesap = new Hesap();
		baglanti = new Baglanti();
		borclubilgisi = new BorcluBilgisi();
		//plakaGetir();
		hesaplarlistesi = new ArrayList<HesaplarList>();
		icradosyasi.setBK84("E");
		icradosyasi.setKKDF("H");
		icradosyasi.setBSMV("E");
		icradosyasi.setTakipSekliId(0);
		icradosyasi.setTakipTipiId(1);
		icradosyasi.setTalepEdilenHak(
				"Alacağımızın asıl alacağa takip tarihinden tahsiline kadar sözleşme gereğince takip tarihinden itibaren işleyecek %30.24 temerrüt faizi, faizin %5 gider vergisi, icra harç ve masrafları, avukatlık vekalet ücreti ile birlikte, TBK.Madde 100 gereğince kısmi ödemelerin öncelikle faiz ve masraflara mahsup edileceği, (artan faiz oranlarının tatbiki kaydı ile talep) haklarımız saklı kalmak ve tahsilde tekerrür etmemek kaydı ile tahsili talebidir.");
		borclubilgisi.setAdresTuruId(9);
		icradosyasi.setTakipYoluId(0);

		icradosyasi.setDosyaTipiId(0);
		borclubilgisi.setTelefon_no3("0000000000");
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	}

	public Date getTarih() {
		return Tarih;
	}

	public void setTarih(Date tarih) {
		Tarih = tarih;
	}

	private IcraDosyasi icradosyasilistesi = new IcraDosyasi();

	public IcraDosyasi getIcradosyasilistesi() {
		return icradosyasilistesi;
	}

	public void setIcradosyasilistesi(IcraDosyasi icradosyasilistesi) {
		this.icradosyasilistesi = icradosyasilistesi;
	}

	public BorcluBilgisi getBorclubilgisilistesi() {
		return borclubilgisilistesi;
	}

	public void setBorclubilgisilistesi(BorcluBilgisi borclubilgisilistesi) {
		this.borclubilgisilistesi = borclubilgisilistesi;
	}

	public Hesap getHesaplistesi() {
		return hesaplistesi;
	}

	public void setHesaplistesi(Hesap hesaplistesi) {
		this.hesaplistesi = hesaplistesi;
	}

	private BorcluBilgisi borclubilgisilistesi = new BorcluBilgisi();
	private Hesap hesaplistesi = new Hesap();
	private AlacakliBilgiler alacaklilistesi = new AlacakliBilgiler();

	public AlacakliBilgiler getAlacaklilistesi() {
		return alacaklilistesi;
	}

	public void setAlacaklilistesi(AlacakliBilgiler alacaklilistesi) {
		this.alacaklilistesi = alacaklilistesi;
	}

	public void Kaydet() throws Exception {

		IcraDosyasiDAO daoicra = new IcraDosyasiDAO();
		int icradosyaid = 0;
		icradosyasi.setGelisTarihi(new Date());

		icradosyaid = daoicra.Kaydet(icradosyasi);

		pelops.controller.AktifBean.setIcraDosyaNo(icradosyasi.getIcraDosyaNo());
		pelops.controller.AktifBean.setIcraDosyaID(icradosyaid);

		hesap.setIcra_dosyasi(icradosyaid);

		BorcluBilgisiDAO daoborclu = new BorcluBilgisiDAO();
		AlacakliDAO daoalacakli = new AlacakliDAO();

		int borcluid = 0;
		borclubilgisi.setAdSoyad(borclubilgisi.getAd() + " " + borclubilgisi.getSoyad());
		borcluid = daoborclu.saveBorclu(borclubilgisi);
		pelops.controller.AktifBean.setBorcluId(borcluid);
		pelops.controller.AktifBean.setMuvekkilAdi(daoalacakli.ListeGetir(alacakliID).getMuvekkilAdi());
		pelops.controller.AktifBean.setBorcluAdi(daoborclu.Liste(borcluid).getAdSoyad());

		HesapDAO daohesap = new HesapDAO();
		int hesapid = 0;
		hesapid = daohesap.Kaydet(hesap);

		baglanti.setAlacakliID(alacakliID);
		baglanti.setBorcluID(borcluid);
		baglanti.setHesaplamaID(hesapid);
		baglanti.setIcradosyasiID(icradosyaid);

		BaglantiDAO daobaglanti = new BaglantiDAO();
		daobaglanti.Kaydet(baglanti);
	

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Kayıt İşlemi Başarı İle Gerçekleştirildi..."));

		icradosyasi = new IcraDosyasi();
		hesap = new Hesap();
		baglanti = new Baglanti();
		borclubilgisi = new BorcluBilgisi();
		//plakaGetir();
		hesaplarlistesi = new ArrayList<HesaplarList>();
		icradosyasi.setBK84("E");
		icradosyasi.setKKDF("H");
		icradosyasi.setBSMV("E");
		icradosyasi.setTakipSekliId(0);
		icradosyasi.setTakipTipiId(1);
		icradosyasi.setTalepEdilenHak(
				"Alacağımızın asıl alacağa takip tarihinden tahsiline kadar sözleşme gereğince takip tarihinden itibaren işleyecek %30.24 temerrüt faizi, faizin %5 gider vergisi, icra harç ve masrafları, avukatlık vekalet ücreti ile birlikte, TBK.Madde 100 gereğince kısmi ödemelerin öncelikle faiz ve masraflara mahsup edileceği, (artan faiz oranlarının tatbiki kaydı ile talep) haklarımız saklı kalmak ve tahsilde tekerrür etmemek kaydı ile tahsili talebidir.");
		borclubilgisi.setAdresTuruId(9);
		icradosyasi.setTakipYoluId(0);

		icradosyasi.setDosyaTipiId(0);
		borclubilgisi.setTelefon_no3("0000000000");

		alacakliID = 0;
		icraDosyaNo = "";
		icraMudurluguID = 0;
	}

	public void IcraDosyasiKaydet() throws Exception {

		IcraDosyasiDAO daoicra = new IcraDosyasiDAO();
		int icradosyaid = 0;

		icradosyaid = daoicra.Kaydet(icradosyasi);

		pelops.controller.AktifBean.setIcraDosyaNo(icradosyasi.getIcraDosyaNo());
		pelops.controller.AktifBean.setIcraDosyaID(icradosyaid);

		hesap.setIcra_dosyasi(icradosyaid);

		BorcluBilgisiDAO daoborclu = new BorcluBilgisiDAO();
		AlacakliDAO daoalacakli = new AlacakliDAO();

		int borcluid = 0;
		borcluid = daoborclu.saveBorclu(borclubilgisi);
		pelops.controller.AktifBean.setBorcluId(borcluid);
		pelops.controller.AktifBean.setMuvekkilAdi(daoalacakli.ListeGetir(alacakliID).getMuvekkilAdi());
		pelops.controller.AktifBean.setBorcluAdi(daoborclu.Liste(borcluid).getAdSoyad());

		HesapDAO daohesap = new HesapDAO();
		int hesapid = 0;
		hesapid = daohesap.Kaydet(hesap);

		baglanti.setAlacakliID(alacakliID);
		baglanti.setBorcluID(borcluid);
		baglanti.setHesaplamaID(hesapid);
		baglanti.setIcradosyasiID(icradosyaid);

		BaglantiDAO daobaglanti = new BaglantiDAO();
		daobaglanti.Kaydet(baglanti);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Kayıt İşlemi Başari İle Gerçekleştirildi..."));

		icradosyasi = new IcraDosyasi();
		hesap = new Hesap();
		baglanti = new Baglanti();
		borclubilgisi = new BorcluBilgisi();
		alacakliID = 0;
		icraDosyaNo = "";
		icraMudurluguID = 0;
	}

	public void guncelleHaciz() throws Exception {

		IcraDosyasiDAO icraDAO = new IcraDosyasiDAO();
		icraDAO.GenelHacizBilgisiGuncelle(icradosyasilistesi);
		
		GelismisListe(AktifBean.icraDosyaID);

	}

	public void guncelleTarih() throws Exception {
		IcraDosyasiDAO icraDAO = new IcraDosyasiDAO();
		icraDAO.TarihBilgisiGuncelle(icradosyasilistesi);

	}

	public void guncelleItiraz() throws Exception {
		IcraDosyasiDAO icraDAO = new IcraDosyasiDAO();
		icraDAO.ItirazBilgisiGuncelle(icradosyasilistesi);
		itirazDurum();
	}

	public void guncelleBorclu() throws Exception {
		IcraDosyasiDAO icraDAO = new IcraDosyasiDAO();
		icraDAO.BorcluBilgisiGuncelle(borclubilgisilistesi);

	}

	public void Liste() throws Exception {

		String icradosyano = icraDosyaNo;
		int icraMudurluguId = icraMudurluguID;
		IcraDosyasiDAO icradosyasidao = new IcraDosyasiDAO();
		int icradosyaID = icradosyasidao.IcraDosyaIdGetir(icradosyano, icraMudurluguId);

		if (icradosyaID == 0) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ÜZGÜNÜZ !!!",
					"Girmiş olduğunuz Kriterlere Uygun Kayıt Bulunamamıştır.");
			RequestContext.getCurrentInstance().showMessageInDialog(message);

		} else {
			pelops.controller.AktifBean.setIcraDosyaNo(icradosyano);
			pelops.controller.AktifBean.setIcraDosyaID(icradosyaID);

			BaglantiDAO baglantidao = new BaglantiDAO();
			int borclubilgisiID = baglantidao.Listele(icradosyaID).getBorcluID();

			int hesapID = baglantidao.Listele(icradosyaID).getHesaplamaID();
			AktifBean.setHesapID(hesapID);

			int alacakliID = baglantidao.Listele(icradosyaID).getAlacakliID();

			BorcluBilgisiDAO daoborclu = new BorcluBilgisiDAO();
			AlacakliDAO daoalacakli = new AlacakliDAO();

			pelops.controller.AktifBean.setBorcluId(borclubilgisiID);
			pelops.controller.AktifBean.setMuvekkilAdi(daoalacakli.ListeGetir(alacakliID).getMuvekkilAdi());
			pelops.controller.AktifBean.setBorcluAdi(daoborclu.Liste(borclubilgisiID).getAdSoyad());

			icradosyasilistesi = icradosyasidao.Listele(icradosyaID);
			
			dosyaStatusu=icradosyasidao.getDosyaStatusu(icradosyasilistesi.getDosyaStatusuId());

			BorcluBilgisiDAO borcludao = new BorcluBilgisiDAO();
			borclubilgisilistesi = borcludao.Liste(borclubilgisiID);
			HesapDAO hesapdao = new HesapDAO();
			hesaplistesi = hesapdao.Liste(hesapID);

			AlacakliDAO alacaklidao = new AlacakliDAO();
			alacaklilistesi = alacaklidao.ListeGetir(alacakliID);
			//plakaGetir();
			Hesapla();
		}

	}

	private static int gelismisgetir;

	public void listelegelismis() {
		GelismisListe(gelismisgetir);
	}

	public void GelismisListe(int id) {
		try {

			gelismisgetir = id;
			IcraDosyasiDAO icradosyasidao = new IcraDosyasiDAO();

			IcraDosyasi icraDosyasi = icradosyasidao.Listele(id);
			
			dosyaStatusu=icradosyasidao.getDosyaStatusu(icraDosyasi.getDosyaStatusuId());
			
			showPic=true;
			
			if(dosyaStatusu.toLowerCase().equals("derdest"))
			{
				showPic=false;
			}
			
			
			

			String icradosyano = icraDosyasi.getIcraDosyaNo();

			if (icraDosyasi.getIcraMudurluguId() == 0) {

				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Üzgünüz !!!",
						"Seçmiş olduğunuz dosyanın icra müdürlüğü ve icra dosya numarası tanımlı değildir!.");
				RequestContext.getCurrentInstance().showMessageInDialog(message);

			} else {
				AktifBean.setIcraMudurlugu(
						(new IcraMudurluguDAO()).Liste(icraDosyasi.getIcraMudurluguId()).get(0).getAdi());
				int icradosyaID = id;
				icradosyasi.setId(id);

				if (icradosyaID == 0) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Üzgünüz !!!",
							"Girmiş Olduğunuz Kritere Uygun Kayıt Bulunamamıştır.");
					RequestContext.getCurrentInstance().showMessageInDialog(message);

				} else {
					pelops.controller.AktifBean.setIcraDosyaNo(icradosyano);
					pelops.controller.AktifBean.setIcraDosyaID(icradosyaID);
					BaglantiDAO baglantidao = new BaglantiDAO();

					Baglanti baglanti = baglantidao.Listele(icradosyaID);

					int borclubilgisiID = baglanti.getBorcluID();

					int alacakliID = baglanti.getAlacakliID();

					BorcluBilgisiDAO daoborclu = new BorcluBilgisiDAO();
					AlacakliDAO daoalacakli = new AlacakliDAO();

					pelops.controller.AktifBean.setBorcluId(borclubilgisiID);
					pelops.controller.AktifBean.setMuvekkilAdi(daoalacakli.ListeGetir(alacakliID).getMuvekkilAdi());
					pelops.controller.AktifBean.setBorcluAdi(daoborclu.Liste(borclubilgisiID).getAdSoyad());
					pelops.controller.AktifBean.setMusteriNo(daoborclu.Liste(borclubilgisiID).getMusteriNo());
					icradosyasilistesi = icradosyasidao.Listele(icradosyaID);

					BorcluBilgisiDAO borcludao = new BorcluBilgisiDAO();
					borclubilgisilistesi = borcludao.Liste(borclubilgisiID);

					hesaplarlistesi.clear();
					HesaplarList hslist = new HesaplarList();
					hslist.setHesapAdi("Tüm Hesap Tutarı");
					hslist.setHesapId(99);
					hesaplarlistesi.add(hslist);

					for (int i = 0; i < baglantidao.BaglantiListele(icradosyaID).size(); i++) {

						hslist = new HesaplarList();
						hslist.setHesapAdi((i + 1) + ". Hesap Tutarı");
						hslist.setHesapId(baglantidao.BaglantiListele(icradosyaID).get(i).getHesaplamaID());

						hesaplarlistesi.add(hslist);
					}

					int hesapID = baglanti.getHesaplamaID();
					AktifBean.setHesapID(hesapID);
					HesapDAO hesapdao = new HesapDAO();
					hesaplistesi = hesapdao.Liste(hesapID);
					hesaplistesi.setId(hesapID);

					AlacakliDAO alacaklidao = new AlacakliDAO();
					alacaklilistesi = alacaklidao.ListeGetir(alacakliID);

					hesapTarihi = new Date();

					//plakaGetir();

					Hesapla();
					refreshPanelVisible();
				}
			}

		} 
			catch (Exception e) {
				
				
				System.out.println("Hata icraDosyaIslemleri GelismisListe :"+e.getMessage());
				
			}

	}

	public void HesapGetir() throws Exception {
		if (HesapTumId == 99) {
			HesapDAO hesapdao = new HesapDAO();
			Hesap tmpHesap = new Hesap();
			for (int i = 1; i < hesaplarlistesi.size(); i++) {

				tmpHesap.setAsil_alacak((tmpHesap.getAsil_alacak()
						+ hesapdao.Liste(hesaplarlistesi.get(i).getHesapId()).getAsil_alacak()));
				tmpHesap.setGecikme_faizi(tmpHesap.getGecikme_faizi()
						+ hesapdao.Liste(hesaplarlistesi.get(i).getHesapId()).getGecikme_faizi());
				tmpHesap.setTemerrut_faizi(tmpHesap.getTemerrut_faizi()
						+ hesapdao.Liste(hesaplarlistesi.get(i).getHesapId()).getTemerrut_faizi());
				tmpHesap.setFaiz_gider_vergisi(tmpHesap.getFaiz_gider_vergisi()
						+ hesapdao.Liste(hesaplarlistesi.get(i).getHesapId()).getFaiz_gider_vergisi());
				tmpHesap.setMasraf_tutari(tmpHesap.getMasraf_tutari()
						+ hesapdao.Liste(hesaplarlistesi.get(i).getHesapId()).getMasraf_tutari());
			}
			hesaplistesi = tmpHesap;
			hesaplistesi.setId(99);
			AktifBean.setHesapID(99);

		} else {

			AktifBean.setHesapID(HesapTumId);
			HesapDAO hesapdao = new HesapDAO();
			hesaplistesi = hesapdao.Liste(HesapTumId);
			hesaplistesi.setId(HesapTumId);

		}

		Hesapla();
	}

	@SuppressWarnings({ "deprecation", "unused" })
	public void Hesapla() throws Exception {

		VekaletSinirlariDAO vekaletsinirlar = new VekaletSinirlariDAO();
		VekaletHarciDAO vekaletharcidao = new VekaletHarciDAO();
		BasvuruHarciDAO basvuruharcidao = new BasvuruHarciDAO();
		int yil = Year.now().getValue();
		BaglantiDAO baglantidao = new BaglantiDAO();
		Date bugun = new Date();
		// hesaplistesi.setId(
		// baglantidao.Listele(pelops.controller.AktifBean.getIcraDosyaID()).getHesaplamaID());

		double asilalacak = hesaplistesi.getAsil_alacak();
		double gecikme = hesaplistesi.getGecikme_faizi();
		double temerrut = hesaplistesi.getTemerrut_faizi();
		double faizgider = hesaplistesi.getFaiz_gider_vergisi();
		double noter = hesaplistesi.getMasraf_tutari();

		double takipalacagi = asilalacak + gecikme + temerrut + faizgider + noter;

		hesaplistesi.setTakip_alacagi(takipalacagi);

		if (hesaplistesi.getTakip_alacagi() <= vekaletsinirlar.Liste(yil).getSinir_1()) {
			hesaplistesi
					.setVekalet_ucreti(hesaplistesi.getTakip_alacagi() * vekaletsinirlar.Liste(yil).getYuzde_1() / 100);

		} else {
			if (hesaplistesi.getTakip_alacagi() <= vekaletsinirlar.Liste(yil).getSinir_2()) {
				hesaplistesi.setVekalet_ucreti(
						(vekaletsinirlar.Liste(yil).getSinir_1() * vekaletsinirlar.Liste(yil).getYuzde_1() / 100)
								+ (hesaplistesi.getTakip_alacagi() - vekaletsinirlar.Liste(yil).getSinir_1())
										* vekaletsinirlar.Liste(yil).getYuzde_2() / 100);
			} else {
				if (hesaplistesi.getTakip_alacagi() <= vekaletsinirlar.Liste(yil).getSinir_3()) {
					hesaplistesi.setVekalet_ucreti(
							(vekaletsinirlar.Liste(yil).getSinir_2() * vekaletsinirlar.Liste(yil).getYuzde_2() / 100)
									+ (hesaplistesi.getTakip_alacagi() - vekaletsinirlar.Liste(yil).getSinir_2())
											* vekaletsinirlar.Liste(yil).getYuzde_3() / 100);
				} else {
					if (hesaplistesi.getTakip_alacagi() <= vekaletsinirlar.Liste(yil).getSinir_4()) {
						hesaplistesi.setVekalet_ucreti((vekaletsinirlar.Liste(yil).getSinir_3()
								* vekaletsinirlar.Liste(yil).getYuzde_3() / 100)
								+ (hesaplistesi.getTakip_alacagi() - vekaletsinirlar.Liste(yil).getSinir_3())
										* vekaletsinirlar.Liste(yil).getYuzde_4() / 100);

					} else {
						if (hesaplistesi.getTakip_alacagi() <= vekaletsinirlar.Liste(yil).getSinir_5()) {
							hesaplistesi.setVekalet_ucreti((vekaletsinirlar.Liste(yil).getSinir_4()
									* vekaletsinirlar.Liste(yil).getYuzde_4() / 100)
									+ (hesaplistesi.getTakip_alacagi() - vekaletsinirlar.Liste(yil).getSinir_4())
											* vekaletsinirlar.Liste(yil).getYuzde_5() / 100);
						} else {
							if (hesaplistesi.getTakip_alacagi() <= vekaletsinirlar.Liste(yil).getSinir_6()) {
								hesaplistesi.setVekalet_ucreti((vekaletsinirlar.Liste(yil).getSinir_5()
										* vekaletsinirlar.Liste(yil).getYuzde_5() / 100)
										+ (hesaplistesi.getTakip_alacagi() - vekaletsinirlar.Liste(yil).getSinir_5())
												* vekaletsinirlar.Liste(yil).getYuzde_6() / 100);
							} else {

								if (hesaplistesi.getTakip_alacagi() <= vekaletsinirlar.Liste(yil).getSinir_7()) {
									hesaplistesi.setVekalet_ucreti((vekaletsinirlar.Liste(yil).getSinir_6()
											* vekaletsinirlar.Liste(yil).getYuzde_6() / 100)
											+ (hesaplistesi.getTakip_alacagi()
													- vekaletsinirlar.Liste(yil).getSinir_6())
													* vekaletsinirlar.Liste(yil).getYuzde_7() / 100);
								} else {
									hesaplistesi.setVekalet_ucreti((vekaletsinirlar.Liste(yil).getSinir_7()
											* vekaletsinirlar.Liste(yil).getYuzde_7() / 100)
											+ (hesaplistesi.getTakip_alacagi()
													- vekaletsinirlar.Liste(yil).getSinir_7())
													* vekaletsinirlar.Liste(yil).getYuzde_8() / 100);

								}

							}

						}

					}

				}

			}

		}

		if (takipalacagi < vekaletsinirlar.Liste(yil).getVekalet_ucret_sinir()) {
			if (asilalacak < vekaletsinirlar.Liste(yil).getVekalet_ucret()) {
				hesaplistesi.setVekalet_ucreti(asilalacak);
			} else {
				hesaplistesi.setVekalet_ucreti(vekaletsinirlar.Liste(yil).getVekalet_ucret());

			}

		}

		long Fark;
		Date ilkTarih = icradosyasilistesi.getTakipTarihi();
		Date sonTarih = new Date();

		if (hesapTarihi.getDate() != bugun.getDate()) {
			sonTarih = hesapTarihi;
		}

		if (sonTarih == null || ilkTarih == null)
			Fark = 0;
		else
			Fark = (sonTarih.getTime() - ilkTarih.getTime());

		int gun = (int) (Fark / (1000 * 60 * 60 * 24));

		if (gun <= 7) {

			hesaplistesi.setVekalet_ucreti(hesaplistesi.getVekalet_ucreti() * 3 / 4);
		}

		double faiz = hesaplistesi.getTakip_alacagi() * gun / 360 * hesaplistesi.getTemerrut_faiz_orani() / 100;

		hesaplistesi.setTakip_faizi(faiz);

		hesaplistesi.setFaiz_gider_vergisi2(faiz * 5 / 100);

		double basvuruHarci = basvuruharcidao.liste(yil).getTutar();

		double vekaletHarci = vekaletharcidao.liste(yil).getTutar();

		double pesinHarc = hesaplistesi.getTakip_alacagi() * 0.5 / 100;

		double digerHarc = basvuruHarci + vekaletHarci + pesinHarc + toplamharc();

		double harcoran = this.getHarcoranTL();

		this.setPesinHarcTL(pesinHarc);
		this.setBasvuruHarciTL(basvuruHarci);
		this.setVekaletHarciTL(vekaletHarci);

		double tahsil_harci = (hesaplistesi.getTakip_alacagi() * harcoran / 100) - pesinHarc;

		hesaplistesi.setTahsil_harci(tahsil_harci);

		hesaplistesi.setDiger_harclar(digerHarc);

		hesaplistesi.setToplam_alacak(hesaplistesi.getTakip_alacagi() + hesaplistesi.getVekalet_ucreti()
				+ hesaplistesi.getTakip_faizi() + hesaplistesi.getFaiz_gider_vergisi2()
				+ hesaplistesi.getDiger_harclar() + hesaplistesi.getMasraf_tutari() + hesaplistesi.getTahsil_harci());

		hesaplistesi.setKalan_alacak(
				hesaplistesi.getToplam_alacak() - hesaplistesi.getTahsilat_tutari() - hesaplistesi.getIndirim_tutari());

		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();

		hesaplistesi.setAsil_alacak_tl(defaultFormat.format(hesaplistesi.getAsil_alacak()));
		hesaplistesi.setDiger_harclar_tl(defaultFormat.format(hesaplistesi.getDiger_harclar()));
		hesaplistesi.setFaiz_gider_vergisi_tl(defaultFormat.format(hesaplistesi.getFaiz_gider_vergisi()));
		hesaplistesi.setFaiz_gider_vergisi2_tl(defaultFormat.format(hesaplistesi.getFaiz_gider_vergisi2()));
		hesaplistesi.setGecikme_faizi_tl(defaultFormat.format(hesaplistesi.getGecikme_faizi()));
		hesaplistesi.setIndirim_faiz_orani_tl(defaultFormat.format(hesaplistesi.getIndirim_faiz_orani()));
		hesaplistesi.setIndirim_tutari_tl(defaultFormat.format(hesaplistesi.getIndirim_tutari()));
		hesaplistesi.setKalan_alacak_tl(defaultFormat.format(hesaplistesi.getKalan_alacak()));
		hesaplistesi.setMasraf_tutari_tl(defaultFormat.format(hesaplistesi.getMasraf_tutari()));
		hesaplistesi.setNoter_masrafi_tl(defaultFormat.format(hesaplistesi.getNoter_masrafi()));
		hesaplistesi.setTahsil_harci_tl(defaultFormat.format(hesaplistesi.getTahsil_harci()));
		hesaplistesi.setTahsilat_tutari_tl(defaultFormat.format(hesaplistesi.getTahsilat_tutari()));
		hesaplistesi.setTakip_alacagi_tl(defaultFormat.format(hesaplistesi.getTakip_alacagi()));

		hesaplistesi.setTakip_faiz_gider_vergi_tl(defaultFormat.format(hesaplistesi.getTakip_faiz_gider_vergi()));
		hesaplistesi.setTakip_faizi_tl(defaultFormat.format(hesaplistesi.getTakip_faizi()));
		hesaplistesi.setTemerrut_faizi_tl(defaultFormat.format(hesaplistesi.getTemerrut_faizi()));
		hesaplistesi.setToplam_alacak_tl(defaultFormat.format(hesaplistesi.getToplam_alacak()));
		hesaplistesi.setVekalet_ucreti_tl(defaultFormat.format(hesaplistesi.getVekalet_ucreti()));

		if (hesapTarihi.getDate() == bugun.getDate()) {
			HesapDAO hesapdao = new HesapDAO();

			hesapdao.Gucelle(hesaplistesi);
		}

		AktifBean.hesaplistesi = hesaplistesi;
	} // PROSEDÜR SONU

	

	public void itirazDurum() {
		IcraDosyasiDAO icd = new IcraDosyasiDAO();

		int id = pelops.controller.AktifBean.getIcraDosyaID();

		String D = "";

		try {

			D = icd.ItirazDurumu(id) == null ? "" : icd.ItirazDurumu(id);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (D.compareTo("true") == 0) {
				this.Itiraz = "img/itiraz.png";

			} else
				this.Itiraz = "img/acikgri.png";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void bilaDurum() {
		IcraDosyasiDAO icd = new IcraDosyasiDAO();

		int id = pelops.controller.AktifBean.getIcraDosyaID();
		String D = null;
		try {
			D = icd.BilaDurumu(id);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (D.compareTo("E") == 0) {
				this.Bila = "img/Bila.png";

			} else
				this.Bila = "img/acikgri.png";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void hitamDurum() {
		IcraDosyasiDAO icd = new IcraDosyasiDAO();

		int id = pelops.controller.AktifBean.getIcraDosyaID();
		String D = null;
		try {
			D = icd.HitamDurumu(id);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (D.compareTo("E") == 0) {
				this.Hitam = "img/Hitam.png";

			} else
				this.Hitam = "img/acikgri.png";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void maasDurum() {
		IcraDosyasiDAO icd = new IcraDosyasiDAO();

		int id = pelops.controller.AktifBean.getIcraDosyaID();
		String D = null;
		try {
			D = icd.MaasDurumu(id) > 0 ? "E" : "H";
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (D.compareTo("E") == 0) {
				this.Para = "img/Maas.png";

			} else
				this.Para = "img/acikgri.png";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String hacizDurum() {
		IcraDosyasiDAO icd = new IcraDosyasiDAO();

		int id = pelops.controller.AktifBean.getIcraDosyaID();
		String D = null;
		try {
			D = icd.HacizDurumu(id) > 0 ? "E" : "H";
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (D.compareTo("E") == 0) {
				this.Haciz = "img/hacze_gidildi.png";

			} else
				this.Haciz = "img/acikgri.png";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return D;
	}

	public void hacizDurumEv() {
		IcraDosyasiDAO icd = new IcraDosyasiDAO();

		int id = pelops.controller.AktifBean.getIcraDosyaID();
		String D = null;
		try {
			D = icd.HacizDurumuEv(id) > 0 ? "E" : "H";
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (D.compareTo("E") == 0) {
				this.Ev = "img/Gayrimenkul_haczi.png";

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void hacizDurumAraba() {
		IcraDosyasiDAO icd = new IcraDosyasiDAO();

		int id = pelops.controller.AktifBean.getIcraDosyaID();
		String D = null;
		try {
			D = icd.HacizDurumuAraba(id) > 0 ? "E" : "H";
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (D.compareTo("E") == 0) {
				this.Arac = "img/Arac_haczi.png";

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void hacizDurumMaas() {
		IcraDosyasiDAO icd = new IcraDosyasiDAO();

		int id = pelops.controller.AktifBean.getIcraDosyaID();
		String D = null;
		try {
			D = icd.HacizDurumuMaas(id) > 0 ? "E" : "H";
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (D.compareTo("E") == 0) {
				this.Para = "img/Maas_haczi.png";

			} else
				this.Para = "img/acikgri.png";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String dlgURL;

	public String getDlgURL() {
		return dlgURL;
	}

	public void setDlgURL(String dlgURL) {
		this.dlgURL = dlgURL;
	}

	public double toplamharc() {

		HarcBilgisiDAO dao = new HarcBilgisiDAO();
		double returndouble = 0;

		try {
			for (HarcBilgisi harc : dao.getAllListFromIcraDosyaID(AktifBean.getIcraDosyaID())) {

				returndouble += harc.getHarc_miktari();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returndouble;

	}

	public void refreshPanelVisible() {
		if (AktifBean.getIcraDosyaID() != 0) {
			this.isdialogsVisible = false;
		}
	}

	public String getDosyaStatusu() {
		return dosyaStatusu;
	}

	public void setDosyaStatusu(String dosyaStatusu) {
		this.dosyaStatusu = dosyaStatusu;
	}

	public String getDosyaStatutDurum() {
		return dosyaStatutDurum;
	}

	public void setDosyaStatutDurum(String dosyaStatutDurum) {
		this.dosyaStatutDurum = dosyaStatutDurum;
	}

	public boolean isShowPic() {
		return showPic;
	}

	public void setShowPic(boolean showPic) {
		this.showPic = showPic;
	}
	
	
	

}