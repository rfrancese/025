package it.unisa.bdsir_takearound.game;

import java.util.List;

import it.unisa.bdsir_takearound.framework.Game;
import it.unisa.bdsir_takearound.framework.Graphics;
import it.unisa.bdsir_takearound.framework.Input;
import it.unisa.bdsir_takearound.framework.Input.TouchEvent;
import it.unisa.bdsir_takearound.framework.Music;
import it.unisa.bdsir_takearound.game.GameScreen.GameState;


public class GameNormalScreen extends GameScreen {
	
	static final String MOD_NORMAL = "normal";
	

	public GameNormalScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		tg = new TargetGenerator(Assets.target,game.getGraphics().getWidth(),game.getGraphics().getHeight()-64);

		contatore = new TimeMachine(); 
		world = new World(tg, contatore, MOD_NORMAL);
		
		int sceltabrano = (int) (Math.random() * 3);
		
		switch (sceltabrano){
		case 0: {
			Assets.audioNormal1 = game.getAudio().newMusic("facile.ogg");
			audio = Assets.audioNormal1; break;
		}
		case 1:{
			Assets.audioNormal2 = game.getAudio().newMusic("medio.ogg");
			audio = Assets.audioNormal2; break;
		}
		case 2:{
			Assets.audioNormal3 = game.getAudio().newMusic("difficile.ogg");
			audio = Assets.audioNormal3; break;
		}
		case 3:{
			Assets.audioRush = game.getAudio().newMusic("rush.ogg"); 
			audio = Assets.audioRush; break;
		}
		default: break;
		}
		audio.setLooping(true);
	}

	@Override
	public void update(float deltaTime) {
		
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		List<it.unisa.bdsir_takearound.framework.Input.KeyEvent> keyEvents = game.getInput().getKeyEvents();

		if(state == GameState.Ready)
			updateReady(touchEvents);

		if(state == GameState.Running)
			updateRunning(touchEvents, keyEvents , deltaTime);

		if(state == GameState.Paused)
			updatePaused(touchEvents);

		if(state == GameState.GameOver)
			updateGameOver(touchEvents);
	}
	

	
	private void updateRunning(List<TouchEvent> touchEvents, List<Input.KeyEvent> keyEvents, float deltaTime) {
		
		
		int len = touchEvents.size();
		for(int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP) {
				if(event.x < 64 && event.y > game.getGraphics().getHeight()-64) {//se l'utente preme il tasto di pausa
					if(Settings.soundEnabled)
						Assets.click.play(1);
					pausaGioco();
					return;
				}
			}
		}
		
		
		
		for(int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			
			if(event.type == TouchEvent.TOUCH_DOWN){
				for (int j=0; j<world.listaTargetDaDisegnare.size(); j++){
					Target tmp = world.listaTargetDaDisegnare.get(j);

					double x=event.x;
					double y=event.y;

					double yCentroImmagine, xCentroImmagine;

					//devo calcolare il centro del rettangolo
					yCentroImmagine=tmp.y+(tmp.getSfondo().getHeight()/2);
					xCentroImmagine=tmp.x+(tmp.getSfondo().getWidth()/2);

					//calcoli per calcolare la circonferenza
					double calc = Math.pow(x - xCentroImmagine,2)+Math.pow(y - yCentroImmagine,2);
					double segmento = Math.sqrt(Math.abs( calc ));

					if (segmento <= (tmp.getSfondo().getHeight()/2)) //il target è stato colpito
						tmp.setCatched(true);
					
				}
			}			
		}
		
		world.update(deltaTime);

		if(world.gameOver) {
			if(Settings.soundEnabled)
				Assets.bitten.play(1);
			state = GameState.GameOver;
		}
		if(oldScore != world.score) {
			oldScore = world.score;
			score = "" + oldScore;
			if(Settings.soundEnabled)
				Assets.eat.play(1);
		}
		
		for (int k=0; k<keyEvents.size(); k++){
			if (keyEvents.get(k).keyCode == android.view.KeyEvent.KEYCODE_BACK || keyEvents.get(k).keyCode == android.view.KeyEvent.KEYCODE_POWER)
				state = GameState.Paused;
		}
	}

	


	protected void updatePaused(List<TouchEvent> touchEvents) {
		
		
		int len = touchEvents.size();
		for(int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP) {
				if(event.x > 80 && event.x <= 240) {
					if(event.y > 100 && event.y <= 148) {
						if(Settings.soundEnabled)
							Assets.click.play(1);

						if (!audio.isPlaying()) audio.play();
						
						contatore.continueRunning();
						
						state = GameState.Running;
						
						return;
					}                    
					if(event.y > 148 && event.y < 196) {//chiude la schermata di gioco e torna al menu
						if(Settings.soundEnabled)
							Assets.click.play(1);
						
						audio.dispose();
						
						game.setScreen(new MainMenuScreen(game));                        
						return;
					}
				}
			}
		}
	}
	
	protected void updateGameOver(List<TouchEvent> touchEvents) {

		if (!audio.isStopped() || audio.isPlaying()){
		//	audio.pause();
			audio.stop();
		//	audio.dispose();
		}
		
		int len = touchEvents.size();
		for(int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP) {
				if(event.x >= 128 && event.x <= 192 &&
						event.y >= 200 && event.y <= 264) {
					if(Settings.soundEnabled)
						Assets.click.play(1);
					
					game.setScreen(new MainMenuScreen(game));
					return;
				}
			}
		}
	}

	@Override
	public void drawText(Graphics g, String line, int x, int y) {
		// TODO Auto-generated method stub
		super.drawText(g, line, x, y);
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();

		g.drawPixmap(Assets.backgroundNormal, 0, 0);
		drawWorld(world);

		if(state == GameState.Ready) 
			drawReadyUI();
		if(state == GameState.Running)
			drawRunningUI();
		if(state == GameState.Paused)
			drawPausedUI();
		if(state == GameState.GameOver)
			drawGameOverUI();

		drawText(g, score, g.getWidth() / 2 - score.length()*20 / 2, g.getHeight() - 42);
	}

	@Override
	public void pause() {
		
		super.pause();
	}

	@Override
	public void resume() {
		
		super.resume();
	}

	@Override
	public void dispose() {
		
		super.dispose();
	}

}
