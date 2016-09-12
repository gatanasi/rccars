package com.exercise.rccars.command;

import com.exercise.rccars.model.rcu.RemoteControlledUnit;

/**
 * Class that represents a command to be executed on the given {@link RemoteControlledUnit}
 */
public interface Command {

	/**
	 * Executes the command on the given {@link RemoteControlledUnit}
	 * 
	 * @param rcUnit
	 *            RemoteControlledUnit where the command will be executed
	 */
	public void execute(RemoteControlledUnit rcUnit);
}
