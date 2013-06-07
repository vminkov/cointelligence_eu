package eu.cointelligence.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import eu.cointelligence.controller.Constants;
import eu.cointelligence.model.ShortSell;

@Entity
@Table(name = "T_ACCOUNT")
public class Account {

	@Basic
	private Long cointels;
	@OneToMany(mappedBy = "account")
	private Collection<StatementStake> statementsStakes;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany
	private Collection<Idea> ideas;
	@OneToMany(mappedBy = "account")
	private Collection<Transaction> transactions;
	@OneToMany(mappedBy = "account")
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
		Map<Long, Long> statementsInPossession = new HashMap<Long, Long>();
		for (StatementStake stake : this.statementsStakes) {
			Long countUntilNow = statementsInPossession.get(stake
					.getStatement().getId());
			if (countUntilNow == null) {
				countUntilNow = new Long(0);
			}

			countUntilNow += stake.getSharesCount();
			statementsInPossession.put(stake.getStatement().getId(),
					countUntilNow);
		}
		return statementsInPossession;
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

	public Long getShortSellsCountForStatement(Long stId) {
		Long owned = new Long(0);
		for (ShortSell shortSell : getShortSells()) {
			if (shortSell.getTransaction().getStatement().getId() == stId
			// &&
			// TradingAction.SHORTSELL.toString().equals(transaction.getOrderType()))
			// //unnecessary?
			) {
				owned += shortSell.getAmount();
			}
		}
		return owned;
	}

	public Long getTotalWealth() {
		Long wealth = this.getCointels();
		for (StatementStake stake : this.statementsStakes) {
			Long cashForStake = stake.getSharesCount()
					* stake.getStatement().getCurrentValue();
			wealth += cashForStake;
		}

		for (ShortSell sell : shortSells) {
			Long cashForShort = sell.getAmount()
					* (Constants.MAX_PRICE_FOR_A_STATMENT
							+ sell.getTransaction().getPriceAtTrade() - sell
							.getTransaction().getStatement().getCurrentValue());

			wealth += cashForShort;
		}
		this.totalWealth = wealth;
		return totalWealth;
	}

	public Long getSharesForStatement(Long id2) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<StatementStake> getStatementStake() {
		return this.statementsStakes;
	}
}
