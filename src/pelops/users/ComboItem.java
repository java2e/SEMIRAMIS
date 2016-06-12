package pelops.users;



public class ComboItem implements  Comparable<ComboItem>{
	private int id;
	private String itemValue;
	private String ack;

	public ComboItem() {

	}

	public ComboItem(int id, String ack) {
		super();
		this.id = id;
		this.ack = ack;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAck() {
		return ack;
	}

	public void setAck(String ack) {
		this.ack = ack;
	}

	@Override
	public int compareTo(ComboItem o) {
		return Util.compareStrings(this.getAck(), o.getAck());
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	@Override
	public String toString()
	{
		return ack;
	}
}
