package org.tryhard.gl.egghunt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import org.tryhard.gl.egghunt.gui.Window;

/**
 * Classe représentant un jardin. Cette classe hérite de GraphicObject ce qui lui permet d'être "dessinable"
 * 
 */
public class Garden extends GraphicObject {
	private int xc;
	private int yc;

	private GraphicObject[][] tableau;

	public static final int WC = 50; // Taille d'une case en pixel

	/**
	 * Constructeur du jardin
	 * 
	 * @param xc
	 *            nombre de colonnes du tableau
	 * @param yc
	 *            nombre de lignes du tableau
	 */

	public Garden(int xc, int yc) {
		super((Window.WIDTH - xc * WC) / 2, (Window.HEIGHT - yc * WC) / 2, xc * WC, yc * WC);
		this.xc = xc;
		this.yc = yc;
		this.tableau = new GraphicObject[xc][yc];
		loadImages(EggHunt.IMGP + "garden.png", 7, Garden.WC, Garden.WC);
	}

	public void addRocks(int xc, int yc, Garden g) {
		Obstacle r = new Obstacle(xc, yc, g);
		tableau[xc][yc] = r;
		addDescendant(r);
	}

	public void addEgg(int xc, int yc, int nb, Garden g) {
		Egg e = new Egg(xc, yc, nb, g);
		tableau[xc][yc] = e;
		addDescendant(e);
	}

	public void addChild(int cx, int cy, char o, ArrayList<Character> inst, String name, Garden g) {
		Child c = new Child(cx, cy, o, inst, name, g);
		tableau[c.getXC()][c.getYC()] = c;
		addDescendant(c);
	}

	@Override
	protected void paint(Graphics2D g) {
		for (int j = 0; j < yc; j++) {
			// Barrière de droite et de gauche
			g.drawImage(imgs[6], x - WC, y + j * WC, null);
			g.drawImage(imgs[6], x + xc * WC, y + j * WC, null);
			for (int i = 0; i < xc; i++) {
				// Herbe + grille
				g.drawImage(imgs[3], x + i * WC, y + j * WC, null);
				g.setColor(Color.BLACK);
				g.drawLine(x + i * WC, y, x + i * WC, y + (j + 1) * WC);
				g.drawLine(x, y + j * WC, x + (i + 1) * WC, y + j * WC);
			}
		}
		// Barrières du haut et du bas
		for (int i = 0; i < xc; i++) {
			g.drawImage(imgs[2], x + i * WC, y - WC, null);
			g.drawImage(imgs[2], x + i * WC, y + yc * WC, null);
		}
		// Barrières de coin
		g.drawImage(imgs[0], x - WC, y - WC, null);
		g.drawImage(imgs[1], x + xc * WC, y - WC, null);
		g.drawImage(imgs[4], x - WC, y + yc * WC, null);
		g.drawImage(imgs[5], x + xc * WC, y + yc * WC, null);
		// Contour du jardin
		g.drawRect(x, y, xc * WC, yc * WC);
		g.drawRect(x - WC, y - WC, (xc + 2) * WC, (yc + 2) * WC);
	}

	@Override
	protected void calculate() {

	}
}
