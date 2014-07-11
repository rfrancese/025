package it.unisa.bdsir_takearound.ui;

import java.util.ArrayList;

import it.unisa.bdsir_takearound.db.DatabaseHelper;
import it.unisa.takearound.R;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
				if (c.getString(1).equals("normal")){
					Log.d(STORAGE_SERVICE, "Letto da database: "+c.getString(0)+","+c.getString(1));
					punteggiNormal.add(c.getString(0));}
				else if (c.getString(1).equals("rush")){
					Log.d(STORAGE_SERVICE, "Letto da database: "+c.getString(0)+","+c.getString(1));
					punteggiRush.add(c.getString(0));}
			}
		}
		finally
		{
			c.close();
		}
		
		
		TableLayout listaPunteggiTotale = (TableLayout)findViewById(R.id.tabellapunteggi);
		
		
		int max;String punteggioNormal, punteggioRush;
		int temp = Math.abs(punteggiNormal.size()-punteggiRush.size());
		
		if (punteggiNormal.size() > punteggiRush.size())
			max = punteggiNormal.size()-temp;
		else
			max = punteggiRush.size()-temp;
		
		for (int k=0; k<max; k++){
			punteggioNormal = punteggiNormal.get(k);
			punteggioRush = punteggiRush.get(k);
			insertRow(listaPunteggiTotale, punteggioNormal, punteggioRush);
		}
		
		if (punteggiNormal.size()<punteggiRush.size()){
			int j= punteggiRush.size()-temp;
			for (int k=j; k<punteggiRush.size(); k++){			
				
				punteggioRush = punteggiRush.get(k);
				insertRowRush(listaPunteggiTotale, punteggioRush);
			}
		}
		else{
			int j= punteggiNormal.size()-temp;
			for (int k=j; k<punteggiNormal.size(); k++){			
				
				punteggioNormal = punteggiNormal.get(k);
				insertRowNormal(listaPunteggiTotale, punteggioNormal);
			}
		}
		
		Button scoreOnline = (Button) this.findViewById(R.id.buttonScoreOnline);
		scoreOnline.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Uri uriUrl = Uri.parse("http://takearound.dudaone.com/classifica");
		        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
		        startActivity(launchBrowser);
			}
		});
	}

	private void insertRowRush(TableLayout listaPunteggiTotale, String punteggioRush) {
		// TODO Auto-generated method stub
		TableRow row= new TableRow(this);
	     TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
	     lp.setMargins(0, 5, 0, 5);
	     
	     TextView punteggioN= new TextView(this);
	     TextView punteggioR = new TextView(this);
	     
	     punteggioN.setTextColor(Color.WHITE);
	     punteggioR.setTextColor(Color.WHITE);
	     
	     punteggioN.setText("");
	     punteggioR.setText(punteggioRush);
	     
	     punteggioN.setGravity(Gravity.LEFT);
	     punteggioR.setGravity(Gravity.RIGHT);
	     
	     row.addView(punteggioN);
	     row.addView(punteggioR);
	     listaPunteggiTotale.addView(row);
	}
	
	private void insertRowNormal(TableLayout listaPunteggiTotale, String punteggioNormal) {
		// TODO Auto-generated method stub
		TableRow row= new TableRow(this);
	     TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
	     lp.setMargins(0, 5, 0, 5);
	     
	     TextView punteggioN= new TextView(this);
	     TextView punteggioR = new TextView(this);
	     
	     punteggioN.setTextColor(Color.WHITE);
	     punteggioR.setTextColor(Color.WHITE);
	     
	     punteggioN.setText(punteggioNormal);
	     punteggioR.setText("");
	     
	     punteggioN.setGravity(Gravity.LEFT);
	     punteggioR.setGravity(Gravity.RIGHT);
	     
	     row.addView(punteggioN);
	     row.addView(punteggioR);
	     listaPunteggiTotale.addView(row);
	}

	private void insertRow(TableLayout listaPunteggiTotale,	String punteggioNormal, String punteggioRush) {
		// TODO Auto-generated method stub
		TableRow row= new TableRow(this);
		TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
		lp.setMargins(0, 5, 0, 5);
		TextView punteggioN= new TextView(this);
		TextView punteggioR = new TextView(this);
	     
	     punteggioN.setTextColor(Color.WHITE);
	     punteggioR.setTextColor(Color.WHITE);
	     
	     punteggioN.setText(punteggioNormal);
	     punteggioR.setText(punteggioRush);
	     
	     punteggioN.setGravity(Gravity.LEFT);
	     punteggioR.setGravity(Gravity.RIGHT);
	     
	     row.addView(punteggioN);
	     row.addView(punteggioR);
	     listaPunteggiTotale.addView(row);
	}

}

