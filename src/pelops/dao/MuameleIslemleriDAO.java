package pelops.dao;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import pelops.db.DBConnection;
import pelops.model.MuameleIslemleri;

public class MuameleIslemleriDAO extends DBConnection{

	public ArrayList<MuameleIslemleri> Liste() throws Exception{
		
		newConnectDB();
		String SQL = "select * from vwmuameleislemleri  ";
		Statement stmt = conn.createStatement();
		ResultSet rs=  stmt.executeQuery(SQL);
		MuameleIslemleri muameleIslm;
		ArrayList<MuameleIslemleri> muameleList = new ArrayList<MuameleIslemleri>();
		
		while(rs.next()){
			muameleIslm = new MuameleIslemleri();
			muameleIslm.setAciklama(rs.getString("aciklama"));
			muameleIslm.setBorclu_adi(rs.getString("borclu_adi"));
			muameleIslm.setBorclu_id(rs.getInt("borclu_id"));
			muameleIslm.setHaciz_baslangic_tarihi(rs.getDate("haciz_baslangic_tarihi"));
			muameleIslm.setHaciz_miktari(rs.getInt("haciz_miktari"));
			muameleIslm.setHaciz_sirasi(rs.getInt("haciz_sirasi"));
			muameleIslm.setIcra_dosyasi_id(rs.getInt("icra_dosyasi_id"));
			muameleIslm.setId(rs.getInt("id"));
			muameleIslm.setKullanici_adi(rs.getString("kullanici_adi"));
			muameleIslm.setMaas_muvafakat(rs.getString("maas_muvafakat"));
			muameleIslm.setMal_bilgisi(rs.getString("mal_bilgisi"));
			muameleIslm.setMal_tipi_adi(rs.getString("mal_tipi_adi"));
			muameleIslm.setMal_tipi_id(rs.getInt("mal_tipi_id"));
			muameleIslm.setMasraf_miktari(rs.getInt("masraf_miktari"));
			muameleIslm.setMasraf_tipi_adi(rs.getString("masraf_tipi_adi"));
			muameleIslm.setMasraf_tipi_id(rs.getInt("masraf_tipi_id"));
			muameleIslm.setMuamele_statusu(rs.getString("muamele_statusu"));
			muameleIslm.setMuamele_tarihi(convertFromJAVADateToSQLDate(rs.getDate("muamele_tarihi")));
			muameleIslm.setMuhatap_adi(rs.getString("muhatap_adi"));
			muameleIslm.setMuhatap_adresi(rs.getString("muhatap_adresi"));
			muameleIslm.setMuzekkere_adi(rs.getString("muzekkere_adi"));
			muameleIslm.setPersonel_id(rs.getInt("personel_id"));
			muameleIslm.setTabligat_tarihi(convertFromJAVADateToSQLDate(rs.getDate("tabligat_tarihi")));
			muameleIslm.setTalep_ifadesi(rs.getString("talep_ifadesi"));
			muameleIslm.setTebligat_sonucu(rs.getString("tebligat_sonucu"));
			muameleList.add(muameleIslm);
					
		}
	
			disconnectDB();
			return muameleList;
		}
	
	public MuameleIslemleri Liste(int id) throws Exception{
		newConnectDB();
		String SQL = "select * from vwmuameleislemleri where id="+id;
		Statement stmt = conn.createStatement();
		ResultSet rs=  stmt.executeQuery(SQL);
		MuameleIslemleri muameleIslm=null;
				
		while(rs.next()){
			muameleIslm = new MuameleIslemleri();
			muameleIslm.setAciklama(rs.getString("aciklama"));
			muameleIslm.setBorclu_adi(rs.getString("borclu_adi"));
			muameleIslm.setBorclu_id(rs.getInt("borclu_id"));
			muameleIslm.setHaciz_baslangic_tarihi(rs.getDate("haciz_baslangic_tarihi"));
			muameleIslm.setHaciz_miktari(rs.getInt("haciz_miktari"));
			muameleIslm.setHaciz_sirasi(rs.getInt("haciz_sirasi"));
			muameleIslm.setIcra_dosyasi_id(rs.getInt("icra_dosyasi_id"));
			muameleIslm.setId(rs.getInt("id"));
			muameleIslm.setKullanici_adi(rs.getString("kullanici_adi"));
			muameleIslm.setMaas_muvafakat(rs.getString("maas_muvafakat"));
			muameleIslm.setMal_bilgisi(rs.getString("mal_bilgisi"));
			muameleIslm.setMal_tipi_adi(rs.getString("mal_tipi_adi"));
			muameleIslm.setMal_tipi_id(rs.getInt("mal_tipi_id"));
			muameleIslm.setMasraf_miktari(rs.getInt("masraf_miktari"));
			muameleIslm.setMasraf_tipi_adi(rs.getString("masraf_tipi_adi"));
			muameleIslm.setMasraf_tipi_id(rs.getInt("masraf_tipi_id"));
			muameleIslm.setMuamele_statusu(rs.getString("muamele_statusu"));
			muameleIslm.setMuamele_tarihi(convertFromJAVADateToSQLDate(rs.getDate("muamele_tarihi")));
			muameleIslm.setMuhatap_adi(rs.getString("muhatap_adi"));
			muameleIslm.setMuhatap_adresi(rs.getString("muhatap_adresi"));
			muameleIslm.setMuzekkere_adi(rs.getString("muzekkere_adi"));
			muameleIslm.setPersonel_id(rs.getInt("personel_id"));
			muameleIslm.setTabligat_tarihi(convertFromJAVADateToSQLDate(rs.getDate("tabligat_tarihi")));
			muameleIslm.setTalep_ifadesi(rs.getString("talep_ifadesi"));
			muameleIslm.setTebligat_sonucu(rs.getString("tebligat_sonucu"));
			
					
		}
			disconnectDB();
			return muameleIslm;
		}
	
