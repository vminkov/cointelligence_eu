package eu.cointelligence.controller;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import eu.cointelligence.controller.entity.beans.RegisterForm;
import eu.cointelligence.controller.entity.beans.UserPortfolioBean;
import eu.cointelligence.controller.entity.beans.UserRanking;
import eu.cointelligence.controller.users.IUserManager;
import eu.cointelligence.controller.users.UserRole;
import eu.cointelligence.controller.users.exceptions.NoSuchUserException;
import eu.cointelligence.controller.users.exceptions.UserCreationException;
import eu.cointelligence.controller.users.exceptions.UserExistsException;
import eu.cointelligence.controller.users.exceptions.WrongPasswordException;
import eu.cointelligence.model.User;

@Stateless
@Path("/users")
public class UsersService {
	@EJB
	private IUserManager userManager;

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public Cookie login(@FormParam("username") String username,
			@FormParam("password") String password) {
		User validatedUser = null;
		try {
			if ((validatedUser = userManager.login(username, password,
					UserRole.USER)) != null) {
				return new Cookie("keepSession",
						validatedUser.getPasswordHash());
			}
		} catch (javax.resource.spi.SecurityException | WrongPasswordException
				| NoSuchUserException e) {
			e.printStackTrace();
		}

		return null;
	}

	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON) -- do not use consumes
	public Response register(@FormParam("regForm") RegisterForm regForm) {
		if (regForm == null) {
			return Response.noContent().build();
		}

		User newUser = null;
		try {
			try {
				newUser = userManager.createNewUser(regForm.getUsername(),
						regForm.getPassword());
			} catch (UserExistsException e) {
				// the user already exists
				return Response.status(Response.Status.CONFLICT)
						.entity("user already exists").build();
			}
			// newUser.set stuff and then userManager.updateUser
			newUser.setFullName(regForm.getFullname());
			newUser.setAge(regForm.getAge());
			newUser.setGender(regForm.getGender());
			newUser.setDepartment(regForm.getDepartment());
			newUser.setEmail(regForm.getEmail());

			userManager.updateUserInfo(newUser);
		} catch (SecurityException | UserCreationException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("ooops").build();
		}

		return Response.ok(newUser, MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/userExists")
	public boolean checkUser(@QueryParam("username") String username) {
		try {
			return this.userManager.isUser(username);
		} catch (InvalidParameterException e) {
			// nothing
		}
		return false;
	}

	@GET
	@Path("/ranking")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserRanking> getUsers() {
		List<UserRanking> rankings = new ArrayList<UserRanking>();
		for (User user : this.userManager.getAllUsers()) {
			UserRanking ranking = new UserRanking();
			ranking.setCointels(user.getAccount().getCointels());
			ranking.setFullname(user.getFullName());
			// TODO!
			ranking.setShortSellsInPossession(null);

			ranking.setStatementsInPossession(user.getAccount()
					.getStatementsInPossession());

			ranking.setTotal(user.getAccount().getTotalWealth());

			ranking.setUsername(user.getUserName());

			rankings.add(ranking);
		}
		return rankings;
	}

	@POST
	@Path("/portfolio")
	@Produces(MediaType.APPLICATION_JSON)
	public UserPortfolioBean getPersonalInfo(
			@FormParam("username") String username,
			@FormParam("token") String password) {
		if (username == null || password == null) {
			return null;
		}

		User thisUser = this.userManager.getUser(username, password);
		if (thisUser == null) {
			return null;
		}

		UserPortfolioBean folio = new UserPortfolioBean();
		folio.setAge(thisUser.getAge());
		folio.setCointels(thisUser.getAccount().getCointels());
		folio.setDepartment(thisUser.getDepartment());
		folio.setEmail(thisUser.getEmail());
		folio.setFullname(thisUser.getFullName());
		folio.setGender(thisUser.getGender().toString());
		folio.setRole(thisUser.getRole().toString());

		folio.setShortSellsInPossession(thisUser.getAccount().getShortSells());

		folio.setStatementsInPossession(thisUser.getAccount()
				.getStatementsInPossession());
		folio.setUsername(thisUser.getUserName());
		folio.setWealth(thisUser.getAccount().getTotalWealth());

		return folio;
	}
}
