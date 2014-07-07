package it.unisa.bdsir_takearound.game;

import java.util.ArrayList;
import java.util.Random;

import it.unisa.bdsir_takearound.framework.Pixmap;

public class TargetGenerator {
	private ArrayList<Target> listaTarget = new ArrayList<Target>();
	private int numeroTarget, surfaceWidth, surfaceHeight;

	private Pixmap sfondo;
	private Random mRnd;
	
	public TargetGenerator(int num, Pixmap sfondo){
		mRnd=new Random();
		
		numeroTarget = num;
		this.sfondo = sfondo;
		
	//	generateTargets();
		
	}
	
	public TargetGenerator(Pixmap sfondo, int surfaceWidth, int surfaceHeight){
		mRnd=new Random();
		
		this.surfaceHeight = surfaceHeight;
		this.surfaceWidth = surfaceWidth;
		
		this.sfondo = sfondo;
		
	//	generateTargets();
	}
	
	public void setNumeroTargets(int numTarget){
		this.numeroTarget = numTarget;
	}
	
	/**
	 * genera target con coordinate random
	 */
	public void generateTargets(){
		int rx,ry;
		double attesa = 0;
		
		rx=mRnd.nextInt(surfaceWidth-sfondo.getWidth());
		ry=mRnd.nextInt(surfaceHeight-sfondo.getHeight());
		Target first = new Target(sfondo, 0, rx, ry);
		first.setAttesa(0);
		
		listaTarget.add(first); 
		
		for(int i=1; i<numeroTarget; i++){
			//posizione random x,y della bitmap all interno della view
			rx=mRnd.nextInt(surfaceWidth-sfondo.getWidth());
			ry=mRnd.nextInt(surfaceHeight-sfondo.getHeight());
			
			Target t = new Target(sfondo,i%10, rx, ry);
			if(i>=0 && i <8)
				attesa += 1;
			else if(i>7 && i <16)
				attesa += 0.8;
			else if(i>15 && i<24)
				attesa += 0.6;
			else if(i>23 && i<33)
				attesa += 0.5;
			else if(i>32 && i<44)
				attesa += 0.4;
			else if(i>43)
				attesa += 0.3;

			t.setAttesa(attesa);
			
			listaTarget.add(t);
			
		}
	}
	
	/**
	 * restituisce la lista di target creati in modo random
	 * @return la lista di target creati in modo random
	 */
	public ArrayList<Target> getTargets(){
		return listaTarget;
	}
	
	public int getSurfaceWidth() {
		return surfaceWidth;
	}

	public int getSurfaceHeight() {
		return surfaceHeight;
	}

}
