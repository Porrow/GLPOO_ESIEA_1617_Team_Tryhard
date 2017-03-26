package org.tryhard.gl.egghunt.gui;

import java.awt.Dimension;
import javax.swing.JFrame;
//import org.apache.log4j.Logger;

public class Window extends JFrame {

	private static final long serialVersionUID = 6725603138216332687L;
	private static final String TITLE = "Egg Hunt - V alpha"; // Titre de la fenêtre
	private static final Dimension DIM = new Dimension(1280, 720); // Dimension de la fenêtre

	public Window() {
		super();
		setTitle(TITLE);
		setPreferredSize(DIM);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//getContentPane(new Panel());
		pack();
		setVisible(true);
		setLocationRelativeTo(null); // Positionne la fenêtre au centre de l'écran
	}
}
