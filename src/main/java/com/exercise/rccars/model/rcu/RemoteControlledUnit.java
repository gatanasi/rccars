package com.exercise.rccars.model.rcu;

import com.exercise.rccars.exceptions.CoordinatesOutOfGridBoundsException;
import com.exercise.rccars.model.Grid;
import com.exercise.rccars.model.Orientation;
import com.exercise.rccars.model.Position;
import com.exercise.rccars.model.Turn;

public interface RemoteControlledUnit {

	public void turn(Turn turn);

	public void move();

	public Position getPosition();

	public Orientation getDirection();

	public void setInitialPosition(Position position);

	public void placeInGrid(Grid grid) throws CoordinatesOutOfGridBoundsException;
}
