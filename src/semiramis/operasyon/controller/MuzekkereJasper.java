package semiramis.operasyon.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.context.FacesContext;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import pelops.controller.AktifBean;
import pelops.model.MuameleIslemleri;
import pelops.model.TebligatListesi;
import pelops.model.TebligatZarfi;
import pelops.reports.controller.GenelYazdirBean;
import semiramis.operasyon.model.Muamele;

public class MuzekkereJasper 
{
	
	
	public  String genelPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\pdfler\\")+"\\";
	
	public String zarfTipi="";
	
	public JasperPrint getMuzekkere(String path,Muamele muamele)
	{
		
		JasperPrint jasperPrint=null;
		
		try {
			
			
			ArrayList<Muamele> dataBeanList = new ArrayList<Muamele>();
			
			muamele.setBarkod(new GenelYazdirBean().createBarcode(muamele.getBarkodTxt()));
			
		
			dataBeanList.add(muamele);

			HashMap<String, Object> parameters = new HashMap<String, Object>();

			String pathName = FacesContext.getCurrentInstance().getExternalContext()
					.getRealPath("/reports/talep_muzekkereler/" + path + ".jrxml");

			InputStream inputStream = new FileInputStream(pathName);

			File file = new File(
					genelPath+ path + ".pdf");
			file.delete();

			path = genelPath + path + ".pdf";

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
			JasperDesign jasperDesign = new JasperDesign();
			jasperDesign = JRXmlLoader.load(inputStream);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
			// JasperExportManager.exportReportToPdfFile(jasperPrint, path);

		
			
		} catch (Exception e) {


			System.out.println("Hata muzekkereJasper getMuzekkere :"+e.getMessage());
			
		}
		
		return jasperPrint;
		
	}
	
	public JasperPrint tebligatZarfiJasper(Muamele muamele, String muzekkereTalep) throws Exception
	{

		JasperPrint jasperPrint=null;
		
		ArrayList<TebligatZarfi> dataBeanListForTebligat = new ArrayList<TebligatZarfi>();
		TebligatZarfi zarf = new TebligatZarfi();
		
		// Maaş Müzekkeresi Genel Durumunda 
		
		if(muamele.getMuzekkereId()==1)
		{
		
		zarf.setBorcluAdi(muamele.getBorcluIsyeriAdi().toUpperCase());
		zarf.setBorcluAdres(muamele.getBorcluIsyeriAdres().toUpperCase());
		zarf.setMuzekkereTalepAdi("Maaş Haciz Müzekkeresi (Genel)");
		zarfTipi="Maaş Haciz Müzekkeresi";

		
		}
		else if(muamele.getMuzekkereId()==2)
		{
			zarf.setBorcluAdi(muamele.getBorcluIsyeriAdi().toUpperCase());
			//zarf.setBorcluAdres(muamele.get).toUpperCase()+" "+muamele.getTapuIl().toUpperCase());
			zarf.setMuzekkereTalepAdi("Tapu Haciz Müzekkeresi (Nokta)");
			zarfTipi="Tapu Haciz Müzekkeresi";
		}
		
		zarf.setIcraDosyaNo(muamele.getIcraDosyaNo());
		zarf.setIcraMudurluguAdi(muamele.getIcraMudurlugu());
		zarf.setAlacakliAdi(muamele.getMuvekkilAdi());
		zarf.setAvukatAdi(muamele.getAvukatAdi());
		zarf.setBarkod(muamele.getBarkod());
		

		dataBeanListForTebligat.add(zarf);

		HashMap<String, Object> parameters = new HashMap<String, Object>();

		String pathName = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/reports/talep_muzekkereler/tebligat_zarfi.jrxml");
		InputStream inputStream = new FileInputStream(pathName);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanListForTebligat);
		JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);

		return jasperPrint;

	}
	
	
	public JasperPrint tebligatListesiJasper(Muamele muamele, String muzekkereTalep) throws Exception {
		
		JasperPrint jasperPrint=null;

		ArrayList<TebligatListesi> dataBeanListForTebligat = new ArrayList<TebligatListesi>();
		TebligatListesi liste = new TebligatListesi();
		
		// Maaş Müzekkeresi Genel Durumunda 
		
		if(muamele.getMuzekkereId()==1)
		{
		
		liste.setBorcluAdi(muamele.getBorcluIsyeriAdi().toUpperCase());
		zarfTipi="Maaş Müzekkeresi";
		
		
		}
		else
		{
			liste.setBorcluAdi(muamele.getBorcluAdSoyad());
				
		}
		
		liste.setIcraDosyaNo(muamele.getIcraDosyaNo());
		liste.setIcraBilgi(muamele.getIcraMudurlugu());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		liste.setTarih(sdf.format(new java.util.Date()));
		liste.setIl(muamele.getIcraMudurlugu().split(" ")[0]);
		liste.setBrcd(muamele.getBarkod());
		liste.setKonu(zarfTipi);

		dataBeanListForTebligat.add(liste);

		HashMap<String, Object> parameters = new HashMap<String, Object>();

		String pathName = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/reports/tebligat_listesi1.jrxml");
		InputStream inputStream = new FileInputStream(pathName);
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanListForTebligat);
		JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);

		return jasperPrint;

	}

}
