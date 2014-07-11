package it.unisa.bdsir_takearound.ui;

import it.unisa.bdsir_takearound.db.DatabaseHelper;
import it.unisa.bdsir_takearound.db.SettingsTable;
import it.unisa.takearound.R;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends Activity {
	private Activity a = this;

	private DatabaseHelper databaseHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		this.setContentView(R.layout.settings_activity);
		
		Button b = (Button) this.findViewById(R.id.buttonRegisterNickname);
		final EditText et = (EditText) this.findViewById(R.id.editTextNickname);
		
		databaseHelper = new DatabaseHelper(this);
		String savedNickname = getNicknameFromDB();
		
		if ((savedNickname != null) && (!savedNickname.equals(""))){//se nel database è stato già inserito un nickname
			
			et.setText(savedNickname);
			b.setText("Back");
			b.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					a.onBackPressed();
				}
			});
		}
		
		else{
			
			b.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					insertNicknameInDB(et.getEditableText().toString());
					Toast.makeText(getBaseContext(), "Nickname saved!", Toast.LENGTH_LONG).show();
					a.onBackPressed();
				}
			});
		
			}
	}

	private String getNicknameFromDB() {
		
		String toR = "";
		
		Cursor c = databaseHelper.getNickname();
		try
		{
			while (c.moveToNext())
			{
				if (c.getString(0) != null){
					Log.d(STORAGE_SERVICE, "Letto da database: "+c.getString(0));
					toR = c.getString(0);
				}
			}
		}
		finally
		{
			c.close();
		}
		
		
		return toR;
	}
	
	public void insertNicknameInDB(String nick){
		SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("nickname", nick);
		
		db.insert(SettingsTable.TABLE_NAME, null, cv);
	}

}
