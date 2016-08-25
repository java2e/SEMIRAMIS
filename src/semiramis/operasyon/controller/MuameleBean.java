package semiramis.operasyon.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.convert.SubreportConverter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import pelops.controller.AktifBean;
import pelops.dao.BaglantiDAO;
import pelops.dao.BorcluBilgisiDAO;
import pelops.dao.MasrafDAO;
import pelops.dao.PostaDAO;
import pelops.model.Baglanti;
import pelops.model.MasrafBilgisi;
import pelops.model.Posta;
import pelops.reports.controller.GenelYazdirBean;
import semiramis.operasyon.dao.HaczeEsasMalBilgisiDAO;
import semiramis.operasyon.dao.MuameleDAO;
import semiramis.operasyon.model.ComboItem;
import semiramis.operasyon.model.HaczeEsasMalBilgisi;
import semiramis.operasyon.model.Muamele;
import semiramis.operasyon.model.SubReport;
import semiramis.operasyon.model.TapuBilgisi;

@ManagedBean(name = "muameleBean", eager = true)
@SessionScoped
public class MuameleBean {

	public final static int MUZEKKERE_MAAS = 1;
	public final static int MUZEKKERE_MAAS_TALEP = 11;

	public final static int MUZEKKERE_TAPU = 2;

	private Muamele muamele;

	private MuameleDAO muameleDAO;

	private List<ComboItem> muzekkereList;

	private List<Muamele> muameleList;

	private List<TapuBilgisi> tapuList;

	public HaczeEsasMalBilgisiDAO haczeEsasdao = new HaczeEsasMalBilgisiDAO();

	public HaczeEsasMalBilgisi haczeEsasMalBilgisi = new HaczeEsasMalBilgisi();

	public MasrafDAO masrafDAO=new MasrafDAO();
	
	public MasrafBilgisi masraf;

	public String genelPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\pdfler\\") + "\\";

	private String pdf;

	public String muzekkereTalep = "";

	public MuameleBean() {

		muzekkereList = new ArrayList<ComboItem>();

		muamele = new Muamele();
		muameleDAO = new MuameleDAO();

		muzekkereList = muameleDAO.getMuzekkereTip();

		muameleList = new ArrayList<Muamele>();

		muameleList = muameleDAO.getMuameleList(AktifBean.icraDosyaNo);

		alanDoldur();

	}

	public void getSelectedId(int selectedId) throws Exception {

		AktifBean.icraDosyaID = selectedId;

		BaglantiDAO baglantiDAO = new BaglantiDAO();

		Baglanti baglanti = baglantiDAO.Listele(selectedId);

		AktifBean.borcluId = baglanti.getBorcluID();
		AktifBean.hesapID = baglanti.getHesaplamaID();
		
		

		System.out.println("secilen id :" + selectedId);

		muamele.setMuzekkereId(0);

		alanDoldur();

	}
	
	public void removeGayrimenkulList(int id) {
		for (int i = 0; i < tapuList.size(); i++) {
			if (tapuList.get(i).getId() == id) {
				tapuList.remove(i);
			}
		}
	}

	public void hidePopup() {

		RequestContext.getCurrentInstance().execute("PF('arama').hide()");

	}

	public void changeMuzekkereTipi() {

		if (muamele.getMuzekkereId() == MUZEKKERE_MAAS) {

			if (muamele.getBorcluIsyeriAdi() == null || muamele.getBorcluIsyeriAdres() == null) {

				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage(
						"Maaş Haciz Müzekkeresi Genel Oluşturmak için MUHATTAB ADI VE ADRESİ EKLEMELİSİNİZ !!!"));
				RequestContext.getCurrentInstance().execute("PF('dlgMuhatap').show();");

			}
		}

