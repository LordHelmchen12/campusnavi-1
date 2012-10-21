package de.fhl.campusnavi;

public class Raum {
	private int id;
	private String name;
	private int nummer;
	private int stockwerk;
	private int gebaeude;
	
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
	public int getGebaeude() {
		return gebaeude;
	}
	public void setGebaeude(int gebaeude) {
		this.gebaeude = gebaeude;
	}
	@Override
	public String toString() {
		return this.getName();
	}

}
