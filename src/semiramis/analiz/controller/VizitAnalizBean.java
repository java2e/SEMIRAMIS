package semiramis.analiz.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import semiramis.analiz.dao.VizitAnalizDAO;
import semiramis.analiz.model.VizitAnaliz;

@ManagedBean(name = "vizitAnalizBean", eager = true)
@SessionScoped
public class VizitAnalizBean {

	public static final int VIZIT_DURUM_YAPILDI = 1;
	public static final int VIZIT_DURUM_YAPILMADI = 2;

	public static final int VIZIT_TAHSILAT_YAPILDI = 3;
	public static final int VIZIT_TAHSILAT_YAPILMADI = 4;

	public static final int VIZIT_STATUSU_1 = 5;
	public static final int VIZIT_STATUSU_2 = 6;
	
	private String vizitDurum;
	
	private String vizitTahsilat;
	
	private String vizitStatusu;

	private int id;

	public VizitAnalizDAO analizDAO;

	private List<VizitAnaliz> liste;

	public VizitAnalizBean() {
		// TODO Auto-generated constructor stub

		analizDAO = new VizitAnalizDAO();

		liste = new ArrayList<VizitAnaliz>();

		liste = analizDAO.getList();

		
		getJSON();
		
	}
	
	public void getJSON()
	{
		
		int vizitDurumYapildi=0,vizitDurumYapilmadi=0;
		
				for (int i = 0; i < liste.size(); i++) {
					
					
					if(liste.get(i).getVizitTarihi()!=null)
					{
						vizitDurumYapildi++;
					}
					else
					{
						vizitDurumYapilmadi++;
						
					}
					
				}
				
				
				vizitDurum="{\"id\":1,\"durum\":\"YAPILDI\",\"adet\":"+vizitDurumYapildi+"},{\"id\":2,\"durum\":\"YAPILMADI\",\"adet\":"+vizitDurumYapilmadi+"}";
		
		
	}

	public void getList() {

		
		liste = analizDAO.getList();
		
		if (id == VIZIT_DURUM_YAPILDI) {
			
			List<VizitAnaliz> liste2 = new ArrayList<VizitAnaliz>();

			for (int i = 0; i < liste.size(); i++) {

				if (liste.get(i).getVizitTarihi() != null) {
					liste2.add(liste.get(i));
				}

			}

			liste = liste2;

		}
		
		else if (id == VIZIT_DURUM_YAPILMADI) {
			
			List<VizitAnaliz> liste2 = new ArrayList<VizitAnaliz>();

			for (int i = 0; i < liste.size(); i++) {

				if (liste.get(i).getVizitTarihi() == null) {
					liste2.add(liste.get(i));
				}

			}

			liste = liste2;

		}

	}

	public List<VizitAnaliz> getListe() {
		return liste;
	}

	public void setListe(List<VizitAnaliz> liste) {
		this.liste = liste;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVizitDurum() {
		return vizitDurum;
	}

	public void setVizitDurum(String vizitDurum) {
		this.vizitDurum = vizitDurum;
	}

	public String getVizitTahsilat() {
		return vizitTahsilat;
	}

	public void setVizitTahsilat(String vizitTahsilat) {
		this.vizitTahsilat = vizitTahsilat;
	}

	public String getVizitStatusu() {
		return vizitStatusu;
	}

	public void setVizitStatusu(String vizitStatusu) {
		this.vizitStatusu = vizitStatusu;
	}
	
	

}
