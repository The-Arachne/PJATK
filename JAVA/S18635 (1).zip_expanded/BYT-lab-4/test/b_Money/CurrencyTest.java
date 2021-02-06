package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}
	/*TEST Currency 1:
	 * checking if currency name equals itself
	 * */
	@Test
	public void testGetName() {
		assertEquals("SEK", SEK.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("EUR", EUR.getName());
	}
	/*TEST Currency 2:
	 * checking if rate from currency equals itself
	 * */
	@Test
	public void testGetRate() {
		assertEquals(Double.valueOf(0.15), SEK.getRate());
		assertEquals(Double.valueOf(0.20), DKK.getRate());
		assertEquals(Double.valueOf(1.5), EUR.getRate());
	}
	/*TEST Currency 3:
	 * checking if you can overwrite rate
	 * */
	@Test
	public void testSetRate() {
		double set=1.4;
		SEK.setRate(set);
		assertEquals(Double.valueOf(set), SEK.getRate());
	}
	/*TEST Currency 4:
	 * casting amounts of money to "universal Value"
	 * */
	@Test
	public void testGlobalValue() {
		int amount=1342;
		assertEquals(Double.valueOf(201.3),SEK.universalValue(amount),0.00001);
	}
	/*TEST Currency 4:
	 * casting amounts of money from other currency to our
	 * */
	@Test
	public void testValueInThisCurrency() {
		int amount=1342;
		assertEquals(Double.valueOf(1789.33333333) ,Double.valueOf(DKK.universalValue(amount)/SEK.getRate()),0.00001);
	}

}
