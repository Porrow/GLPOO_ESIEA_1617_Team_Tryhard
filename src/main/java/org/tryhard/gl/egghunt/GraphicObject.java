package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Classe de base pour les objets dessinables
 * @author menuiserie
 *
 */
public abstract class GraphicObject {

	protected int x; // Coordonnée x relative en pixels
	protected int y; // Coordonnée y relative en pixels
	private ArrayList<GraphicObject> descendants = new ArrayList<GraphicObject>(); // Les objets graphiques contenues, descendants
	// private BufferedImage img;

	/**
	 * Constructeur
	 * @param x coordonnée en x
	 * @param y coordonnée en y
	 */
	protected GraphicObject(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Dessine l'objet et ses enfants
	 * @param g Context graphique dans lequel l'objet sera déssiné.
	 */
	public final void paintAll(Graphics2D g) {
		paint(g);
		for (GraphicObject descendant : descendants)
			descendant.paintAll(g);
	}

	/**
	 * Dessine l'objet
	 * @param g Context graphique dans lequel l'objet sera déssiné.
	 */
	protected abstract void paint(Graphics2D g);

	/**
	 * Calcule l'objet et ses enfants récursivement
	 */
	public final void calculateAll() {
		calculate();
		for (GraphicObject descendant : descendants)
			descendant.calculateAll();
	}

	/**
	 *  Calcule l'objet
	 */
	protected abstract void calculate();
}
