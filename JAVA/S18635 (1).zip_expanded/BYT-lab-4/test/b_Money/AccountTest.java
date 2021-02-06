package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea,DanskeBank,SweBank;
	Account testAccount;
	//TESTY sa izolowane przed zmiana zmiennych przez inne testy (kazdy ma te samo wejscie)
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(1000, SEK));
		//SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	/* TEST Account 1:
	 * adding to hashtable element
	 * checking if an item has been added
	 * removing element from hastable
	 * checking if an item has been removed */
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("test", 12, 15,new Money(400, SEK), SweBank, "Alice");
		assertTrue(testAccount.timedPaymentExists("test"));
		testAccount.removeTimedPayment("test");
		assertFalse(testAccount.timedPaymentExists("test"));
	}
	/*TEST Account 2:
	 * adding to hashtable element
	 * triggering the time method
	 * checking if an item has been removed
	 * */
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		
		testAccount.addTimedPayment("test", 12, 15,new Money(400, SEK), SweBank, "Alice");
		testAccount.tick();
		assertEquals(Double.valueOf(6.0), testAccount.getBalance().getAmount());
	}
	/*TEST Account 3:
	 * removing Money from testAccount
	 * checking if Money has been removed
	 * */
	@Test
	public void testAddWithdraw() {
		testAccount.withdraw(new Money(300, SEK));
		assertEquals(Double.valueOf(7.0), testAccount.getBalance().getAmount());
	}
	/*TEST Account 2:
	 * checking getBalance - nothing special
	 * */
	@Test
	public void testGetBalance() {
		assertEquals(Double.valueOf(10.0), testAccount.getBalance().getAmount());
	}
}
