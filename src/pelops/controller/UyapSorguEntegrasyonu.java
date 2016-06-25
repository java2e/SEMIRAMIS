package pelops.controller;


import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.event.FileUploadEvent;

import org.primefaces.model.UploadedFile;

import pelops.dao.BorcluBilgisiDAO;
import pelops.dao.HaczeEsasMalBilgisiDAO;
import pelops.model.BorcluBilgisi;
import pelops.model.EgmSorgu;
import pelops.model.HaczeEsasMalBilgisi;

@ManagedBean(name="uyapsorguentegrasyonu")
@SessionScoped
public class UyapSorguEntegrasyonu {

	 public static XSSFSheet xlsTablo ;
	 
	 public void XLSDosyaAl(FileUploadEvent event) throws Exception {
		
			UploadedFile uploadedFile = (UploadedFile) event.getFile();
			
			File dosya= new File("C:\\SEMIRAMIS_ICRA_KLASORU\\TEMP\\tmp.xlsx");
		
			FileUtils.copyInputStreamToFile(uploadedFile.getInputstream(), dosya);
			
			XSSFWorkbook workbook = new XSSFWorkbook(dosya);
			
			xlsTablo = workbook.getSheetAt(0);
	 		 
			workbook.close();
			

			String dosyaAdi = uploadedFile.getFileName();
			String[] Uzanti = dosyaAdi.split("[.]");
			String DosyaIsmi="";
			
			File Klasor = new File("C:\\SEMIRAMIS_ICRA_KLASORU\\SORGU-AKTARMA-XLS");
			
			if(!Klasor.exists())Klasor.mkdir();
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
			Date tarih = new Date();
			if(Uzanti[1].equals("xlsx"))
			DosyaIsmi = dosyaAdi+"_"+format.format(tarih)+".xlsx";
			
			File dosya1= new File("C:\\SEMIRAMIS_ICRA_KLASORU\\SORGU-AKTARMA-XLS"+"\\"+DosyaIsmi);
			
			FileUtils.copyInputStreamToFile(uploadedFile.getInputstream(), dosya1);	
		
			
			
		}
	
	 
	 public void XSLSistemeAktar() throws Exception{
	
		 
			int rowNum = xlsTablo.getLastRowNum() + 1;
			
			BorcluBilgisiDAO dao = new BorcluBilgisiDAO();
			BorcluBilgisi borclu ;
		//	 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			for (int i = 1; i < rowNum; i++) {
				
				Row row = xlsTablo.getRow(i);
		
				 if(row == null){
					 
				 }else{
					 borclu = new BorcluBilgisi();
					 
					 borclu.setBabAdi(row.getCell(3).getStringCellValue());
					 borclu.setAnaAdi(row.getCell(42).getStringCellValue());
					 borclu.setDogumYeri(row.getCell(43).getStringCellValue());
					 //borclu.setDogumTarihi(sdf.format(row.getCell(9).getDateCellValue()));
					 //borclu.setMedeniHali(row.getCell(42).getStringCellValue());
					 borclu.setIlAdi(row.getCell(12).getStringCellValue());
					 //borclu.setIlceAdi(row.getCell(42).getStringCellValue());
					 //borclu.setMahalle(row.getCell(42).getStringCellValue());
					 //borclu.setKoy(row.getCell(42).getStringCellValue());
					 //borclu.setCiltNo(row.getCell(42).getStringCellValue());
					 //borclu.setSiraNo(row.getCell(42).getStringCellValue());
					 //borclu.setVerilisYeri(row.getCell(42).getStringCellValue());
					 //borclu.setVerilisNedeni(row.getCell(42).getStringCellValue());
					 //borclu.setVerilisTarihi(row.getCell(42).getStringCellValue());
					 String Adres="";
					 
					 try {
						 Adres += row.getCell(7).getStringCellValue();
					} catch (Exception e) {
						// TODO: handle exception
					}
					try {
						 Adres += " "+ row.getCell(9).getStringCellValue();
					} catch (Exception e) {
						// TODO: handle exception
					}
					 
									 
					 try {
						 Adres += " Dış Kapı No:"+ row.getCell(10).getStringCellValue();
					} catch (Exception e) {
						try {
							BigDecimal bd1 = new BigDecimal(row.getCell(10)
									.getNumericCellValue());
							long lonVal1 = bd1.longValue();
							
							Adres += " Dış Kapı No:"+ Long.toString(lonVal1);
							
						} catch (Exception e2) {
							// TODO: handle exception
						}
					
					}
					
					 try {
						 Adres += " İç Kapı No:"+row.getCell(11).getStringCellValue()=="" ? "": row.getCell(11).getStringCellValue();
					} catch (Exception e) {
						try {
							BigDecimal bd2 = new BigDecimal(row.getCell(11)
									.getNumericCellValue());
							long lonVal2 = bd2.longValue();
							if(lonVal2>=0)
							 Adres += " İç Kapı No:"+Long.toString(lonVal2);
						} catch (Exception e2) {
							// TODO: handle exception
						}
						
					}
					
					 
					 Adres += row.getCell(12).getStringCellValue() + row.getCell(13).getStringCellValue(); 
					 
					 borclu.setAdres(Adres);
					 //borclu.setSemtAdi(row.getCell(42).getStringCellValue());
					 try {
						 borclu.setTcNo(row.getCell(0).getStringCellValue());
					} catch (Exception e) {
						
						BigDecimal bd = new BigDecimal(row.getCell(0)
								.getNumericCellValue());
						long lonVal = bd.longValue();
						borclu.setTcNo(Long.toString(lonVal));
							
						
					}
					 

					dao.uyapKimlikBilgisiGuncelle(borclu);				
				 }
			
						
				
			}
			
			
			
