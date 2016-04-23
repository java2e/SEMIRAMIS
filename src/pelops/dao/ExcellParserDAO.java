package pelops.dao;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pelops.db.DBConnection;
import pelops.model.BorcluBilgisi;
import pelops.model.ExcelEAktarma;
import pelops.model.ExcellModel;
import pelops.model.Hesap;
import pelops.model.IcraDosyasi;

public class ExcellParserDAO extends DBConnection {

	private String SQL;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	int id = 0;
	IcraDosyasiDAO icraDosyasiDAO = new IcraDosyasiDAO();
	BorcluBilgisiDAO borcluDAO = new BorcluBilgisiDAO();
	HesapDAO hesapDAO = new HesapDAO();

	@SuppressWarnings("deprecation")
	public ArrayList<ExcelEAktarma> ExcelListesi(String muvekkilAdi, Date tarih1, Date tarih2) throws Exception{
		
		String SQL1 = "select * from vwexcelaktarmalistesi where 1=1";
		
		 String oldDate="01/01/1900";
	        Date tarih = new Date(oldDate);
	        String fullSQL="";
	        
		  if (tarih1.equals(tarih)!=true) {fullSQL += " and ekleme_tarihi between '"+tarih1+"' and '"+tarih2+"'";	}
	      
	      if (muvekkilAdi!="" ) {fullSQL += " and muvekkil_adi= '"+muvekkilAdi+"'";}
		
	      if (fullSQL=="") {fullSQL = SQL1;} else {fullSQL = SQL1+fullSQL;}
	 
	      newConnectDB();
	  Statement stmt1;
      ResultSet rs1;
      
      stmt1 = conn.createStatement();
      rs1 = stmt1.executeQuery(fullSQL);
      
      ExcelEAktarma modelExcel;
      ArrayList<ExcelEAktarma> listeExcel = new ArrayList<ExcelEAktarma>();
      
       
      while(rs1.next()){
    	  modelExcel = new ExcelEAktarma();
    	  modelExcel.setBorcluAdi(rs1.getString("ad_soyad"));
    	  modelExcel.setIcraDosyaId(rs1.getInt("id"));
    	  modelExcel.setMuvekkilAdi(rs1.getString("muvekkil_adi"));
    	  modelExcel.setTakipAlacagi(rs1.getDouble("takip_alacagi"));
    	  modelExcel.setEklemeTarihi(rs1.getTimestamp("ekleme_tarihi"));
    	  modelExcel.setTckimlik(rs1.getString("tc_no"));
    	  listeExcel.add(modelExcel);
    	  
      }
      
      disconnectDB();
      
	      
		return listeExcel;
	} 

	public static String DOSYA_KLASORU = System.getProperty("catalina.base") + File.separator + "temp" + File.separator
			+ "Liste.xlsx" + File.separator; 
	
