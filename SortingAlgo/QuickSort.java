package SortingAlgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuickSort {
    public static List<List<String>> groupAnagram(List<String> strings) {
        Map<String, List<String>> anagramGroups = new HashMap<>();

        for (String str : strings) {
            String sortedStr = sortString(str.toLowerCase());

            anagramGroups.putIfAbsent(sortedStr, new ArrayList<>());// to create a new list if the sorted string is not
                                                                    // already a key in the map.
            anagramGroups.get(sortedStr).add(str);
        }

        return new ArrayList<>(anagramGroups.values());
    }

    public static String sortString(String str) {
        char[] charArray = str.toCharArray();
        quickSort(charArray, 0, charArray.length - 1);
        return new String(charArray); // converting char array back to String
    }

    private static void quickSort(char[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(char[] arr, int low, int high) {
        char pivot = arr[high]; // selecting last element as pivot
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1; // returns the index of the pivot
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("Rat", "tar", "mango");
        List<List<String>> anagramGroups = groupAnagram(strings);

        for (List<String> group : anagramGroups) {
            System.out.println(group);
        }
    }
}
