package de.fhl.campusnavi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Map extends Activity implements LocationListener {

	// private static final String MAP_URL =
	// "file:///android_asset/googleMap.html";
	private static final String MAP_URL = "http://gmaps-samples.googlecode.com/svn/trunk/articles-android-webmap/simple-android-map.html";
	private WebView webView;
	private Location mostRecentLocation;
	private String markerDevice;
	private String markerTarget;

	private DataSource datasource;

	/**
	 * onCreate() wird aufgerufen, wenn die Activity neu gestartet wird
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		getLocation();

		datasource = new DataSource(this);
		datasource.open();

		Intent intent = getIntent();
		POI poi = datasource.getPoiById(intent.getIntExtra("selected", 0));
		double longitude = poi.getLongitude();
		double latitude = poi.getLatitude();

		setupWebView(latitude, longitude);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

	}

	/**
	 * onResume() wird aufgerufen, wenn der Nutzer wieder zur Activity
	 * zur�ckkehrt. Hier werden Einstellungen aktualisiert.
	 */
	@Override
	protected void onResume() {
		super.onResume();
		// Enable Javascript...This is needed so that Javascript is allowed to
		// executeinside the WebView
		WebSettings webSettings = this.webView.getSettings();
		webSettings.setJavaScriptEnabled(true);

		// Register the 'Javascript Bridge' class under the 'window.android'
		// namespace
		// this class can be invoked from the HTML/Javascript side
		this.webView.addJavascriptInterface(new JavascriptBridge(),
				"window.android");

		// Register the WebChromeClient to assist with alerts/debugging
		this.webView.setWebChromeClient(new MyWebChromeClient());

		// Load assets/html/index.html resource into the WebView control
		this.webView.loadUrl("file:///android_asset/html/index.html");
	}

	/**
	 * setupWebView() Hier werden Einstellungen f�r den WebView (Browser)
	 * festgelegt und die URL geladen
	 */
	private void setupWebView(double latitude, double longitude){
  
     try{
    	markerDevice = "javascript:markerDevice(" + mostRecentLocation.getLatitude() + "," + mostRecentLocation.getLongitude()+ ")";
      }catch(Exception e){
    	// Set marker to campus
        markerDevice = "javascript:markerDevice(53.837859,10.699237)";
      } finally {
    	  markerTarget = "javascript:markerTarget(" + latitude +"," + longitude + ")";
      }
      webView = (WebView) findViewById(R.id.webview);
      webView.getSettings().setJavaScriptEnabled(true);
      //Multitouch wird aktiviert falls es unterst�tzt wird
      webView.getSettings().setBuiltInZoomControls(true); 
      //Wartet auf die zu ladene Seite und sendet Informationen der "location"
      webView.setWebViewClient(new WebViewClient(){  
	        @Override  
	        public void onPageFinished(WebView view, String url){
	        	webView.loadUrl(markerDevice);
	        	webView.loadUrl(markerTarget);
	        	//webView.loadUrl("javascript:showTrafficLayer()");
	        	//webView.loadUrl("javascript:showBicyclingLayer()");
	        }
      });
      webView.loadUrl(MAP_URL);
      webView.addJavascriptInterface(new JavascriptBridge(), "window.android");
    }

	/**
	 * getLocation() Stellt die Standortkoordinaten des Ger�tes fest. Hier pr�ft
	 * das System ob Lokalisierungsabfragen gestellt werden d�rfen und stellt
	 * die letzten bekannten Daten bereit, die dann in das Objekt
	 * mostRecentLocation geschrieben werden
	 */
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
		mostRecentLocation = locationManager.getLastKnownLocation(provider);
	}

	/**
	 * onLocationChanged() Der Standort auf der Karte wird auf Ger�testandort
	 * aktualisiert
	 */
	@Override
	public void onLocationChanged(Location location) {
		mostRecentLocation = location;
		if (markerDevice.equals("javascript:markerDevice("
				+ mostRecentLocation.getLatitude() + ","
				+ mostRecentLocation.getLongitude() + ")")) {
			webView.loadUrl(markerDevice);
		}
	}

	/**
	 * onProviderDisabled() wird nur ben�tigt da: Activity implements
	 * LocationListener
	 */
	@Override
	public void onProviderDisabled(String provider) {
	}

	/**
	 * onProviderEnabled() wird nur ben�tigt da: Activity implements
	 * LocationListener
	 */
	@Override
	public void onProviderEnabled(String provider) {
	}

	/**
	 * onStatusChanged() wird nur ben�tigt da: Activity implements
	 * LocationListener
	 */
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	/**
	 * Klasse JavascriptBrigde �bergabe der Koordinaten an das Javascript
	 * index.html
	 * 
	 * @author JNS
	 * 
	 */
	final class JavascriptBridge {

		public void setNewLocation(Location newCoordinates) {
			mostRecentLocation = newCoordinates;
		}

		public double getLatitude() {
			try {
				return mostRecentLocation.getLatitude();
			} catch (Exception e) {
				e.printStackTrace();
				// Set marker to campus
				return 53.837859;
			}
		}

		public double getLongitude() {
			try {
				return mostRecentLocation.getLongitude();
			} catch (Exception e) {
				e.printStackTrace();
				// Set marker to campus
				return 10.699237;
			}
		}

	}

	/**
	 * Klasse MyWebChromeClient Bietet einen HOOK f�r das Javascript um "Alarm"
	 * zu rufen.
	 * 
	 * @author JNS
	 * 
	 */
	final class MyWebChromeClient extends WebChromeClient {
		@Override
		public boolean onJsAlert(WebView view, String url, String message,
				JsResult result) {
			Log.d("JavascriptBridge", message);
			result.confirm();
			return true;
		}
	}
}
