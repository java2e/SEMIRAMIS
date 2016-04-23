package pelops.report.model;

public class AkbankHesap {

	private int borclu_id;
	private String toplam1;
	private String tarih;
	private String ekler;
	private String giderVergisi;
	private String borc_bilgi;
	private String temmerutFaizi;
	private String asilAlacak;

	public String getToplam1() {
		return toplam1;
	}

	public int getBorclu_id() {
		return borclu_id;
	}

	public void setBorclu_id(int borclu_id) {
		this.borclu_id = borclu_id;
	}

	public void setToplam1(String toplam1) {
		this.toplam1 = toplam1;
	}

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

	public String getEkler() {
		return ekler;
	}

	public void setEkler(String ekler) {
		this.ekler = ekler;
	}

	public String getGiderVergisi() {
		return giderVergisi;
	}

	public void setGiderVergisi(String giderVergisi) {
		this.giderVergisi = giderVergisi;
	}

	public String getBorc_bilgi() {
		return borc_bilgi;
	}

	public void setBorc_bilgi(String borc_bilgi) {
		this.borc_bilgi = borc_bilgi;
	}

	public String getTemmerutFaizi() {
		return temmerutFaizi;
	}

	public void setTemmerutFaizi(String temmerutFaizi) {
		this.temmerutFaizi = temmerutFaizi;
	}

	public String getAsilAlacak() {
		return asilAlacak;
	}

	public void setAsilAlacak(String asilAlacak) {
		this.asilAlacak = asilAlacak;
	}

}
