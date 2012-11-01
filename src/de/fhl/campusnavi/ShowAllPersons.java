package de.fhl.campusnavi;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShowAllPersons extends ListActivity {
	private DataSource datasource;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_list);
		
		Intent intent = getIntent();
			
		datasource = new DataSource(this);
		datasource.open();
		
		List<Person> persons = datasource.getPersonsByType(intent.getIntExtra("selected", 0));

		datasource.close();
		
		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<Person> adapter = new ArrayAdapter<Person>(this,
				android.R.layout.simple_list_item_1, persons);
		final ListView lv = getListView();
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Intent intent = new Intent();
				intent.setClassName(getPackageName(), getPackageName()+".ShowPerson");
				int selected = ((Person) lv.getAdapter().getItem(arg2)).getId();
				intent.putExtra("selected", selected);
				startActivity(intent);
			}
		});
	}
	
	public void Back(View view) {
	    Intent intent = new Intent(this, ShowAllPersonTyp.class);
	    datasource.close();
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
