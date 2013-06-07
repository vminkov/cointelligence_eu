package eu.cointelligence.controller.pricing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import eu.cointelligence.controller.entity.TradingAction;
import eu.cointelligence.model.Statement;
import eu.cointelligence.model.Transaction;

public class PricingAlgorithmFactory {

	// TODO: polish it
	public static IPricingAlgorithm getDumbAlgorithm() {
		// a dumb algorithm
		return new IPricingAlgorithm() {

			@Override
			public Map<Long, Long> recalculate(Map<Long, Long> oldPrices,
					List<Transaction> logs) {

				Iterator<Transaction> iterator = logs.iterator();
				// int last10Counter = logs.size() - 10;
				//
				// while (last10Counter > 0) {
				// iterator.next();
				// last10Counter--;
				// }

				while (iterator.hasNext()) {
					Transaction log = iterator.next();
					if (log.getCheckedByMarketMaker() != null
							&& log.getCheckedByMarketMaker())
						continue;

					log.setCheckedByMarketMaker(new Boolean(true));

					Statement statement = log.getStatement();
					Long price = oldPrices.get(statement.getId());

					if (TradingAction.BUY.toString().equals(log.getOrderType())
							&& price < 100) {
						price += 1;
					} else if (TradingAction.SELL.toString().equals(
							log.getOrderType())
							&& price > 0) {
						price -= 1;
					}
					oldPrices.put(statement.getId(), price);

				}
				return oldPrices;
			}
		};
	}

	public static IPricingAlgorithm getPricingAlgorithm() {
		return new IPricingAlgorithm() {

			@Override
			public Map<Long, Long> recalculate(Map<Long, Long> oldPrices,
					List<Transaction> logs) {
				Map<Long, Long> sumsBought = new HashMap<Long, Long>();
				Map<Long, Long> sumsSold = new HashMap<Long, Long>();
				Map<Long, Long> sumsShortSold = new HashMap<Long, Long>();

				Map<Long, Long> netOborotZaStatement = new HashMap<Long, Long>();
				Map<Long, Long> obshtOborotZaStatement = new HashMap<Long, Long>();

				Map<Long, Statement> tradedStatements = new HashMap<Long, Statement>();
				
				Map<Long, Map<Long, Boolean>> statementsByUsers = new HashMap<Long, Map<Long, Boolean>>();
				
				
				Iterator<Transaction> iterator = logs.iterator();
				
				while (iterator.hasNext()) {
					Transaction log = iterator.next();
					if (log.getCheckedByMarketMaker() != null
							&& log.getCheckedByMarketMaker())
						continue;

					log.setCheckedByMarketMaker(new Boolean(true));

					Statement statement = log.getStatement();
					boolean found = false;
					for (Long id : tradedStatements.keySet()) {
						if (id.longValue() == statement.getId().longValue()) {
							found = true;
							break;
						}
					}
					if (!found) {
						tradedStatements.put(statement.getId(), statement);
					}
					Map<Long, Boolean> glasuvalite = statementsByUsers.get(statement.getId());
					if(glasuvalite == null){
						glasuvalite = new HashMap<Long, Boolean>();
						glasuvalite.put(log.getAccount().getId(), new Boolean(true));
						
						statementsByUsers.put(statement.getId(), glasuvalite);
					} else {
						Long akauntaNaNashiqChovek = log.getAccount().getId();
						if(glasuvalite.get(akauntaNaNashiqChovek) == null){
							glasuvalite.put(akauntaNaNashiqChovek, new Boolean(true));
						}
					}
					
					
//					Long price = oldPrices.get(statement.getId());
					
					if (TradingAction.BUY.toString().equals(log.getOrderType())) {
						Long boughtTillNow = sumsBought.get(statement.getId());
						if (boughtTillNow == null) {
							boughtTillNow = new Long(0);
						}

						boughtTillNow += log.getAmount();

						sumsBought.put(statement.getId(), boughtTillNow);
					} else if (TradingAction.SELL.toString().equals(
							log.getOrderType())) {
						Long soldTillNow = sumsSold.get(statement.getId());
						if (soldTillNow == null) {
							soldTillNow = new Long(0);
						}

						soldTillNow += log.getAmount();

						sumsSold.put(statement.getId(), soldTillNow);
					} else if (TradingAction.SHORTSELL.toString().equals(
							log.getOrderType())) {
						Long shortSoldTillNow = sumsShortSold.get(statement
								.getId());
						if (shortSoldTillNow == null) {
							shortSoldTillNow = new Long(0);
						}

						shortSoldTillNow += log.getAmount();

						sumsShortSold.put(statement.getId(), shortSoldTillNow);
					}
				}

				for (Long id : tradedStatements.keySet()) {
					if(sumsBought.get(id) == null){
						sumsBought.put(id, new Long(0));
					}
					if(sumsSold.get(id) == null){
						sumsSold.put(id, new Long(0));
					}
					if(sumsShortSold.get(id) == null){
						sumsShortSold.put(id, new Long(0));
					}
					netOborotZaStatement.put(id,
							sumsBought.get(id) - sumsSold.get(id)
									- sumsShortSold.get(id));
					obshtOborotZaStatement.put(id, sumsBought.get(id)
							+ sumsSold.get(id) + sumsShortSold.get(id));
				}

				for (Long id : tradedStatements.keySet()) {
					Long neten = netOborotZaStatement.get(id);
					Long obsht = obshtOborotZaStatement.get(id);
					if (obsht < 1 || neten < 1) {
						continue;
					}
					if(statementsByUsers.get(id).size() == 1){
						//samo 1 4ovek e glasuval za tova
						continue;
					}
					
					double procentnoIzmenenie = (((double) neten) / ((double) obsht)) * 100;
					
					long izmenenieNaCenata = 0;
					if(procentnoIzmenenie < 0) {
						if(procentnoIzmenenie > -30){
							izmenenieNaCenata = 0;
						} else if(procentnoIzmenenie > -65) {
							izmenenieNaCenata = 1;
						} else if(procentnoIzmenenie > - 95) {
							izmenenieNaCenata = 2;
						} else {
							izmenenieNaCenata = 3;
						}
					} else {
						if(procentnoIzmenenie < 30){
							izmenenieNaCenata = 0;
						} else if(procentnoIzmenenie < 65) {
							izmenenieNaCenata = 1;
						} else if(procentnoIzmenenie < 95) {
							izmenenieNaCenata = 2;
						} else {
							izmenenieNaCenata = 3;
						}
					}
					
					Long novataCena = oldPrices.get(id) + izmenenieNaCenata;
					
					oldPrices.put(id, novataCena);
				}

				return oldPrices;
			}
		};

	}
}