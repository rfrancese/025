package it.unisa.bdsir_takearound.game;

import it.unisa.bdsir_takearound.framework.Game;
import it.unisa.bdsir_takearound.framework.Graphics;
import it.unisa.bdsir_takearound.framework.Screen;
import it.unisa.bdsir_takearound.framework.Graphics.PixmapFormat;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
    	Graphics g = game.getGraphics();
    	Assets.background = g.newPixmap("background.png", PixmapFormat.RGB565);
    	Assets.backgroundNormal = g.newPixmap("SfondoM2mod.jpg", PixmapFormat.RGB565);
    	Assets.backgroundNormal2 = g.newPixmap("sfondo2mod.jpg", PixmapFormat.RGB565);
    	Assets.backgroundNormal3 = g.newPixmap("sfondo3mod.jpg", PixmapFormat.RGB565);
    	Assets.backgroundRush = g.newPixmap("sfondorush.jpg", PixmapFormat.RGB565);
    	
    	Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
    	Assets.mainMenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB4444);
    	Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.ARGB4444);
    	Assets.help1 = g.newPixmap("help1.png", PixmapFormat.ARGB4444);
    	Assets.help2 = g.newPixmap("help2.png", PixmapFormat.ARGB4444);
    	Assets.help3 = g.newPixmap("help3.png", PixmapFormat.ARGB4444);
    	Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB4444);
    	Assets.ready = g.newPixmap("readymod.png", PixmapFormat.ARGB4444);
    	Assets.pause = g.newPixmap("pausemod.png", PixmapFormat.ARGB4444);
    	Assets.gameOver = g.newPixmap("gameovernewmod.png", PixmapFormat.ARGB4444);
    	Assets.win = g.newPixmap("winnewmod.png", PixmapFormat.ARGB4444);
    	Assets.xbutton = g.newPixmap("xmod.png", PixmapFormat.ARGB4444);



    	Assets.click = game.getAudio().newSound("click.ogg");
    	Assets.eat = game.getAudio().newSound("eat.ogg");
    	Assets.bitten = game.getAudio().newSound("bitten.ogg");
    	
    	Assets.menu = game.getAudio().newMusic("menu.ogg");
    	Assets.audioNormal1 = game.getAudio().newMusic("facile.ogg");
    	Assets.audioNormal2 = game.getAudio().newMusic("medio.ogg");
    	Assets.audioNormal3 = game.getAudio().newMusic("difficile.ogg");
    	Assets.audioRush = game.getAudio().newMusic("rush.ogg");

    	Assets.background = g.newPixmap("sfondo3mod.jpg", PixmapFormat.RGB565);
    	Assets.playnormalbutton = g.newPixmap("playnormalmod.png", PixmapFormat.ARGB4444);
    	Assets.playrushbutton = g.newPixmap("playrushmod.png", PixmapFormat.ARGB4444);


    	//Assets.playbutton = g.newPixmap("play.png", PixmapFormat.ARGB4444);
    	Assets.tutorialbutton = g.newPixmap("tutorialmod.png", PixmapFormat.ARGB4444);
    	Assets.recordbutton = g.newPixmap("recordmod.png", PixmapFormat.ARGB4444);
    	Assets.settingsbutton = g.newPixmap("settingsmod.png", PixmapFormat.ARGB4444);

    	//caricamento immagini numeri
    	Assets.num0 = g.newPixmap("default-0.png", PixmapFormat.ARGB4444);
    	Assets.num1 = g.newPixmap("default-1.png", PixmapFormat.ARGB4444);
    	Assets.num2 = g.newPixmap("default-2.png", PixmapFormat.ARGB4444);
    	Assets.num3 = g.newPixmap("default-3.png", PixmapFormat.ARGB4444);
    	Assets.num4 = g.newPixmap("default-4.png", PixmapFormat.ARGB4444);
    	Assets.num5 = g.newPixmap("default-5.png", PixmapFormat.ARGB4444);
    	Assets.num6 = g.newPixmap("default-6.png", PixmapFormat.ARGB4444);
    	Assets.num7 = g.newPixmap("default-7.png", PixmapFormat.ARGB4444);
    	Assets.num8 = g.newPixmap("default-8.png", PixmapFormat.ARGB4444);
    	Assets.num9 = g.newPixmap("default-9.png", PixmapFormat.ARGB4444);

    	//caricamento immagine target
    	Assets.target = g.newPixmap("hitcircleMod.png", PixmapFormat.ARGB4444);

    	Settings.load(game.getFileIO());
    	game.setScreen(new MainMenuScreen(game));
    }

    @Override
    public void present(float deltaTime) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

	@Override
	public void stopGioco() {
		// TODO Auto-generated method stub
		
	}
}
