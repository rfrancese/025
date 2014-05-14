package it.unisa.bdsir_takearound;

import it.unisa.takearound.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class GameActivity extends Activity{
	CustomView cv;
//  Handler updateCvHandler;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.myfragment_layout);
	    
	    cv=(CustomView)findViewById(R.id.customView);
	    cv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				cv.updatePosition();
				
			}
		});
	    
	    Button b=(Button)findViewById(R.id.Button01);
	    b.setOnClickListener(new View.OnClickListener() {
	    	
	    	public void onClick(View v) {
	    		cv.updatePosition();
	        }
	    });
	} 
}
