package org.tryhard.gl.egghunt;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.tryhard.gl.egghunt.gui.Window;
import org.tryhard.gl.egghunt.io.CsvDao;

public class MapEditor extends GraphicObject implements ActionListener, MouseListener, MouseMotionListener {
	
	private static final Logger LOGGER = Logger.getLogger(MapEditor.class);
	public static final int ID = 3;
	private JPanel topPan;
	private FlowLayout fl;
	private JTextField mapField1 = new JTextField("Enter column number");
	private JTextField mapField2 = new JTextField("Enter lines number");
	private JButton validButton = new JButton("create");
	private JButton finishButton = new JButton("finish");
	private Garden garden;
	private static String selection = "";
	private static int etape = 0;
	private Button[] buttons = new Button[9];
	private final String[] selections = {"Curseur par défaut", "egg1", "egg2", "egg3", "rock", "kidN", "kidE", "kidS", "kidW"};
	int mx;
	int my;
	private Child newChild = null;
	
	
	protected MapEditor() {
		super(0, 0, Window.WIDTH, Window.HEIGHT);
		loadImages(EggHunt.IMGP + "background.png");
		fl = new FlowLayout();
		fl.setAlignment(FlowLayout.CENTER);
		fl.setHgap(15);
		topPan = new JPanel(fl);
		topPan.add(new JLabel("Garden:"));
		topPan.add(mapField1);
		topPan.add(mapField2);
		validButton.addActionListener(this);
		finishButton.addActionListener(this);
		topPan.add(validButton);
		
		topPan.add(new JLabel("(10x10 max)"));
		
		instanciateButtons();
	}
	
	private void instanciateButtons(){
		
		for(int i=0; i < selections.length; i++){
			buttons[i] = new Button(280, 150+i*50, 50, 50,EggHunt.IMGP + selections[i] +".png", false);
			addDescendant(buttons[i]);			
		}
		
	}
	

	public JPanel getPan() {
		return topPan;
	}

	@Override
	protected void paint(Graphics2D g) {
		g.drawImage(imgs[0], 0, 0, null);
		
	}

	@Override
	protected void calculate() {
		if(mapField1.hasFocus() && (mapField1.getText().equals("Enter column number") || mapField1.getText().equals("kid name") || mapField1.getText().equals("map file"))  )
			mapField1.setText("");
		if(mapField2.hasFocus() && (mapField2.getText().equals("Enter lines number") || mapField2.getText().equals("kid instructions") || mapField2.getText().equals("kid file")))
			mapField2.setText("");
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		switch(etape){
		case 0:
		if (isInteger(mapField1.getText()) && isInteger(mapField2.getText())) {
			LOGGER.info("create button pressed");
			int w = Integer.parseInt(mapField1.getText());
			int h = Integer.parseInt(mapField2.getText());
				if(w < 11  && h < 11 && garden == null){
					garden = new Garden(w, h);
					addDescendant(garden);
					topPan.removeAll();
					topPan.add(new JLabel("Select and place your eggs and rocks, then valid"));
					validButton.setText("valid");
					topPan.add(validButton);
					topPan.validate();
					etape++;
					for(int i = 0; i < 5; i++){
						buttons[i].setVisible(true);
					}
				}
			}
		break;
		case 1:
			for(int i = 0; i < 9; i++){
				if(i > 0 && i < 5){
					buttons[i].setVisible(false);
				}else{
					buttons[i].setVisible(true);
				}
			}
			etape++;
			EggHunt.getInstance().getWin().setCursor(EggHunt.IMGP.concat("Curseur par défaut.png"));
			topPan.removeAll();
			topPan.add(new JLabel("new kid:"));
			mapField1.setText("kid name");
			topPan.add(mapField1);
			mapField2.setText("kid instructions");
			topPan.add(mapField2);
			topPan.add(new JLabel("A:walk, G:left and D:right (ex: ADAAGA)"));
			validButton.setText("add");
			topPan.add(validButton);
			topPan.add(finishButton);
			topPan.validate();
			break;
		case 2:
			switch(e.getActionCommand()){
			case "add":
				if(newChild != null && !mapField1.getText().isEmpty() && !mapField1.getText().equals("kid name") 
									&& !mapField2.getText().isEmpty() && !mapField2.getText().equals("kid instructions")
									&& instOK(mapField2.getText())){
					
					newChild.setName(mapField1.getText());
					newChild.setInstructions(getInst(mapField2.getText()));
					garden.getDescendants().set(garden.getDescendants().size()-1, newChild);
					newChild = null;
					mapField1.setText("");
					mapField2.setText("");
					EggHunt.getInstance().getWin().setCursor(EggHunt.IMGP.concat("Curseur par défaut.png"));
					
				}
				break;
			case "finish":
				if(newChild == null){
				etape++;
				topPan.removeAll();
				mapField1.setText("map file");
				topPan.add(new JLabel("map file name:"));
				topPan.add(mapField1);
				mapField2.setText("kid file");
				topPan.add(new JLabel("children file name:"));
				topPan.add(mapField2);
				validButton.setText("save");
				topPan.add(validButton);
				topPan.validate();
				EggHunt.getInstance().getWin().setCursor(EggHunt.IMGP.concat("Curseur par défaut.png"));
				}
				break;
	
			}
			break;
		case 3:
			if(!mapField1.getText().isEmpty() && !mapField1.getText().equals("map name") 
			&& !mapField2.getText().isEmpty() && !mapField2.getText().equals("kid name")
			){
				
				CsvDao.writeCSVsFromGarden(garden, mapField1.getText(), mapField2.getText());
				LOGGER.info("fichiers CSV créés");
				EggHunt.getInstance().setViewChoice(Menu.ID);
				JOptionPane.showMessageDialog(EggHunt.getInstance().getWin(), "Fichiers CSVs crées");
				EggHunt.getInstance().getWin().setCursor(EggHunt.IMGP.concat("Curseur par défaut.png"));
			}
			break;
		}
	}
	
