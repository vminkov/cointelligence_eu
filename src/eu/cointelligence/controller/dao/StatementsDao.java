package eu.cointelligence.controller.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import eu.cointelligence.model.Statement;

@Stateless
public class StatementsDao extends GenericDaoImpl<Statement> {
	
	public StatementsDao() {
		super(Statement.class);
	}

	public String getStatementById(String id){
		System.out.println(em != null);
		return new String(new Boolean(em != null).toString());
	}
	
	public List<Statement> getStatements(){
		Query q = em.createQuery("SELECT s from " + Statement.class.getName() + " as s");
	
		return (List) q.getResultList();
	}
}