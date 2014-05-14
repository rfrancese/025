package it.unisa.bdsir_takearound;

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
import android.widget.Toast;


public class CustomView extends View {

	Bitmap mBmp;
	Random mRnd;
	Paint mPaint;
	int w,h,bw,bh;
	int px=-1,py=-1; //coordinate immagine

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		double x=event.getX();
		double y=event.getY();
		
		double yCentroImmagine, xCentroImmagine;
		
		//devo calcolare il centro del rettangolo
		yCentroImmagine=py+(bh/2);
		xCentroImmagine=px+(bh/2);
		
		double calc = Math.pow(x - xCentroImmagine,2)+Math.pow(y - yCentroImmagine,2);
		double segmento = Math.sqrt(Math.abs( calc ));
		if (segmento<=(bh/2)){
			Toast.makeText(getContext(), "hai cliccato sul target\ncentro target ("+xCentroImmagine+","+yCentroImmagine+")\nhai cliccato in ("+x+","+y+")", Toast.LENGTH_SHORT).show();
			this.updatePosition();
		}else{
			Toast.makeText(getContext(), "hai cliccato FUORI\ncentro target ("+xCentroImmagine+","+yCentroImmagine+")\nhai cliccato in ("+x+","+y+")", Toast.LENGTH_SHORT).show();
		}
		
		return true;
	}



	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);

		mBmp=BitmapFactory.decodeResource(context.getResources(), R.drawable.hitcircle_arancio_0);      //carichiamo l'immagine in una bitmap
		bw=mBmp.getWidth(); //larghezza bitmap
		bh=mBmp.getHeight();//altezza   
		
	//	mBmp.setWidth(10);
	//	mBmp.setHeight(10);
		mPaint=new Paint(); // pennello
		mPaint.setColor(Color.CYAN);     
		mPaint.setAntiAlias(true); 
		mRnd=new Random();

	}


	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		if(px==-1&&py==-1){ // se non abbiamo ancora settato le coordinate, posizioniamo la bmp al centro
			px=w/2-bw/2; //metà della larghezza view, meno metà della figura
			py=h/2-bh/2; //metà dell'altezza view, meno metà della figura
		}


		canvas.drawCircle(px+(bw/2), py+(bh/2), bh, mPaint); //disegnamo un cerchio con centro al centro della bitmap
		canvas.drawBitmap( // disegnamo la bitmap
				mBmp,
				px,
				py,
				null);


	}


	public void updatePosition(){           
		//posizione random x,y della bitmap all interno della view
		px=mRnd.nextInt(w-bw);
		py=mRnd.nextInt(h-bh);
		invalidate();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//registriamo le dimensioni della view
		w=MeasureSpec.getSize(widthMeasureSpec);
		h=MeasureSpec.getSize(heightMeasureSpec);
		setMeasuredDimension(w,h);
	}


}