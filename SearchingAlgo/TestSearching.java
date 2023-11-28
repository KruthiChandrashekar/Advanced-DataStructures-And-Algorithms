package SearchingAlgo;

public class TestSearching {
    public static void main(String args[]) { // main method

        Searching search = new Searching();

        // int[] nums = { 2, 10, 14, 15, 100, 110 }; // input
        int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8 };
        // int[] nums = { 1, 1, 1, 1, 1 };
        // int[] nums = { 1 };

        int target = 8; // input
        // int target = 1;
        // int target = 6;

        int[] result = search.search(nums, target);
        System.out.println("First: " + result[0] + "\nLast: " + result[1]);

    }

}
