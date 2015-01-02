
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class heapTest {
	heap Heap;
	ELEM[] elemArray;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@Before
	public void setUp() throws Exception {

		elemArray =  new ELEM[10];		
		for(int i = 0; i < 10; i++){
			elemArray[i] = new ELEM(i,i);
		}
		Heap = new heap(elemArray, 0, 10);
	}

	@After
	public void tearDown() throws Exception {
		Heap = null;
	}


	@Test
	public void testHeapSize() {
		assertEquals(0, Heap.heapSize());
		Heap.buildHeap();
		assertEquals(10,Heap.heapSize());

	}

	@Test
	public void testIsLeaf() {
		Heap.buildHeap();
		for(int i = 5; i < 10; i++){
			assertTrue(Heap.isLeaf(i));
		}
	}

	@Test
	public void testLeftChild() {
		Heap.buildHeap();
		assertEquals(Heap.getWeight(Heap.leftChild(0)), 8);
		assertEquals(Heap.getWeight(Heap.leftChild(1)), 7);
		assertEquals(Heap.getWeight(Heap.leftChild(2)), 5);
		assertEquals(Heap.getWeight(Heap.leftChild(3)), 0);
		assertEquals(Heap.getWeight(Heap.leftChild(4)), 1);
	}

	@Test
	public void testRightChild() {
		Heap.buildHeap();
		assertEquals(Heap.getWeight(Heap.rightChild(0)), 6);
		assertEquals(Heap.getWeight(Heap.rightChild(1)), 4);
		assertEquals(Heap.getWeight(Heap.rightChild(2)), 2);
		assertEquals(Heap.getWeight(Heap.rightChild(3)), 3);
	}

	@Test
	public void testParent() {
		Heap.buildHeap();
		assertEquals(Heap.getWeight(Heap.parent(1)), 9);
		assertEquals(Heap.getWeight(Heap.parent(2)), 9);
		assertEquals(Heap.getWeight(Heap.parent(3)), 8);
		assertEquals(Heap.getWeight(Heap.parent(4)), 8);
		assertEquals(Heap.getWeight(Heap.parent(5)), 6);
		assertEquals(Heap.getWeight(Heap.parent(6)), 6);
		assertEquals(Heap.getWeight(Heap.parent(7)), 7);
		assertEquals(Heap.getWeight(Heap.parent(8)), 7);
		assertEquals(Heap.getWeight(Heap.parent(9)), 4);
	}

	@Test
	public void testInsert() {
		Heap = null;
		Heap = new heap(new ELEM[10], 0, 10);
		int[] actualArray = new int[10];
		for(int i=0;i<10;i++){
			try {
				Heap.insert(elemArray[i]);
			} catch (DuplicateObjectIDException | FullHeapException
					| ObjectIDOutOfBoundsException e) {
				fail();
			}
		}

		for(int i = 0;i<10;i++){
			actualArray[i] = Heap.getHeapArray()[i].getmObject_ID();
		}
		assertArrayEquals(new int[] {9,8,5,6,7,1,4,0,3,2}, actualArray);
	}

	@Test
	public void testRemoveMax() {
		Heap.buildHeap();
		for(int i=0; i < 10; i++){
			try {
				assertEquals(Heap.removeMax().getmObject_ID(), 9-i);
			} catch (EmptyHeapException e) {
				fail();
			}
		}
	}

	@Test
	public void testRemove() {
		Heap.buildHeap();
		try {
			assertEquals(Heap.remove(5).getmObject_ID(), 5);
		} catch (EmptyHeapException e) {
			fail();
		}
		int[] actualArray = new int[9];

		for(int i = 0;i<9;i++){
			actualArray[i] = Heap.getHeapArray()[i].getmObject_ID();
		}
		assertArrayEquals(new int[] {9,8,6,7,4,1,2,0,3}, actualArray);
	}

	@Test
	public void testBuildHeap() {
		Heap.buildHeap();
		int[] actualArray = new int[10];

		for(int i = 0;i<10;i++){
			actualArray[i] = Heap.getHeapArray()[i].getmObject_ID();
		}
		assertArrayEquals(new int[] {9,8,6,7,4,5,2,0,3,1}, actualArray);
	}

	@Test
	public void testFindCurrentLocationInHeap() {
		Heap.buildHeap();
		assertArrayEquals(new int[] {7,9,6,8,4,5,2,3,1,0}, Heap.getLocationArray());
	}

	@Test
	public void testIsEmpty() {
		Heap.buildHeap();
		for(int i = 0; i < 10; i++){
			try {
				Heap.removeMax();
			} catch (EmptyHeapException e) {
				e.printStackTrace();
			}
		}
		assertEquals(true,Heap.isEmpty());
	}
	@Test
	public void testIsEmptyException(){

		Heap.buildHeap();

		for(int i=0 ; i < 10 ; i++){
			try {
				Heap.removeMax();

			} catch (EmptyHeapException e) {
			}
		}

		try {
			Heap.removeMax();
			fail();
		} catch (EmptyHeapException e) {

		}
	}

	@Test
	public void testHeapChangeWeight(){
		Heap.buildHeap();
		Heap.heapChangeWeight(5, 90);
		int[] actualArray = new int[10];

		for(int i = 0;i<10;i++){
			actualArray[i] = Heap.getHeapArray()[i].getmObject_ID();
		}
		assertArrayEquals(new int[] {5,8,9,7,4,6,2,0,3,1}, actualArray);

	}

}
