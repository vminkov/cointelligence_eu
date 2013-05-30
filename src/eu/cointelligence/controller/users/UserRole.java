package eu.cointelligence.controller.users;

public enum UserRole {
	USER("USER"), MANAGER("MANAGER"), ADMIN("ADMIN");

	private final String value;

	UserRole(String str) {
		this.value = str;
	}

	public static UserRole fromValue(String value) {
		if (value != null) {
			for (UserRole role : values()) {
				if (role.value.equals(value)) {
					return role;
				}
			}
		}

		throw new IllegalArgumentException("Invalid UserRole: " + value);
	}

	public String toValue() {
		return value;
	}

}
