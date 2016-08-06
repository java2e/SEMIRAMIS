package pelops.chronology.model;

import java.util.HashSet;

public class Chronology2 {

	public Chronology2(int icraDosyaID, HashSet<ReportChronology2> chronologies) {
		this.icraDosyaID = icraDosyaID;
		this.chronologies = chronologies;
	}

	private HashSet<ReportChronology2> chronologies = new HashSet<>();

	private int icraDosyaID;

	public int getIcraDosyaID() {
		return icraDosyaID;
	}

	public void setIcraDosyaID(int icraDosyaID) {
		this.icraDosyaID = icraDosyaID;
	}

	public HashSet<ReportChronology2> getChronologies() {
		return chronologies;
	}

	public void setChronologies(HashSet<ReportChronology2> chronologies) {
		this.chronologies = chronologies;
	}

}
