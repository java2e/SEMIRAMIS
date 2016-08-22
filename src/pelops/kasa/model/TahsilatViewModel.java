package pelops.kasa.model;

import java.util.Date;

public class TahsilatViewModel {
	private int id;
	private String fromID;
	private int icraDosyaID;
	private Date tarih;
	private String durum; // Tahislat yapılmış olan viewlerin durumu 0: onayda
							// , 1: onaylanmıs, 2: red/iptal, 3:makbuz
							// yazdırılmış, 4: işlem görmemiş
	private String borcluAdi;
	private Double odemeMiktari;
	private String hangiView;

	private String odemeMiktariTL;

	private String icraDosyaNo;
	
	private String baknaServisNo;
	private String musteriNo;
	
	private int izleme_id;
	private int vizit_id;
	private int odemeplani_id;
	
	private int soz_alan_personel_id;
	
	private String muvekkil_adi;
	
	private String icraMudurluk;
	
	
	
	

	public String getIcraMudurluk() {
		return icraMudurluk;
	}

	public void setIcraMudurluk(String icraMudurluk) {
		this.icraMudurluk = icraMudurluk;
	}

	public String getMuvekkil_adi() {
		return muvekkil_adi;
	}

	public void setMuvekkil_adi(String muvekkil_adi) {
		this.muvekkil_adi = muvekkil_adi;
	}

	public int getSoz_alan_personel_id() {
		return soz_alan_personel_id;
	}

	public void setSoz_alan_personel_id(int soz_alan_personel_id) {
		this.soz_alan_personel_id = soz_alan_personel_id;
	}

	public int getIzleme_id() {
		return izleme_id;
	}

	public void setIzleme_id(int izleme_id) {
		this.izleme_id = izleme_id;
	}

	public int getVizit_id() {
		return vizit_id;
	}

	public void setVizit_id(int vizit_id) {
		this.vizit_id = vizit_id;
	}

	public int getOdemeplani_id() {
		return odemeplani_id;
	}

	public void setOdemeplani_id(int odemeplani_id) {
		this.odemeplani_id = odemeplani_id;
	}
	

	public String getBaknaServisNo() {
		return baknaServisNo;
	}

	public void setBaknaServisNo(String baknaServisNo) {
		this.baknaServisNo = baknaServisNo;
	}

	public String getMusteriNo() {
		return musteriNo;
	}

	public void setMusteriNo(String musteriNo) {
		this.musteriNo = musteriNo;
	}
	

	public String getOdemeMiktariTL() {
		return odemeMiktariTL;
	}

	public void setOdemeMiktariTL(String odemeMiktariTL) {
		this.odemeMiktariTL = odemeMiktariTL;
	}

	public String getIcraDosyaNo() {
		return icraDosyaNo;
	}

	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
	}

	private String personelAdi;

	public String getPersonelAdi() {
		return personelAdi;
	}

	public void setPersonelAdi(String personelAdi) {
		this.personelAdi = personelAdi;
	}

	public String getFromID() {
		return fromID;
	}

	public void setFromID(String fromID) {
		this.fromID = fromID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIcraDosyaID() {
		return icraDosyaID;
	}

	public void setIcraDosyaID(int icraDosyaID) {
		this.icraDosyaID = icraDosyaID;
	}

	public String getHangiView() {
		return hangiView;
	}

	public void setHangiView(String hangiView) {
		this.hangiView = hangiView;
	}

	public Double getOdemeMiktari() {
		return odemeMiktari;
	}

	public void setOdemeMiktari(Double odemeMiktari) {
		this.odemeMiktari = odemeMiktari;
	}

	public Date getTarih() {
		return tarih;
	}

	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}

	public String getDurum() {
		return durum;
	}

	public void setDurum(String durum) {
		this.durum = durum;
	}

	public String getBorcluAdi() {
		return borcluAdi;
	}

	public void setBorcluAdi(String borcluAdi) {
		this.borcluAdi = borcluAdi;
	}

}
