package pelops.chronology.controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pelops.chronology.model.ReportChronology;
import pelops.db.DBConnection;
import pelops.kasa.controller.IDAO;
import pelops.report.model.ConstructedData;
import pelops.report.model.DataToPrint;
import pelops.report.model.ReportGenel;
import pelops.report.model.ReportUtils;

public class ReportChronologyUtil extends DBConnection implements IDAO {

	private static ReportChronologyUtil chronologyUtil;
	private Statement stm;
	private PreparedStatement pstm;
	private ResultSet rs;
	private String SQL = "";

	private static ReportChronologyUtil createInstance() {
		chronologyUtil = new ReportChronologyUtil();
		return chronologyUtil;
	}

	public static ReportChronologyUtil getInstance() {
		if (chronologyUtil == null) {
			chronologyUtil = createInstance();
		}
		return chronologyUtil;
	}

	public void insertAll(ArrayList<ConstructedData> constructedData) throws Exception {
		ArrayList<ReportChronology> chronologies = new ArrayList<>();
		for (ConstructedData constructedData2 : constructedData) {
			ArrayList<DataToPrint> list = constructedData2.getDataToPrints();
			ReportGenel reportGenel = constructedData2.getGenel();
			for (DataToPrint dataToPrint : list) {
				if (dataToPrint.getBelgeAdi() != ReportUtils.REPORT_UYAPSORGU
						&& dataToPrint.getBelgeAdi() != ReportUtils.REPORT_IHTARNAME) {
					ReportChronology chronology = new ReportChronology(dataToPrint.getBelgeAdi(), reportGenel.getId(),
							ReportUtils.DOSYA_YONU_GIDEN, new Date());
					chronologies.add(chronology);
				}
			}
		}

		newConnectDB();
		for (ReportChronology reportChronology : chronologies) {

			SQL = "INSERT INTO tbl_dosya_belgeleri( adi, icra_dosya_id, belge_yonu, belge_tarihi) VALUES ( ?, ?, ?, ?);";
			pstm = conn.prepareStatement(SQL);
			pstm.setString(1, reportChronology.getBelgeAdi());
			pstm.setInt(2, reportChronology.getIcraDosyaID());
			pstm.setString(3, reportChronology.getDosyaYonu());
			pstm.setDate(4, convertFromJAVADateToSQLDate(reportChronology.getTarih()));
			pstm.execute();

		}
		disconnectDB();

	}

	public static ReportChronology convertObjToRC(Object object, String BelgeAdi) {
		if (object instanceof ReportGenel) {

			ReportChronology chronology = new ReportChronology(BelgeAdi, ((ReportGenel) object).getId(),
					ReportUtils.DOSYA_YONU_GIDEN, new Date());
			return chronology;

		} else if (object instanceof DataToPrint) {
			if (((DataToPrint) object).getBelgeAdi().equals(ReportUtils.REPORT_TEBLIGAT_LISTESI)) {
				ReportChronology chronology = new ReportChronology(((DataToPrint) object).getBelgeAdi(), null,
						ReportUtils.DOSYA_YONU_GIDEN, new Date());
				return chronology;
			}
		}
		// :TODO Diğer çıltı alınan raporlara göre burası düzenlenecek...

		return null;
	}

	@Override
	public ArrayList<Object> getAllObjFromDB() throws Exception {
		SQL = "SELECT * from tbl_dosya_belgeleri ;";
		newConnectDB();
		stm = conn.createStatement();
		rs = stm.executeQuery(SQL);
		ReportChronology chronology = null;
		ArrayList<Object> list = new ArrayList<>();
		while (rs.next()) {
			chronology = new ReportChronology(rs.getInt("id"), rs.getString("adi"), rs.getInt("icra_dosya_id"),
					rs.getString("belge_yonu"), rs.getDate("belge_tarihi"));
			list.add(chronology);
		}
		disconnectDB();
		return list;
	}

	@Override
	public int insertObjToDB(Object obj) throws Exception {
		if (obj instanceof ReportChronology) {
			SQL = "INSERT INTO tbl_dosya_belgeleri( adi, icra_dosya_id, belge_yonu, belge_tarihi) VALUES ( ?, ?, ?, ?);";
			newConnectDB();
			pstm = conn.prepareStatement(SQL);
			pstm.setString(1, ((ReportChronology) obj).getBelgeAdi());
			if (((ReportChronology) obj).getIcraDosyaID() != null) {
				pstm.setInt(2, ((ReportChronology) obj).getIcraDosyaID());
			}
			pstm.setString(3, ((ReportChronology) obj).getDosyaYonu());
			pstm.setDate(4, convertFromJAVADateToSQLDate(((ReportChronology) obj).getTarih()));
			pstm.execute();
			disconnectDB();
		}

		return 0;
	}

