package com.example.campusnavi;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataSource {
	private SQLiteDatabase database;
	private DataBaseHelper dbHelper;

	public DataSource(Context context) {
		dbHelper = new DataBaseHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
	
	public POI createPOI(int poi) {
		Cursor cursor = database.rawQuery("SELECT * FROM POIS", null);
		cursor.moveToFirst();
		POI newPOI = cursorToPOI(cursor);
		return newPOI;
	}

	private POI cursorToPOI(Cursor cursor) {
		POI poi = new POI();
	    poi.setId(cursor.getLong(0));
	    poi.setPOI(cursor.getString(1));
	    return poi;
	}

}
