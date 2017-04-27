package org.tryhard.gl.egghunt;

import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.tryhard.gl.egghunt.gui.Window;

public class MapEditor extends GraphicObject implements ActionListener {

	private static final Logger LOGGER = Logger.getLogger(MapEditor.class);
	protected static final int ID = 3;
	private JPanel pan;
	private FlowLayout fl;
	private JTextField gardenWidthField = new JTextField("Enter column number");
	private JTextField gardenHeightField = new JTextField("Enter lines number");
	private JButton validButton = new JButton("create");
	private Garden garden;
	
	protected MapEditor() {
		super(0, 0, Window.WIDTH, Window.HEIGHT);
		fl = new FlowLayout();
		fl.setAlignment(FlowLayout.CENTER);
		fl.setHgap(15);
		pan = new JPanel(fl);
		pan.add(new JLabel("Garden:"));
		pan.add(gardenWidthField);
		pan.add(gardenHeightField);
		validButton.addActionListener(this);
		pan.add(validButton);
		pan.add(new JLabel("(10x10 max)"));
		
	}

	public JPanel getPan() {
		return pan;
	}

	@Override
	protected void paint(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void calculate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String action = e.getActionCommand();
		if (action.equals("create") && isInteger(gardenWidthField.getText()) && isInteger(gardenHeightField.getText())) {
		LOGGER.info("create button pressed");
		int w = Integer.parseInt(gardenWidthField.getText());
		int h = Integer.parseInt(gardenHeightField.getText());
			if(w < 11  && h < 11 && garden == null){
				garden = new Garden(w, h);
				addDescendant(garden);
			}
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

}
