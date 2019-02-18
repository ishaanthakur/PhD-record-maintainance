import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PhDTest {

	/** Test Getter Methods */
	@Test
	void testGroupA() {
		/** Object of PhD created */
		PhD P1= new PhD(5, 10, "Aman");

		/** Test parameters of the object of PhD created. */
		assertEquals(5, P1.month());
		assertEquals(10, P1.year());
		assertEquals("Aman", P1.name());
		assertEquals(null, P1.advisor1());
		assertEquals(null, P1.advisor2());
		assertEquals(0, P1.advisees());

	}

	/** Test Setter Methods. */
	@Test
	void testGroupB() {

		PhD P1= new PhD(5, 10, "Aman");
		PhD P2= new PhD(8, 12, "Arman");
		PhD P3= new PhD(9, 11, "Ben");
		P3.setAdvisor1(P1);
		P3.setAdvisor2(P2);
		assertEquals(1, P1.advisees());
		assertEquals(1, P2.advisees());
		assertEquals(0, P3.advisees());
		/** Object P4 stores the pointer to the address of P3.advisor1() <br>
		 * and later the name stored at it is compared */
		PhD P4= P3.advisor1();
		assertEquals("Aman", P4.name());

	}

	@Test
	void testGroupC() {

		PhD P2= new PhD(8, 12, "Arman");
		PhD P3= new PhD(9, 11, "Ben");
		PhD P6= new PhD(4, 5, "Small", P2, P3);
		PhD P4= P6.advisor1();
		PhD P7= P6.advisor2();
		assertEquals(4, P6.month());
		assertEquals(5, P6.year());
		assertEquals("Small", P6.name());
		assertEquals("Arman", P4.name());
		assertEquals("Ben", P7.name());
		assertEquals(1, P2.advisees());

	}

	@Test
	void testgroupD() {
		PhD feb77= new PhD(2, 1977, "feb77");
		PhD feb77new= new PhD(2, 1977, "feb77");
		PhD jun77= new PhD(6, 1977, "jun77");
		PhD feb99= new PhD(2, 1999, "feb99");
		PhD jan68= new PhD(1, 1968, "jan68");
		PhD dec68= new PhD(12, 1968, "dec68");

		/** Test gotAfter method. */
		assertEquals(false, feb77.gotAfter(feb77new)); // same year, same month
		assertEquals(false, feb77.gotAfter(jun77)); // same year, q’s month before p’s
		assertEquals(true, jun77.gotAfter(feb77)); // same year, q's month after p's
		assertEquals(false, feb77.gotAfter(feb99)); // q’s year before p's, same month
		assertEquals(false, jan68.gotAfter(feb99)); // q’s year before p's, q’s month before p’s
		assertEquals(false, dec68.gotAfter(feb77)); // q’s year before p's, q’s month after p's
		assertEquals(true, feb99.gotAfter(feb77)); // q’s year after p's , same month
		assertEquals(true, feb99.gotAfter(dec68)); // q’s year after p's, q’s month before p’s
		assertEquals(true, feb99.gotAfter(jan68));// q’s year after p's, q’s month after p’s

		PhD P1= new PhD(5, 10, "Aman");
		PhD P2= new PhD(7, 9, "Armaan");
		PhD P3= new PhD(8, 6, "Andrew");
		PhD P7= new PhD(8, 7, "Andre");
		PhD P4= new PhD(2, 3, "Bob", P1, P2);
		PhD P5= new PhD(3, 4, "Ben", P1, P3);
		PhD P6= new PhD(4, 6, "Benoin", P7, P1);
		PhD P8= new PhD(2, 3, "George", P3, P2);
		PhD P9= null;

		/** Test areSiblings method. */
		assertEquals(false, P1.areSiblings(P1)); // A and B are the same object
		assertEquals(false, P1.areSiblings(P2)); // Neither A nor B has an advisor
		assertEquals(true, P5.areSiblings(P4)); // A.advisor1 is not null and equals B.advisor1
		assertEquals(true, P5.areSiblings(P6)); // A.advisor1 is not null and equals B.advisor2
		assertEquals(true, P6.areSiblings(P5)); // A.advisor2 is not null and equals B.advisor1
		assertEquals(true, P8.areSiblings(P4)); // A.advisor2 is not null and equals B.advisor2
		assertEquals(false, P4.areSiblings(P9)); // The call A.areSiblings(null) should return FALSE
	}

	@Test
	void testgroupE() {
		PhD P1= new PhD(5, 10, "Aman");
		PhD P2= new PhD(2, 3, "Bob");

		assertThrows(AssertionError.class, () -> { new PhD(2, 4, ""); });// Group A, checking person with PhD name>=1
		assertThrows(AssertionError.class, () -> { new PhD(2, 4, null); }); // group A, checking if person's name is not
																		    // null.
		assertThrows(AssertionError.class, () -> { new PhD(13, 4, "Hello"); });// Group A, checking month <=12
		assertThrows(AssertionError.class, () -> { new PhD(0, 4, "Hello"); });// Group A, checking month >=1
		assertThrows(AssertionError.class, () -> { P1.setAdvisor1(null); });// Group B, checking object of PhD not null
		assertThrows(AssertionError.class, () -> { P2.setAdvisor2(P1); });// Group B, checking if the first advisor is
																		  // known

		assertThrows(AssertionError.class, () -> { new PhD(2, 4, null, P2); }); // group C, checking if person's name is
																			    // not null.
		assertThrows(AssertionError.class, () -> { new PhD(4, 5, "", P2); });// Group C, checking if name of person>=1
		assertThrows(AssertionError.class, () -> { new PhD(13, 5, "Doe", P1); });// Group C, checking month <=12
		assertThrows(AssertionError.class, () -> { new PhD(-1, 5, "Doe", P1); });// Group C, checking month >=1
		assertThrows(AssertionError.class, () -> { new PhD(2, 4, "Bob", null); });// Group C, checking if object of PhD
																				  // is not null
		assertThrows(AssertionError.class, () -> { new PhD(4, 5, "", P1, P2); }); // Group C, checking if name of
																				  // person>=1 for second function
		assertThrows(AssertionError.class, () -> { new PhD(15, 5, "Ben", P1, P2); });// Group C, checking if month<=12
		assertThrows(AssertionError.class, () -> { new PhD(-2, 5, "Ben", P1, P2); });// Group C, checking if month>=1
		assertThrows(AssertionError.class, () -> { new PhD(12, 4, "b", null, P1); });// Group C, checking object not
																					 // null
		assertThrows(AssertionError.class, () -> { new PhD(11, 1, "a", P1, null); });// Group C, checking second object
																					 // not null
		assertThrows(AssertionError.class, () -> { new PhD(11, 12, "c", P1, P1); });// Group C, checking if both objects
																					// are not the same.

		assertThrows(AssertionError.class, () -> { new PhD(2, 4, null, P1, P2); }); // group A, checking if person's
																				    // name is not null.
		assertThrows(AssertionError.class, () -> { P1.gotAfter(null); });// Group D, checking if object is not null

	}

}
