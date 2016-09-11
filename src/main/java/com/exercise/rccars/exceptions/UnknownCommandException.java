package com.exercise.rccars.exceptions;

public class UnknownCommandException extends Exception {

	private static final long serialVersionUID = -5247324918475232342L;

	public UnknownCommandException(final String message) {
		super(message);
	}
}
