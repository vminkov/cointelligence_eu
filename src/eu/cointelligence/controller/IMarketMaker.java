package eu.cointelligence.controller;

import java.util.Map;

import eu.cointelligence.model.Statement;
import eu.cointelligence.model.Transaction;

public interface IMarketMaker {

	// TODO javadoc
	public abstract Map<Statement, Long> getStatementsAndPrices();

	public abstract Long getPriceForStatement(Long statementId);

	public abstract boolean addLog(Transaction log);

	public abstract boolean addStatement(Statement statement);

}