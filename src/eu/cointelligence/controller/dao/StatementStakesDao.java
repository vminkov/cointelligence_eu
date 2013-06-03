package eu.cointelligence.controller.dao;

import javax.ejb.Stateless;

import eu.cointelligence.model.StatementStake;

@Stateless
public class StatementStakesDao extends GenericDaoImpl<StatementStake>{

	public StatementStakesDao() {
		super(StatementStake.class);
		// TODO Auto-generated constructor stub
	}

}
