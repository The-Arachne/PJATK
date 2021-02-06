package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}
	/*TEST Money 1:
	 * checking if the 100 SEK really is 100 SEK
	 * */
	@Test
	public void testGetAmount() {
		assertEquals(Double.valueOf(100.0), SEK100.getAmount());
		assertEquals(Double.valueOf(20.0), EUR20.getAmount());
		assertEquals(Double.valueOf(0.0), SEK0.getAmount());
		assertEquals(Double.valueOf(0.0), EUR0.getAmount());
	}
	/*TEST Money 2:
	 * checking if currency name equals itself
	 * */
	@Test
	public void testGetCurrency() {
		assertEquals("SEK", SEK100.getCurrency().getName());
		assertEquals("EUR", EUR20.getCurrency().getName());
	}
	/*TEST Money 3:
	 * checking if toString is working
	 * */
	@Test
	public void testToString() {
		assertEquals("100.0 SEK", SEK100.toString());
		assertEquals("20.0 EUR", EUR20.toString());
		assertEquals("0.0 EUR", EUR0.toString());
	}
	/*TEST Money 4:
	 * casting amounts of money to "universal Value"
	 * */
	@Test
	public void testGlobalValue() {
		assertEquals(Double.valueOf(15),SEK100.universalValue(),0.00001);
		assertEquals(Double.valueOf(30),EUR20.universalValue(),0.00001);
	}
	/*TEST Money 5:
	 * checking if money from one currency equals another
	 * */
	@Test
	public void testEqualsMoney() {
		assertTrue(this.EUR20.equals(SEK200));
		assertFalse(this.EUR10.equals(SEK0));
		assertTrue(this.EUR0.equals(SEK0));
		assertFalse(this.EUR0.equals(SEKn100));
	}
	/*TEST Money 6:
	 * checking if you can add money from one currency to another
	 * */
	@Test
	public void testAdd() {
		assertEquals(Double.valueOf(40.0), this.EUR20.add(SEK200).getAmount());
		assertEquals(Double.valueOf(0.0), this.EUR0.add(SEK0).getAmount());
		assertEquals(Double.valueOf(10.0), this.EUR10.add(SEK0).getAmount());
	}
	/*TEST Money 7:
	 * checking if you can substract money from one currency by another
	 * */
	@Test
	public void testSub() {
		assertEquals(Double.valueOf(0.0), this.EUR20.sub(SEK200).getAmount());
		assertEquals(Double.valueOf(0.0), this.EUR0.sub(SEK0).getAmount());
		assertEquals(Double.valueOf(10.0), this.EUR10.sub(SEK0).getAmount());
	}
	/*TEST Money 7:
	 * checking if money equals zero
	 * */
	@Test
	public void testIsZero() {
		assertTrue(this.EUR0.isZero());
		assertFalse(this.EUR20.isZero());
	}
	/*TEST Money 8:
	 * checking if you can negate money
	 * */
	@Test
	public void testNegate() {
		assertEquals(Double.valueOf(-10.0), this.EUR10.negate().getAmount());
	}
	/*TEST Money 9:
	 * checking if compairing different amounts of different currences equals themselfs
	 * */
	@Test
	public void testCompareTo() {
		assertEquals(0,this.EUR0.compareTo(SEK0));
		assertEquals(1,this.EUR10.compareTo(SEK0));
		assertEquals(-1,this.EUR0.compareTo(SEK100));
	}
}
