package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;
/**
 * Classe représentant un Oeuf. Cette classe hérite de GraphicObject ce qui lui permet d'être "dessinable"
 * @author menuiserie
 *
 */
public class Egg extends GraphicObject {

	
	private int nbEggs;
	/**
	 * Constructeur d'un oeuf
	 * @param x définit la coordonée en x de l'oeuf
	 * @param y définit la coordonée en y de l'oeuf
	 */
	public Egg(int x, int y,int nbEggs) {
		super(x, y);
		this.nbEggs = nbEggs;
	}
	
	

	/**
	 * Dessine la représentation graphique de l'oeuf sur l'objet Graphics2D passé en paramètre
	 **/
	@Override
	protected void paint(Graphics2D g) {
		
	}
	/**
	 * 
	 **/
	@Override
	protected void calculate() {
		
	}
}
