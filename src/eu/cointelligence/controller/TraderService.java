package eu.cointelligence.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import eu.cointelligence.model.Account;

@Stateless
@Path("/trader")
public class TraderService {
	@EJB
	private ITrader trader;

	@POST
	@Path("/buy")
	@Produces(MediaType.APPLICATION_JSON)
	public String buy(@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("statementId") Long statementId,
			@FormParam("quantity") Long wantedQuantity) {

		return trader.buy(username, password, statementId, wantedQuantity);
	}

	@POST
	@Path("/sell")
	@Produces(MediaType.APPLICATION_JSON)
	public String sell(@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("statementId") Long statementId,
			@FormParam("quantity") Long quantity) {

		return trader.sell(username, password, statementId, quantity);
	}

	@POST
	@Path("/shortSell")
	@Produces(MediaType.APPLICATION_JSON)
	public String shortSell(@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("statementId") Long statementId,
			@FormParam("quantity") Long quantity,
			@FormParam("timeout") Long minutes) {

		return this.trader.shortSell(username, password, statementId, quantity, minutes);
	
	}

	@POST
	@Path("/check")
	@Produces("application/json")
	public Account checkBankAccount(@FormParam("username") String username,
			@FormParam("passwordHash") String passwordHash) {
		return trader.checkBankAccount(username, passwordHash);
	}
}
