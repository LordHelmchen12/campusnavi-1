package de.fhl.campusnavi;

import android.location.Location;

public class POI {
	private int id;
	private double longitude;
	private double latitude;
	private String tags;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Location getMostRecentLocation() {
		return mostRecentLocation;
	}

	public void setMostRecentLocation(Location mostRecentLocation) {
		this.mostRecentLocation = mostRecentLocation;
	}

	private double distance;
	
	private Location mostRecentLocation;
	
	
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public void calcDistance(POI poi) {
		double D = 0;
		D = Math.sqrt(Math.pow(poi.getLatitude() - mostRecentLocation.getLatitude(),2) + Math.pow(poi.getLongitude() - mostRecentLocation.getLongitude(),2));
		poi.setDistance(D);
	}
}
