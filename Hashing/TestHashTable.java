package Hashing;

public class TestHashTable {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable();

        // Inserting elements
        hashTable.insert("apple");
        hashTable.insert("banana");
        hashTable.insert("cherry");
        hashTable.insert("abc");
        hashTable.insert("cba");

        hashTable.search("apple");

        // Checking the size
        System.out.println("Size: " + hashTable.size());
    }
}
