package de.fhl.campusnavi;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ShowEinrichtung extends Activity {
	private DataSource datasource;
	private Einrichtung einrichtung;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_object);
        
        Intent intent = getIntent();
        
        datasource = new DataSource(this);
		datasource.open();
		
		einrichtung = datasource.getEinrichtungById(intent.getIntExtra("selected", 0));
		
        datasource.close();
        
        TextView text = (TextView) findViewById(R.id.tv);
        text.setText(einrichtung.returnInfo());
    }

    public void showMap(View view) {
    	datasource = new DataSource(this);
		datasource.open();
	    Intent intent = new Intent(this, Map.class);
	    int selected = (einrichtung.getPoi());
		intent.putExtra("selected", selected);
	    startActivity(intent);
	}
}
