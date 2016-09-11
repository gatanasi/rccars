package com.exercise.rccars.utils;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.exercise.rccars.command.Command;
import com.exercise.rccars.command.MoveCommand;
import com.exercise.rccars.command.TurnCommand;
import com.exercise.rccars.exceptions.UnknownCommandException;
import com.exercise.rccars.model.Position;
import com.exercise.rccars.model.Turn;
import com.exercise.rccars.model.rcu.RemoteControlledUnit;

/**
 * Unit test for InputParser
 */
public class InputParserTest {

	@Test
	public void givenAPositionStringInputWhenTheInputParserIsInvokedThenAPositionWithGivenCoordinatesIsCreated() {
		InputParser parser = new InputParser();
		Position parsedPosition = parser.parsePosition("5,5");

		assertEquals(new Position(5, 5), parsedPosition);
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenAPositionStringInputWithMoreIntsWhenTheInputParserIsInvokedThenAIllegalArgumentExceptionIsThrown() {
		InputParser parser = new InputParser();
		parser.parsePosition("5,5,3");
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenAnIncompletePositionStringInputWhenTheInputParserIsInvokedThenAIllegalArgumentExceptionIsThrown() {
		InputParser parser = new InputParser();
		parser.parsePosition("5");
	}

	@Test(expected = IllegalArgumentException.class)
	public void givenAnInvalidPositionDefinitionInputStringWhenTheInputParserIsInvokedThenAIllegalArgumentExceptionIsThrown() {
		InputParser parser = new InputParser();
		parser.parsePosition("E,2");
	}

	@Test
	public void givenACommandsStringInputWhenTheInputParserIsInvokedThenACommandListWithGivenCommandsIsCreated()
			throws UnknownCommandException {
		InputParser parser = new InputParser();
		List<Command> parsedCommands = parser.parseCommands("RFL");

		assertEquals(new TurnCommand(Turn.RIGHT), parsedCommands.get(0));
		assertEquals(new MoveCommand(), parsedCommands.get(1));
		assertEquals(new TurnCommand(Turn.LEFT), parsedCommands.get(2));
	}

	@Test(expected = UnknownCommandException.class)
	public void givenAnUnknownCommandStringInputWhenTheInputParserIsInvokedThenAnUnknownCommandExceptionArises()
			throws UnknownCommandException {
		InputParser parser = new InputParser();
		parser.parseCommands("RZL");
	}

	@Test
	public void givenACompleteStringInputWhenTheInputParserIsInvokedThenACarIsPlacedInTheGridAndTheGivenCommandsAreExecuted()
			throws UnknownCommandException {
		InputParser parser = new InputParser();
		Map<RemoteControlledUnit, List<Command>> parsedInput = parser.parseInput("5,5:RFLFRFLF");

		Entry<RemoteControlledUnit, List<Command>> entry = parsedInput.entrySet().stream().findFirst().get();

		assertEquals(new Position(5, 5), entry.getKey().getPosition());
		assertEquals(new TurnCommand(Turn.RIGHT), entry.getValue().get(0));
		assertEquals(new MoveCommand(), entry.getValue().get(1));
		assertEquals(new TurnCommand(Turn.LEFT), entry.getValue().get(2));
	}
}
