package Hashing;

import java.util.LinkedList;

class HashTable {
    private static final int TABLE_SIZE = 100; // Random table size
    private LinkedList<String>[] table;

    // creating an hashtable
    public HashTable() {
        table = new LinkedList[TABLE_SIZE];

        for (int i = 0; i < TABLE_SIZE; i++) {
            table[i] = new LinkedList<>();
        }

    }

    // hash function
    private int hash(String x) {
        int hash = 0;

        for (char c : x.toCharArray()) {
            hash = (hash * 31 + c) % TABLE_SIZE; // Calculating HashCode
        }

        return hash;
    }

    public void insert(String x) {
        int index = hash(x);
        LinkedList<String> bucket = table[index];

        if (!bucket.contains(x)) {
            bucket.add(x);
        }
    }

    public void search(String x) {
        int index = hash(x);

        if (table[index].contains(x)) {
            System.out.println("Present");

            return;
        }

        System.out.println("Not present");

    }

    public int size() {
        int count = 0;

        for (LinkedList<String> bucket : table) {
            count += bucket.size();
        }

        return count;
    }
}
