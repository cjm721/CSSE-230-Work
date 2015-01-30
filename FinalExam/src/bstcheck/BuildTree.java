package bstcheck;


public class BuildTree {

	private static String contentString;
	private static String shapeString;
	private static int positionInStrings;
	
	
	public static BinaryNode buildTree(String contents, String shape){
		if (contents.length() == 0)
			return null;
		
		contentString = contents;
		shapeString = shape;
		 positionInStrings = 0;

		return buildTree();
	}
	
	public static BinaryNode buildTree() {
		BinaryNode left = null, right = null;
		char element = contentString.charAt(positionInStrings);
		switch (shapeString.charAt(positionInStrings++)) {
			case 'L': left = buildTree(); 
			          break;
			case '2': left = buildTree();
			case 'R': right = buildTree();
			case '0':
		}
		return new BinaryNode(element, left, right);
	}
}