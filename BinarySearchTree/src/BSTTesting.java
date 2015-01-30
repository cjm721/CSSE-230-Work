import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BSTTesting {
	
	private static int points = 0;

	@Test
	public void testInsert(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals("[]", b.toString());
		points += 1;
		b.insert(7);
		assertEquals("[7]", b.toString());
		points += 2;
		b.insert(4);
		assertEquals("[4, 7]", b.toString());	
		b.insert(10);
		assertEquals("[4, 7, 10]", b.toString());	
		b.insert(2);
		assertEquals("[2, 4, 7, 10]", b.toString());	
		b.insert(5);
		assertEquals("[2, 4, 5, 7, 10]", b.toString());	
		points += 12;
		assertFalse(b.insert(5));
		points += 2;
		assertTrue(b.insert(1));
		points += 2;
		
		try {
			b.insert(null);
			fail("Did not throw IllegalArgumentException");
		} catch (Exception e){
			if (!(e instanceof IllegalArgumentException)) {
				fail("Did not throw IllegalArgumentException");				
			}
		}
		points += 1;
	}
	
	@Test
	public void testHeight(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals(-1, b.height());
		points += 1;
		
		b.insert(3);
		assertEquals(0, b.height());
		points += 2;
		
		b.insert(4);
		b.insert(5);
		assertEquals(2, b.height());
		points += 2;
	}

	@Test
	public void testSize(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals(0, b.size());
		points += 1;
		
		b.insert(3);
		assertEquals(1, b.size());
		points += 2;
		
		b.insert(4);
		b.insert(5);
		assertEquals(3, b.size());
		points += 1;
		b.insert(5);
		assertEquals(3, b.size());
		points += 1;
	}

	@Test
	public void testIsEmpty(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertTrue(b.isEmpty());
		points += 1;
		
		b.insert(3);
		assertFalse(b.isEmpty());
		points += 2;
	}

	@Test
	public void testToArrayList(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals(new ArrayList<Object>(), b.toArrayList());
		points += 2;
		
		b.insert(3);
		b.insert(4);
		b.insert(5);

		ArrayList<Object> temp = new ArrayList<Object>();
		temp.add(3);temp.add(4);temp.add(5);
		assertEquals(temp, b.toArrayList());
		points += 8;
	}

	@Test
	public void testToArray(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals(0, b.toArray().length);
		points += 1;
		
		b.insert(3);
		b.insert(4);
		b.insert(5);
		Object[] temp = {3,4,5};
		Object[] foo = b.toArray();
		for (int j = 0; j < temp.length; j++){
			assertEquals(temp[j], foo[j]);			
		}
		points += 2;
	}

	@Test
	public void testToString(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals("[]", b.toString());
		points += 1;
		
		b.insert(3);
		b.insert(4);
		b.insert(5);
		assertEquals( "[3, 4, 5]", b.toString());	
		points += 6;
	}
	
	@Test
	public void testPreOrderIterator(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		Iterator<Integer> i = b.preOrderIterator();
		assertFalse(i.hasNext());
		points += 1;
		
		b.insert(3);
		b.insert(4);
		b.insert(5);
		b.insert(1);
		b.insert(0);
		b.insert(2);

		i = b.preOrderIterator();
		int k = 0;
		Object[] temp = {3, 1, 0, 2, 4, 5};
		boolean[] tempValues = {true, true, true, true, true, false};
		assertEquals(true, i.hasNext());
		while (i.hasNext()){
			assertEquals(temp[k], i.next());	
			assertEquals(tempValues[k++], i.hasNext());
		}
		points += 11;
		try {
			i.next();
			fail("Did not throw NoSuchElementException");
		} catch (Exception e){
			if (!(e instanceof NoSuchElementException)) {
				fail("Did not throw NoSuchElementException");				
			}
		}
		points += 1;
		try {
			i = b.preOrderIterator();
			i.next();
			b.insert(99);
			i.next();
			fail("Did not throw ConcurrentModificationException");
		} catch (Exception e){
			if (!(e instanceof ConcurrentModificationException)) {
				fail("Did not throw ConcurrentModificationException");				
			}
		}
		points += 2;
	}

	@Test
	public void testIterator(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		Iterator<Integer> i = b.iterator();
		assertFalse(i.hasNext());
		points += 1;
		
		b.insert(3);
		b.insert(4);
		b.insert(5);
		b.insert(1);
		b.insert(0);
		b.insert(2);

		i = b.iterator();
		int k = 0;
		Object[] temp = {0, 1, 2, 3, 4, 5};
		boolean[] tempValues = {true, true, true, true, true, false};
		assertEquals(true, i.hasNext());		
		while (i.hasNext()){
			assertEquals(temp[k], i.next());	
			assertEquals(tempValues[k++], i.hasNext());
		}
		points += 8;
		try {
			i.next();
			fail("Did not throw NoSuchElementException");
		} catch (Exception e){
			if (!(e instanceof NoSuchElementException)) {
				fail("Did not throw NoSuchElementException");				
			}
		}
		points += 1;
		try {
			i = b.iterator();
			i.next();
			b.insert(99);
			i.next();
			fail("Did not throw ConcurrentModificationException");
		} catch (Exception e){
			if (!(e instanceof ConcurrentModificationException)) {
				fail("Did not throw ConcurrentModificationException");				
			}
		}
		points += 2;
	}

	@Test
	public void testRemove(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();
		assertEquals("[]", b.toString());
		points += 1;
		//removal from empty tree
		assertFalse(b.remove(7));
		points += 1;

		// remove just root
		b.insert(4);
		assertTrue(b.remove(4));
		assertEquals("[]", b.toString());	
		points += 2;

		// remove right child in simple tree
		b.insert(10);
		b.insert(4);
		b.insert(14);
		assertTrue(b.remove(14));
		points += 1;
		Integer[] a = {10, 4};
		boolean[] bool = {true, false};
		Iterator<Integer> i = b.preOrderIterator();
		assertTrue(i.hasNext());
		for (int k = 0; k < a.length; k++){
			assertEquals(a[k], i.next());
			assertEquals(bool[k], i.hasNext());
		}
		points += 2;
		
		// remove left child in simple tree
		b.insert(14);
		
		//Create an iterator to verify that a ConcurrentModificationException is thrown
		i = b.preOrderIterator();
		assertTrue(b.remove(4));
		try{
			i.next();
			fail("Did not throw ConcurrentModificationException");
		}catch(Exception e){
			if (!(e instanceof ConcurrentModificationException)) {
				fail("Did not throw ConcurrentModificationException");				
			}			
		}
		points += 2;
		
		a[0] = 10; a[1] = 14;
		i = b.preOrderIterator();
		assertTrue(i.hasNext());
		for (int k = 0; k < a.length; k++){
			assertEquals(a[k], i.next());
			assertEquals(bool[k], i.hasNext());
		}
		points += 2;
	
		// remove root in simple tree
		b.insert(4);
		assertTrue(b.remove(10));
		a[0] = 4; a[1] = 14;
		i = b.preOrderIterator();
		assertTrue(i.hasNext());
		for (int k = 0; k < a.length; k++){
			assertEquals(a[k], i.next());
			assertEquals(bool[k], i.hasNext());
		}
		points += 3;
		
		// Remove null element
		try {
			b.remove(null);
			fail("Did not throw IllegalArgumentException");
		} catch (Exception e){
			if (!(e instanceof IllegalArgumentException)) {
				fail("Did not throw IllegalArgumentException");				
			}
		}
		points += 2;
		
		// Remove leaf from complex tree.
		b = new BinarySearchTree<Integer>();
		b.insert(10);
		b.insert(15);
		b.insert(5);
		b.insert(2);
		b.insert(7);
		b.insert(1);
		b.insert(3);
		b.remove(7);
		assertEquals("[1, 2, 3, 5, 10, 15]", b.toString());
		Integer[] m = {10, 5, 2, 1, 3, 15};
		boolean boo[] = {true, true, true, true, true, false}; 
		i = b.preOrderIterator();
		assertTrue(i.hasNext());
		for (int k = 0; k < m.length; k++){
			assertEquals(m[k], i.next());
			assertEquals(boo[k], i.hasNext());
		}
		points += 5;
		
		// Remove node with 1 child from complex tree.	
		b.remove(5);
		assertEquals("[1, 2, 3, 10, 15]", b.toString());
		Integer[] n = {10, 2, 1, 3, 15};
		boolean boo2[] = {true, true, true, true, false}; 
		i = b.preOrderIterator();
		assertTrue(i.hasNext());
		for (int k = 0; k < n.length; k++){
			assertEquals(n[k], i.next());
			assertEquals(boo2[k], i.hasNext());
		}
		points += 5;
		
		// Remove node with 2 children from complex tree.	
		b.remove(10);
		assertEquals("[1, 2, 3, 15]", b.toString());
		Integer[] p = {3, 2, 1, 15};
		boolean boo3[] = {true, true, true, false}; 
		i = b.preOrderIterator();
		assertTrue(i.hasNext());
		for (int k = 0; k < p.length; k++){
			assertEquals(p[k], i.next());
			assertEquals(boo3[k], i.hasNext());
		}
		points += 2;
		
		//removal of non-existing element
		assertFalse(b.remove(7));
		points += 2;
		

	}
	
	@Test
	public void testRemoveInPreOrderIterator(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();

		// Testing exception throwing on empty tree.
		Iterator<Integer> i = b.preOrderIterator();
		try {
			i.remove();
			fail("Did not throw IllegalStateException");
		} catch (Exception e){
			if (!(e instanceof IllegalStateException)) {
				fail("Did not throw IllegalStateException");				
			}
		}
		points += 1;
		
		b.insert(5);
		b.insert(3);
		b.insert(7);
		i = b.preOrderIterator();
		
		// Testing exception throwing when next() has not been
		// called yet.
		assertTrue(i.hasNext());
		try {
			i.remove();
			fail("Did not throw IllegalStateException");
		} catch (Exception e){
			if (!(e instanceof IllegalStateException)) {
				fail("Did not throw IllegalStateException");				
			}
		}
		points += 1;
		
		i.next();
		assertTrue(i.hasNext());
		i.next();
		i.remove();
		assertEquals("[5, 7]", b.toString());
		points += 1;
		
		try {
			i.remove();
			fail("Did not throw IllegalStateException");
		} catch (Exception e){
			if (!(e instanceof IllegalStateException)) {
				fail("Did not throw IllegalStateException");				
			}
		}
		points += 1;
		try {
			b.remove(7);
			i.next();
			fail("Did not throw ConcurrentModificationException");
		} catch (Exception e){
			if (!(e instanceof ConcurrentModificationException)) {
				fail("Did not throw ConcurrentModificationException");				
			}
		}
		points += 1;
	}

	@Test
	public void testRemoveInInOrderIterator(){
		BinarySearchTree<Integer> b = new BinarySearchTree<Integer>();

		// Testing exception throwing on empty tree.
		Iterator<Integer> i = b.iterator();
		try {
			i.remove();
			fail("Did not throw IllegalStateException");
		} catch (Exception e){
			if (!(e instanceof IllegalStateException)) {
				fail("Did not throw IllegalStateException");				
			}
		}
		points += 1;
		
		b.insert(5);
		b.insert(3);
		b.insert(7);
		i = b.iterator();
		
		// Testing exception throwing when next() has not been
		// called yet.
		assertTrue(i.hasNext());
		try {
			i.remove();
			fail("Did not throw IllegalStateException");
		} catch (Exception e){
			if (!(e instanceof IllegalStateException)) {
				fail("Did not throw IllegalStateException");				
			}
		}
		points += 1;
		
		i.next();
		assertTrue(i.hasNext());
		i.next();
		i.remove();
		assertEquals("[3, 7]", b.toString());
		points += 1;
		
		try {
			i.remove();
			fail("Did not throw IllegalStateException");
		} catch (Exception e){
			if (!(e instanceof IllegalStateException)) {
				fail("Did not throw IllegalStateException");				
			}
		}
		points += 1;
		try {
			b.remove(7);
			i.next();
			fail("Did not throw ConcurrentModificationException");
		} catch (Exception e){
			if (!(e instanceof ConcurrentModificationException)) {
				fail("Did not throw ConcurrentModificationException");				
			}
		}
		points += 1;
	}
	
        @AfterClass
	public static void testNothing(){
		System.out.println(points);
	}
	
	
}
