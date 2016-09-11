package com.exercise.rccars;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.exercise.rccars.command.Command;
import com.exercise.rccars.controller.RemoteControl;
import com.exercise.rccars.exceptions.CoordinatesOutOfGridBoundsException;
import com.exercise.rccars.exceptions.UnknownCommandException;
import com.exercise.rccars.model.Grid;
import com.exercise.rccars.model.rcu.RemoteControlledUnit;
import com.exercise.rccars.utils.InputParser;

public class App {
	public static void main(String[] args) {
		System.out
				.println("Please enter the Car initial coordinates and the commands to be executed (e.g. 4,7:FFLFRF)");

		final Scanner in = new Scanner(System.in);
		final String nextLine = in.nextLine();

		final InputParser inputParser = new InputParser();
		Map<RemoteControlledUnit, List<Command>> parsedInput;
		try {
			parsedInput = inputParser.parseInput(nextLine);

			final Grid grid = new Grid();

			for (final Entry<RemoteControlledUnit, List<Command>> rcu : parsedInput.entrySet()) {
				final RemoteControlledUnit controlledUnit = rcu.getKey();

				controlledUnit.placeInGrid(grid);
				final RemoteControl remoteControl = new RemoteControl();
				remoteControl.setRemoteUnit(controlledUnit);

				final List<Command> commands = rcu.getValue();
				commands.forEach(com -> remoteControl.addCommand(com));

				remoteControl.transmitCommands();

				System.out.println(controlledUnit.getPosition());
			}
		} catch (UnknownCommandException e) {
			System.err.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		} catch (CoordinatesOutOfGridBoundsException e) {
			System.err.println(e.getMessage());
		} finally {
			in.close();
		}
	}
}
