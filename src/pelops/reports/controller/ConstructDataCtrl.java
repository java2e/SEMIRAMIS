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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.DefaultConfiguration;
import org.krysalis.barcode4j.BarcodeGenerator;
import org.krysalis.barcode4j.BarcodeUtil;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import pelops.dao.PostaDAO;
import pelops.model.Posta;
import pelops.report.model.ConstructedData;
import pelops.report.model.DataToPrint;
import pelops.report.model.ReportGenel;
import pelops.report.model.ReportUtils;
import semiramis.operasyon.controller.Utils;
import semiramis.operasyon.model.ChronologyIdentifier;

public class ConstructDataCtrl {

	Utils utils = new Utils();

	public ArrayList<ReportGenel> init(ArrayList<ReportGenel> genels) throws Exception {
		// s
		ArrayList<ReportGenel> sendList = new ArrayList<ReportGenel>();
		for (ReportGenel reportGenel : genels) {
			reportGenel = setBarkod(reportGenel);
			reportGenel.setBrcd(createBarcode(reportGenel.getBarkot()));
			if (reportGenel.getIcraBilgi() != "") {
				if (reportGenel.getIcraBilgi() != null) {

					BufferedImage image = convertTextToGraphic(reportGenel.getIcraBilgi(),
							reportGenel.getIcraDosyaNo());
					reportGenel.setImage(image);
				}
			}
			sendList.add(reportGenel);
		}
		return sendList;
	}

	public ArrayList<ConstructedData> createConstructedData(ArrayList<DataToPrint> belgeler,
			ArrayList<ReportGenel> raporlar) throws Exception

	{

		ArrayList<ConstructedData> constructedDatas = new ArrayList<ConstructedData>();
		if (belgeler.size() > 0 && raporlar.size() > 0) {
			ArrayList<ReportGenel> genels = init(raporlar);
			for (ReportGenel reportGenel : genels) {
				ConstructedData data = new ConstructedData();
				ArrayList<DataToPrint> dataToPrints = createDataToPrintListForOneReport(belgeler, reportGenel);
				data.setGenel(reportGenel);
				data.setDataToPrints(dataToPrints);
				constructedDatas.add(data);

			}
		}

		return constructedDatas;
	}

	private ArrayList<DataToPrint> createDataToPrintListForOneReport(ArrayList<DataToPrint> belgeler,
			ReportGenel reportGenel) throws JRException, IOException {
		ArrayList<DataToPrint> sendList = new ArrayList<DataToPrint>();
		if (belgeler.size() > 0) {
			for (DataToPrint belge : belgeler) {
				belge = checkBankaForJasperPrintFileName(reportGenel, belge);
				ArrayList<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
				if (belge.getAdet() > 1) {
					for (int i = 0; i < belge.getAdet(); i++) {
						JasperPrint jasperPrint = createJasperPrint(reportGenel, belge);
						jasperPrints.add(jasperPrint);
					}
				} else {
					JasperPrint jasperPrint = createJasperPrint(reportGenel, belge);
					jasperPrints.add(jasperPrint);
				}
				belge.setJasperPrint(jasperPrints);
				sendList.add(belge);
			}
		}
		return sendList;
	}

