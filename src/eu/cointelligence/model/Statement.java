package eu.cointelligence.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import eu.cointelligence.controller.users.UserRole;

@Entity
@Table(name = "T_STATEMENT")
public class Statement {
	private static Gson gson = new Gson();
	// private static transient Gson gson = new Gson(); ?
	@Basic
	private String title;
	@Basic
	private String description;
	@Basic
	private String ownersGroup;
	@Basic
	private Boolean voteStarted;
	@Basic
	private Long currentValue;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	public void setTitle(String param) {
		this.title = param;
	}

	public String getTitle() {
		return title;
	}

	public void setDescription(String param) {
		this.description = param;
	}

	public String getDescription() {
		return description;
	}

	public void setVoteStarted(Boolean param) {
		this.voteStarted = param;
	}

	public Boolean getVoteStarted() {
		return voteStarted;
	}

	public void setCurrentValue(Long param) {
		this.currentValue = param;
	}

	public Long getCurrentValue() {
		return currentValue;
	}

	public static Statement valueOf(String s) {
		Statement statement = null;
		try {
			statement = gson.fromJson(s, Statement.class);
		} catch (JsonParseException e) {
			// TODO: adequate exception logging
			e.printStackTrace();
		}
		return statement;
	}

	public void setId(Long param) {
		this.id = param;
	}

	public Long getId() {
		return id;
	}
	
	public UserRole getOwnersGroup() {
		return UserRole.valueOf(ownersGroup);
	}
	
	public void setOwnersGroup(UserRole ownersGroup) {
		this.ownersGroup = ownersGroup.toString();
	}
}
