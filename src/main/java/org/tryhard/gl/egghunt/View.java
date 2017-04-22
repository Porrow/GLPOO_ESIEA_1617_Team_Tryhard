package org.tryhard.gl.egghunt;

import java.awt.Color;
import java.awt.Graphics2D;

import org.tryhard.gl.egghunt.io.CsvGardenDao;

/**
 * 
 * @author menuiserie
 *
 */
public class View extends GraphicObject {

	Garden garden;
	public View() {
		super(0, 0);
	}

	public View(String csv_garden, String csv_children) 
	{
		super(0, 0);
		garden = CsvGardenDao.getGarden(csv_garden);
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, 50, 50);
		if(garden!=null)
		garden.paintAll(g);
		
	}

	@Override
	protected void calculate() {

	}
}
