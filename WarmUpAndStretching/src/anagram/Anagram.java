package anagram;

/**
 * This utility class can test with two strings are anagrams.
 *
 * @author Claude Anderson.
 */
public class Anagram {

	/**
	 * We say that two strings are anagrams if one can be transformed into the
	 * other by permuting the characters (and ignoring case).
	 * 
	 * @param s1
	 *            first string
	 * @param s2
	 *            second string
	 * @return true iff s1 is an anagram of s2
	 */
	public static boolean isAnagram(String s1, String s2) {
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();
		if(s1.length() != s2.length())
			return false;
		else{
			for(char c: s1.toCharArray()){
				if(s2.indexOf(c) == -1) return false;
			}
		}
		return true;
	}
}
