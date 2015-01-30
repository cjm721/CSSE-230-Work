import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit tests for ThreadedBinarySearchTree. Note: I removed tests with string 1
 * until we can replace it with another example. (Some students found it
 * confusing when debugging since it contained the word "recursion" and was recursive.)
 * 
 * @author Curt Clifton
 */
public class ThreadedBinarySearchTreeTest {

	private ThreadedBinarySearchTree<String> tree1;
	private static final String expectedForward1 = "(a      null recursive      true false)\n"
			+ "(call      a directly      true true)\n"
			+ "(directly      call either      false true)\n"
			+ "(either      directly indirectly      false false)\n"
			+ "(indirectly      either is      true true)\n"
			+ "(is      either makes      false false)\n"
			+ "(itself      is makes      true true)\n"
			+ "(makes      itself method      false true)\n"
			+ "(method      is or      false false)\n"
			+ "(or      method recursive      true true)\n"
			+ "(recursive      method that      false false)\n"
			+ "(that      recursive to      true false)\n"
			+ "(to      that null      true true)\n";
	private static final String expectedReverse1 = "(to      that null      true true)\n"
			+ "(that      recursive to      true false)\n"
			+ "(recursive      method that      false false)\n"
			+ "(or      method recursive      true true)\n"
			+ "(method      is or      false false)\n"
			+ "(makes      itself method      false true)\n"
			+ "(itself      is makes      true true)\n"
			+ "(is      either makes      false false)\n"
			+ "(indirectly      either is      true true)\n"
			+ "(either      directly indirectly      false false)\n"
			+ "(directly      call either      false true)\n"
			+ "(call      a directly      true true)\n"
			+ "(a      null recursive      true false)\n";

	private ThreadedBinarySearchTree<String> tree2;
	private static final String expectedForward2 = "(Now      null is      true false)\n"
			+ "(aid      Now all      true true)\n"
			+ "(all      aid come      false false)\n"
			+ "(come      all country      true false)\n"
			+ "(country      come for      true true)\n"
			+ "(for      all good      false false)\n"
			+ "(good      for is      true true)\n"
			+ "(is      for the      false false)\n"
			+ "(men      is of      true false)\n"
			+ "(of      men the      true true)\n"
			+ "(the      men time      false false)\n"
			+ "(their      the time      true true)\n"
			+ "(time      their to      false false)\n"
			+ "(to      time null      true true)\n";
	private static final String expectedReverse2 = "(to      time null      true true)\n"
			+ "(time      their to      false false)\n"
			+ "(their      the time      true true)\n"
			+ "(the      men time      false false)\n"
			+ "(of      men the      true true)\n"
			+ "(men      is of      true false)\n"
			+ "(is      for the      false false)\n"
			+ "(good      for is      true true)\n"
			+ "(for      all good      false false)\n"
			+ "(country      come for      true true)\n"
			+ "(come      all country      true false)\n"
			+ "(all      aid come      false false)\n"
			+ "(aid      Now all      true true)\n"
			+ "(Now      null is      true false)\n";

