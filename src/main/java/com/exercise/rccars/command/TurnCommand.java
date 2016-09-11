package com.exercise.rccars.command;

import com.exercise.rccars.model.Turn;
import com.exercise.rccars.model.rcu.RemoteControlledUnit;

public class TurnCommand implements Command {

	private Turn turn;

	public TurnCommand(final Turn turn) {
		this.turn = turn;
	}

	@Override
	public void execute(final RemoteControlledUnit rcUnit) {
		rcUnit.turn(turn);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((turn == null) ? 0 : turn.hashCode());
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
		TurnCommand other = (TurnCommand) obj;
		if (turn != other.turn)
			return false;
		return true;
	}

}
