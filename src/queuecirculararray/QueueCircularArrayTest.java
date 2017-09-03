package queuecirculararray;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QueueCircularArrayTest {
	// Instead of putting a number of Message Dialogs on the screen which the
	// programmer must manually check, as in QueueTest.java, I have instead gone
	// for the more formal and proper means of JUnit tests.
	QueueCircularArray testQueue;

	@Before
	public void setUp() throws Exception {
		testQueue = new QueueCircularArray(4);
		testQueue.enqueue("JUnit"); // Strings are objects.
		testQueue.enqueue("Test");
		testQueue.enqueue("Code");
	}

	@Test
	public void testEnqueue() {
		boolean isSuccess = testQueue.enqueue("Queue");
		assertTrue(isSuccess);
		isSuccess = testQueue.enqueue("Testing");
		assertFalse(isSuccess);
	}
	
	@Test
	public void testDequeue() {
		String actual = (String) testQueue.dequeue();
		assertNotEquals("Code", actual); // "Code" should still be in the queue at the back.
		actual = (String) testQueue.dequeue();
		assertEquals("Test", actual);
	}
	
	@Test
	public void testEnqueueCircular() {
		testQueue.dequeue();
		testQueue.dequeue();
		testQueue.enqueue("Java");
		testQueue.enqueue("Queue"); // This should circle back to queue[0] here.
		
		boolean isSuccess = testQueue.enqueue("Algorithm");
		assertTrue(isSuccess);
		isSuccess = testQueue.enqueue("Program");
		assertFalse(isSuccess);
	}
	
	@Test
	public void testDequeueCircular() {
		testQueue.enqueue("Method");
		testQueue.dequeue();
		testQueue.dequeue(); // These 3 lines remove the items placed in queue[] in setUp().
		testQueue.dequeue();
		testQueue.enqueue(".java");
		
		String correctDequeueCheck = (String) testQueue.dequeue();
		assertNotEquals(".java", correctDequeueCheck); // Should be "Method".
		String actuallyDequeued = (String) testQueue.dequeue();
		assertEquals(".java", actuallyDequeued);
	}
	
	@Test
	public void testFront() {
		String actualFront = (String) testQueue.front();
		assertEquals("JUnit", actualFront);
	}
	
	@Test
	public void testIsEmpty() {
		assertFalse(testQueue.isEmpty());
		testQueue.dequeue();
		testQueue.dequeue();
		testQueue.dequeue();
		assertTrue(testQueue.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(testQueue.isFull());
		testQueue.enqueue("Queue");
		assertTrue(testQueue.isFull());
	}
	
	@Test
	public void testIsEmptyCircular() {
		testQueue.dequeue();
		testQueue.dequeue();
		testQueue.enqueue("String"); // queue[capacity - 1]
		testQueue.dequeue();
		testQueue.enqueue("Programming"); // queue[0]
		testQueue.dequeue(); // After this line, there should only be an element in queue[0].
		
		assertFalse(testQueue.isEmpty());
		
		testQueue.dequeue();
		assertTrue(testQueue.isEmpty());
	}
	
	@Test
	public void testIsFullCircular() {
		testQueue.dequeue();
		testQueue.enqueue("Stack");
		assertFalse(testQueue.isFull());
		testQueue.enqueue("Code");
		assertTrue(testQueue.isFull());
	}
	
}
