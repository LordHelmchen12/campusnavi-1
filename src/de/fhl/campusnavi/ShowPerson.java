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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_object);
        
        Intent intent = getIntent();
        
        datasource = new DataSource(this);
		datasource.open();
		
		person = datasource.getPersonById(intent.getIntExtra("selected", 0));
		
        datasource.close();
        
        TextView text = (TextView) findViewById(R.id.tv);
        text.setText(person.returnInfo());
    }
    
    public void showMap(View view) {
    	datasource = new DataSource(this);
		datasource.open();
	    Intent intent = new Intent(this, Map.class);
	    int raumid = (person.getRaum());
	    Raum raum = datasource.getRaumById(raumid);
	    int selected = raum.getPoi();
	    Log.d("Selected Poi: ", "" + selected);
		intent.putExtra("selected", selected);
	    startActivity(intent);
	}  
}
