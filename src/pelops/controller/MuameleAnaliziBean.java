package pelops.controller;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import pelops.dao.MuameleAnaliziDAO;
import pelops.model.MuameleAnalizi;

@ManagedBean(name = "muameleAnaliziBean")
@SessionScoped
public class MuameleAnaliziBean {

	private ArrayList<MuameleAnalizi> muameleAnaliziListesi;
	private MuameleAnalizi muamelebilgileri;
	String icraDosyaNo = "";
	String muvekkilAdi = "";
	String oldDate = "01/01/1900";
	@SuppressWarnings("deprecation")
	Date gelisTarihi1 = new Date(oldDate), gelisTarihi2 = new Date(), kesinlesmeTarihi1 = new Date(oldDate),
			kesinlesmeTarihi2 = new Date();
	String icraMudurlugu = "";
	String dosyaTipi = "";
	String borcTipi = "";
	String takipTipi = "";
	String riskYoneticisi = "";

	public String getRiskYoneticisi() {
		return riskYoneticisi;
	}

	public void setRiskYoneticisi(String riskYoneticisi) {
		this.riskYoneticisi = riskYoneticisi;
	}

	public String getTakipTipi() {
		return takipTipi;
	}

	public void setTakipTipi(String takipTipi) {
		this.takipTipi = takipTipi;
	}

	public String getBorcTipi() {
		return borcTipi;
	}

	public void setBorcTipi(String borcTipi) {
		this.borcTipi = borcTipi;
	}

	public Date getKesinlesmeTarihi1() {
		return kesinlesmeTarihi1;
	}

	public void setKesinlesmeTarihi1(Date kesinlesmeTarihi1) {
		this.kesinlesmeTarihi1 = kesinlesmeTarihi1;
	}

	public Date getKesinlesmeTarihi2() {
		return kesinlesmeTarihi2;
	}

	public void setKesinlesmeTarihi2(Date kesinlesmeTarihi2) {
		this.kesinlesmeTarihi2 = kesinlesmeTarihi2;
	}

	public String getDosyaTipi() {
		return dosyaTipi;
	}

	public void setDosyaTipi(String dosyaTipi) {
		this.dosyaTipi = dosyaTipi;
	}

	public String getIcraMudurlugu() {
		return icraMudurlugu;
	}

	public void setIcraMudurlugu(String icraMudurlugu) {
		this.icraMudurlugu = icraMudurlugu;
	}

	public String getMuvekkilAdi() {
		return muvekkilAdi;
	}

	public Date getGelisTarihi1() {
		return gelisTarihi1;
	}

	public void setGelisTarihi1(Date gelisTarihi1) {
		this.gelisTarihi1 = gelisTarihi1;
	}

	public Date getGelisTarihi2() {
		return gelisTarihi2;
	}

	public void setGelisTarihi2(Date gelisTarihi2) {
		this.gelisTarihi2 = gelisTarihi2;
	}

	public void setMuvekkilAdi(String muvekkilAdi) {
		this.muvekkilAdi = muvekkilAdi;
	}

	public String getIcraDosyaNo() {
		return icraDosyaNo;
	}

	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
	}

	public MuameleAnalizi getMuamelebilgileri() {
		return muamelebilgileri;
	}

	public void setMuamelebilgileri(MuameleAnalizi muamelebilgileri) {
		this.muamelebilgileri = muamelebilgileri;
	}

	public ArrayList<MuameleAnalizi> getMuameleAnaliziListesi() {
		return muameleAnaliziListesi;
	}

	public void setMuameleAnaliziListesi(ArrayList<MuameleAnalizi> muameleAnaliziListesi) {
		this.muameleAnaliziListesi = muameleAnaliziListesi;
	}

	public MuameleAnaliziBean() throws Exception {
		muameleAnaliziListesi = new ArrayList<MuameleAnalizi>();
		MuameleAnaliziDAO mudao = new MuameleAnaliziDAO();
		muameleAnaliziListesi = mudao.Listele(icraDosyaNo, muvekkilAdi, icraMudurlugu, dosyaTipi, borcTipi, takipTipi,
				riskYoneticisi, gelisTarihi1, gelisTarihi2, kesinlesmeTarihi1, kesinlesmeTarihi2);

	}

	public void Listele() throws Exception {

		MuameleAnaliziDAO mudao = new MuameleAnaliziDAO();
		muameleAnaliziListesi = mudao.Listele(icraDosyaNo, muvekkilAdi, icraMudurlugu, dosyaTipi, borcTipi, takipTipi,
				riskYoneticisi, gelisTarihi1, gelisTarihi2, kesinlesmeTarihi1, kesinlesmeTarihi2);

	}

	public void dlgListele() throws Exception {
		Listele();
		RequestContext.getCurrentInstance().execute("PF('dialogWidget').show()");
	}

}
