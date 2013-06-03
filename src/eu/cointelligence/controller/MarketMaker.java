package eu.cointelligence.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import eu.cointelligence.controller.dao.StatementsDao;
import eu.cointelligence.controller.dao.TransactionsDao;
import eu.cointelligence.controller.users.UserRole;
import eu.cointelligence.model.Statement;
import eu.cointelligence.model.Transaction;

@Singleton
@Startup
public class MarketMaker implements IMarketMaker {
	@EJB
	private TransactionsDao transactionsDao;
	@EJB
	private StatementsDao statementsDao;
	
	
	private Map<Long, Long> statementsAndPrices;

    @PostConstruct
    public void init() {
        this.statementsAndPrices = new HashMap<Long, Long>();
		List<Statement> statements = statementsDao.getStatements();
		
		if(statements.size() == 0){
			//testing data
			for(int i = 0; i < 5; i++){
				Statement st = new Statement();
				st.setCurrentValue(new Long(30 + 10*i));
				if(i < 5)
					st.setDescription("The description " + i);
				
				//st.setId(new Long(i));
				if(i > 0)
					st.setTitle(i + "'s title");
				st.setVoteStarted( i % 2 == 0);
				
				this.statementsDao.create(st);
			}
		}
		
		for (Statement statement : statements) {
			this.statementsAndPrices.put(statement.getId(), statement.getCurrentValue());
			System.out.println(statement.getTitle() + ": " + statement.getCurrentValue());
		}    
		
		//recomputePrices();
	}


	
	@Override
	public Map<Long, Long> getStatementsAndPrices() {
		return this.statementsAndPrices;
	}

	@Override
	public Long getPriceForStatement(Long statementId) {
		return this.statementsDao.find(statementId).getCurrentValue();
	}

	@Override
	public boolean addLog(Transaction log) {
		return (this.transactionsDao.create(log) != null);
	}

	@Override
	public boolean addStatement(Statement statement) {
		return (this.statementsDao.create(statement) != null);
	}

	//TODO
	@Override
	@Schedule(hour = "*", minute = "*/1")
	public void recomputePrices() {
		System.out.println("RECALCULATING!");
		//List<Transaction> logs = transactionsDao.getAllTransaction();
		
		//WE USE THESE LOGS!! filter in order to not overflow the memory
		List<Transaction> logs = transactionsDao.getAllTransaction();
		
		statementsAndPrices = new HashMap<>();
		List<Statement> statements = this.statementsDao.getStatements();
		
		for (Statement statement : statements) {
			this.statementsAndPrices.put(statement.getId(), statement.getCurrentValue());
		}  
		
		statementsAndPrices = PricingAlgorithmFactory.getPricingAlgorithm().recalculate(statementsAndPrices, logs);
		
		//TODO: batch commit(see comment below)
		for (Long stId : this.statementsAndPrices.keySet()) {
			Statement st = this.statementsDao.find(stId);
			st.setCurrentValue(this.statementsAndPrices.get(stId));
			statementsDao.update(st);
			System.out.println(stId + " струва " +  this.statementsAndPrices.get(stId));
		}
		
		
		//TODO: make it a batch commit (turn auto commit off and then commit?)
		for(Transaction log : logs){
			transactionsDao.update(log);
		}
	}

}
