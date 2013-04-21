package eu.cointelligence.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/test")
public class TestRest {
	@GET
	@QueryParam("/{name}{userId}")
	
	public Response getMsg(@QueryParam("name") final String name, @QueryParam("userId") final Long userId){
		
		String output = String.format("Hi, %1$s with id %2$d", name, userId);
		return Response.status(200).entity(output).build();
	}
}
