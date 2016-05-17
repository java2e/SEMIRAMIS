package pelops.controller;

import java.util.ArrayList;
import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="dosyaEklemeBean")
@SessionScoped
public class DosyaEklemeBean {

	private List<String> images;
    
   
    public DosyaEklemeBean() {
        images = new ArrayList<String>();
        images.add("img/Arac.png");
        images.add("img/Arac_2.png");
        images.add("img/Arac_3.png");
        images.add("img/Arac_4.png");
        
    }
 
    public List<String> getImages() {
        return images;
    }
}
