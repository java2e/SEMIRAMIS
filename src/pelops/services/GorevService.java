package pelops.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import pelops.model.Duyuru;
import pelops.model.Gorev;

@ManagedBean(name = "gorevService")
@ApplicationScoped
public class GorevService {

     
    public List<Gorev> createGorevler(int size) {
        List<Gorev> list = new ArrayList<Gorev>();
        for(int i = 0 ; i < size ; i++) {
        	Gorev gorev =new Gorev();
        	gorev.setId(getRandomId());
        	gorev.setKonu("konu");
        	gorev.setAciklama("aciklama");
        	gorev.setBasTarih(new Date());
        	gorev.setBitTarih(new Date());
            list.add(gorev);
        }
         
        return list;
    }
     

     
    private int getRandomId() {
        return (int) (Math.random() * 100);
    }
     
 
}
