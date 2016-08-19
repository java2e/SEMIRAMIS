package semiramis.operasyon.model;

import java.util.Date;


public class IzlemeBilgisi {

	private int id;
	private Date izlemeTarihi;
	private Date izlemeSonucuTarihi;
	private Date odemeSozuTarihi;
	private Double odemeSozuMiktari;
	private String aciklama;
	private int PersonelId;
	private int icraDosyasiId;
	private int IzlemeSonucuId;
	private String izlemeSonucu;
	private boolean vizitDurumu;
	private int cagriAdet;

	


	public String getIzlemeSonucu() {
		return izlemeSonucu;
	}

	public void setIzlemeSonucu(String izlemeSonucu) {
		this.izlemeSonucu = izlemeSonucu;
	}

	public int getIzlemeSonucuId() {
		return IzlemeSonucuId;
	}

	public void setIzlemeSonucuId(int izlemeSonucuId) {
		IzlemeSonucuId = izlemeSonucuId;
	}

	public int getIcraDosyasiId() {
		return icraDosyasiId;
	}

	public void setIcraDosyasiId(int icraDosyasiId) {
		this.icraDosyasiId = icraDosyasiId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getIzlemeTarihi() {
		return izlemeTarihi;
	}

	public void setIzlemeTarihi(Date izlemeTarihi) {
		this.izlemeTarihi = izlemeTarihi;
	}

	public Date getIzlemeSonucuTarihi() {
		return izlemeSonucuTarihi;
	}

	public void setIzlemeSonucuTarihi(Date izlemeSonucuTarihi) {
		this.izlemeSonucuTarihi = izlemeSonucuTarihi;
	}

	public Date getOdemeSozuTarihi() {
		return odemeSozuTarihi;
	}

	public void setOdemeSozuTarihi(Date odemeSozuTarihi) {
		this.odemeSozuTarihi = odemeSozuTarihi;
	}

	public Double getOdemeSozuMiktari() {
		return odemeSozuMiktari;
	}

	public void setOdemeSozuMiktari(Double odemeSozuMiktari) {
		this.odemeSozuMiktari = odemeSozuMiktari;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	public int getPersonelId() {
		return PersonelId;
	}

	public void setPersonelId(int personelId) {
		PersonelId = personelId;
	}

	public boolean isVizitDurumu() {
		return vizitDurumu;
	}

	public void setVizitDurumu(boolean vizitDurumu) {
		this.vizitDurumu = vizitDurumu;
	}

	public int getCagriAdet() {
		return cagriAdet;
	}

	public void setCagriAdet(int cagriAdet) {
		this.cagriAdet = cagriAdet;
	}


	
	
	

}
