package pelops.reports.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.DefaultConfiguration;
import org.krysalis.barcode4j.BarcodeGenerator;
import org.krysalis.barcode4j.BarcodeUtil;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import pelops.controller.AktifBean;
import pelops.dao.PostaDAO;
import pelops.model.Posta;
import pelops.report.model.ReportGenel;

/**
 * @author Ozgen
 *
 */
@SuppressWarnings("deprecation")
@ManagedBean(name = "genelYazdirBean")
@SessionScoped
public class GenelYazdirBean {

	JasperPrint jasperPrint;

	String printFileName = null;

	private String semiNumbers;

	public String getSemiNumbers() {
		return semiNumbers;
	}

	public void setSemiNumbers(String semiNumbers) {
		this.semiNumbers = semiNumbers;
	}

	List<Object> reportList = new ArrayList<Object>();

	GenelRaporDAO dao = new GenelRaporDAO();

	ArrayList<String> reportNames = new ArrayList<String>();

	List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();

	ArrayList<ReportGenel> tebligatListesi = new ArrayList<ReportGenel>();

	String fileN = null;
	ArrayList<String> fileNs = new ArrayList<String>();
	static boolean barkodNoVar = false;
	PostaDAO postaDAO = new PostaDAO();

	Integer total = null;

	private Date tarih2 = new Date();
	String oldDate = "01/01/1900";
	private Date tarih1 = new Date(oldDate);

	private boolean tebZarfi = false;
	private boolean takTalebi = false;
	private boolean oEmri = false;
	private boolean tapSicil = false;

	private String filename = null;

	private boolean hiddenOrShow = false;

	String reportPath;

	int tot = 0;

	ArrayList<ReportGenel> ttLis = new ArrayList<ReportGenel>();
	ArrayList<ReportGenel> oeLis = new ArrayList<ReportGenel>();
	ArrayList<ReportGenel> tzLis = new ArrayList<ReportGenel>();
	ArrayList<ReportGenel> rgList = new ArrayList<ReportGenel>();
	private ArrayList<ReportGenel> PrivateList = new ArrayList<ReportGenel>();

	public void refreshObjects() {
		ttLis = new ArrayList<ReportGenel>();
		oeLis = new ArrayList<ReportGenel>();
		tzLis = new ArrayList<ReportGenel>();
		reportNames = new ArrayList<String>();
		jasperPrints = new ArrayList<JasperPrint>();
		fileNs = new ArrayList<String>();
		rgList = new ArrayList<ReportGenel>();
		try {
		//	rgList = dao.getPrintableList(tarih1, tarih2, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jasperPrint = null;

	}

	public void refreshPage(FacesContext context) {
		String viewId = context.getViewRoot().getViewId();
		ViewHandler handler = context.getApplication().getViewHandler();
		UIViewRoot root = handler.createView(context, viewId);
		root.setViewId(viewId);
		context.setViewRoot(root);
	}

	public ArrayList<ReportGenel> getTebligatListesi() {
		return tebligatListesi;
	}

	public void setTebligatListesi(ArrayList<ReportGenel> tebligatListesi) {
		this.tebligatListesi = tebligatListesi;
	}

	public ArrayList<ReportGenel> getPrivateList() {
		return PrivateList;
	}

	public void setPrivateList(ArrayList<ReportGenel> privateList) {
		PrivateList = privateList;
	}

	public JasperPrint init(ArrayList<ReportGenel> list, String name)
			throws Exception {
		BufferedImage image = null;
		BufferedImage brcd = null;
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		if (list.get(0).getIcraBilgi() != "") {
			image = convertTextToGraphic(list.get(0).getIcraBilgi(), list
					.get(0).getIcraDosyaNo());

			hashMap.put("image", image);

		}
		if (list.get(0).getBarkot() == null
				|| list.get(0).getBarkot().equals("")) {
			ArrayList<ReportGenel> list2 = setBarkod(list, list.get(0).getId());

			list = list2;
			brcd = createBarcode(list2.get(0).getBarkot());
			hashMap.put("brcd", brcd);

		} else {
			brcd = createBarcode(list.get(0).getBarkot());
			hashMap.put("brcd", brcd);

		}
		BufferedImage image2 = getImza();
		hashMap.put("imza", image2);

		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
				list);
		reportPath = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/reports/" + name + ".jasper");

		jasperPrint = JasperFillManager.fillReport(reportPath, hashMap,
				beanCollectionDataSource);

		return jasperPrint;

	}

