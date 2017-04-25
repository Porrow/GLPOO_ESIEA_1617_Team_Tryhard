package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;

import org.tryhard.gl.egghunt.gui.Window;

public class Obstacle extends GraphicObject {
	
	protected Obstacle(int xc, int yc) {
		super(xc * Garden.WC, yc * Garden.WC, Garden.WC, Garden.WC);
		img = Window.loadImage("res/Rock4.png");
		
	}

	@Override
	protected void paint(Graphics2D g) {
		g.drawImage(img, x, y, null);

	}

	@Override
	protected void calculate() {
		
	}

}
