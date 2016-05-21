package pelops.chronology.model;

import java.util.HashSet;

public class Chronology {

	public Chronology(int icraDosyaID, HashSet<ReportChronology> chronologies) {
		this.icraDosyaID = icraDosyaID;
		this.chronologies = chronologies;
	}

	private HashSet<ReportChronology> chronologies = new HashSet<>();

	private int icraDosyaID;

	public int getIcraDosyaID() {
		return icraDosyaID;
	}

	public void setIcraDosyaID(int icraDosyaID) {
		this.icraDosyaID = icraDosyaID;
	}

	public HashSet<ReportChronology> getChronologies() {
		return chronologies;
	}

	public void setChronologies(HashSet<ReportChronology> chronologies) {
		this.chronologies = chronologies;
	}

}
