package eu.cointelligence.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TraderServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBuy() {
		ITraderService trader = new TraderService();
		assertTrue(trader.buy(new Long(1), new Long(1), new Long(1)));
	}

	@Test
	public void testSell() {
		fail("Not yet implemented");
	}

}
