package com.exercise.rccars.model;

import java.util.ArrayList;
import java.util.List;

import com.exercise.rccars.exceptions.CoordinatesOutOfGridBoundsException;
import com.exercise.rccars.model.rcu.RemoteControlledUnit;
import com.exercise.rccars.utils.Constants;

/**
 * Class that represents the grid where the {@code RemoteControlledUnits} live
 */
public class Grid {

	private Position size;
	private List<RemoteControlledUnit> rcUnitsList;

	/**
	 * Creates a new Grid with the default size defined in
	 * {@link Constants#GRID_DEFAULT_SIZE}
	 */
	public Grid() {
		this.size = Constants.GRID_DEFAULT_SIZE;
		this.rcUnitsList = new ArrayList<RemoteControlledUnit>();
	}

	/**
	 * Creates a new grid with the given size
	 * 
	 * @param size
	 *            Size of the created grid
	 */
	public Grid(final Position size) {
		this.size = size;
		this.rcUnitsList = new ArrayList<RemoteControlledUnit>();
	}

	protected Position getSize() {
		return size;
	}

	public List<RemoteControlledUnit> getCars() {
		return rcUnitsList;
	}

	/**
	 * Adds a {@code RemoteControlledUnit} to the list
	 * 
	 * @param rcUnit
	 *            {@code RemoteControlledUnit} to be added
	 * @throws CoordinatesOutOfGridBoundsException
	 *             if the given {@code RemoteControlledUnit} initial position is
	 *             beyond grid boundaries
	 */
	public void addRemoteControlledUnit(final RemoteControlledUnit rcUnit) throws CoordinatesOutOfGridBoundsException {
		if (isInsideGrid(rcUnit.getPosition())) {
			rcUnitsList.add(rcUnit);
		} else {
			throw new CoordinatesOutOfGridBoundsException("Given coordinates exceed Grid boundaries");
		}
	}

	/**
	 * Returns if the given position is inside the grid
	 * 
	 * @param position
	 *            Position to check
	 * @return {@code true} if the given position is within the grid's
	 *         boundaries, otherwise {@code false}
	 */
	public boolean isInsideGrid(final Position position) {
		return position.isWithinBounds(size);
	}
}
