package it.unisa.bdsir_takearound.game;

import java.util.ArrayList;
import java.util.Random;

public class World {
    static final int SCORE_INCREMENT = 10;
    static final float TICK_INITIAL = 0.5f;
    static final float TICK_DECREMENT = 0.05f;
    static final String MOD_NORMAL = "normal"; 
    static final String MOD_RUSH = "rush";
    static int colpitoAttuale = 999;
     
    public boolean gameOver = false;

    public int score = 0;
    public int countDown = 0;
    
    Random random = new Random();
    float tickTime = 0;
    static float tick = TICK_INITIAL;
    
    //variabili istanza per gestione target
	private ArrayList<Target> listaTutti;
	public ArrayList<Target> listaTargetDaDisegnare;
	private TargetGenerator tg;
	
	//variabili livello - per il momento uno solo
	private int durataSecondiTarget = 2;
//	private int numTarget = 30;
	private String modality;
	
	private TimeMachine contatore;

	
	public World(TargetGenerator tg, TimeMachine cont, String mod){
		listaTargetDaDisegnare=new ArrayList<Target>();
		listaTutti = new ArrayList<Target>();
		
		this.tg = tg;
		
		this.setModality(mod);
		contatore= cont;
	}
	
	public void setModality(String mod){
		if (mod.equals(MOD_NORMAL)){
			this.modality = MOD_NORMAL;
			tg.setNumeroTargets(20);
			tg.generateTargets();
			listaTutti = this.tg.getTargets();
		}
		if (mod.equals(MOD_RUSH)){
			this.modality=MOD_RUSH;
			tg.setNumeroTargets(1);
			tg.generateTargets();
			listaTutti = this.tg.getTargets();
		//	this.listaTutti.get(0).setAttesa(21);
		}
	}
	
	
/*
 * The update() method is responsible for updating the World and all the objects in it
 * based on the delta time we pass to it. This method will be called each frame in the game
 * screen so that the World is updated constantly	
 */
    public void update(float deltaTime) {
    	if (modality.equals(MOD_NORMAL)){
    		updateNormal(deltaTime);
    	}
    	if (modality.equals(MOD_RUSH)){
    		updateRush(deltaTime);
    	}
    }


    private void updateRush(float deltaTime) {
    	if (gameOver)
    		return;
    	
    	this.listaTargetDaDisegnare = this.listaTutti;
    	
    	for (int i=0; i<this.listaTargetDaDisegnare.size(); i++){

    		//invia notifica a tutti i target 

    		if (this.listaTargetDaDisegnare.get(i).isCatched()){

    			this.score+=SCORE_INCREMENT; //incrementa il punteggio

    			updatePosition(this.listaTargetDaDisegnare.get(i));
    			
    			this.listaTargetDaDisegnare.get(i).setCatched(false);
    		}
    	}
    	
    	if(this.contatore.elapsedTime() >= 20) //se sono passati 20 secondi
    		gameOver =true;
    	
    	double tmp = 20 - contatore.elapsedTime();
    	if (tmp%1 == 0)
    		countDown = (int) tmp;
    }


	private void updatePosition(Target target) {
		int px=random.nextInt(this.tg.getSurfaceWidth()-target.getSfondo().getWidth());
		int py=random.nextInt(this.tg.getSurfaceHeight()-target.getSfondo().getHeight());
		target.setX(px);
		target.setY(py);
	}


	private void updateNormal(float deltatime) {

        if (gameOver)
            return;
        
        //questo codice sarà eseguito solo se lo stato è RUNNING
        
        //controlla se i target presenti nell'area di gioco sono stati colpiti
        for (int i=0; i<this.listaTargetDaDisegnare.size(); i++){
        	
        	//invia notifica a tutti i target 
        	
        	if (this.listaTargetDaDisegnare.get(i).isCatched()){
        		
        		if (checkCorrettaNumerazione(listaTargetDaDisegnare.get(i))){
        		
	        		this.score+=SCORE_INCREMENT; //incrementa il punteggio
        		}
        		else
        			this.score-=SCORE_INCREMENT;
	        	
        		this.listaTargetDaDisegnare.remove(i);//rimuove il target che è stato colpito
	        	i--;
        		
        	}
        	//altrimenti controlla quali target devono essere eliminati dal gioco perchè è scaduto il loro tempo e non sono stati colpiti
        	else if (contatore.elapsedTime() >= (this.listaTargetDaDisegnare.get(i).getAttesa()+this.durataSecondiTarget)){
        		//se la durata del target è terminata, il target va eliminato e il punteggio decrementato
        		
        		this.score-=SCORE_INCREMENT;
        		
        		this.listaTargetDaDisegnare.remove(i);
        		i--;
        		
        		if (this.score < 0){
        			this.gameOver = true;
        		}
        	}
        }
        
      //aggiunge i target da disegnare non appena è trascorso il tempo di attesa per ogni target
        for (int j=0; j<this.listaTutti.size(); j++){
        	if ((contatore.elapsedTime()) >= listaTutti.get(j).getAttesa()){
        		this.listaTargetDaDisegnare.add(listaTutti.get(j));
        		listaTutti.remove(j); //-> rimuove il target che è stato aggiunto alla lista di target da disegnare
        	}
        }
        
        tickTime += deltatime;
   
	}

	private boolean checkCorrettaNumerazione(Target targ) {
		int colpitoPrecedente = colpitoAttuale;
		colpitoAttuale = targ.getNumero();
		
		if (targ.getNumero()==0){
			if (colpitoPrecedente==999)
				return true;
			else if (colpitoPrecedente!=999){
				if (colpitoPrecedente==9)
					return true;
				else
					return false;
			}
		}
		
		switch(targ.getNumero())
		{
			
			case 1: if (colpitoPrecedente==0) return true;
			case 2: if (colpitoPrecedente==1) return true;
			case 3: if (colpitoPrecedente==2) return true;
			case 4: if (colpitoPrecedente==3) return true;
			case 5: if (colpitoPrecedente==4) return true;
			case 6: if (colpitoPrecedente==5) return true;
			case 7: if (colpitoPrecedente==6) return true;
			case 8: if (colpitoPrecedente==7) return true;
			case 9: if (colpitoPrecedente==8) return true;
			default: return false;
		}
		
	}

}
