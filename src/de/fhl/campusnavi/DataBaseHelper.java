package de.fhl.campusnavi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

	// The Android's default system path of your application database.
	private static String DB_PATH = "/data/data/de.fhl.campusnavi/databases/";
	private static String DB_NAME = "campusnavi.sql";

	public static final String TABLE_POIS = "pois";
	public static final String POIS_ID = "_id";
	public static final String POIS_TAGS = "tags";
	public static final String POIS_LONGITUDE = "longitude";
	public static final String POIS_LATITUDE = "latitude";
	
	public static final String TABLE_PERSON = "person";
	public static final String PERSON_ID = "_id";
	public static final String PERSON_VORNAME = "vorname";
	public static final String PERSON_NACHNAME = "nachname";
	public static final String PERSON_TITEL = "titel";
	public static final String PERSON_ZUSTAENDIGKEIT = "zustaendigkeit";
	public static final String PERSON_EMAIL = "email";
	public static final String PERSON_TELEFON = "telefon";
	public static final String PERSON_SPRECHZEITEN = "sprechzeiten";
	public static final String PERSON_FACHBEREICH = "fachbereich";
	public static final String PERSON_TYP = "typ";
	public static final String PERSON_RAUM = "raum";
	public static final String PERSON_POI = "poi";
	
	public static final String TABLE_PERSON_TYP = "person_typ";
	public static final String PERSON_TYP_ID = "_id";
	public static final String PERSON_TYP_NAME = "name";
	
	public static final String TABLE_RAUM = "raum";
	public static final String RAUM_ID = "_id";
	public static final String RAUM_POI = "poi";
	public static final String RAUM_GEBAEUDE = "gebaeude";
	public static final String RAUM_STOCKWERK = "stockwerk";
	public static final String RAUM_NUMMER = "nummer";
	public static final String RAUM_TYP = "typ";
	public static final String RAUM_FACHBEREICH = "fachbereich";
	
	public static final String TABLE_RAUM_TYP = "raum_typ";
	public static final String RAUM_TYP_ID = "_id";
	public static final String RAUM_TYP_NAME = "name";
	
	public static final String TABLE_FACHBEREICH = "fachbereich";
	public static final String FACHBEREICH_ID = "_id";
	public static final String FACHBEREICH_NAME = "name";

	public static final String TABLE_EINRICHTUNG = "einrichtung";
	public static final String EINRICHTUNG_ID = "_id";
	public static final String EINRICHTUNG_POI = "poi";
	public static final String EINRICHTUNG_NAME = "name";
	public static final String EINRICHTUNG_ANGEBOT = "angebot";
	public static final String EINRICHTUNG_OEFFNUNGSZEITEN = "oeffnungszeiten";
	
	private SQLiteDatabase myDataBase;

	private final Context myContext;

	/**
	 * Constructor Takes and keeps a reference of the passed context in order to
	 * access to the application assets and resources.
	 * 
	 * @param context
	 */
	public DataBaseHelper(Context context) {

		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}

	/**
	 * Creates a empty database on the system and rewrites it with your own
	 * database.
	 * */
	public void createDataBase() throws IOException {
		
		Log.d("Create DB", "Start creating DB");

		boolean dbExist = checkDataBase();

		if (dbExist) {
			Log.d("DB exist", "DB exist");
		} else {

			Log.d("DB NOT exist", "DB NOT exist");
			// By calling this method and empty database will be created into
			// the default system path
			// of your application so we are gonna be able to overwrite that
			// database with our database.
			this.getWritableDatabase().close();

			try {

				copyDataBase();
				System.out.println("Copy success");

			} catch (IOException e) {

				throw new Error("Error copying database");

			}
		}
	}

	/**
	 * Check if the database already exist to avoid re-copying the file each
	 * time you open the application.
	 * 
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {

			// database doesn't exist yet.

		}

		if (checkDB != null) {

			checkDB.close();

		}

		//return checkDB != null ? true : false;
		return false;

	}

	/**
	 * Copies your database from your local assets-folder to the just created
	 * empty database in the system folder, from where it can be accessed and
	 * handled. This is done by transfering bytestream.
	 * */
	private void copyDataBase() throws IOException {

		File f = new File(DB_PATH);
		if (!f.exists()) {
			f.mkdir();
		}

		// Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(DB_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException {

		// Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);

	}

	@Override
	public synchronized void close() {

		if (myDataBase != null)
			myDataBase.close();

		super.close();

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	// Add your public helper methods to access and get content from the
	// database.
	// You could return cursors by doing "return myDataBase.query(....)" so it'd
	// be easy
	// to you to create adapters for your views.

}
