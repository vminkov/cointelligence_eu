package eu.cointelligence.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import eu.cointelligence.controller.dao.IdeasDao;
import eu.cointelligence.model.Idea;

@Stateless
@Path("/ideas")
public class IdeasService {
	@EJB
	private IdeasDao ideasDao;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Idea> getAll() {

		return ideasDao.getAll();
	}
	
	@GET
	@Path("/{ideaId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Idea getById(@PathParam("ideaId") Long ideaId){
		return ideasDao.find(ideaId);
	}
	
	@GET
	@POST
	@Path("/company/{company}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Idea> getByCompany(@PathParam("company") String company){
		return ideasDao.filter("company", company);
	}
}
