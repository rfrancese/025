package it.unisa.bdsir_takearound.ui;

import it.unisa.takearound.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class TutorialActivity extends Activity {
	
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); //rimuove la barra del titolo
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //rimuove la barra delle notifiche
		
		setContentView(R.layout.tutorial_activity);
		
		TextView tvita = (TextView)findViewById(R.id.textViewTutorialITA);
		
		TextView tveng = (TextView)findViewById(R.id.textViewTutorialENG);
		
		TextView tvspa = (TextView)findViewById(R.id.textViewTutorialSPA);
	}

}
