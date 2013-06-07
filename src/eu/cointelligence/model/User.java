package eu.cointelligence.model;

import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import eu.cointelligence.controller.entity.Gender;
import eu.cointelligence.controller.users.UserRole;

@Entity
@Table(name = "T_USER")
public class User {

	@Column(length=130)
	private String fullName;
	@Id
	private Long id;
	@Column(length=130)
	private String passwordHash;
	@Column(length=130)
	private String email;
	@Column(length=130)
	private String roleString;
	@Basic
	private int age;
	@Column(length=130)
	private String genderString;
	@Column(length=130)
	private String department;
	@OneToOne
	private Account account;
	@Column(unique=true,length=100)
	private String userName;
	
	public User(){
		if(id == null || new Long(0).equals(id))
			id = UUID.randomUUID().getLeastSignificantBits();
	}
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return Gender.valueOf(this.genderString);
	}

	public void setGender(Gender gender) {
		this.genderString = gender.toString();
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

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

	public void setRole(UserRole param) {
		this.roleString = param.toString();				
	}

	public UserRole getRole() {
		return UserRole.valueOf(roleString);
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account param) {
		this.account = param;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	

	public Long getId() {
		return id;
	}
}