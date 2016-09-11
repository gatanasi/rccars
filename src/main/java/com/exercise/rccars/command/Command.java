package com.exercise.rccars.command;

import com.exercise.rccars.model.rcu.RemoteControlledUnit;

public interface Command {

	public void execute(RemoteControlledUnit car);
}
