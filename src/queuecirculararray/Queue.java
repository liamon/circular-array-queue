package queuecirculararray;

public interface Queue {
	public boolean enqueue(Object o); // Changed to return true if successful, false if not.
	public Object dequeue();
	public boolean isEmpty();
	public boolean isFull();
	public Object front();
}
