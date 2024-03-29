package eu.cointelligence.controller;

import java.util.List;

import eu.cointelligence.controller.entity.beans.StatementBean;
import eu.cointelligence.model.Account;
import eu.cointelligence.model.Statement;

public interface ITrader {

	public String buy(String username, String password,
			Long statementId,
			Long wantedQuantity);

	public String sell(String username, String password,
			Long statementId,
			Long quantity);

	public abstract Account checkBankAccount(String username, String password);

	public List<Statement> getStatements();

	public Statement getStatementById(Long statementId);

	public String shortSell(String username, String password, Long statementId,
			Long quantity, Long minutes);

	boolean submitStatement(String username, String password,
			StatementBean statement);

}