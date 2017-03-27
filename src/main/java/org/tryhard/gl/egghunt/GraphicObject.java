package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class GraphicObject {

	private int x; // Coordonnée x relative
	private int y; // Coordonnée y relative
	private GraphicObject parent; // L'objet graphique qui le contient
	private ArrayList<GraphicObject> children = new ArrayList<GraphicObject>(); // Les objets graphiques contenues

	// Dessine l'objet
	public void paint(Graphics2D g) {
		
	}
}
