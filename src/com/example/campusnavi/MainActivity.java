package com.example.campusnavi;

import java.io.IOException;
import java.util.List;

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

		List<POI> values = datasource.getAllPOIS();

		datasource.close();
		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<POI> adapter = new ArrayAdapter<POI>(this,
				android.R.layout.simple_list_item_1, values);
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