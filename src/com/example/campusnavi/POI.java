package com.example.campusnavi;

public class POI {
	private int id;
	private String bezeichnung;
	private String gebauede;
	private String fachbereich;
	private int bewertung;
	private String tags;
	private String besonderheit;
	private int x_koordinate;
	private int y_koordinate;

	public void setId(int id) {
		this.id = id;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	public void setGebaeude(String gebauede) {
		this.gebauede = gebauede;
	}
	
	public void setFachbereich(String fachbereich) {
		this.fachbereich = fachbereich;
	}
	
	public void setBewertung(int bewertung) {
		this.bewertung = bewertung;
	}
	
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public void setBesonderheit(String besonderheit) {
		this.besonderheit = besonderheit;
	}
	
	public void setX_koordinate(int x_koordinate) {
		this.x_koordinate = x_koordinate;
	}
	
	public void setY_koordinate(int y_koordinate) {
		this.y_koordinate = y_koordinate;
	}
	
	public long getId() {
		return this.id;
	}
	
	public String getBezeichnung() {
		return this.bezeichnung;
	}

	public String getGebaeude() {
		return this.gebauede;
	}
	
	public String getFachbereich() {
		return this.fachbereich;
	}
	
	public int getBewertung() {
		return this.bewertung;
	}
	
	public String getTags() {
		return this.tags;
	}
	
	public String getBesonderheit() {
		return this.besonderheit;
	}
	
	public int getX_koordinate() {
		return this.x_koordinate;
	}
	
	public int getY_koordinate() {
		return this.y_koordinate;
	}
	
	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return "ID: " + this.getId() + " Bezeichnung: " + this.getBezeichnung();
	}
}
