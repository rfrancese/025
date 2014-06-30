package it.unisa.bdsir_takearound.game;

import java.util.List;

import android.content.Intent;
import it.unisa.bdsir_takearound.framework.Game;
import it.unisa.bdsir_takearound.framework.Graphics;
import it.unisa.bdsir_takearound.framework.Input.TouchEvent;
import it.unisa.bdsir_takearound.framework.Screen;
import it.unisa.bdsir_takearound.framework.impl.AndroidGame;
import it.unisa.bdsir_takearound.ui.RecordActivity;
import it.unisa.bdsir_takearound.ui.TutorialActivity;

public class MainMenuScreen extends Screen {
    
    public MainMenuScreen(Game game) {
        super(game);               
    }   

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();       
        
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(inBounds(event, 0, g.getHeight() - 64, 64, 64)) {
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                }
                if(inBounds(event, 25, 50, 90, 25) ) {//play in modalità NORMAL
                    game.setScreen(new GameNormalScreen(game));
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
                if(inBounds(event, 70, 50, 90, 25) ) {//play in modalità RUSH
                    game.setScreen(new GameRushScreen(game));
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
                if(inBounds(event, 25, 100, 90, 25) ) {
                    Intent intent = new Intent((AndroidGame) this.game, TutorialActivity.class);
                    ((AndroidGame) this.game).startActivity(intent);
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
                if(inBounds(event, 25, 150, 90, 25) ) {
                	Intent intent = new Intent((AndroidGame) this.game, RecordActivity.class);
                    ((AndroidGame) this.game).startActivity(intent);
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
            }
        }
    }
    
    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if(event.x > x && event.x < x + width - 1 && 
           event.y > y && event.y < y + height - 1) 
            return true;
        else
            return false;
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.background, 0, 0);
//        g.drawPixmap(Assets.logo, 32, 20);
//        g.drawPixmap(Assets.mainMenu, 64, 220);
//        if(Settings.soundEnabled)
//            g.drawPixmap(Assets.buttons, 0, 416, 0, 0, 64, 64);
//        else
//            g.drawPixmap(Assets.buttons, 0, 416, 64, 0, 64, 64);
        g.drawPixmap(Assets.playbutton, 25, 50);
        g.drawPixmap(Assets.playbutton, 70, 50);
        g.drawPixmap(Assets.tutorialbutton, 25, 100);
        g.drawPixmap(Assets.recordbutton, 25, 150);
    }

    @Override
    public void pause() {        
        Settings.save(game.getFileIO());
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
