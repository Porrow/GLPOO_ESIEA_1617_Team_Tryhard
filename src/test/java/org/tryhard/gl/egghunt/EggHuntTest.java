package org.tryhard.gl.egghunt;

import java.util.ArrayList;

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
	 */  static Test suite() {
		return new TestSuite(EggHuntTest.class);
	}
	
	public void testChildisMovingBoolean() {
		int x = 10;
		int y = 10;
		int xc = 5;
		int yc = 5;
		Character o = new Character('a');
		ArrayList<Character> inst = new ArrayList<Character>();
		Garden g = new Garden(x,y);
		Child c = new Child(xc,yc,o,inst,"",g);

		try {
			
			c.move();
			assertTrue(c.getIsMoving());

			
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}
	

	public void testChildTreatInstructions() {
		
		try {
			

			
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}
	
public void testChildPickUp() {
		int x = 10;
		int y = 10;
		int xc = 5;
		int yc = 5;
		Character o = new Character('A');
		ArrayList<Character> inst = new ArrayList<Character>();
		Garden g = new Garden(x,y);
		g.addEgg(5, 5, 1, g);
		Child c = new Child(xc,yc,o,inst,"",g);
		
		try {
			c.pickupEgg();
			assertNotNull(c.getInstructions().get(0));
			
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}
	
	/*public void testChildisMoving() {
		int x = 10;
		int y = 10;
		int xc = 5;
		int yc = 5;
		
		Character o = new Character('a');
		ArrayList<Character> inst = new ArrayList<Character>();
		Garden g = new Garden(x,y);
		Child c = new Child(xc,yc,o,inst,"",g);

		try {
			
			c.move();
			assertTrue(c.getXC()!= xc || c.getYC() != yc);

			
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}*/

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
		g.addEgg(6, 7, 1, g);

		try {

			assertNotNull(g.getDescendants().get(0));
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}

	public void testGardenAddChildren() {

		Garden g = new Garden(8, 10);
		ArrayList<Character> inst = new ArrayList<Character>();
		inst.add('A');inst.add('A');inst.add('D');inst.add('A');
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
