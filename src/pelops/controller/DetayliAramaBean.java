package pelops.controller;


import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import pelops.dao.GelismisAramaDAO;
import pelops.model.DetayliArama;

@ManagedBean(name="detayliAramaBean")
@ViewScoped
public class DetayliAramaBean {

	ArrayList<DetayliArama> detayliAramaListesi;
	ArrayList<DetayliArama> filterDetayliAramaListesi;
	
	public ArrayList<DetayliArama> getFilterDetayliAramaListesi() {
		return filterDetayliAramaListesi;
	}




	public void setFilterDetayliAramaListesi(
			ArrayList<DetayliArama> filterDetayliAramaListesi) {
		this.filterDetayliAramaListesi = filterDetayliAramaListesi;
	}


	private String muvekkilAdi, borcluAdi, icraDosyaNo, icraMudurlugu, cepTel, isTel;
	private int bankaServisNo, avukatCikisSevisNo, bankaMusteriNo;
	String oldDate="01/01/1900";
	@SuppressWarnings("deprecation")
	private Date gelisTarihi1 = new Date(oldDate), gelisTarihi2=new Date(), hitamTarihi1=new Date(oldDate), hitamTarihi2=new Date(), takipTarihi1=new Date(oldDate), takipTarihi2=new Date();
	
	
	public void arama(int id)
	{
		
		RequestContext.getCurrentInstance().execute("PF('dlgdetayliarama').hide()");
	}
	
	
	
	
	public ArrayList<DetayliArama> getDetayliAramaListesi() {
		return detayliAramaListesi;
	}
	public void setDetayliAramaListesi(ArrayList<DetayliArama> detayliAramaListesi) {
		this.detayliAramaListesi = detayliAramaListesi;
		this.filterDetayliAramaListesi = detayliAramaListesi;
	}
	public String getMuvekkilAdi() {
		return muvekkilAdi;
	}
	public void setMuvekkilAdi(String muvekkilAdi) {
		this.muvekkilAdi = muvekkilAdi;
	}
	public String getBorcluAdi() {
		return borcluAdi;
	}
	public void setBorcluAdi(String borcluAdi) {
		this.borcluAdi = borcluAdi;
	}
	public String getIcraDosyaNo() {
		return icraDosyaNo;
	}
	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
	}
	public String getIcraMudurlugu() {
		return icraMudurlugu;
	}
	public void setIcraMudurlugu(String icraMudurlugu) {
		this.icraMudurlugu = icraMudurlugu;
	}
	public String getCepTel() {
		return cepTel;
	}
	public void setCepTel(String cepTel) {
		this.cepTel = cepTel;
	}
	public String getIsTel() {
		return isTel;
	}
	public void setIsTel(String isTel) {
		this.isTel = isTel;
	}
	public int getBankaServisNo() {
		return bankaServisNo;
	}
	public void setBankaServisNo(int bankaServisNo) {
		this.bankaServisNo = bankaServisNo;
	}
	public int getAvukatCikisSevisNo() {
		return avukatCikisSevisNo;
	}
	public void setAvukatCikisSevisNo(int avukatCikisSevisNo) {
		this.avukatCikisSevisNo = avukatCikisSevisNo;
	}
	public int getBankaMusteriNo() {
		return bankaMusteriNo;
	}
	public void setBankaMusteriNo(int bankaMusteriNo) {
		this.bankaMusteriNo = bankaMusteriNo;
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
	public Date getHitamTarihi1() {
		return hitamTarihi1;
	}
	public void setHitamTarihi1(Date hitamTarihi1) {
		this.hitamTarihi1 = hitamTarihi1;
	}
	public Date getHitamTarihi2() {
		return hitamTarihi2;
	}
	public void setHitamTarihi2(Date hitamTarihi2) {
		this.hitamTarihi2 = hitamTarihi2;
	}
	public Date getTakipTarihi1() {
		return takipTarihi1;
	}
	public void setTakipTarihi1(Date takipTarihi1) {
		this.takipTarihi1 = takipTarihi1;
	}
	public Date getTakipTarihi2() {
		return takipTarihi2;
	}
	public void setTakipTarihi2(Date takipTarihi2) {
		this.takipTarihi2 = takipTarihi2;
	}
	

	public void Listele() throws Exception{
			
		GelismisAramaDAO geldao = new GelismisAramaDAO();
		detayliAramaListesi = geldao.Listele(muvekkilAdi, borcluAdi, icraDosyaNo, icraMudurlugu, 
				cepTel, isTel, bankaServisNo, avukatCikisSevisNo, bankaMusteriNo, gelisTarihi1, 
				gelisTarihi2, hitamTarihi1, hitamTarihi2, takipTarihi1, takipTarihi2);
	
	}
}
