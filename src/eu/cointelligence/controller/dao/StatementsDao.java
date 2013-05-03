package eu.cointelligence.controller.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import eu.cointelligence.model.Statement;

@Stateless
public class StatementsDao extends GenericDaoImpl<Statement> {
	
	@PersistenceContext(unitName = "COINTELLIGENCE")
	private EntityManager em;
	
	public String getStatementById(String id){
		System.out.println(em != null);
		return new String(new Boolean(em != null).toString());
	}
} 
