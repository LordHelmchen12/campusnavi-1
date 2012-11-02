package de.fhl.campusnavi;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShowAllRaeume extends ListActivity {
	private DataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_list);
		
		Intent intent = getIntent();
			
		datasource = new DataSource(this);
		datasource.open();
		
		List<Raum> raeume = datasource.getRaeumeByType(intent.getIntExtra("selected", 0));

		datasource.close();
		
		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<Raum> adapter = new ArrayAdapter<Raum>(this,
				android.R.layout.simple_list_item_1, raeume);
		final ListView lv = getListView();
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Intent intent = new Intent();
				intent.setClassName(getPackageName(), getPackageName()+".ShowRaum");
				int selected = ((Raum) lv.getAdapter().getItem(arg2)).getId();
				intent.putExtra("selected", selected);
				startActivity(intent);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_show_all_raeume, menu);
        return true;
    }
}
