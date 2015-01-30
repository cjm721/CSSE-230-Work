package bstcheck;

// BinaryNode class; stores a node in a tree.
//
// CONSTRUCTION: with (a) no parameters, or (b) an Object,
//     or (c) an Object, left child, and right child.
//
// *******************PUBLIC OPERATIONS**********************
// int size( )            --> Return size of subtree at node
// int height( )          --> Return height of subtree at node
// void printPostOrder( ) --> Print a postorder tree traversal
// void printInOrder( )   --> Print an inorder tree traversal
// void printPreOrder( )  --> Print a preorder tree traversal
// BinaryNode duplicate( )--> Return a duplicate tree

import java.util.ArrayList;

/**
 * Binary node class with recursive routines to compute size and height.
 */
final class BinaryNode {
	
	private char element;
	private BinaryNode left;
	private BinaryNode right;
	private static String indentString = "                                                                                              ";

	public BinaryNode() {
		this('#', null, null);
	}

	public BinaryNode(char theElement, BinaryNode lt, BinaryNode rt) {
		element = theElement;
		left = lt;
		right = rt;
	}

	// Print tree rooted at current node using preorder traversal.
	public void printPreOrder(int indent, char rightOrLeft) {
		System.out.print(indentString.substring(0, indent * 6));
		System.out.println(rightOrLeft + ": " + element); // Node
		if (left != null)
			left.printPreOrder(indent + 1, 'L'); // Left
		if (right != null)
			right.printPreOrder(indent + 1, 'R'); // Right
	}

	// Print tree rooted at current node using inorder traversal.
	public void printInOrder() {
		if (left != null)
			left.printInOrder(); // Left
		System.out.println("(" + element + ")"); // Node
		if (right != null)
			right.printInOrder(); // Right
	}

	public String inOrder(String current) {
		if (left != null)
			current = left.inOrder(current);
		current += ("(" + element + ")");
		if (right != null)
			current = right.inOrder(current);
		return current;
	}

	public char getElement() {
		return element;
	}

	public BinaryNode getLeft() {
		return left;
	}

	public BinaryNode getRight() {
		return right;
	}

	public void setElement(char x) {
		element = x;
	}

	public void setLeft(BinaryNode t) {
		left = t;
	}

	public void setRight(BinaryNode t) {
		right = t;
	}

}
