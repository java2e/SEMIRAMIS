package pelops.robot;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "robotBean")
@SessionScoped
public class RobotBean {

	public RobotBean() {

	}

	public void getEsasArama() throws Exception {

		
		System.out.println("robottayım");
		RobotDao robotDao = new RobotDao();
		robotDao.OpenConnectAndAccess();
		Thread.sleep(1000);
		robotDao.AccessTheSystem();
		Thread.sleep(1000);
		robotDao.startEsasArama();
	
	}

}
