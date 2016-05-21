package pelops.chronology.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.primefaces.extensions.model.timeline.TimelineEvent;

import pelops.chronology.model.Chronology;
import pelops.chronology.model.ReportChronology;
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
		for (Task task : tasks) {
			TimelineEvent event = new TimelineEvent(task, task.getTarih());
			events.add(event);
		}
		return events;
	}

	private boolean ihtarname = false;
	private boolean odemeEmri = false;
	private boolean vekaletname = false;
	private boolean takipTalebi = false;
	private boolean tebligatZarfi = false;
	private boolean tebligatListesi = false;
	// :TODO Yeni eklenecek raporlarda tekrar güncellenmeli

	public ArrayList<Task> getTaskList(int id) throws Exception {
		ArrayList<Task> tasks = new ArrayList<>();
		HashSet<ReportChronology> chronologies = getChronologiesFromRC(id);

		Date tarih = null;
		ReportChronology reportChronolog = null;
		for (ReportChronology reportChronology : chronologies) {
			if (reportChronology.getBelgeAdi() == ReportUtils.REPORT_IHTARNAME) {
				ihtarname = true;
			} else if (reportChronology.getBelgeAdi().equals(ReportUtils.REPORT_ODEME_EMRI)) {
				odemeEmri = true;
			} else if (reportChronology.getBelgeAdi().equals(ReportUtils.REPORT_TAKIP_TALEBI)) {
				takipTalebi = true;
			} else if (reportChronology.getBelgeAdi().equals(ReportUtils.REPORT_VEKALETNAME)) {
				vekaletname = true;
			} else if (reportChronology.getBelgeAdi().equals(ReportUtils.REPORT_TEBLIGAT_ZARFI)) {
				tebligatZarfi = true;
			} else if (reportChronology.getBelgeAdi().equals(ReportUtils.REPORT_TEBLIGAT_LISTESI)) {
				tebligatListesi = true;
			}
			tarih = reportChronology.getTarih();
			reportChronolog = reportChronology;
		}
		if (takipTalebi && odemeEmri) {
			Task task = new Task(ReportChronologyUtil.ICRADOSYASI_GIRECEK_EVRAK, ReportChronologyUtil.IMAGE_I_D_G_E,
					false, reportChronolog.getIcraDosyaID(), tarih);
			tasks.add(task);
		}
		if (tebligatZarfi && odemeEmri) {

			Task task = new Task(ReportChronologyUtil.BANKA_GONDERILECEK_EVRAK, ReportChronologyUtil.IMAGE_B_G_E, false,
					reportChronolog.getIcraDosyaID(), tarih);
			tasks.add(task);
		}
		if (tebligatListesi) {
			Task task = new Task(ReportChronologyUtil.TEBLIGAT_LISTE_EVRAK, ReportChronologyUtil.IMAGE_T_L, false,
					reportChronolog.getIcraDosyaID(), tarih);
			tasks.add(task);
		} else {
			Task task = new Task(ReportChronologyUtil.DIGER, ReportChronologyUtil.IMAGE_D, false,
					reportChronolog.getIcraDosyaID(), tarih);
			tasks.add(task);
		}

		return tasks;

	}

	private HashSet<ReportChronology> getChronologiesFromRC(int icraDosyaID) throws Exception {

		HashSet<ReportChronology> reportChronologies = new HashSet<>();
		List reports = ReportChronologyUtil.getInstance().getObjFromDBWithIcraDosyaID(icraDosyaID);
		for (Object object2 : reports) {
			if (object2 instanceof ReportChronology) {
				reportChronologies.add((ReportChronology) object2);
			}
		}

		return reportChronologies;
	}

}
