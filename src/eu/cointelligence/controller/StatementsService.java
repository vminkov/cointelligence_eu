package eu.cointelligence.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import eu.cointelligence.controller.dao.StatementsDao;
import eu.cointelligence.controller.entity.beans.StatementBean;
import eu.cointelligence.model.Statement;

@Stateless
@Path("/statements")
public class StatementsService {

	@EJB
	private ITrader trader;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<StatementBean> getStatements() {
		List<Statement> statements = this.trader.getStatements();
		List<StatementBean> beans = new ArrayList<StatementBean>();

		for (Statement statement : statements) {
			StatementBean bean = constructBeanFromEntity(statement);

			beans.add(bean);
		}

		return beans;
	}

	@GET
	@Path("/{stId}")
	@Produces(MediaType.APPLICATION_JSON)
	public StatementBean getStatement(@PathParam("stId") Long statementId) {
		Statement statement = this.trader.getStatementById(statementId);
		StatementBean bean = null;
		if(statement != null){
			bean = constructBeanFromEntity(statement);
		}
		
		return bean;
	}

	@POST
	@Path("/submit")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean submitStatement(@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("newStatement") StatementBean statement) {
		if (statement != null) {
			//this.trader.submitQuestion();
			// if (user is not manager)
			// set voteStarted to false
			// current price to default

			// else if manager - use the provided

			System.out.println(statement.getDescription());
		}
		return false;
	}

	private StatementBean constructBeanFromEntity(Statement entity) {
		StatementBean bean = new StatementBean();

		bean.setCurrentValue(entity.getCurrentValue());
		bean.setDescription(entity.getDescription());
		bean.setTitle(entity.getTitle());
		bean.setVoteStarted(entity.getVoteStarted());
		bean.setId(entity.getId());

		return bean;
	}
}
