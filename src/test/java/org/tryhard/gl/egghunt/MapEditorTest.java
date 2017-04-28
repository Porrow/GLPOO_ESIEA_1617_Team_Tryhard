package org.tryhard.gl.egghunt;

import junit.framework.TestCase;

public class MapEditorTest extends TestCase {
	public MapEditorTest(String testName) {
		super(testName);
	}
	
	public void testMapEditorDescendants(){
		
		
		try {
			MapEditor m = new MapEditor();
			assertNotNull(m.getDescendants().get(7));
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}
	}
	
@SuppressWarnings("deprecation")
public void testMapEditorJPanelElements(){
		
		
		try {
			MapEditor m = new MapEditor();
			assertEquals(m.getPan().countComponents(), 5);
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}
	}
}
