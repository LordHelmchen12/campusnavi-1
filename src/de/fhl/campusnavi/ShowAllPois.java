package de.fhl.campusnavi;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class ShowAllPois extends ListActivity {
	private DataSource datasource;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showallpois);

		datasource = new DataSource(this);
		datasource.open();

		List<POI> pois = datasource.getAllPOIS();

		datasource.close();
		
		ArrayAdapter<POI> adapter = new ArrayAdapter<POI>(this,
				android.R.layout.simple_list_item_1, pois);
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