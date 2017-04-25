package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.tryhard.gl.egghunt.gui.Window;

public class Button extends GraphicObject implements MouseListener {

	private String path;

	public Button(int x, int y, int w, int h, String path) {
		super(x, y, w, h);
		this.path = path;
		img = Window.loadImage(path);
	}

	@Override
	protected void paint(Graphics2D g) {
		g.drawImage(img, x, y, null);
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
			if (EggHunt.getViewChoice() == Menu.ID) {
				switch (path) {
				case "res/Jouer.png":
					EggHunt.setViewChoice(Game.ID);
					break;
				case "res/Quitter.png":
					System.exit(-1);
					break;
				}
			} else {
				// A compléter une fois la classe "Selection" fini
			}
		}

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

}
