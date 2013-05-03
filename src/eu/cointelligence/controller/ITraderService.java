package eu.cointelligence.controller;

import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;

public interface ITraderService {

	// TODO: javadoc
	public abstract boolean buy(Long userId, Long statementId,
			Long wantedQuantity);

	// TODO: javadoc
	public abstract boolean sell(Long userId, Long statementId, Long quantity);

	@GET
	@QueryParam("/{userId}")
	//	@Produces("application/json")
	public abstract String checkBankAccount(@QueryParam("userId") Long userId);

}