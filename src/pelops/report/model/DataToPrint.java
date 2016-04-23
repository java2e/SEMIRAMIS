package pelops.report.model;

import java.util.ArrayList;

import net.sf.jasperreports.engine.JasperPrint;

public class DataToPrint {

	private int adet;
	private String belgeAdi;
	private String JasperFileName;
	private ArrayList<JasperPrint> jasperPrint;

	public int getAdet() {
		return adet;
	}

	public void setAdet(int adet) {
		this.adet = adet;
	}

	public String getBelgeAdi() {
		return belgeAdi;
	}

	public void setBelgeAdi(String belgeAdi) {
		this.belgeAdi = belgeAdi;
	}

	public String getJasperFileName() {
		return JasperFileName;
	}

	public void setJasperFileName(String jasperFileName) {
		JasperFileName = jasperFileName;
	}

	public ArrayList<JasperPrint> getJasperPrint() {
		return jasperPrint;
	}

	public void setJasperPrint(ArrayList<JasperPrint> jasperPrint) {
		this.jasperPrint = jasperPrint;
	}

}
