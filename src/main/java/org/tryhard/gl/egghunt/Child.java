package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;
import java.util.ArrayList;

import org.tryhard.gl.egghunt.gui.Window;

/**
 * Classe reprsentant un enfant. Cette classe hérite de GraphicObject ce qui lui permet d'être "dessinable"
 * 
 * 
 **/
public class Child extends GraphicObject {

	private String orientations = "NESW";
	private int xc; // Coordonnée x en case
	private int yc; // Coordonnée y en case
	private ArrayList<Egg> basket = new ArrayList<Egg>(); // Oeufs ramassés
	private char[] instructions;
	private int etape;
	private Character orientation;
	private String name;
	private Garden g;
	private int timer;
	private boolean isMoving;
	private final int dec = Garden.WC / Window.FPS;

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
		this.timer = 0;
		this.isMoving = false;
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

	public void move() {
		switch (orientation) {
		case 'N':
			yc -= 1;
			break;
		case 'S':
			yc += 1;
			break;
		case 'W':
			xc -= 1;
			break;
		case 'E':
			xc += 1;
			break;
		}
		isMoving = true;
	}

	public void anim() {
		switch (orientation) {
		case 'N':
			y -= dec;
			break;
		case 'S':
			y += dec;
			break;
		case 'W':
			x -= dec;
			break;
		case 'E':
			x += dec;
			break;
		}
		if(timer == Window.FPS -1){
			x = g.x + xc * Garden.WC;
			y = g.y + yc * Garden.WC;
			isMoving = false;
		}
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
	 * Dessine la représentation graphique de l'enfant sur l'objet Graphics2D passé en paramètre
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
		timer += 1;
		if (timer >= Window.FPS && etape != instructions.length) { // Limite l'enfant à une action par seconde et à une seule exécution de ses
																	// instructions
			timer = 0;
			int ind;
			switch (instructions[etape]) {
			case 'A':
				move();
				break;
			case 'D':
				ind = orientations.indexOf(orientation) + 1;
				if (ind < orientations.length())
					orientation = orientations.charAt(ind);
				else
					orientation = orientations.charAt(0);
				break;
			case 'G':
				ind = orientations.indexOf(orientation) - 1;
				if (ind >= 0)
					orientation = orientations.charAt(ind);
				else
					orientation = orientations.charAt(orientations.length() - 1);
				break;
			}
			if (etape < instructions.length)
				etape += 1;
		}
		if (isMoving)
			anim();
	}
}
