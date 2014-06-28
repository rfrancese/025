package it.unisa.bdsir_takearound.entities;

import it.unisa.takearound.R;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.Toast;


public class TargetView extends View {

	Bitmap mBmp;
	Random mRnd;
	Paint mPaint;
	int widthView,heightView,bitmapWidth,bitmapHeight=0;
	double px=0,py=0; //coordinate immagine
	private boolean statoColpito;
	private Target target;

	public TargetView(Context context) {
		super(context,null);
		init(context);
	}

	public TargetView(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    init(context);
	}

	public TargetView(Context context, AttributeSet attrs, int defStyle) {
	    super(context, attrs, defStyle);
	    init(context);
	}
	
	public void init(Context context){
	//	mBmp=BitmapFactory.decodeResource(context.getResources(), R.drawable.hitcircle_arancio_0);      //carichiamo l'immagine in una bitmap
		bitmapWidth=mBmp.getWidth(); //larghezza bitmap
		bitmapHeight=mBmp.getHeight();//altezza   
		
		mRnd = new Random();
	//	mBmp.setWidth(10);
	//	mBmp.setHeight(10);
	//	mPaint=new Paint(); // pennello
	//	mPaint.setColor(Color.CYAN);     
	//	mPaint.setAntiAlias(true); 
	//	mRnd=new Random();
		
		px=mRnd.nextInt(Math.abs(widthView-bitmapWidth));
		py=mRnd.nextInt(Math.abs(heightView-bitmapHeight));
		
		statoColpito=false;
	}


	@Override
	protected void onDraw(Canvas canvas) {
		if(!statoColpito){
			super.onDraw(canvas);
			
			// disegnamo la bitmap
			canvas.drawBitmap( mBmp, (float)px, (float)py, null);
		}
	}


	public void updatePosition(){           
		//posizione random x,y della bitmap all interno della view
		px=mRnd.nextInt(widthView-bitmapWidth);
		py=mRnd.nextInt(heightView-bitmapHeight);
		invalidate();
	}
	public int getBitmapHeight(){
		return bitmapHeight;
	}
	public int getBitmapWidth(){
		return bitmapWidth;
	}

	/*
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		double x=event.getX();
		double y=event.getY();
		
		double yCentroImmagine, xCentroImmagine;
		
		//devo calcolare il centro del rettangolo
		yCentroImmagine=py+(bitmapHeight/2);
		xCentroImmagine=px+(bitmapWidth/2);
		
		double calc = Math.pow(x - xCentroImmagine,2)+Math.pow(y - yCentroImmagine,2);
		double segmento = Math.sqrt(Math.abs( calc ));
		if (segmento<=(bitmapHeight/2)){
		//	Toast.makeText(getContext(), "hai cliccato sul target\ncentro target ("+xCentroImmagine+","+yCentroImmagine+")\nhai cliccato in ("+x+","+y+")", Toast.LENGTH_SHORT).show();
		//	this.updatePosition();
			statoColpito=true;
		}else{
		//	Toast.makeText(getContext(), "hai cliccato FUORI\ncentro target ("+xCentroImmagine+","+yCentroImmagine+")\nhai cliccato in ("+x+","+y+")", Toast.LENGTH_SHORT).show();
		}
		invalidate();
		return true;
	}
*/

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//registriamo le dimensioni della view
		widthView=MeasureSpec.getSize(widthMeasureSpec);
		heightView=MeasureSpec.getSize(heightMeasureSpec);
		setMeasuredDimension(widthView,heightView);
	}
	
	public void setTarget(Target t){
		target=t;
		this.px=t.getX();
		this.py=t.getY();
	}
	
	public void setStatoColpito(){
		statoColpito=true;
	}

	public double gettX() {
		return px;
	}


	public double gettY() {
		return py;
	}

	
	

}