package eu.cointelligence.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_USER")
public class User {

	@Basic
	private String fullName;
	@Basic
	private String userName;
	@Basic
	private String passwordHash;
	@Basic
	private String role;
	@OneToOne
	private Account account;
	@Id
	private Long id;

	public void setFullName(String param) {
		this.fullName = param;
	}

	public String getFullName() {
		return fullName;
	}

	public void setUserName(String param) {
		this.userName = param;
	}

	public String getUserName() {
		return userName;
	}

	public void setPasswordHash(String param) {
		this.passwordHash = param;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setRole(String param) {
		this.role = param;
	}

	public String getRole() {
		return role;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account param) {
		this.account = param;
	}

	public void setId(Long param) {
		this.id = param;
	}

	public Long getId() {
		return id;
	}

}