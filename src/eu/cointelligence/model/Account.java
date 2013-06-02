package eu.cointelligence.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import eu.cointelligence.controller.entity.TradingAction;
import eu.cointelligence.model.ShortSell;

@Entity
@Table(name = "T_ACCOUNT")
public class Account {

	@Basic
	private Long cointels;
	@OneToMany 
	private Collection<StatementStake> statementsStakes;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@OneToMany
	private Collection<Idea> ideas;
	@OneToMany
	private Collection<Transaction> transactions;
	@OneToMany
	private Collection<ShortSell> shortSells;
	@Transient
	private Long totalWealth;
	
	public void setCointels(Long param) {
		this.cointels = param;
	}

	public Long getCointels() {
		return cointels;
	}

	public void setStatementsStakes(Collection<StatementStake> param) {
		this.statementsStakes = param;
	}
 
	public Map<Long, Long> getStatementsInPossession() {
		Map<Long, Long> result = new HashMap<Long, Long>();
		for(StatementStake stake : this.statementsStakes){
			result.put(stake.getStatement().getId(), stake.getSharesCount());
		}
		return result;
	}

	public void setId(Long param) {
		this.id = param;
	}

	public Long getId() {
		return id;
	}

	public Collection<Idea> getIdeas() {
	    return ideas;
	}

	public void setIdeas(Collection<Idea> param) {
	    this.ideas = param;
	}

	public Collection<Transaction> getTransactions() {
	    return transactions;
	}

	public void setTransactions(Collection<Transaction> param) {
	    this.transactions = param;
	}

	public Collection<ShortSell> getShortSells() {
	    return shortSells;
	}

	public void setShortSells(Collection<ShortSell> param) {
	    this.shortSells = param;
	}

	public Long getShortSellsCountForStatement(Long stId) {
		Long owned = new Long(0);
		for(ShortSell shortSell : getShortSells()){
			if(shortSell.getTransaction().getStatement().getId() == stId 
				//	&& TradingAction.SHORTSELL.toString().equals(transaction.getOrderType())) //unnecessary?
					){
				owned += shortSell.getAmount();
			}
		}
		return owned;
	}

	public Long getTotalWealth() {
		Long wealth = this.getCointels();
		for(StatementStake stake : this.statementsStakes){
			Long cashForStake = stake.getSharesCount() * stake.getStatement().getCurrentValue();
			wealth += cashForStake;
		}
		
		for(ShortSell sell : shortSells){
			Long cashForShort = sell.getAmount() * (sell.getTransaction().getPriceAtTrade() - sell.getTransaction().getStatement().getCurrentValue());
			
			wealth += cashForShort;
		}
		this.totalWealth = wealth;
		return totalWealth;
	}

	public Long getSharesForStatement(Long id2) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateStatementStake(Statement statement, Long newOwnedQuantity) {
		for(StatementStake st : this.statementsStakes){
			if(st.getId() == statement.getId()){
				st.setSharesCount(newOwnedQuantity);
				return;
			}
		}
		
		//else
		StatementStake stake = new StatementStake();
		stake.setStatement(statement);
		stake.setSharesCount(newOwnedQuantity);
		this.statementsStakes.add(stake);
		
		//persist?
	}
}
