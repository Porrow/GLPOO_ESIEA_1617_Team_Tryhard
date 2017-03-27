package org.tryhard.gl.egghunt;

import java.util.ArrayList;

public class Child extends GraphicObject{

	private int x; // Coordonnée x en case
	private int y; // Coordonnée y en case
	private ArrayList<Egg> basket = new ArrayList<Egg>(); // Oeufs ramassés

	public Child(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public ArrayList<Egg> getEggs() {
		return basket;
	}
}
