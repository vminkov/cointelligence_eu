package eu.cointelligence.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import eu.cointelligence.model.Statement;

@Stateless
@Path("/statements")
public class StatementsService {	
	
	@PersistenceContext
	private EntityManager em;


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Statement> getStatements(){
		Query q = em.createQuery("SELECT s from " + Statement.class.getName() + " as s");

		return (List) q.getResultList();
	}
}
