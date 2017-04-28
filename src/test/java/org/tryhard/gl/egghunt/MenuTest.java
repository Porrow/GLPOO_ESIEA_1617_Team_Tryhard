package org.tryhard.gl.egghunt;

import junit.framework.TestCase;

public class MenuTest extends TestCase {
	public MenuTest(String testName) {
		super(testName);
	}
	

	public void testMenu() {

		
		

		try {
			Selection m = new Selection();
			assertNotNull(m.getDescendants().get(2));
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}

}
