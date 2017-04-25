package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;
import org.apache.log4j.Logger;
import org.tryhard.gl.egghunt.gui.Window;

public class Menu extends GraphicObject { 

	protected static int ID = 0;
	private Button jButton;
	private Button qButton;
	
	public Menu() {
			
		super(0, 0, Window.WIDTH, Window.HEIGHT);
		img = Window.loadImage("res/OeufsMenu.png");
		jButton = new Button(Window.WIDTH/2-200, 620, 200, 84, "res/Jouer.png");
		qButton = new Button(Window.WIDTH/2, 620, 200, 84, "res/Quitter.png");
		addDescendant(jButton);
		addDescendant(qButton);
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
