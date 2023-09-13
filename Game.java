/* Christopher Lopez
 * Dr. Steinberg
 * COP3503 Fall 2023
 * Programming Assignment 1
A Game That Requires Strategy
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Game {

    private int[][] board_arr;
    private char[] moves_arr;
    private int currentPlayer;
    int knightRow = 0;
    int knightCol = 0;
    int moveCtr = 0;

    public Game(int boardSize, String player2File) {
        try {
            this.board_arr = new int[boardSize][boardSize];
            readMoves(player2File);
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentPlayer = 1;
    }

    private void readMoves(String player2File) throws IOException {
        String moves = new String(Files.readAllBytes(Paths.get(player2File)));
        moves_arr = moves.toCharArray();
    }

    public void player1Turn() {
        System.out.println("Player 1, enter your move (r, b, or d): ");
        Scanner sc = new Scanner(System.in);
        char userInput = sc.next().charAt(0);

        switch (userInput) {
            case 'r':
                if (knightCol < board_arr[0].length - 1) {
                    knightCol++;
                    System.out.println("Player 1 moved to the right.");
                }
                break;
            case 'b':
                if (knightRow < board_arr.length - 1) {
                    knightRow++;
                    System.out.println("Player 1 moved down.");
                }
                break;
            case 'd':
                if (knightCol < board_arr[0].length - 1 && knightRow < board_arr.length - 1) {
                    knightCol++;
                    knightRow++;
                    System.out.println("Player 1 moved diagonally.");
                }
                break;
            default:
                System.out.println("Invalid move. Try again.");
                break;
        } 
        if (checkWin(knightRow, knightCol, board_arr.length)) {
            System.out.println("Player 1 wins!");
        }
        currentPlayer = 2; // Switch to player 2's turn
    }

    public void player2Turn() {
        move = moves_arr[moveCtr];
        switch (move) {
            case 'r':
                if (knightCol < board_arr[0].length - 1) {
                    knightCol++;
                    System.out.println("Player 2 moved to the right.");
                }
                break;
            case 'b':
                if (knightRow < board_arr.length - 1) {
                    knightRow++;
                    System.out.println("Player 2 moved down.");
                }
                break;
            case 'd':
                if (knightCol < board_arr[0].length - 1 && knightRow < board_arr.length - 1) {
                    knightCol++;
                    knightRow++;
                    System.out.println("Player 2 moved diagonally.");
                }
                break;
        }
        if (checkWin(knightRow, knightCol, board_arr.length)) {
            System.out.println("Player 2 wins!");
            return;
        }
        currentPlayer = 1; // Switch to player 1's turn
        moveCtr++;
    }

    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String player2File = "";

    while (true) {
        System.out.print("Enter the exact file name for player 2's moves: ");
        player2File = sc.nextLine();

        // Check if the file exists
        File file = new File(player2File);
        if (file.exists()) {
            break; // Exit the loop if the file exists
        } else {
            System.out.println("File not found. Please enter a valid file name.");
        }
    }

    Game game = new Game(8, player2File);

    boolean gameEnded = false;

    while (!gameEnded) {
        game.player1Turn();
        if (game.checkWin(7, 7, 8)) {
            gameEnded = true;
            break;
        }
        game.player2Turn();
        if (game.checkWin(7, 7, 8)) {
            gameEnded = true;
            break;
        }
    }
}


    public boolean checkWin(int knightRow, int knightCol, int boardSize) {
        return knightRow == boardSize - 1 && knightCol == boardSize - 1;
    }
}


