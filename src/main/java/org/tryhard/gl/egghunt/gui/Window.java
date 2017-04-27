package org.tryhard.gl.egghunt.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.apache.log4j.Logger;
import org.tryhard.gl.egghunt.GraphicObject;
import org.tryhard.gl.egghunt.Button;
import org.tryhard.gl.egghunt.EggHunt;
import org.tryhard.gl.egghunt.MapEditor;
/**
 * Classe déssinant la fênetre principale du programme
 *
 */
public class Window extends JFrame {

	private static final long serialVersionUID = 6725603138216332687L;
	private static final Logger LOGGER = Logger.getLogger(Window.class);
	public static final String TITLE = "Egg Hunt - V 0.1 alpha"; // Titre de la fenêtre
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	private static final Dimension DIM = new Dimension(WIDTH, HEIGHT); // Dimension de la fenêtre : HD
	public static final int FPS = 25;
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
		for (GraphicObject o : menuButtons) {
			if (o instanceof Button){
				pan.addMouseListener((Button) o);
				pan.addMouseMotionListener((Button) o);
			}
		}
		ArrayList<GraphicObject> selectButtons = EggHunt.getInstance().getViews().get(1).getDescendants();
		for (GraphicObject o : selectButtons) {
			if (o instanceof Button){
				pan.addMouseListener((Button) o);
				pan.addMouseMotionListener((Button) o);
			}
		}
		ArrayList<GraphicObject> gameButtons = EggHunt.getInstance().getViews().get(2).getDescendants();
		for (GraphicObject o : gameButtons) {
			if (o instanceof Button){
				pan.addMouseListener((Button) o);
				pan.addMouseMotionListener((Button) o);
			}
		}
		
		ArrayList<GraphicObject> mapEditorButtons = EggHunt.getInstance().getViews().get(3).getDescendants();
		for (GraphicObject o : mapEditorButtons) {
			if (o instanceof Button){
				pan.addMouseListener((Button) o);
				pan.addMouseMotionListener((Button) o);
			}
		}
		MapEditor e = (MapEditor)EggHunt.getInstance().getViews().get(3);
		pan.addMouseListener(e);
		pan.addMouseMotionListener(e);
		
		pan.setLayout(new BorderLayout());
		pan.setBackground(new Color(0, 160, 255));
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					calculateObjects(); // Met à jour les calculs (de positions...)
					pan.repaint(); // Met à jour les graphismes (s'execute sur le Thread principal !)
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
		setLocationRelativeTo(null); // Positionne la fenêtre au centre de l'écran
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
		GraphicObject cont = EggHunt.getInstance().getViews().get(EggHunt.getInstance().getViewChoice()); // Conteneur à afficher
		cont.paintAll(g2); // Affichage du conteneur
	}

	/**
	 * Execute la fonction calculateAll sur le contenaire
	 */
	private void calculateObjects() {
		GraphicObject cont = EggHunt.getInstance().getViews().get(EggHunt.getInstance().getViewChoice()); // Conteneur à calculer
		cont.calculateAll();
	}

	public JPanel getPan() {
		return pan;
	}
}
