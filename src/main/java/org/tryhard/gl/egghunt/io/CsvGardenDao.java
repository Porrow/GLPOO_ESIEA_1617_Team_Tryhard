package org.tryhard.gl.egghunt.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvGardenDao {

	private File file;

	public CsvGardenDao(String path) {
		this.file = new File(path);
		getLignesFromFile();
	}

	//Copier-Coller de ce qu'on trouve dans le TD
	private List<String> getLignesFromFile() {

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
