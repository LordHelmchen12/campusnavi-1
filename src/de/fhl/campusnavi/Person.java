package de.fhl.campusnavi;

public class Person extends POI {
	private int id;
	private String vorname;
	private String nachname;
	private String titel;
	private String sprechzeit;
	private String zustaendigkeiten;
	private String email;
	private String POI;
	
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
	public String getSprechzeit() {
		return sprechzeit;
	}
	public void setSprechzeit(String sprechzeit) {
		this.sprechzeit = sprechzeit;
	}
	public String getZustaendigkeiten() {
		return zustaendigkeiten;
	}
	public void setZustaendigkeiten(String zustaendigkeiten) {
		this.zustaendigkeiten = zustaendigkeiten;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPOI() {
		return POI;
	}
	public void setPOI(String pOI) {
		POI = pOI;
	}
	@Override
	public String toString() {
		String ret = "";
		if (this.getTitel() != null) {
			ret = ret + this.getTitel() + " ";
		}
		ret = ret + this.getVorname() + " " + this.getNachname();
		return ret;
	}

}