			 FacesContext context = FacesContext.getCurrentInstance();
	         
		     context.addMessage(null, new FacesMessage("Sisteme Aktarım Mesajı",  "İşlem Başarı ile Gerçekleştirilmiştir. ") );
			
		}

	 
	 public void XLSEMGSistemeAktar() throws Exception{
		 EgmSorgu sorgu=new EgmSorgu();
		 BorcluBilgisiDAO btc=new BorcluBilgisiDAO();
		 HaczeEsasMalBilgisi hem=new HaczeEsasMalBilgisi();
		 HaczeEsasMalBilgisiDAO hemb=new HaczeEsasMalBilgisiDAO();
		 for (int i = 1; i < 5000; i++) {
			Row satir=xlsTablo.getRow(i);
			if (satir==null) {
				
			}
			else {
				try {
				BigDecimal bdm = new BigDecimal(satir.getCell(29)
						.getNumericCellValue());
				long lonValm = bdm.longValue();
				
				
					BigDecimal bd1 = new BigDecimal(satir.getCell(0)
							.getNumericCellValue());
					long lonVal1 = bd1.longValue();
					
					if (btc.tcSorgulama(Long.toString(lonVal1))==0) {
					
					}
					else {			
					
					sorgu.setId(btc.tcSorgulama(Long.toString(lonVal1)));					
					sorgu.setAracTipi(satir.getCell(28).getStringCellValue());
					if (Long.toString(lonValm)==null) {
						
					}
					else {
						sorgu.setAracModel(Long.toString(lonValm));
					}
					
					sorgu.setAracPlaka(satir.getCell(30).getStringCellValue());
					sorgu.setAracMarka(satir.getCell(31).getStringCellValue());
					hem.setMalTipi("Araç");
					hem.setBorcluId(sorgu.getId());
					hem.setAracAracTipi(sorgu.getAracTipi());
					hem.setAracPlakaNo(sorgu.getAracPlaka());
					hem.setDigerBilgiler("Araç Markası :"+sorgu.getAracMarka()+" - Araç Model Yılı :"+sorgu.getAracModel());
					hemb.egmkaydet(hem);
					
					}
					
				} catch (Exception e) {
					
				}
				
			}
		}
		 
	 }
}
