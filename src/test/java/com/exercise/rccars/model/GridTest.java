package com.exercise.rccars.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.exercise.rccars.utils.Constants;

/**
 * Unit test for Grid
 */
public class GridTest {

	@Test
	public void givenNoSizeThenIGetANewGridWithDefaultSize() {
		Grid newGrid = new Grid();
		assertNotNull(newGrid);
		assertEquals(Constants.GRID_DEFAULT_SIZE, newGrid.getSize());
	}

	@Test
	public void givenASizeThenIGetANewGrid() {
		Position size = new Position(7, 7);
		Grid newGrid = new Grid(size);
		assertNotNull(newGrid);
		assertEquals(size, newGrid.getSize());
	}

}
