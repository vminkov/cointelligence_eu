package eu.cointelligence.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import eu.cointelligence.model.Statement;

@Path("/statements")
public class StatementsService {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Statement> getStatements(){
		List<Statement> list = new ArrayList<Statement>();
		Statement st = new Statement();
		st.setCurrentValue((long) 23);
		st.setDescription("descr");
		list.add(st);
		
		
		return list;
	}
}
