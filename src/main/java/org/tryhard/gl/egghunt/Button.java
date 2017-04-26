package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.Logger;
import org.tryhard.gl.egghunt.gui.Window;

public class Button extends GraphicObject implements MouseListener {

	private static final Logger LOGGER = Logger.getLogger(Button.class);

	private String path;

	public Button(int x, int y, int w, int h, String path) {
		super(x, y, w, h);
		this.path = path;
		loadImages(path, 1, 200, 84);
	}

	@Override
	protected void paint(Graphics2D g) {
		g.drawImage(imgs[0], x, y, null);
	}

	@Override
	protected void calculate() {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (isInside(e)) {
			LOGGER.info("a button is pressed");
			if (EggHunt.getInstance().getViewChoice() == Menu.ID) {
				switch (path) {
				case "res/Jouer.png":
					EggHunt.getInstance().setViewChoice(Selection.ID);
					break;
				case "res/Quitter.png":
					System.exit(-1);
					break;
				}
			} else if (EggHunt.getInstance().getViewChoice() == Selection.ID) {
				switch (path) {
				case "res/mapIcon.png":
					LOGGER.info("map button pressed");
					EggHunt.getInstance().getSelect().getCsvGF().setText(getPath());

					break;
				case "res/kidIcon.png":
					LOGGER.info("kid button pressed");
					EggHunt.getInstance().getSelect().getCsvCF().setText(getPath());
					break;
				case "res/Jouer.png":
					LOGGER.info("play button pressed");
					EggHunt.getInstance().getViews().remove(Game.ID);
					EggHunt.getInstance().getViews().add(Game.ID,
							new Game(EggHunt.getInstance().getSelect().getCsvGF().getText(),
									EggHunt.getInstance().getSelect().getCsvCF().getText()));
					EggHunt.getInstance().setViewChoice(Game.ID);
					break;
				}
			}
		}

		if (path == "res/kidIcon.png")
			LOGGER.info(e.getX() + ";" + e.getY());

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println(e.getX() + ";" + e.getY());

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	private boolean isInside(MouseEvent e) {

		return e.getX() > x && e.getX() < x + w && e.getY() > y && e.getY() < y + h;
	}

	private String getPath() {
		JFileChooser dialogue = new JFileChooser("./res");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers csv.", "csv");
		dialogue.addChoosableFileFilter(filter);
		dialogue.setAcceptAllFileFilterUsed(false);
		dialogue.setFileFilter(filter);
		if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			return dialogue.getSelectedFile().getPath();
		}
		return null;
	}

}
