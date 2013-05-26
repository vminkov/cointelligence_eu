package eu.cointelligence.controller.users;

public enum UserRole {
	USER(0), MANAGER(1), ADMIN(2);

	private int value;

	UserRole(int value) {
		this.value = value;
	}

	// the identifierMethod
	public int toInt() {
		return value;
	}

	public static UserRole fromInt(int value) {
		switch (value) {
		case 0:
			return USER;
		case 1:
			return MANAGER;
		case 2:
			return ADMIN;
		default:
			return USER;
		}
	}
}
