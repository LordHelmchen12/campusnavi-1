package com.example.campusnavi;

public class Gebaeude {
	private int id;
	private int nummer;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNummer() {
		return nummer;
	}
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	
	public String toString() {
		return id + " " + nummer;
	}

}
