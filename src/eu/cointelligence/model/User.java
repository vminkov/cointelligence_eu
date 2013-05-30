package eu.cointelligence.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import eu.cointelligence.controller.entity.beans.Gender;
import eu.cointelligence.controller.users.UserRole;

@Entity
@Table(name = "T_USER")
public class User {

	@Basic
	private String fullName;
	@Id
	@Basic
	private String userName;
	@Basic
	private String passwordHash;
	@Basic
	private String email;
	@Basic
	private String password;
	@Basic
	private String roleString;
	@Basic
	private int age;
	@Basic
	private String genderString;
	@Basic 
	private String department;
		
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

	@OneToOne
	private Account account;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
}