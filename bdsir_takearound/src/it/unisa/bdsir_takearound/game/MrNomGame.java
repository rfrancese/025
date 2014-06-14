package it.unisa.bdsir_takearound.game;

import it.unisa.bdsir_takearound.framework.Screen;
import it.unisa.bdsir_takearound.framework.impl.AndroidGame;

public class MrNomGame extends AndroidGame {
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this); 
    }
}