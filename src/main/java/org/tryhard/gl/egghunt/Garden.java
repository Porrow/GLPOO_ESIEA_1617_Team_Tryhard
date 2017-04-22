package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;

/**
 * Classe représentant un jardin. Cette classe hérite de GraphicObject ce qui lui permet d'être "dessinable"
 * @author menuiserie
 *
 */
public class Garden extends GraphicObject {

	public static final int WC = 50; //Taille d'une case en pixel

	/**
	 * constructeur de jardin
	 * @param csv_child chemin du fichier décrivant les enfants
	 */
	public Garden(String csv_child) {
		super(0, 0);
	}

	@Override
	protected void paint(Graphics2D g) {
		
	}

	@Override
	protected void calculate() {
		
	}
}
