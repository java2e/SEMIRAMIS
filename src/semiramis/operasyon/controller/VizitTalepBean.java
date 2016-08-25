package semiramis.operasyon.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pelops.dao.SehirlerDAO;
import semimis.utils.ConvertDate;
import semiramis.operasyon.dao.VizitTalepDAO;
import semiramis.operasyon.model.ComboItem;
import semiramis.operasyon.model.VizitTalep;
import semiramis.report.util.ReportPublish;

@ManagedBean(name = "vizitTalepBean", eager = true)
@SessionScoped
public class VizitTalepBean {

	private Date tarih;

	private String il;

	private String ilce;
	
	private int ilId;
	
	private int ilceId;

	private int icraMudId;

	private List<VizitTalep> liste;
	
	public SehirlerDAO sehirlerDAO;
	
	private List<ComboItem> listIl;
	private List<ComboItem> listIlce;
	

	public VizitTalepBean() throws SQLException {

		liste = new ArrayList<VizitTalep>();
		sehirlerDAO=new SehirlerDAO();
		
		listIl=new ArrayList<ComboItem>();
		
		listIl=sehirlerDAO.getListIl();
		
		listIlce=new ArrayList<ComboItem>();

	}
	
	
	public void changeIlIlce() throws SQLException
	{
		
		listIlce=sehirlerDAO.getListIlce(ilId);
		
	}

	public void getList() {
		
		
		liste = new ArrayList<VizitTalep>();

		VizitTalepDAO dao = new VizitTalepDAO();

		liste = dao.getList(1, new ConvertDate().convertDateToString(tarih));
		
		 List<VizitTalep> liste2=new ArrayList<VizitTalep>();

		 String ilName=sehirlerDAO.getIlName(ilId);
		 
		if (!ilName.equals("il")) {

			for (int i = 0; i < liste.size(); i++) {
				
				if(ilName.toUpperCase().equals(liste.get(i).getIl()))
					liste2.add(liste.get(i));
				
			}
			
			liste=liste2;
			
		}
		
		
		
		if(!ilce.equals("ilce"))
		{
			liste=new ArrayList<VizitTalep>();
			
			for (int i = 0; i <liste2.size(); i++) {
				
				if(ilce.toUpperCase().equals(liste2.get(i).getIlce()))
				{
					liste.add(liste2.get(i));
					
				}
				
			}
		}
		
		
		
		
		

	}
	
	public void getPDF()
	{
		
		
		ReportPublish publish=new ReportPublish();
		
		
		publish.getReportPDF(liste, null, "vizit_talep");
		
		
		
	}

	public List<VizitTalep> getListe() {
		return liste;
	}

	public void setListe(List<VizitTalep> liste) {
		this.liste = liste;
	}

	public Date getTarih() {
		return tarih;
	}

	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}

	public String getIl() {
		return il;
	}

	public void setIl(String il) {
		this.il = il;
	}

	public String getIlce() {
		return ilce;
	}

	public void setIlce(String ilce) {
		this.ilce = ilce;
	}

	public int getIcraMudId() {
		return icraMudId;
	}

	public void setIcraMudId(int icraMudId) {
		this.icraMudId = icraMudId;
	}


	public List<ComboItem> getListIl() {
		return listIl;
	}


	public void setListIl(List<ComboItem> listIl) {
		this.listIl = listIl;
	}


	public List<ComboItem> getListIlce() {
		return listIlce;
	}


	public void setListIlce(List<ComboItem> listIlce) {
		this.listIlce = listIlce;
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
	
	
	
	
	

}
