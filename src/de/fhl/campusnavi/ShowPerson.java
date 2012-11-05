package de.fhl.campusnavi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ShowPerson extends Activity {
	private DataSource datasource;
	private Person person;
	private int raumid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_object);
        
        Intent intent = getIntent();
        
        datasource = new DataSource(this);
		datasource.open();
		
		person = datasource.getPersonById(intent.getIntExtra("selected", 0));
		
		int gebaeude = 0;
		int stockwerk = 0;
		int raumnummer = 0;
		boolean raumGiven;
		try {
			raumid = person.getRaum();
			gebaeude = datasource.getRaumById(raumid).getGebaeude();
			stockwerk = datasource.getRaumById(raumid).getStockwerk();
			raumnummer = datasource.getRaumById(raumid).getNummer();
			raumGiven = true;
		} catch (Exception e) {
			raumGiven = false;
		}
		
        datasource.close();
        
        TextView text = (TextView) findViewById(R.id.tv);
        String description = person.returnInfo();
        if (raumGiven = true) description += "\nBŸro: " + gebaeude + "." + stockwerk + "." + raumnummer;
        text.setText(description);
    }
    
    public void showMap(View view) {
    	datasource = new DataSource(this);
		datasource.open();
	    Intent intent = new Intent(this, Map.class);
	    Raum raum = datasource.getRaumById(raumid);
		datasource.close();
	    int selected = raum.getPoi();
	    Log.d("Selected Poi: ", "" + selected);
		intent.putExtra("selected", selected);
	    startActivity(intent);
	}  
}
