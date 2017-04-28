package org.tryhard.gl.egghunt;

import java.util.ArrayList;


import org.tryhard.gl.egghunt.gui.ResultsTableModel;

import junit.framework.TestCase;

public class TestResultsTable extends TestCase {
	public TestResultsTable(String testName) {
		super(testName);
	}
	
	public void testValueAtCase0(){
		int x = 10;
		int y = 10;
		int xc = 5;
		int yc = 5;
		Character o = new Character('A');
		ArrayList<Character> inst = new ArrayList<Character>();
		Garden g = new Garden(x, y);
		Child c = new Child(xc, yc, o, inst, "oualid", g);
		ArrayList<Child> array = new ArrayList<Child>();
		array.add(c);
		ResultsTableModel r = new ResultsTableModel(array);
		try {
			assertEquals(r.getValueAt(0, 0), "oualid");
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}
	}
	
	public void testValueAtCase1(){
		int x = 10;
		int y = 10;
		int xc = 5;
		int yc = 5;
		Character o = new Character('A');
		ArrayList<Character> inst = new ArrayList<Character>();
		Garden g = new Garden(x, y);
		Child c = new Child(xc, yc, o, inst, "oualid", g);
		ArrayList<Child> array = new ArrayList<Child>();
		array.add(c);
		ResultsTableModel r = new ResultsTableModel(array);
		try {
			//assertEquals(r.getValueAt(0, 1), "oualid");
			assertEquals(r.getValueAt(0, 1), 0);
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}
	}
	
	public void testValueAtCase2(){
		int x = 10;
		int y = 10;
		int xc = 5;
		int yc = 5;
		Character o = new Character('A');
		ArrayList<Character> inst = new ArrayList<Character>();
		Garden g = new Garden(x, y);
		Child c = new Child(xc, yc, o, inst, "oualid", g);
		ArrayList<Child> array = new ArrayList<Child>();
		array.add(c);
		ResultsTableModel r = new ResultsTableModel(array);
		try {
			assertEquals(r.getValueAt(0, 2), c.getBasket().size());
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}
	}
	
	
}
