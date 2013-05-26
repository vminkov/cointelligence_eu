package eu.cointelligence.controller.users;

import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.resource.spi.SecurityException;

import eu.cointelligence.controller.users.exceptions.UserCreationException;
import eu.cointelligence.controller.users.exceptions.UserExistsException;
import eu.cointelligence.controller.users.exceptions.WrongPasswordException;
import eu.cointelligence.model.User;

@Singleton
public class UserManagerImpl implements IUserManager {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void removeUser(String username, String comment) {
		// TODO Auto-generated method stub

	}

	@Override
	public User login(String username, String password, UserRole loginType)
			throws SecurityException, WrongPasswordException, NoSuchUserException {
		if (username == null || password == null)
			throw new InvalidParameterException("username: " + username
					+ " password: " + password + " are not valid");

		final String userSearch = username.toLowerCase();

		User user = this.entityManager.find(User.class, userSearch);
		if(user == null) {
			throw new NoSuchUserException();
		}
		UserRole role = user.getRole();
		if (role == null || !role.equals(loginType)) {
			throw new SecurityException();
		}

		if (!user.getPassword().equals(password)) {
			throw new WrongPasswordException();
		} else {
			return user;
		}
	}

	@Override
	public User loginWithCookie(String username, byte[] passwordHash,
			UserRole loginType) throws WrongPasswordException,
			SecurityException, NoSuchUserException {
		if (username == null || passwordHash == null
				|| passwordHash.length == 0)
			throw new InvalidParameterException("username: " + username
					+ " password: " + passwordHash + " are not valid");

		final String userSearch = username.toLowerCase();

		User user = this.entityManager.find(User.class, userSearch);
		if(user == null){
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

		User user = this.entityManager.find(User.class, userSearch);
		try {
			if (user != null) {
				System.out.println("found the user " + user.getUserName());
				// XXX revise this later
				if (user.getPassword() == null) {
					// this.entityManager.persist(user);

					user.setPasswordHash(getMD5Hash(password));
					user.setPassword(password);

					System.out.println("setting new pass for user "
							+ user.getUserName() + "\n pass is "
							+ user.getPassword());
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

				user.setPassword(password);
				user.setRole(UserRole.USER);
				// user.removed = Boolean.valueOf(false);
				// user.confirmed = Boolean.valueOf(true);
				System.out.println("creating user " + user.getUserName()
						+ " with pass " + user.getPassword());
			}

			this.entityManager.merge(user);
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

		User user = this.entityManager.find(User.class, userSearch);

		return user.getRole().equals(UserRole.ADMIN);
	}

	@Override
	public boolean isManager(String username) {
		if (username == null)
			throw new InvalidParameterException("username: " + username
					+ " is not valid");

		final String userSearch = username.toLowerCase();

		User user = this.entityManager.find(User.class, userSearch);

		return user.getRole().equals(UserRole.MANAGER);
	}

	@Override
	public boolean isUser(String username) {
		if (username == null)
			throw new InvalidParameterException("username: " + username
					+ " is not valid");

		final String userSearch = username.toLowerCase();

		User user = this.entityManager.find(User.class, userSearch);

		return user.getRole().equals(UserRole.USER);
	}

	@Override
	public User getUser(String username, String password) {
		if (username == null || password == null)
			throw new InvalidParameterException("username: " + username
					+ " password: " + password + " are not valid");

		final String userSearch = username.toLowerCase();

		return this.entityManager.find(User.class, userSearch);

	}

	@Override
	public User updateUserInfo(User user) {
		// do not update the password from here
		if (user.getPassword() != null)
			return null;

		this.entityManager.merge(user);
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

}
