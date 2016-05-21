package pelops.chronology.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.extensions.event.timeline.TimelineSelectEvent;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;

import pelops.chronology.model.Task;
import pelops.controller.AktifBean;
import pelops.util.Util;

@ManagedBean
@SessionScoped
public class BasicTimelineController implements Serializable {

	private TimelineModel model;

	private boolean selectable = true;
	private boolean zoomable = true;
	private boolean moveable = true;
	private boolean stackEvents = true;
	private String eventStyle = "box";
	private boolean axisOnTop;
	private boolean showCurrentTime = true;
	private boolean showNavigation = false;

	private String icraDosyaNo;

	private int icraDosyaID;

	@PostConstruct
	public void initialize() {
		model = new TimelineModel();

		Calendar cal = Calendar.getInstance();

		ArrayList<TimelineEvent> events = null;

		try {
			events = ReportChronologyCtrl.getInstance().getAllEvents(icraDosyaID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (events.size() > 0) {
			for (TimelineEvent timelineEvent : events) {
				model.add(timelineEvent);
			}
		} else {

		}

		// cal.set(2013, Calendar.JUNE, 5, 0, 0, 0);
		// model.add(new TimelineEvent(new Task("Call back my boss",
		// "timeline/callback.png", false), cal.getTime()));
		//
		// cal.set(2013, Calendar.OCTOBER, 3, 0, 0, 0);
		// model.add(new TimelineEvent(new Task("Call back my boss",
		// "timeline/report.png", false), cal.getTime()));
		//
		// cal.set(2013, Calendar.DECEMBER, 28, 0, 0, 0);
		// model.add(new TimelineEvent(new Task("Call back my boss",
		// "timeline/callback.png", false), cal.getTime()));
		//
		// cal.set(2014, Calendar.JANUARY, 1, 0, 0, 0);
		// model.add(new TimelineEvent(new Task("Call back my boss",
		// "timeline/callback.png", false), cal.getTime()));
		//
		// cal.set(2014, Calendar.MAY, 5, 0, 0, 0);
		// model.add(new TimelineEvent(new Task("Call back my boss",
		// "timeline/callback.png", false), cal.getTime()));
		//
		// cal.set(2014, Calendar.AUGUST, 22, 0, 0, 0);
		// model.add(new TimelineEvent(new Task("Call back my boss",
		// "timeline/callback.png", false), cal.getTime()));
		//
		// cal.set(2014, Calendar.NOVEMBER, 3, 0, 0, 0);
		// model.add(new TimelineEvent(new Task("Call back my boss",
		// "timeline/callback.png", false), cal.getTime()));
		//
		// cal.set(2015, Calendar.APRIL, 26, 0, 0, 0);
		// model.add(new TimelineEvent(new Task("Call back my boss",
		// "timeline/callback.png", false), cal.getTime()));
	}

	public void onSelect(TimelineSelectEvent e) {
		TimelineEvent timelineEvent = e.getTimelineEvent();

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected event:",
				timelineEvent.getData().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void icraDosyaSec(int id) {

		RequestContext.getCurrentInstance().execute("PF('dlgdetayliarama').hide()");
		this.icraDosyaNo = AktifBean.getIcraDosyaNo();
		icraDosyaID = AktifBean.getIcraDosyaID();
		System.out.println(icraDosyaNo);
		System.out.println(icraDosyaID);
		initialize();

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
}