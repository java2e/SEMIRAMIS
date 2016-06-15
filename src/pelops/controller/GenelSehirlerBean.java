package pelops.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pelops.dao.SehirlerDAO;
import pelops.db.DBConnection;
import pelops.model.Il;
import pelops.model.Ilce;

@ManagedBean(name = "genelSehirlerBean")
@SessionScoped
public class GenelSehirlerBean extends DBConnection {

	private ArrayList<Il> IlListe = new ArrayList<Il>();
	private ArrayList<Ilce> IlceListe = new ArrayList<Ilce>();
	int ilId;
	int ilceId;

	public GenelSehirlerBean() {

	}

	public ArrayList<Il> getIlListe() {
		return IlListe;
	}

	public void setIlListe(ArrayList<Il> ilListe) {
		IlListe = ilListe;
	}

	public ArrayList<Ilce> getIlceListe() {
		return IlceListe;
	}

	public void setIlceListe(ArrayList<Ilce> ilceListe) {
		IlceListe = ilceListe;
	}

	public int getIlId() {
		return ilId;
	}

	public void setIlId(int ilId) {
		this.ilId = ilId;
	}

	public int getIlceId() {
		return ilceId;
	}

	public void setIlceId(int ilceId) {
		this.ilceId = ilceId;
	}

	public ArrayList<Il> ListeGetirForIl() throws SQLException {

		IlListe = new ArrayList<Il>();

		SehirlerDAO dao = new SehirlerDAO();

		dao.GetListForIl();

		IlListe = dao.GetListForIl();

		return IlListe;
	}

	public ArrayList<Ilce> handleValueChange(int id) throws SQLException {

		ArrayList<Ilce> sonDurum = new ArrayList<Ilce>();
		SehirlerDAO dao = new SehirlerDAO();
		sonDurum = dao.GetListForIlce(id);
		return sonDurum;

	}

	public int getIlKoduFromName(String a) throws Exception {
		
		SehirlerDAO dao = new SehirlerDAO();

		return dao.getIl_KoduFromName(a);
	}
	public ArrayList<Ilce> getlceFromIlAd(String ad) throws SQLException, Exception {
		return handleValueChange(getIlKoduFromName(ad));
	}

}
