package pelops.chronology.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.primefaces.extensions.model.timeline.TimelineEvent;

import pelops.chronology.model.Chronology2;
import pelops.chronology.model.ControlDateAndType2;
import pelops.chronology.model.Instance;
import pelops.chronology.model.ReportChronology2;
import pelops.chronology.model.Task;
import pelops.report.model.ReportUtils;

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

	public ArrayList<TimelineEvent> getAllEvents(Integer icraDosyaId) throws Exception {
		ArrayList<TimelineEvent> events = new ArrayList<>();
		ArrayList<Task> tasks = getTaskList(icraDosyaId);
		ArrayList<Task> instances = getTaskListFromInstances(icraDosyaId);

		for (Task task : tasks) {
			TimelineEvent event = new TimelineEvent(task, task.getTarih());
			events.add(event);
		}
		for (Task task : instances) {
			TimelineEvent event = new TimelineEvent(task, task.getTarih());
			events.add(event);

		}
		return events;
	}

	// :TODO Yeni eklenecek raporlarda tekrar güncellenmeli

	public ArrayList<Task> getTaskList(int id) throws Exception {
		ArrayList<Task> tasks = new ArrayList<>();
		HashSet<ReportChronology2> chronologies = getChronologiesFromRC(id);
		ArrayList<ControlDateAndType2> andTypes = new ArrayList<>();
		HashSet<Date> dates = new HashSet<>();
		for (ReportChronology2 reportChronology : chronologies) {
			dates.add(reportChronology.getTarih());
		}
		for (Date date : dates) {
			ControlDateAndType2 andType = new ControlDateAndType2();

			for (ReportChronology2 reportChronology : chronologies) {
				if (date.toGMTString().equals(reportChronology.getTarih().toGMTString())) {
					if (reportChronology.getBelgeAdi() == ReportUtils.REPORT_IHTARNAME) {
						andType.setIhtarname(true);

					} else if (reportChronology.getBelgeAdi().equals(ReportUtils.REPORT_ODEME_EMRI)) {

						andType.setOdemeEmri(true);

					} else if (reportChronology.getBelgeAdi().equals(ReportUtils.REPORT_TAKIP_TALEBI)) {
						andType.setTakipTalebi(true);
					} else if (reportChronology.getBelgeAdi().equals(ReportUtils.REPORT_VEKALETNAME)) {
						andType.setVekaletname(true);

					} else if (reportChronology.getBelgeAdi().equals(ReportUtils.REPORT_TEBLIGAT_ZARFI)) {
						andType.setTebligatZarfi(true);

					} else if (reportChronology.getBelgeAdi().equals(ReportUtils.REPORT_TEBLIGAT_LISTESI)) {
						andType.setTebligatListesi(true);

					}
				}

			}
			andType.setTarih(date);
			andTypes.add(andType);

		}

		for (ControlDateAndType2 controlDateAndType : andTypes) {
			if (controlDateAndType.isTakipTalebi() && controlDateAndType.isOdemeEmri()) {
				Task task = new Task(ReportChronologyUtil.ICRADOSYASI_GIRECEK_EVRAK, ReportChronologyUtil.IMAGE_I_D_G_E,
						false, id, controlDateAndType.getTarih());
				tasks.add(task);
			}
			if (controlDateAndType.isTebligatZarfi() && controlDateAndType.isOdemeEmri()) {

				Task task = new Task(ReportChronologyUtil.BANKA_GONDERILECEK_EVRAK, ReportChronologyUtil.IMAGE_B_G_E,
						false, id, controlDateAndType.getTarih());
				tasks.add(task);
			}
			if (controlDateAndType.isTebligatListesi()) {
				Task task = new Task(ReportChronologyUtil.TEBLIGAT_LISTE_EVRAK, ReportChronologyUtil.IMAGE_T_L, false,
						id, controlDateAndType.getTarih());
				tasks.add(task);
			}
		}
		return tasks;

	}

	private HashSet<ReportChronology2> getChronologiesFromRC(int icraDosyaID) throws Exception {

		HashSet<ReportChronology2> reportChronologies = new HashSet<>();
		List reports = ReportChronologyUtil.getInstance().getObjFromDBWithIcraDosyaID(icraDosyaID);
		for (Object object2 : reports) {
			if (object2 instanceof ReportChronology2) {
				reportChronologies.add((ReportChronology2) object2);
			}
		}

		return reportChronologies;
	}

	private ArrayList<Task> getTaskListFromInstances(int id) throws Exception {
		ArrayList<Task> tasks = new ArrayList<>();

		List instances = InstanceUtil.getInstance().getAllInstances(id);

		for (Object object : instances) {

			if (object instanceof Instance) {
				String imagePath = "";
				if (((Instance) object).getDurum().equals(InstanceUtil.DURUM_KAYDET)) {
					imagePath = ReportChronologyUtil.IMAGE_FILE_O;
				} else if (((Instance) object).getDurum().equals(InstanceUtil.DURUM_GUNCELLE)) {
					imagePath = ReportChronologyUtil.IMAGE_FILE_U;

				} else if (((Instance) object).getDurum().equals(InstanceUtil.DURUM_SIL)) {
					imagePath = ReportChronologyUtil.IMAGE_FILE_D;

				}

				Task task = new Task(((Instance) object).getOlayAdi(), imagePath, false, id,
						((Instance) object).getTarih());
				tasks.add(task);
			}

		}
		return tasks;
	}

}
