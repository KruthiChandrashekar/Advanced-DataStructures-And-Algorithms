// Test cases

package Stack_Queue;

public class TestQueueImplementation {
	public static void main(String[] args) {
		QueueImplementation queue = new QueueImplementation();
		queue.enqueue(10);
		queue.enqueue(1);
		queue.enqueue(11);

		System.out.println(queue.dequeue());
		System.out.println(queue.poll());
		System.out.println(queue.size());
	}
}