package de.fhl.campusnavi;

import android.location.Location;

public class POI {
	private int id;
	private String bezeichnung;
	private String gebaeude;
	private String fachbereich;
	private int bewertung;
	private String tags;
	private String besonderheit;
	private double x_koordinate;
	private double y_koordinate;
	private double distance;
	
	private Location mostRecentLocation;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getGebaeude() {
		return gebaeude;
	}

	public void setGebaeude(String gebaeude) {
		this.gebaeude = gebaeude;
	}

	public String getFachbereich() {
		return fachbereich;
	}

	public void setFachbereich(String fachbereich) {
		this.fachbereich = fachbereich;
	}

	public int getBewertung() {
		return bewertung;
	}

	public void setBewertung(int bewertung) {
		this.bewertung = bewertung;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getBesonderheit() {
		return besonderheit;
	}

	public void setBesonderheit(String besonderheit) {
		this.besonderheit = besonderheit;
	}

	public double getX_koordinate() {
		return x_koordinate;
	}

	public void setX_koordinate(int x_koordinate) {
		this.x_koordinate = x_koordinate;
	}

	public double getY_koordinate() {
		return y_koordinate;
	}

	public void setY_koordinate(int y_koordinate) {
		this.y_koordinate = y_koordinate;
	}
	
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public void calcDistance(POI poi) {
		double D = 0;
		D = Math.sqrt(Math.pow(poi.getX_koordinate() - mostRecentLocation.getLatitude(),2) + Math.pow(poi.getY_koordinate() - mostRecentLocation.getLongitude(),2));
		poi.setDistance(D);
	}

	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return this.getBezeichnung();
	}
}
