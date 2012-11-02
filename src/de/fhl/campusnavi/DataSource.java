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
		ret.setBezeichnung(cursor.getString(cursor.getColumnIndex(DataBaseHelper.POIS_BEZEICHNUNG)));
		ret.setGebaeude(cursor.getString(cursor.getColumnIndex(DataBaseHelper.POIS_GEBAEUDE)));
		ret.setFachbereich(cursor.getString(cursor.getColumnIndex(DataBaseHelper.POIS_FACHBEREICH)));
		ret.setBewertung(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.POIS_BEWERTUNG)));
		ret.setTags(cursor.getString(cursor.getColumnIndex(DataBaseHelper.POIS_TAGS)));
		ret.setBesonderheit(cursor.getString(cursor.getColumnIndex(DataBaseHelper.POIS_BESONDERHEIT)));
		ret.setX_koordinate(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.POIS_X_KOORDINATE)));
		ret.setY_koordinate(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.POIS_Y_KOORDINATE)));
		return ret;
	}
	
	/* Getters for Person */
	
	public List<Person> getPersonsByType(int typ) {
		List<Person> list = new ArrayList<Person>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_PERSONEN,
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
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_PERSONEN,
				null, "_id = " + Id, null, null, null, null);

		cursor.moveToFirst();
		ret = cursorToPerson(cursor);
		cursor.close();
		return ret;
	}	
	
	private Person cursorToPerson(Cursor cursor) {
		Person ret = new Person();
		ret.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.PERSONEN_ID)));
		ret.setVorname(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSONEN_VORNAME)));
		ret.setNachname(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSONEN_NACHNAME)));
		ret.setTitel(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSONEN_TITEL)));
		ret.setSprechzeit(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSONEN_SPRECHZEIT)));
		ret.setZustaendigkeiten(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSONEN_ZUSTAENDIGKEITEN)));
		ret.setEmail(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSONEN_EMAIL)));
		ret.setPOI(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSONEN_POI)));
		ret.setTelefonnummer(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSONEN_TELEFONNUMMER)));
		return ret;
	}
	
	/* Getters for PersonTyp */
	
	public List<PersonTyp> getAllPersonTypen() {
		List<PersonTyp> list = new ArrayList<PersonTyp>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_PERSONEN_TYPEN,
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
		ret.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.PERSONEN_TYPEN_ID)));
		ret.setBezeichnung(cursor.getString(cursor.getColumnIndex(DataBaseHelper.PERSONEN_TYPEN_BEZEICHNUNG)));
		return ret;
	}
	
	/* Getters for Gebaeude */
	
	public List<Gebaeude> getAllGebaeude() {
		List<Gebaeude> list = new ArrayList<Gebaeude>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_GEBAEUDE,
				null, null, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Gebaeude gebaeude = cursorToGebaeude(cursor);
			list.add(gebaeude);
			cursor.moveToNext();
		}
		
		cursor.close();
		return list;
	}
	
	public Gebaeude getGebaeudeById(int Id) {
		Gebaeude ret = new Gebaeude();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_GEBAEUDE,
				null, "_id = " + Id, null, null, null, null);

		cursor.moveToFirst();
		ret = cursorToGebaeude(cursor);
		cursor.close();
		return ret;
	}	
	
	private Gebaeude cursorToGebaeude(Cursor cursor) {
		Gebaeude ret = new Gebaeude();
		ret.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.GEBAEUDE_ID)));
		ret.setNummer(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.GEBAEUDE_NUMMER)));
		return ret;
	}
	
	/* Getters for Raeume */
	
	public List<Raum> getRaeumeByType(int typ) {
		List<Raum> list = new ArrayList<Raum>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_RAEUME,
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
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_RAEUME,
				null, "_id = " + Id, null, null, null, null);

		cursor.moveToFirst();
		ret = cursorToRaum(cursor);
		cursor.close();
		return ret;
	}	
	
	private Raum cursorToRaum(Cursor cursor) {
		Raum ret = new Raum();
		ret.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.RAEUME_ID)));
		ret.setNummer(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.RAEUME_NUMMER)));
		ret.setName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.RAEUME_NAME)));
		ret.setStockwerk(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.RAEUME_STOCKWERK)));
		return ret;
	}
	
	/* Getters for RaumTyp */
	
	public List<RaumTyp> getAllRaumTypen() {
		List<RaumTyp> list = new ArrayList<RaumTyp>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_RAEUME_TYPEN,
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
	
	public RaumTyp getRaumTypById(int Id) {
		RaumTyp ret = new RaumTyp();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_RAEUME_TYPEN,
				null, "_id = " + Id, null, null, null, null);

		cursor.moveToFirst();
		ret = cursorToRaumTyp(cursor);
		cursor.close();
		return ret;
	}	
	
	private RaumTyp cursorToRaumTyp(Cursor cursor) {
		RaumTyp ret = new RaumTyp();
		ret.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.RAEUME_TYPEN_ID)));
		ret.setBereich(cursor.getString(cursor.getColumnIndex(DataBaseHelper.RAEUME_TYPEN_BEREICH)));
		ret.setBezeichnung(cursor.getString(cursor.getColumnIndex(DataBaseHelper.RAEUME_TYPEN_BEZEICHNUNG)));
		return ret;
	}
	
	/* Getters for Fachbereiche */
	
	public List<Fachbereich> getAllFachbereiche() {
		List<Fachbereich> list = new ArrayList<Fachbereich>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_FACHBEREICHE,
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
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_FACHBEREICHE,
				null, "_id = " + Id, null, null, null, null);

		cursor.moveToFirst();
		ret = cursorToFachbereich(cursor);
		cursor.close();
		return ret;
	}	
	
	private Fachbereich cursorToFachbereich(Cursor cursor) {
		Fachbereich ret = new Fachbereich();
		ret.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.FACHBEREICHE_ID)));
		ret.setBezeichnung(cursor.getString(cursor.getColumnIndex(DataBaseHelper.FACHBEREICHE_BEZEICHNUNG)));
		return ret;
	}
	
	/* Getters for Bedarf */
	
	public List<Bedarf> getBedarfByType(int typ) {
		List<Bedarf> list = new ArrayList<Bedarf>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_BEDARF,
				null, "typ = " + typ, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Bedarf bedarf = cursorToBedarf(cursor);
			list.add(bedarf);
			cursor.moveToNext();
		}
		
		cursor.close();
		return list;
	}	
	
	public Bedarf getBedarfById(int Id) {
		Bedarf ret = new Bedarf();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_BEDARF,
				null, "_id = " + Id, null, null, null, null);

		cursor.moveToFirst();
		ret = cursorToBedarf(cursor);
		cursor.close();
		return ret;
	}	
	
	private Bedarf cursorToBedarf(Cursor cursor) {
		Bedarf ret = new Bedarf();
		ret.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.BEDARF_ID)));
		ret.setTyp(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.BEDARF_TYP)));
		ret.setPoi(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.BEDARF_POI)));
		return ret;
	}
	
	/* Getters for BedarfTyp */
	
	public List<BedarfTyp> getAllBedarfTyp() {
		List<BedarfTyp> list = new ArrayList<BedarfTyp>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_BEDARF_TYPEN,
				null, null, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			BedarfTyp bedarftyp = cursorToBedarfTyp(cursor);
			list.add(bedarftyp);
			cursor.moveToNext();
		}
		
		cursor.close();
		return list;
	}
	
	public BedarfTyp getBedarfTypById(int Id) {
		BedarfTyp ret = new BedarfTyp();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_BEDARF_TYPEN,
				null, "_id = " + Id, null, null, null, null);

		cursor.moveToFirst();
		ret = cursorToBedarfTyp(cursor);
		cursor.close();
		return ret;
	}	
	
	private BedarfTyp cursorToBedarfTyp(Cursor cursor) {
		BedarfTyp ret = new BedarfTyp();
		ret.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.BEDARF_TYPEN_ID)));
		ret.setBezeichnung(cursor.getString(cursor.getColumnIndex(DataBaseHelper.BEDARF_TYPEN_BEZEICHNUNG)));
		return ret;
	}
	
	/* Getters for Wc */
	
	public List<Wc> getWcsByType(int typ) {
		List<Wc> list = new ArrayList<Wc>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_WC,
				null, "typ = " + typ, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Wc wc = cursorToWc(cursor);
			list.add(wc);
			cursor.moveToNext();
		}
		
		cursor.close();
		return list;
	}
	
	public Wc getWcById(int Id) {
		Wc ret = new Wc();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_WC,
				null, "_id = " + Id, null, null, null, null);

		cursor.moveToFirst();
		ret = cursorToWc(cursor);
		cursor.close();
		return ret;
	}	
	
	private Wc cursorToWc(Cursor cursor) {
		Wc ret = new Wc();
		ret.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.WC_ID)));
		ret.setTyp(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.WC_TYP)));
		ret.setPoi(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.WC_POI)));
		return ret;
	}
	
/* Getters for WcTyp */
	
	public List<WcTyp> getAllWcTypen() {
		List<WcTyp> list = new ArrayList<WcTyp>();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_WC_TYPEN,
				null, null, null, null, null, null);
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			WcTyp wctyp = cursorToWcTyp(cursor);
			list.add(wctyp);
			cursor.moveToNext();
		}
		
		cursor.close();
		return list;
	}
	
	public WcTyp getWcTypById(int Id) {
		WcTyp ret = new WcTyp();
		
		Cursor cursor = database.query(DataBaseHelper.TABLE_WC_TYPEN,
				null, "_id = " + Id, null, null, null, null);

		cursor.moveToFirst();
		ret = cursorToWcTyp(cursor);
		cursor.close();
		return ret;
	}	
	
	private WcTyp cursorToWcTyp(Cursor cursor) {
		WcTyp ret = new WcTyp();
		ret.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.WC_TYPEN_ID)));
		ret.setBezeichnung(cursor.getString(cursor.getColumnIndex(DataBaseHelper.WC_TYPEN_BEZEICHNUNG)));
		return ret;
	}
}