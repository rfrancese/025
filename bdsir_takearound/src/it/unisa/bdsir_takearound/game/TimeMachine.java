package it.unisa.bdsir_takearound.game;

import java.util.TimerTask;

public class TimeMachine extends Thread{
	private boolean tickSent,running;
	private double contatoreTick;	//tiene conto dei secondi
	
	public TimeMachine(){
		tickSent = true;
		running = true;
		contatoreTick= 0;
	}
	
	
	public void run() {
		while(true){
			
			if(isRunning()){
			
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				contatoreTick += 0.5;//incremento il contatore di 5 decimi di secondo
				synchronized(this){
					tickSent = true;	
				}
			}
		}
	}
	

	private boolean isRunning() {
		// TODO Auto-generated method stub
		return running;
	}


	public synchronized void pausa(){
		running = false;
	}
	
	public synchronized void continueRunning(){
		running = true;
	}
	
	/**
	 * restituisce il tempo trascorso dall'inizio del gioco, ovvero i secondi contati dal contatore
	 * @return tempo giocato
	 */
	public double elapsedTime(){
		return this.contatoreTick;
		
	}
	
	public boolean tick(){
		return this.tickSent;
		
	}

	public synchronized void setTickFalse() {
		this.tickSent=false;
	}
}
