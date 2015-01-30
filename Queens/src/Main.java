/**
 * Uses the Queen interface and implementations to solve the non-attacking chess
 * queens problem.
 * 
 * @author Curt Clifton, based on previous work by Claude Anderson
 */
public class Main {
	private static int SIZE = 5; // How big is the board?

	/**
	 * Runs the non-attacking queens finder
	 * 
	 * @param args
	 *            first argument, if given, is an integer giving the board size.
	 */
	public static void main(String args[]) {
		// DO NOT CHANGE THIS METHOD
		if (args.length != 0) {
			SIZE = Integer.parseInt(args[0]);
		}
		int count = findSolutions(SIZE);
		System.out.println("Found " + count + " solutions of size " + SIZE);
	}

	/**
	 * Finds and prints all solutions for the given board size.
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

}
