package de.fhl.campusnavi;

public class RaumTypen {
	private int id;
	private String bereich;
	private String bezeichnung;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBereich() {
		return bereich;
	}
	public void setBereich(String bereich) {
		this.bereich = bereich;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	public String toString() {
		return id + " " + bezeichnung;
	}
}
