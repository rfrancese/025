package it.unisa.bdsir_takearound.entities;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.shapes.OvalShape;



public class Cerchio {
	private final static double	size=100; //ampiezza del cerchio e dell'immagine del target
	@SuppressWarnings("unused")
	private double 				x,y; // coordinata sinistra in alto del quadrato che contiene il cerchio
	private OvalShape 			cerchio;
	private double 				raggioEsterno;
	private double 				raggioInterno;
	private Point  				centro;
	
	/**
	 * crea un cerchio 
	 * @param x coordinata x del punto in alto a sinistra del quadrato che contiene il cerchio
	 * @param y coordinata y del punto in alto a sinistra del quadrato che contiene il cerchio
	 */
	public Cerchio(float x, float y){
		this.x=x;
		this.y=y;
		
		cerchio = new OvalShape();
		cerchio.resize(x, y);
		
		raggioEsterno = cerchio.getWidth()/2;
		raggioInterno = cerchio.getWidth()/4;
		
		//setto il centro del cerchio in un oggetto Point
		centro = new Point();
	//	centro.setLocation(cerchio.r.getCenterX(), cerchio.getCenterY());
		
	}
	
	public Cerchio(double x, double y, double size){
		this.x=x;
		this.y=y;
		
	//	this.size = size;
		
		cerchio = new OvalShape();
		
		raggioEsterno = cerchio.getWidth()/2;
		raggioInterno = cerchio.getWidth()/4;
		
		//setto il centro del cerchio in un oggetto Point
		centro = new Point();
	//	centro.setLocation(cerchio.getCenterX(), cerchio.getCenterY());
		
	}
	
	/**
	 * disegna il cerchio sul pannello
	 * @param g2
	 */
/*	public void draw(Graphics2D g2){
		g2.setColor(Color.BLACK);
		g2.fill(cerchio);
	}
	*/
	
	
	/**
	 * restituisce il centro del cerchio incapsulato in un oggetto Point
	 * @return centro del cerchio in un oggetto Point
	 */
	public Point getCentro(){
		return this.centro;
	}

	public double getRaggioEsterno() {
		return raggioEsterno;
	}

	public double getRaggioInterno() {
		return raggioInterno;
	}
	
}
