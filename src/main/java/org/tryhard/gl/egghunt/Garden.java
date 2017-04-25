package org.tryhard.gl.egghunt;

import java.awt.Color;
import java.awt.Graphics2D;

import org.tryhard.gl.egghunt.gui.Window;

/**
 * Classe représentant un jardin. Cette classe hérite de GraphicObject ce qui
 * lui permet d'être "dessinable"
 * 
 * @author menuiserie
 *
 */
public class Garden extends GraphicObject {
	private int largeur;
	private int hauteur;

	private GraphicObject[][] tableau;

	public static final int WC = 50; // Taille d'une case en pixel

	/**
	 * constructeur de jardin
	 * 
	 * @param csv_child
	 *            chemin du fichier décrivant les enfants
	 */
	public Garden(String csv_child) {
		super(0, 0, Window.WIDTH, Window.HEIGHT);
	}

	/**
	 * Constructeur du jardin
	 * 
	 * @param largeur
	 *            nombre de colonnes du tableau
	 * @param hauteur
	 *            nombre de lignes du tableau
	 */

	public Garden(int largeur, int hauteur) {
		super(0, 0, Window.WIDTH, Window.HEIGHT);
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.tableau = new GraphicObject[largeur][hauteur];

	}

	public void addRocks(int xc, int yc) {
		Obstacle r = new Obstacle(xc, yc);
		tableau[xc][yc] = r;
		addDescendant(r);
	}

	public void addEgg(int xc, int yc) {
		Egg e = new Egg(xc, yc);
		tableau[xc][yc] = e;
		addDescendant(e);
	}

	public void addChild(Child c){
		tableau[c.getXC()][c.getYC()] = c;
		addDescendant(c);
	}
	
	@Override
	protected void paint(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, largeur * WC, hauteur * WC);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, largeur * WC, hauteur * WC);
		for (int i = 0; i < largeur; i++) {
			for (int j = 0; j < hauteur; j++) {

				g.setColor(Color.BLACK);
				g.drawLine(i * WC, 0, i * WC, (j + 1) * WC);
				g.drawLine(0, j * WC, (i + 1) * WC, j * WC);
			}
		}
	}

	@Override
	protected void calculate() {

	}
}
