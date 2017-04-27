package org.tryhard.gl.egghunt;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.tryhard.gl.egghunt.io.CsvDao;

public class CsvDaoTest {
	
	CsvDao cv = new CsvDao();
	
	@Test
	public void test_getGardenFromTextLines() {
		
		ArrayList<String> strs = new ArrayList<String>();
		strs.add("J 6 5");
		strs.add("C 4-2 1");
		strs.add("C 1-4 3");
		strs.add("R 5-3");
		try {
			Garden g =cv.getGardenFromTextLines(strs);
			assertEquals(g.getDescendants().size(), 3);
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}


	@Test
	public void test_getChildrenFromTextLines() {
		ArrayList<String> strs_garden = new ArrayList<String>();
		ArrayList<String> strs_children = new ArrayList<String>();
		strs_garden.add("J 6 5");
		strs_garden.add("C 4-2 1");
		strs_garden.add("C 1-4 3");
		strs_garden.add("R 5-3");
		
		strs_children.add("E 1-1 E AADADAGA Julien");

		try {
			Garden g = cv.getGardenFromTextLines(strs_garden);
			cv.getChildrenFromTextLines(g, strs_children);
			assertEquals(g.getDescendants().size(), 4);
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}
	

	
	
	
	

}
