package pelops.chronology.controller;

import java.util.ArrayList;

import pelops.chronology.model.ReportChronology;
import pelops.chronology.model.Task;

public class ReportChronologyCtrl {

	private static ReportChronologyCtrl chronologyCtrl;

	private static ReportChronologyCtrl createInstance() {
		chronologyCtrl = new ReportChronologyCtrl();

		return chronologyCtrl;
	}

	public static ReportChronologyCtrl getInstance() {
		if (chronologyCtrl == null) {
			createInstance();
		}
		return chronologyCtrl;
	}

	public ArrayList<Task> getTaskListFromRC() throws Exception {
		ArrayList<Task> tasks = new ArrayList<>();
		ArrayList<Object> rcList = ReportChronologyUtil.getInstance().getAllObjFromDB();
		for (Object object : rcList) {
			for (int i = 0; i < rcList.size(); i++) {

				if (object instanceof ReportChronology && rcList.get(i) instanceof ReportChronology) {
					ReportChronology chronology = (ReportChronology) object;
					ReportChronology chronology2 = (ReportChronology) rcList.get(i);

					if (chronology.getIcraDosyaID() == chronology2.getIcraDosyaID()) {

					}

				}
			}

		}

		return tasks;
	}

}
