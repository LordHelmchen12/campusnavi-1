package de.fhl.campusnavi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowPerson extends Activity {
	private DataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_person);
        
        Intent intent = getIntent();
        
        datasource = new DataSource(this);
		datasource.open();
		
		Person person = datasource.getPersonById(intent.getIntExtra("selected", 0));
		
        datasource.close();
        
        TextView text = (TextView) findViewById(R.id.tv);
        text.setText(person.returnInfo());
    }
    
}
