package it.unisa.bdsir_takearound.entities;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class TouchListener implements OnTouchListener{

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		
		TargetView t = (TargetView) arg0;
		
		double x=arg1.getX();
		double y=arg1.getY();
		
		double yCentroImmagine, xCentroImmagine;
		
		//devo calcolare il centro del rettangolo
		yCentroImmagine=t.gettY()+(t.getBitmapHeight()/2);
		xCentroImmagine=t.gettX()+(t.getBitmapWidth()/2);
		
		//calcoli per calcolare la circonferenza
		double calc = Math.pow(x - xCentroImmagine,2)+Math.pow(y - yCentroImmagine,2);
		double segmento = Math.sqrt(Math.abs( calc ));
		if (segmento<=(t.getBitmapHeight()/2)){
		//	Toast.makeText(getContext(), "hai cliccato sul target\ncentro target ("+xCentroImmagine+","+yCentroImmagine+")\nhai cliccato in ("+x+","+y+")", Toast.LENGTH_SHORT).show();
		//	this.updatePosition();
			t.setStatoColpito();
		}else{
		//	Toast.makeText(getContext(), "hai cliccato FUORI\ncentro target ("+xCentroImmagine+","+yCentroImmagine+")\nhai cliccato in ("+x+","+y+")", Toast.LENGTH_SHORT).show();
		}
		
		arg0.invalidate();
		return true;
	}

}
