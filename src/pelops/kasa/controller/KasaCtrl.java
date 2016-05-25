package pelops.kasa.controller;

import java.text.NumberFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import pelops.controller.AktifBean;
import pelops.dao.BasvuruHarciDAO;
import pelops.dao.VekaletHarciDAO;
import pelops.dao.VekaletSinirlariDAO;
import pelops.kasa.model.Hitam;
import pelops.kasa.model.HitamView;
import pelops.kasa.model.PrintModel;
import pelops.kasa.model.Reddiyat;
import pelops.kasa.model.ReddiyatView;
import pelops.kasa.model.Tahsilat;
import pelops.kasa.model.TahsilatView;
import pelops.kasa.model.TahsilatViewModel;
import pelops.model.Hesap;
import pelops.model.IcraDosyasi;
import pelops.model.User;
import pelops.util.Util;

public class KasaCtrl {
	ViewDAO viewDAO = new ViewDAO();
	HitamDAO hitamDAO = new HitamDAO();
	ReddiyatDAO reddiyatDAO = new ReddiyatDAO();
	TahsilatDAO tahsilatDAO = new TahsilatDAO();

	/*
	 * sonuc 0: hitama dusmemis borcun tamamı karsilanmamış sonuc 1: hitama
	 * dusecek uyarı ver...
	 */
	public int checkAndSave() {
		return 0;
	}

	// Tahsilat ve izleme bilgisinin tamamı gelir
	public ArrayList<TahsilatViewModel> getListeFromViewsForTahsilatIslemi(Date tarih1, Date tarih2) throws Exception {

		return viewDAO.getAllViewList(tarih1, tarih2);
	}

	public ArrayList<TahsilatView> getTahsilatViewForStatus(int durum) throws Exception {
		return viewDAO.getAllTahsilatFromView(durum);
	}

	public Tahsilat secilenModeliGetir(String id) throws Exception {

		TahsilatViewModel model = viewDAO.getSelectedValueFromID(id);
		Tahsilat tahsilat = new Tahsilat();
		tahsilat.setBorclu_adi(model.getBorcluAdi());
		// tahsilat.setDurum(0);
		tahsilat.setGelis_tarihi(model.getTarih());
		tahsilat.setIcra_dosyasi_id(model.getIcraDosyaID());
		tahsilat.setTahsilat_miktari(model.getOdemeMiktari());
		tahsilat.setIcra_dosya_no(model.getIcraDosyaNo());
		return tahsilat;
	}

	private void kaydetTahsilat(Tahsilat tahsilat, boolean hitam, ArrayList<Reddiyat> reddiyat) throws Exception {
		int tahsilatID = tahsilatDAO.insertObjToDB(tahsilat);
		if (hitam) {
			for (Reddiyat reddiyat1 : reddiyat) {
				reddiyat1.setTahsilatID(tahsilatID);
				reddiyatDAO.insertObjToDB(reddiyat1);
			}
		} else {
			Reddiyat reddiyat2 = convertTahsilatToReddiyat(tahsilat);
			reddiyat2.setTahsilatID(tahsilatID);

			reddiyatDAO.insertObjToDB(reddiyat2);
		}

	}

	public void kaydet(Object object, boolean hitam, ArrayList<Reddiyat> reddiyat) throws Exception {
		if (object instanceof Tahsilat) {
			kaydetTahsilat((Tahsilat) object, hitam, reddiyat);
		} else if (object instanceof Hitam) {
			hitamDAO.insertObjToDB(object);
		} else if (object instanceof Reddiyat) {
			reddiyatDAO.insertObjToDB(object);
		}
	}

	public void kaydet(Object object) throws Exception {
		if (object instanceof Hitam) {
			hitamDAO.insertObjToDB(object);
		} else if (object instanceof Reddiyat) {
			reddiyatDAO.insertObjToDB(object);
		}
	}

	public void guncelle(Object object) throws Exception {
		if (object instanceof Tahsilat) {
			tahsilatDAO.updateObjFromDB(object);
		} else if (object instanceof Hitam) {
			hitamDAO.updateObjFromDB(object);
		} else if (object instanceof Reddiyat) {
			reddiyatDAO.updateObjFromDB(object);
		}
	}

	// obj 1: Tahsilat, 2: hitam, 3:Reddiyat
	public void sil(int id, int obj) throws Exception {
		switch (obj) {
		case 1:
			tahsilatDAO.deleteObjFromDB(id);
			break;
		case 2:
			hitamDAO.deleteObjFromDB(id);
			break;
		case 3:
			reddiyatDAO.deleteObjFromDB(id);
			break;

		default:
			break;
		}

	}

