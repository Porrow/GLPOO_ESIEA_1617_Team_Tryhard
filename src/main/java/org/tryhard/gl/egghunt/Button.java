package org.tryhard.gl.egghunt;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.tryhard.gl.egghunt.gui.Window;

public class Button extends GraphicObject implements MouseListener {

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

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX());
		System.out.println(e.getY());
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
