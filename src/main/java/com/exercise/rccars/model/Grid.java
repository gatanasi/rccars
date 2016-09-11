package com.exercise.rccars.model;

import java.util.ArrayList;
import java.util.List;

import com.exercise.rccars.exceptions.CoordinatesOutOfGridBoundsException;
import com.exercise.rccars.model.rcu.RemoteControlledUnit;
import com.exercise.rccars.utils.Constants;

public class Grid {

	private Position size;
	private List<RemoteControlledUnit> rcUnitsList;

	public Grid() {
		this.size = Constants.GRID_DEFAULT_SIZE;
		this.rcUnitsList = new ArrayList<RemoteControlledUnit>();
	}

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

	public void addRemoteControlledUnit(final RemoteControlledUnit rcUnit) throws CoordinatesOutOfGridBoundsException {
		if (isInsideGrid(rcUnit.getPosition())) {
			rcUnitsList.add(rcUnit);
		} else {
			throw new CoordinatesOutOfGridBoundsException("Given coordinates exceed Grid boundaries");
		}
	}

	public boolean isInsideGrid(final Position position) {
		return position.isWithinBounds(size);
	}
}
