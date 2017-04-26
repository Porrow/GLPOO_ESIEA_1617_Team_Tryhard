package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;

import org.tryhard.gl.egghunt.gui.Window;

public class Obstacle extends GraphicObject {
	
	protected Obstacle(int xc, int yc, Garden g) {
		super(g.x + xc * Garden.WC, g.y + yc * Garden.WC, Garden.WC, Garden.WC);
		loadImages("res/Rock4.png", 1, Garden.WC, Garden.WC);
		
	}

	@Override
	protected void paint(Graphics2D g) {
		g.drawImage(imgs[0], x, y, null);

	}

	@Override
	protected void calculate() {
		
	}

}
