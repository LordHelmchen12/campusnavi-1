package com.example.campusnavi;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataSource {
	private SQLiteDatabase database;
	private DataBaseHelper dbHelper;

	private String[] allColumns = { DataBaseHelper.COLUMN_ID,
			DataBaseHelper.COLUMN_BEZEICHNUNG };

	public DataSource(Context context) {
		dbHelper = new DataBaseHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public List<POI> getAllPOIS() {
		List<POI> pois = new ArrayList<POI>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_POIS,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			POI poi = cursorToPOI(cursor);
			pois.add(poi);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return pois;
	}

	private POI cursorToPOI(Cursor cursor) {
		POI poi = new POI();
		poi.setId(cursor.getInt(0));
		poi.setBezeichnung(cursor.getString(1));
		return poi;
	}

}
