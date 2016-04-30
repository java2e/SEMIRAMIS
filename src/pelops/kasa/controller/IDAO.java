package pelops.kasa.controller;

import java.util.ArrayList;

public interface IDAO {

	public ArrayList<Object> getAllObjFromDB() throws Exception;

	public int insertObjToDB(Object obj) throws Exception;

	public int  updateObjFromDB(Object obj) throws Exception;

	public boolean deleteObjFromDB(int id) throws Exception;

	public Object getObjFromDB(int id) throws Exception;
	
	public int getID(Object object) throws Exception;
	
	public ArrayList<Object>getAllObjFromStatus(int status) throws Exception;

}
