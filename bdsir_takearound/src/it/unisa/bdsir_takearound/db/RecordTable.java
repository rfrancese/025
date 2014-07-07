package it.unisa.bdsir_takearound.db;

import android.provider.BaseColumns;

public interface RecordTable extends BaseColumns{

	String TABLE_NAME = "record";
 
	String PUNTEGGIO = "punteggio";
 
	String MODALITY = "modality";
	
	String ID = "id";
 
	String[] COLUMNS = new String[]
	{  PUNTEGGIO, MODALITY };
}

