package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Classe de base pour les objets dessinables
 * 
 * @author menuiserie
 *
 */
public abstract class GraphicObject {

	protected int x; // Coordonnée x relative en pixels
	protected int y; // Coordonnée y relative en pixels
	protected int w; // Largeur en pixels
	protected int h; // Hauteur en pixels
	protected BufferedImage img; //Image associée
	private ArrayList<GraphicObject> descendants = new ArrayList<GraphicObject>(); // Les objets graphiques contenues, descendants

	// private BufferedImage img;

	/**
	 * Constructeur
	 * 
	 * @param x
	 *            coordonnée en x
	 * @param y
	 *            coordonnée en y
	 */
	protected GraphicObject(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public ArrayList<GraphicObject> getDescendants(){
		return descendants;
	}

	/**
	 * Dessine l'objet et ses enfants
	 * 
	 * @param g
	 *            Context graphique dans lequel l'objet sera déssiné.
	 */
	public final void paintAll(Graphics2D g) {
		paint(g);
		for (GraphicObject descendant : descendants)
			descendant.paintAll(g);
	}
	
	protected void addDescendant(GraphicObject g){
		descendants.add(g);
	}
	
	public final ArrayList<GraphicObject> getDescendants(){
		return descendants;
	}

	/**
	 * Dessine l'objet
	 * 
	 * @param g
	 *            Context graphique dans lequel l'objet sera déssiné.
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
	 * Calcule l'objet
	 */
	protected abstract void calculate();
}
