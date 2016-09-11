package com.exercise.rccars.command;

import com.exercise.rccars.model.rcu.RemoteControlledUnit;

public class MoveCommand implements Command {

	@Override
	public void execute(final RemoteControlledUnit car) {
		car.move();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + super.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
}
