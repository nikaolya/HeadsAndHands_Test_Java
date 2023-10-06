package game;

public enum CreatureType {
	MONSTER("Monster"),
	PLAYER("Player");

	private String type;

	CreatureType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return type;
	}
}
