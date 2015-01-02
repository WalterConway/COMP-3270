import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class PriorityQueueTest {
	
	@Rule
	  public ExpectedException exception = ExpectedException.none();
	
	PriorityQueue priorityQue;
	ELEM[] elemArray;
	heap testHeap;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		elemArray =  new ELEM[10];		
		priorityQue = new PriorityQueue(elemArray, 10);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPriorityQueue() {
		elemArray =  new ELEM[10];		
		priorityQue = new PriorityQueue(elemArray, 10);
		assertTrue(priorityQue.getHeap().isEmpty());
		assertEquals(0, priorityQue.getHeap().heapSize());

	}

	@Test
	public void testEnqueue() throws DuplicateObjectIDException, ObjectIDOutOfBoundsException, FullHeapException {
		boolean thrown = false;
		priorityQue.enqueue(0, 90);
		assertEquals(1, priorityQue.getHeap().heapSize());
		assertFalse(priorityQue.getHeap().isEmpty());
		assertEquals(0, priorityQue.getHeap().findCurrentLocationInHeap(0));
		
		priorityQue.enqueue(1, 15);
		priorityQue.enqueue(2, 91);
		priorityQue.enqueue(3, 18);
		
		assertEquals(4, priorityQue.getHeap().heapSize());
		assertEquals(0, priorityQue.getHeap().findCurrentLocationInHeap(2));
		assertEquals(3, priorityQue.getHeap().findCurrentLocationInHeap(1));
		
		try {
			priorityQue.enqueue(3, 10);
		} catch (DuplicateObjectIDException e) {
			thrown = true;	
		}
		assertTrue(thrown);
		
		thrown = false;
		
		//Need to test for -1
		
		try {
			priorityQue.enqueue(10, 99);
		} catch (ObjectIDOutOfBoundsException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
		
		priorityQue.enqueue(4, 19);
		priorityQue.enqueue(5, 2);
		priorityQue.enqueue(6, 8);
		priorityQue.enqueue(7, 24);
		priorityQue.enqueue(8, 44);
		priorityQue.enqueue(9, 80);
		
		
		thrown = false;
		
		try {
			priorityQue.enqueue(1, 45);
		} catch (FullHeapException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
		
		//fail("Not yet implemented");
	}

	@Test (expected = EmptyHeapException.class)
	public void testDequeue() throws EmptyHeapException, DuplicateObjectIDException, FullHeapException, ObjectIDOutOfBoundsException {
		priorityQue.enqueue(0, 15);
		priorityQue.enqueue(1, 4);
		priorityQue.enqueue(2, 7);
		
		assertEquals(0, priorityQue.dequeue());
		assertEquals(2, priorityQue.dequeue());
		assertEquals(1, priorityQue.dequeue());
		
		//Causes Exception
		priorityQue.dequeue();
		
	}
	
	@Test
	public void testChangeWeight() throws DuplicateObjectIDException, FullHeapException, EmptyHeapException, ObjectIDOutOfBoundsException {
		boolean thrown = false;
		
		try {
			priorityQue.changeWeight(0, 15);
		} catch (EmptyHeapException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
		
		thrown = false;
		priorityQue.enqueue(0, 10);
		priorityQue.enqueue(1, 12);
		assertEquals(0, priorityQue.getHeap().findCurrentLocationInHeap(1));
		
		priorityQue.changeWeight(0, 15);
		//Test for correct Weight of Object ID
		assertEquals(15, priorityQue.getWeightOfObject_ID(0));
		//Test for correct update of location in heap
		assertEquals(0, priorityQue.getHeap().findCurrentLocationInHeap(0));
		
		
	}

	@Test
	public void testGetHeap() {
		elemArray =  new ELEM[10];		
		priorityQue = new PriorityQueue(elemArray, 10);
		assertEquals(0, priorityQue.getHeap().heapSize());
		try {
			priorityQue.enqueue(0, 10);
		} catch (DuplicateObjectIDException e) {
			e.printStackTrace();
		} catch (FullHeapException e) {
			e.printStackTrace();
		} catch (ObjectIDOutOfBoundsException e) {
			e.printStackTrace();
		}
		
		assertEquals(1, priorityQue.getHeap().heapSize());

	}

	@Test
	public void testGetWeightOfObject_ID() throws DuplicateObjectIDException, FullHeapException, ObjectIDOutOfBoundsException, EmptyHeapException {
		priorityQue.enqueue(0, 15);
		assertEquals(15, priorityQue.getWeightOfObject_ID(0));
		
	}

}
