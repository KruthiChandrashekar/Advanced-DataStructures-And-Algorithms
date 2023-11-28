package Heap;

import java.util.List;
import java.util.ArrayList;

class HeapBuilder {
    public static class Node {
        int value;
        Node left;
        Node right;

        // constructor
        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    // Helper method to build Max-Heap
    private static void buildMaxHeap(List<Integer> list, int index) {
        int largest = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        if (leftChild < list.size() && list.get(leftChild) > list.get(largest)) {
            largest = leftChild;
        }

        if (rightChild < list.size() && list.get(rightChild) > list.get(largest)) {
            largest = rightChild;
        }

        if (largest != index) {
            // Swap the elements at index and largest
            int temp = list.get(index);
            list.set(index, list.get(largest));
            list.set(largest, temp); // recurssive
        }
    }

    // Helper method to build Min-Heap
    private static void buildMinHeap(List<Integer> list, int index) {
        int smallest = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        if (leftChild < list.size() && list.get(leftChild) < list.get(smallest)) {
            smallest = leftChild;
        }

        if (rightChild < list.size() && list.get(rightChild) < list.get(smallest)) {
            smallest = rightChild;
        }

        if (smallest != index) {
            // Swap the elements at index and smallest
            int temp = list.get(index);
            list.set(index, list.get(smallest));
            list.set(smallest, temp);

            buildMinHeap(list, smallest);
        }
    }

    // Public method to build Max-Heap binary tree
    public static Node createMaxHeap(List<Integer> list) {
        int size = list.size(); // size of the input array
        for (int i = size / 2 - 1; i >= 0; i--) { // looping through range of internal node - right to left
            buildMaxHeap(list, i);
        }
        return buildHeapTree(list, 0);
    }

    // Public method to build Min-Heap binary tree
    public static Node createMinHeap(List<Integer> list) {
        int size = list.size();
        for (int i = size / 2 - 1; i >= 0; i--) {
            buildMinHeap(list, i);
        }
        return buildHeapTree(list, 0);
    }

    // Helper method to build binary tree from heap
    private static Node buildHeapTree(List<Integer> list, int index) {
        if (index >= list.size()) {
            return null;
        }

        Node node = new Node(list.get(index));
        node.left = buildHeapTree(list, 2 * index + 1); // left child
        node.right = buildHeapTree(list, 2 * index + 2); // right child

        return node;
    }

    public static void main(String[] args) {
        // Sample test cases
        List<Integer> maxHeapInput = new ArrayList<>(List.of(9, 4, 7, 1, 2, 6, 5));
        List<Integer> minHeapInput = new ArrayList<>(List.of(1, 5, 3, 8, 6, 7, 2));

        // Building Max-Heap and Min-Heap trees
        HeapBuilder.Node maxHeapTree = HeapBuilder.createMaxHeap(maxHeapInput);
        HeapBuilder.Node minHeapTree = HeapBuilder.createMinHeap(minHeapInput);

        // Print Max-Heap and Min-Heap trees (in-order traversal)
        System.out.println("Max-Heap Tree (In-order Traversal):");
        printInOrder(maxHeapTree);
        System.out.println();

        System.out.println("Min-Heap Tree (In-order Traversal):");
        printInOrder(minHeapTree);
    }

    // In-order traversal helper method
    private static void printInOrder(HeapBuilder.Node node) {
        if (node == null) {
            return;
        }

        printInOrder(node.left); // print the nodes of the binary trees in sorted order.
        System.out.print(node.value + " ");
        printInOrder(node.right);
    }
}
