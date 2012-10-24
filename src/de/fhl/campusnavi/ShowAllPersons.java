package de.fhl.campusnavi;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class ShowAllPersons extends ListActivity {
	private DataSource datasource;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_all_persons);

		datasource = new DataSource(this);
		datasource.open();

		List<Person> persons = datasource.getAllPersons();

		datasource.close();
		
		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<Person> adapter = new ArrayAdapter<Person>(this,
				android.R.layout.simple_list_item_1, persons);
		setListAdapter(adapter);
	}
	
	public void Back(View view) {
	    Intent intent = new Intent(this, Start.class);
	    startActivity(intent);
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
