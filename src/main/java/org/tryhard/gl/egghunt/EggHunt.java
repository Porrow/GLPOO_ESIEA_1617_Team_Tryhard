package org.tryhard.gl.egghunt;

import org.tryhard.gl.egghunt.gui.Window;
import java.util.ArrayList;

public final class EggHunt {

	private static EggHunt instance = null;
	private static ArrayList<Container> containers = new ArrayList<Container>();
	private static int view = 0;

	private EggHunt() {
		load();
		new Window();
	}

	private void load() {
		containers.add(new Container());
	}

	public static ArrayList<Container> getContainers() {
		return containers;
	}

	public static int getView() {
		return view;
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
