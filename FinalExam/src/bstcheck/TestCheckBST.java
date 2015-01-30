package bstcheck;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCheckBST {
	
	static int totalBSTPoints = 0;
	static int possibleBSTPoints = 0;
	static int bstMultiplier = 1;
	
	static BinaryNode t1, t2, t3, t4, t5, t6, t7, t8, t9;
	
	@BeforeClass
	public static void setUp() {
	    t1 = BuildTree.buildTree("", "");     // BST
	    t2 = BuildTree.buildTree("a", "0");  // BST
	    t3 = BuildTree.buildTree("cba", "LR0");  // not BST
	    t4 = BuildTree.buildTree("cab", "LR0");  // BST
	    t5 = BuildTree.buildTree("dbace", "22000");  // BST
	    t6 = BuildTree.buildTree("pebacjgdihlmkn", "L2200220200200");  // not BST
	    t7 = BuildTree.buildTree("mgdbacefjhnkqptsu", "222200R020L020200");  // not BST
	    t8 = BuildTree.buildTree("mgdbacefjhlkqptsvux", "222200R020L02020200");  // BST
	    t9 = BuildTree.buildTree("mgdbacefjhlkqpitsvux", "222200R020L02L020200");  // not BST
	    
	    // The next part is to prevent earning a few points randomly.
	    if (!CheckBST.checkBST(t5) || CheckBST.checkBST(t3)) {
	    	bstMultiplier=0;  // No credit if you don't pass these tests.
	    	System.out.println("BST multiplier tests failed");
	    }
	}
	
	// Each "one...Test" call tests two trees, one that should return true and one that should
	// return false.  This is to ensure that an "always return true" method that a desperate student 
	// might write will not earn any points.
	public void oneBSTTest(BinaryNode t1, boolean ans1, BinaryNode t2, boolean ans2, int pts){
		possibleBSTPoints += pts;
		assertEquals(ans1, CheckBST.checkBST(t1));
		assertEquals(ans2, CheckBST.checkBST(t2));
		totalBSTPoints += pts;
}
	
	/* 
	 * DO NOT CHANGE THIS FILE.  We have provided a main() function in the CheckBST class that
	 * you can use to print any particular tree and to run your BST tests on that tree.
	 * If your code fails a test from this file, you can use that program to check out the particular 
	 * tree where the failure happens 
	 */
	
	//--------------BST tests---------------
	@Test
	public void testBS1() {
		oneBSTTest(t2, true, t3, false, 3);
	}
	
	@Test
	public void testBS2() {
		oneBSTTest(t2, true, t7, false, 5);
	}
	
	
	@Test
	public void testBS3() {
		oneBSTTest(t8, true, t3, false, 5);
	}
	
	@Test
	public void testBS4() {
		oneBSTTest(t4, true, t9, false, 5);
	}
	
	@Test
	public void testBS5() {
		oneBSTTest(t1, true, t3, false, 3);
	}
	
	@Test
	public void testBS6() {
		oneBSTTest(t4, true, t6, false, 4);
	}
	
	
	@AfterClass
	public static void printResults() {
		System.out.println("***** BST:          " + (bstMultiplier*totalBSTPoints) + " / " + possibleBSTPoints);
	}
	


}
