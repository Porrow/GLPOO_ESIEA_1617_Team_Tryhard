package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;

/**
 * Classe représentant un Oeuf. Cette classe hérite de GraphicObject ce qui lui permet d'être "dessinable"
 * 
 * @author menuiserie
 *
 */
public class Egg extends GraphicObject {

	private int nb;

	/**
	 * Constructeur d'un oeuf
	 * 
	 * @param x
	 *            définit la coordonée en x de l'oeuf
	 * @param y
	 *            définit la coordonée en y de l'oeuf
	 */
	public Egg(int xc, int yc, int nb, Garden g) {
		super(g.x + xc * Garden.WC, g.y + yc * Garden.WC, Garden.WC, Garden.WC);
		loadImages(EggHunt.IMGP + "eggs.png", 3, Garden.WC, Garden.WC);
		this.nb = nb;
	}

	public int getNb() {
		return nb;
	}

	public void setNb(int nb) {
		this.nb = nb;
	}

	/**
	 * Dessine la représentation graphique de l'oeuf sur l'objet Graphics2D passé en paramètre
	 **/
	@Override
	protected void paint(Graphics2D g) {
		if (nb > 0)
			g.drawImage(imgs[nb - 1], x, y, null);
	}

	/**
	 * 
	 **/
	@Override
	protected void calculate() {

	}
}
