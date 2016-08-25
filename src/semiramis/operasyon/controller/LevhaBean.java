package semiramis.operasyon.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import pelops.controller.AktifBean;
import pelops.dao.IcraDosyasiDAO;
import semiramis.operasyon.dao.IzlemeBilgisiDAO;
import semiramis.operasyon.model.Levha;
import semiramis.operasyon.model.PairLevha;

@ManagedBean(name = "levhaBean", eager = true)
@RequestScoped
public class LevhaBean {

	private Levha levhaBilgi;

	private int cagriSayisi=0;
	
	private String vizit="img/acikcagri.png";
	
	
	private int AracSayisi = 0;
	private int EvSayisi = 0;

	private String Ev = "img/acikcagri.png", Arac = "img/acikcagri.png", Para = "img/acikcagri.png",
			Haciz = "img/acikcagri.png", Itiraz = "img/acikcagri.png", Istahbarat = "img/acikcagri.png",
			Bila = "img/acikcagri.png", Hitam = "img/acikcagri.png", Temlik = "img/acikcagri.png", Vefat = "img/acikcagri.png";

	private String Arac_1 = "img/acikcagri.png", Arac_2 = "img/acikcagri.png", Arac_3 = "img/acikcagri.png",
			Arac_4 = "img/acikcagri.png";
	private String Ev_1 = "img/acikcagri.png", Ev_2 = "img/acikcagri.png", Ev_3 = "img/acikcagri.png",
			Ev_4 = "img/acikcagri.png";

	private String kart_1 = "img/HSBC-ADVANCE.jpg", kart2 = "img/HSBC-GOLD.jpg", kart_3 = "img/HSBC-PLATİNİUM.jpg",
			kart_4 = "HSBC-PREMİER.jpg";

	public LevhaBean() throws Exception {
		// TODO Auto-generated constructor stub

		levhaBilgi = new Levha();
		plakaGetir();
		
		cagriSayisi=new IzlemeBilgisiDAO().izlemeSayisi(AktifBean.icraDosyaID);

	}

	public void plakaGetir() throws Exception {
		IcraDosyasiDAO icd = new IcraDosyasiDAO();
		
		PairLevha pairArac=icd.MalSayisi(3, AktifBean.getBorcluId());
		
		levhaBilgi.setAracSayisi(pairArac.getAdet());
		levhaBilgi.setAracDurum(pairArac.getDurum());
		
		if(pairArac.getDurum()==1)
			levhaBilgi.setAracDurumTxt("TESPİT");
		else if(pairArac.getDurum()==2)
			levhaBilgi.setAracDurumTxt("HACİZ ŞERHİ");
		else if(pairArac.getDurum()==3)
			levhaBilgi.setAracDurumTxt("YAKALAMA KAYDI");
		else if(pairArac.getDurum()==4)
			levhaBilgi.setAracDurumTxt("YAKALANDI");
		else if(pairArac.getDurum()==5)
			levhaBilgi.setAracDurumTxt("SATIŞTA");
		else if(pairArac.getDurum()==6)
			levhaBilgi.setAracDurumTxt("SATILDI");
		
		PairLevha pairEv=icd.MalSayisi(1, AktifBean.getBorcluId());
		
		levhaBilgi.setEvSayisi(pairEv.getAdet());
		levhaBilgi.setEvDurum(pairEv.getDurum());
		
		if(pairEv.getDurum()==1)
			levhaBilgi.setEvDurumTxt("TESPİT");
		if(pairEv.getDurum()==2)
			levhaBilgi.setEvDurumTxt("HACİZ ŞERHİ");
		if(pairEv.getDurum()==3)
			levhaBilgi.setEvDurumTxt("SATILDI");
		
		
		PairLevha pairMaas=icd.MalSayisi(2, AktifBean.getBorcluId());
		
		levhaBilgi.setMaasSayisi(pairMaas.getAdet());
		levhaBilgi.setMaasDurum(pairMaas.getDurum());
		
		if(pairMaas.getDurum()==1)
			levhaBilgi.setMaasDurumTxt("TESPİT");
		if(pairMaas.getDurum()==2)
			levhaBilgi.setMaasDurumTxt("HACİZ ŞERHİ");
		if(pairMaas.getDurum()==3)
			levhaBilgi.setMaasDurumTxt("SATILDI");
		
		
		
		

		if (levhaBilgi.getAracSayisi() == 0) {
			Ev = "img/acikcagri.png";
		} 
		else if (levhaBilgi.getAracSayisi() > 1)
		{
			Arac = "img/Arac.png";

		}

		if (levhaBilgi.getEvSayisi() == 0) {
			Ev = "img/acikcagri.png";

		} else if (levhaBilgi.getEvSayisi() > 0) {
			Ev = "img/Gayrimenkul.png";
		}
		
		if(levhaBilgi.getMaasSayisi()==0)
		{
			Para="img/acikcagri.png";
		}
		else
			Para="img/Maas.png";

		itirazDurum();
		bilaDurum();
		hitamDurum();
		//maasDurum();
		hacizDurum();
		hacizDurumAraba();
		hacizDurumEv();
		//hacizDurumMaas();
		vizitDurum();

	}
	
	
	public void vizitDurum()
	{
		
		IcraDosyasiDAO icraDao=new IcraDosyasiDAO();
		
		PairLevha pair=icraDao.vizitDurum(AktifBean.icraDosyaID);
		
		levhaBilgi.setVizitDurum(pair.getDurum());
		levhaBilgi.setVizitDurumTxt(pair.getDurumTxt());
		levhaBilgi.setVizitSayisi(pair.getAdet());
		
		
		if(levhaBilgi.getVizitSayisi()>0)
			vizit="img/levha/vizit.png";
		else
			vizit="img/levha/acikcagri.png";
		
		
		
	}

