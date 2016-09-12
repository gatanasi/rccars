package com.exercise.rccars.model.rcu;

import com.exercise.rccars.exceptions.CoordinatesOutOfGridBoundsException;
import com.exercise.rccars.model.Grid;
import com.exercise.rccars.model.Orientation;
import com.exercise.rccars.model.Position;
import com.exercise.rccars.model.Turn;

public class Car implements RemoteControlledUnit {

	private Position position;
	private Orientation direction;
	private Grid grid;

	@Override
	public Orientation getDirection() {
		return direction;
	}

	@Override
	public void setInitialPosition(final Position position) {
		this.position = position;
	}

	@Override
	public void placeInGrid(final Grid grid) throws CoordinatesOutOfGridBoundsException {
		this.direction = Orientation.NORTH;
		this.grid = grid;
		grid.addRemoteControlledUnit(this);
	}

	@Override
	public Position getPosition() {
		return this.position;
	}

	@Override
	public void turn(final Turn turn) {
		this.direction = direction.turnTo(turn);
	}

	@Override
	public void move() {
		final Position newPosition = position.addPosition(direction.getUnitVector());
		if (grid.isInsideGrid(newPosition)) {
			this.position = newPosition;
		}
	}

}
