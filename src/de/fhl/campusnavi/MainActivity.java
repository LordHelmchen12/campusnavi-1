package de.fhl.campusnavi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.fhl.campusnavi.R;

import android.app.ListActivity;
import android.content.Context;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {
	private DataSource datasource;
	private Context myContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

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

		datasource = new DataSource(this);
		datasource.open();

		List<POI> pois = datasource.getAllPOIS();
		List<Person> personen = datasource.getAllPersons();
		
		List<POI> pois_byid = new ArrayList<POI>();
		pois_byid.add(datasource.getPoiById(0));

		datasource.close();
		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<Person> adapter = new ArrayAdapter<Person>(this,
				android.R.layout.simple_list_item_1, personen);
		setListAdapter(adapter);
	}

	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}
}