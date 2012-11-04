package de.fhl.campusnavi;

import android.content.Context;

public class Raum {
	private int id;
	private int poi;
	private int gebaeude;
	private int stockwerk;
	private int nummer;
	private int typ;
	private int fachbereich;
	
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
	public int getGebaeude() {
		return gebaeude;
	}
	public void setGebaeude(int gebaeude) {
		this.gebaeude = gebaeude;
	}
	public int getStockwerk() {
		return stockwerk;
	}
	public void setStockwerk(int stockwerk) {
		this.stockwerk = stockwerk;
	}
	public int getNummer() {
		return nummer;
	}
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	public int getTyp() {
		return typ;
	}
	public void setTyp(int typ) {
		this.typ = typ;
	}
	public int getFachbereich() {
		return fachbereich;
	}
	public void setFachbereich(int fachbereich) {
		this.fachbereich = fachbereich;
	}
	
	@Override
	public String toString() {
		return "Raum " + gebaeude + "." + stockwerk + "." + nummer;
	}
	
}
