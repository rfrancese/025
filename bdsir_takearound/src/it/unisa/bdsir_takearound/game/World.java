package it.unisa.bdsir_takearound.game;

import java.util.ArrayList;
import java.util.Random;

public class World {
    static final int SCORE_INCREMENT = 10;
    static final float TICK_INITIAL = 0.5f;
    static final float TICK_DECREMENT = 0.05f;
    
    public boolean gameOver = false;

    public int score = 0;
    
    Random random = new Random();
    float tickTime = 0;
    static float tick = TICK_INITIAL;
    
    //variabili istanza per gestione target
	private ArrayList<Target> listaTutti;
	public ArrayList<Target> listaTargetDaDisegnare;
	private TargetGenerator tg;
	
	//variabili livello - per il momento uno solo
	private int durataSecondiTarget = 2;
	private int numTarget = 30;
	
	private TimeMachine contatore;

	
	public World(TargetGenerator tg, TimeMachine cont){
		listaTargetDaDisegnare=new ArrayList<Target>();
		listaTutti = new ArrayList<Target>();
		
		this.tg = tg;
		listaTutti = this.tg.getTargets();
		
		contatore= cont;
	}
	
	
/*
 * The update() method is responsible for updating the World and all the objects in it
 * based on the delta time we pass to it. This method will be called each frame in the game
 * screen so that the World is updated constantly	
 */
    public void update(float deltaTime) {
        if (gameOver)
            return;

        
        //questo codice sarà eseguito solo se lo stato è RUNNING
        
        //controlla se i target presenti nell'area di gioco sono stati colpiti
        for (int i=0; i<this.listaTargetDaDisegnare.size(); i++){
        	
        	//invia notifica a tutti i target 
        	
        	if (this.listaTargetDaDisegnare.get(i).isCatched()){
        		
        		this.score+=SCORE_INCREMENT; //incrementa il punteggio
        		
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
        
        tickTime += deltaTime;
   
        
    }

}
