package eu.cointelligence.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import eu.cointelligence.controller.users.UserRole;
import eu.cointelligence.model.User;

public class UserRoleTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		String bad = "Ð´ÑÐ°Ð´Ð°ÑÐ´Ð°ÑÐ´Ðµ";
		
		
		
		
//		UserRole ur1 = UserRole.USER;
//		UserRole ur2 = UserRole.USER;
//		
//		assertTrue(ur1.equals(ur2));
//		String json = "{\"username\":\"testio32\",\"fullname\":\"pylnoto\",\"password\":\"testev\",\"age\":21,\"gender\":\"MALE\",\"department\":\"testers\",\"role\":\"USER\"}";
//		User user = new Gson().fromJson(json, User.class);
//		
//		assertNotNull(user);
//		
//		assertTrue(ur1.equals(user.getRole()));
	}

}
