package org.tryhard.gl.egghunt;

import java.awt.Color;
import java.awt.Graphics2D;

public class View extends GraphicObject {

	public View() {
		super(0, 0, null);
	}

	public View(String csv_garden, String csv_children) {
		super(0, 0, null);
		new Garden(this, csv_children);
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, 50, 50);
	}

	@Override
	protected void calculate() {

	}
}
