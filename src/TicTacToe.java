import java.util.Scanner;

public class TicTacToe {
	static char[][] board = new char[3][3];
	private static char currentPlayer = ' ';
	
	private static void reset(char[][] board) {
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = ' ';
			}
	}

	public void makeBoard() {
		System.out.println("-------------");
		for (int i = 0; i < board.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	private static char checkRow(char[][] board) {
		char winner = ' ';
		for (int i = 0; i < board.length; i++) {
			if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				winner = board[i][0];
			}
		}
		return winner;
	}

	private static char checkCol(char[][] board) {
		char winner = ' ';
		for (int i = 0; i < board.length; i++) {
			if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
				winner = board[i][0];
			}
		}
		return winner;
	}

	private static char checkDia(char[][] board) {
		char winner = ' ';
		if (board[0][0] != ' ' && board[1][1] != ' ' && board[2][2] != ' ') {
				if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
					winner = board[0][0];
				}
		} else if (board[0][2] != ' ' && board[1][1] != ' ' && board[2][0] != ' ') {
				if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
					winner = board[0][2];
				}
		}
		return winner;
	}

	public char getPlayer() {
		return currentPlayer;
	}

	public char nextPlayer() {
		if (currentPlayer == 'x') {
			return currentPlayer = 'o';
		}
		return currentPlayer = 'x';
	}

	public boolean nextMove(int row, int col) {
		if ((row >= 0) && (col < 3) && (row < 3) && (col >= 0)) {
			if (board[row][col] == ' ') {
				board[row][col] = currentPlayer;
				return true;
			}
		}
		return false;
	}

	private static char getWinner() {
		if (checkRow(board) == 'x' || checkCol(board) == 'x' || checkDia(board) == 'x') {
			return 'x';
		} else if (checkRow(board) == 'x' || checkCol(board) == 'x' || checkDia(board) == 'o') {
			return 'o';
		}
		return ' ';
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		TicTacToe game = new TicTacToe();
		TicTacToe.reset(board);
		System.out.println("Tic-Tac-Toe!");
		int count = 0;
		do {
			System.out.println("Current board layout:");
			game.makeBoard();
			int row;
			int col;
			do {
				game.nextPlayer();
				System.out.println("Player " + game.getPlayer() + ", enter an empty row and column to place your mark!");
				row = scan.nextInt() - 1;
				col = scan.nextInt() - 1;
				count++;
			} while (!game.nextMove(row, col));
		} while (TicTacToe.getWinner() == ' ' && count != 9);
		if (TicTacToe.getWinner() == ' ' && count == 9) {
			System.out.println("The game was a tie!");
			TicTacToe.reset(board);
		} else {
			System.out.println("Current board layout:");
			game.makeBoard();
			System.out.println(Character.toUpperCase(game.getPlayer()) + " Wins!");
		}
	}
}