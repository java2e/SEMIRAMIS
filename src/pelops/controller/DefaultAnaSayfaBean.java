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
import pelops.services.EtkinlikService;
import pelops.services.GorevService;

@ManagedBean(name="defaultAnaSayfaBean")
@ViewScoped
public class DefaultAnaSayfaBean implements Serializable {

	     
	    private ScheduleModel eventModel;

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
	 
	    public void setSelectedGorev(Gorev selectedGorev) {
	        this.selectedGorev = selectedGorev;
	    }
	     
	    
	    @ManagedProperty("#{etkinlikService}")
	    private EtkinlikService etkinlikService;
	    
	     
	    public void setEtkinlikService(EtkinlikService etkinlikService) {
			this.etkinlikService = etkinlikService;
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
	    	eventModel=etkinlikService.createEtkinlikler(eventModel);
	    	
	         
	    }   
	    
	    

	     
	    public ScheduleModel getEventModel() {
	        return eventModel;
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
