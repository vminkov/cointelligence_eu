package eu.cointelligence.controller.entity.beans;

import java.io.Serializable;


public class StatementBean implements Serializable{
	private String title;
	private String description;
	private Boolean voteStarted;
	private Long currentValue;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getVoteStarted() {
		return voteStarted;
	}
	public void setVoteStarted(Boolean voteStarted) {
		this.voteStarted = voteStarted;
	}
	public Long getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(Long currentValue) {
		this.currentValue = currentValue;
	}
}
