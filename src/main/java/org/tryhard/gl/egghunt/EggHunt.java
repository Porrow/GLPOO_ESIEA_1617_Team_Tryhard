package org.tryhard.gl.egghunt;

import org.tryhard.gl.egghunt.gui.Window;
import java.util.ArrayList;

public final class EggHunt {

	private static EggHunt instance = null;

	private ArrayList<GraphicObject> objects = new ArrayList<GraphicObject>();

	private EggHunt() {
		new Window();
	}

	public ArrayList<GraphicObject> getObjects() {
		return objects;
	}

	public void addObject(GraphicObject object) {

	}

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

	public static void main(String[] args) {
		getInstance();
	}
}
