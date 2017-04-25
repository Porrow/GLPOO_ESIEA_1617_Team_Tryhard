package org.tryhard.gl.egghunt.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.tryhard.gl.egghunt.Garden;

/**
 * Classe permetttant l'acces aux donnée d'un jardin
 * 
 * @author menuiserie
 *
 */
public class CsvGardenDao {

	public static Garden getGarden(String gardenFilePath) {

		File f = new File(gardenFilePath);

		List<String> strs = getLignesFromFile(f);
		return getGardenFromTextLines(strs);

	}

	public static Garden getGardenFromTextLines(List<String> strs) {
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
					g.addRocks(rx, ry);
					break;
	
				case "C":
					String[] slc = slt[1].split("-");
					int cx = Integer.parseInt(slc[0]);
					int cy = Integer.parseInt(slc[1]);
					g.addEgg(cx, cy, Integer.parseInt(slt[2]));
					break;
			}
		}
		return g;
	}

	/**
	 * Lis les lignes du fichier texte représentant les informations d'un jardin
	 * 
	 * @return la liste des lignes du fichier
	 */
	private static List<String> getLignesFromFile(File file) {

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
