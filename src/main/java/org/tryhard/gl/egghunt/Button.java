package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;

import org.tryhard.gl.egghunt.gui.Window;

public class Button extends GraphicObject {

	public Button(int x, int y, int w, int h, String path) {
		super(x, y, w, h);
		img = Window.loadImage(path);
	}

	@Override
	protected void paint(Graphics2D g) {
		g.drawImage(img, x, y, null);
	}

	@Override
	protected void calculate() {

	}

}
