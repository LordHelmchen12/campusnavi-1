package de.fhl.campusnavi;


import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class Map extends Activity implements LocationListener {

	//private static final String MAP_URL = "file:///android_asset/googleMap.html";
	private static final String MAP_URL = "http://gmaps-samples.googlecode.com/svn/trunk/articles-android-webmap/simple-android-map.html";
    private WebView webView;
    private Location mostRecentLocation;
    private String markerDevice;
    private String markerTarget;
    //private String infoTarget;
    
  
    /**
     * onCreate()
     * wird aufgerufen, wenn die Activity neu gestartet wird
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_map);
	      getLocation();
	      setupWebView();
	      this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    
    /**
     * onResume()
     * wird aufgerufen, wenn der Nutzer wieder zur Activity zurückkehrt.
     * Hier werden Einstellungen aktualisiert.
     */
    @Override
    protected void onResume(){
            super.onResume();
            
            //Enable Javascript...This is needed so that Javascript is allowed to executeinside the WebView
            WebSettings webSettings = this.webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            
            //Register the 'Javascript Bridge' class under the 'window.android' namespace
            //this class can be invoked from the HTML/Javascript side
            this.webView.addJavascriptInterface(new JavascriptBridge(), "window.android");
            
            //Register the WebChromeClient to assist with alerts/debugging
            this.webView.setWebChromeClient(new MyWebChromeClient());
            
            //Load assets/html/index.html resource into the WebView control
            this.webView.loadUrl("file:///android_asset/html/index.html");
    }
    
    
    /**
     * setupWebView()
     * Hier werden Einstellungen für den WebView (Browser) festgelegt
     * und die URL geladen
     */
    private void setupWebView(){
  
     try{
    	markerDevice = "javascript:markerDevice(" + mostRecentLocation.getLatitude() + "," + mostRecentLocation.getLongitude()+ ")";
        markerTarget = "javascript:markerTarget(" + 53.83769929578616 +"," + 10.69892406463623 + "," + 123456 +")";
      }catch(Exception e){
    	  //go to Nordcap
         e.printStackTrace();
        markerDevice = "javascript:markerDevice(71.147081,25.747833)";
      }
      webView = (WebView) findViewById(R.id.webview);
      //Multitouch wird aktiviert falls es unterstützt wird
      webView.getSettings().setBuiltInZoomControls(true); 
      //Wartet auf die zu ladene Seite und sendet Informationen der "location"
      webView.setWebViewClient(new WebViewClient(){  
	        @Override  
	        public void onPageFinished(WebView view, String url){
	        	webView.loadUrl(markerDevice);
	        	webView.loadUrl(markerTarget);
	        	//webView.loadUrl(infoTarget);
	        	//webView.loadUrl("javascript:showTrafficLayer()");
	        	//webView.loadUrl("javascript:showBicyclingLayer()");
	        }
      });
      webView.loadUrl(MAP_URL);  
    }

    /** 
     * getLocation()
     * Stellt die Standortkoordinaten des Gerätes fest.
     * Hier prüft das System ob Lokalisierungsabfragen gestellt werden dürfen und stellt 
     * die letzten bekannten Daten bereit, die dann in das Objekt mostRecentLocation geschrieben werden
     */
    private void getLocation() {      
      LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
      Criteria criteria = new Criteria();
      criteria.setAccuracy(Criteria.ACCURACY_FINE);
  
      /* 
       * Dient der Sicherheit, dass ein Standortanbieter vorhanden ist.
       * Siehe Manifest: erlaubt wurde -> GPS <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
       * und -> INTERNET <uses-permission android:name="android.permission.INTERNET"/>
       */
      String provider = locationManager.getBestProvider(criteria,true);
      locationManager.requestLocationUpdates(provider, 1, 1, this);
      mostRecentLocation = locationManager.getLastKnownLocation(provider);
    }
    
    /**
     * onLocationChanged()
     * Der Standort auf der Karte wird auf Gerätestandort aktualisiert
     */
    public void onLocationChanged(Location location) {
      mostRecentLocation = location;
	      if(markerDevice.equals("javascript:markerDevice(" + mostRecentLocation.getLatitude() + "," + mostRecentLocation.getLongitude() + ")")){
	         webView.loadUrl(markerDevice);
	      }
    }
    
    /**
     * onProviderDisabled()
     * wird nur benötigt da: Activity implements LocationListener
     */
    public void onProviderDisabled(String provider) {
    }
    
    /**
     * onProviderEnabled()
     * wird nur benötigt da: Activity implements LocationListener
     */
    public void onProviderEnabled(String provider) {	
    }
    
    /**
     * onStatusChanged()
     * wird nur benötigt da: Activity implements LocationListener
     */
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
    
    
    /**
     * Klasse JavascriptBrigde
     * Übergabe der Koordinaten an das Javascript index.html
     * @author JNS
     *
     */
    final class JavascriptBridge{
    	
    	public void setNewLocation(Location newCoordinates){ 
    		mostRecentLocation = newCoordinates;
    	}
    	public double getLatitude() { 
    		return mostRecentLocation.getLatitude();
    	}
    
    	public double getLongitude() {
    	return mostRecentLocation.getLongitude();
    	}
    	
    }
  
	/**
	 * Klasse MyWebChromeClient
	 * Bietet einen HOOK für das Javascript um "Alarm" zu rufen.
	 * @author JNS
	 *
	 */
	final class MyWebChromeClient extends WebChromeClient{
	    @Override
	    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
	        Log.d("JavascriptBridge", message);
	        result.confirm();
	        return true;
	    }
	}
	
}