	private JasperPrint createJasperPrint(ReportGenel genel, DataToPrint data) throws JRException, IOException {
		JasperPrint jasperPrint = null;

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		if (genel.getBarkot() != null) {
			if (genel.getBarkot() != "") {

				hashMap.put("brcd", genel.getBrcd());
			}
		}
		if (genel.getImage() != null) {
			hashMap.put("image", genel.getImage());
		}

		// // :TODO imza resmi nerede ise ona göre get imzayı configure et
		// BufferedImage image2 = getImza();
		// hashMap.put("imza", image2);
		ArrayList<ReportGenel> list = new ArrayList<ReportGenel>();
		list.add(genel);
		JRBeanCollectionDataSource beanCollectionDataSource = null;

		DataToPrint dataNew = checkBankaForJasperPrintFileName(genel, data);
		// :TODO Yeni banka eklendiğinde burası güncellenecek...
		if (genel.getMuvekkilAdi().equals(ReportUtils.GARANTI)) {
			hashMap.put("icraBilgi", genel.getIcraBilgi());
			hashMap.put("icraDosyaNo", genel.getIcraDosyaNo());
			hashMap.put("alacakli", genel.getAlacakli());
			hashMap.put("borclu", genel.getBorclu());
			hashMap.put("vekil", genel.getVekil());
			beanCollectionDataSource = new JRBeanCollectionDataSource(list);

		} else {
			beanCollectionDataSource = new JRBeanCollectionDataSource(list);
		}

		if (dataNew.getJasperFileName() != null) {
			String reportPath = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/reports/" + dataNew.getJasperFileName());
			jasperPrint = JasperFillManager.fillReport(reportPath, hashMap, beanCollectionDataSource);
			if (!dataNew.getJasperFileName().equals(ReportUtils.JASPERFILE_NAME_TEBLIGAT)) {
				utils.saveChronology(genel.getId(), ChronologyIdentifier.ISLEM_YAZDIRMA,
						ReportUtils.convertReportName(data.getBelgeAdi()) + " belgesi yazdırılmıştır.");
			}
		}

		return jasperPrint;

	}

	public DataToPrint checkBankaForJasperPrintFileName(ReportGenel rapor, DataToPrint print) {

		int adet = print.getAdet();
		String belgeAdi = print.getBelgeAdi();
		String JasperFileName = null;
		if (rapor.getMuvekkilAdi().equals(ReportUtils.HSBC)) {
			if (belgeAdi == ReportUtils.REPORT_IHTARNAME) {
				// TODO: dosya hazırlandığında eklenecek
			} else if (belgeAdi.equals(ReportUtils.REPORT_ODEME_EMRI)) {
				JasperFileName = ReportUtils.JASPERFILE_NAME_ODEME_EMRI;
			} else if (belgeAdi.equals(ReportUtils.REPORT_POSTANEDEN_GELEN_EVRAK)) {
				// TODO: dosya hazırlandığında eklenecek
			} else if (belgeAdi.equals(ReportUtils.REPORT_TAKIP_TALEBI)) {
				JasperFileName = ReportUtils.JASPERFILE_NAME_TAKIP_TALEBI;

			} else if (belgeAdi.equals(ReportUtils.REPORT_TALEP_KAGIDI)) {
				// TODO: dosya hazırlandığında eklenecek
			} else if (belgeAdi.equals(ReportUtils.REPORT_TEBLIGAT_LISTESI)) {

				JasperFileName = ReportUtils.JASPERFILE_NAME_TEBLIGAT_LISTESI;

			} else if (belgeAdi.equals(ReportUtils.REPORT_TEBLIGAT_ZARFI)) {
				JasperFileName = ReportUtils.JASPERFILE_NAME_TEBLIGAT;

			} else if (belgeAdi.equals(ReportUtils.REPORT_UYAPSORGU)) {
				// TODO: dosya hazırlandığında eklenecek
			} else if (belgeAdi.equals(ReportUtils.REPORT_VEKALETNAME)) {

				JasperFileName = ReportUtils.JASPERFILE_NAME_VEKALET_HSBC;
			}
		} else if (rapor.getMuvekkilAdi().equals(ReportUtils.AKBANK)) {
			if (belgeAdi == ReportUtils.REPORT_TAKIP_TALEBI) {
				JasperFileName = ReportUtils.JASPERFILE_NAME_TAKIP_TALEBI_AKBANK;
			} else if (belgeAdi == ReportUtils.REPORT_ODEME_EMRI) {
				JasperFileName = ReportUtils.JASPERFILE_NAME_ODEME_EMRI_AKBANK;
			} else if (belgeAdi.equals(ReportUtils.REPORT_TEBLIGAT_ZARFI)) {
				JasperFileName = ReportUtils.JASPERFILE_NAME_TEBLIGAT;

			}
			// :TODO Akbankın diğer rapor tasarımları bittiğinde burası
			// değiştirilecek...

		} else if (rapor.getMuvekkilAdi().equals(ReportUtils.GARANTI)) {
			if (belgeAdi == ReportUtils.REPORT_TAKIP_TALEBI) {
				JasperFileName = ReportUtils.JASPERFILE_NAME_TAKIP_TALEBI_GARANTI;
			} else if (belgeAdi == ReportUtils.REPORT_ODEME_EMRI) {
				JasperFileName = ReportUtils.JASPERFILE_NAME_ODEME_EMRI_GARANTI;
			} else if (belgeAdi.equals(ReportUtils.REPORT_TEBLIGAT_ZARFI)) {
				JasperFileName = ReportUtils.JASPERFILE_NAME_TEBLIGAT;

			}

		} else if (rapor.getMuvekkilAdi().equals(ReportUtils.ING)) {

		}

		DataToPrint dataToPrint = new DataToPrint();

		dataToPrint.setAdet(adet);
		dataToPrint.setBelgeAdi(belgeAdi);
		dataToPrint.setJasperFileName(JasperFileName);
		dataToPrint.setJasperPrint(null);
		return dataToPrint;

	}

