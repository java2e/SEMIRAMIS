package pelops.report.model;

import java.util.ArrayList;

public class ConstructedData {

	private ReportGenel genel = new ReportGenel();

	private ArrayList<DataToPrint> dataToPrints = new ArrayList<DataToPrint>();

	public ReportGenel getGenel() {
		return genel;
	}

	public void setGenel(ReportGenel genel) {
		this.genel = genel;
	}

	public ArrayList<DataToPrint> getDataToPrints() {
		return dataToPrints;
	}

	public void setDataToPrints(ArrayList<DataToPrint> dataToPrints) {
		this.dataToPrints = dataToPrints;
	}

}
