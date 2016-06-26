package pelops.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import pelops.dao.EtkinlikDAO;
import pelops.dao.UserDAO;
import pelops.model.Duyuru;
import pelops.model.Etkinlik;

@ManagedBean(name = "etkinlikService")
@ApplicationScoped
public class EtkinlikService {
	
	
		private EtkinlikDAO etkinlikDAO;
		
		
	
	   public EtkinlikDAO getEtkinlikDAO() {
			return etkinlikDAO;
		}


		public void setEtkinlikDAO(EtkinlikDAO etkinlikDAO) {
			this.etkinlikDAO = etkinlikDAO;
		}


	 public EtkinlikService(){
		 etkinlikDAO=new EtkinlikDAO();
	 }
		
	public ScheduleModel createEtkinlikler(ScheduleModel eventModel) {
	        eventModel.addEvent(new DefaultScheduleEvent("Şampiyonlar Ligi Maçı", previousDay8Pm(), previousDay11Pm()));
	        eventModel.addEvent(new DefaultScheduleEvent("Duygu Doğum Günü", today1Pm(), today6Pm()));
	        eventModel.addEvent(new DefaultScheduleEvent("Öğlen Yemeği", nextDay9Am(), nextDay11Am()));
	        eventModel.addEvent(new DefaultScheduleEvent("Haciz Dosyaları Planla", theDayAfter3Pm(), fourDaysLater3pm()));
	         
	        return eventModel;
	    }
	   
	   
	   public ScheduleModel getEtkinlikListByUserId(ScheduleModel eventModel,Integer userId) {

		   List<Etkinlik> etkinlikList =etkinlikDAO.select(userId);
		   
		   int i=0;
		   for(Etkinlik etkinlik :etkinlikList){
			   eventModel.addEvent(new DefaultScheduleEvent(etkinlik.getAciklama(), etkinlik.getBasTarih(), etkinlik.getBitTarih()));
			   eventModel.getEvents().get(i).setId(etkinlik.getEventId());
			   i++;
		   }
	         
	        return eventModel;
	    }
	   
	   
	   public void addEtkinlik(Etkinlik etkinlik){
		   etkinlikDAO.insert(etkinlik);
	   }
	   
	   public void updateEtkinlikByEventId(Etkinlik etkinlik){
		   etkinlikDAO.updateByEventId(etkinlik);
	   }
	   
	   
	   public void deleteEtkinlikByEventId(String eventId){
		   etkinlikDAO.deleteByEventId(eventId);
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

}
