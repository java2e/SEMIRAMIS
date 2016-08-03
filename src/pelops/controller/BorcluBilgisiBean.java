package pelops.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pelops.dao.BorcluBilgisiDAO;
import pelops.db.DBConnection;
import pelops.model.BorcluBilgisi;
import pelops.model.BorcluTipi;
import pelops.model.Ilce;

@ManagedBean(name = "borcluBilgisiBean")
@RequestScoped
public class BorcluBilgisiBean extends DBConnection {

	private BorcluBilgisi borcluBilgisi = new BorcluBilgisi();

	ArrayList<BorcluBilgisi> borcBilLis = new ArrayList<BorcluBilgisi>();

	ArrayList<Ilce> ilceList;

	ArrayList<BorcluTipi> borcluTipiList = new ArrayList<BorcluTipi>();

	ArrayList<BorcluTipi> list = new ArrayList<BorcluTipi>();

	BorcluBilgisiDAO dao = new BorcluBilgisiDAO();

	String icraDosyaNo;

	public String getIcraDosyaNo() {
		return AktifBean.icraDosyaNo;
	}

	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
	}

	public BorcluBilgisiBean() throws Exception {

		
	
		borcluBilgisi = dao.getBorcluBilgisi(AktifBean.getBorcluAdi());
		
		
	}

	public BorcluBilgisi getBorcluBilgisi() throws Exception {

		if (borcluBilgisi == null) {
			borcluBilgisi = dao.getBorcluBilgisi(AktifBean.borcluAdi);
		}

		return borcluBilgisi;
	}

	public void setBorcluBilgisi(BorcluBilgisi borcluBilgisi) {

		this.borcluBilgisi = borcluBilgisi;
	}

	public ArrayList<BorcluBilgisi> getBorcBilLis() {
		return borcBilLis;
	}

	public void setBorcBilLis(ArrayList<BorcluBilgisi> borcBilLis) {
		this.borcBilLis = borcBilLis;
	}

	public void ilceListeEkle() throws SQLException {

		ilceList = new ArrayList<Ilce>();
		GenelSehirlerBean gs = new GenelSehirlerBean();
		ilceList = gs.handleValueChange(borcluBilgisi.getIlId());



	}

	public ArrayList<Ilce> getIlceList() throws Exception {
		GenelSehirlerBean gs = new GenelSehirlerBean();
		if (borcluBilgisi.getIlId() != 0) {
			ilceList = gs.handleValueChange(borcluBilgisi.getIlId());

		}
		return ilceList;
	}

	public void setIlceList(ArrayList<Ilce> ilceList) {
		this.ilceList = ilceList;
	}

	public ArrayList<BorcluTipi> getBorcluTipiList() throws Exception {
		if (list.size() == 0) {
			list = dao.borcluTipListe();
		}

		return list;
	}

	public void setBorcluTipiList(ArrayList<BorcluTipi> borcluTipiList) {
		this.borcluTipiList = borcluTipiList;
	}

	public void saveBorclu() throws Exception {

		boolean duzenlendi = dao.updateBorclu(borcluBilgisi);
		FacesContext context = FacesContext.getCurrentInstance();

		if (duzenlendi) {

			context.addMessage(null, new FacesMessage("G�ncellendi!"));

		} else {

			context.addMessage(null, new FacesMessage(
					"G�ncelleme ��lemi Ba�ar�s�z!"));
		}

		borcluBilgisi = dao.getBorcluBilgisi(AktifBean.borcluAdi);

	}

	public void Vazgec() throws Exception {

		borcluBilgisi = dao.getBorcluBilgisi(AktifBean.getBorcluAdi());
	}
}
