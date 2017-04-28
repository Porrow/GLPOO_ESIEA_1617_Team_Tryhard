package org.tryhard.gl.egghunt;



import java.util.ArrayList;

import junit.framework.TestCase;

public class GardenTest extends TestCase {
	public GardenTest(String testName) {
		super(testName);
	}
	

	public void testAddRocks() {

		Garden g = new Garden(8, 10);
		

		try {
			g.addRocks(4, 4, g);
			assertNotNull(g.getDescendants().get(0));
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}

	public void testAddEggs() {

		Garden g = new Garden(8, 10);
	

		try {
			g.addEgg(6, 7, 1, g);
			assertNotNull(g.getDescendants().get(0));
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}

	public void testAddChildren() {

		Garden g = new Garden(8, 10);
		ArrayList<Character> inst = new ArrayList<Character>();
		inst.add('A');
		inst.add('A');
		inst.add('D');
		inst.add('A');
		

		try {
			g.addChild(2, 3, 'N', inst, "RomainLeBG", g);
			assertNotNull(g.getDescendants().get(0));
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}
}
