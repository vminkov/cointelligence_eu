package eu.cointelligence.controller.dao;

import javax.ejb.Stateless;

import eu.cointelligence.model.Idea;

@Stateless
public class IdeasDao extends GenericDaoImpl<Idea> 	{

	public IdeasDao() {
		super(Idea.class);
	}
	

}
