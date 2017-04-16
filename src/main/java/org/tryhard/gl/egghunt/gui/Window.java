package org.tryhard.gl.egghunt.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.apache.log4j.Logger;
import org.tryhard.gl.egghunt.View;
import org.tryhard.gl.egghunt.EggHunt;

public class Window extends JFrame {

	private static final long serialVersionUID = 6725603138216332687L;
	public static final String TITLE = "Egg Hunt - V 0.1 alpha"; // Titre de la fenêtre
	private static final Dimension DIM = new Dimension(1280, 720); // Dimension de la fenêtre : HD

	public Window() {
		super();
		setTitle(TITLE);
		setPreferredSize(DIM);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		final JPanel pan = new JPanel() {

			private static final long serialVersionUID = 9015769097796805166L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				paintObjects(g);
			}
		};

		setContentPane(pan);

		new Thread(new Runnable() {
			public void run() {
				while (true) {
					calculateObjects();
					pan.repaint();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		}).start();

		pack();
		setVisible(true);
		setLocationRelativeTo(null); // Positionne la fenêtre au centre de l'écran
	}

	private void paintObjects(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Anti-aliasing
		View cont = EggHunt.getContainers().get(EggHunt.getViewChoice()); // Conteneur à afficher
		cont.paintAll(g2); // Affichage du conteneur
	}

	private void calculateObjects() {
		View cont = EggHunt.getContainers().get(EggHunt.getViewChoice()); // Conteneur à calculer
		cont.calculateAll();
	}
}
