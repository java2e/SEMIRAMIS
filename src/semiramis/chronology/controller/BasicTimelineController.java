package semiramis.chronology.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;
import pelops.controller.AktifBean;
import semiramis.chronology.model.Chronology;

@ManagedBean
@SessionScoped
public class BasicTimelineController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2799560844470830953L;

	private TimelineModel model;

	private boolean selectable = true;
	private boolean zoomable = true;
	private boolean moveable = true;
	private boolean stackEvents = true;
	private String eventStyle = "box";
	private boolean axisOnTop;
	private boolean showCurrentTime = true;
	private boolean showNavigation = false;
	private boolean panelRender = false;
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
				this.panelRender = true;
			} else {
				events = utils.getAllTimeLineEventsFromID(icraDosyaID);
				chronologies = utils.getChronologyList(icraDosyaID);
				this.panelRender = true;

			}

			panelRender = false;
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

	public boolean isPanelRender() {
		return panelRender;
	}

	public void setPanelRender(boolean panelRender) {
		this.panelRender = panelRender;
	}

	public String getIcraDosyaNo() {
		return icraDosyaNo;
	}

	public void setIcraDosyaNo(String icraDosyaNo) {
		this.icraDosyaNo = icraDosyaNo;
	}

	public TimelineModel getModel() {
		return model;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	public boolean isZoomable() {
		return zoomable;
	}

	public void setZoomable(boolean zoomable) {
		this.zoomable = zoomable;
	}

	public boolean isMoveable() {
		return moveable;
	}

	public void setMoveable(boolean moveable) {
		this.moveable = moveable;
	}

	public boolean isStackEvents() {
		return stackEvents;
	}

	public void setStackEvents(boolean stackEvents) {
		this.stackEvents = stackEvents;
	}

	public String getEventStyle() {
		return eventStyle;
	}

	public void setEventStyle(String eventStyle) {
		this.eventStyle = eventStyle;
	}

	public boolean isAxisOnTop() {
		return axisOnTop;
	}

	public void setAxisOnTop(boolean axisOnTop) {
		this.axisOnTop = axisOnTop;
	}

	public boolean isShowCurrentTime() {
		return showCurrentTime;
	}

	public void setShowCurrentTime(boolean showCurrentTime) {
		this.showCurrentTime = showCurrentTime;
	}

	public boolean isShowNavigation() {
		return showNavigation;
	}

	public void setShowNavigation(boolean showNavigation) {
		this.showNavigation = showNavigation;
	}

	public List<Chronology> getChronologies() {
		return chronologies;
	}

	public void setChronologies(List<Chronology> chronologies) {
		this.chronologies = chronologies;
	}

}