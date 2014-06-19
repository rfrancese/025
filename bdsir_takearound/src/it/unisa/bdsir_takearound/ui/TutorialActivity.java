package it.unisa.bdsir_takearound.ui;

import it.unisa.takearound.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TutorialActivity extends Activity {
	
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		setContentView(R.layout.tutorial_activity);
		
		TextView tvita = (TextView)findViewById(R.id.textViewTutorialITA);
		
		TextView tveng = (TextView)findViewById(R.id.textViewTutorialENG);
		
		TextView tvspa = (TextView)findViewById(R.id.textViewTutorialSPA);
	}

}
