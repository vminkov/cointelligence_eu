package eu.cointelligence.controller;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.spi.HttpResponse;

import eu.cointelligence.controller.entity.beans.RegisterForm;
import eu.cointelligence.controller.users.IUserManager;
import eu.cointelligence.controller.users.NoSuchUserException;
import eu.cointelligence.controller.users.UserRole;
import eu.cointelligence.controller.users.exceptions.UserCreationException;
import eu.cointelligence.controller.users.exceptions.UserExistsException;
import eu.cointelligence.controller.users.exceptions.WrongPasswordException;
import eu.cointelligence.model.User;

@Stateless
@Path("/login")
public class UsersService {
	@EJB
	private IUserManager userManager;
	
	@POST
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public Cookie login(@FormParam("username") String username, @FormParam("password") String password) {
		User validatedUser = null;
		try {
			if((validatedUser = userManager.login(username, password, UserRole.USER)) != null){
				return new Cookie("keepSession", validatedUser.getPasswordHash());
			}
		} catch (javax.resource.spi.SecurityException | WrongPasswordException | NoSuchUserException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.APPLICATION_JSON) -- do not use consumes
	public Response register(@FormParam("regForm") RegisterForm regForm) {
		if(regForm == null){
			return Response.noContent().build();
		}
		
		User newUser = null;
		try {
			try {
				newUser = userManager.createNewUser(regForm.getUsername(), regForm.getPassword());
			} catch (UserExistsException e){	
				//the user already exists
				return Response.status(Response.Status.CONFLICT).entity("user already exists").build();
			}
			//newUser.set stuff and then userManager.updateUser
			newUser.setFullName(regForm.getFullname());
			newUser.setAge(regForm.getAge());
			newUser.setGender(regForm.getGender());
			newUser.setDepartment(regForm.getDepartment());
			newUser.setEmail(regForm.getEmail());
			newUser.setPassword(null);//so it can update

			userManager.updateUserInfo(newUser);
		}catch(SecurityException | UserCreationException e){
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ooops").build();
		}
		
		return Response.ok(newUser, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/userExists")
	public boolean checkUser(@FormParam("username") String username) {
		return this.userManager.isUser(username);
	}
	
	@GET
	@Path("/allUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers(){
		return this.userManager.getAllUsers();
	}
}
