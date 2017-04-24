package org.tryhard.gl.egghunt;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.tryhard.gl.egghunt.io.CsvGardenDao;

public class CsvGardenDaoTest {

	@Test
	public void test() {
		ArrayList<String> strs = new ArrayList<String>();
		strs.add("J 6 5");
		strs.add("C 4-2 1");
		strs.add("C 1-4 3");
		strs.add("R 5-3");
		try {
			CsvGardenDao.getGardenFromTextLines(strs);
			assertTrue(true);
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}

	@Test
	public void test2() {
		try {
			ArrayList<String> strs = new ArrayList<String>();
			CsvGardenDao.getGardenFromTextLines(strs);
			assertTrue(true);
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}
	}

	@Test
	public void test3() {
		ArrayList<String> strs = new ArrayList<String>();
		strs.add("J 6 5");
		strs.add("C 4-2 1");
		strs.add("C 1-4 3");
		strs.add("R 5-3");
		try {
			CsvGardenDao.getGardenFromTextLines(strs);
			assertTrue(true);
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}

}
