package com.exercise.rccars.model;

public class Position {

	private int x;
	private int y;

	public Position(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[" + x + "," + y + "]";
	}

	public boolean isWithinBounds(final Position bounds) {
		return ((this.x >= 1) && (this.x <= bounds.x) && (this.y >= 1) && (this.y <= bounds.y));
	}

	public Position addPosition(final Position position) {
		return new Position(this.x + position.x, this.y + position.y);
	}

}
