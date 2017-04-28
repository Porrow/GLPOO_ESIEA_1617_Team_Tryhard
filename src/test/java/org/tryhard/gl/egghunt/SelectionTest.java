package org.tryhard.gl.egghunt;

import junit.framework.TestCase;

public class SelectionTest extends TestCase {

	public SelectionTest(String testName) {
		super(testName);
	}
	
	public void testSelection() {

		
		

		try {
			Selection s = new Selection();
			assertNotNull(s.getDescendants().get(3));
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}
}
