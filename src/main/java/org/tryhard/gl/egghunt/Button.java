package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;

import org.tryhard.gl.egghunt.gui.Window;

public class Button extends GraphicObject {

	protected Button(int x, int y, int w, int h) {
		super(x, y, w, h);
		img = Window.loadImage("res/Jouer.png");
		img2 = Window.loadImage("res/Quitter.png");
	}

	@Override
	protected void paint(Graphics2D g) {
		g.drawImage(img, Window.WIDTH/2,Window.HEIGHT/2, null);
		g.drawImage(img, Window.WIDTH/2,Window.HEIGHT/2 + 84, null);
	}

	@Override
	protected void calculate() {

	}

}
