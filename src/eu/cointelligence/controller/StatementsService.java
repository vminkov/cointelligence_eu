package eu.cointelligence.controller;

import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean submitStatement(@FormParam("newStatement") Statement statement){
		if(statement != null){
			// set the ID 
			statement.setId(UUID.randomUUID().toString());

			//if (user is not manager)
			//set voteStarted to false
			//current price to default
			
			
			//else if manager - use the provided
			
			System.out.println(statement.getDescription());
		}
		return false;
	}
}
