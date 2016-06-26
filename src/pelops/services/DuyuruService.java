package pelops.services;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import pelops.model.Duyuru;

 
@ManagedBean(name = "duyuruService")
@ApplicationScoped
public class DuyuruService {

     
    public List<Duyuru> createDuyurular(int size) {
        List<Duyuru> list = new ArrayList<Duyuru>();
        for(int i = 0 ; i < size ; i++) {
        	Duyuru duyuru =new Duyuru();
        	duyuru.setId(getRandomId());
        	duyuru.setAciklama("Duyuru("+(i+1)+") aciklaması yüklenecektir");
            list.add(duyuru);
        }
         
        return list;
    }
     

     
    private int getRandomId() {
        return (int) (Math.random() * 100);
    }
     
 
}
