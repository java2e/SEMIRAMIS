package pelops.controller;




import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.lowagie.text.pdf.BarcodeEAN;

import pelops.dao.ExcellParserDAO;
import pelops.dao.IcraDosyasiDAO;
import pelops.model.ExcelEAktarma;


@SuppressWarnings({ "deprecation", "unused" })
@ManagedBean(name = "excellParserBean")
public class ExcellParserBean {


	String oldDate="01/01/2016";
	
	public static String DOSYA_KLASORU = System.getProperty("catalina.base") + File.separator + "temp" + File.separator
			+ "Liste.xlsx" + File.separator; 
	
	private Date Takip_Tarihi;
	
	 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	 public static XSSFSheet xlsTablo ;
	 

	 
	 private String muvekkilAdi;
	 

		
		public ExcellParserBean(){
			
			gelisTarihi1 = new Date(oldDate);
			muvekkilAdi = "";
	
					
		}
		
	 
	public String getMuvekkilAdi() {
		return muvekkilAdi;
	}


	
	
	public void setMuvekkilAdi(String muvekkilAdi) {
		this.muvekkilAdi = muvekkilAdi;
	}


	public Date getGelisTarihi1() {
		return gelisTarihi1;
	}


	public Date getTakip_Tarihi() {
		return Takip_Tarihi;
	}


	public void setTakip_Tarihi(Date takip_Tarihi) {
		Takip_Tarihi =  takip_Tarihi;
	}


	public void setGelisTarihi1(Date gelisTarihi1) {
		this.gelisTarihi1 = gelisTarihi1;
	}


	public Date getGelisTarihi2() {
		return gelisTarihi2;
	}


	public void setGelisTarihi2(Date gelisTarihi2) {
		this.gelisTarihi2 = gelisTarihi2;
	}

	private Date gelisTarihi1 = new Date(), gelisTarihi2=new Date();
	
	
	
	ArrayList<ExcelEAktarma> uyapModel;
	
	public ArrayList<ExcelEAktarma> getUyapModel() {
		return uyapModel;
	}


	public void setUyapModel(ArrayList<ExcelEAktarma> uyapModel) {
		this.uyapModel = uyapModel;
	}

	public String getMuvekkil(){
		String setMuvekkil="HSBC BANK A.Ş.";
		switch (muvekkilAdi) {
		case "001": setMuvekkil="HSBC BANK A.Ş.";	break;
		case "002": setMuvekkil="AKBANK T.A.Ş.";	break;
		case "003": setMuvekkil="T. GARANTİ BANKASI A.Ş.";	break;
		case "004": setMuvekkil="İNG BANK A.Ş.";	break;
		
		default:
			break;
		}
	return setMuvekkil;
	}
	

	public void Listele() throws Exception {
		ExcellParserDAO dao = new ExcellParserDAO();
		uyapModel = dao.ExcelListesi(getMuvekkil(), gelisTarihi1, gelisTarihi2);
	}
	
		
	
	public void ExportBean() throws IOException{
		
		
		File file = new File(DOSYA_KLASORU);
		 
		 InputStream fis = new FileInputStream(file);
	      
	      byte[] buf = new byte[(int)file.length()];
	      int offset = 0;
	      int numRead = 0;
	      while ((offset < buf.length) && ((numRead = fis.read(buf, offset, buf.length -offset)) >= 0)) 
	      {
	        offset += numRead;
	      }
	      fis.close();
	      HttpServletResponse response =
	         (HttpServletResponse) FacesContext.getCurrentInstance()
	        .getExternalContext().getResponse();

	     response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	     response.setHeader("Content-Disposition", "attachment;filename=newListe.xlsx");
	     response.getOutputStream().write(buf);
	     response.getOutputStream().flush();
	     response.getOutputStream().close();
	     FacesContext.getCurrentInstance().responseComplete();
	}
	
	public void AktarExcel() throws Exception{
		ExcellParserDAO dao = new ExcellParserDAO();
		
		uyapModel = dao.ExcelListesi(getMuvekkil(), gelisTarihi1, gelisTarihi2);
		dao.ExcelOlustur(uyapModel);
		ExportBean();
		
	

		  
	}
	private StreamedContent file2;
	
	
	
	public StreamedContent getFile2() {
		return file2;
	}


	public void setFile2(StreamedContent file2) {
		this.file2 = file2;
	}


	
	public void XLSDosyaAl(FileUploadEvent event) throws Exception {

		
		UploadedFile uploadedFile = (UploadedFile) event.getFile();
		
		File dosya= new File("tmp.xlsx");
		FileUtils.copyInputStreamToFile(uploadedFile.getInputstream(), dosya);
	
		
		XSSFWorkbook workbook = new XSSFWorkbook(dosya);
		
		xlsTablo = workbook.getSheetAt(0);
 
		 
		workbook.close();
	}
	
	
	public void XSLSistemeAktar() throws Exception{
		
	
		int rowNum = xlsTablo.getLastRowNum() + 1;
		
		IcraDosyasiDAO dao = new IcraDosyasiDAO();
		
		for (int i = 1; i < rowNum; i++) {
			
			Row row = xlsTablo.getRow(i);
	
			 if(row == null){
				 
			 }else{
				 
				 	
				 if(row.getCell(0) != null && row.getCell(2)!=null && row.getCell(1)!=null)
					dao.ExcelGuncelle((int)row.getCell(0).getNumericCellValue(), row.getCell(2).getStringCellValue(), row.getCell(1).getStringCellValue());
				
			 }
		
					
			
		}
		
		 FacesContext context = FacesContext.getCurrentInstance();
         
	     context.addMessage(null, new FacesMessage("Sisteme Aktarım Mesajı",  "İşlem Başarı ile Gerçekleştirilmiştir. ") );
		
	}



}
