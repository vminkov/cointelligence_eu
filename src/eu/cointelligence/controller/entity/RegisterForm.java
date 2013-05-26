package eu.cointelligence.controller.entity;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

public class RegisterForm {
	private static Gson gson = new Gson();
	
	private String username;
	private String fullname;
	private String password;
	private String email;
	private int age;
	private Gender gender;
	private String department; //string - just for the alpha
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public static RegisterForm valueOf(String rf) {
		RegisterForm regForm = null;
		try {
			regForm = gson.fromJson(rf, RegisterForm.class);
		} catch (JsonParseException e) {
			// TODO: adequate exception logging
			e.printStackTrace();
		}
		return regForm;
	}
	
	@Override
	public String toString() {
		return gson.toJson(this);
	}
}
