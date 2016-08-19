package semimis.utils;

import java.util.List;

public interface IDAO<T>
{
	
	public boolean kaydet(T t);
	
	public boolean guncelleme(T t);
	
	public boolean sil(int id);
	
	public T getT(int id);
	
	public List<T> liste(int id,int id2);
	
	public int getId(T t);
	

}
