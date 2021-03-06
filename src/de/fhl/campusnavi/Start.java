package de.fhl.campusnavi;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

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
	
	public void openPersonTypes(View view) {
	    Intent intent = new Intent(this, ShowAllPersonTyp.class);
	    startActivity(intent);
	}
	
	public void openRaumTypen(View view) {
	    Intent intent = new Intent(this, ShowAllRaumTypen.class);
	    startActivity(intent);
	}
	
	public void openEinrichtung(View view) {
	    Intent intent = new Intent(this, ShowAllEinrichtung.class);
	    startActivity(intent);
	}
	
	public void openWcs(View view) {
	    Intent intent = new Intent(this, ShowAllRaumTypenWc.class);
	    startActivity(intent);
	}
	
	public void openLehrsaal(View view) {
	    Intent intent = new Intent(this, ShowAllRaumTypenLehrsaal.class);
	    startActivity(intent);
	}
	
	public void openBuero(View view) {
	    Intent intent = new Intent(this, ShowAllRaumTypenBuero.class);
	    startActivity(intent);
	}
}
