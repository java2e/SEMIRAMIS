package semiramis.kasa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import pelops.controller.AktifBean;
import semiramis.kasa.dao.KasaReddiyatDAO;
import semiramis.kasa.model.KasaReddiyatView;


@ManagedBean(name="kasaReddiyatBean",eager=true)
@RequestScoped
public class KasaReddiyatBean 
{
	
	private List<KasaReddiyatView> listeTahsilat;
	private List<KasaReddiyatView> listeReddiyat;
	
	public KasaReddiyatDAO dao;
	
	
	
	public KasaReddiyatBean() 
	{

		
		dao=new KasaReddiyatDAO();
		
		listeReddiyat=new ArrayList<KasaReddiyatView>();
		
		listeReddiyat=dao.getListReddiyat(AktifBean.icraDosyaID);
		
		listeTahsilat = new ArrayList<KasaReddiyatView>();
		
		listeTahsilat=dao.getListTahsilat(AktifBean.icraDosyaID);
		
		

	}
	
	



	public List<KasaReddiyatView> getListeTahsilat() {
		return listeTahsilat;
	}



	public void setListeTahsilat(List<KasaReddiyatView> listeTahsilat) {
		this.listeTahsilat = listeTahsilat;
	}



	public List<KasaReddiyatView> getListeReddiyat() {
		return listeReddiyat;
	}



	public void setListeReddiyat(List<KasaReddiyatView> listeReddiyat) {
		this.listeReddiyat = listeReddiyat;
	}
	
	
	

}
