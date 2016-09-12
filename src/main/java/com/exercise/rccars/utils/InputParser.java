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
 */
public class InputParser {

	/**
	 * Parses the given {@code String} extracting the {@link Position} data
	 * 
	 * @param positionString
	 *            String containing the {@code Position} data. Expected format:
	 *            X,Y
	 * @return created {@code Position} with the given data
	 * @throws IllegalArgumentException
	 *             if the format of the given {@code String} is not as expected
	 */
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

	/**
	 * Parses the given {@code String} extracting the {@link Commands} data
	 * 
	 * @param commandsString
	 *            String containing a list of {@code Command}. Expected format:
	 *            ABCD
	 * @return created {@code Command} list with the given data
	 * @throws UnknownCommandException
	 *             if any of the given commands was not recognized
	 */
	protected List<Command> parseCommands(final String commandsString) throws UnknownCommandException {
		final List<Command> commandsList = new ArrayList<Command>();

		for (final String commandString : commandsString.split("")) {
			commandsList.add(CommandFactory.getCommand(commandString));
		}

		return commandsList;
	}

	/**
	 * Parses the given {@code String} extracting the {@link Position} and
	 * {@link Commands} data to generate a map with a newly created
	 * {@link RemoteControlledUnit}
	 * 
	 * @param input
	 *            String containing a list of {@code Command}. Expected format:
	 *            X,Y:Commands
	 * @return a {@code Map} with the keys of the newly created
	 *         {@code RemoteControlledUnit} with given initial position and the
	 *         {@code List} of assigned {@code Commands} as value
	 * @throws UnknownCommandException
	 *             if any of the given commands was not recognized
	 * @throws IllegalArgumentException
	 *             if the format of the given {@code String} is not as expected
	 */
	public Map<RemoteControlledUnit, List<Command>> parseInput(final String input)
			throws UnknownCommandException, IllegalArgumentException {
		final Map<RemoteControlledUnit, List<Command>> parsedInput = new HashMap<RemoteControlledUnit, List<Command>>();
		final String[] inputParts = input.split(":");
		if (inputParts.length != 2) {
			throw new IllegalArgumentException("Invalid input definition");
		}
		final Position parsedPosition = parsePosition(inputParts[0]);
		final List<Command> parsedCommands = parseCommands(inputParts[1]);

		final RemoteControlledUnit newCar = new Car();
		newCar.setInitialPosition(parsedPosition);

		parsedInput.put(newCar, parsedCommands);

		return parsedInput;
	}

}
