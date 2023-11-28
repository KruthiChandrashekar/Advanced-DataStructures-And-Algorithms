package Stack_Queue;

import java.util.LinkedList;

public class QueueImplementation {
	LinkedList<Integer> queue = new LinkedList<Integer>();

	public Integer enqueue(Integer number) {
		queue.addLast(number);

		return number;
	}

	public Integer dequeue() {
		if (queue.size() == 0)
			return null;

		return queue.removeFirst();
	}

	public Integer poll() {
		if (queue.size() == 0)
			return null;

		return queue.getFirst();
	}

	public Integer size() {
		return queue.size();
	}
}
