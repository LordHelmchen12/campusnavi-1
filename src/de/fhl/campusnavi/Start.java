package de.fhl.campusnavi;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Start extends Activity {
	private Context myContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		DataBaseHelper myDbHelper = new DataBaseHelper(myContext);
		myDbHelper = new DataBaseHelper(this);

		try {
			myDbHelper.createDataBase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}

		try {
			myDbHelper.openDataBase();
		} catch (SQLException sqle) {
			throw sqle;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_start, menu);
		return true;
	}
	
	public void openPois(View view) {
	    Intent intent = new Intent(this, Map.class);
	    startActivity(intent);
	}
	
	public void openPersons(View view) {
	    Intent intent = new Intent(this, ShowAllPersons.class);
	    startActivity(intent);
	}
}
