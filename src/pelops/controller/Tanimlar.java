package pelops.controller;

import pelops.users.User;
import pelops.users.Util;

public class Tanimlar {
	
	
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

}
