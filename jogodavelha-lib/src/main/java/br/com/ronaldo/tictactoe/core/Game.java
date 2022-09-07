package br.com.ronaldo.tictactoe.core;

import java.io.IOException;

import br.com.ronaldo.tictactoe.Constants;
import br.com.ronaldo.tictactoe.score.FileScoreManager;
import br.com.ronaldo.tictactoe.score.ScoreManager;
import br.com.ronaldo.tictactoe.ui.UI;

public class Game {

	private Board board = new Board();
	private Player[] players = new Player[Constants.SYMBOL_PLAYERS.length];
	private int currentPlayerIndex = -1;
	private ScoreManager scoreManager;

	public void play() throws IOException {
		
		scoreManager = createScoreManager();
		UI.printGameTitle();
		UI.printInstructions();

		for (int i = 0; i < players.length; i++) {
			players[i] = createPlayer(i);
		}

		boolean gameEnded = false;
		Player currentPlayer = nextPlayer();
		Player winner = null;

		while (!gameEnded) {
			board.print();

			boolean sequenceFound;
			
			try {
			 sequenceFound = currentPlayer.play();
			} catch (InvalidMoveException e) {
				UI.printText("ERRO: " + e.getMessage());
				continue;
			}
			
			if (sequenceFound) {
				gameEnded = true;
				winner = currentPlayer;

			} else if (board.isFull()) {
				gameEnded = true;
			} else {
			currentPlayer = nextPlayer();
			}
		}

		if (winner == null) {
			UI.printText("O jogo terminou empatado!");
		
		} else {
			UI.printText("O jogador " + winner.getName() + " venceu o jogo!");
			
			scoreManager.saveScore(winner);
		}
		
		board.print();
		UI.printText("Fim do jogo!");
	}

	private Player createPlayer(int index) {
		String name = UI.readInput("Digite o nome do Jogador " + (index + 1) + " => ");
		char symbol = Constants.SYMBOL_PLAYERS[index];
		Player player = new Player(name, board, symbol);

		Integer score = scoreManager.getScore(player);
		
		if (score != null) {
			UI.printText("O Jogador '" + player.getName()+ "' já possui " + score + " vitória(s)!");
		}
		UI.printText("O jogador " + name + " vai usar o símbolo '" + symbol + "'");

		return player;
	}

	private Player nextPlayer() {
		/*
		 * currentPlayerIndex++; if (currentPlayerIndex >= players.length) {
		 * currentPlayerIndex = 0; } return
		 */

		currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
		return players[currentPlayerIndex];
	}
	
	private ScoreManager createScoreManager() throws IOException {
		return new FileScoreManager(); 
		
	}
}