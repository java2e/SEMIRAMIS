package semiramis.operasyon.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;
import pelops.controller.AktifBean;
import semiramis.operasyon.model.Chronology;

@ManagedBean
@RequestScoped
public class BasicTimelineController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2799560844470830953L;

	private TimelineModel model;

	TimelineEvent event;

	private String icraDosyaNo;
	private List<Chronology> chronologies = new ArrayList<>();

	private int icraDosyaID = 0;

	private Utils utils = new Utils();

	public BasicTimelineController() {
		initialize();
	}

	public void initialize() {
		model = new TimelineModel();

		ArrayList<TimelineEvent> events = null;
		try {
			if (icraDosyaID == 0 && AktifBean.getIcraDosyaID() != 0) {
				events = utils.getAllTimeLineEventsFromID(AktifBean.getIcraDosyaID());
				chronologies = utils.getChronologyList(AktifBean.getIcraDosyaID());
			} else {
				events = utils.getAllTimeLineEventsFromID(icraDosyaID);
				chronologies = utils.getChronologyList(icraDosyaID);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (events != null) {
			if (events.size() > 0) {
				for (TimelineEvent timelineEvent : events) {

					model.add(timelineEvent);
				}
			}

		}

	}

	public void icraDosyaSec(int id) {
		RequestContext.getCurrentInstance().execute("PF('dlgdetayliarama').hide()");
		this.icraDosyaNo = AktifBean.getIcraDosyaNo();
		icraDosyaID = AktifBean.getIcraDosyaID();
		initialize();
		chronologies = utils.getChronologyList(icraDosyaID);

	}

	public String getIcraDosyaNo() {
		return icraDosyaNo;
	}

	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
	}

	public List<Chronology> getChronologies() {
		return chronologies;
	}

	public void setChronologies(List<Chronology> chronologies) {
		this.chronologies = chronologies;
	}

}