	private ThreadedBinarySearchTree<String> tree3;
	private static final String expectedForward3 = "(--      null Almighty      true true)\n"
			+ "(Almighty      -- Forbid      false true)\n"
			+ "(Forbid      Almighty Gentlemen      false true)\n"
			+ "(Gentlemen      Forbid Is      false false)\n"
			+ "(God!      Gentlemen I      true false)\n"
			+ "(Henry      God! I      true true)\n"
			+ "(I      Henry Is      false true)\n"
			+ "(Is      God! It      false true)\n"
			+ "(It      Gentlemen is      false false)\n"
			+ "(Our      It Patrick      true false)\n"
			+ "(Patrick      Our Peace,      true true)\n"
			+ "(Peace,      Our Peace--but      false false)\n"
			+ "(Peace--but      Peace, The      true false)\n"
			+ "(The      Peace--but actually      true false)\n"
			+ "(What      The Why      true true)\n"
			+ "(Why      What actually      false true)\n"
			+ "(actually      Why begun!      false false)\n"
			+ "(already      actually and      true false)\n"
			+ "(and      already are      true true)\n"
			+ "(are      already arms!      false true)\n"
			+ "(arms!      are as      false false)\n"
			+ "(as      arms! be      true false)\n"
			+ "(at      as be      true true)\n"
			+ "(be      at begun!      false true)\n"
			+ "(begun!      arms! bring      false false)\n"
			+ "(brethren      begun! bring      true true)\n"
			+ "(bring      brethren clash      false false)\n"
			+ "(but      bring chains      true true)\n"
			+ "(chains      but clash      false true)\n"
			+ "(clash      chains course      false false)\n"
			+ "(course      clash cry,      true true)\n"
			+ "(cry,      Peace, ears      false false)\n"
			+ "(dear,      cry, death!      true false)\n"
			+ "(death!      dear, ears      true true)\n"
			+ "(ears      dear, extenuate      false true)\n"
			+ "(extenuate      cry, gale      false false)\n"
			+ "(field!      extenuate for      true false)\n"
			+ "(for      field! from      true true)\n"
			+ "(from      field! gale      false true)\n"
			+ "(gale      from here      false false)\n"
			+ "(gentlemen      gale have?      true false)\n"
			+ "(give      gentlemen have?      true true)\n"
			+ "(have?      give here      false true)\n"
			+ "(here      gentlemen idle?      false false)\n"
			+ "(idle?      here in      true true)\n"
			+ "(in      extenuate is      false true)\n"
			+ "(is      in vain,      false false)\n"
			+ "(it      is life      true false)\n"
			+ "(it,      it know      true false)\n"
			+ "(know      it, liberty      true false)\n"
			+ "(liberty      know life      true true)\n"
			+ "(life      it, matter.      false true)\n"
			+ "(matter.      it may      false false)\n"
			+ "(may      matter. no      true false)\n"
			+ "(me      may me,      true true)\n"
			+ "(me,      me next      false true)\n"
			+ "(next      me, no      false true)\n"
			+ "(no      next peace.      false false)\n"
			+ "(north      no our      true false)\n"
			+ "(not      north of      true true)\n"
			+ "(of      not or      false false)\n"
			+ "(or      of others      true false)\n"
			+ "(others      or our      true true)\n"
			+ "(our      of peace      false false)\n"
			+ "(peace      our peace.      true true)\n"
			+ "(peace.      north resounding      false false)\n"
			+ "(price      peace. purchased      true true)\n"
			+ "(purchased      price resounding      false true)\n"
			+ "(resounding      purchased sir,      false true)\n"
			+ "(sir,      matter. to      false false)\n"
			+ "(slavery?      sir, so      true true)\n"
			+ "(so      slavery? stand      false true)\n"
			+ "(stand      so sweeps      false true)\n"
			+ "(sweeps      stand sweet,      false false)\n"
			+ "(sweet,      sweeps take;      true false)\n"
			+ "(take;      sweet, that      true true)\n"
			+ "(that      sweeps the      false true)\n"
			+ "(the      that there      false false)\n"
			+ "(there      the they      true false)\n"
			+ "(they      there to      true true)\n"
			+ "(to      the vain,      false true)\n"
			+ "(vain,      sir, war      false false)\n"
			+ "(war      vain, will      true false)\n"
			+ "(we      war what      true false)\n"
			+ "(what      we will      true true)\n"
			+ "(will      we wish?      false false)\n"
			+ "(wish?      will would      true false)\n"
			+ "(would      wish? null      true true)\n";
	private static final String expectedReverse3 = "(would      wish? null      true true)\n"
			+ "(wish?      will would      true false)\n"
			+ "(will      we wish?      false false)\n"
			+ "(what      we will      true true)\n"
			+ "(we      war what      true false)\n"
			+ "(war      vain, will      true false)\n"
			+ "(vain,      sir, war      false false)\n"
			+ "(to      the vain,      false true)\n"
			+ "(they      there to      true true)\n"
			+ "(there      the they      true false)\n"
			+ "(the      that there      false false)\n"
			+ "(that      sweeps the      false true)\n"
			+ "(take;      sweet, that      true true)\n"
			+ "(sweet,      sweeps take;      true false)\n"
			+ "(sweeps      stand sweet,      false false)\n"
			+ "(stand      so sweeps      false true)\n"
			+ "(so      slavery? stand      false true)\n"
			+ "(slavery?      sir, so      true true)\n"
			+ "(sir,      matter. to      false false)\n"
			+ "(resounding      purchased sir,      false true)\n"
			+ "(purchased      price resounding      false true)\n"
			+ "(price      peace. purchased      true true)\n"
			+ "(peace.      north resounding      false false)\n"
			+ "(peace      our peace.      true true)\n"
			+ "(our      of peace      false false)\n"
			+ "(others      or our      true true)\n"
			+ "(or      of others      true false)\n"
			+ "(of      not or      false false)\n"
			+ "(not      north of      true true)\n"
			+ "(north      no our      true false)\n"
			+ "(no      next peace.      false false)\n"
			+ "(next      me, no      false true)\n"
			+ "(me,      me next      false true)\n"
			+ "(me      may me,      true true)\n"
			+ "(may      matter. no      true false)\n"
			+ "(matter.      it may      false false)\n"
			+ "(life      it, matter.      false true)\n"
			+ "(liberty      know life      true true)\n"
			+ "(know      it, liberty      true false)\n"
			+ "(it,      it know      true false)\n"
			+ "(it      is life      true false)\n"
			+ "(is      in vain,      false false)\n"
			+ "(in      extenuate is      false true)\n"
			+ "(idle?      here in      true true)\n"
			+ "(here      gentlemen idle?      false false)\n"
			+ "(have?      give here      false true)\n"
			+ "(give      gentlemen have?      true true)\n"
			+ "(gentlemen      gale have?      true false)\n"
			+ "(gale      from here      false false)\n"
			+ "(from      field! gale      false true)\n"
			+ "(for      field! from      true true)\n"
			+ "(field!      extenuate for      true false)\n"
			+ "(extenuate      cry, gale      false false)\n"
			+ "(ears      dear, extenuate      false true)\n"
			+ "(death!      dear, ears      true true)\n"
			+ "(dear,      cry, death!      true false)\n"
			+ "(cry,      Peace, ears      false false)\n"
			+ "(course      clash cry,      true true)\n"
			+ "(clash      chains course      false false)\n"
			+ "(chains      but clash      false true)\n"
			+ "(but      bring chains      true true)\n"
			+ "(bring      brethren clash      false false)\n"
			+ "(brethren      begun! bring      true true)\n"
			+ "(begun!      arms! bring      false false)\n"
			+ "(be      at begun!      false true)\n"
			+ "(at      as be      true true)\n"
			+ "(as      arms! be      true false)\n"
			+ "(arms!      are as      false false)\n"
			+ "(are      already arms!      false true)\n"
			+ "(and      already are      true true)\n"
			+ "(already      actually and      true false)\n"
			+ "(actually      Why begun!      false false)\n"
			+ "(Why      What actually      false true)\n"
			+ "(What      The Why      true true)\n"
			+ "(The      Peace--but actually      true false)\n"
			+ "(Peace--but      Peace, The      true false)\n"
			+ "(Peace,      Our Peace--but      false false)\n"
			+ "(Patrick      Our Peace,      true true)\n"
			+ "(Our      It Patrick      true false)\n"
			+ "(It      Gentlemen is      false false)\n"
			+ "(Is      God! It      false true)\n"
			+ "(I      Henry Is      false true)\n"
			+ "(Henry      God! I      true true)\n"
			+ "(God!      Gentlemen I      true false)\n"
			+ "(Gentlemen      Forbid Is      false false)\n"
			+ "(Forbid      Almighty Gentlemen      false true)\n"
			+ "(Almighty      -- Forbid      false true)\n"
			+ "(--      null Almighty      true true)\n";

