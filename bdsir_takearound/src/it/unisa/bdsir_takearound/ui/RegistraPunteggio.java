package it.unisa.bdsir_takearound.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import it.unisa.bdsir_takearound.db.DatabaseHelper;
import it.unisa.bdsir_takearound.db.Punteggio;
import it.unisa.bdsir_takearound.db.RecordTable;
import it.unisa.takearound.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistraPunteggio extends Activity{

	private DatabaseHelper databaseHelper;
	String mod="", nickname=""; int punt=0;
	Punteggio punteggioDaInviare;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.registrapunti_activity);
		
		databaseHelper = new DatabaseHelper(this);
		
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
		
		
		Button b = (Button) findViewById(R.id.button2);
		b.setOnClickListener(new View.OnClickListener() {
		
			final EditText input = new EditText(RegistraPunteggio.this);
			Editable editable = input.getEditableText();
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				new AlertDialog.Builder(RegistraPunteggio.this)
				    .setTitle("Your name:")
				    .setMessage("")
				    .setView(input)
				    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				         public void onClick(DialogInterface dialog, int whichButton) {
				             editable = input.getText(); 
				             nickname = editable.toString();
				             //ora che ho il nickname faccio l'invio al server
				             
				             new inviaPunteggioOnline().execute("http://takearound.altervista.com/registrapunteggio.php");
				         }
				    })
				    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				         public void onClick(DialogInterface dialog, int whichButton) {
				                // Do nothing.
				         }
				    }).show();
				
			}
		});
	}
	
	 
	 
	class inviaPunteggioOnline extends AsyncTask<String, Void, String>
    {

		@Override
		protected String doInBackground(String... params) {
			
			punteggioDaInviare = new Punteggio();
			punteggioDaInviare.setModality(mod);
			punteggioDaInviare.setPunteggioTotale(Integer.toString(punt));
			punteggioDaInviare.setNickname(nickname);
			
			return POST(params[0],punteggioDaInviare);
			
		}
		
		// onPostExcecute display the result of the AsynTask
		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
		}
		
		
	}
	
	public boolean validate(){
		
		return false;
	}



	public static String POST (String url, Punteggio punteggio){
		InputStream inputStream = null;
		String result = "";
		try{
			//1. crea HttpClient
			HttpClient httpClient = new DefaultHttpClient();
			//2. fai una richiesta post all'url dato
			HttpPost httpPost = new HttpPost(url);
			
			String json = "";
			
			//3. costruisci jsonObject
			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("nickname", punteggio.getNickname());
			jsonObject.accumulate("score", punteggio.getPunteggioTotale());
			jsonObject.accumulate("modality", punteggio.getModality());
			
			//4. Converte JSONObject in una stringa
			json = jsonObject.toString();
			
			// 5. set json to StringEntity
			StringEntity se = new StringEntity(json);
			
			// 6. setta l'entita di httpPost
			httpPost.setEntity(se);
			
			// 7. Setta alcuni header per informare il server sul tipo del contenuto
			httpPost.setHeader("Content-type","application/json");
			
			// 8. Esegue la richiesta POST al dato URL
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			// 9. Riceviamo il responso come inputStream
			inputStream = httpResponse.getEntity().getContent();
			
			// 10. converte inputstream in una stringa
			if (inputStream != null)
				result = convertInputStreamToString(inputStream);
			else
				result = "Did not work!";
		} catch(Exception e){
			Log.d("InputStream", e.getLocalizedMessage());
		}
		
		// 11. restituisce il risultato
		return result;
	}
	
	public boolean isConnected(){
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected())
			return true;
		else
			return false;
	}
	
	
	private static String convertInputStreamToString(InputStream inputStream) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while((line=bufferedReader.readLine()) != null)
			result += line;
		
		inputStream.close();
		return result;
	}


	public void insertPunteggio(int punteggio, String mod){
		SQLiteDatabase db = this.databaseHelper.getWritableDatabase();
		
		ContentValues values=new ContentValues();
		values.put(RecordTable.PUNTEGGIO, punteggio);
		values.put(RecordTable.MODALITY, mod);
		db.insert(RecordTable.TABLE_NAME, null, values);
	}
}
