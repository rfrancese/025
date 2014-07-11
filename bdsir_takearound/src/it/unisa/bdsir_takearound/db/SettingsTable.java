package it.unisa.bdsir_takearound.db;

import android.provider.BaseColumns;

public interface SettingsTable extends BaseColumns{
	String TABLE_NAME = "settings";
	
	String nickname = "nickname";
	
	String[] COLUMNS = new String[]
			{  nickname };
}