	/**
	 * Uses {@link ThreadedBinarySearchTree#insert(Comparable)} to create some
	 * test trees.
	 */
	@Before
	public void setUp() {
		String s1 = "a recursive method is a method that either directly "
				+ "or indirectly makes a call to itself";
		this.tree1 = buildTestTree(s1);
		String s2 = "Now is the time for all good men to come to the "
				+ "aid of their country";
		this.tree2 = buildTestTree(s2);
		String s3 = "It is in vain, sir, to extenuate the matter. "
				+ "Gentlemen may cry, Peace, Peace--but there is no peace. "
				+ "The war is actually begun! The next gale that sweeps from "
				+ "the north will bring to our ears the clash of resounding "
				+ "arms! Our brethren are already in the field! Why stand we "
				+ "here idle? What is it that gentlemen wish? What would they "
				+ "have? Is life so dear, or peace so sweet, as to be purchased "
				+ "at the price of chains and slavery? Forbid it, Almighty God! I "
				+ "know not what course others may take; but as for me, give me "
				+ "liberty or give me death!  -- Patrick Henry";
		this.tree3 = buildTestTree(s3);
	}

	private ThreadedBinarySearchTree<String> buildTestTree(String s) {
		ThreadedBinarySearchTree<String> result = new ThreadedBinarySearchTree<String>();
		/*
		 * Uses this set to avoid attempting to insert duplicates. A separate
		 * test checks the duplicate avoidance code:
		 */
		Set<String> alreadyInserted = new HashSet<String>();
		StringTokenizer st = new StringTokenizer(s, " ");
		String current = null;
		while (st.hasMoreTokens()) {
			current = st.nextToken();
			try {
				if (current != null && alreadyInserted.add(current)) {
					result.insert(current);
				}
			} catch (DuplicateItem e) {
				System.out.println("While processing string: " + s);
				System.out.println("Got unexpected duplicate item: " + current);
			}
		}

		return result;
	}

