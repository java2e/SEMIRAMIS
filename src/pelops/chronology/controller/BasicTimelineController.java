package pelops.chronology.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.extensions.event.timeline.TimelineSelectEvent;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;

import pelops.chronology.model.ReportChronology;
import pelops.chronology.model.Task;
import pelops.controller.AktifBean;
import pelops.util.Util;

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
	
	TimelineEvent event;

	private String icraDosyaNo;
	private List<ReportChronology> chronologies = new ArrayList<>();

	private int icraDosyaID;

	@PostConstruct
	public void initialize() {
		model = new TimelineModel();
		
		ArrayList<TimelineEvent> events = null;
		try {
			events = ReportChronologyCtrl.getInstance().getAllEvents(icraDosyaID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (events != null) {
			if (events.size() > 0) {
				for (TimelineEvent timelineEvent : events) {

					model.add(timelineEvent);
				}
			} 
//			  Calendar cal = Calendar.getInstance();  
//		        cal.set(2015, Calendar.AUGUST, 22, 17, 30, 0);  
//		        model.add(new TimelineEvent(new Task("Mail from boss", "timeline/mail.png", false), cal.getTime()));  
//		  
//		        cal.set(2015, Calendar.AUGUST, 23, 23, 0, 0);  
//		        model.add(new TimelineEvent(new Task("Call back my boss", "timeline/callback.png", false), cal.getTime()));  
//		  
//		        cal.set(2015, Calendar.AUGUST, 24, 21, 45, 0);  
//		        model.add(new TimelineEvent("Travel to Spain", cal.getTime()));  
		  
		}

	}

	// Burada sadece gelen event boş geliyo onunda verileri dolu halde gelirse
	// date yi alabiliriz abi
	public void onSelect(TimelineSelectEvent e) {

		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Secildi", null);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("date")
				.toString();
	System.out.println(date);
		try {
//			Date date2 = formatter.parse(date);
//			chronologies = null; 
//			chronologies = ReportChronologyUtil.getInstance().getObjFromDBWithIcraDosyaIDandDate(icraDosyaID, date2);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void icraDosyaSec(int id) {

		RequestContext.getCurrentInstance().execute("PF('dlgdetayliarama').hide()");
		this.icraDosyaNo = AktifBean.getIcraDosyaNo();
		icraDosyaID = AktifBean.getIcraDosyaID();
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

	public List<ReportChronology> getChronologies() {
		return chronologies;
	}

	public void setChronologies(List<ReportChronology> chronologies) {
		this.chronologies = chronologies;
	}

}