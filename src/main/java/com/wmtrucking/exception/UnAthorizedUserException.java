package com.wmtrucking.exception;

public class UnAthorizedUserException extends Exception {

	private static final long serialVersionUID = -3332292346834265371L;

	public UnAthorizedUserException(String msg) {
		super(msg);
	}
}
