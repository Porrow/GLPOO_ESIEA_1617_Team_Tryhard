package org.tryhard.gl.egghunt;

import org.apache.log4j.Logger;
import org.tryhard.gl.egghunt.gui.Window;

import java.awt.BorderLayout;
import java.util.ArrayList;

/**
 * 
 *
 */
public final class EggHunt {

	private static final Logger LOGGER = Logger.getLogger(EggHunt.class);
	public static final String IMGP = "res/img/";
	public static final String CSVP = "res/csv/";
	public static final String GARDENPATH = "res/csv/garden.csv";
	public static final String CHILDRENPATH = "res/csv/children.csv";

	private static EggHunt instance = null;
	private static ArrayList<GraphicObject> views = new ArrayList<GraphicObject>();
	private static int viewChoice = Menu.ID;
	private static Window win;
	private Selection select;
	private MapEditor editor;

	/**
	 * Constructeur de EggHung initialise le jeu et crée la fenêtre d'affichage
	 */
	private EggHunt() {
		LOGGER.debug("Début de l'initialisation...");
		loadViews();
	}

	public Window getWin() {
		return win;
	}
	
	private void loadViews() {
		views.add(new Menu());
		select = new Selection();
		views.add(select);
		views.add(new Game(GARDENPATH, CHILDRENPATH));
		editor = new MapEditor();
		views.add(editor);
	}

	public ArrayList<GraphicObject> getViews() {
		return views;
	}

	public int getViewChoice() {
		return viewChoice;
	}

	public void setViewChoice(int nviewchoice) {
		win.getPan().removeAll();
		viewChoice = nviewchoice;
		if (nviewchoice == Selection.ID) {
			LOGGER.debug("Chargement du panneau de sélection...");
			win.getPan().add(select.getPan(), BorderLayout.NORTH);
			win.getPan().validate();
		}else if(nviewchoice == MapEditor.ID){
			LOGGER.debug("Création fichiers CSV");
			win.getPan().add(editor.getPan(), BorderLayout.NORTH);
			win.getPan().validate();
		}
	}

	public Selection getSelect() {
		return select;
	}

	/**
	 * methode retournant le singleton d' EggHunt
	 * 
	 * @return
	 */
	public static final EggHunt getInstance() {
		if (instance == null) {
			synchronized (EggHunt.class) {
				if (instance == null) {
					instance = new EggHunt();
				}
			}
		}
		return EggHunt.instance;
	}

	/**
	 * fonction principale du programme, créer le singleton EggHunt
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LOGGER.info(Window.TITLE);
		getInstance(); // crée le singleton
		win = new Window();
	}
}
