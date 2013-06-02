package eu.cointelligence.controller.entity;

public enum Gender {
	MALE("MALE"),
	FEMALE("FEMALE"),
	OTHER("OTHER");
	
	private final String value;

	Gender(String str) {
		this.value = str;
	}

	public static Gender fromValue(String value) {
		if (value != null) {
			for (Gender role : values()) {
				if (role.value.equals(value)) {
					return role;
				}
			}
		}
		return OTHER;
	}

	public String toValue() {
		return value;
	}

}