package eu.cointelligence.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import eu.cointelligence.model.Account;

@Stateless
@Path("/trader")
public class TraderService {
	@EJB
	private Trader trader;
	@POST
	@Path("/buy")
	@Produces("application/json")
	public boolean buy(@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("statementId") Long statementId,
			@FormParam("quantity") Long wantedQuantity) {
		
		return trader.buy(username, password, statementId, wantedQuantity);
	}

	@POST
	@Path("/sell")
	@Produces("application/json")
	public boolean sell(@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("statementId") Long statementId,
			@FormParam("quantity") Long quantity) {

		return trader.sell(username, password, statementId, quantity);
	}

	@POST
	@Path("/shortsell")
	@Produces("application/json")
	public boolean shortSell(@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("statementId") Long statementId,
			@FormParam("quantity") Long quantity) {
		
		//TODO
		return false;
	}

	@POST
	@Path("/check")
	@Produces("application/json")
	public Account checkBankAccount(@FormParam("username") String username, @FormParam("passwordHash") String passwordHash) {
		return trader.checkBankAccount(username, passwordHash);
	}
}
