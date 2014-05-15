package it.unisa.bdsir_takearound.ui;

import it.unisa.bdsir_takearound.entities.Target;
import it.unisa.takearound.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class GameActivity extends Activity{
	Target target;
//  Handler updatetargetHandler;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.myfragment_layout);
	    
	    target=(Target)findViewById(R.id.customView);
	    target.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				target.updatePosition();
				
			}
		});
	    
	    Button b=(Button)findViewById(R.id.Button01);
	    b.setOnClickListener(new View.OnClickListener() {
	    	
	    	public void onClick(View v) {
	    		target.updatePosition();
	        }
	    });
	} 
}
