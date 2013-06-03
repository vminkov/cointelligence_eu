package eu.cointelligence.controller.dao;

import javax.ejb.Stateless;

import eu.cointelligence.model.ShortSell;

@Stateless
public class ShortSellsDao extends GenericDaoImpl<ShortSell>{

	public ShortSellsDao() {
		super(ShortSell.class);
	}
}
