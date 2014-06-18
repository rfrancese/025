package it.unisa.bdsir_takearound.game;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import it.unisa.bdsir_takearound.framework.Game;
import it.unisa.bdsir_takearound.framework.Graphics;
import it.unisa.bdsir_takearound.framework.Input;
import it.unisa.bdsir_takearound.framework.Input.TouchEvent;
import it.unisa.bdsir_takearound.framework.Input.KeyEvent;
import it.unisa.bdsir_takearound.framework.Screen;

public class GameScreen extends Screen {
	enum GameState {
		Ready,
		Running,
		Paused,
		GameOver
	}

	GameState state = GameState.Ready;
	World world;
	int oldScore = 0;
	String score = "0";

	TargetGenerator tg;
	TimeMachine contatore;

	public GameScreen(Game game) {
		super(game);

		tg = new TargetGenerator(10, Assets.target,game.getGraphics().getWidth(),game.getGraphics().getHeight());

		contatore = new TimeMachine(); 
		world = new World(tg, contatore);
		
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

	private void updateReady(List<TouchEvent> touchEvents) {
		if(touchEvents.size() > 0){
			state = GameState.Running;
			
			contatore.start();
		}
	}

	private void updateRunning(List<TouchEvent> touchEvents, List<Input.KeyEvent> keyEvents, float deltaTime) {
		
		int len = touchEvents.size();
		for(int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP) {
				if(event.x < 64 && event.y < 64) {//se l'utente preme il tasto di pausa
					if(Settings.soundEnabled)
						Assets.click.play(1);

					state = GameState.Paused;
					
					contatore.pausa();
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

	private void updatePaused(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for(int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH_UP) {
				if(event.x > 80 && event.x <= 240) {
					if(event.y > 100 && event.y <= 148) {
						if(Settings.soundEnabled)
							Assets.click.play(1);

						contatore.continueRunning();
						
						state = GameState.Running;
						
						return;
					}                    
					if(event.y > 148 && event.y < 196) {
						if(Settings.soundEnabled)
							Assets.click.play(1);
						game.setScreen(new MainMenuScreen(game));                        
						return;
					}
				}
			}
		}
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
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
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();

		g.drawPixmap(Assets.background, 0, 0);
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

	private void drawWorld(World world) {
		Graphics g = game.getGraphics();

		ArrayList<Target> listaTarget = world.listaTargetDaDisegnare;

		disegnaTarget(listaTarget, g);

	}

	private void disegnaTarget(ArrayList<Target> listaTarget, Graphics g) {
		// TODO Auto-generated method stub
		ArrayList<Target> lista = listaTarget; 

		for(int i=0; i<lista.size(); i++){
			Target tmp = lista.get(i);
			g.drawPixmap(tmp.getSfondo(), tmp.x, tmp.getY());

			int numW = tmp.getImmagineNumero().getWidth()/2;
			int numH = tmp.getImmagineNumero().getHeight()/2;

			g.drawPixmap(tmp.getImmagineNumero(), tmp.x+(tmp.getSfondo().getWidth()/2)-numW, tmp.getY()+(tmp.getSfondo().getHeight()/2)-numH);
		}
	}

	private void drawReadyUI() {
		Graphics g = game.getGraphics();

		g.drawPixmap(Assets.ready, 47, 100);
		g.drawLine(0, 416, 480, 416, Color.BLACK);
	}

	private void drawRunningUI() {
		Graphics g = game.getGraphics();

		g.drawPixmap(Assets.buttons, 0, 0, 64, 128, 64, 64);//tasto pausa
		//      g.drawLine(0, 416, 480, 416, Color.BLACK);
		//      g.drawPixmap(Assets.buttons, 0, 416, 64, 64, 64, 64);
		//      g.drawPixmap(Assets.buttons, 256, 416, 0, 64, 64, 64);
	}

	private void drawPausedUI() {
		Graphics g = game.getGraphics();

		g.drawPixmap(Assets.pause, 80, 100);
		g.drawLine(0, 416, 480, 416, Color.BLACK);
	}

	private void drawGameOverUI() {
		Graphics g = game.getGraphics();

		g.drawPixmap(Assets.gameOver, 62, 100);
		g.drawPixmap(Assets.buttons, 128, 200, 0, 128, 64, 64);
		g.drawLine(0, 416, 480, 416, Color.BLACK);
	}

	public void drawText(Graphics g, String line, int x, int y) {
		int len = line.length();
		for (int i = 0; i < len; i++) {
			char character = line.charAt(i);

			if (character == ' ') {
				x += 20;
				continue;
			}

			int srcX = 0;
			int srcWidth = 0;
			if (character == '.') {
				srcX = 200;
				srcWidth = 10;
			} else {
				srcX = (character - '0') * 20;
				srcWidth = 20;
			}

			g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
			x += srcWidth;
		}
	}

	@Override
	public void pause() {
		if(state == GameState.Running)
			state = GameState.Paused;

		if(world.gameOver) {
			Settings.addScore(world.score);
			Settings.save(game.getFileIO());
		}
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		
	}
}