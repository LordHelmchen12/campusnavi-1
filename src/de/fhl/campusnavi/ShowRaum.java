package de.fhl.campusnavi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShowRaum extends Activity {
	private DataSource datasource;
	private Raum raum;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_object);
        
        Intent intent = getIntent();
        
        datasource = new DataSource(this);
		datasource.open();
		
		raum = datasource.getRaumById(intent.getIntExtra("selected", 0));
		String fachbereich;
		try {
			int fachbereichid = raum.getFachbereich();
			fachbereich = datasource.getFachbereichById(fachbereichid).getName();
		} catch (Exception e) {
			fachbereich = null;
		}
		
		String raumtyp;
		try {
			int raumtypid = raum.getTyp();
			raumtyp = datasource.getRaumTypById(raumtypid).getName();
		} catch (Exception e) {
			raumtyp = null;
		}
		
        datasource.close();
        
        TextView text = (TextView) findViewById(R.id.tv);
    	String description = raum.toString();
    	if (raumtyp != null) description += "\n" + raumtyp;
    	if (fachbereich != null) description += "\n\nFachbereich:\n" + fachbereich;
    	text.setText(description);
    }

    public void showMap(View view) {
	    Intent intent = new Intent(this, Map.class);
	    int selected = (raum.getPoi());
		intent.putExtra("selected", selected);
	    startActivity(intent);
	}

}
