package it.unisa.bdsir_takearound.game;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import it.unisa.bdsir_takearound.framework.Game;
import it.unisa.bdsir_takearound.framework.Graphics;
import it.unisa.bdsir_takearound.framework.Input;
import it.unisa.bdsir_takearound.framework.Input.TouchEvent;
import it.unisa.bdsir_takearound.framework.Music;
import it.unisa.bdsir_takearound.framework.impl.AndroidGame;
import it.unisa.bdsir_takearound.game.GameScreen.GameState;
import it.unisa.bdsir_takearound.ui.RegistraPunteggio;


public class GameNormalScreen extends GameScreen {
	
	static final String MOD_NORMAL = "normal";
	static int sceltasfondo;
	private boolean flagVittoria=false;

	public GameNormalScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		tg = new TargetGenerator(Assets.target,game.getGraphics().getWidth(),game.getGraphics().getHeight()-64);

		volumestatus = true;
		contatore = new TimeMachine(); 
		world = new World(tg, contatore, MOD_NORMAL);
		sceltasfondo = (int) (Math.random() *3);
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
		
		if(state == GameState.Victory)
			updateWin(touchEvents);
	}
	

	
	private void updateWin(List<TouchEvent> touchEvents) {

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
				else if(event.x > game.getGraphics().getWidth()-64 && event.y > game.getGraphics().getHeight()-64){
					setVolumestatus();
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
		
		if(world.win)
			state = GameState.Victory;
		
		if(oldScore != world.score) {
			oldScore = world.score;
			score = "" + oldScore;
			if(Settings.soundEnabled)
				Assets.eat.play(1);
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
				}
					if(event.x > 120 && event.x <= 200){
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
		
		switch (sceltasfondo){
		case 0: {
			g.drawPixmap(Assets.backgroundNormal, 0, 0); break;
		}
		case 1:{
			g.drawPixmap(Assets.backgroundNormal2, 0, 0); break;
		}
		case 2:{
			g.drawPixmap(Assets.backgroundNormal3, 0, 0); break;
		}
		default: break;
		}

		//g.drawPixmap(Assets.backgroundNormal, 0, 0);
		drawWorld(world);

		if(state == GameState.Ready) 
			drawReadyUI();
		if(state == GameState.Running)
			drawRunningUI();
		if(state == GameState.Paused)
			drawPausedUI();
		if(state == GameState.GameOver)
			drawGameOverUI();
		if(state == GameState.Victory){
			this.flagVittoria=true;
			drawWinUI();
			}
		drawText(g, score, g.getWidth() / 2 - score.length()*20 / 2, g.getHeight() - 42);
	}
	
	public void drawWinUI() {
		Graphics g = game.getGraphics();

		g.drawPixmap(Assets.win, 70, 30);
		g.drawPixmap(Assets.xbutton, 128, 200);
		
		
		if (this.flagVittoria){
			this.flagVittoria=false;
			Intent intent = new Intent( ((AndroidGame)game).getApplicationContext(), RegistraPunteggio.class);
			Bundle datiPunteggio = new Bundle();
			datiPunteggio.putString("modality", MOD_NORMAL);
			datiPunteggio.putInt("punteggio", world.score);
			intent.putExtras(datiPunteggio);
			((AndroidGame)game).startActivity(intent);
			}
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
