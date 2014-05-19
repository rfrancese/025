package it.unisa.bdsir_takearound.ui;

import java.util.ArrayList;

import it.unisa.bdsir_takearound.entities.CustomView;
import it.unisa.bdsir_takearound.entities.Target;
import it.unisa.bdsir_takearound.entities.TargetGroupView;
import it.unisa.bdsir_takearound.entities.TargetView;
import it.unisa.bdsir_takearound.entities.TouchListener;
import it.unisa.takearound.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class GameActivity extends Activity{
	TargetView target;
	ArrayList<TargetView> listaTarget=new ArrayList<TargetView>();
//  Handler updatetargetHandler;
	
	CustomView cv;
	
	public void onCreate(Bundle savedInstanceState) {
/*	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.game_activity);
	    
	    TargetGroupView tgv = (TargetGroupView)findViewById(R.id.targetgroup0);
	//    LinearLayout l = (LinearLayout) findViewById(R.layout.game_activity);
	    
	    //if (tgv!=null){
		    for (int i=0; i<5;i++){
		    	Target t = new Target(i*50, i*50);
		    	TargetView targetV = new TargetView(this);
		    	targetV.setTarget(t);
		    	targetV.setOnTouchListener(new TouchListener()); //<--
		    	listaTarget.add(targetV);
		    }
		    
		    for(int j=0; j<listaTarget.size();j++){
		    	TargetView t = listaTarget.get(j);
		    	t.setOnTouchListener(new TouchListener());
		    	tgv.addView(t); //<--
		    }
	    //}
	    
	    //else setContentView(R.layout.game_activity);
*/
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myfragment_layout);

		cv=(CustomView)findViewById(R.id.customView1);
		cv.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			cv.updatePosition();}
		
		});

/*		Button b=(Button)findViewById(R.id.Button01);
		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				cv.updatePosition();
				}
			});
*/
	} 
	 
}
