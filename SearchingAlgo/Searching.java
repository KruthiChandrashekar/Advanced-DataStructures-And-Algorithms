package SearchingAlgo;

public class Searching {

    // search method
    public int[] search(int[] nums, int target) {
        int first = firstOccurance(nums, target);
        int last = lastOccurance(nums, target);

        int[] position = { first, last };

        return position;
    }

    // Binary search method
    public int firstOccurance(int[] nums, int target) {
        int firstPosition = -1;
        int start = 0;
        int end = nums.length - 1; // Getting the last index

        while (start <= end) {
            int mid = (start + (end - start) / 2); // To find the middle of the array
            // System.out.println(mid);
            if (nums[mid] == target) {
                firstPosition = mid;
                end = mid - 1;
            }

            else if (target > nums[mid]) {
                start = mid + 1;
            }

            else {
                end = mid - 1;
            }

        }

        return firstPosition;
    }

    public int lastOccurance(int[] nums, int target) {
        int lastPosition = -1;
        int start = 0;
        int end = nums.length - 1; // Getting the last index

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                lastPosition = mid;
                start = mid + 1;
            }

            else if (target < nums[mid]) {
                end = mid - 1;
            }

            else {
                start = mid + 1;
            }

        }

        return lastPosition;
    }
}
