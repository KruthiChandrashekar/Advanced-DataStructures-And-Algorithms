package Hashing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Anagram {
    public static void main(String[] args) {
        HashTable anagramRootsTable = new HashTable();

        try (BufferedReader br = new BufferedReader(new FileReader("Hashing/book.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                // Split the line into words using non-alphanumeric characters as delimiters
                String[] words = line.split("[^a-zA-Z0-9]+");

                // Process each word and insert its anagram root into the hash table
                for (String word : words) {

                    if (!word.isEmpty()) {
                        char[] chars = word.toLowerCase().toCharArray();

                        Arrays.sort(chars);

                        String sortedWord = new String(chars);

                        anagramRootsTable.insert(sortedWord);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Calculate the number of unique anagram roots using the size() function
        int uniqueAnagramRoots = anagramRootsTable.size();
        System.out.println("Number of Unique Anagram Roots: " + uniqueAnagramRoots);
    }
}
