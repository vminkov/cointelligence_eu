package eu.cointelligence.controller.dao;

import javax.ejb.Stateless;

import eu.cointelligence.model.Account;

@Stateless
public class AccountsDao extends GenericDaoImpl<Account>{

	public AccountsDao() {
		super(Account.class);
	}
	
}