	// obj 1: TahsilatListesi , 2: hitamListesi, 3: ReddiyatListesi Tüm Listeyi
	// getirir.
	public ArrayList<Object> getListe(int obj) throws Exception {
		if (obj == 1) {
			return tahsilatDAO.getAllObjFromDB();
		} else if (obj == 2) {
			return hitamDAO.getAllObjFromDB();
		} else {
			return reddiyatDAO.getAllObjFromDB();
		}

	}

	// obj 1: TahsilatListesi , 2: hitamListesi, 3: ReddiyatListesi Durumuna
	// göre Listeyi getirir. // Kime göre Tarafı
	// sadece Reddiyat içindir null gönderilirse default olarak sasaya göre
	// duruma getListeWithStatus(0,3,1)
	// bakar dolu ise kimeGore: 1: sasa, 2: muvekkil, 3: devlet
	public ArrayList<Object> getListeWithStatus(int status, int obj, Integer kimegore) throws Exception {
		if (obj == 1) {
			return tahsilatDAO.getAllObjFromStatus(status);
		} else if (obj == 2) {
			hitamDAO.getAllObjFromStatus(status);
		} else {
			if (kimegore == null) {
				return reddiyatDAO.getAllObjFromStatus(status);
			} else {
				return reddiyatDAO.getAllObjFromStatus(status, kimegore);
			}
		}
		return null;

	}

	// Viewler uzerinden getirmek istersen
	/*
	 * Status: Durumu na göre viewlerden gerekli listeyi getirmek icin
	 * 
	 * obj: 1: tahsilatView, 2: HitamView , 3: ReddiyatView
	 * 
	 * Kimegore: Reddiyat için gerekli eger null verilirse default sasa ya gore
	 * durumu ceker null degilse:
	 * 
	 * kimegore: 1:sasa, 2: muvekkil, 3: devlet
	 * 
	 * return olarak List donecek ilgili Arraylist e dönüs için cast yapmak
	 * yeterli
	 * 
	 * Or:
	 * 
	 * List list = kasaCtrl.getListefromView(1,1,null);
	 * 
	 * Arraylist<TahsilatView> tahsilatListesi = (Arraylist<TahsilatView>)list;
	 */
	@SuppressWarnings("rawtypes")
	public List getListefromView(int status, int obj, Integer kimegore) throws Exception {
		if (obj == 1) {

			return viewDAO.getAllTahsilatFromView(status);
		} else if (obj == 2) {
			return viewDAO.getAllHitamFromView(status);
		} else {
			return viewDAO.getAllReddiyatFromView(status, kimegore);
		}

	}

	public Reddiyat convertTahsilatToReddiyat(Tahsilat tahsilat) {
		Reddiyat reddiyat = new Reddiyat();

		reddiyat.setMuvekkilDurum(0);
		reddiyat.setSasaDurum(5);
		reddiyat.setDevletDurum(5);
		reddiyat.setMuvekkilReddiyatTutari(tahsilat.getTahsilat_miktari());
		reddiyat.setKasaPersonelID(getPersonelID());
		reddiyat.setIcraDosyaID(tahsilat.getIcra_dosyasi_id());

		return reddiyat;
	}

	public int getPersonelID() {

		return Integer.valueOf(Util.getSession().getAttribute("UserID").toString());

	}

	public Reddiyat createReddiyatForStatus(int status, Hesap hesap, Tahsilat tahsilat) {
		Reddiyat reddiyat = new Reddiyat();
		reddiyat = convertTahsilatToReddiyat(tahsilat);
		double sasaTutar = hesap.getVekalet_ucreti();
		double devletTutar = hesap.getTakip_faiz_gider_vergi();
		double muvvekkilTutar = hesap.getKalan_alacak() - devletTutar - sasaTutar;
		reddiyat.setMuvekkilDurum(null);
		reddiyat.setMuvekkilReddiyatTutari(0);
		reddiyat.setDevletDurum(5);
		reddiyat.setMuvekkilDurum(5);
		reddiyat.setSasaDurum(5);
		switch (status) {
		case 1:
			reddiyat.setSasaDurum(0);
			reddiyat.setSasaReddiyatTutari(sasaTutar);
			break;
		case 2:
			reddiyat.setMuvekkilDurum(0);
			reddiyat.setMuvekkilReddiyatTutari(muvvekkilTutar);
			break;
		case 3:

			reddiyat.setDevletDurum(0);
			reddiyat.setDevletReddiyatTutari(devletTutar);
			break;
		default:
			break;
		}

		return reddiyat;
	}

