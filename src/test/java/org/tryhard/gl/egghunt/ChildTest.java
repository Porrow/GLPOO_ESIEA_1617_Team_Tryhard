package org.tryhard.gl.egghunt;


import java.util.ArrayList;
import junit.framework.TestCase;

public class ChildTest extends TestCase {

	public ChildTest(String testName) {
		super(testName);
	}
	

	public void testPickUp() {
		int x = 10;
		int y = 10;
		int xc = 5;
		int yc = 5;
		Character o = new Character('A');
		ArrayList<Character> inst = new ArrayList<Character>();
		Garden g = new Garden(x, y);
		g.addEgg(5, 5, 1, g);
		Child c = new Child(xc, yc, o, inst, "", g);

		try {
			c.pickupEgg();
			assertNotNull(c.getInstructions().get(0));

		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}
	
	
	
	public void testCheckCollisionWithObstacle() {
		int x = 10;
		int y = 10;
		int xc = 5;
		int yc = 5;
		Character o = new Character('A');
		ArrayList<Character> inst = new ArrayList<Character>();
		Garden g = new Garden(x, y);
		g.addRocks(xc, yc, g);
		Child c = new Child(xc, yc, o, inst, "", g);
		try {
			assertEquals(c.checkCollision(xc, yc), true);

		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}
	
	
	
	public void testCheckCollisionOutOfBorder() {
		int x = 10;
		int y = 10;
		int xc = 5;
		int yc = 5;
		Character o = new Character('A');
		ArrayList<Character> inst = new ArrayList<Character>();
		Garden g = new Garden(x, y);
		Child c = new Child(xc, yc, o, inst, "", g);
		try {
			assertEquals(c.checkCollision(g.getArray().length+1, g.getArray().length+1), true);
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}
	
	
	
	public void testCheckCollisionWithChild() {
		int x = 10;
		int y = 10;
		int xc = 5;
		int yc = 5;
		Character o = new Character('A');
		ArrayList<Character> inst = new ArrayList<Character>();
		Garden g = new Garden(x, y);
		g.addChild(xc, yc, o, inst, "", g);
		Child c = new Child(xc, yc, o, inst, "", g);
		try {
			assertEquals(c.checkCollision(xc,yc), true);
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}
	
	
	
	public void testMoveWithFreeMovement() {
		int x = 10;
		int y = 10;
		int xc = 5;
		int yc = 5;
		ArrayList<Character> inst = new ArrayList<Character>();
		Garden g = new Garden(x, y);
		g.addRocks(x-1, y-1, g);
		Child c = new Child(xc, yc, 'N', inst, "", g);
		try {
			assertEquals(c.move(), true);
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}
	
	
	
	public void testMoveWithoutFreeMovementToNorth() {
		int x = 10;
		int y = 10;
		int xc = 5;
		int yc = 5;
		ArrayList<Character> inst = new ArrayList<Character>();
		Garden g = new Garden(x, y);
		g.addRocks(xc, yc-1, g);
		Child c = new Child(xc, yc, 'N', inst, "", g);
		try {
			assertEquals(c.move(), false);

		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}
	
	
	
	public void testMoveWithoutFreeMovementToSouth() {
		int x = 10;
		int y = 10;
		int xc = 5;
		int yc = 5;
		ArrayList<Character> inst = new ArrayList<Character>();
		Garden g = new Garden(x, y);
		g.addRocks(xc, yc+1, g);
		Child c = new Child(xc, yc, 'S', inst, "", g);
		try {
			assertEquals(c.move(), false);
		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}
	
	
	
	public void testMoveWithoutFreeMovementToWest() {
		int x = 10;
		int y = 10;
		int xc = 5;
		int yc = 5;
		ArrayList<Character> inst = new ArrayList<Character>();
		Garden g = new Garden(x, y);
		g.addRocks(xc-1, yc, g);
		Child c = new Child(xc, yc, 'W', inst, "", g);
		try {
			assertEquals(c.move(), false);

		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}
	
	
	
	public void testMoveWithoutFreeMovementToEast() {
		int x = 10;
		int y = 10;
		int xc = 5;
		int yc = 5;
		ArrayList<Character> inst = new ArrayList<Character>();
		Garden g = new Garden(x, y);
		g.addRocks(xc+1, yc, g);
		Child c = new Child(xc, yc, 'E', inst, "", g);
		try {
			assertEquals(c.move(), false);

		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}
	
	
	
	public void testAnimOrientationEast() {
		int x = 10;
		int y = 10;
		int xc = 5;
		int yc = 5;
		ArrayList<Character> inst = new ArrayList<Character>();
		Garden g = new Garden(x, y);
		Child c = new Child(xc, yc, 'E', inst, "", g);
		int xa = c.getX();
		//int ya = c.getY();

		try {
			c.anim();
			assertEquals(xa, c.getX()-c.getDec());

		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}
	
	
	
	public void testAnimOrientationWest() {
		int x = 10;
		int y = 10;
		int xc = 5;
		int yc = 5;
		ArrayList<Character> inst = new ArrayList<Character>();
		Garden g = new Garden(x, y);
		Child c = new Child(xc, yc, 'W', inst, "", g);
		int xa = c.getX();
		//int ya = c.getY();
		try {
			c.anim();
			assertEquals(xa, c.getX()+c.getDec());

		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}
	
	
	
	public void testAnimOrientationSouth() {
		int x = 10;
		int y = 10;
		int xc = 5;
		int yc = 5;
		ArrayList<Character> inst = new ArrayList<Character>();
		Garden g = new Garden(x, y);
		Child c = new Child(xc, yc, 'S', inst, "", g);
		//int xa = c.getX();
		int ya = c.getY();

		try {
			c.anim();
			assertEquals(ya, c.getY()-c.getDec());

		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}
	
	
	
	public void testAnimOrientationNorth() {
		int x = 10;
		int y = 10;
		int xc = 5;
		int yc = 5;
		ArrayList<Character> inst = new ArrayList<Character>();
		Garden g = new Garden(x, y);
		Child c = new Child(xc, yc, 'N', inst, "", g);
		//int xa = c.getX();
		int ya = c.getY();
		try {
			c.anim();
			assertEquals(ya, c.getY()+c.getDec());

		} catch (Exception ex) {
			fail("erreur lors de l'execution du test " + ex);
		}

	}
}