		else if (muamele.getMuzekkereId() == MUZEKKERE_TAPU) {

			RequestContext.getCurrentInstance().execute("PF('dlgGayrimenkul').show();");

		}

	}

	public void alanDoldur() // Borlcuya göre Muzekkere Alanları doldurulur
	{

		if (AktifBean.icraDosyaID != 0) {

			muamele = muameleDAO.getMuamele(AktifBean.icraDosyaID);

			muamele.setMumaleTarihi(new Date());

			muamele.setBorcMiktari(muamele.getToplamAlacak() - muamele.getTahsilatMiktari());

			muamele.setBorcMiktari(
					Double.valueOf(new DecimalFormat("0.00").format(muamele.getBorcMiktari()).replace(",", ".")));

			muamele.setPttAdi("PTT İSKİTLER MERKEZ MÜDÜRLÜĞÜ");
			muamele.setAvukatIBAN("TR3000 1230 0067 1038 9292 8100");

			muameleList = new ArrayList<Muamele>();

			muameleList = muameleDAO.getMuameleList(muamele.getIcraDosyaNo());

			tapuList = new ArrayList<TapuBilgisi>();

			List<HaczeEsasMalBilgisi> liste = haczeEsasdao.liste(muamele.getBorcluId(), 1);

			for (int i = 0; i < liste.size(); i++) {

				TapuBilgisi tapu = new TapuBilgisi();
				tapu.setId(liste.get(i).getId());
				tapu.setAciklama(liste.get(i).getTapuAciklama());
				tapu.setIl(liste.get(i).getIlAdi());
				tapu.setIlce(liste.get(i).getIlceAdi());
				tapu.setIlId(liste.get(i).getTapuIlId());
				tapu.setIlceId(liste.get(i).getTapuIlceId());
				tapu.setAda(liste.get(i).getTapuCiltNo());
				tapu.setParsel(liste.get(i).getTapuParsel());
				tapu.setTapuMudurlugu(liste.get(i).getTapuSicilMudurluk());

				tapuList.add(tapu);
			}

		}

	}

	public void nihaiKayit() {

		muamele.setBarkodTxt(barkodUret());

		

		haczeEsasMalBilgisi.setIcraDosyaId(muamele.getIcraDosyaID());
		haczeEsasMalBilgisi.setBorcluId(muamele.getBorcluId());

		if (muamele.getMuzekkereId() == MUZEKKERE_MAAS) {

			// Hacze Esas Mal Bilgisine Ekleniyor..
			
			muameleDAO.kaydet(muamele);

			haczeEsasMalBilgisi.setMalTipiId(2); // SGK
			haczeEsasMalBilgisi.setMuhatapAdi(muamele.getBorcluIsyeriAdi());
			haczeEsasMalBilgisi.setMuhatapAdresi(muamele.getBorcluIsyeriAdres());

			haczeEsasdao.kaydet(haczeEsasMalBilgisi);

			muamele.setMuzekkereId(MUZEKKERE_MAAS_TALEP);
			muameleDAO.kaydet(muamele);
			
			muamele.setMuzekkereId(MUZEKKERE_MAAS);
			
			masraf=masrafEkle();
			
			masraf.setMasrafMiktari(11.0);
			masraf.setMasrafAciklama("Maaş Müzekkere Tebligat Masrafı");
			
			masrafDAO.kaydet(masraf);
			
			
			
			

		}

		else if (muamele.getMuzekkereId() == MUZEKKERE_TAPU) {

			TapuBilgisi bilgi = new TapuBilgisi();

			HashMap<String, List<TapuBilgisi>> map = new HashMap<String, List<TapuBilgisi>>();


			for (int i = 0; i < tapuList.size(); i++) {

				if (map.get(tapuList.get(i).getIlce()) != null) {
					
					bilgi = new TapuBilgisi();

					bilgi.setTapuKayitAciklama(tapuList.get(i).getIl().toUpperCase() + " "
							+ tapuList.get(i).getIlce().toUpperCase() + " ADA :" + tapuList.get(i).getAda()
							+ " PARSEL :" + tapuList.get(i).getParsel() + " / " + tapuList.get(i).getAciklama());
					bilgi.setId(tapuList.get(i).getId());

					map.get(tapuList.get(i).getIlce()).add(bilgi);

				} else {
					
					bilgi = new TapuBilgisi();

					List<TapuBilgisi> liste = new ArrayList<>();

					map.put(tapuList.get(i).getIlce(), liste);

					bilgi.setTapuKayitAciklama(tapuList.get(i).getIl().toUpperCase() + " "
							+ tapuList.get(i).getIlce().toUpperCase() + " ADA :" + tapuList.get(i).getAda()
							+ " PARSEL :" + tapuList.get(i).getParsel() + " / " + tapuList.get(i).getAciklama());
					bilgi.setId(tapuList.get(i).getId());

					map.get(tapuList.get(i).getIlce()).add(bilgi);

				}

			}

			for (Entry<String, List<TapuBilgisi>> entry : map.entrySet())
			{
				String key = entry.getKey();
				List<TapuBilgisi> value = entry.getValue();

				
				muamele.setTapuAciklama("");
				muamele.setHaczeEsasMalId("");
 
				String id="";
				String aciklama="";
				for (int i = 0; i < value.size(); i++) {
				
					
					aciklama=aciklama+value.get(i).getTapuKayitAciklama();
					
					if(value.size()-1!=i)
						aciklama=aciklama+"<br>";
					
					
					id=id+value.get(i).getId();
					
					if(value.size()-1!=i)
						id=id+",";
					
				}
				
				
				muamele.setTapuAciklama(aciklama);
				muamele.setHaczeEsasMalId(id);
				
				muameleDAO.kaydet(muamele);
				
			}
			
			masraf=masrafEkle();
			
			masraf.setMasrafMiktari(11.0);
			masraf.setMasrafAciklama("Tapu Müzekkere Tebligat Masrafı");
			masraf.setMasrafTipiId(4);
			
			
			masrafDAO.kaydet(masraf);
			
			masraf=masrafEkle();
			
			masraf.setMasrafMiktari(5.10);
			masraf.setMasrafAciklama("Tapu Posta Pulu Masrafı");
			masraf.setMasrafTipiId(4);
			
			
			masrafDAO.kaydet(masraf);
			

		}

		muameleList = new ArrayList<Muamele>();

		muameleList = muameleDAO.getMuameleList(muamele.getIcraDosyaNo());

	}
	
	public MasrafBilgisi masrafEkle()
	{
		MasrafBilgisi masrafT=new MasrafBilgisi();
		
		masrafT.setBorcluId(muamele.getBorcluId());
		masrafT.setIcra_dosyasi_id(muamele.getIcraDosyaID());
		masrafT.setIcra_dosyasi_no(muamele.getIcraDosyaNo());
		masrafT.setMasrafTarihi(new Date());
		masrafT.setMuvekkilAdi(muamele.getMuvekkilAdi());
		
		return masrafT;
	}

	public void duzenle(int id) {

		List<JasperPrint> listJasperPrint = new ArrayList<JasperPrint>();

		muamele = muameleDAO.getMuameleDuzenle(id);

		if (muamele.getMuzekkereId() == MUZEKKERE_MAAS) {

			muzekkereTalep = "maashacizmuzekkeresigenel";

		}

		else if (muamele.getMuzekkereId() == MUZEKKERE_MAAS_TALEP) {
			muzekkereTalep = "maashaciztalebigenel";
		}
		
		else if(muamele.getMuzekkereId()==MUZEKKERE_TAPU)
		{
			muzekkereTalep="tapuhacizmuzekkeresinokta";
			
			
			muamele.setTapuMudurlugu(muamele.getTapuAciklama().split(" ")[1]);
			
			List<SubReport> liste=new ArrayList<SubReport>();
			
			for(int i=0;i<muamele.getTapuAciklama().split("<br>").length;i++)
			{
				SubReport report=new SubReport();
				report.setKayit(muamele.getTapuAciklama().split("<br>")[i]);
				liste.add(report);
				
			}
			
			muamele.setSubReportList(liste);
			
			
			
			
		}

		try {

			muamele.setBarkod(new GenelYazdirBean().createBarcode(muamele.getBarkodTxt()));

			if (muamele.getMuzekkereId() > 10)
				listJasperPrint.add(new MuzekkereJasper().getMuzekkere(muzekkereTalep, muamele));
			else {
				listJasperPrint.add(new MuzekkereJasper().getMuzekkere(muzekkereTalep, muamele));

				listJasperPrint.add(new MuzekkereJasper().tebligatZarfiJasper(muamele, muzekkereTalep));

				listJasperPrint.add(new MuzekkereJasper().tebligatListesiJasper(muamele, muzekkereTalep));
			}

			String path = genelPath + muzekkereTalep + ".pdf";

			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(SimpleExporterInput.getInstance(listJasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
			SimplePdfExporterConfiguration config = new SimplePdfExporterConfiguration();
			config.setCreatingBatchModeBookmarks(true);
			exporter.exportReport();

			// Oluşturulan PDF'lerin Gösterimi Sağlanır

			pdf = path;

		} catch (Exception e) {

			System.out.println("Hata muzekkere bean duzenle " + e.getMessage());

		}

	}
	
	
	
	public void topluListe()
		{
			
			List<JasperPrint> listJasperPrint = new ArrayList<JasperPrint>();
			
			try {
				
				
				for (int i = 0; i < muameleList.size(); i++) {
					
					
					muamele = muameleDAO.getMuameleDuzenle(muameleList.get(i).getId());
					
					
					if (muamele.getMuzekkereId() == MUZEKKERE_MAAS) {
	
						muzekkereTalep = "maashacizmuzekkeresigenel";
	
					}
	
					else if (muamele.getMuzekkereId() == MUZEKKERE_MAAS_TALEP) {
						muzekkereTalep = "maashaciztalebigenel";
					}
					
					else if(muamele.getMuzekkereId()==MUZEKKERE_TAPU)
					{
						muzekkereTalep="tapuhacizmuzekkeresinokta";
						
						
						muamele.setTapuMudurlugu(muamele.getTapuAciklama().split(" ")[1]);
						
						List<SubReport> liste=new ArrayList<SubReport>();
						
						for(int j=0;j<muamele.getTapuAciklama().split("<br>").length;j++)
						{
							SubReport report=new SubReport();
							report.setKayit(muamele.getTapuAciklama().split("<br>")[j]);
							liste.add(report);
							
						}
						
						muamele.setSubReportList(liste);
						
						
						
						
					}
	
						muamele.setBarkod(new GenelYazdirBean().createBarcode(muamele.getBarkodTxt()));
	
						if (muamele.getMuzekkereId() > 10)
							listJasperPrint.add(new MuzekkereJasper().getMuzekkere(muzekkereTalep, muamele));
						else {
							listJasperPrint.add(new MuzekkereJasper().getMuzekkere(muzekkereTalep, muamele));
	
							listJasperPrint.add(new MuzekkereJasper().tebligatZarfiJasper(muamele, muzekkereTalep));
	
							listJasperPrint.add(new MuzekkereJasper().tebligatListesiJasper(muamele, muzekkereTalep));
						}
	
					
					
					
					
				}
				
				String path = genelPath + muzekkereTalep + ".pdf";
	
				JRPdfExporter exporter = new JRPdfExporter();
				exporter.setExporterInput(SimpleExporterInput.getInstance(listJasperPrint));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				SimplePdfExporterConfiguration config = new SimplePdfExporterConfiguration();
				config.setCreatingBatchModeBookmarks(true);
				exporter.exportReport();
	
				// Oluşturulan PDF'lerin Gösterimi Sağlanır
	
				pdf = path;
	
				
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
		}
	
	
	
	public void sil(int id)
	{
		
		muameleDAO.sil(id);
		
		muameleList=muameleDAO.getMuameleList(AktifBean.icraDosyaNo);
		
		
	}

	public void borcluMuhatapGuncelleme() {
		try {

			BorcluBilgisiDAO dao = new BorcluBilgisiDAO();

			dao.guncellemeMuhattapBilgisi(muamele.getBorcluIsyeriAdi(), muamele.getBorcluIsyeriAdres(),
					muamele.getBorcluId());

			RequestContext.getCurrentInstance().execute("PF('dlgMuhatap').hide();");

		} catch (Exception e) {

			System.out.println("Hata muameleBean borcluMuhatapGuncelleme :" + e.getMessage());

			// TODO: handle exception
		}
	}

	public String barkodUret() {
		String uretilenBarkod = "";
		try {

			String barkod = null;
			Posta posta = null;
			PostaDAO postaDAO = new PostaDAO();

			barkod = postaDAO.checkBarkod(muamele.getIcraDosyaID());
			posta = postaDAO.BarkodVer();

			if (barkod == null || muamele.getBarkodTxt() == "") {

				uretilenBarkod = posta.getBarkod();
				posta.setDurum(1);
				posta.setIcra_dosya_id(muamele.getIcraDosyaID());
				postaDAO.Duzenle(posta);

			} else {
				uretilenBarkod = barkod;
			}
		} catch (Exception e) {
			System.out.println("Hata muamelebean barkoduret :" + e.getMessage());
		}

		return uretilenBarkod;

	}

	public String getPdf() {

		if (muameleList.isEmpty()) {
			pdf = "./pdfler/default.pdf?pfdrid_c=true";

		} else {

			pdf = "./pdfler/" + muzekkereTalep + ".pdf?pfdrid_c=true";

		}

		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public Muamele getMuamele() {
		return muamele;
	}

	public void setMuamele(Muamele muamele) {
		this.muamele = muamele;
	}

	public List<ComboItem> getMuzekkereList() {
		return muzekkereList;
	}

	public void setMuzekkereList(List<ComboItem> muzekkereList) {
		this.muzekkereList = muzekkereList;
	}

	public List<Muamele> getMuameleList() {
		return muameleList;
	}

	public void setMuameleList(List<Muamele> muameleList) {
		this.muameleList = muameleList;
	}

	public List<TapuBilgisi> getTapuList() {
		return tapuList;
	}

	public void setTapuList(List<TapuBilgisi> tapuList) {
		this.tapuList = tapuList;
	}

}