	@SuppressWarnings({ "resource" })
	public void ExcelOlustur(ArrayList<ExcelEAktarma> liste) throws IOException{
		
		String dosyaAdi = DOSYA_KLASORU;
		
		//String dosyaAdi = "C:\\apache-tomcat-8.0.30\\webapps\\SEMIRAMIS\\UyapXMLFiles\\Liste.xlsx";
		
		FileInputStream file = new FileInputStream(new File(dosyaAdi));
		
		
		

		String[] title = {"SEMİRAMİS NO","İCRA MÜDÜRLÜĞÜ","İCRA DOSYA ADI", "MÜVEKKİL","BORÇLU","TAKİP TUTARI","TC KİMLİK"};


	    
	    XSSFWorkbook workbook = new XSSFWorkbook(file);
	     XSSFSheet sheet = workbook.getSheetAt(0);
	    Cell cell ;
	
	    for (int i2 = 1; i2 < 5000; i2++) {
	    	 XSSFRow row = sheet.getRow(i2);
	            if (row != null) {
	                sheet.removeRow(row);

	            }   
	    	
		}
	    
	    
	    Row rowBaslik = sheet.createRow(0);
	    for (int i = 0; i < title.length; i++) {
			cell = rowBaslik.createCell(i);
			cell.setCellValue(title[i]);
		}
	    
	    
	   for (int i = 0; i < liste.size(); i++) {
		
		   Row row = sheet.createRow(i+1);
		   
		   cell = row.createCell(0);
		   cell.setCellValue(liste.get(i).getIcraDosyaId());
		   cell = row.createCell(1);
		   cell.setCellValue("");
		   cell = row.createCell(2);
		   cell.setCellValue("");
		   cell = row.createCell(3);
		   cell.setCellValue(liste.get(i).getMuvekkilAdi());
		   cell = row.createCell(4);
		   cell.setCellValue(liste.get(i).getBorcluAdi());
		   cell = row.createCell(5);
		   cell.setCellValue(liste.get(i).getTakipAlacagi());
		   cell = row.createCell(6);
		   cell.setCellValue(liste.get(i).getTckimlik());
		   
	  
		   
	   }
	   
//file.close();
	    

	     

	    FileOutputStream outFile =new FileOutputStream(new File(dosyaAdi));

	    workbook.write(outFile);

	    outFile.close();
	    
	
	   
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	@SuppressWarnings("unused")
	public ArrayList<ExcellModel> getAllList(XSSFWorkbook workbook)
			throws Exception {

		// HSSFWorkbook workbook = new HSSFWorkbook(inputStr);
		XSSFSheet sheet = workbook.getSheetAt(0);

		int rowNum = sheet.getLastRowNum() + 1;
	    int colNum = sheet.getRow(0).getLastCellNum() + 1;

		ArrayList<ExcellModel> excellModelList = new ArrayList<ExcellModel>();

		for (int i = 1; i < rowNum; i++) {

			Row row = sheet.getRow(i);

			ExcellModel excellModel = new ExcellModel();

			excellModel.setAvukatServisNo(String.valueOf(row.getCell(0)
					.getNumericCellValue()));
			excellModel.setBankaServisNo(row.getCell(1).getStringCellValue());
			excellModel.setMusteriNo(String.valueOf(row.getCell(2)
					.getNumericCellValue()));
			excellModel.setMusteri(row.getCell(3).getStringCellValue());
			excellModel.setTcNo((long) (row.getCell(4).getNumericCellValue()));
			excellModel.setUrunKategorisi(row.getCell(5).getStringCellValue());
			excellModel.setUrunAdi(row.getCell(6).getStringCellValue());
			excellModel.setUrunNo(String.valueOf(row.getCell(7)
					.getNumericCellValue()));
			excellModel.setAvukat(row.getCell(8).getStringCellValue());
			//excellModel.setYasalTakipTarihi(row.getCell(9).getDateCellValue());
		//	excellModel.setAvukataVerilisTarihi(row.getCell(10)
		//			.getDateCellValue());
			excellModel.setSubeAdi(row.getCell(11).getStringCellValue());
			excellModel.setYTilkBakiye((double) row.getCell(12)
					.getNumericCellValue());
			excellModel.setToplamBorc((double) row.getCell(13)
					.getNumericCellValue());
			//excellModel.setIhtarnameTarihi(row.getCell(14).getDateCellValue());
			excellModel.setIhtarnameTutari((double) row.getCell(15)
					.getNumericCellValue());
			excellModel.setAkdiFaizOrani((double) row.getCell(16)
					.getNumericCellValue());
			excellModel.setTemmerrutFaizi((double) row.getCell(17)
					.getNumericCellValue());

			excellModelList.add(excellModel);

		}

		return excellModelList;
	}

	@SuppressWarnings("unused")
	public ArrayList<ExcellModel> getAllListHSS(HSSFWorkbook workbook)
			throws Exception {
		HSSFSheet sheet = workbook.getSheetAt(0);

		int rowNum = sheet.getLastRowNum() + 1;
		int colNum = sheet.getRow(0).getLastCellNum() + 1;

		ArrayList<ExcellModel> excellModelList = new ArrayList<ExcellModel>();

		for (int i = 1; i < rowNum; i++) {

			Row row = sheet.getRow(i);

			ExcellModel excellModel = new ExcellModel();

			excellModel.setAvukatServisNo(String.valueOf(row.getCell(0)
					.getNumericCellValue()));
			excellModel.setBankaServisNo(row.getCell(1).getStringCellValue());
			excellModel.setMusteriNo(String.valueOf(row.getCell(2)
					.getNumericCellValue()));
			excellModel.setMusteri(row.getCell(3).getStringCellValue());
			excellModel.setTcNo((long) (row.getCell(4).getNumericCellValue()));
			excellModel.setUrunKategorisi(row.getCell(5).getStringCellValue());
			excellModel.setUrunAdi(row.getCell(6).getStringCellValue());
			excellModel.setUrunNo(String.valueOf(row.getCell(7)
					.getNumericCellValue()));
			excellModel.setAvukat(row.getCell(8).getStringCellValue());
		//	excellModel.setYasalTakipTarihi(row.getCell(9).getDateCellValue());
		//	excellModel.setAvukataVerilisTarihi(row.getCell(10)
		//			.getDateCellValue());
			excellModel.setSubeAdi(row.getCell(11).getStringCellValue());
			excellModel.setYTilkBakiye((double) row.getCell(12)
					.getNumericCellValue());
			excellModel.setToplamBorc((double) row.getCell(13)
					.getNumericCellValue());
		//	excellModel.setIhtarnameTarihi(row.getCell(14).getDateCellValue());
			excellModel.setIhtarnameTutari((double) row.getCell(15)
					.getNumericCellValue());
			excellModel.setAkdiFaizOrani((double) row.getCell(16)
					.getNumericCellValue());
			excellModel.setTemmerrutFaizi((double) row.getCell(17)
					.getNumericCellValue());

			excellModelList.add(excellModel);

		}

		return excellModelList;
	}

	public void saveExcellModels(ArrayList<ExcellModel> models)
			throws Exception {

		for (int i = 0; i < models.size(); i++) {
			ExcellModel excellModel = models.get(i);
			Hesap hesap = new Hesap();
			IcraDosyasi icraDosyasi = new IcraDosyasi();
			BorcluBilgisi borclu = new BorcluBilgisi();

			// icra Dosyası
			String urunAdi = excellModel.getUrunAdi();

			String[] txt = urunAdi.split(" ");
			String text = txt[0];
			int dosyaID = finfDosyaTipiID(text);
			icraDosyasi.setDosyaTipiId(dosyaID);
			icraDosyasi.setTakipYoluId(findTakipYoluID(excellModel
					.getUrunKategorisi()));
			icraDosyasi.setBorcTipiId(findBorcTipiID(excellModel
					.getUrunKategorisi()));
			String no = generateIcraDosyaNo();
			icraDosyasi.setIcraDosyaNo(no);
		//	icraDosyasi.setTakipTarihi(excellModel.getYasalTakipTarihi());
		//	icraDosyasi.setGelisTarihi(excellModel.getAvukataVerilisTarihi());
		//	icraDosyasi.setTakipTarihi(excellModel.getYasalTakipTarihi());
		//	icraDosyasi.setIhtarnameTarihi(excellModel.getIhtarnameTarihi());
			icraDosyasi.setIcraMudurluguId(825);// excelde olmadığından default
												// olarak bunu atadım.
			icraDosyasi.setVekaletUcretTipi("1");
			icraDosyasi.setAvukat_sevis_no(excellModel.getAvukatServisNo());
			icraDosyasi.setBanka_servis_no(excellModel.getBankaServisNo());

			int icraDosID = icraDosyasiDAO.Kaydet(icraDosyasi);
			// icra dosyası Kaydedildi

			// hesabı doldurulacak
			hesap.setIcra_dosyasi(icraDosID);
			hesap.setAsil_alacak(excellModel.getYTilkBakiye());
			hesap.setGecikme_faizi(excellModel.getAkdiFaizOrani());
			hesap.setTemerrut_faizi(excellModel.getTemmerrutFaizi());

			hesapDAO.Kaydet(hesap);
			// hesap kaydedildi

			// borclu kayıt

			String subeAdi = excellModel.getSubeAdi();
			String[] ililce = subeAdi.split("/");
			String il = "";
			String ilce = "";

			if (ililce.length > 1) {
				il = ililce[1];
				ilce = ililce[0];
			} else {
				il = ililce[0];
				ilce = null;
			}
			if (ilce == null) {
				id = findIlID(il);
				borclu.setIlId(id);
			} else {
				id = findIlID(il);
				borclu.setIlId(id);
				id = findIlceID(ilce, il);
				borclu.setIlceId(id);
			}

			borclu.setUrunNo(excellModel.getUrunNo());
			borclu.setAdSoyad(excellModel.getMusteri());
			borclu.setUrunNo(excellModel.getUrunNo());
			borclu.setTcNo(String.valueOf(excellModel.getTcNo()));
			borclu.setAdres(excellModel.getSubeAdi());
			borclu.setAdresTuruId(2);// Yurtiçi adres olarak default atadım
										// bilgi :D
			borcluDAO.saveBorclu(borclu);

		}

	}

	public int findIlceID(String ilce, String il) throws Exception {
		ilce = ilce.toUpperCase();
		il = il.toUpperCase();
		int i = findIlID(il);

		SQL = "select id from tbl_ilce where ilce_adi = '" + ilce
				+ "' and il_id = " + i + ";";
		newConnectDB();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			id = rs.getInt("id");
		}
		disconnectDB();
		return id;

	}

