package com.exercise.rccars.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exercise.rccars.command.Command;
import com.exercise.rccars.command.CommandFactory;
import com.exercise.rccars.exceptions.UnknownCommandException;
import com.exercise.rccars.model.Position;
import com.exercise.rccars.model.rcu.Car;
import com.exercise.rccars.model.rcu.RemoteControlledUnit;

/**
 * This class contains the parsing methods for the data input
 * 
 * @author gatan
 *
 */
public class InputParser {

	protected Position parsePosition(final String positionString) throws IllegalArgumentException {
		final String[] coordinates = positionString.split("[\\s,]");
		if (coordinates.length != 2) {
			throw new IllegalArgumentException("Invalid position definition");
		}

		int posX = 0;
		int posY = 0;

		try {
			posX = Integer.parseInt(coordinates[0]);
			posY = Integer.parseInt(coordinates[1]);
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentException("Position coordinates must be numbers");
		}
		return new Position(posX, posY);
	}

	protected List<Command> parseCommands(final String commandsString) throws UnknownCommandException {
		final List<Command> commandsList = new ArrayList<Command>();

		for (String commandString : commandsString.split("")) {
			commandsList.add(CommandFactory.getCommand(commandString));
		}

		return commandsList;
	}

	public Map<RemoteControlledUnit, List<Command>> parseInput(final String input)
			throws UnknownCommandException, IllegalArgumentException {
		final Map<RemoteControlledUnit, List<Command>> parsedInput = new HashMap<RemoteControlledUnit, List<Command>>();
		final String[] inputParts = input.split(":");
		if (inputParts.length != 2) {
			throw new IllegalArgumentException("Invalid input definition");
		}
		Position parsedPosition = parsePosition(inputParts[0]);
		List<Command> parsedCommands = parseCommands(inputParts[1]);

		RemoteControlledUnit newCar = new Car();
		newCar.setInitialPosition(parsedPosition);

		parsedInput.put(newCar, parsedCommands);

		return parsedInput;
	}

}
