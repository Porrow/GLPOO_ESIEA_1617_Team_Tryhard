package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

/**
 * Classe de base pour les objets dessinables
 * 
 * @author menuiserie
 *
 */
public abstract class GraphicObject {

	private static final Logger LOGGER = Logger.getLogger(GraphicObject.class);

	protected int x; // Coordonnée x relative en pixels
	protected int y; // Coordonnée y relative en pixels
	protected int w; // Largeur en pixels
	protected int h; // Hauteur en pixels
	protected BufferedImage[] imgs; // Images associées
	private ArrayList<GraphicObject> descendants = new ArrayList<GraphicObject>(); // Les objets graphiques contenues, descendants

	/**
	 * Constructeur
	 * 
	 * @param x
	 *            Coordonnée x en pixels
	 * @param y
	 *            Coordonnée y en pixels
	 * @param w
	 *            Largeur en pixels
	 * @param h
	 *            Hauteur en pixels
	 */
	protected GraphicObject(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public ArrayList<GraphicObject> getDescendants() {
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

	protected void addDescendant(GraphicObject g) {
		descendants.add(g);
	}
	
	protected void removeDescendant(GraphicObject g){
		descendants.remove(g);
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

	/**
	 * Charge l'image imgpath
	 * 
	 * @param imgpath
	 *            Chemin de l'image à charger
	 */
	private BufferedImage loadImage(String imgpath) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(imgpath));
			return img;
		} catch (IOException ex) {
			LOGGER.error("Impossible de charger l'image " + imgpath);
			LOGGER.error("Le programme doit s'arrêter");
			System.exit(-1);
			return img;
		}
	}

	/**
	 * Extrait un tableau d'image à partir d'une image
	 * 
	 * @param img
	 *            L'image à découper
	 * @param nImg
	 *            La taille du tableau de retour
	 * @param wi
	 *            La largeur des sous-images
	 * @param he
	 *            La hauteur des sous-images
	 */
	public void loadImages(String imgpath, int nImg, int wi, int he) {
		BufferedImage img = loadImage(imgpath);
		imgs = new BufferedImage[nImg];
		if (nImg == 1) {
			imgs[0] = img;
			return;
		}
		for (int i = 0, j = 0, k = 0; k < nImg; i++, k++) {
			if (k % (img.getWidth() / wi) == 0 && i != 0) {
				j++;
				i = 0;
			}
			imgs[k] = img.getSubimage(i * wi, j * he, wi, he);
		}
	}

	public void loadImages(String imgpath) {
		BufferedImage img = loadImage(imgpath);
		loadImages(imgpath, 1, img.getWidth(), img.getHeight());
	}
}
