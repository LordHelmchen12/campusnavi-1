package de.fhl.campusnavi;

public class Einrichtung {
	private int id;
	private int poi;
	private String name;
	private String angebot;
	private String oeffnungszeiten;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPoi() {
		return poi;
	}
	public void setPoi(int poi) {
		this.poi = poi;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAngebot() {
		return angebot;
	}
	public void setAngebot(String angebot) {
		this.angebot = angebot;
	}
	public String getOeffnungszeiten() {
		return oeffnungszeiten;
	}
	public void setOeffnungszeiten(String oeffnungszeiten) {
		this.oeffnungszeiten = oeffnungszeiten;
	}
	
	public String returnInfo() {
		String ret = name;
		if (angebot != null) ret += "\nAngebot: " + angebot;
		if (oeffnungszeiten != null) ret += "\n\n…ffnungszeiten:\n" + oeffnungszeiten.replaceAll(";", "\n");
		return ret;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
