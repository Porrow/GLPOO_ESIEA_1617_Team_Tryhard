package org.tryhard.gl.egghunt;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class EggHuntTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public EggHuntTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(EggHuntTest.class);
	}

	public void testGardenAddRocks() {

		Garden g = new Garden(8, 10);
		g.addRocks(4, 4, g);

		try {

			assertNotNull(g.getDescendants().get(0));
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}

	public void testGardenAddEggs() {

		Garden g = new Garden(8, 10);
		g.addEgg(6, 7, g);

		try {

			assertNotNull(g.getDescendants().get(0));
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}

	public void testGardenAddChildren() {

		Garden g = new Garden(8, 10);
		char[] inst = { 'A', 'A', 'D', 'A' };
		g.addChild(2, 3, 'N', inst, "RomainLeBG", g);

		try {

			assertNotNull(g.getDescendants().get(0));
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}
}