	public void itirazDurum() {
		IcraDosyasiDAO icd = new IcraDosyasiDAO();

		int id = pelops.controller.AktifBean.getIcraDosyaID();

		String D = "";

		try {

			D = icd.ItirazDurumu(id) == null ? "" : icd.ItirazDurumu(id);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (D.compareTo("true") == 0) {
				this.Itiraz = "img/itiraz.png";

			} else
				this.Itiraz = "img/acikcagri.png";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void bilaDurum() {
		IcraDosyasiDAO icd = new IcraDosyasiDAO();

		int id = pelops.controller.AktifBean.getIcraDosyaID();
		String D = null;
		try {
			D = icd.BilaDurumu(id);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (D.compareTo("E") == 0) {
				this.Bila = "img/Bila.png";

			} else
				this.Bila = "img/acikcagri.png";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void hitamDurum() {
		IcraDosyasiDAO icd = new IcraDosyasiDAO();

		int id = pelops.controller.AktifBean.getIcraDosyaID();
		String D = null;
		try {
			D = icd.HitamDurumu(id);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (D.compareTo("E") == 0) {
				this.Hitam = "img/Hitam.png";

			} else
				this.Hitam = "img/acikcagri.png";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void maasDurum() {
		IcraDosyasiDAO icd = new IcraDosyasiDAO();

		int id = pelops.controller.AktifBean.getIcraDosyaID();
		String D = null;
		try {
			D = icd.MaasDurumu(id) > 0 ? "E" : "H";
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (D.compareTo("E") == 0) {
				this.Para = "img/Maas.png";

			} else
				this.Para = "img/acikcagri.png";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String hacizDurum() {
		IcraDosyasiDAO icd = new IcraDosyasiDAO();

		int id = pelops.controller.AktifBean.getIcraDosyaID();
		String D = null;
		try {
			D = icd.HacizDurumu(id) > 0 ? "E" : "H";
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (D.compareTo("E") == 0) {
				this.Haciz = "img/hacze_gidildi.png";

			} else
				this.Haciz = "img/acikcagri.png";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return D;
	}

	public void hacizDurumEv() {
		IcraDosyasiDAO icd = new IcraDosyasiDAO();

		int id = pelops.controller.AktifBean.getIcraDosyaID();
		String D = null;
		try {
			D = icd.HacizDurumuEv(id) > 0 ? "E" : "H";
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (D.compareTo("E") == 0) {
				this.Ev = "img/Gayrimenkul_haczi.png";

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void hacizDurumAraba() {
		IcraDosyasiDAO icd = new IcraDosyasiDAO();

		int id = pelops.controller.AktifBean.getIcraDosyaID();
		String D = null;
		try {
			D = icd.HacizDurumuAraba(id) > 0 ? "E" : "H";
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (D.compareTo("E") == 0) {
				this.Arac = "img/Arac_haczi.png";

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void hacizDurumMaas() {
		IcraDosyasiDAO icd = new IcraDosyasiDAO();

		int id = pelops.controller.AktifBean.getIcraDosyaID();
		String D = null;
		try {
			D = icd.HacizDurumuMaas(id) > 0 ? "E" : "H";
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (D.compareTo("E") == 0) {
				this.Para = "img/Maas_haczi.png";

			} else
				this.Para = "img/acikcagri.png";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getAracSayisi() {
		return AracSayisi;
	}

	public void setAracSayisi(int aracSayisi) {
		AracSayisi = aracSayisi;
	}

	public int getEvSayisi() {
		return EvSayisi;
	}

	public void setEvSayisi(int evSayisi) {
		EvSayisi = evSayisi;
	}

	public String getEv() {
		return Ev;
	}

	public void setEv(String ev) {
		Ev = ev;
	}

	public String getArac() {
		return Arac;
	}

	public void setArac(String arac) {
		Arac = arac;
	}

	public String getPara() {
		return Para;
	}

	public void setPara(String para) {
		Para = para;
	}

	public String getHaciz() {
		return Haciz;
	}

	public void setHaciz(String haciz) {
		Haciz = haciz;
	}

	public String getItiraz() {
		return Itiraz;
	}

	public void setItiraz(String itiraz) {
		Itiraz = itiraz;
	}

	public String getIstahbarat() {
		return Istahbarat;
	}

	public void setIstahbarat(String istahbarat) {
		Istahbarat = istahbarat;
	}

	public String getBila() {
		return Bila;
	}

	public void setBila(String bila) {
		Bila = bila;
	}

	public String getHitam() {
		return Hitam;
	}

	public void setHitam(String hitam) {
		Hitam = hitam;
	}

	public String getTemlik() {
		return Temlik;
	}

	public void setTemlik(String temlik) {
		Temlik = temlik;
	}

	public String getVefat() {
		return Vefat;
	}

	public void setVefat(String vefat) {
		Vefat = vefat;
	}

	public String getArac_1() {
		return Arac_1;
	}

	public void setArac_1(String arac_1) {
		Arac_1 = arac_1;
	}

	public String getArac_2() {
		return Arac_2;
	}

	public void setArac_2(String arac_2) {
		Arac_2 = arac_2;
	}

	public String getArac_3() {
		return Arac_3;
	}

	public void setArac_3(String arac_3) {
		Arac_3 = arac_3;
	}

	public String getArac_4() {
		return Arac_4;
	}

	public void setArac_4(String arac_4) {
		Arac_4 = arac_4;
	}

	public String getEv_1() {
		return Ev_1;
	}

	public void setEv_1(String ev_1) {
		Ev_1 = ev_1;
	}

	public String getEv_2() {
		return Ev_2;
	}

	public void setEv_2(String ev_2) {
		Ev_2 = ev_2;
	}

	public String getEv_3() {
		return Ev_3;
	}

	public void setEv_3(String ev_3) {
		Ev_3 = ev_3;
	}

	public String getEv_4() {
		return Ev_4;
	}

	public void setEv_4(String ev_4) {
		Ev_4 = ev_4;
	}

	public String getKart_1() {
		return kart_1;
	}

	public void setKart_1(String kart_1) {
		this.kart_1 = kart_1;
	}

	public String getKart2() {
		return kart2;
	}

	public void setKart2(String kart2) {
		this.kart2 = kart2;
	}

	public String getKart_3() {
		return kart_3;
	}

	public void setKart_3(String kart_3) {
		this.kart_3 = kart_3;
	}

	public String getKart_4() {
		return kart_4;
	}

	public void setKart_4(String kart_4) {
		this.kart_4 = kart_4;
	}

	public Levha getLevhaBilgi() {
		return levhaBilgi;
	}

	public void setLevhaBilgi(Levha levhaBilgi) {
		this.levhaBilgi = levhaBilgi;
	}

	public int getCagriSayisi() {
		return cagriSayisi;
	}

	public void setCagriSayisi(int cagriSayisi) {
		this.cagriSayisi = cagriSayisi;
	}

	public String getVizit() {
		return vizit;
	}

	public void setVizit(String vizit) {
		this.vizit = vizit;
	}

	
	
	
}
