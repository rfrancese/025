package it.unisa.bdsir_takearound.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import it.unisa.takearound.R;

public class MainActivity extends Activity{
	public void onCreate(Bundle icicle){
		
		super.onCreate(icicle);
		
		setContentView(R.layout.main_activity);
		
		Button gioca = (Button) this.findViewById(R.id.buttonGioca);
		gioca.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
			//	Toast.makeText(getBaseContext(), "hai cliccato su GIOCA", Toast.LENGTH_SHORT).show();
				openLevelChoiceActivity();
			}
		});
		
		Button record = (Button) findViewById(R.id.buttonRecord);
		record.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
			//	Toast.makeText(getBaseContext(), "hai cliccato su Record", Toast.LENGTH_SHORT).show();
				openRecordActivity(v);
			}
		});
		
		final Button tutorial = (Button) findViewById(R.id.buttonTutorial);
		tutorial.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
			//	Toast.makeText(getBaseContext(), "hai cliccato su Tutorial", Toast.LENGTH_SHORT).show();
				openTutorialActivity(tutorial);
			}
		});
	}
	
	protected void openLevelChoiceActivity() {
		Intent intent = new Intent(this, LevelChoiceActivity.class);
		startActivity(intent);
	}

	protected void openRecordActivity(View view) {
		Intent intent = new Intent(this, RecordActivity.class);
		startActivity(intent);
	}

	protected void openTutorialActivity(View view){
		Intent intent = new Intent(this, TutorialActivity.class);
		startActivity(intent);
	}
}
