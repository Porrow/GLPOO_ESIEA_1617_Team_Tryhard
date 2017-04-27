package org.tryhard.gl.egghunt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.tryhard.gl.egghunt.gui.Window;

public class Selection extends GraphicObject {

	protected static final int ID = 1;
	private JPanel pan = new JPanel(new GridLayout(6, 1, 0, 5));
	private JTextField csvGF = new JTextField(EggHunt.GARDENPATH);
	private JTextField csvCF = new JTextField(EggHunt.CHILDRENPATH);
	private Button gardenButton;
	private Button childrenButton;
	private Button quitButton;
	private Button goButton;

	protected Selection() {
		super(0, 0, Window.WIDTH, Window.HEIGHT);
		csvGF.setEditable(false);
		csvGF.setFont(new Font("Serif", Font.BOLD, 20));
		csvGF.setAlignmentX(100);
		csvCF.setEditable(false);
		csvCF.setFont(new Font("Serif", Font.BOLD, 20));
		pan.setBackground(new Color(0, 160, 255));
		JLabel gLab = new JLabel("csv Garden:");
		gLab.setFont(new Font("Serif", Font.BOLD, 20));
		pan.add(new JLabel(""));
		pan.add(gLab);
		pan.add(csvGF);
		pan.add(new JLabel(""));
		JLabel cLab = new JLabel("csv Children:");
		cLab.setFont(new Font("Serif", Font.BOLD, 20));
		pan.add(cLab);
		pan.add(csvCF);
		
		childrenButton = new Button(1100, 266, 128, 128, EggHunt.IMGP + "kidIcon.png");
		gardenButton = new Button(900, 266, 128, 128, EggHunt.IMGP + "mapIcon.png");
		goButton = new Button(Window.WIDTH / 2 + 200, 620, 200, 84, EggHunt.IMGP + "JouerSelect.png");
		quitButton = new Button(Window.WIDTH / 2 + 400, 620, 200, 84, EggHunt.IMGP + "QuitterSelect.png");
		addDescendant(gardenButton);
		addDescendant(childrenButton);
		addDescendant(goButton);
		addDescendant(quitButton);
	}

	public JTextField getCsvGF() {
		return csvGF;
	}

	public void setCsvGF(JTextField csvGF) {
		this.csvGF = csvGF;
	}

	public JTextField getCsvCF() {
		return csvCF;
	}

	public void setCsvCF(JTextField csvCF) {
		this.csvCF = csvCF;
	}

	public JPanel getPan() {
		return pan;
	}

	@Override
	protected void paint(Graphics2D g) {
	}

	@Override
	protected void calculate() {
	}

}
