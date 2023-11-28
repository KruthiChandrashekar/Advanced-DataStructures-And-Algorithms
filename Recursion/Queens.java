package Recursion;

import java.util.Scanner;

public class Queens {
    // main method taking inputs from the user
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int[] queens = new int[8];
            for (int i = 0; i < 8; i++) {
                queens[i] = scanner.nextInt();
            }
            int moves = minimumMoves(queens);
            System.out.println(moves);
        }
    }

    private static int minimumMoves(int[] queens) { // recursive
        return solve(queens, 0);
    }

    private static int solve(int[] queens, int col) {
        if (col >= 8) { // all the queens are already placed
            return 0;
        }
        int minMoves = Integer.MAX_VALUE;
        for (int row = 1; row <= 8; row++) {
            if (isValid(queens, col, row)) {
                int[] newQueens = queens.clone();
                newQueens[col] = row;
                int moves = solve(newQueens, col + 1);
                if (moves != -1) {
                    minMoves = Math.min(minMoves, moves + (row != queens[col] ? 1 : 0));
                }
            }
        }
        return (minMoves == Integer.MAX_VALUE) ? -1 : minMoves;
    }

    // method to check if the move is valid
    private static boolean isValid(int[] queens, int col, int row) {
        for (int prevCol = 0; prevCol < col; prevCol++) {
            if (queens[prevCol] == row || Math.abs(queens[prevCol] - row) == Math.abs(prevCol - col)) {
                return false;
            }
        }
        return true;
    }
}
