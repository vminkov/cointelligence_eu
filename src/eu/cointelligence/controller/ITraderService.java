package eu.cointelligence.controller;

import eu.cointelligence.model.Account;

public interface ITraderService {

	public boolean buy(String userId,
			String statementId,
			Long wantedQuantity);

	public boolean sell(String userId,
			String statementId,
			Long quantity);

	public abstract Account checkBankAccount(String userId);

}