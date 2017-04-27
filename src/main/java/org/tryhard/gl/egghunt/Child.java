package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.tryhard.gl.egghunt.gui.Window;

/**
 * Classe reprsentant un enfant. Cette classe hérite de GraphicObject ce qui lui permet d'être "dessinable"
 * 
 * 
 **/
public class Child extends GraphicObject {

	private static final Logger LOGGER = Logger.getLogger(Child.class);
	private static final String orientations = "NESW";
	private static final int dec = Garden.WC / Window.FPS; // Indique le nombre de pixel dont se déplace un enfant à chaque frame
	private static final int nAnimImgs = 5; // Le nombre d'images utilisés pour l'animation d'un déplacement
	private int xc; // Coordonnée x en case
	private int yc; // Coordonnée y en case
	private ArrayList<Egg> basket = new ArrayList<Egg>(); // Oeufs ramassés
	private ArrayList<Character> instructions;
	private int etape;
	private Character orientation;
	private String name;
	private Garden g;
	private int timer;
	private boolean isMoving; // Détermine si l'enfant est actuellement en mouvement

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
	public Child(int xc, int yc, Character o, ArrayList<Character> inst, String name, Garden g) {
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
		loadImages(EggHunt.IMGP + "Kid1.png", nAnimImgs * orientations.length(), Garden.WC, Garden.WC); // orientations.length() : Nombre d'orientationss
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

	public ArrayList<Character> getInstructions() {
		return instructions;
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
		if (timer == Window.FPS - 1) {
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

	public void pickupEgg() {
		for (GraphicObject d : g.getDescendants()) {
			if (d instanceof Egg && d.x == x && d.y == y) {
				Egg e = (Egg) d;
				if (e.getNb() > 0 && !basket.contains(e)) {
					basket.add(e);
					e.setNb(e.getNb() - 1);
					g.getDescendants().set(g.getDescendants().indexOf(d), e);
					LOGGER.info("oeuf trouvé!");
					instructions.add(etape, 'R');
				}
			}
		}
	}

	public void treatInstructions() {
		int ind;
		switch (instructions.get(etape)) {

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
		default:
			break;
		}
	}

	/**
	 * Dessine la représentation graphique de l'enfant sur l'objet Graphics2D passé en paramètre
	 **/
	@Override
	protected void paint(Graphics2D g) {
		int e = orientations.indexOf(orientation) * 5;
		if (timer < Window.FPS && isMoving)
			e += timer * nAnimImgs / Window.FPS;
		g.drawImage(imgs[e], x, y, null);
	}

	/**
	 * 
	 */
	@Override
	protected void calculate() {
		timer++;
		if (timer >= Window.FPS && etape != instructions.size()) { // Limite l'enfant à une action par seconde et à une seule exécution de ses
																	// instructions
			timer = 0;
			pickupEgg();
			treatInstructions();
			if (etape < instructions.size())
				etape += 1;
		}
		if (isMoving)
			anim();
	}

	public int getEtape() {
		return etape;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public int getTimer() {
		return timer;
	}

	public ArrayList<Egg> getBasket() {
		return basket;
	}

	public String getName() {
		return name;
	}
}
