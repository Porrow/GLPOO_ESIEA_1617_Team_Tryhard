package org.tryhard.gl.egghunt;

import junit.framework.TestCase;

public class MapEditorTest extends TestCase {
	public MapEditorTest(String testName) {
		super(testName);
	}
	
	public void testMapEditor(){
		
		
		try {
			MapEditor m = new MapEditor();
			assertNotNull(m.getDescendants().get(7));
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}
	}
}
