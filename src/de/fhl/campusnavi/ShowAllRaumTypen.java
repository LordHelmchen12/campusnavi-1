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
import android.widget.ListAdapter;
import android.widget.ListView;

public class ShowAllRaumTypen extends ListActivity {
	private DataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list); 

		datasource = new DataSource(this);
		datasource.open();

		List<RaumTyp> raumtypen = datasource.getAllRaumTypen();

		datasource.close();
		
		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ListAdapter adapter = new ArrayAdapter<RaumTyp>(this,
				android.R.layout.simple_list_item_1, raumtypen);
		final ListView lv = getListView();
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Intent intent = new Intent();
				intent.setClassName(getPackageName(), getPackageName()+".ShowAllRaeume");
				int selected = ((RaumTyp) lv.getAdapter().getItem(arg2)).getId();
				intent.putExtra("selected", selected);
				startActivity(intent);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_show_all_raum_typen, menu);
        return true;
    }
}
