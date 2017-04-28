package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;

public class Obstacle extends GraphicObject {

	private int img;

	private int xc;
	private int yc;

	protected Obstacle(int xc, int yc, Garden g) {
		super(g.x + xc * Garden.WC, g.y + yc * Garden.WC, Garden.WC, Garden.WC);
		loadImages(EggHunt.IMGP + "obstacles.png", 3, Garden.WC, Garden.WC);
		img = (int) Math.round(Math.random() * 2);
		this.xc = xc;
		this.yc = yc;
	}

	public int getXc() {
		return xc;
	}

	public int getYc() {
		return yc;
	}

	@Override
	protected void paint(Graphics2D g) {
		g.drawImage(imgs[img], x, y, null);
	}

	@Override
	protected void calculate() {

	}

}
