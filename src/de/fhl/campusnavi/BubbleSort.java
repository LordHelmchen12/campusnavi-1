package de.fhl.campusnavi;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class BubbleSort extends Activity implements LocationListener{
	
	private Location LocationDevice;

	private void getLocation() {
	LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	Criteria criteria = new Criteria();
	criteria.setAccuracy(Criteria.ACCURACY_FINE);

	/*
	 * Dient der Sicherheit, dass ein Standortanbieter vorhanden ist. Siehe
	 * Manifest: erlaubt wurde -> GPS <uses-permission
	 * android:name="android.permission.ACCESS_FINE_LOCATION" /> und ->
	 * INTERNET <uses-permission
	 * android:name="android.permission.INTERNET"/>
	 */
	String provider = locationManager.getBestProvider(criteria, true);
	locationManager.requestLocationUpdates(provider, 1, 1, this);
	LocationDevice = locationManager.getLastKnownLocation(provider);
	
	}
	
	public List<POI> sort(List<POI> list) {
		for(int i=0; i < list.size() - 1; i++) {
			list.get(i).calcDistance(list.get(i));
		}
		
		boolean changed = true;
		// Durch Array laufen
		for(int i=0; i < list.size() - 1 && changed; i++) {
			// Gab es eine €nderung?
			changed = false;
			for(int k =0; k < list.size() -1; k++) {
				if (list.get(k-1).getDistance() > list.get(k).getDistance()) {
					POI tmp = list.get(k-1);
					list.set(k-1, list.get(k));
					list.set(k, tmp);
					changed = true;
				}
			}
		}
		return list;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}
