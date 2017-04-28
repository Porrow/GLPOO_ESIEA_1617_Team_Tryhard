package org.tryhard.gl.egghunt.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.tryhard.gl.egghunt.Garden;
import org.tryhard.gl.egghunt.GraphicObject;
import org.tryhard.gl.egghunt.Obstacle;
import org.apache.log4j.Logger;
import org.tryhard.gl.egghunt.Child;
import org.tryhard.gl.egghunt.Egg;
import org.tryhard.gl.egghunt.EggHunt;

/**
 * Classe permetttant l'acces aux donnée d'un jardin
 * 
 * @author menuiserie
 *
 */
public class CsvDao {

	private static final Logger LOGGER = Logger.getLogger(EggHunt.class);

	public Garden getGardenAndChilds(String gardenFilePath, String childrenFilePath) {

		File fg = new File(gardenFilePath);
		File fc = new File(childrenFilePath);
		if (!fg.exists() || !fc.exists()) {
			LOGGER.error("Impossible de trouver certains CSV");
			System.exit(-1);
		}
		List<String> strsg = getLignesFromFile(fg);
		List<String> strsc = getLignesFromFile(fc);
		Garden g = getGardenFromTextLines(strsg);
		getChildrenFromTextLines(g, strsc);

		return g;
	}

	public Garden getGardenFromTextLines(List<String> strs) {
		Garden g = null;
		for (String s : strs) {
			String[] slt = s.split(" ");
			switch (slt[0]) {
			case "J":
				int jx = Integer.parseInt(slt[1]);
				int jy = Integer.parseInt(slt[2]);
				g = new Garden(jx, jy);
				break;

			case "R":
				String[] slr = slt[1].split("-");
				int rx = Integer.parseInt(slr[0]);
				int ry = Integer.parseInt(slr[1]);
				g.addRocks(rx, ry, g);
				break;

			case "C":
				String[] slc = slt[1].split("-");
				int cx = Integer.parseInt(slc[0]);
				int cy = Integer.parseInt(slc[1]);
				int nb = Integer.parseInt(slt[2]);
				g.addEgg(cx, cy, nb, g);
				break;
			}
		}
		return g;
	}

	public void getChildrenFromTextLines(Garden g, List<String> strs) {
		for (String s : strs) {
			String[] sc = s.split(" ");
			String[] sPos = sc[1].split("-");
			int cx = Integer.parseInt(sPos[0]);
			int cy = Integer.parseInt(sPos[1]);
			char o = sc[2].charAt(0);
			ArrayList<Character> inst = stringToArrayListChar(sc[3]);
			String name = sc[4];
			g.addChild(cx, cy, o, inst, name, g);
		}
	}

	private ArrayList<Character> stringToArrayListChar(String str) {
		ArrayList<Character> list = new ArrayList<Character>();
		for (int i = 0; i < str.length(); i++) {
			list.add(str.charAt(i));
		}
		return list;
	}
	
	public static void writeCSVsFromGarden(Garden g, String mapName, String childrenName){
		final String gpath = EggHunt.CSVP.concat(mapName).concat(".csv");
		final String cpath = EggHunt.CSVP.concat(childrenName).concat(".csv");
        final File gfile = new File(gpath);
        final File cfile = new File(cpath);
        try {
            // Creation des fichier
            gfile .createNewFile();
            cfile .createNewFile();
            // creation des writer
            final FileWriter gwriter = new FileWriter(gfile);
            final FileWriter cwriter = new FileWriter(cfile);
            try {
            	String str = "J "+g.getXc()+" "+g.getYc();
            	gwriter.write(str);
            	gwriter.write("\n");
            		for(GraphicObject go : g.getDescendants()){
            			if(go instanceof Egg){
            				Egg e = (Egg)go;
            				str = "C "+ e.getXc()+"-"+e.getYc()+" "+e.getNb();
            				gwriter.write(str);
            				gwriter.write("\n");
            			}else if(go instanceof Obstacle){
            				Obstacle o = (Obstacle)go;
            				str = "R "+ o.getXc()+"-"+o.getYc();
            				gwriter.write(str);
            				gwriter.write("\n");
            			}else if(go instanceof Child){
            				Child c = (Child)go;
            				str = "E "+c.getXC()+"-"+c.getYC()+" "+c.getOrientation()+" "+c.getInstructionsToString()+" "+c.getName();
            				cwriter.write(str);
            				cwriter.write("\n");
            			}
            		}
            } finally {
                gwriter.close();
                cwriter.close();
            }
        } catch (Exception e) {
            System.out.println("Impossible de creer un ou plusieurs fichier");
        }
	}

	/**
	 * Lis les lignes du fichier texte représentant les informations d'un jardin
	 * 
	 * @return la liste des lignes du fichier
	 */
	private List<String> getLignesFromFile(File file) {

		// LOGGER.debug("getLignesFromFile");

		final List<String> lignes = new ArrayList<String>();

		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			for (String ligne = br.readLine(); ligne != null; ligne = br.readLine()) {

				// Suppression des espaces en trop
				ligne = ligne.trim();
				// Filtre des lignes vides
				if (ligne.isEmpty()) {
					continue;
				}
				// Filtre des lignes de commentaire
				if (ligne.startsWith("#")) {
					continue;
				}
				lignes.add(ligne);
			}
		} catch (IOException e) {
			// LOGGER.error("Lecture impossible", e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// LOGGER.error("Fermeture impossible", e);
				}
			}
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					// LOGGER.error("Fermeture impossible", e);
				}
			}
		}

		return lignes;

	}
}
