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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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
				if (c.getString(1).equals("normal"))
					punteggiNormal.add(c.getString(0));
				else if (c.getString(1).equals("rush"))
					punteggiRush.add(c.getString(0));
			}
		}
		finally
		{
			c.close();
		}
		
		
		TableLayout listaPunteggiTotale = (TableLayout)findViewById(R.id.tabellapunteggi);

//		String [] array = {"Antonio","Giovanni","Michele","Giuseppe", "Leonardo", "Alessandro"};
		String[] arrayNormal = new String[punteggiNormal.size()];
		for (int i=0; i<punteggiNormal.size(); i++){
			arrayNormal[i] = punteggiNormal.get(i);
		}
		
		String[] arrayRush = new String[punteggiRush.size()];
		for (int j=0; j<punteggiNormal.size(); j++){
			arrayRush[j] = punteggiNormal.get(j);
		}
		int max;String punteggioNormal, punteggioRush, mod;
		if (punteggiNormal.size()<punteggiRush.size()) max=punteggiRush.size(); else max=punteggiNormal.size();
		for (int k=0; k<max; k++){
			punteggioNormal = arrayNormal[k];
			punteggioRush = arrayRush[k];
			insertRow(listaPunteggiTotale, punteggioNormal, punteggioRush);
		}
        
	}

	private void insertRow(TableLayout listaPunteggiTotale,
			String punteggioNormal, String punteggioRush) {
		// TODO Auto-generated method stub
		TableRow row= new TableRow(this);
	     TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
	     lp.setMargins(0, 5, 0, 5);
	     TextView punteggioN= new TextView(this);
	     TextView punteggioR = new TextView(this);
	}

}

