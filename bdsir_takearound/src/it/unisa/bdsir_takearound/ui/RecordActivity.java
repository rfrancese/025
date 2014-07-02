package it.unisa.bdsir_takearound.ui;

import java.util.ArrayList;

import it.unisa.bdsir_takearound.db.DatabaseHelper;
import it.unisa.takearound.R;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RecordActivity extends Activity {
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); //rimuove la barra del titolo

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //rimuove la barra delle notifiche
		this.setContentView(R.layout.record_activity);
		
		ArrayList<String> punteggiNormal = new ArrayList<String>();
		ArrayList<String> punteggiRush = new ArrayList<String>();
		
		//legge i punteggi dalla tabella record
		DatabaseHelper databaseHelper = new DatabaseHelper(this);
		
		Cursor c = databaseHelper.getPunteggi();
		try
		{
			while (c.moveToNext())
			{
				if (c.getString(2).equals("normal"))
					punteggiNormal.add(c.getString(1));
				else if (c.getString(2).equals("rush"))
					punteggiRush.add(c.getString(1));
			}
		}
		finally
		{
			c.close();
		}
		
		
		ListView listView = (ListView)findViewById(R.id.listViewRecordNormal);
        String [] array = {"Antonio","Giovanni","Michele","Giuseppe", "Leonardo", "Alessandro"};
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, R.layout.row, R.id.textViewList, array);
        listView.setAdapter(arrayAdapter);

	}

}

