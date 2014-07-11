package it.unisa.bdsir_takearound.game;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import it.unisa.bdsir_takearound.framework.Game;
import it.unisa.bdsir_takearound.framework.Graphics;
import it.unisa.bdsir_takearound.framework.Music;
import it.unisa.bdsir_takearound.framework.Input.TouchEvent;
import it.unisa.bdsir_takearound.framework.Screen;
import it.unisa.bdsir_takearound.framework.impl.AndroidGame;

public abstract class GameScreen extends Screen {

	protected Music audio;
	
	public GameScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		
	}

	enum GameState {
		Ready,
		Running,
		Paused,
		GameOver,
		Victory
	}

	GameState state = GameState.Ready;
	World world;
	int oldScore = 0;
	String score = "0";
	String modality;
	String contoAllaRovescia = "30";

	TargetGenerator tg;
	TimeMachine contatore;
	boolean volumestatus;

	double cotrolloTouchUtente = 0;


	@Override
	public void update(float deltaTime) {
		
	}

	protected void updateReady(List<TouchEvent> touchEvents) {
		if(touchEvents.size() > 0){
			state = GameState.Running;
			
			audio.play();
			contatore.start();
		}
	}
	
	protected void updateRunning(List<TouchEvent> touchEvents){
		
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

						contatore.continueRunning();
						
						state = GameState.Running;
						
						return;
					}    
				}
				if(event.x > 120 && event.x <= 200){
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

	protected void updateGameOver(List<TouchEvent> touchEvents) {
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
	
	
	protected void pausaGioco() {
		if (audio.isPlaying()) audio.pause();
		state = GameState.Paused;					
		
		contatore.pausa();
		
	}


	@Override
	public void present(float deltaTime) {}

	
	protected void drawWorld(World world) {
		Graphics g = game.getGraphics();

		disegnaTarget(world.listaTargetDaDisegnare, g);

	}

	protected void disegnaTarget(ArrayList<Target> listaTarget, Graphics g) {
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

	protected void drawReadyUI() {
		Graphics g = game.getGraphics();

		g.drawPixmap(Assets.ready, 50, 70);
		
	}

	public void drawRunningUI() {
		Graphics g = game.getGraphics();

		g.drawPixmap(Assets.pausebutton, 0, g.getHeight()-64);//tasto pausa
		
		if(volumestatus == true)
			g.drawPixmap(Assets.volumeon, g.getWidth()-64, g.getHeight()-64);
		else
			g.drawPixmap(Assets.volumeoff, g.getWidth()-64, g.getHeight()-64);
		
	}

	public void drawPausedUI() {
		Graphics g = game.getGraphics();

		g.drawPixmap(Assets.pause, 80, 100);
		
	}

	public void drawGameOverUI() {
		Graphics g = game.getGraphics();

		g.drawPixmap(Assets.gameOver, 20, 30);
		g.drawPixmap(Assets.xbutton, 150, 200);
	}
	public void drawWinUI() {}

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
		pausaGioco();
		
	}

	@Override
	public void resume() {
		
		
	}

	@Override
	public void dispose() {
	//	pausaGioco();
	}
	
	public void stopGioco(){
		audio.dispose();
		
		game.setScreen(new MainMenuScreen(game));
	}

	public void setVolumestatus() {
		if(volumestatus==true){
			volumestatus = false;
			audio.setVolume(0);
		}
		
		else {
			volumestatus = true;
			audio.setVolume(1);
		}
	}

}


	


