package Stack_Queue;

//Task 1: Implement Stack using LinkedList
public class StackUsingLL {
    class Node {
        double data;
        Node next;

        public Node(double data) {
            this.data = data;
            this.next = null;
        }
    }

    Node top; // reference variable top to indicate top of the stack
    int size;

    public StackUsingLL() {
        this.top = null;
        size = 0;
    }

    // method to insert/push values into the stack
    public void push(double element) {
        Node newData = new Node(element);
        newData.next = top;
        top = newData;
        size++;
    }

    // method to delete/pop values
    public double pop() {
        if (isStackEmpty()) {
            System.out.println("The Stack is Empty!!");
        }
        double temp = top.data;
        top = top.next;
        size--;
        return temp;

    }

    // checking if the stack is empty
    public boolean isStackEmpty() {
        return top == null;
    }

    // method to return the top value
    public double peek() {
        if (isStackEmpty()) {
            System.out.println("The Stack is Empty!!");
        }
        return top.data;
    }

    // method to find the size of the stack
    public int size() {
        return size;
    }

}
