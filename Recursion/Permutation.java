package Recursion;

public class Permutation {
    public boolean checkPermutation(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] countS1 = new int[52]; // 26 for lowercase letters, 26 for uppercase letters

        for (char c : s1.toCharArray()) {
            int index = getIndex(c);
            if (index != -1) {
                countS1[index]++;
            }
        }

        int left = 0, right = 0;
        int[] countWindow = new int[52];

        while (right < s2.length()) {
            char rightChar = s2.charAt(right);
            int rightIndex = getIndex(rightChar);
            if (rightIndex != -1) {
                countWindow[rightIndex]++;
            }

            while (isCountWindowExceeding(countWindow, countS1)) {
                char leftChar = s2.charAt(left);
                int leftIndex = getIndex(leftChar);
                if (leftIndex != -1) {
                    countWindow[leftIndex]--;
                }
                left++;
            }

            if (right - left + 1 == s1.length()) {
                return true;
            }
            right++;
        }

        return false;
    }

    private int getIndex(char c) { // get index from 0-51
        if (Character.isLowerCase(c)) {
            return c - 'a';
        } else if (Character.isUpperCase(c)) {
            return c - 'A' + 26;
        }
        return -1; // Character is not a letter
    }

    private boolean isCountWindowExceeding(int[] countWindow, int[] countS1) { // checks if the frequency of characters
                                                                               // in the countWindow array exceeds the
                                                                               // frequency in the countS1 array.
        for (int i = 0; i < 52; i++) {
            if (countWindow[i] > countS1[i]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Permutation result = new Permutation();

        // Test cases
        String s1_1 = "ab";
        String s2_1 = "eidBAooo";
        System.out.println(result.checkPermutation(s1_1, s2_1)); // Output: true

        String s1_2 = "aB";
        String s2_2 = "eidBoaoo";
        System.out.println(result.checkPermutation(s1_2, s2_2)); // Output: false
    }
}
