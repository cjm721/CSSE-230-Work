package queens;
/**
 * Uses the Queen interface and implementations to solve the non-attacking chess
 * queens problem.
 * 
 * @author Curt Clifton, based on previous work by Claude Anderson
 *  redone for Exam 2 by Claude Anderson
 */
public class Main {
	private static int SIZE = 5; // How big is the board?


	/**
	 * Finds and prints all solutions for the given board size.
	 * This is just here for reference; you do not need to run or change it for this exam.
	 * Furthermore, it is likely that the changes you make in other file(s) will cause this
	 * findSolutions method to no longer work as it did before.  That
	 * is okay, since it will not be called; just get findPermutations to work.
	 * 
	 * @param size
	 * @return the number of solutions found
	 */
	static int findSolutions(int size) {
		// Sets up the board:
		Queen previousQueen = new NullQueen();
		for (int i = 1; i <= size; i++) {
			Queen newQueen = new RealQueen(previousQueen, i, size);
			previousQueen = newQueen;
		}

		// Looks for the solutions:
		final Queen lastQueen = previousQueen;
		int solutionCount = 0;
		if (lastQueen.findFirst()) {
			System.out.println("SOLUTION: " + previousQueen);
			solutionCount++;

			while (lastQueen.findNext()) {
				System.out.println("SOLUTION: " + previousQueen);
				solutionCount++;
			}
		}
		
		return solutionCount;
	}
	
	
	/**
	 * This method will be similar to findSolutions, except that it will return 
	 * an ordered array of all permutations of the numbers {1, 2, ..., size}.
	 * You can do this by making  a MINOR change to the original queens code and 
	 * modifying this function so it collects the generated permutations in an array.
	 * @param size The length of each permutation
	 * @return
	 */
	static String[] findPermutations(int size) {
		int count = 1;
		for (int i=2; i <= size; i++) 
			count *= i;
		String[] result = new String[count];
		// Now you have an empty array to be filled in with permutations.
		
		// Linked list setup is the same as for original Queens problem
		Queen previousQueen = new NullQueen();
		for (int i = 1; i <= size; i++) {
			Queen newQueen = new RealQueen(previousQueen, i, size);
			previousQueen = newQueen;
		}

		// Looks for the permutations:
		final Queen lastQueen = previousQueen;
		// TODO:  Fill in the details here (a few lines of code).
		//        Then change other file(s) so that permuations are actually generated.
		// Looks for the solutions:
		previousQueen.findFirst();
		for(int i = 0; i < result.length; i++){
			result[i] = previousQueen.toString();
			previousQueen.findNext();
		}
		
		return result;
	}
	
	/**
	 * Convert an array of permutations to a single String.
	 * Mainly for human checking purposes.
	 * The unit tests make it clearer what this returns.
	 * You should not have to change it.
	 * 
	 * @param perms  The array of permutation strings.
	 * @return A string representing the set of permutations.
	 */
	public static String permsToString(String[] perms) {
		StringBuilder result = new StringBuilder("{");
		for (String s: perms)
			result.append(s + ", ");
		int len = result.length();
		result.delete(len-2, len); // remove final comma and space
		result.append("}");
		return result.toString();
	}
	
	
		

}
