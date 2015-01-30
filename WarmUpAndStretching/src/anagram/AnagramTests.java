package anagram;

import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Tests Anagram.
 * 
 * @author Claude Anderson.
 */
public class AnagramTests {

	/**
	 * Tests {@link Anagram#isAnagram(String, String)}.
	 */
	@Test
	public void testAnagram1() {
		assertFalse(Anagram.isAnagram("a", "b"));
		assertTrue(Anagram.isAnagram("a", "a"));
	}

	/**
	 * Tests {@link Anagram#isAnagram(String, String)}.
	 */
	@Test
	public void testAnagram2() {
		assertFalse(Anagram.isAnagram("a", "b"));
		assertTrue(Anagram.isAnagram("a", "A"));
	}

	/**
	 * Tests {@link Anagram#isAnagram(String, String)}.
	 */
	@Test
	public void testAnagram3() {
		assertFalse(Anagram.isAnagram("a", "b"));
		assertTrue(Anagram.isAnagram("ab", "ba"));
	}

	/**
	 * Tests {@link Anagram#isAnagram(String, String)}.
	 */
	@Test
	public void testAnagram4() {
		assertFalse(Anagram.isAnagram("a", "b"));
		assertTrue(Anagram.isAnagram("abc", "cba"));
	}

	/**
	 * Tests {@link Anagram#isAnagram(String, String)}.
	 */
	@Test
	public void testAnagram5() {
		assertFalse(Anagram.isAnagram("a", "b"));
		assertTrue(Anagram.isAnagram("abc", "bca"));
	}

	/**
	 * Tests {@link Anagram#isAnagram(String, String)}.
	 */
	@Test
	public void testAnagram7() {
		assertFalse(Anagram.isAnagram("aabb", "bbbaa"));
		assertTrue(Anagram.isAnagram("Claude Anderson", "Nuanced Ordeals"));
		assertTrue(Anagram.isAnagram("Matt Boutell", "Total Tumble"));
		assertTrue(Anagram.isAnagram("Curt Clifton", "Conflict Rut"));
		assertTrue(Anagram.isAnagram("Delvin Defoe", "Defend Olive")); // like Popeye!
		assertTrue(Anagram.isAnagram("Dave Fisher", "Evader Fish"));
		assertTrue(Anagram.isAnagram("Dave Mutchler", "Traveled Much"));
		assertTrue(Anagram.isAnagram("  Wollowski", "Silk Owl Ow"));
	}

	/**
	 * Tests {@link Anagram#isAnagram(String, String)}.
	 */
	@Test
	public void testAnagram8() {
		assertTrue(Anagram.isAnagram("aabb", "bbaa"));
		assertFalse(Anagram.isAnagram("Claude Anderson", "Nuanced  Ordeals"));
		assertFalse(Anagram.isAnagram("MA", "LB"));
		assertFalse(Anagram.isAnagram("ay", "bx"));
		assertFalse(Anagram.isAnagram("ab", "c"));
	}

}
