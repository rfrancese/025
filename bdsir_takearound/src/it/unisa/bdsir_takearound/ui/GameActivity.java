package it.unisa.bdsir_takearound.ui;

import java.io.IOException;
import java.util.ArrayList;

import it.unisa.bdsir_takearound.entities.CustomView;
import it.unisa.bdsir_takearound.entities.Target;
import it.unisa.bdsir_takearound.entities.TargetGroupView;
import it.unisa.bdsir_takearound.entities.TargetView;
import it.unisa.bdsir_takearound.entities.TouchListener;
import it.unisa.takearound.R;
import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameActivity extends Activity{
	TargetView target;
	ArrayList<TargetView> listaTarget=new ArrayList<TargetView>();
	MediaPlayer mPlayer;
	TextView tv;
	public int currentScore = 0;
	
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
		setContentView(R.layout.game_activity);
		
		tv = (TextView)findViewById(R.id.textViewScore);
		tv.setText("Punteggio:  0");
		
		
		
		mPlayer = MediaPlayer.create(GameActivity.this, R.raw.medio);
		try {
			mPlayer.prepare();
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		} 
		
		mPlayer.setLooping(true);
		mPlayer.start();
		

		target=(TargetView)findViewById(R.id.customView1);
		//application = (TryTakeARound) getApplication();
		target.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				double x=arg1.getX();
				double y=arg1.getY();

				double yCentroImmagine, xCentroImmagine;

				yCentroImmagine=target.gettY()+(target.getBitmapHeight()/2);
				xCentroImmagine=target.gettX()+(target.getBitmapWidth()/2);
				
				double calc = Math.pow(x - xCentroImmagine,2)+Math.pow(y - yCentroImmagine,2);
				double segmento = Math.sqrt(Math.abs( calc ));
				if (segmento<=(target.getBitmapHeight()/2)){
				//	Toast.makeText(getContext(), "hai cliccato sul target\ncentro target ("+xCentroImmagine+","+yCentroImmagine+")\nhai cliccato in ("+x+","+y+")", Toast.LENGTH_SHORT).show();
				//	this.updatePosition();
				//	target.setStatoColpito();
					((TargetView) arg0).updatePosition();
					arg0.invalidate();
					//application.updateScore(10);
					
					tv.setText("Punteggio: " + (currentScore +=50));
				}else{
				//	Toast.makeText(getContext(), "hai cliccato FUORI\ncentro target ("+xCentroImmagine+","+yCentroImmagine+")\nhai cliccato in ("+x+","+y+")", Toast.LENGTH_SHORT).show();
				}
				
				return false;
			}
		});
			
			
		//	application.updateScore(10);
		//	tv.setText("Punteggio: "+ application.currentScore);
			
			
		
		
		
		
		
		//tv.setText("Punteggio: "+ currentScore);
		
/*		Button b=(Button)findViewById(R.id.Button01);
		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				cv.updatePosition();
				}
			});
*/
	} 
	
	public void onBackPressed(){
		mPlayer.stop();
		super.onBackPressed();
	}

	 
}

