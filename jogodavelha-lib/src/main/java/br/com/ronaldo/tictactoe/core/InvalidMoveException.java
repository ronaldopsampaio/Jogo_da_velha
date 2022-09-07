package br.com.ronaldo.tictactoe.core;

// Ou apertar Ctrl + 1.

@SuppressWarnings ("serial")
public class InvalidMoveException extends Exception {

	
	public InvalidMoveException(String message) {
		super(message);
		
	}

}
