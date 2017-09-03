package queuecirculararray;

public class QueueCircularArray implements Queue {
	private Object[] queue;
	private int front = 0, back = 0;
	private int capacity;
	private static final int DEFAULT_CAPACITY = 256;
	
	public QueueCircularArray() {
		this(DEFAULT_CAPACITY);
	}

	public QueueCircularArray(int capacity) {
		// Using ternary operator in same way as in provided QueueArray.java.
		this.capacity = (capacity > 0) ? capacity : DEFAULT_CAPACITY;
		queue = new Object[capacity];
	}
	
	public boolean enqueue(Object o) {
		// Originally I checked whether it was full right before adding the object
		// to the array. However, this resulted in bugs when front was a bigger number
		// than back.
		if (isFull()) {
			return false;
		}
		int queueIndex = front;
		// Move through the array from the front until either you reach the end
		// of it or you come across an empty element.
		while (queueIndex < capacity && queue[queueIndex] != null) {
			queueIndex++;
		}
		// This next if statement deals with when the queue goes around in a circle,
		// i.e. when the front is somewhere other than the first element of the array
		// and when the first element of the array is not empty.
		if (front != 0 && queue[0] != null) {
			queueIndex = 0; // Since the queue loops back around to zero, this must be set to 0.
			while (queue[queueIndex] != null) { // i.e, until reaching an empty element.
				queueIndex++;
			}
		}
		// This next line deals with when queueIndex is equal to capacity without
		// interfering with the number otherwise.
		back = queueIndex % capacity;
		queue[back] = o;
		return true;
	}
	
	public Object dequeue() {
		// Originally I tried assigning queue[front] directly to objectInFront.
		// Since objectInFront just holds a reference, I could not make queue[front]
		// null without also making objectInFront null. I tried coming up with
		// a bunch of messy workarounds which didn't really work.
		//
		// However, after a bit of research online I found out about the getClass()
		// method and Class objects. I was now able to assign null to queue[front],
		// as it was no longer being referenced by objectInFront.
		
		Class<?> objectClass = queue[front].getClass();
		// NB. The next line casts queue[front] as an Object, so it is still
		// necessary to cast the return value to a subclass.
		Object objectInFront = objectClass.cast(queue[front]);
		queue[front] = null;
		front++;
		// If front has moved all the way around the array, put it back to the start.
		if (front >= capacity) {
			front = 0;
		}
		return objectInFront;
	}
	
	public boolean isEmpty() {
		return queue[front] == null;
	}
	
	public boolean isFull() {
		// The last place where an object can join the queue is capacity - 1 if
		// the queue does not loop around the array and the front is 0. Otherwise,
		// it is the number directly behind front.
		int lastIndex = front == 0 ? capacity - 1 : front - 1;
		return queue[lastIndex] != null;
	}
	
	public Object front() {
		return queue[front];
	}

}
