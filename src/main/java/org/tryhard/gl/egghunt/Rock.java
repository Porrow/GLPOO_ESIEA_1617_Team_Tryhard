package org.tryhard.gl.egghunt;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Rock extends GraphicObject 
{
	private static BufferedImage img;
	
	static
	{
		 try 
		 {
			img = ImageIO.read(new File("res\\Rock4.png"));
			
			int w = img.getWidth(null);
			int h = img.getHeight(null);
			BufferedImage bi = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
			Graphics g = bi.getGraphics();
			g.drawImage(img, 0, 0, 50, 50, 0, 0, w, h, null);
			img = bi;
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
	}
	
	
	protected Rock(int x, int y) 
	{
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void paint(Graphics2D g) 
	{
		
		g.drawImage(img,x,y,null);
		
		
	}

	@Override
	protected void calculate() {
		// TODO Auto-generated method stub
		
	}

}
