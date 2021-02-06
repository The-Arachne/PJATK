package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		
		SEK = new Currency("SEK", 0.15);
		
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		SweBank.deposit("Bob", new Money(500,SEK));
		
		Nordea = new Bank("Nordea", SEK);		
		Nordea.openAccount("Bob");
		
		DKK = new Currency("DKK", 0.20);
		
		DanskeBank = new Bank("DanskeBank", DKK);
		DanskeBank.openAccount("Gertrud");
		
		//male porzadki
	}
	/*TEST Bank 1:
	 * checking if Bank will return his name
	 * */
	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
		assertEquals("Nordea", Nordea.getName());
	}
	/*TEST Bank 2:
	 * checking if Bank will return his name of currency
	 * */
	@Test
	public void testGetCurrency() {
		assertEquals("SEK", SweBank.getCurrency().getName());
		assertEquals("DKK", DanskeBank.getCurrency().getName());
	}
	/*TEST Bank 3:
	 * trying to open new accounts
	 * checking what will happened if one of them has repeated ID
	 * */
	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		SweBank.openAccount("Benc");
		try {
			SweBank.openAccount("Bob");
		}catch(AccountExistsException e) {System.out.println(e.toString()+" account with same ID already exists");}
		
	}
	/*TEST Bank 4:
	 * donating some cash to "Bob" from SweBank
	 * checking if he really get his money
	 * */
	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(100, SEK));
		assertEquals(Double.valueOf(6.0), SweBank.getBalance("Bob"));
		SweBank.deposit("Bob", new Money(300, SEK));
		assertEquals(Double.valueOf(9.0), SweBank.getBalance("Bob"));
	}
	/*TEST Bank 5:
	 * checking if you can witdraw money from account
	 * */
	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.withdraw("Bob", new Money(100, SEK));
		assertEquals(Double.valueOf(4.0), SweBank.getBalance("Bob"));
		SweBank.withdraw("Bob", new Money(300, SEK));
		assertEquals(Double.valueOf(1.0), SweBank.getBalance("Bob"));
	}
	/*TEST Bank 5:
	 * checking if "print" is working / why print? i was using this to almost every method
	 * */
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals(Double.valueOf(5.0), SweBank.getBalance("Bob"));
	}
	/*TEST Bank 6:
	 * transfering money from one Bob to another
	 * checking if both their accounts changes
	 * */
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.transfer("Bob", Nordea, "Bob", new Money(300, SEK));
		assertEquals(Double.valueOf(2.0),SweBank.getBalance("Bob"));
		assertEquals(Double.valueOf(3.0),Nordea.getBalance("Bob"));
	}
	/*TEST Bank 7:
	 * schedule money from one Bob to another
	 * checking if the receiver get his money
	 * */
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.addTimedPayment("Bob", "test", 12, 15, new Money(300, SEK), Nordea, "Bob");
		SweBank.tick();
		assertEquals(Double.valueOf(3.0),Nordea.getBalance("Bob"));
	}
}
