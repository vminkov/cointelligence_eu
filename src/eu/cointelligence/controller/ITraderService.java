package eu.cointelligence.controller;

import eu.cointelligence.model.Account;

public interface ITraderService {

	public boolean buy(String username, String password,
			Long statementId,
			Long wantedQuantity);

	public boolean sell(String username, String password,
			Long statementId,
			Long quantity);

	public abstract Account checkBankAccount(String username, String password);

}