	public ArrayList<JasperPrint> getTebligatListesiJasperPrint(DataToPrint print, ArrayList<ReportGenel> report)
			throws Exception {
		JasperPrint jasperPrint = null;

		ArrayList<ReportGenel> reports = init(report);
		ArrayList<JasperPrint> list = new ArrayList<JasperPrint>();
		for (int i = 0; i < print.getAdet(); i++) {

			Map<String, Object> hashMap = new HashMap<String, Object>();
			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(reports);

			if (print.getJasperFileName() != null) {
				String reportPath = FacesContext.getCurrentInstance().getExternalContext()
						.getRealPath("/reports/" + print.getJasperFileName());
				jasperPrint = JasperFillManager.fillReport(reportPath, hashMap, beanCollectionDataSource);
				list.add(jasperPrint);
			}
		}

		return list;
	}

	public BufferedImage convertTextToGraphic(String IcraMd, String DosyaNo) throws IOException {

		String spText[] = IcraMd.split(" ");

		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = img.createGraphics();

		FontMetrics fm = g2d.getFontMetrics();
		int width = 240;
		int height = 130;
		g2d.dispose();

		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		g2d = img.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

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
			BarcodeGenerator gen = util.createBarcodeGenerator(buildCfg("ean-13"));
			int resolution = 200;
			OutputStream fout = new FileOutputStream("ean-13.jpg");
			BitmapCanvasProvider canvas = new BitmapCanvasProvider(fout, "image/jpeg", resolution,
					BufferedImage.TYPE_BYTE_BINARY, false, 0);
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

	public ReportGenel setBarkod(ReportGenel genel) throws Exception {
		String barkod = null;
		Posta posta = null;
		PostaDAO postaDAO = new PostaDAO();
		barkod = postaDAO.checkBarkod(genel.getId());
		posta = postaDAO.BarkodVer();
		if (barkod == null || genel.getBarkot() == "") {
			genel.setBarkot(posta.getBarkod());
			posta.setDurum(1);
			posta.setIcra_dosya_id(genel.getId());
			postaDAO.Duzenle(posta);

		}
		return genel;

	}

	public static String DOSYA_KLASORU = System.getProperty("catalina.base") + File.separator + "temp" + File.separator
			+ "imza.jpg";

	public BufferedImage getImza() throws IOException {
		BufferedImage image = ImageIO.read(new File(DOSYA_KLASORU));
		return image;
	}

}
