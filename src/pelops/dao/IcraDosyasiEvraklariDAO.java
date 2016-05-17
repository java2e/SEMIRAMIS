package pelops.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.db.DBConnection;
import pelops.model.IcraDosyasiEvraklar;

public class IcraDosyasiEvraklariDAO  extends DBConnection{

	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	String SQL;

	public void Kaydet(IcraDosyasiEvraklar Item){
		
		java.sql.Date dateSon = convertFromJAVADateToSQLDate(Item.getTarih());
		SQL ="INSERT INTO public.tbl_icra_dosyasi_evraklar("
            +" icra_dosyasi_no, tarih, borclu_id, ekleme_tarihi," 
            +" dosya_yolu, dosya_turu, dosya_tur_id, personel_id, muvekkil)"
            +" VALUES (?, ?, ?, ?, ?, ?,?, ?,?)";
		
		try {
			newConnectDB();
			pstmt = conn.prepareStatement(SQL.toString());
			pstmt.setInt(1, Item.getIcra_dosyasi_no());
			pstmt.setDate(2, dateSon);
			pstmt.setInt(3, Item.getBorclu_id());
			pstmt.setDate(4, dateSon);
			pstmt.setString(5, Item.getDosya_yolu());
			pstmt.setString(6, Item.getDosya_turu());
			pstmt.setInt(7, Item.getDosya_tur_id());
			pstmt.setInt(8, Item.getPersonel_id());
			pstmt.setString(9, Item.getMuvekkil());
			
			pstmt.executeUpdate();
			disconnectDB();
			
		} catch (Exception e) {
			System.out.println("Kayıt İşleminde Hata Var.....:"+ e.getMessage());
		}
		
	}
	
	public void Duzenle(IcraDosyasiEvraklar Item){
		java.sql.Date dateSon = convertFromJAVADateToSQLDate(Item.getTarih());
		SQL ="UPDATE public.tbl_icra_dosyasi_evraklar("
            +" icra_dosyasi_no=?, tarih=?, borclu_id=?, guncelleme_tarihi=?," 
            +" dosya_yolu=?, dosya_turu=?, dosya_tur_id=?, personel_id=?, muvekkil=?)"
            +" WHERE id="+Item.getId();
		
		try {
			newConnectDB();
			pstmt = conn.prepareStatement(SQL.toString());
			pstmt.setInt(1, Item.getIcra_dosyasi_no());
			pstmt.setDate(2, dateSon);
			pstmt.setInt(3, Item.getBorclu_id());
			pstmt.setDate(4, dateSon);
			pstmt.setString(5, Item.getDosya_yolu());
			pstmt.setString(6, Item.getDosya_turu());
			pstmt.setInt(7, Item.getDosya_tur_id());
			pstmt.setInt(8, Item.getPersonel_id());
			pstmt.setString(9, Item.getMuvekkil());
			pstmt.executeUpdate();
			
			disconnectDB();
			
		} catch (Exception e) {
			System.out.println("Düzenleme İşleminde Hata Var.....:"+ e.getMessage());
		}
		
	}
	
	public void Sil(int ID){
		SQL ="DELETE public.tbl_icra_dosyasi_evraklar WHERE id="+ID;
			
			try {
				newConnectDB();
				pstmt = conn.prepareStatement(SQL.toString());
				pstmt.executeUpdate();
				disconnectDB();
				
			} catch (Exception e) {
				System.out.println("Silme İşleminde Hata Var.....:"+ e.getMessage());
			}
			
	}
	
	public ArrayList<IcraDosyasiEvraklar> Liste(int icraDosyasiNo) throws Exception{
		
		ArrayList<IcraDosyasiEvraklar> list =  new ArrayList<IcraDosyasiEvraklar>();
		
		SQL = "SELECT id, icra_dosyasi_no, tarih, borclu_id, ekleme_tarihi, guncelleme_tarihi,"
				+" dosya_yolu, dosya_turu, dosya_tur_id, personel_id, muvekkil"
				+" FROM public.tbl_icra_dosyasi_evraklar WHERE icra_dosyasi_no="+icraDosyasiNo;

		newConnectDB();

		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			IcraDosyasiEvraklar Evrak = new IcraDosyasiEvraklar();
			Evrak.setId(rs.getInt("id"));
			Evrak.setBorclu_id(rs.getInt("borclu_id"));
			Evrak.setDosya_tur_id(rs.getInt("dosya_tur_id"));
			Evrak.setDosya_turu(rs.getString("dosya_turu"));
			Evrak.setDosya_yolu(rs.getString("dosya_yolu"));
			Evrak.setEkleme_tarihi(rs.getTimestamp("ekleme_tarihi"));
			Evrak.setGuncelleme_tarihi(rs.getTimestamp("guncelleme_tarihi"));
			Evrak.setIcra_dosyasi_no(rs.getInt("icra_dosyasi_no"));
			Evrak.setPersonel_id(rs.getInt("personel_id"));
			Evrak.setTarih(rs.getTime("tarih"));
			Evrak.setMuvekkil(rs.getString("muvekkil"));
			list.add(Evrak);		
			
		}
		
		disconnectDB();
		return list;
	}
	
	public java.sql.Date convertFromJAVADateToSQLDate(java.util.Date javaDate) {

		java.sql.Date sqlDate = null;

		if (javaDate != null) {

			sqlDate = new Date(javaDate.getTime());
		}
		return sqlDate;
	}
}
