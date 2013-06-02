package eu.cointelligence.controller.entity.beans;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateful;

import eu.cointelligence.model.ShortSell;

@Stateful
public class UserPortfolioBean {
	private String fullname;
	private String username;
	private String passwordHash;
	private String email;
	private String role;
	private int age;
	private String gender;
	private String department;
	private Map<Long, Long> statementsInPossession;
	private Map<Long, Long> shortSellsInPossession;
	private Long cointels;
	
	private Long weatlh;
	
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
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
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
	public void setShortSellsInPossession(Collection<ShortSell> shortSells) {
		this.shortSellsInPossession = new HashMap<>();
		for(ShortSell sell : shortSells){
			Long statementId = sell.getTransaction().getStatement().getId();
			Long possessed = this.shortSellsInPossession.get(statementId);
			
			if(possessed == null){
				possessed = sell.getAmount();
			} else {
				possessed += sell.getAmount();
			}
			
			this.shortSellsInPossession.put(statementId, possessed);
		}
	}
	public Long getCointels() {
		return cointels;
	}
	public void setCointels(Long cointels) {
		this.cointels = cointels;
	}
	public Long getWeatlh() {
		return weatlh;
	}
	public void setWeatlh(Long weatlh) {
		this.weatlh = weatlh;
	}
}
