package eu.cointelligence.controller;

import java.util.List;

import eu.cointelligence.model.Account;
import eu.cointelligence.model.Statement;

public interface ITrader {

	public boolean buy(String username, String password,
			Long statementId,
			Long wantedQuantity);

	public boolean sell(String username, String password,
			Long statementId,
			Long quantity);

	public abstract Account checkBankAccount(String username, String password);

	public List<Statement> getStatements();

	public Statement getStatementById(Long statementId);

	public boolean shortSell(String username, String password, Long statementId,
			Long quantity, Long minutes);

}