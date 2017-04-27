package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;
import org.apache.log4j.Logger;
import org.tryhard.gl.egghunt.gui.Window;

public class Menu extends GraphicObject {

	protected static final int ID = 0;
	private Button jButton;
	private Button qButton;

	public Menu() {

		super(0, 0, Window.WIDTH, Window.HEIGHT);
		loadImages(EggHunt.IMGP + "OeufsMenu.png", 1, Window.WIDTH, Window.HEIGHT);
		jButton = new Button(Window.WIDTH / 2 - 200, 620, 200, 84, EggHunt.IMGP + "JouerMenu.png");
		qButton = new Button(Window.WIDTH / 2, 620, 200, 84, EggHunt.IMGP + "QuitterMenu.png");
		addDescendant(jButton);
		addDescendant(qButton);
	}

	@Override
	public void paint(Graphics2D g) {

		g.drawImage(imgs[0], 0, 0, null);
	}

	@Override
	protected void calculate() {
	}

}
