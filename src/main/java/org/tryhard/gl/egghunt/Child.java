package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;
import java.util.ArrayList;
/**
 * Classe reprsentant un enfant. Cette classe hérite de GraphicObject ce qui lui permet d'être "dessinable"
 * 
 * 
 * **/
public class Child extends GraphicObject {

	private int xc; // Coordonnée x en case
	private int yc; // Coordonnée y en case
	private ArrayList<Egg> basket = new ArrayList<Egg>(); // Oeufs ramassés

	/**
	 * Constructeur d'un enfant
	 * 
	 * @param xc définit la coordonée X de l'enfant
	 * @param yc définit la coordonée Y de l'enfant
	 * @param g définit le jardin dans lequel se trouve l'enfant
	 */
	public Child(int xc, int yc, Garden g) {
		super(xc * Garden.WC, yc * Garden.WC);
		this.xc = xc;
		this.yc = yc;
	}

	/**
	 * Getter XC
	 * @return xc
	 */
	public int getXC() {
		return xc;
	}

	/**
	 * Getter YC
	 * @return yc
	 */
	public int getYC() {
		return yc;
	}

	/**
	 * renvoie le panier de l'enfant
	 * @return panier de l'enfant
	 */
	public ArrayList<Egg> getEggs() {
		return basket;
	}

	/**
	 * Dessine la représentation graphique de l'enfant sur l'objet Graphics2D passé en paramètre
	 **/
	@Override
	protected void paint(Graphics2D g) 
	{
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 */
	@Override
	protected void calculate() {
		// TODO Auto-generated method stub
		
	}
}