	public int findIlID(String il) throws Exception {
		il = il.toUpperCase();
		SQL = "select id from tbl_il where il_adi = '" + il + "';";
		newConnectDB();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			id = rs.getInt("id");
		}
		disconnectDB();

		return id;

	}

	public String generateIcraDosyaNo() throws Exception {
		ArrayList<IcraDosyasi> icraDList = icraDosyasiDAO.Listele();
		IcraDosyasi icraDosyasi = icraDList.get(icraDList.size() - 1);
		String no = icraDosyasi.getIcraDosyaNo();
		String[] nos = no.split("/");
		int last = Integer.valueOf(nos[1]);
		last = last + 1;
		String generatedNo = nos[0] + "/" + String.valueOf(last);

		return generatedNo;
	}

	public int findBorcTipiID(String urunKategori) throws Exception {

		SQL = "select id from tbl_borc_tipi where adi ='" + urunKategori + "';";
		newConnectDB();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		while (rs.next()) {
			id = rs.getInt("id");
		}
		disconnectDB();
		return id;
	}

	public int finfDosyaTipiID(String text) throws Exception {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int ids = 0;
		SQL = "SELECT id FROM tbl_dosya_tipi where adi like '" + text + "%';";
		newConnectDB();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			id = rs.getInt("id");
			list.add(id);
		}
		disconnectDB();
		if (list.size() > 0) {
			ids = list.get(0);
		}
		return ids;
	}

	public int findTakipYoluID(String urunKategorisi) {
		int ids = 0;
		if (urunKategorisi.equals("Kredi Kart�")) {
			ids = 8;

		}
		return ids;

	}





}
