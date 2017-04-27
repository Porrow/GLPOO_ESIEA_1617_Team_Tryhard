package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.log4j.Logger;

public class Button extends GraphicObject implements MouseListener {

	private static final Logger LOGGER = Logger.getLogger(Button.class);

	private String path;

	public Button(int x, int y, int w, int h, String path) {
		super(x, y, w, h);
		this.path = path;
		loadImages(path);
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
			switch (EggHunt.getInstance().getViewChoice()) {

			case Menu.ID:
				switch (path) {
				case EggHunt.IMGP + "JouerMenu.png":
					EggHunt.getInstance().setViewChoice(Selection.ID);
					break;
				case EggHunt.IMGP + "QuitterMenu.png":
					System.exit(-1);
					break;
				default:
					break;
				}
				LOGGER.info("menu ID");
				break;

			case Selection.ID:
				switch (path) {
				case EggHunt.IMGP + "mapIcon.png":
					LOGGER.info("map button pressed");
					EggHunt.getInstance().getSelect().getCsvGF().setText(getPath());
					break;
				case EggHunt.IMGP + "kidIcon.png":
					LOGGER.info("kid button pressed");
					EggHunt.getInstance().getSelect().getCsvCF().setText(getPath());
					break;
				case EggHunt.IMGP + "JouerSelect.png":
					LOGGER.info("play button pressed");
					EggHunt.getInstance().getViews().remove(Game.ID);
					EggHunt.getInstance().getViews().add(Game.ID, new Game(EggHunt.getInstance().getSelect().getCsvGF().getText(), EggHunt.getInstance().getSelect().getCsvCF().getText()));
					EggHunt.getInstance().setViewChoice(Game.ID);
					break;
				case EggHunt.IMGP + "QuitterSelect.png":
					EggHunt.getInstance().setViewChoice(Menu.ID);
					break;
				default:
					break;
				}
				LOGGER.info("select ID");
				break;

			case Game.ID:
				switch (path) {
				case EggHunt.IMGP + "QuitterGame.png":
					EggHunt.getInstance().setViewChoice(Menu.ID);
					LOGGER.info("game ID");
					break;
				default:
					break;
				}
				break;
			default:
				break;
			}

		}

		if (path == EggHunt.IMGP + "kidIcon.png")
			LOGGER.info(e.getX() + ";" + e.getY());

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	private boolean isInside(MouseEvent e) {
		return e.getX() > x && e.getX() < x + w && e.getY() > y && e.getY() < y + h;
	}

	private String getPath() {
		JFileChooser dialogue = new JFileChooser("./" + EggHunt.CSVP);
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
