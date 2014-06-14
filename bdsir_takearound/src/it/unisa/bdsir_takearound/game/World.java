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
	
	private long start;
			
	
	public World(TargetGenerator tg){
		listaTargetDaDisegnare=new ArrayList<Target>();
		listaTutti = new ArrayList<Target>();
		
		this.tg = tg;
		listaTutti = tg.getTargets();
		
		start = System.currentTimeMillis();
	}
	
	
/*
 * The update() method is responsible for updating the World and all the objects in it
 * based on the delta time we pass to it. This method will be called each frame in the game
 * screen so that the World is updated constantly	
 */
    public void update(float deltaTime) {
        if (gameOver)
            return;
        
        long now = System.currentTimeMillis();
        
        //controlla se i target presenti nell'area di gioco sono stati colpiti
        for (int i=0; i<this.listaTargetDaDisegnare.size(); i++){
        	if (this.listaTargetDaDisegnare.get(i).isCatched()){
        		
        		this.score+=SCORE_INCREMENT; //incrementa il punteggio
        		
        		this.listaTargetDaDisegnare.remove(i);//rimuove il target che è stato colpito
        		i--;
        	}
        	//altrimenti controlla quali target devono essere eliminati dal gioco perchè è scaduto il loro tempo e non sono stati colpiti
        	else if ((now-start) >= (this.listaTargetDaDisegnare.get(i).getAttesa()+this.durataSecondiTarget*1000)){
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
        	if ((now-start) >= listaTutti.get(j).getAttesa()){
        		this.listaTargetDaDisegnare.add(listaTutti.get(j));
        	//	listaTutti.remove(j); //-> rimuove il target che è stato aggiunto alla lista di target da disegnare
        	}
        }
        
        tickTime += deltaTime;

/*        while (tickTime > tick) {
            tickTime -= tick;	}*/
        

       
        
    }
	
	
	
	
	
	
	
	
	
/*    static final int WORLD_WIDTH = 10;
    static final int WORLD_HEIGHT = 13;
    static final int SCORE_INCREMENT = 10;
    static final float TICK_INITIAL = 0.5f;
    static final float TICK_DECREMENT = 0.05f;

    public Snake snake;
    public Stain stain;
    
    public boolean gameOver = false;
    public int score = 0;

    boolean fields[][] = new boolean[WORLD_WIDTH][WORLD_HEIGHT];
    
    Random random = new Random();
    float tickTime = 0;
    static float tick = TICK_INITIAL;
    
    public World() {
        snake = new Snake();
        placeStain();
        
    }
    
    private void placeStain() {
        for (int x = 0; x < WORLD_WIDTH; x++) {
            for (int y = 0; y < WORLD_HEIGHT; y++) {
                fields[x][y] = false;
            }
        }

        int len = snake.parts.size();
        for (int i = 0; i < len; i++) {
            SnakePart part = snake.parts.get(i);
            fields[part.x][part.y] = true;
        }

        int stainX = random.nextInt(WORLD_WIDTH);
        int stainY = random.nextInt(WORLD_HEIGHT);
        while (true) {
            if (fields[stainX][stainY] == false)
                break;
            stainX += 1;
            if (stainX >= WORLD_WIDTH) {
                stainX = 0;
                stainY += 1;
                if (stainY >= WORLD_HEIGHT) {
                    stainY = 0;
                }
            }
        }
        stain = new Stain(stainX, stainY, random.nextInt(3));
    }

    public void update(float deltaTime) {
        if (gameOver)
            return;

        tickTime += deltaTime;

        while (tickTime > tick) {
            tickTime -= tick;
            
            snake.advance();
            
            if (snake.checkBitten()) {
                gameOver = true;
                return;
            }

            SnakePart head = snake.parts.get(0);
            if (head.x == stain.x && head.y == stain.y) {
                score += SCORE_INCREMENT;
                
                snake.eat();
                
                if (snake.parts.size() == WORLD_WIDTH * WORLD_HEIGHT) {
                    gameOver = true;
                    return;
                } else {
                    placeStain();
                }

                if (score % 100 == 0 && tick - TICK_DECREMENT > 0) {
                    tick -= TICK_DECREMENT;
                }
            }
        }
    }

*/	
}
