package org.tryhard.gl.egghunt;

import java.awt.Font;
import java.awt.Graphics2D;

import org.tryhard.gl.egghunt.gui.Window;
import org.tryhard.gl.egghunt.io.CsvDao;

/**
 *
 *
 */
public class Game extends GraphicObject {

	protected static final int ID = 2;
	private Garden gd;
	private int timer;
	private Button returnButton;

	public Game(String csv_garden, String csv_children) {
		super(0, 0, Window.WIDTH, Window.HEIGHT);
		CsvDao cd = new CsvDao();
		gd = cd.getGardenAndChilds(csv_garden, csv_children);
		addDescendant(gd);
		timer = 0;
		returnButton = new Button(Window.WIDTH / 2 + 400, 620, 200, 84, EggHunt.IMGP + "QuitterGame.png");
		addDescendant(returnButton);
	}

	@Override
	public void paint(Graphics2D g) {
		int i = 0;
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		for (GraphicObject d : gd.getDescendants()) {
			if (d.getClass() == Child.class) {
				Child c = (Child) d;
				String str = c.getName() + " : " + c.getBasket().size();
				g.drawString(str, 1000, 100 + 50 * i);
				g.drawString(String.valueOf(timer / Window.FPS), 500, 50);
				i++;
			}
		}
	}

	@Override
	protected void calculate() {
		timer++;
	}

}
