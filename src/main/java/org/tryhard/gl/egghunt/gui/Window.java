package org.tryhard.gl.egghunt.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.apache.log4j.Logger;
import org.tryhard.gl.egghunt.GraphicObject;
import org.tryhard.gl.egghunt.Button;
import org.tryhard.gl.egghunt.EggHunt;
import org.tryhard.gl.egghunt.Menu;

/**
 * Classe déssinant la fênetre principale du programme
 *
 */
public class Window extends JFrame {

	private static final long serialVersionUID = 6725603138216332687L;
	private static final Logger LOGGER = Logger.getLogger(Window.class);
	public static final String TITLE = "Egg Hunt - V 0.1 alpha"; // Titre de la
																	// fenêtre
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	private static final Dimension DIM = new Dimension(WIDTH, HEIGHT); // Dimension
																		// de la
																		// fenêtre
																		// : HD
	public static final int FPS = 50;
	private final JPanel pan;

	/**
	 * Constructeur, initialise le gaphisme de la fenêtre, et affiche la fenêtre
	 */
	public Window() {
		super();
		setTitle(TITLE);
		setPreferredSize(DIM);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		pan = new JPanel() {

			private static final long serialVersionUID = 9015769097796805166L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				paintObjects(g);
			}
		};
		setContentPane(pan);
		ArrayList<GraphicObject> menuButtons = EggHunt.getInstance().getViews().get(0).getDescendants();
		for (GraphicObject o : menuButtons)
			pan.addMouseListener((Button) o);
		ArrayList<GraphicObject> selectButtons = EggHunt.getInstance().getViews().get(1).getDescendants();
		for (GraphicObject o : selectButtons)
			pan.addMouseListener((Button) o);
		pan.setLayout(new BorderLayout());
		pan.setBackground(new Color(0, 160, 255));
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					calculateObjects(); // Met à jour les calculs (de
										// positions...)
					pan.repaint(); // Met à jour les graphismes (s'execute sur
									// le Thread principal !)
					try {
						Thread.sleep(1000 / FPS);
					} catch (InterruptedException e) {
						LOGGER.error(e.getMessage());
					}
				}
			}
		}).start(); // Démarre un Thread chargé d'effectuer les calculs

		pack();
		setVisible(true);
		setLocationRelativeTo(null); // Positionne la fenêtre au centre de
										// l'écran
	}

	/**
	 * Dessine les objets graphiques
	 * 
	 * @param g
	 *            ContextGraphique sur lequel dessiner
	 */
	private void paintObjects(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Anti-aliasing
		GraphicObject cont = EggHunt.getInstance().getViews().get(EggHunt.getInstance().getViewChoice()); // Conteneur
																											// à
																											// afficher
		cont.paintAll(g2); // Affichage du conteneur
	}

	/**
	 * Execute la fonction calculateAll sur le contenaire
	 */
	private void calculateObjects() {
		GraphicObject cont = EggHunt.getInstance().getViews().get(EggHunt.getInstance().getViewChoice()); // Conteneur
																											// à
																											// calculer
		cont.calculateAll();
	}

	/**
	 * Charge l'image imgpath
	 * 
	 * @param imgpath
	 *            Chemin de l'image à charger
	 */
	public static BufferedImage loadImage(String imgpath) {
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
	public static BufferedImage[] extraction(BufferedImage img, int nImg, int wi, int he) {
		BufferedImage[] imgs = new BufferedImage[nImg];
		for (int i = 0, j = 0, k = 0; k < nImg; i++, k++) {
			if (k % (img.getWidth() / wi) == 0 && i != 0) {
				j++;
				i = 0;
			}
			imgs[k] = img.getSubimage(i * wi, j * he, wi, he);
		}
		return imgs;
	}

	public JPanel getPan() {
		return pan;
	}
}
