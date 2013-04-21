package eu.cointelligence.model;

import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_ACCOUNT")
public class Account {

	@ElementCollection
	private Map<Statement, Long> statementsInPossession;
	@Basic
	private Long cointels;
	@Id
	private Long id;

	public void setStatementsInPossession(Map<Statement, Long> param) {
		this.statementsInPossession = param;
	}

	public Map<Statement, Long> getStatementsInPossession() {
		return statementsInPossession;
	}

	public void setCointels(Long param) {
		this.cointels = param;
	}

	public Long getCointels() {
		return cointels;
	}

	public void setId(Long param) {
		this.id = param;
	}

	public Long getId() {
		return id;
	}

}