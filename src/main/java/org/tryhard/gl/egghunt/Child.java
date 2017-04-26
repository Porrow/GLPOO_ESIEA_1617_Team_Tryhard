package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Classe reprsentant un enfant. Cette classe hérite de GraphicObject ce qui lui
 * permet d'être "dessinable"
 * 
 * 
 **/
public class Child extends GraphicObject {

	private int xc; // Coordonnée x en case
	private int yc; // Coordonnée y en case
	private ArrayList<Egg> basket = new ArrayList<Egg>(); // Oeufs ramassés
	private char[] instructions;
	private int etape;
	private Character orientation;
	private String name;
	private Garden g;

	/**
	 * Constructeur d'un enfant
	 * 
	 * @param xc
	 *            définit la coordonée X de l'enfant sur la grille
	 * @param yc
	 *            définit la coordonée Y de l'enfant sur la grille
	 * @param g
	 *            définit le jardin dans lequel se trouve l'enfant
	 * 
	 */
	public Child(int xc, int yc, Character o, char[] inst, String name, Garden g) {
		super(g.x + xc * Garden.WC, g.y + yc * Garden.WC, Garden.WC, Garden.WC);
		this.xc = xc;
		this.yc = yc;
		this.etape = 0;
		this.orientation = o;
		this.instructions = inst;
		this.name = name;
		this.g = g;
		loadImages("res/Kid1_0.png", 1, Garden.WC, Garden.WC);
	}

	/**
	 * Getter XC
	 * 
	 * @return xc
	 */
	public int getXC() {
		return xc;
	}

	/**
	 * Getter YC
	 * 
	 * @return yc
	 */
	public int getYC() {
		return yc;
	}

	/**
	 * renvoie le panier de l'enfant
	 * 
	 * @return panier de l'enfant
	 */
	public ArrayList<Egg> getEggs() {
		return basket;
	}

	

	/**
	 * Dessine la représentation graphique de l'enfant sur l'objet Graphics2D
	 * passé en paramètre
	 **/
	@Override
	protected void paint(Graphics2D g) {
		g.drawImage(imgs[0], x, y, null);
	}

	/**
	 * 
	 */
	@Override
	protected void calculate() {
		// TODO Auto-generated method stub

	}
}
