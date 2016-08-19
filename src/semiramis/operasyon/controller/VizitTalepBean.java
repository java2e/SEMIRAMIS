package semiramis.operasyon.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import semimis.utils.ConvertDate;
import semiramis.operasyon.dao.VizitTalepDAO;
import semiramis.operasyon.model.VizitTalep;


@ManagedBean(name="vizitTalepBean",eager=true)
@SessionScoped
public class VizitTalepBean
{
	
	private Date tarih;
	
	private String il;
	
	private String ilce;
	
	private int icraMudId;
	
	private List<VizitTalep> liste;
	
	public VizitTalepBean()
	{
		
		liste=new ArrayList<VizitTalep>();
		
		
	}
	
	public void getList()
	{
		liste=new ArrayList<VizitTalep>();
		
		VizitTalepDAO dao=new VizitTalepDAO();
		
		liste=dao.getList(1, new ConvertDate().convertDateToString(tarih));
		
		
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
	
	

}
