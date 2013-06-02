package eu.cointelligence.controller.users;

import java.util.List;

import javax.resource.spi.SecurityException;

import eu.cointelligence.controller.users.exceptions.NoSuchUserException;
import eu.cointelligence.controller.users.exceptions.UserCreationException;
import eu.cointelligence.controller.users.exceptions.UserExistsException;
import eu.cointelligence.controller.users.exceptions.WrongPasswordException;
import eu.cointelligence.model.User;

public interface IUserManager {

    /**
     * It is not actually a remove, just this user will not be able to log in
     * 
     * @param username
     *            the username of the user to be removed
     * @param comment
     *            comment of the user removal
     */
    void removeUser(String username, String comment);

    /**
     * Tries to log the user in.
     * 
     * @param username
     *            username of the user
     * @param password
     *            password, encoded
     * @param loginType
     *            admin or user login
     * @return the whole user object, without password!!!
     * @throws SecurityException 
     * @throws WrongPasswordException 
     * @throws NoSuchUserException 
     */
    User login(String username, String password, UserRole loginType) throws SecurityException, WrongPasswordException, NoSuchUserException;

    /**
     * Creates new site user
     * 
     * @param username
     *            username of the user
     * @param password
     *            desired password
     * @return the newly created user
     * @throws UserExistsException 
     * @throws UserCreationException 
     */
    User createNewUser(String username, String password) throws UserExistsException, UserCreationException;

    /**
     * Confirms an user.
     * 
     * @param id
     *            id of the user
     * @return the user itself
     */
    User confirmUser(String id);

    /**
     * @param username
     *            username of the user
     * @return the unencoded password changed
     */
    String changePasswordOfUser(String username);

    /**
     * Check if the this user exists, and if it is admin
     * 
     * @param username
     *            username of the user
     * @param pass
     *            password of the user
     * 
     * @return true, if the user is admin
     */
    boolean isAdmin(String username);

    /**
     * Check if the this user exists, and if it is a manager
     * 
     * @param username
     *            username of the user
     * @param pass
     *            password of the user
     * 
     * @return true, if the user is a manager
     */
    boolean isManager(String username);

    /**
     * Check if the this user exists
     * 
     * @param username
     *            username of the user
     * @param pass
     *            password of the user
     * 
     * @return true, if the user is a manager
     */
    boolean isUser(String username);

    /**
     * @param username
     *            username of the user
     * @param password
     *            password of the user
     * @return the user herself, or null 
     */
    User getUser(String username, String password);
    
    /**
     * @param user
     *            a user object that is allowed to have null fields
     *            and only the non-null fields are updated
     * @return the user herself, or null 
     */
    User updateUserInfo(User user);

	User loginWithCookie(String username, String passwordHash,
			UserRole loginType) throws WrongPasswordException, SecurityException, NoSuchUserException;

	List<User> getAllUsers();
}
