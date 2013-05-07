package eu.cointelligence.controller;

import eu.cointelligence.model.Account;

public interface ITraderService {

	public boolean buy(Long userId,
			Long statementId,
			Long wantedQuantity);

	public boolean sell(Long userId,
			Long statementId,
			Long quantity);

	public abstract Account checkBankAccount(Long userId);

}