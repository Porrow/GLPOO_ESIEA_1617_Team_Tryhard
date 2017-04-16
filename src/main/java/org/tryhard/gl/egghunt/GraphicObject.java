package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class GraphicObject {

	protected int x; // Coordonnée x relative en pixels
	protected int y; // Coordonnée y relative en pixels
	private ArrayList<GraphicObject> descendants = new ArrayList<GraphicObject>(); // Les objets graphiques contenues, descendants
	// private BufferedImage img;

	protected GraphicObject(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// Dessine l'objet et ses enfants
	public final void paintAll(Graphics2D g) {
		paint(g);
		for (GraphicObject descendant : descendants)
			descendant.paintAll(g);
	}

	// Dessine l'objet
	protected abstract void paint(Graphics2D g);

	// Calcule l'objet et ses enfants
	public final void calculateAll() {
		calculate();
		for (GraphicObject descendant : descendants)
			descendant.calculateAll();
	}

	// Calcule l'objet
	protected abstract void calculate();
}
