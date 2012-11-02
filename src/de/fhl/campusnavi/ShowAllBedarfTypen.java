package de.fhl.campusnavi;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ShowAllBedarfTypen extends ListActivity {
	private DataSource datasource;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list); 

		datasource = new DataSource(this);
		datasource.open();

		List<BedarfTyp> bedarftypen = datasource.getAllBedarfTyp();

		datasource.close();
		
		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ListAdapter adapter = new ArrayAdapter<BedarfTyp>(this,
				android.R.layout.simple_list_item_1, bedarftypen);
		final ListView lv = getListView();
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Intent intent = new Intent();
				intent.setClassName(getPackageName(), getPackageName()+".ShowAllBedarf");
				int selected = ((PersonTyp) lv.getAdapter().getItem(arg2)).getId();
				intent.putExtra("selected", selected);
				startActivity(intent);
			}
		});
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_show_all_bedarf_typen, menu);
        return true;
    }
}