	public int Kaydet(MuameleIslemleri muamele) throws Exception{
		
		String SQL = "INSERT INTO tbl_muamele_islemleri("
				+ "borclu_id, muamele_tarihi, muzekkere_adi, talep_ifadesi,"
				+ "masraf_tipi_id, masraf_miktari, mal_tipi_id, muhatap_adi, muhatap_adresi,"
				+ "tabligat_tarihi, tebligat_sonucu, muamele_statusu, haciz_sirasi,"
				+ "haciz_baslangic_tarihi, haciz_miktari, maas_muvafakat, aciklama,"
				+ "personel_id, icra_dosyasi_id, mal_bilgisi)"
				+ " VALUES (?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		newConnectDB();
		PreparedStatement pstmt = conn.prepareStatement(SQL.toString());
		
		pstmt.setInt(1, muamele.getBorclu_id());
		pstmt.setDate(2, convertFromJAVADateToSQLDate(muamele.getMuamele_tarihi()));
		pstmt.setString(3, muamele.getMuzekkere_adi());
		pstmt.setString(4, muamele.getTalep_ifadesi());
		pstmt.setInt(5, muamele.getMasraf_tipi_id());
		pstmt.setInt(6, muamele.getMasraf_miktari());
		pstmt.setInt(7, muamele.getMal_tipi_id());
		pstmt.setString(8, muamele.getMuhatap_adi());
		pstmt.setString(9, muamele.getMuhatap_adresi());
		pstmt.setDate(10, convertFromJAVADateToSQLDate(muamele.getTabligat_tarihi()));
		pstmt.setString(11, muamele.getTebligat_sonucu());
		pstmt.setString(12, muamele.getMuamele_statusu());
		pstmt.setInt(13, muamele.getHaciz_sirasi());
		pstmt.setDate(14, convertFromJAVADateToSQLDate(muamele.getHaciz_baslangic_tarihi()));
		pstmt.setInt(15, muamele.getHaciz_miktari());
		pstmt.setString(16, muamele.getMaas_muvafakat());
		pstmt.setString(17, muamele.getAciklama());
		pstmt.setInt(18, muamele.getPersonel_id());
		pstmt.setInt(19, muamele.getIcra_dosyasi_id());
		pstmt.setString(20, muamele.getMal_bilgisi());
		
		int result = pstmt.executeUpdate();
		disconnectDB();
		
		
		return result;
	}
	
	public int Duzenle(MuameleIslemleri muamele) throws Exception{
		
		String SQL = "UPDATE tbl_muamele_islemleri"
				+ "  SET borclu_id=?, muamele_tarihi=?, muzekkere_adi=?, talep_ifadesi=?,"
				+ " masraf_tipi_id=?, masraf_miktari=?, mal_tipi_id=?, muhatap_adi=?,"
				+ " muhatap_adresi=?, tabligat_tarihi=?, tebligat_sonucu=?, muamele_statusu=?,"
				+ " haciz_sirasi=?, haciz_baslangic_tarihi=?, haciz_miktari=?, maas_muvafakat=?,"
				+ " aciklama=?, personel_id=?, icra_dosyasi_id=?, mal_bilgisi=? WHERE id=?";
		
		newConnectDB();
		PreparedStatement pstmt = conn.prepareStatement(SQL.toString());
		
		pstmt.setInt(1, muamele.getBorclu_id());
		pstmt.setDate(2, convertFromJAVADateToSQLDate(muamele.getMuamele_tarihi()));
		pstmt.setString(3, muamele.getMuzekkere_adi());
		pstmt.setString(4, muamele.getTalep_ifadesi());
		pstmt.setInt(5, muamele.getMasraf_tipi_id());
		pstmt.setInt(6, muamele.getMasraf_miktari());
		pstmt.setInt(7, muamele.getMal_tipi_id());
		pstmt.setString(8, muamele.getMuhatap_adi());
		pstmt.setString(9, muamele.getMuhatap_adresi());
		pstmt.setDate(10, convertFromJAVADateToSQLDate(muamele.getTabligat_tarihi()));
		pstmt.setString(11, muamele.getTebligat_sonucu());
		pstmt.setString(12, muamele.getMuamele_statusu());
		pstmt.setInt(13, muamele.getHaciz_sirasi());
		pstmt.setDate(14, convertFromJAVADateToSQLDate(muamele.getHaciz_baslangic_tarihi()));
		pstmt.setInt(15, muamele.getHaciz_miktari());
		pstmt.setString(16, muamele.getMaas_muvafakat());
		pstmt.setString(17, muamele.getAciklama());
		pstmt.setInt(18, muamele.getPersonel_id());
		pstmt.setInt(19, muamele.getIcra_dosyasi_id());
		pstmt.setString(20, muamele.getMal_bilgisi());
		pstmt.setInt(21, muamele.getId());
		
		 int result = pstmt.executeUpdate();
		disconnectDB();
		
		return result;
		
	}
	
	public int   Sil(int id) throws Exception{
		newConnectDB();
		String SQL="delete from tbl_muamele_islemleri where id="+id;
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		 int result =pstmt.executeUpdate();
		disconnectDB();
		
		return result;
		
	}

	public java.sql.Date convertFromJAVADateToSQLDate(java.util.Date javaDate) {
		java.sql.Date sqlDate = null;
		if (javaDate != null) {
			sqlDate = new Date(javaDate.getTime());
		}
		return sqlDate;
	}
}
