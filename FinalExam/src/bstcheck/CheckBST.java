package bstcheck;

public class CheckBST {
	
	/**
	 * Is binary tree rooted at root a BST?
	 * @param root
	 * @return true if it is a BST, false otherwise.
	 */
	public static boolean checkBST(BinaryNode root) {
		if(root == null)
			return true;
		

		BinaryNode tempNode = root.getLeft();
		
		//Left
		if(tempNode != null){
			if(tempNode.getElement() < root.getElement()){
				if(checkBST(tempNode)){
					//Some element in leftsub tree is larger then this root.
					if( !(getRightMost(tempNode) < root.getElement()) )
						return false;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}
		tempNode = root.getRight();
		
		//Right
		if(tempNode != null){
			if(tempNode.getElement() > root.getElement()){
				if(checkBST(tempNode)){
					//Some element in right tree is smaller then this root.
					if( (getLeftMost(tempNode) < root.getElement()) )
						return false;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}
		
		return true;
	}
	
	private static char getRightMost(BinaryNode node){
		while(node.getRight() != null){
			node = node.getRight();
		}
		return node.getElement();
	}
	
	private static char getLeftMost(BinaryNode node){
		while(node.getLeft() != null){
			node = node.getLeft();
		}
		return node.getElement();
	}
	
	
	
	/*
	 * THESE ARE NOT THE OFFICIAL UNIT TESTS.  But it may be easier for you to debug each part from here, 
	 * then run the unit tests when you get things working.  THZis code prints a representation of the tree.
	 * You can uncomment the test that you want to run, set breakpoints for th edebugger, etc.
	 */
	public static void main(String[] args) {
		 BinaryNode t1, t2, t3, t4, t5, t6, t7, t8, t9;
		 
		    t1 = BuildTree.buildTree("", "");     // BST
		    t2 = BuildTree.buildTree("a", "0");  // BST
		    t3 = BuildTree.buildTree("cba", "LR0");  // not BST
		    t4 = BuildTree.buildTree("cab", "LR0");  // BST
		    t5 = BuildTree.buildTree("dbace", "22000");  // BST
		    t6 = BuildTree.buildTree("pebacjgdihlmkn", "L2200220200200");  // not BST
		    t7 = BuildTree.buildTree("mgdbacefjhnkqptsu", "222200R020L020200");  // not BST
		    t8 = BuildTree.buildTree("mgdbacefjhlkqptsvux", "222200R020L02020200");  // BST
		    t9 = BuildTree.buildTree("mgdbacefjhlkqpitsvux", "222200R020L02L020200");  // not BST
	    
// To test a tree, uncomment the line that contains the tree you want to print and test.
	    
	//	    testOneTree(t1, "t1"); 
	//	    testOneTree(t2, "t2"); 
	//	    testOneTree(t4, "t4"); 
	//	    testOneTree(t5, "t5"); 
	//      testOneTree(t6, "t6"); 
		    testOneTree(t7, "t7"); 
	//	    testOneTree(t8, "t8"); 
	//	    testOneTree(t9, "t9"); 

	}
	
	// Print the tree and run both tests on it.
	public static void testOneTree(BinaryNode t, String name){
		System.out.println(name +":");
		if (t != null) 
			t.printPreOrder(0, ' '); 
		else 
			System.out.println("null");
		System.out.println("---------------");
		System.out.println("bstCheck: " + checkBST(t));
		System.out.println("---------------");
	}

}
