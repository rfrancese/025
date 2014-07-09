package it.unisa.bdsir_takearound.ui;

import it.unisa.bdsir_takearound.db.DatabaseHelper;
import it.unisa.bdsir_takearound.db.RecordTable;
import it.unisa.takearound.R;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class RegistraPunteggio extends Activity{

	private DatabaseHelper databaseHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.registrapunti_activity);
		
		databaseHelper = new DatabaseHelper(this);
		String mod=null; int punt=0;
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			mod = extras.getString("modality");
			punt = extras.getInt("punteggio");
		}
		
		Log.d(NOTIFICATION_SERVICE, ""+mod+":"+punt);

		insertPunteggio(punt, mod);
		
		TextView punteggio = (TextView)this.findViewById(R.id.scoreFinale);
		punteggio.setText(mod);
		punteggio.setText(Integer.toString(punt));
		punteggio.setTextColor(Color.WHITE);
		
		
	}
	
	public void insertPunteggio(int punteggio, String mod){
		SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
		
		ContentValues values=new ContentValues();
		values.put(RecordTable.PUNTEGGIO, punteggio);
		values.put(RecordTable.MODALITY, mod);
		db.insert(RecordTable.TABLE_NAME, null, values);
	}
}
