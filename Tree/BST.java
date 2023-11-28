package Tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class TreeNode {
    int studentNumber;
    String lastName;
    String dept;
    String prog;
    int year;
    TreeNode left, right;

    TreeNode(int studentNumber, String lastName, String dept, String prog, int year) {
        this.studentNumber = studentNumber;
        this.lastName = lastName;
        this.dept = dept;
        this.prog = prog;
        this.year = year;
    }
}

public class BST {
    TreeNode root;
    private static Queue<TreeNode> bfsQueue = new LinkedList<>();

    public BST() {
        this.root = null;
    }

    private TreeNode insertNode(TreeNode node, TreeNode newRoot) {
        if (newRoot == null) {
            return node;
        } else if (node.lastName.compareToIgnoreCase(newRoot.lastName) < 0) {
            newRoot.left = insertNode(node, newRoot.left);
        } else {
            newRoot.right = insertNode(node, newRoot.right);
        }

        return newRoot;
    }

    public TreeNode insert(int studentNumber, String lastName, String dept, String prog, int year) {
        TreeNode newNode = new TreeNode(studentNumber, lastName, dept, prog, year);

        this.root = insertNode(newNode, this.root);

        return newNode;
    }

    public TreeNode delete(TreeNode root, String lastName) {
        // If current root is null
        if (root == null)
            return root;

        // if last name is less than current node
        if (root.lastName.compareToIgnoreCase(lastName) > 0) {
            root.left = delete(root.left, lastName);

            return root;

            // If last name is more than current node
        } else if (root.lastName.compareToIgnoreCase(lastName) < 0) {
            root.right = delete(root.right, lastName);

            return root;
        }

        // If there's no left sub child
        if (root.left == null) {
            TreeNode temp = root.right;

            return temp;

            // If there's no right sub child
        } else if (root.right == null) {
            TreeNode temp = root.left;

            return temp;
        }

        // If there are 2 child
        else {

            TreeNode nextLastNameParent = root;
            TreeNode nextLastName = root.right;

            while (nextLastName.left != null) {
                nextLastNameParent = nextLastName;
                nextLastName = nextLastName.left;
            }

            if (nextLastNameParent != root)
                nextLastNameParent.left = nextLastName.right;
            else
                nextLastNameParent.right = nextLastName.right;

            root.lastName = nextLastName.lastName;

            return root;
        }

    }

    private void inOrderTraversal() {
        TreeNode node = this.root;

        if (node == null) {
            return;
        }

        inOrderTraversal(node.left);
        System.out.println(node.lastName);
        inOrderTraversal(node.right);

    }

    private static void inOrderTraversal(TreeNode node) {

        if (node == null) {
            return;
        }

        inOrderTraversal(node.left);
        System.out.println(node.lastName);
        inOrderTraversal(node.right);

    }

    private void preOrderTraversal() {
        TreeNode node = this.root;

        if (node == null) {
            return;
        }

        System.out.println(node.lastName);
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);

    }

    private static void preOrderTraversal(TreeNode node) {

        if (node == null) {
            return;
        }

        System.out.println(node.lastName);
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    private void postOrderTraversal() {
        TreeNode node = this.root;

        if (node == null) {
            return;
        }

        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.println(node.lastName);

    }

    private static void postOrderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }

        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.println(node.lastName);
    }

    private void levelOrderTraversal() {
        TreeNode node = this.root;

        System.out.println(node.lastName);

        if (node.left != null)
            bfsQueue.add(node.left);

        if (node.right != null)
            bfsQueue.add(node.right);

        if (bfsQueue.peek() != null) {
            levelOrderTraversal(bfsQueue.poll());
        }
    }

    private static void levelOrderTraversal(TreeNode node) {
        System.out.println(node.lastName);

        if (node.left != null)
            bfsQueue.add(node.left);

        if (node.right != null)
            bfsQueue.add(node.right);

        if (bfsQueue.peek() != null) {
            levelOrderTraversal(bfsQueue.poll());
        }
    }

    public static void main(String[] ar) throws FileNotFoundException {
        BST tree = new BST();
        Scanner scanner = new Scanner(new File("Tree/input.txt"));

        while (scanner.hasNextLine()) {
            String student = scanner.nextLine();
            int studentNumber = Integer.parseInt(student.substring(1, 8));
            String lastName = student.substring(8, 33);
            String dept = student.substring(33, 37);
            String prog = student.substring(37, 41);
            int year = Integer.parseInt(student.substring(41, 42));

            tree.insert(studentNumber, lastName, dept, prog, year);
        }

        System.out.println("\nDFS: Inorder Traversal");
        tree.inOrderTraversal();

        System.out.println("\nDFS: Pre Order Traversal");
        tree.preOrderTraversal();

        System.out.println("\nDFS: Post Order Traversal");
        tree.postOrderTraversal();

        System.out.println("\nLevel Order Traversal: ");
        tree.levelOrderTraversal();

        // This is for 5th assignment task 2
        BSTToHeapTransformer bstToHeap = new BSTToHeapTransformer();
        bstToHeap.createHeaps(tree.root);
        // End here

        Scanner deleteFile = new Scanner(new File("Tree/delete.txt"));

        while (deleteFile.hasNextLine()) {
            String student = deleteFile.nextLine();
            String lastName = student.substring(8, 33);
            System.out.println(lastName);

            tree.delete(tree.root, lastName);
        }

        System.out.println("\nInorder: ");
        tree.inOrderTraversal();

    }
}