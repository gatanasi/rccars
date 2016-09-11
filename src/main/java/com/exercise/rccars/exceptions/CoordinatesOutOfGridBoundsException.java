package com.exercise.rccars.exceptions;

public class CoordinatesOutOfGridBoundsException extends Exception {

	private static final long serialVersionUID = 6739082345610357255L;

	public CoordinatesOutOfGridBoundsException(final String message) {
		super(message);
	}
}
