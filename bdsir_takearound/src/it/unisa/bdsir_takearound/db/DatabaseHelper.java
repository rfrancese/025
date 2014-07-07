package it.unisa.bdsir_takearound.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

	private static final String DATABASE_NAME = "takearoundDB.db";
	 
	private static final int SCHEMA_VERSION = 1;
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql="CREATE TABLE IF NOT EXISTS "+RecordTable.TABLE_NAME; 
		sql+="("+RecordTable.PUNTEGGIO+" int,";
		sql+=RecordTable.MODALITY+" varchar(20) primary key)";
		//Eseguiamo la query
		db.execSQL(sql);
		
		sql=null;
		
		insertPunteggio(db, "100","rush");
		insertPunteggio(db, "100","normal");
		insertPunteggio(db, "900","normal");
		
	}
	
	public void insertPunteggio(SQLiteDatabase db, String punteggio, String mod){
		int p = Integer.parseInt(punteggio);
		ContentValues v = new ContentValues();
		v.put(RecordTable.PUNTEGGIO, p);
		v.put(RecordTable.MODALITY, mod);
		db.insert(RecordTable.TABLE_NAME, null, v);
	}
	
	public Cursor getPunteggi(){
		return (getReadableDatabase().query(
				RecordTable.TABLE_NAME, 
				RecordTable.COLUMNS, 
				null, 
				null,
				null, 
				null, 
				RecordTable.MODALITY));
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

}
