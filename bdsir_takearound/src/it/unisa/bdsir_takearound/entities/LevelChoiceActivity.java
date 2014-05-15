package it.unisa.bdsir_takearound.entities;

import it.unisa.bdsir_takearound.ui.GameActivity;
import it.unisa.takearound.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelChoiceActivity extends Activity{
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		this.setContentView(R.layout.levelchoice_activity);
		
		Button play = (Button) findViewById(R.id.button1);
		play.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				openGameActivity();
			}
		});
	}

	protected void openGameActivity() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
	}

}
