package eu.cointelligence.controller.entity.beans;

import java.util.Map;

public class UserRanking {
	private String fullname;
	private String username;
	private Map<Long, Long> statementsInPossession;
	private Map<Long, Long> shortSellsInPossession;
	private Long cointels;
	private Long total;
	
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Map<Long, Long> getStatementsInPossession() {
		return statementsInPossession;
	}
	public void setStatementsInPossession(Map<Long, Long> statementsInPossession) {
		this.statementsInPossession = statementsInPossession;
	}
	public Map<Long, Long> getShortSellsInPossession() {
		return shortSellsInPossession;
	}
	public void setShortSellsInPossession(Map<Long, Long> shortSellsInPossession) {
		this.shortSellsInPossession = shortSellsInPossession;
	}
	public Long getCointels() {
		return cointels;
	}
	public void setCointels(Long cointels) {
		this.cointels = cointels;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
}
