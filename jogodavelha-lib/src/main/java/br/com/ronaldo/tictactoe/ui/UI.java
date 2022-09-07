package br.com.ronaldo.tictactoe.ui;

import br.com.softblue.commons.io.Console;

public class UI {
	
	public static void printText (String text) {
		System.out.println(text);
	}

	public static void printTextWithNoNewLine (String text) {
		System.out.print(text);
	}
	
	public static void printNewLine() {
		System.out.println();
	}

	public static void printGameTitle() {
		printText ("=================");
		printText ("| JOGO DA VELHA |");
		printText ("=================");
		printNewLine();
	}
	
	public static void printInstructions() {
		printText (" Como funciona o jogo: \n Cada jogador deverá inserir uma coordenada ( X,X ) com os intervalos de 0 até 2,\n sendo o primeiro número correspondente à linha do tabuleiro e o segundo número à coluna.");
		printNewLine();
	}
	
	public static String readInput (String text) {
		printTextWithNoNewLine (text + " ");
		return Console.readString();
	}
}
