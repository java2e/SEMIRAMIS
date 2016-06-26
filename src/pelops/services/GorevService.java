package pelops.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import pelops.dao.GorevDAO;
import pelops.model.Duyuru;
import pelops.model.Gorev;

@ManagedBean(name = "gorevService")
@ApplicationScoped
public class GorevService {
	
	private GorevDAO gorevDAO;
	
	
	
	
	public GorevDAO getGorevDAO() {
		return gorevDAO;
	}


	public void setGorevDAO(GorevDAO gorevDAO) {
		this.gorevDAO = gorevDAO;
	}


	public GorevService(){
		gorevDAO=new GorevDAO();
		
	}

     
    public List<Gorev> createGorevler(int size) {
        List<Gorev> list = new ArrayList<Gorev>();
        for(int i = 0 ; i < size ; i++) {
        	Gorev gorev =new Gorev();
        	gorev.setId(getRandomId());
        	gorev.setKonu("Konu");
        	gorev.setAciklama("Görev aciklaması yüklenecektir");
        	gorev.setBasTarih(new Date());
        	gorev.setBitTarih(new Date());
            list.add(gorev);
        }
         
        return list;
    }
    
    
    
    public List<Gorev> getGorevListByUserId(Integer userId){
         return gorevDAO.select(userId);	
    }
     

     
    private int getRandomId() {
        return (int) (Math.random() * 100);
    }
     
 
}
