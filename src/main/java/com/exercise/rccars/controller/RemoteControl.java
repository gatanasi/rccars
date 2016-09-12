package com.exercise.rccars.controller;

import java.util.ArrayList;
import java.util.List;

import com.exercise.rccars.command.Command;
import com.exercise.rccars.model.rcu.RemoteControlledUnit;

/**
 * Class that accumulates the commands to be transmitted to the set
 * {@code RemoteControlledUnit}
 */
public class RemoteControl {

	private List<Command> commandList;
	private RemoteControlledUnit rcUnit;

	public RemoteControl() {
		this.commandList = new ArrayList<Command>();
	}

	/**
	 * Adds a command to the list of commands that will be executed
	 * 
	 * @param command
	 *            {@link Command} to be added
	 */
	public void addCommand(final Command command) {
		this.commandList.add(command);
	}

	/**
	 * Executes sequentially the stored commands and empties the command list
	 */
	public void transmitCommands() {
		for (final Command command : commandList) {
			command.execute(this.rcUnit);
		}
		this.commandList = new ArrayList<Command>();
	}

	/**
	 * Sets the {@code RemoteControlledUnit} where the added commands will be
	 * transmitted
	 * 
	 * @param rcUnit
	 *            {@link RemoteControlledUnit} to assign
	 */
	public void setRemoteUnit(final RemoteControlledUnit rcUnit) {
		this.rcUnit = rcUnit;
	}
}
