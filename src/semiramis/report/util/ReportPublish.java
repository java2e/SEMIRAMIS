package semiramis.report.util;

import java.io.IOException;
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
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

public class ReportPublish
{

	JasperPrint jasperPrint;

	@SuppressWarnings("unchecked")
	public void getReportXLS(List getListe, String getPath)
	{

		String path = "reports/" + getPath + ".jasper";

		try
		{
			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
					getListe);

			String reportPath = FacesContext.getCurrentInstance()
					.getExternalContext().getRealPath(path);

			jasperPrint = JasperFillManager.fillReport(reportPath,
					new HashMap(), beanCollectionDataSource);

			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			httpServletResponse.addHeader("Content-disposition",
					"attachment; filename=vizit_talepleri.xlsx");
			ServletOutputStream servletOutputStream = httpServletResponse
					.getOutputStream();
			JRXlsxExporter docxExporter = new JRXlsxExporter();
			docxExporter.setParameter(JRExporterParameter.JASPER_PRINT,
					jasperPrint);
			docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
					servletOutputStream);
			docxExporter.exportReport();

			FacesContext.getCurrentInstance().responseComplete();

		}
		catch (JRException | IOException e)
		{
			e.printStackTrace();
		}
	}

	public void getReportPDF(List getListe, HashMap paramMap, String getPath)
	{

		String path = "reports/" + getPath + ".jasper";

		try
		{

			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
					getListe);
			String reportPath = FacesContext.getCurrentInstance()
					.getExternalContext().getRealPath(path);
			jasperPrint = JasperFillManager.fillReport(reportPath,
					paramMap, beanCollectionDataSource);

			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			httpServletResponse.addHeader("Content-disposition",
					"attachment; filename=vizit_talepleri.pdf");
			ServletOutputStream servletOutputStream = httpServletResponse
					.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint,
					servletOutputStream);
			FacesContext.getCurrentInstance().responseComplete();

		}
		catch (JRException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
