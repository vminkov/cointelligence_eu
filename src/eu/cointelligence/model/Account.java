package eu.cointelligence.model;

import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_ACCOUNT")
public class Account {

	@Basic
	private Long cointels;
	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, Long> statementsInPossession;
	@Id
	private String id;

	public void setCointels(Long param) {
		this.cointels = param;
	}

	public Long getCointels() {
		return cointels;
	}

	public void setStatementsInPossession(Map<String, Long> param) {
		this.statementsInPossession = param;
	}

	public Map<String, Long> getStatementsInPossession() {
		return statementsInPossession;
	}

	public void setId(String param) {
		this.id = param;
	}

	public String getId() {
		return id;
	}

}