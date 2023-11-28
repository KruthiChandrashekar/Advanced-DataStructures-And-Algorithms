package Tree;

import java.util.List;
import java.util.ArrayList;

public class BSTToHeapTransformer {
    static List<TreeNode> students = new ArrayList<TreeNode>();

    private static void print(TreeNode[] node) {
        for (int i = 0; i < node.length; i += 1) {
            System.out.println(node[i].lastName);
        }
    }

    private static TreeNode[] bstToMaxHeap(TreeNode[] studentArr) {
        TreeNode[] maxHeap = new TreeNode[studentArr.length];

        for (int i = studentArr.length - 1; i >= 0; i -= 1) {
            maxHeap[maxHeap.length - 1 - i] = studentArr[i];
        }

        return maxHeap;
    }

    private static TreeNode[] bstToMinHeap(TreeNode[] studentArr) {

        for (int i = 0; i < studentArr.length; i += 1) {
            studentArr[i] = students.get(i);
        }

        return studentArr;
    }

    public static void traverseTree(TreeNode root) {
        if (root != null) {
            traverseTree(root.left);
            students.add(root);
            traverseTree(root.right);
        }
    }

    public void createHeaps(TreeNode root) {
        traverseTree(root);
        TreeNode[] studentArr = new TreeNode[students.size()];

        System.out.println("\nMin Heap: ");
        print(bstToMinHeap(studentArr));

        System.out.println("\nMax Heap: ");
        print(bstToMaxHeap(studentArr));
    }

}
