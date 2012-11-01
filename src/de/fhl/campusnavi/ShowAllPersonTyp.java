package de.fhl.campusnavi;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ShowAllPersonTyp extends ListActivity {
	private DataSource datasource;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list); 

		datasource = new DataSource(this);
		datasource.open();

		List<PersonTyp> persontypen = datasource.getAllPersonTypen();

		datasource.close();
		
		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ListAdapter adapter = new ArrayAdapter<PersonTyp>(this,
				android.R.layout.simple_list_item_1, persontypen);
		final ListView lv = getListView();
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Intent intent = new Intent();
				intent.setClassName(getPackageName(), getPackageName()+".ShowAllPersons");
				int selected = ((PersonTyp) lv.getAdapter().getItem(arg2)).getId();
				intent.putExtra("selected", selected);
				startActivity(intent);
			}
		});
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
