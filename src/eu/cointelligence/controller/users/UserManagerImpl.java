package eu.cointelligence.controller.users;

import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.resource.spi.SecurityException;

import eu.cointelligence.controller.Constants;
import eu.cointelligence.controller.dao.AccountsDao;
import eu.cointelligence.controller.dao.UsersDao;
import eu.cointelligence.controller.users.exceptions.NoSuchUserException;
import eu.cointelligence.controller.users.exceptions.UserCreationException;
import eu.cointelligence.controller.users.exceptions.UserExistsException;
import eu.cointelligence.controller.users.exceptions.WrongPasswordException;
import eu.cointelligence.model.Account;
import eu.cointelligence.model.StatementStake;
import eu.cointelligence.model.User;

@Singleton
public class UserManagerImpl implements IUserManager {

	@EJB
	private UsersDao usersDao;
	@EJB
	private AccountsDao accountsDao;

	@Override
	public void removeUser(String username, String comment) {
		// TODO Auto-generated method stub

	}

	@Override
	public User login(String username, String password, UserRole loginType)
			throws SecurityException, WrongPasswordException,
			NoSuchUserException {
		if (username == null || password == null)
			throw new InvalidParameterException("username: " + username
					+ " password: " + password + " are not valid");

		final String userSearch = username.toLowerCase();

		User user = this.usersDao.find(userSearch);
		if (user == null) {
			throw new NoSuchUserException();
		}

		UserRole role = user.getRole();
		// TODO: smarter check according to the hierarchy
		if (role == null || !role.equals(loginType)) {
			throw new SecurityException();
		}
		boolean validPass = false;
		try {
			validPass = user.getPasswordHash().equals(getMD5Hash(password));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new SecurityException(e);
		}
		if (!validPass) {
			throw new WrongPasswordException();
		}
		return user;
	}

	@Override
	public User loginWithCookie(String username, String passwordHash,
			UserRole loginType) throws WrongPasswordException,
			SecurityException, NoSuchUserException {
		if (username == null || passwordHash == null)
			throw new InvalidParameterException("username: " + username
					+ " password: " + passwordHash + " are not valid");

		final String userSearch = username.toLowerCase();

		User user = this.usersDao.find(userSearch);
		if (user == null) {
			throw new NoSuchUserException();
		}
		if (!user.getRole().equals(loginType)) {
			throw new SecurityException();
		}

		if (!user.getPasswordHash().equals(new String(passwordHash))) {
			throw new WrongPasswordException();
		} else {
			return user;
		}
	}

	@Override
	public User createNewUser(String username, String password)
			throws UserExistsException, UserCreationException {
		if (username == null || password == null)
			throw new InvalidParameterException("username: " + username
					+ " password: " + password + " are not valid");

		final String userSearch = username.toLowerCase();

		User user = this.usersDao.find(userSearch);
		try {
			if (user != null) {
				System.out.println("found the user " + user.getUserName());
				// XXX revise this later
				if (user.getPasswordHash() == null) {
					// this.entityManager.persist(user);

					user.setPasswordHash(getMD5Hash(password));

					System.out.println("setting new pass for user "
							+ user.getUserName() + "\n pass is " + password);
				} else {
					System.out.println("the user: " + username
							+ " already exist");
					throw new UserExistsException();
				}
			} else {
				user = new User();
				user.setUserName(username.toLowerCase());
				// this.entityManager.persist(user);

				user.setPasswordHash(getMD5Hash(password));

				user.setRole(UserRole.USER);
				// user.removed = Boolean.valueOf(false);
				// user.confirmed = Boolean.valueOf(true);
				Account newAccount = accountsDao.create(new Account());

				newAccount.setCointels(Constants.DEFAULT_COINTELS);
				newAccount.setStatementsStakes(new ArrayList<StatementStake>());

				user.setAccount(newAccount);
				System.out.println("creating user " + user.getUserName()
						+ " with pass " + password);
			}

			this.usersDao.update(user);
			return user;
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println("failed to set a new password");
			throw new UserCreationException(e);
		}
	}

	@Override
	public User confirmUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changePasswordOfUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAdmin(String username) {
		if (username == null)
			throw new InvalidParameterException("username: " + username
					+ " are not valid");

		final String userSearch = username.toLowerCase();

		User user = this.usersDao.find(userSearch);

		return user.getRole().equals(UserRole.ADMIN);
	}

	@Override
	public boolean isManager(String username) {
		if (username == null)
			throw new InvalidParameterException("username: " + username
					+ " is not valid");

		final String userSearch = username.toLowerCase();

		User user = this.usersDao.find(userSearch);

		return user.getRole().equals(UserRole.MANAGER);
	}

	@Override
	public boolean isUser(String username) {
		if (username == null)
			throw new InvalidParameterException("username: " + username
					+ " is not valid");

		final String userSearch = username.toLowerCase();

		User user = this.usersDao.find(userSearch);
		if (user == null)
			return false;

		return user.getRole().equals(UserRole.USER);
	}

	@Override
	public User getUser(String username, String password) {
		if (username == null || password == null)
			throw new InvalidParameterException("username: " + username
					+ " password: " + password + " are not valid");

		final String userSearch = username.toLowerCase();

		return this.usersDao.find(userSearch);

	}

	@Override
	public User updateUserInfo(User user) {
		// // do not update the password from here
		// if (user.getPassword() != null)
		// return null;

		User result = this.usersDao.find(user.getUserName());
		User merged = this.usersDao.update(result);

		merged.setAge(user.getAge());
		if (user.getDepartment() != null)
			merged.setDepartment(user.getDepartment());
		if (user.getEmail() != null)
			merged.setEmail(user.getEmail());
		if (user.getFullName() != null)
			merged.setFullName(user.getFullName());
		if (user.getGender() != null)
			merged.setGender(user.getGender());
		if (user.getRole() != null)
			merged.setRole(user.getRole());

		return user;
	}

	private String getMD5Hash(String password) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		byte[] passwordAsBytes = password.getBytes("UTF-8");
		MessageDigest md = null;
		md = MessageDigest.getInstance("MD5");
		byte[] theDigest = md.digest(passwordAsBytes);

		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < theDigest.length; i++) {
			String hex = Integer.toHexString(0xff & theDigest[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}

		return hexString.toString();
	}

	@Override
	public List<User> getAllUsers() {
		return this.usersDao.getAll();
	}

}
