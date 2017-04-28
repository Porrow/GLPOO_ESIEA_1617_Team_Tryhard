package org.tryhard.gl.egghunt;

import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.tryhard.gl.egghunt.gui.Window;

public class MapEditor extends GraphicObject implements ActionListener, MouseListener, MouseMotionListener {

	private static final Logger LOGGER = Logger.getLogger(MapEditor.class);
	protected static final int ID = 3;
	private JPanel topPan;
	private FlowLayout fl;
	private JTextField gardenWidthField = new JTextField("Enter column number");
	private JTextField gardenHeightField = new JTextField("Enter lines number");
	private JButton validButton = new JButton("create");
	private Garden garden;
	private static String selection = "";
	private static int etape = 0;
	private Button egg1Button;
	private Button egg2Button;
	private Button egg3Button;
	private Button rockButton;
	private Button pnButton;
	private Button peButton;
	private Button psButton;
	private Button pwButton;
	int mx;
	int my;
	
	
	protected MapEditor() {
		super(0, 0, Window.WIDTH, Window.HEIGHT);
		fl = new FlowLayout();
		fl.setAlignment(FlowLayout.CENTER);
		fl.setHgap(15);
		topPan = new JPanel(fl);
		topPan.add(new JLabel("Garden:"));
		gardenWidthField.addActionListener(this);
		gardenWidthField.setActionCommand("width");
		gardenHeightField.setActionCommand("height");
		gardenHeightField.addActionListener(this);
		topPan.add(gardenWidthField);
		topPan.add(gardenHeightField);
		validButton.addActionListener(this);
		topPan.add(validButton);
		
		topPan.add(new JLabel("(10x10 max)"));
		
		instanciateButtons();
	}
	
	private void instanciateButtons(){
		egg1Button = new Button(100,200,50,50,EggHunt.IMGP + "egg1.png", false);
		egg2Button = new Button(150,200,50,50,EggHunt.IMGP + "egg2.png", false);
		egg3Button = new Button(200,200,50,50,EggHunt.IMGP + "egg3.png", false);
		rockButton = new Button(100,250,50,50,EggHunt.IMGP + "rock.png", false);
		pnButton = new Button(100,200,50,50,EggHunt.IMGP + "kidN.png", false);
		peButton = new Button(150,200,50,50,EggHunt.IMGP + "kidE.png", false);
		psButton = new Button(200,200,50,50,EggHunt.IMGP + "kidS.png", false);
		pwButton = new Button(250,200,50,50,EggHunt.IMGP + "kidW.png", false);
		

		addDescendant(egg1Button);
		addDescendant(egg2Button);
		addDescendant(egg3Button);
		addDescendant(rockButton);
		addDescendant(pnButton);
		addDescendant(peButton);
		addDescendant(psButton);
		addDescendant(pwButton);
	}

	public JPanel getPan() {
		return topPan;
	}

	@Override
	protected void paint(Graphics2D g) {
		if(!selection.equals("")){
			LOGGER.info("ca dessine"+mx+";"+my);
			switch(selection){
			case "egg1":
				g.drawImage(getDescendants().get(0).imgs[0], mx - Garden.WC/2, my - Garden.WC/2, null);
				break;
			case "egg2":
				g.drawImage(getDescendants().get(1).imgs[0], mx - Garden.WC/2, my - Garden.WC/2, null);
				break;
			case "egg3":
				g.drawImage(getDescendants().get(2).imgs[0], mx - Garden.WC/2, my - Garden.WC/2, null);
				break;
			case "rock":
				g.drawImage(getDescendants().get(3).imgs[0], mx - Garden.WC/2, my - Garden.WC/2, null);
				break;
			case "kidN":
				g.drawImage(getDescendants().get(4).imgs[0], mx - Garden.WC/2, my - Garden.WC/2, null);
				break;
			case "kidE":
				g.drawImage(getDescendants().get(5).imgs[0], mx - Garden.WC/2, my - Garden.WC/2, null);
				break;
			case "kidS":
				g.drawImage(getDescendants().get(6).imgs[0], mx - Garden.WC/2, my - Garden.WC/2, null);
				break;
			case "kidW":
				g.drawImage(getDescendants().get(7).imgs[0], mx - Garden.WC/2, my - Garden.WC/2, null);
				break;
				
			}
		}
		
	}

	@Override
	protected void calculate() {
		// TODO Auto-generated method stub
		if(gardenWidthField.hasFocus() && gardenWidthField.getText().equals("Enter column number"))
			gardenWidthField.setText("");
		if(gardenHeightField.hasFocus() && gardenHeightField.getText().equals("Enter lines number"))
			gardenHeightField.setText("");
	
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String action = e.getActionCommand();
		switch(action){
		case "create":
		if (isInteger(gardenWidthField.getText()) && isInteger(gardenHeightField.getText())) {
			LOGGER.info("create button pressed");
			int w = Integer.parseInt(gardenWidthField.getText());
			int h = Integer.parseInt(gardenHeightField.getText());
				if(w < 11  && h < 11 && garden == null){
					garden = new Garden(w, h);
					addDescendant(garden);
					topPan.removeAll();
					topPan.add(new JLabel("Select and place your eggs and rocks"));
					topPan.validate();
					etape++;
					egg1Button.setVisible(true);
					egg2Button.setVisible(true);
					egg3Button.setVisible(true);
					rockButton.setVisible(true);
					/*pnButton.setVisible(true);
					peButton.setVisible(true);
					psButton.setVisible(true);
					pwButton.setVisible(true);*/
				}
			}
		break;
		}
	}
	
	private boolean isInteger(String str) {
	    try {
	        Integer.parseInt(str);
	    } catch (NumberFormatException ex) {
	        return false;
	    }
	    return true;
	}
	
	

	public static int getEtape() {
		return etape;
	}

	public static String getSelection() {
		return selection;
	}

	public static void setSelection(String selection) {
		MapEditor.selection = selection;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
