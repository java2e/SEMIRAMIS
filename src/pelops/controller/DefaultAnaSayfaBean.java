package pelops.controller;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
 
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import org.primefaces.model.LazyDataModel;


import java.util.List;
import javax.faces.bean.ManagedProperty;

import pelops.model.Duyuru;
import pelops.model.Gorev;
import pelops.services.DuyuruService;
import pelops.services.GorevService;

@ManagedBean(name="defaultAnaSayfaBean")
@ViewScoped
public class DefaultAnaSayfaBean implements Serializable {

	     
	    private ScheduleModel eventModel;
	     
	    private ScheduleModel lazyEventModel;
	 
	    private ScheduleEvent event = new DefaultScheduleEvent();
  
	    
	    private List<Duyuru> duyurular;
	     
	    @ManagedProperty("#{duyuruService}")
	    private DuyuruService duyuruService;
	    
	    public List<Duyuru> getDuyurular() {
	        return duyurular;
	    }
	 
	    public void setDuyuruService(DuyuruService duyuruService) {
	        this.duyuruService = duyuruService;
	    }
	    
	    
	    private List<Gorev> gorevler;
	    private Gorev selectedGorev;
	     
	    @ManagedProperty("#{gorevService}")
	    private GorevService gorevService;
	    
	    public List<Gorev> getGorevler() {
	        return gorevler;
	    }
	 
	    public void setGorevService(GorevService gorevService) {
	        this.gorevService = gorevService;
	    }
	    
	    public Gorev getSelectedGorev() {
	        return selectedGorev;
	    }
	 
	    public void setSelectedCar(Gorev selectedGorev) {
	        this.selectedGorev = selectedGorev;
	    }
	     
	     
	    public void onRowSelect(SelectEvent event) {
	    	
	    	
	    	selectedGorev.setId( ((Gorev)event.getObject()).getId());
	    	selectedGorev.setKonu(((Gorev)event.getObject()).getKonu());
	    	selectedGorev.setAciklama(((Gorev)event.getObject()).getAciklama());
	    	selectedGorev.setBasTarih(((Gorev)event.getObject()).getBasTarih());
	    	selectedGorev.setBitTarih(((Gorev)event.getObject()).getBitTarih());
	    	
	    	
	    	FacesMessage msg = new FacesMessage("Gorev Selected", (String.valueOf( ((Gorev) event.getObject()).getId())));
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	 
	    @PostConstruct
	    public void init() {
	    	duyurular = duyuruService.createDuyurular(10);
	    	
	    	gorevler = gorevService.createGorevler(5);
	    	selectedGorev=new Gorev();
	    	
	    	eventModel = new DefaultScheduleModel();
	        eventModel.addEvent(new DefaultScheduleEvent("Champions League Match", previousDay8Pm(), previousDay11Pm()));
	        eventModel.addEvent(new DefaultScheduleEvent("Birthday Party", today1Pm(), today6Pm()));
	        eventModel.addEvent(new DefaultScheduleEvent("Breakfast at Tiffanys", nextDay9Am(), nextDay11Am()));
	        eventModel.addEvent(new DefaultScheduleEvent("Plant the new garden stuff", theDayAfter3Pm(), fourDaysLater3pm()));
	         
	        lazyEventModel = new LazyScheduleModel() {
	             
	            @Override
	            public void loadEvents(Date start, Date end) {
	                Date random = getRandomDate(start);
	                addEvent(new DefaultScheduleEvent("Lazy Event 1", random, random));
	                 
	                random = getRandomDate(start);
	                addEvent(new DefaultScheduleEvent("Lazy Event 2", random, random));
	            }   
	        };
	    }   
	    
	    
	    public Date getRandomDate(Date base) {
	        Calendar date = Calendar.getInstance();
	        date.setTime(base);
	        date.add(Calendar.DATE, ((int) (Math.random()*30)) + 1);    //set random day of month
	         
	        return date.getTime();
	    }
	     
	    public Date getInitialDate() {
	        Calendar calendar = Calendar.getInstance();
	        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);
	         
	        return calendar.getTime();
	    }
	     
	    public ScheduleModel getEventModel() {
	        return eventModel;
	    }
	     
	    public ScheduleModel getLazyEventModel() {
	        return lazyEventModel;
	    }
	 
	    private Calendar today() {
	        Calendar calendar = Calendar.getInstance();
	        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
	 
	        return calendar;
	    }
	     
	    private Date previousDay8Pm() {
	        Calendar t = (Calendar) today().clone();
	        t.set(Calendar.AM_PM, Calendar.PM);
	        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
	        t.set(Calendar.HOUR, 8);
	         
	        return t.getTime();
	    }
	     
	    private Date previousDay11Pm() {
	        Calendar t = (Calendar) today().clone();
	        t.set(Calendar.AM_PM, Calendar.PM);
	        t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
	        t.set(Calendar.HOUR, 11);
	         
	        return t.getTime();
	    }
	     
	    private Date today1Pm() {
	        Calendar t = (Calendar) today().clone();
	        t.set(Calendar.AM_PM, Calendar.PM);
	        t.set(Calendar.HOUR, 1);
	         
	        return t.getTime();
	    }
	     
	    private Date theDayAfter3Pm() {
	        Calendar t = (Calendar) today().clone();
	        t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);     
	        t.set(Calendar.AM_PM, Calendar.PM);
	        t.set(Calendar.HOUR, 3);
	         
	        return t.getTime();
	    }
	 
	    private Date today6Pm() {
	        Calendar t = (Calendar) today().clone(); 
	        t.set(Calendar.AM_PM, Calendar.PM);
	        t.set(Calendar.HOUR, 6);
	         
	        return t.getTime();
	    }
	     
	    private Date nextDay9Am() {
	        Calendar t = (Calendar) today().clone();
	        t.set(Calendar.AM_PM, Calendar.AM);
	        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
	        t.set(Calendar.HOUR, 9);
	         
	        return t.getTime();
	    }
	     
	    private Date nextDay11Am() {
	        Calendar t = (Calendar) today().clone();
	        t.set(Calendar.AM_PM, Calendar.AM);
	        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
	        t.set(Calendar.HOUR, 11);
	         
	        return t.getTime();
	    }
	     
	    private Date fourDaysLater3pm() {
	        Calendar t = (Calendar) today().clone(); 
	        t.set(Calendar.AM_PM, Calendar.PM);
	        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
	        t.set(Calendar.HOUR, 3);
	         
	        return t.getTime();
	    }
	     
	    public ScheduleEvent getEvent() {
	        return event;
	    }
	 
	    public void setEvent(ScheduleEvent event) {
	        this.event = event;
	    }
	     
	    public void addEvent(ActionEvent actionEvent) {
	        if(event.getId() == null)
	            eventModel.addEvent(event);
	        else
	            eventModel.updateEvent(event);
	         
	        event = new DefaultScheduleEvent();
	    }
	     
	    public void onEventSelect(SelectEvent selectEvent) {
	        event = (ScheduleEvent) selectEvent.getObject();
	    }
	     
	    public void onDateSelect(SelectEvent selectEvent) {
	        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
	    }
	     
	    public void onEventMove(ScheduleEntryMoveEvent event) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
	         
	        addMessage(message);
	    }
	     
	    public void onEventResize(ScheduleEntryResizeEvent event) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
	         
	        addMessage(message);
	    }
	     
	    private void addMessage(FacesMessage message) {
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	    
	    
	    
	

}
