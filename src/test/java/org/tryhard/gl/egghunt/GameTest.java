package org.tryhard.gl.egghunt;

import junit.framework.TestCase;

public class GameTest extends TestCase {
	public GameTest(String testName) {
		super(testName);
	}
	
	public void testGame() {
		
		
		try {
			Game g = new Game(EggHunt.GARDENPATH,EggHunt.CHILDRENPATH);
			assertNotNull(g.getDescendants().get(1));
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}
	
public void testGameTimer() {
		
		
		try {
			Game g = new Game(EggHunt.GARDENPATH,EggHunt.CHILDRENPATH);
			assertEquals(g.getTimer(), 0);
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}
}
