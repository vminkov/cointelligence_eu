package eu.cointelligence.controller.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import eu.cointelligence.model.Transaction;

@Stateless
public class TransactionsDao extends GenericDaoImpl<Transaction> {

	public TransactionsDao() {
		super(Transaction.class);
	}

	
	public List<Transaction> getAllTransaction(){
		Query q = em.createQuery("SELECT t from " + Transaction.class.getName() + " as t");
	
		return (List<Transaction>) q.getResultList();
	}
}
