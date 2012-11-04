package de.fhl.campusnavi;

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
	
	public POI getPoiById(int Id) {
		POI ret = new POI();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_POIS,
				null, "_id = " + Id, null, null, null, null);

		cursor.moveToFirst();
		ret = cursorToPOI(cursor);
		cursor.close();
		return ret;
	}

	private POI cursorToPOI(Cursor cursor) {
		POI ret = new POI();
		ret.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.POIS_ID)));
		ret.setLongitude(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.POIS_LONGITUDE)));
		ret.setLatitude(cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.POIS_LATITUDE)));
		ret.setTags(cursor.getString(cursor.getColumnIndex(DataBaseHelper.POIS_TAGS)));
		return ret;
	}
	
	/* Getters for Person */
	
	public List<Person> getPersonsByType(int typ) {
		List<Person> list = new ArrayList<Person>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_PERSON,
				null, "typ = " + typ, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Person person = cursorToPerson(cursor);
			list.add(person);
			cursor.moveToNext();
		}
		
		cursor.close();
		return list;
	}
	
	public Person getPersonById(int Id) {
		Person ret = new Person();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_PERSON,
				null, "_id = " + Id, null, null, null, null);

		cursor.moveToFirst();
		ret = cursorToPerson(cursor);
		cursor.close();
		return ret;
	}	
	
	private Person cursorToPerson(Cursor cursor) {
		Person ret = new Person();
		ret.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.PERSON_ID)));
		ret.setVorname(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSON_VORNAME)));
		ret.setNachname(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSON_NACHNAME)));
		ret.setTitel(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSON_TITEL)));
		ret.setZustaendigkeit(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSON_ZUSTAENDIGKEIT)));
		ret.setEmail(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSON_EMAIL)));
		ret.setTelefon(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSON_TELEFON)));
		ret.setSprechzeiten(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSON_SPRECHZEITEN)));
		ret.setFachbereich(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.PERSON_FACHBEREICH)));
		ret.setTyp(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.PERSON_TYP)));
		ret.setRaum(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.PERSON_RAUM)));
		return ret;
	}
	
	/* Getters for PersonTyp */
	
	public List<PersonTyp> getAllPersonTypen() {
		List<PersonTyp> list = new ArrayList<PersonTyp>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_PERSON_TYP,
				null, null, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			PersonTyp persontyp = cursorToPersonTyp(cursor);
			list.add(persontyp);
			cursor.moveToNext();
		}
		
		cursor.close();
		return list;
	}

	private PersonTyp cursorToPersonTyp(Cursor cursor) {
		PersonTyp ret = new PersonTyp();
		ret.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.PERSON_TYP_ID)));
		ret.setName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSON_TYP_NAME)));
		return ret;
	}
	
	/* Getters for Raum */
	
	public List<Raum> getRaeumeByType(int typ) {
		List<Raum> list = new ArrayList<Raum>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_RAUM,
				null, "typ = " + typ, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Raum raum = cursorToRaum(cursor);
			list.add(raum);
			cursor.moveToNext();
		}
		
		cursor.close();
		return list;
	}
	
	public Raum getRaumById(int Id) {
		Raum ret = new Raum();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_RAUM,
				null, "_id = " + Id, null, null, null, null);

		cursor.moveToFirst();
		ret = cursorToRaum(cursor);
		cursor.close();
		return ret;
	}	
	
	private Raum cursorToRaum(Cursor cursor) {
		Raum ret = new Raum();
		ret.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.RAUM_ID)));
		ret.setPoi(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.RAUM_POI)));
		ret.setGebaeude(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.RAUM_GEBAEUDE)));
		ret.setStockwerk(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.RAUM_STOCKWERK)));
		ret.setNummer(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.RAUM_NUMMER)));
		ret.setTyp(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.RAUM_TYP)));
		ret.setFachbereich(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.RAUM_FACHBEREICH)));
		return ret;
	}
	
	/* Getters for RaumTyp */
	
	public List<RaumTyp> getAllRaumTypen() {
		List<RaumTyp> list = new ArrayList<RaumTyp>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_RAUM_TYP,
				null, null, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			RaumTyp raumtyp = cursorToRaumTyp(cursor);
			list.add(raumtyp);
			cursor.moveToNext();
		}
		
		cursor.close();
		return list;
	}
	
	public List<RaumTyp> getAllRaumTypen(String filter) {
		List<RaumTyp> list = new ArrayList<RaumTyp>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_RAUM_TYP,
				null, "name LIKE '%" + filter + "%'", null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			RaumTyp raumtyp = cursorToRaumTyp(cursor);
			list.add(raumtyp);
			cursor.moveToNext();
		}
		
		cursor.close();
		return list;
	}
	
	public List<RaumTyp> getAllRaumTypen(int a, int b) {
		List<RaumTyp> list = new ArrayList<RaumTyp>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_RAUM_TYP,
				null, "_id =" + a + " OR _id =" + b, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			RaumTyp raumtyp = cursorToRaumTyp(cursor);
			list.add(raumtyp);
			cursor.moveToNext();
		}
		
		cursor.close();
		return list;
	}
	
	public List<RaumTyp> getAllRaumTypen(int a, int b, int c) {
		List<RaumTyp> list = new ArrayList<RaumTyp>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_RAUM_TYP,
				null, "_id =" + a + " OR _id =" + b + " OR _id =" + c, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			RaumTyp raumtyp = cursorToRaumTyp(cursor);
			list.add(raumtyp);
			cursor.moveToNext();
		}
		
		cursor.close();
		return list;
	}
	
	public RaumTyp getRaumTypById(int Id) {
		RaumTyp ret = new RaumTyp();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_RAUM_TYP,
				null, "_id = " + Id, null, null, null, null);

		cursor.moveToFirst();
		ret = cursorToRaumTyp(cursor);
		cursor.close();
		return ret;
	}
	
	private RaumTyp cursorToRaumTyp(Cursor cursor) {
		RaumTyp ret = new RaumTyp();
		ret.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.RAUM_TYP_ID)));
		ret.setName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.RAUM_TYP_NAME)));
		return ret;
	}
	
	/* Getters for Fachbereiche */
	
	public List<Fachbereich> getAllFachbereiche() {
		List<Fachbereich> list = new ArrayList<Fachbereich>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_FACHBEREICH,
				null, null, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Fachbereich fachbereich = cursorToFachbereich(cursor);
			list.add(fachbereich);
			cursor.moveToNext();
		}
		
		cursor.close();
		return list;
	}
	
	public Fachbereich getFachbereichById(int Id) {
		Fachbereich ret = new Fachbereich();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_FACHBEREICH,
				null, "_id = " + Id, null, null, null, null);

		cursor.moveToFirst();
		ret = cursorToFachbereich(cursor);
		cursor.close();
		return ret;
	}	
	
	private Fachbereich cursorToFachbereich(Cursor cursor) {
		Fachbereich ret = new Fachbereich();
		ret.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.FACHBEREICH_ID)));
		ret.setName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.FACHBEREICH_NAME)));
		return ret;
	}
	
	/* Getters for Einrichtung */
	
	public List<Einrichtung> getAllEinrichtung() {
		List<Einrichtung> list = new ArrayList<Einrichtung>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_EINRICHTUNG,
				null, null, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Einrichtung bedarf = cursorToEinrichtung(cursor);
			list.add(bedarf);
			cursor.moveToNext();
		}
		
		cursor.close();
		return list;
	}	
	
	public Einrichtung getEinrichtungById(int Id) {
		Einrichtung ret = new Einrichtung();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_EINRICHTUNG,
				null, "_id = " + Id, null, null, null, null);

		cursor.moveToFirst();
		ret = cursorToEinrichtung(cursor);
		cursor.close();
		return ret;
	}	
	
	private Einrichtung cursorToEinrichtung(Cursor cursor) {
		Einrichtung ret = new Einrichtung();
		ret.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.EINRICHTUNG_ID)));
		ret.setPoi(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.EINRICHTUNG_POI)));
		ret.setName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.EINRICHTUNG_NAME)));
		ret.setAngebot(cursor.getString(cursor.getColumnIndex(DataBaseHelper.EINRICHTUNG_ANGEBOT)));
		ret.setOeffnungszeiten(cursor.getString(cursor.getColumnIndex(DataBaseHelper.EINRICHTUNG_OEFFNUNGSZEITEN)));
		return ret;
	}
}