package de.fhl.campusnavi;

public class Wc extends POI {
	private int id;
	private int typ;
	private int poi;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTyp() {
		return typ;
	}
	public void setTyp(int typ) {
		this.typ = typ;
	}
	public int getPoi() {
		return poi;
	}
	public void setPoi(int poi) {
		this.poi = poi;
	}
	@Override
	public String toString() {
		return "Wc [typ=" + typ + "]";
	}

}
