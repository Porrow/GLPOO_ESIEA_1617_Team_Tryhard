package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;
import org.apache.log4j.Logger;
import org.tryhard.gl.egghunt.gui.Window;

public class Menu extends GraphicObject { 

	public Menu() {
		super(0, 0, Window.WIDTH, Window.HEIGHT);
		img = Window.loadImage("res/OeufsMenu2.png");	
	}
	
	@Override
	public void paint(Graphics2D g) {
		//g.fillRect(20, 20, 20, 20);
		g.drawImage(img, 0, 70, null);
	}

	@Override
	protected void calculate() {
	}

}
