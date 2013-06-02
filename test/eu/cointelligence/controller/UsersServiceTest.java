package eu.cointelligence.controller;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import eu.cointelligence.controller.entity.Gender;
import eu.cointelligence.controller.entity.beans.RegisterForm;

public class UsersServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRegister() throws NamingException {
	
		String regJson = "{\"username\":\"testio\",\"password\":\"testev\",\"age\":21,\"gender\":\"MALE\",\"department\":\"testers\"}";
		
		RegisterForm regForm2 = RegisterForm.valueOf(regJson);
		System.out.println(regForm2);
		
		RegisterForm regForm = new RegisterForm();
		regForm.setAge(21);
		regForm.setUsername("tester");
		regForm.setPassword("testing123");
		regForm.setGender(Gender.valueOf("MALE"));
		regForm.setDepartment("testers dep");
		
		System.out.println(regForm.getGender());

	}

}
