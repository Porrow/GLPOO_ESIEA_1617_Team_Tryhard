package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.tryhard.gl.egghunt.gui.Window;

/**
 * Classe reprsentant un enfant. Cette classe hérite de GraphicObject ce qui lui permet d'être "dessinable"
 * 
 **/
public class Child extends GraphicObject {

	private static final Logger LOGGER = Logger.getLogger(Child.class);
	private static final String orientations = "NESW";
	private static final int dec = Garden.WC / Window.FPS; // Indique le nombre de pixel dont se déplace un enfant à chaque frame
	private static final int nAnimImgs = 8; // Le nombre d'images utilisés pour l'animation d'un déplacement
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
	private boolean isPaused; // Détermine si l'enfant est en pause

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
		this.isPaused = false;
		String pathImg;
		if (name.length() % 2 == 0)
			pathImg = "Kid2.png";
		else
			pathImg = "Kid3.png";
		loadImages(EggHunt.IMGP + pathImg, nAnimImgs * orientations.length(), Garden.WC, Garden.WC); // orientations.length() : Nombre d'orientations
	}

	public boolean getIsMoving() {
		return isMoving;
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
	 * Renvoie true si une collision avec la barrière ou avec un obstacle est détectée
	 */
	public boolean checkCollision(int cx, int cy) {
		GraphicObject[][] array = g.getArray();
		if (cx < 0 || cx >= array[0].length || cy <= 0 || cy >= array.length) // Collision avec la barrière
			return true;
		if (array[cy][cx] instanceof Obstacle)
			return true;
		if (array[cy][cx] instanceof Child) {
			isPaused = true;
			return true;
		}
		array[yc][xc] = null; // On supprime l'enfant de son ancienne position
		array[cy][cx] = this; // On le met sur la nouvelle
		return false;
	}

	/**
	 * Détermine si l'enfant doit se déplacer (renvoie true dans ce cas) et de quelle manière
	 */
	public boolean move() {
		switch (orientation) {
		case 'N':
			if (checkCollision(xc, yc - 1))
				return false;
			yc -= 1;
			break;
		case 'S':
			if (checkCollision(xc, yc + 1))
				return false;
			yc += 1;
			break;
		case 'W':
			if (checkCollision(xc - 1, yc))
				return false;
			xc -= 1;
			break;
		case 'E':
			if (checkCollision(xc + 1, yc))
				return false;
			xc += 1;
			break;
		}
		return true;
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
			if (move()) // S'il n'y a pas eu de collision
				isMoving = true;
			else if (!isPaused)
				while (etape + 1 < instructions.size() && instructions.get(etape + 1) == 'A') // On saute les instructions qui font avancer
					etape++;
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
		int e = orientations.indexOf(orientation) * 8;
		if (timer < Window.FPS && isMoving)
			e += timer * nAnimImgs / Window.FPS;
		g.drawImage(imgs[e], x, y, null);
	}

	/**
	 * Calcule la position et l'animation de l'enfant
	 */
	@Override
	protected void calculate() {
		timer++;
		if (timer >= Window.FPS && etape != instructions.size()) { // Limite l'enfant à une action par seconde et à une seule exécution de ses
																	// instructions
			timer = 0;
			pickupEgg();
			treatInstructions();
			if (etape < instructions.size() && !isPaused)
				etape += 1;
		}
		if (isMoving)
			anim();
		isPaused = false;
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
