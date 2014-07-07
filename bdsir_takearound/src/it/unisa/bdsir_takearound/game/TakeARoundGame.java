package it.unisa.bdsir_takearound.game;

import it.unisa.bdsir_takearound.db.DatabaseHelper;
import it.unisa.bdsir_takearound.framework.Screen;
import it.unisa.bdsir_takearound.framework.impl.AndroidGame;

public class TakeARoundGame extends AndroidGame {
	protected DatabaseHelper databaseHelper;
	
    public TakeARoundGame() {
		super();
		 databaseHelper = new DatabaseHelper(this);
	}

	@Override
    public Screen getStartScreen() {
        return new LoadingScreen(this); 
    }

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		this.screen.pause();
	}
    
    public DatabaseHelper getDatabaseHelper(){
    	return this.databaseHelper;
    }
}