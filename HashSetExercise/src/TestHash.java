
/**
 * Tests various aspects of hash tables. 
 * 
 * @author Matt Boutell.
 *         Created Feb 1, 2006.
 */
public class TestHash {
	
	/**
	 * Main point of execution.
	 *
	 * @param args Command-line parameter.
	 */
	public static void main(String[] args) {

		TestHash.testNumericHash();
//		TestHash.testNumericStringHash();

		// Reminder: before you run this code, be sure to comment out the 
		// body of HashSet's rehash() method.
//		TestHash.testLoadFactors();
	}

	/** Adds a series of numbers to the table. */
	private static void testNumericHash() {
		HashSet test = new HashSet(100);
		for (Integer i = 0; i < 1000; i++) {
			test.add(i);
		}
		for (Integer i = 0; i < 1000; i++) {
			System.out.println("Found " + i + " at position " + test.findPos(i));
		}
	}

	/** Adds the string representation of a series of numbers to the table*/
	private static void testNumericStringHash() {
		HashSet test = new HashSet(); 
		for (int i = 0; i < 1000; i++) {
			test.add(Integer.toString(i));
		}
	
		for (int i = 0; i < 1000; i++) {
			System.out.println("Found " + i + " at position " + test.findPos(Integer.toString(i)));
		}
	}
	
	/**
	 * Tests how load factor affects the average number of probes made when 
	 * searching a hash set. 
	 */
	private static void testLoadFactors() {
		String probingMethod = "quadratic";
		System.out.println("With " + probingMethod + " probing: ");
		for (int i = 1; i <= 9; i++) {
			TestHash.testLoadFactors(i/10.0, probingMethod);
		}

		// You need to implement linear probing before this will work!
		probingMethod = "linear";
		System.out.println("With " + probingMethod + " probing: ");
		for (int i = 1; i <= 9; i++) {
			TestHash.testLoadFactors(i/10.0, probingMethod);
		}
	}

	/**
	 *  Experiment with the number of probes for each load factor. 
	 */
	private static void testLoadFactors(double lambda, String probingMethod) {
		// Fill the hash table to a given load factor.
		// Fix the capacity so the load factor doesn't change.
		// Note: you must comment out the rehash() to prevent it from doing so if you
		// want the load factor to increase over 0.5.
		
		int capacity = 1009; // prime  
		HashSet test = new HashSet(capacity);

		// We want to add the correct number of elements for the given load factor.
		int size = (int)(capacity * lambda); 
		
		// Add the items randomly. Note that this will cause clustering.
		// We add numbers in the range 1-1000*cap to reduce duplicates.
		test.setProbingMethod(probingMethod);
		for (int i = 0; i < size; i++) {
			test.add((int)(Math.random()*capacity*1000));
		}
		System.out.println("Size = " + test.size() );

		// Show output of table. 
		for (int i = 0; i < test.getCapacity(); i++) {
//			Integer x = (test.getItemAt(i) == null ? null : (Integer)(test.getItemAt(i))); 
//			System.out.println(i + " " + x);
		}

		// Now search for lots of numbers to get stats on number of collisions/probes.
		// We can't test by adding more things, because that would change the load factor.
		int sampleSize = 1000000;
		for (int i = 0; i < sampleSize; i++) {
			test.getMatch((int)(Math.random()*capacity));
		}
		int nCollisions = test.getNumCollisions();
		double avgCollisions = (double)nCollisions / sampleSize;
		System.out.println("For load factor " + lambda +", avg probes = " + (avgCollisions + 1));
		
	}
}
