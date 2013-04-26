package eu.cointelligence.controller;

import java.util.List;
import java.util.Map;

import eu.cointelligence.model.Statement;
import eu.cointelligence.model.Transaction;

public interface IPricingAlgorithm {
	public Map<Statement, Long> recalculate(Map<Statement, Long> oldPrices, List<Transaction> logs);
}