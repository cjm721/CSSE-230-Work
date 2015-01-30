package preOrderInOrderBuild;

// This is the class you should modify for problem 2B.

public class PreorderInorderBuild {

	public static BinaryNode buildFromPreOrderInorder(String pre, String in) {
		if(pre.length() == 0)
			return null;
		
		BinaryNode root = new BinaryNode(new Character(pre.charAt(0)), null, null);
	
		String left = in.substring(0, in.indexOf(pre.charAt(0)));
		int rank = left.length();
		
		String right;
		if(rank + 1 == pre.length()) //No right tree
			right = "";
		else{
			right = in.substring(rank+1);
		}
		if(left.length() > 0)
			root.left = buildFromPreOrderInorder(pre.substring(1,1+left.length()), left);
		if(right.length() > 0)
			root.right = buildFromPreOrderInorder(pre.substring(1+left.length()), right);
		
		return root;
	}
}
