package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.log4j.Logger;
import org.tryhard.gl.egghunt.gui.Window;

public class Button extends GraphicObject implements MouseListener, MouseMotionListener {

	private static final Logger LOGGER = Logger.getLogger(Button.class);

	private String path;
	private Integer etat = 0;
	private boolean visible = true; 

	public Button(int x, int y, int w, int h, String path) {
		super(x, y, w, h);
		this.path = path;
		loadImages(path, 2, w, h);
	}
	
	public Button(int x, int y, int w, int h, String path, boolean visible) {
		super(x, y, w, h);
		this.path = path;
		this.visible = visible;
		loadImages(path, 2, w, h);
	}

	@Override
	protected void paint(Graphics2D g) {
		if(visible)
		g.drawImage(imgs[etat], x, y, null);
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
			switch (EggHunt.getInstance().getViewChoice()) {
			case Menu.ID:
				treatMenuButtons();
				break;

			case Selection.ID:
				treatSelectionButtons();
				break;

			case Game.ID:
				treatGameButtons();
				break;
			case MapEditor.ID:
				treatMapEditorButtons();
				break;
			default:
				break;
			}

		}

		if (path == EggHunt.IMGP + "kidIcon.png")
			LOGGER.info(e.getX() + ";" + e.getY());

	}

	private void treatMapEditorButtons() {
		
		EggHunt.getInstance().getWin().setCursor(path);
		
	}

	private void treatMenuButtons() {
		switch (path) {
		case EggHunt.IMGP + "JouerMenu.png":
			EggHunt.getInstance().setViewChoice(Selection.ID);
			break;
		case EggHunt.IMGP + "QuitterMenu.png":
			System.exit(-1);
			break;
		case EggHunt.IMGP + "mapEditor.png":
			EggHunt.getInstance().setViewChoice(MapEditor.ID);
			break;
		default:
			break;
		}
	}

	private void treatSelectionButtons() {
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
	}

	private void treatGameButtons() {
		switch (path) {
		case EggHunt.IMGP + "QuitGame.png":
			EggHunt.getInstance().getWin().setPaused(false); // On sort le jeu de la pause
			EggHunt.getInstance().setViewChoice(Menu.ID);
			LOGGER.debug("Retour au menu de sélection");
			break;
		case EggHunt.IMGP + "PauseGame.png":
			if (etat == 0) {
				etat = 1;
				EggHunt.getInstance().getWin().setPaused(true);
				LOGGER.debug("Jeu en pause");
			} else {
				etat = 0;
				EggHunt.getInstance().getWin().setPaused(false);
				LOGGER.debug("Reprise");
			}

			break;
		case EggHunt.IMGP + "ScoreGame.png":
			Window.dialog.setVisible(true);
			LOGGER.debug("Affichage des scores");
			break;
		default:
			break;
		}

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

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		/*
		 * if (isInside(e)) { etat = 1; } else { etat = 0; }
		 */

	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
