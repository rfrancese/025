package it.unisa.bdsir_takearound.ui;

import it.unisa.takearound.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class TutorialActivity extends Activity {
	
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		
		setContentView(R.layout.tutorial_activity);
		
		
	}
	
	public void goToTutorialOnline(View v) {
		Uri uriUrl = Uri.parse("http://takearound.dudaone.com/how-to-play");
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
	}

}
