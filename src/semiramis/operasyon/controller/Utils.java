package semiramis.operasyon.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.primefaces.extensions.model.timeline.TimelineEvent;

import pelops.controller.AktifBean;
import semiramis.operasyon.dao.UtilsDAO;
import semiramis.operasyon.model.Chronology;
import semiramis.operasyon.model.ChronologyIdentifier;
import semiramis.operasyon.model.Task;

public class Utils {

	/**
	 * Bu sınıf üzerinden kronoloji kadi yapılıyor: Ör: Utils utils = new
	 * Utils(); utils.saveChronology(icraDosyaID,
	 * ChronologyIdentifier.ISLEM_TAKIP_ACILISI, "Açıklama free text");
	 */

	private UtilsDAO dao = new UtilsDAO();

	public void saveChronology(int icraDosyaId, String islem, String aciklama) {

		Chronology chronology = new Chronology(icraDosyaId, dao.getDepartman(), dao.getBorcluAdi(icraDosyaId), islem,
				aciklama, dao.getUserID(), new Date());
		dao.insertChronology(chronology);

	}

	public ArrayList<TimelineEvent> getAllTimeLineEventsFromID(int icraDosyaID) {
		ArrayList<Chronology> list = (ArrayList<Chronology>) dao.getListFromIcraDosyaID(icraDosyaID);
		ArrayList<TimelineEvent> events = new ArrayList<TimelineEvent>();
		for (Chronology chronology : list) {
			Task task = new Task(chronology.getIslem(), ChronologyIdentifier.getImagePath(chronology), false,
					icraDosyaID, chronology.getTarih());
			TimelineEvent event = new TimelineEvent(task, chronology.getTarih());
			events.add(event);
		}
		return events;

	}

	public List getChronologyList(int id) {

		return dao.getListFromIcraDosyaID(id);
	}

	public String getUserName() {
		return dao.getPersoneAdi();
	}
	
	public String getBocluAdi(){
		String name = null;
		if(AktifBean.getBorcluId()!=0){
			name = dao.getBorcluAdi(AktifBean.getBorcluId());
		}
		return name;
	}

}
