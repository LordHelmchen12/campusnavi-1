package com.example.campusnavi;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void openDB() {
		DataBaseHelper myDbHelper = new DataBaseHelper(null);
	    myDbHelper = new DataBaseHelper(this);
	
	    try {
	    	myDbHelper.createDataBase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}
	    
		try {
			myDbHelper.openDataBase();
		} catch(SQLException sqle){
			throw sqle;
		}
	}
}