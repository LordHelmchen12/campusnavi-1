package de.fhl.campusnavi;

public class Raum extends POI {
	private int id;
	private String name;
	private int nummer;
	private int stockwerk;
	private POI poi;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNummer() {
		return nummer;
	}
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	public int getStockwerk() {
		return stockwerk;
	}
	public void setStockwerk(int stockwerk) {
		this.stockwerk = stockwerk;
	}
	
	public POI getPoi() {
		return poi;
	}
	public void setPoi(POI poi) {
		this.poi = poi;
	}
	
	@Override
	public String toString() {
		return this.getNummer() + " " + this.getName();
	}

}
