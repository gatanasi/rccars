package com.exercise.rccars.model;

public enum Orientation {
	NORTH(0), EAST(1), SOUTH(2), WEST(3);

	private final int p;

	private Orientation(final int p) {
		this.p = p;
	}

	private static Orientation[] vals = new Orientation[4];
	static {
		for (Orientation o : Orientation.values())
			vals[o.p] = o;
	}

	public Orientation turnTo(final Turn t) {
		return vals[(4 + this.p + (t == Turn.RIGHT ? 1 : -1)) % 4];
	}

	public Position getUnitVector() {
		Position vector = new Position(0, 0);

		switch (this) {
		case NORTH:
			vector = new Position(0, 1);
			break;
		case EAST:
			vector = new Position(1, 0);
			break;
		case SOUTH:
			vector = new Position(0, -1);
			break;
		case WEST:
			vector = new Position(-1, 0);
			break;
		}

		return vector;
	}
}
