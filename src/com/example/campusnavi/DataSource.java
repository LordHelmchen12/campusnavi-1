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

	public DataSource(Context context) {
		dbHelper = new DataBaseHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	/* Getters for POIS */
	
	public List<POI> getAllPOIS() {
		List<POI> pois = new ArrayList<POI>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_POIS,
				null, null, null, null, null, null);

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
	
	public POI getPoiById(int Id) {
		POI poi = new POI();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_POIS,
				null, "_id = " + Id, null, null, null, null);

		cursor.moveToFirst();
		poi = cursorToPOI(cursor);
		cursor.close();
		return poi;
	}

	private POI cursorToPOI(Cursor cursor) {
		POI poi = new POI();
		poi.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.POIS_ID)));
		poi.setBezeichnung(cursor.getString(cursor.getColumnIndex(DataBaseHelper.POIS_BEZEICHNUNG)));
		poi.setGebaeude(cursor.getString(cursor.getColumnIndex(DataBaseHelper.POIS_GEBAEUDE)));
		poi.setFachbereich(cursor.getString(cursor.getColumnIndex(DataBaseHelper.POIS_FACHBEREICH)));
		poi.setBewertung(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.POIS_BEWERTUNG)));
		poi.setTags(cursor.getString(cursor.getColumnIndex(DataBaseHelper.POIS_TAGS)));
		poi.setBesonderheit(cursor.getString(cursor.getColumnIndex(DataBaseHelper.POIS_BESONDERHEIT)));
		poi.setX_koordinate(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.POIS_X_KOORDINATE)));
		poi.setY_koordinate(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.POIS_Y_KOORDINATE)));
		return poi;
	}
	
	/* Getters for Personen */
	
	public List<Person> getAllPersons() {
		List<Person> personen = new ArrayList<Person>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_PERSONEN,
				null, null, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Person person = cursorToPerson(cursor);
			personen.add(person);
			cursor.moveToNext();
		}
		
		cursor.close();
		return personen;
	}
	
	public Person getPersonById(int Id) {
		Person person = new Person();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_PERSONEN,
				null, "_id = " + Id, null, null, null, null);

		cursor.moveToFirst();
		person = cursorToPerson(cursor);
		cursor.close();
		return person;
	}	
	
	private Person cursorToPerson(Cursor cursor) {
		Person person = new Person();
		person.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.PERSONEN_ID)));
		person.setVorname(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSONEN_VORNAME)));
		person.setNachname(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSONEN_NACHNAME)));
		person.setTitel(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSONEN_TITEL)));
		person.setSprechzeit(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSONEN_SPRECHZEIT)));
		person.setZustaendigkeiten(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSONEN_ZUSTAENDIGKEITEN)));
		person.setEmail(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSONEN_EMAIL)));
		person.setPOI(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSONEN_POI)));
		return person;
	}
	
	/* Getters for Gebaeude */
	
	public List<Gebaeude> getAllGebaeude() {
		List<Gebaeude> gebaeudeliste = new ArrayList<Gebaeude>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_GEBAEUDE,
				null, null, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Gebaeude gebaeude = cursorToGebaeude(cursor);
			gebaeudeliste.add(gebaeude);
			cursor.moveToNext();
		}
		
		cursor.close();
		return gebaeudeliste;
	}
	
	public Gebaeude getGebaeudeById(int Id) {
		Gebaeude gebaeude = new Gebaeude();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_GEBAEUDE,
				null, "_id = " + Id, null, null, null, null);

		cursor.moveToFirst();
		gebaeude = cursorToGebaeude(cursor);
		cursor.close();
		return gebaeude;
	}	
	
	private Gebaeude cursorToGebaeude(Cursor cursor) {
		Gebaeude gebaeude = new Gebaeude();
		gebaeude.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.GEBAEUDE_ID)));
		gebaeude.setNummer(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.GEBAEUDE_NUMMER)));
		return gebaeude;
	}
	
	/* Getters for Raeume */
	
	public List<Raum> getAllRaeume() {
		List<Raum> raeume = new ArrayList<Raum>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_RAEUME,
				null, null, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Raum raum = cursorToRaum(cursor);
			raeume.add(raum);
			cursor.moveToNext();
		}
		
		cursor.close();
		return raeume;
	}
	
	public Raum getRaumById(int Id) {
		Raum raum = new Raum();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_RAEUME,
				null, "_id = " + Id, null, null, null, null);

		cursor.moveToFirst();
		raum = cursorToRaum(cursor);
		cursor.close();
		return raum;
	}	
	
	private Raum cursorToRaum(Cursor cursor) {
		Raum raum = new Raum();
		raum.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.RAEUME_ID)));
		raum.setNummer(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.RAEUME_NUMMER)));
		raum.setName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.RAEUME_NAME)));
		raum.setStockwerk(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.RAEUME_STOCKWERK)));
		raum.setGebaeude(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.RAEUME_GEBAEUDE)));
		return raum;
	}
	
	/* Getters for Fachbereiche */
	
	public List<Fachbereich> getAllFachbereiche() {
		List<Fachbereich> fachbereiche = new ArrayList<Fachbereich>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_FACHBEREICHE,
				null, null, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Fachbereich fachbereich = cursorToFachbereich(cursor);
			fachbereiche.add(fachbereich);
			cursor.moveToNext();
		}
		
		cursor.close();
		return fachbereiche;
	}
	
	public Fachbereich getFachbereichById(int Id) {
		Fachbereich fachbereich = new Fachbereich();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_FACHBEREICHE,
				null, "_id = " + Id, null, null, null, null);

		cursor.moveToFirst();
		fachbereich = cursorToFachbereich(cursor);
		cursor.close();
		return fachbereich;
	}	
	
	private Fachbereich cursorToFachbereich(Cursor cursor) {
		Fachbereich fachbereich = new Fachbereich();
		fachbereich.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.FACHBEREICHE_ID)));
		fachbereich.setBezeichnung(cursor.getString(cursor.getColumnIndex(DataBaseHelper.FACHBEREICHE_BEZEICHNUNG)));
		return fachbereich;
	}

}
