package semimis.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConvertDate {
	
	//metod parametre listesindeki date argumaninin tipini Date den Strin ge ceviren metod
	public String convertDateToString(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		String dt = sdf.format(date);
		
		return dt;
	}
	
	
	//bugunun tarihini done metod
	public String getNowDate()
	{
		
		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		   //get current date time with Date()
		 Date date = new Date();
		
		 return dateFormat.format(date);
	}
	
	//dunku tarihi String olarak doner
	public String yesterdayString() 
	{
	       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	       Calendar cal = Calendar.getInstance();
	       cal.add(Calendar.DATE, -1);
	       return dateFormat.format(cal.getTime());
	}
	
	//bugnku tarihi Date doner
	public Date nowDate() 
	{
    Calendar cal = Calendar.getInstance();
    return cal.getTime();
	}

}