	@Override
	public int updateObjFromDB(Object obj) throws Exception {
		if (obj instanceof ReportChronology) {
			SQL = "UPDATE tbl_dosya_belgeleri SET  adi=?, icra_dosya_id=?,  belge_yonu=?, belge_tarihi=? WHERE id ="
					+ ((ReportChronology) obj).getId() + ";";
			newConnectDB();
			pstm = conn.prepareStatement(SQL);
			pstm.setString(1, ((ReportChronology) obj).getBelgeAdi());
			pstm.setInt(2, ((ReportChronology) obj).getIcraDosyaID());
			pstm.setString(3, ((ReportChronology) obj).getDosyaYonu());
			pstm.setDate(4, convertFromJAVADateToSQLDate(((ReportChronology) obj).getTarih()));
			pstm.execute();
			disconnectDB();
		}

		return 0;
	}

	@Override
	public boolean deleteObjFromDB(int id) throws Exception {
		SQL = "DELETE FROM tbl_dosya_belgeleri WHERE id=" + id + ";";
		newConnectDB();
		stm = conn.createStatement();
		stm.executeQuery(SQL);
		disconnectDB();
		return false;
	}

	@Override
	public Object getObjFromDB(int id) throws Exception {
		SQL = "SELECT * from tbl_dosya_belgeleri WHERE id=" + id + ";";
		newConnectDB();
		stm = conn.createStatement();
		rs = stm.executeQuery(SQL);
		ReportChronology chronology = null;
		while (rs.next()) {
			chronology = new ReportChronology(rs.getInt("id"), rs.getString("adi"), rs.getInt("icra_dosya_id"),
					rs.getString("belge_yonu"), rs.getDate("belge_tarihi"));
		}
		disconnectDB();
		return chronology;
	}

	public List getObjFromDBWithIcraDosyaID(int id) throws Exception {
		SQL = "SELECT * from tbl_dosya_belgeleri WHERE icra_dosya_id=" + id + ";";
		newConnectDB();
		stm = conn.createStatement();
		rs = stm.executeQuery(SQL);
		ReportChronology chronology = null;
		List list = new ArrayList<ReportChronology>();
		while (rs.next()) {
			chronology = new ReportChronology(rs.getInt("id"), rs.getString("adi"), rs.getInt("icra_dosya_id"),
					rs.getString("belge_yonu"), rs.getDate("belge_tarihi"));
			list.add(chronology);
		}
		disconnectDB();
		return list;
	}

	public List getObjFromDBWithIcraDosyaIDandDate(int id, Date date) throws Exception {
		SQL = "SELECT * from tbl_dosya_belgeleri WHERE icra_dosya_id=" + id + " and belge_tarihi='" + date + "';";
		newConnectDB();
		stm = conn.createStatement();
		rs = stm.executeQuery(SQL);
		ReportChronology chronology = null;
		List list = new ArrayList<ReportChronology>();
		while (rs.next()) {
			chronology = new ReportChronology(rs.getInt("id"), rs.getString("adi"), rs.getInt("icra_dosya_id"),
					rs.getString("belge_yonu"), rs.getDate("belge_tarihi"));
			list.add(chronology);
		}
		disconnectDB();
		return list;
	}

	@Override
	public int getID(Object object) throws Exception {
		return 0;
	}

	@Override
	public ArrayList<Object> getAllObjFromStatus(int status) throws Exception {
		return null;
	}

	// Timeline için kullanılacak Static Tanımlar

	public static final String ICRADOSYASI_GIRECEK_EVRAK = "İcra Dosyasına Girecek Evraklar";

	public static final String BORCLU_GIDECEK_EVRAK = "Borçluya Gidecek Evraklar";

	public static final String TEBLIGAT_LISTE_EVRAK = "Tebligat Listesi";

	public static final String BANKA_GONDERILECEK_EVRAK = "Bankaya Gönderilecek Evraklar";

	public static final String DIGER = "Diğer";

	public static final String IMAGE_I_D_G_E = "timeline/report.png";

	public static final String IMAGE_B_G_E = "timeline/report2.png";

	public static final String IMAGE_T_L = "timeline/tebligat_listesi.png";

	public static final String IMAGE_D = "timeline/report3.png";

}
