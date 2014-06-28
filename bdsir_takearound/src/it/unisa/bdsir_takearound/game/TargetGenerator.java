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
		int rx,ry, attesa=0;
		
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
			
			if (i%2 == 0) attesa+=1;
			if (i%2 == 1) attesa+=2;
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
