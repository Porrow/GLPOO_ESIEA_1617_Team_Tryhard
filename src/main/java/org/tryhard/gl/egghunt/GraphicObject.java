package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class GraphicObject {

	protected int x; // Coordonnée x relative en pixels
	protected int y; // Coordonnée y relative en pixels
	private GraphicObject parent; // L'objet graphique qui le contient
	private ArrayList<GraphicObject> children = new ArrayList<GraphicObject>(); // Les objets graphiques contenues
	// private BufferedImage img;

	protected GraphicObject(int x, int y, GraphicObject parent) {
		this.x = x;
		this.y = y;
		this.parent = parent;
	}

	// Dessine l'objet
	public void paint(Graphics2D g) {
		for (GraphicObject child : children)
			child.paint(g);
	}
}
