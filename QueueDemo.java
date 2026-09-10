
import java.util.*;

//memort O(2n) double the amount of size as of refernce tracking
class LinkedQueue<E> {

	private Node<E> front;
	private Node<E> rear;

	public E remove() {
		if(front == null) {
			throw new NoSuchElementException();
		}
		E result = front.data;
		front = front.next;
		//case for 1 element only
		if(front == null) {
			rear = null;
		}
		return result;
	}

	public E poll() {
		if(front == null) {
			return null;
		}
		E result = front.data;
		front = front.next;
		//case for 1 element only
		if(front == null) {
			rear = null;
		}
		return result;
	}



	public E peek() {
		if(front == null) {
			return null;
		}
		return this.front.data;
	}

	public E element() {
		if(front == null) {
			throw new NoSuchElementException();
		}
		return this.front.data;
	}

	public boolean offer(E item) {
		if(front == null) {
			front = new Node<>(item);
			rear = front;
		}
		else {
			rear.next = new Node<>(item);
			rear = rear.next;
		}
		return true;
	}

	private static class Node<E> {
		private E data;
		private Node<E> next;

		private Node(E data) {
			this.data = data;
		}
	}
}

public class QueueDemo
{
	public static void main(String[] args) {
		LinkedQueue<String> name = new LinkedQueue<>();
		name.offer("mary");
		name.offer("tony");
		name.offer("miguel");
		name.offer("peter");
		
    	System.out.println("Person to be removed: " + name.remove());
    	System.out.println("Person at front: " + name.element());
    	
        while (name.peek() != null) {
            System.out.println(name.poll());
    	}

	}
}
