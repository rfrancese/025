package it.unisa.bdsir_takearound.ui;

import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;

import it.unisa.bdsir_takearound.entities.Target;
import it.unisa.bdsir_takearound.entities.TargetView;
import it.unisa.takearound.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class GameActivity extends Activity{
	TargetView target;
	ArrayList<Target> listaTarget=new ArrayList<Target>();
//  Handler updatetargetHandler;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
/*	    LinearLayout l = (LinearLayout)findViewById(R.layout.game_activity);
	    
	    
	    for (int i=0; i<5;i++){
	    	Target t = new Target(i*10, i*10);
	    	TargetView targetV = new TargetView(this.getBaseContext());
	    	targetV.setTarget(t);
	    	l.addView(targetV);
	    }
*/	    
	    setContentView(R.layout.game_activity);
	} 
}
