package Recursion;

import java.util.Scanner;

public class NQueens {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int[] queens = new int[8];
            for (int i = 0; i < 8; i++) {
                queens[i] = scanner.nextInt();
            }
            int moves = minMovesToSolve(queens);
            System.out.println(moves);
        }
    }

    public static int minMovesToSolve(int[] queens) {
        int minMoves = Integer.MAX_VALUE;

        for (int row1 = 1; row1 <= 8; row1++) {
            for (int row2 = 1; row2 <= 8; row2++) {
                if (row1 == row2)
                    continue; // Avoid swapping with the same row

                int moves = 0;
                int[] tempQueens = queens.clone();
                tempQueens[0] = row1;

                for (int col = 1; col < 8; col++) {
                    if (!isValid(tempQueens, col)) {
                        moves++;
                        tempQueens[col] = row2;
                    }
                }

                if (isValid(tempQueens, 7)) {
                    minMoves = Math.min(minMoves, moves);
                }
            }
        }

        return (minMoves == Integer.MAX_VALUE) ? -1 : minMoves;
    }

    private static boolean isValid(int[] queens, int col) {
        for (int i = 0; i < col; i++) {
            if (queens[i] == queens[col] || Math.abs(queens[i] - queens[col]) == Math.abs(i - col)) {
                return false;
            }
        }
        return true;
    }
}
