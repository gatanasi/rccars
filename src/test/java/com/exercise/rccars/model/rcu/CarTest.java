package com.exercise.rccars.model.rcu;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.exercise.rccars.command.Command;
import com.exercise.rccars.command.MoveCommand;
import com.exercise.rccars.command.TurnCommand;
import com.exercise.rccars.controller.RemoteControl;
import com.exercise.rccars.exceptions.CoordinatesOutOfGridBoundsException;
import com.exercise.rccars.model.Grid;
import com.exercise.rccars.model.Orientation;
import com.exercise.rccars.model.Position;
import com.exercise.rccars.model.Turn;

/**
 * Unit test for Car
 */
public class CarTest {

	@Test
	public void givenACarAndCoordinatesThenTheCarIsPositionedInTheGrid() throws CoordinatesOutOfGridBoundsException {
		Position carPosition = new Position(5, 5);
		RemoteControlledUnit newCar = new Car();
		newCar.setInitialPosition(carPosition);
		Grid grid = new Grid();
		newCar.placeInGrid(grid);

		assertEquals(carPosition, grid.getCars().stream().findFirst().get().getPosition());
	}

	@Test(expected = CoordinatesOutOfGridBoundsException.class)
	public void givenACarAndCoordinatesOutsideTheGridThenAnExceptionArises()
			throws CoordinatesOutOfGridBoundsException {
		Position carPosition = new Position(3, 3);
		RemoteControlledUnit newCar = new Car();
		newCar.setInitialPosition(carPosition);
		Grid grid = new Grid(new Position(2, 2));
		newCar.placeInGrid(grid);
	}

	@Test
	public void whenANewCarIsPlacedThenTheCarIsHeadingNorth() throws CoordinatesOutOfGridBoundsException {
		Position carPosition = new Position(6, 6);
		RemoteControlledUnit newCar = new Car();
		newCar.setInitialPosition(carPosition);
		Grid grid = new Grid();
		newCar.placeInGrid(grid);

		assertEquals(Orientation.NORTH, grid.getCars().stream().findFirst().get().getDirection());
	}

	@Test
	public void givenACarInTheGridWhenATurnLeftOrRightCommandIsSentThenTheCarDirectionChanges()
			throws CoordinatesOutOfGridBoundsException {
		Position carPosition = new Position(8, 4);
		RemoteControlledUnit newCar = new Car();
		newCar.setInitialPosition(carPosition);
		Grid grid = new Grid();
		newCar.placeInGrid(grid);

		RemoteControl remoteControl = new RemoteControl();
		remoteControl.setRemoteUnit(newCar);

		Command command = new TurnCommand(Turn.LEFT);

		remoteControl.addCommand(command);
		remoteControl.transmitCommands();

		assertEquals(Orientation.WEST, grid.getCars().stream().findFirst().get().getDirection());

		Command command2 = new TurnCommand(Turn.RIGHT);

		remoteControl.addCommand(command2);

		remoteControl.transmitCommands();

		assertEquals(Orientation.NORTH, grid.getCars().stream().findFirst().get().getDirection());
	}

	@Test
	public void givenACarInTheGridWhenAMoveForwardCommandIsSentThenTheCarPositionChanges()
			throws CoordinatesOutOfGridBoundsException {
		Position carPosition = new Position(2, 2);
		RemoteControlledUnit newCar = new Car();
		newCar.setInitialPosition(carPosition);
		Grid grid = new Grid();
		newCar.placeInGrid(grid);

		RemoteControl remoteControl = new RemoteControl();
		remoteControl.setRemoteUnit(newCar);

		Command command = new MoveCommand();

		remoteControl.addCommand(command);
		remoteControl.transmitCommands();

		assertEquals(new Position(2, 3), grid.getCars().stream().findFirst().get().getPosition());
	}

	@Test
	public void givenACarInTheGridWhenSeveralCommandsAreSentThenTheyAreExecutedInOrder()
			throws CoordinatesOutOfGridBoundsException {
		Position carPosition = new Position(2, 2);
		RemoteControlledUnit newCar = new Car();
		newCar.setInitialPosition(carPosition);
		Grid grid = new Grid();
		newCar.placeInGrid(grid);

		RemoteControl remoteControl = new RemoteControl();
		remoteControl.setRemoteUnit(newCar);

		Command command = new MoveCommand();
		Command command2 = new TurnCommand(Turn.LEFT);

		remoteControl.addCommand(command);
		remoteControl.addCommand(command2);
		remoteControl.addCommand(command);
		remoteControl.transmitCommands();

		assertEquals(new Position(1, 3), grid.getCars().stream().findFirst().get().getPosition());
		assertEquals(Orientation.WEST, grid.getCars().stream().findFirst().get().getDirection());
	}

	@Test
	public void givenACarInTheGridWhenAForwardCommandAtTheEdgeOfTheGridIsSentThenTheCarStaysStill()
			throws CoordinatesOutOfGridBoundsException {
		Position carPosition = new Position(1, 1);
		RemoteControlledUnit newCar = new Car();
		newCar.setInitialPosition(carPosition);
		Grid grid = new Grid();
		newCar.placeInGrid(grid);

		RemoteControl remoteControl = new RemoteControl();
		remoteControl.setRemoteUnit(newCar);

		Command command = new MoveCommand();
		Command command2 = new TurnCommand(Turn.LEFT);

		remoteControl.addCommand(command2);
		remoteControl.addCommand(command);
		remoteControl.addCommand(command);
		remoteControl.addCommand(command);
		remoteControl.transmitCommands();

		assertEquals(new Position(1, 1), grid.getCars().stream().findFirst().get().getPosition());
	}
}
