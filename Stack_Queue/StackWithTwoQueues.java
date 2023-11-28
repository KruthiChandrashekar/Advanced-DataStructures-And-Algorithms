package Stack_Queue;

public class StackWithTwoQueues {

    QueueImplementation queueOne = new QueueImplementation();
    QueueImplementation queueTwo = new QueueImplementation();

    int size;

    // all stack methods

    public void push(int item) {
        queueTwo.enqueue(item);

        while (queueOne.poll() != null) {
            queueTwo.enqueue(queueOne.dequeue());
        }

        QueueImplementation mainQueue = queueOne;
        // Swapping Queue names
        queueOne = queueTwo;
        queueTwo = mainQueue;

    } // pushes x in the stack.

    public int pop() {
        if (queueOne.size() == 0) {
            return -1;
        }

        return queueOne.dequeue();

    } // removes the latest element from the stack and returns it.

    // returns the latest element from the stack without removing it
    public int peek() {
        if (queueOne.size() == 0) {
            return -1;
        }

        return queueOne.poll();
    }

    // returns the size of the stack.
    public int size() {
        return queueOne.size();
    }

    public static void main(String args[]) {
        StackWithTwoQueues stack = new StackWithTwoQueues();

        stack.push(3);
        stack.push(30);
        stack.push(39);

        System.out.println(stack.size());
        System.out.println(stack.pop());
        System.out.println(stack.peek());

    }
}