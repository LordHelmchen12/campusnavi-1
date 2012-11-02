package de.fhl.campusnavi;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ShowAllWc extends ListActivity {
	private DataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_list);
		
		Intent intent = getIntent();
			
		datasource = new DataSource(this);
		datasource.open();
		
		List<Wc> wc = datasource.getWcsByType(intent.getIntExtra("selected", 0));

		datasource.close();
		
		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<Wc> adapter = new ArrayAdapter<Wc>(this,
				android.R.layout.simple_list_item_1, wc);
		final ListView lv = getListView();
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Intent intent = new Intent();
				intent.setClassName(getPackageName(), getPackageName()+".ShowWc");
				int selected = ((Raum) lv.getAdapter().getItem(arg2)).getId();
				intent.putExtra("selected", selected);
				startActivity(intent);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_show_all_wc, menu);
        return true;
    }
}
