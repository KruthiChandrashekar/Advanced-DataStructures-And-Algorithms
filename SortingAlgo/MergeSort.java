package SortingAlgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MergeSort {
    // Group anagram method
    public static List<List<String>> groupAnagram(List<String> strings) {
        Map<String, List<String>> anagramGroups = new HashMap<>();

        for (String str : strings) {
            String sortedStr = sortString(str.toLowerCase());

            anagramGroups.putIfAbsent(sortedStr, new ArrayList<>()); // to create a new list if the sorted string is not
                                                                     // already a key in the map.
            anagramGroups.get(sortedStr).add(str);
        }

        return new ArrayList<>(anagramGroups.values());
    }

    // sort string method
    public static String sortString(String str) {
        char[] charArray = str.toCharArray();
        char[] tempArray = new char[charArray.length];
        mergeSort(charArray, tempArray, 0, charArray.length - 1);
        return new String(charArray);
    }

    private static void mergeSort(char[] arr, char[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, temp, left, mid); // reccursively dividing the array
            mergeSort(arr, temp, mid + 1, right);
            merge(arr, temp, left, mid, right);
        }
    }

    // Merge Sort Algorithm
    private static void merge(char[] arr, char[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (i = left; i <= right; i++) {
            arr[i] = temp[i]; // arr contains sorted char array
        }
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("bucket", "Rat", "mango", "tango", "ogtan", "tar");
        // List<String> strings = Arrays.asList("h");
        List<List<String>> groups = groupAnagram(strings);

        for (List<String> group : groups) {
            System.out.println(group);
        }
    }
}
