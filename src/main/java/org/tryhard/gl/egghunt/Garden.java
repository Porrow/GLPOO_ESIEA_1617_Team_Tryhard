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
		super((Window.WIDTH - (xc+1) * WC) / 2, (Window.HEIGHT - (yc+1) * WC) / 2, (xc+1) * WC, (yc+1) * WC);
		this.xc = xc;
		this.yc = yc;
		this.tableau = new GraphicObject[xc][yc];
		loadImages(EggHunt.IMGP + "terrain.png", 7, Garden.WC, Garden.WC);
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
		g.setColor(Color.BLACK);
		for (int i = 0; i < xc; i++) {
			for (int j = 0; j < yc; j++) {
				g.drawImage(imgs[0], x + i * WC, y + j * WC, null);
				g.setColor(Color.BLACK);
				g.drawLine(x + i * WC, y, x + i * WC, y + (j + 1) * WC);
				g.drawLine(x, y + j * WC, x + (i + 1) * WC, y + j * WC);
			}
		}
		g.drawRect(x, y, xc * WC, yc * WC);
	}

	@Override
	protected void calculate() {

	}
}
