
public class PhD {

	/** NetId: it233, acs299. Time spent: 2 hours, 5 minutes. */
	/** An instance maintains info about the PhD of a person. */

	/** Month PhD awarded. In 1..12, with 1 meaning January, etc. */
	private int month;
	/** Year PhD awarded. */
	private int year;
	/** Name of the person with a PhD. length()>0 */
	private String name;
	/** First advisor of this person */
	private PhD adname1;
	/** Second advisor of this person */
	private PhD adname2;
	/** Number of PhD advisee of this Person */
	private int NumberOfAdvisees;

	/** Constructor: a PhD with PhD month m, PhD year y, and name n.<br>
	 * The advisors are unknown, and there are 0 advisees.<br>
	 * Precondition: n has at least 1 char and m is in 1..12. */

	public PhD(int m, int y, String n) {
		/** Check Precondition */
		assert n != null;
		assert n.length() >= 1;
		assert m >= 1 && m <= 12;
		/** Assigning input to field variables */
		month= m;
		year= y;
		name= n;
		adname1= null;
		adname2= null;
		NumberOfAdvisees= 0;

	}

	/** Return the name of this person. */

	public String name() {
		return name;
	}

	/** Return the year this person got their PhD. */
	public int year() {
		return year;
	}

	/** Return the month this person got their PhD. */

	public int month() {
		return month;
	}

	/** Return the first advisor of this PhD (null if unknown). */

	public PhD advisor1() {
		return adname1;
	}

	/** Return the second advisor of this PhD (null if unknown or non-existent). */

	public PhD advisor2() {
		return adname2;
	}

	/** Return the number of PhD advisees of this person. */

	public int advisees() {
		return NumberOfAdvisees;
	}

	/** Make p the first advisor of this person.<br>
	 * Precondition: the first advisor is unknown and p is not null. */

	public void setAdvisor1(PhD p) {
		/** Check precondition */
		assert p != null;

		adname1= p;

		/** Increment number of advisees */
		p.NumberOfAdvisees++ ;

	}

	/** Make p the second advisor of this PhD. <br>
	 * Precondition: The first advisor is known, the second advisor is unknown, <br>
	 * p is not null, and p is different from the first advisor. */
	public void setAdvisor2(PhD p) {
		/** Check Precondition */
		assert advisor1() != null;
		assert advisor1() != p;
		/** Increment number of advisees */
		adname2= p;
		p.NumberOfAdvisees++ ;

	}

	/** Constructor: a PhD with PhD month m, PhD year y, name n, <br>
	 * first advisor adv1, and no second advisor.<br>
	 * Precondition: n has at least 1 char, m is in 1..12, and adv1 is not null. */

	public PhD(int m, int y, String n, PhD adv1) {
		/** Check Preconditions */
		assert n != null;
		assert n.length() >= 1;
		assert m >= 1 && m <= 12;
		assert adv1 != null;
		/** Assigning input to field variables */
		month= m;
		year= y;
		name= n;
		adname1= adv1;
		/** Incrementing number of advisees of the person. */
		adv1.NumberOfAdvisees++ ;
	}

	/** Constructor: a PhD with PhD month m, PhD year y, name n, <br>
	 * first advisor adv1, and second advisor adv2.<br>
	 * Precondition: n has at least 1 char, m is in 1..12,<br>
	 * adv1 and adv2 are not null, and adv1 and adv2 are different. */

	public PhD(int m, int y, String n, PhD adv1, PhD adv2) {
		/** Check Preconditions */
		assert n != null;
		assert n.length() >= 1;
		assert m >= 1 && m <= 12;
		assert adv1 != null;
		assert adv2 != null;
		assert adv1 != adv2;

		/** Assigning input to field variables */
		month= m;
		year= y;
		name= n;
		adname1= adv1;
		adname2= adv2;
		/** Incrementing number of advisees of the person. */
		adv1.NumberOfAdvisees++ ;
		adv2.NumberOfAdvisees++ ;

	}

	/** Return value of: this PhD got the PhD after p.<br>
	 * Precondition: p is not null. */

	public boolean gotAfter(PhD p) {
		/** Check preconditions */
		assert p != null;

		return ((this != p) && (year > p.year()) || (year == p.year() && month > p.month()));

	}

	/** Return value of: p is not null and this PhD and p are intellectual siblings. */
	public boolean areSiblings(PhD p) {
		return ((p != null) && ((this != p) &&
			((p.advisor1() != null && adname1 != null && (p.advisor1() == adname1 || p.advisor1() == adname2)) ||
				(p.advisor2() != null && adname2 != null && (p.advisor2() == adname2 || p.advisor2() == adname1)))));
	}

}