	private ArrayList<Character> getInst(String str) {
		ArrayList<Character> list = new ArrayList<Character>();
		for (int i = 0; i < str.length(); i++) {
			list.add(str.charAt(i));
		}
		return list;
	}
	
	private boolean instOK(String str){
		for(int i=0; i < str.length(); i++){
			if(str.charAt(i) != 'A' && str.charAt(i) != 'G' && str.charAt(i) != 'D'){
				return false;
			}
		}
		return true;
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

		
		
		if(!selection.equals("")){
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		LOGGER.info(EggHunt.getInstance().getWin().getCursor().getName());
		String cursorName = EggHunt.getInstance().getWin().getCursor().getName();
		if(etape != 0 && etape != 3){
			if(isInGarden(e)){
				int xc = getGardenCase(e).x;
				int yc = getGardenCase(e).y;
				
				
				LOGGER.info("est dans:"+xc+yc);
				if(!garden.isCaseBusy(yc, xc) && !cursorName.equals("Curseur par défaut") && etape != 3){
					if(etape == 1){
						switch(cursorName){
						case "egg1.png":
							garden.addEgg(xc, yc, 1, garden);
							break;
	
						case "egg2.png":
							garden.addEgg(xc, yc, 2, garden);
							break;
	
						case "egg3.png":
							garden.addEgg(xc, yc, 3, garden);
		
							break;
	
						case "rock.png":
							garden.addRocks(xc, yc, garden);
		 
							break;
						}
					}
					else if(etape == 2 && newChild == null){
						switch(cursorName){
						case "kidN.png":
							newChild = new Child(xc, yc, 'N', null, null, garden);
							garden.addChild(newChild);
							break;
	
						case "kidE.png":
							newChild = new Child(xc, yc, 'E', null, null, garden);
							garden.addChild(newChild);
							break;
	
						case "kidS.png":
							newChild = new Child(xc, yc, 'S', null, null, garden);
							garden.addChild(newChild);
							break;
	
						case "kidW.png":
							newChild = new Child(xc, yc, 'W', null, null, garden);
							garden.addChild(newChild);
							break;
						}
					}
				}
			}
		}
	}
	
	public boolean isInGarden(MouseEvent e){
		return e.getX() - 25> garden.x && e.getX() - 25 < garden.x+w && e.getY() - 25> garden.y && e.getY() - 25 < garden.y+h;
	}
	public Point getGardenCase(MouseEvent e){
			int xc = (e.getX()-garden.x - 25)/Garden.WC;
			int yc = (e.getY()-garden.y - 25)/Garden.WC;
			Point p = new Point(xc, yc);
		return p;
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
