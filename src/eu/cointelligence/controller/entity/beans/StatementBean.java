package eu.cointelligence.controller.entity.beans;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import eu.cointelligence.model.Statement;


public class StatementBean implements Serializable{
	private String title;
	private String description;
	private Boolean voteStarted;
	private Long currentValue;
	private Long id;
	
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
	
	
	private static Gson gson = new Gson();

	public static StatementBean valueOf(String s) {
		StatementBean statement = null;
		try {
			statement = gson.fromJson(s, StatementBean.class);
		} catch (JsonParseException e) {
			// TODO: adequate exception logging
			e.printStackTrace();
		}
		return statement;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
