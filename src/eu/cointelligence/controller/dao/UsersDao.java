package eu.cointelligence.controller.dao;

import javax.ejb.Stateless;

import eu.cointelligence.model.User;

@Stateless
public class UsersDao extends GenericDaoImpl<User> {


	public UsersDao() {
		super(User.class);
	}
	
}
