package eu.cointelligence.model;

import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_ACCOUNT")
public class Account {

	@Basic
	private Long cointels;
	@ElementCollection(fetch = FetchType.EAGER)
	private Map<Long, Long> statementsInPossession;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	public void setCointels(Long param) {
		this.cointels = param;
	}

	public Long getCointels() {
		return cointels;
	}

	public void setStatementsInPossession(Map<Long, Long> param) {
		this.statementsInPossession = param;
	}
 
	public Map<Long, Long> getStatementsInPossession() {
		return statementsInPossession;
	}

	public void setId(Long param) {
		this.id = param;
	}

	public Long getId() {
		return id;
	}

}