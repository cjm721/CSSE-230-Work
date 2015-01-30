/*

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

import org.junit.Test;

public class BSTManualTesting {

	@Test
	public void testHeight(){
		BinarySearchTree b = new BinarySearchTree();
		assertEquals(-1, b.height());
		
		BinaryNodeOld n1 = new BinaryNodeOld(3);
		b = new BinarySearchTree(n1);
		assertEquals(0, b.height());
		
		BinaryNodeOld n2 = new BinaryNodeOld(4);
		BinaryNodeOld n3 = new BinaryNodeOld(5);
		n1.setRightChild(n2);
		n2.setRightChild(n3);
		assertEquals(2, b.height());
	}
	
	@Test
	public void testSize(){
		BinarySearchTree b = new BinarySearchTree();
		assertEquals(0, b.size());
		
		BinaryNodeOld n1 = new BinaryNodeOld(3);
		b = new BinarySearchTree(n1);
		assertEquals(1, b.size());
		
		BinaryNodeOld n2 = new BinaryNodeOld(4);
		BinaryNodeOld n3 = new BinaryNodeOld(5);
		n1.setRightChild(n2);
		n2.setRightChild(n3);
		assertEquals(3, b.size());
	}
	
	@Test
	public void testIsEmpty(){
		BinarySearchTree b = new BinarySearchTree();
		assertTrue(b.isEmpty());
		
		BinaryNodeOld n1 = new BinaryNodeOld(3);
		b = new BinarySearchTree(n1);
		assertFalse(b.isEmpty());
	}
	
	@Test
	public void testToArrayList(){
		BinarySearchTree b = new BinarySearchTree();
		assertEquals(new ArrayList(), b.toArrayList());
		
		BinaryNodeOld n1 = new BinaryNodeOld(3);
		b = new BinarySearchTree(n1);
		BinaryNodeOld n2 = new BinaryNodeOld(4);
		BinaryNodeOld n3 = new BinaryNodeOld(5);
		n1.setRightChild(n2);
		n2.setRightChild(n3);

		ArrayList temp = new ArrayList();
		temp.add(3);temp.add(4);temp.add(5);
		assertEquals(temp, b.toArrayList());
		
		BinaryNodeOld n4 = new BinaryNodeOld(6);
		n1.setLeftChild(n4);
		temp.add(0, 6);
		assertEquals(temp, b.toArrayList());
	}
	
	@Test
	public void testToArray(){
		BinarySearchTree b = new BinarySearchTree();
		assertEquals(0, b.toArray().length);
		
		BinaryNodeOld n1 = new BinaryNodeOld(3);
		b = new BinarySearchTree(n1);
		BinaryNodeOld n2 = new BinaryNodeOld(4);
		BinaryNodeOld n3 = new BinaryNodeOld(5);
		n1.setRightChild(n2);
		n2.setRightChild(n3);
		
		Object[] temp = {3,4,5};
		Object[] foo = b.toArray();
		for (int j = 0; j < temp.length; j++){
			assertEquals(temp[j], foo[j]);			
		}
	}
	
	@Test
	public void testToString(){
		BinarySearchTree b = new BinarySearchTree();
		assertEquals("[]", b.toString());
		
		BinaryNodeOld n1 = new BinaryNodeOld(3);
		b = new BinarySearchTree(n1);
		BinaryNodeOld n2 = new BinaryNodeOld(4);
		BinaryNodeOld n3 = new BinaryNodeOld(5);
		n1.setRightChild(n2);
		n2.setRightChild(n3);

		assertEquals( "[3, 4, 5]", b.toString());			
	}
	
	@Test
	public void testPreOrderIterator(){
		BinarySearchTree b = new BinarySearchTree();
		Iterator i = b.preOrderIterator();
		assertFalse(i.hasNext());
		
		BinaryNodeOld n1 = new BinaryNodeOld(3);
		b = new BinarySearchTree(n1);
		BinaryNodeOld n2 = new BinaryNodeOld(4);
		BinaryNodeOld n3 = new BinaryNodeOld(5);
		n1.setRightChild(n2);
		n2.setRightChild(n3);
		BinaryNodeOld n4 = new BinaryNodeOld(1);
		BinaryNodeOld n5 = new BinaryNodeOld(0);
		BinaryNodeOld n6 = new BinaryNodeOld(2);
		n1.setLeftChild(n4);
		n4.setLeftChild(n5);
		n4.setRightChild(n6);

		i = b.preOrderIterator();
		int k = 0;
		Object[] temp = {3, 1, 0, 2, 4, 5};
		boolean[] tempValues = {true, true, true, true, true, false};
		while (i.hasNext()){
			assertEquals(temp[k], i.next());	
			assertEquals(tempValues[k++], i.hasNext());
		}
		try {
			i.next();
			fail("Did not throw NoSuchElementException");
		} catch (Exception e){
			if (!(e instanceof NoSuchElementException)) {
				fail("Did not throw NoSuchElementException");				
			}
		};
	}
	
	@Test
	public void testIterator(){
		BinarySearchTree b = new BinarySearchTree();
		Iterator i = b.iterator();
		assertFalse(i.hasNext());
		
		BinaryNodeOld n1 = new BinaryNodeOld(3);
		b = new BinarySearchTree(n1);
		BinaryNodeOld n2 = new BinaryNodeOld(4);
		BinaryNodeOld n3 = new BinaryNodeOld(5);
		n1.setRightChild(n2);
		n2.setRightChild(n3);
		BinaryNodeOld n4 = new BinaryNodeOld(1);
		BinaryNodeOld n5 = new BinaryNodeOld(0);
		BinaryNodeOld n6 = new BinaryNodeOld(2);
		n1.setLeftChild(n4);
		n4.setLeftChild(n5);
		n4.setRightChild(n6);

		i = b.iterator();
		int k = 0;
		Object[] temp = {0, 1, 2, 3, 4, 5};
		boolean[] tempValues = {true, true, true, true, true, false};
		while (i.hasNext()){
			assertEquals(temp[k], i.next());	
			assertEquals(tempValues[k++], i.hasNext());
		}
		try {
			i.next();
			fail("Did not throw NoSuchElementException");
		} catch (Exception e){
			if (!(e instanceof NoSuchElementException)) {
				fail("Did not throw NoSuchElementException");				
			}
		};
	}

	
	@Test
	public void testContains(){
		BinarySearchTree b = new BinarySearchTree();
		assertFalse(b.contains(5));
		BinaryNodeOld n3 = new BinaryNodeOld(3);
		b = new BinarySearchTree(n3);
		BinaryNodeOld n4 = new BinaryNodeOld(4);
		BinaryNodeOld n5 = new BinaryNodeOld(5);
		n3.setRightChild(n4);
		n4.setRightChild(n5);
		
		BinaryNodeOld n10 = new BinaryNodeOld(10);
		n3.setLeftChild(n10);
		BinaryNodeOld n7 = new BinaryNodeOld(7);
		n5.setLeftChild(n7);
		assertTrue(b.contains(3));
		assertTrue(b.contains(7));
		assertFalse(b.contains(20));
	}
	
	@Test
	public void testRemove(){
		BinarySearchTree b = new BinarySearchTree();
		b.insert(4);
		assertTrue(b.remove(4));
	}
}

*/