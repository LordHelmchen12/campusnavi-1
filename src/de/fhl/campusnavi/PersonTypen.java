package de.fhl.campusnavi;

public class PersonTypen {
	private int id;
	private String bezeichnung;
	
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
	
	public String toString() {
		return id + " " + bezeichnung;
	}

}
