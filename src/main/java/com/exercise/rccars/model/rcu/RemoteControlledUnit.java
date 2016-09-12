package com.exercise.rccars.model.rcu;

import com.exercise.rccars.exceptions.CoordinatesOutOfGridBoundsException;
import com.exercise.rccars.model.Grid;
import com.exercise.rccars.model.Orientation;
import com.exercise.rccars.model.Position;
import com.exercise.rccars.model.Turn;

/**
 * This interface defines an abstraction for remote controlled objects
 */
public interface RemoteControlledUnit {

	/**
	 * Turns the {@code RemoteControlledUnit} to the given side
	 * 
	 * @param turn
	 *            Side to turn
	 */
	public void turn(Turn turn);

	/**
	 * Moves the {@code RemoteControlledUnit} in the direction where it is
	 * pointing
	 */
	public void move();

	public Position getPosition();

	public Orientation getDirection();

	/**
	 * Sets the initial position of the {@code RemoteControlledUnit}
	 * 
	 * @param position
	 *            Position to be set
	 */
	public void setInitialPosition(Position position);

	/**
	 * Places the {@code RemoteControlledUnit} in the given {@code Grid} at the
	 * set initial position
	 * 
	 * @param grid
	 *            {@link Grid} where the {@code RemoteControlledUnit} should be
	 *            placed
	 * @throws CoordinatesOutOfGridBoundsException
	 *             if the initial position for the {@code RemoteControlledUnit}
	 *             is beyond grid boundaries
	 */
	public void placeInGrid(Grid grid) throws CoordinatesOutOfGridBoundsException;
}
