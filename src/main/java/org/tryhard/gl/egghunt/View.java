package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;

import org.tryhard.gl.egghunt.gui.Window;
import org.tryhard.gl.egghunt.io.CsvDao;

/**
 * 
 * @author menuiserie
 *
 */
public class View extends GraphicObject {

	public View() {
		super(0, 0, Window.WIDTH, Window.HEIGHT);
	}

	public View(String csv_garden, String csv_children) {
		super(0, 0, Window.WIDTH, Window.HEIGHT);
		CsvDao cd = new CsvDao();
		addDescendant(cd.getGardenAndChilds(csv_garden, csv_children));
	}

	@Override
	public void paint(Graphics2D g) {
		
	}

	@Override
	protected void calculate() {
	}
}
