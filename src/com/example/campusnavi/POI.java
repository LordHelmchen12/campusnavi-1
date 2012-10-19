package com.example.campusnavi;

public class POI {
	private int id;
	private String bezeichnung;

	public void setId(int id) {
		this.id = id;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	
	public long getId() {
		return this.id;
	}
	
	public String getBezeichnung() {
		return this.bezeichnung;
	}

	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return "ID: " + this.getId() + " Bezeichnung: " + this.getBezeichnung();
	}
}
