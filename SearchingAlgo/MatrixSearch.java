package SearchingAlgo;

public class MatrixSearch {
    public boolean search(int[][] matrix, int target) {
        if (matrix == null) { // checking if the matrix is empty
            return false;
        }

        int m = matrix.length; // number of rows
        int n = matrix[0].length;
        int left = 0;
        int right = m * n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // middle element in the matrix
            int row = mid / n;
            int col = mid % n;
            int value = matrix[row][col]; // 2D array position

            if (value == target) {
                return true;
            } else if (value < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        MatrixSearch search = new MatrixSearch();

        int[][] matrix = {
                { 6, 7, 8 },
                /*
                 * { 10, 11, 16, 20 },
                 * { 23, 30, 34, 60 }
                 */
        };

        // Test cases
        System.out.println(search.search(matrix, 6)); // true
        System.out.println(search.search(matrix, 5)); // false
    }
}
