package it.unisa.bdsir_takearound.ui;

import it.unisa.bdsir_takearound.db.DatabaseHelper;
import it.unisa.bdsir_takearound.db.RecordTable;
import it.unisa.takearound.R;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class RegistraPunteggio extends Activity{

	private DatabaseHelper databaseHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		databaseHelper = new DatabaseHelper(this);
		
		Bundle extras = getIntent().getExtras();
		String mod = extras.getString("modality");
		int punt = extras.getInt("punteggio");

		insertPunteggio(punt, mod);
		
		setContentView(R.layout.registrapunti_activity);
	}
	
	public void insertPunteggio(int punteggio, String mod){
		SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
		
		ContentValues values=new ContentValues();
		values.put(RecordTable.PUNTEGGIO, punteggio);
		values.put(RecordTable.MODALITY, mod);
		db.insert(RecordTable.TABLE_NAME, null, values);
	}
}
