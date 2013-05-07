package eu.cointelligence.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import eu.cointelligence.controller.dao.StatementsDao;
import eu.cointelligence.model.Statement;

@Stateless
@Path("/statements")
public class StatementsService {	

	@EJB
	private StatementsDao dao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Statement> getStatements(){
		return dao.getStatements();
	}
}
