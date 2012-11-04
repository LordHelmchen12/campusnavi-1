package de.fhl.campusnavi;

public class Person {
	private int id;
	private String vorname;
	private String nachname;
	private String titel;
	private String zustaendigkeit;
	private String email;
	private String telefon;
	private String sprechzeiten;
	private int fachbereich;
	private int typ;
	private int raum;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getZustaendigkeit() {
		return zustaendigkeit;
	}

	public void setZustaendigkeit(String zustaendigkeit) {
		this.zustaendigkeit = zustaendigkeit;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getSprechzeiten() {
		return sprechzeiten;
	}

	public void setSprechzeiten(String sprechzeiten) {
		this.sprechzeiten = sprechzeiten;
	}

	public int getFachbereich() {
		return fachbereich;
	}

	public void setFachbereich(int fachbereich) {
		this.fachbereich = fachbereich;
	}

	public int getTyp() {
		return typ;
	}

	public void setTyp(int typ) {
		this.typ = typ;
	}

	public int getRaum() {
		return raum;
	}

	public void setRaum(int raum) {
		this.raum = raum;
	}
	
	public String returnInfo() {
		String ret = "";
		if (titel != null) ret += titel + " ";
		ret += vorname + " " + nachname;
		if(email != null) ret += "\n" + email;
		if(telefon != null) ret += "\n\nTel: " + telefon;
		if(sprechzeiten != null) ret += "\n\nSprechzeiten:\n" + sprechzeiten.replaceAll(";", "\n");
		if(zustaendigkeit != null) ret += "\n\nZuständigkeit: " + zustaendigkeit;
		return ret;
	}
	
	@Override
	public String toString() {
		String ret = "";
		if (titel != null) {
			ret = titel + " " + vorname + " " + nachname;
		} else {
			ret = vorname + " " + nachname;
		}
		return ret;
	}
}