	public void Listele() throws Exception {

	//	rgList = dao.getPrintableList(tarih1, tarih2, null);

	}

	public void getPDF() throws Exception {
		Integer id = Integer.parseInt(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("pdf")
				.toString());

		if (id != null) {
			barkodNoVar = postaDAO.checkBarkodStatus();
			String barkod = postaDAO.checkBarkod(id);

			if (!oEmri && !takTalebi && !tebZarfi) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(
						null,
						new FacesMessage(
								"Lütfen yazdırmak istediğiniz rapor türünü veya türlerini şeçiniz!"));
			} else {
				if (barkodNoVar && barkod == null) {
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(
							null,
							new FacesMessage(
									"Sistemde verilecek barkod numarası kalmamıştır!!  "));
				} else {

					checkComboBox(1, id);
				//	tebligatListesi.add(dao.getPrintableList(null, null, id)
//							.get(0));
				}
			}
		}
		refreshObjects();

	}

	public void kaydet() throws Exception {
		int id = Integer.parseInt(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("save")
				.toString());
		if (!oEmri && !takTalebi && !tebZarfi) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(
					null,
					new FacesMessage(
							"Lütfen yazdırmak istediğiniz rapor türünü veya türlerini şeçiniz!"));
		} else {
			checkComboBox(2, id);
			jasperPrints = new ArrayList<JasperPrint>();

		}

	}

	public boolean isHiddenOrShow() {
		return hiddenOrShow;
	}

	public void setHiddenOrShow(boolean hiddenOrShow) {
		this.hiddenOrShow = hiddenOrShow;
	}

	public String checkValuesForName(ArrayList<ReportGenel> list, String nm) {
		String name = null;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getNoterMasrafi().equals("$0.00")
					|| list.get(i).getNoterMasrafi().equals("0.00TL")) {
				name = nm;

			} else if (list.get(i).getTemmerutFaizi().equals("$0.00")
					|| list.get(i).getTemmerutFaizi().equals("0.00TL")) {
				name = nm;
			} else if (list.get(i).getGiderVergisi().equals("$0.00")
					|| list.get(i).getGiderVergisi().equals("0.00TL")) {

				name = nm;

			} else {
				name = nm;
			}

		}
		return name;

	}

	public ArrayList<ReportGenel> setBarkod(ArrayList<ReportGenel> list, int id)
			throws Exception {
		String barkod = null;
		Posta posta = null;
		barkod = postaDAO.checkBarkod(id);
		posta = postaDAO.BarkodVer();
		if (barkod == null || list.get(0).getBarkot() == "") {
			list.get(0).setBarkot(posta.getBarkod());
			posta.setDurum(1);
			posta.setIcra_dosya_id(id);
			postaDAO.Duzenle(posta);

		}
		return list;

	}

	public void checkComboBox(int status, int id) throws Exception {

		if (takTalebi) {
//			ttLis = dao.getPrintableList(null, null, id);

			fileN = checkValuesForName(ttLis, "takip_talebi");

			JasperPrint print = init(ttLis, fileN);

			if (!oEmri && !tebZarfi && status != 3) {
				generateOnePDF(fileN);
				dao.saveGeneratedFileToDB(fileN);
			} else {
				jasperPrints.add(print);
				fileNs.add(fileN);

			}

		}
		if (tebZarfi) {
			tzLis = dao.getAllTebligatFromIcraDosyaID(id);
			fileN = "tebligat_zarfi";
			JasperPrint print = init(tzLis, fileN);

			if (!oEmri && !takTalebi && status != 3) {

				generateOnePDF(fileN);
				dao.saveGeneratedFileToDB(fileN);

			} else {
				jasperPrints.add(print);
				fileNs.add(fileN);

			}

		}
		if (oEmri) {
//			oeLis = dao.getPrintableList(null, null, id);
			fileN = checkValuesForName(oeLis, "odeme_emri");
			JasperPrint print = init(oeLis, fileN);
			if (!tebZarfi && !takTalebi && status != 3) {

				generateOnePDF(fileN);
				dao.saveGeneratedFileToDB(fileN);

			} else {
				jasperPrints.add(print);
				fileNs.add(fileN);

			}
		}

		if (jasperPrints.size() > 0) {
			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			switch (status) {
			case 1:
				generatePDFs(jasperPrints, httpServletResponse);
				for (int i = 0; i < fileNs.size(); i++) {
					dao.saveGeneratedFileToDB(fileNs.get(i));
				}
				FacesContext.getCurrentInstance().responseComplete();
				break;
			case 2:
				for (int i = 0; i < jasperPrints.size(); i++) {

					savePDFtoServer(jasperPrints.get(i), httpServletResponse,
							fileNs.get(i));
					dao.saveGeneratedFileToDB(fileNs.get(i));

					JasperExportManager.exportReportToPdfStream(
							jasperPrints.get(i), output);

				}

				FacesContext.getCurrentInstance().responseComplete();

				break;
			case 3:
				// hiç bişi yapma sadece jasperPrints ve fileNs e ekle hepsini
				break;

			default:
				break;
			}

		}

	}

	public void generateOnePDF(String name) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
		Date date = new Date();
		String dt = dateFormat.format(date);
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		httpServletResponse.addHeader(
				"Content-disposition",
				"attachment; filename=" + dt + "-" + name + "_"
						+ AktifBean.getIcraDosyaID() + ".pdf");
		ServletOutputStream servletOutputStream = httpServletResponse
				.getOutputStream();

		JasperExportManager.exportReportToPdfStream(jasperPrint,
				servletOutputStream);
		filename = dt + "-" + name + "_" + AktifBean.getIcraDosyaID() + ".pdf";

		// String destinationForServer = "e:\\muko\\";
		/*
		 * destinationForServer = Serverda belirlenecek Dosya olmalı!!!!
		 */
		String destinationForServer = "/Users/Ozgen/Desktop/dosya/";

		OutputStream output = new FileOutputStream(new File(
				destinationForServer + filename));
		JasperExportManager.exportReportToPdfStream(jasperPrint, output);

		refreshPage(FacesContext.getCurrentInstance());
		 servletOutputStream.flush();
		    servletOutputStream.close();
		FacesContext.getCurrentInstance().responseComplete();
		
		refreshObjects();

	}

	OutputStream output;

	public void savePDFtoServer(JasperPrint print,
			HttpServletResponse httpServletResponse, String name)
			throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
		Date date = new Date();
		String dt = dateFormat.format(date);
		httpServletResponse.addHeader(
				"Content-disposition",
				"attachment; filename=" + dt + "-" + name + "_"
						+ AktifBean.getIcraDosyaID() + ".pdf");
		ServletOutputStream servletOutputStream = httpServletResponse
				.getOutputStream();

		JasperExportManager.exportReportToPdfStream(jasperPrint,
				servletOutputStream);
		filename = dt + "-" + name + "_" + AktifBean.getIcraDosyaID() + ".pdf";

		// String destinationForServer = "e:\\muko\\";
		String destinationForServer = "/Users/Ozgen/Desktop/dosya/";
		output = new FileOutputStream(new File(destinationForServer + filename));
		 servletOutputStream.flush();
		    servletOutputStream.close();
		FacesContext.getCurrentInstance().responseComplete();
		refreshObjects();

	}

	public void generatePDFs(List<JasperPrint> jasperPrints2,
			HttpServletResponse httpServletResponse) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
		Date date = new Date();
		String dt = dateFormat.format(date);

		httpServletResponse.addHeader("Content-disposition",
				"attachment; filename=" + dt + "-" + "reports" + "_"
						+ AktifBean.getIcraDosyaID() + ".pdf");
		ServletOutputStream servletOutputStream = httpServletResponse
				.getOutputStream();
		@SuppressWarnings("unused")
		String fileName = dt + "-" + "reports" + "_"
				+ AktifBean.getIcraDosyaID() + ".pdf";

		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,
				jasperPrints2);

		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				servletOutputStream);
		exporter.setParameter(
				JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG,
				Boolean.TRUE);

		exporter.exportReport();

		refreshPage(FacesContext.getCurrentInstance());
		 servletOutputStream.flush();
		    servletOutputStream.close();
		FacesContext.getCurrentInstance().responseComplete();
		refreshObjects();

	}

	public void printAll() throws Exception {

		barkodNoVar = postaDAO.checkBarkodStatus();

		if (!oEmri && !takTalebi && !tebZarfi) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(
					null,
					new FacesMessage(
							"Lütfen yazdırmak istediğiniz rapor türünü veya türlerini şeçiniz!"));
		} else {
			if (barkodNoVar) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						"Sistemde verilecek barkod numarası kalmamıştır!!  "));
			} else {
				hiddenOrShow = true;
				DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
				Date date = new Date();
				String dt = dateFormat.format(date);
				HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
						.getCurrentInstance().getExternalContext()
						.getResponse();
				httpServletResponse.addHeader("Content-disposition",
						"attachment; filename=" + dt + "-" + "reports" + "_"
								+ AktifBean.getIcraDosyaID() + ".pdf");
				ServletOutputStream servletOutputStream = httpServletResponse
						.getOutputStream();
				@SuppressWarnings("unused")
				String fileName = dt + "-" + "reports" + "_"
						+ AktifBean.getIcraDosyaID() + ".pdf";
//				rgList = dao.getPrintableList(tarih1, tarih2, null);
				reportNames.add("takip_talebi");
				reportNames.add("tebligat_zarfi");
				reportNames.add("odeme_emri");
				@SuppressWarnings("unused")
				JasperPrint print;
				@SuppressWarnings("unused")
				String namePr = null;

				for (int i = 0; i < rgList.size(); i++) {
					String barkod = postaDAO.checkBarkod(rgList.get(i).getId());
					if (barkodNoVar && barkod.equals(null)) {
						FacesContext context = FacesContext
								.getCurrentInstance();
						context.addMessage(
								null,
								new FacesMessage(
										"Sistemde verilecek barkod numarası kalmamıştır!!  "));
						break;
					}

					checkComboBox(3, rgList.get(i).getId());
					tebligatListesi.add(rgList.get(i));

				}

				tot = jasperPrints.size() * fileNs.size();

				JRPdfExporter exporter = new JRPdfExporter();

				exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,
						jasperPrints);

				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
						servletOutputStream);
				exporter.setParameter(
						JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG,
						Boolean.TRUE);

				exporter.exportReport();

				hiddenOrShow = false;
				 servletOutputStream.flush();
				    servletOutputStream.close();
				FacesContext.getCurrentInstance().responseComplete();

				jasperPrints = new ArrayList<JasperPrint>();
			}
		}
		rgList.clear();
		refreshObjects();
	}

	public void printTebligatListesi() throws Exception {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		if (tebligatListesi.size() > 0) {

			for (ReportGenel tebligat : tebligatListesi) {
				if (tebligat.getBarkot() != null) {
					BufferedImage barkod = createBarcode(tebligat.getBarkot());
					tebligat.setBrcd(barkod);
				}
			}

			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
					tebligatListesi);
			reportPath = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/reports/" + "tebligat_listesi" + ".jasper");

			jasperPrint = JasperFillManager.fillReport(reportPath, hashMap,
					beanCollectionDataSource);

			DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
			Date date = new Date();
			String dt = dateFormat.format(date);
			
			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			httpServletResponse.addHeader("Content-disposition",
					"attachment; filename=" + dt + "-" + "tebligatListesi"
							+ "_" + AktifBean.getIcraDosyaID() + ".pdf");
			ServletOutputStream servletOutputStream = httpServletResponse
					.getOutputStream();

			JasperExportManager.exportReportToPdfStream(jasperPrint,
					servletOutputStream);

			 servletOutputStream.flush();
			    servletOutputStream.close();
			FacesContext.getCurrentInstance().responseComplete();
			
			tebligatListesi = new ArrayList<ReportGenel>();
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("Dikkat", new FacesMessage(
					"Daha çıktı alınmamıştır!!  "));
		}
	}

	public boolean isTebZarfi() {
		return tebZarfi;
	}

	public void setTebZarfi(boolean tebZarfi) {
		this.tebZarfi = tebZarfi;
	}

	public boolean isTakTalebi() {
		return takTalebi;
	}

	public void setTakTalebi(boolean takTalebi) {
		this.takTalebi = takTalebi;
	}

	public boolean isoEmri() {
		return oEmri;
	}

	public void setoEmri(boolean oEmri) {
		this.oEmri = oEmri;
	}

	public boolean isTapSicil() {
		return tapSicil;
	}

	public void setTapSicil(boolean tapSicil) {
		this.tapSicil = tapSicil;
	}

	public Integer getTotal() throws Exception {

		total = dao.getAllReportInfo().size();

		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public ArrayList<ReportGenel> getReportList() throws Exception {

		return null;//dao.getPrintableList(null, null, AktifBean.getIcraDosyaID());
	}

	public void setReportList(List<Object> reportList) {
		this.reportList = reportList;
	}

	public ArrayList<ReportGenel> getRgList() throws Exception {
		return rgList;
	}

	public void setRgList(ArrayList<ReportGenel> rgList) {
		this.rgList = rgList;
	}

	public Date getTarih1() {
		return tarih1;
	}

	public void setTarih1(Date tarih1) {
		this.tarih1 = tarih1;
	}

	public Date getTarih2() {
		return tarih2;
	}

	public void setTarih2(Date tarih2) {
		this.tarih2 = tarih2;
	}

	public boolean isBarkodNoVar() throws Exception {
		return postaDAO.checkBarkodStatus();
	}

	@SuppressWarnings("static-access")
	public void setBarkodNoVar(boolean barkodNoVar) {
		this.barkodNoVar = barkodNoVar;
	}

	@SuppressWarnings("unused")
	public BufferedImage convertTextToGraphic(String IcraMd, String DosyaNo)
			throws IOException {

		String spText[] = IcraMd.split(" ");

		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();

		FontMetrics fm = g2d.getFontMetrics();
		int width = 240;
		int height = 130;
		g2d.dispose();

		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		g2d = img.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
				RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_DITHERING,
				RenderingHints.VALUE_DITHER_ENABLE);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
				RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_PURE);

		fm = g2d.getFontMetrics();
		g2d.setColor(Color.RED);

		g2d.setBackground(Color.white);
		Font newfont = new Font("Times New Roman", Font.BOLD, 26);
		g2d.setFont(newfont);
		g2d.drawString("T.C.", 106, 33);
		String Il = spText[0];
		g2d.drawString(Il, 110 - (Il.length() * 6), 60);

		Font newfont1 = new Font("Serif", Font.PLAIN, 18);
		g2d.setFont(newfont1);
		g2d.drawString("İCRA MÜDÜRLÜĞÜ", 64, 80);

		Font newfont2 = new Font("Serif", Font.BOLD, 50);
		g2d.setFont(newfont2);
		String IcraMud = spText[1];
		g2d.drawString(IcraMud, 53 - IcraMud.length() * 18, 80);

		Font newfont3 = new Font("Times New Roman", Font.BOLD, 36);
		g2d.setFont(newfont3);
		// Calendar calendar = new GregorianCalendar();
		// String Yil = String.valueOf(calendar.get(Calendar.YEAR));
		g2d.drawString(DosyaNo, 35, 115);

		g2d.drawLine(1, 1, 238, 1);
		g2d.drawLine(2, 2, 239, 2);

		g2d.drawLine(1, 1, 1, 128);
		g2d.drawLine(2, 2, 2, 129);

		g2d.drawLine(238, 1, 238, 128);
		g2d.drawLine(239, 2, 239, 129);

		g2d.drawLine(1, 128, 238, 128);
		g2d.drawLine(2, 129, 239, 129);

		g2d.dispose();

		// ImageIO.write(img, "png", new
		// File("C:/Users/JAVACI/Desktop/muko/deneme.png"));
		return img;
	}

	public BufferedImage createBarcode(String number) throws Exception {
		BufferedImage bufferedImage = null;
		if (number != null) {

			BarcodeUtil util = BarcodeUtil.getInstance();
			BarcodeGenerator gen = util
					.createBarcodeGenerator(buildCfg("ean-13"));
			int resolution = 200;
			OutputStream fout = new FileOutputStream("ean-13.jpg");
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(fout,
					"image/jpeg", resolution, BufferedImage.TYPE_BYTE_BINARY,
					false, 0);
			gen.generateBarcode(canvas, number);
			
			
			canvas.finish();

			bufferedImage = canvas.getBufferedImage();

		}
		return bufferedImage;
	}

	private static Configuration buildCfg(String type) {
		DefaultConfiguration cfg = new DefaultConfiguration("barcode");

		// Bar code type
		DefaultConfiguration child = new DefaultConfiguration(type);
		cfg.addChild(child);

		// Human readable text position
		DefaultConfiguration attr = new DefaultConfiguration("human-readable");
		DefaultConfiguration subAttr = new DefaultConfiguration("placement");
		subAttr.setValue("bottom");
		attr.addChild(subAttr);

		child.addChild(attr);
		return cfg;
	}

	// MUHAMMET ALİ KAYA EKLEDİKLERİM BELLİ OLSUN DİYE BURAYA HAT ÇİZDİM SONRA
	// SİLİNECEK BU HAT
	// ----------------------------------------------------------------------------------------

	public void SemiNoYazdir() throws NumberFormatException, Exception {

		String[] SemiNums = this.semiNumbers.split(",");
		if (SemiNums.length == 1)
			SemiNums = this.semiNumbers.split("-");
		if (SemiNums.length == 1)
			SemiNums = this.semiNumbers.split(";");
		if (SemiNums.length == 1)
			SemiNums = this.semiNumbers.split(":");

		ArrayList<ReportGenel> RaporListesi = new ArrayList<ReportGenel>();
		GenelRaporDAO dao = new GenelRaporDAO();
		for (int i = 0; i < SemiNums.length; i++) {
			RaporListesi.add(dao.getFilteredReports(
					Integer.parseInt(SemiNums[i])).get(0));

		}
		printAll(RaporListesi);

	}

	public void printAll(ArrayList<ReportGenel> repors) throws Exception {

		barkodNoVar = postaDAO.checkBarkodStatus();

		if (!oEmri && !takTalebi && !tebZarfi) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(
					null,
					new FacesMessage(
							"Lütfen yazdırmak istediğiniz rapor türünü veya türlerini şeçiniz!"));
		} else {
			if (barkodNoVar) {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						"Sistemde verilecek barkod numarası kalmamıştır!!  "));
			} else {
				hiddenOrShow = true;
				DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
				Date date = new Date();
				String dt = dateFormat.format(date);
				HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
						.getCurrentInstance().getExternalContext()
						.getResponse();
				httpServletResponse.addHeader("Content-disposition",
						"attachment; filename=" + dt + "-" + "reports" + "_"
								+ AktifBean.getIcraDosyaID() + ".pdf");
				ServletOutputStream servletOutputStream = httpServletResponse
						.getOutputStream();
				@SuppressWarnings("unused")
				String fileName = dt + "-" + "reports" + "_"
						+ AktifBean.getIcraDosyaID() + ".pdf";
				rgList = repors;
				reportNames.add("takip_talebi");
				reportNames.add("tebligat_zarfi");
				reportNames.add("odeme_emri");
				@SuppressWarnings("unused")
				JasperPrint print;
				@SuppressWarnings("unused")
				String namePr = null;

				for (int i = 0; i < rgList.size(); i++) {
					String barkod = postaDAO.checkBarkod(rgList.get(i).getId());
					if (barkodNoVar && barkod.equals(null)) {
						FacesContext context = FacesContext
								.getCurrentInstance();
						context.addMessage(
								null,
								new FacesMessage(
										"Sistemde verilecek barkod numarası kalmamıştır!!  "));
						break;
					}

					checkComboBox(3, rgList.get(i).getId());

				}

				tot = jasperPrints.size() * fileNs.size();

				JRPdfExporter exporter = new JRPdfExporter();

				exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST,
						jasperPrints);

				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
						servletOutputStream);
				exporter.setParameter(
						JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG,
						Boolean.TRUE);

				exporter.exportReport();

				hiddenOrShow = false;
				 servletOutputStream.flush();
				    servletOutputStream.close();
				FacesContext.getCurrentInstance().responseComplete();

				jasperPrints = new ArrayList<JasperPrint>();
			}
		}

	}

	public void privateListEkle() throws Exception {

		int id = Integer.parseInt(FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get("ekle")
				.toString());

		PrivateList.add(dao.getFilteredReports(id).get(0));

	}

	public void privatePrint() throws Exception {

		printAll(PrivateList);
		PrivateList.clear();
	}

	public void clearLists() {
		PrivateList = new ArrayList<ReportGenel>();
		rgList = new ArrayList<ReportGenel>();
	}

	public BufferedImage getImza() throws IOException {
		BufferedImage image = ImageIO.read(new File(
				"C:\\apache-tomcat-8.0.30\\webapps\\SEMIRAMIS\\img\\imza.jpg"));
		return image;
	}
}
