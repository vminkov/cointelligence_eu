package eu.cointelligence.model;

import javax.persistence.*;

@Table(name = "T_IDEA")
@Entity
public class Idea {

	@ManyToOne
	private User user;
	@Id
	private Long id;
	@Basic
	private String title;
	@Basic
	private String description;
	@Basic
	private String company;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setId(Long param) {
		this.id = param;
	}

	public Long getId() {
		return id;
	}

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

	public void setCompany(String param) {
		this.company = param;
	}

	public String getCompany() {
		return company;
	}

}