	/**
	 * Test method for {@link ThreadedBinarySearchTree#find(Comparable)}.
	 */
	@Test(timeout = 3000)
	public void testFind() {
		/*
		 * The provided find method is correct. So if you are failing this test
		 * case, it means your insert() code has a problem.
		 */
		// assertFalse(this.tree1.find(new String("the")));
		assertTrue(this.tree2.find(new String("the")));
		assertTrue(this.tree3.find(new String("the")));

		// assertFalse(this.tree1.find(new String("at")));
		assertFalse(this.tree2.find(new String("at")));
		assertTrue(this.tree3.find(new String("at")));
	}

	/**
	 * Test method for {@link ThreadedBinarySearchTree#insert(Comparable)}.
	 */
	@Test(timeout = 1000, expected = DuplicateItem.class)
	public void testInsertDuplicate() {
		ThreadedBinarySearchTree<Integer> t = new ThreadedBinarySearchTree<Integer>();
		t.insert(2);
		t.insert(3);
		t.insert(1);
		// Duplicate insert should throw exception:
		t.insert(3);
	}

	/**
	 * Test method for {@link ThreadedBinarySearchTree#toStringInOrder()}.
	 */
	@Test(timeout = 3000)
	public void testToStringInOrder() {
		// assertEquals(expectedForward1, this.tree1.toStringInOrder());
		assertEquals(expectedForward2, this.tree2.toStringInOrder());
		assertEquals(expectedForward3, this.tree3.toStringInOrder());
	}

	/**
	 * Test method for {@link ThreadedBinarySearchTree#toStringInReverseOrder()}
	 * .
	 */
	@Test(timeout = 3000)
	public void testToStringInReveseOrder() {
		// assertEquals(expectedReverse1, this.tree1.toStringInReverseOrder());
		assertEquals(expectedReverse2, this.tree2.toStringInReverseOrder());
		assertEquals(expectedReverse3, this.tree3.toStringInReverseOrder());
	}

}
