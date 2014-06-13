package it.unisa.bdsir_takearound.game;

import it.unisa.bdsir_takearound.framework.Pixmap;

public class Target {
	private int x,y;
	private int numero;
	private Pixmap sfondo,num;
	private boolean catched;
	private int attesa;
	
	public Target(Pixmap sfondo, int num){
		this.sfondo = sfondo;
		numero = num;
		catched = false;
		
		setPixmapNumero();
	}
	
	public Target(Pixmap sfondo, int num, int x, int y){
		this.sfondo = sfondo;
		numero = num;
		this.x=x;
		this.y=y;
		catched = false;
		
		setPixmapNumero();
	}
	
	private void setPixmapNumero() {
		// stupidi break!
		switch(numero)
		{
			case 0: setImmagineNumero(Assets.num0); break;
			case 1: setImmagineNumero(Assets.num1); break;
			case 2: this.num = Assets.num2; break;
			case 3: setImmagineNumero(Assets.num3); break;
			case 4: setImmagineNumero(Assets.num4); break;
			case 5: setImmagineNumero(Assets.num5); break;
			case 6: setImmagineNumero(Assets.num6); break;
			case 7: setImmagineNumero(Assets.num7); break;
			case 8: setImmagineNumero(Assets.num8); break;
			case 9: setImmagineNumero(Assets.num9); break;
			default: break;
		}
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Pixmap getSfondo() {
		return sfondo;
	}
	public void setSfondo(Pixmap sfondo) {
		this.sfondo = sfondo;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Pixmap getImmagineNumero() {
		return num;
	}

	public void setImmagineNumero(Pixmap num) {
		this.num = num;
	}

	public boolean isCatched() {
		return catched;
	}

	public void setCatched(boolean catched) {
		this.catched = catched;
	}

	/**
	 * @return the attesa
	 */
	public int getAttesa() {
		return attesa;
	}

	/**
	 * @param attesa the attesa to set
	 */
	public void setAttesa(int attesa) {
		this.attesa = attesa;
	}

}
