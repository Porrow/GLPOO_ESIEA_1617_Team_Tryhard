package org.tryhard.gl.egghunt;

import java.awt.Color;
import java.awt.Graphics2D;

public class Container extends GraphicObject {

	public Container() {
		super(0, 0, null);
		
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, 50, 50);
		super.paint(g);
	}
}
