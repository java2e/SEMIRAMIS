package pelops.users;

import java.text.Collator;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



public class Util {
	
	
	public boolean isReadPage(int pageID)
	{ // Menuden sayfalara erisim
		// kullanicinin yetkisine gore
		// kontrol edilmistir.
		boolean rendered = false;
		User user = Util.getUser();

		if (user == null)
		{
			return false;
		}

		for (int i = 0; i < user.getPage().size(); i++)
		{
			if (user.getPage().get(i) == pageID)
			{
				rendered = true;
				break;
			}
		}

		return rendered;
	}
 
      public static HttpSession getSession() {
        return (HttpSession)
          FacesContext.
          getCurrentInstance().
          getExternalContext().
          getSession(false);
      }
       
      public static HttpServletRequest getRequest() {
       return (HttpServletRequest) FacesContext.
          getCurrentInstance().
          getExternalContext().getRequest();
      }
 
      public static String getUserName()
      {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return  session.getAttribute("username").toString();
      }
       
      public static String getUserId()
      {
        HttpSession session = getSession();
        if ( session != null )
            return (String) session.getAttribute("userid");
        else
            return null;
      }
      
      public static User getUser()
      {
        HttpSession session = getSession();
        if ( session != null )
            return (User) session.getAttribute("user");
        else
            return null;
      }
      
  	public static int compareStrings(String s1, String s2)
  	{
  		if (s1 == null || s1.trim().length() == 0)
  		{
  			if (s2 == null || s2.trim().length() == 0)
  			{
  				return 0;
  			}
  			else
  			{
  				return -1;
  			}
  		}
  		else if (s2 == null || s2.trim().length() == 0)
  		{
  			return 1;
  		}
  		else
  		{
  			Collator myCollator = Collator.getInstance(new Locale("tr", "", ""));
  			return myCollator.compare(s1, s2);
  		}
  	}
  	
	public static char[] trChars = {'Ö', 'Ü', 'Ş', 'Ğ', 'Ç', 'İ'};
  	public static char[] trCorrChars = {'O', 'U', 'S', 'G', 'C', 'I'};
  	
  	/**
  	 * 
  	 * @param ad
  	 * @return
  	 */
	public static String processNameForCharacterReplacements(String ad)
	{
		if (ad == null || ad.trim().length() == 0)
		{
			return "BILINMEYEN_MERKEZ";
		}
		
		String intermediateForm = ad.toUpperCase(Locale.forLanguageTag("tr-TR"));
		char[] chars = intermediateForm.toCharArray(); 
		StringBuffer buffer = new StringBuffer();
		
		for (int i = 0; i < chars.length; i++)
		{
			char currentChar = chars[i];
			
			if (currentChar == ' ')
			{
				buffer.append("-");
			}
			else if (Character.isDigit(currentChar) || Character.isLetter(currentChar))
			{
				boolean isTrChar = false;
				for (int t = 0; t < trChars.length; t++)
				{
					if (currentChar == trChars[t])
					{
						isTrChar = true;
						buffer.append(trCorrChars[t]);
						break;
					}
				}
				
				if (!isTrChar)
				{
					buffer.append(currentChar);
				}
			}
		}
		
		return buffer.toString();
	}
	
}