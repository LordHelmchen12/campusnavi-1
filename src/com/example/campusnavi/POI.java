package com.example.campusnavi;

public class POI {
	private long id;
	  private String poi;

	  public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	  public String getPOI() {
	    return poi;
	  }

	  public void setPOI(String comment) {
	    this.poi = comment;
	  }

	  // Will be used by the ArrayAdapter in the ListView
	  @Override
	  public String toString() {
	    return poi;
	  }
}