	public Reddiyat createReddiyatFromReddiyatView(ReddiyatView view) {

		Reddiyat reddiyat = new Reddiyat();

		reddiyat.setId(view.getId());
		reddiyat.setIcraDosyaID(view.getIcraDosyaId());
		reddiyat.setBorcluAdi(view.getBorcluAdi());
		reddiyat.setDevletDurum(view.getDevletDurum());
		reddiyat.setDevletReddiyatTutari(view.getDevletReddiyatTuttar());
		reddiyat.setKasaPersonelID(getPersonelID());
		reddiyat.setMuvekkilAdi(view.getMuvekkilAdi());
		reddiyat.setMuvekkilDurum(view.getMuvekkilDurum());
		reddiyat.setMuvekkilReddiyatTutari(view.getMuvekkilReddiyatTutar());
		reddiyat.setSasaDurum(view.getSasaDurum());
		reddiyat.setSasaReddiyatTutari(view.getSasaReddiyatTutar());
		reddiyat.setTahsilatID(view.getTahsilatId());
		reddiyat.setToplamReddiyatTutari(view.getToplamTutar());
		reddiyat.setIcraDosyaNo(view.getIcraDosyaNo());
		if (reddiyat.getDevletDurum() == 0) {
			reddiyat.setAktifTutar(reddiyat.getDevletReddiyatTutari());
			reddiyat.setReddiyatTuru("Devlete Reddiyat");
		} else if (reddiyat.getMuvekkilDurum() == 0) {
			reddiyat.setAktifTutar(reddiyat.getMuvekkilReddiyatTutari());
			reddiyat.setReddiyatTuru("Bankaya Reddiyat");
		} else if (reddiyat.getSasaDurum() == 0) {
			reddiyat.setAktifTutar(reddiyat.getSasaReddiyatTutari());
			reddiyat.setReddiyatTuru("Sasaya Reddiyat");
		}

		return reddiyat;
	}

	private PrintModel generatePrintModelFromTahsilat(TahsilatView view) {
		PrintModel model = new PrintModel();
		model.setSeri("");
		model.setAlacakli(view.getMuvekkilAdi());
		model.setBorclu(view.getBorcluAdi());
		model.setDosyaNo(view.getIcraDosyaNo());
		model.setMakbuzNo(String.valueOf(view.getId()));
		model.setSebebi("");
		model.setMiktari(String.valueOf(view.getTahsilatMiktari()));
		model.setAdSoyad(view.getAdSoyad());
		model.setTarih(String.valueOf(new Date()));
		model.setAlinanMiktar(String.valueOf(view.getTahsilatMiktari()));
		model.setSiraNo(String.valueOf(view.getId()));

		return model;

	}

	private PrintModel generatePrintModelFromHitamView(HitamView view) {

		PrintModel model = new PrintModel();
		model.setAlacakli(view.getMuvekkilAdi());
		model.setBorclu(view.getBorcluAdi());
		model.setDosyaNo(view.getIcraDosyaNo());
		model.setMakbuzNo(String.valueOf(view.getId()));
		model.setAlinanMiktar(String.valueOf(view.getTahsilatMiktari()));
		model.setSebebi("");
		model.setMiktari(String.valueOf(view.getTahsilatMiktari()));
		model.setSeri("");
		model.setAdSoyad(view.getAdSoyad());
		model.setSiraNo(String.valueOf(view.getId()));
		return model;
	}

	private JasperPrint generateTahsilatMakbuzu(TahsilatView view) throws JRException {
		PrintModel model = generatePrintModelFromTahsilat(view);
		JasperPrint jasperPrint = null;
		ArrayList<PrintModel> list = new ArrayList<>();
		list.add(model);
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
		String reportPath = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/reports/JASPER/" + PrintModel.TAHSILAT_MAKBUZU);
		jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap<>(), beanCollectionDataSource);

		return jasperPrint;

	}

	private JasperPrint generateHitamMakbuzu(HitamView view) throws JRException {
		PrintModel model = generatePrintModelFromHitamView(view);
		JasperPrint jasperPrint = null;
		ArrayList<PrintModel> list = new ArrayList<>();
		list.add(model);
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
		String reportPath = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/reports/JASPER/" + PrintModel.HITAM_MAKBUZU);
		jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap<>(), beanCollectionDataSource);

		return jasperPrint;

	}

	public void printTahsilatMakbuzu(int id) throws Exception {
		TahsilatView tahsilatView = viewDAO.getTahsilatFromViewByID(id);
		JasperPrint jasperPrint = generateTahsilatMakbuzu(tahsilatView);
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
				.getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + "makbuz.pdf");
		ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
		exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);

		exporter.exportReport();
	}

	public void printHitamMakbuzu(int id) throws Exception {
		HitamView hitamView = viewDAO.getHitamFromViewByID(id);
		JasperPrint jasperPrint = generateHitamMakbuzu(hitamView);
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
				.getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + "makbuz.pdf");
		ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
		exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);

		exporter.exportReport();

	}

}
