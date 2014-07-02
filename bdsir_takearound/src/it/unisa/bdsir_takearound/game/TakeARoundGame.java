package it.unisa.bdsir_takearound.game;

import it.unisa.bdsir_takearound.framework.Screen;
import it.unisa.bdsir_takearound.framework.impl.AndroidGame;

public class TakeARoundGame extends AndroidGame {
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this); 
    }

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
		this.screen.pause();
	}
    
    
}