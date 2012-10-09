package com.example.campusnavi;

import java.io.IOException;

import android.database.SQLException;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DataBaseHelper myDbHelper = new DataBaseHelper();
		myDbHelper = new DataBaseHelper(this);

		try {

			myDbHelper.createDataBase();

		} catch (IOException ioe) {

			throw new Error("Unable to create database");

		}

		try {

			myDbHelper.openDataBase();

		} catch (SQLException sqle) {

			throw sqle;

		}

	}

}
