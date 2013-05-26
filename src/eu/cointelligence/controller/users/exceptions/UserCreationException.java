package eu.cointelligence.controller.users.exceptions;

public class UserCreationException extends Exception {

	public UserCreationException(Throwable e) {
		addSuppressed(e);
	}

}
